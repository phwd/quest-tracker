package X;

import android.content.Context;
import com.oculus.aidl.OVRServiceInterface;
import com.oculus.os.AnalyticsEvent;
import com.oculus.os.UnifiedTelemetryLogger;
import java.util.Locale;

public final class ZT {
    public final AnalyticsEvent A00 = new AnalyticsEvent("oculus_device_config_event");
    public final Context A01;

    public static void A01(Context context, int i) {
        String format = String.format("Unknown MC type %s", Integer.valueOf(i));
        ZT zt = new ZT(context);
        A0B(zt, AnonymousClass09.A0P);
        A0C(zt, format);
        A09(zt);
        C0139Dd.A0A("DeviceConfigTelemetryLogger", format);
    }

    public static void A00(Context context) {
        ZT zt = new ZT(context);
        A0B(zt, AnonymousClass09.A0M);
        A0C(zt, "Param is not a boolean");
        zt.A00.setExtra("param_name", "oculus_sysux_social:oculus_vrshell_anytimeui_v2_messenger");
        A09(zt);
        C0139Dd.A0O("DeviceConfigTelemetryLogger", "%s: %s", "Param is not a boolean", "oculus_sysux_social:oculus_vrshell_anytimeui_v2_messenger");
    }

    public static void A02(Context context, long j) {
        ZT zt = new ZT(context);
        A0B(zt, AnonymousClass09.A00);
        zt.A00.setExtra("latency", Long.valueOf(j));
        A09(zt);
        C0139Dd.A09("DeviceConfigTelemetryLogger", "onSuccess()");
    }

    public static void A03(Context context, ZS zs, Exception exc) {
        ZT zt = new ZT(context);
        A0B(zt, AnonymousClass09.A0C);
        AnalyticsEvent analyticsEvent = zt.A00;
        analyticsEvent.setExtra("valueType", zs.toString().toLowerCase(Locale.US));
        analyticsEvent.setExtra("param_name", "oculus_sysux_social:oculus_vrshell_anytimeui_v2_messenger");
        A0A(zt, exc);
        A09(zt);
        C0139Dd.A0O("DeviceConfigTelemetryLogger", "get%s(%s) threw an exception", zs, "oculus_sysux_social:oculus_vrshell_anytimeui_v2_messenger", exc);
    }

    public static void A04(Context context, ZS zs, Object obj, String str, long j) {
        ZT zt = new ZT(context);
        A0B(zt, AnonymousClass09.A0C);
        AnalyticsEvent analyticsEvent = zt.A00;
        analyticsEvent.setExtra("valueType", zs.toString().toLowerCase(Locale.US));
        analyticsEvent.setExtra("param_name", "oculus_sysux_social:oculus_vrshell_anytimeui_v2_messenger");
        if (obj != null) {
            analyticsEvent.setExtra("value", obj);
        }
        analyticsEvent.setExtra("source", str);
        analyticsEvent.setExtra("latency", Long.valueOf(j));
        A09(zt);
        C0139Dd.A0K("DeviceConfigTelemetryLogger", "Get %s %s=%s Source: %s", zs, "oculus_sysux_social:oculus_vrshell_anytimeui_v2_messenger", obj, str);
    }

    public static void A05(Context context, Exception exc) {
        ZT zt = new ZT(context);
        A0B(zt, AnonymousClass09.A0P);
        A0A(zt, exc);
        A09(zt);
        C0139Dd.A0L("DeviceConfigTelemetryLogger", "threw an exception", exc);
    }

    public static void A06(Context context, String str, String str2) {
        ZT zt = new ZT(context);
        A0B(zt, AnonymousClass09.A0N);
        A0C(zt, str);
        zt.A00.setExtra("param_name", str2);
        A09(zt);
        C0139Dd.A0O("DeviceConfigTelemetryLogger", "%s: %s", str, str2);
    }

    public static void A07(Context context, String str, String str2) {
        ZT zt = new ZT(context);
        A0B(zt, AnonymousClass09.A0P);
        A0C(zt, str);
        zt.A00.setExtra("param_name", str2);
        A09(zt);
        C0139Dd.A0O("DeviceConfigTelemetryLogger", "%s: %s", str, str2);
    }

    public static void A08(Context context, String str, String str2) {
        ZT zt = new ZT(context);
        A0B(zt, AnonymousClass09.A0L);
        A0C(zt, str);
        zt.A00.setExtra("param_name", str2);
        A09(zt);
        C0139Dd.A0O("DeviceConfigTelemetryLogger", "%s: %s", str, str2);
    }

    public static void A09(ZT zt) {
        UnifiedTelemetryLogger.getInstance(zt.A01).reportEvent(zt.A00, false);
    }

    public static void A0B(ZT zt, Integer num) {
        String str;
        AnalyticsEvent analyticsEvent = zt.A00;
        switch (num.intValue()) {
            case 1:
                str = "SUBSCRIBE_FAILURE";
                break;
            case 2:
                str = "GET_VALUE_SUCCESS";
                break;
            case 3:
                str = "GET_VALUE_FAILURE";
                break;
            case 4:
                str = "NOT_SUBSCRIBED";
                break;
            case 5:
                str = "UNKNOWN_PARAM";
                break;
            case 6:
                str = "INCORRECT_TYPE_PARAM";
                break;
            case OVRServiceInterface.Stub.TRANSACTION_getEntitlementCheckBundle /*{ENCODED_INT: 7}*/:
                str = "INCORRECT_DEFAULT_VALUE";
                break;
            case 8:
                str = "MISSING_DEFAULT_VALUES";
                break;
            case OVRServiceInterface.Stub.TRANSACTION_getLatestAvailableAppInformation /*{ENCODED_INT: 9}*/:
                str = "INTERNAL_ERROR";
                break;
            default:
                str = "SUBSCRIBE_SUCCESS";
                break;
        }
        analyticsEvent.setExtra("sub_event", str.toLowerCase(Locale.US));
    }

    public static void A0C(ZT zt, String str) {
        if (str != null) {
            zt.A00.setExtra("error_msg", str);
        }
    }

    public ZT(Context context) {
        this.A01 = context;
    }

    public static void A0A(ZT zt, Exception exc) {
        A0C(zt, exc.getMessage());
        zt.A00.setExtra("error_stacktrace", exc.getStackTrace().toString());
    }
}
