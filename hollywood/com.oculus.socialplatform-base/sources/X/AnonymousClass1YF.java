package X;

import android.text.TextUtils;
import com.facebook.infer.annotation.Nullsafe;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1YF  reason: invalid class name */
public abstract class AnonymousClass1YF {
    public final List<AnonymousClass1YG> A00 = new LinkedList();

    public final void A00() {
        int i;
        AnonymousClass1YE r3 = (AnonymousClass1YE) this;
        try {
            JSONObject jSONObject = new JSONObject(r3.A01);
            synchronized (r3) {
                for (AnonymousClass1YG r2 : ((AnonymousClass1YF) r3).A00) {
                    try {
                        String A4T = r2.A4T();
                        if (!TextUtils.isEmpty(A4T)) {
                            jSONObject.put("host_name_v6", A4T);
                        }
                        String A3O = r2.A3O();
                        if (!TextUtils.isEmpty(A3O)) {
                            jSONObject.put("analytics_endpoint", A3O);
                        }
                    } catch (JSONException unused) {
                    }
                }
            }
            if ("sandbox".equals(r3.A03) && !TextUtils.isEmpty(r3.A02)) {
                String str = r3.A02;
                try {
                    if (!TextUtils.isEmpty(str)) {
                        if (str.contains(":")) {
                            String[] split = str.split(":", 2);
                            str = split[0];
                            i = Integer.parseInt(split[1]);
                        } else {
                            i = 8883;
                        }
                        if (!TextUtils.isEmpty(str)) {
                            jSONObject.put("host_name_v6", str);
                            jSONObject.put("default_port", i);
                            jSONObject.put("backup_port", i);
                            jSONObject.put("use_ssl", false);
                            jSONObject.put("use_compression", false);
                        }
                    }
                    if (!TextUtils.isEmpty(null)) {
                        jSONObject.put("php_sandbox_host_name", (Object) null);
                    }
                } catch (Throwable th) {
                    AnonymousClass0MD.A08("ConnectionConfigManager", "Failed to parse mqtt sandbox URL", th);
                }
            }
            r3.A00 = new AnonymousClass22Y(jSONObject);
        } catch (JSONException e) {
            AnonymousClass0MD.A0C("BasicConnectionConfigManager", e, "Could not load connection config. Using default");
            r3.A00 = new AnonymousClass22Y(new JSONObject());
        }
    }
}
