package sun.misc;

import java.util.Properties;

public class VM {
    private static boolean allowArraySyntax = defaultAllowArraySyntax;
    private static volatile boolean booted = false;
    private static boolean defaultAllowArraySyntax = false;
    private static long directMemory = 67108864;
    private static volatile int finalRefCount = 0;
    private static final Object lock = new Object();
    private static volatile int peakFinalRefCount = 0;
    private static final Properties savedProps = new Properties();

    public static void initializeOSEnvironment() {
    }

    public static void booted() {
        synchronized (lock) {
            booted = true;
            lock.notifyAll();
        }
    }

    public static boolean isBooted() {
        return booted;
    }

    public static String getSavedProperty(String str) {
        return savedProps.getProperty(str);
    }
}
