package com.oculus.uploader;

import X.AbstractC08320wM;
import X.AnonymousClass0Lx;
import X.AnonymousClass1Rj;
import X.AnonymousClass1Rl;
import X.AnonymousClass1kE;
import X.AnonymousClass1kF;
import X.AnonymousClass1kI;
import X.C08370wR;
import android.os.SystemClock;
import com.facebook.common.stringformat.StringFormatUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class OkHttp3RequestBody extends AbstractC08320wM {
    public static final Class<?> TAG = OkHttp3RequestBody.class;
    public final AnonymousClass1Rl mRequestBody;
    public final AnonymousClass1Rj mResponseHandler;

    @Override // X.AbstractC08320wM
    public final long A00() {
        return this.mRequestBody.A01.A01.length() - this.mRequestBody.A00;
    }

    @Override // X.AbstractC08320wM
    public final C08370wR A01() {
        return C08370wR.A00(this.mRequestBody.A01.A03);
    }

    @Override // X.AbstractC08320wM
    public final void A02(AnonymousClass0Lx r12) throws IOException {
        AnonymousClass1Rl r2 = this.mRequestBody;
        File file = r2.A01.A01;
        long j = r2.A00;
        if (j != file.length()) {
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                if (fileInputStream.skip(j) == j) {
                    byte[] bArr = new byte[1024];
                    for (int read = fileInputStream.read(bArr); read != -1; read = fileInputStream.read(bArr)) {
                        r12.AA8(bArr, 0, read);
                        AnonymousClass1Rj r1 = this.mResponseHandler;
                        if (r1 instanceof AnonymousClass1kF) {
                            AnonymousClass1kF r13 = (AnonymousClass1kF) r1;
                            AnonymousClass1kE r7 = r13.A02.get();
                            if (r7 != null) {
                                AnonymousClass1kI r6 = r13.A01;
                                long j2 = r6.A00;
                                long uptimeMillis = SystemClock.uptimeMillis();
                                r6.A00 = uptimeMillis;
                                AnonymousClass1kI.A00(r6, (long) read, uptimeMillis - j2);
                                AnonymousClass1kE.A01(r7, read);
                            }
                        }
                    }
                    fileInputStream.close();
                    return;
                }
                throw new IOException(StringFormatUtil.formatStrLocaleSafe("Failed to skip %d bytes", Long.valueOf(j)));
            } catch (Throwable unused) {
            }
        } else {
            return;
        }
        throw th;
    }

    public OkHttp3RequestBody(AnonymousClass1Rl r1, AnonymousClass1Rj r2) {
        this.mRequestBody = r1;
        this.mResponseHandler = r2;
    }
}
