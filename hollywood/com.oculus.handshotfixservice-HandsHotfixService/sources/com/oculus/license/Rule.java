package com.oculus.license;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class Rule implements Parcelable {
    public static final Parcelable.Creator<Rule> CREATOR = new Parcelable.Creator<Rule>() {
        /* class com.oculus.license.Rule.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public Rule createFromParcel(Parcel in) {
            return new Rule(in);
        }

        @Override // android.os.Parcelable.Creator
        public Rule[] newArray(int size) {
            return new Rule[size];
        }
    };
    public final List<String> capabilities;
    public final List<FilterConfig> conditions;
    public final Disposition disposition;

    public enum Disposition {
        grant_or_skip,
        deny_or_skip,
        grant_or_deny
    }

    public Rule(Disposition disposition2, List<String> capabilities2, List<FilterConfig> conditions2) {
        this.disposition = disposition2;
        this.capabilities = Collections.unmodifiableList(capabilities2);
        this.conditions = Collections.unmodifiableList(conditions2);
    }

    public Rule(Parcel in) {
        this((Disposition) in.readSerializable(), in.createStringArrayList(), in.createTypedArrayList(FilterConfig.CREATOR));
    }

    public int size() {
        List<String> list = this.capabilities;
        int i = 0;
        int sum = (list != null ? list.stream().mapToInt($$Lambda$iJJA9esWtry25yiadirK2_w1ZNg.INSTANCE).sum() : 0) + 1;
        List<FilterConfig> list2 = this.conditions;
        if (list2 != null) {
            i = list2.stream().mapToInt($$Lambda$8mOKOqXzzDAx6qzRBsxkofEK82A.INSTANCE).sum();
        }
        return sum + i;
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Rule)) {
            return false;
        }
        Rule o = (Rule) other;
        if (this.disposition != o.disposition || !Objects.equals(this.capabilities, o.capabilities) || !Objects.equals(this.conditions, o.conditions)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hash(this.disposition, this.capabilities, this.conditions);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeSerializable(this.disposition);
        out.writeStringList(this.capabilities);
        out.writeTypedList(this.conditions);
    }
}
