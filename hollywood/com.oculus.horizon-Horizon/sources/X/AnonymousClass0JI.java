package X;

import android.util.Log;
import com.facebook.infer.annotation.NullsafeStrict;

@NullsafeStrict
/* renamed from: X.0JI  reason: invalid class name */
public final class AnonymousClass0JI implements AbstractC01090Kc {
    public static final AnonymousClass0JI A01 = new AnonymousClass0JI();
    public int A00;

    @Override // X.AbstractC01090Kc
    public final boolean A54(int i) {
        if (this.A00 <= i) {
            return true;
        }
        return false;
    }

    @Override // X.AbstractC01090Kc
    public final int A3t() {
        return this.A00;
    }

    @Override // X.AbstractC01090Kc
    public final void A8g(int i) {
        this.A00 = i;
    }

    @Override // X.AbstractC01090Kc
    public final void A2K(String str, String str2, Throwable th) {
        Log.e(str, str2, th);
    }

    @Override // X.AbstractC01090Kc
    public final void A5H(int i, String str, String str2) {
        Log.println(i, str, str2);
    }

    @Override // X.AbstractC01090Kc
    public final void A9y(String str, String str2, Throwable th) {
        Log.w(str, str2, th);
    }

    @Override // X.AbstractC01090Kc
    public final void AAL(String str, String str2, Throwable th) {
        Log.e(str, str2, th);
    }

    @Override // X.AbstractC01090Kc
    public final void A2J(String str, String str2) {
        Log.e(str, str2);
    }

    @Override // X.AbstractC01090Kc
    public final void A9x(String str, String str2) {
        Log.w(str, str2);
    }

    @Override // X.AbstractC01090Kc
    public final void AAK(String str, String str2) {
        Log.e(str, str2);
    }

    public AnonymousClass0JI() {
    }

    public AnonymousClass0JI(int i) {
    }
}
