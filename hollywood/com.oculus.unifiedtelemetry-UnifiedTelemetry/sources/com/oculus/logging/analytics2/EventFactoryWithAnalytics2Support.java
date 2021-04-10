package com.oculus.logging.analytics2;

import X.AbstractC0096Hu;
import X.AbstractC0247Xu;
import X.C0219We;
import X.C0515sp;
import X.Fe;
import X.QC;
import X.Rc;
import X.SL;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import com.facebook.inject.Assisted;
import com.facebook.ultralight.Dependencies;
import com.oculus.common.serial.BuildSerialUtil;
import com.oculus.errorreporting.interfaces.IErrorReporter;
import com.oculus.logging.EventFactory;
import com.oculus.logging.ExtraKeys;
import com.oculus.logging.OculusLoggingEvent;
import com.oculus.time.Clock;
import com.oculus.unifiedtelemetry.unifiedlogging.MobileConfigClient;
import com.oculus.unifiedtelemetry.unifiedlogging.NonSystemLoggingUser;
import com.oculus.unifiedtelemetry.unifiedlogging.SystemLoggingUser;
import javax.annotation.Nullable;
import javax.inject.Inject;

@Dependencies({"_UL__ULSEP_com_oculus_logging_analytics2_EventCacheProvider_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_logging_analytics2_EventBuilderProvider_ULSEP_BINDING_ID"})
public class EventFactoryWithAnalytics2Support implements EventFactory {
    public final EventBuilder mEventBuilder;
    @Inject
    public final EventBuilderProvider mEventBuilderProvider;
    public final EventCache mEventCache;
    @Inject
    public final EventCacheProvider mEventCacheProvider;

    @Override // com.oculus.logging.EventFactory
    public final OculusLoggingEvent A3S(String str) {
        return A3T(null, str, false);
    }

    @Inject
    public EventFactoryWithAnalytics2Support(AbstractC0247Xu xu, @Assisted LoggingUser loggingUser) {
        this.mEventCacheProvider = (EventCacheProvider) C0515sp.A00(28, xu);
        this.mEventBuilderProvider = (EventBuilderProvider) C0515sp.A00(53, xu);
        EventCacheProvider eventCacheProvider = this.mEventCacheProvider;
        EventCache eventCache = new EventCache(eventCacheProvider, (Context) C0515sp.A00(99, eventCacheProvider), this);
        this.mEventCache = eventCache;
        this.mEventBuilder = new EventBuilder(this.mEventBuilderProvider, loggingUser, eventCache);
    }

    @Override // com.oculus.logging.EventFactory
    public final OculusLoggingEvent A3T(@Nullable String str, String str2, boolean z) {
        OculusLoggingEvent noOpEvent;
        String str3;
        boolean z2;
        boolean A01;
        EventBuilder eventBuilder = this.mEventBuilder;
        SharedPreferences A00 = EventBuilderConfig.A00((EventBuilderConfig) AbstractC0096Hu.A03(5, 37, eventBuilder._UL_mInjectionContext));
        boolean z3 = true;
        if (A00 != null) {
            z3 = A00.getBoolean(EventBuilderConfig.KEY_A2_ENABLED, true);
        }
        if (z3) {
            QC qc = eventBuilder._UL_mInjectionContext;
            EventCache eventCache = eventBuilder.mEventCache;
            noOpEvent = new OculusLoggingEventAnalytics2Impl((Fe) AbstractC0096Hu.A03(0, 101, qc), (IErrorReporter) AbstractC0096Hu.A03(3, 135, qc), (C0219We) AbstractC0096Hu.A03(4, 73, qc), eventCache, str, str2, z, eventCache.A04());
        } else {
            noOpEvent = new NoOpEvent();
        }
        QC qc2 = eventBuilder._UL_mInjectionContext;
        EventBuilderConfig eventBuilderConfig = (EventBuilderConfig) AbstractC0096Hu.A03(5, 37, qc2);
        Clock clock = (Clock) AbstractC0096Hu.A03(1, 62, qc2);
        C0219We we = (C0219We) AbstractC0096Hu.A03(4, 73, qc2);
        SharedPreferences A002 = EventBuilderConfig.A00(eventBuilderConfig);
        if (A002 != null && A002.getBoolean(EventBuilderConfig.KEY_LOGCAT_ENABLED, false)) {
            SharedPreferences A003 = EventBuilderConfig.A00(eventBuilderConfig);
            if (A003 != null && !A003.getBoolean(EventBuilderConfig.KEY_A2_ENABLED, true)) {
                noOpEvent = new OculusDebugParamCollectionOnlyEvent(we);
            }
            noOpEvent = new OculusDebugEventWrapper(noOpEvent, str, str2, eventBuilderConfig, clock);
        }
        String str4 = eventBuilder.mXrsSessionId;
        if (str4 != null) {
            noOpEvent.A15(ExtraKeys.XRS_SESSION_ID, str4);
        }
        LoggingUser loggingUser = eventBuilder.mUser;
        if (!(loggingUser instanceof SystemLoggingUser)) {
            str3 = ((NonSystemLoggingUser) loggingUser).mId;
        } else {
            str3 = ((SystemLoggingUser) loggingUser).mCredentialsProvider.get().mUserId;
            if (TextUtils.isEmpty(str3)) {
                str3 = "0";
            }
        }
        noOpEvent.A15("id", str3);
        noOpEvent.A15(ExtraKeys.HW_DEVICE_ID, eventBuilder.mSettingsManager.mHwDeviceId);
        noOpEvent.A15(ExtraKeys.DEVICE_SERIAL, BuildSerialUtil.A00());
        LoggingUser loggingUser2 = eventBuilder.mUser;
        if (!(loggingUser2 instanceof SystemLoggingUser)) {
            z2 = false;
        } else {
            SystemLoggingUser systemLoggingUser = (SystemLoggingUser) loggingUser2;
            z2 = false;
            if (!TextUtils.isEmpty(systemLoggingUser.mCredentialsProvider.get().mUserId)) {
                MobileConfigClient mobileConfigClient = (MobileConfigClient) AbstractC0096Hu.A03(0, 134, systemLoggingUser._UL_mInjectionContext);
                if (mobileConfigClient.mIsInitialized) {
                    A01 = ((Rc) AbstractC0096Hu.A03(0, 115, mobileConfigClient._UL_mInjectionContext)).A2L(36310271995936771L);
                } else {
                    A01 = SL.A01(36310271995936771L);
                }
                if (A01) {
                    z2 = true;
                }
            }
        }
        noOpEvent.A16(ExtraKeys.TRUSTED_USER, z2);
        noOpEvent.A16(ExtraKeys.UTL_PRIMARY_USER, eventBuilder.mUser.isPrimary);
        noOpEvent.A15(ExtraKeys.ENDPOINT_HOST, (String) AbstractC0096Hu.A03(2, 47, eventBuilder._UL_mInjectionContext));
        noOpEvent.A15(ExtraKeys.BUILD_PRODUCT, Build.PRODUCT);
        noOpEvent.A15(ExtraKeys.BUILD_MANUFACTURER, Build.MANUFACTURER);
        noOpEvent.A15("build_type", Build.TYPE);
        noOpEvent.A15(ExtraKeys.BUILD_VERSION, Build.VERSION.INCREMENTAL);
        noOpEvent.A15(ExtraKeys.BUILD_RELEASE_VERSION, Build.VERSION.RELEASE);
        noOpEvent.A13(ExtraKeys.MANAGED_MODE, eventBuilder.mSettingsManager.mManagedMode);
        return noOpEvent;
    }
}
