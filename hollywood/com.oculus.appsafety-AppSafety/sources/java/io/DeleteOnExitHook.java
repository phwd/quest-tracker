package java.io;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;

/* access modifiers changed from: package-private */
public class DeleteOnExitHook {
    private static LinkedHashSet<String> files = new LinkedHashSet<>();

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

    static synchronized void add(String file) {
        synchronized (DeleteOnExitHook.class) {
            if (files != null) {
                files.add(file);
            } else {
                throw new IllegalStateException("Shutdown in progress");
            }
        }
    }

    static void runHooks() {
        LinkedHashSet<String> theFiles;
        synchronized (DeleteOnExitHook.class) {
            theFiles = files;
            files = null;
        }
        ArrayList<String> toBeDeleted = new ArrayList<>(theFiles);
        Collections.reverse(toBeDeleted);
        Iterator<String> it = toBeDeleted.iterator();
        while (it.hasNext()) {
            new File(it.next()).delete();
        }
    }
}
