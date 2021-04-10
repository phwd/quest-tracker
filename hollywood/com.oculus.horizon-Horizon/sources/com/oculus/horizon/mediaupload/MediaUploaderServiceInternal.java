package com.oculus.horizon.mediaupload;

import X.AbstractC06640p5;
import X.AnonymousClass03W;
import X.AnonymousClass0J2;
import X.AnonymousClass0QC;
import android.content.Context;
import android.content.Intent;

public class MediaUploaderServiceInternal extends AnonymousClass03W {
    public static final int JOB_ID = 1000;
    public static final String TAG = "MediaUploaderServiceInternal";
    public AnonymousClass0QC _UL_mInjectionContext;

    public static final void _UL_staticInjectMe(AbstractC06640p5 r2, MediaUploaderServiceInternal mediaUploaderServiceInternal) {
        mediaUploaderServiceInternal._UL_mInjectionContext = new AnonymousClass0QC(3, r2);
    }

    public static void enqueueWork(Context context, Intent intent) {
        AnonymousClass03W.enqueueWork(context, MediaUploaderServiceInternal.class, 1000, intent);
    }

    public static final void _UL_injectMe(Context context, MediaUploaderServiceInternal mediaUploaderServiceInternal) {
        _UL_staticInjectMe(AnonymousClass0J2.get(context), mediaUploaderServiceInternal);
    }

    @Override // X.AnonymousClass03W
    public void onCreate() {
        super.onCreate();
        _UL_injectMe(this, this);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(8:4|5|(1:7)(2:8|(1:10)(3:11|12|13))|14|15|(4:53|(6:55|(1:57)(3:58|(2:60|61)|64)|65|66|67|(1:69))|490|524)(2:19|(2:21|(2:23|(2:25|(2:27|(2:29|(4:34|(4:38|(1:42)|492|519)|44|(1:49)(4:48|73|74|75))(1:33))(2:77|520))(2:78|513))(2:80|521))(2:81|522))(2:50|(1:52)(2:82|523)))|70|(6:72|83|(9:86|87|88|89|508|(7:93|94|96|(11:98|99|100|101|(2:104|102)|509|105|106|107|(1:109)|110)|113|(1:118)|119)|507|487|84)|504|488|489)(1:511)) */
    /* JADX WARNING: Code restructure failed: missing block: B:345:0x0a44, code lost:
        if (r1.client_mutation_id != null) goto L_0x0b39;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:368:0x0b37, code lost:
        if (((com.oculus.mediaupload.api.FacebookShareGraphQLResponse) new com.oculus.mediaupload.api.FacebookShareMethods.AnonymousClass5(r3, r10, r30, r7, r11, r15).A00()).client_mutation_id != null) goto L_0x0b39;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00b7, code lost:
        if (r0 != false) goto L_0x00b9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:493:0x1002, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:526:?, code lost:
        throw new com.oculus.mediaupload.model.MediaUploaderException(r0.error);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x0065 */
    /* JADX WARNING: Removed duplicated region for block: B:213:0x062f  */
    /* JADX WARNING: Removed duplicated region for block: B:216:0x0637  */
    /* JADX WARNING: Removed duplicated region for block: B:223:0x0645  */
    /* JADX WARNING: Removed duplicated region for block: B:224:0x064e  */
    /* JADX WARNING: Removed duplicated region for block: B:301:0x08bb  */
    /* JADX WARNING: Removed duplicated region for block: B:302:0x08bd  */
    /* JADX WARNING: Removed duplicated region for block: B:372:0x0b3f  */
    /* JADX WARNING: Removed duplicated region for block: B:373:0x0b62  */
    /* JADX WARNING: Removed duplicated region for block: B:376:0x0b99  */
    /* JADX WARNING: Removed duplicated region for block: B:377:0x0b9b  */
    /* JADX WARNING: Removed duplicated region for block: B:418:0x0d17  */
    /* JADX WARNING: Removed duplicated region for block: B:422:0x0d3e  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00e3 A[Catch:{ MediaUploaderIntentException -> 0x1002 }] */
    @Override // X.AnonymousClass03W
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onHandleWork(android.content.Intent r39) {
        /*
        // Method dump skipped, instructions count: 4254
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.mediaupload.MediaUploaderServiceInternal.onHandleWork(android.content.Intent):void");
    }
}
