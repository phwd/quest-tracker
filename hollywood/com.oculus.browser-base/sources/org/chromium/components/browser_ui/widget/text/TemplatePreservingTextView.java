package org.chromium.components.browser_ui.widget.text;

import android.content.Context;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TemplatePreservingTextView extends N8 {
    public String I;

    /* renamed from: J  reason: collision with root package name */
    public CharSequence f10828J = "";
    public CharSequence K;

    public TemplatePreservingTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 16842884);
    }

    public final void f(int i, boolean z) {
        CharSequence charSequence;
        if (this.I == null) {
            charSequence = this.f10828J;
        } else if (getMaxLines() != 1 || z) {
            charSequence = String.format(this.I, this.f10828J);
        } else {
            TextPaint paint = getPaint();
            float f = (float) i;
            CharSequence ellipsize = TextUtils.ellipsize(this.f10828J, paint, Math.max(f - paint.measureText(String.format(this.I, "")), 0.0f), TextUtils.TruncateAt.END);
            charSequence = String.format(this.I, ellipsize);
        }
        if (!charSequence.equals(this.K)) {
            this.K = charSequence;
            super.setText(charSequence, TextView.BufferType.SPANNABLE);
        }
    }

    @Override // defpackage.N8
    public void onMeasure(int i, int i2) {
        f((View.MeasureSpec.getSize(i) - getPaddingLeft()) - getPaddingRight(), View.MeasureSpec.getMode(i) == 0);
        super.onMeasure(i, i2);
    }

    @Override // android.widget.TextView
    public void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        if (charSequence == null) {
            charSequence = "";
        }
        this.f10828J = charSequence;
        String str = this.I;
        if (str != null) {
            charSequence = String.format(str, charSequence);
        }
        setContentDescription(charSequence);
        f(0, true);
    }
}
