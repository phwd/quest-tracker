package com.oculus.panelapp.anytimeui.v2.tablet.settings.sections;

import android.content.Context;
import android.util.Pair;
import com.oculus.os.PreferencesManager;
import com.oculus.os.SettingsManager;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsDescriptiveText;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsItem;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsSliderActionType;
import com.oculus.tablet.navigation.TabletDeepLinkingUriUtil;
import java.util.ArrayList;

public class SettingsTrackedKeyboardSection extends SettingsSection {
    private static final int MAX_CONTRAST = 15;
    private static final int MAX_SENSITIVITY = 9;
    private static final int MIN_CONTRAST = 10;
    private static final int MIN_SENSITIVITY = 1;
    private static final String PASSTHROUGH_HANDS_CONTRAST_PREF_KEY = "passthrough_hands_contrast";
    private static final int SLIDER_SCALE = 100;
    private static final String TRACKPAD_SENSITIVITY = "trackpad_sensitivity";
    private final Context mContext;
    private final PreferencesManager mPreferencesManager = new PreferencesManager();
    private final SettingsManager mSettingsManager = new SettingsManager();

    private int doubleToSlider(double d, int i, int i2, int i3) {
        int i4 = (int) (((d - ((double) i)) / ((double) (i2 - i))) * ((double) i3));
        return i4 > i2 ? i2 : i4 < i ? i : i4;
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsSection
    public void destroy() {
    }

    public SettingsTrackedKeyboardSection(Context context, AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2) {
        super(context.getString(R.string.settings_tracked_keyboard_section_item_title), TabletDeepLinkingUriUtil.SETTINGS_EXPERIMENTS_URI);
        this.mContext = context;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingsDescriptiveText.Builder(this.mContext, anytimeUIAndroidPanelAppV2).withBody(R.string.settings_tracked_keyboard_section_overview));
        arrayList.add(new SettingsItem.Builder(this.mContext, anytimeUIAndroidPanelAppV2).withSettingName("trackpad_sensitivity_slider_item").withTitle(R.string.settings_trackpad_sensitivity_item_title).withSubtitle(R.string.settings_trackpad_sensitivity_item_subtitle).withSettingsActionType(new SettingsSliderActionType.Builder(this.mContext).withValue(doubleToSensitivitySlider(this.mSettingsManager.getDouble("trackpad_sensitivity", 3.0d))).withMaxLabel(R.string.settings_trackpad_sensitivity_slider_fast).withMinLabel(R.string.settings_trackpad_sensitivity_slider_slow).withOnDrag(new SettingsSliderActionType.OnValueChangeHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsTrackedKeyboardSection$MaX0Bs6eF5KEV_zXHCcWmZ6YdhM */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsSliderActionType.OnValueChangeHandler
            public final void onValueChange(int i) {
                SettingsTrackedKeyboardSection.this.lambda$new$467$SettingsTrackedKeyboardSection(i);
            }
        }).withOnRelease(new SettingsSliderActionType.OnValueChangeHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsTrackedKeyboardSection$QONT4fNaedNTymEEoUSaIs3AVtc */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsSliderActionType.OnValueChangeHandler
            public final void onValueChange(int i) {
                SettingsTrackedKeyboardSection.this.lambda$new$468$SettingsTrackedKeyboardSection(i);
            }
        })));
        Double valueOf = Double.valueOf(12.0d);
        Pair<Boolean, Double> pair = this.mPreferencesManager.getDouble(PASSTHROUGH_HANDS_CONTRAST_PREF_KEY);
        arrayList.add(new SettingsItem.Builder(this.mContext, anytimeUIAndroidPanelAppV2).withTitle(R.string.settings_passthough_hands_contrast_item_title).withSubtitle(R.string.settings_passthough_hands_contrast_item_subtitle).withSettingsActionType(new SettingsSliderActionType.Builder(this.mContext).withValue(doubleToContrastSlider((((Boolean) pair.first).booleanValue() ? (Double) pair.second : valueOf).doubleValue())).withMaxLabel(R.string.settings_passthough_hands_contrast_slider_low).withMinLabel(R.string.settings_passthough_hands_contrast_slider_high).withOnDrag(new SettingsSliderActionType.OnValueChangeHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsTrackedKeyboardSection$1T2IxNDOILAo5m7KQlTyHVEBCnc */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsSliderActionType.OnValueChangeHandler
            public final void onValueChange(int i) {
                SettingsTrackedKeyboardSection.this.lambda$new$469$SettingsTrackedKeyboardSection(i);
            }
        }).withOnRelease(new SettingsSliderActionType.OnValueChangeHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsTrackedKeyboardSection$x2_70l0yl1LGnQY4JmlwUqPK1_Y */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsSliderActionType.OnValueChangeHandler
            public final void onValueChange(int i) {
                SettingsTrackedKeyboardSection.this.lambda$new$470$SettingsTrackedKeyboardSection(i);
            }
        })));
        addSettingsItems(arrayList);
    }

    public /* synthetic */ void lambda$new$467$SettingsTrackedKeyboardSection(int i) {
        this.mSettingsManager.setDouble("trackpad_sensitivity", sensitivitySliderToDouble(i));
    }

    public /* synthetic */ void lambda$new$468$SettingsTrackedKeyboardSection(int i) {
        this.mSettingsManager.setDouble("trackpad_sensitivity", sensitivitySliderToDouble(i));
    }

    public /* synthetic */ void lambda$new$469$SettingsTrackedKeyboardSection(int i) {
        this.mPreferencesManager.set(PASSTHROUGH_HANDS_CONTRAST_PREF_KEY, contrastSliderToDouble(i));
    }

    public /* synthetic */ void lambda$new$470$SettingsTrackedKeyboardSection(int i) {
        this.mPreferencesManager.set(PASSTHROUGH_HANDS_CONTRAST_PREF_KEY, contrastSliderToDouble(i));
    }

    private int doubleToSensitivitySlider(double d) {
        return doubleToSlider(d, 1, 9, 100);
    }

    private double sensitivitySliderToDouble(int i) {
        return sliderToDouble(i, 1, 9, 100);
    }

    private int doubleToContrastSlider(double d) {
        return doubleToSlider(d, 10, 15, 100);
    }

    private double contrastSliderToDouble(int i) {
        return sliderToDouble(i, 10, 15, 100);
    }

    private double sliderToDouble(int i, int i2, int i3, int i4) {
        return (double) (((i / i4) * (i3 - i2)) + i2);
    }
}
