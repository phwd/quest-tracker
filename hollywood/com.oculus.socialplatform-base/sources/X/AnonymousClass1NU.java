package X;

import com.facebook.msys.mci.Database;

/* renamed from: X.1NU  reason: invalid class name */
public class AnonymousClass1NU extends AnonymousClass1Kd {
    public static final String __redex_internal_original_name = "com.facebook.msys.mci.Database$1";
    public final /* synthetic */ Database A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass1NU(Database database) {
        super("fixAll");
        this.A00 = database;
    }

    public final void run() {
        this.A00.mDatabaseHealthMonitor.fixAll();
    }
}
