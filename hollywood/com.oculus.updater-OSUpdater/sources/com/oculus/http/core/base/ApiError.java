package com.oculus.http.core.base;

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

    public static class FBApiErrorResponse {
        @Nullable
        @Expose
        public Error error;

        public static class Error {
            @Expose
            public int code;
            @Expose
            public int error_subcode;
            @Expose
            public String error_user_msg;
            @Expose
            public String error_user_title;
            @Expose
            public String message;
            @Expose
            public String type;
        }
    }

    public enum Type {
        HTTP_ERROR,
        NETWORK_ERROR,
        CONVERSION_ERROR,
        UNEXPECTED_ERROR,
        VALIDATIION_ERROR
    }

    private static FBApiErrorResponse parseFBApiErrorResponse(RetrofitError retrofitError) {
        try {
            return (FBApiErrorResponse) retrofitError.getBodyAs(FBApiErrorResponse.class);
        } catch (Exception unused) {
            return null;
        }
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

    @Nullable
    public String getResponseHeadersForDebug() {
        Response response;
        Throwable cause = getCause();
        if ((cause instanceof RetrofitError) && (response = ((RetrofitError) cause).getResponse()) != null) {
            return String.valueOf(response.getHeaders());
        }
        return null;
    }
}
