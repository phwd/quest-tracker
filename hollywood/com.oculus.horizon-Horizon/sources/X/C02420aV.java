package X;

import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.rti.common.time.RealtimeSinceBootClock;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0aV  reason: invalid class name and case insensitive filesystem */
public final class C02420aV {
    public final long A00 = SystemClock.elapsedRealtime();
    public final AnonymousClass0XJ A01;
    public final C06570ne A02;
    public final RealtimeSinceBootClock A03;
    public final String A04;

    public static String A00(Integer num) {
        switch (num.intValue()) {
            case 1:
                return "REQUEST_SENT_SUCCESS";
            case 2:
                return "REQUEST_SENT_FAIL";
            case 3:
                return "RESPONSE_RECEIVED";
            case 4:
                return "FAILURE_CACHE_UPDATE";
            case 5:
                return "FAILURE_SERVICE_NOT_STARTED";
            case 6:
                return "FAILURE_MQTT_NOT_CONNECTED";
            case 7:
                return "FAILURE_UNKNOWN_CLIENT_ERROR";
            case 8:
                return "FAILURE_SERVER_RESPOND_WITH_ERROR";
            case 9:
                return "FAILURE_SERVER_RESPOND_WITH_INVALID_PACKAGE_NAME";
            case 10:
                return "FAILURE_SERVER_RESPOND_WITH_INVALID_TOKEN";
            case 11:
                return "FAILURE_PACKAGE_DOES_NOT_MATCH_INTENT";
            case 12:
                return "FAILURE_EMPTY_PACKAGE_NAME";
            case 13:
                return "UNREGISTER_CALLED";
            case 14:
                return "AUTHFAIL_AUTO_REGISTER";
            case 15:
                return "REGISTER";
            case 16:
                return "UNREGISTER_FAILURE_MQTT_NOT_CONNECTED";
            case 17:
                return "UNREGISTER_REQUEST_SENT_SUCCESS";
            case 18:
                return "UNREGISTER_REQUEST_SENT_FAIL";
            case 19:
                return "CREDENTIALS_UPDATED";
            default:
                return "CACHE_HIT";
        }
    }

    public final void A02(Integer num, @Nullable String str) {
        Map<String, String> A002 = AnonymousClass0VY.A00("event_type", A00(num));
        if (!TextUtils.isEmpty(str)) {
            A002.put("event_extra_info", str);
        }
        A01(this, "fbns_registration_event", A002);
    }

    public final void A03(Integer num, @Nullable String str) {
        String str2;
        String[] strArr = new String[2];
        strArr[0] = "event_type";
        if (1 - num.intValue() != 0) {
            str2 = "JSON_PARSE_ERROR";
        } else {
            str2 = "UNEXPECTED_TOPIC";
        }
        strArr[1] = str2;
        Map<String, String> A002 = AnonymousClass0VY.A00(strArr);
        if (!TextUtils.isEmpty(str)) {
            A002.put("event_extra_info", str);
        }
        A01(this, "fbns_service_event", A002);
    }

    public final void A04(Integer num, @Nullable String str, @Nullable String str2) {
        Map<String, String> A002 = AnonymousClass0VY.A00("event_type", AnonymousClass0aS.A00(num));
        if (!TextUtils.isEmpty(str)) {
            A002.put("event_extra_info", str);
        }
        if (!TextUtils.isEmpty(str2)) {
            A002.put("dpn", str2);
        }
        A01(this, "fbns_message_event", A002);
    }

    public final void A05(@Nullable String str) {
        Map<String, String> A002 = AnonymousClass0VY.A00("event_type", "verify_sender_failed");
        if (!TextUtils.isEmpty(str)) {
            A002.put("event_extra_info", str);
        }
        A01(this, "fbns_auth_intent_event", A002);
    }

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/String;Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;)V */
    public static final void A01(@Nonnull C02420aV r2, String str, Map map) {
        AnonymousClass0VX r1 = new AnonymousClass0VX(str, r2.A04);
        r1.A01(map);
        r2.A02.A02(r1);
    }

    public C02420aV(Context context, AnonymousClass0XJ r4, RealtimeSinceBootClock realtimeSinceBootClock, C06570ne r6) {
        this.A04 = context.getPackageName();
        this.A01 = r4;
        this.A03 = realtimeSinceBootClock;
        this.A02 = r6;
    }
}
