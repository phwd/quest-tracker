package com.oculus.panelapp.anytimeui.v2.tablet.settings;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import com.oculus.ocui.OCEventHandler;
import com.oculus.panelapp.anytimeui.databinding.SettingsButtonActionViewBinding;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.BaseSettingsActionType;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.util.SettingsLogger;
import java.util.function.Supplier;

public class SettingsButtonActionType extends BaseSettingsActionType {
    private static final int NO_RESOURCE_ID = 0;
    @NonNull
    private final Context mContext;
    @Nullable
    private final Supplier<Boolean> mIsEnabled;
    @NonNull
    private final ButtonClickHandler mOnClickHandler;
    @NonNull
    private final Supplier<String> mTitleFetcher;
    @StringRes
    private final int mTitleID;

    public interface ButtonClickHandler {
        void onClick();
    }

    public SettingsButtonActionType(@NonNull ButtonClickHandler buttonClickHandler, @StringRes int i, @Nullable Supplier<String> supplier, @Nullable Supplier<Boolean> supplier2, @NonNull Context context) {
        this.mTitleID = i;
        this.mTitleFetcher = supplier;
        this.mIsEnabled = supplier2;
        this.mContext = context;
        this.mOnClickHandler = buttonClickHandler;
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.BaseSettingsActionType
    public void buildView(SettingsListItem settingsListItem, OCEventHandler oCEventHandler, SettingsLogger settingsLogger) {
        SettingsButtonActionViewBinding inflate = SettingsButtonActionViewBinding.inflate(LayoutInflater.from(settingsListItem.getContext()), settingsListItem.getRightViewGroup(), true);
        inflate.setButtonActionType(this);
        String settingName = settingsListItem.getSettingName();
        inflate.button.setOnClickListener(new View.OnClickListener(settingsLogger, settingName) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.$$Lambda$SettingsButtonActionType$_6UYE5eNhkxmsTQ4Dzd020dgdw */
            private final /* synthetic */ SettingsLogger f$1;
            private final /* synthetic */ String f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void onClick(View view) {
                SettingsButtonActionType.this.lambda$buildView$251$SettingsButtonActionType(this.f$1, this.f$2, view);
            }
        });
        inflate.button.setEventHandler(oCEventHandler);
        inflate.button.setTag(settingName);
    }

    public /* synthetic */ void lambda$buildView$251$SettingsButtonActionType(SettingsLogger settingsLogger, String str, View view) {
        settingsLogger.logButtonClick(str);
        onClick();
    }

    public String getTitle() {
        Supplier<String> supplier = this.mTitleFetcher;
        if (supplier != null) {
            return supplier.get();
        }
        if (this.mTitleID != 0) {
            return this.mContext.getResources().getString(this.mTitleID);
        }
        throw new Error("No title resource ID provided for button.");
    }

    public boolean getEnabled() {
        Supplier<Boolean> supplier = this.mIsEnabled;
        if (supplier != null) {
            return supplier.get().booleanValue();
        }
        return true;
    }

    public void onClick() {
        this.mOnClickHandler.onClick();
    }

    public static class Builder extends BaseSettingsActionType.Builder {
        private Context mContext;
        private Supplier<Boolean> mIsEnabled;
        private ButtonClickHandler mOnClickHandler;
        private Supplier<String> mTitleFetcher;
        private int mTitleID;

        public Builder(Context context) {
            this.mContext = context;
        }

        public Builder withTitle(int i) {
            this.mTitleID = i;
            return this;
        }

        public Builder withDynamicTitle(Supplier<String> supplier) {
            this.mTitleFetcher = supplier;
            return this;
        }

        public Builder withEnabled(Supplier<Boolean> supplier) {
            this.mIsEnabled = supplier;
            return this;
        }

        public Builder withOnClickHandler(ButtonClickHandler buttonClickHandler) {
            this.mOnClickHandler = buttonClickHandler;
            return this;
        }

        @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.BaseSettingsActionType.Builder
        public SettingsButtonActionType build() {
            return new SettingsButtonActionType(this.mOnClickHandler, this.mTitleID, this.mTitleFetcher, this.mIsEnabled, this.mContext);
        }
    }
}
