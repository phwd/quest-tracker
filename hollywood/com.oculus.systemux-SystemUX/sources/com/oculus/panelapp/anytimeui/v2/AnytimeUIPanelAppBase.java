package com.oculus.panelapp.anytimeui.v2;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import com.oculus.common.deviceconfig.DeviceConfigHelper;
import com.oculus.common.gatekeepers.Gatekeeper;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.ocui.R;
import com.oculus.ocui.OCEventHandler;
import com.oculus.os.UnifiedTelemetryLogger;
import com.oculus.os.enterprise.EnterpriseServer;
import com.oculus.os.enterprise.internal.Configuration;
import com.oculus.panelapp.anytimeui.config.ConsumerConfiguration;
import com.oculus.panelapp.anytimeui.config.EnterpriseConfiguration;
import com.oculus.panelapp.anytimeui.config.SystemUXConfiguration;
import com.oculus.panelapp.anytimeui.v2.abtest.StoreOptionalityExperiment;
import com.oculus.panelapp.anytimeui.v2.nux.OnboardingTutorial;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.LibraryViewModel;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsViewModel;
import com.oculus.panelapp.social.SocialBundleConstants;
import com.oculus.systemdialog.DialogManager;
import com.oculus.tablet.logging.ClickEventButtonId;
import com.oculus.tablet.logging.SectionTrackerEvent;
import com.oculus.tablet.view.ViewModelLifecycle;
import com.oculus.userserver.api.OculusUserManager;
import com.oculus.userserver.api.user.OculusUser;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.panels.AndroidPanelApp;
import com.oculus.vrshell.panels.SoundType;
import com.oculus.vrshell.panels.automation.AutomationHelpers;
import com.oculus.vrshell.panels.telemetry.Logger;
import com.oculus.vrshell.panels.telemetry.LoggingApi;
import com.oculus.vrshell.util.ThreadExecutor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class AnytimeUIPanelAppBase extends AndroidPanelApp implements OCEventHandler, Logger {
    private static final String ENVIRONMENT_ARG_ENTERPRISE_CONFIG = "_oc_shell_enterprise_marshalled_configuration";
    private static final String ENVIRONMENT_ARG_ENTERPRISE_CONFIGURATION_MODE_INDEX = "_oc_shell_enterprise_configuration_mode_index";
    protected static final int TABLET_VIEW_HEIGHT = 340;
    protected static final int TABLET_VIEW_WIDTH = 768;
    private static final String TAG = LoggingUtil.tag(AnytimeUIPanelAppBase.class);
    private final DialogManager mDialogManager = new DialogManager();
    private final Map<String, Boolean> mLegacyGKsValues;
    protected LibraryViewModel mLibraryViewModel;
    private LoggingApi mLoggingApi;
    private OculusUser mOculusUser;
    private OculusUserManager mOculusUserManager;
    protected OnboardingTutorial mOnboardingTutorial;
    protected SettingsViewModel mSettingsViewModel;
    protected StoreOptionalityExperiment mStoreOptionalityExperiment;
    private final SystemUXConfiguration mSystemUXConfiguration;
    private final UnifiedTelemetryLogger mUnifiedTelemetryLogger;
    private final boolean mUseDeviceConfig;
    protected Map<ViewModelLifecycle, Integer> mViewModels = new HashMap();

    public String getReturnComponent() {
        return "";
    }

    public AnytimeUIPanelAppBase(Application application, Context context, Surface surface, Map<String, Surface> map, Bundle bundle) {
        super(application, context, surface, map, bundle);
        this.mUnifiedTelemetryLogger = UnifiedTelemetryLogger.getInstance(context);
        this.mUseDeviceConfig = SocialBundleConstants.FB_UPSELL_MUST_INTERACT.equals(getEnvironmentArg("_oc_gk:" + Gatekeeper.DEVICECONFIG_ENABLED.toString()));
        this.mLegacyGKsValues = checkAndMergeValuesFromDeviceConfig();
        if (EnterpriseServer.isInEnterpriseMode(context)) {
            this.mSystemUXConfiguration = EnterpriseConfiguration.init(context, Integer.parseInt((String) this.mEnvironmentArgs.getOrDefault(ENVIRONMENT_ARG_ENTERPRISE_CONFIGURATION_MODE_INDEX, "0")), getConfig());
        } else {
            this.mSystemUXConfiguration = ConsumerConfiguration.builder().setAuiSocialIconsEnabled(true).setGuardianPillButtonsEnabled(true).build();
        }
        this.mLoggingApi = new LoggingApi(this);
        getStoreOptionalityExperiment();
        initializeOculusUserManager(new Runnable() {
            /* class com.oculus.panelapp.anytimeui.v2.$$Lambda$AnytimeUIPanelAppBase$Uneq1KMz04DdbIlFujMuYaNqUxs */

            public final void run() {
                AnytimeUIPanelAppBase.this.lambda$new$1$AnytimeUIPanelAppBase();
            }
        });
    }

    public /* synthetic */ void lambda$new$1$AnytimeUIPanelAppBase() {
        getOnboardingTutorial().maybeInitialize();
    }

    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public void destroy() {
        this.mStoreOptionalityExperiment.destroy();
        this.mStoreOptionalityExperiment = null;
        this.mOnboardingTutorial.destroy();
        this.mOnboardingTutorial = null;
        for (ViewModelLifecycle viewModelLifecycle : this.mViewModels.keySet()) {
            String str = TAG;
            Log.e(str, "ViewModel not released " + viewModelLifecycle.getClass().getSimpleName());
            viewModelLifecycle.destroy();
        }
        super.destroy();
    }

    public Configuration getConfig() {
        String environmentArg = getEnvironmentArg(ENVIRONMENT_ARG_ENTERPRISE_CONFIG);
        if (environmentArg == null) {
            return null;
        }
        return Configuration.fromMarshalledString(environmentArg);
    }

    public boolean isGuardianEnabled() {
        return this.mSystemUXConfiguration.areGuardianPillButtonsEnabled;
    }

    public boolean isGKEnabled(Gatekeeper gatekeeper) {
        if (gatekeeper.shouldUseDeviceConfig() || (this.mUseDeviceConfig && !gatekeeper.isSharedConfigParam())) {
            return DeviceConfigHelper.getBoolean(getContext(), gatekeeper);
        }
        return this.mLegacyGKsValues.getOrDefault(gatekeeper.toString(), false).booleanValue();
    }

    public Map<String, Boolean> checkAndMergeValuesFromDeviceConfig() {
        HashMap hashMap = new HashMap();
        Iterator<String> it = Gatekeeper.getGatekeepers().iterator();
        while (it.hasNext()) {
            String str = it.next().split(" ", 2)[0];
            hashMap.put(str, Boolean.valueOf(SocialBundleConstants.FB_UPSELL_MUST_INTERACT.equals(getEnvironmentArg("_oc_gk:" + str))));
        }
        DeviceConfigHelper.checkAndMergeValues(getContext(), this.mUnifiedTelemetryLogger, hashMap, isOCShellAutomationEnabled());
        return hashMap;
    }

    public boolean isOCShellAutomationEnabled() {
        return SocialBundleConstants.FB_UPSELL_MUST_INTERACT.equals(this.mEnvironmentArgs.getOrDefault(AutomationHelpers.AUTOMATION_ENVIRONMENT_ARG, ""));
    }

    public StoreOptionalityExperiment getStoreOptionalityExperiment() {
        if (this.mStoreOptionalityExperiment == null) {
            this.mStoreOptionalityExperiment = new StoreOptionalityExperiment(this);
        }
        return this.mStoreOptionalityExperiment;
    }

    public OnboardingTutorial getOnboardingTutorial() {
        if (this.mOnboardingTutorial == null) {
            this.mOnboardingTutorial = new OnboardingTutorial(getContext(), this);
        }
        return this.mOnboardingTutorial;
    }

    public void logButtonClick(ClickEventButtonId clickEventButtonId) {
        rawLogEvent("oculus_vrshell_aui_button_press", "button_id", clickEventButtonId.getTelemetryButtonId());
        String str = TAG;
        Log.d(str, "ClickEventButtonId: " + clickEventButtonId.toString());
    }

    public void logSectionEntry(SectionTrackerEvent sectionTrackerEvent) {
        rawLogEvent("oculus_vrshell_aui_section_entry", "section_id", sectionTrackerEvent.getTelemetrySectionId());
    }

    public DialogManager getDialogManager() {
        return this.mDialogManager;
    }

    public void actionNavigate(SystemUXRoute systemUXRoute, String str) {
        actionNavigate(systemUXRoute.path(), str);
    }

    public void actionNavigate(String str, String str2) {
        String str3 = TAG;
        Log.i(str3, "actionNavigate - App:  " + str + ", Uri:  " + str2);
        getCommandChannel().launch(str, str2);
    }

    public SystemUXConfiguration getSystemUXConfig() {
        return this.mSystemUXConfiguration;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public JSONObject convertViewToObject(Resources resources, View view, boolean z) throws JSONException {
        View view2;
        JSONObject convertViewToObject = super.convertViewToObject(resources, view, z);
        if (z && (view2 = (View) view.getTag(R.id.ocui_automation_attachedview_key)) != null) {
            convertViewToObject.put("attachedView", convertViewToObject(resources, view2, z));
        }
        return convertViewToObject;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public List<View> getContentViewsForLayer(String str) {
        List<View> contentViewsForLayer = super.getContentViewsForLayer(str);
        ArrayList arrayList = new ArrayList();
        for (View view : contentViewsForLayer) {
            findAttachedContentViews(arrayList, view);
        }
        contentViewsForLayer.addAll(arrayList);
        return contentViewsForLayer;
    }

    private void findAttachedContentViews(List<View> list, View view) {
        View view2 = (View) view.getTag(R.id.ocui_automation_attachedview_key);
        if (view2 != null) {
            list.add(view2);
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                findAttachedContentViews(list, viewGroup.getChildAt(i));
            }
        }
    }

    /* access modifiers changed from: protected */
    public synchronized void addViewModelReference(ViewModelLifecycle viewModelLifecycle) {
        Integer num = this.mViewModels.get(viewModelLifecycle);
        if (num == null) {
            num = 0;
        }
        Integer valueOf = Integer.valueOf(num.intValue() + 1);
        String str = TAG;
        Log.d(str, "Acquiring viewModel " + viewModelLifecycle.getClass().getSimpleName() + " refCount: " + valueOf);
        this.mViewModels.put(viewModelLifecycle, valueOf);
    }

    /* access modifiers changed from: protected */
    public synchronized boolean removeViewModelReference(ViewModelLifecycle viewModelLifecycle) {
        Integer num = this.mViewModels.get(viewModelLifecycle);
        if (num == null) {
            String str = TAG;
            Log.e(str, "Release ViewModel called on already destroyed ViewModel" + viewModelLifecycle);
            return false;
        }
        Integer valueOf = Integer.valueOf(num.intValue() - 1);
        String str2 = TAG;
        Log.d(str2, "Releasing viewModel " + viewModelLifecycle.getClass().getSimpleName() + " refCount: " + valueOf);
        if (valueOf.intValue() == 0) {
            this.mViewModels.remove(viewModelLifecycle);
            return true;
        }
        this.mViewModels.put(viewModelLifecycle, valueOf);
        return false;
    }

    public synchronized SettingsViewModel acquireSettingsViewModel() {
        if (this.mSettingsViewModel == null) {
            this.mSettingsViewModel = new SettingsViewModel(getContext(), this);
        }
        addViewModelReference(this.mSettingsViewModel);
        return this.mSettingsViewModel;
    }

    public synchronized void releaseSettingsViewModel() {
        if (removeViewModelReference(this.mSettingsViewModel)) {
            this.mSettingsViewModel.destroy();
            this.mSettingsViewModel = null;
        }
    }

    public synchronized LibraryViewModel acquireLibraryViewModel() {
        if (this.mLibraryViewModel == null) {
            this.mLibraryViewModel = new LibraryViewModel(getContext(), this);
        }
        addViewModelReference(this.mLibraryViewModel);
        return this.mLibraryViewModel;
    }

    public synchronized void releaseLibraryViewModel() {
        if (removeViewModelReference(this.mLibraryViewModel)) {
            this.mLibraryViewModel.destroy();
            this.mLibraryViewModel = null;
        }
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

    @Override // com.oculus.vrshell.panels.telemetry.Logger
    @Deprecated
    public void rawLogEvent(String str, String... strArr) {
        this.mLoggingApi.rawLogEvent(str, strArr);
    }

    @Override // com.oculus.vrshell.panels.telemetry.Logger
    @Deprecated
    public void rawLogEvent(String str, Map<String, String> map) {
        this.mLoggingApi.rawLogEvent(str, map);
    }

    @Override // com.oculus.vrshell.panels.telemetry.Logger
    @Deprecated
    public void rawLogJsonEvent(String str, String str2) {
        this.mLoggingApi.rawLogJsonEvent(str, str2);
    }

    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public void frame(String str, float f, float f2, long j, long j2, float f3, float f4, float f5) {
        super.frame(str, f, f2, j, j2, f3, f4, f5);
        String dialogIPC = this.mDialogManager.getDialogIPC();
        if (dialogIPC != null) {
            getCommandChannel().sendCommand(dialogIPC);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public void onSystemDialogResult(String str) {
        this.mDialogManager.handleSystemDialogResult(str);
    }

    public boolean isUserDeviceOwner() {
        OculusUser oculusUser = this.mOculusUser;
        if (oculusUser != null && oculusUser.getUserId() == 0) {
            return true;
        }
        return false;
    }

    private void initializeOculusUserManager(Runnable runnable) {
        ThreadExecutor.getInstance().execute(new Callable(runnable) {
            /* class com.oculus.panelapp.anytimeui.v2.$$Lambda$AnytimeUIPanelAppBase$KyPC7HAtWjs0yYIaaHRgRznaAE */
            private final /* synthetic */ Runnable f$1;

            {
                this.f$1 = r2;
            }

            @Override // java.util.concurrent.Callable
            public final Object call() {
                return AnytimeUIPanelAppBase.this.lambda$initializeOculusUserManager$2$AnytimeUIPanelAppBase(this.f$1);
            }
        });
    }

    public /* synthetic */ Object lambda$initializeOculusUserManager$2$AnytimeUIPanelAppBase(Runnable runnable) throws Exception {
        try {
            this.mOculusUserManager = new OculusUserManager(getContext());
            this.mOculusUser = this.mOculusUserManager.getSelf();
            Log.d(TAG, "Finished Initializing Oculus User Manager");
        } catch (Exception e) {
            Log.e(TAG, "Error getting the user instance", e);
        }
        runnable.run();
        return null;
    }
}
