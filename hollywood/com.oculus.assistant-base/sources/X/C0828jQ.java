package X;

import android.util.Log;
import com.facebook.assistant.oacr.OacrConstants;
import java.io.PrintWriter;
import java.io.StringWriter;

/* renamed from: X.jQ  reason: case insensitive filesystem */
public final class C0828jQ implements CM {
    public static final C0828jQ A01 = new C0828jQ();
    public int A00 = 5;

    @Override // X.CM
    public final void A1b(String str, String str2) {
        A00(3, str, str2);
    }

    @Override // X.CM
    public final void A1c(String str, String str2, Throwable th) {
        A01(3, str, str2, th);
    }

    @Override // X.CM
    public final void A1j(String str, String str2) {
        A00(6, str, str2);
    }

    @Override // X.CM
    public final void A1k(String str, String str2, Throwable th) {
        A01(6, str, str2, th);
    }

    @Override // X.CM
    public final void A3C(String str, String str2) {
        A00(4, str, str2);
    }

    @Override // X.CM
    public final void A3D(String str, String str2, Throwable th) {
        A01(4, str, str2, th);
    }

    @Override // X.CM
    public final void A5L(String str, String str2) {
        A00(2, str, str2);
    }

    @Override // X.CM
    public final void A5M(String str, String str2, Throwable th) {
        A01(2, str, str2, th);
    }

    @Override // X.CM
    public final void A5Y(String str, String str2) {
        A00(5, str, str2);
    }

    @Override // X.CM
    public final void A5Z(String str, String str2, Throwable th) {
        A01(5, str, str2, th);
    }

    @Override // X.CM
    public final void A60(String str, String str2) {
        A00(6, str, str2);
    }

    @Override // X.CM
    public final void A61(String str, String str2, Throwable th) {
        A01(6, str, str2, th);
    }

    private void A00(int i, String str, String str2) {
        Log.println(i, AnonymousClass08.A05("unknown", ":", str), str2);
    }

    private void A01(int i, String str, String str2, Throwable th) {
        String obj;
        String A05 = AnonymousClass08.A05("unknown", ":", str);
        StringBuilder sb = new StringBuilder();
        sb.append(str2);
        sb.append('\n');
        if (th == null) {
            obj = OacrConstants.AUTO_SPEECH_DOMAIN;
        } else {
            StringWriter stringWriter = new StringWriter();
            th.printStackTrace(new PrintWriter(stringWriter));
            obj = stringWriter.toString();
        }
        sb.append(obj);
        Log.println(i, A05, sb.toString());
    }

    @Override // X.CM
    public final boolean A3Y(int i) {
        if (this.A00 <= i) {
            return true;
        }
        return false;
    }

    @Override // X.CM
    public final int A2h() {
        return this.A00;
    }

    @Override // X.CM
    public final void A3k(int i, String str, String str2) {
        A00(i, str, str2);
    }

    @Override // X.CM
    public final void A51(int i) {
        this.A00 = i;
    }
}
