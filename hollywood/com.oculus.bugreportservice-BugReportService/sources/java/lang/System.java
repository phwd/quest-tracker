package java.lang;

import android.system.ErrnoException;
import android.system.StructUtsname;
import dalvik.system.VMRuntime;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Console;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.Properties;
import libcore.icu.ICU;
import libcore.io.Libcore;
import libcore.timezone.TimeZoneDataFiles;
import sun.misc.VM;
import sun.misc.Version;

public final class System {
    private static final Object LOCK = new Object();
    private static volatile Console cons = null;
    public static final PrintStream err;
    public static final InputStream in;
    private static boolean justRanFinalization;
    private static String lineSeparator = props.getProperty("line.separator");
    public static final PrintStream out;
    private static Properties props = initProperties();
    private static boolean runGC;
    private static Properties unchangeableProps = initUnchangeableSystemProperties();

    public static native void arraycopy(Object obj, int i, Object obj2, int i2, int i3);

    private static native void arraycopyByteUnchecked(byte[] bArr, int i, byte[] bArr2, int i2, int i3);

    public static native long currentTimeMillis();

    public static SecurityManager getSecurityManager() {
        return null;
    }

    private static native void log(char c, String str, Throwable th);

    public static native long nanoTime();

    private static native String[] specialProperties();

    static {
        addLegacyLocaleSystemProperties();
        Version.initSystemProperties();
        FileInputStream fileInputStream = new FileInputStream(FileDescriptor.in);
        FileOutputStream fileOutputStream = new FileOutputStream(FileDescriptor.out);
        FileOutputStream fileOutputStream2 = new FileOutputStream(FileDescriptor.err);
        in = new BufferedInputStream(fileInputStream, 128);
        out = newPrintStream(fileOutputStream, props.getProperty("sun.stdout.encoding"));
        err = newPrintStream(fileOutputStream2, props.getProperty("sun.stderr.encoding"));
        VM.initializeOSEnvironment();
        VM.booted();
    }

    public static void arraycopy(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        if (bArr == null) {
            throw new NullPointerException("src == null");
        } else if (bArr2 == null) {
            throw new NullPointerException("dst == null");
        } else if (i < 0 || i2 < 0 || i3 < 0 || i > bArr.length - i3 || i2 > bArr2.length - i3) {
            throw new ArrayIndexOutOfBoundsException("src.length=" + bArr.length + " srcPos=" + i + " dst.length=" + bArr2.length + " dstPos=" + i2 + " length=" + i3);
        } else if (i3 > 32) {
            arraycopyByteUnchecked(bArr, i, bArr2, i2, i3);
        } else if (bArr != bArr2 || i >= i2 || i2 >= i + i3) {
            for (int i4 = 0; i4 < i3; i4++) {
                bArr2[i2 + i4] = bArr[i + i4];
            }
        } else {
            for (int i5 = i3 - 1; i5 >= 0; i5--) {
                bArr2[i2 + i5] = bArr[i + i5];
            }
        }
    }

    public static int identityHashCode(Object obj) {
        if (obj == null) {
            return 0;
        }
        return Object.identityHashCode(obj);
    }

    /* access modifiers changed from: package-private */
    public static final class PropertiesWithNonOverrideableDefaults extends Properties {
        PropertiesWithNonOverrideableDefaults(Properties properties) {
            super(properties);
        }

        @Override // java.util.Map, java.util.Hashtable
        public Object put(Object obj, Object obj2) {
            if (!this.defaults.containsKey(obj)) {
                return super.put(obj, obj2);
            }
            System.logE("Ignoring attempt to set property \"" + obj + "\" to value \"" + obj2 + "\".");
            return this.defaults.get(obj);
        }

        @Override // java.util.Map, java.util.Hashtable
        public Object remove(Object obj) {
            if (!this.defaults.containsKey(obj)) {
                return super.remove(obj);
            }
            System.logE("Ignoring attempt to remove property \"" + obj + "\".");
            return null;
        }
    }

    private static void parsePropertyAssignments(Properties properties, String[] strArr) {
        for (String str : strArr) {
            int indexOf = str.indexOf(61);
            properties.put(str.substring(0, indexOf), str.substring(indexOf + 1));
        }
    }

