package com.oculus.horizon.cloudstorage2;

import X.AbstractC06640p5;
import X.AnonymousClass0J2;
import X.AnonymousClass0QC;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.logging.utils.Event;
import com.oculus.logging.utils.EventManager;

@Dependencies({"_UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID"})
public class CloudStorageLogger {
    public static final String EVENT_CLOUD_STORAGE_END = "oculus_mobile_cloud_storage_end";
    public static final String EVENT_CLOUD_STORAGE_PROGRESS = "oculus_mobile_cloud_storage_progress";
    public static final String EVENT_CLOUD_STORAGE_SERVICE = "oculus_mobile_cloud_storage_service";
    public static final String EVENT_CLOUD_STORAGE_SERVICE_ERROR = "oculus_mobile_cloud_storage_service_error";
    public static final String EVENT_CLOUD_STORAGE_START = "oculus_mobile_cloud_storage_start";
    public static final String KEY_APP_ITEM_ID = "app_item_id";
    public static final String KEY_APP_PACKAGE_NAME = "app_package_name";
    public static final String KEY_OPERATION_ID = "operation_id";
    public static final String KEY_OPERATION_RESULT = "result";
    public static final String KEY_OPERATION_TYPE = "operation_type";
    public static final String SERVICE_OPERATION_BACKGROUND_UPLOAD = "background_upload";
    public static final String SERVICE_OPERATION_LAUNCH_SYNC = "launch_sync";
    public static final String SERVICE_OPERATION_SCHEDULE_UPLOAD = "schedule_upload";
    public AnonymousClass0QC _UL_mInjectionContext;

    public final void A00(String str, String str2) {
        Event A22 = ((EventManager) AnonymousClass0J2.A03(0, 242, this._UL_mInjectionContext)).A22(EVENT_CLOUD_STORAGE_SERVICE);
        A22.A15(KEY_OPERATION_TYPE, str2);
        A22.A15(KEY_APP_ITEM_ID, str);
        A22.A5L();
    }

    @Inject
    public CloudStorageLogger(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(1, r3);
    }
}
