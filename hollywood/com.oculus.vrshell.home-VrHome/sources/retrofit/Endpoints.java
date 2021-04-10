package retrofit;

public final class Endpoints {
    private static final String DEFAULT_NAME = "default";

    private Endpoints() {
    }

    public static Endpoint newFixedEndpoint(String url) {
        return new FixedEndpoint(url, DEFAULT_NAME);
    }

    public static Endpoint newFixedEndpoint(String url, String name) {
        return new FixedEndpoint(url, name);
    }

    private static class FixedEndpoint implements Endpoint {
        private final String apiUrl;
        private final String name;

        FixedEndpoint(String apiUrl2, String name2) {
            this.apiUrl = apiUrl2;
            this.name = name2;
        }

        @Override // retrofit.Endpoint
        public String getUrl() {
            return this.apiUrl;
        }

        @Override // retrofit.Endpoint
        public String getName() {
            return this.name;
        }
    }
}
