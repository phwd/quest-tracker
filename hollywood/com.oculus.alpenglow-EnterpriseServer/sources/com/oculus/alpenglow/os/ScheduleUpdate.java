package com.oculus.alpenglow.os;

import X.AbstractC02990bJ;
import X.AnonymousClass006;
import X.AnonymousClass0GO;
import X.AnonymousClass0Lh;
import X.AnonymousClass0NK;
import X.AnonymousClass0R7;
import X.AnonymousClass0u6;
import X.C01710Lb;
import X.C03200bn;
import X.C04910hv;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.oculus.alpenglow.config.ConfigurationStore;
import com.oculus.alpenglow.config.Device;
import com.oculus.alpenglow.constants.Constants;
import com.oculus.alpenglow.graphql.enums.GraphQLHWMDayOfWeek;
import com.oculus.alpenglow.graphql.enums.GraphQLHWMOculusOSConfigUpdateRule;
import com.oculus.alpenglow.logging.OSUpdateLogger;
import com.oculus.os.SettingsManager;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID", "_UL__ULSEP_com_facebook_common_time_SystemClock_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_alpenglow_logging_OSUpdateLogger_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_os_SettingsManager_ULSEP_BINDING_ID", "_UL__ULSEP_com_facebook_secure_context_SecureContextHelper_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_alpenglow_config_ConfigurationStore_ULSEP_BINDING_ID"})
public class ScheduleUpdate {
    public static final Map<GraphQLHWMDayOfWeek, Integer> DAY_OF_WEEK_MAPPING;
    public static final String OTA_COMPONENT_NAME = "com.oculus.updater.core.os.OSUpdateService";
    public static final String OTA_PACKAGE_NAME = "com.oculus.updater";
    public static final long SCHEDULE_BACKOFF_TIME = TimeUnit.MINUTES.toMillis(5);
    public static final String TAG = AnonymousClass006.A05(Constants.TAG_PREFIX, "ScheduleUpdate");
    public AnonymousClass0R7 _UL_mInjectionContext;

