package defpackage;

import android.content.Context;
import android.graphics.Rect;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* renamed from: tu1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC4993tu1 {

    /* renamed from: a  reason: collision with root package name */
    public C5163uu1 f11376a;
    public int b = 0;

    public AbstractC4993tu1() {
    }

    public int a() {
        C5163uu1 uu1 = this.f11376a;
        if (uu1 != null) {
            return uu1.d;
        }
        return 0;
    }

    public void b(CoordinatorLayout coordinatorLayout, View view, int i) {
        coordinatorLayout.q(view, i);
    }

    public /* bridge */ /* synthetic */ boolean c(CoordinatorLayout coordinatorLayout, View view, View view2) {
        return false;
    }

    public /* bridge */ /* synthetic */ boolean d(CoordinatorLayout coordinatorLayout, View view, View view2) {
        return false;
    }

    public void e(CoordinatorLayout coordinatorLayout, View view, View view2) {
    }

    public /* bridge */ /* synthetic */ boolean f(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        return false;
    }

    public boolean g(CoordinatorLayout coordinatorLayout, View view, int i) {
        b(coordinatorLayout, view, i);
        if (this.f11376a == null) {
            this.f11376a = new C5163uu1(view);
        }
        C5163uu1 uu1 = this.f11376a;
        uu1.b = uu1.f11445a.getTop();
        uu1.c = uu1.f11445a.getLeft();
        this.f11376a.a();
        int i2 = this.b;
        if (i2 == 0) {
            return true;
        }
        C5163uu1 uu12 = this.f11376a;
        if (uu12.d != i2) {
            uu12.d = i2;
            uu12.a();
        }
        this.b = 0;
        return true;
    }

    public abstract /* bridge */ /* synthetic */ boolean h(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3, int i4);

    public void i(CoordinatorLayout coordinatorLayout, View view, View view2, int i, int i2, int[] iArr, int i3) {
    }

    public void j(CoordinatorLayout coordinatorLayout, View view, View view2, int i, int i2, int i3, int i4, int i5, int[] iArr) {
        iArr[0] = iArr[0] + i3;
        iArr[1] = iArr[1] + i4;
    }

    public /* bridge */ /* synthetic */ boolean k(CoordinatorLayout coordinatorLayout, View view, Rect rect, boolean z) {
        return false;
    }

    public void l(CoordinatorLayout coordinatorLayout, View view, Parcelable parcelable) {
    }

    public Parcelable m(CoordinatorLayout coordinatorLayout, View view) {
        return View.BaseSavedState.EMPTY_STATE;
    }

    public /* bridge */ /* synthetic */ boolean n(CoordinatorLayout coordinatorLayout, View view, View view2, View view3, int i, int i2) {
        return false;
    }

    public void o(CoordinatorLayout coordinatorLayout, View view, View view2, int i) {
    }

    public /* bridge */ /* synthetic */ boolean p(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        return false;
    }

    public boolean q(int i) {
        C5163uu1 uu1 = this.f11376a;
        if (uu1 == null) {
            this.b = i;
            return false;
        } else if (uu1.d == i) {
            return false;
        } else {
            uu1.d = i;
            uu1.a();
            return true;
        }
    }

    public AbstractC4993tu1(Context context, AttributeSet attributeSet) {
    }
}
