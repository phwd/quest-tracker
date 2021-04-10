package defpackage;

import J.N;
import android.content.SharedPreferences;
import org.chromium.chrome.browser.flags.CachedFeatureFlags;

/* renamed from: JG  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class JG extends AbstractC2574fl {
    public JG(String str, String str2, double d) {
        super(str, str2, 3, null);
    }

    @Override // defpackage.AbstractC2574fl
    public void a() {
        double MQdjXFDB = N.MQdjXFDB(this.f9946a, this.b, 1.0d);
        PU0 pu0 = NU0.f8549a;
        String b = b();
        pu0.f8694a.a(b);
        SharedPreferences.Editor edit = AbstractC3983nz.f10523a.edit();
        edit.putLong(b, Double.doubleToRawLongBits(MQdjXFDB));
        edit.apply();
    }

    public double c() {
        String b = b();
        Double d = (Double) CachedFeatureFlags.f.get(b);
        if (d == null) {
            NU0.f8549a.f8694a.a(b);
            SharedPreferences sharedPreferences = AbstractC3983nz.f10523a;
            P21 f0 = P21.f0();
            try {
                if (!sharedPreferences.contains(b)) {
                    d = Double.valueOf(1.0d);
                } else {
                    d = Double.valueOf(Double.longBitsToDouble(sharedPreferences.getLong(b, 0)));
                }
                f0.close();
                CachedFeatureFlags.f.put(b, d);
            } catch (Throwable th) {
                AbstractC0754Mh1.f8495a.a(th, th);
            }
        }
        return d.doubleValue();
        throw th;
    }
}
