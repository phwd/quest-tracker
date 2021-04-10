package defpackage;

import android.os.Build;
import android.view.View;

/* renamed from: wv1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5506wv1 extends AbstractC5336vv1 {
    public static boolean g = true;

    @Override // defpackage.AbstractC5846yv1
    public void f(View view, int i) {
        if (Build.VERSION.SDK_INT == 28) {
            super.f(view, i);
        } else if (g) {
            try {
                view.setTransitionVisibility(i);
            } catch (NoSuchMethodError unused) {
                g = false;
            }
        }
    }
}