    public final boolean A02(@Nullable Device.ManagementInfo.DeviceConfig.OsConfig osConfig) {
        if (osConfig == null) {
            ((OSUpdateLogger) AnonymousClass0Lh.A03(2, 81, this._UL_mInjectionContext)).A01(true, false, "no os config provided");
            return false;
        }
        ImmutableList<? extends Device.ManagementInfo.DeviceConfig.OsConfig.UpdateTimeWindows> A4p = osConfig.A4p();
        Date date = new Date(((C03200bn) AnonymousClass0Lh.A03(1, 98, this._UL_mInjectionContext)).now());
        AnonymousClass0u6<? extends Device.ManagementInfo.DeviceConfig.OsConfig.UpdateTimeWindows> it = A4p.iterator();
        Date date2 = null;
        Date date3 = null;
        boolean z = false;
        while (it.hasNext()) {
            Device.ManagementInfo.DeviceConfig.OsConfig.UpdateTimeWindows updateTimeWindows = (Device.ManagementInfo.DeviceConfig.OsConfig.UpdateTimeWindows) it.next();
            if (updateTimeWindows != null) {
                AnonymousClass0u6<GraphQLHWMDayOfWeek> it2 = updateTimeWindows.A3L().iterator();
                while (it2.hasNext()) {
                    Integer num = DAY_OF_WEEK_MAPPING.get(it2.next());
                    if (num == null) {
                        AnonymousClass0NK.A01(TAG, "Unsupported dayOfWeek format for daysOfWeek, please double check.");
                    } else {
                        int intValue = num.intValue();
                        Calendar instance = Calendar.getInstance();
                        instance.setTime(date);
                        int i = instance.get(7);
                        if (i != intValue) {
                            int i2 = i - intValue;
                            int i3 = 7 - i2;
                            if (i2 < 0) {
                                i3 = -i2;
                            }
                            instance.add(6, i3);
                        }
                        Date time = instance.getTime();
                        Calendar instance2 = Calendar.getInstance();
                        instance2.setTime(time);
                        instance2.set(11, 0);
                        instance2.set(12, 0);
                        instance2.set(13, 0);
                        instance2.set(14, 0);
                        Date time2 = instance2.getTime();
                        int A4x = updateTimeWindows.A4x();
                        Calendar instance3 = Calendar.getInstance();
                        instance3.setTime(time2);
                        instance3.add(13, A4x);
                        Date time3 = instance3.getTime();
                        Calendar instance4 = Calendar.getInstance();
                        instance4.setTime(time);
                        instance4.set(11, 0);
                        instance4.set(12, 0);
                        instance4.set(13, 0);
                        instance4.set(14, 0);
                        Date time4 = instance4.getTime();
                        int A4w = updateTimeWindows.A4w();
                        Calendar instance5 = Calendar.getInstance();
                        instance5.setTime(time4);
                        instance5.add(13, A4w);
                        Date time5 = instance5.getTime();
                        if (!time5.after(time3)) {
                            Calendar instance6 = Calendar.getInstance();
                            instance6.setTime(time5);
                            instance6.add(6, 1);
                            time5 = instance6.getTime();
                        }
                        if (time5.getTime() - time3.getTime() == TimeUnit.DAYS.toMillis(1)) {
                            z = true;
                        }
                        if (time5.after(date) && (date2 == null || time3.before(date2))) {
                            date3 = time5;
                            date2 = time3;
                        }
                    }
                }
            }
        }
        if (date2 != null) {
            JobInfo.Builder builder = new JobInfo.Builder(Constants.OTA_SCHEDULE_WINDOW_START_JOB_ID, new ComponentName((Context) AnonymousClass0Lh.A03(0, 4, this._UL_mInjectionContext), ScheduleUpdateJobService.class));
            builder.setPersisted(true).setRequiredNetworkType(1).setMinimumLatency(Math.max(1L, date2.getTime() - ((C03200bn) AnonymousClass0Lh.A03(1, 98, this._UL_mInjectionContext)).now())).setOverrideDeadline(Math.max(1L, date2.getTime() - ((C03200bn) AnonymousClass0Lh.A03(1, 98, this._UL_mInjectionContext)).now())).setBackoffCriteria(SCHEDULE_BACKOFF_TIME, 0);
            JobScheduler jobScheduler = (JobScheduler) ((Context) AnonymousClass0Lh.A03(0, 4, this._UL_mInjectionContext)).getSystemService("jobscheduler");
            if (jobScheduler == null) {
                AnonymousClass0NK.A01(TAG, "Job scheduler service not found");
                ((OSUpdateLogger) AnonymousClass0Lh.A03(2, 81, this._UL_mInjectionContext)).A01(true, false, "scheduleOTAWindowStart: Job scheduler not found");
            } else {
                jobScheduler.schedule(builder.build());
                ((OSUpdateLogger) AnonymousClass0Lh.A03(2, 81, this._UL_mInjectionContext)).A01(true, true, null);
            }
            if (!z && date3 != null) {
                JobInfo.Builder builder2 = new JobInfo.Builder(Constants.OTA_SCHEDULE_WINDOW_END_JOB_ID, new ComponentName((Context) AnonymousClass0Lh.A03(0, 4, this._UL_mInjectionContext), ScheduleUpdateJobService.class));
                builder2.setPersisted(true).setRequiredNetworkType(1).setMinimumLatency(Math.max(1L, date3.getTime() - ((C03200bn) AnonymousClass0Lh.A03(1, 98, this._UL_mInjectionContext)).now())).setOverrideDeadline(Math.max(1L, date3.getTime() - ((C03200bn) AnonymousClass0Lh.A03(1, 98, this._UL_mInjectionContext)).now())).setBackoffCriteria(SCHEDULE_BACKOFF_TIME, 0);
                JobScheduler jobScheduler2 = (JobScheduler) ((Context) AnonymousClass0Lh.A03(0, 4, this._UL_mInjectionContext)).getSystemService("jobscheduler");
                if (jobScheduler2 == null) {
                    AnonymousClass0NK.A01(TAG, "Job scheduler service not found");
                } else {
                    jobScheduler2.schedule(builder2.build());
                    return true;
                }
            }
            return true;
        }
        ((OSUpdateLogger) AnonymousClass0Lh.A03(2, 81, this._UL_mInjectionContext)).A01(true, false, "no update window found");
        return false;
    }

