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

    public static RetrofitError networkError(String str, IOException iOException) {
        return new RetrofitError(iOException.getMessage(), str, null, null, null, Kind.NETWORK, iOException);
    }

    public static RetrofitError conversionError(String str, Response response2, Converter converter2, Type type, ConversionException conversionException) {
        return new RetrofitError(conversionException.getMessage(), str, response2, converter2, type, Kind.CONVERSION, conversionException);
    }

    public static RetrofitError httpError(String str, Response response2, Converter converter2, Type type) {
        return new RetrofitError(response2.getStatus() + " " + response2.getReason(), str, response2, converter2, type, Kind.HTTP, null);
    }

    public static RetrofitError unexpectedError(String str, Throwable th) {
        return new RetrofitError(th.getMessage(), str, null, null, null, Kind.UNEXPECTED, th);
    }

    RetrofitError(String str, String str2, Response response2, Converter converter2, Type type, Kind kind2, Throwable th) {
        super(str, th);
        this.url = str2;
        this.response = response2;
        this.converter = converter2;
        this.successType = type;
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

    public Object getBodyAs(Type type) {
        TypedInput body;
        Response response2 = this.response;
        if (response2 == null || (body = response2.getBody()) == null) {
            return null;
        }
        try {
            return this.converter.fromBody(body, type);
        } catch (ConversionException e) {
            throw new RuntimeException(e);
        }
    }
}
