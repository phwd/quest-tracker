package defpackage;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.material.textfield.TextInputLayout;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: c10  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C1941c10 {

    /* renamed from: a  reason: collision with root package name */
    public final Context f9578a;
    public final TextInputLayout b;
    public LinearLayout c;
    public int d;
    public FrameLayout e;
    public int f;
    public Animator g;
    public final float h;
    public int i;
    public int j;
    public CharSequence k;
    public boolean l;
    public TextView m;
    public CharSequence n;
    public int o;
    public ColorStateList p;
    public CharSequence q;
    public boolean r;
    public TextView s;
    public int t;
    public ColorStateList u;

    public C1941c10(TextInputLayout textInputLayout) {
        Context context = textInputLayout.getContext();
        this.f9578a = context;
        this.b = textInputLayout;
        this.h = (float) context.getResources().getDimensionPixelSize(R.dimen.f18590_resource_name_obfuscated_RES_2131165478);
    }

    public void a(TextView textView, int i2) {
        if (this.c == null && this.e == null) {
            LinearLayout linearLayout = new LinearLayout(this.f9578a);
            this.c = linearLayout;
            linearLayout.setOrientation(0);
            this.b.addView(this.c, -1, -2);
            this.e = new FrameLayout(this.f9578a);
            this.c.addView(this.e, new LinearLayout.LayoutParams(0, -2, 1.0f));
            if (this.b.f9696J != null) {
                b();
            }
        }
        if (i2 == 0 || i2 == 1) {
            this.e.setVisibility(0);
            this.e.addView(textView);
            this.f++;
        } else {
            this.c.addView(textView, new LinearLayout.LayoutParams(-2, -2));
        }
        this.c.setVisibility(0);
        this.d++;
    }

    public void b() {
        LinearLayout linearLayout = this.c;
        if ((linearLayout == null || this.b.f9696J == null) ? false : true) {
            EditText editText = this.b.f9696J;
            AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
            linearLayout.setPaddingRelative(editText.getPaddingStart(), 0, this.b.f9696J.getPaddingEnd(), 0);
        }
    }

    public void c() {
        Animator animator = this.g;
        if (animator != null) {
            animator.cancel();
        }
    }

    public final void d(List list, boolean z, TextView textView, int i2, int i3, int i4) {
        if (textView != null && z) {
            if (i2 == i4 || i2 == i3) {
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(textView, View.ALPHA, i4 == i2 ? 1.0f : 0.0f);
                ofFloat.setDuration(167L);
                ofFloat.setInterpolator(P6.f8667a);
                list.add(ofFloat);
                if (i4 == i2) {
                    ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(textView, View.TRANSLATION_Y, -this.h, 0.0f);
                    ofFloat2.setDuration(217L);
                    ofFloat2.setInterpolator(P6.d);
                    list.add(ofFloat2);
                }
            }
        }
    }

    public boolean e() {
        if (this.j != 1 || this.m == null || TextUtils.isEmpty(this.k)) {
            return false;
        }
        return true;
    }

    public final TextView f(int i2) {
        if (i2 == 1) {
            return this.m;
        }
        if (i2 != 2) {
            return null;
        }
        return this.s;
    }

    public int g() {
        TextView textView = this.m;
        if (textView != null) {
            return textView.getCurrentTextColor();
        }
        return -1;
    }

    public void h() {
        this.k = null;
        c();
        if (this.i == 1) {
            if (!this.r || TextUtils.isEmpty(this.q)) {
                this.j = 0;
            } else {
                this.j = 2;
            }
        }
        k(this.i, this.j, j(this.m, null));
    }

    public void i(TextView textView, int i2) {
        FrameLayout frameLayout;
        LinearLayout linearLayout = this.c;
        if (linearLayout != null) {
            if (!(i2 == 0 || i2 == 1) || (frameLayout = this.e) == null) {
                linearLayout.removeView(textView);
            } else {
                int i3 = this.f - 1;
                this.f = i3;
                if (i3 == 0) {
                    frameLayout.setVisibility(8);
                }
                this.e.removeView(textView);
            }
            int i4 = this.d - 1;
            this.d = i4;
            LinearLayout linearLayout2 = this.c;
            if (i4 == 0) {
                linearLayout2.setVisibility(8);
            }
        }
    }

    public final boolean j(TextView textView, CharSequence charSequence) {
        TextInputLayout textInputLayout = this.b;
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        return textInputLayout.isLaidOut() && this.b.isEnabled() && (this.j != this.i || textView == null || !TextUtils.equals(textView.getText(), charSequence));
    }

    public final void k(int i2, int i3, boolean z) {
        TextView f2;
        TextView f3;
        if (i2 != i3) {
            if (z) {
                AnimatorSet animatorSet = new AnimatorSet();
                this.g = animatorSet;
                ArrayList arrayList = new ArrayList();
                d(arrayList, this.r, this.s, 2, i2, i3);
                d(arrayList, this.l, this.m, 1, i2, i3);
                int size = arrayList.size();
                long j2 = 0;
                for (int i4 = 0; i4 < size; i4++) {
                    Animator animator = (Animator) arrayList.get(i4);
                    j2 = Math.max(j2, animator.getDuration() + animator.getStartDelay());
                }
                ValueAnimator ofInt = ValueAnimator.ofInt(0, 0);
                ofInt.setDuration(j2);
                arrayList.add(0, ofInt);
                animatorSet.playTogether(arrayList);
                animatorSet.addListener(new C1770b10(this, i3, f(i2), i2, f(i3)));
                animatorSet.start();
            } else if (i2 != i3) {
                if (!(i3 == 0 || (f3 = f(i3)) == null)) {
                    f3.setVisibility(0);
                    f3.setAlpha(1.0f);
                }
                if (!(i2 == 0 || (f2 = f(i2)) == null)) {
                    f2.setVisibility(4);
                    if (i2 == 1) {
                        f2.setText((CharSequence) null);
                    }
                }
                this.i = i3;
            }
            this.b.L();
            this.b.O(z, false);
            this.b.V();
        }
    }
}
