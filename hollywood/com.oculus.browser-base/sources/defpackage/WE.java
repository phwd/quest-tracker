package defpackage;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.preference.Preference;
import com.oculus.browser.R;

/* renamed from: WE  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class WE extends Preference {
    public CharSequence t0;
    public CharSequence u0;
    public Drawable v0;
    public CharSequence w0;
    public CharSequence x0;
    public int y0;

    public WE(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, FJ0.I, i, i2);
        String e = Ko1.e(obtainStyledAttributes, 9, 0);
        this.t0 = e;
        if (e == null) {
            this.t0 = this.M;
        }
        String string = obtainStyledAttributes.getString(8);
        this.u0 = string == null ? obtainStyledAttributes.getString(1) : string;
        Drawable drawable = obtainStyledAttributes.getDrawable(6);
        this.v0 = drawable == null ? obtainStyledAttributes.getDrawable(2) : drawable;
        String string2 = obtainStyledAttributes.getString(11);
        this.w0 = string2 == null ? obtainStyledAttributes.getString(3) : string2;
        String string3 = obtainStyledAttributes.getString(10);
        this.x0 = string3 == null ? obtainStyledAttributes.getString(4) : string3;
        this.y0 = obtainStyledAttributes.getResourceId(7, obtainStyledAttributes.getResourceId(5, 0));
        obtainStyledAttributes.recycle();
    }

    @Override // androidx.preference.Preference
    public void y() {
        AbstractC3862nF0 nf0 = this.G.i;
        if (nf0 != null) {
            nf0.k(this);
        }
    }

    public WE(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, Ko1.a(context, R.attr.f3800_resource_name_obfuscated_RES_2130968826, 16842897), 0);
    }
}
