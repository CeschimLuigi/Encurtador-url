package com.luigiceschim.EncurtandoUrl.Links;

import java.time.LocalDateTime;

public record LinkResponse(
        Long id, String longUrl, String shortUrl, LocalDateTime createdAt
) {
}
