package defpackage;

import android.net.Uri;
import android.util.Log;
import java.net.URL;
import java.util.HashMap;
import java.util.Objects;

/* renamed from: bY  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1854bY implements AbstractC2182dR {

    /* renamed from: a  reason: collision with root package name */
    public Uri f9546a;

    public C1854bY(String str) {
        this.f9546a = Uri.parse(str);
    }

    public void a(C2141dB dBVar) {
        try {
            HashMap hashMap = new HashMap(dBVar.size());
            XL0[] xl0Arr = AbstractC1585a.d;
            for (XL0 xl0 : xl0Arr) {
                String str = (String) dBVar.get(xl0);
                if (str != null && !str.isEmpty()) {
                    hashMap.put(xl0.toString(), str);
                }
            }
            URL url = new URL(this.f9546a.toString());
            String str2 = AbstractC1585a.f9392a;
            url.toString();
            Objects.requireNonNull(AbstractC1585a.b);
            AbstractC3050iY.a(hashMap, url, "application/x-www-form-urlencoded", null);
        } catch (Exception e) {
            String str3 = AbstractC1585a.f9392a;
            StringBuilder i = AbstractC2531fV.i("send exception: ");
            i.append(e.toString());
            Log.e(str3, i.toString());
            throw new C1651aM0("Error while sending report to Http Post Form.", e);
        }
    }

    public boolean b(String str) {
        if (str.isEmpty()) {
            return false;
        }
        if (str.equals(this.f9546a.getHost())) {
            return true;
        }
        this.f9546a = this.f9546a.buildUpon().authority(str).build();
        return true;
    }
}
