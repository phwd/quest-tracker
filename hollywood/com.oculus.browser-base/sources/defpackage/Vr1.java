package defpackage;

import android.app.Activity;
import android.os.Handler;
import org.chromium.chrome.browser.profiles.Profile;

/* renamed from: Vr1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Vr1 {

    /* renamed from: a  reason: collision with root package name */
    public final Activity f9111a;
    public final Handler b;
    public final WT c;

    public Vr1(Activity activity, Handler handler, WT wt) {
        this.f9111a = activity;
        this.b = handler;
        this.c = wt;
    }

    public void a(XY xy) {
        Tm1 tm1 = (Tm1) this.c.apply(Profile.b());
        tm1.a(new Sr1(this, tm1, xy));
    }
}
