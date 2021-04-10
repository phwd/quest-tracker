package com.oculus.remotewipe;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Locale;

public class WipeRequester implements Parcelable {
    public static final Parcelable.Creator<WipeRequester> CREATOR = new Parcelable.Creator<WipeRequester>() {
        /* class com.oculus.remotewipe.WipeRequester.AnonymousClass1 */

        /* Return type fixed from 'java.lang.Object' to match base method */
        @Override // android.os.Parcelable.Creator
        public final WipeRequester createFromParcel(Parcel parcel) {
            return new WipeRequester(parcel);
        }

        /* Return type fixed from 'java.lang.Object[]' to match base method */
        @Override // android.os.Parcelable.Creator
        public final WipeRequester[] newArray(int i) {
            return new WipeRequester[i];
        }
    };
    public int mAttemptNum;
    public final Source mSource;

    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        return String.format(Locale.US, "Requester: %s, Attempt Num: %d", this.mSource.name(), Integer.valueOf(this.mAttemptNum));
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mSource.name());
        parcel.writeInt(this.mAttemptNum);
    }

    public WipeRequester(Parcel parcel) {
        this.mSource = Source.valueOf(parcel.readString());
        this.mAttemptNum = parcel.readInt();
    }

    public WipeRequester(Source source) {
        this.mSource = source;
        this.mAttemptNum = 1;
    }
}
