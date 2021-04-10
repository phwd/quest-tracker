package com.oculus.socialplatform.partyservice;

import android.content.Context;
import android.content.Intent;
import bolts.Continuation;
import bolts.Task;
import bolts.TaskCompletionSource;
import com.oculus.auth.credentials.Credentials;
import com.oculus.auth.handler.LoginHandler;
import com.oculus.deviceconfigclient.DeviceConfigCallback;
import com.oculus.deviceconfigclient.DeviceConfigClient;
import com.oculus.horizon.vr_lifecycle.VRLifecycleManager;
import com.oculus.partystatemanager.IPartyServiceDelegate;
import com.oculus.partystatemanager.PartyStateManager;
import com.oculus.partystatemanager.logging.PartyEventFields;
import com.oculus.socialplatform.auth.SocialPlatformCredentialsManager;
import com.oculus.socialplatform.sharedstate.PartySharedState;
import com.oculus.util.constants.SocialPlatformContants;
import javax.annotation.Nullable;

public class PartyServiceManager implements VRLifecycleManager.VRLifecycle, LoginHandler {
    public static final String TAG = "PartyServiceManager";
    public final TaskCompletionSource mAuthTask = new TaskCompletionSource();
    public final Context mContext;
    public final SocialPlatformCredentialsManager mCredentialsManager;
    public DeviceConfigClient mDeviceConfigClient;
    public boolean mIsMounted = true;
    public final PartyNativeModuleManager mNativeCode;
    public final Object mPartiesStateLock = new Object();
    public final PartyStateManager mPartiesStateManager;
    public final PartySharedState mSharedPartyState;
    public boolean mShouldRestore = false;
    public final VRLifecycleManager mVRLifecycleManager;

    public static /* synthetic */ String access$300() {
        return TAG;
    }

    private DeviceConfigClient getDeviceConfigClient() {
        if (this.mDeviceConfigClient == null) {
            DeviceConfigClient deviceConfigClient = new DeviceConfigClient(this.mContext);
            this.mDeviceConfigClient = deviceConfigClient;
            deviceConfigClient.subscribe(new DeviceConfigCallback());
        }
        return this.mDeviceConfigClient;
    }

    @Override // com.oculus.auth.handler.LoginHandler
    public Task<Void> afterLoginAsync() throws IllegalArgumentException {
        if (this.mCredentialsManager.getCredentials() != null) {
            this.mAuthTask.trySetResult(null);
            return this.mAuthTask.task;
        }
        throw new IllegalArgumentException("Called afterLoginAsync, but credential manager returned null credentials.  Exploding!");
    }

    public void handleStartCommand(final Context context, final Intent intent) {
        this.mAuthTask.task.continueWith(new Continuation<Void, Void>() {
            /* class com.oculus.socialplatform.partyservice.PartyServiceManager.AnonymousClass3 */

            @Override // bolts.Continuation
            public Void then(Task<Void> task) throws Exception {
                PartyServiceManager.this.handleStartCommandImpl(context, intent);
                return null;
            }
        });
    }

    @Override // com.oculus.horizon.vr_lifecycle.VRLifecycleManager.VRLifecycle
    public void onHeadsetMounted() {
        synchronized (this.mPartiesStateLock) {
            this.mIsMounted = true;
        }
        this.mPartiesStateManager.enforcePartyChatStateConsistency(false, null, true);
    }

    @Override // com.oculus.horizon.vr_lifecycle.VRLifecycleManager.VRLifecycle
    public void onHeadsetUnmounted() {
        synchronized (this.mPartiesStateLock) {
            this.mIsMounted = false;
        }
        this.mNativeCode.callNativeSetSystemVoipDisabled(true, PartyEventFields.CallerName.REMOUNT);
    }

    public void onPartyServiceCreated() {
        this.mVRLifecycleManager.connect(this, true);
    }

    public void onTearDown() {
        this.mVRLifecycleManager.disconnect(this);
        PartySharedState partySharedState = this.mSharedPartyState;
        partySharedState.mNativeAPI = null;
        partySharedState.onDestroy();
        this.mNativeCode.tearDown();
    }

    @Override // com.oculus.horizon.vr_lifecycle.VRLifecycleManager.VRLifecycle
    public void onVRSessionEnded() {
        synchronized (this.mPartiesStateLock) {
            this.mShouldRestore = true;
            this.mSharedPartyState.recordPartyState();
        }
        this.mNativeCode.callNativePartyChatStop();
    }

