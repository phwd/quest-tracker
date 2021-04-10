package org.chromium.components.browser_ui.settings;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import androidx.preference.Preference;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ChromeImageViewPreference extends Preference {
    public AbstractC1528Zb0 t0;
    public View.OnClickListener u0;
    public int v0;
    public int w0;
    public int x0;
    public boolean y0;
    public ImageView z0;

    public ChromeImageViewPreference(Context context) {
        this(context, null);
    }

    public final void a0() {
        int i = this.v0;
        if (i != 0 && this.z0 != null) {
            this.z0.setImageDrawable(AbstractC2870hT0.c(this.F, i, this.w0));
            this.z0.setEnabled(this.y0);
            if (this.y0) {
                this.z0.setOnClickListener(this.u0);
            }
            if (this.x0 != 0) {
                ImageView imageView = this.z0;
                imageView.setContentDescription(imageView.getResources().getString(this.x0));
            }
        }
    }

    public void b0(int i, int i2, View.OnClickListener onClickListener) {
        this.v0 = i;
        this.x0 = i2;
        this.u0 = onClickListener;
        a0();
        s();
    }

    @Override // java.lang.Comparable, androidx.preference.Preference
    public /* bridge */ /* synthetic */ int compareTo(Object obj) {
        return compareTo((Preference) obj);
    }

    @Override // androidx.preference.Preference
    public void x(C4886tF0 tf0) {
        super.x(tf0);
        ImageView imageView = (ImageView) tf0.x(R.id.image_view_widget);
        this.z0 = imageView;
        imageView.setBackgroundColor(0);
        this.z0.setVisibility(0);
        a0();
        AbstractC1528Zb0 zb0 = this.t0;
        View view = tf0.G;
        if (zb0 != null) {
            AbstractC1865bc0.c(zb0, this, view);
            if (zb0.d(this) || zb0.a(this)) {
                ImageView imageView2 = (ImageView) view.findViewById(R.id.image_view_widget);
                imageView2.setImageDrawable(AbstractC1865bc0.a(zb0, this));
                imageView2.setOnClickListener(new View$OnClickListenerC1685ac0(zb0, this));
            }
        }
    }

    @Override // androidx.preference.Preference
    public void y() {
        if (AbstractC1865bc0.d(this.t0, this)) {
        }
    }

    public ChromeImageViewPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.y0 = true;
        this.l0 = R.layout.f40710_resource_name_obfuscated_RES_2131624380;
        R(false);
        if (this.w0 != R.color.f11220_resource_name_obfuscated_RES_2131099812) {
            this.w0 = R.color.f11220_resource_name_obfuscated_RES_2131099812;
            a0();
        }
    }
}
