package com.squareup.okhttp.internal;

import X.AbstractC0312cb;
import X.AnonymousClass98;
import X.ci;
import com.facebook.acra.LogCatCollector;
import com.squareup.okhttp.HttpUrl;
import java.io.Closeable;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public final class Util {
    public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    public static final String[] EMPTY_STRING_ARRAY = new String[0];
    public static final Charset UTF_8 = Charset.forName(LogCatCollector.UTF_8_ENCODING);

    public static String[] concat(String[] strArr, String str) {
        int length = strArr.length;
        int i = length + 1;
        String[] strArr2 = new String[i];
        System.arraycopy(strArr, 0, strArr2, 0, length);
        strArr2[i - 1] = str;
        return strArr2;
    }

    public static void checkOffsetAndCount(long j, long j2, long j3) {
        if ((j2 | j3) < 0 || j2 > j || j - j2 < j3) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public static boolean equal(Object obj, Object obj2) {
        if (obj == obj2) {
            return true;
        }
        if (obj == null || !obj.equals(obj2)) {
            return false;
        }
        return true;
    }

    public static String hostHeader(HttpUrl httpUrl) {
        int i = httpUrl.port;
        if (i == HttpUrl.defaultPort(httpUrl.scheme)) {
            return httpUrl.host;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(httpUrl.host);
        sb.append(":");
        sb.append(i);
        return sb.toString();
    }

    public static <K, V> Map<K, V> immutableMap(Map<K, V> map) {
        return Collections.unmodifiableMap(new LinkedHashMap(map));
    }

    public static String md5Hex(String str) {
        try {
            return ci.A05(MessageDigest.getInstance("MD5").digest(str.getBytes(LogCatCollector.UTF_8_ENCODING))).A09();
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }

    public static ci sha1(ci ciVar) {
        try {
            return ci.A05(MessageDigest.getInstance("SHA-1").digest(ciVar.A0I()));
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }

    public static String shaBase64(String str) {
        try {
            return ci.A05(MessageDigest.getInstance("SHA-1").digest(str.getBytes(LogCatCollector.UTF_8_ENCODING))).A08();
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }

    public static ThreadFactory threadFactory(final String str, final boolean z) {
        return new ThreadFactory() {
            /* class com.squareup.okhttp.internal.Util.AnonymousClass1 */

            public Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable, str);
                thread.setDaemon(z);
                return thread;
            }
        };
    }

    public static void closeAll(Closeable closeable, Closeable closeable2) throws IOException {
        Throwable th;
        try {
            closeable.close();
            th = null;
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            closeable2.close();
            if (th == null) {
                return;
            }
        } catch (Throwable th3) {
            if (th == null) {
                th = th3;
            }
        }
        if ((th instanceof IOException) || (th instanceof RuntimeException) || (th instanceof Error)) {
            throw th;
        }
        throw new AssertionError(th);
    }

    public static boolean contains(String[] strArr, String str) {
        return Arrays.asList(strArr).contains(str);
    }

    public static boolean discard(AbstractC0312cb cbVar, int i, TimeUnit timeUnit) {
        try {
            return skipAll(cbVar, i, timeUnit);
        } catch (IOException unused) {
            return false;
        }
    }

    public static boolean isAndroidGetsocknameError(AssertionError assertionError) {
        if (assertionError.getCause() == null || assertionError.getMessage() == null || !assertionError.getMessage().contains("getsockname failed")) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x005c, code lost:
        if (r0 != Long.MAX_VALUE) goto L_0x0066;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean skipAll(X.AbstractC0312cb r12, int r13, java.util.concurrent.TimeUnit r14) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 111
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.okhttp.internal.Util.skipAll(X.cb, int, java.util.concurrent.TimeUnit):boolean");
    }

    public static String toHumanReadableAscii(String str) {
        int i;
        int length = str.length();
        int i2 = 0;
        while (i2 < length) {
            int codePointAt = str.codePointAt(i2);
            if (codePointAt <= 31 || codePointAt >= 127) {
                AnonymousClass98 r2 = new AnonymousClass98();
                r2.A0H(str, 0, i2);
                while (i2 < length) {
                    int codePointAt2 = str.codePointAt(i2);
                    if (codePointAt2 > 31) {
                        i = codePointAt2;
                        if (codePointAt2 < 127) {
                            r2.A0D(i);
                            i2 += Character.charCount(codePointAt2);
                        }
                    }
                    i = 63;
                    r2.A0D(i);
                    i2 += Character.charCount(codePointAt2);
                }
                return r2.A04();
            }
            i2 += Character.charCount(codePointAt);
        }
        return str;
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception unused) {
            }
        }
    }

    public static void closeQuietly(ServerSocket serverSocket) {
        if (serverSocket != null) {
            try {
                serverSocket.close();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception unused) {
            }
        }
    }

    public static void closeQuietly(Socket socket) {
        if (socket != null) {
            try {
                socket.close();
            } catch (AssertionError e) {
                if (!isAndroidGetsocknameError(e)) {
                    throw e;
                }
            } catch (RuntimeException e2) {
                throw e2;
            } catch (Exception unused) {
            }
        }
    }

    public static <T> List<T> immutableList(List<T> list) {
        return Collections.unmodifiableList(new ArrayList(list));
    }

    public static <T> List<T> immutableList(T... tArr) {
        return Collections.unmodifiableList(Arrays.asList((Object[]) tArr.clone()));
    }

    public static <T> List<T> intersect(T[] tArr, T[] tArr2) {
        ArrayList arrayList = new ArrayList();
        for (T t : tArr) {
            int length = tArr2.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                T t2 = tArr2[i];
                if (t.equals(t2)) {
                    arrayList.add(t2);
                    break;
                }
                i++;
            }
        }
        return arrayList;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: java.util.List */
    /* JADX WARN: Multi-variable type inference failed */
    public static <T> T[] intersect(Class<T> cls, T[] tArr, T[] tArr2) {
        List intersect = intersect(tArr, tArr2);
        return (T[]) intersect.toArray((Object[]) Array.newInstance((Class<?>) cls, intersect.size()));
    }
}
