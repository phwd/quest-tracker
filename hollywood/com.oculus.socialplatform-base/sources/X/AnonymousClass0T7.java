package X;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import com.oculus.deviceconfigclient.DeviceConfigClient;

/* renamed from: X.0T7  reason: invalid class name */
public class AnonymousClass0T7 extends ContentObserver {
    public final DeviceConfigClient.ChangeListener A00;
    public final String A01;

    public AnonymousClass0T7(String str, DeviceConfigClient.ChangeListener changeListener, Context context) {
        super(new Handler(context.getMainLooper()));
        this.A01 = str;
        this.A00 = changeListener;
    }

    public final void onChange(boolean z, Uri uri) {
        super.onChange(z);
        this.A00.onConfigChanged(this.A01);
    }
}
