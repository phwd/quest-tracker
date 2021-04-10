package com.facebook.secure.intent;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.ComponentInfo;
import android.content.pm.PackageInfo;
import com.facebook.secure.intent.IntentScope;
import com.facebook.secure.logger.LoggingConfiguration;
import com.facebook.secure.logger.Reporter;
import com.facebook.secure.trustedapp.AppIdentity;
import com.facebook.secure.trustedapp.AppVerifier;
import com.facebook.secure.trustedapp.CallerInfoHelper;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

@SuppressLint({"InstanceMethodCanBeStatic"})
public class SameKeyIntentScope extends BaseIntentScope {
    private static final String TAG = "SameKeyIntentScope";

    public SameKeyIntentScope(LaunchEnforcement launchEnforcement, Reporter reporter) {
        this(launchEnforcement, reporter, new LoggingConfiguration());
    }

    public SameKeyIntentScope(LaunchEnforcement launchEnforcement, Reporter reporter, LoggingConfiguration loggingConfiguration) {
        this(launchEnforcement, reporter, loggingConfiguration, false);
    }

    public SameKeyIntentScope(LaunchEnforcement launchEnforcement, Reporter reporter, LoggingConfiguration loggingConfiguration, boolean z) {
        super(launchEnforcement, reporter, loggingConfiguration, z);
    }

    @Override // com.facebook.secure.intent.IntentScope
    public IntentScope.ScopeType getScopeType() {
        return IntentScope.ScopeType.SAME_KEY;
    }

    @Override // com.facebook.secure.intent.IntentScope
    @Nullable
    public Intent enforceActivityIntent(Intent intent, Context context, @Nullable String str) {
        Intent attachCallerInfoWithErrorReporting = CallerInfoHelper.attachCallerInfoWithErrorReporting(intent, context, str, this.reporter);
        if (isExplicitInternalIntent(attachCallerInfoWithErrorReporting, context)) {
            return attachCallerInfoWithErrorReporting;
        }
        return enforceIntent(attachCallerInfoWithErrorReporting, context, filterActivityByMatchingIntentFilter(attachCallerInfoWithErrorReporting, context));
    }

    @Override // com.facebook.secure.intent.IntentScope
    @Nullable
    public Intent enforceServiceIntent(Intent intent, Context context, @Nullable String str) {
        Intent attachCallerInfoWithErrorReporting = CallerInfoHelper.attachCallerInfoWithErrorReporting(intent, context, str, this.reporter);
        if (isExplicitInternalIntent(attachCallerInfoWithErrorReporting, context)) {
            return attachCallerInfoWithErrorReporting;
        }
        return enforceIntent(attachCallerInfoWithErrorReporting, context, filterServiceByMatchingIntentFilter(attachCallerInfoWithErrorReporting, context));
    }

    @Override // com.facebook.secure.intent.IntentScope
    @Nullable
    @SuppressLint({"DefaultLocale"})
    public Intent enforceReceiverIntent(Intent intent, Context context, @Nullable String str) {
        int i;
        int i2 = context.getApplicationInfo().uid;
        AppIdentity callerInfo = CallerInfoHelper.getCallerInfo(context, intent);
        if (callerInfo == null) {
            i = -1;
        } else {
            i = callerInfo.getUid();
        }
        if (verifySameSignatureFailOpen(context, i2, i)) {
            return intent;
        }
        String format = String.format("Access denied. Process %d cannot receive broadcasts from %d", Integer.valueOf(i2), Integer.valueOf(i));
        this.reporter.report(TAG, format, null);
        throw new SecurityException(format);
    }

    @Override // com.facebook.secure.intent.IntentScope
    public List<Intent> enforceBroadcastIntent(Intent intent, Context context, @Nullable String str) {
        List<Intent> broadcastIntentsWithinScope = getBroadcastIntentsWithinScope(CallerInfoHelper.attachCallerInfoWithErrorReporting(intent, context, str, this.reporter), context);
        if (broadcastIntentsWithinScope.isEmpty()) {
            this.reporter.report(TAG, "No matching same-key packages", null);
        }
        return broadcastIntentsWithinScope;
    }

    @Override // com.facebook.secure.intent.IntentScope
    public boolean enforceContentProvider(String str, Context context) {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.secure.intent.BaseIntentScope
    public boolean isPackageWithinScope(Context context, PackageInfo packageInfo) {
        return verifySameSignatureFailOpen(context, context.getApplicationInfo(), packageInfo.applicationInfo);
    }

    @Nullable
    private Intent enforceIntent(Intent intent, Context context, List<? extends ComponentInfo> list) {
        List<ComponentInfo> filterSameKeyComponents = filterSameKeyComponents(context, list);
        if (filterSameKeyComponents.isEmpty()) {
            this.reporter.report(TAG, "No matching same-key components.", null);
            return null;
        } else if (this.showChooser && filterSameKeyComponents.size() > 1) {
            return createChooserIntent(getIntentsForTrustedComponents(filterSameKeyComponents, intent));
        } else {
            ComponentInfo componentInfo = filterSameKeyComponents.get(0);
            if (filterSameKeyComponents.size() > 1) {
                for (ComponentInfo componentInfo2 : filterSameKeyComponents) {
                    if (!AppVerifier.verifySamePackageName(context, componentInfo2.packageName)) {
                        componentInfo = componentInfo2;
                    }
                }
            }
            intent.setComponent(new ComponentName(componentInfo.packageName, componentInfo.name));
            return intent;
        }
    }

    private List<ComponentInfo> filterSameKeyComponents(Context context, List<? extends ComponentInfo> list) {
        ArrayList arrayList = new ArrayList(list.size());
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        if (applicationInfo == null) {
            this.reporter.report(TAG, "Current app info is null.", null);
            return arrayList;
        }
        for (ComponentInfo componentInfo : list) {
            ApplicationInfo applicationInfo2 = componentInfo.applicationInfo;
            if (applicationInfo2 == null) {
                this.reporter.report(TAG, "Target app info is null.", null);
            } else if (verifySameSignatureFailOpen(context, applicationInfo, applicationInfo2)) {
                arrayList.add(componentInfo);
            } else if (isOpenEverywhere()) {
                this.reporter.report(TAG, String.format("Different signature of the component but fail-open: current app=%s, target app=%s.", applicationInfo.packageName, applicationInfo2.packageName), null);
                arrayList.add(componentInfo);
            } else {
                this.reporter.report(TAG, String.format("Different signature component blocked: current app=%s, target app=%s.", applicationInfo.packageName, applicationInfo2.packageName), null);
            }
        }
        return arrayList;
    }

    private boolean verifySameSignatureFailOpen(Context context, ApplicationInfo applicationInfo, ApplicationInfo applicationInfo2) {
        try {
            return AppVerifier.verifySameSignature(context, applicationInfo, applicationInfo2);
        } catch (SecurityException e) {
            Reporter reporter = getReporter();
            reporter.report(TAG, "Unexpected exception in verifying signature for: " + applicationInfo2.packageName, e);
            return isOpenEverywhere();
        }
    }

    private boolean verifySameSignatureFailOpen(Context context, int i, int i2) {
        try {
            return AppVerifier.verifySameSignature(context, i, i2);
        } catch (SecurityException e) {
            Reporter reporter = getReporter();
            reporter.report(TAG, "Unexpected exception in verifying signature for: " + i2, e);
            return isOpenEverywhere();
        }
    }
}
