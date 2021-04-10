package defpackage;

import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;

/* renamed from: We0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1354We0 extends AbstractC0385Gg0 {

    /* renamed from: a  reason: collision with root package name */
    public final String f9161a;
    public final C1293Ve0 b;

    public C1354We0(C1537Ze0 ze0, String str, C1293Ve0 ve0) {
        this.f9161a = str;
        this.b = ve0;
    }

    @Override // defpackage.AbstractC0385Gg0
    public void f(int i) {
        C1293Ve0 ve0;
        String str = this.f9161a;
        if (str != null && (ve0 = this.b) != null) {
            int andIncrement = ve0.l.getAndIncrement();
            Message obtain = Message.obtain();
            obtain.what = 7;
            obtain.arg1 = andIncrement;
            Bundle bundle = new Bundle();
            bundle.putInt("volume", i);
            bundle.putString("routeId", str);
            obtain.setData(bundle);
            obtain.replyTo = ve0.i;
            try {
                ve0.h.send(obtain);
            } catch (DeadObjectException unused) {
            } catch (RemoteException e) {
                Log.e("MR2Provider", "Could not send control request to service.", e);
            }
        }
    }

    @Override // defpackage.AbstractC0385Gg0
    public void i(int i) {
        C1293Ve0 ve0;
        String str = this.f9161a;
        if (str != null && (ve0 = this.b) != null) {
            int andIncrement = ve0.l.getAndIncrement();
            Message obtain = Message.obtain();
            obtain.what = 8;
            obtain.arg1 = andIncrement;
            Bundle bundle = new Bundle();
            bundle.putInt("volume", i);
            bundle.putString("routeId", str);
            obtain.setData(bundle);
            obtain.replyTo = ve0.i;
            try {
                ve0.h.send(obtain);
            } catch (DeadObjectException unused) {
            } catch (RemoteException e) {
                Log.e("MR2Provider", "Could not send control request to service.", e);
            }
        }
    }
}
