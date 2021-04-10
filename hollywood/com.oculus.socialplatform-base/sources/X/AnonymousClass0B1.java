package X;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.os.Handler;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.HashMap;

@Deprecated
/* renamed from: X.0B1  reason: invalid class name */
public final class AnonymousClass0B1 {
    public static AnonymousClass0B1 A05;
    public static final Object A06 = new Object();
    public final ArrayList<AnonymousClass0Az> A00 = new ArrayList<>();
    public final HashMap<String, ArrayList<AnonymousClass0B0>> A01 = new HashMap<>();
    public final HashMap<BroadcastReceiver, ArrayList<AnonymousClass0B0>> A02 = new HashMap<>();
    public final Context A03;
    public final Handler A04;

    @NonNull
    public static AnonymousClass0B1 A00(@NonNull Context context) {
        AnonymousClass0B1 r1;
        synchronized (A06) {
            r1 = A05;
            if (r1 == null) {
                r1 = new AnonymousClass0B1(context.getApplicationContext());
                A05 = r1;
            }
        }
        return r1;
    }

    public AnonymousClass0B1(Context context) {
        this.A03 = context;
        this.A04 = new HandlerC00500Ay(this, context.getMainLooper());
    }
}
