package com.oculus.mediaupload;

import X.AbstractC06640p5;
import X.AnonymousClass0J2;
import X.AnonymousClass0QC;
import X.AnonymousClass0Rg;
import android.content.Context;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.logging.utils.EventManager;
import com.oculus.mediaupload.io.MediaUploaderDB;
import com.oculus.mediaupload.logging.ScreenshotUploadFBFailedEvent;
import com.oculus.mediaupload.logging.UploadEventFactory;
import com.oculus.mediaupload.logging.VideoUploadFBFailedEvent;
import com.oculus.mediaupload.model.FacebookShareRequest;
import com.oculus.mediaupload.model.MediaMetadata;
import com.oculus.mediaupload.model.MediaUploaderResult;
import com.oculus.mediaupload.model.MediaUploaderServiceManagerResult;
import com.oculus.mediaupload.model.NotificationRequest;
import javax.annotation.Nullable;

@Dependencies({"_UL__ULSEP_com_oculus_mediaupload_api_FacebookShareMethods_ULSEP_BINDING_ID", "_UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_mediaupload_logging_UploadEventFactory_ULSEP_BINDING_ID"})
@Nullsafe(Nullsafe.Mode.LOCAL)
public class FacebookMediaUploaderServiceManager {
    public static final String TAG = "FacebookMediaUploaderServiceManager";
    public AnonymousClass0QC _UL_mInjectionContext;

