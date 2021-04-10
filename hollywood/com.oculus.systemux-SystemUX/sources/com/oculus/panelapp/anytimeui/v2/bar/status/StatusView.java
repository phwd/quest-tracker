package com.oculus.panelapp.anytimeui.v2.bar.status;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.net.ConnectivityManager;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.os.ConfigurationCompat;
import com.oculus.common.gatekeepers.Gatekeeper;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.horizoncontent.social.SocialParty;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.databinding.AnytimeBarStatusBatteryDotsViewV2Binding;
import com.oculus.panelapp.anytimeui.databinding.AnytimeBarStatusViewV2Binding;
import com.oculus.panelapp.anytimeui.tooltip.TooltipController;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.panelapp.anytimeui.v2.bar.status.StatusViewModel;
import com.oculus.panelapp.anytimeui.v2.nux.OnboardingTutorial;
import com.oculus.panelapp.social.SocialPartyObserver;
import com.oculus.panelapp.social.SocialViewModel;
import com.oculus.tablet.logging.ClickEventButtonId;
import com.oculus.tablet.navigation.TabletDeepLinkingUriUtil;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.panels.InputFrame;
import java.text.DateFormat;

public final class StatusView extends ConstraintLayout {
    public static final float BATTERY_CURVE_CRITICAL = 0.1f;
    public static final float BATTERY_CURVE_EMPTY = 0.01f;
    public static final float BATTERY_CURVE_FULL = 0.75f;
    public static final float BATTERY_CURVE_LOW = 0.25f;
    public static final float BATTERY_CURVE_MID = 0.5f;
    public static final int BATTERY_DOT_ALPHA_EMPTY = 90;
    public static final int BATTERY_DOT_ALPHA_FULL = 255;
    public static final int BATTERY_DOT_CHARGING_ANIMATION = R.anim.anytime_bar_status_battery_dots_charging;
    public static final int BATTERY_DOT_GRAY = R.drawable.anytime_bar_status_battery_dot_gray;
    public static final int BATTERY_DOT_GRAY_DARK = R.drawable.anytime_bar_status_battery_dot_gray_dark;
    public static final int BATTERY_DOT_ORANGE = R.drawable.anytime_bar_status_battery_dot_orange;
    public static final int BATTERY_DOT_RED = R.drawable.anytime_bar_status_battery_dot_red;
    private static final String TAG = LoggingUtil.tag(StatusView.class);
    public static final String TOOLTIP_ID_BATTERY_HEADSET = "anytime_bar_status_battery_headset";
    public static final String TOOLTIP_ID_BATTERY_LEFT_CONTROLLER = "anytime_bar_status_battery_left_controller";
    public static final String TOOLTIP_ID_BATTERY_RIGHT_CONTROLLER = "anytime_bar_status_battery_right_controller";
    public static final String TOOLTIP_ID_DATETIME = "anytime_bar_datetime_tooltip";
    public static final String TOOLTIP_ID_WIFI_BUTTON = "anytime_bar_status_wifi_button";
    private static final int WIFI_ALT_OFF = R.drawable.oc_icon_wifi_alt_off_filled_24_a5a5a5;
    private static final int WIFI_LOW = R.drawable.oc_icon_wifi_low_filled_24_a5a5a5;
    private static final int WIFI_MID = R.drawable.oc_icon_wifi_mid_filled_24_a5a5a5;
    private static final int WIFI_OFF = R.drawable.oc_icon_wifi_off_filled_24_d2d2d2;
    private static final int WIFI_ON = R.drawable.oc_icon_wifi_on_filled_24_a5a5a5;
    private ImageView[] mBatteryDotsHeadset;
    private ImageView[] mBatteryDotsLeftController;
    private ImageView[] mBatteryDotsRightController;
    private AnytimeBarStatusViewV2Binding mBinding = AnytimeBarStatusViewV2Binding.inflate(LayoutInflater.from(getContext()), this, true);
    private ConnectivityManager mConnectivityManager;
    private SparseArray<Drawable> mIndicatorDrawables = new SparseArray<>();
    private boolean mIsVisibleInHmd = false;
    @Nullable
    private OnboardingTutorial mOnboardingTutorial;
    private AnytimeUIAndroidPanelAppV2 mPanelApp;
    private SocialPartyObserver mPartyObserver;
    private SocialViewModel mSocialViewModel;
    private StatusViewModel mStatusViewModel;
    private TooltipController mTooltipController;

