package X;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: X.1Q3  reason: invalid class name */
public abstract class AnonymousClass1Q3 implements AnonymousClass1PS {
    public final String A00;
    @GuardedBy("this")
    public final HashMap<AnonymousClass1Q4, Object> A01 = new HashMap<>();

    public final synchronized <T> T A00(AnonymousClass1Q4 r5) {
        HashMap<AnonymousClass1Q4, Object> hashMap;
        try {
            hashMap = this.A01;
            if (!hashMap.containsKey(r5)) {
                hashMap.put(r5, r5.getValueType().newInstance());
            }
        } catch (Exception e) {
            throw new RuntimeException(String.format("Incorrect usage for %s type %s", r5.getKey(), r5.getValueType()), e);
        }
        return (T) hashMap.get(r5);
    }

    public final synchronized JSONObject A01(boolean z, boolean z2) throws JSONException {
        JSONObject jSONObject;
        boolean z3 = this instanceof AnonymousClass1Q5;
        synchronized (this) {
            if (!z3) {
                jSONObject = new JSONObject();
                for (Map.Entry<AnonymousClass1Q4, Object> entry : this.A01.entrySet()) {
                    jSONObject.putOpt(entry.getKey().getKey(), entry.getValue());
                }
            } else {
                jSONObject = new JSONObject();
                long j = 0;
                for (Map.Entry<AnonymousClass1Q4, Object> entry2 : this.A01.entrySet()) {
                    switch (((AnonymousClass1QD) entry2.getKey()).ordinal()) {
                        case 0:
                        case 1:
                            j += ((AtomicLong) entry2.getValue()).longValue();
                            break;
                        case 5:
                        case 6:
                        case 7:
                        case 8:
                        case 9:
                        case 10:
                        case 11:
                        case 12:
                        case 13:
                            if (z) {
                                jSONObject.putOpt(entry2.getKey().getKey(), Long.valueOf(((AtomicLong) entry2.getValue()).getAndSet(0)));
                                continue;
                            } else {
                                jSONObject.putOpt(entry2.getKey().getKey(), entry2.getValue());
                            }
                    }
                    jSONObject.putOpt(entry2.getKey().getKey(), entry2.getValue());
                }
                jSONObject.putOpt(AnonymousClass1QD.MqttTotalDurationMs.getKey(), Long.valueOf(j));
            }
        }
        return jSONObject;
    }

    public final synchronized <T> void A02(AnonymousClass1Q4 r2, @Nullable T t) {
        this.A01.put(r2, t);
    }

    public final String toString() {
        try {
            return A01(false, false).toString();
        } catch (JSONException unused) {
            return "";
        }
    }

    public AnonymousClass1Q3(String str) {
        this.A00 = str;
    }
}
