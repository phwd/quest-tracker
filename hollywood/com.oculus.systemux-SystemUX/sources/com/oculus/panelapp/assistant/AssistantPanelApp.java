package com.oculus.panelapp.assistant;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.os.EnvironmentCompat;
import com.oculus.assistant.service.api.AssistantPanelContract;
import com.oculus.assistant.service.api.panel.AssistantDialogContract;
import com.oculus.assistant.service.api.panel.AssistantUiLifecycleContract;
import com.oculus.common.build.BuildConstants;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.ocui.OCEventHandler;
import com.oculus.panelapp.assistant.attentionui.AssistantAttentionView;
import com.oculus.panelapp.assistant.config.BroadcastConfig;
import com.oculus.panelapp.assistant.dialogs.AssistantDialog;
import com.oculus.panelapp.assistant.dialogs.IAssistantDialog;
import com.oculus.panelapp.assistant.dialogs.ISurface;
import com.oculus.panelapp.assistant.dialogs.MultiSuggestionDialog;
import com.oculus.panelapp.assistant.dialogs.SystemDialog;
import com.oculus.panelapp.assistant.dialogs.SystemStylePanelDialog;
import com.oculus.systemdialog.DialogManager;
import com.oculus.vrshell.panels.AndroidPanelApp;
import com.oculus.vrshell.panels.AndroidPanelLayer;
import com.oculus.vrshell.panels.FrameCommandChannel;
import com.oculus.vrshell.panels.SoundType;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;
import org.json.JSONException;
import org.json.JSONObject;

public class AssistantPanelApp extends AndroidPanelApp implements OCEventHandler {
    private static final String ATTENTION_LAYER;
    private static final AndroidPanelLayer.Spec ATTENTION_LAYER_SPEC = new AndroidPanelLayer.Spec(ATTENTION_LAYER, 1, 1, AndroidPanelLayer.ResizeBehavior.WRAP_CONTENT_WIDTH_HEIGHT, AndroidPanelLayer.HitTestingBehavior.NOT_HIT_TESTABLE, AndroidPanelLayer.Shape.Flat, R.style.PanelAppTheme);
    private static final int DEFAULT_STATE_ICON_PADDING;
    private static final String ID_ATTENTION_SYSTEM_CONTROL;
    private static final AndroidPanelLayer.Spec MAIN_LAYER_SPEC = new AndroidPanelLayer.Spec(AndroidPanelApp.MAIN_LAYER, 1, 1, AndroidPanelLayer.ResizeBehavior.NONE, AndroidPanelLayer.HitTestingBehavior.HIT_TESTABLE, AndroidPanelLayer.Shape.Flat, R.style.PanelAppTheme);
    public static final int MSG_ATTN_SYSTEM_RESPONSE;
    public static final int MSG_DIALOG_RESPONSE;
    private static final String PACKAGE_ASSISTANT;
    public static final String PREF_DEBUG_ANIM;
    private static final long SERVICE_SHUTDOWN_TIMEOUT;
    private static String TAG = LoggingUtil.tag(AssistantPanelApp.class);
    private Map<String, IAssistantDialog> mActiveDialogs = new HashMap();
    private BroadcastConfig mAssistantBroadcastConfig = new BroadcastConfig();
    private final SharedPreferences mAssistantDebuggingPrefs;
    private AssistantAttentionView mAttentionView;
    private JSONObject mCurrentAttentionSystemData;
    private IAssistantDialog mCurrentDialog;
    private QueuedUI mCurrentUI;
    private final DialogManager mDialogManager = new DialogManager();
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private boolean mIsSafeToShowNextUI = true;
    private JSONObject mLastAttentionSystemJson;
    private IAssistantDialog mLastPendingDialog;
    private Queue<Runnable> mMainLayerActionQueue = new LinkedBlockingDeque();
    private AssistantView mMainView;
    private MainLayerCreatedListener mPendingUi;
    private final AssistantPanelService mService;
    private Runnable mServiceKiller;
    private Queue<QueuedUI> mUIQueue = new LinkedBlockingDeque();

    public interface MainLayerCreatedListener {
        boolean onMainLayerReady(Context context);
    }

    private native long nativeCreateInstance(long j, long j2);

    public static class QueuedUI {
        private boolean mClearOnClose;
        private JSONObject mData;
        private String mLayer;

        private QueuedUI() {
        }

        public static QueuedUI create(String str, JSONObject jSONObject) {
            QueuedUI queuedUI = new QueuedUI();
            queuedUI.mLayer = str;
            queuedUI.mData = jSONObject;
            queuedUI.mClearOnClose = jSONObject.optBoolean(AssistantDialogContract.CLEAR_QUEUE_ON_CLOSE, false);
            return queuedUI;
        }
    }

