package com.facebook.secure.context;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import androidx.core.app.ActivityCompat;
import androidx.core.app.TaskStackBuilder;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.facebook.annotations.DoNotOptimize;
import com.facebook.secure.intent.BaseIntentScope;
import com.facebook.secure.receiver.SecureBroadcastReceiver;
import com.facebook.secure.trustedapp.CallerInfoHelper;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;

@SuppressLint({"BadSuperClassIntentLauncher", "ImprovedNewApi", "BadMethodUse-androidx.fragment.app.Fragment.startActivityForResult"})
public class ScopedIntentLauncher extends BaseIntentLauncher {
    private static final String API_WARNING = "Warning: launching intents with a bundle on API < 16 will crash the app.";
    @Nullable
    private String mCallerDomain;
    private final List<IntentLaunchingPlugin> mIntentLaunchingPlugins;
    protected final BaseIntentScope mScope;

    public ScopedIntentLauncher(BaseIntentScope baseIntentScope) {
        this(baseIntentScope, Collections.emptyList());
    }

    public ScopedIntentLauncher(BaseIntentScope baseIntentScope, List<IntentLaunchingPlugin> list) {
        this.mCallerDomain = null;
        this.mScope = baseIntentScope;
        this.mIntentLaunchingPlugins = list;
    }

    public ScopedIntentLauncher withDomain(@Nullable String str) {
        this.mCallerDomain = str;
        return this;
    }

    @Override // com.facebook.secure.context.BaseIntentLauncher, com.facebook.secure.context.IntentLauncher
    public boolean launchActivity(Intent intent, Context context) {
        Intent enforcedIntentWithPlugins = getEnforcedIntentWithPlugins(intent, context);
        if (enforcedIntentWithPlugins == null) {
            return false;
        }
        context.startActivity(enforcedIntentWithPlugins);
        return true;
    }

    public boolean canLaunchActivity(Intent intent, Context context) {
        return getEnforcedIntentWithPlugins(intent, context) != null;
    }

    @Override // com.facebook.secure.context.BaseIntentLauncher, com.facebook.secure.context.IntentLauncher
    public boolean launchReactNativeActivity(Intent intent, Context context) {
        Intent applyPlugins;
        Intent enforceActivityIntent = this.mScope.enforceActivityIntent(intent, context, this.mCallerDomain);
        withDomain(null);
        if (enforceActivityIntent == null || (applyPlugins = applyPlugins(CallerInfoHelper.removeCallerInfo(enforceActivityIntent), context)) == null) {
            return false;
        }
        context.startActivity(applyPlugins);
        return true;
    }

    @Override // com.facebook.secure.context.BaseIntentLauncher, com.facebook.secure.context.IntentLauncher
    public boolean launchActivity(Intent intent, Bundle bundle, Context context) {
        Intent enforceActivityIntent = this.mScope.enforceActivityIntent(intent, context, this.mCallerDomain);
        withDomain(null);
        if (enforceActivityIntent == null) {
            return false;
        }
        if (!isOnOrAboveApi16() && bundle != null) {
            this.mScope.getReporter().report(API_WARNING);
        }
        Intent applyPlugins = applyPlugins(enforceActivityIntent, context);
        if (applyPlugins == null) {
            return false;
        }
        ContextCompat.startActivity(context, applyPlugins, bundle);
        return true;
    }

    @Override // com.facebook.secure.context.BaseIntentLauncher, com.facebook.secure.context.IntentLauncher
    public boolean launchActivities(TaskStackBuilder taskStackBuilder, Context context) {
        if (taskStackBuilder.getIntentCount() == 0) {
            return false;
        }
        TaskStackBuilder create = TaskStackBuilder.create(context);
        for (Intent intent : taskStackBuilder.getIntents()) {
            Intent enforceActivityIntent = this.mScope.enforceActivityIntent(intent, context, this.mCallerDomain);
            if (enforceActivityIntent == null) {
                withDomain(null);
                return false;
            }
            Intent applyPlugins = applyPlugins(enforceActivityIntent, context);
            if (applyPlugins == null) {
                withDomain(null);
                return false;
            }
            create.addNextIntent(applyPlugins);
        }
        withDomain(null);
        create.startActivities();
        return true;
    }

