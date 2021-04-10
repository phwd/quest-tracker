package com.facebook.secure.context;

import android.content.Context;
import android.content.Intent;

public interface IntentRewriter {
    Intent rewriteIntent(Intent intent, Context context);
}
