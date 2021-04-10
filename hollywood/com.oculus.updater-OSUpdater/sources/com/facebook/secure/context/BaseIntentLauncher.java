package com.facebook.secure.context;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import javax.annotation.Nullable;

public abstract class BaseIntentLauncher {
    @Nullable
    public ComponentName launchService(Intent intent, Context context) {
        throw new UnsupportedOperationException();
    }
}
