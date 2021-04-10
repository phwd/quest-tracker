package X;

import android.content.Intent;
import com.facebook.rti.common.guavalite.annotations.VisibleForTesting;
import javax.annotation.Nullable;
import org.json.JSONObject;

@VisibleForTesting
/* renamed from: X.0mJ  reason: invalid class name */
public class AnonymousClass0mJ extends AbstractC02020Yq {
    public long A00;
    public final long A01;

    @Nullable
    public static AnonymousClass0mJ A00(Object obj) {
        try {
            String str = (String) obj;
            if (str.length() > 50000) {
                return null;
            }
            JSONObject jSONObject = new JSONObject(str);
            return new AnonymousClass0mJ(Intent.parseUri(jSONObject.getString("key_intent"), 0), jSONObject.getString("key_notifid"), jSONObject.getLong("key_timestamp_received"), jSONObject.getLong("key_timestamp_last_retried"));
        } catch (Exception unused) {
            return null;
        }
    }

    @Nullable
    public final String A01() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.putOpt("key_intent", super.A00.toUri(0));
            jSONObject.putOpt("key_notifid", super.A01);
            jSONObject.putOpt("key_timestamp_received", Long.valueOf(this.A01));
            jSONObject.putOpt("key_timestamp_last_retried", Long.valueOf(this.A00));
            return jSONObject.toString();
        } catch (Exception unused) {
            return null;
        }
    }

    public AnonymousClass0mJ(Intent intent, String str, long j, long j2) {
        super(intent, str);
        this.A01 = j;
        this.A00 = j2;
    }
}
