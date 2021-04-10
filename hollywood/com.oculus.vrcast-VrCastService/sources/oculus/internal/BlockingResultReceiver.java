package oculus.internal;

import android.os.Bundle;
import android.os.ResultReceiver;
import java.util.concurrent.CountDownLatch;

public class BlockingResultReceiver extends ResultReceiver {
    private volatile CountDownLatch mLatch;
    private Bundle mResult;
    private int mResultCode;

    /* access modifiers changed from: protected */
    public void onReceiveResult(int i, Bundle bundle) {
        this.mResultCode = i;
        this.mResult = bundle;
        this.mLatch.countDown();
    }
}
