package defpackage;

import android.text.Editable;
import android.text.style.BackgroundColorSpan;

/* renamed from: HY0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class HY0 {

    /* renamed from: a  reason: collision with root package name */
    public final AbstractC1225Uc f8163a;
    public BackgroundColorSpan b;

    public HY0(AbstractC1225Uc uc) {
        this.f8163a = uc;
    }

    public final int a(Editable editable) {
        BackgroundColorSpan backgroundColorSpan;
        if (editable == null || (backgroundColorSpan = this.b) == null) {
            return -1;
        }
        return editable.getSpanStart(backgroundColorSpan);
    }

    public boolean b() {
        c(true);
        Editable editableText = this.f8163a.getEditableText();
        int a2 = a(editableText);
        if (a2 == -1) {
            return false;
        }
        editableText.removeSpan(this.b);
        editableText.delete(a2, editableText.length());
        this.b = null;
        return true;
    }

    public final void c(boolean z) {
        if (this.f8163a.isFocused()) {
            this.f8163a.setCursorVisible(z);
        }
    }
}
