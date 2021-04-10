package retrofit;

import retrofit.client.Response;

final class ResponseWrapper {
    final Response response;
    final Object responseBody;

    ResponseWrapper(Response response2, Object responseBody2) {
        this.response = response2;
        this.responseBody = responseBody2;
    }
}
