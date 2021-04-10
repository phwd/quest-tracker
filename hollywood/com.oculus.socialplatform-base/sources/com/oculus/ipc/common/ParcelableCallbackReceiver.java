package com.oculus.ipc.common;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.ResultReceiver;
import com.oculus.ipc.common.ParcelableCallbackReceiver.Callback;

public class ParcelableCallbackReceiver<C extends Callback<R>, R extends Parcelable> {
    public static final String EXTRA_RESULT = "extra_callback_receiver_result";
    public final C mCallback;
    public final ResultReceiver mReceiver;

    public interface Callback<R> {
        void onResult(R r);
    }

    public void sendResult(R r) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_RESULT, r);
        this.mReceiver.send(0, bundle);
    }

    public ResultReceiver getReceiverForIPC() {
        Parcel obtain = Parcel.obtain();
        this.mReceiver.writeToParcel(obtain, 0);
        obtain.setDataPosition(0);
        ResultReceiver resultReceiver = (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(obtain);
        obtain.recycle();
        return resultReceiver;
    }

    public ParcelableCallbackReceiver(Parcelable parcelable) {
        this.mCallback = null;
        this.mReceiver = (ResultReceiver) parcelable;
    }

    public ParcelableCallbackReceiver(C c) {
        if (c != null) {
            this.mCallback = c;
            this.mReceiver = new ResultReceiver(null) {
                /* class com.oculus.ipc.common.ParcelableCallbackReceiver.AnonymousClass1 */

                public void onReceiveResult(int i, Bundle bundle) {
                    bundle.setClassLoader(ParcelableCallbackReceiver.this.mCallback.getClass().getClassLoader());
                    ParcelableCallbackReceiver.this.mCallback.onResult(bundle.getParcelable(ParcelableCallbackReceiver.EXTRA_RESULT));
                }
            };
            return;
        }
        throw new IllegalArgumentException("Callback cannot be null!");
    }
}
