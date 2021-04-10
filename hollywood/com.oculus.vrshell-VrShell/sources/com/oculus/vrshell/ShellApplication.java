package com.oculus.vrshell;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.Image;
import android.media.ImageReader;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.os.UserManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceView;
import com.oculus.android.exoplayer2.upstream.DataSchemeDataSource;
import com.oculus.android.exoplayer2.util.MimeTypes;
import com.oculus.appmanager.info.model.InstallerResult;
import com.oculus.auth.service.contract.ServiceContract;
import com.oculus.authapi.OVRAuth;
import com.oculus.common.auth.AuthManager;
import com.oculus.common.httpclient.GraphQLRequest;
import com.oculus.common.httpclient.HttpClient;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.navigation.NavigationRouter;
import com.oculus.common.packageutils.PackageHelpers;
import com.oculus.ipc.service.CallerIdentityHandshakeServer;
import com.oculus.library.model.InstallerCallback;
import com.oculus.os.ActivityManagerUtils;
import com.oculus.os.UnifiedTelemetryLogger;
import com.oculus.os.enterprise.EnterpriseServer;
import com.oculus.panelapp.dogfood.util.DogfoodNotifications;
import com.oculus.vrshell.PanelServiceConnection;
import com.oculus.vrshell.ShellApplication;
import com.oculus.vrshell.WidgetServiceConnection;
import com.oculus.vrshell.config.BootConfig;
import com.oculus.vrshell.config.EnterpriseConfig;
import com.oculus.vrshell.config.GatekeepersHandler;
import com.oculus.vrshell.config.NativeAppLaunchConfiguration;
import com.oculus.vrshell.config.PanelAppConfiguration;
import com.oculus.vrshell.notifications.INotificationDataSetProvider;
import com.oculus.vrshell.panelservice.PanelVerifier;
import com.oculus.vrshell.panelservice.SigCertVerifier;
import com.oculus.vrshell.systemdialog.SystemDialogDefinition;
import com.oculus.vrshell.util.AndroidSystemProperties;
import com.oculus.vrshell.util.CallerInfoHelper;
import com.oculus.vrshell.util.HorizonUtil;
import com.oculus.vrshell.util.PanelAppConfigurationUtil;
import com.oculus.vrshell.util.PreferenceStringHelper;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import jp.co.omronsoft.iwnnime.ml.iwnn.iWnnEngine;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ShellApplication extends Application implements PanelServiceConnection.PanelServiceConnectionListener, WidgetServiceConnection.WidgetServiceConnectionListener {
    private static final String ACTION_LAUNCH_INTENT_CHANGED = "com.oculus.launch_intent_changed";
    private static final String BROADCAST_ABUSE_RECORDING_OVERLAY = "broadcast_abuse_recording_overlay_show";
    private static final String HORIZON_COMPONENT_NAME = "com.oculus.horizon.service_media.OVRMediaService";
    private static final String HORIZON_PACKAGE_NAME = "com.oculus.horizon";
    private static final String INTENT_KEY_CMD = "intent_cmd";
    private static final String INTENT_KEY_DATA = "intent_data";
    public static final String INTENT_KEY_FROM_PKG = "intent_pkg";
    private static final String INTENT_KEY_URI = "uri";
    private static final String KEY_MESSAGE_TYPE = "message_type";
    private static final String LOCAL_STREAM_PACKAGE = "com.oculus.companion.server";
    private static final String LOCAL_STREAM_PERMISSION = "com.oculus.companion.server.permission.SEND_INTENTS";
    private static final String NOTIFICATION_DATASET_INTENT = "com.oculus.vrshell.notifications.aidl.MAIN";
    public static final String SHELLENV_PACKAGE_NAME = "com.oculus.shellenv";
    private static final String START_VIDEO_CAPTURE_HORIZON_ACTION = "com.oculus.horizon.START_CAPTURE_TO_DISK";
    private static final String STOP_VIDEO_CAPTURE_HORIZON_ACTION = "com.oculus.horizon.STOP_CAPTURE_TO_DISK";
    private static final String SYSTEMUX_SET_SCREENSHOT_ACTION = "com.oculus.systemux.action.SET_SCREENSHOT";
    private static final String SYSTEM_ACTIVITIES_SCREENSHOT_ACTION = "com.oculus.systemactivities.SCREENSHOT";
    private static final String SYSTEM_DRIVER_PACKAGE_NAME = "com.oculus.systemdriver";
    private static final String SYSTEM_UX_PACKAGE_NAME = "com.oculus.systemux";
    private static final String TAG = LoggingUtil.tag(ShellApplication.class);
    private static final String TEMPORARY_EXTRA_EMAIL = "email";
    private static final String TEMPORARY_EXTRA_FB_EMAIL = "facebook_email";
    public static final String VRSHELL_PACKAGE_NAME = "com.oculus.vrshell";
    public static final UUID sAnalyticsSessionId = UUID.randomUUID();
    private static Method sInternalSetSecureMethod;
    private MainActivity mActivity = null;
    private ShellAudioFocusHandler mAudioFocusHandler = null;
    private AuthManager mAuthManager = null;
    private Handler mBgHandler;
    private HandlerThread mBgThread;
    private BootConfig.BootConfigResult mBootConfig = null;
    private String mCachedAppScopedUserToken = null;
    private CredentialsResponse mCachedCredentials = null;
    private IVrClient mClient = null;
    private final Object mClientChangeSynchronization = new Object();
    private final Map<Integer, PanelServiceConnection> mConnectionMap = new HashMap();
    private EnterpriseConfig mEnterpriseConfig;
    private PanelVerifier mFirstPartyVerifier;
    private GatekeepersHandler mGatekeepersHandler = null;
    private final Handler mHandler = new Handler();
    private boolean mHasAvatar = true;
    private NavigationRouter mNavigationRouter = null;
    private int mNextWidgetId = 0;
    private final Map<String, PanelAppConfiguration> mPanelAppConfigurationMap = new HashMap();
    private SurfaceView mSurfaceView;
    private UnifiedTelemetryLogger mUnifiedTelemetryLogger = null;
    private final Map<Integer, WidgetServiceConnection> mWidgetConnectionMap = new HashMap();

    public interface IVrClient {
        Application getApplication();

        Context getApplicationContext();

        HttpClient getHttpClient();

        long getNativeAppPtr();

        IBinder getWindowToken();

        void notifyAppScopedToken(String str);

        void notifyUserStatus(boolean z, boolean z2, String str, String str2);
    }

    public static native void nativeBroadcastIntent(long j, String[] strArr);

    private static native void nativeDestroyWidget(long j, int i);

    private static native void nativeRefreshWidget(long j, int i, int i2, int i3, float f, float f2, float f3, float f4, int i4, int[] iArr);

    /* access modifiers changed from: private */
    public static native void nativeUpdatePackageFailed(long j, int i);

    private static native void nativeUserIdentityAppScopedTokenResponse(long j, String str);

    private static native void nativeUserIdentityResponse(long j, boolean z, boolean z2, String str, String str2, String str3, String str4);

    /* JADX WARNING: Removed duplicated region for block: B:33:0x0075  */
    static {
        /*
        // Method dump skipped, instructions count: 174
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.vrshell.ShellApplication.<clinit>():void");
    }

    /* access modifiers changed from: private */
    public static class CredentialsResponse {
        public boolean isSuccess;
        public boolean isUserLoggedIn;
        public String userAuthEmail;
        public String userAuthFBEmail;
        public String userAuthToken;
        public String userId;

        private CredentialsResponse() {
        }
    }

    public void onCreate() {
        this.mUnifiedTelemetryLogger = UnifiedTelemetryLogger.getInstance(this);
        boolean z = true;
        boolean z2 = !Build.TYPE.equals(ServiceContract.EXTRA_USER);
        this.mFirstPartyVerifier = new PanelVerifier(this, z2);
        if (!z2 || TextUtils.isEmpty(AndroidSystemProperties.getSystemPropertyString("persist.ovr.override_credentials_uid", null))) {
            new OVRAuth(this, new OVRAuth.CallerInfoProvider() {
                /* class com.oculus.vrshell.ShellApplication.AnonymousClass1 */

                @Override // com.oculus.authapi.OVRAuth.CallerInfoProvider
                public Intent attachCallerInfo(Intent intent) {
                    return CallerInfoHelper.attachCallerInfo(intent, ShellApplication.this, "ShellApplication:AuthHelper");
                }
            }).status(new ResultReceiver(null) {
                /* class com.oculus.vrshell.ShellApplication.AnonymousClass2 */

                /* access modifiers changed from: protected */
                public void onReceiveResult(int i, Bundle bundle) {
                    Log.i(ShellApplication.TAG, "ovrAuth status received");
                    synchronized (ShellApplication.this.mClientChangeSynchronization) {
                        ShellApplication.this.mCachedCredentials = new CredentialsResponse();
                        ShellApplication.this.mCachedCredentials.isSuccess = i == -1;
                        ShellApplication.this.mCachedCredentials.isUserLoggedIn = ShellApplication.this.mCachedCredentials.isSuccess;
                        ShellApplication.this.mCachedCredentials.userId = bundle.getString(ServiceContract.EXTRA_USER_ID, null);
                        if (ShellApplication.this.mCachedCredentials.isSuccess) {
                            ShellApplication.this.mCachedCredentials.userAuthToken = bundle.getString("access_token", null);
                        } else {
                            ShellApplication.this.mCachedCredentials.userAuthEmail = bundle.getString("email", null);
                            ShellApplication.this.mCachedCredentials.userAuthFBEmail = bundle.getString("facebook_email", null);
                        }
                        ShellApplication.handleCredentials(ShellApplication.this.mClient, ShellApplication.this.mCachedCredentials);
                    }
                }
            });
        } else {
            this.mCachedCredentials = new CredentialsResponse();
            CredentialsResponse credentialsResponse = this.mCachedCredentials;
            credentialsResponse.isSuccess = true;
            credentialsResponse.userId = AndroidSystemProperties.getSystemPropertyString("persist.ovr.override_credentials_uid", null);
            this.mCachedCredentials.userAuthToken = AndroidSystemProperties.getSystemPropertyString("persist.ovr.override_credentials_token", null);
            CredentialsResponse credentialsResponse2 = this.mCachedCredentials;
            if (TextUtils.isEmpty(credentialsResponse2.userId) || TextUtils.isEmpty(this.mCachedCredentials.userAuthToken)) {
                z = false;
            }
            credentialsResponse2.isUserLoggedIn = z;
        }
        this.mAuthManager = new AuthManager(this, "1031607236937163", new AuthManager.AuthListener() {
            /* class com.oculus.vrshell.ShellApplication.AnonymousClass3 */

            @Override // com.oculus.common.auth.AuthManager.AuthListener
            public void onUserAccessToken(String str) {
                synchronized (ShellApplication.this.mClientChangeSynchronization) {
                    ShellApplication.this.mCachedAppScopedUserToken = str;
                    ShellApplication.this.handleUserAccessToken(ShellApplication.this.mClient, ShellApplication.this.mCachedAppScopedUserToken);
                }
            }

            @Override // com.oculus.common.auth.AuthManager.AuthListener
            public void onUserAccessTokenError(int i, String str) {
                Log.e(ShellApplication.TAG, String.format(Locale.ROOT, "Access token error: %d - %s", Integer.valueOf(i), str));
            }
        });
        handleUserAccessTokenChanged();
        this.mBootConfig = BootConfig.getBootConfig(this);
        this.mAudioFocusHandler = new ShellAudioFocusHandler(getApplicationContext());
        this.mBgThread = new HandlerThread("ShellAppBg");
        this.mBgThread.start();
        this.mBgHandler = new Handler(this.mBgThread.getLooper());
        super.onCreate();
    }

    /* access modifiers changed from: private */
    public static void handleCredentials(IVrClient iVrClient, CredentialsResponse credentialsResponse) {
        if (iVrClient != null && credentialsResponse != null) {
            iVrClient.notifyUserStatus(credentialsResponse.isSuccess, credentialsResponse.isUserLoggedIn, credentialsResponse.userId, credentialsResponse.userAuthToken);
            nativeUserIdentityResponse(iVrClient.getNativeAppPtr(), credentialsResponse.isSuccess, credentialsResponse.isUserLoggedIn, credentialsResponse.userId, credentialsResponse.userAuthToken, credentialsResponse.userAuthEmail, credentialsResponse.userAuthFBEmail);
        }
    }

    public void handleUserAccessTokenChanged() {
        IVrClient iVrClient = this.mClient;
        if (iVrClient != null) {
            iVrClient.notifyAppScopedToken("");
            nativeUserIdentityAppScopedTokenResponse(this.mClient.getNativeAppPtr(), "");
        }
        this.mAuthManager.requestUserAccessToken();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handleUserAccessToken(IVrClient iVrClient, String str) {
        if (iVrClient != null && str != null) {
            iVrClient.notifyAppScopedToken(str);
            nativeUserIdentityAppScopedTokenResponse(iVrClient.getNativeAppPtr(), str);
            updateCommonGraphQLRequests(iVrClient, str);
        }
    }

    private void updateCommonGraphQLRequests(IVrClient iVrClient, String str) {
        GraphQLRequest.get(iVrClient.getHttpClient().getOkHttpClient(), str, "graphql?doc_id=2992430400788127", new GraphQLRequest.GraphQLResponse() {
            /* class com.oculus.vrshell.ShellApplication.AnonymousClass4 */

            @Override // com.oculus.common.httpclient.GraphQLRequest.GraphQLResponse
            public void onFailure(String str) {
                String str2 = ShellApplication.TAG;
                Log.w(str2, "Failed LocalUserInfo request with " + str);
            }

            @Override // com.oculus.common.httpclient.GraphQLRequest.GraphQLResponse
            public void onSuccess(JSONObject jSONObject) {
                try {
                    ShellApplication.this.mHasAvatar = !TextUtils.isEmpty(jSONObject.getJSONObject(DataSchemeDataSource.SCHEME_DATA).getJSONObject("viewer").getJSONObject(ServiceContract.EXTRA_USER).getJSONObject("avatar_v2").getJSONObject("avatar_image").optString(ShellApplication.INTENT_KEY_URI));
                } catch (JSONException unused) {
                    ShellApplication.this.mHasAvatar = true;
                }
            }
        });
    }

    private String gateDUCCommandString(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return str2;
        }
        try {
            boolean z = true;
            if (!new JSONObject(getPreferenceString("Gatekeepers")).optBoolean("oculus_duc_local_cache_gating_mobile", false)) {
                return str2;
            }
            JSONObject jSONObject = new JSONObject(str2);
            if (!jSONObject.has("ovr_social_launch")) {
                return str2;
            }
            JSONObject jSONObject2 = jSONObject.getJSONObject("ovr_social_launch");
            List<String> list = HorizonUtil.getApplication(this, str).accessFeatureKeys;
            boolean contains = list.contains("DEEP_LINKING");
            boolean z2 = list.contains("USER_ID") && contains;
            if (!list.contains("USER_PROFILE") || !contains) {
                z = false;
            }
            if (jSONObject2.opt("users") != null) {
                if (!z) {
                    Log.d(TAG, "sendLaunchIntent: DUC: Clearing User Profiles for app launch.");
                    JSONArray jSONArray = jSONObject2.getJSONArray("users");
                    ArrayList arrayList = new ArrayList();
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONObject jSONObject3 = new JSONObject();
                        jSONObject3.put("id", jSONArray.getJSONObject(i).get("id"));
                        jSONObject3.put("alias", "");
                        arrayList.add(jSONObject3);
                    }
                    jSONObject2.put("users", new JSONArray((Collection) arrayList));
                }
                if (!z2) {
                    Log.d(TAG, "sendLaunchIntent: DUC: Clearing User IDs for app launch.");
                    jSONObject2.put("users", new ArrayList());
                }
            }
            if (!contains && jSONObject2.opt("room_id") != null) {
                Log.d(TAG, "sendLaunchIntent: DUC: Clearing Room ID for app launch.");
                jSONObject2.put("room_id", "0");
            }
            jSONObject.put("ovr_social_launch", jSONObject2);
            return jSONObject.toString();
        } catch (JSONException e) {
            Log.e(TAG, "sendLaunchIntent: Could not parse command string.", e);
            return str2;
        }
    }

    @Override // com.oculus.vrshell.PanelServiceConnection.PanelServiceConnectionListener
    public void onServiceConnected(ComponentName componentName) {
        notifyOsSetPanelState(true, componentName.getPackageName(), componentName.getClassName());
    }

    @Override // com.oculus.vrshell.PanelServiceConnection.PanelServiceConnectionListener
    public void onServiceDisconnected(int i) {
        notifyPanelAppDestruction(i);
    }

    @Override // com.oculus.vrshell.WidgetServiceConnection.WidgetServiceConnectionListener
    public void onWidgetServiceConnected(ComponentName componentName) {
        String str = TAG;
        Log.i(str, "onWidgetServiceConnected:  " + componentName.getPackageName() + ", " + componentName.getClassName());
    }

    @Override // com.oculus.vrshell.WidgetServiceConnection.WidgetServiceConnectionListener
    public void onWidgetRefresh(int i, Bitmap bitmap, float f, float f2, float f3, float f4, WidgetServiceConnection.WidgetPosition widgetPosition) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int i2 = width * height;
        if (i2 > 0) {
            int[] iArr = new int[i2];
            bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
            nativeRefreshWidget(this.mClient.getNativeAppPtr(), i, width, height, f, f2, f3, f4, widgetPosition.ordinal(), iArr);
        }
    }

    @Override // com.oculus.vrshell.WidgetServiceConnection.WidgetServiceConnectionListener
    public void onWidgetServiceDisconnected(int i) {
        notifyWidgetDestruction(i);
    }

    public void setVrClient(IVrClient iVrClient) {
        synchronized (this.mClientChangeSynchronization) {
            this.mClient = iVrClient;
            this.mGatekeepersHandler = new GatekeepersHandler(iVrClient);
            handleCredentials(this.mClient, this.mCachedCredentials);
            handleUserAccessToken(this.mClient, this.mCachedAppScopedUserToken);
            handleDogfoodChecks(this.mClient);
        }
    }

    private void handleDogfoodChecks(IVrClient iVrClient) {
        boolean z = iVrClient instanceof ShellOverlayService;
        if (this.mBootConfig.isEligibleForDogfood && !this.mBootConfig.isAutomationEnabled && !z) {
            this.mBgHandler.postDelayed(new Runnable(iVrClient) {
                /* class com.oculus.vrshell.$$Lambda$ShellApplication$b0GFqSzlmPNHMqnLShPaNv4dF0c */
                private final /* synthetic */ ShellApplication.IVrClient f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    ShellApplication.this.lambda$handleDogfoodChecks$0$ShellApplication(this.f$1);
                }
            }, 15000);
        }
    }

    public /* synthetic */ void lambda$handleDogfoodChecks$0$ShellApplication(IVrClient iVrClient) {
        DogfoodNotifications.checkForDogfoodUpdates(this, iVrClient.getHttpClient().getOkHttpClient(), this.mCachedAppScopedUserToken);
    }

    public BootConfig.BootConfigResult getBootConfig() {
        return this.mBootConfig;
    }

    public IVrClient getVrClient() {
        return this.mClient;
    }

    public NavigationRouter getNavigationRouter() {
        if (this.mNavigationRouter == null) {
            this.mNavigationRouter = new NavigationRouter(getApplicationContext(), this.mBootConfig.isShellEnvEnabled);
        }
        return this.mNavigationRouter;
    }

    public void refreshFromExternalStates() {
        GatekeepersHandler gatekeepersHandler = this.mGatekeepersHandler;
        if (gatekeepersHandler == null) {
            Log.w(TAG, "refreshFromExternalStates called early");
        } else {
            gatekeepersHandler.updateGatekeepers();
        }
    }

    public void setActivityContext(MainActivity mainActivity) {
        this.mActivity = mainActivity;
        MainActivity mainActivity2 = this.mActivity;
        if (mainActivity2 != null) {
            this.mSurfaceView = new SurfaceView(mainActivity2);
        } else {
            this.mSurfaceView = null;
        }
    }

    public UnifiedTelemetryLogger getUnifiedTelemetryLogger() {
        return this.mUnifiedTelemetryLogger;
    }

    public PanelVerifier getFirstPartyAppVerifier() {
        return this.mFirstPartyVerifier;
    }

    public SurfaceView getSurfaceView() {
        return this.mSurfaceView;
    }

    public void registerCaptivePortalHandler() {
        ((ConnectivityManager) getApplicationContext().getSystemService("connectivity")).registerNetworkCallback(new NetworkRequest.Builder().addTransportType(1).build(), new ConnectivityManager.NetworkCallback() {
            /* class com.oculus.vrshell.ShellApplication.AnonymousClass5 */
            private boolean inCaptivePortal = false;

            public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
                if (!this.inCaptivePortal && networkCapabilities.hasCapability(17)) {
                    this.inCaptivePortal = true;
                    Log.d(ShellApplication.TAG, "Wi-Fi Captive Portal detected");
                    Intent intent = new Intent("com.oculus.vrshell.intent.action.LAUNCH");
                    intent.setPackage("com.oculus.vrshell");
                    intent.putExtra(ShellApplication.INTENT_KEY_DATA, Uri.parse("systemux://captive-wifi-portal"));
                    intent.putExtra(ShellApplication.INTENT_KEY_URI, "ovrweb://webtask?uri=http%3A%2F%2Fclients3.google.com%2Fgenerate_204");
                    ShellApplication.this.sendBroadcast(intent);
                } else if (this.inCaptivePortal && !networkCapabilities.hasCapability(17)) {
                    this.inCaptivePortal = false;
                }
            }
        });
    }

    public void startVrNotificationService() {
        startService(new Intent(getApplicationContext(), VrNotificationService.class));
    }

    public boolean isTrustedAppLaunch(Intent intent) {
        int i;
        RemoteException e;
        if (intent != null && "app_launch".equals(intent.getAction())) {
            try {
                i = new CallerIdentityHandshakeServer().getCallingUid(intent.getExtras());
                try {
                    Log.d(TAG, "Verifying caller for app launch, uid = " + i);
                } catch (RemoteException e2) {
                    e = e2;
                }
            } catch (RemoteException e3) {
                e = e3;
                i = -1;
                Log.e(TAG, "Failed to verify identity of calling application for app launch flow.", e);
                if (i != -1) {
                }
                return false;
            }
            if (i != -1 || !isTrustedAppLaunchSource(i)) {
                return false;
            }
            return true;
        }
        return false;
    }

    private boolean isTrustedAppLaunchSource(int i) {
        SigCertVerifier.SigCertInfo checkApkSignature = getFirstPartyAppVerifier().checkApkSignature(i);
        String str = TAG;
        Log.d(str, "Verifying caller for app launch = " + checkApkSignature);
        return checkApkSignature.isTrusted;
    }

    public String getSystemDialogDefinition(String str, String str2) {
        return SystemDialogDefinition.getSystemDialogDefinition(this, str, str2);
    }

    public String getPreferenceString(String str) {
        return PreferenceStringHelper.getPreferenceString(this, str);
    }

    public void setPreferenceString(String str, String str2) {
        PreferenceStringHelper.setPreferenceString(this, str, str2);
    }

    public void cacheServerGKs(String str) throws JSONException {
        BootConfig.cacheServerGKs(this, this.mUnifiedTelemetryLogger, this.mBootConfig.isAutomationEnabled, str);
    }

    public void cacheOverrideGK(String str, boolean z) {
        BootConfig.cacheOverrideGK(this, str, z);
    }

    public void clearOverrideGK(String str) {
        BootConfig.clearOverrideGK(this, str);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0027  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0030  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void clearCachedGKs(java.lang.String r4) {
        /*
            r3 = this;
            int r0 = r4.hashCode()
            r1 = -197461524(0xfffffffff43af9ec, float:-5.925514E31)
            r2 = 1
            if (r0 == r1) goto L_0x001a
            r1 = 833410243(0x31acd4c3, float:5.030047E-9)
            if (r0 == r1) goto L_0x0010
            goto L_0x0024
        L_0x0010:
            java.lang.String r0 = "overrideGKs"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0024
            r4 = r2
            goto L_0x0025
        L_0x001a:
            java.lang.String r0 = "serverGKs"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0024
            r4 = 0
            goto L_0x0025
        L_0x0024:
            r4 = -1
        L_0x0025:
            if (r4 == 0) goto L_0x0030
            if (r4 == r2) goto L_0x002a
            goto L_0x0035
        L_0x002a:
            com.oculus.vrshell.config.BootConfig$CachedGKsType r4 = com.oculus.vrshell.config.BootConfig.CachedGKsType.OverrideGKs
            com.oculus.vrshell.config.BootConfig.clearCachedGKs(r3, r4)
            goto L_0x0035
        L_0x0030:
            com.oculus.vrshell.config.BootConfig$CachedGKsType r4 = com.oculus.vrshell.config.BootConfig.CachedGKsType.ServerGKs
            com.oculus.vrshell.config.BootConfig.clearCachedGKs(r3, r4)
        L_0x0035:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.vrshell.ShellApplication.clearCachedGKs(java.lang.String):void");
    }

    public void sendNotifyExitToShell(String str) {
        String packageNameForComponent = getPackageNameForComponent(str);
        Intent intent = new Intent("com.oculus.system_activity");
        intent.setPackage(packageNameForComponent);
        intent.putExtra(INTENT_KEY_FROM_PKG, getPackageName());
        Context applicationContext = getApplicationContext();
        CallerInfoHelper.attachCallerInfo(intent, applicationContext, getClass().getSimpleName() + ":sendNotifyExitToShell()");
        intent.putExtra(INTENT_KEY_CMD, createSystemActivitiesIntentCmd("exitToHome", packageNameForComponent));
        sendBroadcast(intent);
        intent.putExtra(INTENT_KEY_CMD, createSystemActivitiesIntentCmd("returnToLauncher", packageNameForComponent));
        sendBroadcast(intent);
        String str2 = TAG;
        Log.i(str2, "Sent exit broadcast to SystemActivities for:  " + packageNameForComponent);
    }

    public boolean isPrimaryUser() {
        return ((UserManager) getSystemService(ServiceContract.EXTRA_USER)).isSystemUser();
    }

    public void shutdown() {
        MainActivity mainActivity = this.mActivity;
        if (mainActivity != null) {
            mainActivity.shutdown();
            this.mActivity = null;
            this.mEnterpriseConfig = null;
        }
    }

    public void shutdownWhenIdle(long j) {
        MainActivity mainActivity = this.mActivity;
        if (mainActivity != null) {
            mainActivity.shutdownWhenIdle(j);
        }
    }

    public void refreshWidgets() {
        synchronized (this.mWidgetConnectionMap) {
            for (Map.Entry<Integer, WidgetServiceConnection> entry : this.mWidgetConnectionMap.entrySet()) {
                entry.getValue().sendWidgetRefreshMessage();
            }
        }
    }

    public int sendWidgetLaunchIntent(String str, String str2) {
        int i = this.mNextWidgetId;
        this.mNextWidgetId = i + 1;
        this.mHandler.post(new Runnable(str, str2, i) {
            /* class com.oculus.vrshell.$$Lambda$ShellApplication$qpOXCFktieBIBrDqDYg0BmjQNw8 */
            private final /* synthetic */ String f$1;
            private final /* synthetic */ String f$2;
            private final /* synthetic */ int f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            public final void run() {
                ShellApplication.this.lambda$sendWidgetLaunchIntent$1$ShellApplication(this.f$1, this.f$2, this.f$3);
            }
        });
        return i;
    }

    public /* synthetic */ void lambda$sendWidgetLaunchIntent$1$ShellApplication(String str, String str2, int i) {
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(str, str2));
            WidgetServiceConnection widgetServiceConnection = new WidgetServiceConnection(i, this, this.mFirstPartyVerifier, getPackageManager());
            synchronized (this.mWidgetConnectionMap) {
                String str3 = TAG;
                Log.d(str3, "widget bindService " + intent);
                bindService(intent, widgetServiceConnection, 1);
                this.mWidgetConnectionMap.put(Integer.valueOf(i), widgetServiceConnection);
            }
        } catch (Exception e) {
            Log.e(TAG, "sendWidgetLaunchIntent threw exception ", e);
        }
    }

    public String[] getWidgetComponentsToAutoLaunch(String str) {
        Map<String, String> allStringPreferencesWithPrefix = PreferenceStringHelper.getAllStringPreferencesWithPrefix(this, str);
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, String> entry : allStringPreferencesWithPrefix.entrySet()) {
            if (entry.getValue().equals("true")) {
                arrayList.add(entry.getKey().split(str)[1]);
            }
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    public boolean sendApkPanelLaunchIntent(String str, String str2, int i, Surface surface, HashMap hashMap, int i2, String[] strArr) {
        String str3 = str + "/" + str2;
        PanelAppConfiguration panelAppConfiguration = this.mPanelAppConfigurationMap.get(str3);
        if (panelAppConfiguration == null) {
            try {
                Log.d(TAG, "sendApkPanelLaunchIntent unable to find a configuration for component" + str3);
                return false;
            } catch (Exception e) {
                Log.e(TAG, "sendApkPanelLaunchIntent threw exception ", e);
                return false;
            }
        } else if (!panelAppConfiguration.isValidService()) {
            Log.d(TAG, "sendApkPanelLaunchIntent unable to find a compliant service intent for package " + panelAppConfiguration.getPackageName());
            return false;
        } else {
            Log.d(TAG, String.format("sendApkPanelLaunchIntent: packageName=%s, serviceClassName=%s", str, str2));
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(str, str2));
            Bundle bundle = new Bundle();
            if (strArr.length % 2 == 0) {
                for (int i3 = 0; i3 < strArr.length - 1; i3 += 2) {
                    String str4 = strArr[i3];
                    String str5 = strArr[i3 + 1];
                    if (!bundle.containsKey(str4)) {
                        bundle.putString(str4, str5);
                    } else {
                        throw new IllegalArgumentException("Duplicate key name found in environment. Key " + str4 + " with values '" + bundle.getString(str4) + "' and '" + str5 + "'. Unable to continue launching the panel app.");
                    }
                }
                PanelServiceConnection panelServiceConnection = new PanelServiceConnection(this.mUnifiedTelemetryLogger, this.mFirstPartyVerifier, i, surface, hashMap, panelAppConfiguration, i2, bundle, this, getContentResolver(), this.mClient.getWindowToken(), this);
                synchronized (this.mConnectionMap) {
                    Log.d(TAG, "bindService " + intent);
                    bindService(intent, panelServiceConnection, 1);
                    notifyOsSetPanelState(true, str, str2);
                    this.mConnectionMap.put(Integer.valueOf(i), panelServiceConnection);
                }
                return true;
            }
            throw new IllegalArgumentException("Environment strings are malformed. Not all keys are paired with values.");
        }
    }

    public void sendBroadcastShowRecordingOverlayIntent() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.oculus.horizon", HORIZON_COMPONENT_NAME));
        intent.putExtra(KEY_MESSAGE_TYPE, BROADCAST_ABUSE_RECORDING_OVERLAY);
        CallerInfoHelper.attachCallerInfo(intent, this, MainActivity.class.getSimpleName() + ":sendBroadcastShowRecordingOverlayIntent");
        startService(intent);
        Log.d(TAG, "sendBroadcastShowRecordingOverlayIntent: sent");
    }

    public void notifyPanelAppDestruction(int i) {
        PanelServiceConnection remove;
        synchronized (this.mConnectionMap) {
            remove = this.mConnectionMap.remove(Integer.valueOf(i));
        }
        if (remove != null) {
            String str = TAG;
            Log.d(str, "Unbinding service for panel app id " + i);
            unbindService(remove);
            return;
        }
        String str2 = TAG;
        Log.w(str2, "Could not find active binder connection for panel app id " + i);
    }

    public void notifyWidgetDestruction(int i) {
        WidgetServiceConnection remove;
        synchronized (this.mWidgetConnectionMap) {
            remove = this.mWidgetConnectionMap.remove(Integer.valueOf(i));
        }
        if (remove != null) {
            String str = TAG;
            Log.d(str, "Unbinding service for widget id " + i);
            unbindService(remove);
            nativeDestroyWidget(this.mClient.getNativeAppPtr(), i);
            return;
        }
        String str2 = TAG;
        Log.w(str2, "Could not find active binder connection for widget id " + i);
    }

    public NativeAppLaunchConfiguration getNativeAppLaunchConfigurationForPackage(String str) throws PackageManager.NameNotFoundException {
        PackageManager packageManager = getPackageManager();
        NativeAppLaunchConfiguration nativeAppLaunchConfiguration = new NativeAppLaunchConfiguration();
        nativeAppLaunchConfiguration.setIsVrApplication(PackageHelpers.isVrApp(packageManager, str));
        if (!nativeAppLaunchConfiguration.isVrApplication()) {
            Intent leanbackLaunchIntentForPackage = packageManager.getLeanbackLaunchIntentForPackage(str);
            if (leanbackLaunchIntentForPackage != null) {
                nativeAppLaunchConfiguration.setIsTvApplication(true);
                nativeAppLaunchConfiguration.setLaunchComponent(leanbackLaunchIntentForPackage.getComponent().flattenToString());
            } else {
                Intent launchIntentForPackage = packageManager.getLaunchIntentForPackage(str);
                if (launchIntentForPackage != null) {
                    nativeAppLaunchConfiguration.setLaunchComponent(launchIntentForPackage.getComponent().flattenToString());
                }
            }
        }
        return nativeAppLaunchConfiguration;
    }

    public String getPackageNameForComponent(String str) {
        ResolveInfo resolveActivity;
        ComponentName unflattenFromString = ComponentName.unflattenFromString(str);
        return (unflattenFromString == null || (resolveActivity = getPackageManager().resolveActivity(new Intent().setComponent(unflattenFromString), 0)) == null || resolveActivity.activityInfo == null) ? str : resolveActivity.activityInfo.packageName;
    }

    public PanelAppConfiguration getPanelAppConfiguration(String str, String str2) {
        try {
            return PanelAppConfigurationUtil.buildPanelAppConfiguration(str, str2, this.mPanelAppConfigurationMap, getPackageManager());
        } catch (PackageManager.NameNotFoundException unused) {
            return new PanelAppConfiguration.Builder().withPackageName(str).build();
        } catch (IllegalArgumentException unused2) {
            return new PanelAppConfiguration.Builder().withPackageName(str).build();
        } catch (Exception e) {
            throw new RuntimeException(String.format("Unexpected exception while calling buildPanelAppConfiguration for %s/%s", str, str2), e);
        }
    }

    public void adjustVolume(int i) {
        ((AudioManager) getSystemService(MimeTypes.BASE_TYPE_AUDIO)).adjustStreamVolume(3, i, 0);
    }

    public boolean sendLaunchIntent(String str, String str2) {
        String str3 = TAG;
        Log.d(str3, "sendLaunchIntent: '" + str + "' command: '" + str2 + "'");
        ComponentName unflattenFromString = ComponentName.unflattenFromString(str);
        Intent launchIntentForPackage = getPackageManager().getLaunchIntentForPackage(unflattenFromString != null ? unflattenFromString.getPackageName() : str);
        if (launchIntentForPackage == null) {
            Log.d(TAG, "sendLaunchIntent: null destination activity");
            return false;
        }
        String gateDUCCommandString = gateDUCCommandString(str, str2);
        launchIntentForPackage.putExtra(INTENT_KEY_CMD, gateDUCCommandString);
        launchIntentForPackage.putExtra(INTENT_KEY_FROM_PKG, getPackageName());
        sendLaunchIntentChangedBroadcast(this, launchIntentForPackage);
        try {
            launchIntentForPackage.addFlags(65536);
            launchIntentForPackage.addFlags(iWnnEngine.WNNWORD_ATTRIBUTE_CORRECTION_WORD);
            if (!(!TextUtils.isEmpty(gateDUCCommandString))) {
                if (maybeLaunchFromRecents(unflattenFromString != null ? new Intent().setComponent(unflattenFromString).setFlags(launchIntentForPackage.getFlags()) : launchIntentForPackage)) {
                    return true;
                }
            }
            String str4 = TAG;
            Log.d(str4, "startActivity " + launchIntentForPackage);
            startActivity(launchIntentForPackage);
            return true;
        } catch (ActivityNotFoundException unused) {
            String str5 = TAG;
            Log.d(str5, "startActivity " + launchIntentForPackage + " not found!");
            return false;
        } catch (Exception e) {
            String str6 = TAG;
            Log.e(str6, "sendLaunchIntent threw exception " + e);
            return false;
        }
    }

    public boolean sendLaunchShellActivityIntent(String str, String str2, String str3) {
        Intent intent;
        String str4 = TAG;
        Log.d(str4, "sendLaunchShellActivityIntent- panelComponent: '" + str + "' uri: '" + str2 + "'");
        if (this.mBootConfig.isShellEnvEnabled) {
            intent = getPackageManager().getLaunchIntentForPackage("com.oculus.shellenv");
        } else {
            intent = getPackageManager().getLaunchIntentForPackage("com.oculus.vrshell");
        }
        intent.putExtra(INTENT_KEY_DATA, str);
        intent.putExtra(INTENT_KEY_URI, str2);
        intent.putExtra(INTENT_KEY_FROM_PKG, str3);
        try {
            intent.addFlags(65536);
            intent.addFlags(iWnnEngine.WNNWORD_ATTRIBUTE_CORRECTION_WORD);
            String str5 = TAG;
            Log.d(str5, "startActivity " + intent);
            startActivity(intent);
            return true;
        } catch (ActivityNotFoundException unused) {
            String str6 = TAG;
            Log.d(str6, "startActivity " + intent + " not found!");
            return false;
        } catch (Exception e) {
            String str7 = TAG;
            Log.e(str7, "sendLaunchIntent threw exception " + e);
            return false;
        }
    }

    private static void sendLaunchIntentChangedBroadcast(Context context, Intent intent) {
        Intent intent2 = new Intent(ACTION_LAUNCH_INTENT_CHANGED);
        intent2.setPackage(intent.getPackage());
        intent2.putExtras(intent.getExtras());
        context.sendBroadcast(intent2);
    }

    public boolean isPackageInstalled(String str) {
        try {
            getPackageManager().getPackageInfo(str, 0);
            return true;
        } catch (Exception unused) {
            String str2 = TAG;
            Log.i(str2, "Could not get package info for (" + str + ")");
            return false;
        }
    }

    public void updatePackageCurrencyInLibrary(String str) {
        this.mBgHandler.post(new Runnable(str) {
            /* class com.oculus.vrshell.$$Lambda$ShellApplication$MwipZhWUq4PNcbd8qHHi96b7M */
            private final /* synthetic */ String f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                ShellApplication.this.lambda$updatePackageCurrencyInLibrary$2$ShellApplication(this.f$1);
            }
        });
    }

    public /* synthetic */ void lambda$updatePackageCurrencyInLibrary$2$ShellApplication(String str) {
        HorizonUtil.markIsSeen(this, str);
        HorizonUtil.markRecentActivity(this, str);
    }

    public void uninstallPackage(String str) {
        HorizonUtil.uninstallPackage(this, str);
    }

    public void updatePackage(String str) {
        HorizonUtil.updatePackage(this, str, new InstallerCallback() {
            /* class com.oculus.vrshell.ShellApplication.AnonymousClass6 */

            @Override // com.oculus.library.model.InstallerCallback
            public void onInstallerResult(InstallerResult installerResult) {
                if (installerResult.error != null) {
                    ShellApplication.nativeUpdatePackageFailed(ShellApplication.this.mClient.getNativeAppPtr(), installerResult.error.code);
                }
            }
        });
    }

    public void restoreOfficialPackage(String str) {
        HorizonUtil.restoreOfficialPackage(this, str);
    }

    public String getPackageApplicationId(String str) {
        return HorizonUtil.getApplicationId(this, str);
    }

    public void notifyForegroundPanel(String str, String str2) {
        Intent intent = new Intent("com.oculus.horizon.SET_NEW_PANEL_ACTIVE");
        intent.setPackage("com.oculus.horizon");
        intent.putExtra("package_name", str);
        intent.putExtra("service_name", str2);
        sendBroadcast(intent);
    }

    public boolean hasNetworkConnectivity() {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
                return false;
            }
            return true;
        } catch (Exception unused) {
            Log.d(TAG, "hasNetworkConnectivity exception: Make sure android.permission.ACCESS_NETWORK_STATE is set in the manifest.");
            return false;
        }
    }

    public void updateAssistantPanelMode(int i) {
        AssistantContentProvider.storePanelMode(getApplicationContext(), i);
    }

    public void forwardClientUxRouteToAssistant(String str) {
        Intent intent = new Intent("com.oculus.assistant.ROUTE");
        intent.putExtra(INTENT_KEY_URI, str);
        intent.setPackage("com.oculus.assistant");
        sendBroadcast(intent);
        Log.d(TAG, "Forwarding route to Assistant Client.");
    }

    public void setSurfaceSecureFlag(final boolean z) {
        this.mHandler.post(new Runnable() {
            /* class com.oculus.vrshell.ShellApplication.AnonymousClass7 */

            public void run() {
                if (ShellApplication.this.mSurfaceView != null) {
                    if (ShellApplication.sInternalSetSecureMethod != null) {
                        try {
                            ShellApplication.sInternalSetSecureMethod.invoke(ShellApplication.this.mSurfaceView, Boolean.valueOf(z));
                            return;
                        } catch (Exception e) {
                            Log.w(ShellApplication.TAG, "Error setting the secure flag", e);
                        }
                    }
                    ShellApplication.this.mSurfaceView.setSecure(z);
                }
            }
        });
    }

    public void notifyOsSetPanelState(boolean z, String str, String str2) {
        String str3 = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("notify OS to set ");
        sb.append(str);
        sb.append("/");
        sb.append(str2);
        sb.append(" to ");
        sb.append(z ? "FOREGROUND" : "BACKGROUND");
        Log.d(str3, sb.toString());
        new ActivityManagerUtils().setIsForegroundPanelService(z, new ComponentName(str, str2));
    }

    public void sendGuardianHandTrackingNotification(String str, int i) {
        Intent intent = new Intent("com.oculus.vrshell.intent.action.GUARDIAN");
        intent.putExtra(INTENT_KEY_DATA, GuardianController.GUARDIAN_NOTIF_HAND_TRACKING);
        intent.putExtra(INTENT_KEY_FROM_PKG, str);
        intent.putExtra("guardian_action_type", i);
        intent.setPackage("com.oculus.vrshell");
        intent.addFlags(65536);
        sendBroadcast(intent);
    }

    public void suppressToasts() {
        AnonymousClass8 r0 = new ServiceConnection() {
            /* class com.oculus.vrshell.ShellApplication.AnonymousClass8 */

            public void onServiceDisconnected(ComponentName componentName) {
            }

            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                try {
                    INotificationDataSetProvider.Stub.asInterface(iBinder).suppressToasts();
                } catch (RemoteException e) {
                    String str = ShellApplication.TAG;
                    Log.e(str, "suppressToasts() failed with RemoteException: " + e);
                } catch (Throwable th) {
                    ShellApplication.this.getApplicationContext().unbindService(this);
                    throw th;
                }
                ShellApplication.this.getApplicationContext().unbindService(this);
            }
        };
        Intent intent = new Intent(NOTIFICATION_DATASET_INTENT);
        intent.setPackage("com.oculus.vrshell");
        getApplicationContext().bindService(intent, r0, 1);
    }

    public void unsuppressToasts() {
        AnonymousClass9 r0 = new ServiceConnection() {
            /* class com.oculus.vrshell.ShellApplication.AnonymousClass9 */

            public void onServiceDisconnected(ComponentName componentName) {
            }

            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                try {
                    INotificationDataSetProvider.Stub.asInterface(iBinder).unsuppressToasts();
                } catch (RemoteException e) {
                    String str = ShellApplication.TAG;
                    Log.e(str, "unsuppressToasts() failed with RemoteException: " + e);
                } catch (Throwable th) {
                    ShellApplication.this.getApplicationContext().unbindService(this);
                    throw th;
                }
                ShellApplication.this.getApplicationContext().unbindService(this);
            }
        };
        Intent intent = new Intent(NOTIFICATION_DATASET_INTENT);
        intent.setPackage("com.oculus.vrshell");
        getApplicationContext().bindService(intent, r0, 1);
    }

    public BluetoothDeviceInfo[] getConnectedBluetoothDeviceInfos() {
        List<BluetoothDevice> connectedDevices = ((BluetoothManager) getApplicationContext().getSystemService("bluetooth")).getConnectedDevices(7);
        BluetoothDeviceInfo[] bluetoothDeviceInfoArr = new BluetoothDeviceInfo[connectedDevices.size()];
        int i = 0;
        for (BluetoothDevice bluetoothDevice : connectedDevices) {
            bluetoothDeviceInfoArr[i] = new BluetoothDeviceInfo(bluetoothDevice.getName(), bluetoothDevice.getAddress(), Integer.toString(bluetoothDevice.getBluetoothClass().getDeviceClass()), Integer.toString(bluetoothDevice.getBluetoothClass().getMajorDeviceClass()), Integer.toString(bluetoothDevice.getType()));
            i++;
        }
        return bluetoothDeviceInfoArr;
    }

    public EnterpriseConfig getEnterpriseConfiguration() {
        if (!EnterpriseServer.isInEnterpriseMode(getApplicationContext())) {
            return null;
        }
        if (this.mEnterpriseConfig == null) {
            this.mEnterpriseConfig = new EnterpriseConfig(getApplicationContext());
        }
        return this.mEnterpriseConfig;
    }

    public byte[] openFileContentUri(String str) {
        try {
            InputStream openInputStream = getContentResolver().openInputStream(Uri.parse(str));
            byte[] bArr = new byte[4096];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
                int read = openInputStream.read(bArr);
                if (read == -1) {
                    return byteArrayOutputStream.toByteArray();
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
        } catch (FileNotFoundException unused) {
            Log.w(TAG, String.format("VrShell did not have appropriate permissions to retrieve content URI %s", str));
            return null;
        } catch (IOException unused2) {
            Log.w(TAG, String.format("Failed to read InputStream when retrieving content URI %s", str));
            return null;
        }
    }

    public Context getInputFocusTokenViewContext() {
        Log.d(TAG, "getInputFocusTokenViewContext");
        IVrClient iVrClient = this.mClient;
        if (iVrClient instanceof BaseOverlayService) {
            return ((BaseOverlayService) iVrClient).getInputFocusTokenViewContext();
        }
        Log.e(TAG, "getInputFocusServiceView with invalid client");
        return null;
    }

    public boolean isDeviceLocked() {
        Log.d(TAG, "ShellApplication isDeviceLocked");
        IVrClient iVrClient = this.mClient;
        if (iVrClient instanceof ShellOverlayService) {
            return ((ShellOverlayService) iVrClient).isDeviceLocked();
        }
        return false;
    }

    public void setOverlayInputFocus(boolean z, boolean z2) {
        String str = TAG;
        Log.d(str, "setOverlayInputFocus: inputFocus=" + z + " shouldSupportIME=" + z2);
        IVrClient iVrClient = this.mClient;
        if (iVrClient instanceof BaseOverlayService) {
            ((BaseOverlayService) iVrClient).setOverlayInputFocus(z, z2);
        } else {
            Log.e(TAG, "setOverlayInputFocus with invalid client");
        }
    }

    public void sendNamedNotification(String str) {
        NotificationHelper.sendNamedNotification(this, str);
    }

    public void sendPackageLaunchIntent(String str) {
        if (TextUtils.isEmpty(str)) {
            Log.e(TAG, "sendPackageLaunchIntent got empty package name");
            return;
        }
        try {
            Intent launchIntentForPackage = getPackageManager().getLaunchIntentForPackage(str);
            if (launchIntentForPackage == null) {
                Log.e(TAG, "sendPackageLaunchIntent no launch intent for package name");
            } else {
                startActivity(launchIntentForPackage);
            }
        } catch (Exception e) {
            Log.e(TAG, "sendPackageLaunchIntent failed to start activity", e);
        }
    }

    public void broadcastBugReportScreenshotIntent() {
        Intent intent = new Intent(SYSTEM_ACTIVITIES_SCREENSHOT_ACTION);
        intent.setPackage("com.oculus.systemdriver");
        ImageReader newInstance = ImageReader.newInstance(1024, 1024, 1, 1);
        intent.putExtra("surface", newInstance.getSurface());
        intent.putExtra(INTENT_KEY_FROM_PKG, "com.oculus.vrshell");
        intent.putExtra("delayInSeconds", 0.0d);
        try {
            CallerInfoHelper.attachCallerInfo(intent, this, ":onReceive()");
            newInstance.setOnImageAvailableListener(new ImageReader.OnImageAvailableListener() {
                /* class com.oculus.vrshell.$$Lambda$ShellApplication$sNwaPn2JdhsteNP_iZin1B2Ny4I */

                public final void onImageAvailable(ImageReader imageReader) {
                    ShellApplication.this.lambda$broadcastBugReportScreenshotIntent$3$ShellApplication(imageReader);
                }
            }, this.mHandler);
            sendBroadcast(intent);
        } catch (Exception e) {
            Log.e(TAG, "Cannot attach callerInfo", e);
        }
    }

    public /* synthetic */ void lambda$broadcastBugReportScreenshotIntent$3$ShellApplication(ImageReader imageReader) {
        Log.d(TAG, "onImageAvailable");
        Image acquireLatestImage = imageReader.acquireLatestImage();
        if (acquireLatestImage != null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ConvertImageToJpg(acquireLatestImage.getWidth(), acquireLatestImage.getHeight(), 85, byteArrayOutputStream, acquireLatestImage.getPlanes());
            Intent intent = new Intent(SYSTEMUX_SET_SCREENSHOT_ACTION);
            intent.setPackage("com.oculus.systemux");
            intent.putExtra("image_data", byteArrayOutputStream.toByteArray());
            sendBroadcast(intent);
        }
    }

    public void killBackgroundProcesses(String str) {
        ((ActivityManager) getApplicationContext().getSystemService("activity")).killBackgroundProcesses(str);
    }

    public boolean requestAudioFocus(boolean z) {
        String str = TAG;
        Log.d(str, "requestAudioFocus, transientFocus=" + z);
        return this.mAudioFocusHandler.requestFocus(z);
    }

    public void abandonAudioFocus() {
        Log.d(TAG, "abandonAudioFocus");
        this.mAudioFocusHandler.abandonFocus();
    }

    public boolean hasAudioFocus() {
        return this.mAudioFocusHandler.hasFocus();
    }

    public boolean checkIsPWA(String str) {
        return PackageHelpers.getPackageMetaDataString(getPackageManager(), str, PackageHelpers.METADATA_PWA_WEB_MANIFEST_URL_KEY) != null;
    }

    public void requestRemoteKeyboard() {
        IVrClient iVrClient = this.mClient;
        if (iVrClient != null && this.mCachedAppScopedUserToken != null) {
            OkHttpClient okHttpClient = iVrClient.getHttpClient().getOkHttpClient();
            String str = this.mCachedAppScopedUserToken;
            GraphQLRequest.post(okHttpClient, str, "remote_keyboard_open?device_serial=" + this.mBootConfig.deviceSerial, new FormBody.Builder().build(), new GraphQLRequest.GraphQLResponse() {
                /* class com.oculus.vrshell.ShellApplication.AnonymousClass10 */

                @Override // com.oculus.common.httpclient.GraphQLRequest.GraphQLResponse
                public void onSuccess(JSONObject jSONObject) {
                }

                @Override // com.oculus.common.httpclient.GraphQLRequest.GraphQLResponse
                public void onFailure(String str) {
                    String str2 = ShellApplication.TAG;
                    Log.w(str2, "Failed remote keyboard open with error " + str);
                }
            });
        }
    }

    private static void ConvertImageToJpg(int i, int i2, int i3, OutputStream outputStream, Image.Plane[] planeArr) {
        ByteBuffer buffer = planeArr[0].getBuffer();
        if (buffer == null) {
            Log.e(TAG, "No image data in buffer.");
            return;
        }
        byte[] bArr = new byte[buffer.capacity()];
        buffer.get(bArr);
        int pixelStride = planeArr[0].getPixelStride();
        int rowStride = planeArr[0].getRowStride() - (pixelStride * i);
        int i4 = i * i2;
        int[] iArr = new int[i4];
        int i5 = 0;
        for (int i6 = 0; i6 < i4; i6++) {
            iArr[i6] = ((bArr[i5] & 255) << 16) | ((bArr[i5 + 1] & 255) << 8) | ((bArr[i5 + 2] & 255) << 0) | ((bArr[i5 + 3] & 255) << 24);
            i5 += pixelStride;
            if (i6 % i == 0 && i6 != 0) {
                i5 += rowStride;
            }
        }
        Bitmap createBitmap = Bitmap.createBitmap(iArr, i, i2, Bitmap.Config.ARGB_8888);
        createBitmap.setHasAlpha(false);
        createBitmap.compress(Bitmap.CompressFormat.JPEG, i3, outputStream);
    }

    private String createSystemActivitiesIntentCmd(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("Command", str);
            jSONObject.put("PlatformUIVersion", 4);
            jSONObject.put("ToPackage", str2);
            return jSONObject.toString();
        } catch (JSONException e) {
            String str3 = TAG;
            Log.e(str3, "createSystemActivitiesIntentCmd JSON Error: " + e.getMessage());
            return "";
        }
    }

    public boolean getCurrentAndResetAvatarStatus() {
        boolean z = this.mHasAvatar;
        this.mHasAvatar = true;
        return z;
    }

    private boolean maybeLaunchFromRecents(Intent intent) {
        String str = TAG;
        Log.d(str, "startActivityFromRecents: " + intent);
        try {
            return ((Boolean) Activity.class.getMethod("startActivityFromRecents", Intent.class, Bundle.class).invoke(this.mActivity, intent, null)).booleanValue();
        } catch (Exception e) {
            String str2 = TAG;
            Log.w(str2, "Error launching from recents " + e);
            return false;
        }
    }

    public static String getProcessName() {
        if (Build.VERSION.SDK_INT >= 28) {
            return Application.getProcessName();
        }
        try {
            return (String) Class.forName("android.app.ActivityThread").getDeclaredMethod("currentProcessName", new Class[0]).invoke(null, new Object[0]);
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
