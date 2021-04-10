package defpackage;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.material.tabs.TabLayout;
import com.oculus.browser.R;
import java.util.Objects;

/* renamed from: G81  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class G81 extends LinearLayout {
    public D81 F;
    public TextView G;
    public ImageView H;
    public View I;

    /* renamed from: J  reason: collision with root package name */
    public TextView f8068J;
    public ImageView K;
    public Drawable L;
    public int M = 2;
    public final /* synthetic */ TabLayout N;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v0, types: [G81, android.widget.LinearLayout, android.view.View] */
    /* JADX WARN: Type inference failed for: r0v4, types: [android.graphics.drawable.RippleDrawable] */
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public G81(com.google.android.material.tabs.TabLayout r9, android.content.Context r10) {
        /*
        // Method dump skipped, instructions count: 165
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.G81.<init>(com.google.android.material.tabs.TabLayout, android.content.Context):void");
    }

    public final void a() {
        Drawable drawable;
        D81 d81 = this.F;
        Drawable drawable2 = null;
        View view = d81 != null ? d81.e : null;
        if (view != null) {
            ViewParent parent = view.getParent();
            if (parent != this) {
                if (parent != null) {
                    ((ViewGroup) parent).removeView(view);
                }
                addView(view);
            }
            this.I = view;
            TextView textView = this.G;
            if (textView != null) {
                textView.setVisibility(8);
            }
            ImageView imageView = this.H;
            if (imageView != null) {
                imageView.setVisibility(8);
                this.H.setImageDrawable(null);
            }
            TextView textView2 = (TextView) view.findViewById(16908308);
            this.f8068J = textView2;
            if (textView2 != null) {
                this.M = textView2.getMaxLines();
            }
            this.K = (ImageView) view.findViewById(16908294);
        } else {
            View view2 = this.I;
            if (view2 != null) {
                removeView(view2);
                this.I = null;
            }
            this.f8068J = null;
            this.K = null;
        }
        boolean z = false;
        if (this.I == null) {
            if (this.H == null) {
                ImageView imageView2 = (ImageView) LayoutInflater.from(getContext()).inflate(R.layout.f37860_resource_name_obfuscated_RES_2131624095, (ViewGroup) this, false);
                this.H = imageView2;
                addView(imageView2, 0);
            }
            if (!(d81 == null || (drawable = d81.f7870a) == null)) {
                drawable2 = drawable.mutate();
            }
            if (drawable2 != null) {
                drawable2.setTintList(this.N.Q);
                PorterDuff.Mode mode = this.N.T;
                if (mode != null) {
                    drawable2.setTintMode(mode);
                }
            }
            if (this.G == null) {
                TextView textView3 = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.f37870_resource_name_obfuscated_RES_2131624096, (ViewGroup) this, false);
                this.G = textView3;
                addView(textView3);
                this.M = this.G.getMaxLines();
            }
            this.G.setTextAppearance(this.N.O);
            ColorStateList colorStateList = this.N.P;
            if (colorStateList != null) {
                this.G.setTextColor(colorStateList);
            }
            b(this.G, this.H);
            ImageView imageView3 = this.H;
            if (imageView3 != null) {
                imageView3.addOnLayoutChangeListener(new F81(this, imageView3));
            }
            TextView textView4 = this.G;
            if (textView4 != null) {
                textView4.addOnLayoutChangeListener(new F81(this, textView4));
            }
        } else {
            TextView textView5 = this.f8068J;
            if (!(textView5 == null && this.K == null)) {
                b(textView5, this.K);
            }
        }
        if (d81 != null && !TextUtils.isEmpty(d81.c)) {
            setContentDescription(d81.c);
        }
        if (d81 != null && d81.a()) {
            z = true;
        }
        setSelected(z);
    }

    public final void b(TextView textView, ImageView imageView) {
        Drawable drawable;
        D81 d81 = this.F;
        CharSequence charSequence = null;
        Drawable mutate = (d81 == null || (drawable = d81.f7870a) == null) ? null : drawable.mutate();
        D81 d812 = this.F;
        CharSequence charSequence2 = d812 != null ? d812.b : null;
        if (imageView != null) {
            if (mutate != null) {
                imageView.setImageDrawable(mutate);
                imageView.setVisibility(0);
                setVisibility(0);
            } else {
                imageView.setVisibility(8);
                imageView.setImageDrawable(null);
            }
        }
        boolean z = !TextUtils.isEmpty(charSequence2);
        if (textView != null) {
            if (z) {
                textView.setText(charSequence2);
                Objects.requireNonNull(this.F);
                textView.setVisibility(0);
                setVisibility(0);
            } else {
                textView.setVisibility(8);
                textView.setText((CharSequence) null);
            }
        }
        if (imageView != null) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) imageView.getLayoutParams();
            int a2 = (!z || imageView.getVisibility() != 0) ? 0 : (int) AbstractC4486qv1.a(getContext(), 8);
            if (this.N.j0) {
                if (a2 != marginLayoutParams.getMarginEnd()) {
                    marginLayoutParams.setMarginEnd(a2);
                    marginLayoutParams.bottomMargin = 0;
                    imageView.setLayoutParams(marginLayoutParams);
                    imageView.requestLayout();
                }
            } else if (a2 != marginLayoutParams.bottomMargin) {
                marginLayoutParams.bottomMargin = a2;
                marginLayoutParams.setMarginEnd(0);
                imageView.setLayoutParams(marginLayoutParams);
                imageView.requestLayout();
            }
        }
        D81 d813 = this.F;
        CharSequence charSequence3 = d813 != null ? d813.c : null;
        if (!z) {
            charSequence = charSequence3;
        }
        Il1.a(this, charSequence);
    }

    public void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.L;
        boolean z = false;
        if (drawable != null && drawable.isStateful()) {
            z = false | this.L.setState(drawableState);
        }
        if (z) {
            invalidate();
            this.N.invalidate();
        }
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setCollectionItemInfo((AccessibilityNodeInfo.CollectionItemInfo) C.a(0, 1, this.F.d, 1, false, isSelected()).f7778a);
        if (isSelected()) {
            accessibilityNodeInfo.setClickable(false);
            accessibilityNodeInfo.removeAction((AccessibilityNodeInfo.AccessibilityAction) A.f7647a.i);
        }
        accessibilityNodeInfo.getExtras().putCharSequence("AccessibilityNodeInfo.roleDescription", "Tab");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x008d, code lost:
        if (((r0 / r2.getPaint().getTextSize()) * r2.getLineWidth(0)) > ((float) ((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight()))) goto L_0x008f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onMeasure(int r8, int r9) {
        /*
        // Method dump skipped, instructions count: 160
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.G81.onMeasure(int, int):void");
    }

    public boolean performClick() {
        boolean performClick = super.performClick();
        if (this.F == null) {
            return performClick;
        }
        if (!performClick) {
            playSoundEffect(0);
        }
        this.F.b();
        return true;
    }

    public void setSelected(boolean z) {
        if (isSelected() != z) {
        }
        super.setSelected(z);
        TextView textView = this.G;
        if (textView != null) {
            textView.setSelected(z);
        }
        ImageView imageView = this.H;
        if (imageView != null) {
            imageView.setSelected(z);
        }
        View view = this.I;
        if (view != null) {
            view.setSelected(z);
        }
    }
}
