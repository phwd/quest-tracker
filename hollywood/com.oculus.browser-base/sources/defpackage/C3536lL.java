package defpackage;

import android.content.Context;
import org.chromium.chrome.browser.profiles.Profile;

/* renamed from: lL  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3536lL {

    /* renamed from: a  reason: collision with root package name */
    public final Context f10339a;
    public final Profile b;
    public AbstractC3365kL c;
    public C5572xH0 d;
    public UH0 e;
    public boolean f;

    public C3536lL(Context context, Profile profile) {
        this.f10339a = context;
        this.b = profile;
    }

    public void a() {
        this.c = null;
        C5572xH0 xh0 = this.d;
        if (xh0 != null) {
            xh0.b.b();
            ViewTreeObserver$OnPreDrawListenerC1587a00 a00 = xh0.c;
            if (a00 != null) {
                a00.a(null);
            }
            xh0.c = null;
            if (this.f) {
                this.f = false;
                AbstractC3365kL kLVar = this.c;
                if (kLVar != null) {
                    ((C2861hP) kLVar).F.c(false, null);
                }
            }
        }
    }
}
