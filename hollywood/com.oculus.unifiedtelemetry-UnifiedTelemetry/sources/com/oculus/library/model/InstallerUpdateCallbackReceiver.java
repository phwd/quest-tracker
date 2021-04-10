package com.oculus.library.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.ResultReceiver;

public class InstallerUpdateCallbackReceiver implements Parcelable {
    public static final Parcelable.Creator<InstallerUpdateCallbackReceiver> CREATOR = new Parcelable.Creator<InstallerUpdateCallbackReceiver>() {
        /* class com.oculus.library.model.InstallerUpdateCallbackReceiver.AnonymousClass2 */

        /* Return type fixed from 'java.lang.Object' to match base method */
        @Override // android.os.Parcelable.Creator
        public final InstallerUpdateCallbackReceiver createFromParcel(Parcel parcel) {
            return new InstallerUpdateCallbackReceiver(parcel);
        }

        /* Return type fixed from 'java.lang.Object[]' to match base method */
        @Override // android.os.Parcelable.Creator
        public final InstallerUpdateCallbackReceiver[] newArray(int i) {
            return new InstallerUpdateCallbackReceiver[i];
        }
    };
    public static final String EXTRA_RESULT = "EXTRA_UPDATE_RESULT";
    public final InstallerUpdateCallback mCallback = null;
    public final ResultReceiver mReceiver;

    /* renamed from: com.oculus.library.model.InstallerUpdateCallbackReceiver$1  reason: invalid class name */
    public class AnonymousClass1 extends ResultReceiver {
        public final /* synthetic */ InstallerUpdateCallbackReceiver this$0;
    }

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mReceiver, i);
    }

    public InstallerUpdateCallbackReceiver(Parcel parcel) {
        this.mReceiver = (ResultReceiver) parcel.readParcelable(ResultReceiver.class.getClassLoader());
    }
}
