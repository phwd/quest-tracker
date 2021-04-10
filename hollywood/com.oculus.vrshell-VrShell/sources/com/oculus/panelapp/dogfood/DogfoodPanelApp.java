package com.oculus.panelapp.dogfood;

import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import com.oculus.common.auth.AuthManager;
import com.oculus.common.httpclient.GraphQLRequest;
import com.oculus.common.httpclient.HttpClient;
import com.oculus.common.httpclient.VrShellUserAgentInterceptor;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.vrshellutil.R;
import com.oculus.os.SettingsManager;
import com.oculus.vrshell.panels.AndroidPanelApp;
import com.oculus.vrshell.panels.AndroidPanelLayer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import okhttp3.OkHttpClient;
import org.json.JSONObject;

public final class DogfoodPanelApp extends AndroidPanelApp {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int MAIN_LAYER_HEIGHT = 600;
    private static final int MAIN_LAYER_WIDTH = 840;
    private static String TAG = LoggingUtil.tag(DogfoodPanelApp.class);
    private boolean isUserAuthStateValid = false;
    private String mAccessToken;
    private final AuthManager mAuthManager;
    private DogfoodPanelBroadcastReceiver mBroadcastReceiver;
    private final OkHttpClient mClient;
    private long mCurrentGraphQLRequestId = 0;
    private DogfoodView mMainView;
    private List<Pair<GraphQLRequester, String>> mPendingGraphQLRequests = new ArrayList();
    private final SettingsManager mSettingsManager = new SettingsManager();
    private final Handler mUiThreadHandler;

    private native long nativeCreateInstance(long j, long j2);

    DogfoodPanelApp(Application application, Context context, Surface surface, Map<String, Surface> map, Bundle bundle) {
        super(application, context, surface, map, bundle);
        int i;
        showMainLayer();
        initializeBroadcastReceiver();
        String str = "1.0.0.0";
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            str = packageInfo.versionName;
            i = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException unused) {
            i = 0;
        }
        this.mUiThreadHandler = new Handler(context.getMainLooper());
        this.mClient = HttpClient.getOkHttpCient(new VrShellUserAgentInterceptor(str, String.valueOf(i), "com.oculus.vrshell", context.getResources().getConfiguration().getLocales().get(0).toString()), HttpClient.getOkHttpCacheDir(context.getApplicationInfo().dataDir, "Dogfood"));
        this.mAuthManager = new AuthManager(context, "1031607236937163", new AuthManager.AuthListener() {
            /* class com.oculus.panelapp.dogfood.DogfoodPanelApp.AnonymousClass1 */

            @Override // com.oculus.common.auth.AuthManager.AuthListener
            public void onUserAccessToken(String str) {
                DogfoodPanelApp.this.handleUserAccessToken(str);
            }

            @Override // com.oculus.common.auth.AuthManager.AuthListener
            public void onUserAccessTokenError(int i, String str) {
                Log.e(DogfoodPanelApp.TAG, String.format(Locale.ROOT, "Access token error: %d - %s", Integer.valueOf(i), str));
                DogfoodPanelApp.this.handleUserAccessToken(null);
            }
        });
        this.mAuthManager.requestUserAccessToken();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handleUserAccessToken(String str) {
        this.isUserAuthStateValid = true;
        this.mAccessToken = str;
        List<Pair<GraphQLRequester, String>> list = this.mPendingGraphQLRequests;
        this.mPendingGraphQLRequests = new ArrayList();
        for (Pair<GraphQLRequester, String> pair : list) {
            makeGraphQLRequest((GraphQLRequester) pair.first, (String) pair.second);
        }
    }

