package defpackage;

import J.N;
import android.net.Uri;
import java.io.File;
import java.io.IOException;
import org.chromium.base.ContextUtils;
import org.chromium.media.MediaPlayerBridge;

/* renamed from: Ge0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0379Ge0 extends AbstractC2032cb {
    public final String i;
    public File j;
    public final /* synthetic */ MediaPlayerBridge k;

    public C0379Ge0(MediaPlayerBridge mediaPlayerBridge, String str) {
        this.k = mediaPlayerBridge;
        this.i = str;
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0044 */
    @Override // defpackage.AbstractC2032cb
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object c() {
        /*
            r7 = this;
            r0 = 0
            java.lang.String r1 = "decoded"
            java.lang.String r2 = "mediadata"
            java.io.File r1 = java.io.File.createTempFile(r1, r2)     // Catch:{ IOException -> 0x0044 }
            r7.j = r1     // Catch:{ IOException -> 0x0044 }
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0044 }
            java.io.File r2 = r7.j     // Catch:{ IOException -> 0x0044 }
            r1.<init>(r2)     // Catch:{ IOException -> 0x0044 }
            java.io.ByteArrayInputStream r0 = new java.io.ByteArrayInputStream     // Catch:{ IOException -> 0x003d, all -> 0x003b }
            java.lang.String r2 = r7.i     // Catch:{ IOException -> 0x003d, all -> 0x003b }
            byte[] r2 = defpackage.AbstractC3153j7.b(r2)     // Catch:{ IOException -> 0x003d, all -> 0x003b }
            r0.<init>(r2)     // Catch:{ IOException -> 0x003d, all -> 0x003b }
            android.util.Base64InputStream r2 = new android.util.Base64InputStream     // Catch:{ IOException -> 0x003d, all -> 0x003b }
            r3 = 0
            r2.<init>(r0, r3)     // Catch:{ IOException -> 0x003d, all -> 0x003b }
            r0 = 1024(0x400, float:1.435E-42)
            byte[] r0 = new byte[r0]     // Catch:{ IOException -> 0x003d, all -> 0x003b }
        L_0x0027:
            int r4 = r2.read(r0)     // Catch:{ IOException -> 0x003d, all -> 0x003b }
            r5 = -1
            if (r4 == r5) goto L_0x0032
            r1.write(r0, r3, r4)     // Catch:{ IOException -> 0x003d, all -> 0x003b }
            goto L_0x0027
        L_0x0032:
            r2.close()     // Catch:{ IOException -> 0x003d, all -> 0x003b }
            java.lang.Boolean r0 = java.lang.Boolean.TRUE     // Catch:{ IOException -> 0x003d, all -> 0x003b }
            defpackage.O21.a(r1)
            goto L_0x004a
        L_0x003b:
            r0 = move-exception
            goto L_0x004b
        L_0x003d:
            r0 = r1
            goto L_0x0044
        L_0x003f:
            r1 = move-exception
            r6 = r1
            r1 = r0
            r0 = r6
            goto L_0x004b
        L_0x0044:
            java.lang.Boolean r1 = java.lang.Boolean.FALSE     // Catch:{ all -> 0x003f }
            defpackage.O21.a(r0)
            r0 = r1
        L_0x004a:
            return r0
        L_0x004b:
            defpackage.O21.a(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C0379Ge0.c():java.lang.Object");
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        Boolean bool = (Boolean) obj;
        if (h()) {
            m();
            return;
        }
        if (bool.booleanValue()) {
            try {
                this.k.b().setDataSource(ContextUtils.getApplicationContext(), Uri.fromFile(this.j));
            } catch (IOException unused) {
                bool = Boolean.FALSE;
            }
        }
        m();
        MediaPlayerBridge mediaPlayerBridge = this.k;
        N.Mo4Rd8TE(mediaPlayerBridge.c, mediaPlayerBridge, bool.booleanValue());
    }

    public final void m() {
        File file = this.j;
        if (file != null && !file.delete()) {
            StringBuilder i2 = AbstractC2531fV.i("Failed to delete temporary file: ");
            i2.append(this.j);
            AbstractC1220Ua0.a("media", i2.toString(), new Object[0]);
        }
    }
}
