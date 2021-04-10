package com.oculus.horizon.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.common.base.Optional;
import java.util.ArrayList;

public class MinimumAppVersion {
    public static final int UNKNOWN_SIZE = -1;
    public ArrayList<MinimumAppVersionEntry> required_versions;

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
        public Optional<String> external_signature = Optional.absent();
        public int minimum_version;
        public Optional<Long> obbSize = Optional.absent();
        public Optional<String> obbUri = Optional.absent();
        public String package_name;
        public Optional<Long> size = Optional.absent();

        public int describeContents() {
            return 0;
        }

        public MinimumAppVersionEntry() {
        }

        public MinimumAppVersionEntry(Parcel parcel) {
            this.package_name = parcel.readString();
            this.download_uri = parcel.readString();
            this.download_version = parcel.readInt();
            this.display_name = parcel.readString();
            this.minimum_version = parcel.readInt();
            long readLong = parcel.readLong();
            this.size = readLong == -1 ? Optional.absent() : Optional.of(Long.valueOf(readLong));
            String readString = parcel.readString();
            this.obbUri = readString == null ? Optional.absent() : Optional.of(readString);
            long readLong2 = parcel.readLong();
            this.obbSize = readLong2 == -1 ? Optional.absent() : Optional.of(Long.valueOf(readLong2));
            String readString2 = parcel.readString();
            this.external_signature = readString2 == null ? Optional.absent() : Optional.of(readString2);
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.package_name);
            parcel.writeString(this.download_uri);
            parcel.writeInt(this.download_version);
            parcel.writeString(this.display_name);
            parcel.writeInt(this.minimum_version);
            long j = -1;
            parcel.writeLong(this.size.isPresent() ? this.size.get().longValue() : -1);
            String str = null;
            parcel.writeString(this.obbUri.isPresent() ? this.obbUri.get() : null);
            if (this.obbSize.isPresent()) {
                j = this.obbSize.get().longValue();
            }
            parcel.writeLong(j);
            if (this.external_signature.isPresent()) {
                str = this.external_signature.get();
            }
            parcel.writeString(str);
        }
    }
}
