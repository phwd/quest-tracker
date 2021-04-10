package X;

import androidx.annotation.Nullable;

/* renamed from: X.2NA  reason: invalid class name */
public class AnonymousClass2NA extends AnonymousClass2OU {
    public final /* synthetic */ AnonymousClass2N9 A00;

    public AnonymousClass2NA(AnonymousClass2N9 r1) {
        this.A00 = r1;
    }

    @Override // X.AnonymousClass2OU
    public final boolean areContentsTheSame(int i, int i2) {
        AnonymousClass2N9 r3 = this.A00;
        Object obj = r3.A04.get(i);
        Object obj2 = r3.A03.get(i2);
        if (obj != null) {
            if (obj2 != null) {
                return r3.A01.A04.A00.areContentsTheSame(obj, obj2);
            }
        } else if (obj2 == null) {
            return true;
        }
        throw new AssertionError();
    }

    @Override // X.AnonymousClass2OU
    public final boolean areItemsTheSame(int i, int i2) {
        AnonymousClass2N9 r3 = this.A00;
        Object obj = r3.A04.get(i);
        Object obj2 = r3.A03.get(i2);
        if (obj != null) {
            if (obj2 != null) {
                return r3.A01.A04.A00.areItemsTheSame(obj, obj2);
            }
            return false;
        } else if (obj2 != null) {
            return false;
        } else {
            return true;
        }
    }

    @Override // X.AnonymousClass2OU
    @Nullable
    public final Object getChangePayload(int i, int i2) {
        AnonymousClass2N9 r2 = this.A00;
        Object obj = r2.A04.get(i);
        Object obj2 = r2.A03.get(i2);
        if (obj != null && obj2 != null) {
            return null;
        }
        throw new AssertionError();
    }

    @Override // X.AnonymousClass2OU
    public final int getNewListSize() {
        return this.A00.A03.size();
    }

    @Override // X.AnonymousClass2OU
    public final int getOldListSize() {
        return this.A00.A04.size();
    }
}
