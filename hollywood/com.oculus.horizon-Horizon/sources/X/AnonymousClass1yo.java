package X;

import android.os.Handler;

/* renamed from: X.1yo  reason: invalid class name */
public class AnonymousClass1yo implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.cameracore.audio.encoder.LegacyAudioEncoderImpl$1";
    public final /* synthetic */ Handler A00;
    public final /* synthetic */ C11401ym A01;
    public final /* synthetic */ AbstractC11131xk A02;

    public AnonymousClass1yo(C11401ym r1, AbstractC11131xk r2, Handler handler) {
        this.A01 = r1;
        this.A02 = r2;
        this.A00 = handler;
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x0032 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r6 = this;
            X.1ym r4 = r6.A01
            X.1xk r3 = r6.A02
            android.os.Handler r2 = r6.A00
            java.lang.String r5 = "audio/mp4a-latm"
            java.lang.Integer r1 = r4.A06
            java.lang.Integer r0 = X.AnonymousClass007.A00
            if (r1 == r0) goto L_0x0023
            java.lang.String r1 = "Must only call prepare() on a stopped AudioEncoder. Current state is: "
            java.lang.Integer r0 = r4.A06
            java.lang.String r0 = X.AnonymousClass1z1.A00(r0)
            java.lang.String r1 = X.AnonymousClass006.A05(r1, r0)
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r1)
            X.AnonymousClass1z6.A01(r3, r2, r0)
            return
        L_0x0023:
            X.1yh r1 = r4.A03     // Catch:{ Exception -> 0x0032 }
            r0 = 0
            android.media.MediaFormat r1 = X.C11401ym.A00(r1, r0)     // Catch:{ Exception -> 0x0032 }
            r0 = 0
            android.media.MediaCodec r0 = X.AnonymousClass1x5.A00(r5, r1, r0)     // Catch:{ Exception -> 0x0032 }
            r4.A01 = r0     // Catch:{ Exception -> 0x0032 }
            goto L_0x0040
        L_0x0032:
            X.1yh r1 = r4.A03     // Catch:{ Exception -> 0x0048 }
            r0 = 1
            android.media.MediaFormat r1 = X.C11401ym.A00(r1, r0)     // Catch:{ Exception -> 0x0048 }
            r0 = 0
            android.media.MediaCodec r0 = X.AnonymousClass1x5.A00(r5, r1, r0)     // Catch:{ Exception -> 0x0048 }
            r4.A01 = r0     // Catch:{ Exception -> 0x0048 }
        L_0x0040:
            java.lang.Integer r0 = X.AnonymousClass007.A01
            r4.A06 = r0
            X.AnonymousClass1z6.A00(r3, r2)
            return
        L_0x0048:
            r0 = move-exception
            X.AnonymousClass1z6.A01(r3, r2, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1yo.run():void");
    }
}
