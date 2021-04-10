package X;

import android.util.Log;
import com.facebook.infer.annotation.Nullsafe;
import java.io.PrintWriter;
import java.io.StringWriter;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.St  reason: case insensitive filesystem */
public final class C0137St implements KN {
    public static final C0137St A01 = new C0137St();
    public int A00 = 5;

    @Override // X.KN
    public final void A1O(String str, String str2) {
        Log.println(6, AnonymousClass06.A04("unknown", ":", str), str2);
    }

    @Override // X.KN
    public final void A1P(String str, String str2, Throwable th) {
        A00(6, str, str2, th);
    }

    @Override // X.KN
    public final void A3s(String str, String str2) {
        Log.println(5, AnonymousClass06.A04("unknown", ":", str), str2);
    }

    @Override // X.KN
    public final void A3t(String str, String str2, Throwable th) {
        A00(5, str, str2, th);
    }

    @Override // X.KN
    public final void A45(String str, String str2) {
        Log.println(6, AnonymousClass06.A04("unknown", ":", str), str2);
    }

    @Override // X.KN
    public final void A46(String str, String str2, Throwable th) {
        A00(6, str, str2, th);
    }

    private void A00(int i, String str, String str2, Throwable th) {
        String obj;
        String A04 = AnonymousClass06.A04("unknown", ":", str);
        if (th == null) {
            obj = "";
        } else {
            StringWriter stringWriter = new StringWriter();
            th.printStackTrace(new PrintWriter(stringWriter));
            obj = stringWriter.toString();
        }
        Log.println(i, A04, AnonymousClass06.A00(str2, '\n', obj));
    }

    @Override // X.KN
    public final boolean A2C(int i) {
        if (this.A00 <= i) {
            return true;
        }
        return false;
    }

    @Override // X.KN
    public final int A1n() {
        return this.A00;
    }

    @Override // X.KN
    public final void A3Z(int i) {
        this.A00 = i;
    }
}
