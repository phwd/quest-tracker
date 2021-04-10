package com.facebook.secure.context;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Handler;
import com.facebook.secure.intent.AccessibleByAnyAppIntentScope;
import com.facebook.secure.intent.AnyIntentScope;
import com.facebook.secure.intent.ExternalIntentScope;
import com.facebook.secure.intent.FamilyIntentScope;
import com.facebook.secure.intent.InternalIntentScope;
import com.facebook.secure.intent.LaunchEnforcement;
import com.facebook.secure.intent.SameKeyIntentScope;
import com.facebook.secure.intent.ThirdPartyIntentScope;
import com.facebook.secure.intent.TrustedAppIntentScope;
import com.facebook.secure.logger.LoggingConfiguration;
import com.facebook.secure.logger.Reporter;
import com.facebook.secure.receiver.SecureBroadcastReceiver;
import com.facebook.secure.trustedapp.TrustedApp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressLint({"InstanceMethodCanBeStatic"})
public class SecureContextHelper {
    protected static final LaunchEnforcement sDelegatingEnforcement = new LaunchEnforcement();
    protected static final LoggingConfiguration sDelegatingLoggingLevel = new LoggingConfiguration();
    protected static final DelegatingReporter sDelegatingReporter = new DelegatingReporter();
    private static SecureContextHelper sSecureContextHelper = null;
    private ScopedIntentLauncher mAccessibleByAnyAppIntentLauncher = null;
    private AccessibleByAnyAppIntentScope mAccessibleByAnyAppIntentScope = null;
    private ScopedIntentLauncher mAnyIntentLauncher = null;
    private AnyIntentScope mAnyIntentScope = null;
    private ScopedIntentLauncher mExternalIntentLauncher = null;
    private ExternalIntentScope mExternalIntentScope = null;
    private ScopedIntentLauncher mFamilyIntentLauncher = null;
    private ScopedIntentLauncher mFamilyIntentLauncherShowChooser = null;
    private FamilyIntentScope mFamilyIntentScope = null;
    private FamilyIntentScope mFamilyIntentScopeShowChooser = null;
    private final List<Object> mIntentLaunchingPlugins = IntentLaunchingPluginHolder.getIntentLaunchingPlugins();
    private ScopedIntentLauncher mInternalIntentLauncher = null;
    private InternalIntentScope mInternalIntentScope = null;
    private ScopedIntentLauncher mSameKeyIntentLauncher = null;
    private ScopedIntentLauncher mSameKeyIntentLauncherShowChooser = null;
    private SameKeyIntentScope mSameKeyIntentScope = null;
    private SameKeyIntentScope mSameKeyIntentScopeShowChooser = null;
    private ScopedIntentLauncher mThirdPartyIntentLauncher = null;
    private ThirdPartyIntentScope mThirdPartyIntentScope = null;
    private final Map<TrustedApp, ScopedIntentLauncher> mTrustedAppIntentLaunchers = new HashMap();
    private final Map<TrustedApp, ScopedIntentLauncher> mTrustedAppIntentLaunchersShowChooser = new HashMap();
    private Map<TrustedApp, TrustedAppIntentScope> mTrustedAppIntentScopes = new HashMap();
    private Map<TrustedApp, TrustedAppIntentScope> mTrustedAppIntentScopesShowChooser = new HashMap();

    protected SecureContextHelper() {
    }

    public static synchronized SecureContextHelper get() {
        SecureContextHelper secureContextHelper;
        synchronized (SecureContextHelper.class) {
            secureContextHelper = get(null, null);
        }
        return secureContextHelper;
    }

    public static synchronized SecureContextHelper get(LaunchEnforcement.EnforceMode enforceMode, Reporter reporter) {
        SecureContextHelper secureContextHelper;
        synchronized (SecureContextHelper.class) {
            secureContextHelper = get(enforceMode, reporter, LoggingConfiguration.DEFAULT_LOGGING_LEVEL);
        }
        return secureContextHelper;
    }

    public static synchronized SecureContextHelper get(LaunchEnforcement.EnforceMode enforceMode, Reporter reporter, LoggingConfiguration.LoggingLevel loggingLevel) {
        SecureContextHelper secureContextHelper;
        synchronized (SecureContextHelper.class) {
            if (sSecureContextHelper == null) {
                sSecureContextHelper = new SecureContextHelper();
            }
            if (enforceMode != null) {
                setEnforceMode(enforceMode);
            }
            if (reporter != null) {
                setReporter(reporter);
            }
            secureContextHelper = sSecureContextHelper;
        }
        return secureContextHelper;
    }

    public static void setEnforceMode(LaunchEnforcement.EnforceMode enforceMode) {
        sDelegatingEnforcement.setEnforceMode(enforceMode);
    }

    public static void setReporter(Reporter reporter) {
        sDelegatingReporter.setReporter(reporter);
    }

    public boolean registerTrustedAppReceiver(Context context, TrustedApp trustedApp, SecureBroadcastReceiver secureBroadcastReceiver, IntentFilter intentFilter, String str, Handler handler) {
        secureBroadcastReceiver.setIntentScope(getTrustedAppIntentScope(trustedApp));
        context.registerReceiver(secureBroadcastReceiver, intentFilter, str, handler);
        return true;
    }

    private synchronized TrustedAppIntentScope getTrustedAppIntentScope(TrustedApp trustedApp) {
        if (!this.mTrustedAppIntentScopes.containsKey(trustedApp)) {
            this.mTrustedAppIntentScopes.put(trustedApp, new TrustedAppIntentScope(trustedApp, sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel, false));
        }
        return this.mTrustedAppIntentScopes.get(trustedApp);
    }
}
