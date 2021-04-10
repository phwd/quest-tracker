package com.oculus.http.core;

import X.AbstractC03180ld;
import X.AnonymousClass0Hr;
import X.AnonymousClass0MD;
import X.AnonymousClass0VC;
import X.AnonymousClass0lg;
import X.AnonymousClass1TK;
import android.annotation.TargetApi;
import com.facebook.common.stringformat.StringFormatUtil;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.http.core.base.ApiError;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import javax.annotation.Nullable;
import javax.inject.Provider;
import retrofit.ErrorHandler;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedInput;

@Dependencies({"_UL__ULSEP_com_oculus_http_core_HttpCoreLogger_ULSEP_BINDING_ID"})
@TargetApi(19)
public class LoggingErrorHandler implements ErrorHandler {
    public static final String TAG = "LoggingErrorHandler";
    @Inject
    @Eager
    public final HttpCoreLogger mHttpCoreLogger;

    @AutoGeneratedAccessMethod
    public static final AbstractC03180ld _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_http_core_LoggingErrorHandler_ULGT__ULSEP_ACCESS_METHOD(AnonymousClass0lg r1) {
        return AnonymousClass0Hr.A00(41, r1);
    }

    @AutoGeneratedAccessMethod
    public static final LoggingErrorHandler _UL__ULSEP_com_oculus_http_core_LoggingErrorHandler_ULSEP_ACCESS_METHOD(AnonymousClass0lg r2) {
        return (LoggingErrorHandler) AnonymousClass1TK.A00(41, r2, null);
    }

    @AutoGeneratedFactoryMethod
    public static final LoggingErrorHandler _UL__ULSEP_com_oculus_http_core_LoggingErrorHandler_ULSEP_FACTORY_METHOD(AnonymousClass0lg r1, Object obj) {
        return new LoggingErrorHandler(r1);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_http_core_LoggingErrorHandler_ULGT__ULSEP_ACCESS_METHOD(AnonymousClass0lg r1) {
        return AnonymousClass0VC.A00(41, r1);
    }

    public static String extractBody(@Nullable TypedInput typedInput) {
        if (typedInput != null) {
            try {
                InputStream in = typedInput.in();
                if (in != null) {
                    try {
                        InputStreamReader inputStreamReader = new InputStreamReader(in, StandardCharsets.UTF_8);
                        StringBuilder sb = new StringBuilder();
                        if (inputStreamReader instanceof Reader) {
                            char[] cArr = new char[2048];
                            while (true) {
                                int read = inputStreamReader.read(cArr);
                                if (read == -1) {
                                    break;
                                }
                                sb.append(cArr, 0, read);
                            }
                        } else {
                            CharBuffer allocate = CharBuffer.allocate(2048);
                            while (inputStreamReader.read(allocate) != -1) {
                                allocate.flip();
                                sb.append((CharSequence) allocate);
                                allocate.clear();
                            }
                        }
                        String obj = sb.toString();
                        in.close();
                        return obj;
                    } catch (Throwable unused) {
                    }
                }
            } catch (IOException unused2) {
                return "";
            }
        }
        return "";
        throw th;
    }

    public static void log(RetrofitError retrofitError) {
        int i;
        String str;
        Object obj;
        String str2 = retrofitError.url;
        RetrofitError.Kind kind = retrofitError.kind;
        Response response = retrofitError.response;
        if (response != null) {
            i = response.status;
        } else {
            i = 0;
        }
        String str3 = "";
        if (response != null) {
            str = response.reason;
            obj = response.headers;
            str3 = extractBody(response.body);
        } else {
            str = str3;
            obj = str3;
        }
        AnonymousClass0MD.A09(TAG, "Retrofit error, url: [%s], kind: [%s], status: [%d - %s], headers: %s, body: [%s]", str2, kind, Integer.valueOf(i), str, obj, str3);
    }

    @Inject
    public LoggingErrorHandler(AnonymousClass0lg r2) {
        this.mHttpCoreLogger = HttpCoreLogger._UL__ULSEP_com_oculus_http_core_HttpCoreLogger_ULSEP_ACCESS_METHOD(r2);
    }

    private void report(ApiError apiError) {
        HttpCoreLogger httpCoreLogger;
        String str;
        String str2;
        String str3;
        String str4;
        String responseHeadersForDebug = apiError.getResponseHeadersForDebug();
        ApiError.Type type = apiError.type;
        if (type == ApiError.Type.NETWORK_ERROR) {
            if (apiError.isNetworkTimeout.booleanValue()) {
                httpCoreLogger = this.mHttpCoreLogger;
                str = apiError.path;
                str2 = null;
                str3 = apiError.uuid;
                str4 = "network_error_timeout";
            } else {
                return;
            }
        } else if (type == ApiError.Type.HTTP_ERROR) {
            str2 = StringFormatUtil.formatStrLocaleSafe("[message=%s]", apiError.getMessage());
            httpCoreLogger = this.mHttpCoreLogger;
            str = apiError.path;
            str3 = apiError.uuid;
            str4 = "http_error";
        } else if (type == ApiError.Type.CONVERSION_ERROR) {
            httpCoreLogger = this.mHttpCoreLogger;
            str = apiError.path;
            str2 = apiError.conversionMessage;
            str3 = apiError.uuid;
            str4 = "conversion_error";
        } else {
            httpCoreLogger = this.mHttpCoreLogger;
            str = apiError.path;
            str2 = null;
            str3 = apiError.uuid;
            str4 = "unexpected_error";
        }
        httpCoreLogger.reportApiError(str4, str, str2, str3, responseHeadersForDebug);
    }

    @Override // retrofit.ErrorHandler
    public Throwable handleError(RetrofitError retrofitError) {
        log(retrofitError);
        report(new ApiError(retrofitError));
        return retrofitError;
    }
}