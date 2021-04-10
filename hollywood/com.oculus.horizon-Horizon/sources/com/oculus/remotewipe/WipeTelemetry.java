package com.oculus.remotewipe;

import X.AbstractC06640p5;
import X.AnonymousClass0J2;
import X.AnonymousClass0NO;
import X.AnonymousClass0QC;
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
    public AnonymousClass0QC _UL_mInjectionContext;

    public static void A00(WipeTelemetry wipeTelemetry, @Nullable String str, WipeRequester wipeRequester, @Nullable String str2, Exception exc) {
        String name;
        Event A23 = ((EventManager) AnonymousClass0J2.A03(0, 242, wipeTelemetry._UL_mInjectionContext)).A23(str, null, true);
        if (wipeRequester == null) {
            name = "null";
        } else {
            name = wipeRequester.mSource.name();
        }
        A23.A15(KEY_REQUESTER, name);
        if (wipeRequester != null) {
            A23.A13(KEY_ATTEMPT_NUM, wipeRequester.mAttemptNum);
        }
        if (str2 != null) {
            A23.A15("info", str2);
        }
        if (exc != null) {
            StringWriter stringWriter = new StringWriter();
            exc.printStackTrace(new PrintWriter(stringWriter));
            A23.A15("exception_message", stringWriter.toString());
        }
        A23.A5L();
    }

    public static void A01(String str, @Nullable WipeRequester wipeRequester, String str2, @Nullable Exception exc, boolean z) {
        String obj;
        String str3;
        if (wipeRequester == null) {
            obj = "null";
        } else {
            obj = wipeRequester.toString();
        }
        Locale locale = Locale.US;
        Object[] objArr = new Object[3];
        objArr[0] = str2;
        objArr[1] = obj;
        if (z) {
            str3 = "Yes";
        } else {
            str3 = "No";
        }
        objArr[2] = str3;
        String format = String.format(locale, "RemoteWipe failed: %s. %s, Scheduled retry: %s.", objArr);
        if (exc != null) {
            AnonymousClass0NO.A0B(str, format, exc);
        } else {
            AnonymousClass0NO.A08(str, format);
        }
    }

    public final void A02(String str, @Nullable WipeRequester wipeRequester) {
        String name;
        Event A23 = ((EventManager) AnonymousClass0J2.A03(0, 242, this._UL_mInjectionContext)).A23(str, null, true);
        if (wipeRequester == null) {
            name = "null";
        } else {
            name = wipeRequester.mSource.name();
        }
        A23.A15(KEY_REQUESTER, name);
        if (wipeRequester != null) {
            A23.A13(KEY_ATTEMPT_NUM, wipeRequester.mAttemptNum);
        }
        A23.A5L();
    }

    @Inject
    public WipeTelemetry(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(1, r3);
    }
}
