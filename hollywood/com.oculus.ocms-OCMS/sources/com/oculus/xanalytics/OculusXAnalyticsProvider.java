package com.oculus.xanalytics;

import com.facebook.inject.FbInjector;
import com.facebook.inject.InjectionContext;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.Lazy;
import com.facebook.inject.UltralightLazy;
import com.facebook.inject.UltralightProvider;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.facebook.ultralight.UL;
import com.facebook.xanalytics.XAnalyticsAdapter;
import com.facebook.xanalytics.XAnalyticsAdapterHolder;
import com.facebook.xanalytics.XAnalyticsHolder;
import com.facebook.xanalytics.XAnalyticsProvider;
import com.oculus.logging.utils.Event;
import com.oculus.logging.utils.EventManager;
import com.oculus.logging.utils.UtilsModule;
import com.oculus.xanalytics.OculusXAnalyticsModule;
import javax.inject.Provider;

@Dependencies({"_UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID"})
public class OculusXAnalyticsProvider implements XAnalyticsProvider {
    private InjectionContext _UL_mInjectionContext;
    private final XAnalyticsAdapterHolder mXAnalyticsAdapter = new XAnalyticsAdapterHolder(new XAnalyticsAdapter() {
        /* class com.oculus.xanalytics.OculusXAnalyticsProvider.AnonymousClass1 */

        @Override // com.facebook.xanalytics.XAnalyticsAdapter
        public void cleanup() {
        }

        @Override // com.facebook.xanalytics.XAnalyticsAdapter
        public void flush() {
        }

        @Override // com.facebook.xanalytics.XAnalyticsAdapter
        public String getStructureSamplingConfig(String str) {
            return "";
        }

        @Override // com.facebook.xanalytics.XAnalyticsAdapter
        public void logCounter(String str, double d) {
        }

        @Override // com.facebook.xanalytics.XAnalyticsAdapter
        public void logCounter(String str, double d, String str2) {
        }

        @Override // com.facebook.xanalytics.XAnalyticsAdapter
        public boolean shouldLog(String str) {
            return true;
        }

        @Override // com.facebook.xanalytics.XAnalyticsAdapter
        public void logEvent(String str, String str2, String str3, boolean z, double d) {
            ((EventManager) FbInjector.lazyInstance(0, UtilsModule.UL_id._UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID, OculusXAnalyticsProvider.this._UL_mInjectionContext)).createEvent(str, str2, z).logAndRelease();
        }

        @Override // com.facebook.xanalytics.XAnalyticsAdapter
        public void logEventBypassSampling(String str, String str2) {
            Event createEvent = ((EventManager) FbInjector.lazyInstance(0, UtilsModule.UL_id._UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID, OculusXAnalyticsProvider.this._UL_mInjectionContext)).createEvent(str, str2, false);
            createEvent.setXplatTagForInternalUse();
            createEvent.logAndRelease();
        }
    });

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_xanalytics_OculusXAnalyticsProvider_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightLazy.get(OculusXAnalyticsModule.UL_id._UL__ULSEP_com_oculus_xanalytics_OculusXAnalyticsProvider_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_xanalytics_OculusXAnalyticsProvider_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightProvider.get(OculusXAnalyticsModule.UL_id._UL__ULSEP_com_oculus_xanalytics_OculusXAnalyticsProvider_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final OculusXAnalyticsProvider _UL__ULSEP_com_oculus_xanalytics_OculusXAnalyticsProvider_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (OculusXAnalyticsProvider) UL.factorymap.get(OculusXAnalyticsModule.UL_id._UL__ULSEP_com_oculus_xanalytics_OculusXAnalyticsProvider_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final OculusXAnalyticsProvider _UL__ULSEP_com_oculus_xanalytics_OculusXAnalyticsProvider_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return new OculusXAnalyticsProvider(injectorLike);
    }

    @Inject
    OculusXAnalyticsProvider(InjectorLike injectorLike) {
        this._UL_mInjectionContext = new InjectionContext(1, injectorLike);
    }

    @Override // com.facebook.xanalytics.XAnalyticsProvider
    public XAnalyticsHolder getXAnalyticsNative() {
        return this.mXAnalyticsAdapter;
    }
}
