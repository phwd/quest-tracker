package com.oculus.mediaupload.model;

import java.util.Locale;
import javax.annotation.Nullable;

public class MediaUploaderServiceManagerRequest {
    @Nullable
    public final String mediaId;
    public final MediaUploaderRequest request;
    public final Type type;
    @Nullable
    public final UploadPhase uploadPhase;
    @Nullable
    public final String uploadSessionId;

    public enum Type {
        UPLOAD,
        CHUNKED_UPLOAD
    }

    public enum UploadPhase {
        START,
        TRANSFER,
        FINISH
    }

    /* renamed from: com.oculus.mediaupload.model.MediaUploaderServiceManagerRequest$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$mediaupload$model$MediaUploaderRequest$Type;

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|1|2|3|4|5|6|7|8|10) */
        /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0024 */
        static {
            /*
                com.oculus.mediaupload.model.MediaUploaderRequest$Type[] r0 = com.oculus.mediaupload.model.MediaUploaderRequest.Type.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                com.oculus.mediaupload.model.MediaUploaderServiceManagerRequest.AnonymousClass1.$SwitchMap$com$oculus$mediaupload$model$MediaUploaderRequest$Type = r2
                com.oculus.mediaupload.model.MediaUploaderRequest$Type r0 = com.oculus.mediaupload.model.MediaUploaderRequest.Type.UPLOAD_SCREENSHOT_TO_FACEBOOK     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                com.oculus.mediaupload.model.MediaUploaderRequest$Type r0 = com.oculus.mediaupload.model.MediaUploaderRequest.Type.UPLOAD_SCREENSHOT_TO_OCULUS     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                com.oculus.mediaupload.model.MediaUploaderRequest$Type r0 = com.oculus.mediaupload.model.MediaUploaderRequest.Type.UPLOAD_VIDEOSHOT_TO_FACEBOOK     // Catch:{ NoSuchFieldError -> 0x0024 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0024 }
                r0 = 3
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0024 }
            L_0x0024:
                com.oculus.mediaupload.model.MediaUploaderRequest$Type r0 = com.oculus.mediaupload.model.MediaUploaderRequest.Type.UPLOAD_VIDEOSHOT_TO_OCULUS     // Catch:{ NoSuchFieldError -> 0x002d }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x002d }
                r0 = 4
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x002d }
            L_0x002d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.mediaupload.model.MediaUploaderServiceManagerRequest.AnonymousClass1.<clinit>():void");
        }
    }

    public static MediaUploaderServiceManagerRequest A00(String str, MediaUploaderRequest mediaUploaderRequest, String str2) {
        return new MediaUploaderServiceManagerRequest(mediaUploaderRequest, Type.CHUNKED_UPLOAD, UploadPhase.FINISH, str2, str);
    }

    public static MediaUploaderServiceManagerRequest A01(String str, MediaUploaderRequest mediaUploaderRequest, String str2) {
        return new MediaUploaderServiceManagerRequest(mediaUploaderRequest, Type.CHUNKED_UPLOAD, UploadPhase.TRANSFER, str2, str);
    }

    public final String toString() {
        Locale locale = Locale.US;
        Object[] objArr = new Object[5];
        objArr[0] = "MediaUploaderServiceManagerRequest";
        objArr[1] = this.type.name();
        UploadPhase uploadPhase2 = this.uploadPhase;
        if (uploadPhase2 == null) {
            uploadPhase2 = null;
        }
        objArr[2] = uploadPhase2;
        objArr[3] = this.uploadSessionId;
        objArr[4] = this.mediaId;
        return String.format(locale, "%s[%s, upload_phase = %s, upload_session_id = %s, media_id = %s]", objArr);
    }

    public MediaUploaderServiceManagerRequest(MediaUploaderRequest mediaUploaderRequest, Type type2, @Nullable UploadPhase uploadPhase2, @Nullable String str, @Nullable String str2) {
        this.request = mediaUploaderRequest;
        this.type = type2;
        this.uploadPhase = uploadPhase2;
        this.uploadSessionId = str;
        this.mediaId = str2;
    }
}
