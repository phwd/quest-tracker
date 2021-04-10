package sun.misc;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.security.AccessController;
import java.security.PrivilegedAction;

public class Cleaner extends PhantomReference<Object> {
    private static final ReferenceQueue<Object> dummyQueue = new ReferenceQueue<>();
    private static Cleaner first = null;
    private Cleaner next = null;
    private Cleaner prev = null;
    private final Runnable thunk;

    private static synchronized Cleaner add(Cleaner cl) {
        synchronized (Cleaner.class) {
            if (first != null) {
                cl.next = first;
                first.prev = cl;
            }
            first = cl;
        }
        return cl;
    }

    private static synchronized boolean remove(Cleaner cl) {
        synchronized (Cleaner.class) {
            if (cl.next == cl) {
                return false;
            }
            if (first == cl) {
                Cleaner cleaner = cl.next;
                if (cleaner != null) {
                    first = cleaner;
                } else {
                    first = cl.prev;
                }
            }
            Cleaner cleaner2 = cl.next;
            if (cleaner2 != null) {
                cleaner2.prev = cl.prev;
            }
            Cleaner cleaner3 = cl.prev;
            if (cleaner3 != null) {
                cleaner3.next = cl.next;
            }
            cl.next = cl;
            cl.prev = cl;
            return true;
        }
    }

    private Cleaner(Object referent, Runnable thunk2) {
        super(referent, dummyQueue);
        this.thunk = thunk2;
    }

    public static Cleaner create(Object ob, Runnable thunk2) {
        if (thunk2 == null) {
            return null;
        }
        return add(new Cleaner(ob, thunk2));
    }

    public void clean() {
        if (remove(this)) {
            try {
                this.thunk.run();
            } catch (Throwable x) {
                AccessController.doPrivileged(new PrivilegedAction<Void>() {
                    /* class sun.misc.Cleaner.AnonymousClass1 */

                    @Override // java.security.PrivilegedAction
                    public Void run() {
                        if (System.err != null) {
                            new Error("Cleaner terminated abnormally", x).printStackTrace();
                        }
                        System.exit(1);
                        return null;
                    }
                });
            }
        }
    }
}
