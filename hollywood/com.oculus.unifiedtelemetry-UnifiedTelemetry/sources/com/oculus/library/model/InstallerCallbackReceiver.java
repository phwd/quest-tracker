package com.oculus.library.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.ResultReceiver;

public class InstallerCallbackReceiver implements Parcelable {
    public static final Parcelable.Creator<InstallerCallbackReceiver> CREATOR = new Parcelable.Creator<InstallerCallbackReceiver>() {
        /* class com.oculus.library.model.InstallerCallbackReceiver.AnonymousClass2 */

        /* Return type fixed from 'java.lang.Object' to match base method */
        @Override // android.os.Parcelable.Creator
        public final InstallerCallbackReceiver createFromParcel(Parcel parcel) {
            return new InstallerCallbackReceiver(parcel);
        }

        /* Return type fixed from 'java.lang.Object[]' to match base method */
        @Override // android.os.Parcelable.Creator
        public final InstallerCallbackReceiver[] newArray(int i) {
            return new InstallerCallbackReceiver[i];
        }
    };
    public static final String EXTRA_RESULT = "EXTRA_RESULT";
    public final InstallerCallback mCallback = null;
    public final ResultReceiver mReceiver;

    /* renamed from: com.oculus.library.model.InstallerCallbackReceiver$1  reason: invalid class name */
    public class AnonymousClass1 extends ResultReceiver {
        public final /* synthetic */ InstallerCallbackReceiver this$0;
    }

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mReceiver, i);
    }

    public InstallerCallbackReceiver(Parcel parcel) {
        this.mReceiver = (ResultReceiver) parcel.readParcelable(ResultReceiver.class.getClassLoader());
    }
}
