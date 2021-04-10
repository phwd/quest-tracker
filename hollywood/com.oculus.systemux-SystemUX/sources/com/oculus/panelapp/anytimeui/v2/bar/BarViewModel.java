package com.oculus.panelapp.anytimeui.v2.bar;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import com.oculus.common.deviceconfig.DeviceConfigHelper;
import com.oculus.common.gatekeepers.Gatekeeper;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.anytimeui.BR;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.panelapp.anytimeui.v2.abtest.StoreOptionalityExperiment;
import com.oculus.tablet.view.ViewModelLifecycle;
import com.oculus.vrshell.sharedprefs.PrefKey;
import com.oculus.vrshell.sharedprefs.SharedKeys;
import com.oculus.vrshell.sharedprefs.SharedPreferencesHelper;
import java.util.HashSet;
import java.util.Set;

public class BarViewModel extends BaseObservable implements ViewModelLifecycle {
    private static final PrefKey EMPLOYEE_DEBUG_BAR_PINNED = SharedKeys.SETTINGS.extend("employee_debug_bar_pinned");
    private static final String TAG = LoggingUtil.tag(BarViewModel.class);
    private Context mContext;
    private Set<DebugBarPinnedChangeListener> mDebugBarPinnedChangeListeners = new HashSet();
    private boolean mHighlightLibrary = false;
    private boolean mOnlinePresenceDotVisible;
    private AnytimeUIAndroidPanelAppV2 mPanelApp;
    private StoreOptionalityExperiment mStoreOptionalityExperiment;

    public interface DebugBarPinnedChangeListener {
        void onDebugBarPinnedChanged(boolean z);
    }

    public BarViewModel(Context context, AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2) {
        Log.d(TAG, "Constructing BarViewModel");
        this.mContext = context;
        this.mPanelApp = anytimeUIAndroidPanelAppV2;
        this.mStoreOptionalityExperiment = anytimeUIAndroidPanelAppV2.getStoreOptionalityExperiment();
    }

    @Override // com.oculus.tablet.view.ViewModelLifecycle
    public void destroy() {
        Log.d(TAG, "Destroying BarViewModel");
    }

    public boolean libraryNavigationButtonVisible() {
        return !this.mPanelApp.getSystemUXConfig().isEnterpriseMode || this.mPanelApp.getSystemUXConfig().isEnterpriseHandTrackingEnabled || !this.mPanelApp.getSystemUXConfig().isDefaultApplicationSet;
    }

    public boolean socialNavigationButtonVisible() {
        return !this.mPanelApp.getSystemUXConfig().isEnterpriseMode && !DeviceConfigHelper.getBoolean(this.mContext, Gatekeeper.AUI_V2_MESSENGER);
    }

    public boolean messengerNavigationButtonVisible() {
        return !this.mPanelApp.getSystemUXConfig().isEnterpriseMode && DeviceConfigHelper.getBoolean(this.mContext, Gatekeeper.AUI_V2_MESSENGER);
    }

    public Drawable messengerNavigationButtonIcon() {
        if (shouldShowMessengerIconAndLabel()) {
            return this.mContext.getDrawable(R.drawable.oc_icon_messenger_filled_24_e0e0e0);
        }
        return this.mContext.getDrawable(R.drawable.oc_icon_social_filled_24_e0e0e0);
    }

    public String messengerNavigationButtonLabel() {
        if (shouldShowMessengerIconAndLabel()) {
            return this.mContext.getString(R.string.anytime_detail_button_chats);
        }
        return this.mContext.getString(R.string.anytime_tablet_nav_social_v2);
    }

    /* access modifiers changed from: package-private */
    public boolean shouldShowMessengerIconAndLabel() {
        return this.mPanelApp.isGKEnabled(Gatekeeper.AUI_V2_MESSENGER_ICON);
    }

    public boolean notificationsNavigationButtonVisible() {
        return !this.mPanelApp.getSystemUXConfig().isEnterpriseMode;
    }

    public boolean storeRightNavigationButtonVisible() {
        return !this.mPanelApp.getSystemUXConfig().isEnterpriseMode && this.mStoreOptionalityExperiment.isStorePinnedToBarPositionRightEnabled();
    }

    public boolean storeLeftNavigationButtonVisible() {
        return !this.mPanelApp.getSystemUXConfig().isEnterpriseMode && this.mStoreOptionalityExperiment.isStorePinnedToBarPositionLeftEnabled();
    }

    public boolean isBarButtonsWithLabelsEnabled() {
        return this.mStoreOptionalityExperiment.isBarButtonsWithLabelsEnabled();
    }

    public boolean getDebugBarPinned() {
        return SharedPreferencesHelper.getBoolean(this.mContext, EMPLOYEE_DEBUG_BAR_PINNED, false);
    }

    public void setDebugBarPinned(boolean z) {
        SharedPreferencesHelper.putBoolean(this.mContext, EMPLOYEE_DEBUG_BAR_PINNED, z);
        for (DebugBarPinnedChangeListener debugBarPinnedChangeListener : this.mDebugBarPinnedChangeListeners) {
            debugBarPinnedChangeListener.onDebugBarPinnedChanged(z);
        }
    }

    public void addDebugBarPinnedChangeListener(DebugBarPinnedChangeListener debugBarPinnedChangeListener) {
        this.mDebugBarPinnedChangeListeners.add(debugBarPinnedChangeListener);
    }

    public void removeDebugBarPinnedChangeListener(DebugBarPinnedChangeListener debugBarPinnedChangeListener) {
        this.mDebugBarPinnedChangeListeners.remove(debugBarPinnedChangeListener);
    }

    @Bindable
    public boolean getHighlightLibrary() {
        return this.mHighlightLibrary;
    }

    public void setHighlightLibrary(boolean z) {
        this.mHighlightLibrary = z;
        notifyPropertyChanged(BR.highlightLibrary);
    }

    @Bindable
    public boolean getOnlinePresenceDotVisible() {
        return this.mOnlinePresenceDotVisible;
    }

    public void setOnlinePresenceDotVisible(boolean z) {
        this.mOnlinePresenceDotVisible = z;
        notifyPropertyChanged(BR.onlinePresenceDotVisible);
    }
}