    private int getBatteryChargeLevel(float f) {
        if (f > 0.75f) {
            return 4;
        }
        if (f > 0.5f) {
            return 3;
        }
        if (f > 0.25f) {
            return 2;
        }
        return f > 0.01f ? 1 : 0;
    }

    public StatusView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void initialize(AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2) {
        this.mPanelApp = anytimeUIAndroidPanelAppV2;
        this.mStatusViewModel = anytimeUIAndroidPanelAppV2.acquireStatusViewModel();
        this.mBinding.setViewModel(this.mStatusViewModel);
        this.mConnectivityManager = (ConnectivityManager) getContext().getSystemService("connectivity");
        this.mTooltipController = this.mPanelApp.getSystemTooltipController();
        OnboardingTutorial onboardingTutorial = this.mPanelApp.getOnboardingTutorial();
        if (onboardingTutorial.isActive()) {
            this.mOnboardingTutorial = onboardingTutorial;
        }
        initializeWifiButton();
        initializePartyButton();
        initializeBatteryDots();
        initializeDateTooltip();
        if (anytimeUIAndroidPanelAppV2.isGKEnabled(Gatekeeper.TRUSTED_USER)) {
            initializeTimeButton();
        }
    }

    public void destroy() {
        Log.d(TAG, "Destroying StatusView");
        this.mStatusViewModel.removeDateChangeListener(new StatusViewModel.DateChangeListener() {
            /* class com.oculus.panelapp.anytimeui.v2.bar.status.$$Lambda$StatusView$njq9VSxR6B42l9HbrhyTDCvigw */

            @Override // com.oculus.panelapp.anytimeui.v2.bar.status.StatusViewModel.DateChangeListener
            public final void onDateChanged() {
                StatusView.this.updateDateTooltip();
            }
        });
        this.mPanelApp.releaseStatusViewModel();
        SocialViewModel socialViewModel = this.mSocialViewModel;
        if (socialViewModel != null) {
            socialViewModel.removePartyObserver(this.mPartyObserver);
            this.mPanelApp.releaseSocialViewModel();
        }
    }

    public void onShow() {
        this.mIsVisibleInHmd = true;
    }

    public void onHide() {
        clearHeadsetBatteryDotAnimations();
        this.mIsVisibleInHmd = false;
    }

