package X;

import com.facebook.messengervr.mca.MailboxMessengerVrJNI;
import com.facebook.msys.mca.Mailbox;
import java.util.Map;

/* renamed from: X.269  reason: invalid class name */
public final class AnonymousClass269 extends AbstractC06351Ze {
    public static Map<String, String> A00;

    public static synchronized String A00(String str) {
        String str2;
        synchronized (AnonymousClass269.class) {
            Map<String, String> map = A00;
            if (map == null) {
                map = MailboxMessengerVrJNI.getNotificationStrings(0);
                A00 = map;
            }
            str2 = map.get(str);
        }
        return str2;
    }

    public AnonymousClass269(Mailbox mailbox) {
        super(mailbox);
    }
}
