package defpackage;

import android.view.View;

/* renamed from: tv1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC4996tv1 extends AbstractC5846yv1 {
    public static boolean c = true;

    @Override // defpackage.AbstractC5846yv1
    public void a(View view) {
    }

    @Override // defpackage.AbstractC5846yv1
    public float b(View view) {
        if (c) {
            try {
                return view.getTransitionAlpha();
            } catch (NoSuchMethodError unused) {
                c = false;
            }
        }
        return view.getAlpha();
    }

    @Override // defpackage.AbstractC5846yv1
    public void c(View view) {
    }

    @Override // defpackage.AbstractC5846yv1
    public void e(View view, float f) {
        if (c) {
            try {
                view.setTransitionAlpha(f);
                return;
            } catch (NoSuchMethodError unused) {
                c = false;
            }
        }
        view.setAlpha(f);
    }
}
