package X;

import android.util.Log;
import com.facebook.infer.annotation.Nullsafe;
import java.io.PrintWriter;
import java.io.StringWriter;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0pl  reason: invalid class name and case insensitive filesystem */
public final class C06750pl implements AbstractC01090Kc {
    public static final C06750pl A01 = new C06750pl();
    public int A00 = 5;

    @Override // X.AbstractC01090Kc
    public final void A2J(String str, String str2) {
        Log.println(6, AnonymousClass006.A07("unknown", ":", str), str2);
    }

    @Override // X.AbstractC01090Kc
    public final void A2K(String str, String str2, Throwable th) {
        A00(6, str, str2, th);
    }

    @Override // X.AbstractC01090Kc
    public final void A9x(String str, String str2) {
        Log.println(5, AnonymousClass006.A07("unknown", ":", str), str2);
    }

    @Override // X.AbstractC01090Kc
    public final void A9y(String str, String str2, Throwable th) {
        A00(5, str, str2, th);
    }

    @Override // X.AbstractC01090Kc
    public final void AAK(String str, String str2) {
        Log.println(6, AnonymousClass006.A07("unknown", ":", str), str2);
    }

    @Override // X.AbstractC01090Kc
    public final void AAL(String str, String str2, Throwable th) {
        A00(6, str, str2, th);
    }

    private void A00(int i, String str, String str2, Throwable th) {
        String obj;
        String A07 = AnonymousClass006.A07("unknown", ":", str);
        if (th == null) {
            obj = "";
        } else {
            StringWriter stringWriter = new StringWriter();
            th.printStackTrace(new PrintWriter(stringWriter));
            obj = stringWriter.toString();
        }
        Log.println(i, A07, AnonymousClass006.A00(str2, '\n', obj));
    }

    @Override // X.AbstractC01090Kc
    public final boolean A54(int i) {
        if (this.A00 <= i) {
            return true;
        }
        return false;
    }

    @Override // X.AbstractC01090Kc
    public final void A5H(int i, String str, String str2) {
        Log.println(i, AnonymousClass006.A07("unknown", ":", str), str2);
    }

    @Override // X.AbstractC01090Kc
    public final int A3t() {
        return this.A00;
    }

    @Override // X.AbstractC01090Kc
    public final void A8g(int i) {
        this.A00 = i;
    }
}
