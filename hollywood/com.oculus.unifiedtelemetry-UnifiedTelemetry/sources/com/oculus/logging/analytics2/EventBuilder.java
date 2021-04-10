package com.oculus.logging.analytics2;

import X.AbstractC0247Xu;
import X.QC;
import android.annotation.TargetApi;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.inject.Assisted;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.unifiedtelemetry.unifiedlogging.utils.SettingsManager;
import javax.annotation.Nullable;

@Dependencies({"_UL__ULSEP_com_facebook_analytics2_logger_Analytics2Logger_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_time_Clock_ULSEP_BINDING_ID", "_UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_OculusApiEndpoint_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID", "_UL__ULSEP_com_fasterxml_jackson_databind_ObjectMapper_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_logging_analytics2_EventBuilderConfig_ULSEP_BINDING_ID"})
@TargetApi(23)
@Nullsafe(Nullsafe.Mode.LOCAL)
public class EventBuilder {
    public QC _UL_mInjectionContext;
    public final EventCache mEventCache;
    public final SettingsManager mSettingsManager;
    public final LoggingUser mUser;
    @Nullable
    public String mXrsSessionId = null;

    @Inject
    public EventBuilder(AbstractC0247Xu xu, @Assisted LoggingUser loggingUser, @Assisted EventCache eventCache) {
        this._UL_mInjectionContext = new QC(6, xu);
        this.mUser = loggingUser;
        this.mEventCache = eventCache;
        this.mSettingsManager = new SettingsManager();
    }
}
