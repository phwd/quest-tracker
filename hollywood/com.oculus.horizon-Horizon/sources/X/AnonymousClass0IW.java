package X;

import com.facebook.infer.annotation.Nullsafe;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONException;
import org.json.JSONObject;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0IW  reason: invalid class name */
public final class AnonymousClass0IW extends AnonymousClass0nJ {
    @Override // X.AnonymousClass0nJ
    public final synchronized JSONObject A01(boolean z, boolean z2) throws JSONException {
        JSONObject jSONObject;
        jSONObject = new JSONObject();
        long j = 0;
        for (Map.Entry<AbstractC01720Wy, Object> entry : this.A01.entrySet()) {
            switch (((AnonymousClass0nF) entry.getKey()).ordinal()) {
                case 0:
                case 1:
                    j += ((AtomicLong) entry.getValue()).longValue();
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
                        jSONObject.putOpt(entry.getKey().getKey(), Long.valueOf(((AtomicLong) entry.getValue()).getAndSet(0)));
                        continue;
                    } else {
                        jSONObject.putOpt(entry.getKey().getKey(), entry.getValue());
                    }
            }
            jSONObject.putOpt(entry.getKey().getKey(), entry.getValue());
        }
        jSONObject.putOpt(AnonymousClass0nF.MqttTotalDurationMs.getKey(), Long.valueOf(j));
        return jSONObject;
    }

    public AnonymousClass0IW() {
        super("lc");
    }
}