    static {
        ImmutableMap.Builder builder = new ImmutableMap.Builder();
        builder.put(GraphQLHWMDayOfWeek.SUNDAY, 1);
        builder.put(GraphQLHWMDayOfWeek.MONDAY, 2);
        builder.put(GraphQLHWMDayOfWeek.TUESDAY, 3);
        builder.put(GraphQLHWMDayOfWeek.WEDNESDAY, 4);
        builder.put(GraphQLHWMDayOfWeek.THURSDAY, 5);
        builder.put(GraphQLHWMDayOfWeek.FRIDAY, 6);
        builder.put(GraphQLHWMDayOfWeek.SATURDAY, 7);
        DAY_OF_WEEK_MAPPING = builder.build();
    }

    public final void A00() {
        GraphQLHWMOculusOSConfigUpdateRule graphQLHWMOculusOSConfigUpdateRule;
        String str;
        C01710Lb r4;
        Device.ManagementInfo.DeviceConfig A3Q;
        Device.ManagementInfo.DeviceConfig.OsConfig A4D;
        Device.ManagementInfo A3y = ((ConfigurationStore) AnonymousClass0Lh.A03(5, 97, this._UL_mInjectionContext)).mData.device.A3y();
        if (A3y == null || (A3Q = A3y.A3Q()) == null || (A4D = A3Q.A4D()) == null || (graphQLHWMOculusOSConfigUpdateRule = A4D.A4o()) == null || graphQLHWMOculusOSConfigUpdateRule == GraphQLHWMOculusOSConfigUpdateRule.UNSET_OR_UNRECOGNIZED_ENUM_VALUE) {
            graphQLHWMOculusOSConfigUpdateRule = GraphQLHWMOculusOSConfigUpdateRule.IDLE;
        }
        if (GraphQLHWMOculusOSConfigUpdateRule.FORCE.equals(graphQLHWMOculusOSConfigUpdateRule)) {
            str = "ext_check_updates";
        } else {
            str = "check_updates";
        }
        Intent intent = new Intent(str);
        intent.setComponent(new ComponentName("com.oculus.updater", OTA_COMPONENT_NAME));
        Object A03 = AnonymousClass0Lh.A03(4, 15, this._UL_mInjectionContext);
        C04910hv r5 = (C04910hv) A03;
        synchronized (A03) {
            r4 = r5.A00;
            if (r4 == null) {
                AnonymousClass0GO r3 = r5.A01;
                if (r3 == null) {
                    r3 = new AnonymousClass0GO(C04910hv.A0D, C04910hv.A0C, C04910hv.A0E);
                    r5.A01 = r3;
                }
                r4 = new C01710Lb(r3, r5.A08);
                r5.A00 = r4;
            }
        }
        r4.A00(intent, (Context) AnonymousClass0Lh.A03(0, 4, this._UL_mInjectionContext));
    }

    public final void A01(boolean z) {
        ((SettingsManager) AnonymousClass0Lh.A03(3, 105, this._UL_mInjectionContext)).setBoolean("enterprise_auto_ota", z);
    }

    @Inject
    public ScheduleUpdate(AbstractC02990bJ r3) {
        this._UL_mInjectionContext = new AnonymousClass0R7(6, r3);
    }
}
