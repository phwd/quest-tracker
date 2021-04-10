package com.oculus.panelapp.anytimeui.v2.tablet.settings;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.SeekBar;
import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;
import androidx.annotation.VisibleForTesting;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import com.oculus.ocui.OCEventHandler;
import com.oculus.panelapp.anytimeui.databinding.SettingsSliderActionViewBinding;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.BaseSettingsActionType;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.util.SettingsLogger;

public class SettingsSliderActionType extends BaseSettingsActionType {
    private final Drawable mMaxIcon;
    private final String mMaxLabel;
    private final int mMaxValue;
    private final Drawable mMinIcon;
    private final String mMinLabel;
    private final int mMinValue;
    private final OnValueChangeHandler mOnDragHandler;
    private final OnValueChangeHandler mOnReleaseHandler;
    private int mValue;

    public interface OnValueChangeHandler {
        void onValueChange(int i);
    }

    private SettingsSliderActionType(int i, int i2, int i3, String str, String str2, Drawable drawable, Drawable drawable2, OnValueChangeHandler onValueChangeHandler, OnValueChangeHandler onValueChangeHandler2) {
        this.mMaxValue = i;
        this.mMinValue = i2;
        this.mValue = i3;
        this.mMaxLabel = str;
        this.mMinLabel = str2;
        this.mMaxIcon = drawable;
        this.mMinIcon = drawable2;
        this.mOnDragHandler = onValueChangeHandler;
        this.mOnReleaseHandler = onValueChangeHandler2;
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.BaseSettingsActionType
    public void buildView(final SettingsListItem settingsListItem, OCEventHandler oCEventHandler, final SettingsLogger settingsLogger) {
        ViewGroup bottomViewGroup = settingsListItem.getBottomViewGroup();
        bottomViewGroup.setVisibility(0);
        SettingsSliderActionViewBinding inflate = SettingsSliderActionViewBinding.inflate(LayoutInflater.from(settingsListItem.getContext()), bottomViewGroup, true);
        inflate.setSliderAction(this);
        inflate.slider.setEventHandler(oCEventHandler);
        inflate.slider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsSliderActionType.AnonymousClass1 */

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                SettingsSliderActionType.this.handleValueOnDrag(SettingsSliderActionType.this.clampProgress(i));
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                settingsLogger.logSliderStartDrag(settingsListItem.getSettingName(), SettingsSliderActionType.this.mValue);
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                int clampProgress = SettingsSliderActionType.this.clampProgress(seekBar.getProgress());
                settingsLogger.logSliderStopDrag(settingsListItem.getSettingName(), clampProgress);
                SettingsSliderActionType.this.handleValueOnRelease(clampProgress);
            }
        });
    }

    public void handleValueOnDrag(int i) {
        OnValueChangeHandler onValueChangeHandler = this.mOnDragHandler;
        if (onValueChangeHandler != null && i != this.mValue) {
            onValueChangeHandler.onValueChange(i);
            this.mValue = i;
        }
    }

    public void handleValueOnRelease(int i) {
        OnValueChangeHandler onValueChangeHandler = this.mOnReleaseHandler;
        if (onValueChangeHandler != null && i != this.mValue) {
            onValueChangeHandler.onValueChange(i);
            this.mValue = i;
        }
    }

    public int getMaxProgress() {
        return this.mMaxValue - this.mMinValue;
    }

    @Bindable
    public int getProgress() {
        return this.mValue - this.mMinValue;
    }

    public void setValue(int i) {
        this.mValue = i;
        notifyPropertyChanged(BR.value);
    }

    @VisibleForTesting
    public int clampProgress(int i) {
        int i2 = this.mMinValue;
        return Math.max(i2, Math.min(this.mMaxValue, i + i2));
    }

    public String getMaxLabel() {
        return this.mMaxLabel;
    }

    public String getMinLabel() {
        return this.mMinLabel;
    }

    public Drawable getMaxIcon() {
        return this.mMaxIcon;
    }

    public Drawable getMinIcon() {
        return this.mMinIcon;
    }

    public int getMaxLabelVisibility() {
        return TextUtils.isEmpty(this.mMaxLabel) ? 8 : 0;
    }

    public int getMinLabelVisibility() {
        return TextUtils.isEmpty(this.mMinLabel) ? 8 : 0;
    }

    public int getMaxIconVisibility() {
        return this.mMaxIcon == null ? 8 : 0;
    }

    public int getMinIconVisibility() {
        return this.mMinIcon == null ? 8 : 0;
    }

    public static class Builder extends BaseSettingsActionType.Builder {
        private final Context mContext;
        private Drawable mMaxIcon;
        private String mMaxLabel;
        private int mMaxValue = 100;
        private Drawable mMinIcon;
        private String mMinLabel;
        private int mMinValue = 0;
        private OnValueChangeHandler mOnDragHandler;
        private OnValueChangeHandler mOnReleaseHandler;
        private int mValue = 0;

        public Builder(Context context) {
            this.mContext = context;
        }

        public Builder withMaxValue(int i) {
            this.mMaxValue = i;
            return this;
        }

        public Builder withMinValue(int i) {
            this.mMinValue = i;
            return this;
        }

        public Builder withValue(int i) {
            this.mValue = i;
            return this;
        }

        public Builder withMaxLabel(@StringRes int i) {
            this.mMaxLabel = this.mContext.getResources().getString(i);
            return this;
        }

        public Builder withMinLabel(@StringRes int i) {
            this.mMinLabel = this.mContext.getResources().getString(i);
            return this;
        }

        public Builder withMaxIcon(@DrawableRes int i) {
            this.mMaxIcon = this.mContext.getResources().getDrawable(i, null);
            return this;
        }

        public Builder withMinIcon(@DrawableRes int i) {
            this.mMinIcon = this.mContext.getResources().getDrawable(i, null);
            return this;
        }

        public Builder withOnDrag(OnValueChangeHandler onValueChangeHandler) {
            this.mOnDragHandler = onValueChangeHandler;
            return this;
        }

        public Builder withOnRelease(OnValueChangeHandler onValueChangeHandler) {
            this.mOnReleaseHandler = onValueChangeHandler;
            return this;
        }

        @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.BaseSettingsActionType.Builder
        public SettingsSliderActionType build() {
            return new SettingsSliderActionType(this.mMaxValue, this.mMinValue, this.mValue, this.mMaxLabel, this.mMinLabel, this.mMaxIcon, this.mMinIcon, this.mOnDragHandler, this.mOnReleaseHandler);
        }
    }
}
