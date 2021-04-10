package com.facebook.secure.trustedapp.checker;

import android.content.Context;
import android.content.Intent;
import com.facebook.secure.logger.Reporter;

public interface Checker {
    Checker and(Checker checker);

    boolean shouldAllowIntent(Context context, Object obj, Intent intent, Reporter reporter);
}
