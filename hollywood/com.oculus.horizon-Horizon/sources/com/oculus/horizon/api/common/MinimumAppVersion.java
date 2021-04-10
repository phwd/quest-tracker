package com.oculus.horizon.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.common.base.Absent;
import com.google.common.base.Optional;
import java.util.ArrayList;

public class MinimumAppVersion {
    public static final int UNKNOWN_SIZE = -1;
    public final ArrayList<MinimumAppVersionEntry> required_versions;

    public static class MinimumAppVersionEntry implements Parcelable {
        public static final Parcelable.Creator<MinimumAppVersionEntry> CREATOR = new Parcelable.Creator<MinimumAppVersionEntry>() {
            /* class com.oculus.horizon.api.common.MinimumAppVersion.MinimumAppVersionEntry.AnonymousClass1 */

            @Override // android.os.Parcelable.Creator
            public MinimumAppVersionEntry createFromParcel(Parcel parcel) {
                return new MinimumAppVersionEntry(parcel);
            }

            @Override // android.os.Parcelable.Creator
            public MinimumAppVersionEntry[] newArray(int i) {
                return new MinimumAppVersionEntry[i];
            }
        };
        public String display_name;
        public String download_uri;
        public int download_version;
        public Optional<String> external_signature;
        public int minimum_version;
        public Optional<Long> obbSize;
        public Optional<String> obbUri;
        public String package_name;
        public Optional<Long> size;

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            long j;
            String str;
            parcel.writeString(this.package_name);
            parcel.writeString(this.download_uri);
            parcel.writeInt(this.download_version);
            parcel.writeString(this.display_name);
            parcel.writeInt(this.minimum_version);
            Optional<Long> optional = this.size;
            long j2 = -1;
            if (optional.isPresent()) {
                j = optional.get().longValue();
            } else {
                j = -1;
            }
            parcel.writeLong(j);
            Optional<String> optional2 = this.obbUri;
            String str2 = null;
            if (optional2.isPresent()) {
                str = optional2.get();
            } else {
                str = null;
            }
            parcel.writeString(str);
            Optional<Long> optional3 = this.obbSize;
            if (optional3.isPresent()) {
                j2 = optional3.get().longValue();
            }
            parcel.writeLong(j2);
            Optional<String> optional4 = this.external_signature;
            if (optional4.isPresent()) {
                str2 = optional4.get();
            }
            parcel.writeString(str2);
        }

        public MinimumAppVersionEntry() {
            Absent<Object> absent = Absent.INSTANCE;
            this.size = absent;
            this.obbUri = absent;
            this.obbSize = absent;
            this.external_signature = absent;
        }

        public MinimumAppVersionEntry(Parcel parcel) {
            Optional of;
            Optional of2;
            Optional of3;
            Optional optional = Absent.INSTANCE;
            this.size = optional;
            this.obbUri = optional;
            this.obbSize = optional;
            this.external_signature = optional;
            this.package_name = parcel.readString();
            this.download_uri = parcel.readString();
            this.download_version = parcel.readInt();
            this.display_name = parcel.readString();
            this.minimum_version = parcel.readInt();
            long readLong = parcel.readLong();
            if (readLong == -1) {
                of = optional;
            } else {
                of = Optional.of(Long.valueOf(readLong));
            }
            this.size = of;
            String readString = parcel.readString();
            if (readString == null) {
                of2 = optional;
            } else {
                of2 = Optional.of(readString);
            }
            this.obbUri = of2;
            long readLong2 = parcel.readLong();
            if (readLong2 == -1) {
                of3 = optional;
            } else {
                of3 = Optional.of(Long.valueOf(readLong2));
            }
            this.obbSize = of3;
            String readString2 = parcel.readString();
            this.external_signature = readString2 != null ? Optional.of(readString2) : optional;
        }
    }
}
