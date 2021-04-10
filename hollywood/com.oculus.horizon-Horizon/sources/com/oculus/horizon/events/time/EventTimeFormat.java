package com.oculus.horizon.events.time;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import com.facebook.inject.UnsafeContextInjection;
import com.facebook.ultralight.Dependencies;
import com.oculus.time.Clock;
import com.oculus.time.ForOculusTimeZone;
import java.text.DateFormat;
import java.util.Locale;
import java.util.TimeZone;
import javax.inject.Inject;
import javax.inject.Provider;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_time_Clock_ULSEP_BINDING_ID", "_UL__ULSEP_java_util_Locale_ULSEP_BINDING_ID", "_UL__ULSEP_java_util_TimeZone_ULSEP_com_oculus_time_ForOculusTimeZone_ULSEP_BINDING_ID"})
public class EventTimeFormat {
    public static final int EVENING_HOUR_THRESHOLD = 18;
    public static final int LIVE_REMAINING_HOURS_THRESHOLD = 24;
    public static final String MONTH_DAY_FORMAT = "MMM d";
    public static final String WEEKDAY_FORMAT = "EEEE";
    public final Clock mClock;
    public final Context mContext;
    public final Provider<Locale> mLocaleProvider;
    public final Provider<TimeZone> mTimeZoneProvider;

    @VisibleForTesting
    public static final String A00(EventTimeFormat eventTimeFormat, long j) {
        return DateFormat.getTimeInstance(3, eventTimeFormat.mLocaleProvider.get()).format(Long.valueOf(j)).replace("PM", "pm").replace("AM", "am");
    }

    @Inject
    public EventTimeFormat(@UnsafeContextInjection Context context, Clock clock, Provider<Locale> provider, @ForOculusTimeZone Provider<TimeZone> provider2) {
        this.mContext = context;
        this.mClock = clock;
        this.mLocaleProvider = provider;
        this.mTimeZoneProvider = provider2;
    }
}
