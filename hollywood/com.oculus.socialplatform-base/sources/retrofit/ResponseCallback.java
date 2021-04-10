package retrofit;

import retrofit.client.Response;

public abstract class ResponseCallback implements Callback<Response> {
    public abstract void success(Response response);

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, retrofit.client.Response] */
    @Override // retrofit.Callback
    public /* bridge */ /* synthetic */ void success(Response response, Response response2) {
    }

    public void success(Response response, Response response2) {
        throw new NullPointerException("success");
    }
}
