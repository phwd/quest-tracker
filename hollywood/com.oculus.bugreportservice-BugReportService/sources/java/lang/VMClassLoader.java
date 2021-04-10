package java.lang;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import libcore.io.ClassPathURLStreamHandler;

/* access modifiers changed from: package-private */
public class VMClassLoader {
    private static final ClassPathURLStreamHandler[] bootClassPathUrlHandlers = createBootClassPathUrlHandlers();

    static native Class findLoadedClass(ClassLoader classLoader, String str);

    private static native String[] getBootClassPathEntries();

    private static ClassPathURLStreamHandler[] createBootClassPathUrlHandlers() {
        String[] bootClassPathEntries = getBootClassPathEntries();
        ArrayList arrayList = new ArrayList(bootClassPathEntries.length);
        for (String str : bootClassPathEntries) {
            try {
                new File(str).toURI().toString();
                arrayList.add(new ClassPathURLStreamHandler(str));
            } catch (IOException e) {
                System.logE("Unable to open boot classpath entry: " + str, e);
            }
        }
        return (ClassPathURLStreamHandler[]) arrayList.toArray(new ClassPathURLStreamHandler[arrayList.size()]);
    }

    static URL getResource(String str) {
        for (ClassPathURLStreamHandler classPathURLStreamHandler : bootClassPathUrlHandlers) {
            URL entryUrlOrNull = classPathURLStreamHandler.getEntryUrlOrNull(str);
            if (entryUrlOrNull != null) {
                return entryUrlOrNull;
            }
        }
        return null;
    }

    static List getResources(String str) {
        ArrayList arrayList = new ArrayList();
        for (ClassPathURLStreamHandler classPathURLStreamHandler : bootClassPathUrlHandlers) {
            URL entryUrlOrNull = classPathURLStreamHandler.getEntryUrlOrNull(str);
            if (entryUrlOrNull != null) {
                arrayList.add(entryUrlOrNull);
            }
        }
        return arrayList;
    }
}
