package X;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.SystemClock;
import com.facebook.rti.common.time.RealtimeSinceBootClock;
import com.oculus.horizon.abuse_prevention.VideoUploaderCleanerServiceManager;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: X.0nI  reason: invalid class name */
public abstract class AnonymousClass0nI implements AbstractC01730Wz {
    public int A00 = ((int) (System.currentTimeMillis() / VideoUploaderCleanerServiceManager.STALE_THRESHOLD_MILLIS));
    public long A01 = SystemClock.elapsedRealtime();
    public SharedPreferences A02;
    public final String A03;
    public final Context A04;
    public final RealtimeSinceBootClock A05;
    public final AnonymousClass0nN A06;
    public final String A07;
    public final HashMap<String, Long> A08 = new HashMap<>();
    public final boolean A09;

    private synchronized void A01() {
        if (this.A02 == null) {
            this.A02 = AnonymousClass0WG.A00.A00(this.A04, new AnonymousClass0WI(AnonymousClass006.A08("rti.mqtt.counter.", this.A07, ".", this.A03)).A00, false);
        }
    }

    private void A00() {
        HashMap hashMap;
        if (this.A09) {
            HashMap<String, Long> hashMap2 = this.A08;
            synchronized (hashMap2) {
                hashMap = new HashMap(hashMap2);
                hashMap2.clear();
            }
            if (!hashMap.isEmpty()) {
                A01();
                SharedPreferences.Editor edit = this.A02.edit();
                for (Map.Entry entry : hashMap.entrySet()) {
                    edit.putLong((String) entry.getKey(), this.A02.getLong((String) entry.getKey(), 0) + ((Number) entry.getValue()).longValue());
                }
                edit.apply();
                this.A01 = SystemClock.elapsedRealtime();
            }
        }
    }

    public AnonymousClass0nI(Context context, String str, AnonymousClass0nN r7, RealtimeSinceBootClock realtimeSinceBootClock, String str2, boolean z) {
        this.A04 = context;
        this.A07 = str;
        this.A06 = r7;
        this.A05 = realtimeSinceBootClock;
        this.A03 = str2;
        this.A09 = z;
    }

    @Nullable
    public final JSONObject A02(boolean z) throws JSONException {
        int i;
        int indexOf;
        A01();
        JSONObject jSONObject = new JSONObject();
        int currentTimeMillis = (int) (System.currentTimeMillis() / VideoUploaderCleanerServiceManager.STALE_THRESHOLD_MILLIS);
        Map<String, ?> all = this.A02.getAll();
        SharedPreferences.Editor edit = this.A02.edit();
        for (Map.Entry<String, ?> entry : all.entrySet()) {
            String key = entry.getKey();
            if (key == null || (indexOf = key.indexOf(".")) <= 0) {
                i = 0;
            } else {
                try {
                    i = Integer.parseInt(key.substring(0, indexOf));
                } catch (NumberFormatException unused) {
                    i = 0;
                }
            }
            if (i <= currentTimeMillis && i + 3 >= currentTimeMillis) {
                if (z) {
                    jSONObject.putOpt(entry.getKey(), entry.getValue());
                } else if (i != currentTimeMillis) {
                    jSONObject.putOpt(entry.getKey(), entry.getValue());
                }
            }
            edit.remove(entry.getKey());
        }
        edit.apply();
        if (jSONObject.length() == 0) {
            return null;
        }
        if (!z) {
            return jSONObject;
        }
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("period_ms", System.currentTimeMillis() % VideoUploaderCleanerServiceManager.STALE_THRESHOLD_MILLIS);
        jSONObject2.put("data", jSONObject);
        return jSONObject2;
    }

    public final void A03(long j, String... strArr) {
        int currentTimeMillis = (int) (System.currentTimeMillis() / VideoUploaderCleanerServiceManager.STALE_THRESHOLD_MILLIS);
        if (this.A00 != currentTimeMillis) {
            this.A00 = currentTimeMillis;
            A00();
        }
        String valueOf = String.valueOf(currentTimeMillis);
        StringBuilder sb = new StringBuilder();
        sb.append(valueOf);
        for (String str : strArr) {
            sb.append(".");
            sb.append(str);
        }
        String obj = sb.toString();
        HashMap<String, Long> hashMap = this.A08;
        synchronized (hashMap) {
            Long l = hashMap.get(obj);
            if (l == null) {
                l = 0L;
            }
            hashMap.put(obj, Long.valueOf(l.longValue() + j));
        }
        if (SystemClock.elapsedRealtime() - this.A01 > 3600000) {
            A00();
        }
    }
}
