package com.facebook.secure.context;

import android.annotation.SuppressLint;
import com.facebook.annotations.OkToExtend;
import com.facebook.secure.intent.AccessibleByAnyAppIntentScope;
import com.facebook.secure.intent.AnyIntentScope;
import com.facebook.secure.intent.ExternalIntentScope;
import com.facebook.secure.intent.FamilyIntentScope;
import com.facebook.secure.intent.InternalIntentScope;
import com.facebook.secure.intent.LaunchEnforcement;
import com.facebook.secure.intent.SameKeyIntentScope;
import com.facebook.secure.intent.ThirdPartyIntentScope;
import com.facebook.secure.logger.LoggingConfiguration;
import com.facebook.secure.logger.Reporter;
import com.facebook.secure.trustedapp.TrustedApp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

@OkToExtend
@SuppressLint({"InstanceMethodCanBeStatic"})
public class SecureContextHelper {
    protected static final LaunchEnforcement sDelegatingEnforcement = new LaunchEnforcement();
    protected static final LoggingConfiguration sDelegatingLoggingLevel = new LoggingConfiguration();
    protected static final DelegatingReporter sDelegatingReporter = new DelegatingReporter();
    @Nullable
    private static SecureContextHelper sSecureContextHelper = null;
    @Nullable
    private ScopedIntentLauncher mAccessibleByAnyAppIntentLauncher = null;
    @Nullable
    private AccessibleByAnyAppIntentScope mAccessibleByAnyAppIntentScope = null;
    @Nullable
    private ScopedIntentLauncher mAnyIntentLauncher = null;
    @Nullable
    private AnyIntentScope mAnyIntentScope = null;
    @Nullable
    private ScopedIntentLauncher mExternalIntentLauncher = null;
    @Nullable
    private ExternalIntentScope mExternalIntentScope = null;
    @Nullable
    private ScopedIntentLauncher mFamilyIntentLauncher = null;
    @Nullable
    private ScopedIntentLauncher mFamilyIntentLauncherShowChooser = null;
    @Nullable
    private FamilyIntentScope mFamilyIntentScope = null;
    @Nullable
    private FamilyIntentScope mFamilyIntentScopeShowChooser = null;
    private final List<Object> mIntentLaunchingPlugins = IntentLaunchingPluginHolder.getIntentLaunchingPlugins();
    @Nullable
    private ScopedIntentLauncher mInternalIntentLauncher = null;
    @Nullable
    private InternalIntentScope mInternalIntentScope = null;
    @Nullable
    private ScopedIntentLauncher mSameKeyIntentLauncher = null;
    @Nullable
    private ScopedIntentLauncher mSameKeyIntentLauncherShowChooser = null;
    @Nullable
    private SameKeyIntentScope mSameKeyIntentScope = null;
    @Nullable
    private SameKeyIntentScope mSameKeyIntentScopeShowChooser = null;
    @Nullable
    private ScopedIntentLauncher mThirdPartyIntentLauncher = null;
    @Nullable
    private ThirdPartyIntentScope mThirdPartyIntentScope = null;
    private final Map<TrustedApp, ScopedIntentLauncher> mTrustedAppIntentLaunchers = new HashMap();
    private final Map<TrustedApp, ScopedIntentLauncher> mTrustedAppIntentLaunchersShowChooser = new HashMap();
    private Map<TrustedApp, Object> mTrustedAppIntentScopes = new HashMap();
    private Map<TrustedApp, Object> mTrustedAppIntentScopesShowChooser = new HashMap();

    protected SecureContextHelper() {
    }

    public static synchronized SecureContextHelper get() {
        SecureContextHelper secureContextHelper;
        synchronized (SecureContextHelper.class) {
            secureContextHelper = get(null, null);
        }
        return secureContextHelper;
    }

    public static synchronized SecureContextHelper get(@Nullable LaunchEnforcement.EnforceMode enforceMode, @Nullable Reporter reporter) {
        SecureContextHelper secureContextHelper;
        synchronized (SecureContextHelper.class) {
            secureContextHelper = get(enforceMode, reporter, LoggingConfiguration.DEFAULT_LOGGING_LEVEL);
        }
        return secureContextHelper;
    }

    public static synchronized SecureContextHelper get(@Nullable LaunchEnforcement.EnforceMode enforceMode, @Nullable Reporter reporter, LoggingConfiguration.LoggingLevel loggingLevel) {
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

    public synchronized ScopedIntentLauncher internal() {
        if (this.mInternalIntentLauncher == null) {
            this.mInternalIntentLauncher = new ScopedIntentLauncher(getInternalIntentScope(), this.mIntentLaunchingPlugins);
        }
        return this.mInternalIntentLauncher;
    }

    private synchronized InternalIntentScope getInternalIntentScope() {
        if (this.mInternalIntentScope == null) {
            this.mInternalIntentScope = new InternalIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel);
        }
        return this.mInternalIntentScope;
    }
}
