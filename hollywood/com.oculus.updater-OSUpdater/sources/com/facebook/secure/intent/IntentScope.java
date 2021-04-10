package com.facebook.secure.intent;

import android.content.Context;
import android.content.Intent;
import javax.annotation.Nullable;

public interface IntentScope {
    @Nullable
    Intent enforceServiceIntent(Intent intent, Context context, @Nullable String str);
}
