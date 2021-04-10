package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Looper;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.util.SparseArray;
import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

/* renamed from: FI1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class FI1 implements ServiceConnection {

    /* renamed from: a  reason: collision with root package name */
    public int f8008a = 0;
    public final Messenger b = new Messenger(new HandlerC3356kH1(Looper.getMainLooper(), new LI1(this)));
    public C3021iJ1 c;
    public final Queue d = new ArrayDeque();
    public final SparseArray e = new SparseArray();
    public final /* synthetic */ C4896tI1 f;

    public FI1(C4896tI1 ti1, AI1 ai1) {
        this.f = ti1;
    }

    public final synchronized void a(int i, String str) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String valueOf = String.valueOf(str);
            if (valueOf.length() != 0) {
                "Disconnected: ".concat(valueOf);
            } else {
                new String("Disconnected: ");
            }
        }
        int i2 = this.f8008a;
        if (i2 == 0) {
            throw new IllegalStateException();
        } else if (i2 == 1 || i2 == 2) {
            Log.isLoggable("MessengerIpcClient", 2);
            this.f8008a = 4;
            C1030Qx a2 = C1030Qx.a();
            Context context = this.f.f11335a;
            Objects.requireNonNull(a2);
            context.unbindService(this);
            C5218vC1 vc1 = new C5218vC1(i, str);
            for (C5898zC1 zc1 : this.d) {
                zc1.a(vc1);
            }
            this.d.clear();
            for (int i3 = 0; i3 < this.e.size(); i3++) {
                ((C5898zC1) this.e.valueAt(i3)).a(vc1);
            }
            this.e.clear();
        } else if (i2 == 3) {
            this.f8008a = 4;
        } else if (i2 != 4) {
            int i4 = this.f8008a;
            StringBuilder sb = new StringBuilder(26);
            sb.append("Unknown state: ");
            sb.append(i4);
            throw new IllegalStateException(sb.toString());
        }
    }

    public final synchronized boolean b(C5898zC1 zc1) {
        int i = this.f8008a;
        if (i == 0) {
            this.d.add(zc1);
            SE0.j(this.f8008a == 0);
            this.f8008a = 1;
            Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
            intent.setPackage("com.google.android.gms");
            C1030Qx a2 = C1030Qx.a();
            Context context = this.f.f11335a;
            Objects.requireNonNull(a2);
            context.getClass().getName();
            if (!a2.b(context, intent, this, 1)) {
                a(0, "Unable to bind to service");
            } else {
                this.f.b.schedule(new RI1(this), 30, TimeUnit.SECONDS);
            }
            return true;
        } else if (i == 1) {
            this.d.add(zc1);
            return true;
        } else if (i != 2) {
            if (i != 3) {
                if (i != 4) {
                    int i2 = this.f8008a;
                    StringBuilder sb = new StringBuilder(26);
                    sb.append("Unknown state: ");
                    sb.append(i2);
                    throw new IllegalStateException(sb.toString());
                }
            }
            return false;
        } else {
            this.d.add(zc1);
            this.f.b.execute(new XI1(this));
            return true;
        }
    }

    public final synchronized void c() {
        if (this.f8008a == 2 && this.d.isEmpty() && this.e.size() == 0) {
            Log.isLoggable("MessengerIpcClient", 2);
            this.f8008a = 3;
            C1030Qx a2 = C1030Qx.a();
            Context context = this.f.f11335a;
            Objects.requireNonNull(a2);
            context.unbindService(this);
        }
    }

    public final synchronized void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (iBinder == null) {
            a(0, "Null service connection");
            return;
        }
        try {
            this.c = new C3021iJ1(iBinder);
            this.f8008a = 2;
            this.f.b.execute(new XI1(this));
        } catch (RemoteException e2) {
            a(0, e2.getMessage());
        }
    }

    public final synchronized void onServiceDisconnected(ComponentName componentName) {
        Log.isLoggable("MessengerIpcClient", 2);
        a(2, "Service disconnected");
    }
}