    @Override // com.oculus.horizon.vr_lifecycle.VRLifecycleManager.VRLifecycle
    public void onVRSessionStarted() {
    }

    public void setServiceDelegate(IPartyServiceDelegate iPartyServiceDelegate) {
        this.mPartiesStateManager.mPartyServiceDelegate = iPartyServiceDelegate;
        this.mSharedPartyState.mPartyServiceDelegate = iPartyServiceDelegate;
    }

    public PartyServiceManager(Context context, PartyStateManager partyStateManager, SocialPlatformCredentialsManager socialPlatformCredentialsManager, VRLifecycleManager vRLifecycleManager, PartySharedState partySharedState, PartyNativeModuleManager partyNativeModuleManager) {
        this.mContext = context;
        this.mCredentialsManager = socialPlatformCredentialsManager;
        this.mSharedPartyState = partySharedState;
        this.mNativeCode = partyNativeModuleManager;
        socialPlatformCredentialsManager.addLoginHandler(this);
        if (this.mCredentialsManager.getCredentials() != null) {
            afterLoginAsync();
        }
        this.mPartiesStateManager = partyStateManager;
        this.mVRLifecycleManager = vRLifecycleManager;
    }

    public void handleStartCommandImpl(Context context, Intent intent) throws IllegalArgumentException {
        String action = intent.getAction();
        if (action == null) {
            throw new IllegalArgumentException("Intent must have an action");
        } else if (action.equals(SocialPlatformContants.RUN_PARTY_CHAT_ENFORCER_ACTION)) {
            this.mPartiesStateManager.enforcePartyChatStateConsistency();
        } else if (action.equals(SocialPlatformContants.ON_APP_SWITCH_ACTION)) {
            String stringExtra = intent.getStringExtra("package_name");
            boolean booleanExtra = intent.getBooleanExtra("exclusive_mic", false);
            if (stringExtra != null) {
                this.mPartiesStateManager.onAppStart(context, stringExtra, booleanExtra);
                return;
            }
            throw new IllegalArgumentException("App switch intent must have package name");
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void initNativeCode(@Nullable String str) {
        String str2;
        Credentials credentials = this.mCredentialsManager.getCredentials();
        if (credentials == null) {
            str2 = null;
        } else {
            str2 = credentials.mUserId;
        }
        if (str == null) {
            if (credentials == null) {
                str = null;
            } else {
                str = credentials.mAccessToken;
            }
        }
        if (str2 == null || str == null) {
            throw new IllegalArgumentException("PartyServiceManager.initNativeCode called without credentials.  Exploding.");
        }
        this.mNativeCode.maybePerformInit(str2, str, getDeviceConfigClient(), new PartyRemountHandler() {
            /* class com.oculus.socialplatform.partyservice.PartyServiceManager.AnonymousClass2 */

            @Override // com.oculus.socialplatform.partyservice.PartyRemountHandler
            public void restorePartyState() {
                synchronized (PartyServiceManager.this.mPartiesStateLock) {
                    PartyServiceManager partyServiceManager = PartyServiceManager.this;
                    if (partyServiceManager.mShouldRestore) {
                        partyServiceManager.mSharedPartyState.restorePartyState();
                    }
                    PartyServiceManager.this.mShouldRestore = false;
                }
            }
        });
        this.mSharedPartyState.mNativeAPI = this.mNativeCode;
    }

    public void initNativeCode(final long j) {
        this.mCredentialsManager.fetchDeviceScopedAccessToken(new SocialPlatformCredentialsManager.FetchDsatCallBack() {
            /* class com.oculus.socialplatform.partyservice.PartyServiceManager.AnonymousClass1 */

            @Override // com.oculus.socialplatform.auth.SocialPlatformCredentialsManager.FetchDsatCallBack
            public void onFetchedDsat(@Nullable String str) {
                PartyServiceManager.this.initNativeCode((PartyServiceManager) str);
                synchronized (PartyServiceManager.this.mPartiesStateLock) {
                    PartyServiceManager partyServiceManager = PartyServiceManager.this;
                    if (partyServiceManager.mIsMounted) {
                        long currentPartyId = partyServiceManager.mNativeCode.getCurrentPartyId();
                        long j = j;
                        if (!(currentPartyId == j || j == 0)) {
                            PartyServiceManager.this.mNativeCode.callNativePartyChatStart(j);
                        }
                    }
                }
            }
        });
    }
}
