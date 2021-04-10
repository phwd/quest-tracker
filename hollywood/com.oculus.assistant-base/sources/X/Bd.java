package X;

import android.content.Context;
import com.google.common.collect.ArrayListMultimap;
import java.util.HashMap;
import java.util.Map;

public final class Bd {
    public static Bd A06;
    public static final Object A07 = new Object();
    public final AnonymousClass3Q A00;
    public final UK A01 = new ArrayListMultimap();
    public final Map A02 = new HashMap();
    public final Map A03 = new HashMap();
    public final Map A04 = new HashMap();
    public final Context A05;

    public static Bd A00(Context context) {
        Bd bd;
        synchronized (A07) {
            bd = A06;
            if (bd == null) {
                bd = new Bd(context.getApplicationContext());
                A06 = bd;
            }
        }
        return bd;
    }

    public Bd(Context context) {
        AnonymousClass3Q r1;
        this.A05 = context;
        synchronized (AnonymousClass3Q.A06) {
            r1 = AnonymousClass3Q.A05;
            if (r1 == null) {
                r1 = new AnonymousClass3Q(context.getApplicationContext());
                AnonymousClass3Q.A05 = r1;
            }
        }
        this.A00 = r1;
    }
}
