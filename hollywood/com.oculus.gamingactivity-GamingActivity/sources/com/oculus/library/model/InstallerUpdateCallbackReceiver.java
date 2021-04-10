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
        public InstallerUpdateCallbackReceiver createFromParcel(Parcel in) {
            return new InstallerUpdateCallbackReceiver(in);
        }

        @Override // android.os.Parcelable.Creator
        public InstallerUpdateCallbackReceiver[] newArray(int size) {
            return new InstallerUpdateCallbackReceiver[size];
        }
    };
    public static final String EXTRA_RESULT = "EXTRA_UPDATE_RESULT";
    private final InstallerUpdateCallback mCallback;
    private final ResultReceiver mReceiver;

    public InstallerUpdateCallbackReceiver(InstallerUpdateCallback callback) {
        this.mCallback = callback;
        this.mReceiver = new ResultReceiver(null) {
            /* class com.oculus.library.model.InstallerUpdateCallbackReceiver.AnonymousClass1 */

            /* access modifiers changed from: protected */
            public void onReceiveResult(int resultCode, Bundle resultData) {
                resultData.setClassLoader(InstallerUpdateResult.class.getClassLoader());
                InstallerUpdateCallbackReceiver.this.mCallback.onDownloaderResult((InstallerUpdateResult) resultData.getParcelable(InstallerUpdateCallbackReceiver.EXTRA_RESULT));
            }
        };
    }

    protected InstallerUpdateCallbackReceiver(Parcel in) {
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

    public void sendResult(InstallerUpdateResult result) {
        Bundle resultBundle = new Bundle();
        resultBundle.putParcelable(EXTRA_RESULT, result);
        this.mReceiver.send(0, resultBundle);
    }
}
