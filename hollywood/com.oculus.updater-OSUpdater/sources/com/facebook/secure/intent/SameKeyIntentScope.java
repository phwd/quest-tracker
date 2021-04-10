package com.facebook.secure.intent;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.ComponentInfo;
import com.facebook.secure.logger.Reporter;
import com.facebook.secure.trustedapp.AppVerifier;
import com.facebook.secure.trustedapp.CallerInfoHelper;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

@SuppressLint({"InstanceMethodCanBeStatic"})
public class SameKeyIntentScope extends BaseIntentScope {
    @Override // com.facebook.secure.intent.IntentScope
    @Nullable
    public Intent enforceServiceIntent(Intent intent, Context context, @Nullable String str) {
        Intent attachCallerInfoWithErrorReporting = CallerInfoHelper.attachCallerInfoWithErrorReporting(intent, context, str, this.reporter);
        if (isExplicitInternalIntent(attachCallerInfoWithErrorReporting, context)) {
            return attachCallerInfoWithErrorReporting;
        }
        return enforceIntent(attachCallerInfoWithErrorReporting, context, filterServiceByMatchingIntentFilter(attachCallerInfoWithErrorReporting, context));
    }

    @Nullable
    private Intent enforceIntent(Intent intent, Context context, List<? extends ComponentInfo> list) {
        List<ComponentInfo> filterSameKeyComponents = filterSameKeyComponents(context, list);
        if (filterSameKeyComponents.isEmpty()) {
            this.reporter.report("SameKeyIntentScope", "No matching same-key components.", null);
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
            this.reporter.report("SameKeyIntentScope", "Current app info is null.", null);
            return arrayList;
        }
        for (ComponentInfo componentInfo : list) {
            ApplicationInfo applicationInfo2 = componentInfo.applicationInfo;
            if (applicationInfo2 == null) {
                this.reporter.report("SameKeyIntentScope", "Target app info is null.", null);
            } else if (verifySameSignatureFailOpen(context, applicationInfo, applicationInfo2)) {
                arrayList.add(componentInfo);
            } else if (isOpenEverywhere()) {
                this.reporter.report("SameKeyIntentScope", String.format("Different signature of the component but fail-open: current app=%s, target app=%s.", applicationInfo.packageName, applicationInfo2.packageName), null);
                arrayList.add(componentInfo);
            } else {
                this.reporter.report("SameKeyIntentScope", String.format("Different signature component blocked: current app=%s, target app=%s.", applicationInfo.packageName, applicationInfo2.packageName), null);
            }
        }
        return arrayList;
    }

    private boolean verifySameSignatureFailOpen(Context context, ApplicationInfo applicationInfo, ApplicationInfo applicationInfo2) {
        try {
            return AppVerifier.verifySameSignature(context, applicationInfo, applicationInfo2);
        } catch (SecurityException e) {
            Reporter reporter = getReporter();
            reporter.report("SameKeyIntentScope", "Unexpected exception in verifying signature for: " + applicationInfo2.packageName, e);
            return isOpenEverywhere();
        }
    }
}
