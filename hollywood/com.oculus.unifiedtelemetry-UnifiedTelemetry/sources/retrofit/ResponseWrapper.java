package retrofit;

import retrofit.client.Response;

public final class ResponseWrapper {
    public final Response response;
    public final Object responseBody;

    public ResponseWrapper(Response response2, Object obj) {
        this.response = response2;
        this.responseBody = obj;
    }
}