    private static Properties initUnchangeableSystemProperties() {
        VMRuntime runtime = VMRuntime.getRuntime();
        Properties properties = new Properties();
        properties.put("java.boot.class.path", runtime.bootClassPath());
        properties.put("java.class.path", runtime.classPath());
        String str = getenv("JAVA_HOME");
        if (str == null) {
            str = "/system";
        }
        properties.put("java.home", str);
        properties.put("java.vm.version", runtime.vmVersion());
        try {
            properties.put("user.name", Libcore.os.getpwuid(Libcore.os.getuid()).pw_name);
            StructUtsname uname = Libcore.os.uname();
            properties.put("os.arch", uname.machine);
            properties.put("os.name", uname.sysname);
            properties.put("os.version", uname.release);
            properties.put("android.icu.library.version", ICU.getIcuVersion());
            properties.put("android.icu.unicode.version", ICU.getUnicodeVersion());
            properties.put("android.icu.cldr.version", ICU.getCldrVersion());
            properties.put("android.icu.impl.ICUBinary.dataPath", TimeZoneDataFiles.generateIcuDataPath());
            parsePropertyAssignments(properties, specialProperties());
            parsePropertyAssignments(properties, runtime.properties());
            String[][] strArr = AndroidHardcodedSystemProperties.STATIC_PROPERTIES;
            for (String[] strArr2 : strArr) {
                if (properties.containsKey(strArr2[0])) {
                    logE("Ignoring command line argument: -D" + strArr2[0]);
                }
                if (strArr2[1] == null) {
                    properties.remove(strArr2[0]);
                } else {
                    properties.put(strArr2[0], strArr2[1]);
                }
            }
            return properties;
        } catch (ErrnoException e) {
            throw new AssertionError(e);
        }
    }

    private static Properties initProperties() {
        PropertiesWithNonOverrideableDefaults propertiesWithNonOverrideableDefaults = new PropertiesWithNonOverrideableDefaults(unchangeableProps);
        setDefaultChangeableProperties(propertiesWithNonOverrideableDefaults);
        return propertiesWithNonOverrideableDefaults;
    }

    private static Properties setDefaultChangeableProperties(Properties properties) {
        if (!unchangeableProps.containsKey("java.io.tmpdir")) {
            properties.put("java.io.tmpdir", "/tmp");
        }
        if (!unchangeableProps.containsKey("user.home")) {
            properties.put("user.home", "");
        }
        return properties;
    }

    public static void setUnchangeableSystemProperty(String str, String str2) {
        checkKey(str);
        unchangeableProps.put(str, str2);
    }

    private static void addLegacyLocaleSystemProperties() {
        String property = getProperty("user.locale", "");
        if (!property.isEmpty()) {
            Locale forLanguageTag = Locale.forLanguageTag(property);
            setUnchangeableSystemProperty("user.language", forLanguageTag.getLanguage());
            setUnchangeableSystemProperty("user.region", forLanguageTag.getCountry());
            setUnchangeableSystemProperty("user.variant", forLanguageTag.getVariant());
            return;
        }
        String property2 = getProperty("user.language", "");
        String property3 = getProperty("user.region", "");
        if (property2.isEmpty()) {
            setUnchangeableSystemProperty("user.language", "en");
        }
        if (property3.isEmpty()) {
            setUnchangeableSystemProperty("user.region", "US");
        }
    }

    public static String lineSeparator() {
        return lineSeparator;
    }

    public static String getProperty(String str) {
        checkKey(str);
        SecurityManager securityManager = getSecurityManager();
        if (securityManager == null) {
            return props.getProperty(str);
        }
        securityManager.checkPropertyAccess(str);
        throw null;
    }

    public static String getProperty(String str, String str2) {
        checkKey(str);
        SecurityManager securityManager = getSecurityManager();
        if (securityManager == null) {
            return props.getProperty(str, str2);
        }
        securityManager.checkPropertyAccess(str);
        throw null;
    }

    private static void checkKey(String str) {
        if (str == null) {
            throw new NullPointerException("key can't be null");
        } else if (str.equals("")) {
            throw new IllegalArgumentException("key can't be empty");
        }
    }

    public static String getenv(String str) {
        if (str != null) {
            return Libcore.os.getenv(str);
        }
        throw new NullPointerException("name == null");
    }

    public static void exit(int i) {
        Runtime.getRuntime().exit(i);
    }

    public static void gc() {
        boolean z;
        synchronized (LOCK) {
            z = justRanFinalization;
            if (z) {
                justRanFinalization = false;
            } else {
                runGC = true;
            }
        }
        if (z) {
            Runtime.getRuntime().gc();
        }
    }

    private static PrintStream newPrintStream(FileOutputStream fileOutputStream, String str) {
        if (str != null) {
            try {
                return new PrintStream((OutputStream) new BufferedOutputStream(fileOutputStream, 128), true, str);
            } catch (UnsupportedEncodingException unused) {
            }
        }
        return new PrintStream((OutputStream) new BufferedOutputStream(fileOutputStream, 128), true);
    }

    public static void logE(String str) {
        log('E', str, null);
    }

    public static void logE(String str, Throwable th) {
        log('E', str, th);
    }

    public static void logI(String str) {
        log('I', str, null);
    }

    public static void logW(String str) {
        log('W', str, null);
    }
}
