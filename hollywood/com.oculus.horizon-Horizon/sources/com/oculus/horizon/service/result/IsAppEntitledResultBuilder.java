package com.oculus.horizon.service.result;

import X.AbstractC06640p5;
import android.os.Bundle;
import android.os.RemoteException;
import com.facebook.inject.Assisted;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.horizon.service.OVRService;
import com.oculus.horizon.service.ServiceLogger;
import com.oculus.libraryapi.OVRLibrary;
import com.oculus.libraryapi.OVRLibraryModule;
import com.oculus.platform.aidl.RemoteConstants;

@Dependencies({"_UL__ULSEP_com_oculus_horizon_service_ServiceLogger_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_libraryapi_OVRLibrary_ULSEP_BINDING_ID"})
public class IsAppEntitledResultBuilder extends ResultBuilderNoBundle<RemoteConstants.EntitlementResult> {
    public static final Class<?> TAG = IsAppEntitledResultBuilder.class;
    @Inject
    @Eager
    public OVRLibrary mLibrary;
    @Inject
    @Eager
    public ServiceLogger mLogger;

    @Inject
    public IsAppEntitledResultBuilder(AbstractC06640p5 r2, @Assisted OVRService oVRService) {
        super(oVRService);
        this.mLogger = ServiceLogger._UL__ULSEP_com_oculus_horizon_service_ServiceLogger_ULSEP_ACCESS_METHOD(r2);
        this.mLibrary = OVRLibraryModule.A00(r2);
    }

    /* renamed from: A01 */
    public final RemoteConstants.EntitlementResult A00(Bundle bundle) {
        try {
            RemoteConstants.EntitlementResult entitlementResult = (RemoteConstants.EntitlementResult) super.A00(bundle);
            if (entitlementResult == null) {
                return RemoteConstants.EntitlementResult.NOT_ENTITLED;
            }
            return entitlementResult;
        } catch (RemoteException unused) {
            return RemoteConstants.EntitlementResult.NOT_ENTITLED;
        }
    }
}