    @SuppressLint({"BadMethodUse-android.content.Context.startActivities"})
    public boolean launchActivities(Intent[] intentArr, Context context) {
        if (intentArr == null || intentArr.length == 0) {
            return false;
        }
        Intent[] intentArr2 = new Intent[intentArr.length];
        for (int i = 0; i < intentArr.length; i++) {
            Intent enforceActivityIntent = this.mScope.enforceActivityIntent(intentArr[i], context, this.mCallerDomain);
            if (enforceActivityIntent == null) {
                withDomain(null);
                return false;
            }
            Intent applyPlugins = applyPlugins(enforceActivityIntent, context);
            if (applyPlugins == null) {
                withDomain(null);
                return false;
            }
            intentArr2[i] = applyPlugins;
        }
        withDomain(null);
        context.startActivities(intentArr2);
        return true;
    }

    @Override // com.facebook.secure.context.BaseIntentLauncher, com.facebook.secure.context.IntentLauncher
    public boolean launchActivityForResult(Intent intent, int i, Activity activity) {
        Intent applyPlugins;
        Intent enforceActivityIntent = this.mScope.enforceActivityIntent(intent, activity, this.mCallerDomain);
        withDomain(null);
        if (enforceActivityIntent == null || (applyPlugins = applyPlugins(enforceActivityIntent, activity, i)) == null) {
            return false;
        }
        activity.startActivityForResult(applyPlugins, i);
        return true;
    }

    @Override // com.facebook.secure.context.BaseIntentLauncher, com.facebook.secure.context.IntentLauncher
    public boolean launchActivityForResult(Intent intent, int i, @Nullable Bundle bundle, Activity activity) {
        Intent enforceActivityIntent = this.mScope.enforceActivityIntent(intent, activity, this.mCallerDomain);
        withDomain(null);
        if (enforceActivityIntent == null) {
            return false;
        }
        if (!isOnOrAboveApi16() && bundle != null) {
            this.mScope.getReporter().report(API_WARNING);
        }
        Intent applyPlugins = applyPlugins(enforceActivityIntent, activity, i);
        if (applyPlugins == null) {
            return false;
        }
        ActivityCompat.startActivityForResult(activity, applyPlugins, i, bundle);
        return true;
    }

    @Override // com.facebook.secure.context.BaseIntentLauncher, com.facebook.secure.context.IntentLauncher
    public boolean launchActivityForResult(Intent intent, int i, Fragment fragment) {
        Intent applyPlugins;
        Intent enforceActivityIntent = this.mScope.enforceActivityIntent(intent, fragment.getContext(), this.mCallerDomain);
        withDomain(null);
        if (enforceActivityIntent == null || (applyPlugins = applyPlugins(enforceActivityIntent, fragment.getContext(), i)) == null) {
            return false;
        }
        fragment.startActivityForResult(applyPlugins, i);
        return true;
    }

    @Override // com.facebook.secure.context.BaseIntentLauncher, com.facebook.secure.context.IntentLauncher
    public boolean launchActivityForResult(Intent intent, int i, @Nullable Bundle bundle, Fragment fragment) {
        Intent applyPlugins;
        Intent enforceActivityIntent = this.mScope.enforceActivityIntent(intent, fragment.getContext(), this.mCallerDomain);
        withDomain(null);
        if (enforceActivityIntent == null || (applyPlugins = applyPlugins(enforceActivityIntent, fragment.getContext(), i)) == null) {
            return false;
        }
        if (isOnOrAboveApi16()) {
            Api16Utils.startActivityForResult(fragment, applyPlugins, i, bundle);
            return true;
        }
        if (bundle != null) {
            this.mScope.getReporter().report(API_WARNING);
        }
        fragment.startActivityForResult(applyPlugins, i);
        return true;
    }

