package com.oculus.panelapp.anytimeui.v2.tablet.settings.sections;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import com.oculus.panelapp.anytimeui.BR;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.BaseSettingsItem;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsButtonActionType;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsList;
import java.util.ArrayList;
import java.util.List;

public abstract class SettingsSection extends BaseObservable {
    @Nullable
    private Runnable mOnScrolledToBottomHandler;
    private final String mParentSectionRoute;
    protected SettingsList mSettingsList = new SettingsList();
    public String mTitle;
    @Nullable
    private SettingsButtonActionType mToolbarButton;

    public abstract void destroy();

    public void onHide() {
    }

    public void onShow() {
    }

    public SettingsSection(String str, @Nullable String str2) {
        this.mTitle = str;
        this.mParentSectionRoute = str2;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }

    public void addSettingsItems(@NonNull List<BaseSettingsItem.Builder> list) {
        for (BaseSettingsItem.Builder builder : list) {
            this.mSettingsList.addSettingsItem(builder);
        }
    }

    public void addSettingsItem(@NonNull BaseSettingsItem.Builder builder) {
        this.mSettingsList.addSettingsItem(builder);
    }

    public void clearSettingsItems() {
        this.mSettingsList.clear();
    }

    public SettingsList getSettingsList() {
        return this.mSettingsList;
    }

    @NonNull
    public List<BaseSettingsItem> getFilteredSettingsItems() {
        ArrayList arrayList = new ArrayList();
        for (BaseSettingsItem baseSettingsItem : this.mSettingsList.getSettingsItems()) {
            if (baseSettingsItem.isVisible()) {
                arrayList.add(baseSettingsItem);
            }
        }
        return arrayList;
    }

    public boolean hasParentSection() {
        return !TextUtils.isEmpty(this.mParentSectionRoute);
    }

    public String getParentSectionRoute() {
        return this.mParentSectionRoute;
    }

    public void setToolbarButton(SettingsButtonActionType.Builder builder) {
        if (builder == null) {
            this.mToolbarButton = null;
        } else {
            this.mToolbarButton = builder.build();
        }
        notifyPropertyChanged(BR.toolbarButtonTitle);
        notifyPropertyChanged(BR.hasToolbarButton);
    }

    @Bindable
    public boolean getHasToolbarButton() {
        return this.mToolbarButton != null;
    }

    @Bindable
    public String getToolbarButtonTitle() {
        SettingsButtonActionType settingsButtonActionType = this.mToolbarButton;
        return settingsButtonActionType != null ? settingsButtonActionType.getTitle() : "";
    }

    public void clickToolbarButton() {
        SettingsButtonActionType settingsButtonActionType = this.mToolbarButton;
        if (settingsButtonActionType != null) {
            settingsButtonActionType.onClick();
        }
    }

    public void setOnScrollToBottom(Runnable runnable) {
        this.mOnScrolledToBottomHandler = runnable;
    }

    public void onScrolledToBottom() {
        Runnable runnable = this.mOnScrolledToBottomHandler;
        if (runnable != null) {
            runnable.run();
        }
    }
}
