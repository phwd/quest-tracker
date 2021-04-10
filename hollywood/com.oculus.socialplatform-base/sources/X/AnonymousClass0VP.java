package X;

import android.util.Log;
import com.facebook.infer.annotation.NullsafeStrict;

@NullsafeStrict
/* renamed from: X.0VP  reason: invalid class name */
public final class AnonymousClass0VP implements AnonymousClass0J6 {
    public static final AnonymousClass0VP A01 = new AnonymousClass0VP();
    public int A00;

    @Override // X.AnonymousClass0J6
    public final boolean A64(int i) {
        if (this.A00 <= i) {
            return true;
        }
        return false;
    }

    @Override // X.AnonymousClass0J6
    public final int A4S() {
        return this.A00;
    }

    @Override // X.AnonymousClass0J6
    public final void AA2(int i) {
        this.A00 = i;
    }

    @Override // X.AnonymousClass0J6
    public final void A2h(String str, String str2) {
        Log.e(str, str2);
    }

    @Override // X.AnonymousClass0J6
    public final void AB0(String str, String str2) {
        Log.w(str, str2);
    }

    @Override // X.AnonymousClass0J6
    public final void ABM(String str, String str2) {
        Log.e(str, str2);
    }

    @Override // X.AnonymousClass0J6
    public final void A2i(String str, String str2, Throwable th) {
        Log.e(str, str2, th);
    }

    @Override // X.AnonymousClass0J6
    public final void A6J(int i, String str, String str2) {
        Log.println(i, str, str2);
    }

    @Override // X.AnonymousClass0J6
    public final void AB1(String str, String str2, Throwable th) {
        Log.w(str, str2, th);
    }

    @Override // X.AnonymousClass0J6
    public final void ABN(String str, String str2, Throwable th) {
        Log.e(str, str2, th);
    }

    public AnonymousClass0VP() {
    }

    public AnonymousClass0VP(int i) {
    }
}
