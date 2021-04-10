package com.facebook.secure.intent;

import android.content.Context;
import android.content.Intent;

public interface IntentScope {
    Intent enforceReceiverIntent(Intent intent, Context context);
}
