package defpackage;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

/* renamed from: lo1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AsyncTaskC3610lo1 extends AsyncTask {

    /* renamed from: a  reason: collision with root package name */
    public final Context f10375a;
    public final Intent b;
    public final ServiceConnectionC0786Mx c;

    public AsyncTaskC3610lo1(Context context, Intent intent, ServiceConnectionC0786Mx mx) {
        this.f10375a = context.getApplicationContext();
        this.b = intent;
        this.c = mx;
    }

    @Override // android.os.AsyncTask
    public Object doInBackground(Object[] objArr) {
        Void[] voidArr = (Void[]) objArr;
        try {
            if (this.f10375a.bindService(this.b, this.c, 4097)) {
                return null;
            }
            this.f10375a.unbindService(this.c);
            return new IllegalStateException("Could not bind to the service");
        } catch (SecurityException e) {
            Log.w("TWAConnectionPool", "SecurityException while binding.", e);
            return e;
        }
    }

    @Override // android.os.AsyncTask
    public void onPostExecute(Object obj) {
        Exception exc = (Exception) obj;
        if (exc != null) {
            ServiceConnectionC0786Mx mx = this.c;
            for (C1250Ul ul : mx.e) {
                boolean z = true;
                ul.d = true;
                C1433Xl xl = ul.b;
                if (xl == null || !xl.G.j(exc)) {
                    z = false;
                }
                if (z) {
                    ul.f9047a = null;
                    ul.b = null;
                    ul.c = null;
                }
            }
            mx.e.clear();
            mx.f8513a.run();
            mx.c = 3;
            mx.f = exc;
        }
    }
}
