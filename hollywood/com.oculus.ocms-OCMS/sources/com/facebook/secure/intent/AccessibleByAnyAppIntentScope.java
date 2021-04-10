package com.facebook.secure.intent;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ComponentInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.os.Build;
import com.facebook.annotations.DoNotOptimize;
import com.facebook.secure.intent.IntentScope;
import com.facebook.secure.intentparser.IntentParser;
import com.facebook.secure.logger.LoggingConfiguration;
import com.facebook.secure.logger.Reporter;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import org.json.JSONException;

public class AccessibleByAnyAppIntentScope extends BaseIntentScope {
    private static final String TAG = "AccessibleByAnyAppIntentScope";

    public AccessibleByAnyAppIntentScope(LaunchEnforcement launchEnforcement, Reporter reporter) {
        this(launchEnforcement, reporter, new LoggingConfiguration());
    }

    public AccessibleByAnyAppIntentScope(LaunchEnforcement launchEnforcement, Reporter reporter, LoggingConfiguration loggingConfiguration) {
        super(launchEnforcement, reporter, loggingConfiguration);
    }

    @Override // com.facebook.secure.intent.IntentScope
    public IntentScope.ScopeType getScopeType() {
        return IntentScope.ScopeType.ACCESSIBLE_BY_ANY_APP;
    }

    @Override // com.facebook.secure.intent.IntentScope
    @Nullable
    public Intent enforceActivityIntent(Intent intent, Context context, @Nullable String str) {
        List<ActivityInfo> filterActivityByMatchingIntentFilter = filterActivityByMatchingIntentFilter(intent, context);
        Iterator<ActivityInfo> it = filterActivityByMatchingIntentFilter.iterator();
        boolean z = false;
        while (it.hasNext()) {
            ActivityInfo next = it.next();
            if (!isEndpointAccessibleByAnyApp(intent, context, next, next.permission)) {
                z = true;
                it.remove();
            }
        }
        return enforceIntent(intent, filterActivityByMatchingIntentFilter, z);
    }

    @Override // com.facebook.secure.intent.IntentScope
    @Nullable
    public Intent enforceServiceIntent(Intent intent, Context context, @Nullable String str) {
        List<ServiceInfo> filterServiceByMatchingIntentFilter = filterServiceByMatchingIntentFilter(intent, context);
        Iterator<ServiceInfo> it = filterServiceByMatchingIntentFilter.iterator();
        boolean z = false;
        while (it.hasNext()) {
            ServiceInfo next = it.next();
            if (!isEndpointAccessibleByAnyApp(intent, context, next, next.permission)) {
                z = true;
                it.remove();
            }
        }
        return enforceIntent(intent, filterServiceByMatchingIntentFilter, z);
    }

    @Nullable
    private Intent enforceIntent(Intent intent, List<? extends ComponentInfo> list, boolean z) {
        if (list.isEmpty()) {
            this.reporter.report(TAG, "No matching public components.", null);
            return null;
        } else if (!z) {
            return ExternalIntentSanitization.sanitize(intent, this.reporter, isOpenEverywhere());
        } else {
            if (list.size() > 1) {
                return createChooserIntent(getIntentsForTrustedComponents(list, ExternalIntentSanitization.sanitize(intent, this.reporter, isOpenEverywhere())));
            }
            ComponentInfo componentInfo = (ComponentInfo) list.get(0);
            intent.setComponent(new ComponentName(componentInfo.packageName, componentInfo.name));
            return ExternalIntentSanitization.sanitize(intent, this.reporter, isOpenEverywhere());
        }
    }

    private boolean isEndpointAccessibleByAnyApp(Intent intent, Context context, ComponentInfo componentInfo, @Nullable String str) {
        IntentParser.ParsedIntent parsedIntent;
        String str2;
        int i;
        if (componentInfo.exported) {
            if (str == null) {
                return true;
            }
            try {
                int i2 = context.getPackageManager().getPermissionInfo(str, 0).protectionLevel;
                if (isOnOrAboveApi23()) {
                    i = Api23Utils.getProtectionFlagPrivileged();
                } else {
                    i = 3;
                }
                if (!((i2 & 2) == 2 || (i2 & i) == i)) {
                    return true;
                }
            } catch (PackageManager.NameNotFoundException e) {
                if (!isOpenOnNonFbAndException()) {
                    return false;
                }
                this.reporter.report(TAG, String.format("Error checking permission for %s/%s but fail-open: exported=%s, permission=%s.", componentInfo.packageName, componentInfo.name, Boolean.valueOf(componentInfo.exported), str), e);
                return true;
            }
        }
        if (!isOpenEverywhere()) {
            return false;
        }
        try {
            parsedIntent = IntentParser.parseIntent(intent);
            str2 = null;
        } catch (JSONException e2) {
            str2 = e2.getMessage();
            parsedIntent = null;
        }
        if (parsedIntent == null) {
            this.reporter.report(TAG, String.format("Fail-open: allowing non-public component %s/%s: exported=%s, permission=%s for context package %s with error in intent parser %s", componentInfo.packageName, componentInfo.name, Boolean.valueOf(componentInfo.exported), str, context.getPackageName(), str2), null);
        } else {
            Reporter reporter = this.reporter;
            Object[] objArr = new Object[6];
            objArr[0] = componentInfo.packageName;
            objArr[1] = componentInfo.name;
            objArr[2] = Boolean.valueOf(componentInfo.exported);
            objArr[3] = str;
            objArr[4] = context.getPackageName();
            objArr[5] = parsedIntent.mIntentContent == null ? "" : parsedIntent.mIntentContent.toString();
            reporter.report(TAG, String.format("Fail-open: allowing non-public component %s/%s: exported=%s, permission=%s for context package %s from intent %s", objArr), null);
        }
        return true;
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

    static boolean isOnOrAboveApi23() {
        return Build.VERSION.SDK_INT >= 23;
    }

    /* access modifiers changed from: private */
    @TargetApi(23)
    @DoNotOptimize
    public static class Api23Utils {
        public static int getProtectionFlagPrivileged() {
            return 16;
        }

        private Api23Utils() {
        }
    }
}
