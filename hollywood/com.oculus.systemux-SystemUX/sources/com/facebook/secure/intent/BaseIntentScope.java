package com.facebook.secure.intent;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ComponentInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import com.facebook.debug.log.LoggingUtil;
import com.facebook.secure.intent.LaunchEnforcement;
import com.facebook.secure.logger.LoggingConfiguration;
import com.facebook.secure.logger.Reporter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;

public abstract class BaseIntentScope implements IntentScope {
    protected static final int DEFAULT_FILTER_FLAG = 65600;
    private static final String INTENT_CHOOSER_LABEL = "Choose an app to launch.";
    private static final String TAG = "BaseIntentScope";
    protected final LoggingConfiguration mDelegatingLoggingLevel;
    private final LaunchEnforcement mLaunchEnforcement;
    protected final Reporter reporter;
    protected final boolean showChooser;

    /* access modifiers changed from: protected */
    public abstract boolean isPackageWithinScope(Context context, PackageInfo packageInfo);

    public BaseIntentScope(LaunchEnforcement launchEnforcement, Reporter reporter2, LoggingConfiguration loggingConfiguration, boolean z) {
        this.mLaunchEnforcement = launchEnforcement;
        this.reporter = reporter2;
        this.mDelegatingLoggingLevel = loggingConfiguration;
        this.showChooser = z;
    }

    public BaseIntentScope(LaunchEnforcement launchEnforcement, Reporter reporter2, LoggingConfiguration loggingConfiguration) {
        this(launchEnforcement, reporter2, loggingConfiguration, false);
    }

    public Reporter getReporter() {
        return this.reporter;
    }

    public LaunchEnforcement.EnforceMode getEnforceMode() {
        return this.mLaunchEnforcement.getEnforceMode();
    }

    public LoggingConfiguration.LoggingLevel getLoggingLevel() {
        return this.mDelegatingLoggingLevel.getLoggingLevel();
    }

    public boolean isShowChooser() {
        return this.showChooser;
    }

    /* access modifiers changed from: package-private */
    public boolean isOpenEverywhere() {
        return getEnforceMode() == LaunchEnforcement.EnforceMode.OPEN_EVERYWHERE;
    }

    /* access modifiers changed from: package-private */
    public boolean isOpenOnNonFbAndException() {
        return isOpenEverywhere() || getEnforceMode() == LaunchEnforcement.EnforceMode.OPEN_NONFB_AND_EXCEPTION_ONLY;
    }

    /* access modifiers changed from: package-private */
    public boolean isEnforceEverywhere() {
        return getEnforceMode() == LaunchEnforcement.EnforceMode.ENFORCE_EVERYWHERE;
    }

    @Override // com.facebook.secure.intent.IntentScope
    @Nullable
    public Intent enforceActivityIntent(Intent intent, Context context) {
        return enforceActivityIntent(intent, context, null);
    }

    @Override // com.facebook.secure.intent.IntentScope
    @Nullable
    public Intent enforceServiceIntent(Intent intent, Context context) {
        return enforceServiceIntent(intent, context, null);
    }

    @Override // com.facebook.secure.intent.IntentScope
    public List<Intent> enforceBroadcastIntent(Intent intent, Context context) {
        return enforceBroadcastIntent(intent, context, null);
    }

    @Override // com.facebook.secure.intent.IntentScope
    @Nullable
    public Intent enforceReceiverIntent(Intent intent, Context context) {
        return enforceReceiverIntent(intent, context, null);
    }

    public List<ActivityInfo> filterActivityByMatchingIntentFilter(Intent intent, Context context) {
        return filterActivityByFlag(intent, context, DEFAULT_FILTER_FLAG);
    }

    public List<ServiceInfo> filterServiceByMatchingIntentFilter(Intent intent, Context context) {
        return filterServiceByFlag(intent, context, DEFAULT_FILTER_FLAG);
    }

    public List<ActivityInfo> filterLaunchableActivity(Intent intent, Context context) {
        return filterActivityByFlag(intent, context, 0);
    }

    public List<ServiceInfo> filterLaunchableService(Intent intent, Context context) {
        return filterServiceByFlag(intent, context, 0);
    }

