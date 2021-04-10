package X;

import android.app.Service;
import android.content.Intent;
import android.os.Looper;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
/* renamed from: X.0Yb  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractServiceC01930Yb extends Service {
    public boolean A00;
    public final Object A01 = new Object();
    public volatile HandlerC01920Ya A02;

    public abstract Looper A0C();

    public abstract void A0D();

    public abstract void A0E();

    public abstract void A0F(Intent intent, int i, int i2);

    public final void onStart(Intent intent, int i) {
        onStartCommand(intent, -1, i);
    }

    public final void A0B() {
        synchronized (this.A01) {
            if (!this.A00) {
                A0D();
                this.A00 = true;
            }
        }
    }

    public void onDestroy() {
        this.A02.A00();
        super.onDestroy();
    }

    public final int onStartCommand(Intent intent, int i, int i2) {
        this.A02.A02(intent, i, i2);
        return 1;
    }

    public final void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        A0B();
        A0G(fileDescriptor, printWriter, strArr);
    }

    public void onCreate() {
        HandlerC01920Ya r0;
        super.onCreate();
        Looper A0C = A0C();
        if (A0C == null || A0C == Looper.getMainLooper()) {
            r0 = new HandlerC06110mW(this, Looper.getMainLooper());
        } else {
            r0 = new HandlerC01920Ya(this, A0C);
        }
        this.A02 = r0;
        this.A02.A01();
    }

    public void A0G(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dump(fileDescriptor, printWriter, strArr);
    }
}
