package X;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.NetworkInfo;
import android.os.PowerManager;
import android.os.Process;
import android.os.SystemClock;
import com.facebook.infer.annotation.Nullsafe;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.Nullable;

@TargetApi(3)
@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1QK  reason: invalid class name */
public final class AnonymousClass1QK {
    public static final AtomicLong A0A = new AtomicLong(0);
    public final Context A00;
    public final C145923x A01;
    public final AnonymousClass1QS A02;
    public final long A03;
    public final long A04;
    public final long A05 = SystemClock.elapsedRealtime();
    public final AnonymousClass1Kw A06;
    public final AnonymousClass1PJ A07;
    public final String A08;
    public final String A09;

    public final void A04(String str, int i, int i2, long j, int i3, long j2) {
        A00(this, "mqtt_publish_debug", AnonymousClass1Ks.A00("result", "success", "operation", str, "qos", Integer.toString(i), "msg_id", Integer.toString(i2), "original_ops_id", Integer.toString(i3), "timespan_ms", Long.toString(j), "retry_cnt", Integer.toString(0), "mqtt_session_id", Long.toString(j2)));
    }

    public final void A05(String str, String str2, int i, int i2, int i3, @Nullable Throwable th, long j) {
        Map<String, String> A002 = AnonymousClass1Ks.A00("result", str, "operation", str2, "qos", Integer.toString(i), "msg_id", Integer.toString(i2), "original_ops_id", Integer.toString(i3), "retry_cnt", Integer.toString(0), "mqtt_session_id", Long.toString(j));
        if (th != null) {
            A002.put("error_message", th.toString());
        }
        A00(this, "mqtt_publish_debug", A002);
    }

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/String;Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;)V */
    public static final void A00(AnonymousClass1QK r5, String str, Map map) {
        String obj;
        map.put("service_name", r5.A09);
        map.put("service_session_id", Long.toString(r5.A05));
        map.put("process_id", Long.toString(r5.A04));
        map.put("logger_object_id", Long.toString(r5.A03));
        if (!map.containsKey("network_session_id")) {
            map.put("network_session_id", Long.toString(r5.A02.A06.get()));
        }
        AnonymousClass1Kt r4 = new AnonymousClass1Kt(str, r5.A08);
        for (Map.Entry entry : map.entrySet()) {
            String obj2 = entry.getKey().toString();
            if (entry.getValue() == null) {
                obj = "";
            } else {
                obj = entry.getValue().toString();
            }
            r4.A03.put(obj2, obj);
        }
        r5.A06.reportEvent(r4);
    }

    /* JADX WARN: Incorrect args count in method signature: (Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;Landroid/net/NetworkInfo;)V */
    public static void A01(AnonymousClass1QK r5, @Nullable Map map, NetworkInfo networkInfo) {
        if (networkInfo != null) {
            String obj = networkInfo.getState().toString();
            String typeName = networkInfo.getTypeName();
            if (typeName == null) {
                typeName = "";
            }
            String subtypeName = networkInfo.getSubtypeName();
            if (subtypeName == null) {
                subtypeName = "";
            }
            String extraInfo = networkInfo.getExtraInfo();
            if (extraInfo == null) {
                extraInfo = "";
            }
            map.put("network_state", obj);
            map.put("network_type", typeName);
            map.put("network_subtype", subtypeName);
            map.put("network_extra_info", extraInfo);
        } else {
            map.put("network_info", "null");
        }
        boolean z = false;
        try {
            AnonymousClass1QO A002 = r5.A02.A03.A00("power", PowerManager.class);
            if (A002.A02() && ((PowerManager) A002.A01()).isDeviceIdleMode()) {
                z = true;
            }
        } catch (Exception unused) {
            AnonymousClass0MD.A04("MqttNetworkManager", "Exception in getting DeviceIdleMode");
        }
        map.put("is_in_idle_mode", Boolean.toString(z));
    }

    public final void A03(long j, int i, String str, AnonymousClass1QO<Throwable> r11, long j2, long j3, NetworkInfo networkInfo) {
        Map<String, String> A002 = AnonymousClass1Ks.A00("timespan_ms", String.valueOf(j), "port", String.valueOf(i), "he_state", str);
        if (r11.A02()) {
            String obj = r11.A01().toString();
            if (r11.A01().getCause() != null) {
                obj = AnonymousClass006.A09(obj, " Caused by: ", r11.A01().getCause().toString());
            }
            A002.put("error_message", obj);
        }
        A002.put("mqtt_session_id", Long.toString(j2));
        A02(A002, j3);
        A01(this, A002, networkInfo);
        A00(this, "mqtt_socket_connect", A002);
    }

    public AnonymousClass1QK(Context context, String str, @Nullable AnonymousClass1QS r5, C145923x r6, AnonymousClass1Kw r7, AnonymousClass1PJ r8) {
        this.A00 = context;
        this.A09 = str;
        this.A02 = r5;
        this.A01 = r6;
        this.A08 = context.getPackageName();
        this.A06 = r7;
        this.A07 = r8;
        this.A04 = (long) Process.myPid();
        this.A03 = A0A.incrementAndGet();
    }

    public static void A02(Map<String, String> map, long j) {
        map.put("network_session_id", Long.toString(j));
    }
}
