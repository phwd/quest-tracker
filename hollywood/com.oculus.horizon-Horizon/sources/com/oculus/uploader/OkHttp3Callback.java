package com.oculus.uploader;

import X.AbstractC08570wn;
import X.AnonymousClass0Mz;
import X.AnonymousClass1QC;
import X.AnonymousClass1Rj;
import X.C08330wN;
import com.facebook.common.stringformat.StringFormatUtil;
import java.io.IOException;

public class OkHttp3Callback implements AbstractC08570wn {
    public final AnonymousClass1Rj mResponseHandler;

    @Override // X.AbstractC08570wn
    public final void onFailure(AnonymousClass0Mz r13, IOException iOException) {
        AnonymousClass1Rj r3 = this.mResponseHandler;
        C08330wN r2 = r13.A02;
        r3.A00(new AnonymousClass1QC(StringFormatUtil.formatStrLocaleSafe("Request: %s Headers: %s", r2, r2.A02), -1, false, iOException, false, null), "get".equalsIgnoreCase(r2.A01));
    }

    /* JADX WARNING: Removed duplicated region for block: B:100:0x01c9 A[Catch:{ IOException -> 0x01e8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x01cd A[Catch:{ IOException -> 0x01e8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x01d0  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x01a4 A[Catch:{ IOException -> 0x01e8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x01c6 A[Catch:{ IOException -> 0x01e8 }] */
    @Override // X.AbstractC08570wn
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onResponse(X.AnonymousClass0Mz r21, X.C08220wC r22) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 582
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.uploader.OkHttp3Callback.onResponse(X.0Mz, X.0wC):void");
    }

    public OkHttp3Callback(AnonymousClass1Rj r1) {
        this.mResponseHandler = r1;
    }
}
