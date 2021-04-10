package X;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import androidx.appcompat.widget.ActionBarContextView;
import com.oculus.alpenglow.R;
import java.lang.reflect.Method;

/* renamed from: X.0f3  reason: invalid class name and case insensitive filesystem */
public class C04510f3 implements AbstractC00840Ac {
    public final /* synthetic */ LayoutInflater$Factory2C04430et A00;

    public C04510f3(LayoutInflater$Factory2C04430et r1) {
        this.A00 = r1;
    }

    @Override // X.AbstractC00840Ac
    public final AnonymousClass0B7 A5t(View view, AnonymousClass0B7 r17) {
        boolean z;
        boolean z2;
        ViewGroup.MarginLayoutParams marginLayoutParams;
        int i;
        Context context;
        int i2;
        AnonymousClass0B7 r2 = r17;
        AnonymousClass0B6 r6 = r2.A00;
        int i3 = r6.A01().A03;
        LayoutInflater$Factory2C04430et r9 = this.A00;
        int i4 = 0;
        int i5 = r6.A01().A03;
        ActionBarContextView actionBarContextView = r9.A0K;
        if (actionBarContextView == null || !(actionBarContextView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            z = false;
        } else {
            ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) r9.A0K.getLayoutParams();
            z = true;
            if (r9.A0K.isShown()) {
                if (r9.A07 == null) {
                    r9.A07 = new Rect();
                    r9.A08 = new Rect();
                }
                Rect rect = r9.A07;
                Rect rect2 = r9.A08;
                rect.set(r6.A01().A01, r6.A01().A03, r6.A01().A02, r6.A01().A00);
                ViewGroup viewGroup = r9.A0A;
                Method method = C005405m.A00;
                if (method != null) {
                    try {
                        method.invoke(viewGroup, rect, rect2);
                    } catch (Exception unused) {
                    }
                }
                int i6 = rect.top;
                int i7 = rect.left;
                int i8 = rect.right;
                AnonymousClass0B6 r10 = AnonymousClass0B7.A01(r9.A0A.getRootWindowInsets()).A00;
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
                if (i6 <= 0 || r9.A09 != null) {
                    View view2 = r9.A09;
                    if (!(view2 == null || ((marginLayoutParams = (ViewGroup.MarginLayoutParams) view2.getLayoutParams()).height == (i = marginLayoutParams2.topMargin) && marginLayoutParams.leftMargin == i9 && marginLayoutParams.rightMargin == i10))) {
                        marginLayoutParams.height = i;
                        marginLayoutParams.leftMargin = i9;
                        marginLayoutParams.rightMargin = i10;
                        r9.A09.setLayoutParams(marginLayoutParams);
                    }
                } else {
                    View view3 = new View(r9.A0j);
                    r9.A09 = view3;
                    view3.setVisibility(8);
                    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, marginLayoutParams2.topMargin, 51);
                    layoutParams.leftMargin = i9;
                    layoutParams.rightMargin = i10;
                    r9.A0A.addView(r9.A09, -1, layoutParams);
                }
                View view4 = r9.A09;
                if (view4 == null) {
                    z = false;
                } else if (view4.getVisibility() != 0) {
                    View view5 = r9.A09;
                    if ((view5.getWindowSystemUiVisibility() & 8192) != 0) {
                        context = r9.A0j;
                        i2 = R.color.abc_decor_view_status_guard_light;
                    } else {
                        context = r9.A0j;
                        i2 = R.color.abc_decor_view_status_guard;
                    }
                    view5.setBackgroundColor(context.getColor(i2));
                }
                if (!r9.A0Y && z) {
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
                r9.A0K.setLayoutParams(marginLayoutParams2);
            }
        }
        View view6 = r9.A09;
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
            AnonymousClass0B4 r0 = new AnonymousClass0B4(r2);
            AnonymousClass08P A002 = AnonymousClass08P.A00(i11, i5, i12, i13);
            AnonymousClass0B5 r02 = r0.A00;
            r02.A02(A002);
            r2 = r02.A00();
        }
        WindowInsets A02 = r2.A02();
        if (A02 == null) {
            return r2;
        }
        WindowInsets onApplyWindowInsets = view.onApplyWindowInsets(A02);
        if (!onApplyWindowInsets.equals(A02)) {
            return new AnonymousClass0B7(onApplyWindowInsets);
        }
        return r2;
    }
}
