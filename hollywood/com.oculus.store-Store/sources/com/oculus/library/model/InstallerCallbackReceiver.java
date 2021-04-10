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
        public InstallerCallbackReceiver createFromParcel(Parcel in) {
            return new InstallerCallbackReceiver(in);
        }

        @Override // android.os.Parcelable.Creator
        public InstallerCallbackReceiver[] newArray(int size) {
            return new InstallerCallbackReceiver[size];
        }
    };
    public static final String EXTRA_RESULT = "EXTRA_RESULT";
    private final InstallerCallback mCallback;
    private final ResultReceiver mReceiver;

    public InstallerCallbackReceiver(InstallerCallback callback) {
        this.mCallback = callback;
        this.mReceiver = new ResultReceiver(null) {
            /* class com.oculus.library.model.InstallerCallbackReceiver.AnonymousClass1 */

            /* access modifiers changed from: protected */
            public void onReceiveResult(int resultCode, Bundle resultData) {
                resultData.setClassLoader(InstallerResult.class.getClassLoader());
                InstallerCallbackReceiver.this.mCallback.onInstallerResult((InstallerResult) resultData.getParcelable(InstallerCallbackReceiver.EXTRA_RESULT));
            }
        };
    }

    protected InstallerCallbackReceiver(Parcel in) {
        this.mCallback = null;
        this.mReceiver = (ResultReceiver) in.readParcelable(ResultReceiver.class.getClassLoader());
    }

    public ResultReceiver getReceiver() {
        return this.mReceiver;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.mReceiver, flags);
    }

    public void sendResult(InstallerResult result) {
        Bundle resultBundle = new Bundle();
        resultBundle.putParcelable(EXTRA_RESULT, result);
        this.mReceiver.send(0, resultBundle);
    }
}
