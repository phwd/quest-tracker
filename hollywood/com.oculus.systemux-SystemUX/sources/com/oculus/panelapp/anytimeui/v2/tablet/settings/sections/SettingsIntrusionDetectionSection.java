package com.oculus.panelapp.anytimeui.v2.tablet.settings.sections;

import android.content.Context;
import android.util.Pair;
import com.oculus.os.PreferencesManager;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsItem;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsSliderActionType;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType;
import com.oculus.tablet.navigation.TabletDeepLinkingUriUtil;
import java.util.ArrayList;
import java.util.function.Supplier;

public class SettingsIntrusionDetectionSection extends SettingsSection {
    private static final String ALERT_SENSITIVITY_KEY = "intrusion_detection_alert_sensitivity";
    private static final String AREA_SENSITIVITY_KEY = "intrusion_detection_area_sensitivity";
    private static final String BOUNDARY_SENSITIVITY_KEY = "intrusion_detection_boundary_sensitivity";
    private static final String FLOOR_HEIGHT_KEY = "intrusion_detection_floor_height";
    private static final String INTRUSION_DETECTION_ENABLED = "intrusion_detection_enabled";
    private static final int MAX_HEIGHT = 15;
    private static final int MAX_SENSITIVITY = 9;
    private static final int MIN_HEIGHT = 10;
    private static final int MIN_SENSITIVITY = 1;
    private static final int SLIDER_SCALE = 100;
    private final Context mContext;
    private final PreferencesManager mPreferencesManager = new PreferencesManager();
    private final Runnable mRefreshView;

    private int doubleToSlider(double d) {
        return (int) (((d - 1.0d) / 9.0d) * 100.0d);
    }

