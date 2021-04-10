package X;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import androidx.appcompat.widget.ActionBarContextView;
import com.oculus.socialplatform.R;
import java.lang.reflect.Method;

/* renamed from: X.1Er  reason: invalid class name and case insensitive filesystem */
public class C06011Er implements AnonymousClass07L {
    public final /* synthetic */ AnonymousClass1rJ A00;

    public C06011Er(AnonymousClass1rJ r1) {
        this.A00 = r1;
    }

    @Override // X.AnonymousClass07L
    public final C003307q A6m(View view, C003307q r18) {
        boolean z;
        boolean z2;
        ViewGroup.MarginLayoutParams marginLayoutParams;
        int i;
        Context context;
        int i2;
        C003307q r3 = r18;
        AnonymousClass07p r6 = r3.A00;
        int i3 = r6.A01().A03;
        AnonymousClass1rJ r9 = this.A00;
        int i4 = 0;
        int i5 = r6.A01().A03;
        ActionBarContextView actionBarContextView = r9.A0J;
        if (actionBarContextView == null || !(actionBarContextView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            z = false;
        } else {
            ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) r9.A0J.getLayoutParams();
            z = true;
            if (r9.A0J.isShown()) {
                if (r9.A03 == null) {
                    r9.A03 = new Rect();
                    r9.A04 = new Rect();
                }
                Rect rect = r9.A03;
                Rect rect2 = r9.A04;
                rect.set(r6.A01().A01, r6.A01().A03, r6.A01().A02, r6.A01().A00);
                ViewGroup viewGroup = r9.A07;
                Method method = AnonymousClass1EP.A00;
                if (method != null) {
                    try {
                        method.invoke(viewGroup, rect, rect2);
                    } catch (Exception unused) {
                    }
                }
                int i6 = rect.top;
                int i7 = rect.left;
                int i8 = rect.right;
                AnonymousClass07p r10 = C003307q.A01(r9.A07.getRootWindowInsets()).A00;
                int i9 = r10.A01().A01;
                int i10 = r10.A01().A02;
                if (marginLayoutParams2.topMargin == i6 && marginLayoutParams2.leftMargin == i7 && marginLayoutParams2.rightMargin == i8) {
                    z2 = false;
                } else {
                    marginLayoutParams2.topMargin = i6;
                    marginLayoutParams2.leftMargin = i7;
                    marginLayoutParams2.rightMargin = i8;
                    z2 = true;
                }
                if (i6 <= 0 || r9.A06 != null) {
                    View view2 = r9.A06;
                    if (!(view2 == null || ((marginLayoutParams = (ViewGroup.MarginLayoutParams) view2.getLayoutParams()).height == (i = marginLayoutParams2.topMargin) && marginLayoutParams.leftMargin == i9 && marginLayoutParams.rightMargin == i10))) {
                        marginLayoutParams.height = i;
                        marginLayoutParams.leftMargin = i9;
                        marginLayoutParams.rightMargin = i10;
                        r9.A06.setLayoutParams(marginLayoutParams);
                    }
                } else {
                    View view3 = new View(r9.A0j);
                    r9.A06 = view3;
                    view3.setVisibility(8);
                    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, marginLayoutParams2.topMargin, 51);
                    layoutParams.leftMargin = i9;
                    layoutParams.rightMargin = i10;
                    r9.A07.addView(r9.A06, -1, layoutParams);
                }
                View view4 = r9.A06;
                if (view4 == null) {
                    z = false;
                } else if (view4.getVisibility() != 0) {
                    View view5 = r9.A06;
                    if ((view5.getWindowSystemUiVisibility() & 8192) != 0) {
                        context = r9.A0j;
                        i2 = R.color.abc_decor_view_status_guard_light;
                    } else {
                        context = r9.A0j;
                        i2 = R.color.abc_decor_view_status_guard;
                    }
                    view5.setBackgroundColor(context.getColor(i2));
                }
                if (!r9.A0c && z) {
                    i5 = 0;
                }
            } else {
                if (marginLayoutParams2.topMargin != 0) {
                    marginLayoutParams2.topMargin = 0;
                    z2 = true;
                } else {
                    z2 = false;
                }
                z = false;
            }
            if (z2) {
                r9.A0J.setLayoutParams(marginLayoutParams2);
            }
        }
        View view6 = r9.A06;
        if (view6 != null) {
            if (!z) {
                i4 = 8;
            }
            view6.setVisibility(i4);
        }
        if (i3 != i5) {
            int i11 = r6.A01().A01;
            int i12 = r6.A01().A02;
            int i13 = r6.A01().A00;
            AnonymousClass07n r0 = new AnonymousClass07n(r3);
            AnonymousClass057 A002 = AnonymousClass057.A00(i11, i5, i12, i13);
            AnonymousClass07o r02 = r0.A00;
            r02.A02(A002);
            r3 = r02.A00();
        }
        WindowInsets A02 = r3.A02();
        if (A02 == null) {
            return r3;
        }
        WindowInsets onApplyWindowInsets = view.onApplyWindowInsets(A02);
        if (!onApplyWindowInsets.equals(A02)) {
            return C003307q.A01(onApplyWindowInsets);
        }
        return r3;
    }
}
