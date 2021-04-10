package com.oculus.mediaupload.model;

import X.AbstractC06640p5;
import X.AnonymousClass0QC;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import com.facebook.common.stringformat.StringFormatUtil;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.ipc.common.ParcelableCallbackReceiver;
import com.oculus.mediaupload.io.FacebookGamingProfileGetTokenIntentHelper;
import com.oculus.mediaupload.io.FacebookGamingProfileTokenManager;
import com.oculus.mediaupload.io.FileUtils;
import com.oculus.mediaupload.io.MediaUploaderDB;
import com.oculus.mediaupload.io.MediaUploaderNotifications;
import com.oculus.mediaupload.model.FacebookShareRequest;
import com.oculus.mediaupload.model.MediaUploaderRequest;
import com.oculus.mediaupload.model.MediaUploaderResult;
import com.oculus.mediaupload.model.NotificationRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

@Dependencies({"_UL__ULSEP_com_oculus_mediaupload_io_FacebookGamingProfileTokenManager_ULSEP_BINDING_ID"})
public class MediaUploaderIntentValidator {
    public static final String REQUEST_KEY = "request";
    public static final String REQUEST_TYPE_KEY = "request_type";
    public static final String RESULT_RECEIVER_KEY = "result_receiver";
    public AnonymousClass0QC _UL_mInjectionContext;

    public enum Type {
        SYNC_TO_OCULUS,
        SYNC_TO_FB_USER_INITIATED,
        SYNC_TO_FB,
        SINGLE_REQUEST
    }

