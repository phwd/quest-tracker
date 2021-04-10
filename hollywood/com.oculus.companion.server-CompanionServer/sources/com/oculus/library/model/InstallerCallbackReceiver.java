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
    private final InstallerCallback mCallback = null;
    private final ResultReceiver mReceiver;

    public int describeContents() {
        return 0;
    }

    /* renamed from: com.oculus.library.model.InstallerCallbackReceiver$1  reason: invalid class name */
    class AnonymousClass1 extends ResultReceiver {
        final /* synthetic */ InstallerCallbackReceiver this$0;

        /* access modifiers changed from: protected */
        public void onReceiveResult(int i, Bundle bundle) {
            bundle.setClassLoader(InstallerResult.class.getClassLoader());
            this.this$0.mCallback.onInstallerResult((InstallerResult) bundle.getParcelable("EXTRA_RESULT"));
        }
    }

    protected InstallerCallbackReceiver(Parcel parcel) {
        this.mReceiver = (ResultReceiver) parcel.readParcelable(ResultReceiver.class.getClassLoader());
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mReceiver, i);
    }
}
