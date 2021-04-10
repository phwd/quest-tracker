package com.oculus.panelapp.anytimeui.v2.nux;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.oculus.assistant.service.api.panel.AssistantComponentContract;
import com.oculus.assistant.service.api.panel.AssistantDialogContract;
import com.oculus.assistant.service.api.panel.AssistantUiLifecycleContract;
import com.oculus.assistant.service.api.panel.AttentionSystemContract;
import com.oculus.common.gatekeepers.Gatekeeper;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.os.PreferencesManager;
import com.oculus.os.SettingsManager;
import com.oculus.os.SettingsObserverCallback;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIPanelAppBase;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.LibraryAppMigration;
import com.oculus.panelapp.anytimeui.v2.tablet.common.Tablet;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.util.DeviceProperties;
import com.oculus.vrshell.util.HorizonUtil;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OnboardingTutorial {
    private static final String ASSISTANT_DISMISSED_EVENT_ACTION = "com.oculus.assistant.ACTION_UI_CONTROL_SERVICE";
    private static final String ASSISTANT_INTENT_ACTION = "com.oculus.assistant.COMMAND";
    private static final String ASSISTANT_INTENT_EXTRA_COMMAND_KEY = "command";
    private static final String ASSISTANT_INTENT_EXTRA_DATA_KEY = "data";
    private static final String ASSISTANT_INTENT_EXTRA_HIDE_ATTENTION = "hide-attention";
    private static final String ASSISTANT_INTENT_EXTRA_SHOW_ATTENTION = "show-attention";
    private static final String ASSISTANT_INTENT_RECEIVER = "com.oculus.vrshell.AssistantBroadcastReceiver";
    private static final String ASSISTANT_LIFECYCLE_EVENT_ACTION = "com.oculus.assistant.UI_LIFECYCLE_EVENT";
    private static final String AUDIO_ID = "aui_onboarding_audio_id";
    private static final String LOGGING_EVENT_NAME = "oculus_auiv2_onboarding_tutorial";
    private static final String ONBOARDING_TUTORIAL_PREFERENCES_KEY = "aui_onboarding_tutorial_state";
    private static final String TAG = LoggingUtil.tag(OnboardingTutorial.class);
    private BroadcastReceiver mAssistantBroadcastReceiver;
    private final Context mContext;
    private Step mCurrentStep = Step.NOT_STARTED;
    private SettingsObserverCallback mFirstTimeNuxCompleteCallback;
    private Handler mHandler;
    private final boolean mIsFirstTimeUser;
    private final boolean mIsForceDebug;
    private Set<OnboardingTutorialChangeListener> mOnChangeListeners = new HashSet();
    private final AnytimeUIPanelAppBase mPanelApp;
    private final PreferencesManager mPreferencesManager = new PreferencesManager();
    private final SettingsManager mSettingsManager = new SettingsManager();

    /* access modifiers changed from: package-private */
    @FunctionalInterface
    public interface OnDismissed {
        void run();
    }

    /* access modifiers changed from: package-private */
    @FunctionalInterface
    public interface OnDisplayed {
        void run(String str, String str2);
    }

    public interface OnboardingTutorialChangeListener {
        void onStepChanged(Step step);
    }

    public OnboardingTutorial(Context context, AnytimeUIPanelAppBase anytimeUIPanelAppBase) {
        Log.d(TAG, "Initialize Onboarding Tutorial");
        this.mContext = context;
        this.mPanelApp = anytimeUIPanelAppBase;
        this.mHandler = new Handler(this.mContext.getMainLooper());
        boolean z = true;
        this.mIsFirstTimeUser = !isNuxCompleted();
        Pair<Boolean, String> string = this.mPreferencesManager.getString(ONBOARDING_TUTORIAL_PREFERENCES_KEY);
        if (((Boolean) string.first).booleanValue()) {
            this.mIsForceDebug = Step.fromString((String) string.second) != Step.DEBUG ? false : z;
        } else {
            this.mIsForceDebug = false;
        }
        if (shouldInitializeTutorial()) {
            this.mCurrentStep = Step.PENDING_INITIALIZATION;
        }
    }

    public void maybeInitialize() {
        if (this.mCurrentStep == Step.PENDING_INITIALIZATION) {
            initialize();
        }
    }

    private boolean shouldInitializeTutorial() {
        if (!this.mIsFirstTimeUser && !this.mIsForceDebug) {
            Log.d(TAG, "Not a first time user; skipping the tutorial");
            return false;
        } else if (this.mPanelApp.isOCShellAutomationEnabled() && !this.mIsForceDebug) {
            Log.d(TAG, "In automation mode; skipping the tutorial");
            return false;
        } else if (this.mPanelApp.isGKEnabled(Gatekeeper.AUI_ONBOARDING_TUTORIAL)) {
            return true;
        } else {
            Log.d(TAG, "Not in experiment; skipping the tutorial");
            setAndSaveStep(Step.NOT_IN_EXPERIMENT);
            return false;
        }
    }

    private void initialize() {
        if (this.mPanelApp.isUserDeviceOwner()) {
            Log.d(TAG, "Refetch library for primary user");
            HorizonUtil.refetch(this.mContext, new HorizonUtil.RefetchHandler() {
                /* class com.oculus.panelapp.anytimeui.v2.nux.$$Lambda$OnboardingTutorial$yDY_4DE6acA0rESre4SYTuhgs0 */

                @Override // com.oculus.vrshell.util.HorizonUtil.RefetchHandler
                public final void onCompleted(boolean z) {
                    OnboardingTutorial.this.lambda$initialize$76$OnboardingTutorial(z);
                }
            });
            return;
        }
        Log.d(TAG, "Initializing as secondary user");
        queueTutorial();
        initializeAssistantReceiver();
        startTutorialWhenReady();
    }

    public /* synthetic */ void lambda$initialize$76$OnboardingTutorial(boolean z) {
        if (!this.mIsFirstTimeUser || !DeviceProperties.supportsAppMigration() || (z && !LibraryAppMigration.hasMigrationCandidateApps(this.mContext))) {
            queueTutorial();
        } else {
            Log.d(TAG, "User has migratable apps this is not a new user; skipping the tutorial");
            setAndSaveStep(Step.NOT_BRAND_NEW_USER);
        }
        initializeAssistantReceiver();
        startTutorialWhenReady();
    }

    private boolean isNuxCompleted() {
        return this.mSettingsManager.getBoolean(SettingsManager.FIRST_TIME_NUX_COMPLETE, false);
    }

    private void startTutorialWhenReady() {
        if (this.mCurrentStep == Step.QUEUED || this.mCurrentStep == Step.NOT_BRAND_NEW_USER) {
            if (isNuxCompleted()) {
                handleNuxCompleted();
                return;
            }
            this.mFirstTimeNuxCompleteCallback = new SettingsObserverCallback() {
                /* class com.oculus.panelapp.anytimeui.v2.nux.$$Lambda$OnboardingTutorial$jZxjlCnUQG1dl0vNk4pqMvuxl70 */

                @Override // com.oculus.os.SettingsObserverCallback
                public final void onSettingChange(String str) {
                    OnboardingTutorial.this.lambda$startTutorialWhenReady$77$OnboardingTutorial(str);
                }
            };
            this.mSettingsManager.registerSettingsObserver(SettingsManager.FIRST_TIME_NUX_COMPLETE, this.mFirstTimeNuxCompleteCallback, this.mHandler);
        } else if (this.mCurrentStep == Step.DEBUG) {
            startTutorial();
        }
    }

    public /* synthetic */ void lambda$startTutorialWhenReady$77$OnboardingTutorial(String str) {
        if (isNuxCompleted()) {
            handleNuxCompleted();
        }
    }

    private void handleNuxCompleted() {
        if (this.mCurrentStep == Step.QUEUED) {
            startTutorial();
        } else if (this.mCurrentStep == Step.NOT_BRAND_NEW_USER) {
            this.mPanelApp.actionNavigate(SystemUXRoute.HOME, "");
        }
    }

    private void queueTutorial() {
        if (this.mIsFirstTimeUser) {
            Log.d(TAG, "Initializing as first time user");
            setAndSaveStep(Step.QUEUED);
            return;
        }
        Log.d(TAG, "Initializing as debug");
        setAndSaveStep(Step.DEBUG);
    }

    private void initializeAssistantReceiver() {
        if (this.mCurrentStep == Step.QUEUED || this.mCurrentStep == Step.DEBUG) {
            Log.d(TAG, "Registering assistant lifecycle receivers");
            this.mAssistantBroadcastReceiver = registerAssistantBroadcastListener(this.mContext, new OnDisplayed() {
                /* class com.oculus.panelapp.anytimeui.v2.nux.$$Lambda$OnboardingTutorial$p_ClruxbCyCdjpBL2ODA2snJk */

                @Override // com.oculus.panelapp.anytimeui.v2.nux.OnboardingTutorial.OnDisplayed
                public final void run(String str, String str2) {
                    OnboardingTutorial.this.lambda$initializeAssistantReceiver$78$OnboardingTutorial(str, str2);
                }
            }, new OnDismissed() {
                /* class com.oculus.panelapp.anytimeui.v2.nux.$$Lambda$OnboardingTutorial$pmLuLjEhKxk03YvHkgxcRUE45As */

                @Override // com.oculus.panelapp.anytimeui.v2.nux.OnboardingTutorial.OnDismissed
                public final void run() {
                    OnboardingTutorial.this.lambda$initializeAssistantReceiver$79$OnboardingTutorial();
                }
            });
        }
    }

    public /* synthetic */ void lambda$initializeAssistantReceiver$78$OnboardingTutorial(String str, String str2) {
        Step fromString = Step.fromString(str);
        if (AssistantUiLifecycleContract.SurfaceEvents.SURFACE_SHOWN.equals(str2) && fromString != null) {
            Log.d(TAG, String.format("Displaying attention system for %s", fromString.name()));
            int i = AnonymousClass2.$SwitchMap$com$oculus$panelapp$anytimeui$v2$nux$OnboardingTutorial$Step[fromString.ordinal()];
            if (i == 4 || i == 6 || i == 8) {
                setAndSaveStep(fromString);
            }
            playAudioPrompt(fromString);
        }
    }

    public /* synthetic */ void lambda$initializeAssistantReceiver$79$OnboardingTutorial() {
        stopAudioPrompt();
        setAndSaveStep(Step.SKIPPED);
    }

    private static BroadcastReceiver registerAssistantBroadcastListener(Context context, final OnDisplayed onDisplayed, final OnDismissed onDismissed) {
        AnonymousClass1 r0 = new BroadcastReceiver() {
            /* class com.oculus.panelapp.anytimeui.v2.nux.OnboardingTutorial.AnonymousClass1 */

            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (action == null) {
                    Log.w(OnboardingTutorial.TAG, "Received an intent with no action.");
                    return;
                }
                char c = 65535;
                int hashCode = action.hashCode();
                if (hashCode != -1569033918) {
                    if (hashCode == -95292951 && action.equals("com.oculus.assistant.ACTION_UI_CONTROL_SERVICE")) {
                        c = 0;
                    }
                } else if (action.equals("com.oculus.assistant.UI_LIFECYCLE_EVENT")) {
                    c = 1;
                }
                if (c == 0) {
                    onDismissed.run();
                } else if (c == 1) {
                    onDisplayed.run(intent.getStringExtra(AssistantUiLifecycleContract.EXTRA_SURFACE_ID), intent.getStringExtra(AssistantUiLifecycleContract.EXTRA_EVENT_ID));
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.oculus.assistant.ACTION_UI_CONTROL_SERVICE");
        intentFilter.addAction("com.oculus.assistant.UI_LIFECYCLE_EVENT");
        context.registerReceiver(r0, intentFilter);
        return r0;
    }

    @VisibleForTesting
    public void startTutorial() {
        setAndSaveStep(Step.STARTED);
        showAttentionSystem(this.mContext, new Intent(), this.mContext.getResources().getString(DeviceProperties.supportsBackwardCompatibilityTutorial() ? R.string.aui_onboarding_quest_welcome_message : R.string.aui_onboarding_quest_2_welcome_message), Step.STARTED, false);
        showAttentionSystem(this.mContext, buildAssistantEmbeddedImageLayout(this.mContext.getResources().getString(R.string.aui_onboarding_apps_button_prompt_part_1), this.mContext.getResources().getString(R.string.aui_onboarding_apps_button_prompt_part_2), R.drawable.oc_icon_apps_filled_24_d2d2d2), Step.BAR, true);
    }

    private void playAudioPrompt(Step step) {
        String audioFile = step.getAudioFile();
        if (!TextUtils.isEmpty(audioFile)) {
            stopAudioPrompt();
            this.mPanelApp.queueRawCommand(String.format("audio play apk://%s/assets/audio/aui/%s id %s", "com.oculus.systemux", audioFile, AUDIO_ID));
        }
    }

    private void stopAudioPrompt() {
        this.mPanelApp.queueRawCommand(String.format("audio stop %s", AUDIO_ID));
    }

    @VisibleForTesting
    public void onLibraryShown() {
        if (this.mCurrentStep == Step.STARTED || this.mCurrentStep == Step.BAR) {
            setAndSaveStep(Step.LIBRARY_OVERVIEW);
            hideAttentionSystem();
            showAttentionSystem(this.mContext, new Intent(), this.mContext.getResources().getString(R.string.aui_onboarding_library_explanation), Step.LIBRARY_OVERVIEW, false);
            showAttentionSystem(this.mContext, buildAssistantEmbeddedImageLayout(this.mContext.getResources().getString(R.string.aui_onboarding_home_button_prompt_part_1), this.mContext.getResources().getString(R.string.aui_onboarding_home_button_prompt_part_2), R.drawable.oc_icon_home_filled_24_d2d2d2), Step.LIBRARY, true);
        } else if (this.mCurrentStep == Step.HIGHLIGHT_APPS) {
            setAndSaveStep(Step.LIBRARY);
        }
    }

    public void onClickHome() {
        if (this.mCurrentStep == Step.LIBRARY_OVERVIEW || this.mCurrentStep == Step.LIBRARY) {
            setAndSaveStep(Step.LAUNCHED_EXPLORE);
            hideAttentionSystem();
            showAttentionSystem(this.mContext, new Intent(), this.mContext.getResources().getString(R.string.aui_onboarding_completed_message_1), Step.LAUNCHED_EXPLORE, false);
            showAttentionSystem(this.mContext, new Intent(), this.mContext.getResources().getString(R.string.aui_onboarding_completed_message_2), Step.COMPLETED, true);
        }
    }

    public Step getCurrentStep() {
        return this.mCurrentStep;
    }

    public boolean isActive() {
        switch (this.mCurrentStep) {
            case Step.PENDING_INITIALIZATION:
            case Step.QUEUED:
            case Step.STARTED:
            case Step.BAR:
            case Step.LIBRARY_OVERVIEW:
            case Step.LIBRARY:
            case Step.HIGHLIGHT_APPS:
                return true;
            default:
                return false;
        }
    }

    public void setToDebugState() {
        setAndSaveStep(Step.DEBUG);
    }

    @VisibleForTesting
    public void setAndSaveStep(Step step) {
        Log.d(TAG, String.format("Proceeding from %s to %s", this.mCurrentStep.name(), step.name()));
        this.mCurrentStep = step;
        this.mPreferencesManager.set(ONBOARDING_TUTORIAL_PREFERENCES_KEY, step.name());
        this.mPanelApp.rawLogEvent(LOGGING_EVENT_NAME, "state", step.name());
        for (OnboardingTutorialChangeListener onboardingTutorialChangeListener : this.mOnChangeListeners) {
            onboardingTutorialChangeListener.onStepChanged(step);
        }
    }

    @VisibleForTesting
    public static void showAttentionSystem(Context context, Intent intent, String str, Step step, boolean z) {
        try {
            intent.setAction(ASSISTANT_INTENT_ACTION);
            intent.setClassName("com.oculus.vrshell", "com.oculus.vrshell.AssistantBroadcastReceiver");
            intent.putExtra(ASSISTANT_INTENT_EXTRA_COMMAND_KEY, "show-attention");
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("message", str);
            jSONObject.put(AttentionSystemContract.KEY_DURATION, step.getAudioDuration());
            jSONObject.put("hit-testable", true);
            jSONObject.put(AssistantDialogContract.TARGET, "com.oculus.systemux");
            jSONObject.put("id", step.name());
            jSONObject.put(AssistantDialogContract.QUEUE, z);
            jSONObject.put(AssistantDialogContract.CLEAR_QUEUE_ON_CLOSE, true);
            intent.putExtra("data", jSONObject.toString());
            context.sendBroadcast(intent);
        } catch (JSONException e) {
            Log.e(TAG, "Error building assistant attention data", e);
        }
    }

    private static void showAttentionSystem(Context context, JSONObject jSONObject, Step step, boolean z) {
        try {
            Intent intent = new Intent();
            intent.setAction(ASSISTANT_INTENT_ACTION);
            intent.setClassName("com.oculus.vrshell", "com.oculus.vrshell.AssistantBroadcastReceiver");
            intent.putExtra(ASSISTANT_INTENT_EXTRA_COMMAND_KEY, "show-attention");
            jSONObject.put("hit-testable", true);
            jSONObject.put(AttentionSystemContract.KEY_DURATION, step.getAudioDuration());
            jSONObject.put(AssistantDialogContract.TARGET, "com.oculus.systemux");
            jSONObject.put("id", step.name());
            jSONObject.put(AssistantDialogContract.QUEUE, z);
            jSONObject.put(AssistantDialogContract.CLEAR_QUEUE_ON_CLOSE, true);
            intent.putExtra("data", jSONObject.toString());
            context.sendBroadcast(intent);
        } catch (JSONException e) {
            Log.e(TAG, "Error building assistant attention data", e);
        }
    }

    private static JSONObject buildAssistantEmbeddedImageLayout(String str, String str2, @DrawableRes int i) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(buildAssistantTextComponent(str));
            jSONArray.put(buildAssistantImageComponent(i));
            jSONArray.put(buildAssistantTextComponent(str2));
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("components", jSONArray);
            jSONObject2.put(AssistantComponentContract.Components.LinearLayoutComponent.ORIENTATION, 0);
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("type", AssistantComponentContract.Components.LinearLayoutComponent.TYPE);
            jSONObject3.put("data", jSONObject2);
            JSONArray jSONArray2 = new JSONArray();
            jSONArray2.put(jSONObject3);
            JSONObject jSONObject4 = new JSONObject();
            jSONObject4.put("components", jSONArray2);
            jSONObject.put("data", jSONObject4);
        } catch (JSONException e) {
            Log.e(TAG, "Error building assistant custom layout", e);
        }
        return jSONObject;
    }

    private static JSONObject buildAssistantTextComponent(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(AssistantComponentContract.Components.TextComponent.VALUE, str);
            jSONObject2.put(AssistantComponentContract.Components.TextComponent.STYLE, AssistantComponentContract.Components.TextComponent.Styles.BODY1);
            jSONObject2.put("layout-gravity", AssistantComponentContract.Gravity.CENTER_VERTICAL);
            jSONObject.put("type", "text");
            jSONObject.put("data", jSONObject2);
        } catch (JSONException e) {
            Log.e(TAG, "Error building assistant text component", e);
        }
        return jSONObject;
    }

    private static JSONObject buildAssistantImageComponent(@DrawableRes int i) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("package", "com.oculus.systemux");
            jSONObject2.put("res", i);
            jSONObject2.put("height", 24);
            jSONObject2.put("width", 24);
            jSONObject2.put("layout-gravity", AssistantComponentContract.Gravity.CENTER_VERTICAL);
            jSONObject2.put(AssistantComponentContract.Margins.MARGINS, 4);
            jSONObject.put("type", "image");
            jSONObject.put("data", jSONObject2);
        } catch (JSONException e) {
            Log.e(TAG, "Error building assistant image component", e);
        }
        return jSONObject;
    }

    public void destroy() {
        SettingsObserverCallback settingsObserverCallback = this.mFirstTimeNuxCompleteCallback;
        if (settingsObserverCallback != null) {
            this.mSettingsManager.unregisterSettingsObserver(SettingsManager.FIRST_TIME_NUX_COMPLETE, settingsObserverCallback);
        }
        BroadcastReceiver broadcastReceiver = this.mAssistantBroadcastReceiver;
        if (broadcastReceiver != null) {
            this.mContext.unregisterReceiver(broadcastReceiver);
        }
        if (isActive()) {
            hideAttentionSystem();
        }
    }

    private void hideAttentionSystem() {
        Intent intent = new Intent();
        intent.setAction(ASSISTANT_INTENT_ACTION);
        intent.setClassName("com.oculus.vrshell", "com.oculus.vrshell.AssistantBroadcastReceiver");
        intent.putExtra(ASSISTANT_INTENT_EXTRA_COMMAND_KEY, "hide-attention");
        this.mContext.sendBroadcast(intent);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.panelapp.anytimeui.v2.nux.OnboardingTutorial$2  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$panelapp$anytimeui$v2$tablet$common$Tablet = new int[Tablet.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(24:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|(3:31|32|34)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(26:0|1|2|3|(2:5|6)|7|(2:9|10)|11|13|14|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|(3:31|32|34)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(28:0|1|2|3|(2:5|6)|7|(2:9|10)|11|13|14|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|34) */
        /* JADX WARNING: Can't wrap try/catch for region: R(29:0|1|2|3|5|6|7|(2:9|10)|11|13|14|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|34) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0048 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0052 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x005c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0066 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0071 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x007c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x0087 */
        static {
            /*
            // Method dump skipped, instructions count: 148
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.anytimeui.v2.nux.OnboardingTutorial.AnonymousClass2.<clinit>():void");
        }
    }

    public void onTabletShown(Tablet tablet) {
        if (isActive()) {
            int i = AnonymousClass2.$SwitchMap$com$oculus$panelapp$anytimeui$v2$tablet$common$Tablet[tablet.ordinal()];
            if (i == 1 || i == 2 || i == 3) {
                onLibraryShown();
            } else if (i == 4) {
                if (this.mCurrentStep == Step.LIBRARY || this.mCurrentStep == Step.LIBRARY_OVERVIEW) {
                    setAndSaveStep(Step.HIGHLIGHT_APPS);
                }
            }
        }
    }

    public void addOnChangeListener(OnboardingTutorialChangeListener onboardingTutorialChangeListener) {
        this.mOnChangeListeners.add(onboardingTutorialChangeListener);
    }

    public void removeOnChangeListener(OnboardingTutorialChangeListener onboardingTutorialChangeListener) {
        this.mOnChangeListeners.remove(onboardingTutorialChangeListener);
    }

    /* JADX INFO: Failed to restore enum class, 'enum' modifier removed */
    public static final class Step extends Enum<Step> {
        private static final /* synthetic */ Step[] $VALUES = {DEBUG, NOT_STARTED, NOT_IN_EXPERIMENT, NOT_BRAND_NEW_USER, PENDING_INITIALIZATION, QUEUED, STARTED, BAR, LIBRARY_OVERVIEW, LIBRARY, LAUNCHED_EXPLORE, COMPLETED, SKIPPED, HIGHLIGHT_APPS};
        public static final Step BAR = new Step("BAR", 7, "onboarding_tutorial_apps_prompt.ogg", 60000);
        public static final Step COMPLETED = new Step("COMPLETED", 11, "onboarding_tutorial_completed.ogg", 5000);
        public static final Step DEBUG = new Step("DEBUG", 0);
        public static final Step HIGHLIGHT_APPS = new Step("HIGHLIGHT_APPS", 13);
        public static final Step LAUNCHED_EXPLORE = new Step("LAUNCHED_EXPLORE", 10, "onboarding_tutorial_launched_explore.ogg", 3500);
        public static final Step LIBRARY = new Step("LIBRARY", 9, "onboarding_tutorial_explore_prompt.ogg", 60000);
        public static final Step LIBRARY_OVERVIEW = new Step("LIBRARY_OVERVIEW", 8, "onboarding_tutorial_library_overview.ogg", 5000);
        public static final Step NOT_BRAND_NEW_USER = new Step("NOT_BRAND_NEW_USER", 3);
        public static final Step NOT_IN_EXPERIMENT = new Step("NOT_IN_EXPERIMENT", 2);
        public static final Step NOT_STARTED = new Step("NOT_STARTED", 1);
        public static final Step PENDING_INITIALIZATION = new Step("PENDING_INITIALIZATION", 4);
        public static final Step QUEUED = new Step("QUEUED", 5);
        public static final Step SKIPPED = new Step("SKIPPED", 12);
        public static final Step STARTED = new Step("STARTED", 6, DeviceProperties.supportsBackwardCompatibilityTutorial() ? "onboarding_tutorial_welcome_quest.ogg" : "onboarding_tutorial_welcome_quest_2.ogg", 4500);
        private final String mAudioFile;
        private final int mDuration;

        public static Step valueOf(String str) {
            return (Step) Enum.valueOf(Step.class, str);
        }

        public static Step[] values() {
            return (Step[]) $VALUES.clone();
        }

        private Step(String str, int i) {
            this.mAudioFile = null;
            this.mDuration = 0;
        }

        private Step(String str, int i, String str2, int i2) {
            this.mAudioFile = str2;
            this.mDuration = i2;
        }

        @Nullable
        public String getAudioFile() {
            return this.mAudioFile;
        }

        public int getAudioDuration() {
            return this.mDuration;
        }

        @Nullable
        public static Step fromString(String str) {
            Step[] values = values();
            for (Step step : values) {
                if (step.name().equalsIgnoreCase(str)) {
                    return step;
                }
            }
            return null;
        }
    }
}
