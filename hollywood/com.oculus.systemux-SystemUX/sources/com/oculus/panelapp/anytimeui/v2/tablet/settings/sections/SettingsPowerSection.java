package com.oculus.panelapp.anytimeui.v2.tablet.settings.sections;

import android.content.Context;
import com.oculus.assistant.assistantutils.audio.AudioDataUtil;
import com.oculus.os.SettingsManager;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.AndroidSettingsViewModel;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsDropdownActionType;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsItem;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType;
import com.oculus.tablet.navigation.TabletDeepLinkingUriUtil;
import java.util.ArrayList;
import java.util.SortedMap;
import java.util.TreeMap;

public class SettingsPowerSection extends SettingsSection {
    private static final int DEFAULT_AUTOSLEEP_TIME_SECONDS = 15;
    private static final SortedMap<Integer, Integer> mAutoSleepTimes = new TreeMap<Integer, Integer>() {
        /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsPowerSection.AnonymousClass1 */

        {
            put(15, Integer.valueOf(R.string.settings_autosleep_15));
            put(60, Integer.valueOf(R.string.settings_autosleep_60));
            put(120, Integer.valueOf(R.string.settings_autosleep_120));
            put(180, Integer.valueOf(R.string.settings_autosleep_180));
            put(300, Integer.valueOf(R.string.settings_autosleep_300));
            put(900, Integer.valueOf(R.string.settings_autosleep_900));
            put(Integer.valueOf((int) AudioDataUtil.AUDIO_BUFFER_SIZE), Integer.valueOf(R.string.settings_autosleep_14400));
        }
    };
    private final Context mContext;
    private final AnytimeUIAndroidPanelAppV2 mPanelApp;
    private final SettingsManager mSettingsManager = new SettingsManager();

    public SettingsPowerSection(Context context, AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2) {
        super(context.getString(R.string.settings_power_section_item_title), TabletDeepLinkingUriUtil.SETTINGS_DEVICE_URI);
        this.mContext = context;
        this.mPanelApp = anytimeUIAndroidPanelAppV2;
        AndroidSettingsViewModel acquireAndroidSettingsViewModel = this.mPanelApp.acquireAndroidSettingsViewModel();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingsItem.Builder(this.mContext, anytimeUIAndroidPanelAppV2).withSettingName("auto_wake").withTitle(R.string.settings_autowake_item_title).withSubtitle(R.string.settings_autowake_item_subtitle).withShowInEnterprise(true).withSettingsActionType(new SettingsToggleActionType.Builder().withSettingsManagerKey(SettingsManager.AUTOWAKE)));
        arrayList.add(new SettingsItem.Builder(this.mContext, anytimeUIAndroidPanelAppV2).withSettingName("auto_sleep_time").withTitle(R.string.settings_autosleep_item_title).withSubtitle(R.string.settings_autosleep_item_subtitle).withShowInEnterprise(true).withSettingsActionType(new SettingsDropdownActionType.Builder().withSelectorVisibilityHandler(acquireAndroidSettingsViewModel).withItems(mAutoSleepTimes.keySet().toArray(new Integer[mAutoSleepTimes.keySet().size()])).withSelectedItem(Integer.valueOf(this.mSettingsManager.getInt(SettingsManager.AUTOSLEEP_TIME, 15))).withTitleMap(mAutoSleepTimes).withOnSelectHandler(new SettingsDropdownActionType.OnSelectHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsPowerSection$n8h185ZEqlj5grdqxcaTFIycjs */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsDropdownActionType.OnSelectHandler
            public final boolean onSelect(Object obj) {
                return SettingsPowerSection.this.lambda$new$466$SettingsPowerSection((Integer) obj);
            }
        })));
        addSettingsItems(arrayList);
    }

    public /* synthetic */ boolean lambda$new$466$SettingsPowerSection(Integer num) {
        return this.mSettingsManager.setInt(SettingsManager.AUTOSLEEP_TIME, num.intValue());
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsSection
    public void destroy() {
        this.mPanelApp.releaseAndroidSettingsViewModel();
    }
}
