package X;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: X.0xE  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC08430xE implements AbstractC07670vl {
    public final String A00;
    @GuardedBy("this")
    public final HashMap<AbstractC09350zH, Object> A01 = new HashMap<>();

    public final synchronized <T> T A00(AbstractC09350zH r5) {
        HashMap<AbstractC09350zH, Object> hashMap;
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
        boolean z3 = this instanceof C08560xS;
        synchronized (this) {
            if (!z3) {
                JSONObject jSONObject = new JSONObject();
                for (Map.Entry<AbstractC09350zH, Object> entry : this.A01.entrySet()) {
                    jSONObject.putOpt(entry.getKey().getKey(), entry.getValue());
                }
                return jSONObject;
            }
            JSONObject jSONObject2 = new JSONObject();
            long j = 0;
            for (Map.Entry<AbstractC09350zH, Object> entry2 : this.A01.entrySet()) {
                switch (((EnumC08570xT) entry2.getKey()).ordinal()) {
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
                            jSONObject2.putOpt(entry2.getKey().getKey(), Long.valueOf(((AtomicLong) entry2.getValue()).getAndSet(0)));
                            continue;
                        } else {
                            jSONObject2.putOpt(entry2.getKey().getKey(), entry2.getValue());
                        }
                }
                jSONObject2.putOpt(entry2.getKey().getKey(), entry2.getValue());
            }
            jSONObject2.putOpt(EnumC08570xT.MqttTotalDurationMs.getKey(), Long.valueOf(j));
            return jSONObject2;
        }
    }

    public final synchronized <T> void A02(AbstractC09350zH r2, @Nullable T t) {
        this.A01.put(r2, t);
    }

    public final String toString() {
        try {
            return A01(false, false).toString();
        } catch (JSONException unused) {
            return "";
        }
    }

    public AbstractC08430xE(String str) {
        this.A00 = str;
    }
}
