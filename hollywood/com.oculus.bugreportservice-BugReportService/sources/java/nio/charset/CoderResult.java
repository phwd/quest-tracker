package java.nio.charset;

import java.lang.ref.WeakReference;
import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.util.HashMap;
import java.util.Map;

public class CoderResult {
    public static final CoderResult OVERFLOW = new CoderResult(1, 0);
    public static final CoderResult UNDERFLOW = new CoderResult(0, 0);
    private static Cache malformedCache = new Cache() {
        /* class java.nio.charset.CoderResult.AnonymousClass1 */

        @Override // java.nio.charset.CoderResult.Cache
        public CoderResult create(int i) {
            return new CoderResult(2, i);
        }
    };
    private static final String[] names = {"UNDERFLOW", "OVERFLOW", "MALFORMED", "UNMAPPABLE"};
    private static Cache unmappableCache = new Cache() {
        /* class java.nio.charset.CoderResult.AnonymousClass2 */

        @Override // java.nio.charset.CoderResult.Cache
        public CoderResult create(int i) {
            return new CoderResult(3, i);
        }
    };
    private final int length;
    private final int type;

    private CoderResult(int i, int i2) {
        this.type = i;
        this.length = i2;
    }

    public String toString() {
        String str = names[this.type];
        if (!isError()) {
            return str;
        }
        return str + "[" + this.length + "]";
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
        private Map cache;

        /* access modifiers changed from: protected */
        public abstract CoderResult create(int i);

        private Cache() {
            this.cache = null;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private synchronized CoderResult get(int i) {
            CoderResult coderResult;
            if (i > 0) {
                Integer num = new Integer(i);
                coderResult = null;
                if (this.cache == null) {
                    this.cache = new HashMap();
                } else {
                    WeakReference weakReference = (WeakReference) this.cache.get(num);
                    if (weakReference != null) {
                        coderResult = (CoderResult) weakReference.get();
                    }
                }
                if (coderResult == null) {
                    coderResult = create(i);
                    this.cache.put(num, new WeakReference(coderResult));
                }
            } else {
                throw new IllegalArgumentException("Non-positive length");
            }
            return coderResult;
        }
    }

    public static CoderResult malformedForLength(int i) {
        return malformedCache.get(i);
    }

    public void throwException() {
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
