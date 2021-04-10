package com.facebook.secure.intent;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ComponentInfo;
import android.content.pm.PackageInfo;
import android.content.pm.ServiceInfo;
import com.facebook.secure.logger.LoggingConfiguration;
import com.facebook.secure.logger.Reporter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

@SuppressLint({"InstanceMethodCanBeStatic"})
public abstract class DifferentKeyIntentScope extends BaseIntentScope {
    public static final String RESOLVE_ACTIVITY = "com.android.internal.app.ResolverActivity";
    private static final String TAG = "DifferentKeyIntentScope";

    /* access modifiers changed from: package-private */
    public abstract boolean verifySignature(Context context, ComponentInfo componentInfo);

    protected DifferentKeyIntentScope(LaunchEnforcement launchEnforcement, Reporter reporter) {
        this(launchEnforcement, reporter, new LoggingConfiguration());
    }

    protected DifferentKeyIntentScope(LaunchEnforcement launchEnforcement, Reporter reporter, LoggingConfiguration loggingConfiguration) {
        super(launchEnforcement, reporter, loggingConfiguration);
    }

    @Override // com.facebook.secure.intent.IntentScope
    @Nullable
    public Intent enforceActivityIntent(Intent intent, Context context, @Nullable String str) {
        if (isExplicitInternalIntent(intent, context)) {
            return null;
        }
        List<ActivityInfo> filterActivityByMatchingIntentFilter = filterActivityByMatchingIntentFilter(intent, context);
        if (filterActivityByMatchingIntentFilter.isEmpty()) {
            filterActivityByMatchingIntentFilter = filterLaunchableActivity(intent, context);
        }
        return enforceIntent(intent, context, filterActivityByMatchingIntentFilter);
    }

    @Override // com.facebook.secure.intent.IntentScope
    @Nullable
    public Intent enforceServiceIntent(Intent intent, Context context, @Nullable String str) {
        if (isExplicitInternalIntent(intent, context)) {
            return null;
        }
        List<ServiceInfo> filterServiceByMatchingIntentFilter = filterServiceByMatchingIntentFilter(intent, context);
        if (filterServiceByMatchingIntentFilter.isEmpty()) {
            filterServiceByMatchingIntentFilter = filterLaunchableService(intent, context);
        }
        return enforceIntent(intent, context, filterServiceByMatchingIntentFilter);
    }

    @Override // com.facebook.secure.intent.IntentScope
    @Nullable
    public Intent enforceReceiverIntent(Intent intent, Context context, @Nullable String str) {
        throw new UnsupportedOperationException();
    }

    @Override // com.facebook.secure.intent.IntentScope
    public List<Intent> enforceBroadcastIntent(Intent intent, Context context, @Nullable String str) {
        throw new UnsupportedOperationException();
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
        ArrayList arrayList = new ArrayList(list.size());
        for (ComponentInfo componentInfo : list) {
            if (!verifySignature(context, componentInfo)) {
                if (isOpenEverywhere()) {
                    arrayList.add(componentInfo);
                    this.reporter.report(TAG, "Non-external/third-party component detected, but allowing because of fail-open: " + intentToString(intent), null);
                } else {
                    this.reporter.report(TAG, "Removed non-external/third-party component: " + intentToString(intent), null);
                }
            } else if (!isResolveActivity(componentInfo)) {
                arrayList.add(componentInfo);
            } else if (isOpenOnNonFbAndException()) {
                arrayList.add(componentInfo);
                this.reporter.report(TAG, "Found potentially dangerous resolver but not removing: " + intentToString(intent), null);
            } else {
                this.reporter.report(TAG, "Removed potentially dangerous resolver: " + intentToString(intent), null);
            }
        }
        if (arrayList.isEmpty()) {
            Reporter reporter = this.reporter;
            reporter.report(TAG, "No matching different-signature components for: " + intentToString(intent), null);
            return null;
        }
        if (arrayList.size() != list.size()) {
            if (arrayList.size() > 1) {
                intent = createChooserIntent(getIntentsForTrustedComponents(arrayList, intent));
            } else {
                ComponentInfo componentInfo2 = (ComponentInfo) arrayList.get(0);
                intent.setComponent(new ComponentName(componentInfo2.packageName, componentInfo2.name));
            }
        }
        return ExternalIntentSanitization.sanitize(intent, this.reporter, isOpenEverywhere());
    }

    private static boolean isResolveActivity(ComponentInfo componentInfo) {
        return componentInfo.applicationInfo != null && RESOLVE_ACTIVITY.equals(componentInfo.applicationInfo.className);
    }
}
