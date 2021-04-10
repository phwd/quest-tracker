package defpackage;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import java.util.Map;
import java.util.WeakHashMap;

/* renamed from: YK0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class YK0 extends C5349w {
    public final ZK0 d;
    public Map e = new WeakHashMap();

    public YK0(ZK0 zk0) {
        this.d = zk0;
    }

    @Override // defpackage.C5349w
    public boolean a(View view, AccessibilityEvent accessibilityEvent) {
        C5349w wVar = (C5349w) this.e.get(view);
        if (wVar != null) {
            return wVar.a(view, accessibilityEvent);
        }
        return this.b.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    @Override // defpackage.C5349w
    public H b(View view) {
        C5349w wVar = (C5349w) this.e.get(view);
        if (wVar != null) {
            return wVar.b(view);
        }
        return super.b(view);
    }

    @Override // defpackage.C5349w
    public void c(View view, AccessibilityEvent accessibilityEvent) {
        C5349w wVar = (C5349w) this.e.get(view);
        if (wVar != null) {
            wVar.c(view, accessibilityEvent);
        } else {
            this.b.onInitializeAccessibilityEvent(view, accessibilityEvent);
        }
    }

    @Override // defpackage.C5349w
    public void d(View view, D d2) {
        IK0 ik0;
        if (this.d.k() || (ik0 = this.d.d.U) == null) {
            this.b.onInitializeAccessibilityNodeInfo(view, d2.b);
            return;
        }
        ik0.n0(view, d2);
        C5349w wVar = (C5349w) this.e.get(view);
        if (wVar != null) {
            wVar.d(view, d2);
        } else {
            this.b.onInitializeAccessibilityNodeInfo(view, d2.b);
        }
    }

    @Override // defpackage.C5349w
    public void e(View view, AccessibilityEvent accessibilityEvent) {
        C5349w wVar = (C5349w) this.e.get(view);
        if (wVar != null) {
            wVar.e(view, accessibilityEvent);
        } else {
            this.b.onPopulateAccessibilityEvent(view, accessibilityEvent);
        }
    }

    @Override // defpackage.C5349w
    public boolean f(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
        C5349w wVar = (C5349w) this.e.get(viewGroup);
        if (wVar != null) {
            return wVar.f(viewGroup, view, accessibilityEvent);
        }
        return this.b.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
    }

    @Override // defpackage.C5349w
    public boolean g(View view, int i, Bundle bundle) {
        if (this.d.k() || this.d.d.U == null) {
            return super.g(view, i, bundle);
        }
        C5349w wVar = (C5349w) this.e.get(view);
        if (wVar != null) {
            if (wVar.g(view, i, bundle)) {
                return true;
            }
        } else if (super.g(view, i, bundle)) {
            return true;
        }
        IK0 ik0 = this.d.d.U;
        PK0 pk0 = ik0.b.f9482J;
        return ik0.E0();
    }

    @Override // defpackage.C5349w
    public void h(View view, int i) {
        C5349w wVar = (C5349w) this.e.get(view);
        if (wVar != null) {
            wVar.h(view, i);
        } else {
            this.b.sendAccessibilityEvent(view, i);
        }
    }

    @Override // defpackage.C5349w
    public void i(View view, AccessibilityEvent accessibilityEvent) {
        C5349w wVar = (C5349w) this.e.get(view);
        if (wVar != null) {
            wVar.i(view, accessibilityEvent);
        } else {
            this.b.sendAccessibilityEventUnchecked(view, accessibilityEvent);
        }
    }
}
