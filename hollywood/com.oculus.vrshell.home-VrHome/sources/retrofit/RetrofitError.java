package retrofit;

import java.io.IOException;
import java.lang.reflect.Type;
import retrofit.client.Response;
import retrofit.converter.ConversionException;
import retrofit.converter.Converter;
import retrofit.mime.TypedInput;

public class RetrofitError extends RuntimeException {
    private final Converter converter;
    private final Kind kind;
    private final Response response;
    private final Type successType;
    private final String url;

    public enum Kind {
        NETWORK,
        CONVERSION,
        HTTP,
        UNEXPECTED
    }

    public static RetrofitError networkError(String url2, IOException exception) {
        return new RetrofitError(exception.getMessage(), url2, null, null, null, Kind.NETWORK, exception);
    }

    public static RetrofitError conversionError(String url2, Response response2, Converter converter2, Type successType2, ConversionException exception) {
        return new RetrofitError(exception.getMessage(), url2, response2, converter2, successType2, Kind.CONVERSION, exception);
    }

    public static RetrofitError httpError(String url2, Response response2, Converter converter2, Type successType2) {
        return new RetrofitError(response2.getStatus() + " " + response2.getReason(), url2, response2, converter2, successType2, Kind.HTTP, null);
    }

    public static RetrofitError unexpectedError(String url2, Throwable exception) {
        return new RetrofitError(exception.getMessage(), url2, null, null, null, Kind.UNEXPECTED, exception);
    }

    RetrofitError(String message, String url2, Response response2, Converter converter2, Type successType2, Kind kind2, Throwable exception) {
        super(message, exception);
        this.url = url2;
        this.response = response2;
        this.converter = converter2;
        this.successType = successType2;
        this.kind = kind2;
    }

    public String getUrl() {
        return this.url;
    }

    public Response getResponse() {
        return this.response;
    }

    @Deprecated
    public boolean isNetworkError() {
        return this.kind == Kind.NETWORK;
    }

    public Kind getKind() {
        return this.kind;
    }

    public Object getBody() {
        return getBodyAs(this.successType);
    }

    public Type getSuccessType() {
        return this.successType;
    }

    public Object getBodyAs(Type type) {
        TypedInput body;
        if (this.response == null || (body = this.response.getBody()) == null) {
            return null;
        }
        try {
            return this.converter.fromBody(body, type);
        } catch (ConversionException e) {
            throw new RuntimeException(e);
        }
    }
}
