package com.oculus.http.core;

import X.BZ;
import X.Om;
import X.SZ;
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
    public Om _UL_mInjectionContext;

    public final void A00(String str, String str2, String str3, String str4, String str5) {
        Event A1G = ((EventManager) BZ.A02(0, 43, this._UL_mInjectionContext)).A1G(ERROR_API);
        A1G.A0m(EVENT_VALUE, str);
        A1G.A0m("path", str2);
        A1G.A0m("message", str3);
        if (str4 == null) {
            str4 = "null";
        }
        A1G.A0m(REQUEST_UUID, str4);
        if (str5 == null) {
            str5 = "";
        }
        A1G.A0m(RESPONSE_HEADERS, str5);
        A1G.A2K();
    }

    @Inject
    public HttpCoreLogger(SZ sz) {
        this._UL_mInjectionContext = new Om(1, sz);
    }
}
