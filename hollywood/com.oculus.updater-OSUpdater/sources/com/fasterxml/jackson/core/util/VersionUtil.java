package com.fasterxml.jackson.core.util;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.Versioned;
import java.io.PrintStream;
import java.util.regex.Pattern;

public class VersionUtil {
    private static final Pattern VERSION_SEPARATOR = Pattern.compile("[-_./;:]");
    private final Version _version;

    protected VersionUtil() {
        Version version;
        try {
            version = versionFor(getClass());
        } catch (Exception unused) {
            PrintStream printStream = System.err;
            printStream.println("ERROR: Failed to load Version information for bundle (via " + getClass().getName() + ").");
            version = null;
        }
        this._version = version == null ? Version.unknownVersion() : version;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(9:7|8|9|10|11|12|13|14|15) */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0026, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002c, code lost:
        throw new java.lang.RuntimeException(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0032, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x003b, code lost:
        return com.fasterxml.jackson.core.Version.unknownVersion();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x003c, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0042, code lost:
        throw new java.lang.RuntimeException(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0046, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0047, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x004d, code lost:
        throw new java.lang.RuntimeException(r2);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0022 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0034 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.fasterxml.jackson.core.Version versionFor(java.lang.Class<?> r2) {
        /*
            com.fasterxml.jackson.core.Version r0 = packageVersionFor(r2)
            if (r0 == 0) goto L_0x0007
            return r0
        L_0x0007:
            java.lang.String r0 = "VERSION.txt"
            java.io.InputStream r2 = r2.getResourceAsStream(r0)
            if (r2 != 0) goto L_0x0014
            com.fasterxml.jackson.core.Version r2 = com.fasterxml.jackson.core.Version.unknownVersion()
            return r2
        L_0x0014:
            java.io.InputStreamReader r0 = new java.io.InputStreamReader     // Catch:{ UnsupportedEncodingException -> 0x0034 }
            java.lang.String r1 = "UTF-8"
            r0.<init>(r2, r1)     // Catch:{ UnsupportedEncodingException -> 0x0034 }
            com.fasterxml.jackson.core.Version r1 = doReadVersion(r0)     // Catch:{ all -> 0x002d }
            r0.close()     // Catch:{ IOException -> 0x0022 }
        L_0x0022:
            r2.close()     // Catch:{ IOException -> 0x0026 }
            return r1
        L_0x0026:
            r2 = move-exception
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            r0.<init>(r2)
            throw r0
        L_0x002d:
            r1 = move-exception
            r0.close()     // Catch:{ IOException -> 0x0031 }
        L_0x0031:
            throw r1
        L_0x0032:
            r0 = move-exception
            goto L_0x0043
        L_0x0034:
            com.fasterxml.jackson.core.Version r0 = com.fasterxml.jackson.core.Version.unknownVersion()     // Catch:{ all -> 0x0032 }
            r2.close()     // Catch:{ IOException -> 0x003c }
            return r0
        L_0x003c:
            r2 = move-exception
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            r0.<init>(r2)
            throw r0
        L_0x0043:
            r2.close()     // Catch:{ IOException -> 0x0047 }
            throw r0
        L_0x0047:
            r2 = move-exception
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            r0.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.util.VersionUtil.versionFor(java.lang.Class):com.fasterxml.jackson.core.Version");
    }

    public static Version packageVersionFor(Class<?> cls) {
        try {
            Class<?> cls2 = Class.forName(cls.getPackage().getName() + "." + "PackageVersion", true, cls.getClassLoader());
            if (cls2 == null) {
                return null;
            }
            try {
                Object newInstance = cls2.newInstance();
                if (newInstance instanceof Versioned) {
                    return ((Versioned) newInstance).version();
                }
                throw new IllegalArgumentException("Bad version class " + cls2.getName() + ": does not implement " + Versioned.class.getName());
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e2) {
                throw new IllegalArgumentException("Failed to instantiate " + cls2.getName() + " to find version information, problem: " + e2.getMessage(), e2);
            }
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001c, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0018 */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x001c A[ExcHandler: all (r3v2 'th' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:1:0x0006] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0026  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x002c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.fasterxml.jackson.core.Version doReadVersion(java.io.Reader r3) {
        /*
            java.io.BufferedReader r0 = new java.io.BufferedReader
            r0.<init>(r3)
            r3 = 0
            java.lang.String r1 = r0.readLine()     // Catch:{ IOException -> 0x0021, all -> 0x001c }
            if (r1 == 0) goto L_0x0017
            java.lang.String r2 = r0.readLine()     // Catch:{ IOException -> 0x0017, all -> 0x001c }
            if (r2 == 0) goto L_0x0018
            java.lang.String r3 = r0.readLine()     // Catch:{ IOException -> 0x0018, all -> 0x001c }
            goto L_0x0018
        L_0x0017:
            r2 = r3
        L_0x0018:
            r0.close()     // Catch:{ IOException -> 0x0024 }
            goto L_0x0024
        L_0x001c:
            r3 = move-exception
            r0.close()     // Catch:{ IOException -> 0x0020 }
        L_0x0020:
            throw r3
        L_0x0021:
            r1 = r3
            r2 = r1
            goto L_0x0018
        L_0x0024:
            if (r2 == 0) goto L_0x002a
            java.lang.String r2 = r2.trim()
        L_0x002a:
            if (r3 == 0) goto L_0x0030
            java.lang.String r3 = r3.trim()
        L_0x0030:
            com.fasterxml.jackson.core.Version r3 = parseVersion(r1, r2, r3)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.util.VersionUtil.doReadVersion(java.io.Reader):com.fasterxml.jackson.core.Version");
    }

    public static Version parseVersion(String str, String str2, String str3) {
        String str4 = null;
        if (str == null) {
            return null;
        }
        String trim = str.trim();
        if (trim.length() == 0) {
            return null;
        }
        String[] split = VERSION_SEPARATOR.split(trim);
        int parseVersionPart = parseVersionPart(split[0]);
        int parseVersionPart2 = split.length > 1 ? parseVersionPart(split[1]) : 0;
        int parseVersionPart3 = split.length > 2 ? parseVersionPart(split[2]) : 0;
        if (split.length > 3) {
            str4 = split[3];
        }
        return new Version(parseVersionPart, parseVersionPart2, parseVersionPart3, str4, str2, str3);
    }

    protected static int parseVersionPart(String str) {
        String str2 = str.toString();
        int length = str2.length();
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = str2.charAt(i2);
            if (charAt > '9' || charAt < '0') {
                break;
            }
            i = (i * 10) + (charAt - '0');
        }
        return i;
    }

    public static final void throwInternal() {
        throw new RuntimeException("Internal error: this code path should never get executed");
    }
}
