package defpackage;

import android.content.Context;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.oculus.browser.R;

/* renamed from: ov  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC4142ov extends ImageView {
    public final ColorDrawable F;
    public int G;
    public float H;
    public int I = getVisibility();

    public AbstractC4142ov(Context context, int i) {
        super(context);
        int color = getResources().getColor(R.color.f14760_resource_name_obfuscated_RES_2131100166);
        this.G = getResources().getColor(R.color.f14740_resource_name_obfuscated_RES_2131100164);
        ColorDrawable colorDrawable = new ColorDrawable(color);
        this.F = colorDrawable;
        setImageDrawable(new ClipDrawable(colorDrawable, 8388611, 1));
        setBackgroundColor(this.G);
        setLayoutParams(new ViewGroup.LayoutParams(-1, i));
    }

    public void a(float f) {
        if (this.H != f) {
            this.H = f;
            getDrawable().setLevel(Math.round(f * 10000.0f));
        }
    }

    public final void b() {
        int visibility = getVisibility();
        int i = this.I;
        if (getAlpha() == 0.0f && this.I == 0) {
            i = 4;
        }
        if (visibility != i) {
            super.setVisibility(i);
        }
    }

    public boolean onSetAlpha(int i) {
        b();
        return super.onSetAlpha(i);
    }

    public void setBackgroundColor(int i) {
        if (i == 0) {
            setBackground(null);
        } else {
            super.setBackgroundColor(i);
        }
        this.G = i;
    }
}
