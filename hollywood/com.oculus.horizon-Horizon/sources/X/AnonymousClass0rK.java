package X;

import android.graphics.Rect;
import android.view.View;
import android.view.WindowInsets;
import androidx.viewpager.widget.ViewPager;

/* renamed from: X.0rK  reason: invalid class name */
public class AnonymousClass0rK implements AnonymousClass07J {
    public final Rect A00 = new Rect();
    public final /* synthetic */ ViewPager A01;

    public AnonymousClass0rK(ViewPager viewPager) {
        this.A01 = viewPager;
    }

    @Override // X.AnonymousClass07J
    public final AnonymousClass07r A5k(View view, AnonymousClass07r r9) {
        AnonymousClass053 r1;
        AnonymousClass07r r0;
        WindowInsets A002 = r9.A00();
        if (A002 != null) {
            WindowInsets onApplyWindowInsets = view.onApplyWindowInsets(A002);
            if (!onApplyWindowInsets.equals(A002)) {
                r9 = new AnonymousClass07r(onApplyWindowInsets);
            }
        }
        C001807q r12 = r9.A00;
        if (r12.A07()) {
            return r9;
        }
        Rect rect = this.A00;
        rect.left = r12.A01().A01;
        rect.top = r12.A01().A03;
        rect.right = r12.A01().A02;
        rect.bottom = r12.A01().A00;
        ViewPager viewPager = this.A01;
        int childCount = viewPager.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewPager.getChildAt(i);
            WindowInsets A003 = r9.A00();
            if (A003 == null || childAt.dispatchApplyWindowInsets(A003).equals(A003)) {
                r0 = r9;
            } else {
                r0 = new AnonymousClass07r(A003);
            }
            C001807q r6 = r0.A00;
            rect.left = Math.min(r6.A01().A01, rect.left);
            rect.top = Math.min(r6.A01().A03, rect.top);
            rect.right = Math.min(r6.A01().A02, rect.right);
            rect.bottom = Math.min(r6.A01().A00, rect.bottom);
        }
        int i2 = rect.left;
        int i3 = rect.top;
        int i4 = rect.right;
        int i5 = rect.bottom;
        C001607o r02 = new C001607o(r9);
        if (i2 == 0 && i3 == 0 && i4 == 0 && i5 == 0) {
            r1 = AnonymousClass053.A04;
        } else {
            r1 = new AnonymousClass053(i2, i3, i4, i5);
        }
        C001707p r03 = r02.A00;
        r03.A01(r1);
        return r03.A00();
    }
}
