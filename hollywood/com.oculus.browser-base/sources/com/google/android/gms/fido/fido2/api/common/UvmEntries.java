package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class UvmEntries extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new XC1();
    public final List F;

    public UvmEntries(List list) {
        this.F = list;
    }

    public boolean equals(Object obj) {
        List list;
        if (!(obj instanceof UvmEntries)) {
            return false;
        }
        UvmEntries uvmEntries = (UvmEntries) obj;
        List list2 = this.F;
        if (list2 == null && uvmEntries.F == null) {
            return true;
        }
        if (list2 == null || (list = uvmEntries.F) == null || !list2.containsAll(list) || !uvmEntries.F.containsAll(this.F)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{new HashSet(this.F)});
    }

    public void writeToParcel(Parcel parcel, int i) {
        int l = AbstractC5758yO0.l(parcel, 20293);
        AbstractC5758yO0.k(parcel, 1, this.F, false);
        AbstractC5758yO0.n(parcel, l);
    }
}