    private void initializePartyButton() {
        if (!this.mPanelApp.isGKEnabled(Gatekeeper.AUI_V2_ACTIVE_CALL_BAR)) {
            this.mPartyObserver = new SocialPartyObserver() {
                /* class com.oculus.panelapp.anytimeui.v2.bar.status.$$Lambda$StatusView$K8WryUZ3Wb1k0fRu7J5JHYWoM4 */

                @Override // com.oculus.panelapp.social.SocialPartyObserver
                public final void onUpdateParty(SocialParty socialParty) {
                    StatusView.this.lambda$initializePartyButton$81$StatusView(socialParty);
                }
            };
            this.mSocialViewModel = this.mPanelApp.acquireSocialViewModel();
            this.mSocialViewModel.registerPartyObserver(this.mPartyObserver);
        }
        this.mBinding.statusPartyIcon.setEventHandler(this.mPanelApp);
        this.mBinding.statusPartyIcon.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.anytimeui.v2.bar.status.$$Lambda$StatusView$CgQateESYLoiYEsPoFXXO3T91V4 */

            public final void onClick(View view) {
                StatusView.this.lambda$initializePartyButton$82$StatusView(view);
            }
        });
    }

    public /* synthetic */ void lambda$initializePartyButton$81$StatusView(SocialParty socialParty) {
        this.mBinding.setPartyButtonVisible(socialParty != null);
    }

    public /* synthetic */ void lambda$initializePartyButton$82$StatusView(View view) {
        if (this.mPanelApp.isGKEnabled(Gatekeeper.SOCIAL_NEW_PARTIES_PANEL_APP)) {
            this.mPanelApp.actionNavigate(SystemUXRoute.AUI_PARTIES, "");
        } else {
            this.mPanelApp.actionNavigate(SystemUXRoute.AUI_SOCIAL, TabletDeepLinkingUriUtil.AUI_SOCIAL_PARTIES_URI);
        }
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_SOCIAL_PARTIES_ACTIVE_PARTY_ICON);
    }

    private void initializeTimeButton() {
        this.mBinding.clockTooltipWrapper.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.anytimeui.v2.bar.status.$$Lambda$StatusView$_DAFvOHAodgAd6gvDas5DXsY8 */

            public final void onClick(View view) {
                StatusView.this.lambda$initializeTimeButton$83$StatusView(view);
            }
        });
    }

    public /* synthetic */ void lambda$initializeTimeButton$83$StatusView(View view) {
        this.mPanelApp.actionNavigate(SystemUXRoute.DATE_TIME_SETTINGS, "");
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_STATUS_TIME);
    }

    private void initializeWifiButton() {
        this.mBinding.statusWifiButton.setEventHandler(this.mPanelApp);
        this.mBinding.statusWifiButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.anytimeui.v2.bar.status.$$Lambda$StatusView$syda5j3SxQb9JniIXf3kJvPg_E */

            public final void onClick(View view) {
                StatusView.this.lambda$initializeWifiButton$84$StatusView(view);
            }
        });
    }

    public /* synthetic */ void lambda$initializeWifiButton$84$StatusView(View view) {
        this.mPanelApp.actionNavigate(SystemUXRoute.WIFI, "");
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_STATUS_WIFI);
    }

    private void initializeBatteryDots() {
        this.mBatteryDotsHeadset = new ImageView[]{this.mBinding.batteryDotsHeadset.dot1, this.mBinding.batteryDotsHeadset.dot2, this.mBinding.batteryDotsHeadset.dot3, this.mBinding.batteryDotsHeadset.dot4};
        this.mBatteryDotsLeftController = new ImageView[]{this.mBinding.batteryDotsLeftController.dot1, this.mBinding.batteryDotsLeftController.dot2, this.mBinding.batteryDotsLeftController.dot3, this.mBinding.batteryDotsLeftController.dot4};
        this.mBatteryDotsRightController = new ImageView[]{this.mBinding.batteryDotsRightController.dot1, this.mBinding.batteryDotsRightController.dot2, this.mBinding.batteryDotsRightController.dot3, this.mBinding.batteryDotsRightController.dot4};
    }

    private void initializeDateTooltip() {
        this.mStatusViewModel.addDateChangeListener(new StatusViewModel.DateChangeListener() {
            /* class com.oculus.panelapp.anytimeui.v2.bar.status.$$Lambda$StatusView$njq9VSxR6B42l9HbrhyTDCvigw */

            @Override // com.oculus.panelapp.anytimeui.v2.bar.status.StatusViewModel.DateChangeListener
            public final void onDateChanged() {
                StatusView.this.updateDateTooltip();
            }
        });
        updateDateTooltip();
    }

    /* access modifiers changed from: private */
    public void updateDateTooltip() {
        this.mTooltipController.setTooltipText(TOOLTIP_ID_DATETIME, DateFormat.getDateInstance(0, ConfigurationCompat.getLocales(getResources().getConfiguration()).get(0)).format(this.mStatusViewModel.getCurrentDate()));
    }

    public void frame(InputFrame inputFrame) {
        updateWifi(inputFrame);
        updateDeviceBattery(inputFrame);
    }

    private void updateWifi(InputFrame inputFrame) {
        this.mStatusViewModel.updateWifi(inputFrame);
        if (this.mStatusViewModel.shouldUpdateWifiUI()) {
            Log.d(TAG, "Updating WIFI button and tooltips");
            updateWifiIndicator();
            updateWifiTooltips();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.panelapp.anytimeui.v2.bar.status.StatusView$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$panelapp$anytimeui$v2$bar$status$WifiState = new int[WifiState.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        static {
            /*
                com.oculus.panelapp.anytimeui.v2.bar.status.WifiState[] r0 = com.oculus.panelapp.anytimeui.v2.bar.status.WifiState.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.oculus.panelapp.anytimeui.v2.bar.status.StatusView.AnonymousClass1.$SwitchMap$com$oculus$panelapp$anytimeui$v2$bar$status$WifiState = r0
                int[] r0 = com.oculus.panelapp.anytimeui.v2.bar.status.StatusView.AnonymousClass1.$SwitchMap$com$oculus$panelapp$anytimeui$v2$bar$status$WifiState     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.oculus.panelapp.anytimeui.v2.bar.status.WifiState r1 = com.oculus.panelapp.anytimeui.v2.bar.status.WifiState.OFF     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.oculus.panelapp.anytimeui.v2.bar.status.StatusView.AnonymousClass1.$SwitchMap$com$oculus$panelapp$anytimeui$v2$bar$status$WifiState     // Catch:{ NoSuchFieldError -> 0x001f }
                com.oculus.panelapp.anytimeui.v2.bar.status.WifiState r1 = com.oculus.panelapp.anytimeui.v2.bar.status.WifiState.CONNECTED     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = com.oculus.panelapp.anytimeui.v2.bar.status.StatusView.AnonymousClass1.$SwitchMap$com$oculus$panelapp$anytimeui$v2$bar$status$WifiState     // Catch:{ NoSuchFieldError -> 0x002a }
                com.oculus.panelapp.anytimeui.v2.bar.status.WifiState r1 = com.oculus.panelapp.anytimeui.v2.bar.status.WifiState.NOT_CONNECTED     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.anytimeui.v2.bar.status.StatusView.AnonymousClass1.<clinit>():void");
        }
    }

    private void updateWifiTooltips() {
        Resources resources = getResources();
        int i = AnonymousClass1.$SwitchMap$com$oculus$panelapp$anytimeui$v2$bar$status$WifiState[this.mStatusViewModel.getWifiState().ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    return;
                }
            } else if (this.mStatusViewModel.getConnectedNetworkSSID() != null) {
                this.mTooltipController.setTooltipText(TOOLTIP_ID_WIFI_BUTTON, this.mStatusViewModel.getConnectedNetworkSSID());
                if (this.mStatusViewModel.isNetworkConnectedWithInternet()) {
                    this.mTooltipController.setTooltipSubtext(TOOLTIP_ID_WIFI_BUTTON, resources.getString(R.string.anytime_bar_status_wifi_button_subtext_connected_tooltip_v2));
                    return;
                } else {
                    this.mTooltipController.setTooltipSubtext(TOOLTIP_ID_WIFI_BUTTON, resources.getString(R.string.anytime_bar_status_wifi_button_subtext_connected_no_internet_tooltip_v2));
                    return;
                }
            }
            this.mTooltipController.setTooltipText(TOOLTIP_ID_WIFI_BUTTON, resources.getString(R.string.anytime_bar_status_wifi_button_title_tooltip_v2));
            this.mTooltipController.setTooltipSubtext(TOOLTIP_ID_WIFI_BUTTON, resources.getString(R.string.anytime_bar_status_wifi_button_subtext_not_connected_tooltip_v2));
            return;
        }
        this.mTooltipController.setTooltipText(TOOLTIP_ID_WIFI_BUTTON, resources.getString(R.string.anytime_bar_status_wifi_button_title_tooltip_v2));
        this.mTooltipController.setTooltipSubtext(TOOLTIP_ID_WIFI_BUTTON, resources.getString(R.string.anytime_bar_status_wifi_button_subtext_off_tooltip_v2));
    }

    /* access modifiers changed from: protected */
    public void updateWifiIndicator() {
        int i = WIFI_ALT_OFF;
        if (this.mStatusViewModel.getWifiState() == WifiState.OFF) {
            i = WIFI_OFF;
        } else if (this.mStatusViewModel.isNetworkConnectedWithInternet()) {
            if (this.mStatusViewModel.getSignalLevelThreeScale() >= 2) {
                i = WIFI_ON;
            } else if (this.mStatusViewModel.getSignalLevelThreeScale() == 1) {
                i = WIFI_MID;
            } else if (this.mStatusViewModel.getSignalLevelThreeScale() == 0) {
                i = WIFI_LOW;
            }
        }
        if (this.mIndicatorDrawables.get(i) == null) {
            this.mIndicatorDrawables.put(i, new ScaleDrawable(getIndicatorDrawable(i), 0, 12.0f, 12.0f).getDrawable());
        }
        this.mBinding.statusWifiIcon.setImageDrawable(this.mIndicatorDrawables.get(i));
    }

    private void updateBatteryDots(ImageView[] imageViewArr, float f, boolean z) {
        if (f > 0.25f) {
            imageViewArr[0].setImageDrawable(getIndicatorDrawable(BATTERY_DOT_GRAY));
        } else if (f > 0.1f) {
            imageViewArr[0].setImageDrawable(getIndicatorDrawable(BATTERY_DOT_ORANGE));
        } else if (z || f > 0.01f) {
            imageViewArr[0].setImageDrawable(getIndicatorDrawable(BATTERY_DOT_RED));
        } else {
            imageViewArr[0].setImageDrawable(getIndicatorDrawable(BATTERY_DOT_GRAY_DARK));
        }
        int batteryChargeLevel = getBatteryChargeLevel(f);
        for (int i = 1; i < imageViewArr.length; i++) {
            if (i < batteryChargeLevel) {
                imageViewArr[i].setImageDrawable(getIndicatorDrawable(BATTERY_DOT_GRAY));
            } else {
                imageViewArr[i].setImageDrawable(getIndicatorDrawable(BATTERY_DOT_GRAY_DARK));
            }
        }
    }

    private void updateBatteryDotAnimations(boolean z, ImageView[] imageViewArr, int i) {
        int i2 = 0;
        if (!z || !this.mIsVisibleInHmd) {
            int length = imageViewArr.length;
            while (i2 < length) {
                imageViewArr[i2].clearAnimation();
                i2++;
            }
            return;
        }
        ImageView imageView = imageViewArr[i <= 0 ? 0 : i - 1];
        if (imageView.getAnimation() == null) {
            int length2 = imageViewArr.length;
            while (i2 < length2) {
                imageViewArr[i2].clearAnimation();
                i2++;
            }
            imageView.startAnimation(AnimationUtils.loadAnimation(getContext(), BATTERY_DOT_CHARGING_ANIMATION));
            imageView.setImageAlpha(255);
        }
    }

    private void clearHeadsetBatteryDotAnimations() {
        AnytimeBarStatusBatteryDotsViewV2Binding anytimeBarStatusBatteryDotsViewV2Binding = this.mBinding.batteryDotsHeadset;
        anytimeBarStatusBatteryDotsViewV2Binding.dot1.clearAnimation();
        anytimeBarStatusBatteryDotsViewV2Binding.dot2.clearAnimation();
        anytimeBarStatusBatteryDotsViewV2Binding.dot3.clearAnimation();
        anytimeBarStatusBatteryDotsViewV2Binding.dot4.clearAnimation();
    }

    public void updateDeviceBattery(InputFrame inputFrame) {
        this.mStatusViewModel.updateDeviceBattery(inputFrame);
        if (this.mStatusViewModel.shouldUpdateBatteryUI()) {
            Log.d(TAG, "Updating Device Battery Indicator");
            updateDeviceBatteryIndicator();
        }
    }

    /* access modifiers changed from: protected */
    public void updateDeviceBatteryIndicator() {
        boolean isExtendedBatteryConnected = this.mStatusViewModel.isExtendedBatteryConnected();
        float batteryLevel = ((float) this.mStatusViewModel.getBatteryLevel()) / ((float) this.mStatusViewModel.getBatteryScale());
        float combinedBatteryLevel = isExtendedBatteryConnected ? ((float) this.mStatusViewModel.getCombinedBatteryLevel()) / ((float) this.mStatusViewModel.getCombinedBatteryScale()) : batteryLevel;
        boolean isDeviceBatteryCharging = this.mStatusViewModel.isDeviceBatteryCharging();
        updateBatteryDots(this.mBatteryDotsHeadset, combinedBatteryLevel, isDeviceBatteryCharging);
        if (isExtendedBatteryConnected) {
            this.mBinding.iconBatteryHeadset.setImageResource(R.drawable.oc_icon_molokini_filled_24_a5a5a5);
            this.mTooltipController.setTooltipText(TOOLTIP_ID_BATTERY_HEADSET, getResources().getString(R.string.anytime_bar_status_headset_battery_plus_tooltip_v2, Integer.valueOf(convertChargeRatioToPercent(batteryLevel)), Integer.valueOf(convertChargeRatioToPercent(((float) this.mStatusViewModel.getExtendedBatteryLevel()) / ((float) this.mStatusViewModel.getExtendedBatteryScale())))));
        } else {
            this.mBinding.iconBatteryHeadset.setImageResource(R.drawable.oc_icon_headset_filled_24_a5a5a5);
            this.mTooltipController.setTooltipText(TOOLTIP_ID_BATTERY_HEADSET, getResources().getString(R.string.anytime_bar_status_headset_battery_tooltip_v2, Integer.valueOf(convertChargeRatioToPercent(batteryLevel))));
        }
        if (isDeviceBatteryCharging) {
            this.mTooltipController.setTooltipSubtext(TOOLTIP_ID_BATTERY_HEADSET, getResources().getString(R.string.anytime_bar_status_headset_battery_charging_tooltip_subtext_v2));
        } else if (this.mStatusViewModel.isDeviceBatterySaverOn()) {
            this.mTooltipController.setTooltipSubtext(TOOLTIP_ID_BATTERY_HEADSET, getResources().getString(R.string.anytime_bar_status_headset_battery_saver_tooltip_subtext_v2));
        } else {
            this.mTooltipController.setTooltipSubtext(TOOLTIP_ID_BATTERY_HEADSET, "");
        }
    }

    private Drawable getIndicatorDrawable(int i) {
        if (this.mIndicatorDrawables.get(i) == null) {
            this.mIndicatorDrawables.put(i, getContext().getDrawable(i));
        }
        return this.mIndicatorDrawables.get(i);
    }

    private int convertChargeRatioToPercent(float f) {
        return Math.max(Math.min(Math.round(f * 100.0f), 100), 0);
    }

    private String getControllerBatteryTooltipText(ControllerBatteryState controllerBatteryState) {
        int max = Math.max(Math.min(Math.round(controllerBatteryState.getCharge() * 10.0f) * 10, 100), 0);
        if (controllerBatteryState.getHandedness() == ControllerBatteryHandedness.LEFT) {
            if (!controllerBatteryState.isConnected()) {
                return getResources().getString(R.string.anytime_bar_status_left_controller_battery_not_connected_tooltip_v2);
            }
            return getResources().getString(R.string.anytime_bar_status_left_controller_battery_tooltip_v2, Integer.valueOf(max));
        } else if (controllerBatteryState.getHandedness() != ControllerBatteryHandedness.RIGHT) {
            String str = TAG;
            Log.e(str, "Unhandled controller handedness " + controllerBatteryState.getHandedness());
            return "";
        } else if (!controllerBatteryState.isConnected()) {
            return getResources().getString(R.string.anytime_bar_status_right_controller_battery_not_connected_tooltip_v2);
        } else {
            return getResources().getString(R.string.anytime_bar_status_right_controller_battery_tooltip_v2, Integer.valueOf(max));
        }
    }

    public void updateControllerBattery(ControllerBatteryState controllerBatteryState) {
        String str;
        ImageView[] imageViewArr;
        if (controllerBatteryState.getHandedness() == ControllerBatteryHandedness.LEFT) {
            imageViewArr = this.mBatteryDotsLeftController;
            str = TOOLTIP_ID_BATTERY_LEFT_CONTROLLER;
        } else if (controllerBatteryState.getHandedness() == ControllerBatteryHandedness.RIGHT) {
            imageViewArr = this.mBatteryDotsRightController;
            str = TOOLTIP_ID_BATTERY_RIGHT_CONTROLLER;
        } else {
            String str2 = TAG;
            Log.e(str2, "Unhandled controller handedness " + controllerBatteryState.getHandedness());
            return;
        }
        updateBatteryDots(imageViewArr, controllerBatteryState.getCharge(), false);
        this.mTooltipController.setTooltipText(str, getControllerBatteryTooltipText(controllerBatteryState));
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        OnboardingTutorial onboardingTutorial = this.mOnboardingTutorial;
        if (onboardingTutorial == null || !onboardingTutorial.isActive()) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        return true;
    }

    public boolean onInterceptHoverEvent(MotionEvent motionEvent) {
        OnboardingTutorial onboardingTutorial = this.mOnboardingTutorial;
        if (onboardingTutorial == null || !onboardingTutorial.isActive()) {
            return super.onInterceptHoverEvent(motionEvent);
        }
        return true;
    }
}
