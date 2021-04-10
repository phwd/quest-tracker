package defpackage;

import android.text.Editable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import java.util.Objects;
import org.chromium.chrome.browser.omnibox.UrlBarApi26;

/* renamed from: Tc  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1164Tc implements AbstractC1286Vc {

    /* renamed from: a  reason: collision with root package name */
    public final AbstractC1225Uc f8968a;
    public final C1103Sc b;
    public int c;
    public int d = -1;
    public String e;
    public boolean f;
    public boolean g;
    public boolean h = true;
    public boolean i;
    public boolean j;
    public int k;
    public int l;
    public final InputConnectionWrapper m = new C1042Rc(this, null, true);

    public C1164Tc(AbstractC1225Uc uc) {
        this.f8968a = uc;
        this.b = new C1103Sc(this, null);
    }

    @Override // defpackage.AbstractC1286Vc
    public void a(int i2, int i3) {
        boolean z = true;
        if (this.c == 0) {
            int length = this.f8968a.getText().length();
            if (o(i2, i3)) {
                if (this.f8968a.getText().length() >= length) {
                    z = false;
                }
                m(z, false);
            }
            n();
            return;
        }
        this.f = true;
    }

    @Override // defpackage.AbstractC1286Vc
    public String b() {
        return this.f8968a.getText().toString();
    }

    @Override // defpackage.AbstractC1286Vc
    public void c(CharSequence charSequence, CharSequence charSequence2) {
        int length = charSequence.length();
        String b2 = b();
        CharSequence concat = TextUtils.concat(charSequence, charSequence2);
        this.h = true;
        if (!TextUtils.equals(b2, concat)) {
            if (TextUtils.indexOf(concat, b2) == 0) {
                this.f8968a.append(concat.subSequence(b2.length(), concat.length()));
            } else {
                ((UrlBarApi26) this.f8968a).setText(concat.toString());
            }
        }
        if (!(this.f8968a.getSelectionStart() == length && this.f8968a.getSelectionEnd() == this.f8968a.getText().length())) {
            AbstractC1225Uc uc = this.f8968a;
            uc.setSelection(length, uc.getText().length());
            if (charSequence2.length() != 0) {
                this.f8968a.announceForAccessibility(charSequence2);
            }
        }
        if (TextUtils.isEmpty(charSequence2)) {
            this.b.a();
        } else {
            C1103Sc sc = this.b;
            Editable text = sc.c.f8968a.getText();
            text.removeSpan(sc);
            sc.b = charSequence2;
            sc.f8902a = charSequence;
            text.setSpan(sc, charSequence.length(), text.length(), 33);
        }
        this.h = false;
    }

    @Override // defpackage.AbstractC1286Vc
    public void d() {
        this.j = true;
    }

    @Override // defpackage.AbstractC1286Vc
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return ((AbstractC0981Qc) this.f8968a).a(keyEvent);
    }

    @Override // defpackage.AbstractC1286Vc
    public InputConnection e(InputConnection inputConnection) {
        this.k = this.f8968a.getSelectionStart();
        this.l = this.f8968a.getSelectionEnd();
        this.c = 0;
        this.m.setTarget(inputConnection);
        return this.m;
    }

    @Override // defpackage.AbstractC1286Vc
    public String f() {
        int spanStart = this.f8968a.getText().getSpanStart(this.b);
        if (spanStart < 0) {
            return b();
        }
        return b().substring(0, spanStart);
    }

    @Override // defpackage.AbstractC1286Vc
    public boolean g() {
        return false;
    }

    @Override // defpackage.AbstractC1286Vc
    public boolean h() {
        if (this.i) {
            return false;
        }
        Editable text = this.f8968a.getText();
        int selectionStart = this.f8968a.getSelectionStart();
        int selectionEnd = this.f8968a.getSelectionEnd();
        int spanStart = this.f8968a.getText().getSpanStart(this.b);
        int length = this.f8968a.getText().length();
        if (spanStart < 0) {
            spanStart = length;
        }
        if (!(selectionStart == spanStart && selectionEnd == length) || this.j || this.c != 0 || BaseInputConnection.getComposingSpanEnd(text) != BaseInputConnection.getComposingSpanStart(text)) {
            return false;
        }
        return true;
    }

    @Override // defpackage.AbstractC1286Vc
    public void i(boolean z) {
        if (!z) {
            this.b.a();
        }
    }

    @Override // defpackage.AbstractC1286Vc
    public void j(CharSequence charSequence) {
        C1103Sc sc = this.b;
        if (sc.f8902a != null && sc.b != null) {
            if (this.f8968a.getText().getSpanStart(this.b) < 0) {
                this.b.a();
            } else {
                l();
            }
        }
    }

    @Override // defpackage.AbstractC1286Vc
    public void k(boolean z) {
        this.h = z;
    }

    public final void l() {
        Editable editableText = this.f8968a.getEditableText();
        C1103Sc sc = this.b;
        CharSequence charSequence = sc.f8902a;
        CharSequence charSequence2 = sc.b;
        if (editableText.length() != charSequence2.length() + charSequence.length()) {
            this.b.a();
        } else if (TextUtils.indexOf(this.f8968a.getText(), charSequence) != 0 || TextUtils.indexOf(this.f8968a.getText(), charSequence2, charSequence.length()) != 0) {
            this.b.a();
        }
    }

    public final void m(boolean z, boolean z2) {
        if (this.h) {
            AbstractC1220Ua0.f("AutocompleteModel", "notification ignored", new Object[0]);
            return;
        }
        this.i = z;
        ((UrlBarApi26) this.f8968a).d(z2);
        if (z) {
            AbstractC1225Uc uc = this.f8968a;
            uc.setSelection(uc.getSelectionStart(), this.f8968a.getSelectionStart());
        }
    }

    public final void n() {
        int selectionStart = this.f8968a.getSelectionStart();
        int selectionEnd = this.f8968a.getSelectionEnd();
        if (selectionStart != this.k || selectionEnd != this.l) {
            this.k = selectionStart;
            this.l = selectionEnd;
            Objects.requireNonNull((AbstractC0981Qc) this.f8968a);
        }
    }

    public final boolean o(int i2, int i3) {
        Editable text = this.f8968a.getText();
        int spanStart = text.getSpanStart(this.b);
        int spanEnd = text.getSpanEnd(this.b);
        if (spanStart < 0) {
            return false;
        }
        if (spanStart == i2 && spanEnd == i3) {
            return false;
        }
        C1103Sc sc = this.b;
        CharSequence charSequence = sc.b;
        sc.a();
        if (i3 > spanStart || !TextUtils.equals(charSequence, text.subSequence(spanStart, text.length()))) {
            return true;
        }
        text.delete(spanStart, text.length());
        return true;
    }

    @Override // defpackage.AbstractC1286Vc
    public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        boolean z = i4 == 0;
        if (this.c == 0) {
            m(z, true);
        } else {
            AbstractC1220Ua0.f("AutocompleteModel", "onTextChanged: in batch edit", new Object[0]);
            this.g = z;
        }
        this.j = false;
    }
}
