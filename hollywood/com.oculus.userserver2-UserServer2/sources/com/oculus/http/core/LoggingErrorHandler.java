package com.oculus.http.core;

import X.IX;
import X.SZ;
import com.facebook.common.stringformat.StringFormatUtil;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.http.core.base.ApiError;
import retrofit.ErrorHandler;
import retrofit.RetrofitError;
import retrofit.client.Response;

@Dependencies({"_UL__ULSEP_com_oculus_http_core_HttpCoreLogger_ULSEP_BINDING_ID"})
public class LoggingErrorHandler implements ErrorHandler {
    @Inject
    @Eager
    public final HttpCoreLogger mHttpCoreLogger;

    @Override // retrofit.ErrorHandler
    public final Throwable handleError(RetrofitError retrofitError) {
        String str;
        HttpCoreLogger httpCoreLogger;
        String str2;
        String str3;
        String str4;
        Response response;
        ApiError apiError = new ApiError(retrofitError);
        Throwable cause = apiError.getCause();
        String str5 = null;
        if (!(cause instanceof RetrofitError) || (response = ((RetrofitError) cause).response) == null) {
            str = null;
        } else {
            str = String.valueOf(response.headers);
        }
        ApiError.Type type = apiError.type;
        if (type == ApiError.Type.NETWORK_ERROR) {
            if (apiError.isNetworkTimeout.booleanValue()) {
                httpCoreLogger = this.mHttpCoreLogger;
                str2 = apiError.path;
                str3 = apiError.uuid;
                str4 = "network_error_timeout";
            }
            return retrofitError;
        } else if (type == ApiError.Type.HTTP_ERROR) {
            str5 = StringFormatUtil.formatStrLocaleSafe("[message=%s]", apiError.getMessage());
            httpCoreLogger = this.mHttpCoreLogger;
            str2 = apiError.path;
            str3 = apiError.uuid;
            str4 = "http_error";
        } else if (type == ApiError.Type.CONVERSION_ERROR) {
            httpCoreLogger = this.mHttpCoreLogger;
            str2 = apiError.path;
            str5 = apiError.conversionMessage;
            str3 = apiError.uuid;
            str4 = "conversion_error";
        } else {
            httpCoreLogger = this.mHttpCoreLogger;
            str2 = apiError.path;
            str3 = apiError.uuid;
            str4 = "unexpected_error";
        }
        httpCoreLogger.A00(str4, str2, str5, str3, str);
        return retrofitError;
    }

    @Inject
    public LoggingErrorHandler(SZ sz) {
        this.mHttpCoreLogger = (HttpCoreLogger) IX.A00(13, sz);
    }
}
