package defpackage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;

/* renamed from: L7  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class L7 extends Button implements AbstractC4085oc {
    public final K7 F;
    public final M8 G;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public L7(Context context, AttributeSet attributeSet, int i) {
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

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(Button.class.getName());
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(Button.class.getName());
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        M8 m8 = this.G;
        if (m8 != null && !AbstractC4085oc.f) {
            m8.h.a();
        }
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

    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(AbstractC0751Mg1.d(this, callback));
    }

    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        M8 m8 = this.G;
        if (m8 != null) {
            m8.f(context, i);
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
}
