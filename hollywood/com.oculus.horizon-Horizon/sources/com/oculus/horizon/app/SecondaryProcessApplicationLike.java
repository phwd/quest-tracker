package com.oculus.horizon.app;

import X.AnonymousClass006;
import android.app.Application;
import com.facebook.acra.ACRA;
import com.facebook.acra.ErrorReporter;
import com.facebook.acra.config.DefaultAcraConfig;
import com.facebook.acra.constants.ErrorReportingConstants;
import com.oculus.base.app.ApplicationLike;
import com.oculus.util.constants.OculusConstants;

public class SecondaryProcessApplicationLike implements ApplicationLike {
    @Override // com.oculus.base.app.ApplicationLike
    public final void A5v(Application application) {
        ACRA.init(new DefaultAcraConfig(application, AnonymousClass006.A05("https://www.facebook.com/mobile/generic_android_crash_logs/", OculusConstants.HORIZON_APP_ID), false));
        ErrorReporter.putCustomData(ErrorReportingConstants.APP_NAME_KEY, OculusConstants.HORIZON_APP_NAME);
        ErrorReporter.putCustomData("fb_app_id", OculusConstants.HORIZON_APP_ID);
    }
}
