package com.facebook.secure.intent;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.ComponentInfo;
import android.content.pm.PackageInfo;
import android.content.pm.ProviderInfo;
import com.facebook.annotations.OkToExtend;
import com.facebook.secure.intent.IntentScope;
import com.facebook.secure.logger.LoggingConfiguration;
import com.facebook.secure.logger.Reporter;
import com.facebook.secure.trustedapp.AppIdentity;
import com.facebook.secure.trustedapp.AppSignatureHash;
import com.facebook.secure.trustedapp.AppVerifier;
import com.facebook.secure.trustedapp.CallerInfoHelper;
import com.facebook.secure.trustedapp.TrustedApp;
import com.facebook.secure.trustedapp.TrustedAppHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;

@OkToExtend
public class TargetedAppsIntentScope extends BaseIntentScope {
    private static final String sMissingPackageMessage = "No matching packages available.";
    private static final String sTargetedTAG = "TargetedAppsIntentScope";
    private final boolean mAttachCallerInfo;
    private final String mTag;
    private final TrustedApp mTrustedApp;

    public TargetedAppsIntentScope(LaunchEnforcement launchEnforcement, Reporter reporter, Set<AppSignatureHash> set, Set<String> set2, boolean z) {
        this(launchEnforcement, reporter, new LoggingConfiguration(), set, set2, z);
    }

    public TargetedAppsIntentScope(LaunchEnforcement launchEnforcement, Reporter reporter, LoggingConfiguration loggingConfiguration, Set<AppSignatureHash> set, Set<String> set2, boolean z) {
        this(launchEnforcement, reporter, loggingConfiguration, TrustedAppHelper.createTrustedApp(set, set2), sTargetedTAG, false, z);
    }

    public TargetedAppsIntentScope(LaunchEnforcement launchEnforcement, Reporter reporter, LoggingConfiguration loggingConfiguration, TrustedApp trustedApp, boolean z) {
        this(launchEnforcement, reporter, loggingConfiguration, trustedApp, sTargetedTAG, false, z);
    }

    protected TargetedAppsIntentScope(LaunchEnforcement launchEnforcement, Reporter reporter, LoggingConfiguration loggingConfiguration, TrustedApp trustedApp, String str, boolean z, boolean z2) {
        super(launchEnforcement, reporter, loggingConfiguration, z2);
        this.mTag = str;
        this.mTrustedApp = trustedApp;
        this.mAttachCallerInfo = z;
    }

    @Override // com.facebook.secure.intent.IntentScope
    public IntentScope.ScopeType getScopeType() {
        return IntentScope.ScopeType.ANY;
    }

    @Override // com.facebook.secure.intent.IntentScope
    @Nullable
    public Intent enforceActivityIntent(Intent intent, Context context, @Nullable String str) {
        return enforceIntent(intent, context, str, filterActivityByMatchingIntentFilter(intent, context));
    }

    @Override // com.facebook.secure.intent.IntentScope
    @Nullable
    public Intent enforceServiceIntent(Intent intent, Context context, @Nullable String str) {
        return enforceIntent(intent, context, str, filterServiceByMatchingIntentFilter(intent, context));
    }

    @Override // com.facebook.secure.intent.IntentScope
    @Nullable
    public Intent enforceReceiverIntent(Intent intent, Context context, @Nullable String str) {
        int i;
        int i2 = context.getApplicationInfo().uid;
        AppIdentity callerInfo = CallerInfoHelper.getCallerInfo(context, intent);
        if (callerInfo == null) {
            i = -1;
        } else {
            i = callerInfo.getUid();
        }
        if (this.mTrustedApp.isAppIdentityTrusted(i, context)) {
            return intent;
        }
        String format = String.format("Access denied. Process %d cannot receive broadcasts from %d", Integer.valueOf(i2), Integer.valueOf(i));
        this.reporter.report(this.mTag, format, new SecurityException(format));
        return null;
    }

    @Override // com.facebook.secure.intent.IntentScope
    public List<Intent> enforceBroadcastIntent(Intent intent, Context context, @Nullable String str) {
        if (this.mAttachCallerInfo) {
            intent = CallerInfoHelper.attachCallerInfoWithErrorReporting(intent, context, str, this.reporter);
        }
        List<Intent> broadcastIntentsWithinScope = getBroadcastIntentsWithinScope(intent, context);
        if (broadcastIntentsWithinScope.isEmpty()) {
            this.reporter.report(this.mTag, sMissingPackageMessage, null);
        }
        return broadcastIntentsWithinScope;
    }

    @Override // com.facebook.secure.intent.IntentScope
    public boolean enforceContentProvider(String str, Context context) {
        ProviderInfo resolveContentProvider = context.getPackageManager().resolveContentProvider(str, 0);
        if (resolveContentProvider != null) {
            return isTargetedAppsFailOpen(context, resolveContentProvider.applicationInfo);
        }
        return !isEnforceEverywhere();
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.secure.intent.BaseIntentScope
    public boolean isPackageWithinScope(Context context, PackageInfo packageInfo) {
        return isTargetedAppsFailOpen(context, packageInfo.applicationInfo);
    }

    @Nullable
    private Intent enforceIntent(Intent intent, Context context, @Nullable String str, List<? extends ComponentInfo> list) {
        if (this.mAttachCallerInfo) {
            intent = CallerInfoHelper.attachCallerInfoWithErrorReporting(intent, context, str, this.reporter);
        }
        List<ComponentInfo> filterTargetedAppsComponents = filterTargetedAppsComponents(context, list);
        if (filterTargetedAppsComponents.isEmpty()) {
            this.reporter.report(this.mTag, sMissingPackageMessage, null);
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
                reporter.report(str2, str + " is not an app defined the targeted app whitelist but fail-open.", null);
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
            reporter3.report(str4, str + " is not an app defined the targeted app whitelist but fail-open.", null);
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
