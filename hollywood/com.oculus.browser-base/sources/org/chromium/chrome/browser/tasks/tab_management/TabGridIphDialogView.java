package org.chromium.chrome.browser.tasks.tab_management;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TabGridIphDialogView extends LinearLayout {
    public final int F;
    public final int G;
    public final int H;
    public final int I;

    /* renamed from: J  reason: collision with root package name */
    public final int f10782J;
    public final Context K;
    public View L;
    public Drawable M;
    public Animatable N;
    public D6 O;
    public ViewGroup.MarginLayoutParams P;
    public ViewGroup.MarginLayoutParams Q;
    public int R;

    public TabGridIphDialogView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.K = context;
        this.F = (int) context.getResources().getDimension(R.dimen.f25420_resource_name_obfuscated_RES_2131166161);
        this.G = (int) context.getResources().getDimension(R.dimen.f25460_resource_name_obfuscated_RES_2131166165);
        this.H = (int) context.getResources().getDimension(R.dimen.f25430_resource_name_obfuscated_RES_2131166162);
        this.I = (int) context.getResources().getDimension(R.dimen.f25450_resource_name_obfuscated_RES_2131166164);
        this.f10782J = (int) context.getResources().getDimension(R.dimen.f25440_resource_name_obfuscated_RES_2131166163);
    }

    public void a() {
        int i;
        if (this.R != this.L.getHeight()) {
            this.R = this.L.getHeight();
            if (this.K.getResources().getConfiguration().orientation == 1) {
                i = this.I;
            } else {
                i = this.f10782J;
            }
            ViewGroup.MarginLayoutParams marginLayoutParams = this.P;
            int i2 = this.H;
            marginLayoutParams.setMargins(i2, i, i2, i);
            ViewGroup.MarginLayoutParams marginLayoutParams2 = this.Q;
            int i3 = this.H;
            marginLayoutParams2.setMargins(i3, 0, i3, i);
            setMinimumHeight(Math.min(this.F, this.R - (this.G * 2)));
        }
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        Drawable drawable = ((ImageView) findViewById(R.id.animation_drawable)).getDrawable();
        this.M = drawable;
        this.N = (Animatable) drawable;
        this.O = new C4693s71(this);
        this.P = (ViewGroup.MarginLayoutParams) ((TextView) findViewById(R.id.title)).getLayoutParams();
        this.Q = (ViewGroup.MarginLayoutParams) ((TextView) findViewById(R.id.description)).getLayoutParams();
    }
}
