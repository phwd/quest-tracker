package com.facebook.secure.intent;

import android.content.Context;
import android.content.Intent;
import com.facebook.secure.logger.Reporter;
import javax.annotation.Nullable;

public class AnyIntentScope extends BaseIntentScope {
    @Override // com.facebook.secure.intent.IntentScope
    public Intent enforceServiceIntent(Intent intent, Context context, @Nullable String str) {
        Reporter reporter = this.reporter;
        reporter.report("AnyIntentScope", "Any_UNSAFE scope used for launching service: " + intentToString(intent), null);
        return intent;
    }
}
