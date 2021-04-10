package X;

import android.text.TextUtils;
import com.facebook.infer.annotation.Nullsafe;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0v4  reason: invalid class name */
public abstract class AnonymousClass0v4 {
    public final List<AnonymousClass0vW> A00 = new LinkedList();

    public final void A00() {
        AnonymousClass0v5 r5 = (AnonymousClass0v5) this;
        JSONObject jSONObject = new JSONObject();
        synchronized (r5) {
            for (AnonymousClass0vW r2 : ((AnonymousClass0v4) r5).A00) {
                try {
                    String A45 = r2.A45();
                    if (!TextUtils.isEmpty(A45)) {
                        jSONObject.put("host_name_v6", A45);
                    }
                    String A2v = r2.A2v();
                    if (!TextUtils.isEmpty(A2v)) {
                        jSONObject.put("analytics_endpoint", A2v);
                    }
                } catch (JSONException unused) {
                }
            }
        }
        r5.A01 = new C08460xH(jSONObject);
    }
}
