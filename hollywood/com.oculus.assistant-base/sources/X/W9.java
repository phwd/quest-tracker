package X;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import com.facebook.debug.tracer.Tracer;
import com.oculus.aidl.IAuthService2;
import java.util.concurrent.atomic.AtomicBoolean;

public final class W9 {
    public IAuthService2 A00;
    public ZC A01;
    public final Context A02;
    public final ServiceConnection A03 = new W7(this);
    public final W8 A04;
    public final AtomicBoolean A05 = new AtomicBoolean(false);

    public final ZC A00() {
        Tracer.A01("getCredentials");
        try {
            IAuthService2 iAuthService2 = this.A00;
            if (iAuthService2 != null && this.A01 == null) {
                try {
                    Bundle credentials = iAuthService2.getCredentials();
                    ZC zc = null;
                    if (credentials == null) {
                        C0139Dd.A0D("AuthServiceClient", "null bundle received from OVRAuth");
                        this.A01 = null;
                        return null;
                    }
                    String string = credentials.getString("user_id", null);
                    String string2 = credentials.getString("access_token", null);
                    if (TextUtils.isEmpty(string) || TextUtils.isEmpty(string2)) {
                        C0139Dd.A0D("AuthServiceClient", "Received invalid uid or access token from OVRAuth");
                    } else {
                        zc = new ZC(string, string2);
                    }
                    this.A01 = zc;
                } catch (RemoteException e) {
                    C0139Dd.A0L("AuthServiceClient", "RemoteException", e);
                }
            }
            ZC zc2 = this.A01;
            Tracer.A00();
            return zc2;
        } finally {
            Tracer.A00();
        }
    }

    public final void A01() {
        AtomicBoolean atomicBoolean = this.A05;
        if (!atomicBoolean.get() && this.A00 == null) {
            atomicBoolean.set(true);
            try {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("com.oculus.horizon", "com.oculus.auth.service.AuthService2"));
                this.A02.bindService(intent, this.A03, 1);
            } catch (SecurityException e) {
                C0139Dd.A0L("AuthServiceClient", "Failed to bind to AuthService", e);
            }
        }
    }

    public W9(Context context, W8 w8) {
        this.A02 = context;
        this.A04 = w8;
    }
}
