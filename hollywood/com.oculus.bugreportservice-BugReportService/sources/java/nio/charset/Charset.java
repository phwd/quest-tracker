package java.nio.charset;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.spi.CharsetProvider;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.AbstractMap;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import java.util.Set;
import sun.misc.VM;
import sun.nio.cs.ThreadLocalCoders;
import sun.security.action.GetPropertyAction;

public abstract class Charset implements Comparable {
    private static volatile String bugLevel;
    private static volatile Map.Entry cache1;
    private static final HashMap cache2 = new HashMap();
    private static Charset defaultCharset;
    private static ThreadLocal gate = new ThreadLocal();
    private Set aliasSet;
    private final String[] aliases;
    private final String name;

    public abstract CharsetDecoder newDecoder();

    public abstract CharsetEncoder newEncoder();

    static boolean atBugLevel(String str) {
        String str2 = bugLevel;
        if (str2 == null) {
            if (!VM.isBooted()) {
                return false;
            }
            str2 = (String) AccessController.doPrivileged(new GetPropertyAction("sun.nio.cs.bugLevel", ""));
            bugLevel = str2;
        }
        return str2.equals(str);
    }

    private static void checkName(String str) {
        int length = str.length();
        if (atBugLevel("1.4") || length != 0) {
            for (int i = 0; i < length; i++) {
                char charAt = str.charAt(i);
                if ((charAt < 'A' || charAt > 'Z') && ((charAt < 'a' || charAt > 'z') && ((charAt < '0' || charAt > '9') && ((charAt != '-' || i == 0) && ((charAt != '+' || i == 0) && ((charAt != ':' || i == 0) && ((charAt != '_' || i == 0) && (charAt != '.' || i == 0)))))))) {
                    throw new IllegalCharsetNameException(str);
                }
            }
            return;
        }
        throw new IllegalCharsetNameException(str);
    }

    private static void cache(String str, Charset charset) {
        synchronized (cache2) {
            String name2 = charset.name();
            Charset charset2 = (Charset) cache2.get(name2);
            if (charset2 != null) {
                charset = charset2;
            } else {
                cache2.put(name2, charset);
                for (String str2 : charset.aliases()) {
                    cache2.put(str2, charset);
                }
            }
            cache2.put(str, charset);
        }
        cache1 = new AbstractMap.SimpleImmutableEntry(str, charset);
    }

