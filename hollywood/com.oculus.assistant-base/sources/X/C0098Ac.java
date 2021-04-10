package X;

import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

/* renamed from: X.Ac  reason: case insensitive filesystem */
public final class C0098Ac {
    public final Handler A00;
    public final String A01;
    public final Map A02 = Collections.synchronizedMap(new HashMap());

    public final synchronized String A00(String str, Object obj) {
        if (obj == null) {
            str = null;
        } else if (str == null) {
            str = UUID.randomUUID().toString();
            A00(str, obj);
        } else {
            this.A02.put(str, obj);
        }
        return str;
    }

    public final synchronized void A01(String str) {
        if (str != null) {
            this.A02.remove(str);
        }
    }

    public final synchronized boolean A02(AbstractC0096Aa aa) {
        boolean z;
        boolean z2;
        z = false;
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.A02.entrySet());
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String str = (String) entry.getKey();
            try {
                z2 = aa.A4w(str, entry.getValue());
            } catch (DeadObjectException unused) {
                Log.w(this.A01, AnonymousClass08.A05("Client ", str, " lost connection or died. Unregistering for future requests"));
                A01(str);
            } catch (RemoteException e) {
                Log.e(this.A01, e.getMessage(), e);
            } catch (Exception e2) {
                Log.e(this.A01, AnonymousClass08.A04("Client threw an exception. Catching it here to prevent service crash. Error: ", e2.getMessage()), e2);
            }
            z |= z2;
        }
        return z;
        z2 = false;
        z |= z2;
    }

    public C0098Ac(String str) {
        Handler handler = new Handler(Looper.getMainLooper());
        this.A00 = handler;
        this.A01 = str;
    }
}
