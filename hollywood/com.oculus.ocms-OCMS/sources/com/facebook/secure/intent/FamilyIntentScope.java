package com.facebook.secure.intent;

import android.content.Context;
import android.content.Intent;
import com.facebook.secure.intent.IntentScope;
import com.facebook.secure.logger.LoggingConfiguration;
import com.facebook.secure.logger.Reporter;
import com.facebook.secure.trustedapp.TrustedApp;
import com.facebook.secure.trustedapp.TrustedAppHelper;
import java.util.List;
import javax.annotation.Nullable;

public class FamilyIntentScope extends TargetedAppsIntentScope {
    private static final String TAG = "FamilyIntentScope";

    @Override // com.facebook.secure.intent.TargetedAppsIntentScope, com.facebook.secure.intent.IntentScope
    @Nullable
    public /* bridge */ /* synthetic */ Intent enforceActivityIntent(Intent intent, Context context, @Nullable String str) {
        return super.enforceActivityIntent(intent, context, str);
    }

    @Override // com.facebook.secure.intent.TargetedAppsIntentScope, com.facebook.secure.intent.IntentScope
    public /* bridge */ /* synthetic */ List enforceBroadcastIntent(Intent intent, Context context, @Nullable String str) {
        return super.enforceBroadcastIntent(intent, context, str);
    }

    @Override // com.facebook.secure.intent.TargetedAppsIntentScope, com.facebook.secure.intent.IntentScope
    public /* bridge */ /* synthetic */ boolean enforceContentProvider(String str, Context context) {
        return super.enforceContentProvider(str, context);
    }

    @Override // com.facebook.secure.intent.TargetedAppsIntentScope, com.facebook.secure.intent.IntentScope
    @Nullable
    public /* bridge */ /* synthetic */ Intent enforceReceiverIntent(Intent intent, Context context, @Nullable String str) {
        return super.enforceReceiverIntent(intent, context, str);
    }

    @Override // com.facebook.secure.intent.TargetedAppsIntentScope, com.facebook.secure.intent.IntentScope
    @Nullable
    public /* bridge */ /* synthetic */ Intent enforceServiceIntent(Intent intent, Context context, @Nullable String str) {
        return super.enforceServiceIntent(intent, context, str);
    }

    @Override // com.facebook.secure.intent.TargetedAppsIntentScope
    public /* bridge */ /* synthetic */ TrustedApp getTrustedApp() {
        return super.getTrustedApp();
    }

    public FamilyIntentScope(LaunchEnforcement launchEnforcement, Reporter reporter) {
        this(launchEnforcement, reporter, new LoggingConfiguration());
    }

    public FamilyIntentScope(LaunchEnforcement launchEnforcement, Reporter reporter, LoggingConfiguration loggingConfiguration) {
        this(launchEnforcement, reporter, loggingConfiguration, false);
    }

    public FamilyIntentScope(LaunchEnforcement launchEnforcement, Reporter reporter, LoggingConfiguration loggingConfiguration, boolean z) {
        this(launchEnforcement, reporter, loggingConfiguration, z, TrustedAppHelper.createAllFBFamilyTrustedApp());
    }

    public FamilyIntentScope(LaunchEnforcement launchEnforcement, Reporter reporter, LoggingConfiguration loggingConfiguration, boolean z, TrustedApp trustedApp) {
        super(launchEnforcement, reporter, loggingConfiguration, trustedApp, TAG, true, z);
    }

    @Override // com.facebook.secure.intent.TargetedAppsIntentScope, com.facebook.secure.intent.IntentScope
    public IntentScope.ScopeType getScopeType() {
        return IntentScope.ScopeType.FAMILY;
    }
}
