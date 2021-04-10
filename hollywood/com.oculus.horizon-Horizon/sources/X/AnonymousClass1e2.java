package X;

import android.content.Context;
import com.facebook.internal.Utility;
import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.List;

/* renamed from: X.1e2  reason: invalid class name */
public class AnonymousClass1e2 {
    public static Object A02 = new Object();
    public Context A00;
    public HashMap<AnonymousClass1gV, List<C09401dq>> A01 = new HashMap<>();

    public static AnonymousClass1e2 A00(Context context) {
        AnonymousClass1e2 r5;
        Throwable th;
        Exception e;
        synchronized (A02) {
            r5 = new AnonymousClass1e2(context);
            ObjectInputStream objectInputStream = null;
            try {
                ObjectInputStream objectInputStream2 = new ObjectInputStream(new BufferedInputStream(r5.A00.openFileInput("AppEventsLogger.persistedevents")));
                try {
                    r5.A00.getFileStreamPath("AppEventsLogger.persistedevents").delete();
                    r5.A01 = (HashMap) objectInputStream2.readObject();
                    Utility.closeQuietly(objectInputStream2);
                } catch (FileNotFoundException unused) {
                    objectInputStream = objectInputStream2;
                    Utility.closeQuietly(objectInputStream);
                    return r5;
                } catch (Exception e2) {
                    e = e2;
                    objectInputStream = objectInputStream2;
                    try {
                        e.toString();
                        Utility.closeQuietly(objectInputStream);
                        return r5;
                    } catch (Throwable th2) {
                        th = th2;
                        Utility.closeQuietly(objectInputStream);
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    objectInputStream = objectInputStream2;
                    Utility.closeQuietly(objectInputStream);
                    throw th;
                }
            } catch (FileNotFoundException unused2) {
                Utility.closeQuietly(objectInputStream);
                return r5;
            } catch (Exception e3) {
                e = e3;
                e.toString();
                Utility.closeQuietly(objectInputStream);
                return r5;
            }
        }
        return r5;
    }

    public AnonymousClass1e2(Context context) {
        this.A00 = context;
    }
}
