package com.facebook.secure.intent;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.ComponentInfo;
import com.facebook.annotations.OkToExtend;
import com.facebook.secure.logger.Reporter;
import com.facebook.secure.trustedapp.AppVerifier;
import com.facebook.secure.trustedapp.CallerInfoHelper;
import com.facebook.secure.trustedapp.TrustedApp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;

@OkToExtend
class TargetedAppsIntentScope extends BaseIntentScope {
    private final boolean mAttachCallerInfo;
    private final String mTag;
    private final TrustedApp mTrustedApp;

    @Override // com.facebook.secure.intent.IntentScope
    @Nullable
    public Intent enforceServiceIntent(Intent intent, Context context, @Nullable String str) {
        return enforceIntent(intent, context, str, filterServiceByMatchingIntentFilter(intent, context));
    }

    @Nullable
    private Intent enforceIntent(Intent intent, Context context, @Nullable String str, List<? extends ComponentInfo> list) {
        if (this.mAttachCallerInfo) {
            intent = CallerInfoHelper.attachCallerInfoWithErrorReporting(intent, context, str, this.reporter);
        }
        List<ComponentInfo> filterTargetedAppsComponents = filterTargetedAppsComponents(context, list);
        if (filterTargetedAppsComponents.isEmpty()) {
            this.reporter.report(this.mTag, "No matching packages available.", null);
            return null;
        } else if (this.showChooser && filterTargetedAppsComponents.size() > 1) {
            return createChooserIntent(getIntentsForTrustedComponents(filterTargetedAppsComponents, intent));
        } else {
            ComponentInfo componentInfo = filterTargetedAppsComponents.get(0);
            if (filterTargetedAppsComponents.size() > 1) {
                Iterator<ComponentInfo> it = filterTargetedAppsComponents.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    ComponentInfo next = it.next();
                    if (isDifferentSignatureComponent(next, context)) {
                        componentInfo = next;
                        break;
                    }
                }
            }
            intent.setComponent(new ComponentName(componentInfo.packageName, componentInfo.name));
            return intent;
        }
    }

    private List<ComponentInfo> filterTargetedAppsComponents(Context context, List<? extends ComponentInfo> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (ComponentInfo componentInfo : list) {
            if (isTargetedAppsFailOpen(context, componentInfo.applicationInfo)) {
                arrayList.add(componentInfo);
            }
        }
        return arrayList;
    }

    private boolean isTargetedAppsFailOpen(Context context, ApplicationInfo applicationInfo) {
        String str = applicationInfo.packageName;
        if (this.mTrustedApp.mightMatchTrustedAppScope(str)) {
            try {
                if (this.mTrustedApp.isAppIdentityTrusted(applicationInfo.uid, context)) {
                    return true;
                }
                if (!isOpenEverywhere()) {
                    return false;
                }
                Reporter reporter = this.reporter;
                String str2 = this.mTag;
                reporter.report(str2, str + " is not an app matching the targeted app filter, but fail-open.", null);
                return true;
            } catch (SecurityException e) {
                Reporter reporter2 = this.reporter;
                String str3 = this.mTag;
                reporter2.report(str3, "Unexpected exception in checking trusted app for " + str, e);
                return !isEnforceEverywhere();
            }
        } else if (!isOpenEverywhere()) {
            return false;
        } else {
            Reporter reporter3 = this.reporter;
            String str4 = this.mTag;
            reporter3.report(str4, str + " is not an app matching the targeted app filter, but fail-open.", null);
            return true;
        }
    }

    private boolean isDifferentSignatureComponent(ComponentInfo componentInfo, Context context) {
        try {
            return !AppVerifier.verifySameSignature(context, componentInfo.packageName);
        } catch (SecurityException e) {
            Reporter reporter = this.reporter;
            String str = this.mTag;
            reporter.report(str, "Error verifying the signature for " + componentInfo.packageName, e);
            return false;
        }
    }
}
