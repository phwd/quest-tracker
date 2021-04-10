package java.io;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;

/* access modifiers changed from: package-private */
public class DeleteOnExitHook {
    private static LinkedHashSet files = new LinkedHashSet();

    static {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            /* class java.io.DeleteOnExitHook.AnonymousClass1 */

            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                DeleteOnExitHook.runHooks();
            }
        });
    }

    private DeleteOnExitHook() {
    }

    static synchronized void add(String str) {
        synchronized (DeleteOnExitHook.class) {
            if (files != null) {
                files.add(str);
            } else {
                throw new IllegalStateException("Shutdown in progress");
            }
        }
    }

    static void runHooks() {
        LinkedHashSet linkedHashSet;
        synchronized (DeleteOnExitHook.class) {
            linkedHashSet = files;
            files = null;
        }
        ArrayList arrayList = new ArrayList(linkedHashSet);
        Collections.reverse(arrayList);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            new File((String) it.next()).delete();
        }
    }
}