    /* renamed from: com.oculus.mediaupload.FacebookMediaUploaderServiceManager$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$mediaupload$model$FacebookShareRequest$FacebookShareType;
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$mediaupload$model$MediaUploaderServiceManagerRequest$UploadPhase;

        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|(2:1|2)|3|(2:5|6)|7|9|10|11|12|(2:13|14)|15|17|18|19|20|(3:21|22|24)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(19:0|(2:1|2)|3|(2:5|6)|7|9|10|11|12|13|14|15|17|18|19|20|21|22|24) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0024 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x002d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0047 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x004f */
        static {
            /*
                com.oculus.mediaupload.model.FacebookShareRequest$FacebookShareType[] r0 = com.oculus.mediaupload.model.FacebookShareRequest.FacebookShareType.values()
                int r0 = r0.length
                int[] r5 = new int[r0]
                com.oculus.mediaupload.FacebookMediaUploaderServiceManager.AnonymousClass1.$SwitchMap$com$oculus$mediaupload$model$FacebookShareRequest$FacebookShareType = r5
                r4 = 1
                com.oculus.mediaupload.model.FacebookShareRequest$FacebookShareType r0 = com.oculus.mediaupload.model.FacebookShareRequest.FacebookShareType.MESSENGER_THREAD     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r0 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r5[r0] = r4     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r3 = 2
                com.oculus.mediaupload.model.FacebookShareRequest$FacebookShareType r0 = com.oculus.mediaupload.model.FacebookShareRequest.FacebookShareType.TIMELINE     // Catch:{ NoSuchFieldError -> 0x001b }
                int r0 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r5[r0] = r3     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                r2 = 3
                com.oculus.mediaupload.model.FacebookShareRequest$FacebookShareType r0 = com.oculus.mediaupload.model.FacebookShareRequest.FacebookShareType.GROUP     // Catch:{ NoSuchFieldError -> 0x0024 }
                int r0 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0024 }
                r5[r0] = r2     // Catch:{ NoSuchFieldError -> 0x0024 }
            L_0x0024:
                com.oculus.mediaupload.model.FacebookShareRequest$FacebookShareType r0 = com.oculus.mediaupload.model.FacebookShareRequest.FacebookShareType.STORY     // Catch:{ NoSuchFieldError -> 0x002d }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x002d }
                r0 = 4
                r5[r1] = r0     // Catch:{ NoSuchFieldError -> 0x002d }
            L_0x002d:
                com.oculus.mediaupload.model.FacebookShareRequest$FacebookShareType r0 = com.oculus.mediaupload.model.FacebookShareRequest.FacebookShareType.SYNC     // Catch:{ NoSuchFieldError -> 0x0036 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0036 }
                r0 = 5
                r5[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0036 }
            L_0x0036:
                com.oculus.mediaupload.model.MediaUploaderServiceManagerRequest$UploadPhase[] r0 = com.oculus.mediaupload.model.MediaUploaderServiceManagerRequest.UploadPhase.values()
                int r0 = r0.length
                int[] r1 = new int[r0]
                com.oculus.mediaupload.FacebookMediaUploaderServiceManager.AnonymousClass1.$SwitchMap$com$oculus$mediaupload$model$MediaUploaderServiceManagerRequest$UploadPhase = r1
                com.oculus.mediaupload.model.MediaUploaderServiceManagerRequest$UploadPhase r0 = com.oculus.mediaupload.model.MediaUploaderServiceManagerRequest.UploadPhase.START     // Catch:{ NoSuchFieldError -> 0x0047 }
                int r0 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0047 }
                r1[r0] = r4     // Catch:{ NoSuchFieldError -> 0x0047 }
            L_0x0047:
                com.oculus.mediaupload.model.MediaUploaderServiceManagerRequest$UploadPhase r0 = com.oculus.mediaupload.model.MediaUploaderServiceManagerRequest.UploadPhase.FINISH     // Catch:{ NoSuchFieldError -> 0x004f }
                int r0 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x004f }
                r1[r0] = r3     // Catch:{ NoSuchFieldError -> 0x004f }
            L_0x004f:
                com.oculus.mediaupload.model.MediaUploaderServiceManagerRequest$UploadPhase r0 = com.oculus.mediaupload.model.MediaUploaderServiceManagerRequest.UploadPhase.TRANSFER     // Catch:{ NoSuchFieldError -> 0x0057 }
                int r0 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0057 }
                r1[r0] = r2     // Catch:{ NoSuchFieldError -> 0x0057 }
            L_0x0057:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.mediaupload.FacebookMediaUploaderServiceManager.AnonymousClass1.<clinit>():void");
        }
    }

    @Nullable
    public static MediaMetadata A00(FacebookMediaUploaderServiceManager facebookMediaUploaderServiceManager, Context context, String str) {
        if (!((AnonymousClass0Rg) AnonymousClass0J2.A03(1, 399, facebookMediaUploaderServiceManager._UL_mInjectionContext)).A36(36310306355413018L)) {
            return null;
        }
        return MediaUploaderDB.A00(context, str);
    }

    public static MediaUploaderServiceManagerResult A02(FacebookMediaUploaderServiceManager facebookMediaUploaderServiceManager, MediaUploaderResult.MediaUploaderRequestError mediaUploaderRequestError, long j, FacebookShareRequest.FacebookShareType facebookShareType) {
        NotificationRequest.Type type;
        new VideoUploadFBFailedEvent((EventManager) AnonymousClass0J2.A03(0, 242, ((UploadEventFactory) AnonymousClass0J2.A03(2, 16, facebookMediaUploaderServiceManager._UL_mInjectionContext))._UL_mInjectionContext), j, facebookShareType, mediaUploaderRequestError).A00();
        if (facebookShareType == FacebookShareRequest.FacebookShareType.MESSENGER_THREAD) {
            type = NotificationRequest.Type.MESSENGER_VIDEO_UPLOAD_FAILURE;
        } else {
            type = NotificationRequest.Type.FACEBOOK_VIDEO_UPLOAD_FAILURE;
        }
        return MediaUploaderServiceManagerResult.A01(mediaUploaderRequestError, new NotificationRequest(type, 0));
    }

    public static MediaUploaderServiceManagerResult A03(FacebookMediaUploaderServiceManager facebookMediaUploaderServiceManager, MediaUploaderResult.MediaUploaderRequestError mediaUploaderRequestError, long j, FacebookShareRequest.FacebookShareType facebookShareType, Exception exc) {
        NotificationRequest.Type type;
        new VideoUploadFBFailedEvent((EventManager) AnonymousClass0J2.A03(0, 242, ((UploadEventFactory) AnonymousClass0J2.A03(2, 16, facebookMediaUploaderServiceManager._UL_mInjectionContext))._UL_mInjectionContext), j, facebookShareType, mediaUploaderRequestError, exc).A00();
        if (facebookShareType == FacebookShareRequest.FacebookShareType.MESSENGER_THREAD) {
            type = NotificationRequest.Type.MESSENGER_VIDEO_UPLOAD_FAILURE;
        } else {
            type = NotificationRequest.Type.FACEBOOK_VIDEO_UPLOAD_FAILURE;
        }
        return MediaUploaderServiceManagerResult.A01(mediaUploaderRequestError, new NotificationRequest(type, 0));
    }

    public static MediaUploaderServiceManagerResult A04(FacebookMediaUploaderServiceManager facebookMediaUploaderServiceManager, @Nullable MediaUploaderResult.MediaUploaderRequestError mediaUploaderRequestError, FacebookShareRequest.FacebookShareType facebookShareType) {
        NotificationRequest.Type type;
        new ScreenshotUploadFBFailedEvent((EventManager) AnonymousClass0J2.A03(0, 242, ((UploadEventFactory) AnonymousClass0J2.A03(2, 16, facebookMediaUploaderServiceManager._UL_mInjectionContext))._UL_mInjectionContext), mediaUploaderRequestError, facebookShareType).A00();
        if (facebookShareType == FacebookShareRequest.FacebookShareType.MESSENGER_THREAD) {
            type = NotificationRequest.Type.MESSENGER_SCREENSHOT_UPLOAD_FAILURE;
        } else {
            type = NotificationRequest.Type.FACEBOOK_SCREENSHOT_UPLOAD_FAILURE;
        }
        return MediaUploaderServiceManagerResult.A01(mediaUploaderRequestError, new NotificationRequest(type, 0));
    }

    public static MediaUploaderServiceManagerResult A05(FacebookMediaUploaderServiceManager facebookMediaUploaderServiceManager, MediaUploaderResult.MediaUploaderRequestError mediaUploaderRequestError, FacebookShareRequest.FacebookShareType facebookShareType) {
        NotificationRequest.Type type;
        new VideoUploadFBFailedEvent((EventManager) AnonymousClass0J2.A03(0, 242, ((UploadEventFactory) AnonymousClass0J2.A03(2, 16, facebookMediaUploaderServiceManager._UL_mInjectionContext))._UL_mInjectionContext), 0, facebookShareType, mediaUploaderRequestError).A00();
        if (facebookShareType == FacebookShareRequest.FacebookShareType.MESSENGER_THREAD) {
            type = NotificationRequest.Type.MESSENGER_VIDEO_UPLOAD_FAILURE;
        } else {
            type = NotificationRequest.Type.FACEBOOK_VIDEO_UPLOAD_FAILURE;
        }
        return MediaUploaderServiceManagerResult.A01(mediaUploaderRequestError, new NotificationRequest(type, 0));
    }

    @Inject
    public FacebookMediaUploaderServiceManager(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(3, r3);
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x011c  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0191  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.oculus.mediaupload.model.MediaUploaderServiceManagerResult A01(com.oculus.mediaupload.FacebookMediaUploaderServiceManager r18, android.content.Context r19, java.lang.String r20, com.oculus.mediaupload.model.FacebookShareRequest r21, java.io.File r22, com.oculus.mediaupload.model.MediaUploaderRequest r23, java.lang.String r24) throws com.oculus.mediaupload.model.MediaUploaderException {
        /*
        // Method dump skipped, instructions count: 424
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.mediaupload.FacebookMediaUploaderServiceManager.A01(com.oculus.mediaupload.FacebookMediaUploaderServiceManager, android.content.Context, java.lang.String, com.oculus.mediaupload.model.FacebookShareRequest, java.io.File, com.oculus.mediaupload.model.MediaUploaderRequest, java.lang.String):com.oculus.mediaupload.model.MediaUploaderServiceManagerResult");
    }
}
