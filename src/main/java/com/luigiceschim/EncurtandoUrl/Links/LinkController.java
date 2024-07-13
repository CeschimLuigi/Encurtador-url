package com.luigiceschim.EncurtandoUrl.Links;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
public class LinkController {

    @Autowired
    private LinkService service;

    private LinkRepository repository;

    @PostMapping
    public ResponseEntity<LinkResponse> generateUrl(@RequestBody LinkRequestPayload payload){

        Link link = this.service.shortenUrl(payload.longUrl());

        String redirect = "http://localhost:8080/r/" + link.getShortUrl();

        return ResponseEntity.status(HttpStatus.CREATED).body(new LinkResponse(link.getId(), link.getLongUrl(), redirect,link.getCreatedAt()));


    }


    @GetMapping("/r/{shortUrl}")
    public void redirection(@PathVariable String shortUrl, HttpServletResponse response) throws IOException {

        Link link = this.service.getOriginalLink(shortUrl);



    if (link != null){
        response.sendRedirect(link.getLongUrl());
        System.out.println(link.getLongUrl());

    } else {
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
    }

    }

}
