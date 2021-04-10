package com.oculus.panelapp.anytimeui.v2.tablet.settings.sections;

import android.content.Context;
import com.oculus.os.PreferencesManager;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsDescriptiveText;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsItem;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsSliderActionType;
import com.oculus.tablet.navigation.TabletDeepLinkingUriUtil;
import java.util.ArrayList;

public class SettingsBluetoothMouseSection extends SettingsSection {
    private static final int MAX_SENSITIVITY = 9;
    private static final String MOUSE_SENSITIVITY = "mouse_sensitivity";
    private static final int SLIDER_DEFAULT = 3;
    private static final int SLIDER_SCALE = 100;
    private final Context mContext;
    private final PreferencesManager mPreferencesManager = new PreferencesManager();

    private int doubleToSlider(double d) {
        return (int) (((d - 1.0d) / 9.0d) * 100.0d);
    }

    private double sliderToDouble(int i) {
        return ((((double) i) / 100.0d) * 9.0d) + 1.0d;
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsSection
    public void destroy() {
    }

    public SettingsBluetoothMouseSection(Context context, AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2) {
        super(context.getString(R.string.settings_bluetooth_mouse_section_item_title), TabletDeepLinkingUriUtil.SETTINGS_EXPERIMENTS_URI);
        this.mContext = context;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingsDescriptiveText.Builder(this.mContext, anytimeUIAndroidPanelAppV2).withBody(R.string.settings_bluetooth_mouse_section_overview));
        arrayList.add(new SettingsItem.Builder(this.mContext, anytimeUIAndroidPanelAppV2).withSettingName("mouse_sensitivity_slider_item").withTitle(R.string.settings_mouse_sensitivity_item_title).withSubtitle(R.string.settings_mouse_sensitivity_item_subtitle).withSettingsActionType(new SettingsSliderActionType.Builder(this.mContext).withValue(doubleToSlider(((Double) this.mPreferencesManager.getDouble(MOUSE_SENSITIVITY).second).doubleValue())).withMaxLabel(R.string.settings_mouse_sensitivity_slider_fast).withMinLabel(R.string.settings_mouse_sensitivity_slider_slow).withOnDrag(new SettingsSliderActionType.OnValueChangeHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsBluetoothMouseSection$P5KBzhwsL_iWYGCfD3vGcXEVFxk */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsSliderActionType.OnValueChangeHandler
            public final void onValueChange(int i) {
                SettingsBluetoothMouseSection.this.lambda$new$325$SettingsBluetoothMouseSection(i);
            }
        }).withOnRelease(new SettingsSliderActionType.OnValueChangeHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsBluetoothMouseSection$0ac9yPfU6F4fLR8Is2pO228rMM0 */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsSliderActionType.OnValueChangeHandler
            public final void onValueChange(int i) {
                SettingsBluetoothMouseSection.this.lambda$new$326$SettingsBluetoothMouseSection(i);
            }
        })));
        addSettingsItems(arrayList);
    }

    public /* synthetic */ void lambda$new$325$SettingsBluetoothMouseSection(int i) {
        this.mPreferencesManager.set(MOUSE_SENSITIVITY, sliderToDouble(i));
    }

    public /* synthetic */ void lambda$new$326$SettingsBluetoothMouseSection(int i) {
        this.mPreferencesManager.set(MOUSE_SENSITIVITY, sliderToDouble(i));
    }
}
