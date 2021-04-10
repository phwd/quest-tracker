package com.oculus.http.core.base;

import X.AbstractC08820ye;
import com.google.gson.annotations.Expose;
import com.oculus.http.core.uuid.ApiUUID;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Iterator;
import javax.annotation.Nullable;
import retrofit.RetrofitError;
import retrofit.client.Header;
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
            public final AbstractC08820ye error_data;
            @Expose
            public final int error_subcode;
            @Expose
            public final String error_user_msg;
            @Expose
            public final String error_user_title;
            @Expose
            public boolean is_transient;
            @Expose
            public final String message;
            @Expose
            public final String type;
        }
    }

    public enum Type {
        HTTP_ERROR,
        NETWORK_ERROR,
        CONVERSION_ERROR,
        UNEXPECTED_ERROR,
        VALIDATIION_ERROR
    }

    @Nullable
    public final String A00(@Nullable String str) {
        FBApiErrorResponse.Error error;
        FBApiErrorResponse fBApiErrorResponse = this.fbApiErrorResponse;
        if (!(fBApiErrorResponse == null || (error = fBApiErrorResponse.error) == null)) {
            String str2 = error.error_user_msg;
            if (str2 != null && !str2.isEmpty()) {
                return str2;
            }
            String str3 = error.message;
            if (str3 == null || str3.isEmpty()) {
                return str;
            }
            return str3;
        }
        return str;
    }

    public final boolean A01(int i) {
        int i2;
        FBApiErrorResponse fBApiErrorResponse = this.fbApiErrorResponse;
        if (fBApiErrorResponse != null) {
            FBApiErrorResponse.Error error = fBApiErrorResponse.error;
            if (error != null) {
                i2 = error.error_subcode;
            } else {
                i2 = 0;
            }
            if (i2 != i) {
                return false;
            }
            return true;
        }
        return false;
    }

    public ApiError(Type type2, RuntimeException runtimeException) {
        super(runtimeException);
        this.type = type2;
    }

    public ApiError(RetrofitError retrofitError) {
        super(retrofitError);
        String str;
        String str2;
        FBApiErrorResponse fBApiErrorResponse;
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
            Iterator<Header> it = response.headers.iterator();
            while (true) {
                if (!it.hasNext()) {
                    str2 = null;
                    break;
                }
                Header next = it.next();
                if (ApiUUID.HEADER_NAME.equals(next.name)) {
                    str2 = next.value;
                    break;
                }
            }
            this.uuid = str2;
            this.httpStatus = Integer.valueOf(retrofitError.response.status);
            try {
                fBApiErrorResponse = (FBApiErrorResponse) retrofitError.getBodyAs(FBApiErrorResponse.class);
            } catch (Exception unused2) {
                fBApiErrorResponse = null;
            }
            this.fbApiErrorResponse = fBApiErrorResponse;
        } else if (retrofitError.getCause() instanceof ConversionException) {
            this.type = Type.CONVERSION_ERROR;
            Iterator<Header> it2 = response.headers.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    str = null;
                    break;
                }
                Header next2 = it2.next();
                if (ApiUUID.HEADER_NAME.equals(next2.name)) {
                    str = next2.value;
                    break;
                }
            }
            this.uuid = str;
            this.conversionMessage = retrofitError.getCause().getMessage();
        } else {
            this.type = Type.UNEXPECTED_ERROR;
        }
    }
}
