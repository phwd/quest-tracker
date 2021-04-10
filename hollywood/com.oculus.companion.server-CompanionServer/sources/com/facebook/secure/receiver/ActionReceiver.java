package com.facebook.secure.receiver;

import android.content.Context;
import android.content.Intent;

public interface ActionReceiver {
    void onReceive(Context context, Intent intent, BroadcastReceiverLike broadcastReceiverLike);
}
