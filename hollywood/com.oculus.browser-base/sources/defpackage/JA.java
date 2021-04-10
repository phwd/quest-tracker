package defpackage;

import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: JA  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class JA implements AbstractC0290Es0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ CoordinatorLayout f8275a;

    public JA(CoordinatorLayout coordinatorLayout) {
        this.f8275a = coordinatorLayout;
    }

    @Override // defpackage.AbstractC0290Es0
    public C3985nz1 a(View view, C3985nz1 nz1) {
        CoordinatorLayout coordinatorLayout = this.f8275a;
        if (!Objects.equals(coordinatorLayout.a0, nz1)) {
            coordinatorLayout.a0 = nz1;
            boolean z = true;
            boolean z2 = nz1.d() > 0;
            coordinatorLayout.b0 = z2;
            if (z2 || coordinatorLayout.getBackground() != null) {
                z = false;
            }
            coordinatorLayout.setWillNotDraw(z);
            if (!nz1.f()) {
                int childCount = coordinatorLayout.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View childAt = coordinatorLayout.getChildAt(i);
                    AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
                    if (childAt.getFitsSystemWindows() && ((NA) childAt.getLayoutParams()).f8531a != null && nz1.f()) {
                        break;
                    }
                }
            }
            coordinatorLayout.requestLayout();
        }
        return nz1;
    }
}
