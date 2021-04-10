package X;

import android.util.Log;

/* renamed from: X.c3  reason: case insensitive filesystem */
public final class C0566c3 implements CM {
    public static final C0566c3 A01 = new C0566c3();
    public int A00;

    @Override // X.CM
    public final boolean A3Y(int i) {
        if (this.A00 <= i) {
            return true;
        }
        return false;
    }

    @Override // X.CM
    public final void A1c(String str, String str2, Throwable th) {
        Log.d(str, str2, th);
    }

    @Override // X.CM
    public final void A1k(String str, String str2, Throwable th) {
        Log.e(str, str2, th);
    }

    @Override // X.CM
    public final int A2h() {
        return this.A00;
    }

    @Override // X.CM
    public final void A3D(String str, String str2, Throwable th) {
        Log.i(str, str2, th);
    }

    @Override // X.CM
    public final void A3k(int i, String str, String str2) {
        Log.println(i, str, str2);
    }

    @Override // X.CM
    public final void A5M(String str, String str2, Throwable th) {
        Log.v(str, str2, th);
    }

    @Override // X.CM
    public final void A5Z(String str, String str2, Throwable th) {
        Log.w(str, str2, th);
    }

    @Override // X.CM
    public final void A61(String str, String str2, Throwable th) {
        Log.e(str, str2, th);
    }

    @Override // X.CM
    public final void A51(int i) {
        this.A00 = i;
    }

    @Override // X.CM
    public final void A1b(String str, String str2) {
        Log.d(str, str2);
    }

    @Override // X.CM
    public final void A1j(String str, String str2) {
        Log.e(str, str2);
    }

    @Override // X.CM
    public final void A3C(String str, String str2) {
        Log.i(str, str2);
    }

    @Override // X.CM
    public final void A5L(String str, String str2) {
        Log.v(str, str2);
    }

    @Override // X.CM
    public final void A5Y(String str, String str2) {
        Log.w(str, str2);
    }

    @Override // X.CM
    public final void A60(String str, String str2) {
        Log.e(str, str2);
    }

    public C0566c3() {
    }

    public C0566c3(int i) {
    }
}
