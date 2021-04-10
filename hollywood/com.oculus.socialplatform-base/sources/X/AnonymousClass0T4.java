package X;

import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import com.oculus.deviceconfigclient.DeviceConfigClient;

/* renamed from: X.0T4  reason: invalid class name */
public class AnonymousClass0T4 extends ContentObserver {
    public final /* synthetic */ Uri A00;
    public final /* synthetic */ AnonymousClass0T8 A01;
    public final /* synthetic */ DeviceConfigClient.AnonymousClass1 A02;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass0T4(AnonymousClass0T8 r1, Handler handler, Uri uri, DeviceConfigClient.AnonymousClass1 r4) {
        super(handler);
        this.A01 = r1;
        this.A00 = uri;
        this.A02 = r4;
    }

    public final void onChange(boolean z, Uri uri) {
        AnonymousClass0T8 r2 = this.A01;
        super.onChange(z);
        if (uri.equals(this.A00)) {
            this.A02.onMobileConfigSubscribeSuccess();
            r2.A02.A00("subscribe", "", true, "");
            return;
        }
        String[] split = uri.getPath().split("/");
        this.A02.onMobileConfigSubscribeFailure(split[1]);
        r2.A02.A00("subscribe", "", false, split[1]);
    }
}
