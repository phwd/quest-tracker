package com.facebook.secure.receiver;

import android.content.Context;
import android.content.Intent;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public interface ActionReceiver {
    void onReceive(Context context, Intent intent, BroadcastReceiverLike broadcastReceiverLike);
}
