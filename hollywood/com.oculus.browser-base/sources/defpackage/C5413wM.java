package defpackage;

import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: wM  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5413wM extends H {
    public final /* synthetic */ AbstractC5583xM b;

    public C5413wM(AbstractC5583xM xMVar) {
        this.b = xMVar;
    }

    @Override // defpackage.H
    public D a(int i) {
        return new D(AccessibilityNodeInfo.obtain(this.b.r(i).b));
    }

    @Override // defpackage.H
    public D b(int i) {
        int i2 = i == 2 ? this.b.n : this.b.o;
        if (i2 == Integer.MIN_VALUE) {
            return null;
        }
        return new D(AccessibilityNodeInfo.obtain(this.b.r(i2).b));
    }

    @Override // defpackage.H
    public boolean c(int i, int i2, Bundle bundle) {
        int i3;
        AbstractC5583xM xMVar = this.b;
        if (i != -1) {
            boolean z = true;
            if (i2 == 1) {
                return xMVar.x(i);
            }
            if (i2 == 2) {
                return xMVar.k(i);
            }
            if (i2 == 64) {
                if (!xMVar.k.isEnabled() || !xMVar.k.isTouchExplorationEnabled() || (i3 = xMVar.n) == i) {
                    z = false;
                } else {
                    if (i3 != Integer.MIN_VALUE) {
                        xMVar.j(i3);
                    }
                    xMVar.n = i;
                    xMVar.l.invalidate();
                    xMVar.y(i, 32768);
                }
                return z;
            } else if (i2 != 128) {
                return xMVar.s(i, i2, bundle);
            } else {
                return xMVar.j(i);
            }
        } else {
            View view = xMVar.l;
            AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
            return view.performAccessibilityAction(i2, bundle);
        }
    }
}
