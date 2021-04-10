package com.oculus.library.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.ResultReceiver;
import com.oculus.appmanager.info.model.InstallerUpdateResult;

public class InstallerUpdateCallbackReceiver implements Parcelable {
    public static final Parcelable.Creator<InstallerUpdateCallbackReceiver> CREATOR = new Parcelable.Creator<InstallerUpdateCallbackReceiver>() {
        /* class com.oculus.library.model.InstallerUpdateCallbackReceiver.AnonymousClass2 */

        @Override // android.os.Parcelable.Creator
        public InstallerUpdateCallbackReceiver createFromParcel(Parcel parcel) {
            return new InstallerUpdateCallbackReceiver(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public InstallerUpdateCallbackReceiver[] newArray(int i) {
            return new InstallerUpdateCallbackReceiver[i];
        }
    };
    public static final String EXTRA_RESULT = "EXTRA_UPDATE_RESULT";
    public final InstallerUpdateCallback mCallback;
    public final ResultReceiver mReceiver;

    public int describeContents() {
        return 0;
    }

    public ResultReceiver getReceiver() {
        return this.mReceiver;
    }

    public void sendResult(InstallerUpdateResult installerUpdateResult) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_RESULT, installerUpdateResult);
        this.mReceiver.send(0, bundle);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mReceiver, i);
    }

    public InstallerUpdateCallbackReceiver(Parcel parcel) {
        this.mCallback = null;
        this.mReceiver = (ResultReceiver) parcel.readParcelable(ResultReceiver.class.getClassLoader());
    }

    public InstallerUpdateCallbackReceiver(InstallerUpdateCallback installerUpdateCallback) {
        this.mCallback = installerUpdateCallback;
        this.mReceiver = new ResultReceiver(null) {
            /* class com.oculus.library.model.InstallerUpdateCallbackReceiver.AnonymousClass1 */

            public void onReceiveResult(int i, Bundle bundle) {
                bundle.setClassLoader(InstallerUpdateResult.class.getClassLoader());
                InstallerUpdateCallbackReceiver.this.mCallback.onDownloaderResult((InstallerUpdateResult) bundle.getParcelable(InstallerUpdateCallbackReceiver.EXTRA_RESULT));
            }
        };
    }
}
