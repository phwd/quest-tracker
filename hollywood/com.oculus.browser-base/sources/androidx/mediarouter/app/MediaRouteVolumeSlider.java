package androidx.mediarouter.app;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.util.Log;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class MediaRouteVolumeSlider extends C5714y8 {
    public final float G;
    public boolean H;
    public Drawable I;

    /* renamed from: J  reason: collision with root package name */
    public int f9479J;
    public int K;

    public MediaRouteVolumeSlider(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.f7450_resource_name_obfuscated_RES_2130969191);
        this.G = AbstractC4783sh0.d(context);
    }

    public void a(int i, int i2) {
        if (this.f9479J != i) {
            if (Color.alpha(i) != 255) {
                StringBuilder i3 = AbstractC2531fV.i("Volume slider progress and thumb color cannot be translucent: #");
                i3.append(Integer.toHexString(i));
                Log.e("MediaRouteVolumeSlider", i3.toString());
            }
            this.f9479J = i;
        }
        if (this.K != i2) {
            if (Color.alpha(i2) != 255) {
                StringBuilder i4 = AbstractC2531fV.i("Volume slider background color cannot be translucent: #");
                i4.append(Integer.toHexString(i2));
                Log.e("MediaRouteVolumeSlider", i4.toString());
            }
            this.K = i2;
        }
    }

    public void b(boolean z) {
        Drawable drawable;
        if (this.H != z) {
            this.H = z;
            if (z) {
                drawable = null;
            } else {
                drawable = this.I;
            }
            super.setThumb(drawable);
        }
    }

    @Override // defpackage.C5714y8
    public void drawableStateChanged() {
        super.drawableStateChanged();
        int i = isEnabled() ? 255 : (int) (this.G * 255.0f);
        this.I.setColorFilter(this.f9479J, PorterDuff.Mode.SRC_IN);
        this.I.setAlpha(i);
        Drawable progressDrawable = getProgressDrawable();
        if (progressDrawable instanceof LayerDrawable) {
            LayerDrawable layerDrawable = (LayerDrawable) getProgressDrawable();
            Drawable findDrawableByLayerId = layerDrawable.findDrawableByLayerId(16908301);
            layerDrawable.findDrawableByLayerId(16908288).setColorFilter(this.K, PorterDuff.Mode.SRC_IN);
            progressDrawable = findDrawableByLayerId;
        }
        progressDrawable.setColorFilter(this.f9479J, PorterDuff.Mode.SRC_IN);
        progressDrawable.setAlpha(i);
    }

    public void setThumb(Drawable drawable) {
        this.I = drawable;
        if (this.H) {
            drawable = null;
        }
        super.setThumb(drawable);
    }
}
