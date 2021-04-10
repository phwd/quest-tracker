package com.oculus.http.core.base;

import X.AnonymousClass13R;
import com.google.gson.annotations.Expose;
import com.oculus.http.core.uuid.ApiUUID;
import java.net.SocketTimeoutException;
import java.net.URL;
import javax.annotation.Nullable;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.ConversionException;

public class ApiError extends Exception {
    @Expose
    public String conversionMessage;
    @Nullable
    @Expose
    public FBApiErrorResponse fbApiErrorResponse;
    @Expose
    public Integer httpStatus;
    @Expose
    public Boolean isNetworkTimeout;
    @Expose
    public String path;
    @Expose
    public Type type;
    @Expose
    public String uuid;

    public static class FBApiErrorResponse {
        @Nullable
        @Expose
        public final Error error;

        public static class Error {
            @Expose
            public final int code;
            @Expose
            public final AnonymousClass13R error_data;
            @Expose
            public final int error_subcode;
            @Expose
            public final String error_user_msg;
            @Expose
            public final String error_user_title;
            @Expose
            public final boolean is_transient;
            @Expose
            public final String message;
            @Expose
            public final String type;

            public String toString() {
                StringBuilder sb = new StringBuilder("message = ");
                sb.append(this.message);
                sb.append(", type = ");
                sb.append(this.type);
                sb.append(", code = ");
                sb.append(this.code);
                sb.append(", error_subcode = ");
                sb.append(this.error_subcode);
                sb.append(", is_transient = ");
                sb.append(this.is_transient);
                sb.append(", error_user_title = ");
                sb.append(this.error_user_title);
                sb.append(", error_user_msg = ");
                sb.append(this.error_user_msg);
                sb.append(", error_data = ");
                sb.append(this.error_data);
                return sb.toString();
            }
        }

        public int getErrorCode() {
            Error error2 = this.error;
            if (error2 != null) {
                return error2.code;
            }
            return 0;
        }

        @Nullable
        public AnonymousClass13R getErrorData() {
            Error error2 = this.error;
            if (error2 != null) {
                return error2.error_data;
            }
            return null;
        }

        @Nullable
        public String getErrorMessage(@Nullable String str) {
            Error error2 = this.error;
            if (error2 != null) {
                String str2 = error2.error_user_msg;
                if (str2 != null && !str2.isEmpty()) {
                    return str2;
                }
                String str3 = error2.message;
                if (str3 == null || str3.isEmpty()) {
                    return str;
                }
                return str3;
            }
            return str;
        }

        @Nullable
        public String getErrorTitle(@Nullable String str) {
            Error error2 = this.error;
            if (error2 != null) {
                return error2.error_user_title;
            }
            return str;
        }

        public int getSubErrorCode() {
            Error error2 = this.error;
            if (error2 != null) {
                return error2.error_subcode;
            }
            return 0;
        }

        public String toString() {
            Error error2 = this.error;
            if (error2 != null) {
                return error2.toString();
            }
            return "";
        }
    }

    public enum Type {
        HTTP_ERROR,
        NETWORK_ERROR,
        CONVERSION_ERROR,
        UNEXPECTED_ERROR,
        VALIDATIION_ERROR
    }

    public static ApiError makeValidationError(RuntimeException runtimeException) {
        return new ApiError(Type.VALIDATIION_ERROR, runtimeException);
    }

    public static FBApiErrorResponse parseFBApiErrorResponse(RetrofitError retrofitError) {
        try {
            return (FBApiErrorResponse) retrofitError.getBodyAs(FBApiErrorResponse.class);
        } catch (Exception unused) {
            return null;
        }
    }

    public boolean fbErrorCodeEquals(int i) {
        FBApiErrorResponse.Error error;
        FBApiErrorResponse fBApiErrorResponse = this.fbApiErrorResponse;
        if (fBApiErrorResponse == null || (error = fBApiErrorResponse.error) == null || error.code != i) {
            return false;
        }
        return true;
    }

    public boolean fbErrorSubCodeEquals(int i) {
        FBApiErrorResponse fBApiErrorResponse = this.fbApiErrorResponse;
        if (fBApiErrorResponse == null || fBApiErrorResponse.getSubErrorCode() != i) {
            return false;
        }
        return true;
    }

    public int getFBErrorCode() {
        FBApiErrorResponse fBApiErrorResponse = this.fbApiErrorResponse;
        if (fBApiErrorResponse != null) {
            return fBApiErrorResponse.getErrorCode();
        }
        return 0;
    }

    @Nullable
    public AnonymousClass13R getFBErrorData() {
        FBApiErrorResponse fBApiErrorResponse = this.fbApiErrorResponse;
        if (fBApiErrorResponse != null) {
            return fBApiErrorResponse.getErrorData();
        }
        return null;
    }

    @Nullable
    public String getFBErrorMessage(@Nullable String str) {
        FBApiErrorResponse fBApiErrorResponse = this.fbApiErrorResponse;
        if (fBApiErrorResponse != null) {
            return fBApiErrorResponse.getErrorMessage(str);
        }
        return str;
    }

    public int getFBErrorSubErrorCode() {
        FBApiErrorResponse fBApiErrorResponse = this.fbApiErrorResponse;
        if (fBApiErrorResponse != null) {
            return fBApiErrorResponse.getSubErrorCode();
        }
        return 0;
    }

    @Nullable
    public String getFBErrorTitle(@Nullable String str) {
        FBApiErrorResponse fBApiErrorResponse = this.fbApiErrorResponse;
        if (fBApiErrorResponse != null) {
            return fBApiErrorResponse.getErrorTitle(str);
        }
        return str;
    }

    @Nullable
    public String getResponseHeadersForDebug() {
        Response response;
        Throwable cause = getCause();
        if (!(cause instanceof RetrofitError) || (response = ((RetrofitError) cause).response) == null) {
            return null;
        }
        return String.valueOf(response.headers);
    }

    public Type getType() {
        return this.type;
    }

    public ApiError(Type type2, RuntimeException runtimeException) {
        super(runtimeException);
        this.type = type2;
    }

    public ApiError(RetrofitError retrofitError) {
        super(retrofitError);
        try {
            this.path = new URL(retrofitError.url).getPath();
        } catch (Exception unused) {
            this.path = retrofitError.url;
        }
        Response response = retrofitError.response;
        if (retrofitError.isNetworkError()) {
            this.type = Type.NETWORK_ERROR;
            Throwable cause = retrofitError.getCause();
            this.uuid = cause.getMessage();
            this.isNetworkTimeout = Boolean.valueOf(cause.getCause() instanceof SocketTimeoutException);
        } else if (retrofitError.getCause() == null && response != null) {
            this.type = Type.HTTP_ERROR;
            this.uuid = ApiUUID.getFromHeaders(response.headers);
            this.httpStatus = Integer.valueOf(retrofitError.response.status);
            this.fbApiErrorResponse = parseFBApiErrorResponse(retrofitError);
        } else if (retrofitError.getCause() instanceof ConversionException) {
            this.type = Type.CONVERSION_ERROR;
            this.uuid = ApiUUID.getFromHeaders(response.headers);
            this.conversionMessage = retrofitError.getCause().getMessage();
        } else {
            this.type = Type.UNEXPECTED_ERROR;
        }
    }
}