    @Override // com.facebook.secure.context.BaseIntentLauncher, com.facebook.secure.context.IntentLauncher
    @SuppressLint({"BadMethodUse-android.app.Activity.startActivityIfNeeded"})
    public boolean launchActivityIfNeeded(Intent intent, int i, Activity activity) {
        Intent applyPlugins;
        Intent enforceActivityIntent = this.mScope.enforceActivityIntent(intent, activity, this.mCallerDomain);
        withDomain(null);
        if (enforceActivityIntent == null || (applyPlugins = applyPlugins(enforceActivityIntent, activity, i)) == null) {
            return false;
        }
        return activity.startActivityIfNeeded(applyPlugins, i);
    }

    @Override // com.facebook.secure.context.BaseIntentLauncher, com.facebook.secure.context.IntentLauncher
    @SuppressLint({"BadMethodUse-android.app.Activity.startActivityIfNeeded"})
    public boolean launchActivityIfNeeded(Intent intent, int i, @Nullable Bundle bundle, Activity activity) {
        Intent applyPlugins;
        Intent enforceActivityIntent = this.mScope.enforceActivityIntent(intent, activity, this.mCallerDomain);
        withDomain(null);
        if (enforceActivityIntent == null || (applyPlugins = applyPlugins(enforceActivityIntent, activity, i)) == null) {
            return false;
        }
        if (isOnOrAboveApi16()) {
            return Api16Utils.startActivityIfNeeded(activity, applyPlugins, i, bundle);
        }
        if (bundle != null) {
            this.mScope.getReporter().report(API_WARNING);
        }
        return activity.startActivityIfNeeded(applyPlugins, i);
    }

    @Override // com.facebook.secure.context.BaseIntentLauncher, com.facebook.secure.context.IntentLauncher
    @Nullable
    public ComponentName launchService(Intent intent, Context context) {
        Intent enforceServiceIntent = this.mScope.enforceServiceIntent(intent, context, this.mCallerDomain);
        withDomain(null);
        if (enforceServiceIntent == null) {
            return null;
        }
        return context.startService(enforceServiceIntent);
    }

    @Override // com.facebook.secure.context.BaseIntentLauncher, com.facebook.secure.context.IntentLauncher
    @Nullable
    public ComponentName launchForegroundService(Intent intent, Context context) {
        Intent enforceServiceIntent = this.mScope.enforceServiceIntent(intent, context, this.mCallerDomain);
        withDomain(null);
        if (enforceServiceIntent == null) {
            return null;
        }
        if (isOnOrAboveApi26()) {
            return Api26Utils.startForegroundService(context, enforceServiceIntent);
        }
        return context.startService(enforceServiceIntent);
    }

    @Override // com.facebook.secure.context.BaseIntentLauncher, com.facebook.secure.context.IntentLauncher
    public boolean bindToService(Intent intent, ServiceConnection serviceConnection, int i, Context context) {
        Intent enforceServiceIntent = this.mScope.enforceServiceIntent(intent, context, this.mCallerDomain);
        withDomain(null);
        if (enforceServiceIntent == null) {
            return false;
        }
        return context.bindService(enforceServiceIntent, serviceConnection, i);
    }

    @Override // com.facebook.secure.context.BaseIntentLauncher, com.facebook.secure.context.IntentLauncher
    public boolean stopService(Intent intent, Context context) {
        Intent enforceServiceIntent = this.mScope.enforceServiceIntent(intent, context, this.mCallerDomain);
        withDomain(null);
        if (enforceServiceIntent == null) {
            return false;
        }
        return context.stopService(enforceServiceIntent);
    }

    @Override // com.facebook.secure.context.BaseIntentLauncher, com.facebook.secure.context.IntentLauncher
    public boolean sendBroadcast(Intent intent, Context context) {
        return sendBroadcast(intent, context, null);
    }

    @Override // com.facebook.secure.context.BaseIntentLauncher, com.facebook.secure.context.IntentLauncher
    public boolean sendBroadcast(Intent intent, Context context, @Nullable String str) {
        List<Intent> enforceBroadcastIntent = this.mScope.enforceBroadcastIntent(intent, context, this.mCallerDomain);
        withDomain(null);
        if (enforceBroadcastIntent.isEmpty()) {
            return false;
        }
        for (Intent intent2 : enforceBroadcastIntent) {
            try {
                context.sendBroadcast(intent2, str);
            } catch (RuntimeException e) {
                if (!(e.getCause() instanceof DeadObjectException)) {
                    throw e;
                }
            }
        }
        return true;
    }