    /* renamed from: com.oculus.mediaupload.model.MediaUploaderIntentValidator$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$mediaupload$model$MediaUploaderIntentValidator$Type;

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
                com.oculus.mediaupload.model.MediaUploaderIntentValidator$Type[] r0 = com.oculus.mediaupload.model.MediaUploaderIntentValidator.Type.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                com.oculus.mediaupload.model.MediaUploaderIntentValidator.AnonymousClass1.$SwitchMap$com$oculus$mediaupload$model$MediaUploaderIntentValidator$Type = r2
                com.oculus.mediaupload.model.MediaUploaderIntentValidator$Type r0 = com.oculus.mediaupload.model.MediaUploaderIntentValidator.Type.SYNC_TO_FB     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                com.oculus.mediaupload.model.MediaUploaderIntentValidator$Type r0 = com.oculus.mediaupload.model.MediaUploaderIntentValidator.Type.SYNC_TO_FB_USER_INITIATED     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                com.oculus.mediaupload.model.MediaUploaderIntentValidator$Type r0 = com.oculus.mediaupload.model.MediaUploaderIntentValidator.Type.SYNC_TO_OCULUS     // Catch:{ NoSuchFieldError -> 0x0024 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0024 }
                r0 = 3
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0024 }
            L_0x0024:
                com.oculus.mediaupload.model.MediaUploaderIntentValidator$Type r0 = com.oculus.mediaupload.model.MediaUploaderIntentValidator.Type.SINGLE_REQUEST     // Catch:{ NoSuchFieldError -> 0x002d }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x002d }
                r0 = 4
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x002d }
            L_0x002d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.mediaupload.model.MediaUploaderIntentValidator.AnonymousClass1.<clinit>():void");
        }
    }

    public static class EnabledJobType {
        public final boolean syncToFBGamingProfile;
        public final boolean syncToOculus;

        public EnabledJobType(boolean z, boolean z2) {
            this.syncToOculus = z;
            this.syncToFBGamingProfile = z2;
        }
    }

    public static class MediaUploaderIntent {
        public final ParcelableCallbackReceiver<MediaUploaderCallback, MediaUploaderResult> receiver;
        public final MediaUploaderRequest[] requests;
        public final Type type;

        public MediaUploaderIntent(MediaUploaderRequest[] mediaUploaderRequestArr, ParcelableCallbackReceiver<MediaUploaderCallback, MediaUploaderResult> parcelableCallbackReceiver, Type type2) {
            this.requests = mediaUploaderRequestArr;
            this.receiver = parcelableCallbackReceiver;
            this.type = type2;
        }
    }

    public static class MediaUploaderIntentException extends Throwable {
        public final MediaUploaderResult.MediaUploaderRequestError error;

        public MediaUploaderIntentException(MediaUploaderResult.MediaUploaderRequestError mediaUploaderRequestError) {
            this.error = mediaUploaderRequestError;
        }
    }

    public static ArrayList<MediaUploaderRequest> A01(HashSet<String> hashSet, boolean z, Type type) throws MediaUploaderIntentException {
        MediaUploaderRequest.Type type2;
        FacebookShareRequest facebookShareRequest;
        ArrayList<MediaUploaderRequest> arrayList = new ArrayList<>();
        Iterator<String> it = hashSet.iterator();
        while (it.hasNext()) {
            String next = it.next();
            switch (type.ordinal()) {
                case 0:
                    if (z) {
                        type2 = MediaUploaderRequest.Type.UPLOAD_VIDEOSHOT_TO_OCULUS;
                    } else {
                        type2 = MediaUploaderRequest.Type.UPLOAD_SCREENSHOT_TO_OCULUS;
                    }
                    facebookShareRequest = null;
                    break;
                case 1:
                case 2:
                    if (z) {
                        type2 = MediaUploaderRequest.Type.UPLOAD_VIDEOSHOT_TO_FACEBOOK;
                    } else {
                        type2 = MediaUploaderRequest.Type.UPLOAD_SCREENSHOT_TO_FACEBOOK;
                    }
                    facebookShareRequest = new FacebookShareRequest(FacebookShareRequest.FacebookShareType.SYNC);
                    break;
                default:
                    throw new MediaUploaderIntentException(MediaUploaderResult.MediaUploaderRequestError.UNKNOWN_TYPE);
            }
            arrayList.add(new MediaUploaderRequest(type2, next, facebookShareRequest));
        }
        return arrayList;
    }

    @Inject
    public MediaUploaderIntentValidator(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(1, r3);
    }

    public static final MediaUploaderIntent A00(Intent intent, Context context, MediaUploaderDB.Type type, Type type2) throws MediaUploaderIntentException {
        MediaUploaderResult.MediaUploaderRequestError mediaUploaderRequestError;
        Parcelable parcelable;
        HashSet<String> A03 = MediaUploaderDB.A03(MediaUploaderDB.A01(context, type));
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        Bundle extras = intent.getExtras();
        if (extras == null || (parcelable = extras.getParcelable("result_receiver")) == null) {
            mediaUploaderRequestError = MediaUploaderResult.MediaUploaderRequestError.MEDIA_UPLOADER_RESULT_RECEIVER_MISSING;
        } else {
            ParcelableCallbackReceiver parcelableCallbackReceiver = new ParcelableCallbackReceiver(parcelable);
            HashSet<String> A00 = FileUtils.A00(A03, new File(StringFormatUtil.formatStrLocaleSafe("%s/Oculus/Screenshots", externalStorageDirectory)));
            HashSet<String> A002 = FileUtils.A00(A03, new File(StringFormatUtil.formatStrLocaleSafe("%s/Oculus/VideoShots", externalStorageDirectory)));
            ArrayList<MediaUploaderRequest> A01 = A01(A00, false, type2);
            A01.addAll(A01(A002, true, type2));
            MediaUploaderRequest[] mediaUploaderRequestArr = (MediaUploaderRequest[]) A01.toArray(new MediaUploaderRequest[A01.size()]);
            if (type2 != Type.SYNC_TO_FB && type2 != Type.SYNC_TO_FB_USER_INITIATED) {
                return new MediaUploaderIntent(mediaUploaderRequestArr, parcelableCallbackReceiver, type2);
            }
            boolean z = false;
            if (type2 == Type.SYNC_TO_FB_USER_INITIATED) {
                z = true;
            }
            try {
                throw new FacebookGamingProfileTokenManager.TokenValidationException(FacebookGamingProfileTokenManager.TokenValidationError.TOKEN_IS_MISSING);
            } catch (FacebookGamingProfileTokenManager.TokenValidationException unused) {
                if (z) {
                    context.sendBroadcast(FacebookGamingProfileGetTokenIntentHelper.A00());
                } else {
                    MediaUploaderNotifications.MediaUploaderNotification A012 = MediaUploaderNotifications.A01(context, new NotificationRequest(NotificationRequest.Type.REQUEST_FACEBOOK_GAMING_LOGIN, 0), new MediaUploaderBatchInfo(1, 1), null);
                    if (A012 != null) {
                        ((NotificationManager) context.getSystemService("notification")).notify(A012.notificationId, A012.builder.A01());
                    }
                }
                mediaUploaderRequestError = MediaUploaderResult.MediaUploaderRequestError.FACEBOOK_SYNC_SHARE_ACCESS_TOKEN_MISSING;
            }
        }
        throw new MediaUploaderIntentException(mediaUploaderRequestError);
    }
}
