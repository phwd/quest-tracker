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
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import libcore.icu.NativeConverter;
import sun.misc.ASCIICaseInsensitiveComparator;
import sun.misc.VM;
import sun.nio.cs.ThreadLocalCoders;
import sun.security.action.GetPropertyAction;

public abstract class Charset implements Comparable<Charset> {
    private static volatile String bugLevel = null;
    private static volatile Map.Entry<String, Charset> cache1 = null;
    private static final HashMap<String, Charset> cache2 = new HashMap<>();
    private static Charset defaultCharset;
    private static ThreadLocal<ThreadLocal<?>> gate = new ThreadLocal<>();
    private Set<String> aliasSet = null;
    private final String[] aliases;
    private final String name;

    public abstract boolean contains(Charset charset);

    public abstract CharsetDecoder newDecoder();

    public abstract CharsetEncoder newEncoder();

    static boolean atBugLevel(String bl) {
        String level = bugLevel;
        if (level == null) {
            if (!VM.isBooted()) {
                return false;
            }
            String str = (String) AccessController.doPrivileged(new GetPropertyAction("sun.nio.cs.bugLevel", ""));
            level = str;
            bugLevel = str;
        }
        return level.equals(bl);
    }

    private static void checkName(String s) {
        int n = s.length();
        if (atBugLevel("1.4") || n != 0) {
            for (int i = 0; i < n; i++) {
                char c = s.charAt(i);
                if ((c < 'A' || c > 'Z') && ((c < 'a' || c > 'z') && ((c < '0' || c > '9') && ((c != '-' || i == 0) && ((c != '+' || i == 0) && ((c != ':' || i == 0) && ((c != '_' || i == 0) && (c != '.' || i == 0)))))))) {
                    throw new IllegalCharsetNameException(s);
                }
            }
            return;
        }
        throw new IllegalCharsetNameException(s);
    }

    private static void cache(String charsetName, Charset cs) {
        synchronized (cache2) {
            String canonicalName = cs.name();
            Charset canonicalCharset = cache2.get(canonicalName);
            if (canonicalCharset != null) {
                cs = canonicalCharset;
            } else {
                cache2.put(canonicalName, cs);
                for (String alias : cs.aliases()) {
                    cache2.put(alias, cs);
                }
            }
            cache2.put(charsetName, cs);
        }
        cache1 = new AbstractMap.SimpleImmutableEntry(charsetName, cs);
    }