    @Override // com.facebook.secure.context.BaseIntentLauncher, com.facebook.secure.context.IntentLauncher
    public boolean sendOrderedBroadcast(Intent intent, Context context, @Nullable String str, @Nullable BroadcastReceiver broadcastReceiver, @Nullable Handler handler, int i, @Nullable String str2, @Nullable Bundle bundle) {
        List<Intent> enforceBroadcastIntent = this.mScope.enforceBroadcastIntent(intent, context, this.mCallerDomain);
        withDomain(null);
        if (enforceBroadcastIntent.isEmpty()) {
            return false;
        }
        for (Intent intent2 : enforceBroadcastIntent) {
            context.sendOrderedBroadcast(intent2, str, broadcastReceiver, handler, i, str2, bundle);
        }
        return true;
    }

    @Override // com.facebook.secure.context.BaseIntentLauncher, com.facebook.secure.context.IntentLauncher
    public void registerReceiver(Context context, SecureBroadcastReceiver secureBroadcastReceiver, IntentFilter intentFilter, @Nullable String str, @Nullable Handler handler) {
        secureBroadcastReceiver.setIntentScope(this.mScope);
        context.registerReceiver(secureBroadcastReceiver, intentFilter, str, handler);
    }

    @Nullable
    private Intent applyPluginsInternal(Intent intent, @Nullable Context context, @Nullable Integer num) {
        if (context == null || this.mIntentLaunchingPlugins.isEmpty()) {
            return intent;
        }
        for (IntentLaunchingPlugin intentLaunchingPlugin : this.mIntentLaunchingPlugins) {
            if (intentLaunchingPlugin != null && intentLaunchingPlugin.getApplicableScopeTypes().contains(this.mScope.getScopeType()) && intentLaunchingPlugin.isEligible(intent, context)) {
                if (num == null) {
                    intent = intentLaunchingPlugin.apply(intent, context);
                } else {
                    intent = intentLaunchingPlugin.apply(intent, context, num.intValue());
                }
                if (intent == null) {
                    return null;
                }
            }
        }
        return intent;
    }

    @Nullable
    private Intent applyPlugins(Intent intent, @Nullable Context context, int i) {
        return applyPluginsInternal(intent, context, Integer.valueOf(i));
    }

    @Nullable
    private Intent applyPlugins(Intent intent, @Nullable Context context) {
        return applyPluginsInternal(intent, context, null);
    }

    static boolean isOnOrAboveApi16() {
        return Build.VERSION.SDK_INT >= 16;
    }

    @Nullable
    private Intent getEnforcedIntentWithPlugins(Intent intent, Context context) {
        Intent enforceActivityIntent = this.mScope.enforceActivityIntent(intent, context, this.mCallerDomain);
        withDomain(null);
        if (enforceActivityIntent == null) {
            return null;
        }
        return applyPlugins(enforceActivityIntent, context);
    }

    private static boolean isOnOrAboveApi26() {
        return Build.VERSION.SDK_INT >= 26;
    }

    @TargetApi(16)
    @DoNotOptimize
    private static class Api16Utils {
        private Api16Utils() {
        }

        public static void startActivityForResult(Fragment fragment, Intent intent, int i, Bundle bundle) {
            fragment.startActivityForResult(intent, i, bundle);
        }

        @SuppressLint({"BadMethodUse-android.app.Activity.startActivityIfNeeded"})
        public static boolean startActivityIfNeeded(Activity activity, Intent intent, int i, Bundle bundle) {
            return activity.startActivityIfNeeded(intent, i, bundle);
        }
    }

    @TargetApi(26)
    @DoNotOptimize
    private static class Api26Utils {
        private Api26Utils() {
        }

        @SuppressLint({"BadMethodUse-android.content.Context.startForegroundService"})
        public static ComponentName startForegroundService(Context context, Intent intent) {
            return context.startForegroundService(intent);
        }
    }
}
