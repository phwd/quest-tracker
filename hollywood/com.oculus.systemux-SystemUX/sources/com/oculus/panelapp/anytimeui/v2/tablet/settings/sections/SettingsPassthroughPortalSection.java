package com.oculus.panelapp.anytimeui.v2.tablet.settings.sections;

import android.content.Context;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.BaseSettingsItem;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsButtonActionType;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsItem;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsList;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsSliderActionType;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.modules.GuardianModule;
import com.oculus.tablet.navigation.TabletDeepLinkingUriUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SettingsPassthroughPortalSection extends SettingsSection {
    private static int PASSTHROUGH_PORTAL_SLIDER_INDEX = 1;
    private final Context mContext;
    private GuardianModule.GuardianConfigUpdateListener mPassthroughGuardianConfigListener = new GuardianModule.GuardianConfigUpdateListener() {
        /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsPassthroughPortalSection$ULrryvuhDO7JS8_rM5gy5NHzoU */

        @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.modules.GuardianModule.GuardianConfigUpdateListener
        public final void onUpdate(float f) {
            SettingsPassthroughPortalSection.this.lambda$new$456$SettingsPassthroughPortalSection(f);
        }
    };

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsSection
    public void destroy() {
    }

    public SettingsPassthroughPortalSection(Context context, AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2) {
        super(context.getString(R.string.settings_passthrough_keyboard_section_item_title), TabletDeepLinkingUriUtil.SETTINGS_EXPERIMENTS_URI);
        this.mContext = context;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingsItem.Builder(this.mContext, anytimeUIAndroidPanelAppV2).withSettingName("portal_create").withTitle(R.string.settings_passthrough_keyboard_item_title).withSubtitle(R.string.settings_passthrough_keyboard_item_subtitle).withSettingsActionType(new SettingsButtonActionType.Builder(this.mContext).withTitle(R.string.settings_passthrough_keyboard_button_title).withOnClickHandler(new SettingsButtonActionType.ButtonClickHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsPassthroughPortalSection$8UfHEBH9UgkMWOuQ4QmbZMIyc */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsButtonActionType.ButtonClickHandler
            public final void onClick() {
                SettingsPassthroughPortalSection.lambda$new$453(AnytimeUIAndroidPanelAppV2.this);
            }
        })));
        anytimeUIAndroidPanelAppV2.getCommandChannel().sendCommand(String.format(Locale.US, "guardianController getGuardianConfigValue %d", 18));
        arrayList.add(new SettingsItem.Builder(this.mContext, anytimeUIAndroidPanelAppV2).withSettingName("passthrough_contrast_slider").withTitle(R.string.settings_passthrough_contrast_item_title).withSettingsActionType(new SettingsSliderActionType.Builder(this.mContext).withValue(0).withMaxLabel(R.string.settings_haptics_item_slider_max_title).withMinLabel(R.string.settings_haptics_item_slider_min_title).withOnDrag(new SettingsSliderActionType.OnValueChangeHandler(anytimeUIAndroidPanelAppV2) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsPassthroughPortalSection$mI6V3pbFKC5PHFie9MYNSV2R1g */
            private final /* synthetic */ AnytimeUIAndroidPanelAppV2 f$1;

            {
                this.f$1 = r2;
            }

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsSliderActionType.OnValueChangeHandler
            public final void onValueChange(int i) {
                SettingsPassthroughPortalSection.this.lambda$new$454$SettingsPassthroughPortalSection(this.f$1, i);
            }
        }).withOnRelease(new SettingsSliderActionType.OnValueChangeHandler(anytimeUIAndroidPanelAppV2) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsPassthroughPortalSection$c2jArwv4CP1mzywMcmDQ4C7lzu8 */
            private final /* synthetic */ AnytimeUIAndroidPanelAppV2 f$1;

            {
                this.f$1 = r2;
            }

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsSliderActionType.OnValueChangeHandler
            public final void onValueChange(int i) {
                SettingsPassthroughPortalSection.this.lambda$new$455$SettingsPassthroughPortalSection(this.f$1, i);
            }
        })));
        anytimeUIAndroidPanelAppV2.getGuardianModule().addGuardianUpdateListener(18, this.mPassthroughGuardianConfigListener);
        addSettingsItems(arrayList);
    }

    public /* synthetic */ void lambda$new$454$SettingsPassthroughPortalSection(AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, int i) {
        setPassthroughConfigValue(anytimeUIAndroidPanelAppV2, ((float) i) / 100.0f);
    }

    public /* synthetic */ void lambda$new$455$SettingsPassthroughPortalSection(AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, int i) {
        setPassthroughConfigValue(anytimeUIAndroidPanelAppV2, ((float) i) / 100.0f);
    }

    /* access modifiers changed from: private */
    /* renamed from: updatePassthroughContrastSlider */
    public void lambda$new$456$SettingsPassthroughPortalSection(float f) {
        SettingsList settingsList = getSettingsList();
        if (settingsList != null) {
            List<BaseSettingsItem> settingsItems = settingsList.getSettingsItems();
            if (PASSTHROUGH_PORTAL_SLIDER_INDEX < settingsItems.size()) {
                SettingsItem settingsItem = (SettingsItem) settingsItems.get(PASSTHROUGH_PORTAL_SLIDER_INDEX);
                SettingsSliderActionType settingsSliderActionType = (SettingsSliderActionType) ((SettingsItem) settingsItems.get(PASSTHROUGH_PORTAL_SLIDER_INDEX)).getActionType();
                if (settingsSliderActionType != null) {
                    settingsSliderActionType.setValue((int) (f * 100.0f));
                }
            }
        }
    }

    private void setPassthroughConfigValue(AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, float f) {
        anytimeUIAndroidPanelAppV2.getCommandChannel().sendCommand(String.format(Locale.US, "guardianController setGuardianConfigValue %d %f", 18, Float.valueOf(f)));
    }
}
