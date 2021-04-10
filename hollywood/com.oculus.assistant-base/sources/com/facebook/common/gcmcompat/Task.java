package com.facebook.common.gcmcompat;

import X.AnonymousClass08;
import X.C4;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.acra.AppComponentStats;

public abstract class Task implements Parcelable {
    public final String A00;
    public final int A01;
    public final Bundle A02;
    public final String A03;
    public final boolean A04;
    public final boolean A05;
    public final boolean A06;

    public final int describeContents() {
        return 0;
    }

    public static void A00(Bundle bundle) {
        if (bundle != null) {
            Parcel obtain = Parcel.obtain();
            bundle.writeToParcel(obtain, 0);
            if (obtain.dataSize() <= 10240) {
                obtain.recycle();
                for (String str : bundle.keySet()) {
                    Object obj = bundle.get(str);
                    if (obj instanceof Bundle) {
                        A00((Bundle) obj);
                    } else if (!(obj instanceof Integer) && !(obj instanceof Long) && !(obj instanceof Double) && !(obj instanceof String) && !(obj instanceof Boolean)) {
                        throw new IllegalArgumentException("Only the following extra parameter types are supported: Integer, Long, Double, String, Boolean, and nested Bundles with the same restrictions.");
                    }
                }
                return;
            }
            obtain.recycle();
            throw new IllegalArgumentException(AnonymousClass08.A04("Extras exceeding maximum size(10240 bytes): ", Integer.toString(obtain.dataSize())));
        }
    }

    public void A01(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle2.putInt("retry_policy", 0);
        bundle2.putInt("initial_backoff_seconds", 30);
        bundle2.putInt("maximum_backoff_seconds", 3600);
        bundle.putString("tag", this.A03);
        bundle.putBoolean("update_current", this.A06);
        bundle.putBoolean("persisted", this.A04);
        bundle.putString(AppComponentStats.TAG_SERVICE, this.A00);
        bundle.putInt("requiredNetwork", this.A01);
        bundle.putBoolean("requiresCharging", this.A05);
        bundle.putBoolean("requiresIdle", false);
        bundle.putBundle("retryStrategy", bundle2);
        bundle.putBundle("extras", this.A02);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.A00);
        parcel.writeString(this.A03);
        parcel.writeInt(this.A06 ? 1 : 0);
        parcel.writeInt(this.A04 ? 1 : 0);
        parcel.writeInt(this.A01);
        parcel.writeInt(this.A05 ? 1 : 0);
        parcel.writeBundle(this.A02);
    }

    public Task(Parcel parcel) {
        this.A00 = parcel.readString();
        this.A03 = parcel.readString();
        boolean z = false;
        this.A06 = parcel.readInt() == 1;
        this.A04 = parcel.readInt() == 1;
        this.A01 = parcel.readInt();
        this.A05 = parcel.readInt() == 1 ? true : z;
        this.A02 = parcel.readBundle(getClass().getClassLoader());
    }

    public Task(C4 c4) {
        this.A02 = c4.A00;
        this.A01 = 0;
        this.A05 = false;
        this.A00 = c4.A01;
        this.A03 = c4.A02;
        this.A04 = c4.A03;
        this.A06 = c4.A04;
    }
}
