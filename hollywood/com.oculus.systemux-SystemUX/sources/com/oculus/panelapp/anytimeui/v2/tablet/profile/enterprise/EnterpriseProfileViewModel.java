package com.oculus.panelapp.anytimeui.v2.tablet.profile.enterprise;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;
import androidx.core.os.ConfigurationCompat;
import androidx.core.text.HtmlCompat;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.os.enterprise.Configuration;
import com.oculus.os.enterprise.EnterpriseServer;
import com.oculus.os.enterprise.Mode;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.tablet.view.ViewModelLifecycle;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class EnterpriseProfileViewModel extends BaseObservable implements ViewModelLifecycle {
    public static final int EXPIRATION_WARNING_THRESHOLD_DAYS = 14;
    public static final int MAX_KEYPAD_ENTRY_LENGTH = 4;
    public static final int MILLISECOND_PER_DAY = 86400000;
    private static final String OC_SHELL_ENTERPRISE_CONFIGURATION_MODE_INDEX = "_oc_shell_enterprise_configuration_mode_index";
    private static final String TAG = LoggingUtil.tag(EnterpriseProfileViewModel.class);
    private final Configuration mConfiguration;
    private final Context mContext;
    private final DateFormat mDeviceUpdateDateFormat;
    private final SimpleDateFormat mDeviceUpdateTimeFormat;
    private final String mExpectedPin;
    private final DateFormat mExpirationFormat;
    private boolean mIsKeypadThrottled = false;
    private CharSequence mLastSyncText;
    private CharSequence mLicenseText;
    private final AnytimeUIAndroidPanelAppV2 mPanelApp;
    private StringBuilder mPinCode;
    private CharSequence mTimestampText;
    private UpdateCheckState mUpdateCheckState = UpdateCheckState.ReadyToCheck;

    public enum UpdateCheckState {
        ReadyToCheck,
        CheckingForUpdates,
        NoUpdatesFound
    }

    public EnterpriseProfileViewModel(Context context, AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2) {
        Log.d(TAG, "Constructing ViewModel");
        this.mContext = context;
        this.mPanelApp = anytimeUIAndroidPanelAppV2;
        this.mConfiguration = EnterpriseServer.getConfiguration(this.mContext);
        this.mPinCode = new StringBuilder();
        Locale locale = ConfigurationCompat.getLocales(this.mContext.getResources().getConfiguration()).get(0);
        this.mExpirationFormat = DateFormat.getDateInstance(1, locale);
        this.mDeviceUpdateDateFormat = DateFormat.getDateInstance(2, locale);
        this.mDeviceUpdateTimeFormat = createTimeFormat(this.mContext, locale);
        Mode[] modes = this.mConfiguration.getModes();
        if (modes[getAdminModeIndex()].getPin().isPresent()) {
            this.mExpectedPin = modes[getAdminModeIndex()].getPin().get();
        } else {
            this.mExpectedPin = "";
        }
        refreshSubtitleText();
    }

    @Override // com.oculus.tablet.view.ViewModelLifecycle
    public void destroy() {
        Log.d(TAG, "Destroying ViewModel");
    }

    private static SimpleDateFormat createTimeFormat(Context context, Locale locale) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(android.text.format.DateFormat.getBestDateTimePattern(locale, android.text.format.DateFormat.is24HourFormat(context) ? "Hm" : "hm"), locale);
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        return simpleDateFormat;
    }

    private CharSequence formatString(int i, Object... objArr) {
        return HtmlCompat.fromHtml(String.format(this.mContext.getResources().getString(i), objArr), 0);
    }

    public void refreshSubtitleText() {
        Configuration configuration = EnterpriseServer.getConfiguration(this.mContext);
        Date licenseExpirationDate = configuration.getLicenseExpirationDate();
        String format = this.mExpirationFormat.format(licenseExpirationDate);
        if (((int) (Math.max(licenseExpirationDate.getTime() - new Date().getTime(), 0L) / 86400000)) <= 14) {
            this.mLicenseText = formatString(R.string.anytime_tablet_enterprise_profile_license_subtitle_expiring_soon_format, format);
        } else {
            this.mLicenseText = formatString(R.string.anytime_tablet_enterprise_profile_license_subtitle_active_format, format);
        }
        Date timestamp = configuration.getTimestamp();
        String format2 = this.mDeviceUpdateDateFormat.format(timestamp);
        String format3 = this.mDeviceUpdateTimeFormat.format(timestamp);
        this.mTimestampText = formatString(R.string.enterprise_profile_last_update_time_subtitle_format, format2, format3);
        if (!configuration.getTimestamp().equals(new Date(0))) {
            Date lastFetchTime = configuration.getLastFetchTime();
            String format4 = this.mDeviceUpdateDateFormat.format(lastFetchTime);
            String format5 = this.mDeviceUpdateTimeFormat.format(lastFetchTime);
            this.mLastSyncText = formatString(R.string.enterprise_profile_last_sync_time_subtitle_format, format4, format5);
        } else {
            this.mLastSyncText = this.mContext.getResources().getString(R.string.enterprise_profile_last_sync_time_subtitle_last_sync_unknown);
        }
        notifyPropertyChanged(BR.licenseText);
        notifyPropertyChanged(BR.lastUpdateTimeText);
        notifyPropertyChanged(BR.lastSyncTimeText);
    }

    @Bindable
    public String getCompanyName() {
        String ownerName = this.mConfiguration.getOwnerName();
        return ownerName == null ? this.mContext.getResources().getString(R.string.anytime_tablet_enterprise_profile_default_company_name) : ownerName;
    }

    @Bindable
    public CharSequence getLicenseText() {
        return this.mLicenseText;
    }

    @Bindable
    public CharSequence getLastUpdateTimeText() {
        return this.mTimestampText;
    }

    @Bindable
    public CharSequence getLastSyncTimeText() {
        return this.mLastSyncText;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.panelapp.anytimeui.v2.tablet.profile.enterprise.EnterpriseProfileViewModel$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$panelapp$anytimeui$v2$tablet$profile$enterprise$EnterpriseProfileViewModel$UpdateCheckState = new int[UpdateCheckState.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        static {
            /*
                com.oculus.panelapp.anytimeui.v2.tablet.profile.enterprise.EnterpriseProfileViewModel$UpdateCheckState[] r0 = com.oculus.panelapp.anytimeui.v2.tablet.profile.enterprise.EnterpriseProfileViewModel.UpdateCheckState.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.oculus.panelapp.anytimeui.v2.tablet.profile.enterprise.EnterpriseProfileViewModel.AnonymousClass1.$SwitchMap$com$oculus$panelapp$anytimeui$v2$tablet$profile$enterprise$EnterpriseProfileViewModel$UpdateCheckState = r0
                int[] r0 = com.oculus.panelapp.anytimeui.v2.tablet.profile.enterprise.EnterpriseProfileViewModel.AnonymousClass1.$SwitchMap$com$oculus$panelapp$anytimeui$v2$tablet$profile$enterprise$EnterpriseProfileViewModel$UpdateCheckState     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.oculus.panelapp.anytimeui.v2.tablet.profile.enterprise.EnterpriseProfileViewModel$UpdateCheckState r1 = com.oculus.panelapp.anytimeui.v2.tablet.profile.enterprise.EnterpriseProfileViewModel.UpdateCheckState.CheckingForUpdates     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.oculus.panelapp.anytimeui.v2.tablet.profile.enterprise.EnterpriseProfileViewModel.AnonymousClass1.$SwitchMap$com$oculus$panelapp$anytimeui$v2$tablet$profile$enterprise$EnterpriseProfileViewModel$UpdateCheckState     // Catch:{ NoSuchFieldError -> 0x001f }
                com.oculus.panelapp.anytimeui.v2.tablet.profile.enterprise.EnterpriseProfileViewModel$UpdateCheckState r1 = com.oculus.panelapp.anytimeui.v2.tablet.profile.enterprise.EnterpriseProfileViewModel.UpdateCheckState.NoUpdatesFound     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.anytimeui.v2.tablet.profile.enterprise.EnterpriseProfileViewModel.AnonymousClass1.<clinit>():void");
        }
    }

    @Bindable({"lastSyncTimeText", "updateCheckState"})
    public CharSequence getLastSyncSubtitleText() {
        Resources resources = this.mContext.getResources();
        int i = AnonymousClass1.$SwitchMap$com$oculus$panelapp$anytimeui$v2$tablet$profile$enterprise$EnterpriseProfileViewModel$UpdateCheckState[this.mUpdateCheckState.ordinal()];
        if (i == 1) {
            return resources.getString(R.string.enterprise_profile_last_sync_time_subtitle_checking_for_updates);
        }
        if (i != 2) {
            return this.mLastSyncText;
        }
        return resources.getString(R.string.enterprise_profile_last_sync_time_subtitle_no_updates_found);
    }

    @Bindable
    public boolean getIsAdminPinRequired() {
        return !this.mExpectedPin.equals("");
    }

    @Bindable
    public boolean getIsAdminModeEnabled() {
        return getCurrentModeIndex() != getConfigurationDefaultModeIndex();
    }

    public int getConfigurationDefaultModeIndex() {
        return this.mConfiguration.getDefaultModeIndex();
    }

    private int getAdminModeIndex() {
        return this.mConfiguration.getDefaultModeIndex() != 0 ? 0 : 1;
    }

    private int getCurrentModeIndex() {
        String environmentArg = this.mPanelApp.getEnvironmentArg(OC_SHELL_ENTERPRISE_CONFIGURATION_MODE_INDEX);
        if (environmentArg != null) {
            try {
                return Integer.parseInt(environmentArg);
            } catch (NumberFormatException unused) {
                String str = TAG;
                Log.d(str, "Unable to parse current mode index: " + environmentArg);
            }
        } else {
            Log.d(TAG, "Configuration mode index was not found");
            return 0;
        }
    }

    @Bindable
    public int getKeypadEntryLength() {
        return this.mPinCode.length();
    }

    public void appendKeypadDigit(int i) {
        this.mPinCode.append(i);
        notifyPropertyChanged(BR.keypadEntryLength);
    }

    public void eraseLastKeypadDigit() {
        if (this.mPinCode.length() > 0) {
            StringBuilder sb = this.mPinCode;
            sb.deleteCharAt(sb.length() - 1);
        }
        notifyPropertyChanged(BR.keypadEntryLength);
    }

    public void clearKeypadDigits() {
        this.mPinCode = new StringBuilder();
        notifyPropertyChanged(BR.keypadEntryLength);
    }

    public boolean isKeypadEntryCorrect() {
        return this.mPinCode.toString().equals(this.mExpectedPin);
    }

    @Bindable
    public boolean getIsKeypadThrottled() {
        return this.mIsKeypadThrottled;
    }

    public void throttleKeypad(boolean z) {
        this.mIsKeypadThrottled = z;
        notifyPropertyChanged(BR.isKeypadThrottled);
    }

    @Bindable({"isAdminModeEnabled", "updateCheckState"})
    public boolean getIsCheckForUpdatesVisible() {
        return getIsAdminModeEnabled() && (this.mUpdateCheckState == UpdateCheckState.ReadyToCheck || this.mUpdateCheckState == UpdateCheckState.NoUpdatesFound);
    }

    @Bindable({"isAdminModeEnabled", "updateCheckState"})
    public boolean getIsCheckingForUpdatesVisible() {
        return getIsAdminModeEnabled() && this.mUpdateCheckState == UpdateCheckState.CheckingForUpdates;
    }

    @Bindable({"keypadEntryLength", "isKeypadThrottled"})
    public boolean getIsKeypadBackspaceEnabled() {
        return getKeypadEntryLength() > 0 && !getIsKeypadThrottled();
    }

    @Bindable({"keypadEntryLength", "isKeypadThrottled"})
    public boolean getIsKeypadEntryReadyToBeChecked() {
        return getKeypadEntryLength() == 4 && !getIsKeypadThrottled();
    }

    @Bindable({"isKeypadThrottled"})
    public Drawable getFilledKeypadBubble() {
        return this.mContext.getResources().getDrawable(getIsKeypadThrottled() ? R.drawable.anytime_tablet_enterprise_admin_keypad_throttled_bubble : R.drawable.anytime_tablet_enterprise_admin_keypad_filled_bubble, null);
    }

    @Bindable
    public UpdateCheckState getUpdateCheckState() {
        return this.mUpdateCheckState;
    }

    public void setUpdateCheckState(UpdateCheckState updateCheckState) {
        this.mUpdateCheckState = updateCheckState;
        notifyPropertyChanged(BR.updateCheckState);
    }
}
