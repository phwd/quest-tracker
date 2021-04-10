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
        public Rule createFromParcel(Parcel parcel) {
            return new Rule(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public Rule[] newArray(int i) {
            return new Rule[i];
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

    public int describeContents() {
        return 0;
    }

    public Rule(Disposition disposition2, List<String> list, List<FilterConfig> list2) {
        this.disposition = disposition2;
        this.capabilities = Collections.unmodifiableList(list);
        this.conditions = Collections.unmodifiableList(list2);
    }

    public Rule(Parcel parcel) {
        this((Disposition) parcel.readSerializable(), parcel.createStringArrayList(), parcel.createTypedArrayList(FilterConfig.CREATOR));
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Rule)) {
            return false;
        }
        Rule rule = (Rule) obj;
        return this.disposition == rule.disposition && Objects.equals(this.capabilities, rule.capabilities) && Objects.equals(this.conditions, rule.conditions);
    }

    public int hashCode() {
        return Objects.hash(this.disposition, this.capabilities, this.conditions);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeSerializable(this.disposition);
        parcel.writeStringList(this.capabilities);
        parcel.writeTypedList(this.conditions);
    }
}
