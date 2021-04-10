package com.oculus.mediaupload;

import X.AbstractC06640p5;
import X.AnonymousClass0J2;
import X.AnonymousClass0QC;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.logging.utils.EventManager;
import com.oculus.mediaupload.logging.UploadEventFactory;
import com.oculus.mediaupload.logging.VideoUploadOculusFailedEvent;
import com.oculus.mediaupload.model.MediaUploaderResult;
import com.oculus.mediaupload.model.MediaUploaderServiceManagerResult;
import com.oculus.mediaupload.model.NotificationRequest;

@Dependencies({"_UL__ULSEP_com_oculus_mediaupload_api_OculusShareMethods_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_mediaupload_logging_UploadEventFactory_ULSEP_BINDING_ID"})
public class OculusMediaUploaderServiceManager {
    public static final String TAG = "OculusMediaUploaderServiceManager";
    public AnonymousClass0QC _UL_mInjectionContext;

    /* renamed from: com.oculus.mediaupload.OculusMediaUploaderServiceManager$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$mediaupload$model$MediaUploaderServiceManagerRequest$UploadPhase;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001b */
        static {
            /*
                com.oculus.mediaupload.model.MediaUploaderServiceManagerRequest$UploadPhase[] r0 = com.oculus.mediaupload.model.MediaUploaderServiceManagerRequest.UploadPhase.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                com.oculus.mediaupload.OculusMediaUploaderServiceManager.AnonymousClass1.$SwitchMap$com$oculus$mediaupload$model$MediaUploaderServiceManagerRequest$UploadPhase = r2
                com.oculus.mediaupload.model.MediaUploaderServiceManagerRequest$UploadPhase r0 = com.oculus.mediaupload.model.MediaUploaderServiceManagerRequest.UploadPhase.START     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                com.oculus.mediaupload.model.MediaUploaderServiceManagerRequest$UploadPhase r0 = com.oculus.mediaupload.model.MediaUploaderServiceManagerRequest.UploadPhase.TRANSFER     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                com.oculus.mediaupload.model.MediaUploaderServiceManagerRequest$UploadPhase r0 = com.oculus.mediaupload.model.MediaUploaderServiceManagerRequest.UploadPhase.FINISH     // Catch:{ NoSuchFieldError -> 0x0024 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0024 }
                r0 = 3
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0024 }
            L_0x0024:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.mediaupload.OculusMediaUploaderServiceManager.AnonymousClass1.<clinit>():void");
        }
    }

    public static MediaUploaderServiceManagerResult A00(OculusMediaUploaderServiceManager oculusMediaUploaderServiceManager, MediaUploaderResult.MediaUploaderRequestError mediaUploaderRequestError) {
        new VideoUploadOculusFailedEvent((EventManager) AnonymousClass0J2.A03(0, 242, ((UploadEventFactory) AnonymousClass0J2.A03(1, 16, oculusMediaUploaderServiceManager._UL_mInjectionContext))._UL_mInjectionContext), mediaUploaderRequestError).A00();
        return MediaUploaderServiceManagerResult.A01(mediaUploaderRequestError, new NotificationRequest(NotificationRequest.Type.OCULUS_VIDEO_UPLOAD_FAILURE, 0));
    }

    public static MediaUploaderServiceManagerResult A01(OculusMediaUploaderServiceManager oculusMediaUploaderServiceManager, MediaUploaderResult.MediaUploaderRequestError mediaUploaderRequestError, long j, Exception exc) {
        new VideoUploadOculusFailedEvent((EventManager) AnonymousClass0J2.A03(0, 242, ((UploadEventFactory) AnonymousClass0J2.A03(1, 16, oculusMediaUploaderServiceManager._UL_mInjectionContext))._UL_mInjectionContext), j, mediaUploaderRequestError, exc).A00();
        return MediaUploaderServiceManagerResult.A01(mediaUploaderRequestError, new NotificationRequest(NotificationRequest.Type.OCULUS_VIDEO_UPLOAD_FAILURE, 0));
    }

    @Inject
    public OculusMediaUploaderServiceManager(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(2, r3);
    }
}
