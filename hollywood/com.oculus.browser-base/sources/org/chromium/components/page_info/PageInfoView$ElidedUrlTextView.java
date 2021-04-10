package org.chromium.components.page_info;

import android.content.Context;
import android.text.Layout;
import android.util.AttributeSet;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PageInfoView$ElidedUrlTextView extends N8 {
    public Integer I;

    /* renamed from: J  reason: collision with root package name */
    public Integer f10869J;
    public boolean K = true;
    public int L = -1;
    public int M = Integer.MAX_VALUE;

    public PageInfoView$ElidedUrlTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 16842884);
    }

    public final int f(int i) {
        Layout layout = getLayout();
        int i2 = 0;
        while (i2 < layout.getLineCount() && layout.getLineEnd(i2) < i) {
            i2++;
        }
        return i2 + 1;
    }

    public final boolean g() {
        int intValue = this.f10869J.intValue();
        if (this.K) {
            intValue = this.I.intValue();
        }
        if (intValue == this.M) {
            return false;
        }
        setMaxLines(intValue);
        return true;
    }

    @Override // defpackage.N8
    public void onMeasure(int i, int i2) {
        setMaxLines(Integer.MAX_VALUE);
        super.onMeasure(i, i2);
        String charSequence = getText().toString();
        this.I = Integer.valueOf(f(this.L) + 1);
        int indexOf = charSequence.indexOf(35);
        if (indexOf == -1) {
            indexOf = charSequence.length();
        }
        Integer valueOf = Integer.valueOf(f(indexOf) + 1);
        this.f10869J = valueOf;
        if (valueOf.intValue() < this.I.intValue()) {
            this.I = this.f10869J;
        }
        if (g()) {
            super.onMeasure(i, i2);
        }
    }

    public void setMaxLines(int i) {
        super.setMaxLines(i);
        this.M = i;
    }
}
