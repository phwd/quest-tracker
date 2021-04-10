package defpackage;

import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.util.SparseArray;

/* renamed from: kL0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C3366kL0 implements IBinder.DeathRecipient {

    /* renamed from: a  reason: collision with root package name */
    public final Messenger f10273a;
    public final HandlerC3879nL0 b;
    public final Messenger c;
    public int d = 1;
    public int e = 1;
    public int f;
    public int g;
    public final SparseArray h = new SparseArray();
    public final /* synthetic */ ServiceConnectionC4562rL0 i;

    public C3366kL0(ServiceConnectionC4562rL0 rl0, Messenger messenger) {
        this.i = rl0;
        this.f10273a = messenger;
        HandlerC3879nL0 nl0 = new HandlerC3879nL0(this);
        this.b = nl0;
        this.c = new Messenger(nl0);
    }

    public void a(int i2) {
        int i3 = this.d;
        this.d = i3 + 1;
        b(5, i3, i2, null, null);
    }

    public final boolean b(int i2, int i3, int i4, Object obj, Bundle bundle) {
        Message obtain = Message.obtain();
        obtain.what = i2;
        obtain.arg1 = i3;
        obtain.arg2 = i4;
        obtain.obj = obj;
        obtain.setData(bundle);
        obtain.replyTo = this.c;
        try {
            this.f10273a.send(obtain);
            return true;
        } catch (DeadObjectException unused) {
            return false;
        } catch (RemoteException e2) {
            if (i2 == 2) {
                return false;
            }
            Log.e("MediaRouteProviderProxy", "Could not send message to service.", e2);
            return false;
        }
    }

    public void binderDied() {
        this.i.j.post(new RunnableC3195jL0(this));
    }

    public void c(C1052Rf0 rf0) {
        int i2 = this.d;
        this.d = i2 + 1;
        b(10, i2, 0, rf0 != null ? rf0.f8844a : null, null);
    }

    public void d(int i2, int i3) {
        Bundle bundle = new Bundle();
        bundle.putInt("volume", i3);
        int i4 = this.d;
        this.d = i4 + 1;
        b(7, i4, i2, null, bundle);
    }

    public void e(int i2, int i3) {
        Bundle bundle = new Bundle();
        bundle.putInt("unselectReason", i3);
        int i4 = this.d;
        this.d = i4 + 1;
        b(6, i4, i2, null, bundle);
    }

    public void f(int i2, int i3) {
        Bundle bundle = new Bundle();
        bundle.putInt("volume", i3);
        int i4 = this.d;
        this.d = i4 + 1;
        b(8, i4, i2, null, bundle);
    }
}
