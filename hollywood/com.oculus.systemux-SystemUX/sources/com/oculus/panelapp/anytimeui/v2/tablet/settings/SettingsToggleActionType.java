package com.oculus.panelapp.anytimeui.v2.tablet.settings;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.ocui.OCEventHandler;
import com.oculus.os.SettingsManager;
import com.oculus.panelapp.anytimeui.databinding.SettingsToggleActionViewBinding;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.BaseSettingsActionType;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.util.SettingsLogger;

public class SettingsToggleActionType extends BaseSettingsActionType {
    private Boolean mAsyncSetValue;
    private final ToggleGetValueHandler mGetValue;
    private final ToggleSetValueObserver mObserver;
    private final ToggleSetValueHandler mSetValue;

    public interface ToggleGetValueHandler {
        boolean get();
    }

    public interface ToggleSetValueHandler {
        void set(boolean z, SettingsToggleActionType settingsToggleActionType);
    }

    public interface ToggleSetValueObserver {
        void onValueChanged(boolean z);
    }

    public SettingsToggleActionType(ToggleGetValueHandler toggleGetValueHandler, ToggleSetValueHandler toggleSetValueHandler, ToggleSetValueObserver toggleSetValueObserver) {
        this.mGetValue = toggleGetValueHandler;
        this.mSetValue = toggleSetValueHandler;
        this.mObserver = toggleSetValueObserver;
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.BaseSettingsActionType
    public void buildView(SettingsListItem settingsListItem, OCEventHandler oCEventHandler, SettingsLogger settingsLogger) {
        SettingsToggleActionViewBinding inflate = SettingsToggleActionViewBinding.inflate(LayoutInflater.from(settingsListItem.getContext()), settingsListItem.getRightViewGroup(), true);
        inflate.setToggleAction(this);
        inflate.toggle.setOnClickListener(new View.OnClickListener(inflate, settingsLogger, settingsListItem) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.$$Lambda$SettingsToggleActionType$6HDNpk250m8qpoojnH3IQS0SFs */
            private final /* synthetic */ SettingsToggleActionViewBinding f$1;
            private final /* synthetic */ SettingsLogger f$2;
            private final /* synthetic */ SettingsListItem f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            public final void onClick(View view) {
                SettingsToggleActionType.this.lambda$buildView$265$SettingsToggleActionType(this.f$1, this.f$2, this.f$3, view);
            }
        });
        inflate.toggle.setEventHandler(oCEventHandler);
        inflate.toggle.setTag(settingsListItem.getSettingName());
    }

    public /* synthetic */ void lambda$buildView$265$SettingsToggleActionType(SettingsToggleActionViewBinding settingsToggleActionViewBinding, SettingsLogger settingsLogger, SettingsListItem settingsListItem, View view) {
        boolean isChecked = settingsToggleActionViewBinding.toggle.isChecked();
        settingsLogger.logToggleChange(settingsListItem.getSettingName(), isChecked);
        setValue(isChecked);
    }

    @VisibleForTesting
    public void setValue(boolean z) {
        this.mSetValue.set(z, this);
        ToggleSetValueObserver toggleSetValueObserver = this.mObserver;
        if (toggleSetValueObserver != null) {
            toggleSetValueObserver.onValueChanged(z);
        }
    }

    @Bindable
    public boolean getValue() {
        Boolean bool = this.mAsyncSetValue;
        if (bool != null) {
            return bool.booleanValue();
        }
        return this.mGetValue.get();
    }

    public void asyncSetValue(boolean z) {
        this.mAsyncSetValue = Boolean.valueOf(z);
        notifyPropertyChanged(BR.value);
    }

    public void refreshToggle() {
        notifyPropertyChanged(BR.value);
    }

    public static class Builder extends BaseSettingsActionType.Builder {
        private static final String TAG = LoggingUtil.tag(SettingsToggleActionType.class);
        private ToggleGetValueHandler mGetValue;
        private ToggleSetValueHandler mSetValue;
        private SettingsManager mSettingsManager;
        private String mSettingsManagerKey;
        private ToggleSetValueObserver mValueObserver;

        public Builder withSetValue(ToggleSetValueHandler toggleSetValueHandler) {
            this.mSetValue = toggleSetValueHandler;
            return this;
        }

        public Builder withGetValue(ToggleGetValueHandler toggleGetValueHandler) {
            this.mGetValue = toggleGetValueHandler;
            return this;
        }

        public Builder withObserver(ToggleSetValueObserver toggleSetValueObserver) {
            this.mValueObserver = toggleSetValueObserver;
            return this;
        }

        public Builder withSettingsManagerKey(@NonNull String str) {
            this.mSettingsManagerKey = str;
            return this;
        }

        @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.BaseSettingsActionType.Builder
        public SettingsToggleActionType build() {
            if (this.mSettingsManagerKey != null) {
                if (!(this.mGetValue == null && this.mSetValue == null)) {
                    Log.e(TAG, "A setter/getter was provided, but overridden by a SettingsManager key");
                }
                this.mSettingsManager = new SettingsManager();
                withGetValue(new ToggleGetValueHandler() {
                    /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.$$Lambda$SettingsToggleActionType$Builder$gjLTMbB7tMWmKqUEgiZdLPIFvP4 */

                    @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType.ToggleGetValueHandler
                    public final boolean get() {
                        return SettingsToggleActionType.Builder.this.lambda$build$266$SettingsToggleActionType$Builder();
                    }
                });
                withSetValue(new ToggleSetValueHandler() {
                    /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.$$Lambda$SettingsToggleActionType$Builder$Bmi0ZFMnBRXE9Uvu9jgyp3ReMdQ */

                    @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType.ToggleSetValueHandler
                    public final void set(boolean z, SettingsToggleActionType settingsToggleActionType) {
                        SettingsToggleActionType.Builder.this.lambda$build$267$SettingsToggleActionType$Builder(z, settingsToggleActionType);
                    }
                });
            }
            return new SettingsToggleActionType(this.mGetValue, this.mSetValue, this.mValueObserver);
        }

        public /* synthetic */ boolean lambda$build$266$SettingsToggleActionType$Builder() {
            return this.mSettingsManager.getBoolean(this.mSettingsManagerKey, false);
        }

        public /* synthetic */ void lambda$build$267$SettingsToggleActionType$Builder(boolean z, SettingsToggleActionType settingsToggleActionType) {
            this.mSettingsManager.setBoolean(this.mSettingsManagerKey, z);
        }
    }
}