    public AssistantPanelApp(Application application, Context context, AssistantPanelService assistantPanelService, Surface surface, Map<String, Surface> map, Bundle bundle) {
        super(application, context, surface, map, bundle);
        this.mService = assistantPanelService;
        this.mServiceKiller = new Runnable() {
            /* class com.oculus.panelapp.assistant.$$Lambda$AssistantPanelApp$hwEuonaFh10VeUE_RHll3eHGNwE */

            public final void run() {
                AssistantPanelApp.this.lambda$new$0$AssistantPanelApp();
            }
        };
        this.mAssistantDebuggingPrefs = context.getSharedPreferences("assistant_debugging", 0);
        Log.d(TAG, "AssistantPanelApp Created.");
    }

    public /* synthetic */ void lambda$new$0$AssistantPanelApp() {
        if (!isShowing()) {
            Log.d(TAG, "Shutting down Assistant UI Service due to inactivity.");
            stopService();
        }
    }

    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public void destroy() {
        AssistantAttentionView assistantAttentionView = this.mAttentionView;
        if (assistantAttentionView != null) {
            assistantAttentionView.destroy();
        }
        super.destroy();
    }

    public void hideMainLayer() {
        Log.i(TAG, "Hiding the main layer");
        this.mDialogManager.hideDialog();
        destroyLayer(AndroidPanelApp.MAIN_LAYER);
        sendLifecycleEvent(AssistantUiLifecycleContract.LayerEvents.MAIN_LAYER_DESTROYED, null, this.mAssistantBroadcastConfig);
        this.mMainView = null;
        scheduleServiceClose();
    }

    private void clearScheduledServiceClose() {
        this.mHandler.removeCallbacks(this.mServiceKiller);
    }

    private void scheduleServiceClose() {
        if (!isShowing()) {
            clearScheduledServiceClose();
            this.mHandler.postDelayed(this.mServiceKiller, SERVICE_SHUTDOWN_TIMEOUT);
        }
    }

