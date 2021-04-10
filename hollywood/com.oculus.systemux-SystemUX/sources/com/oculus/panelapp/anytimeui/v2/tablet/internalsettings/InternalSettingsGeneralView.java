package com.oculus.panelapp.anytimeui.v2.tablet.internalsettings;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import androidx.core.app.NotificationCompat;
import androidx.databinding.ViewDataBinding;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;
import com.oculus.os.PreferencesManager;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletInternalSettingsGeneralViewV2Binding;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.panelapp.anytimeui.v2.bar.BarViewModel;
import com.oculus.panelapp.anytimeui.v2.tablet.common.ToggleButton;
import com.oculus.systemdialog.CommonSystemDialog;
import com.oculus.systemdialog.DialogDefinitionCommon;
import com.oculus.systemdialog.DialogResult;
import com.oculus.systemdialog.DialogResultHandler;
import com.oculus.tablet.logging.ClickEventButtonId;
import com.oculus.tablet.logging.SectionTrackerEvent;
import com.oculus.tablet.view.BaseView;
import com.oculus.vrshell.panels.AndroidPanelApp;
import java.io.File;
import java.io.IOException;
import org.json.JSONException;

public class InternalSettingsGeneralView extends BaseView {
    private static final String DEBUG_LAUNCH_APPS_IN_TABLET_SPACE_PREF_KEY = "debug_launch_apps_in_tablet_space";
    public static final int FILE_PICKER_NOTIFICATION_ID = 512345;
    private static final String TAG = LoggingUtil.tag(InternalSettingsGeneralView.class);
    private BarViewModel mBarViewModel;
    private AnytimeTabletInternalSettingsGeneralViewV2Binding mBinding;
    private Context mContext;
    private AnytimeUIAndroidPanelAppV2 mPanelApp;
    private final PreferencesManager mPreferencesManager = new PreferencesManager();

