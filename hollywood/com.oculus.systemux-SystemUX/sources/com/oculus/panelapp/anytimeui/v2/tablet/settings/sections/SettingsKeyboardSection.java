package com.oculus.panelapp.anytimeui.v2.tablet.settings.sections;

import android.content.Context;
import com.oculus.common.gatekeepers.Gatekeeper;
import com.oculus.os.SettingsManager;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsItem;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType;
import com.oculus.tablet.navigation.TabletDeepLinkingUriUtil;
import java.util.ArrayList;

public class SettingsKeyboardSection extends SettingsSection {
    private final Context mContext;

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsSection
    public void destroy() {
    }

    public SettingsKeyboardSection(Context context, AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2) {
        super(context.getResources().getString(R.string.settings_keyboard_section_item_title), TabletDeepLinkingUriUtil.SETTINGS_DEVICE_URI);
        this.mContext = context;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingsItem.Builder(this.mContext, anytimeUIAndroidPanelAppV2).withSettingName(SettingsManager.KEYBOARD_FEDERATED_LEARNING_ENABLED).withTitle(R.string.settings_keyboard_federated_learning_title).withSubtitle(R.string.settings_keyboard_federated_learning_subtitle).withGatekeepers(Gatekeeper.KEYBOARD_FEDERATED_LEARNING).withSettingsActionType(new SettingsToggleActionType.Builder().withSettingsManagerKey(SettingsManager.KEYBOARD_FEDERATED_LEARNING_ENABLED)));
        addSettingsItems(arrayList);
    }
}
