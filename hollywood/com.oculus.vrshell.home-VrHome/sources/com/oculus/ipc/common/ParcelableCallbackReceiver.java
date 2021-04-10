package com.oculus.ipc.common;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.ResultReceiver;
import com.oculus.ipc.common.ParcelableCallbackReceiver.Callback;

public class ParcelableCallbackReceiver<C extends Callback<R>, R extends Parcelable> {
    private static final String EXTRA_RESULT = "extra_callback_receiver_result";
    private final C mCallback;
    private final ResultReceiver mReceiver;

    public interface Callback<R> {
        void onResult(R r);
    }

    public ParcelableCallbackReceiver(C callback) {
        if (callback == null) {
            throw new IllegalArgumentException("Callback cannot be null!");
        }
        this.mCallback = callback;
        this.mReceiver = new ResultReceiver(null) {
            /* class com.oculus.ipc.common.ParcelableCallbackReceiver.AnonymousClass1 */

            /* access modifiers changed from: protected */
            public void onReceiveResult(int resultCode, Bundle resultData) {
                resultData.setClassLoader(ParcelableCallbackReceiver.this.mCallback.getClass().getClassLoader());
                ParcelableCallbackReceiver.this.mCallback.onResult(resultData.getParcelable(ParcelableCallbackReceiver.EXTRA_RESULT));
            }
        };
    }

    public ParcelableCallbackReceiver(Parcelable parcelableReceiver) {
        this.mCallback = null;
        this.mReceiver = (ResultReceiver) parcelableReceiver;
    }

    public ResultReceiver getReceiverForIPC() {
        Parcel out = Parcel.obtain();
        this.mReceiver.writeToParcel(out, 0);
        out.setDataPosition(0);
        ResultReceiver forSending = (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(out);
        out.recycle();
        return forSending;
    }

    public void sendResult(R result) {
        Bundle resultBundle = new Bundle();
        resultBundle.putParcelable(EXTRA_RESULT, result);
        this.mReceiver.send(0, resultBundle);
    }
}
