package org.chromium.components.browser_ui.widget.async_image;

import J.N;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import java.util.Objects;
import org.chromium.chrome.browser.video_tutorials.Tutorial;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AsyncImageView extends ForegroundRoundedCornerImageView {
    public Drawable Q;
    public Drawable R;
    public Bt1 S;
    public Runnable T;
    public boolean U;
    public Object V;

    public AsyncImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        TypedArray typedArray;
        if (attributeSet == null) {
            typedArray = null;
        } else {
            typedArray = context.obtainStyledAttributes(attributeSet, FJ0.q, 0, 0);
        }
        this.Q = C2547fc.b(AbstractC2417ep1.e(context, typedArray, 0));
        this.R = C2547fc.b(AbstractC2417ep1.e(context, typedArray, 1));
        if (typedArray != null) {
            typedArray.recycle();
        }
    }

    public final void e() {
        Bt1 bt1;
        if (getWidth() > 0 && getHeight() > 0 && (bt1 = this.S) != null) {
            this.U = true;
            C0305Fa fa = new C0305Fa(this, this.V);
            int width = getWidth();
            int height = getHeight();
            Ft1 ft1 = bt1.f7767a;
            Tutorial tutorial = bt1.b;
            Objects.requireNonNull(ft1);
            boolean M6bsIDpc = N.M6bsIDpc("VideoTutorials", "use_animated_gif_url", true);
            LZ lz = new LZ(M6bsIDpc ? tutorial.c : tutorial.d, "VideoTutorialsIPH", width, height, 0);
            if (M6bsIDpc) {
                ft1.d.c(lz, new Ct1(fa));
            } else {
                ft1.d.d(lz, new Dt1(ft1, fa));
            }
            this.T = new Et1();
            if (!this.U) {
                this.T = null;
            }
            this.S = null;
        }
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        e();
    }

    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i != i3 || i2 == i4) {
        }
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, org.chromium.components.browser_ui.widget.RoundedCornerImageView
    public void setImageDrawable(Drawable drawable) {
        this.S = null;
        this.V = null;
        if (this.U) {
            Runnable runnable = this.T;
            if (runnable != null) {
                runnable.run();
            }
            this.T = null;
            this.U = false;
        }
        this.P.d(null);
        super.setImageDrawable(drawable);
    }
}
