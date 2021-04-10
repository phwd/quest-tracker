package com.facebook.secure.intent;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import com.facebook.secure.intent.IntentScope;
import com.facebook.secure.logger.LoggingConfiguration;
import com.facebook.secure.logger.Reporter;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;

public class AnyIntentScope extends BaseIntentScope {
    private static final String TAG = "AnyIntentScope";

    @Override // com.facebook.secure.intent.IntentScope
    @Nullable
    public Intent enforceReceiverIntent(Intent intent, Context context, @Nullable String str) {
        return intent;
    }

    public AnyIntentScope(LaunchEnforcement launchEnforcement, Reporter reporter) {
        this(launchEnforcement, reporter, new LoggingConfiguration());
    }

    public AnyIntentScope(LaunchEnforcement launchEnforcement, Reporter reporter, LoggingConfiguration loggingConfiguration) {
        super(launchEnforcement, reporter, loggingConfiguration);
    }

    @Override // com.facebook.secure.intent.IntentScope
    public IntentScope.ScopeType getScopeType() {
        return IntentScope.ScopeType.ANY;
    }

    @Override // com.facebook.secure.intent.IntentScope
    public Intent enforceActivityIntent(Intent intent, Context context, @Nullable String str) {
        Reporter reporter = this.reporter;
        reporter.report(TAG, "Any_UNSAFE scope used for launching activity: " + intentToString(intent), null);
        return intent;
    }

    @Override // com.facebook.secure.intent.IntentScope
    public Intent enforceServiceIntent(Intent intent, Context context, @Nullable String str) {
        Reporter reporter = this.reporter;
        reporter.report(TAG, "Any_UNSAFE scope used for launching service: " + intentToString(intent), null);
        return intent;
    }

    @Override // com.facebook.secure.intent.IntentScope
    public List<Intent> enforceBroadcastIntent(Intent intent, Context context, @Nullable String str) {
        Reporter reporter = this.reporter;
        reporter.report(TAG, "Any_UNSAFE scope used for sending a broadcast: " + intentToString(intent), null);
        return Collections.singletonList(intent);
    }

    @Override // com.facebook.secure.intent.IntentScope
    public boolean enforceContentProvider(String str, Context context) {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.secure.intent.BaseIntentScope
    public boolean isPackageWithinScope(Context context, PackageInfo packageInfo) {
        throw new UnsupportedOperationException();
    }
}
