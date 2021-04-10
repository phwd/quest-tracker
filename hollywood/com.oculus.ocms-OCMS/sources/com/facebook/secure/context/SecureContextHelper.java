package com.facebook.secure.context;

import android.annotation.SuppressLint;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Handler;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.facebook.annotations.OkToExtend;
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
import com.facebook.secure.trustedapp.TrustedAppHelper;
import java.util.Collections;
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
    private ScopedIntentLauncher mAccessibleByAnyAppIntentLauncher;
    @Nullable
    private AccessibleByAnyAppIntentScope mAccessibleByAnyAppIntentScope;
    @Nullable
    private ScopedIntentLauncher mAnyIntentLauncher;
    @Nullable
    private AnyIntentScope mAnyIntentScope;
    @Nullable
    private ScopedIntentLauncher mExternalIntentLauncher;
    @Nullable
    private ExternalIntentScope mExternalIntentScope;
    @Nullable
    private ScopedIntentLauncher mFamilyIntentLauncher;
    @Nullable
    private ScopedIntentLauncher mFamilyIntentLauncherShowChooser;
    @Nullable
    private FamilyIntentScope mFamilyIntentScope;
    @Nullable
    private FamilyIntentScope mFamilyIntentScopeShowChooser;
    private final List<IntentLaunchingPlugin> mIntentLaunchingPlugins;
    @Nullable
    private ScopedIntentLauncher mInternalIntentLauncher;
    @Nullable
    private InternalIntentScope mInternalIntentScope;
    @Nullable
    private ScopedIntentLauncher mSameKeyIntentLauncher;
    @Nullable
    private ScopedIntentLauncher mSameKeyIntentLauncherShowChooser;
    @Nullable
    private SameKeyIntentScope mSameKeyIntentScope;
    @Nullable
    private SameKeyIntentScope mSameKeyIntentScopeShowChooser;
    @Nullable
    private ScopedIntentLauncher mThirdPartyIntentLauncher;
    @Nullable
    private ThirdPartyIntentScope mThirdPartyIntentScope;
    private final Map<TrustedApp, ScopedIntentLauncher> mTrustedAppIntentLaunchers;
    private final Map<TrustedApp, ScopedIntentLauncher> mTrustedAppIntentLaunchersShowChooser;
    private Map<TrustedApp, TrustedAppIntentScope> mTrustedAppIntentScopes;
    private Map<TrustedApp, TrustedAppIntentScope> mTrustedAppIntentScopesShowChooser;

    private SecureContextHelper(InternalIntentScope internalIntentScope, SameKeyIntentScope sameKeyIntentScope, SameKeyIntentScope sameKeyIntentScope2, FamilyIntentScope familyIntentScope, FamilyIntentScope familyIntentScope2, Map<TrustedApp, TrustedAppIntentScope> map, Map<TrustedApp, TrustedAppIntentScope> map2, AccessibleByAnyAppIntentScope accessibleByAnyAppIntentScope, ExternalIntentScope externalIntentScope, ThirdPartyIntentScope thirdPartyIntentScope, AnyIntentScope anyIntentScope) {
        this.mIntentLaunchingPlugins = IntentLaunchingPluginHolder.getIntentLaunchingPlugins();
        this.mInternalIntentScope = null;
        this.mSameKeyIntentScope = null;
        this.mSameKeyIntentScopeShowChooser = null;
        this.mFamilyIntentScope = null;
        this.mFamilyIntentScopeShowChooser = null;
        this.mAccessibleByAnyAppIntentScope = null;
        this.mExternalIntentScope = null;
        this.mThirdPartyIntentScope = null;
        this.mAnyIntentScope = null;
        this.mInternalIntentLauncher = null;
        this.mSameKeyIntentLauncher = null;
        this.mSameKeyIntentLauncherShowChooser = null;
        this.mFamilyIntentLauncher = null;
        this.mFamilyIntentLauncherShowChooser = null;
        this.mAccessibleByAnyAppIntentLauncher = null;
        this.mExternalIntentLauncher = null;
        this.mThirdPartyIntentLauncher = null;
        this.mAnyIntentLauncher = null;
        this.mTrustedAppIntentScopes = new HashMap();
        this.mTrustedAppIntentScopesShowChooser = new HashMap();
        this.mTrustedAppIntentLaunchers = new HashMap();
        this.mTrustedAppIntentLaunchersShowChooser = new HashMap();
        this.mInternalIntentScope = internalIntentScope;
        this.mSameKeyIntentScope = sameKeyIntentScope;
        this.mSameKeyIntentScopeShowChooser = sameKeyIntentScope2;
        this.mFamilyIntentScope = familyIntentScope;
        this.mFamilyIntentScopeShowChooser = familyIntentScope2;
        this.mTrustedAppIntentScopes = map;
        this.mTrustedAppIntentScopesShowChooser = map2;
        this.mAccessibleByAnyAppIntentScope = accessibleByAnyAppIntentScope;
        this.mExternalIntentScope = externalIntentScope;
        this.mThirdPartyIntentScope = thirdPartyIntentScope;
        this.mAnyIntentScope = anyIntentScope;
    }

    protected SecureContextHelper() {
        this.mIntentLaunchingPlugins = IntentLaunchingPluginHolder.getIntentLaunchingPlugins();
        this.mInternalIntentScope = null;
        this.mSameKeyIntentScope = null;
        this.mSameKeyIntentScopeShowChooser = null;
        this.mFamilyIntentScope = null;
        this.mFamilyIntentScopeShowChooser = null;
        this.mAccessibleByAnyAppIntentScope = null;
        this.mExternalIntentScope = null;
        this.mThirdPartyIntentScope = null;
        this.mAnyIntentScope = null;
        this.mInternalIntentLauncher = null;
        this.mSameKeyIntentLauncher = null;
        this.mSameKeyIntentLauncherShowChooser = null;
        this.mFamilyIntentLauncher = null;
        this.mFamilyIntentLauncherShowChooser = null;
        this.mAccessibleByAnyAppIntentLauncher = null;
        this.mExternalIntentLauncher = null;
        this.mThirdPartyIntentLauncher = null;
        this.mAnyIntentLauncher = null;
        this.mTrustedAppIntentScopes = new HashMap();
        this.mTrustedAppIntentScopesShowChooser = new HashMap();
        this.mTrustedAppIntentLaunchers = new HashMap();
        this.mTrustedAppIntentLaunchersShowChooser = new HashMap();
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

    public static LaunchEnforcement.EnforceMode getEnforceMode() {
        return sDelegatingEnforcement.getEnforceMode();
    }

    public static void setLoggingLevel(LoggingConfiguration.LoggingLevel loggingLevel) {
        sDelegatingLoggingLevel.setLoggingLevel(loggingLevel);
    }

    public static LoggingConfiguration.LoggingLevel getLoggingLevel() {
        return sDelegatingLoggingLevel.getLoggingLevel();
    }

    public static void setReporter(Reporter reporter) {
        sDelegatingReporter.setReporter(reporter);
    }

    public static Reporter getReporter() {
        return sDelegatingReporter.getReporter();
    }

    public synchronized ScopedIntentLauncher internal() {
        if (this.mInternalIntentLauncher == null) {
            this.mInternalIntentLauncher = new ScopedIntentLauncher(getInternalIntentScope(), this.mIntentLaunchingPlugins);
        }
        return this.mInternalIntentLauncher;
    }

    public synchronized ScopedIntentLauncher internalWithDomain(String str) {
        if (this.mInternalIntentLauncher == null) {
            this.mInternalIntentLauncher = new ScopedIntentLauncher(getInternalIntentScope(), this.mIntentLaunchingPlugins);
        }
        return this.mInternalIntentLauncher.withDomain(str);
    }

    public synchronized ScopedIntentLauncher sameKey() {
        if (this.mSameKeyIntentLauncher == null) {
            this.mSameKeyIntentLauncher = new ScopedIntentLauncher(getSameKeyIntentScope(), this.mIntentLaunchingPlugins);
        }
        return this.mSameKeyIntentLauncher;
    }

    public synchronized ScopedIntentLauncher sameKeyWithDomain(String str) {
        if (this.mSameKeyIntentLauncher == null) {
            this.mSameKeyIntentLauncher = new ScopedIntentLauncher(getSameKeyIntentScope(), this.mIntentLaunchingPlugins);
        }
        return this.mSameKeyIntentLauncher.withDomain(str);
    }

    public synchronized ScopedIntentLauncher sameKeyShowChooser() {
        if (this.mSameKeyIntentLauncherShowChooser == null) {
            this.mSameKeyIntentLauncherShowChooser = new ScopedIntentLauncher(getSameKeyIntentScopeShowChooser(), this.mIntentLaunchingPlugins);
        }
        return this.mSameKeyIntentLauncherShowChooser;
    }

    public synchronized ScopedIntentLauncher sameKeyShowChooserWithDomain(String str) {
        if (this.mSameKeyIntentLauncherShowChooser == null) {
            this.mSameKeyIntentLauncherShowChooser = new ScopedIntentLauncher(getSameKeyIntentScopeShowChooser(), this.mIntentLaunchingPlugins);
        }
        return this.mSameKeyIntentLauncherShowChooser.withDomain(str);
    }

    public synchronized ScopedIntentLauncher family() {
        if (this.mFamilyIntentLauncher == null) {
            this.mFamilyIntentLauncher = new ScopedIntentLauncher(getFamilyIntentScope(), this.mIntentLaunchingPlugins);
        }
        return this.mFamilyIntentLauncher;
    }

    public synchronized ScopedIntentLauncher familyWithDomain(String str) {
        if (this.mFamilyIntentLauncher == null) {
            this.mFamilyIntentLauncher = new ScopedIntentLauncher(getFamilyIntentScope(), this.mIntentLaunchingPlugins);
        }
        return this.mFamilyIntentLauncher.withDomain(str);
    }

    public synchronized ScopedIntentLauncher familyShowChooser() {
        if (this.mFamilyIntentLauncherShowChooser == null) {
            this.mFamilyIntentLauncherShowChooser = new ScopedIntentLauncher(getFamilyIntentScopeShowChooser(), this.mIntentLaunchingPlugins);
        }
        return this.mFamilyIntentLauncherShowChooser;
    }

    public synchronized ScopedIntentLauncher familyShowChooserWithDomain(String str) {
        if (this.mFamilyIntentLauncherShowChooser == null) {
            this.mFamilyIntentLauncherShowChooser = new ScopedIntentLauncher(getFamilyIntentScopeShowChooser(), this.mIntentLaunchingPlugins);
        }
        return this.mFamilyIntentLauncherShowChooser.withDomain(str);
    }

    public synchronized ScopedIntentLauncher trustedApp(TrustedApp trustedApp) {
        if (!this.mTrustedAppIntentLaunchers.containsKey(trustedApp)) {
            this.mTrustedAppIntentLaunchers.put(trustedApp, new ScopedIntentLauncher(getTrustedAppIntentScope(trustedApp), this.mIntentLaunchingPlugins));
        }
        return this.mTrustedAppIntentLaunchers.get(trustedApp);
    }

    public synchronized ScopedIntentLauncher trustedAppWithDomain(TrustedApp trustedApp, String str) {
        return new ScopedIntentLauncher(getTrustedAppIntentScope(trustedApp), this.mIntentLaunchingPlugins).withDomain(str);
    }

    public synchronized ScopedIntentLauncher trustedAppShowChooser(TrustedApp trustedApp) {
        if (!this.mTrustedAppIntentLaunchersShowChooser.containsKey(trustedApp)) {
            this.mTrustedAppIntentLaunchersShowChooser.put(trustedApp, new ScopedIntentLauncher(getTrustedAppIntentScopeShowChooser(trustedApp), this.mIntentLaunchingPlugins));
        }
        return this.mTrustedAppIntentLaunchersShowChooser.get(trustedApp);
    }

    public synchronized ScopedIntentLauncher trustedAppShowChooserWithDomain(TrustedApp trustedApp, String str) {
        return new ScopedIntentLauncher(getTrustedAppIntentScopeShowChooser(trustedApp), this.mIntentLaunchingPlugins).withDomain(str);
    }

    public synchronized ScopedIntentLauncher accessibleByAnyApp() {
        if (this.mAccessibleByAnyAppIntentLauncher == null) {
            this.mAccessibleByAnyAppIntentLauncher = new ScopedIntentLauncher(getAccessibleByAnyAppIntentScope(), this.mIntentLaunchingPlugins);
        }
        return this.mAccessibleByAnyAppIntentLauncher;
    }

    public synchronized ScopedIntentLauncher external() {
        if (this.mExternalIntentLauncher == null) {
            this.mExternalIntentLauncher = new ScopedIntentLauncher(getExternalIntentScope(), this.mIntentLaunchingPlugins);
        }
        return this.mExternalIntentLauncher;
    }

    public synchronized ScopedIntentLauncher thirdParty() {
        if (this.mThirdPartyIntentLauncher == null) {
            this.mThirdPartyIntentLauncher = new ScopedIntentLauncher(getThirdPartyIntentScope(), this.mIntentLaunchingPlugins);
        }
        return this.mThirdPartyIntentLauncher;
    }

    public synchronized ScopedIntentLauncher any_UNSAFE() {
        if (this.mAnyIntentLauncher == null) {
            this.mAnyIntentLauncher = new ScopedIntentLauncher(getAnyIntentScope(), this.mIntentLaunchingPlugins);
        }
        return this.mAnyIntentLauncher;
    }

    public void registerInternalReceiver(Context context, SecureBroadcastReceiver secureBroadcastReceiver, IntentFilter intentFilter) {
        LocalBroadcastManager.getInstance(context).registerReceiver(secureBroadcastReceiver, intentFilter);
    }

    public boolean registerSameKeyReceiver(Context context, SecureBroadcastReceiver secureBroadcastReceiver, IntentFilter intentFilter) {
        secureBroadcastReceiver.setIntentScope(getSameKeyIntentScope());
        context.registerReceiver(secureBroadcastReceiver, intentFilter);
        return true;
    }

    public boolean registerSameKeyReceiver(Context context, SecureBroadcastReceiver secureBroadcastReceiver, IntentFilter intentFilter, @Nullable String str, @Nullable Handler handler) {
        secureBroadcastReceiver.setIntentScope(getSameKeyIntentScope());
        context.registerReceiver(secureBroadcastReceiver, intentFilter, str, handler);
        return true;
    }

    public boolean registerTrustedAppReceiver(Context context, TrustedApp trustedApp, SecureBroadcastReceiver secureBroadcastReceiver, IntentFilter intentFilter, @Nullable String str, @Nullable Handler handler) {
        secureBroadcastReceiver.setIntentScope(getTrustedAppIntentScope(trustedApp));
        context.registerReceiver(secureBroadcastReceiver, intentFilter, str, handler);
        return true;
    }

    public boolean registerFamilyReceiver(Context context, SecureBroadcastReceiver secureBroadcastReceiver, IntentFilter intentFilter) {
        secureBroadcastReceiver.setIntentScope(getFamilyIntentScope());
        context.registerReceiver(secureBroadcastReceiver, intentFilter);
        return true;
    }

    public boolean registerPublicReceiver(Context context, SecureBroadcastReceiver secureBroadcastReceiver, IntentFilter intentFilter) {
        secureBroadcastReceiver.setIntentScope(getAnyIntentScope());
        context.registerReceiver(secureBroadcastReceiver, intentFilter);
        return true;
    }

    SecureContextHelper(InternalIntentScope internalIntentScope) {
        this(internalIntentScope, new SameKeyIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel, false), new SameKeyIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel, true), new FamilyIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel, false), new FamilyIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel, true), getEmptyTrustedAppIntentScopes(false), getEmptyTrustedAppIntentScopes(true), new AccessibleByAnyAppIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel), new ExternalIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel), new ThirdPartyIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel), new AnyIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel));
    }

    SecureContextHelper(SameKeyIntentScope sameKeyIntentScope) {
        this(new InternalIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel), sameKeyIntentScope, sameKeyIntentScope, new FamilyIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel, false), new FamilyIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel, true), getEmptyTrustedAppIntentScopes(false), getEmptyTrustedAppIntentScopes(true), new AccessibleByAnyAppIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel), new ExternalIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel), new ThirdPartyIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel), new AnyIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel));
    }

    SecureContextHelper(FamilyIntentScope familyIntentScope) {
        this(new InternalIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel), new SameKeyIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel, false), new SameKeyIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel, true), familyIntentScope, familyIntentScope, getEmptyTrustedAppIntentScopes(false), getEmptyTrustedAppIntentScopes(true), new AccessibleByAnyAppIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel), new ExternalIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel), new ThirdPartyIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel), new AnyIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel));
    }

    SecureContextHelper(TrustedAppIntentScope trustedAppIntentScope, TrustedApp trustedApp) {
        this(new InternalIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel), new SameKeyIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel, false), new SameKeyIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel, true), new FamilyIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel, false), new FamilyIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel, true), getSingleTrustedAppIntentScopes(trustedAppIntentScope, trustedApp), getSingleTrustedAppIntentScopes(trustedAppIntentScope, trustedApp), new AccessibleByAnyAppIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel), new ExternalIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel), new ThirdPartyIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel), new AnyIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel));
    }

    SecureContextHelper(AccessibleByAnyAppIntentScope accessibleByAnyAppIntentScope) {
        this(new InternalIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel), new SameKeyIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel, false), new SameKeyIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel, true), new FamilyIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel, false), new FamilyIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel, true), getEmptyTrustedAppIntentScopes(false), getEmptyTrustedAppIntentScopes(true), accessibleByAnyAppIntentScope, new ExternalIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel), new ThirdPartyIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel), new AnyIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel));
    }

    SecureContextHelper(ExternalIntentScope externalIntentScope) {
        this(new InternalIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel), new SameKeyIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel, false), new SameKeyIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel, true), new FamilyIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel, false), new FamilyIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel, true), getEmptyTrustedAppIntentScopes(false), getEmptyTrustedAppIntentScopes(true), new AccessibleByAnyAppIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel), externalIntentScope, new ThirdPartyIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel), new AnyIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel));
    }

    SecureContextHelper(ThirdPartyIntentScope thirdPartyIntentScope) {
        this(new InternalIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel), new SameKeyIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel, false), new SameKeyIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel, true), new FamilyIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel, false), new FamilyIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel, true), getEmptyTrustedAppIntentScopes(false), getEmptyTrustedAppIntentScopes(true), new AccessibleByAnyAppIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel), new ExternalIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel), thirdPartyIntentScope, new AnyIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel));
    }

    SecureContextHelper(AnyIntentScope anyIntentScope) {
        this(new InternalIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel), new SameKeyIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel, false), new SameKeyIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel, true), new FamilyIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel, false), new FamilyIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel, true), getEmptyTrustedAppIntentScopes(false), getEmptyTrustedAppIntentScopes(true), new AccessibleByAnyAppIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel), new ExternalIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel), new ThirdPartyIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel), anyIntentScope);
    }

    private synchronized InternalIntentScope getInternalIntentScope() {
        if (this.mInternalIntentScope == null) {
            this.mInternalIntentScope = new InternalIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel);
        }
        return this.mInternalIntentScope;
    }

    public synchronized SameKeyIntentScope getSameKeyIntentScope() {
        if (this.mSameKeyIntentScope == null) {
            this.mSameKeyIntentScope = new SameKeyIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel, false);
        }
        return this.mSameKeyIntentScope;
    }

    private synchronized SameKeyIntentScope getSameKeyIntentScopeShowChooser() {
        if (this.mSameKeyIntentScopeShowChooser == null) {
            this.mSameKeyIntentScopeShowChooser = new SameKeyIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel, true);
        }
        return this.mSameKeyIntentScopeShowChooser;
    }

    /* access modifiers changed from: protected */
    public synchronized FamilyIntentScope getFamilyIntentScope() {
        if (this.mFamilyIntentScope == null) {
            this.mFamilyIntentScope = new FamilyIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel, false);
        }
        return this.mFamilyIntentScope;
    }

    /* access modifiers changed from: protected */
    public synchronized FamilyIntentScope getFamilyIntentScopeShowChooser() {
        if (this.mFamilyIntentScopeShowChooser == null) {
            this.mFamilyIntentScopeShowChooser = new FamilyIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel, true);
        }
        return this.mFamilyIntentScopeShowChooser;
    }

    public ContentProviderClient getFamilyContentProviderClient(Context context, Uri uri) throws SecurityException, IllegalStateException {
        ContentProviderClient contentProviderClient = getFamilyIntentScope().getTrustedApp().getContentProviderClient(context, uri);
        if (contentProviderClient != null) {
            return contentProviderClient;
        }
        throw new IllegalStateException("Can't find appropriate Content Provider");
    }

    private synchronized TrustedAppIntentScope getTrustedAppIntentScope(TrustedApp trustedApp) {
        if (!this.mTrustedAppIntentScopes.containsKey(trustedApp)) {
            this.mTrustedAppIntentScopes.put(trustedApp, new TrustedAppIntentScope(trustedApp, sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel, false));
        }
        return this.mTrustedAppIntentScopes.get(trustedApp);
    }

    private synchronized TrustedAppIntentScope getTrustedAppIntentScopeShowChooser(TrustedApp trustedApp) {
        if (!this.mTrustedAppIntentScopesShowChooser.containsKey(trustedApp)) {
            this.mTrustedAppIntentScopesShowChooser.put(trustedApp, new TrustedAppIntentScope(trustedApp, sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel, true));
        }
        return this.mTrustedAppIntentScopesShowChooser.get(trustedApp);
    }

    private synchronized AccessibleByAnyAppIntentScope getAccessibleByAnyAppIntentScope() {
        if (this.mAccessibleByAnyAppIntentScope == null) {
            this.mAccessibleByAnyAppIntentScope = new AccessibleByAnyAppIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel);
        }
        return this.mAccessibleByAnyAppIntentScope;
    }

    private synchronized ExternalIntentScope getExternalIntentScope() {
        if (this.mExternalIntentScope == null) {
            this.mExternalIntentScope = new ExternalIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel);
        }
        return this.mExternalIntentScope;
    }

    /* access modifiers changed from: protected */
    public synchronized ThirdPartyIntentScope getThirdPartyIntentScope() {
        if (this.mThirdPartyIntentScope == null) {
            this.mThirdPartyIntentScope = new ThirdPartyIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel);
        }
        return this.mThirdPartyIntentScope;
    }

    private synchronized AnyIntentScope getAnyIntentScope() {
        if (this.mAnyIntentScope == null) {
            this.mAnyIntentScope = new AnyIntentScope(sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel);
        }
        return this.mAnyIntentScope;
    }

    private static Map<TrustedApp, TrustedAppIntentScope> getSingleTrustedAppIntentScopes(TrustedAppIntentScope trustedAppIntentScope, TrustedApp trustedApp) {
        return new HashMap(Collections.singletonMap(trustedApp, trustedAppIntentScope));
    }

    private static Map<TrustedApp, TrustedAppIntentScope> getEmptyTrustedAppIntentScopes(boolean z) {
        TrustedApp createEmptyTrustedApp = TrustedAppHelper.createEmptyTrustedApp();
        return getSingleTrustedAppIntentScopes(new TrustedAppIntentScope(createEmptyTrustedApp, sDelegatingEnforcement, sDelegatingReporter, sDelegatingLoggingLevel, z), createEmptyTrustedApp);
    }
}
