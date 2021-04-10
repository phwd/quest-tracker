package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import org.chromium.base.TraceEvent;

/* renamed from: Hq0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0469Hq0 extends AbstractC0500Ie {
    public Object i = new Object();
    public final /* synthetic */ Context j;
    public final /* synthetic */ C0591Jq0 k;

    public C0469Hq0(C0591Jq0 jq0, Context context) {
        this.k = jq0;
        this.j = context;
    }

    @Override // defpackage.AbstractC2032cb
    public Object c() {
        C0591Jq0 jq0;
        String str;
        C0591Jq0 jq02;
        TraceEvent.l0("OVRServiceManager.requestUserCredentials", 1);
        this.k.c = new ServiceConnectionC0408Gq0(this);
        Context context = this.j;
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.oculus.horizon", "com.oculus.horizon.service.OVRService"));
        if (context.bindService(intent, this.k.c, 1)) {
            try {
                synchronized (this.i) {
                    while (this.k.d == null) {
                        this.i.wait();
                    }
                }
                try {
                    Bundle bundle = new Bundle();
                    bundle.putInt("sdk_major_version", 1);
                    bundle.putInt("sdk_minor_version", 16);
                    bundle.putString("app_id", "1916519981771802");
                    C0591Jq0 jq03 = this.k;
                    if (jq03.f == null) {
                        Bundle c = ((C0225Dq0) jq03.d).c(bundle);
                        this.k.f = c.getString("access_token");
                        jq0 = this.k;
                        str = jq0.f;
                        if (str == null) {
                            Log.e("OVRServiceManager", "AccessToken empty");
                            jq02 = this.k;
                            jq02.c();
                        }
                        jq0.d(str);
                    }
                } catch (Exception e) {
                    Log.e("OVRServiceManager", "Exception", e);
                    this.k.c();
                }
            } catch (InterruptedException e2) {
                Log.e("OVRServiceManager", "Interrupted while waiting for service connection", e2);
                this.k.c();
                Bundle bundle2 = new Bundle();
                bundle2.putInt("sdk_major_version", 1);
                bundle2.putInt("sdk_minor_version", 16);
                bundle2.putString("app_id", "1916519981771802");
                C0591Jq0 jq04 = this.k;
                if (jq04.f == null) {
                    Bundle c2 = ((C0225Dq0) jq04.d).c(bundle2);
                    this.k.f = c2.getString("access_token");
                    jq0 = this.k;
                    str = jq0.f;
                    if (str == null) {
                        Log.e("OVRServiceManager", "AccessToken empty");
                        jq02 = this.k;
                    }
                }
            } catch (Throwable th) {
                try {
                    Bundle bundle3 = new Bundle();
                    bundle3.putInt("sdk_major_version", 1);
                    bundle3.putInt("sdk_minor_version", 16);
                    bundle3.putString("app_id", "1916519981771802");
                    C0591Jq0 jq05 = this.k;
                    if (jq05.f == null) {
                        Bundle c3 = ((C0225Dq0) jq05.d).c(bundle3);
                        this.k.f = c3.getString("access_token");
                        C0591Jq0 jq06 = this.k;
                        String str2 = jq06.f;
                        if (str2 != null) {
                            jq06.d(str2);
                        } else {
                            Log.e("OVRServiceManager", "AccessToken empty");
                            this.k.c();
                        }
                    }
                } catch (Exception e3) {
                    Log.e("OVRServiceManager", "Exception", e3);
                    this.k.c();
                }
                throw th;
            }
        } else {
            Log.e("OVRServiceManager", "Unable to start com.oculus.horizon.service.OVRService");
            this.k.c();
        }
        TraceEvent.g0("OVRServiceManager.requestUserCredentials", 1);
        return Boolean.TRUE;
    }
}
