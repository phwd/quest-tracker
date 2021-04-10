package defpackage;

import android.content.Context;
import android.view.View;
import java.lang.ref.WeakReference;
import org.chromium.chrome.browser.app.ChromeActivity;

/* renamed from: Kr  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0653Kr extends JX0 {
    public C0653Kr(WeakReference weakReference) {
        super(weakReference);
    }

    @Override // defpackage.C3493l60
    public boolean d(View view) {
        boolean z;
        ChromeActivity l = k();
        if (l != null) {
            z = l.S0.c(view);
            l.S0.b();
        } else {
            z = false;
        }
        if (c(view) || z) {
            return true;
        }
        return false;
    }

    @Override // defpackage.JX0, defpackage.C3493l60
    public boolean f(Context context, View view) {
        ChromeActivity l = k();
        return super.f(context, view) || (l != null && l.S0.c(view));
    }

    /* renamed from: l */
    public ChromeActivity k() {
        return (ChromeActivity) super.k();
    }
}
