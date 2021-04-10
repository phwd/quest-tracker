package X;

import android.util.Log;
import com.facebook.infer.annotation.NullsafeStrict;

@NullsafeStrict
public final class I5 implements K7 {
    public static final I5 A01 = new I5();
    public int A00;

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
    public final void A1q(String str, String str2) {
        Log.e(str, str2);
    }

    @Override // X.K7
    public final void A52(int i) {
        this.A00 = i;
    }

    @Override // X.K7
    public final void A5d(String str, String str2) {
        Log.w(str, str2);
    }

    @Override // X.K7
    public final void A5z(String str, String str2) {
        Log.e(str, str2);
    }

    @Override // X.K7
    public final void A1r(String str, String str2, Throwable th) {
        Log.e(str, str2, th);
    }

    @Override // X.K7
    public final void A5e(String str, String str2, Throwable th) {
        Log.w(str, str2, th);
    }

    @Override // X.K7
    public final void A60(String str, String str2, Throwable th) {
        Log.e(str, str2, th);
    }

    public I5() {
    }

    public I5(int i) {
    }
}
