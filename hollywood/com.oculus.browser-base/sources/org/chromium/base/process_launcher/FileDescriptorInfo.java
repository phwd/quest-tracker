package org.chromium.base.process_launcher;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class FileDescriptorInfo implements Parcelable {
    public static final Parcelable.Creator CREATOR = new EP();
    public final int F;
    public final ParcelFileDescriptor G;
    public final long H;
    public final long I;

    public FileDescriptorInfo(int i, ParcelFileDescriptor parcelFileDescriptor, long j, long j2) {
        this.F = i;
        this.G = parcelFileDescriptor;
        this.H = j;
        this.I = j2;
    }

    public int describeContents() {
        return 1;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.F);
        parcel.writeParcelable(this.G, 1);
        parcel.writeLong(this.H);
        parcel.writeLong(this.I);
    }

    public FileDescriptorInfo(Parcel parcel) {
        this.F = parcel.readInt();
        this.G = (ParcelFileDescriptor) parcel.readParcelable(ParcelFileDescriptor.class.getClassLoader());
        this.H = parcel.readLong();
        this.I = parcel.readLong();
    }
}
