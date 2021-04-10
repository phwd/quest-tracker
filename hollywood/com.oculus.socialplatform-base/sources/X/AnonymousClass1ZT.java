package X;

import com.facebook.experiment.mca.MailboxExperimentJNI;
import com.facebook.msys.mca.Mailbox;
import java.util.Map;

/* renamed from: X.1ZT  reason: invalid class name */
public final class AnonymousClass1ZT extends AbstractC06351Ze {
    public static Map<String, String> A00;

    public static synchronized String A00(String str) {
        String str2;
        synchronized (AnonymousClass1ZT.class) {
            Map<String, String> map = A00;
            if (map == null) {
                map = MailboxExperimentJNI.getNotificationStrings(0);
                A00 = map;
            }
            str2 = map.get(str);
        }
        return str2;
    }

    public AnonymousClass1ZT(Mailbox mailbox) {
        super(mailbox);
    }
}
