package X;

import android.util.Log;
import com.facebook.infer.annotation.NullsafeStrict;

@NullsafeStrict
/* renamed from: X.Bg  reason: case insensitive filesystem */
public final class C0034Bg implements KN {
    public static final C0034Bg A01 = new C0034Bg();
    public int A00;

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
    public final void A1O(String str, String str2) {
        Log.e(str, str2);
    }

    @Override // X.KN
    public final void A3Z(int i) {
        this.A00 = i;
    }

    @Override // X.KN
    public final void A3s(String str, String str2) {
        Log.w(str, str2);
    }

    @Override // X.KN
    public final void A45(String str, String str2) {
        Log.e(str, str2);
    }

    @Override // X.KN
    public final void A1P(String str, String str2, Throwable th) {
        Log.e(str, str2, th);
    }

    @Override // X.KN
    public final void A3t(String str, String str2, Throwable th) {
        Log.w(str, str2, th);
    }

    @Override // X.KN
    public final void A46(String str, String str2, Throwable th) {
        Log.e(str, str2, th);
    }

    public C0034Bg() {
    }

    public C0034Bg(int i) {
    }
}
