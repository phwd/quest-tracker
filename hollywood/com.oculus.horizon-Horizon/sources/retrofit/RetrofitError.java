package retrofit;

import java.io.IOException;
import java.lang.reflect.Type;
import retrofit.client.Response;
import retrofit.converter.ConversionException;
import retrofit.converter.Converter;
import retrofit.mime.TypedInput;

public class RetrofitError extends RuntimeException {
    public final Converter converter;
    public final Kind kind;
    public final Response response;
    public final Type successType;
    public final String url;

    public enum Kind {
        NETWORK,
        CONVERSION,
        HTTP,
        UNEXPECTED
    }

    public static RetrofitError conversionError(String str, Response response2, Converter converter2, Type type, ConversionException conversionException) {
        return new RetrofitError(conversionException.getMessage(), str, response2, converter2, type, Kind.CONVERSION, conversionException);
    }

    public static RetrofitError networkError(String str, IOException iOException) {
        return new RetrofitError(iOException.getMessage(), str, null, null, null, Kind.NETWORK, iOException);
    }

    public static RetrofitError unexpectedError(String str, Throwable th) {
        return new RetrofitError(th.getMessage(), str, null, null, null, Kind.UNEXPECTED, th);
    }

    public static RetrofitError httpError(String str, Response response2, Converter converter2, Type type) {
        StringBuilder sb = new StringBuilder();
        sb.append(response2.status);
        sb.append(" ");
        sb.append(response2.reason);
        return new RetrofitError(sb.toString(), str, response2, converter2, type, Kind.HTTP, null);
    }

    public Object getBody() {
        return getBodyAs(this.successType);
    }

    public Object getBodyAs(Type type) {
        TypedInput typedInput;
        Response response2 = this.response;
        if (response2 == null || (typedInput = response2.body) == null) {
            return null;
        }
        try {
            return this.converter.fromBody(typedInput, type);
        } catch (ConversionException e) {
            throw new RuntimeException(e);
        }
    }

    @Deprecated
    public boolean isNetworkError() {
        if (this.kind == Kind.NETWORK) {
            return true;
        }
        return false;
    }

    public RetrofitError(String str, String str2, Response response2, Converter converter2, Type type, Kind kind2, Throwable th) {
        super(str, th);
        this.url = str2;
        this.response = response2;
        this.converter = converter2;
        this.successType = type;
        this.kind = kind2;
    }

    public Kind getKind() {
        return this.kind;
    }

    public Response getResponse() {
        return this.response;
    }

    public Type getSuccessType() {
        return this.successType;
    }

    public String getUrl() {
        return this.url;
    }
}
