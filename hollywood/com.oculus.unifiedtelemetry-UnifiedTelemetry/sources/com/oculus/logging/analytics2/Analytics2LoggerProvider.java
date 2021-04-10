package com.oculus.logging.analytics2;

import X.AbstractC0274Yy;
import X.Fd;
import X.Fe;
import X.IF;
import X.SU;
import X.ZC;
import android.content.Context;
import com.facebook.analytics2.logger.EventListener;
import com.facebook.inject.ApplicationScoped;
import com.facebook.inject.ForAppContext;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import java.util.Iterator;
import java.util.Set;
import javax.annotation.Nullable;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID", "_UL__ULSEP_java_util_Set_ULLT_com_facebook_analytics2_logger_EventListener_ULGT__ULSEP_com_oculus_logging_analytics2_NormalPriEventListener_ULSEP_BINDING_ID", "_UL__ULSEP_java_util_Set_ULLT_com_facebook_analytics2_logger_EventListener_ULGT__ULSEP_com_oculus_logging_analytics2_HighPriEventListener_ULSEP_BINDING_ID", "_UL__ULSEP_com_facebook_pigeon_common_protocol_DeviceIdProvider_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_logging_analytics2_SessionManagerImpl_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_logging_analytics2_AppInfoProviderImpl_ULSEP_BINDING_ID"})
@ApplicationScoped
public class Analytics2LoggerProvider {
    public static volatile Analytics2LoggerProvider _UL__ULSEP_com_oculus_logging_analytics2_Analytics2LoggerProvider_ULSEP_INSTANCE;
    public Fe mAnalytics2Logger;

    @Inject
    public Analytics2LoggerProvider(@ForAppContext Context context, @Nullable @NormalPriEventListener Set<EventListener> set, @HighPriEventListener @Nullable Set<EventListener> set2, SU su, SessionManagerImpl sessionManagerImpl, AppInfoProviderImpl appInfoProviderImpl) {
        IF r3 = new IF();
        if (set != null) {
            Iterator<EventListener> it = set.iterator();
            while (it.hasNext()) {
                r3.A02((AbstractC0274Yy) it.next());
            }
        }
        IF r2 = new IF();
        if (set2 != null) {
            Iterator<EventListener> it2 = set2.iterator();
            while (it2.hasNext()) {
                r2.A02((AbstractC0274Yy) it2.next());
            }
        }
        Fd fd = new Fd(context);
        fd.A03 = r3;
        fd.A02 = r2;
        fd.A04 = sessionManagerImpl;
        fd.A00 = new ZC();
        fd.A05 = appInfoProviderImpl;
        fd.A06 = su;
        fd.A07 = AnalyticsUploaderImpl.class;
        fd.A01 = sessionManagerImpl;
        this.mAnalytics2Logger = new Fe(fd);
    }
}
