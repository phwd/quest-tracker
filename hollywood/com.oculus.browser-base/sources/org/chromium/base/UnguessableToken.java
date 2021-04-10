package org.chromium.base;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class UnguessableToken implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C5148up1();
    public final long F;
    public final long G;

    public UnguessableToken(long j, long j2) {
        this.F = j;
        this.G = j2;
    }

    public static UnguessableToken create(long j, long j2) {
        return new UnguessableToken(j, j2);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        UnguessableToken unguessableToken = (UnguessableToken) obj;
        if (unguessableToken.F == this.F && unguessableToken.G == this.G) {
            return true;
        }
        return false;
    }

    public long getHighForSerialization() {
        return this.F;
    }

    public long getLowForSerialization() {
        return this.G;
    }

    public int hashCode() {
        long j = this.G;
        long j2 = this.F;
        return (((int) (j ^ (j >>> 32))) * 31) + ((int) ((j2 >>> 32) ^ j2));
    }

    public final UnguessableToken parcelAndUnparcelForTesting() {
        Parcel obtain = Parcel.obtain();
        writeToParcel(obtain, 0);
        obtain.setDataPosition(0);
        UnguessableToken unguessableToken = (UnguessableToken) CREATOR.createFromParcel(obtain);
        obtain.recycle();
        return unguessableToken;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.F);
        parcel.writeLong(this.G);
    }

    public UnguessableToken(long j, long j2, C5148up1 up1) {
        this.F = j;
        this.G = j2;
    }
}
