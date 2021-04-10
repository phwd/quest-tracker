package X;

import java.util.LinkedList;

public final class MR {
    public static final LinkedList A00 = new LinkedList();

    public static synchronized void A00(byte[] bArr) {
        synchronized (MR.class) {
            LinkedList linkedList = A00;
            if (linkedList.size() < 20) {
                linkedList.add(bArr);
            }
        }
    }
}
