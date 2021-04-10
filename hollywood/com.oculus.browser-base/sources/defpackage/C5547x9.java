package defpackage;

import android.animation.TimeAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.oculus.browser.R;
import java.util.ArrayList;

/* renamed from: x9  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5547x9 {

    /* renamed from: a  reason: collision with root package name */
    public final Context f11594a;
    public final View$OnKeyListenerC2476f9 b;
    public final float c;
    public final TimeAnimator d;
    public float e;
    public int f;
    public volatile float g;
    public volatile float h;
    public volatile float i;
    public final int j;
    public boolean k;
    public int l;
    public final Rect m = new Rect();
    public final int[] n = new int[2];
    public final int o;
    public final int p;

    public C5547x9(Context context, View$OnKeyListenerC2476f9 f9Var, int i2) {
        TimeAnimator timeAnimator = new TimeAnimator();
        this.d = timeAnimator;
        this.f11594a = context;
        this.b = f9Var;
        this.j = i2;
        this.c = (float) context.getResources().getDimensionPixelSize(R.dimen.f16570_resource_name_obfuscated_RES_2131165276);
        timeAnimator.setTimeListener(new C5377w9(this));
        this.o = (ViewConfiguration.getLongPressTimeout() + ViewConfiguration.getTapTimeout()) / 2;
        this.p = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public void a() {
        if (this.b.M.isShowing()) {
            c(0, 0, 2);
        }
        this.d.cancel();
    }

    public Rect b(View view) {
        view.getLocalVisibleRect(this.m);
        view.getLocationOnScreen(this.n);
        Rect rect = this.m;
        int[] iArr = this.n;
        rect.offset(iArr[0], iArr[1]);
        return this.m;
    }

    public final boolean c(int i2, int i3, int i4) {
        boolean z;
        ListView listView = this.b.N;
        View childAt = listView.getChildAt(0);
        if (!(listView.getFirstVisiblePosition() != 0 || childAt == null || childAt.getTop() != 0 || b(childAt).bottom > this.l)) {
            return false;
        }
        ListView listView2 = this.b.N;
        ArrayList arrayList = new ArrayList();
        for (int i5 = 0; i5 < listView2.getChildCount(); i5++) {
            if (listView2.getChildAt(i5) instanceof LinearLayout) {
                LinearLayout linearLayout = (LinearLayout) listView2.getChildAt(i5);
                z = false;
                for (int i6 = 0; i6 < linearLayout.getChildCount(); i6++) {
                    arrayList.add(linearLayout.getChildAt(i6));
                    if (linearLayout.getChildAt(i6) instanceof ImageButton) {
                        z = true;
                    }
                }
            } else {
                z = false;
            }
            if (!z) {
                arrayList.add(listView2.getChildAt(i5));
            }
        }
        boolean z2 = false;
        for (int i7 = 0; i7 < arrayList.size(); i7++) {
            View view = (View) arrayList.get(i7);
            boolean z3 = view.isEnabled() && view.isShown() && b(view).contains(i2, i3);
            if (i4 == 0) {
                view.setPressed(z3);
            } else if (i4 != 1) {
                if (i4 == 2) {
                    view.setPressed(false);
                }
            } else if (z3) {
                AbstractC3535lK0.a("MobileUsingMenuBySwButtonDragging");
                view.performClick();
                z2 = true;
            }
        }
        return z2;
    }
}