    private void showMainLayer() {
        ensurePanelLayer(AndroidPanelApp.MAIN_LAYER, new AndroidPanelLayer.Spec(AndroidPanelApp.MAIN_LAYER, MAIN_LAYER_WIDTH, MAIN_LAYER_HEIGHT, AndroidPanelLayer.ResizeBehavior.NONE, AndroidPanelLayer.HitTestingBehavior.HIT_TESTABLE, AndroidPanelLayer.Shape.LandscapeCylinder, R.style.PanelAppTheme), new AndroidPanelApp.ViewCreator() {
            /* class com.oculus.panelapp.dogfood.DogfoodPanelApp.AnonymousClass2 */

            @Override // com.oculus.vrshell.panels.AndroidPanelApp.ViewCreator
            public String name() {
                return "dogfood_#main";
            }

            @Override // com.oculus.vrshell.panels.AndroidPanelApp.ViewCreator
            public View createView(Context context) {
                return DogfoodPanelApp.this.createMainLayerView(context);
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private View createMainLayerView(Context context) {
        if (this.mMainView == null) {
            this.mMainView = (DogfoodView) LayoutInflater.from(context).inflate(R.layout.dogfood_panel, (ViewGroup) null);
            this.mMainView.initialize(this);
            return this.mMainView;
        }
        throw new UnsupportedOperationException("Trying to recreate main layer!");
    }

    private void initializeBroadcastReceiver() {
        this.mBroadcastReceiver = new DogfoodPanelBroadcastReceiver(this.mMainView);
        getContext().registerReceiver(this.mBroadcastReceiver, new IntentFilter(OTAUpdateHelper.ACTION_UPDATER_STATE_NOTIFICATION));
    }

    public void onDestroy() {
        if (this.mBroadcastReceiver != null) {
            getContext().unregisterReceiver(this.mBroadcastReceiver);
        }
    }

    public void actionNavigate(String str, String str2) {
        String str3 = TAG;
        Log.i(str3, "actionNavigate - App:  " + str + ", Uri:  " + str2);
        getCommandChannel().launch(str, str2);
    }

    public void makeGraphQLRequest(final GraphQLRequester graphQLRequester, String str) {
        if (this.isUserAuthStateValid) {
            String str2 = this.mAccessToken;
            if (str2 == null) {
                graphQLRequester.handleFailedGraphQlResponse("Invalid Auth Token");
            } else {
                GraphQLRequest.get(this.mClient, str2, str, new GraphQLRequest.GraphQLResponse() {
                    /* class com.oculus.panelapp.dogfood.DogfoodPanelApp.AnonymousClass3 */

                    @Override // com.oculus.common.httpclient.GraphQLRequest.GraphQLResponse
                    public void onFailure(final String str) {
                        DogfoodPanelApp.this.mUiThreadHandler.post(new Runnable() {
                            /* class com.oculus.panelapp.dogfood.DogfoodPanelApp.AnonymousClass3.AnonymousClass1 */

                            public void run() {
                                graphQLRequester.handleFailedGraphQlResponse(str);
                            }
                        });
                    }

                    @Override // com.oculus.common.httpclient.GraphQLRequest.GraphQLResponse
                    public void onSuccess(final JSONObject jSONObject) {
                        DogfoodPanelApp.this.mUiThreadHandler.post(new Runnable() {
                            /* class com.oculus.panelapp.dogfood.DogfoodPanelApp.AnonymousClass3.AnonymousClass2 */

                            public void run() {
                                graphQLRequester.handleGraphQlResponse(jSONObject);
                            }
                        });
                    }
                });
            }
        } else {
            this.mPendingGraphQLRequests.add(new Pair<>(graphQLRequester, str));
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public long createNativeInstance() {
        return nativeCreateInstance(840, 600);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public AndroidPanelLayer.Spec getLayerSpec(String str, int i) {
        if (((str.hashCode() == 35667036 && str.equals(AndroidPanelApp.MAIN_LAYER)) ? (char) 0 : 65535) != 0) {
            throw new IllegalArgumentException("Unknown layer: " + str);
        }
        throw new IllegalArgumentException(String.format("Layer %s is not supported by getLayerSpec.", new Object[0]));
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public View createViewForLayer(String str, int i, Context context) {
        String str2 = TAG;
        Log.d(str2, "Inflating view for viewIdentifier = " + i);
        if (((str.hashCode() == 35667036 && str.equals(AndroidPanelApp.MAIN_LAYER)) ? (char) 0 : 65535) != 0) {
            throw new IllegalArgumentException("Unknown layerName: " + str);
        }
        throw new IllegalArgumentException(String.format("Layer %s is not supported by createViewForLayer.", new Object[0]));
    }

    public SettingsManager getSettingsManager() {
        return this.mSettingsManager;
    }

    public boolean isGKEnabled(String str) {
        return "true".equals(getEnvironmentArg("_oc_gk:" + str));
    }
}
