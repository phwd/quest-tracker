package com.oculus.panelapp.anytimeui.v2.tablet.settings.sections;

import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.util.DoNotDisturbUtil;

/* renamed from: com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.-$$Lambda$ziE5c5EZy5GNewNMMb09TB20Bs4  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$ziE5c5EZy5GNewNMMb09TB20Bs4 implements SettingsToggleActionType.ToggleGetValueHandler {
    public static final /* synthetic */ $$Lambda$ziE5c5EZy5GNewNMMb09TB20Bs4 INSTANCE = new $$Lambda$ziE5c5EZy5GNewNMMb09TB20Bs4();

    private /* synthetic */ $$Lambda$ziE5c5EZy5GNewNMMb09TB20Bs4() {
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType.ToggleGetValueHandler
    public final boolean get() {
        return DoNotDisturbUtil.getDoNotDisturbEnabled();
    }
}
