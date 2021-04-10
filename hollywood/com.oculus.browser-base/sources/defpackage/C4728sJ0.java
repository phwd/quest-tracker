package defpackage;

import android.text.DynamicLayout;
import android.text.Layout;
import android.text.TextPaint;
import android.text.TextUtils;

/* renamed from: sJ0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4728sJ0 extends DynamicLayout {

    /* renamed from: a  reason: collision with root package name */
    public int f11265a;

    public C4728sJ0(C4898tJ0 tj0, CharSequence charSequence, TextPaint textPaint, int i, Layout.Alignment alignment, float f, float f2, boolean z, int i2) {
        super(charSequence, charSequence, textPaint, i, alignment, f, f2, z, TextUtils.TruncateAt.END, i);
        this.f11265a = i2;
    }

    public int getEllipsisCount(int i) {
        return (i != this.f11265a + -1 || super.getLineCount() + -2 <= i) ? 0 : 3;
    }

    public int getEllipsisStart(int i) {
        if (i != this.f11265a - 1 || super.getLineCount() - 2 <= i) {
            return 0;
        }
        return (getLineEnd(i) - getLineStart(i)) - 1;
    }

    public int getLineCount() {
        int lineCount = super.getLineCount() - 1;
        int i = this.f11265a;
        if (lineCount > i) {
            return i;
        }
        return super.getLineCount() - 1;
    }
}
