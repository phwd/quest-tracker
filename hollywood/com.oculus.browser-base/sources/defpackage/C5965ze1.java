package defpackage;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.oculus.browser.R;

/* renamed from: ze1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5965ze1 extends TextView {
    public C2803h4 F;
    public int G;
    public int H;

    public C5965ze1(Context context) {
        super(context, null);
        setGravity(16);
        setMaxLines(1);
        setTextAppearance(getContext(), R.style.f71850_resource_name_obfuscated_RES_2132017758);
    }

    public void layout(int i, int i2, int i3, int i4) {
        int i5;
        C2803h4 h4Var = this.F;
        if (h4Var != null) {
            int i6 = this.H;
            int i7 = this.G;
            int i8 = i3 - i;
            int i9 = h4Var.b;
            h4Var.b = Math.max(i9, i7);
            h4Var.c = Math.max(h4Var.c, i6);
            int i10 = h4Var.b;
            if (i8 >= i10) {
                i5 = i7 - i6;
            } else {
                if (i10 != i9) {
                    int size = h4Var.f10046a.size();
                    for (int i11 = 0; i11 < size; i11++) {
                        View view = (View) h4Var.f10046a.get(i11);
                        if (view != this) {
                            view.requestLayout();
                        }
                    }
                }
                i5 = Math.max(i8 - h4Var.c, 0);
            }
            if (getLayoutDirection() == 1) {
                i3 -= i5;
            } else {
                i += i5;
            }
        }
        super.layout(i, i2, i3, i4);
    }
}
