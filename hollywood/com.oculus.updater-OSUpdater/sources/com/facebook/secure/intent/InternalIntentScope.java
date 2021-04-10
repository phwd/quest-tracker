package com.facebook.secure.intent;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.ComponentInfo;
import com.facebook.secure.logger.LoggingConfiguration;
import com.facebook.secure.logger.Reporter;
import com.facebook.secure.trustedapp.CallerInfoHelper;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

public class InternalIntentScope extends BaseIntentScope {
    public InternalIntentScope(LaunchEnforcement launchEnforcement, Reporter reporter, LoggingConfiguration loggingConfiguration) {
        super(launchEnforcement, reporter, loggingConfiguration);
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

    @Nullable
    private Intent enforceIntent(Intent intent, Context context, List<? extends ComponentInfo> list) {
        List<ComponentInfo> filterInternalComponents = filterInternalComponents(context, list);
        if (filterInternalComponents.isEmpty()) {
            this.reporter.report("InternalIntentScope", "No matching internal components", null);
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
        reporter.report("InternalIntentScope", "Detected different package name component but fail open: " + str, null);
        return true;
    }
}
