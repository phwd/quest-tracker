package okhttp3;

import java.io.IOException;

public interface Interceptor {

    public interface Chain {
        Response proceed(Request request) throws IOException;

        Request request();
    }

    Response intercept(Chain chain) throws IOException;
}
