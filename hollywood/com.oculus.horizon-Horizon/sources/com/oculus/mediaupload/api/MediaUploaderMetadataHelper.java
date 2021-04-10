package com.oculus.mediaupload.api;

import X.AbstractC06640p5;
import X.AnonymousClass0QC;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.library.model.App;
import com.oculus.libraryapi.OVRLibrary;
import com.oculus.libraryapi.OVRLibraryModule;
import java.io.File;

@Dependencies({"_UL__ULSEP_com_oculus_libraryapi_OVRLibrary_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_mediaupload_api_OculusShareMethods_ULSEP_BINDING_ID", "_UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_BINDING_ID"})
public class MediaUploaderMetadataHelper {
    public static final String TAG = "MediaUploaderMetadataHelper";
    public AnonymousClass0QC _UL_mInjectionContext;
    @Inject
    @Eager
    public final OVRLibrary mOVRLibrary;

    @Inject
    public MediaUploaderMetadataHelper(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(2, r3);
        this.mOVRLibrary = OVRLibraryModule.A00(r3);
    }

    public final String A00(File file) {
        App A01;
        String str;
        String name = file.getName();
        int indexOf = name.indexOf(45);
        if (indexOf < 0 || (A01 = this.mOVRLibrary.A01(name.substring(0, indexOf))) == null || (str = A01.id) == null) {
            return "0";
        }
        return str;
    }
}
