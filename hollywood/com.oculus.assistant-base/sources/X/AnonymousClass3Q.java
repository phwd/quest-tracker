package X;

import android.content.Context;
import android.os.Handler;
import java.util.ArrayList;
import java.util.HashMap;

/* renamed from: X.3Q  reason: invalid class name */
public final class AnonymousClass3Q {
    public static AnonymousClass3Q A05;
    public static final Object A06 = new Object();
    public final ArrayList A00 = new ArrayList();
    public final HashMap A01 = new HashMap();
    public final HashMap A02 = new HashMap();
    public final Context A03;
    public final Handler A04;

    public AnonymousClass3Q(Context context) {
        this.A03 = context;
        this.A04 = new AnonymousClass3N(this, context.getMainLooper());
    }
}
