package X;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.SystemClock;
import com.facebook.rti.common.time.RealtimeSinceBootClock;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: X.0w1  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC07790w1 implements AbstractC07670vl {
    public int A00 = ((int) (System.currentTimeMillis() / 86400000));
    public long A01 = SystemClock.elapsedRealtime();
    public SharedPreferences A02;
    public final String A03;
    public final Context A04;
    public final RealtimeSinceBootClock A05;
    public final C07640vh A06;
    public final String A07;
    public final HashMap<String, Long> A08 = new HashMap<>();
    public final boolean A09;

    private synchronized void A01() {
        if (this.A02 == null) {
            this.A02 = this.A04.getSharedPreferences(new C07570vV(AnonymousClass006.A08("rti.mqtt.counter.", this.A07, ".", this.A03)).A00, 0);
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
                    edit.putLong((String) entry.getKey(), this.A02.getLong((String) entry.getKey(), 0) + ((Long) entry.getValue()).longValue());
                }
                edit.apply();
                this.A01 = SystemClock.elapsedRealtime();
            }
        }
    }

    public AbstractC07790w1(Context context, String str, C07640vh r7, RealtimeSinceBootClock realtimeSinceBootClock, String str2, boolean z) {
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
        int currentTimeMillis = (int) (System.currentTimeMillis() / 86400000);
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
        jSONObject2.put("period_ms", System.currentTimeMillis() % 86400000);
        jSONObject2.put("data", jSONObject);
        return jSONObject2;
    }

    public final void A03(long j, String... strArr) {
        int currentTimeMillis = (int) (System.currentTimeMillis() / 86400000);
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
        String sb2 = sb.toString();
        HashMap<String, Long> hashMap = this.A08;
        synchronized (hashMap) {
            Long l = hashMap.get(sb2);
            if (l == null) {
                l = 0L;
            }
            hashMap.put(sb2, Long.valueOf(l.longValue() + j));
        }
        if (SystemClock.elapsedRealtime() - this.A01 > 3600000) {
            A00();
        }
    }
}
