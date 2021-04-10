package defpackage;

import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;

/* renamed from: ZW  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ZW extends C5349w {
    public final /* synthetic */ C1672aX d;

    public ZW(C1672aX aXVar) {
        this.d = aXVar;
    }

    @Override // defpackage.C5349w
    public void d(View view, D d2) {
        A a2;
        this.b.onInitializeAccessibilityNodeInfo(view, d2.b);
        if (this.d.H) {
            a2 = A.d;
        } else {
            a2 = A.e;
        }
        d2.b.addAction((AccessibilityNodeInfo.AccessibilityAction) new A(1, a2.b()).i);
        d2.b.addAction((AccessibilityNodeInfo.AccessibilityAction) a2.i);
    }

    @Override // defpackage.C5349w
    public boolean g(View view, int i, Bundle bundle) {
        if (i == 262144 || i == 524288) {
            return this.d.performClick();
        }
        return super.g(view, i, bundle);
    }
}
