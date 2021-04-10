package sun.misc;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.security.AccessController;
import java.security.PrivilegedAction;

public class Cleaner extends PhantomReference {
    private static final ReferenceQueue dummyQueue = new ReferenceQueue();
    private static Cleaner first = null;
    private Cleaner next = null;
    private Cleaner prev = null;
    private final Runnable thunk;

    private static synchronized Cleaner add(Cleaner cleaner) {
        synchronized (Cleaner.class) {
            if (first != null) {
                cleaner.next = first;
                first.prev = cleaner;
            }
            first = cleaner;
        }
        return cleaner;
    }

    private static synchronized boolean remove(Cleaner cleaner) {
        synchronized (Cleaner.class) {
            if (cleaner.next == cleaner) {
                return false;
            }
            if (first == cleaner) {
                if (cleaner.next != null) {
                    first = cleaner.next;
                } else {
                    first = cleaner.prev;
                }
            }
            if (cleaner.next != null) {
                cleaner.next.prev = cleaner.prev;
            }
            if (cleaner.prev != null) {
                cleaner.prev.next = cleaner.next;
            }
            cleaner.next = cleaner;
            cleaner.prev = cleaner;
            return true;
        }
    }

    private Cleaner(Object obj, Runnable runnable) {
        super(obj, dummyQueue);
        this.thunk = runnable;
    }

    public static Cleaner create(Object obj, Runnable runnable) {
        if (runnable == null) {
            return null;
        }
        Cleaner cleaner = new Cleaner(obj, runnable);
        add(cleaner);
        return cleaner;
    }

    public void clean() {
        if (remove(this)) {
            try {
                this.thunk.run();
            } catch (Throwable th) {
                AccessController.doPrivileged(new PrivilegedAction() {
                    /* class sun.misc.Cleaner.AnonymousClass1 */

                    @Override // java.security.PrivilegedAction
                    public Void run() {
                        if (System.err != null) {
                            new Error("Cleaner terminated abnormally", th).printStackTrace();
                        }
                        System.exit(1);
                        return null;
                    }
                });
            }
        }
    }
}
