package com.oculus.library.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.ResultReceiver;
import com.oculus.appmanager.info.model.InstallerResult;

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
    public final InstallerCallback mCallback;
    public final ResultReceiver mReceiver;

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mReceiver, i);
    }

    public InstallerCallbackReceiver(Parcel parcel) {
        this.mCallback = null;
        this.mReceiver = (ResultReceiver) parcel.readParcelable(ResultReceiver.class.getClassLoader());
    }

    public InstallerCallbackReceiver(InstallerCallback installerCallback) {
        this.mCallback = installerCallback;
        this.mReceiver = new ResultReceiver() {
            /* class com.oculus.library.model.InstallerCallbackReceiver.AnonymousClass1 */

            public final void onReceiveResult(int i, Bundle bundle) {
                bundle.setClassLoader(InstallerResult.class.getClassLoader());
                InstallerCallbackReceiver.this.mCallback.A69((InstallerResult) bundle.getParcelable(InstallerCallbackReceiver.EXTRA_RESULT));
            }
        };
    }
}
