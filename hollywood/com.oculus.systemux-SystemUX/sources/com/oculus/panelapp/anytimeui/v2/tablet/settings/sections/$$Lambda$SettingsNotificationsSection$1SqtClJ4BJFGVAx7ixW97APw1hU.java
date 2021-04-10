package com.oculus.panelapp.anytimeui.v2.tablet.settings.sections;

import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.util.DoNotDisturbUtil;

/* renamed from: com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.-$$Lambda$SettingsNotificationsSection$1SqtClJ4BJFGVAx7ixW97APw1hU  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$SettingsNotificationsSection$1SqtClJ4BJFGVAx7ixW97APw1hU implements SettingsToggleActionType.ToggleSetValueHandler {
    public static final /* synthetic */ $$Lambda$SettingsNotificationsSection$1SqtClJ4BJFGVAx7ixW97APw1hU INSTANCE = new $$Lambda$SettingsNotificationsSection$1SqtClJ4BJFGVAx7ixW97APw1hU();

    private /* synthetic */ $$Lambda$SettingsNotificationsSection$1SqtClJ4BJFGVAx7ixW97APw1hU() {
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType.ToggleSetValueHandler
    public final void set(boolean z, SettingsToggleActionType settingsToggleActionType) {
        DoNotDisturbUtil.setDoNotDisturbEnabled(z);
    }
}
