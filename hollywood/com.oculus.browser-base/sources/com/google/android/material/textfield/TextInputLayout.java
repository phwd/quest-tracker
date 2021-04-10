package com.google.android.material.textfield;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStructure;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.customview.view.AbsSavedState;
import com.google.android.material.internal.CheckableImageButton;
import com.oculus.browser.R;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TextInputLayout extends LinearLayout {
    public PorterDuff.Mode A0;
    public boolean B0;
    public Drawable C0;
    public int D0;
    public View.OnLongClickListener E0;
    public final FrameLayout F;
    public final LinkedHashSet F0 = new LinkedHashSet();
    public final LinearLayout G;
    public int G0 = 0;
    public final LinearLayout H;
    public final SparseArray H0;
    public final FrameLayout I;
    public final CheckableImageButton I0;

    /* renamed from: J  reason: collision with root package name */
    public EditText f9696J;
    public final LinkedHashSet J0;
    public CharSequence K;
    public ColorStateList K0;
    public final C1941c10 L;
    public boolean L0;
    public boolean M;
    public PorterDuff.Mode M0;
    public int N;
    public boolean N0;
    public boolean O;
    public Drawable O0;
    public TextView P;
    public int P0;
    public int Q;
    public Drawable Q0;
    public int R;
    public View.OnLongClickListener R0;
    public CharSequence S;
    public final CheckableImageButton S0;
    public boolean T;
    public ColorStateList T0;
    public TextView U;
    public ColorStateList U0;
    public ColorStateList V;
    public ColorStateList V0;
    public int W;
    public int W0;
    public int X0;
    public int Y0;
    public ColorStateList Z0;
    public ColorStateList a0;
    public int a1;
    public ColorStateList b0;
    public final int b1;
    public CharSequence c0;
    public final int c1;
    public final TextView d0;
    public final int d1;
    public CharSequence e0;
    public int e1;
    public final TextView f0;
    public boolean f1;
    public boolean g0;
    public final C0356Fv g1;
    public CharSequence h0;
    public boolean h1;
    public boolean i0;
    public ValueAnimator i1;
    public C3234jd0 j0;
    public boolean j1;
    public C3234jd0 k0;
    public boolean k1;
    public C3553lT0 l0;
    public final int m0;
    public int n0;
    public final int o0;
    public int p0;
    public final int q0;
    public final int r0;
    public int s0;
    public int t0;
    public final Rect u0 = new Rect();
    public final Rect v0 = new Rect();
    public final RectF w0 = new RectF();
    public final CheckableImageButton x0;
    public ColorStateList y0;
    public boolean z0;

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class SavedState extends AbsSavedState {
        public static final Parcelable.Creator CREATOR = new C0264Eg1();
        public CharSequence H;
        public boolean I;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            StringBuilder i = AbstractC2531fV.i("TextInputLayout.SavedState{");
            i.append(Integer.toHexString(System.identityHashCode(this)));
            i.append(" error=");
            i.append((Object) this.H);
            i.append("}");
            return i.toString();
        }

        @Override // androidx.customview.view.AbsSavedState
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.G, i);
            TextUtils.writeToParcel(this.H, parcel, i);
            parcel.writeInt(this.I ? 1 : 0);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.H = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.I = parcel.readInt() != 1 ? false : true;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:110:0x04ea  */
    /* JADX WARNING: Removed duplicated region for block: B:115:0x0548  */
    /* JADX WARNING: Removed duplicated region for block: B:124:0x0580  */
    /* JADX WARNING: Removed duplicated region for block: B:138:0x05dc  */
    /* JADX WARNING: Removed duplicated region for block: B:150:0x0666  */
    /* JADX WARNING: Removed duplicated region for block: B:153:0x066f  */
    /* JADX WARNING: Removed duplicated region for block: B:162:0x0699  */
    /* JADX WARNING: Removed duplicated region for block: B:165:0x06a9  */
    /* JADX WARNING: Removed duplicated region for block: B:168:0x06b6  */
    /* JADX WARNING: Removed duplicated region for block: B:171:0x06bd  */
    /* JADX WARNING: Removed duplicated region for block: B:174:0x06c6  */
    /* JADX WARNING: Removed duplicated region for block: B:182:0x06de  */
    /* JADX WARNING: Removed duplicated region for block: B:186:0x06ea  */
    /* JADX WARNING: Removed duplicated region for block: B:187:0x06ec  */
    /* JADX WARNING: Removed duplicated region for block: B:190:0x06ff  */
    /* JADX WARNING: Removed duplicated region for block: B:193:0x0708  */
    /* JADX WARNING: Removed duplicated region for block: B:194:0x070a  */
    /* JADX WARNING: Removed duplicated region for block: B:197:0x0721  */
    /* JADX WARNING: Removed duplicated region for block: B:198:0x0723  */
    /* JADX WARNING: Removed duplicated region for block: B:201:0x073c  */
    /* JADX WARNING: Removed duplicated region for block: B:207:0x0753  */
    /* JADX WARNING: Removed duplicated region for block: B:222:0x078b  */
    /* JADX WARNING: Removed duplicated region for block: B:243:0x07e2  */
    /* JADX WARNING: Removed duplicated region for block: B:246:0x07f3  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x032f  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x033e  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0368  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x03fd  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x0454  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public TextInputLayout(android.content.Context r33, android.util.AttributeSet r34) {
        /*
        // Method dump skipped, instructions count: 2112
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.textfield.TextInputLayout.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    public static void C(CheckableImageButton checkableImageButton, View.OnLongClickListener onLongClickListener) {
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        boolean hasOnClickListeners = checkableImageButton.hasOnClickListeners();
        boolean z = false;
        int i = 1;
        boolean z2 = onLongClickListener != null;
        if (hasOnClickListeners || z2) {
            z = true;
        }
        checkableImageButton.setFocusable(z);
        checkableImageButton.setClickable(hasOnClickListeners);
        checkableImageButton.K = hasOnClickListeners;
        checkableImageButton.setLongClickable(z2);
        if (!z) {
            i = 2;
        }
        checkableImageButton.setImportantForAccessibility(i);
    }

    public static void q(ViewGroup viewGroup, boolean z) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            childAt.setEnabled(z);
            if (childAt instanceof ViewGroup) {
                q((ViewGroup) childAt, z);
            }
        }
    }

    public void A(boolean z) {
        C1941c10 c10 = this.L;
        if (c10.r != z) {
            c10.c();
            if (z) {
                N8 n8 = new N8(c10.f9578a);
                c10.s = n8;
                n8.setId(R.id.textinput_helper_text);
                c10.s.setTextAlignment(5);
                c10.s.setVisibility(4);
                c10.s.setAccessibilityLiveRegion(1);
                int i = c10.t;
                c10.t = i;
                TextView textView = c10.s;
                if (textView != null) {
                    textView.setTextAppearance(i);
                }
                ColorStateList colorStateList = c10.u;
                c10.u = colorStateList;
                TextView textView2 = c10.s;
                if (!(textView2 == null || colorStateList == null)) {
                    textView2.setTextColor(colorStateList);
                }
                c10.a(c10.s, 1);
            } else {
                c10.c();
                int i2 = c10.i;
                if (i2 == 2) {
                    c10.j = 0;
                }
                c10.k(i2, c10.j, c10.j(c10.s, null));
                c10.i(c10.s, 1);
                c10.s = null;
                c10.b.L();
                c10.b.V();
            }
            c10.r = z;
        }
    }

    public void B(CharSequence charSequence) {
        if (this.g0) {
            if (!TextUtils.equals(charSequence, this.h0)) {
                this.h0 = charSequence;
                C0356Fv fv = this.g1;
                if (charSequence == null || !TextUtils.equals(fv.w, charSequence)) {
                    fv.w = charSequence;
                    fv.x = null;
                    Bitmap bitmap = fv.z;
                    if (bitmap != null) {
                        bitmap.recycle();
                        fv.z = null;
                    }
                    fv.j();
                }
                if (!this.f1) {
                    p();
                }
            }
            sendAccessibilityEvent(2048);
        }
    }

    public final void D(boolean z) {
        if (this.T != z) {
            if (z) {
                N8 n8 = new N8(getContext());
                this.U = n8;
                n8.setId(R.id.textinput_placeholder);
                this.U.setAccessibilityLiveRegion(1);
                int i = this.W;
                this.W = i;
                TextView textView = this.U;
                if (textView != null) {
                    textView.setTextAppearance(i);
                }
                ColorStateList colorStateList = this.V;
                if (colorStateList != colorStateList) {
                    this.V = colorStateList;
                    TextView textView2 = this.U;
                    if (!(textView2 == null || colorStateList == null)) {
                        textView2.setTextColor(colorStateList);
                    }
                }
                TextView textView3 = this.U;
                if (textView3 != null) {
                    this.F.addView(textView3);
                    this.U.setVisibility(0);
                }
            } else {
                TextView textView4 = this.U;
                if (textView4 != null) {
                    textView4.setVisibility(8);
                }
                this.U = null;
            }
            this.T = z;
        }
    }

    public void E(boolean z) {
        int i = 0;
        if ((this.x0.getVisibility() == 0) != z) {
            CheckableImageButton checkableImageButton = this.x0;
            if (!z) {
                i = 8;
            }
            checkableImageButton.setVisibility(i);
            Q();
            K();
        }
    }

    public void F(TextView textView, int i) {
        boolean z = true;
        try {
            textView.setTextAppearance(i);
            if (textView.getTextColors().getDefaultColor() != -65281) {
                z = false;
            }
        } catch (Exception unused) {
        }
        if (z) {
            textView.setTextAppearance(R.style.f70390_resource_name_obfuscated_RES_2132017612);
            Context context = getContext();
            Object obj = K2.f8337a;
            textView.setTextColor(context.getColor(R.color.f11990_resource_name_obfuscated_RES_2131099889));
        }
    }

    public final void G() {
        TextView textView = this.U;
        if (textView != null && this.T) {
            textView.setText(this.S);
            this.U.setVisibility(0);
            this.U.bringToFront();
        }
    }

    public final void H() {
        if (this.P != null) {
            EditText editText = this.f9696J;
            I(editText == null ? 0 : editText.getText().length());
        }
    }

    public void I(int i) {
        boolean z = this.O;
        int i2 = this.N;
        if (i2 == -1) {
            this.P.setText(String.valueOf(i));
            this.P.setContentDescription(null);
            this.O = false;
        } else {
            this.O = i > i2;
            Context context = getContext();
            this.P.setContentDescription(context.getString(this.O ? R.string.f48620_resource_name_obfuscated_RES_2131952179 : R.string.f48610_resource_name_obfuscated_RES_2131952178, Integer.valueOf(i), Integer.valueOf(this.N)));
            if (z != this.O) {
                J();
            }
            this.P.setText(C4271ph.c().d(getContext().getString(R.string.f48630_resource_name_obfuscated_RES_2131952180, Integer.valueOf(i), Integer.valueOf(this.N))));
        }
        if (this.f9696J != null && z != this.O) {
            O(false, false);
            V();
            L();
        }
    }

    public final void J() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        TextView textView = this.P;
        if (textView != null) {
            F(textView, this.O ? this.Q : this.R);
            if (!this.O && (colorStateList2 = this.a0) != null) {
                this.P.setTextColor(colorStateList2);
            }
            if (this.O && (colorStateList = this.b0) != null) {
                this.P.setTextColor(colorStateList);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x007f  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x009c  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x011c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean K() {
        /*
        // Method dump skipped, instructions count: 319
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.textfield.TextInputLayout.K():boolean");
    }

    public void L() {
        Drawable background;
        TextView textView;
        EditText editText = this.f9696J;
        if (editText != null && this.n0 == 0 && (background = editText.getBackground()) != null) {
            if (XI.a(background)) {
                background = background.mutate();
            }
            if (this.L.e()) {
                background.setColorFilter(C3840n8.c(this.L.g(), PorterDuff.Mode.SRC_IN));
            } else if (!this.O || (textView = this.P) == null) {
                background.clearColorFilter();
                this.f9696J.refreshDrawableState();
            } else {
                background.setColorFilter(C3840n8.c(textView.getCurrentTextColor(), PorterDuff.Mode.SRC_IN));
            }
        }
    }

    public final void M(CheckableImageButton checkableImageButton, ColorStateList colorStateList) {
        Drawable drawable = checkableImageButton.getDrawable();
        if (checkableImageButton.getDrawable() != null && colorStateList != null && colorStateList.isStateful()) {
            int colorForState = colorStateList.getColorForState(getDrawableState(), colorStateList.getDefaultColor());
            Drawable mutate = drawable.mutate();
            mutate.setTintList(ColorStateList.valueOf(colorForState));
            checkableImageButton.setImageDrawable(mutate);
        }
    }

    public final void N() {
        if (this.n0 != 1) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.F.getLayoutParams();
            int f = f();
            if (f != layoutParams.topMargin) {
                layoutParams.topMargin = f;
                this.F.requestLayout();
            }
        }
    }

    public final void O(boolean z, boolean z2) {
        ColorStateList colorStateList;
        TextView textView;
        boolean isEnabled = isEnabled();
        EditText editText = this.f9696J;
        boolean z3 = editText != null && !TextUtils.isEmpty(editText.getText());
        EditText editText2 = this.f9696J;
        boolean z4 = editText2 != null && editText2.hasFocus();
        boolean e = this.L.e();
        ColorStateList colorStateList2 = this.U0;
        if (colorStateList2 != null) {
            C0356Fv fv = this.g1;
            if (fv.l != colorStateList2) {
                fv.l = colorStateList2;
                fv.j();
            }
            C0356Fv fv2 = this.g1;
            ColorStateList colorStateList3 = this.U0;
            if (fv2.k != colorStateList3) {
                fv2.k = colorStateList3;
                fv2.j();
            }
        }
        if (!isEnabled) {
            ColorStateList colorStateList4 = this.U0;
            int colorForState = colorStateList4 != null ? colorStateList4.getColorForState(new int[]{-16842910}, this.e1) : this.e1;
            this.g1.l(ColorStateList.valueOf(colorForState));
            C0356Fv fv3 = this.g1;
            ColorStateList valueOf = ColorStateList.valueOf(colorForState);
            if (fv3.k != valueOf) {
                fv3.k = valueOf;
                fv3.j();
            }
        } else if (e) {
            C0356Fv fv4 = this.g1;
            TextView textView2 = this.L.m;
            fv4.l(textView2 != null ? textView2.getTextColors() : null);
        } else if (this.O && (textView = this.P) != null) {
            this.g1.l(textView.getTextColors());
        } else if (z4 && (colorStateList = this.V0) != null) {
            C0356Fv fv5 = this.g1;
            if (fv5.l != colorStateList) {
                fv5.l = colorStateList;
                fv5.j();
            }
        }
        if (z3 || (isEnabled() && (z4 || e))) {
            if (z2 || this.f1) {
                ValueAnimator valueAnimator = this.i1;
                if (valueAnimator != null && valueAnimator.isRunning()) {
                    this.i1.cancel();
                }
                if (!z || !this.h1) {
                    this.g1.n(1.0f);
                } else {
                    b(1.0f);
                }
                this.f1 = false;
                if (g()) {
                    p();
                }
                G();
                R();
                U();
            }
        } else if (z2 || !this.f1) {
            ValueAnimator valueAnimator2 = this.i1;
            if (valueAnimator2 != null && valueAnimator2.isRunning()) {
                this.i1.cancel();
            }
            if (!z || !this.h1) {
                this.g1.n(0.0f);
            } else {
                b(0.0f);
            }
            if (g() && (!((C5726yC) this.j0).e0.isEmpty()) && g()) {
                ((C5726yC) this.j0).t(0.0f, 0.0f, 0.0f, 0.0f);
            }
            this.f1 = true;
            TextView textView3 = this.U;
            if (textView3 != null && this.T) {
                textView3.setText((CharSequence) null);
                this.U.setVisibility(4);
            }
            R();
            U();
        }
    }

    public final void P(int i) {
        if (i != 0 || this.f1) {
            TextView textView = this.U;
            if (textView != null && this.T) {
                textView.setText((CharSequence) null);
                this.U.setVisibility(4);
                return;
            }
            return;
        }
        G();
    }

    public final void Q() {
        if (this.f9696J != null) {
            TextView textView = this.d0;
            int i = 0;
            if (!(this.x0.getVisibility() == 0)) {
                i = this.f9696J.getPaddingLeft();
            }
            textView.setPadding(i, this.f9696J.getCompoundPaddingTop(), this.d0.getCompoundPaddingRight(), this.f9696J.getCompoundPaddingBottom());
        }
    }

    public final void R() {
        this.d0.setVisibility((this.c0 == null || this.f1) ? 8 : 0);
        K();
    }

    public final void S(boolean z, boolean z2) {
        int defaultColor = this.Z0.getDefaultColor();
        int colorForState = this.Z0.getColorForState(new int[]{16843623, 16842910}, defaultColor);
        int colorForState2 = this.Z0.getColorForState(new int[]{16843518, 16842910}, defaultColor);
        if (z) {
            this.s0 = colorForState2;
        } else if (z2) {
            this.s0 = colorForState;
        } else {
            this.s0 = defaultColor;
        }
    }

    public final void T() {
        if (this.f9696J != null) {
            TextView textView = this.f0;
            int paddingLeft = textView.getPaddingLeft();
            int paddingTop = this.f9696J.getPaddingTop();
            int i = 0;
            if (!n()) {
                if (!(this.S0.getVisibility() == 0)) {
                    i = this.f9696J.getPaddingRight();
                }
            }
            textView.setPadding(paddingLeft, paddingTop, i, this.f9696J.getPaddingBottom());
        }
    }

    public final void U() {
        int visibility = this.f0.getVisibility();
        int i = 0;
        boolean z = this.e0 != null && !this.f1;
        TextView textView = this.f0;
        if (!z) {
            i = 8;
        }
        textView.setVisibility(i);
        if (visibility != this.f0.getVisibility()) {
            h().c(z);
        }
        K();
    }

    /* JADX WARNING: Removed duplicated region for block: B:109:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00bb  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x00fc  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x0121  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x013e  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x0159  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x017a  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x0188  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void V() {
        /*
        // Method dump skipped, instructions count: 419
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.textfield.TextInputLayout.V():void");
    }

    public void a(AbstractC0203Dg1 dg1) {
        this.F0.add(dg1);
        if (this.f9696J != null) {
            dg1.a(this);
        }
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        boolean z;
        boolean z2;
        if (view instanceof EditText) {
            FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(layoutParams);
            layoutParams2.gravity = (layoutParams2.gravity & -113) | 16;
            this.F.addView(view, layoutParams2);
            this.F.setLayoutParams(layoutParams);
            N();
            EditText editText = (EditText) view;
            if (this.f9696J == null) {
                if (this.G0 != 3 && !(editText instanceof TextInputEditText)) {
                    Log.i("TextInputLayout", "EditText added is not a TextInputEditText. Please switch to using that class instead.");
                }
                this.f9696J = editText;
                o();
                C0142Cg1 cg1 = new C0142Cg1(this);
                EditText editText2 = this.f9696J;
                if (editText2 != null) {
                    AbstractC1920bu1.n(editText2, cg1);
                }
                C0356Fv fv = this.g1;
                Typeface typeface = this.f9696J.getTypeface();
                C2577fm fmVar = fv.v;
                if (fmVar != null) {
                    fmVar.c = true;
                }
                if (fv.s != typeface) {
                    fv.s = typeface;
                    z = true;
                } else {
                    z = false;
                }
                if (fv.t != typeface) {
                    fv.t = typeface;
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (z || z2) {
                    fv.j();
                }
                C0356Fv fv2 = this.g1;
                float textSize = this.f9696J.getTextSize();
                if (fv2.i != textSize) {
                    fv2.i = textSize;
                    fv2.j();
                }
                int gravity = this.f9696J.getGravity();
                this.g1.m((gravity & -113) | 48);
                C0356Fv fv3 = this.g1;
                if (fv3.g != gravity) {
                    fv3.g = gravity;
                    fv3.j();
                }
                this.f9696J.addTextChangedListener(new C5801yg1(this));
                if (this.U0 == null) {
                    this.U0 = this.f9696J.getHintTextColors();
                }
                if (this.g0) {
                    if (TextUtils.isEmpty(this.h0)) {
                        CharSequence hint = this.f9696J.getHint();
                        this.K = hint;
                        B(hint);
                        this.f9696J.setHint((CharSequence) null);
                    }
                    this.i0 = true;
                }
                if (this.P != null) {
                    I(this.f9696J.getText().length());
                }
                L();
                this.L.b();
                this.G.bringToFront();
                this.H.bringToFront();
                this.I.bringToFront();
                this.S0.bringToFront();
                Iterator it = this.F0.iterator();
                while (it.hasNext()) {
                    ((AbstractC0203Dg1) it.next()).a(this);
                }
                Q();
                T();
                if (!isEnabled()) {
                    editText.setEnabled(false);
                }
                O(false, true);
                return;
            }
            throw new IllegalArgumentException("We already have an EditText, can only have one");
        }
        super.addView(view, i, layoutParams);
    }

    public void b(float f) {
        if (this.g1.c != f) {
            if (this.i1 == null) {
                ValueAnimator valueAnimator = new ValueAnimator();
                this.i1 = valueAnimator;
                valueAnimator.setInterpolator(P6.b);
                this.i1.setDuration(167L);
                this.i1.addUpdateListener(new C0081Bg1(this));
            }
            this.i1.setFloatValues(this.g1.c, f);
            this.i1.start();
        }
    }

    public final void c() {
        d(this.I0, this.L0, this.K0, this.N0, this.M0);
    }

    public final void d(CheckableImageButton checkableImageButton, boolean z, ColorStateList colorStateList, boolean z2, PorterDuff.Mode mode) {
        Drawable drawable = checkableImageButton.getDrawable();
        if (drawable != null && (z || z2)) {
            drawable = drawable.mutate();
            if (z) {
                drawable.setTintList(colorStateList);
            }
            if (z2) {
                drawable.setTintMode(mode);
            }
        }
        if (checkableImageButton.getDrawable() != drawable) {
            checkableImageButton.setImageDrawable(drawable);
        }
    }

    public void dispatchProvideAutofillStructure(ViewStructure viewStructure, int i) {
        EditText editText;
        if (this.K == null || (editText = this.f9696J) == null) {
            super.dispatchProvideAutofillStructure(viewStructure, i);
            return;
        }
        boolean z = this.i0;
        this.i0 = false;
        CharSequence hint = editText.getHint();
        this.f9696J.setHint(this.K);
        try {
            super.dispatchProvideAutofillStructure(viewStructure, i);
        } finally {
            this.f9696J.setHint(hint);
            this.i0 = z;
        }
    }

    @Override // android.view.View, android.view.ViewGroup
    public void dispatchRestoreInstanceState(SparseArray sparseArray) {
        this.k1 = true;
        super.dispatchRestoreInstanceState(sparseArray);
        this.k1 = false;
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.g0) {
            C0356Fv fv = this.g1;
            Objects.requireNonNull(fv);
            int save = canvas.save();
            if (fv.x != null && fv.b) {
                fv.M.getLineLeft(0);
                fv.E.setTextSize(fv.B);
                float f = fv.q;
                float f2 = fv.r;
                float lineAscent = (float) fv.M.getLineAscent(0);
                float f3 = fv.A;
                if (f3 != 1.0f) {
                    canvas.scale(f3, f3, f, f2);
                }
                canvas.translate(f, f2 + lineAscent);
                fv.M.draw(canvas);
                canvas.restoreToCount(save);
            }
        }
        C3234jd0 jd0 = this.k0;
        if (jd0 != null) {
            Rect bounds = jd0.getBounds();
            bounds.top = bounds.bottom - this.p0;
            this.k0.draw(canvas);
        }
    }

    public void drawableStateChanged() {
        boolean z;
        boolean z2;
        ColorStateList colorStateList;
        if (!this.j1) {
            boolean z3 = true;
            this.j1 = true;
            super.drawableStateChanged();
            int[] drawableState = getDrawableState();
            C0356Fv fv = this.g1;
            if (fv != null) {
                fv.C = drawableState;
                ColorStateList colorStateList2 = fv.l;
                if ((colorStateList2 != null && colorStateList2.isStateful()) || ((colorStateList = fv.k) != null && colorStateList.isStateful())) {
                    fv.j();
                    z2 = true;
                } else {
                    z2 = false;
                }
                z = z2 | false;
            } else {
                z = false;
            }
            if (this.f9696J != null) {
                AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
                if (!isLaidOut() || !isEnabled()) {
                    z3 = false;
                }
                O(z3, false);
            }
            L();
            V();
            if (z) {
                invalidate();
            }
            this.j1 = false;
        }
    }

    public final void e() {
        d(this.x0, this.z0, this.y0, this.B0, this.A0);
    }

    public final int f() {
        float f;
        if (!this.g0) {
            return 0;
        }
        int i = this.n0;
        if (i == 0 || i == 1) {
            f = this.g1.f();
        } else if (i != 2) {
            return 0;
        } else {
            f = this.g1.f() / 2.0f;
        }
        return (int) f;
    }

    public final boolean g() {
        return this.g0 && !TextUtils.isEmpty(this.h0) && (this.j0 instanceof C5726yC);
    }

    public int getBaseline() {
        EditText editText = this.f9696J;
        if (editText == null) {
            return super.getBaseline();
        }
        return f() + getPaddingTop() + editText.getBaseline();
    }

    public final AbstractC2170dL h() {
        AbstractC2170dL dLVar = (AbstractC2170dL) this.H0.get(this.G0);
        return dLVar != null ? dLVar : (AbstractC2170dL) this.H0.get(0);
    }

    public CharSequence i() {
        C1941c10 c10 = this.L;
        if (c10.l) {
            return c10.k;
        }
        return null;
    }

    public CharSequence j() {
        if (this.g0) {
            return this.h0;
        }
        return null;
    }

    public final int k(int i, boolean z) {
        int compoundPaddingLeft = this.f9696J.getCompoundPaddingLeft() + i;
        return (this.c0 == null || z) ? compoundPaddingLeft : (compoundPaddingLeft - this.d0.getMeasuredWidth()) + this.d0.getPaddingLeft();
    }

    public final int l(int i, boolean z) {
        int compoundPaddingRight = i - this.f9696J.getCompoundPaddingRight();
        if (this.c0 == null || !z) {
            return compoundPaddingRight;
        }
        return this.d0.getPaddingRight() + this.d0.getMeasuredWidth() + compoundPaddingRight;
    }

    public final boolean m() {
        return this.G0 != 0;
    }

    public boolean n() {
        return this.I.getVisibility() == 0 && this.I0.getVisibility() == 0;
    }

    public final void o() {
        int i = this.n0;
        boolean z = true;
        if (i == 0) {
            this.j0 = null;
            this.k0 = null;
        } else if (i == 1) {
            this.j0 = new C3234jd0(this.l0);
            this.k0 = new C3234jd0();
        } else if (i == 2) {
            if (!this.g0 || (this.j0 instanceof C5726yC)) {
                this.j0 = new C3234jd0(this.l0);
            } else {
                this.j0 = new C5726yC(this.l0);
            }
            this.k0 = null;
        } else {
            throw new IllegalArgumentException(this.n0 + " is illegal; only @BoxBackgroundMode constants are supported.");
        }
        EditText editText = this.f9696J;
        if (editText == null || this.j0 == null || editText.getBackground() != null || this.n0 == 0) {
            z = false;
        }
        if (z) {
            EditText editText2 = this.f9696J;
            C3234jd0 jd0 = this.j0;
            AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
            editText2.setBackground(jd0);
        }
        V();
        if (this.n0 != 0) {
            N();
        }
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        super.onLayout(z, i, i2, i3, i4);
        EditText editText = this.f9696J;
        if (editText != null) {
            Rect rect = this.u0;
            ThreadLocal threadLocal = AbstractC4029oE.f10538a;
            boolean z2 = false;
            rect.set(0, 0, editText.getWidth(), editText.getHeight());
            ThreadLocal threadLocal2 = AbstractC4029oE.f10538a;
            Matrix matrix = (Matrix) threadLocal2.get();
            if (matrix == null) {
                matrix = new Matrix();
                threadLocal2.set(matrix);
            } else {
                matrix.reset();
            }
            AbstractC4029oE.a(this, editText, matrix);
            ThreadLocal threadLocal3 = AbstractC4029oE.b;
            RectF rectF = (RectF) threadLocal3.get();
            if (rectF == null) {
                rectF = new RectF();
                threadLocal3.set(rectF);
            }
            rectF.set(rect);
            matrix.mapRect(rectF);
            rect.set((int) (rectF.left + 0.5f), (int) (rectF.top + 0.5f), (int) (rectF.right + 0.5f), (int) (rectF.bottom + 0.5f));
            C3234jd0 jd0 = this.k0;
            if (jd0 != null) {
                int i7 = rect.bottom;
                jd0.setBounds(rect.left, i7 - this.r0, rect.right, i7);
            }
            if (this.g0) {
                C0356Fv fv = this.g1;
                float textSize = this.f9696J.getTextSize();
                if (fv.i != textSize) {
                    fv.i = textSize;
                    fv.j();
                }
                int gravity = this.f9696J.getGravity();
                this.g1.m((gravity & -113) | 48);
                C0356Fv fv2 = this.g1;
                if (fv2.g != gravity) {
                    fv2.g = gravity;
                    fv2.j();
                }
                C0356Fv fv3 = this.g1;
                if (this.f9696J != null) {
                    Rect rect2 = this.v0;
                    AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
                    boolean z3 = getLayoutDirection() == 1;
                    rect2.bottom = rect.bottom;
                    int i8 = this.n0;
                    if (i8 == 1) {
                        rect2.left = k(rect.left, z3);
                        rect2.top = rect.top + this.o0;
                        rect2.right = l(rect.right, z3);
                    } else if (i8 != 2) {
                        rect2.left = k(rect.left, z3);
                        rect2.top = getPaddingTop();
                        rect2.right = l(rect.right, z3);
                    } else {
                        rect2.left = this.f9696J.getPaddingLeft() + rect.left;
                        rect2.top = rect.top - f();
                        rect2.right = rect.right - this.f9696J.getPaddingRight();
                    }
                    Objects.requireNonNull(fv3);
                    int i9 = rect2.left;
                    int i10 = rect2.top;
                    int i11 = rect2.right;
                    int i12 = rect2.bottom;
                    if (!C0356Fv.k(fv3.e, i9, i10, i11, i12)) {
                        fv3.e.set(i9, i10, i11, i12);
                        fv3.D = true;
                        fv3.i();
                    }
                    C0356Fv fv4 = this.g1;
                    if (this.f9696J != null) {
                        Rect rect3 = this.v0;
                        TextPaint textPaint = fv4.F;
                        textPaint.setTextSize(fv4.i);
                        textPaint.setTypeface(fv4.t);
                        float f = -fv4.F.ascent();
                        rect3.left = this.f9696J.getCompoundPaddingLeft() + rect.left;
                        if (this.n0 == 1 && this.f9696J.getMinLines() <= 1) {
                            i5 = (int) (((float) rect.centerY()) - (f / 2.0f));
                        } else {
                            i5 = rect.top + this.f9696J.getCompoundPaddingTop();
                        }
                        rect3.top = i5;
                        rect3.right = rect.right - this.f9696J.getCompoundPaddingRight();
                        if (this.n0 == 1 && this.f9696J.getMinLines() <= 1) {
                            z2 = true;
                        }
                        if (z2) {
                            i6 = (int) (((float) rect3.top) + f);
                        } else {
                            i6 = rect.bottom - this.f9696J.getCompoundPaddingBottom();
                        }
                        rect3.bottom = i6;
                        int i13 = rect3.left;
                        int i14 = rect3.top;
                        int i15 = rect3.right;
                        if (!C0356Fv.k(fv4.d, i13, i14, i15, i6)) {
                            fv4.d.set(i13, i14, i15, i6);
                            fv4.D = true;
                            fv4.i();
                        }
                        this.g1.j();
                        if (g() && !this.f1) {
                            p();
                            return;
                        }
                        return;
                    }
                    throw new IllegalStateException();
                }
                throw new IllegalStateException();
            }
        }
    }

    public void onMeasure(int i, int i2) {
        EditText editText;
        int max;
        super.onMeasure(i, i2);
        boolean z = false;
        if (this.f9696J != null && this.f9696J.getMeasuredHeight() < (max = Math.max(this.H.getMeasuredHeight(), this.G.getMeasuredHeight()))) {
            this.f9696J.setMinimumHeight(max);
            z = true;
        }
        boolean K2 = K();
        if (z || K2) {
            this.f9696J.post(new RunnableC0020Ag1(this));
        }
        if (this.U != null && (editText = this.f9696J) != null) {
            this.U.setGravity(editText.getGravity());
            this.U.setPadding(this.f9696J.getCompoundPaddingLeft(), this.f9696J.getCompoundPaddingTop(), this.f9696J.getCompoundPaddingRight(), this.f9696J.getCompoundPaddingBottom());
        }
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.G);
        w(savedState.H);
        if (savedState.I) {
            this.I0.post(new RunnableC5971zg1(this));
        }
        requestLayout();
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        if (this.L.e()) {
            savedState.H = i();
        }
        savedState.I = m() && this.I0.isChecked();
        return savedState;
    }

    public final void p() {
        float f;
        float f2;
        int i;
        float f3;
        float f4;
        float f5;
        int i2;
        if (g()) {
            RectF rectF = this.w0;
            C0356Fv fv = this.g1;
            int width = this.f9696J.getWidth();
            int gravity = this.f9696J.getGravity();
            boolean c = fv.c(fv.w);
            fv.y = c;
            if (gravity == 17 || (gravity & 7) == 1) {
                f5 = ((float) width) / 2.0f;
                f4 = fv.b() / 2.0f;
            } else {
                if ((gravity & 8388613) == 8388613 || (gravity & 5) == 5) {
                    if (c) {
                        i2 = fv.e.left;
                    } else {
                        f5 = (float) fv.e.right;
                        f4 = fv.b();
                    }
                } else if (c) {
                    f5 = (float) fv.e.right;
                    f4 = fv.b();
                } else {
                    i2 = fv.e.left;
                }
                f = (float) i2;
                rectF.left = f;
                Rect rect = fv.e;
                rectF.top = (float) rect.top;
                if (gravity != 17 || (gravity & 7) == 1) {
                    f2 = (((float) width) / 2.0f) + (fv.b() / 2.0f);
                } else {
                    if ((gravity & 8388613) == 8388613 || (gravity & 5) == 5) {
                        if (fv.y) {
                            f3 = fv.b();
                        } else {
                            i = rect.right;
                            f2 = (float) i;
                        }
                    } else if (fv.y) {
                        i = rect.right;
                        f2 = (float) i;
                    } else {
                        f3 = fv.b();
                    }
                    f2 = f3 + f;
                }
                rectF.right = f2;
                float f6 = fv.f() + ((float) fv.e.top);
                rectF.bottom = f6;
                float f7 = rectF.left;
                float f8 = (float) this.m0;
                rectF.left = f7 - f8;
                rectF.top -= f8;
                rectF.right += f8;
                rectF.bottom = f6 + f8;
                rectF.offset((float) (-getPaddingLeft()), (float) (-getPaddingTop()));
                C5726yC yCVar = (C5726yC) this.j0;
                Objects.requireNonNull(yCVar);
                yCVar.t(rectF.left, rectF.top, rectF.right, rectF.bottom);
            }
            f = f5 - f4;
            rectF.left = f;
            Rect rect2 = fv.e;
            rectF.top = (float) rect2.top;
            if (gravity != 17) {
            }
            f2 = (((float) width) / 2.0f) + (fv.b() / 2.0f);
            rectF.right = f2;
            float f62 = fv.f() + ((float) fv.e.top);
            rectF.bottom = f62;
            float f72 = rectF.left;
            float f82 = (float) this.m0;
            rectF.left = f72 - f82;
            rectF.top -= f82;
            rectF.right += f82;
            rectF.bottom = f62 + f82;
            rectF.offset((float) (-getPaddingLeft()), (float) (-getPaddingTop()));
            C5726yC yCVar2 = (C5726yC) this.j0;
            Objects.requireNonNull(yCVar2);
            yCVar2.t(rectF.left, rectF.top, rectF.right, rectF.bottom);
        }
    }

    public void r(boolean z) {
        if (this.M != z) {
            if (z) {
                N8 n8 = new N8(getContext());
                this.P = n8;
                n8.setId(R.id.textinput_counter);
                this.P.setMaxLines(1);
                this.L.a(this.P, 2);
                ((ViewGroup.MarginLayoutParams) this.P.getLayoutParams()).setMarginStart(getResources().getDimensionPixelOffset(R.dimen.f22610_resource_name_obfuscated_RES_2131165880));
                J();
                H();
            } else {
                this.L.i(this.P, 2);
                this.P = null;
            }
            this.M = z;
        }
    }

    public void s(CharSequence charSequence) {
        if (this.I0.getContentDescription() != charSequence) {
            this.I0.setContentDescription(charSequence);
        }
    }

    public void setEnabled(boolean z) {
        q(this, z);
        super.setEnabled(z);
    }

    public void t(int i) {
        int i2 = this.G0;
        this.G0 = i;
        v(i != 0);
        if (h().b(this.n0)) {
            h().a();
            c();
            Iterator it = this.J0.iterator();
            while (it.hasNext()) {
                Objects.requireNonNull((C0241Dy0) it.next());
                EditText editText = this.f9696J;
                if (editText != null && i2 == 1) {
                    editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
            return;
        }
        StringBuilder i3 = AbstractC2531fV.i("The current box background mode ");
        i3.append(this.n0);
        i3.append(" is not supported by the end icon mode ");
        i3.append(i);
        throw new IllegalStateException(i3.toString());
    }

    public void u(View.OnClickListener onClickListener) {
        CheckableImageButton checkableImageButton = this.I0;
        View.OnLongClickListener onLongClickListener = this.R0;
        checkableImageButton.setOnClickListener(null);
        C(checkableImageButton, onLongClickListener);
    }

    public void v(boolean z) {
        if (n() != z) {
            this.I0.setVisibility(z ? 0 : 8);
            T();
            K();
        }
    }

    public void w(CharSequence charSequence) {
        if (!this.L.l) {
            if (!TextUtils.isEmpty(charSequence)) {
                x(true);
            } else {
                return;
            }
        }
        if (!TextUtils.isEmpty(charSequence)) {
            C1941c10 c10 = this.L;
            c10.c();
            c10.k = charSequence;
            c10.m.setText(charSequence);
            int i = c10.i;
            if (i != 1) {
                c10.j = 1;
            }
            c10.k(i, c10.j, c10.j(c10.m, charSequence));
            return;
        }
        this.L.h();
    }

    public void x(boolean z) {
        C1941c10 c10 = this.L;
        if (c10.l != z) {
            c10.c();
            if (z) {
                N8 n8 = new N8(c10.f9578a);
                c10.m = n8;
                n8.setId(R.id.textinput_error);
                c10.m.setTextAlignment(5);
                int i = c10.o;
                c10.o = i;
                TextView textView = c10.m;
                if (textView != null) {
                    c10.b.F(textView, i);
                }
                ColorStateList colorStateList = c10.p;
                c10.p = colorStateList;
                TextView textView2 = c10.m;
                if (!(textView2 == null || colorStateList == null)) {
                    textView2.setTextColor(colorStateList);
                }
                CharSequence charSequence = c10.n;
                c10.n = charSequence;
                TextView textView3 = c10.m;
                if (textView3 != null) {
                    textView3.setContentDescription(charSequence);
                }
                c10.m.setVisibility(4);
                c10.m.setAccessibilityLiveRegion(1);
                c10.a(c10.m, 0);
            } else {
                c10.h();
                c10.i(c10.m, 0);
                c10.m = null;
                c10.b.L();
                c10.b.V();
            }
            c10.l = z;
        }
    }

    public void y(Drawable drawable) {
        this.S0.setImageDrawable(drawable);
        z(drawable != null && this.L.l);
    }

    public final void z(boolean z) {
        int i = 0;
        this.S0.setVisibility(z ? 0 : 8);
        FrameLayout frameLayout = this.I;
        if (z) {
            i = 8;
        }
        frameLayout.setVisibility(i);
        T();
        if (!m()) {
            K();
        }
    }
}
