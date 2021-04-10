package defpackage;

import android.provider.Settings;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.inputmethod.InputConnection;
import java.util.Objects;
import java.util.regex.Pattern;

/* renamed from: IY0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class IY0 implements AbstractC1286Vc {

    /* renamed from: a  reason: collision with root package name */
    public static final Pattern f8233a = Pattern.compile("[\\p{script=latin}\\p{script=cyrillic}\\p{script=greek}\\p{script=hebrew}\\p{Punct} 0-9]*");
    public final AbstractC1225Uc b;
    public final C2721gd c;
    public final C2721gd d;
    public final C2721gd e;
    public final HY0 f;
    public GY0 g;
    public boolean h = true;
    public boolean i = true;
    public int j;
    public int k;
    public int l;
    public int m;
    public boolean n = true;

    public IY0(AbstractC1225Uc uc) {
        this.b = uc;
        C2721gd gdVar = new C2721gd(uc.getText().toString(), "", uc.getSelectionStart(), uc.getSelectionEnd());
        this.c = gdVar;
        this.d = new C2721gd(gdVar);
        this.e = new C2721gd(gdVar);
        this.f = new HY0(uc);
    }

    @Override // defpackage.AbstractC1286Vc
    public void a(int i2, int i3) {
        C2721gd gdVar = this.c;
        if (gdVar.c != i2 || gdVar.d != i3) {
            gdVar.c = i2;
            gdVar.d = i3;
            if (this.j <= 0) {
                int length = gdVar.f10008a.length();
                if (this.c.c()) {
                    if (i2 > length || i3 > length) {
                        GY0 gy0 = this.g;
                        if (gy0 != null) {
                            gy0.a();
                        }
                    } else {
                        l();
                    }
                }
                n();
                m();
            }
        }
    }

    @Override // defpackage.AbstractC1286Vc
    public String b() {
        return this.c.b();
    }

    @Override // defpackage.AbstractC1286Vc
    public void c(CharSequence charSequence, CharSequence charSequence2) {
        String charSequence3 = charSequence.toString();
        String charSequence4 = charSequence2.toString();
        C2721gd gdVar = this.e;
        int length = charSequence3.length();
        int length2 = charSequence3.length();
        gdVar.f10008a = charSequence3;
        gdVar.b = charSequence4;
        gdVar.c = length;
        gdVar.d = length2;
        GY0 gy0 = this.g;
        if (gy0 != null) {
            gy0.d();
            this.g.e();
        }
    }

    @Override // defpackage.AbstractC1286Vc
    public void d() {
    }

    @Override // defpackage.AbstractC1286Vc
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        GY0 gy0 = this.g;
        if (gy0 == null) {
            return ((AbstractC0981Qc) this.b).a(keyEvent);
        }
        gy0.d();
        if (keyEvent.getKeyCode() == 66 && keyEvent.getAction() == 0) {
            this.g.a();
        }
        boolean a2 = ((AbstractC0981Qc) this.b).a(keyEvent);
        this.g.e();
        return a2;
    }

    @Override // defpackage.AbstractC1286Vc
    public InputConnection e(InputConnection inputConnection) {
        this.l = this.b.getSelectionStart();
        this.m = this.b.getSelectionEnd();
        this.j = 0;
        if (inputConnection == null) {
            this.g = null;
            return null;
        }
        GY0 gy0 = new GY0(this);
        this.g = gy0;
        gy0.setTarget(inputConnection);
        return this.g;
    }

    @Override // defpackage.AbstractC1286Vc
    public String f() {
        return this.c.f10008a;
    }

    @Override // defpackage.AbstractC1286Vc
    public boolean g() {
        return this.n;
    }

    @Override // defpackage.AbstractC1286Vc
    public boolean h() {
        if (this.j == 0 && this.h && this.c.e()) {
            String string = Settings.Secure.getString(((AbstractC0981Qc) this.b).getContext().getContentResolver(), "default_input_method");
            if (string == null) {
                string = "";
            }
            if (!string.contains(".iqqi") && !string.contains("omronsoft") && !string.contains(".iwnn")) {
                if (f8233a.matcher(this.c.f10008a).matches()) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // defpackage.AbstractC1286Vc
    public void i(boolean z) {
        if (!z) {
            C2721gd gdVar = this.d;
            gdVar.c = -1;
            gdVar.d = -1;
            C2721gd gdVar2 = this.c;
            gdVar2.c = -1;
            gdVar2.d = -1;
        }
    }

    @Override // defpackage.AbstractC1286Vc
    public void j(CharSequence charSequence) {
        this.c.h(charSequence.toString(), "", charSequence.length(), charSequence.length());
        HY0 hy0 = this.f;
        hy0.c(true);
        Editable editableText = hy0.f8163a.getEditableText();
        if (hy0.a(editableText) != -1) {
            editableText.removeSpan(hy0.b);
        }
        hy0.b = null;
        this.d.a(this.c);
        this.e.a(this.c);
        if (this.j == 0) {
            n();
        }
    }

    @Override // defpackage.AbstractC1286Vc
    public void k(boolean z) {
        this.i = z;
    }

    public final void l() {
        this.e.b = "";
        this.c.b = "";
        GY0 gy0 = this.g;
        if (gy0 != null) {
            gy0.d();
            this.g.e();
            return;
        }
        this.f.b();
        m();
    }

    /* JADX WARNING: Removed duplicated region for block: B:40:0x00fd  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void m() {
        /*
        // Method dump skipped, instructions count: 357
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.IY0.m():void");
    }

    public final void n() {
        int selectionStart = this.b.getSelectionStart();
        int selectionEnd = this.b.getSelectionEnd();
        if (selectionStart != this.l || selectionEnd != this.m) {
            this.l = selectionStart;
            this.m = selectionEnd;
            Objects.requireNonNull((AbstractC0981Qc) this.b);
        }
    }

    @Override // defpackage.AbstractC1286Vc
    public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        Editable editable;
        int a2;
        HY0 hy0 = this.f;
        C2721gd gdVar = this.c;
        Objects.requireNonNull(hy0);
        if (!(charSequence instanceof Editable) || (a2 = hy0.a((editable = (Editable) charSequence))) == -1) {
            gdVar.f10008a = charSequence.toString();
        } else {
            gdVar.f10008a = editable.subSequence(0, a2).toString();
        }
        if (this.j <= 0) {
            this.h = false;
            l();
        }
    }
}
