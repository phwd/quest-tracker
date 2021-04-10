package com.oculus.panelapp.anytimeui.v2.tablet.settings;

import android.view.LayoutInflater;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.oculus.ocui.OCEventHandler;
import com.oculus.panelapp.anytimeui.databinding.SettingsNavigationActionViewBinding;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.BaseSettingsActionType;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsNavigationActionType;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.util.SettingsLogger;
import com.oculus.systemdialog.DialogDefinitionBase;
import com.oculus.vrshell.SystemUXRoute;

public class SettingsNavigationActionType extends BaseSettingsActionType {
    private final boolean mIsInternal;
    @NonNull
    private final Runnable mOnClick;

    public SettingsNavigationActionType(boolean z, @NonNull Runnable runnable) {
        this.mIsInternal = z;
        this.mOnClick = runnable;
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.BaseSettingsActionType
    public void buildView(SettingsListItem settingsListItem, OCEventHandler oCEventHandler, SettingsLogger settingsLogger) {
        SettingsNavigationActionViewBinding.inflate(LayoutInflater.from(settingsListItem.getContext()), settingsListItem.getRightViewGroup(), true).setNavigationActionType(this);
        settingsListItem.setOnClickListener(new View.OnClickListener(settingsLogger, settingsListItem) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.$$Lambda$SettingsNavigationActionType$hW2Jpw2vIXhbgnV3Tp9i2u9kVE */
            private final /* synthetic */ SettingsLogger f$1;
            private final /* synthetic */ SettingsListItem f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void onClick(View view) {
                SettingsNavigationActionType.this.lambda$buildView$260$SettingsNavigationActionType(this.f$1, this.f$2, view);
            }
        });
    }

    public /* synthetic */ void lambda$buildView$260$SettingsNavigationActionType(SettingsLogger settingsLogger, SettingsListItem settingsListItem, View view) {
        settingsLogger.logNavigation(settingsListItem.getSettingName());
        onNavigate();
    }

    @VisibleForTesting
    public void onNavigate() {
        this.mOnClick.run();
    }

    public boolean isInternal() {
        return this.mIsInternal;
    }

    public static class Builder extends BaseSettingsActionType.Builder {
        private static final String WEBTASK_FORMAT = "ovrweb://webtask?uri=%s";
        private boolean mIsInternal = false;
        private Runnable mOnClick;

        public Builder isInternal(boolean z) {
            this.mIsInternal = z;
            return this;
        }

        public Builder withSystemDialog(AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, DialogDefinitionBase dialogDefinitionBase) {
            this.mOnClick = new Runnable(dialogDefinitionBase) {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.$$Lambda$SettingsNavigationActionType$Builder$sRFG0TflYde9I8Drk18DPITk */
                private final /* synthetic */ DialogDefinitionBase f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    AnytimeUIAndroidPanelAppV2.this.getDialogManager().showDialog(this.f$1);
                }
            };
            return this;
        }

        public Builder withSystemUXRoute(AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, SystemUXRoute systemUXRoute, String str) {
            this.mOnClick = new Runnable(systemUXRoute, str) {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.$$Lambda$SettingsNavigationActionType$Builder$qY0V0Eqbn4A6jXBiZW2sjDKNC8M */
                private final /* synthetic */ SystemUXRoute f$1;
                private final /* synthetic */ String f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    AnytimeUIAndroidPanelAppV2.this.actionNavigate((AnytimeUIAndroidPanelAppV2) this.f$1, (SystemUXRoute) this.f$2);
                }
            };
            return this;
        }

        public Builder withStringRoute(AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, String str, String str2) {
            this.mOnClick = new Runnable(str, str2) {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.$$Lambda$SettingsNavigationActionType$Builder$c8LqYfl3Ui6HuMZxakR6jUGwXCI */
                private final /* synthetic */ String f$1;
                private final /* synthetic */ String f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    AnytimeUIAndroidPanelAppV2.this.actionNavigate((AnytimeUIAndroidPanelAppV2) this.f$1, this.f$2);
                }
            };
            return this;
        }

        public Builder withLink(AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, String str) {
            this.mOnClick = new Runnable(str) {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.$$Lambda$SettingsNavigationActionType$Builder$keJLIts_QUBEfmfVQmAbQwjHa4 */
                private final /* synthetic */ String f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    AnytimeUIAndroidPanelAppV2.this.actionNavigate((AnytimeUIAndroidPanelAppV2) SystemUXRoute.DEFAULT_BROWSER, String.format(SettingsNavigationActionType.Builder.WEBTASK_FORMAT, this.f$1));
                }
            };
            return this;
        }

        @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.BaseSettingsActionType.Builder
        public SettingsNavigationActionType build() {
            return new SettingsNavigationActionType(this.mIsInternal, this.mOnClick);
        }
    }
}
