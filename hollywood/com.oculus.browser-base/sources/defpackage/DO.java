package defpackage;

import androidx.recyclerview.widget.LinearLayoutManager;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: DO  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DO extends AK0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ FO f7887a;

    public DO(FO fo) {
        this.f7887a = fo;
    }

    @Override // defpackage.AK0
    public void d(int i, int i2) {
        FO fo = this.f7887a;
        String str = fo.e;
        if (str != null) {
            EO eo = new EO();
            try {
                JSONObject jSONObject = new JSONObject(str);
                eo.f7959a = jSONObject.getInt("pos");
                eo.b = jSONObject.getInt("lpos");
                eo.c = jSONObject.getInt("off");
            } catch (JSONException unused) {
                eo = null;
            }
            boolean z = true;
            if (eo != null) {
                if (fo.c.T.b() <= eo.b) {
                    z = false;
                } else {
                    LinearLayoutManager linearLayoutManager = (LinearLayoutManager) fo.c.U;
                    if (linearLayoutManager != null) {
                        linearLayoutManager.C1(eo.f7959a, eo.c);
                    }
                }
            }
            if (z) {
                this.f7887a.e = null;
            }
        }
    }
}
