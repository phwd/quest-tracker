package okhttp3;

import java.io.IOException;

public interface Interceptor {

    public interface Chain {
        int connectTimeoutMillis();

        Response proceed(Request request) throws IOException;

        int readTimeoutMillis();

        Request request();

        int writeTimeoutMillis();
    }

    Response intercept(Chain chain) throws IOException;
}
