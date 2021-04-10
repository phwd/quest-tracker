package com.oculus.panelapp.anytimeui.v2.tablet.settings;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import com.oculus.ocui.OCEventHandler;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.databinding.SettingsVoiceActivityLogActionViewBinding;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.BaseSettingsActionType;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.util.SettingsLogger;

public final class SettingsVoiceActivityLogActionType extends BaseSettingsActionType {
    private SettingsVoiceActivityLogActionViewBinding mBinding;
    private final Context mContext;
    private final OnButtonClickHandler mDeleteButtonOnClick;
    private boolean mIsPlaying;
    private final String mLogID;
    private final OnButtonClickHandler mPlayButtonOnClick;

    public interface OnButtonClickHandler {
        void onClick(SettingsVoiceActivityLogActionType settingsVoiceActivityLogActionType);
    }

    private SettingsVoiceActivityLogActionType(Context context, OnButtonClickHandler onButtonClickHandler, OnButtonClickHandler onButtonClickHandler2, String str) {
        this.mIsPlaying = false;
        this.mContext = context;
        this.mPlayButtonOnClick = onButtonClickHandler;
        this.mDeleteButtonOnClick = onButtonClickHandler2;
        this.mLogID = str;
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.BaseSettingsActionType
    public void buildView(SettingsListItem settingsListItem, OCEventHandler oCEventHandler, SettingsLogger settingsLogger) {
        this.mBinding = SettingsVoiceActivityLogActionViewBinding.inflate(LayoutInflater.from(settingsListItem.getContext()), settingsListItem.getRightViewGroup(), true);
        this.mBinding.primaryButton.setEventHandler(oCEventHandler);
        this.mBinding.primaryButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.$$Lambda$SettingsVoiceActivityLogActionType$VZV_Gk_XvgF7Wj4A49Pj5eaw1U */

            public final void onClick(View view) {
                SettingsVoiceActivityLogActionType.this.lambda$buildView$284$SettingsVoiceActivityLogActionType(view);
            }
        });
        this.mBinding.secondaryButton.setEventHandler(oCEventHandler);
        this.mBinding.secondaryButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.$$Lambda$SettingsVoiceActivityLogActionType$7IKYXcxTAUOhXUKh7Dv9WXNc8YY */

            public final void onClick(View view) {
                SettingsVoiceActivityLogActionType.this.lambda$buildView$285$SettingsVoiceActivityLogActionType(view);
            }
        });
        setPlaying(this.mIsPlaying);
    }

    public /* synthetic */ void lambda$buildView$284$SettingsVoiceActivityLogActionType(View view) {
        this.mPlayButtonOnClick.onClick(this);
    }

    public /* synthetic */ void lambda$buildView$285$SettingsVoiceActivityLogActionType(View view) {
        this.mDeleteButtonOnClick.onClick(this);
    }

    public void setPlaying(boolean z) {
        this.mIsPlaying = z;
        SettingsVoiceActivityLogActionViewBinding settingsVoiceActivityLogActionViewBinding = this.mBinding;
        if (settingsVoiceActivityLogActionViewBinding != null) {
            settingsVoiceActivityLogActionViewBinding.primaryButton.setBackground(this.mContext.getResources().getDrawable(z ? R.drawable.settings_voice_activity_log_pause_button : R.drawable.settings_voice_activity_log_play_button, null));
        }
    }

    public boolean isPlaying() {
        return this.mIsPlaying;
    }

    public String getLogID() {
        return this.mLogID;
    }

    public static final class Builder extends BaseSettingsActionType.Builder {
        private final Context mContext;
        private OnButtonClickHandler mDeleteButtonOnClick;
        private String mLogID;
        private OnButtonClickHandler mPlayButtonOnClick;

        public Builder(Context context) {
            this.mContext = context;
        }

        public Builder withPlayButtonOnClick(OnButtonClickHandler onButtonClickHandler) {
            this.mPlayButtonOnClick = onButtonClickHandler;
            return this;
        }

        public Builder withDeleteButtonOnClick(OnButtonClickHandler onButtonClickHandler) {
            this.mDeleteButtonOnClick = onButtonClickHandler;
            return this;
        }

        public Builder withLogId(String str) {
            this.mLogID = str;
            return this;
        }

        @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.BaseSettingsActionType.Builder
        public SettingsVoiceActivityLogActionType build() {
            return new SettingsVoiceActivityLogActionType(this.mContext, this.mPlayButtonOnClick, this.mDeleteButtonOnClick, this.mLogID);
        }
    }
}
