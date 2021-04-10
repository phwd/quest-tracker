package X;

import com.facebook.msys.mci.NotificationCenter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/* renamed from: X.1Kc  reason: invalid class name */
public class AnonymousClass1Kc extends AnonymousClass1Kd {
    public static final String __redex_internal_original_name = "com.facebook.msys.mci.NotificationCenter$2";
    public final /* synthetic */ NotificationCenter A00;
    public final /* synthetic */ Object A01;
    public final /* synthetic */ String A02;
    public final /* synthetic */ ArrayList A03;
    public final /* synthetic */ Map A04;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass1Kc(NotificationCenter notificationCenter, ArrayList arrayList, String str, Object obj, Map map) {
        super("dispatchNotificationToCallbacks");
        this.A00 = notificationCenter;
        this.A03 = arrayList;
        this.A02 = str;
        this.A01 = obj;
        this.A04 = map;
    }

    public final void run() {
        Iterator it = this.A03.iterator();
        while (it.hasNext()) {
            ((NotificationCenter.NotificationCallback) it.next()).onNewNotification(this.A02, this.A01, this.A04);
        }
    }
}
