package defpackage;

import android.os.Bundle;
import android.view.View;

/* renamed from: s9  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4697s9 extends View.AccessibilityDelegate implements AbstractView$OnTouchListenerC4526r9 {
    public final C5887z9 F;
    public Runnable G;
    public boolean H;

    public C4697s9(C5887z9 z9Var) {
        this.F = z9Var;
    }

    public final boolean a(View view, boolean z) {
        if (this.F.g() || !this.F.k(view, z)) {
            return false;
        }
        if (!z) {
            AbstractC3535lK0.a("MobileUsingMenuBySwButtonTap");
        }
        Runnable runnable = this.G;
        if (runnable == null) {
            return true;
        }
        runnable.run();
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x002f  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0127  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x012f  */
    /* JADX WARNING: Removed duplicated region for block: B:65:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouch(android.view.View r17, android.view.MotionEvent r18) {
        /*
        // Method dump skipped, instructions count: 326
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C4697s9.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }

    public boolean performAccessibilityAction(View view, int i, Bundle bundle) {
        if (i != 16) {
            return super.performAccessibilityAction(view, i, bundle);
        }
        if (!this.F.g()) {
            a(view, false);
        } else {
            this.F.f();
        }
        view.playSoundEffect(0);
        view.sendAccessibilityEvent(1);
        return true;
    }
}
