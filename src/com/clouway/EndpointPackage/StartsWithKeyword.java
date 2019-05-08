package com.clouway.EndpointPackage;

public class StartsWithKeyword implements Endpoint {
    private String endpoint;

    public StartsWithKeyword(String endpoint) {
        this.endpoint = endpoint;
    }

    /**
     * Splits url into endpoints and returns first endpoint
     * @param url ult being split
     * @return a word after 4th "/"
     */
    @Override
    public boolean matches(String url) {
        return url.split("/")[3].startsWith(endpoint);
    }
}