    public InternalSettingsGeneralView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        Log.d(TAG, "Constructing InternalSettingsGeneralView");
    }

    public void initialize(AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, ViewDataBinding viewDataBinding) {
        super.initialize((AndroidPanelApp) anytimeUIAndroidPanelAppV2, viewDataBinding);
        Log.d(TAG, "Initializing InternalSettingsGeneralView");
        this.mPanelApp = anytimeUIAndroidPanelAppV2;
        this.mBinding = (AnytimeTabletInternalSettingsGeneralViewV2Binding) viewDataBinding;
        this.mBarViewModel = this.mPanelApp.acquireBarViewModel();
        initializeShellSettingsButton();
        initializePinDebugBarButton();
        initializeFilePickerButton();
        initializeOnBoardingTutorial();
        initializeFilePreviewerDebugButton();
        initializeLaunchAppsInTabletSpaceButton();
        this.mPanelApp.logSectionEntry(SectionTrackerEvent.INTERNAL_SETTINGS_GENERAL_TABLET);
    }

    @SuppressLint({"SetTextI18n"})
    private void initializeOnBoardingTutorial() {
        this.mBinding.onboardingTutorial.setEventHandler(this.mPanelApp);
        this.mBinding.onboardingTutorial.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.internalsettings.$$Lambda$InternalSettingsGeneralView$dQ7ELKQOg1713mVZWdIuAFqurs */

            public final void onClick(View view) {
                InternalSettingsGeneralView.this.lambda$initializeOnBoardingTutorial$177$InternalSettingsGeneralView(view);
            }
        });
    }

    public /* synthetic */ void lambda$initializeOnBoardingTutorial$177$InternalSettingsGeneralView(View view) {
        this.mPanelApp.getOnboardingTutorial().setToDebugState();
        this.mBinding.onboardingTutorial.setText("Restart Shell to view AUI Tutorial");
    }

    @Override // com.oculus.tablet.view.BaseView
    public void destroy() {
        Log.d(TAG, "Destroying InternalSettingsGeneralView");
        this.mPanelApp.releaseBarViewModel();
    }

    @Override // com.oculus.tablet.view.BaseView
    public void onShow(String str) {
        Log.d(TAG, "Showing InternalSettingsGeneralView");
    }

    @Override // com.oculus.tablet.view.BaseView
    public void onHide() {
        Log.d(TAG, "Hiding InternalSettingsGeneralView");
    }

    private void initializeShellSettingsButton() {
        Log.d(TAG, "Initializing shell settings button");
        OCButton oCButton = this.mBinding.debugShellSettingsButton;
        oCButton.setEventHandler(this.mPanelApp);
        oCButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.internalsettings.$$Lambda$InternalSettingsGeneralView$iXH0WkSwQO1WfNS3hjJvXH0GfuQ */

            public final void onClick(View view) {
                InternalSettingsGeneralView.this.lambda$initializeShellSettingsButton$178$InternalSettingsGeneralView(view);
            }
        });
        Log.d(TAG, "Initialized shell settings button");
    }

    public /* synthetic */ void lambda$initializeShellSettingsButton$178$InternalSettingsGeneralView(View view) {
        this.mPanelApp.actionNavigate("com.oculus.vrshell/com.oculus.panelapp.debug.ShellDebugPanelService", "");
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_INTERNAL_SETTINGS_SHELL_DEBUG);
    }

    private void initializePinDebugBarButton() {
        Log.d(TAG, "Initializing pin debug bar button");
        this.mBinding.debugPinDebugBarButton.setSelected(this.mBarViewModel.getDebugBarPinned());
        this.mBinding.debugPinDebugBarButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.internalsettings.$$Lambda$InternalSettingsGeneralView$h4VeryzHyD3ucO_uF8CbC9VGhoI */

            public final void onClick(View view) {
                InternalSettingsGeneralView.this.lambda$initializePinDebugBarButton$179$InternalSettingsGeneralView(view);
            }
        });
        Log.d(TAG, "Initializing pin debug bar button");
    }

    public /* synthetic */ void lambda$initializePinDebugBarButton$179$InternalSettingsGeneralView(View view) {
        boolean z = !this.mBinding.debugPinDebugBarButton.isSelected();
        this.mBinding.debugPinDebugBarButton.setSelected(z);
        this.mBarViewModel.setDebugBarPinned(z);
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_INTERNAL_SETTINGS_PIN_DEBUG_BAR);
    }

    private void initializeFilePickerButton() {
        Log.d(TAG, "Initializing file picker button");
        OCButton oCButton = this.mBinding.debugFilePickerButton;
        OCTextView oCTextView = this.mBinding.debugFilePickerSelectedFile;
        oCButton.setEventHandler(this.mPanelApp);
        oCButton.setOnClickListener(new View.OnClickListener(oCTextView) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.internalsettings.$$Lambda$InternalSettingsGeneralView$NQ842Buxcqg61rxbLJy2ZauXy_g */
            private final /* synthetic */ OCTextView f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                InternalSettingsGeneralView.this.lambda$initializeFilePickerButton$181$InternalSettingsGeneralView(this.f$1, view);
            }
        });
        Log.d(TAG, "Initialized file picker button");
    }

    public /* synthetic */ void lambda$initializeFilePickerButton$181$InternalSettingsGeneralView(OCTextView oCTextView, View view) {
        DialogDefinitionCommon dialogDefinitionCommon = new DialogDefinitionCommon(CommonSystemDialog.FILE_PICKER);
        dialogDefinitionCommon.setDialogResultHandler(new DialogResultHandler(oCTextView) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.internalsettings.$$Lambda$InternalSettingsGeneralView$5PZsX_heJ4qd1DuDfWu4vxmalA */
            private final /* synthetic */ OCTextView f$1;

            {
                this.f$1 = r2;
            }

            @Override // com.oculus.systemdialog.DialogResultHandler
            public final boolean handleDialogResult(DialogResult dialogResult) {
                return InternalSettingsGeneralView.this.lambda$null$180$InternalSettingsGeneralView(this.f$1, dialogResult);
            }
        });
        this.mPanelApp.getDialogManager().showDialog(dialogDefinitionCommon);
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_INTERNAL_SETTINGS_FILE_PICKER);
    }

    public /* synthetic */ boolean lambda$null$180$InternalSettingsGeneralView(OCTextView oCTextView, DialogResult dialogResult) {
        String str = TAG;
        Log.i(str, "Got dialog action: " + dialogResult.getDialogAction());
        try {
            String string = dialogResult.getDialogResultJson().getJSONArray(DialogResult.DIALOG_SELECTED_LIST_ITEM_IDS_KEY).getString(0);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this.mContext);
            builder.setContentTitle("File Selected:").setContentText(new File(string).getName()).setPriority(1).setSmallIcon(R.drawable.ic_home);
            ((NotificationManager) this.mContext.getSystemService("notification")).notify(FILE_PICKER_NOTIFICATION_ID, builder.build());
            try {
                oCTextView.setText(new File(string).getCanonicalPath());
            } catch (IOException e) {
                Log.e(TAG, "Unable to get the canonical file path", e);
            }
            return true;
        } catch (JSONException unused) {
            Log.i(TAG, "No selected file");
            return true;
        }
    }

    private void initializeFilePreviewerDebugButton() {
        Log.d(TAG, "Initializing file previewer debug button");
        OCButton oCButton = this.mBinding.buttonFilePreviewerDebug;
        oCButton.setEventHandler(this.mPanelApp);
        oCButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.internalsettings.$$Lambda$InternalSettingsGeneralView$R2fllQhrXDt3DszEejbMlZ9ITVU */

            public final void onClick(View view) {
                InternalSettingsGeneralView.this.lambda$initializeFilePreviewerDebugButton$182$InternalSettingsGeneralView(view);
            }
        });
        Log.d(TAG, "Initialized file previewer debug button");
    }

    public /* synthetic */ void lambda$initializeFilePreviewerDebugButton$182$InternalSettingsGeneralView(View view) {
        this.mPanelApp.getDialogManager().showDialog(new DialogDefinitionCommon(CommonSystemDialog.FILE_PREVIEWER));
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_INTERNAL_SETTINGS_FILE_PREVIEWER);
    }

    private void initializeLaunchAppsInTabletSpaceButton() {
        Log.d(TAG, "Initializing launch apps in tablet space button");
        Pair<Boolean, Boolean> pair = this.mPreferencesManager.getBoolean(DEBUG_LAUNCH_APPS_IN_TABLET_SPACE_PREF_KEY);
        boolean z = ((Boolean) pair.first).booleanValue() && ((Boolean) pair.second).booleanValue();
        ToggleButton toggleButton = this.mBinding.debugLaunchAppsInTabletSpaceButton;
        toggleButton.setSelected(z);
        toggleButton.setOnClickListener(new View.OnClickListener(toggleButton) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.internalsettings.$$Lambda$InternalSettingsGeneralView$PIFLybtBppbVtDizqtrlTnGtkZM */
            private final /* synthetic */ ToggleButton f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                InternalSettingsGeneralView.this.lambda$initializeLaunchAppsInTabletSpaceButton$183$InternalSettingsGeneralView(this.f$1, view);
            }
        });
        Log.d(TAG, "Initialized launch apps in tablet space button");
    }

    public /* synthetic */ void lambda$initializeLaunchAppsInTabletSpaceButton$183$InternalSettingsGeneralView(ToggleButton toggleButton, View view) {
        boolean z = !toggleButton.isSelected();
        this.mBinding.debugLaunchAppsInTabletSpaceButton.setSelected(z);
        this.mPreferencesManager.set(DEBUG_LAUNCH_APPS_IN_TABLET_SPACE_PREF_KEY, z);
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_INTERNAL_SETTINGS_LAUNCH_APPS_IN_TABLET_SPACE);
    }
}
