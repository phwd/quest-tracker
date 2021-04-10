package X;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.NetworkInfo;
import android.os.PowerManager;
import android.os.Process;
import android.os.SystemClock;
import android.text.TextUtils;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.alpenglow.logging.LoggerConstants;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.Nullable;

@TargetApi(3)
@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0x2  reason: invalid class name */
public final class AnonymousClass0x2 {
    public static final AtomicLong A0B = new AtomicLong(0);
    public final Context A00;
    @Nullable
    public final AbstractC09080yd A01;
    public final C07620vf A02;
    public final C08400xB A03;
    public final long A04;
    public final long A05;
    public final long A06 = SystemClock.elapsedRealtime();
    public final C07980wL A07;
    public final C07640vh A08;
    public final String A09;
    public final String A0A;

    public final void A04(String str, int i, int i2, long j, int i3, long j2) {
        A00(this, "mqtt_publish_debug", C09120yh.A00("result", LoggerConstants.CONFIGURATION_FETCH_SUCCESS, "operation", str, "qos", Integer.toString(i), "msg_id", Integer.toString(i2), "original_ops_id", Integer.toString(i3), "timespan_ms", Long.toString(j), "retry_cnt", Integer.toString(0), "mqtt_session_id", Long.toString(j2)));
    }

    public final void A05(String str, String str2, int i, int i2, int i3, @Nullable Throwable th, long j) {
        Map<String, String> A002 = C09120yh.A00("result", str, "operation", str2, "qos", Integer.toString(i), "msg_id", Integer.toString(i2), "original_ops_id", Integer.toString(i3), "retry_cnt", Integer.toString(0), "mqtt_session_id", Long.toString(j));
        if (th != null) {
            A002.put("error_message", th.toString());
        }
        A00(this, "mqtt_publish_debug", A002);
    }

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/String;Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;)V */
    public static final void A00(AnonymousClass0x2 r3, String str, Map map) {
        map.put("service_name", r3.A0A);
        map.put("service_session_id", Long.toString(r3.A06));
        map.put("process_id", Long.toString(r3.A05));
        map.put("logger_object_id", Long.toString(r3.A04));
        if (!map.containsKey("network_session_id")) {
            map.put("network_session_id", Long.toString(r3.A03.A06.get()));
        }
        C07840w6 r1 = new C07840w6(str, r3.A09);
        r1.A01(map);
        r3.A07.A02(r1);
    }

    /* JADX WARN: Incorrect args count in method signature: (Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;Landroid/net/NetworkInfo;)V */
    public static void A01(AnonymousClass0x2 r5, @Nullable Map map, NetworkInfo networkInfo) {
        if (networkInfo != null) {
            String state = networkInfo.getState().toString();
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
            map.put("network_state", state);
            map.put("network_type", typeName);
            map.put("network_subtype", subtypeName);
            map.put("network_extra_info", extraInfo);
        } else {
            map.put("network_info", "null");
        }
        boolean z = false;
        try {
            AbstractC09150yk A002 = r5.A03.A03.A00("power", PowerManager.class);
            if (A002.A02() && ((PowerManager) A002.A01()).isDeviceIdleMode()) {
                z = true;
            }
        } catch (Exception unused) {
            AnonymousClass0NK.A01("MqttNetworkManager", "Exception in getting DeviceIdleMode");
        }
        map.put("is_in_idle_mode", Boolean.toString(z));
    }

    public final void A03(long j, int i, String str, AbstractC09150yk<Throwable> r11, long j2, long j3, NetworkInfo networkInfo) {
        Map<String, String> A002 = C09120yh.A00("timespan_ms", String.valueOf(j), "port", String.valueOf(i), "he_state", str);
        if (r11.A02()) {
            String th = r11.A01().toString();
            if (r11.A01().getCause() != null) {
                th = AnonymousClass006.A07(th, " Caused by: ", r11.A01().getCause().toString());
            }
            A002.put("error_message", th);
        }
        A002.put("mqtt_session_id", Long.toString(j2));
        A02(A002, j3);
        A01(this, A002, networkInfo);
        A00(this, "mqtt_socket_connect", A002);
    }

    public AnonymousClass0x2(Context context, String str, @Nullable C08400xB r5, C07620vf r6, C07980wL r7, C07640vh r8, AbstractC09080yd r9) {
        this.A00 = context;
        this.A0A = str;
        this.A03 = r5;
        this.A02 = r6;
        this.A09 = context.getPackageName();
        this.A07 = r7;
        this.A08 = r8;
        this.A05 = (long) Process.myPid();
        this.A04 = A0B.incrementAndGet();
        this.A01 = r9;
    }

    public static void A02(Map<String, String> map, long j) {
        map.put("network_session_id", Long.toString(j));
    }

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;LX/0yk<Ljava/lang/Integer;>;LX/0yk<Ljava/lang/Integer;>;ZIJLandroid/net/NetworkInfo;)V */
    public final void A06(String str, String str2, @Nullable String str3, AbstractC09150yk r7, AbstractC09150yk r8, boolean z, long j, NetworkInfo networkInfo) {
        Map<String, String> A002 = C09120yh.A00("act", str, "running", String.valueOf(z));
        A002.put("process_id", Long.toString(this.A05));
        A002.put("thread_id", Long.toString(Thread.currentThread().getId()));
        if (str2 != null) {
            A002.put("mqtt_persistence_string", str2);
        }
        A02(A002, j);
        A01(this, A002, networkInfo);
        if (!TextUtils.isEmpty(str3)) {
            A002.put("calr", str3);
        }
        if (r7.A02()) {
            A002.put("flg", String.valueOf(r7.A01()));
        }
        if (r8.A02()) {
            A002.put("sta_id", String.valueOf(r8.A01()));
        }
        A00(this, "mqtt_service_state", A002);
    }
}
