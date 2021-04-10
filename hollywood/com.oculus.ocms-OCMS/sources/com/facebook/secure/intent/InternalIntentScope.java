package com.facebook.secure.intent;

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
import com.facebook.secure.trustedapp.CallerInfoHelper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;

public class InternalIntentScope extends BaseIntentScope {
    private static final String TAG = "InternalIntentScope";

    public InternalIntentScope(LaunchEnforcement launchEnforcement, Reporter reporter) {
        this(launchEnforcement, reporter, new LoggingConfiguration());
    }

    public InternalIntentScope(LaunchEnforcement launchEnforcement, Reporter reporter, LoggingConfiguration loggingConfiguration) {
        super(launchEnforcement, reporter, loggingConfiguration);
    }

    @Override // com.facebook.secure.intent.IntentScope
    public IntentScope.ScopeType getScopeType() {
        return IntentScope.ScopeType.INTERNAL;
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
    public Intent enforceReceiverIntent(Intent intent, Context context, @Nullable String str) {
        AppIdentity callerInfo = CallerInfoHelper.getCallerInfo(context, intent);
        String packageName = callerInfo != null ? callerInfo.getPackageName() : null;
        String packageName2 = context.getPackageName();
        if (packageName2.equals(packageName)) {
            return intent;
        }
        String format = String.format("Access denied. %s cannot receive broadcasts from %s", packageName2, AppIdentity.printPackageName(callerInfo));
        if (isOpenEverywhere()) {
            Reporter reporter = this.reporter;
            reporter.report(TAG, "Fail-open: " + format, null);
            return intent;
        }
        this.reporter.report(TAG, format, new SecurityException(format));
        return null;
    }

    @Override // com.facebook.secure.intent.IntentScope
    public List<Intent> enforceBroadcastIntent(Intent intent, Context context, @Nullable String str) {
        Intent attachCallerInfoWithErrorReporting = CallerInfoHelper.attachCallerInfoWithErrorReporting(intent, context, str, this.reporter);
        if (isExplicitInternalIntent(attachCallerInfoWithErrorReporting, context)) {
            return Collections.singletonList(attachCallerInfoWithErrorReporting);
        }
        attachCallerInfoWithErrorReporting.setPackage(context.getPackageName());
        return Collections.singletonList(attachCallerInfoWithErrorReporting);
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

    @Nullable
    private Intent enforceIntent(Intent intent, Context context, List<? extends ComponentInfo> list) {
        List<ComponentInfo> filterInternalComponents = filterInternalComponents(context, list);
        if (filterInternalComponents.isEmpty()) {
            this.reporter.report(TAG, "No matching internal components", null);
            return null;
        }
        ComponentInfo componentInfo = filterInternalComponents.get(0);
        intent.setComponent(new ComponentName(componentInfo.packageName, componentInfo.name));
        return intent;
    }

    private List<ComponentInfo> filterInternalComponents(Context context, List<? extends ComponentInfo> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (ComponentInfo componentInfo : list) {
            if (isInternalComponent(componentInfo, context)) {
                arrayList.add(componentInfo);
            }
        }
        return arrayList;
    }

    private boolean isInternalComponent(ComponentInfo componentInfo, Context context) {
        ApplicationInfo applicationInfo = componentInfo.applicationInfo;
        if (applicationInfo == null) {
            return false;
        }
        String str = applicationInfo.packageName;
        if (str.equals(context.getPackageName())) {
            return true;
        }
        if (!isOpenEverywhere()) {
            return false;
        }
        Reporter reporter = this.reporter;
        reporter.report(TAG, "Detected different package name component but fail open: " + str, null);
        return true;
    }
}
