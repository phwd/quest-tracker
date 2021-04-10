package com.oculus.panelapp.anytimeui.v2.tablet.settings;

import android.view.LayoutInflater;
import android.view.View;
import androidx.annotation.Nullable;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCEventHandler;
import com.oculus.panelapp.anytimeui.databinding.SettingsComplexButtonActionViewBinding;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.BaseSettingsActionType;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.util.SettingsLogger;

public class SettingsComplexButtonActionType<T> extends BaseSettingsActionType {
    @Nullable
    private SettingsComplexButtonActionViewBinding mBinding;
    @Nullable
    private final GetTitleHandler<T> mGetTitle;
    @Nullable
    private final GetEnabledHandler<T> mIsEnabled;
    private boolean mLoadingDisabled;
    @Nullable
    private final OnButtonClickHandler<T> mOnClickHandler;
    @Nullable
    private T mState;

    public interface GetEnabledHandler<T> {
        boolean get(@Nullable T t);
    }

    public interface GetTitleHandler<T> {
        String get(@Nullable T t);
    }

    public interface OnButtonClickHandler<T> {
        void onClick(@Nullable T t, UpdateStateHandler<T> updateStateHandler);
    }

    public interface OnInitializeState<T> {
        void run(SettingsComplexButtonActionType<T> settingsComplexButtonActionType);
    }

    public interface UpdateStateHandler<T> {
        void updateState(T t);
    }

    private SettingsComplexButtonActionType(@Nullable OnButtonClickHandler<T> onButtonClickHandler, @Nullable GetTitleHandler<T> getTitleHandler, @Nullable GetEnabledHandler<T> getEnabledHandler, OnInitializeState<T> onInitializeState, boolean z) {
        this.mLoadingDisabled = false;
        this.mGetTitle = getTitleHandler;
        this.mIsEnabled = getEnabledHandler;
        this.mOnClickHandler = onButtonClickHandler;
        this.mLoadingDisabled = z;
        onInitializeState.run(this);
    }

    public void updateState(T t) {
        this.mState = t;
        if (this.mBinding != null) {
            updateView();
        }
    }

    private void updateView() {
        this.mBinding.button.setLoading(!this.mLoadingDisabled && this.mState == null);
        this.mBinding.button.setText(getTitle());
        this.mBinding.button.setEnabled(getEnabled());
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.BaseSettingsActionType
    public void buildView(SettingsListItem settingsListItem, OCEventHandler oCEventHandler, SettingsLogger settingsLogger) {
        boolean z = true;
        SettingsComplexButtonActionViewBinding inflate = SettingsComplexButtonActionViewBinding.inflate(LayoutInflater.from(settingsListItem.getContext()), settingsListItem.getRightViewGroup(), true);
        inflate.setComplexButtonActionType(this);
        String settingName = settingsListItem.getSettingName();
        inflate.button.setOnClickListener(new View.OnClickListener(settingsLogger, settingName) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.$$Lambda$SettingsComplexButtonActionType$4ToDJSe2M9eQt3hwTHRDSm5XZeM */
            private final /* synthetic */ SettingsLogger f$1;
            private final /* synthetic */ String f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void onClick(View view) {
                SettingsComplexButtonActionType.this.lambda$buildView$254$SettingsComplexButtonActionType(this.f$1, this.f$2, view);
            }
        });
        OCButton oCButton = inflate.button;
        if (this.mLoadingDisabled || this.mState != null) {
            z = false;
        }
        oCButton.setLoading(z);
        inflate.button.setEventHandler(oCEventHandler);
        inflate.button.setTag(settingName);
        this.mBinding = inflate;
    }

    public /* synthetic */ void lambda$buildView$254$SettingsComplexButtonActionType(SettingsLogger settingsLogger, String str, View view) {
        if (this.mOnClickHandler != null) {
            settingsLogger.logButtonClick(str);
            this.mOnClickHandler.onClick(this.mState, new UpdateStateHandler() {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.$$Lambda$SulVu7FSsITvHkWwuI6QgoU1Lvg */

                @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsComplexButtonActionType.UpdateStateHandler
                public final void updateState(Object obj) {
                    SettingsComplexButtonActionType.this.updateState(obj);
                }
            });
        }
    }

    public String getTitle() {
        GetTitleHandler<T> getTitleHandler = this.mGetTitle;
        return getTitleHandler != null ? getTitleHandler.get(this.mState) : "";
    }

    public boolean getEnabled() {
        GetEnabledHandler<T> getEnabledHandler = this.mIsEnabled;
        if (getEnabledHandler != null) {
            return getEnabledHandler.get(this.mState);
        }
        return true;
    }

    public static class Builder<T> extends BaseSettingsActionType.Builder {
        private GetTitleHandler<T> mGetTitle;
        private OnInitializeState<T> mInitializeState;
        private GetEnabledHandler<T> mIsEnabled;
        private boolean mLoadingDisabled;
        private OnButtonClickHandler<T> mOnClickHandler;

        public Builder<T> withTitle(GetTitleHandler<T> getTitleHandler) {
            this.mGetTitle = getTitleHandler;
            return this;
        }

        public Builder<T> withEnabled(GetEnabledHandler<T> getEnabledHandler) {
            this.mIsEnabled = getEnabledHandler;
            return this;
        }

        public Builder<T> withLoadingDisabled(boolean z) {
            this.mLoadingDisabled = z;
            return this;
        }

        public Builder<T> withOnClickHandler(OnButtonClickHandler<T> onButtonClickHandler) {
            this.mOnClickHandler = onButtonClickHandler;
            return this;
        }

        public Builder<T> withInitializeState(OnInitializeState<T> onInitializeState) {
            this.mInitializeState = onInitializeState;
            return this;
        }

        @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.BaseSettingsActionType.Builder
        public SettingsComplexButtonActionType<T> build() {
            return new SettingsComplexButtonActionType<>(this.mOnClickHandler, this.mGetTitle, this.mIsEnabled, this.mInitializeState, this.mLoadingDisabled);
        }
    }
}