    /* access modifiers changed from: private */
    public static Iterator<CharsetProvider> providers() {
        return new Iterator<CharsetProvider>() {
            /* class java.nio.charset.Charset.AnonymousClass1 */
            Iterator<CharsetProvider> i = this.sl.iterator();
            CharsetProvider next = null;
            ServiceLoader<CharsetProvider> sl = ServiceLoader.load(CharsetProvider.class);

            private boolean getNext() {
                while (this.next == null) {
                    try {
                        if (!this.i.hasNext()) {
                            return false;
                        }
                        this.next = this.i.next();
                    } catch (ServiceConfigurationError sce) {
                        if (!(sce.getCause() instanceof SecurityException)) {
                            throw sce;
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
                    CharsetProvider n = this.next;
                    this.next = null;
                    return n;
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
    private static Charset lookupViaProviders(final String charsetName) {
        if (!VM.isBooted() || gate.get() != null) {
            return null;
        }
        try {
            gate.set(gate);
            Charset charset = (Charset) AccessController.doPrivileged(new PrivilegedAction<Charset>() {
                /* class java.nio.charset.Charset.AnonymousClass2 */

                @Override // java.security.PrivilegedAction
                public Charset run() {
                    Iterator<CharsetProvider> i = Charset.providers();
                    while (i.hasNext()) {
                        Charset cs = i.next().charsetForName(String.this);
                        if (cs != null) {
                            return cs;
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

    private static Charset lookup(String charsetName) {
        if (charsetName != null) {
            Map.Entry<String, Charset> cached = cache1;
            if (cached == null || !charsetName.equals(cached.getKey())) {
                return lookup2(charsetName);
            }
            return cached.getValue();
        }
        throw new IllegalArgumentException("Null charset name");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001d, code lost:
        if (r0 != null) goto L_0x002c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001f, code lost:
        r0 = lookupViaProviders(r3);
        r1 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0024, code lost:
        if (r0 == null) goto L_0x0027;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0027, code lost:
        checkName(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002b, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002c, code lost:
        cache(r3, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002f, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0018, code lost:
        r0 = libcore.icu.NativeConverter.charsetForName(r3);
        r1 = r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.nio.charset.Charset lookup2(java.lang.String r3) {
        /*
            java.util.HashMap<java.lang.String, java.nio.charset.Charset> r0 = java.nio.charset.Charset.cache2
            monitor-enter(r0)
            java.util.HashMap<java.lang.String, java.nio.charset.Charset> r1 = java.nio.charset.Charset.cache2     // Catch:{ all -> 0x0030 }
            java.lang.Object r1 = r1.get(r3)     // Catch:{ all -> 0x0030 }
            java.nio.charset.Charset r1 = (java.nio.charset.Charset) r1     // Catch:{ all -> 0x0030 }
            r2 = r1
            if (r1 == 0) goto L_0x0017
            java.util.AbstractMap$SimpleImmutableEntry r1 = new java.util.AbstractMap$SimpleImmutableEntry     // Catch:{ all -> 0x0030 }
            r1.<init>(r3, r2)     // Catch:{ all -> 0x0030 }
            java.nio.charset.Charset.cache1 = r1     // Catch:{ all -> 0x0030 }
            monitor-exit(r0)     // Catch:{ all -> 0x0030 }
            return r2
        L_0x0017:
            monitor-exit(r0)     // Catch:{ all -> 0x0030 }
            java.nio.charset.Charset r0 = libcore.icu.NativeConverter.charsetForName(r3)
            r1 = r0
            if (r0 != 0) goto L_0x002c
            java.nio.charset.Charset r0 = lookupViaProviders(r3)
            r1 = r0
            if (r0 == 0) goto L_0x0027
            goto L_0x002c
        L_0x0027:
            checkName(r3)
            r0 = 0
            return r0
        L_0x002c:
            cache(r3, r1)
            return r1
        L_0x0030:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: java.nio.charset.Charset.lookup2(java.lang.String):java.nio.charset.Charset");
    }

    public static boolean isSupported(String charsetName) {
        return lookup(charsetName) != null;
    }

    public static Charset forName(String charsetName) {
        Charset cs = lookup(charsetName);
        if (cs != null) {
            return cs;
        }
        throw new UnsupportedCharsetException(charsetName);
    }

    public static Charset forNameUEE(String charsetName) throws UnsupportedEncodingException {
        try {
            return forName(charsetName);
        } catch (Exception cause) {
            UnsupportedEncodingException ex = new UnsupportedEncodingException(charsetName);
            ex.initCause(cause);
            throw ex;
        }
    }

    /* access modifiers changed from: private */
    public static void put(Iterator<Charset> i, Map<String, Charset> m) {
        while (i.hasNext()) {
            Charset cs = i.next();
            if (!m.containsKey(cs.name())) {
                m.put(cs.name(), cs);
            }
        }
    }

    public static SortedMap<String, Charset> availableCharsets() {
        return (SortedMap) AccessController.doPrivileged(new PrivilegedAction<SortedMap<String, Charset>>() {
            /* class java.nio.charset.Charset.AnonymousClass3 */

            @Override // java.security.PrivilegedAction
            public SortedMap<String, Charset> run() {
                TreeMap<String, Charset> m = new TreeMap<>(ASCIICaseInsensitiveComparator.CASE_INSENSITIVE_ORDER);
                for (String charsetName : NativeConverter.getAvailableCharsetNames()) {
                    Charset charset = NativeConverter.charsetForName(charsetName);
                    m.put(charset.name(), charset);
                }
                Iterator i = Charset.providers();
                while (i.hasNext()) {
                    Charset.put(((CharsetProvider) i.next()).charsets(), m);
                }
                return Collections.unmodifiableSortedMap(m);
            }
        });
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

    protected Charset(String canonicalName, String[] aliases2) {
        checkName(canonicalName);
        String[] as = aliases2 == null ? new String[0] : aliases2;
        for (String str : as) {
            checkName(str);
        }
        this.name = canonicalName;
        this.aliases = as;
    }

    public final String name() {
        return this.name;
    }

    public final Set<String> aliases() {
        Set<String> set = this.aliasSet;
        if (set != null) {
            return set;
        }
        int n = this.aliases.length;
        HashSet<String> hs = new HashSet<>(n);
        for (int i = 0; i < n; i++) {
            hs.add(this.aliases[i]);
        }
        this.aliasSet = Collections.unmodifiableSet(hs);
        return this.aliasSet;
    }

    public String displayName() {
        return this.name;
    }

    public final boolean isRegistered() {
        return !this.name.startsWith("X-") && !this.name.startsWith("x-");
    }

    public String displayName(Locale locale) {
        return this.name;
    }

    public boolean canEncode() {
        return true;
    }

    public final CharBuffer decode(ByteBuffer bb) {
        try {
            return ThreadLocalCoders.decoderFor(this).onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE).decode(bb);
        } catch (CharacterCodingException x) {
            throw new Error(x);
        }
    }

    public final ByteBuffer encode(CharBuffer cb) {
        try {
            return ThreadLocalCoders.encoderFor(this).onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE).encode(cb);
        } catch (CharacterCodingException x) {
            throw new Error(x);
        }
    }

    public final ByteBuffer encode(String str) {
        return encode(CharBuffer.wrap(str));
    }

    public final int compareTo(Charset that) {
        return name().compareToIgnoreCase(that.name());
    }

    public final int hashCode() {
        return name().hashCode();
    }

    public final boolean equals(Object ob) {
        if (!(ob instanceof Charset)) {
            return false;
        }
        if (this == ob) {
            return true;
        }
        return this.name.equals(((Charset) ob).name());
    }

    public final String toString() {
        return name();
    }
}
