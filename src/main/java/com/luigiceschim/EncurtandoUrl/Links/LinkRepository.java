package com.luigiceschim.EncurtandoUrl.Links;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepository extends JpaRepository<Link,Long> {

    Link findByShortUrl(String shortUrl);

}
