package X;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;

@RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
/* renamed from: X.0GD  reason: invalid class name */
public class AnonymousClass0GD implements Comparable<AnonymousClass0GD> {
    public final int A00;
    public final int A01;
    public final String A02;
    public final String A03;

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // java.lang.Comparable
    public final int compareTo(@NonNull AnonymousClass0GD r3) {
        AnonymousClass0GD r32 = r3;
        int i = this.A01 - r32.A01;
        if (i == 0) {
            return this.A00 - r32.A00;
        }
        return i;
    }

    public AnonymousClass0GD(int i, int i2, String str, String str2) {
        this.A01 = i;
        this.A00 = i2;
        this.A02 = str;
        this.A03 = str2;
    }
}
