package X;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.facebook.infer.annotation.Nullsafe;
import org.json.JSONException;
import org.json.JSONObject;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0jt  reason: invalid class name and case insensitive filesystem */
public final class C04920jt extends AnonymousClass0X3 {
    public final Context A00;
    public volatile AnonymousClass0X5 A01 = new AnonymousClass0X5(new JSONObject());

    @Override // X.AnonymousClass0X3
    public final void A01() {
        JSONObject jSONObject = new JSONObject();
        synchronized (this) {
            for (AnonymousClass0X4 r2 : super.A00) {
                try {
                    String A3v = r2.A3v();
                    if (!TextUtils.isEmpty(A3v)) {
                        jSONObject.put("host_name_v6", A3v);
                    }
                    String A2y = r2.A2y();
                    if (!TextUtils.isEmpty(A2y)) {
                        jSONObject.put("analytics_endpoint", A2y);
                    }
                } catch (JSONException unused) {
                }
            }
        }
        this.A01 = new AnonymousClass0X5(jSONObject);
    }

    @Override // X.AnonymousClass0X3
    public final void A02() {
        Context context = this.A00;
        context.sendBroadcast(new Intent("com.facebook.rti.mqtt.ACTION_MQTT_CONFIG_CHANGED").setPackage(context.getPackageName()));
    }

    public C04920jt(Context context) {
        this.A00 = context;
    }

    @Override // X.AnonymousClass0X3
    public final AnonymousClass0X5 A00() {
        return this.A01;
    }
}
