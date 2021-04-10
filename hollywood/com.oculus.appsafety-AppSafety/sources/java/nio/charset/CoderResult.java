package java.nio.charset;

import java.lang.ref.WeakReference;
import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.util.HashMap;
import java.util.Map;

public class CoderResult {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int CR_ERROR_MIN = 2;
    private static final int CR_MALFORMED = 2;
    private static final int CR_OVERFLOW = 1;
    private static final int CR_UNDERFLOW = 0;
    private static final int CR_UNMAPPABLE = 3;
    public static final CoderResult OVERFLOW = new CoderResult(1, 0);
    public static final CoderResult UNDERFLOW = new CoderResult(0, 0);
    private static Cache malformedCache = new Cache() {
        /* class java.nio.charset.CoderResult.AnonymousClass1 */

        @Override // java.nio.charset.CoderResult.Cache
        public CoderResult create(int len) {
            return new CoderResult(2, len);
        }
    };
    private static final String[] names = {"UNDERFLOW", "OVERFLOW", "MALFORMED", "UNMAPPABLE"};
    private static Cache unmappableCache = new Cache() {
        /* class java.nio.charset.CoderResult.AnonymousClass2 */

        @Override // java.nio.charset.CoderResult.Cache
        public CoderResult create(int len) {
            return new CoderResult(3, len);
        }
    };
    private final int length;
    private final int type;

    private CoderResult(int type2, int length2) {
        this.type = type2;
        this.length = length2;
    }

    public String toString() {
        String nm = names[this.type];
        if (!isError()) {
            return nm;
        }
        return nm + "[" + this.length + "]";
    }

    public boolean isUnderflow() {
        return this.type == 0;
    }

    public boolean isOverflow() {
        return this.type == 1;
    }

    public boolean isError() {
        return this.type >= 2;
    }

    public boolean isMalformed() {
        return this.type == 2;
    }

    public boolean isUnmappable() {
        return this.type == 3;
    }

    public int length() {
        if (isError()) {
            return this.length;
        }
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: private */
    public static abstract class Cache {
        private Map<Integer, WeakReference<CoderResult>> cache;

        /* access modifiers changed from: protected */
        public abstract CoderResult create(int i);

        private Cache() {
            this.cache = null;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private synchronized CoderResult get(int len) {
            CoderResult e;
            if (len > 0) {
                Integer k = new Integer(len);
                e = null;
                if (this.cache == null) {
                    this.cache = new HashMap();
                } else {
                    WeakReference<CoderResult> w = this.cache.get(k);
                    if (w != null) {
                        e = w.get();
                    }
                }
                if (e == null) {
                    e = create(len);
                    this.cache.put(k, new WeakReference<>(e));
                }
            } else {
                throw new IllegalArgumentException("Non-positive length");
            }
            return e;
        }
    }

    public static CoderResult malformedForLength(int length2) {
        return malformedCache.get(length2);
    }

    public static CoderResult unmappableForLength(int length2) {
        return unmappableCache.get(length2);
    }

    public void throwException() throws CharacterCodingException {
        int i = this.type;
        if (i == 0) {
            throw new BufferUnderflowException();
        } else if (i == 1) {
            throw new BufferOverflowException();
        } else if (i == 2) {
            throw new MalformedInputException(this.length);
        } else if (i == 3) {
            throw new UnmappableCharacterException(this.length);
        }
    }
}
