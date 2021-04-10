package com.facebook.secure.intent;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ComponentInfo;
import android.content.pm.ServiceInfo;
import android.os.Build;
import com.facebook.secure.logger.Reporter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

@SuppressLint({"InstanceMethodCanBeStatic"})
public abstract class DifferentKeyIntentScope extends BaseIntentScope {
    /* access modifiers changed from: package-private */
    public abstract boolean verifySignature(Context context, ComponentInfo componentInfo);

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

    @Nullable
    private Intent enforceIntent(Intent intent, Context context, List<? extends ComponentInfo> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (ComponentInfo componentInfo : list) {
            if (!verifySignature(context, componentInfo)) {
                if (isOpenEverywhere()) {
                    arrayList.add(componentInfo);
                    this.reporter.report("DifferentKeyIntentScope", "Non-external/third-party component detected, but allowing because of fail-open: " + intentToString(intent), null);
                } else {
                    this.reporter.report("DifferentKeyIntentScope", "Removed non-external/third-party component: " + intentToString(intent), null);
                }
            } else if (!isResolveActivity(componentInfo)) {
                arrayList.add(componentInfo);
            } else if (isOpenOnNonFbAndException()) {
                arrayList.add(componentInfo);
                this.reporter.report("DifferentKeyIntentScope", "Found potentially dangerous resolver but not removing: " + intentToString(intent), null);
            } else {
                this.reporter.report("DifferentKeyIntentScope", "Removed potentially dangerous resolver: " + intentToString(intent), null);
            }
        }
        if (!arrayList.isEmpty()) {
            if (arrayList.size() != list.size()) {
                if (arrayList.size() > 1) {
                    intent = createChooserIntent(getIntentsForTrustedComponents(arrayList, intent));
                } else {
                    ComponentInfo componentInfo2 = (ComponentInfo) arrayList.get(0);
                    intent.setComponent(new ComponentName(componentInfo2.packageName, componentInfo2.name));
                }
            }
            return ExternalIntentSanitization.sanitize(intent, this.reporter, isOpenEverywhere());
        } else if (Build.VERSION.SDK_INT < 30 || !list.isEmpty()) {
            Reporter reporter = this.reporter;
            reporter.report("DifferentKeyIntentScope", "No matching different-signature components for: " + intentToString(intent), null);
            return null;
        } else {
            Reporter reporter2 = this.reporter;
            reporter2.report("DifferentKeyIntentScope", "No matching different-signature components for: " + intentToString(intent) + " on API 30+ device. Intent target is not in any PackageFinder aware app, so it's probably a non-FB app. Attempting to proceed.", null);
            return ExternalIntentSanitization.sanitize(intent, this.reporter, isOpenEverywhere());
        }
    }

    private static boolean isResolveActivity(ComponentInfo componentInfo) {
        return componentInfo.applicationInfo != null && "com.android.internal.app.ResolverActivity".equals(componentInfo.applicationInfo.className);
    }
}
