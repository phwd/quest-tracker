package org.chromium.chrome.browser.toolbar;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.view.View;
import com.oculus.browser.R;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.ui.widget.ChromeImageButton;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class NewTabButton extends ChromeImageButton implements C00, View.OnLongClickListener {
    public final ColorStateList H;
    public final ColorStateList I;

    /* renamed from: J  reason: collision with root package name */
    public boolean f10785J = false;
    public D00 K;

    public NewTabButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Context context2 = getContext();
        ThreadLocal threadLocal = AbstractC5544x8.f11592a;
        this.H = context2.getColorStateList(R.color.f11330_resource_name_obfuscated_RES_2131099823);
        this.I = getContext().getColorStateList(R.color.f11390_resource_name_obfuscated_RES_2131099829);
        setImageDrawable(Fs1.a(getContext().getResources(), R.drawable.f34190_resource_name_obfuscated_RES_2131231459, getContext().getTheme()));
        d();
        setOnLongClickListener(this);
    }

    @Override // defpackage.C00
    public void c(boolean z) {
        if (this.f10785J != z) {
            this.f10785J = z;
            setContentDescription(getResources().getText(z ? R.string.f46270_resource_name_obfuscated_RES_2131951944 : R.string.f46280_resource_name_obfuscated_RES_2131951945));
            d();
            invalidate();
        }
    }

    public final void d() {
        setImageTintList(DeviceFormFactor.a(getContext()) || ((C5052uE.a() || AbstractC4772sd1.b() || AbstractC2793h01.b()) && this.f10785J) ? this.H : this.I);
    }

    public boolean onLongClick(View view) {
        return C1184Ti1.c(getContext(), view, getResources().getString(this.f10785J ? R.string.f48420_resource_name_obfuscated_RES_2131952159 : R.string.f48430_resource_name_obfuscated_RES_2131952160));
    }
}
