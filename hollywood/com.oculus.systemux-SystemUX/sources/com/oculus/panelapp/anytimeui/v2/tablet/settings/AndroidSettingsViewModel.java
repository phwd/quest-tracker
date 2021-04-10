package com.oculus.panelapp.anytimeui.v2.tablet.settings;

import android.content.Context;
import androidx.annotation.Nullable;
import com.oculus.common.gatekeepers.Gatekeeper;
import com.oculus.ocui.OCSelect;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.util.SettingsLogger;
import com.oculus.tablet.view.ViewModelLifecycle;

public class AndroidSettingsViewModel implements ViewModelLifecycle, SettingsSelectorVisibilityHandler {
    private final Context mContext;
    private String mCurrentUri = "";
    private int mDisableScrollRequestSemaphore;
    private boolean mIsScrollEnabled = true;
    @Nullable
    private OCSelect mOpenSelector;
    private final AnytimeUIAndroidPanelAppV2 mPanelApp;
    private boolean mPendingCreateNewUserConfirmationDialog = false;
    private SettingsLogger mSettingsLogger;

    public AndroidSettingsViewModel(Context context, AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2) {
        this.mContext = context;
        this.mPanelApp = anytimeUIAndroidPanelAppV2;
    }

    public boolean getIsScrollEnabled() {
        return this.mIsScrollEnabled;
    }

    private void setScrollEnabled(boolean z) {
        this.mDisableScrollRequestSemaphore += z ? -1 : 1;
        boolean z2 = false;
        if (this.mDisableScrollRequestSemaphore < 0) {
            this.mDisableScrollRequestSemaphore = 0;
        }
        if (this.mDisableScrollRequestSemaphore == 0) {
            z2 = true;
        }
        this.mIsScrollEnabled = z2;
    }

    public void resetScrollEnabled() {
        this.mDisableScrollRequestSemaphore = 0;
        this.mIsScrollEnabled = true;
    }

    public void dismissDropdown() {
        OCSelect oCSelect = this.mOpenSelector;
        if (oCSelect != null) {
            oCSelect.dismissDropdown();
            this.mOpenSelector = null;
            setScrollEnabled(true);
        }
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsSelectorVisibilityHandler
    public void setOpenSelector(@Nullable OCSelect oCSelect) {
        if (oCSelect == null) {
            setScrollEnabled(true);
            this.mOpenSelector = null;
            return;
        }
        setScrollEnabled(false);
        this.mOpenSelector = oCSelect;
    }

    @Override // com.oculus.tablet.view.ViewModelLifecycle
    public void destroy() {
        dismissDropdown();
    }

    public boolean isPendingCreateNewUserConfirmationDialog() {
        return this.mPendingCreateNewUserConfirmationDialog;
    }

    public void setPendingCreateNewUserConfirmationDialog(boolean z) {
        this.mPendingCreateNewUserConfirmationDialog = z;
    }

    public SettingsLogger getSettingsLogger() {
        if (this.mSettingsLogger == null) {
            this.mSettingsLogger = new SettingsLogger(this.mContext);
        }
        return this.mSettingsLogger;
    }

    public String getCurrentUri() {
        return this.mCurrentUri;
    }

    public void setCurrentUri(String str) {
        this.mCurrentUri = str;
    }

    public boolean isInternal() {
        return this.mPanelApp.isGKEnabled(Gatekeeper.IS_TRUSTED_USER);
    }
}
