package X;

import android.content.ComponentName;
import android.content.Intent;
import com.facebook.proxygen.LigerHttpResponseHandler;

/* renamed from: X.17  reason: invalid class name */
public abstract class AnonymousClass17 {
    public int A00;
    public boolean A01;
    public final ComponentName A02;

    public void A02(Intent intent) {
        dX dXVar = (dX) this;
        Intent intent2 = new Intent(intent);
        intent2.setComponent(((AnonymousClass17) dXVar).A02);
        if (dXVar.A02.startService(intent2) != null) {
            synchronized (dXVar) {
                if (!dXVar.A00) {
                    dXVar.A00 = true;
                    if (!dXVar.A01) {
                        dXVar.A03.acquire(LigerHttpResponseHandler.MAX_WAIT_TIME_MILLIS);
                    }
                }
            }
        }
    }

    public final void A00() {
        if (this instanceof dX) {
            dX dXVar = (dX) this;
            synchronized (dXVar) {
                if (dXVar.A01) {
                    if (dXVar.A00) {
                        dXVar.A03.acquire(LigerHttpResponseHandler.MAX_WAIT_TIME_MILLIS);
                    }
                    dXVar.A01 = false;
                    dXVar.A04.release();
                }
            }
        }
    }

    public final void A01() {
        if (!this.A01) {
            this.A01 = true;
            this.A00 = 1000;
            return;
        }
        int i = this.A00;
        if (i != 1000) {
            throw new IllegalArgumentException(AnonymousClass08.A02("Given job ID ", 1000, " is different than previous ", i));
        }
    }

    public AnonymousClass17(ComponentName componentName) {
        this.A02 = componentName;
    }
}
