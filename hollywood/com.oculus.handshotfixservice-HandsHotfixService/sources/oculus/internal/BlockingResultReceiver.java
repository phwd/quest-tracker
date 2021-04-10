package oculus.internal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.util.Log;
import android.util.Pair;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class BlockingResultReceiver extends ResultReceiver {
    private static final String TAG = "BlockingResRec";
    private final Context mContext;
    private final Intent mIntent;
    private volatile CountDownLatch mLatch;
    private final String mReceiverKey;
    private Bundle mResult;
    private int mResultCode;

    public BlockingResultReceiver(Context context, Handler handler, Intent intent, String receiverKey) {
        super(handler);
        this.mContext = context;
        this.mIntent = intent;
        this.mReceiverKey = receiverKey;
    }

    /* access modifiers changed from: protected */
    public void onReceiveResult(int resultCode, Bundle result) {
        this.mResultCode = resultCode;
        this.mResult = result;
        this.mLatch.countDown();
    }

    public Pair<Integer, Bundle> getResult(long timeoutMillis) {
        this.mLatch = new CountDownLatch(1);
        this.mIntent.putExtra(this.mReceiverKey, ResultReceiverUtils.getCrossPackageReceiver(this));
        this.mContext.startService(this.mIntent);
        try {
            if (this.mLatch.await(timeoutMillis, TimeUnit.MILLISECONDS)) {
                return new Pair<>(Integer.valueOf(this.mResultCode), this.mResult);
            }
            return null;
        } catch (InterruptedException e) {
            Log.w(TAG, "Interrupted while waiting for " + this.mIntent);
            Thread.currentThread().interrupt();
            return null;
        }
    }
}
