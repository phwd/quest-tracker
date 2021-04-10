package com.oculus.http.core;

import X.AbstractC06640p5;
import X.AnonymousClass0J2;
import X.AnonymousClass0QC;
import X.AnonymousClass117;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.logging.utils.Event;
import com.oculus.logging.utils.EventManager;

@Dependencies({"_UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID"})
public class HttpCoreLogger {
    public static final String ERROR_API = "moonlight_error_api";
    public static final String EVENT_VALUE = "event_value";
    public static final String MESSAGE = "message";
    public static final String PATH = "path";
    public static final String REQUEST_UUID = "request_uuid";
    public static final String RESPONSE_HEADERS = "response_headers";
    public AnonymousClass0QC _UL_mInjectionContext;

    @AutoGeneratedAccessMethod
    public static final HttpCoreLogger A00(AbstractC06640p5 r1) {
        return (HttpCoreLogger) AnonymousClass117.A00(299, r1);
    }

    @AutoGeneratedFactoryMethod
    public static final HttpCoreLogger A01(AbstractC06640p5 r1) {
        return new HttpCoreLogger(r1);
    }

    public final void A02(String str, String str2, String str3, String str4, String str5) {
        Event A22 = ((EventManager) AnonymousClass0J2.A03(0, 242, this._UL_mInjectionContext)).A22("moonlight_error_api");
        A22.A15("event_value", str);
        A22.A15("path", str2);
        A22.A15("message", str3);
        if (str4 == null) {
            str4 = "null";
        }
        A22.A15("request_uuid", str4);
        if (str5 == null) {
            str5 = "";
        }
        A22.A15("response_headers", str5);
        A22.A5L();
    }

    @Inject
    public HttpCoreLogger(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(1, r3);
    }
}