    private double sliderToDouble(int i) {
        return ((((double) i) / 100.0d) * 9.0d) + 1.0d;
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsSection
    public void destroy() {
    }

    public SettingsIntrusionDetectionSection(Context context, AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, Runnable runnable) {
        super(context.getString(R.string.settings_intrusion_detection_section_item_title), TabletDeepLinkingUriUtil.SETTINGS_EXPERIMENTS_URI);
        this.mContext = context;
        this.mRefreshView = runnable;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingsItem.Builder(this.mContext, anytimeUIAndroidPanelAppV2).withSettingName("guardian_enable_intrusion_detection").withTitle(R.string.settings_intrusion_detection_section_item_title).withSubtitle(R.string.settings_intrusion_detection_section_item_subtitle).withIcon(R.drawable.oc_icon_guardian_intrusion_detection_filled_24_d2d2d2).withSettingsActionType(new SettingsToggleActionType.Builder().withGetValue(new SettingsToggleActionType.ToggleGetValueHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsIntrusionDetectionSection$8Mdok_LH6G1o1aEaPdtWtLAno */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType.ToggleGetValueHandler
            public final boolean get() {
                return SettingsIntrusionDetectionSection.this.lambda$new$421$SettingsIntrusionDetectionSection();
            }
        }).withSetValue(new SettingsToggleActionType.ToggleSetValueHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsIntrusionDetectionSection$fc_tkaY_6cs9UlQGN1uoYFaHuQ */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType.ToggleSetValueHandler
            public final void set(boolean z, SettingsToggleActionType settingsToggleActionType) {
                SettingsIntrusionDetectionSection.this.lambda$new$422$SettingsIntrusionDetectionSection(z, settingsToggleActionType);
            }
        })));
        arrayList.add(new SettingsItem.Builder(this.mContext, anytimeUIAndroidPanelAppV2).withSettingName(ALERT_SENSITIVITY_KEY).withTitle(R.string.settings_intrusion_detection_alert_sensitivity_item_title).withSubtitle(R.string.settings_intrusion_detection_alert_sensitivity_item_subtitle).withVisibilityCondition(new Supplier() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsIntrusionDetectionSection$3TJ9rtOhdR17YMLe27XSOYftN70 */

            @Override // java.util.function.Supplier
            public final Object get() {
                return SettingsIntrusionDetectionSection.this.lambda$new$423$SettingsIntrusionDetectionSection();
            }
        }).withSettingsActionType(new SettingsSliderActionType.Builder(this.mContext).withValue(doubleToSlider(((Double) this.mPreferencesManager.getDouble(ALERT_SENSITIVITY_KEY).second).doubleValue())).withMaxLabel(R.string.settings_intrusion_detection_sensitivity_slider_high).withMinLabel(R.string.settings_intrusion_detection_sensitivity_slider_low).withOnDrag(new SettingsSliderActionType.OnValueChangeHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsIntrusionDetectionSection$R2EwcNMvm5RINC06knGuzGkQ84 */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsSliderActionType.OnValueChangeHandler
            public final void onValueChange(int i) {
                SettingsIntrusionDetectionSection.this.lambda$new$424$SettingsIntrusionDetectionSection(i);
            }
        }).withOnRelease(new SettingsSliderActionType.OnValueChangeHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsIntrusionDetectionSection$jvXR6JpmHO69M7EsxFhT9MVxw */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsSliderActionType.OnValueChangeHandler
            public final void onValueChange(int i) {
                SettingsIntrusionDetectionSection.this.lambda$new$425$SettingsIntrusionDetectionSection(i);
            }
        })));
        arrayList.add(new SettingsItem.Builder(this.mContext, anytimeUIAndroidPanelAppV2).withSettingName(AREA_SENSITIVITY_KEY).withTitle(R.string.settings_intrusion_detection_area_sensitivity_item_title).withSubtitle(R.string.settings_intrusion_detection_area_sensitivity_item_subtitle).withVisibilityCondition(new Supplier() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsIntrusionDetectionSection$_luwyvM5yYJccohtBlwN4HVlEM8 */

            @Override // java.util.function.Supplier
            public final Object get() {
                return SettingsIntrusionDetectionSection.this.lambda$new$426$SettingsIntrusionDetectionSection();
            }
        }).withSettingsActionType(new SettingsSliderActionType.Builder(this.mContext).withValue(doubleToSlider(((Double) this.mPreferencesManager.getDouble(AREA_SENSITIVITY_KEY).second).doubleValue())).withMaxLabel(R.string.settings_intrusion_detection_sensitivity_slider_high).withMinLabel(R.string.settings_intrusion_detection_sensitivity_slider_low).withOnDrag(new SettingsSliderActionType.OnValueChangeHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsIntrusionDetectionSection$_7Q6Yn_8KZ5xhHLpd7ljSFUv9c */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsSliderActionType.OnValueChangeHandler
            public final void onValueChange(int i) {
                SettingsIntrusionDetectionSection.this.lambda$new$427$SettingsIntrusionDetectionSection(i);
            }
        }).withOnRelease(new SettingsSliderActionType.OnValueChangeHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsIntrusionDetectionSection$6Cjdd9sDrfLSckoMUeqOmIupJdg */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsSliderActionType.OnValueChangeHandler
            public final void onValueChange(int i) {
                SettingsIntrusionDetectionSection.this.lambda$new$428$SettingsIntrusionDetectionSection(i);
            }
        })));
        arrayList.add(new SettingsItem.Builder(this.mContext, anytimeUIAndroidPanelAppV2).withSettingName(BOUNDARY_SENSITIVITY_KEY).withTitle(R.string.settings_intrusion_detection_boundary_sensitivity_item_title).withSubtitle(R.string.settings_intrusion_detection_boundary_sensitivity_item_subtitle).withVisibilityCondition(new Supplier() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsIntrusionDetectionSection$fgjrNU2gxeR0OJEIE1ITOeOrQYw */

            @Override // java.util.function.Supplier
            public final Object get() {
                return SettingsIntrusionDetectionSection.this.lambda$new$429$SettingsIntrusionDetectionSection();
            }
        }).withSettingsActionType(new SettingsSliderActionType.Builder(this.mContext).withValue(doubleToSlider(((Double) this.mPreferencesManager.getDouble(BOUNDARY_SENSITIVITY_KEY).second).doubleValue())).withMaxLabel(R.string.settings_intrusion_detection_sensitivity_slider_high).withMinLabel(R.string.settings_intrusion_detection_sensitivity_slider_low).withOnDrag(new SettingsSliderActionType.OnValueChangeHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsIntrusionDetectionSection$zf27kkotdqgRMRx8opNIxszFXi4 */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsSliderActionType.OnValueChangeHandler
            public final void onValueChange(int i) {
                SettingsIntrusionDetectionSection.this.lambda$new$430$SettingsIntrusionDetectionSection(i);
            }
        }).withOnRelease(new SettingsSliderActionType.OnValueChangeHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsIntrusionDetectionSection$sTu0oncwUvassMKIRwrKHNokNTE */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsSliderActionType.OnValueChangeHandler
            public final void onValueChange(int i) {
                SettingsIntrusionDetectionSection.this.lambda$new$431$SettingsIntrusionDetectionSection(i);
            }
        })));
        arrayList.add(new SettingsItem.Builder(this.mContext, anytimeUIAndroidPanelAppV2).withSettingName(FLOOR_HEIGHT_KEY).withTitle(R.string.settings_intrusion_detection_floor_height_item_title).withSubtitle(R.string.settings_intrusion_detection_floor_height_item_subtitle).withVisibilityCondition(new Supplier() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsIntrusionDetectionSection$nXpACa8z96TK7DFyp7Fcwf94lPg */

            @Override // java.util.function.Supplier
            public final Object get() {
                return SettingsIntrusionDetectionSection.this.lambda$new$432$SettingsIntrusionDetectionSection();
            }
        }).withSettingsActionType(new SettingsSliderActionType.Builder(this.mContext).withValue(doubleToSlider(((Double) this.mPreferencesManager.getDouble(FLOOR_HEIGHT_KEY).second).doubleValue())).withMaxLabel(R.string.settings_intrusion_detection_sensitivity_slider_high).withMinLabel(R.string.settings_intrusion_detection_sensitivity_slider_low).withOnDrag(new SettingsSliderActionType.OnValueChangeHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsIntrusionDetectionSection$VHVM9N1gP1oXgTXbqAU7mn3uCo */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsSliderActionType.OnValueChangeHandler
            public final void onValueChange(int i) {
                SettingsIntrusionDetectionSection.this.lambda$new$433$SettingsIntrusionDetectionSection(i);
            }
        }).withOnRelease(new SettingsSliderActionType.OnValueChangeHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsIntrusionDetectionSection$CHWF8joTnMfxVpI31n1JVBve0u8 */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsSliderActionType.OnValueChangeHandler
            public final void onValueChange(int i) {
                SettingsIntrusionDetectionSection.this.lambda$new$434$SettingsIntrusionDetectionSection(i);
            }
        })));
        addSettingsItems(arrayList);
    }

    public /* synthetic */ void lambda$new$422$SettingsIntrusionDetectionSection(boolean z, SettingsToggleActionType settingsToggleActionType) {
        this.mPreferencesManager.set("intrusion_detection_enabled", z);
        this.mRefreshView.run();
    }

    public /* synthetic */ Boolean lambda$new$423$SettingsIntrusionDetectionSection() {
        return Boolean.valueOf(lambda$new$421$SettingsIntrusionDetectionSection());
    }

    public /* synthetic */ void lambda$new$424$SettingsIntrusionDetectionSection(int i) {
        this.mPreferencesManager.set(ALERT_SENSITIVITY_KEY, sliderToDouble(i));
    }

    public /* synthetic */ void lambda$new$425$SettingsIntrusionDetectionSection(int i) {
        this.mPreferencesManager.set(ALERT_SENSITIVITY_KEY, sliderToDouble(i));
    }

    public /* synthetic */ Boolean lambda$new$426$SettingsIntrusionDetectionSection() {
        return Boolean.valueOf(lambda$new$421$SettingsIntrusionDetectionSection());
    }

    public /* synthetic */ void lambda$new$427$SettingsIntrusionDetectionSection(int i) {
        this.mPreferencesManager.set(AREA_SENSITIVITY_KEY, sliderToDouble(i));
    }

    public /* synthetic */ void lambda$new$428$SettingsIntrusionDetectionSection(int i) {
        this.mPreferencesManager.set(AREA_SENSITIVITY_KEY, sliderToDouble(i));
    }

    public /* synthetic */ Boolean lambda$new$429$SettingsIntrusionDetectionSection() {
        return Boolean.valueOf(lambda$new$421$SettingsIntrusionDetectionSection());
    }

    public /* synthetic */ void lambda$new$430$SettingsIntrusionDetectionSection(int i) {
        this.mPreferencesManager.set(BOUNDARY_SENSITIVITY_KEY, sliderToDouble(i));
    }

    public /* synthetic */ void lambda$new$431$SettingsIntrusionDetectionSection(int i) {
        this.mPreferencesManager.set(BOUNDARY_SENSITIVITY_KEY, sliderToDouble(i));
    }

    public /* synthetic */ Boolean lambda$new$432$SettingsIntrusionDetectionSection() {
        return Boolean.valueOf(lambda$new$421$SettingsIntrusionDetectionSection());
    }

    public /* synthetic */ void lambda$new$433$SettingsIntrusionDetectionSection(int i) {
        this.mPreferencesManager.set(FLOOR_HEIGHT_KEY, sliderToDouble(i));
    }

    public /* synthetic */ void lambda$new$434$SettingsIntrusionDetectionSection(int i) {
        this.mPreferencesManager.set(FLOOR_HEIGHT_KEY, sliderToDouble(i));
    }

    /* access modifiers changed from: private */
    /* renamed from: isIntrusionDetectionEnabled */
    public boolean lambda$new$421$SettingsIntrusionDetectionSection() {
        Pair<Boolean, Boolean> pair = this.mPreferencesManager.getBoolean("intrusion_detection_enabled");
        return ((Boolean) pair.first).booleanValue() && ((Boolean) pair.second).booleanValue();
    }
}