    public static Intent createAssistantShellCommandIntent(String str) {
        Intent intent = new Intent();
        intent.setAction("com.oculus.assistant.COMMAND");
        intent.setClassName("com.oculus.vrshell", BuildConstants.ASSISTANT_BROADCAST_RECEIVER_NAME_SHELL);
        intent.putExtra("command", str);
        return intent;
    }

    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public long createNativeInstance() {
        return nativeCreateInstance((long) MAIN_LAYER_SPEC.width, (long) MAIN_LAYER_SPEC.height);
    }

    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public void frame(String str, float f, float f2, long j, long j2, float f3, float f4, float f5) {
        super.frame(str, f, f2, j, j2, f3, f4, f5);
        String dialogIPC = this.mDialogManager.getDialogIPC();
        if (dialogIPC != null) {
            getCommandChannel().sendCommand(dialogIPC);
        }
    }

    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public void onSystemDialogResult(String str) {
        this.mDialogManager.handleSystemDialogResult(str);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0028 A[ADDED_TO_REGION] */
    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.oculus.vrshell.panels.AndroidPanelLayer.Spec getLayerSpec(java.lang.String r4, int r5) {
        /*
            r3 = this;
            int r5 = r4.hashCode()
            r0 = -353951458(0xffffffffeae7211e, float:-1.3970913E26)
            r1 = 0
            r2 = 1
            if (r5 == r0) goto L_0x001b
            r0 = 35667036(0x2203c5c, float:1.1772266E-37)
            if (r5 == r0) goto L_0x0011
            goto L_0x0025
        L_0x0011:
            java.lang.String r5 = "#main"
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x0025
            r4 = r1
            goto L_0x0026
        L_0x001b:
            java.lang.String r5 = "attention"
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x0025
            r4 = r2
            goto L_0x0026
        L_0x0025:
            r4 = -1
        L_0x0026:
            if (r4 == 0) goto L_0x0032
            if (r4 == r2) goto L_0x0032
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
            java.lang.String r5 = "Unsupported layer."
            r4.<init>(r5)
            throw r4
        L_0x0032:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
            java.lang.Object[] r5 = new java.lang.Object[r1]
            java.lang.String r0 = "Layer %s is not supported by getLayerSpec."
            java.lang.String r5 = java.lang.String.format(r0, r5)
            r4.<init>(r5)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.assistant.AssistantPanelApp.getLayerSpec(java.lang.String, int):com.oculus.vrshell.panels.AndroidPanelLayer$Spec");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x003e A[ADDED_TO_REGION] */
    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.View createViewForLayer(java.lang.String r3, int r4, android.content.Context r5) {
        /*
        // Method dump skipped, instructions count: 101
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.assistant.AssistantPanelApp.createViewForLayer(java.lang.String, int, android.content.Context):android.view.View");
    }

    private View createAttentionSystemLayer(Context context) {
        if (this.mAttentionView == null) {
            this.mAttentionView = (AssistantAttentionView) LayoutInflater.from(context).inflate(R.layout.assistant_attention_view, (ViewGroup) null);
            this.mAttentionView.initialize(this);
            this.mAttentionView.setDebuggingEnabled(this.mAssistantDebuggingPrefs.getBoolean(PREF_DEBUG_ANIM, false));
            this.mAttentionView.setOnHiddenListener(new AssistantAttentionView.OnHiddenListener() {
                /* class com.oculus.panelapp.assistant.$$Lambda$AssistantPanelApp$AY6hxiFpAyBDSQ9f7pz2oldnpXA */

                @Override // com.oculus.panelapp.assistant.attentionui.AssistantAttentionView.OnHiddenListener
                public final void onHidden() {
                    AssistantPanelApp.lambda$AY6hxiFpAyBDSQ9f7pz2oldnpXA(AssistantPanelApp.this);
                }
            });
            this.mAttentionView.setOnHidingListener(new AssistantAttentionView.OnHidingListener() {
                /* class com.oculus.panelapp.assistant.$$Lambda$AssistantPanelApp$3mMdskzfJ_7kvJoDo39BoTUbNvA */

                @Override // com.oculus.panelapp.assistant.attentionui.AssistantAttentionView.OnHidingListener
                public final void onHiding() {
                    AssistantPanelApp.this.lambda$createAttentionSystemLayer$1$AssistantPanelApp();
                }
            });
            this.mAttentionView.setOnShowMessageStartListener(new AssistantAttentionView.OnShowMessageStartListener() {
                /* class com.oculus.panelapp.assistant.$$Lambda$AssistantPanelApp$L5drYqkMjEaSZbKwxNrymCaAK4s */

                @Override // com.oculus.panelapp.assistant.attentionui.AssistantAttentionView.OnShowMessageStartListener
                public final void onShowMessageStart() {
                    AssistantPanelApp.lambda$L5drYqkMjEaSZbKwxNrymCaAK4s(AssistantPanelApp.this);
                }
            });
            this.mAttentionView.setOnCloseClickedListener(new AssistantAttentionView.OnCloseClickedListener() {
                /* class com.oculus.panelapp.assistant.$$Lambda$AssistantPanelApp$YxecjKuep9ozeppdG9PJVLILEo */

                @Override // com.oculus.panelapp.assistant.attentionui.AssistantAttentionView.OnCloseClickedListener
                public final void onCloseClicked() {
                    AssistantPanelApp.this.lambda$createAttentionSystemLayer$2$AssistantPanelApp();
                }
            });
            return this.mAttentionView;
        }
        throw new UnsupportedOperationException("Trying to recreate attention layer!");
    }

    public /* synthetic */ void lambda$createAttentionSystemLayer$1$AssistantPanelApp() {
        showNextUI(true);
    }

    public /* synthetic */ void lambda$createAttentionSystemLayer$2$AssistantPanelApp() {
        sendAttentionSystemAction("ATTENTION_SYSTEM_CONTROL", "hide-attention", this.mAttentionView.getBroadcastConfig());
        QueuedUI queuedUI = this.mCurrentUI;
        if (queuedUI != null && queuedUI.mClearOnClose) {
            clearQueue();
        }
    }

    private View createMainLayer(Context context) {
        if (this.mMainView == null) {
            this.mMainView = (AssistantView) LayoutInflater.from(context).inflate(R.layout.assistant_panel, (ViewGroup) null);
            this.mMainView.initialize(this);
            MainLayerCreatedListener mainLayerCreatedListener = this.mPendingUi;
            if (mainLayerCreatedListener != null) {
                if (!mainLayerCreatedListener.onMainLayerReady(context)) {
                    Log.e(TAG, "Creating UI while showing the panel failed. Hiding the UI layer.");
                    this.mMainView.post(new Runnable() {
                        /* class com.oculus.panelapp.assistant.$$Lambda$YJhafmgtKhZR1EVmaB9N7Uwf_UU */

                        public final void run() {
                            AssistantPanelApp.this.hideMainLayer();
                        }
                    });
                }
                this.mPendingUi = null;
            }
            return this.mMainView;
        }
        throw new UnsupportedOperationException("Trying to recreate main layer!");
    }

    public void addView(View view) {
        AssistantView assistantView = this.mMainView;
        if (assistantView == null) {
            ensureMainLayer(new MainLayerCreatedListener(view) {
                /* class com.oculus.panelapp.assistant.$$Lambda$AssistantPanelApp$ZXbluM9kQKEGZL9x4xpqaumLDpA */
                private final /* synthetic */ View f$1;

                {
                    this.f$1 = r2;
                }

                @Override // com.oculus.panelapp.assistant.AssistantPanelApp.MainLayerCreatedListener
                public final boolean onMainLayerReady(Context context) {
                    return AssistantPanelApp.this.lambda$addView$3$AssistantPanelApp(this.f$1, context);
                }
            });
        } else {
            assistantView.addView(view);
        }
    }

    public /* synthetic */ boolean lambda$addView$3$AssistantPanelApp(View view, Context context) {
        this.mMainView.addView(view);
        return true;
    }

    public void removeView(View view) {
        AssistantView assistantView = this.mMainView;
        if (assistantView != null) {
            assistantView.removeView(view);
        }
    }

    @Override // com.oculus.vrshell.panels.GenericInputListener, com.oculus.vrshell.panels.AndroidPanelApp
    public boolean onBackButton() {
        sendLifecycleEvent(AssistantUiLifecycleContract.SystemEvents.SYSTEM_BACK_PRESSED, null, this.mAssistantBroadcastConfig);
        if (this.mCurrentDialog == null) {
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("source", "back");
            reportCloseRequest(AndroidPanelApp.MAIN_LAYER, jSONObject.toString());
        } catch (JSONException unused) {
        }
        sendAction(this.mCurrentDialog, "back", null);
        getCommandChannel().playAudio(SoundType.CLOSE);
        clearQueue();
        closeDialog();
        hideMainLayer();
        return true;
    }

    private void clearQueue() {
        this.mUIQueue.clear();
        this.mIsSafeToShowNextUI = true;
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    public void onCommand(String str, String str2) {
        char c;
        switch (str.hashCode()) {
            case -186290485:
                if (str.equals("debug-animations-off")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -169343402:
                if (str.equals("shutdown")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -73155481:
                if (str.equals(AssistantPanelContract.HIDE_ATTENTION_INSTANT)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 825274627:
                if (str.equals("debug-animations-on")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            this.mAssistantDebuggingPrefs.edit().putBoolean(PREF_DEBUG_ANIM, true);
            AssistantAttentionView assistantAttentionView = this.mAttentionView;
            if (assistantAttentionView != null) {
                assistantAttentionView.setDebuggingEnabled(true);
            }
        } else if (c == 1) {
            this.mAssistantDebuggingPrefs.edit().putBoolean(PREF_DEBUG_ANIM, false);
            AssistantAttentionView assistantAttentionView2 = this.mAttentionView;
            if (assistantAttentionView2 != null) {
                assistantAttentionView2.setDebuggingEnabled(false);
            }
        } else if (c == 2) {
            reportCloseRequest(ATTENTION_LAYER, str2);
            hideAttentionSystem();
        } else if (c != 3) {
            handleGenericMessage(str + " " + str2);
        } else {
            Log.d(TAG, "Stopping Assistant UI Service");
            this.mService.stopSelf();
        }
    }

    public void onHide(String str) {
        clearQueue();
        String str2 = TAG;
        Log.i(str2, "Assistant panel hidden. " + str);
        if (this.mCurrentDialog != null) {
            reportCloseRequest(AndroidPanelApp.MAIN_LAYER, str);
            closeDialog();
            return;
        }
        hideMainLayer();
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0029  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0030  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void reportCloseRequest(java.lang.String r4, java.lang.String r5) {
        /*
            r3 = this;
            int r0 = r4.hashCode()
            r1 = -353951458(0xffffffffeae7211e, float:-1.3970913E26)
            r2 = 1
            if (r0 == r1) goto L_0x001a
            r1 = 35667036(0x2203c5c, float:1.1772266E-37)
            if (r0 == r1) goto L_0x0010
            goto L_0x0024
        L_0x0010:
            java.lang.String r0 = "#main"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0024
            r4 = r2
            goto L_0x0025
        L_0x001a:
            java.lang.String r0 = "attention"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0024
            r4 = 0
            goto L_0x0025
        L_0x0024:
            r4 = -1
        L_0x0025:
            java.lang.String r0 = "MAIN_LAYER_ON_CLOSE"
            if (r4 == 0) goto L_0x0030
            if (r4 == r2) goto L_0x002d
            r4 = 0
            goto L_0x0034
        L_0x002d:
            com.oculus.panelapp.assistant.dialogs.IAssistantDialog r4 = r3.mCurrentDialog
            goto L_0x0034
        L_0x0030:
            com.oculus.panelapp.assistant.attentionui.AssistantAttentionView r4 = r3.mAttentionView
            java.lang.String r0 = "ATTENTION_SYSTEM_ON_CLOSE"
        L_0x0034:
            android.os.Bundle r1 = new android.os.Bundle
            r1.<init>()
            java.lang.String r2 = "EXTRA_EVENT_DATA_SOURCE"
            r1.putString(r2, r5)
            com.oculus.panelapp.assistant.config.BroadcastConfig r5 = r3.mAssistantBroadcastConfig
            r3.sendLifecycleEvent(r0, r4, r5, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.assistant.AssistantPanelApp.reportCloseRequest(java.lang.String, java.lang.String):void");
    }

    public void onShowAttentionSystem(String str) {
        clearScheduledServiceClose();
        enqueueUI(str, ATTENTION_LAYER);
    }

    public void onShow(String str) {
        clearScheduledServiceClose();
        enqueueUI(str, AndroidPanelApp.MAIN_LAYER);
    }

    private void enqueueUI(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                boolean z = false;
                boolean optBoolean = jSONObject.optBoolean(AssistantDialogContract.QUEUE, false);
                this.mUIQueue.add(QueuedUI.create(str2, jSONObject));
                if (!optBoolean) {
                    z = true;
                }
                showNextUI(z);
            } catch (JSONException e) {
                String str3 = TAG;
                Log.w(str3, "Error parsing UI request: " + e.getMessage() + "\n" + str, e);
            }
        }
    }

    private void showAttentionSystem(JSONObject jSONObject) {
        this.mCurrentAttentionSystemData = jSONObject;
        String optString = jSONObject.optString("type", "");
        IAssistantDialog iAssistantDialog = this.mCurrentDialog;
        if (iAssistantDialog == null || !AssistantDialogContract.DialogTypes.EXPANDED_ATTENTION_SYSTEM.equals(iAssistantDialog.getSurfaceType())) {
            char c = 65535;
            if (optString.hashCode() == 207341151 && optString.equals(AssistantDialogContract.DialogTypes.EXPANDED_ATTENTION_SYSTEM)) {
                c = 0;
            }
            if (c != 0) {
                ensureAttentionLayer();
                this.mAttentionView.showAttentionSystem(jSONObject);
            } else {
                showExpandedAttnSystem(jSONObject);
            }
        } else {
            ((ExpandedAttentionSystem) this.mCurrentDialog).show(jSONObject);
        }
        this.mLastAttentionSystemJson = jSONObject;
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x0051 A[Catch:{ JSONException -> 0x0064 }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x005c A[Catch:{ JSONException -> 0x0064 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void showNextUI(boolean r7) {
        /*
        // Method dump skipped, instructions count: 118
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.assistant.AssistantPanelApp.showNextUI(boolean):void");
    }

    public boolean isShowing() {
        return (this.mMainView == null && this.mAttentionView == null) ? false : true;
    }

    private void showExpandedAttnSystem(JSONObject jSONObject) {
        JSONObject jSONObject2 = this.mLastAttentionSystemJson;
        if (jSONObject2 != null) {
            showAssistantUi(jSONObject2, AssistantDialogContract.DialogTypes.EXPANDED_ATTENTION_SYSTEM);
        }
        hideAttentionSystem();
        showAssistantUi(jSONObject, AssistantDialogContract.DialogTypes.EXPANDED_ATTENTION_SYSTEM);
    }

    /* access modifiers changed from: public */
    private void ensureAttentionLayer() {
        if (this.mAttentionView == null) {
            ensurePanelLayer(ATTENTION_LAYER, ATTENTION_LAYER_SPEC, new AndroidPanelApp.ViewCreator() {
                /* class com.oculus.panelapp.assistant.AssistantPanelApp.AnonymousClass1 */

                @Override // com.oculus.vrshell.panels.AndroidPanelApp.ViewCreator
                public String name() {
                    return "assistant_attention";
                }

                @Override // com.oculus.vrshell.panels.AndroidPanelApp.ViewCreator
                public View createView(Context context) {
                    return AssistantPanelApp.this.createAttentionSystemLayer(context);
                }
            });
            attachResizeLayoutListener(getPanelLayer(ATTENTION_LAYER), AndroidPanelLayer.ResizeBehavior.WRAP_CONTENT_WIDTH_HEIGHT);
        }
    }

    private void ensureMainLayer(MainLayerCreatedListener mainLayerCreatedListener) {
        AssistantView assistantView = this.mMainView;
        if (assistantView == null) {
            this.mPendingUi = mainLayerCreatedListener;
            ensurePanelLayer(AndroidPanelApp.MAIN_LAYER, MAIN_LAYER_SPEC, new AndroidPanelApp.ViewCreator() {
                /* class com.oculus.panelapp.assistant.AssistantPanelApp.AnonymousClass2 */

                @Override // com.oculus.vrshell.panels.AndroidPanelApp.ViewCreator
                public String name() {
                    return "assistant_main";
                }

                @Override // com.oculus.vrshell.panels.AndroidPanelApp.ViewCreator
                public View createView(Context context) {
                    return AssistantPanelApp.this.createMainLayer(context);
                }
            });
            attachResizeLayoutListener(getPanelLayer(AndroidPanelApp.MAIN_LAYER), AndroidPanelLayer.ResizeBehavior.WRAP_CONTENT_WIDTH_HEIGHT);
        } else if (!mainLayerCreatedListener.onMainLayerReady(assistantView.getContext())) {
            hideMainLayer();
        }
    }

    public void onHideAttentionSystem(String str) {
        clearQueue();
        if (this.mAttentionView != null) {
            reportCloseRequest(ATTENTION_LAYER, str);
            this.mCurrentAttentionSystemData = null;
            this.mAttentionView.hide();
            return;
        }
        hideAttentionSystem();
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0038 A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean isValidUiType(java.lang.String r6) {
        /*
            r5 = this;
            int r0 = r6.hashCode()
            r1 = -1332085432(0xffffffffb099fd48, float:-1.1204202E-9)
            r2 = 0
            r3 = 2
            r4 = 1
            if (r0 == r1) goto L_0x002b
            r1 = 258290968(0xf653518, float:1.1300797E-29)
            if (r0 == r1) goto L_0x0021
            r1 = 315159206(0x12c8f2a6, float:1.2681592E-27)
            if (r0 == r1) goto L_0x0017
            goto L_0x0035
        L_0x0017:
            java.lang.String r0 = "system-dialog"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0035
            r6 = r4
            goto L_0x0036
        L_0x0021:
            java.lang.String r0 = "multisuggestion-dialog"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0035
            r6 = r3
            goto L_0x0036
        L_0x002b:
            java.lang.String r0 = "dialog"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0035
            r6 = r2
            goto L_0x0036
        L_0x0035:
            r6 = -1
        L_0x0036:
            if (r6 == 0) goto L_0x003d
            if (r6 == r4) goto L_0x003d
            if (r6 == r3) goto L_0x003d
            return r2
        L_0x003d:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.assistant.AssistantPanelApp.isValidUiType(java.lang.String):boolean");
    }

    private void showMainLayer(JSONObject jSONObject) {
        String optString = jSONObject.optString("type", EnvironmentCompat.MEDIA_UNKNOWN);
        if (AssistantDialogContract.DialogTypes.SYSTEM_DIALOG.equals(optString) && !jSONObject.optBoolean(AssistantDialogContract.USE_PANEL_DIALOGS, false)) {
            hideMainLayer();
            Log.d(TAG, "Showing system dialog");
            showDialog(SystemDialog.createDialog(this, jSONObject.getJSONObject("data")));
        } else if (AssistantDialogContract.DialogTypes.EXPANDED_ATTENTION_SYSTEM.equals(optString)) {
            showExpandedAttnSystem(jSONObject);
        } else {
            showAssistantUi(jSONObject, optString);
        }
    }

    private void showAssistantUi(JSONObject jSONObject, String str) {
        ensureMainLayer(new MainLayerCreatedListener(jSONObject, str) {
            /* class com.oculus.panelapp.assistant.$$Lambda$AssistantPanelApp$MIZLQdGPzt8B99r0taiPwJmgag */
            private final /* synthetic */ JSONObject f$1;
            private final /* synthetic */ String f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            @Override // com.oculus.panelapp.assistant.AssistantPanelApp.MainLayerCreatedListener
            public final boolean onMainLayerReady(Context context) {
                return AssistantPanelApp.this.lambda$showAssistantUi$4$AssistantPanelApp(this.f$1, this.f$2, context);
            }
        });
    }

    public /* synthetic */ boolean lambda$showAssistantUi$4$AssistantPanelApp(JSONObject jSONObject, String str, Context context) {
        try {
            return constructUi(context, jSONObject, str);
        } catch (IllegalStateException e) {
            Log.w(TAG, e.getMessage(), e);
            return false;
        } catch (JSONException e2) {
            String str2 = TAG;
            Log.w(str2, "Unable to show " + str + " failed to parse request: " + e2.getMessage(), e2);
            return false;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    private boolean constructUi(Context context, JSONObject jSONObject, String str) {
        char c;
        switch (str.hashCode()) {
            case -1332085432:
                if (str.equals(AssistantDialogContract.DialogTypes.ASSISTANT_SYSTEM_DIALOG)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 207341151:
                if (str.equals(AssistantDialogContract.DialogTypes.EXPANDED_ATTENTION_SYSTEM)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 258290968:
                if (str.equals(AssistantDialogContract.DialogTypes.MULTISUGGESTION_DIALOG)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 315159206:
                if (str.equals(AssistantDialogContract.DialogTypes.SYSTEM_DIALOG)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            IAssistantDialog iAssistantDialog = this.mCurrentDialog;
            if (iAssistantDialog == null || !AssistantDialogContract.DialogTypes.EXPANDED_ATTENTION_SYSTEM.equals(iAssistantDialog.getSurfaceType())) {
                showDialog(ExpandedAttentionSystem.createDialog(context, this, jSONObject));
            } else {
                ((ExpandedAttentionSystem) this.mCurrentDialog).show(jSONObject);
            }
            return true;
        } else if (c == 1) {
            showDialog(AssistantDialog.createDialog(context, this, getDialogData(jSONObject)));
            return true;
        } else if (c == 2) {
            showDialog(SystemStylePanelDialog.createDialog(context, this, getDialogData(jSONObject)));
            return true;
        } else if (c != 3) {
            Log.w(TAG, "Unsupported ui type (" + str + ") requested.");
            return false;
        } else {
            showDialog(MultiSuggestionDialog.createDialog(context, this, getDialogData(jSONObject)));
            return true;
        }
    }

    private JSONObject getDialogData(JSONObject jSONObject) {
        if (jSONObject.has("data")) {
            return jSONObject.getJSONObject("data");
        }
        throw new JSONException("Missing data field describing dialog.");
    }

    /* access modifiers changed from: public */
    private void hideAttentionSystem() {
        Log.d(TAG, "Hiding the attention system.");
        this.mLastAttentionSystemJson = null;
        destroyLayer(ATTENTION_LAYER);
        sendLifecycleEvent(AssistantUiLifecycleContract.LayerEvents.ATTENTION_SYSTEM_DESTROYED, null, this.mAssistantBroadcastConfig);
        AssistantAttentionView assistantAttentionView = this.mAttentionView;
        if (assistantAttentionView != null) {
            assistantAttentionView.destroy();
            this.mAttentionView = null;
        }
        clearQueue();
        scheduleServiceClose();
    }

    public void setAttentionSystemHitTesting(boolean z) {
        FrameCommandChannel commandChannel = getCommandChannel();
        StringBuilder sb = new StringBuilder();
        sb.append("layerHittestable attention ");
        sb.append(z ? "1" : "0");
        commandChannel.sendCommand(sb.toString());
    }

    private void showDialog(IAssistantDialog iAssistantDialog) {
        IAssistantDialog iAssistantDialog2 = this.mCurrentDialog;
        if (iAssistantDialog2 != null && !TextUtils.equals(iAssistantDialog2.getSurfaceId(), iAssistantDialog.getSurfaceId())) {
            closeDialog();
        }
        this.mLastPendingDialog = iAssistantDialog;
        queueAction(new Runnable(iAssistantDialog) {
            /* class com.oculus.panelapp.assistant.$$Lambda$AssistantPanelApp$O2WWMDqxgQ5aJlP6V0hwu9PeJeI */
            private final /* synthetic */ IAssistantDialog f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                AssistantPanelApp.this.lambda$showDialog$6$AssistantPanelApp(this.f$1);
            }
        });
    }

    public /* synthetic */ void lambda$showDialog$6$AssistantPanelApp(IAssistantDialog iAssistantDialog) {
        this.mCurrentDialog = iAssistantDialog;
        iAssistantDialog.show();
        if (iAssistantDialog.getVisibleDuration() > 0) {
            this.mHandler.postDelayed(new Runnable(iAssistantDialog) {
                /* class com.oculus.panelapp.assistant.$$Lambda$AssistantPanelApp$cDZ4Wr4sVNNYyBAueCBVWOEEGE */
                private final /* synthetic */ IAssistantDialog f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    AssistantPanelApp.this.lambda$null$5$AssistantPanelApp(this.f$1);
                }
            }, iAssistantDialog.getVisibleDuration());
        }
        completeAction();
    }

    private void closeDialog() {
        lambda$null$5$AssistantPanelApp(this.mCurrentDialog);
    }

    /* renamed from: closeDialog */
    public void lambda$null$5$AssistantPanelApp(IAssistantDialog iAssistantDialog) {
        if (iAssistantDialog != null) {
            queueAction(new Runnable(iAssistantDialog) {
                /* class com.oculus.panelapp.assistant.$$Lambda$AssistantPanelApp$mAEn_I_p8y8oSKqsw692oJaTJTQ */
                private final /* synthetic */ IAssistantDialog f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    AssistantPanelApp.this.lambda$closeDialog$7$AssistantPanelApp(this.f$1);
                }
            });
        }
    }

    public /* synthetic */ void lambda$closeDialog$7$AssistantPanelApp(IAssistantDialog iAssistantDialog) {
        sendAction(iAssistantDialog, "hide", null);
        if (iAssistantDialog == this.mLastPendingDialog) {
            this.mLastPendingDialog = null;
            this.mCurrentDialog = null;
            iAssistantDialog.hide(new Runnable() {
                /* class com.oculus.panelapp.assistant.$$Lambda$AssistantPanelApp$0HPeZHwIceqZzCvLlJeEMrBiLto */

                public final void run() {
                    AssistantPanelApp.lambda$0HPeZHwIceqZzCvLlJeEMrBiLto(AssistantPanelApp.this);
                }
            });
            return;
        }
        completeAction();
    }

    /* access modifiers changed from: public */
    private void completeHide() {
        if (this.mMainLayerActionQueue.size() == 0) {
            hideMainLayer();
        }
        completeAction();
    }

    private void queueAction(Runnable runnable) {
        this.mMainLayerActionQueue.add(runnable);
        if (this.mMainLayerActionQueue.size() == 1) {
            runnable.run();
        }
    }

    private void completeAction() {
        this.mMainLayerActionQueue.poll();
        if (this.mMainLayerActionQueue.size() > 0) {
            this.mMainLayerActionQueue.peek().run();
        }
    }

    public void stopService() {
        this.mService.stopSelf();
        this.mFrameCommandChannel.quit();
    }

    public void sendMessage(final Message message, BroadcastConfig broadcastConfig) {
        if (broadcastConfig.useBroadcast()) {
            Intent intent = new Intent();
            intent.setAction(AssistantUiLifecycleContract.ACTION_UI_CONTROL_EVENT);
            intent.setPackage(broadcastConfig.getResponseTarget());
            intent.putExtra(AssistantDialogContract.MESSAGE_TYPE, message.what);
            if (message.getData() != null) {
                intent.putExtras(message.getData());
            }
            getContext().sendBroadcast(intent);
            return;
        }
        AnonymousClass3 r0 = new ServiceConnection() {
            /* class com.oculus.panelapp.assistant.AssistantPanelApp.AnonymousClass3 */

            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                try {
                    new Messenger(iBinder).send(message);
                } catch (RemoteException e) {
                    Log.e(AssistantPanelApp.TAG, e.getMessage(), e);
                }
                AssistantPanelApp.this.getContext().unbindService(this);
            }

            public void onServiceDisconnected(ComponentName componentName) {
                Log.d(AssistantPanelApp.TAG, "Assistant control service has disconnected. Closing panel.");
                AssistantPanelApp.this.hideMainLayer();
            }
        };
        Intent intent2 = new Intent();
        String responseTarget = broadcastConfig.getResponseTarget() != null ? broadcastConfig.getResponseTarget() : "com.oculus.assistant";
        intent2.setClassName(responseTarget, responseTarget + ".service.AssistantService");
        intent2.setAction(AssistantUiLifecycleContract.ACTION_UI_CONTROL_EVENT);
        getContext().bindService(intent2, r0, 1);
    }

    public void sendLifecycleEvent(String str, ISurface iSurface, BroadcastConfig broadcastConfig) {
        sendLifecycleEvent(str, iSurface, broadcastConfig, null);
    }

    public void sendLifecycleEvent(String str, ISurface iSurface, BroadcastConfig broadcastConfig, Bundle bundle) {
        new Bundle().putString("action", str);
        Intent intent = new Intent();
        intent.setPackage("com.oculus.assistant");
        intent.setAction(AssistantUiLifecycleContract.ACTION_UI_EVENT_BROADCAST);
        intent.putExtra(AssistantUiLifecycleContract.EXTRA_EVENT_ID, str);
        if (iSurface != null) {
            intent.putExtra(AssistantUiLifecycleContract.EXTRA_SURFACE_ID, iSurface.getSurfaceId());
            intent.putExtra(AssistantUiLifecycleContract.EXTRA_SURFACE_TYPE, iSurface.getSurfaceType());
            String responseTarget = broadcastConfig.getResponseTarget();
            if (responseTarget != null) {
                intent.setPackage(responseTarget);
            }
        }
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        getContext().sendBroadcast(intent);
    }

    private void sendSurfaceMessage(int i, ISurface iSurface, BroadcastConfig broadcastConfig, String str, Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle2.putString("id", iSurface.getSurfaceId());
        bundle2.putString("action", str);
        if (bundle != null) {
            bundle2.putBundle(AssistantDialogContract.DialogResponse.EXTRAS, bundle);
        }
        Message obtain = Message.obtain();
        obtain.what = i;
        obtain.setData(bundle2);
        sendMessage(obtain, broadcastConfig);
    }

    public void sendAttentionSystemAction(String str, String str2, BroadcastConfig broadcastConfig) {
        Bundle bundle = new Bundle();
        bundle.putString("id", str);
        bundle.putString("action", str2);
        Message obtain = Message.obtain();
        obtain.what = 2;
        obtain.setData(bundle);
        sendMessage(obtain, broadcastConfig);
    }

    public void sendAction(ISurface iSurface, String str, Bundle bundle) {
        sendSurfaceMessage(1, iSurface, iSurface.getBroadcastConfig(), str, bundle);
    }

    public boolean onDialogResult(IAssistantDialog iAssistantDialog, BroadcastConfig broadcastConfig, String str, Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle2.putString("id", iAssistantDialog.getSurfaceId());
        bundle2.putString("action", str);
        if (bundle != null) {
            bundle2.putBundle(AssistantDialogContract.DialogResponse.EXTRAS, bundle);
        }
        iAssistantDialog.getResultData(bundle2);
        Message obtain = Message.obtain();
        obtain.what = 1;
        obtain.setData(bundle2);
        sendMessage(obtain, broadcastConfig);
        return true;
    }

    public DialogManager getDialogManager() {
        return this.mDialogManager;
    }

    @Override // com.oculus.ocui.OCEventHandler
    public void onButtonClick() {
        getCommandChannel().playAudio(SoundType.SELECT);
    }

    @Override // com.oculus.ocui.OCEventHandler
    public void onButtonEnter() {
        getCommandChannel().playAudio(SoundType.HOVER);
    }

    @Override // com.oculus.ocui.OCEventHandler
    public void onBackButtonClick() {
        getCommandChannel().playAudio(SoundType.CLOSE);
    }
}
