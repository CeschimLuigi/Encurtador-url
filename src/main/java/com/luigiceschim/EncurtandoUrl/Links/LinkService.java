package com.luigiceschim.EncurtandoUrl.Links;


import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class LinkService {


    @Autowired
    private LinkRepository linkRepository;


    public String generateUrl(){
        return RandomStringUtils.randomAlphanumeric(5,10);
    }

    public Link shortenUrl(String longUrl){

        Link link = new Link();

        link.setShortUrl(generateUrl());
        link.setLongUrl(longUrl);
        link.setCreatedAt(LocalDateTime.now());

        return this.linkRepository.save(link);

    }

    public Link getOriginalLink(String shortUrl){

        try {
            return linkRepository.findByShortUrl(shortUrl);

        } catch (Exception e){
             throw new RuntimeException("Not Found!" , e  );
        }


    }

}
