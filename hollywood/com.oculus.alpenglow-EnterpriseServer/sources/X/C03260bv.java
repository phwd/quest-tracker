package X;

import android.util.Log;
import com.facebook.infer.annotation.Nullsafe;
import java.io.PrintWriter;
import java.io.StringWriter;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0bv  reason: invalid class name and case insensitive filesystem */
public final class C03260bv implements AnonymousClass0Ke {
    public static final C03260bv A01 = new C03260bv();
    public int A00 = 5;

    @Override // X.AnonymousClass0Ke
    public final void A2C(String str, String str2) {
        Log.println(6, AnonymousClass006.A07("unknown", ":", str), str2);
    }

    @Override // X.AnonymousClass0Ke
    public final void A2D(String str, String str2, Throwable th) {
        A00(6, str, str2, th);
    }

    @Override // X.AnonymousClass0Ke
    public final void A8m(String str, String str2) {
        Log.println(5, AnonymousClass006.A07("unknown", ":", str), str2);
    }

    @Override // X.AnonymousClass0Ke
    public final void A8n(String str, String str2, Throwable th) {
        A00(5, str, str2, th);
    }

    @Override // X.AnonymousClass0Ke
    public final void A9G(String str, String str2) {
        Log.println(6, AnonymousClass006.A07("unknown", ":", str), str2);
    }

    @Override // X.AnonymousClass0Ke
    public final void A9H(String str, String str2, Throwable th) {
        A00(6, str, str2, th);
    }

    private void A00(int i, String str, String str2, Throwable th) {
        String stringWriter;
        String A07 = AnonymousClass006.A07("unknown", ":", str);
        if (th == null) {
            stringWriter = "";
        } else {
            StringWriter stringWriter2 = new StringWriter();
            th.printStackTrace(new PrintWriter(stringWriter2));
            stringWriter = stringWriter2.toString();
        }
        Log.println(i, A07, AnonymousClass006.A00(str2, '\n', stringWriter));
    }

    @Override // X.AnonymousClass0Ke
    public final int A44() {
        return this.A00;
    }

    @Override // X.AnonymousClass0Ke
    public final boolean A5V(int i) {
        if (this.A00 <= i) {
            return true;
        }
        return false;
    }

    @Override // X.AnonymousClass0Ke
    public final void A82(int i) {
        this.A00 = i;
    }
}
