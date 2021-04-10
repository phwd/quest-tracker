package X;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import java.util.Collections;
import java.util.List;

@RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
/* renamed from: X.0GC  reason: invalid class name */
public final class AnonymousClass0GC {
    @NonNull
    public final String A00;
    @NonNull
    public final String A01;
    @NonNull
    public final String A02;
    @NonNull
    public final List<String> A03;
    @NonNull
    public final List<String> A04;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AnonymousClass0GC) {
            AnonymousClass0GC r4 = (AnonymousClass0GC) obj;
            if (this.A02.equals(r4.A02) && this.A00.equals(r4.A00) && this.A01.equals(r4.A01) && this.A03.equals(r4.A03)) {
                return this.A04.equals(r4.A04);
            }
        }
        return false;
    }

    public final int hashCode() {
        return (((((((this.A02.hashCode() * 31) + this.A00.hashCode()) * 31) + this.A01.hashCode()) * 31) + this.A03.hashCode()) * 31) + this.A04.hashCode();
    }

    public final String toString() {
        return "ForeignKey{referenceTable='" + this.A02 + '\'' + ", onDelete='" + this.A00 + '\'' + ", onUpdate='" + this.A01 + '\'' + ", columnNames=" + this.A03 + ", referenceColumnNames=" + this.A04 + '}';
    }

    public AnonymousClass0GC(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull List<String> list, @NonNull List<String> list2) {
        this.A02 = str;
        this.A00 = str2;
        this.A01 = str3;
        this.A03 = Collections.unmodifiableList(list);
        this.A04 = Collections.unmodifiableList(list2);
    }
}
