package retrofit;

public final class Endpoints {
    public static final String DEFAULT_NAME = "default";

    public static class FixedEndpoint implements Endpoint {
        public final String apiUrl;
        public final String name;

        @Override // retrofit.Endpoint
        public String getName() {
            return this.name;
        }

        @Override // retrofit.Endpoint
        public String getUrl() {
            return this.apiUrl;
        }

        public FixedEndpoint(String str, String str2) {
            this.apiUrl = str;
            this.name = str2;
        }
    }

    public static Endpoint newFixedEndpoint(String str) {
        return new FixedEndpoint(str, "default");
    }

    public static Endpoint newFixedEndpoint(String str, String str2) {
        return new FixedEndpoint(str, str2);
    }
}
