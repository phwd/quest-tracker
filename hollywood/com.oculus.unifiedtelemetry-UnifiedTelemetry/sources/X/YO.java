package X;

import android.util.Log;
import com.facebook.infer.annotation.Nullsafe;
import java.io.PrintWriter;
import java.io.StringWriter;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class YO implements K7 {
    public static final YO A01 = new YO();
    public int A00 = 5;

    @Override // X.K7
    public final void A1q(String str, String str2) {
        Log.println(6, AnonymousClass06.A05("unknown", ":", str), str2);
    }

    @Override // X.K7
    public final void A1r(String str, String str2, Throwable th) {
        A00(6, str, str2, th);
    }

    @Override // X.K7
    public final void A5d(String str, String str2) {
        Log.println(5, AnonymousClass06.A05("unknown", ":", str), str2);
    }

    @Override // X.K7
    public final void A5e(String str, String str2, Throwable th) {
        A00(5, str, str2, th);
    }

    @Override // X.K7
    public final void A5z(String str, String str2) {
        Log.println(6, AnonymousClass06.A05("unknown", ":", str), str2);
    }

    @Override // X.K7
    public final void A60(String str, String str2, Throwable th) {
        A00(6, str, str2, th);
    }

    private void A00(int i, String str, String str2, Throwable th) {
        String obj;
        String A05 = AnonymousClass06.A05("unknown", ":", str);
        if (th == null) {
            obj = "";
        } else {
            StringWriter stringWriter = new StringWriter();
            th.printStackTrace(new PrintWriter(stringWriter));
            obj = stringWriter.toString();
        }
        Log.println(i, A05, AnonymousClass06.A00(str2, '\n', obj));
    }

    @Override // X.K7
    public final boolean A3F(int i) {
        if (this.A00 <= i) {
            return true;
        }
        return false;
    }

    @Override // X.K7
    public final int A2f() {
        return this.A00;
    }

    @Override // X.K7
    public final void A52(int i) {
        this.A00 = i;
    }
}
