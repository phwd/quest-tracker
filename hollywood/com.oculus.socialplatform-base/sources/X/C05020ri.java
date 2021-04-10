package X;

import android.util.Log;
import com.facebook.infer.annotation.Nullsafe;
import java.io.PrintWriter;
import java.io.StringWriter;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0ri  reason: invalid class name and case insensitive filesystem */
public final class C05020ri implements AnonymousClass0J6 {
    public static final C05020ri A01 = new C05020ri();
    public int A00 = 5;

    @Override // X.AnonymousClass0J6
    public final void A2h(String str, String str2) {
        Log.println(6, AnonymousClass006.A09("unknown", ":", str), str2);
    }

    @Override // X.AnonymousClass0J6
    public final void A2i(String str, String str2, Throwable th) {
        A00(6, str, str2, th);
    }

    @Override // X.AnonymousClass0J6
    public final void AB0(String str, String str2) {
        Log.println(5, AnonymousClass006.A09("unknown", ":", str), str2);
    }

    @Override // X.AnonymousClass0J6
    public final void AB1(String str, String str2, Throwable th) {
        A00(5, str, str2, th);
    }

    @Override // X.AnonymousClass0J6
    public final void ABM(String str, String str2) {
        Log.println(6, AnonymousClass006.A09("unknown", ":", str), str2);
    }

    @Override // X.AnonymousClass0J6
    public final void ABN(String str, String str2, Throwable th) {
        A00(6, str, str2, th);
    }

    private void A00(int i, String str, String str2, Throwable th) {
        String obj;
        String A09 = AnonymousClass006.A09("unknown", ":", str);
        if (th == null) {
            obj = "";
        } else {
            StringWriter stringWriter = new StringWriter();
            th.printStackTrace(new PrintWriter(stringWriter));
            obj = stringWriter.toString();
        }
        Log.println(i, A09, AnonymousClass006.A02(str2, '\n', obj));
    }

    @Override // X.AnonymousClass0J6
    public final boolean A64(int i) {
        if (this.A00 <= i) {
            return true;
        }
        return false;
    }

    @Override // X.AnonymousClass0J6
    public final void A6J(int i, String str, String str2) {
        Log.println(i, AnonymousClass006.A09("unknown", ":", str), str2);
    }

    @Override // X.AnonymousClass0J6
    public final int A4S() {
        return this.A00;
    }

    @Override // X.AnonymousClass0J6
    public final void AA2(int i) {
        this.A00 = i;
    }
}