    /* access modifiers changed from: private */
    public static Iterator providers() {
        return new Iterator() {
            /* class java.nio.charset.Charset.AnonymousClass1 */
            Iterator i = this.sl.iterator();
            CharsetProvider next = null;
            ServiceLoader sl = ServiceLoader.load(CharsetProvider.class);

            private boolean getNext() {
                while (this.next == null) {
                    try {
                        if (!this.i.hasNext()) {
                            return false;
                        }
                        this.next = (CharsetProvider) this.i.next();
                    } catch (ServiceConfigurationError e) {
                        if (!(e.getCause() instanceof SecurityException)) {
                            throw e;
                        }
                    }
                }
                return true;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return getNext();
            }

            @Override // java.util.Iterator
            public CharsetProvider next() {
                if (getNext()) {
                    CharsetProvider charsetProvider = this.next;
                    this.next = null;
                    return charsetProvider;
                }
                throw new NoSuchElementException();
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    /* JADX INFO: finally extract failed */
    private static Charset lookupViaProviders(final String str) {
        if (!VM.isBooted() || gate.get() != null) {
            return null;
        }
        try {
            gate.set(gate);
            Charset charset = (Charset) AccessController.doPrivileged(new PrivilegedAction() {
                /* class java.nio.charset.Charset.AnonymousClass2 */

                @Override // java.security.PrivilegedAction
                public Charset run() {
                    Iterator providers = Charset.providers();
                    while (providers.hasNext()) {
                        Charset charsetForName = ((CharsetProvider) providers.next()).charsetForName(String.this);
                        if (charsetForName != null) {
                            return charsetForName;
                        }
                    }
                    return null;
                }
            });
            gate.set(null);
            return charset;
        } catch (Throwable th) {
            gate.set(null);
            throw th;
        }
    }

    private static Charset lookup(String str) {
        if (str != null) {
            Map.Entry entry = cache1;
            if (entry == null || !str.equals(entry.getKey())) {
                return lookup2(str);
            }
            return (Charset) entry.getValue();
        }
        throw new IllegalArgumentException("Null charset name");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001b, code lost:
        if (r0 != null) goto L_0x0029;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001d, code lost:
        r0 = lookupViaProviders(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0021, code lost:
        if (r0 == null) goto L_0x0024;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0024, code lost:
        checkName(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0028, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0029, code lost:
        cache(r3, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002c, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0017, code lost:
        r0 = libcore.icu.NativeConverter.charsetForName(r3);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.nio.charset.Charset lookup2(java.lang.String r3) {
        /*
            java.util.HashMap r0 = java.nio.charset.Charset.cache2
            monitor-enter(r0)
            java.util.HashMap r1 = java.nio.charset.Charset.cache2     // Catch:{ all -> 0x002d }
            java.lang.Object r1 = r1.get(r3)     // Catch:{ all -> 0x002d }
            java.nio.charset.Charset r1 = (java.nio.charset.Charset) r1     // Catch:{ all -> 0x002d }
            if (r1 == 0) goto L_0x0016
            java.util.AbstractMap$SimpleImmutableEntry r2 = new java.util.AbstractMap$SimpleImmutableEntry     // Catch:{ all -> 0x002d }
            r2.<init>(r3, r1)     // Catch:{ all -> 0x002d }
            java.nio.charset.Charset.cache1 = r2     // Catch:{ all -> 0x002d }
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            return r1
        L_0x0016:
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            java.nio.charset.Charset r0 = libcore.icu.NativeConverter.charsetForName(r3)
            if (r0 != 0) goto L_0x0029
            java.nio.charset.Charset r0 = lookupViaProviders(r3)
            if (r0 == 0) goto L_0x0024
            goto L_0x0029
        L_0x0024:
            checkName(r3)
            r3 = 0
            return r3
        L_0x0029:
            cache(r3, r0)
            return r0
        L_0x002d:
            r3 = move-exception
            monitor-exit(r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: java.nio.charset.Charset.lookup2(java.lang.String):java.nio.charset.Charset");
    }

    public static boolean isSupported(String str) {
        return lookup(str) != null;
    }

    public static Charset forName(String str) {
        Charset lookup = lookup(str);
        if (lookup != null) {
            return lookup;
        }
        throw new UnsupportedCharsetException(str);
    }

    public static Charset forNameUEE(String str) {
        try {
            return forName(str);
        } catch (Exception e) {
            UnsupportedEncodingException unsupportedEncodingException = new UnsupportedEncodingException(str);
            unsupportedEncodingException.initCause(e);
            throw unsupportedEncodingException;
        }
    }

    public static Charset defaultCharset() {
        Charset charset;
        synchronized (Charset.class) {
            if (defaultCharset == null) {
                defaultCharset = StandardCharsets.UTF_8;
            }
            charset = defaultCharset;
        }
        return charset;
    }

    public final String name() {
        return this.name;
    }

    public final Set aliases() {
        Set set = this.aliasSet;
        if (set != null) {
            return set;
        }
        int length = this.aliases.length;
        HashSet hashSet = new HashSet(length);
        for (int i = 0; i < length; i++) {
            hashSet.add(this.aliases[i]);
        }
        this.aliasSet = Collections.unmodifiableSet(hashSet);
        return this.aliasSet;
    }

    public final ByteBuffer encode(CharBuffer charBuffer) {
        try {
            return ThreadLocalCoders.encoderFor(this).onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE).encode(charBuffer);
        } catch (CharacterCodingException e) {
            throw new Error(e);
        }
    }

    public final ByteBuffer encode(String str) {
        return encode(CharBuffer.wrap(str));
    }

    public final int compareTo(Charset charset) {
        return name().compareToIgnoreCase(charset.name());
    }

    public final int hashCode() {
        return name().hashCode();
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof Charset)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        return this.name.equals(((Charset) obj).name());
    }

    public final String toString() {
        return name();
    }
}
