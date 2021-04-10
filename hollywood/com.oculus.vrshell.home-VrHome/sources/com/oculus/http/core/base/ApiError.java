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

    public static ApiError makeValidationError(RuntimeException e) {
        return new ApiError(Type.VALIDATIION_ERROR, e);
    }

    private static FBApiErrorResponse parseFBApiErrorResponse(RetrofitError retrofitError) {
        try {
            return (FBApiErrorResponse) retrofitError.getBodyAs(FBApiErrorResponse.class);
        } catch (Exception e) {
            return null;
        }
    }

    private ApiError(Type type2, RuntimeException cause) {
        super(cause);
        this.type = type2;
    }

    public ApiError(RetrofitError retrofitError) {
        super(retrofitError);
        try {
            this.path = new URL(retrofitError.getUrl()).getPath();
        } catch (Exception e) {
            this.path = retrofitError.getUrl();
        }
        Response response = retrofitError.getResponse();
        if (retrofitError.isNetworkError()) {
            this.type = Type.NETWORK_ERROR;
            IOException ioException = (IOException) retrofitError.getCause();
            this.uuid = ioException.getMessage();
            this.isNetworkTimeout = Boolean.valueOf(ioException.getCause() instanceof SocketTimeoutException);
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

    public boolean fbErrorSubCodeEquals(int errorCode) {
        return this.fbApiErrorResponse != null && this.fbApiErrorResponse.getSubErrorCode() == errorCode;
    }

    public boolean fbErrorCodeEquals(int errorCode) {
        return (this.fbApiErrorResponse == null || this.fbApiErrorResponse.error == null || this.fbApiErrorResponse.error.code != errorCode) ? false : true;
    }

    @Nullable
    public String getFBErrorTitle(@Nullable String defaultTitle) {
        if (this.fbApiErrorResponse != null) {
            return this.fbApiErrorResponse.getErrorTitle(defaultTitle);
        }
        return defaultTitle;
    }

    @Nullable
    public String getFBErrorMessage(@Nullable String defaultMsg) {
        return this.fbApiErrorResponse != null ? this.fbApiErrorResponse.getErrorMessage(defaultMsg) : defaultMsg;
    }

    public int getFBErrorSubErrorCode() {
        if (this.fbApiErrorResponse != null) {
            return this.fbApiErrorResponse.getSubErrorCode();
        }
        return 0;
    }

    public int getFBErrorCode() {
        if (this.fbApiErrorResponse != null) {
            return this.fbApiErrorResponse.getErrorCode();
        }
        return 0;
    }

    @Nullable
    public JsonElement getFBErrorData() {
        if (this.fbApiErrorResponse != null) {
            return this.fbApiErrorResponse.getErrorData();
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
        public String getErrorTitle(@Nullable String defaultTitle) {
            return this.error != null ? this.error.error_user_title : defaultTitle;
        }

        @Nullable
        public String getErrorMessage(@Nullable String defaultMsg) {
            if (this.error == null) {
                return defaultMsg;
            }
            if (this.error.error_user_msg != null && !this.error.error_user_msg.isEmpty()) {
                return this.error.error_user_msg;
            }
            if (this.error.message == null || this.error.message.isEmpty()) {
                return defaultMsg;
            }
            return this.error.message;
        }

        public int getSubErrorCode() {
            if (this.error != null) {
                return this.error.error_subcode;
            }
            return 0;
        }

        public int getErrorCode() {
            if (this.error != null) {
                return this.error.code;
            }
            return 0;
        }

        @Nullable
        public JsonElement getErrorData() {
            if (this.error != null) {
                return this.error.error_data;
            }
            return null;
        }
    }
}