    /* access modifiers changed from: protected */
    public List<Intent> getBroadcastIntentsWithinScope(Intent intent, Context context) {
        ArrayList arrayList = new ArrayList(1);
        for (PackageInfo packageInfo : getPackagesWithinScope(intent, context)) {
            Intent intent2 = new Intent(intent);
            intent2.setPackage(packageInfo.packageName);
            arrayList.add(intent2);
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    @SuppressLint({"CatchGeneralException"})
    public List<PackageInfo> getPackagesWithinScope(Intent intent, Context context) {
        List<PackageInfo> list;
        String str = intent.getPackage();
        if (str != null) {
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 64);
                list = packageInfo != null ? Arrays.asList(packageInfo) : new ArrayList<>();
            } catch (PackageManager.NameNotFoundException | RuntimeException e) {
                this.reporter.report(TAG, "Error querying PackageManager.", e);
                return new ArrayList();
            }
        } else {
            list = context.getPackageManager().getInstalledPackages(64);
        }
        ArrayList arrayList = new ArrayList(1);
        for (PackageInfo packageInfo2 : list) {
            if (isPackageWithinScope(context, packageInfo2)) {
                arrayList.add(packageInfo2);
            }
        }
        return arrayList;
    }

    private List<ActivityInfo> filterActivityByFlag(Intent intent, Context context, int i) {
        List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, i);
        if (queryIntentActivities == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(1);
        for (T t : queryIntentActivities) {
            if (!(((ResolveInfo) t).activityInfo == null || ((ResolveInfo) t).activityInfo.applicationInfo == null)) {
                arrayList.add(((ResolveInfo) t).activityInfo);
            }
        }
        return arrayList;
    }

    private List<ServiceInfo> filterServiceByFlag(Intent intent, Context context, int i) {
        List<ResolveInfo> queryIntentServices = context.getPackageManager().queryIntentServices(intent, i);
        if (queryIntentServices == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(1);
        for (T t : queryIntentServices) {
            if (!(((ResolveInfo) t).serviceInfo == null || ((ResolveInfo) t).serviceInfo.applicationInfo == null)) {
                arrayList.add(((ResolveInfo) t).serviceInfo);
            }
        }
        return arrayList;
    }

    protected static boolean isExplicitInternalIntent(Intent intent, Context context) {
        return intent.getComponent() != null && intent.getComponent().getPackageName().equals(context.getPackageName());
    }

    protected static String intentToString(@Nullable Intent intent) {
        if (intent == null) {
            return LoggingUtil.NO_HASHCODE;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("intent(");
        sb.append("action = ");
        sb.append(intent.getAction());
        sb.append(", data= ");
        sb.append(intent.getData());
        sb.append(", type= ");
        sb.append(intent.getType());
        if (intent.getComponent() != null) {
            sb.append(", component = ");
            sb.append(intent.getComponent());
        }
        Bundle extras = intent.getExtras();
        if (extras != null) {
            sb.append(", extras = [");
            for (String str : extras.keySet()) {
                sb.append(str);
                sb.append(" = ");
                sb.append(extras.get(str));
                sb.append(", ");
            }
            sb.append("]");
        }
        sb.append(")");
        return sb.toString();
    }

    protected static Intent createChooserIntent(List<Intent> list) {
        Intent[] intentArr = new Intent[(list.size() - 1)];
        int i = 0;
        while (i < list.size() - 1) {
            int i2 = i + 1;
            intentArr[i] = list.get(i2);
            i = i2;
        }
        Intent createChooser = Intent.createChooser(list.get(0), INTENT_CHOOSER_LABEL);
        createChooser.putExtra("android.intent.extra.INITIAL_INTENTS", intentArr);
        return createChooser;
    }

    protected static List<Intent> getIntentsForTrustedComponents(List<? extends ComponentInfo> list, Intent intent) {
        ArrayList arrayList = new ArrayList(list.size());
        for (ComponentInfo componentInfo : list) {
            Intent intent2 = new Intent(intent);
            intent2.setComponent(new ComponentName(componentInfo.packageName, componentInfo.name));
            intent2.setPackage(componentInfo.packageName);
            arrayList.add(intent2);
        }
        return arrayList;
    }
}
