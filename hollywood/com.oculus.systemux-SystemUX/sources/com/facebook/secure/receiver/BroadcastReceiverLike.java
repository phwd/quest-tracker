package com.facebook.secure.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

public interface BroadcastReceiverLike {
    void abortBroadcast();

    void clearAbortBroadcast();

    boolean getAbortBroadcast();

    boolean getDebugUnregister();

    int getResultCode();

    String getResultData();

    Bundle getResultExtras(boolean z);

    BroadcastReceiver.PendingResult goAsync();

    boolean isInitialStickyBroadcast();

    boolean isOrderedBroadcast();

    IBinder peekService(Context context, Intent intent);

    void setDebugUnregister(boolean z);

    void setOrderedHint(boolean z);

    void setResult(int i, String str, Bundle bundle);

    void setResultCode(int i);

    void setResultData(String str);

    void setResultExtras(Bundle bundle);
}
