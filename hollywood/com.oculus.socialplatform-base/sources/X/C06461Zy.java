package X;

import com.facebook.core.mca.MailboxCoreJNI;
import com.facebook.msys.mca.Mailbox;
import java.util.Map;

/* renamed from: X.1Zy  reason: invalid class name and case insensitive filesystem */
public final class C06461Zy extends AbstractC06351Ze {
    public static Map<String, String> A00;

    public static synchronized String A00(String str) {
        String str2;
        synchronized (C06461Zy.class) {
            Map<String, String> map = A00;
            if (map == null) {
                map = MailboxCoreJNI.getNotificationStrings(0);
                A00 = map;
            }
            str2 = map.get(str);
        }
        return str2;
    }

    public C06461Zy(Mailbox mailbox) {
        super(mailbox);
    }
}
