package X;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import com.oculus.alpenglow.R;

/* renamed from: X.02x  reason: invalid class name */
public final class AnonymousClass02x {
    public int A00;
    public int A01;
    public Bundle A02;
    public int A03;
    public int A04;
    public Context A05;
    public View A06;
    public View A07;
    public ViewGroup A08;
    public C04290ea A09;
    public C04280eZ A0A;
    public boolean A0B;
    public boolean A0C;
    public boolean A0D;
    public boolean A0E = false;
    public boolean A0F;

    public final void A00(Context context) {
        TypedValue typedValue = new TypedValue();
        Resources.Theme newTheme = context.getResources().newTheme();
        newTheme.setTo(context.getTheme());
        newTheme.resolveAttribute(R.attr.actionBarPopupTheme, typedValue, true);
        int i = typedValue.resourceId;
        if (i != 0) {
            newTheme.applyStyle(i, true);
        }
        newTheme.resolveAttribute(R.attr.panelMenuListTheme, typedValue, true);
        int i2 = typedValue.resourceId;
        if (i2 == 0) {
            i2 = 2131493001;
        }
        newTheme.applyStyle(i2, true);
        AnonymousClass03F r1 = new AnonymousClass03F(context, 0);
        r1.getTheme().setTo(newTheme);
        this.A05 = r1;
        TypedArray obtainStyledAttributes = r1.obtainStyledAttributes(AnonymousClass18N.A09);
        this.A00 = obtainStyledAttributes.getResourceId(84, 0);
        this.A01 = obtainStyledAttributes.getResourceId(1, 0);
        obtainStyledAttributes.recycle();
    }

    public AnonymousClass02x(int i) {
        this.A03 = i;
    }
}
