package com.oculus.http.core.base;

import com.google.gson.JsonElement;
import com.google.gson.annotations.Expose;
import com.oculus.http.core.uuid.ApiUUID;
import java.io.IOException;
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

    private static FBApiErrorResponse parseFBApiErrorResponse(RetrofitError retrofitError) {
        try {
            return (FBApiErrorResponse) retrofitError.getBodyAs(FBApiErrorResponse.class);
        } catch (Exception unused) {
            return null;
        }
    }

    private ApiError(Type type2, RuntimeException runtimeException) {
        super(runtimeException);
        this.type = type2;
    }

    public ApiError(RetrofitError retrofitError) {
        super(retrofitError);
        try {
            this.path = new URL(retrofitError.getUrl()).getPath();
        } catch (Exception unused) {
            this.path = retrofitError.getUrl();
        }
        Response response = retrofitError.getResponse();
        if (retrofitError.isNetworkError()) {
            this.type = Type.NETWORK_ERROR;
            IOException iOException = (IOException) retrofitError.getCause();
            this.uuid = iOException.getMessage();
            this.isNetworkTimeout = Boolean.valueOf(iOException.getCause() instanceof SocketTimeoutException);
        } else if (retrofitError.getCause() == null && response != null) {
            this.type = Type.HTTP_ERROR;
            this.uuid = ApiUUID.getFromHeaders(response.getHeaders());
            this.httpStatus = Integer.valueOf(retrofitError.getResponse().getStatus());
            this.fbApiErrorResponse = parseFBApiErrorResponse(retrofitError);
        } else if (retrofitError.getCause() instanceof ConversionException) {
            this.type = Type.CONVERSION_ERROR;
            this.uuid = ApiUUID.getFromHeaders(response.getHeaders());
            this.conversionMessage = retrofitError.getCause().getMessage();
        } else {
            this.type = Type.UNEXPECTED_ERROR;
        }
    }

    public Type getType() {
        return this.type;
    }

    public boolean fbErrorSubCodeEquals(int i) {
        FBApiErrorResponse fBApiErrorResponse = this.fbApiErrorResponse;
        return fBApiErrorResponse != null && fBApiErrorResponse.getSubErrorCode() == i;
    }

    public boolean fbErrorCodeEquals(int i) {
        FBApiErrorResponse fBApiErrorResponse = this.fbApiErrorResponse;
        return (fBApiErrorResponse == null || fBApiErrorResponse.error == null || this.fbApiErrorResponse.error.code != i) ? false : true;
    }

    @Nullable
    public String getFBErrorTitle(@Nullable String str) {
        FBApiErrorResponse fBApiErrorResponse = this.fbApiErrorResponse;
        return fBApiErrorResponse != null ? fBApiErrorResponse.getErrorTitle(str) : str;
    }

    @Nullable
    public String getFBErrorMessage(@Nullable String str) {
        FBApiErrorResponse fBApiErrorResponse = this.fbApiErrorResponse;
        return fBApiErrorResponse != null ? fBApiErrorResponse.getErrorMessage(str) : str;
    }

    public int getFBErrorSubErrorCode() {
        FBApiErrorResponse fBApiErrorResponse = this.fbApiErrorResponse;
        if (fBApiErrorResponse != null) {
            return fBApiErrorResponse.getSubErrorCode();
        }
        return 0;
    }

    public int getFBErrorCode() {
        FBApiErrorResponse fBApiErrorResponse = this.fbApiErrorResponse;
        if (fBApiErrorResponse != null) {
            return fBApiErrorResponse.getErrorCode();
        }
        return 0;
    }

    @Nullable
    public JsonElement getFBErrorData() {
        FBApiErrorResponse fBApiErrorResponse = this.fbApiErrorResponse;
        if (fBApiErrorResponse != null) {
            return fBApiErrorResponse.getErrorData();
        }
        return null;
    }

    @Nullable
    public String getResponseHeadersForDebug() {
        Response response;
        Throwable cause = getCause();
        if ((cause instanceof RetrofitError) && (response = ((RetrofitError) cause).getResponse()) != null) {
            return String.valueOf(response.getHeaders());
        }
        return null;
    }

    public static class FBApiErrorResponse {
        @Nullable
        @Expose
        public Error error;

        public static class Error {
            @Expose
            public int code;
            @Expose
            public JsonElement error_data;
            @Expose
            public int error_subcode;
            @Expose
            public String error_user_msg;
            @Expose
            public String error_user_title;
            @Expose
            public boolean is_transient;
            @Expose
            public String message;
            @Expose
            public String type;
        }

        @Nullable
        public String getErrorTitle(@Nullable String str) {
            Error error2 = this.error;
            return error2 != null ? error2.error_user_title : str;
        }

        @Nullable
        public String getErrorMessage(@Nullable String str) {
            Error error2 = this.error;
            if (error2 == null) {
                return str;
            }
            if (error2.error_user_msg == null || this.error.error_user_msg.isEmpty()) {
                return (this.error.message == null || this.error.message.isEmpty()) ? str : this.error.message;
            }
            return this.error.error_user_msg;
        }

        public int getSubErrorCode() {
            Error error2 = this.error;
            if (error2 != null) {
                return error2.error_subcode;
            }
            return 0;
        }

        public int getErrorCode() {
            Error error2 = this.error;
            if (error2 != null) {
                return error2.code;
            }
            return 0;
        }

        @Nullable
        public JsonElement getErrorData() {
            Error error2 = this.error;
            if (error2 != null) {
                return error2.error_data;
            }
            return null;
        }
    }
}
