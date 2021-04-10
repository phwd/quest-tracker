package defpackage;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.textclassifier.TextClassifier;
import android.widget.TextView;

/* renamed from: N8  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class N8 extends TextView implements AbstractC4085oc {
    public final K7 F;
    public final M8 G;
    public final K8 H;

    public N8(Context context) {
        this(context, null, 16842884);
    }

    public final void c() {
    }

    public void drawableStateChanged() {
        super.drawableStateChanged();
        K7 k7 = this.F;
        if (k7 != null) {
            k7.a();
        }
        M8 m8 = this.G;
        if (m8 != null) {
            m8.b();
        }
    }

    public int getAutoSizeMaxTextSize() {
        if (AbstractC4085oc.f) {
            return super.getAutoSizeMaxTextSize();
        }
        M8 m8 = this.G;
        if (m8 != null) {
            return Math.round(m8.h.h);
        }
        return -1;
    }

    public int getAutoSizeMinTextSize() {
        if (AbstractC4085oc.f) {
            return super.getAutoSizeMinTextSize();
        }
        M8 m8 = this.G;
        if (m8 != null) {
            return Math.round(m8.h.g);
        }
        return -1;
    }

    public int getAutoSizeStepGranularity() {
        if (AbstractC4085oc.f) {
            return super.getAutoSizeStepGranularity();
        }
        M8 m8 = this.G;
        if (m8 != null) {
            return Math.round(m8.h.f);
        }
        return -1;
    }

    public int[] getAutoSizeTextAvailableSizes() {
        if (AbstractC4085oc.f) {
            return super.getAutoSizeTextAvailableSizes();
        }
        M8 m8 = this.G;
        return m8 != null ? m8.h.i : new int[0];
    }

    public int getAutoSizeTextType() {
        if (!AbstractC4085oc.f) {
            M8 m8 = this.G;
            if (m8 != null) {
                return m8.h.d;
            }
            return 0;
        } else if (super.getAutoSizeTextType() == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    public int getFirstBaselineToTopHeight() {
        return getPaddingTop() - getPaint().getFontMetricsInt().top;
    }

    public int getLastBaselineToBottomHeight() {
        return getPaddingBottom() + getPaint().getFontMetricsInt().bottom;
    }

    public CharSequence getText() {
        c();
        return super.getText();
    }

    public TextClassifier getTextClassifier() {
        K8 k8;
        if (Build.VERSION.SDK_INT >= 28 || (k8 = this.H) == null) {
            return super.getTextClassifier();
        }
        return k8.a();
    }

    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection onCreateInputConnection = super.onCreateInputConnection(editorInfo);
        AbstractC4182p8.a(onCreateInputConnection, editorInfo, this);
        return onCreateInputConnection;
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        M8 m8 = this.G;
        if (m8 != null && !AbstractC4085oc.f) {
            m8.h.a();
        }
    }

    public void onMeasure(int i, int i2) {
        c();
        super.onMeasure(i, i2);
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        M8 m8 = this.G;
        if (m8 != null && !AbstractC4085oc.f && m8.d()) {
            this.G.h.a();
        }
    }

    public void setAutoSizeTextTypeUniformWithConfiguration(int i, int i2, int i3, int i4) {
        if (AbstractC4085oc.f) {
            super.setAutoSizeTextTypeUniformWithConfiguration(i, i2, i3, i4);
            return;
        }
        M8 m8 = this.G;
        if (m8 != null) {
            m8.g(i, i2, i3, i4);
        }
    }

    public void setAutoSizeTextTypeUniformWithPresetSizes(int[] iArr, int i) {
        if (AbstractC4085oc.f) {
            super.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i);
            return;
        }
        M8 m8 = this.G;
        if (m8 != null) {
            m8.h(iArr, i);
        }
    }

    public void setAutoSizeTextTypeWithDefaults(int i) {
        if (AbstractC4085oc.f) {
            super.setAutoSizeTextTypeWithDefaults(i);
            return;
        }
        M8 m8 = this.G;
        if (m8 != null) {
            m8.i(i);
        }
    }

    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        K7 k7 = this.F;
        if (k7 != null) {
            k7.e();
        }
    }

    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        K7 k7 = this.F;
        if (k7 != null) {
            k7.f(i);
        }
    }

    public void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        M8 m8 = this.G;
        if (m8 != null) {
            m8.b();
        }
    }

    public void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        M8 m8 = this.G;
        if (m8 != null) {
            m8.b();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        M8 m8 = this.G;
        if (m8 != null) {
            m8.b();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        M8 m8 = this.G;
        if (m8 != null) {
            m8.b();
        }
    }

    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(AbstractC0751Mg1.d(this, callback));
    }

    public void setFirstBaselineToTopHeight(int i) {
        if (Build.VERSION.SDK_INT >= 28) {
            super.setFirstBaselineToTopHeight(i);
        } else {
            AbstractC0751Mg1.a(this, i);
        }
    }

    public void setLastBaselineToBottomHeight(int i) {
        if (Build.VERSION.SDK_INT >= 28) {
            super.setLastBaselineToBottomHeight(i);
        } else {
            AbstractC0751Mg1.b(this, i);
        }
    }

    public void setLineHeight(int i) {
        AbstractC0751Mg1.c(this, i);
    }

    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        M8 m8 = this.G;
        if (m8 != null) {
            m8.f(context, i);
        }
    }

    public void setTextClassifier(TextClassifier textClassifier) {
        K8 k8;
        if (Build.VERSION.SDK_INT >= 28 || (k8 = this.H) == null) {
            super.setTextClassifier(textClassifier);
        } else {
            k8.b = textClassifier;
        }
    }

    public void setTextSize(int i, float f) {
        boolean z = AbstractC4085oc.f;
        if (z) {
            super.setTextSize(i, f);
            return;
        }
        M8 m8 = this.G;
        if (m8 != null && !z && !m8.d()) {
            m8.h.f(i, f);
        }
    }

    public void setTypeface(Typeface typeface, int i) {
        Typeface typeface2;
        if (typeface == null || i <= 0) {
            typeface2 = null;
        } else {
            Context context = getContext();
            To1 to1 = Lo1.f8441a;
            if (context != null) {
                typeface2 = Typeface.create(typeface, i);
            } else {
                throw new IllegalArgumentException("Context cannot be null");
            }
        }
        if (typeface2 != null) {
            typeface = typeface2;
        }
        super.setTypeface(typeface, i);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public N8(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        AbstractC0331Fi1.a(context);
        AbstractC1361Wg1.a(this, getContext());
        K7 k7 = new K7(this);
        this.F = k7;
        k7.d(attributeSet, i);
        M8 m8 = new M8(this);
        this.G = m8;
        m8.e(attributeSet, i);
        m8.b();
        this.H = new K8(this);
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(int i, int i2, int i3, int i4) {
        Context context = getContext();
        Drawable drawable = null;
        Drawable a2 = i != 0 ? AbstractC5544x8.a(context, i) : null;
        Drawable a3 = i2 != 0 ? AbstractC5544x8.a(context, i2) : null;
        Drawable a4 = i3 != 0 ? AbstractC5544x8.a(context, i3) : null;
        if (i4 != 0) {
            drawable = AbstractC5544x8.a(context, i4);
        }
        setCompoundDrawablesRelativeWithIntrinsicBounds(a2, a3, a4, drawable);
        M8 m8 = this.G;
        if (m8 != null) {
            m8.b();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(int i, int i2, int i3, int i4) {
        Context context = getContext();
        Drawable drawable = null;
        Drawable a2 = i != 0 ? AbstractC5544x8.a(context, i) : null;
        Drawable a3 = i2 != 0 ? AbstractC5544x8.a(context, i2) : null;
        Drawable a4 = i3 != 0 ? AbstractC5544x8.a(context, i3) : null;
        if (i4 != 0) {
            drawable = AbstractC5544x8.a(context, i4);
        }
        setCompoundDrawablesWithIntrinsicBounds(a2, a3, a4, drawable);
        M8 m8 = this.G;
        if (m8 != null) {
            m8.b();
        }
    }
}
