package defpackage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.oculus.browser.R;
import org.chromium.components.browser_ui.widget.DualControlLayout;

/* renamed from: BA0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class BA0 extends LinearLayout implements View.OnClickListener {
    public final AbstractView$OnClickListenerC5891zA0 F;
    public final int G;
    public final Button H;
    public final boolean I;

    /* renamed from: J  reason: collision with root package name */
    public int f7721J = 3;
    public final int K;
    public final int L;
    public final int M;
    public final LinearLayout N;
    public final ImageView O;
    public final ImageView P;
    public TextView Q;
    public LinearLayout R;
    public TextView S;
    public TextView T;
    public Drawable U;
    public boolean V = true;

    public BA0(Context context, String str, AbstractView$OnClickListenerC5891zA0 za0, AbstractC4871tA0 ta0) {
        super(context);
        this.F = za0;
        setOnClickListener(za0);
        setOrientation(0);
        setGravity(16);
        this.L = getResources().getColor(R.color.f14550_resource_name_obfuscated_RES_2131100145);
        this.M = getResources().getColor(R.color.f14570_resource_name_obfuscated_RES_2131100147);
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.f19000_resource_name_obfuscated_RES_2131165519);
        this.G = dimensionPixelSize;
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(R.dimen.f24000_resource_name_obfuscated_RES_2131166019);
        this.K = dimensionPixelSize2;
        setPadding(dimensionPixelSize, dimensionPixelSize2, dimensionPixelSize, dimensionPixelSize2);
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(1);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -2);
        layoutParams.weight = 1.0f;
        addView(linearLayout, layoutParams);
        TextView textView = new TextView(getContext());
        this.Q = textView;
        textView.setText(str);
        AbstractC3153j7.i(this.Q, R.style.f71920_resource_name_obfuscated_RES_2132017765);
        linearLayout.addView(this.Q, new LinearLayout.LayoutParams(-1, -2));
        TextView textView2 = new TextView(getContext());
        this.S = textView2;
        textView2.setId(R.id.payments_left_summary_label);
        AbstractC3153j7.i(this.S, R.style.f71850_resource_name_obfuscated_RES_2132017758);
        TextView textView3 = new TextView(getContext());
        this.T = textView3;
        AbstractC3153j7.i(textView3, R.style.f71850_resource_name_obfuscated_RES_2132017758);
        this.T.setTextAlignment(3);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(0, -2);
        layoutParams2.weight = 1.0f;
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams3.setMarginStart(getContext().getResources().getDimensionPixelSize(R.dimen.f19010_resource_name_obfuscated_RES_2131165520));
        LinearLayout linearLayout2 = new LinearLayout(getContext());
        this.R = linearLayout2;
        linearLayout2.addView(this.S, layoutParams2);
        this.R.addView(this.T, layoutParams3);
        linearLayout.addView(this.R, new LinearLayout.LayoutParams(-1, -2));
        ImageView imageView = null;
        f(null, null);
        a(linearLayout);
        this.N = linearLayout;
        if (this instanceof C5721yA0) {
            imageView = new ImageView(getContext());
            imageView.setMaxWidth(getContext().getResources().getDimensionPixelSize(R.dimen.f18970_resource_name_obfuscated_RES_2131165516));
            imageView.setAdjustViewBounds(true);
            LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(-2, -2);
            layoutParams4.setMarginStart(dimensionPixelSize);
            addView(imageView, layoutParams4);
        }
        this.O = imageView;
        Button a2 = DualControlLayout.a(getContext(), true, getResources().getString(R.string.f48670_resource_name_obfuscated_RES_2131952184), this);
        a2.setId(R.id.payments_section);
        LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams5.setMarginStart(dimensionPixelSize);
        addView(a2, layoutParams5);
        this.H = a2;
        C0636Ki1 b = C0636Ki1.b(getContext(), R.drawable.f30150_resource_name_obfuscated_RES_2131231055, R.color.f14560_resource_name_obfuscated_RES_2131100146);
        ImageView imageView2 = new ImageView(getContext());
        imageView2.setImageDrawable(b);
        LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams6.setMarginStart(dimensionPixelSize);
        addView(imageView2, layoutParams6);
        this.P = imageView2;
        this.I = true;
        d(3);
    }

    public abstract void a(LinearLayout linearLayout);

    public int b() {
        return 0;
    }

    public void c(View view) {
    }

    public void d(int i) {
        this.f7721J = i;
        g();
    }

    public void e(TextUtils.TruncateAt truncateAt, boolean z, TextUtils.TruncateAt truncateAt2, boolean z2) {
        this.S.setEllipsize(truncateAt);
        this.S.setSingleLine(z);
        this.T.setEllipsize(null);
        this.T.setSingleLine(z2);
    }

    public void f(CharSequence charSequence, CharSequence charSequence2) {
        this.S.setText(charSequence);
        this.T.setText(charSequence2);
        this.T.setVisibility(TextUtils.isEmpty(charSequence2) ? 8 : 0);
        g();
    }

    public void g() {
        if (this.I) {
            int i = this.f7721J;
            boolean z = true;
            int i2 = 0;
            boolean z2 = i == 5 || i == 6;
            setBackgroundColor(z2 ? this.M : this.L);
            ImageView imageView = this.O;
            int i3 = 8;
            if (imageView != null) {
                imageView.setVisibility(this.U != null && this.f7721J != 5 ? 0 : 8);
            }
            int b = b();
            if (b == 0) {
                this.H.setVisibility(8);
                this.P.setVisibility(this.f7721J == 4 ? 0 : 8);
            } else {
                int i4 = this.f7721J;
                boolean z3 = i4 == 4 || i4 == 3;
                this.P.setVisibility(8);
                this.H.setVisibility(z3 ? 0 : 8);
                this.H.setText(b == 1 ? R.string.f48670_resource_name_obfuscated_RES_2131952184 : R.string.f46530_resource_name_obfuscated_RES_2131951970);
            }
            LinearLayout linearLayout = this.R;
            if (this.V) {
                i3 = 0;
            }
            linearLayout.setVisibility(i3);
            int i5 = 0;
            for (int i6 = 0; i6 < this.N.getChildCount(); i6++) {
                if (this.N.getChildAt(i6).getVisibility() == 0) {
                    i5++;
                }
            }
            if (i5 <= 1 || !z2) {
                z = false;
            }
            int i7 = ((ViewGroup.MarginLayoutParams) this.Q.getLayoutParams()).bottomMargin;
            if (z) {
                i2 = this.K;
            }
            if (i7 != i2) {
                ((ViewGroup.MarginLayoutParams) this.Q.getLayoutParams()).bottomMargin = i2;
                requestLayout();
            }
        }
    }

    public final void onClick(View view) {
        if (((TA0) this.F).j()) {
            if (view != this.H) {
                c(view);
                g();
            } else if (b() == 2) {
                ((TA0) this.F).k(this);
            } else {
                ((TA0) this.F).h(this);
            }
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return !((TA0) this.F).j();
    }
}
