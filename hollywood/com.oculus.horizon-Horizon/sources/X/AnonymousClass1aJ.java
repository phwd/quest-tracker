package X;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
/* renamed from: X.1aJ  reason: invalid class name */
public final class AnonymousClass1aJ {
    public final long A00;
    public final AnonymousClass1aI A01;
    public final String A02;
    @Nullable
    public final String A03;
    public final String A04;
    @Nullable
    public final String A05;
    @Nullable
    public final String A06;
    @Nullable
    public final Collection<AnonymousClass1kL> A07;
    @Nullable
    public final HashMap<String, String> A08;

    public final boolean equals(Object obj) {
        if (this != obj) {
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            AnonymousClass1aJ r7 = (AnonymousClass1aJ) obj;
            if (this.A00 != r7.A00 || !this.A04.equals(r7.A04) || !this.A02.equals(r7.A02) || this.A01.getValue() != r7.A01.getValue() || !A00(this.A06, r7.A06) || !A00(this.A03, r7.A03) || !A00(this.A08, r7.A08) || !A00(this.A07, r7.A07) || !A00(this.A05, r7.A05)) {
                return false;
            }
        }
        return true;
    }

    public static boolean A00(@Nullable Object obj, @Nullable Object obj2) {
        if (obj == null) {
            if (obj2 != null) {
                return false;
            }
            return true;
        } else if (obj2 != null) {
            return obj.equals(obj2);
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.A04, this.A02, Integer.valueOf(this.A01.getValue()), Long.valueOf(this.A00), this.A06, this.A03, this.A08, this.A07, this.A05});
    }

    public final String toString() {
        String str;
        HashMap hashMap = new HashMap();
        hashMap.put("mSuccessfulResult", this.A04);
        hashMap.put("mResponse", this.A02);
        hashMap.put("mDedupState", this.A01.name());
        hashMap.put("mUploadId", this.A06);
        hashMap.put("mStatus", this.A03);
        HashMap<String, String> hashMap2 = this.A08;
        if (hashMap2 != null) {
            str = hashMap2.toString();
        } else {
            str = null;
        }
        hashMap.put("xSharingNonces", str);
        return hashMap.toString();
    }

    public AnonymousClass1aJ(String str, String str2, AnonymousClass1aI r4, long j, @Nullable String str3, @Nullable String str4, @Nullable HashMap<String, String> hashMap, @Nullable ArrayList<AnonymousClass1kL> arrayList, @Nullable String str5) {
        Collection<AnonymousClass1kL> unmodifiableCollection;
        this.A04 = str;
        this.A02 = str2;
        this.A01 = r4;
        this.A00 = j;
        this.A06 = str3;
        this.A05 = str5;
        this.A03 = str4;
        this.A08 = hashMap;
        if (arrayList == null) {
            unmodifiableCollection = null;
        } else {
            unmodifiableCollection = Collections.unmodifiableCollection(arrayList);
        }
        this.A07 = unmodifiableCollection;
    }
}
