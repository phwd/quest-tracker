package com.oculus.remotewipe;

import X.AbstractC02990bJ;
import X.AnonymousClass0Lh;
import X.AnonymousClass0NK;
import X.AnonymousClass0R7;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.logging.utils.Event;
import com.oculus.logging.utils.EventManager;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Locale;
import javax.annotation.Nullable;

@Dependencies({"_UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID"})
public class WipeTelemetry {
    public static final String EVENT_WIPE_FAIL = "oculus_mobile_remote_wipe_fail";
    public static final String EVENT_WIPE_REQUESTED = "oculus_mobile_remote_wipe_requested";
    public static final String EVENT_WIPE_RETRY = "oculus_mobile_remote_wipe_retry";
    public static final String EVENT_WIPE_SUCCESS = "oculus_mobile_remote_wipe_success";
    public static final String KEY_ATTEMPT_NUM = "attempt_num";
    public static final String KEY_EXCEPTION_MESSAGE = "exception_message";
    public static final String KEY_INFO = "info";
    public static final String KEY_REQUESTER = "requester";
    public static final String TELEMETRY_PREFIX = "oculus_mobile_remote_wipe_";
    public AnonymousClass0R7 _UL_mInjectionContext;

    public static void A00(WipeTelemetry wipeTelemetry, @Nullable String str, WipeRequester wipeRequester, @Nullable String str2, Exception exc) {
        String name;
        Event A20 = ((EventManager) AnonymousClass0Lh.A03(0, 103, wipeTelemetry._UL_mInjectionContext)).A20(str, null, true);
        if (wipeRequester == null) {
            name = "null";
        } else {
            name = wipeRequester.mSource.name();
        }
        A20.A0z(KEY_REQUESTER, name);
        if (wipeRequester != null) {
            A20.A0y(KEY_ATTEMPT_NUM, wipeRequester.mAttemptNum);
        }
        if (str2 != null) {
            A20.A0z(KEY_INFO, str2);
        }
        if (exc != null) {
            StringWriter stringWriter = new StringWriter();
            exc.printStackTrace(new PrintWriter(stringWriter));
            A20.A0z(KEY_EXCEPTION_MESSAGE, stringWriter.toString());
        }
        A20.A5i();
    }

    public static void A01(String str, @Nullable WipeRequester wipeRequester, String str2, @Nullable Exception exc, boolean z) {
        String wipeRequester2;
        String str3;
        if (wipeRequester == null) {
            wipeRequester2 = "null";
        } else {
            wipeRequester2 = wipeRequester.toString();
        }
        Locale locale = Locale.US;
        if (z) {
            str3 = "Yes";
        } else {
            str3 = "No";
        }
        String format = String.format(locale, "RemoteWipe failed: %s. %s, Scheduled retry: %s.", str2, wipeRequester2, str3);
        if (exc != null) {
            AnonymousClass0NK.A04(str, format, exc);
        } else {
            AnonymousClass0NK.A01(str, format);
        }
    }

    public final void A02(String str, @Nullable WipeRequester wipeRequester) {
        String name;
        Event A20 = ((EventManager) AnonymousClass0Lh.A03(0, 103, this._UL_mInjectionContext)).A20(str, null, true);
        if (wipeRequester == null) {
            name = "null";
        } else {
            name = wipeRequester.mSource.name();
        }
        A20.A0z(KEY_REQUESTER, name);
        if (wipeRequester != null) {
            A20.A0y(KEY_ATTEMPT_NUM, wipeRequester.mAttemptNum);
        }
        A20.A5i();
    }

    @Inject
    public WipeTelemetry(AbstractC02990bJ r3) {
        this._UL_mInjectionContext = new AnonymousClass0R7(1, r3);
    }
}
