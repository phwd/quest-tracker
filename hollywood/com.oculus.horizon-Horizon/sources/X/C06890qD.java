package X;

import android.os.PersistableBundle;

/* renamed from: X.0qD  reason: invalid class name and case insensitive filesystem */
public class C06890qD implements AnonymousClass0HV, AnonymousClass0HW<PersistableBundle> {
    public final PersistableBundle A00;

    @Override // X.AnonymousClass0HV
    public final int A3e(String str, int i) {
        return this.A00.getInt(str, i);
    }

    @Override // X.AnonymousClass0HV
    public final String A4R(String str, String str2) {
        return this.A00.getString(str, str2);
    }

    @Override // X.AnonymousClass0HW
    public final void A7j(String str, int i) {
        this.A00.putInt(str, i);
    }

    @Override // X.AnonymousClass0HW
    public final void A7k(String str, String str2) {
        this.A00.putString(str, str2);
    }

    public C06890qD(PersistableBundle persistableBundle) {
        this.A00 = persistableBundle;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass0HW
    public final PersistableBundle A9V() {
        return this.A00;
    }
}
