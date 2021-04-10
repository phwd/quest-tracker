package X;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public final class QM {
    public static QM A02;
    public static final Lock A03 = new ReentrantLock();
    public final SharedPreferences A00;
    public final Lock A01 = new ReentrantLock();

    public QM(Context context) {
        this.A00 = context.getSharedPreferences("com.google.android.gms.signin", 0);
    }
}
