package com.clouway.EndpointPackage;

public class EndpointFilter {
        private final Endpoint[] endpoints;

        public EndpointFilter(Endpoint... endpoints) {
            this.endpoints = endpoints;
        }

    /**
     * Filters endpoints in url
     * @param url url filtered
     * @return true if one endpoint matches with url
     */
    public boolean shouldFilter(String url) {
            for (Endpoint each : endpoints) {
                if (each.matches(url)) {
                    return true;
                }
            }
            return false;
        }
    }

