package defpackage;

import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import androidx.recyclerview.widget.RecyclerView;

/* renamed from: ZK0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ZK0 extends C5349w {
    public final RecyclerView d;
    public final YK0 e;

    public ZK0(RecyclerView recyclerView) {
        this.d = recyclerView;
        C5349w j = j();
        if (j == null || !(j instanceof YK0)) {
            this.e = new YK0(this);
        } else {
            this.e = (YK0) j;
        }
    }

    @Override // defpackage.C5349w
    public void c(View view, AccessibilityEvent accessibilityEvent) {
        IK0 ik0;
        this.b.onInitializeAccessibilityEvent(view, accessibilityEvent);
        if ((view instanceof RecyclerView) && !k() && (ik0 = ((RecyclerView) view).U) != null) {
            ik0.j0(accessibilityEvent);
        }
    }

    @Override // defpackage.C5349w
    public void d(View view, D d2) {
        IK0 ik0;
        this.b.onInitializeAccessibilityNodeInfo(view, d2.b);
        if (!k() && (ik0 = this.d.U) != null) {
            RecyclerView recyclerView = ik0.b;
            ik0.l0(recyclerView.f9482J, recyclerView.Q0, d2);
        }
    }

    @Override // defpackage.C5349w
    public boolean g(View view, int i, Bundle bundle) {
        IK0 ik0;
        if (super.g(view, i, bundle)) {
            return true;
        }
        if (k() || (ik0 = this.d.U) == null) {
            return false;
        }
        RecyclerView recyclerView = ik0.b;
        return ik0.D0(recyclerView.f9482J, recyclerView.Q0, i, bundle);
    }

    public C5349w j() {
        return this.e;
    }

    public boolean k() {
        return this.d.R();
    }
}
