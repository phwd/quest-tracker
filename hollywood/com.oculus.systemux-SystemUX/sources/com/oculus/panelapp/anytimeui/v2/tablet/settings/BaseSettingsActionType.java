package com.oculus.panelapp.anytimeui.v2.tablet.settings;

import androidx.databinding.BaseObservable;
import com.oculus.ocui.OCEventHandler;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.util.SettingsLogger;

public abstract class BaseSettingsActionType extends BaseObservable {

    public static abstract class Builder {
        public abstract BaseSettingsActionType build();
    }

    public abstract void buildView(SettingsListItem settingsListItem, OCEventHandler oCEventHandler, SettingsLogger settingsLogger);
}
