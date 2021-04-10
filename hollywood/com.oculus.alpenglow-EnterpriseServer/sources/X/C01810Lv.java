package X;

import android.util.Log;
import com.facebook.infer.annotation.NullsafeStrict;

@NullsafeStrict
/* renamed from: X.0Lv  reason: invalid class name and case insensitive filesystem */
public final class C01810Lv implements AnonymousClass0Ke {
    public static final C01810Lv A01 = new C01810Lv();
    public int A00;

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
    public final void A2C(String str, String str2) {
        Log.e(str, str2);
    }

    @Override // X.AnonymousClass0Ke
    public final void A82(int i) {
        this.A00 = i;
    }

    @Override // X.AnonymousClass0Ke
    public final void A8m(String str, String str2) {
        Log.w(str, str2);
    }

    @Override // X.AnonymousClass0Ke
    public final void A9G(String str, String str2) {
        Log.e(str, str2);
    }

    @Override // X.AnonymousClass0Ke
    public final void A2D(String str, String str2, Throwable th) {
        Log.e(str, str2, th);
    }

    @Override // X.AnonymousClass0Ke
    public final void A8n(String str, String str2, Throwable th) {
        Log.w(str, str2, th);
    }

    @Override // X.AnonymousClass0Ke
    public final void A9H(String str, String str2, Throwable th) {
        Log.e(str, str2, th);
    }

    public C01810Lv() {
    }

    public C01810Lv(int i) {
    }
}
