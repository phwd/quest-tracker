package X;

import com.facebook.msys.mca.Mailbox;
import com.facebook.msys.mci.NotificationCenter;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

/* renamed from: X.1Ns  reason: invalid class name */
public class AnonymousClass1Ns implements NotificationCenter.NotificationCallback {
    public final /* synthetic */ Mailbox A00;

    public AnonymousClass1Ns(Mailbox mailbox) {
        this.A00 = mailbox;
    }

    @Override // com.facebook.msys.mci.NotificationCenter.NotificationCallback
    public final void onNewNotification(String str, @Nullable Object obj, @Nullable Map<Object, Object> map) {
        if (map != null) {
            Set<String> set = (Set) map.get("MCDNotificationKeyChangedStoredProcedures");
            Iterator<AnonymousClass1Nt> it = this.A00.mStoredProcedureChangedListeners.iterator();
            while (it.hasNext()) {
                it.next().onStoredProcedureChanged(set);
            }
        }
    }
}
