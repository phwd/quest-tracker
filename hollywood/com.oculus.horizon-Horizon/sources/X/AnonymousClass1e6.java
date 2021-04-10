package X;

import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import com.oculus.deviceconfigclient.DeviceConfigClient;

/* renamed from: X.1e6  reason: invalid class name */
public class AnonymousClass1e6 extends ContentObserver {
    public final /* synthetic */ Uri A00;
    public final /* synthetic */ AnonymousClass1e9 A01;
    public final /* synthetic */ DeviceConfigClient.AnonymousClass1 A02;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass1e6(AnonymousClass1e9 r1, Handler handler, Uri uri, DeviceConfigClient.AnonymousClass1 r4) {
        super(handler);
        this.A01 = r1;
        this.A00 = uri;
        this.A02 = r4;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00fb, code lost:
        if (r0 <= 0) goto L_0x00fd;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onChange(boolean r24, android.net.Uri r25) {
        /*
        // Method dump skipped, instructions count: 445
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1e6.onChange(boolean, android.net.Uri):void");
    }
}
