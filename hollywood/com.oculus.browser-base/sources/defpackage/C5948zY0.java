package defpackage;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.oculus.browser.R;
import java.util.Objects;
import org.chromium.components.browser_ui.widget.text.TemplatePreservingTextView;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: zY0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5948zY0 {

    /* renamed from: a  reason: collision with root package name */
    public final WindowAndroid f11749a;
    public final ViewGroup b;
    public final ViewGroup c;
    public final TemplatePreservingTextView d;
    public final TextView e;
    public final ImageView f;
    public final int g;
    public final boolean h;
    public ViewGroup i;
    public ViewGroup j;
    public C4076oY0 k;
    public View l;
    public Rect m = new Rect();
    public Rect n = new Rect();
    public int[] o = new int[2];
    public View.OnLayoutChangeListener p = new View$OnLayoutChangeListenerC5438wY0(this);

    public C5948zY0(Activity activity, View.OnClickListener onClickListener, C4076oY0 oy0, ViewGroup viewGroup, WindowAndroid windowAndroid) {
        this.h = DeviceFormFactor.a(activity);
        this.i = viewGroup;
        this.f11749a = windowAndroid;
        this.l = activity.findViewById(16908290);
        this.j = this.i;
        ViewGroup viewGroup2 = (ViewGroup) LayoutInflater.from(activity).inflate(R.layout.f41530_resource_name_obfuscated_RES_2131624462, this.j, false);
        this.b = viewGroup2;
        this.c = (ViewGroup) viewGroup2.findViewById(R.id.snackbar);
        this.g = viewGroup2.getResources().getInteger(17694721);
        this.d = (TemplatePreservingTextView) viewGroup2.findViewById(R.id.snackbar_message);
        TextView textView = (TextView) viewGroup2.findViewById(R.id.snackbar_button);
        this.e = textView;
        textView.setOnClickListener(onClickListener);
        this.f = (ImageView) viewGroup2.findViewById(R.id.snackbar_profile_image);
        g(oy0, false);
    }

    public void a() {
        StringBuilder sb = new StringBuilder(this.d.getContentDescription());
        if (this.e.getContentDescription() != null) {
            sb.append(". ");
            sb.append(this.e.getContentDescription());
            sb.append(". ");
            sb.append(this.b.getResources().getString(R.string.f48290_resource_name_obfuscated_RES_2131952146));
        }
        this.d.announceForAccessibility(sb);
    }

    public void b() {
        this.e.setEnabled(false);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration((long) this.g);
        animatorSet.addListener(new C5778yY0(this));
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.b, View.TRANSLATION_Y, (float) d());
        ofFloat.setInterpolator(G30.f8058a);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.b, View.ALPHA, 0.0f);
        ofFloat2.setInterpolator(animation.InterpolatorC5286vf.f);
        animatorSet.playTogether(ofFloat2, ofFloat);
        WindowAndroid windowAndroid = this.f11749a;
        if (windowAndroid != null) {
            windowAndroid.I0(animatorSet);
        } else {
            animatorSet.start();
        }
    }

    public int c() {
        this.j.getLocationInWindow(this.o);
        return Math.max(0, (this.j.getHeight() + this.o[1]) - this.m.bottom);
    }

    public int d() {
        return this.b.getHeight() + ((FrameLayout.LayoutParams) this.b.getLayoutParams()).bottomMargin;
    }

    public final void e(TextView textView, CharSequence charSequence, boolean z) {
        if (!textView.getText().toString().equals(charSequence)) {
            textView.animate().cancel();
            if (z) {
                textView.setAlpha(0.0f);
                textView.setText(charSequence);
                textView.animate().alpha(1.0f).setDuration((long) this.g).setListener(null);
                return;
            }
            textView.setText(charSequence);
        }
    }

    public void f() {
        this.j.addView(this.b);
        this.l.addOnLayoutChangeListener(this.p);
        this.b.addOnLayoutChangeListener(new View$OnLayoutChangeListenerC5608xY0(this));
    }

    public final boolean g(C4076oY0 oy0, boolean z) {
        int i2;
        int i3;
        if (this.k == oy0) {
            return false;
        }
        this.k = oy0;
        this.d.setMaxLines(oy0.i ? 1 : 5);
        TemplatePreservingTextView templatePreservingTextView = this.d;
        String str = oy0.c;
        Objects.requireNonNull(templatePreservingTextView);
        if (TextUtils.isEmpty(str)) {
            str = null;
        }
        templatePreservingTextView.I = str;
        e(this.d, oy0.b, z);
        TemplatePreservingTextView templatePreservingTextView2 = this.d;
        if (oy0.n == 1) {
            i2 = R.style.f71990_resource_name_obfuscated_RES_2132017772;
        } else {
            i2 = oy0.h;
            if (i2 == 0) {
                i2 = R.style.f71970_resource_name_obfuscated_RES_2132017770;
            }
        }
        AbstractC3153j7.i(templatePreservingTextView2, i2);
        AbstractC3153j7.i(this.e, oy0.n == 1 ? R.style.f70920_resource_name_obfuscated_RES_2132017665 : R.style.f72370_resource_name_obfuscated_RES_2132017810);
        ViewGroup viewGroup = this.b;
        if (oy0.n == 1) {
            i3 = viewGroup.getResources().getColor(R.color.f11100_resource_name_obfuscated_RES_2131099800);
        } else {
            int i4 = oy0.g;
            if (i4 != 0) {
                i3 = i4;
            } else {
                i3 = viewGroup.getResources().getColor(R.color.f14990_resource_name_obfuscated_RES_2131100189);
            }
        }
        if (this.h) {
            this.c.setBackgroundResource(R.drawable.f35020_resource_name_obfuscated_RES_2131231542);
            ((GradientDrawable) this.c.getBackground().mutate()).setColor(i3);
        } else {
            this.c.setBackgroundColor(i3);
        }
        if (oy0.d != null) {
            this.e.setVisibility(0);
            this.e.setContentDescription(oy0.d);
            e(this.e, oy0.d, z);
        } else {
            this.e.setVisibility(8);
        }
        Drawable drawable = oy0.k;
        if (drawable != null) {
            this.f.setVisibility(0);
            this.f.setImageDrawable(drawable);
        } else {
            this.f.setVisibility(8);
        }
        if (this.h) {
            this.b.findViewById(R.id.snackbar_shadow_left).setVisibility(0);
            this.b.findViewById(R.id.snackbar_shadow_right).setVisibility(0);
        }
        return true;
    }
}
