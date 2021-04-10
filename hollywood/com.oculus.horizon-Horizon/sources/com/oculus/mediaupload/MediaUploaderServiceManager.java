package com.oculus.mediaupload;

import X.AbstractC06640p5;
import X.AnonymousClass0QC;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;

@Dependencies({"_UL__ULSEP_com_oculus_mediaupload_FacebookMediaUploaderServiceManager_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_mediaupload_OculusMediaUploaderServiceManager_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_mediaupload_api_FacebookShareMethods_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_mediaupload_api_OculusShareMethods_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_mediaupload_logging_UploadEventFactory_ULSEP_BINDING_ID"})
public class MediaUploaderServiceManager {
    public static final String TAG = "MediaUploaderServiceManager";
    public AnonymousClass0QC _UL_mInjectionContext;

    /* renamed from: com.oculus.mediaupload.MediaUploaderServiceManager$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$mediaupload$model$MediaUploaderRequest$Type;
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$mediaupload$model$MediaUploaderServiceManagerRequest$Type;

        /* JADX WARNING: Can't wrap try/catch for region: R(15:0|(2:1|2)|3|5|6|7|8|9|10|11|13|14|15|16|18) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x001b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0024 */
        static {
            /*
                com.oculus.mediaupload.model.MediaUploaderRequest$Type[] r0 = com.oculus.mediaupload.model.MediaUploaderRequest.Type.values()
                int r0 = r0.length
                int[] r4 = new int[r0]
                com.oculus.mediaupload.MediaUploaderServiceManager.AnonymousClass1.$SwitchMap$com$oculus$mediaupload$model$MediaUploaderRequest$Type = r4
                r3 = 1
                com.oculus.mediaupload.model.MediaUploaderRequest$Type r0 = com.oculus.mediaupload.model.MediaUploaderRequest.Type.UPLOAD_SCREENSHOT_TO_OCULUS     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r0 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r4[r0] = r3     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r2 = 2
                com.oculus.mediaupload.model.MediaUploaderRequest$Type r0 = com.oculus.mediaupload.model.MediaUploaderRequest.Type.UPLOAD_SCREENSHOT_TO_FACEBOOK     // Catch:{ NoSuchFieldError -> 0x001b }
                int r0 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r4[r0] = r2     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                com.oculus.mediaupload.model.MediaUploaderRequest$Type r0 = com.oculus.mediaupload.model.MediaUploaderRequest.Type.UPLOAD_VIDEOSHOT_TO_FACEBOOK     // Catch:{ NoSuchFieldError -> 0x0024 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0024 }
                r0 = 3
                r4[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0024 }
            L_0x0024:
                com.oculus.mediaupload.model.MediaUploaderRequest$Type r0 = com.oculus.mediaupload.model.MediaUploaderRequest.Type.UPLOAD_VIDEOSHOT_TO_OCULUS     // Catch:{ NoSuchFieldError -> 0x002d }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x002d }
                r0 = 4
                r4[r1] = r0     // Catch:{ NoSuchFieldError -> 0x002d }
            L_0x002d:
                com.oculus.mediaupload.model.MediaUploaderServiceManagerRequest$Type[] r0 = com.oculus.mediaupload.model.MediaUploaderServiceManagerRequest.Type.values()
                int r0 = r0.length
                int[] r1 = new int[r0]
                com.oculus.mediaupload.MediaUploaderServiceManager.AnonymousClass1.$SwitchMap$com$oculus$mediaupload$model$MediaUploaderServiceManagerRequest$Type = r1
                com.oculus.mediaupload.model.MediaUploaderServiceManagerRequest$Type r0 = com.oculus.mediaupload.model.MediaUploaderServiceManagerRequest.Type.UPLOAD     // Catch:{ NoSuchFieldError -> 0x003e }
                int r0 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r1[r0] = r3     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                com.oculus.mediaupload.model.MediaUploaderServiceManagerRequest$Type r0 = com.oculus.mediaupload.model.MediaUploaderServiceManagerRequest.Type.CHUNKED_UPLOAD     // Catch:{ NoSuchFieldError -> 0x0046 }
                int r0 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0046 }
                r1[r0] = r2     // Catch:{ NoSuchFieldError -> 0x0046 }
            L_0x0046:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.mediaupload.MediaUploaderServiceManager.AnonymousClass1.<clinit>():void");
        }
    }

    @Inject
    public MediaUploaderServiceManager(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(5, r3);
    }
}
