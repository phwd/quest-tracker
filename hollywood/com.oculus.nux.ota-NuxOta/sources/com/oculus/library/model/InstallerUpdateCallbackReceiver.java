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
    private final InstallerUpdateCallback mCallback = null;
    private final ResultReceiver mReceiver;

    public int describeContents() {
        return 0;
    }

    /* renamed from: com.oculus.library.model.InstallerUpdateCallbackReceiver$1  reason: invalid class name */
    class AnonymousClass1 extends ResultReceiver {
        final /* synthetic */ InstallerUpdateCallbackReceiver this$0;

        /* access modifiers changed from: protected */
        public void onReceiveResult(int i, Bundle bundle) {
            bundle.setClassLoader(InstallerUpdateResult.class.getClassLoader());
            this.this$0.mCallback.onDownloaderResult((InstallerUpdateResult) bundle.getParcelable("EXTRA_UPDATE_RESULT"));
        }
    }

    protected InstallerUpdateCallbackReceiver(Parcel parcel) {
        this.mReceiver = (ResultReceiver) parcel.readParcelable(ResultReceiver.class.getClassLoader());
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mReceiver, i);
    }
}
