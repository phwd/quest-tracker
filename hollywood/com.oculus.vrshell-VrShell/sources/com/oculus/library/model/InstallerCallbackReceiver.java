package com.oculus.library.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.ResultReceiver;
import com.oculus.appmanager.info.model.InstallerResult;

public class InstallerCallbackReceiver implements Parcelable {
    public static final Parcelable.Creator<InstallerCallbackReceiver> CREATOR = new Parcelable.Creator<InstallerCallbackReceiver>() {
        /* class com.oculus.library.model.InstallerCallbackReceiver.AnonymousClass2 */

        @Override // android.os.Parcelable.Creator
        public InstallerCallbackReceiver createFromParcel(Parcel parcel) {
            return new InstallerCallbackReceiver(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public InstallerCallbackReceiver[] newArray(int i) {
            return new InstallerCallbackReceiver[i];
        }
    };
    public static final String EXTRA_RESULT = "EXTRA_RESULT";
    private final InstallerCallback mCallback;
    private final ResultReceiver mReceiver;

    public int describeContents() {
        return 0;
    }

    public InstallerCallbackReceiver(InstallerCallback installerCallback) {
        this.mCallback = installerCallback;
        this.mReceiver = new ResultReceiver(null) {
            /* class com.oculus.library.model.InstallerCallbackReceiver.AnonymousClass1 */

            /* access modifiers changed from: protected */
            public void onReceiveResult(int i, Bundle bundle) {
                bundle.setClassLoader(InstallerResult.class.getClassLoader());
                InstallerCallbackReceiver.this.mCallback.onInstallerResult((InstallerResult) bundle.getParcelable(InstallerCallbackReceiver.EXTRA_RESULT));
            }
        };
    }

    protected InstallerCallbackReceiver(Parcel parcel) {
        this.mCallback = null;
        this.mReceiver = (ResultReceiver) parcel.readParcelable(ResultReceiver.class.getClassLoader());
    }

    public ResultReceiver getReceiver() {
        return this.mReceiver;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mReceiver, i);
    }

    public void sendResult(InstallerResult installerResult) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_RESULT, installerResult);
        this.mReceiver.send(0, bundle);
    }
}
