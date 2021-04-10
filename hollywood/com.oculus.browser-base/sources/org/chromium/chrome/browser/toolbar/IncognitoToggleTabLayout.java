package org.chromium.chrome.browser.toolbar;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.google.android.material.tabs.TabLayout;
import com.oculus.browser.R;
import org.chromium.ui.widget.ChromeImageView;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class IncognitoToggleTabLayout extends TabLayout implements AbstractC5710y61 {
    public static final /* synthetic */ int w0 = 0;
    public ImageView A0;
    public C1410Xc1 B0;
    public ColorStateList C0;
    public ColorStateList D0 = getContext().getColorStateList(R.color.f15610_resource_name_obfuscated_RES_2131100251);
    public ColorStateList E0 = getContext().getColorStateList(R.color.f11100_resource_name_obfuscated_RES_2131099800);
    public ColorStateList F0 = getContext().getColorStateList(R.color.f15640_resource_name_obfuscated_RES_2131100254);
    public AbstractC0124Ca1 G0;
    public C5880z61 H0;
    public AbstractC0612Ka1 I0;
    public D81 x0;
    public D81 y0;
    public ImageView z0 = new ChromeImageView(getContext());

    public IncognitoToggleTabLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Context context2 = getContext();
        ThreadLocal threadLocal = AbstractC5544x8.f11592a;
        this.C0 = context2.getColorStateList(R.color.f11390_resource_name_obfuscated_RES_2131099829);
        C1410Xc1 e = C1410Xc1.e(getContext(), false);
        this.B0 = e;
        this.z0.setImageDrawable(e);
        this.z0.setContentDescription(getResources().getString(R.string.f45980_resource_name_obfuscated_RES_2131951915));
        ChromeImageView chromeImageView = new ChromeImageView(getContext());
        this.A0 = chromeImageView;
        chromeImageView.setImageResource(R.drawable.f33150_resource_name_obfuscated_RES_2131231355);
        this.A0.setContentDescription(getResources().getString(R.string.f45960_resource_name_obfuscated_RES_2131951913));
        D81 l = l();
        l.e = this.z0;
        l.e();
        this.x0 = l;
        a(l);
        D81 l2 = l();
        l2.e = this.A0;
        l2.e();
        this.y0 = l2;
        a(l2);
        X00 x00 = new X00(this);
        if (!this.m0.contains(x00)) {
            this.m0.add(x00);
        }
    }

    @Override // defpackage.AbstractC5710y61
    public void b(int i, boolean z) {
        if (!z) {
            this.B0.g(i, z);
        }
    }

    public final void w() {
        AbstractC0124Ca1 ca1 = this.G0;
        if (ca1 != null) {
            boolean r = ((AbstractC0246Ea1) ca1).r();
            if (r) {
                r(this.F0.getDefaultColor());
                this.z0.setImageTintList(this.D0);
                this.B0.c(this.D0);
                this.A0.setImageTintList(this.F0);
            } else {
                r(this.E0.getDefaultColor());
                this.z0.setImageTintList(this.E0);
                this.B0.c(this.E0);
                this.A0.setImageTintList(this.C0);
            }
            if (r && !this.y0.a()) {
                this.y0.b();
            } else if (!r && !this.x0.a()) {
                this.x0.b();
            }
        }
    }

    public void x(AbstractC0124Ca1 ca1) {
        this.G0 = ca1;
        if (ca1 != null) {
            Y00 y00 = new Y00(this);
            this.I0 = y00;
            ((AbstractC0246Ea1) ca1).c(y00);
            w();
            this.B0.g(((AbstractC0246Ea1) this.H0.b).c.g(false).getCount(), false);
        }
    }
}
