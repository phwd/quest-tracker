package sun.nio.cs;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

public class ThreadLocalCoders {
    private static Cache decoderCache = new Cache(3) {
        /* class sun.nio.cs.ThreadLocalCoders.AnonymousClass1 */

        /* access modifiers changed from: package-private */
        @Override // sun.nio.cs.ThreadLocalCoders.Cache
        public boolean hasName(Object obj, Object obj2) {
            if (obj2 instanceof String) {
                return ((CharsetDecoder) obj).charset().name().equals(obj2);
            }
            if (obj2 instanceof Charset) {
                return ((CharsetDecoder) obj).charset().equals(obj2);
            }
            return false;
        }

        /* access modifiers changed from: package-private */
        @Override // sun.nio.cs.ThreadLocalCoders.Cache
        public Object create(Object obj) {
            if (obj instanceof String) {
                return Charset.forName((String) obj).newDecoder();
            }
            if (obj instanceof Charset) {
                return ((Charset) obj).newDecoder();
            }
            return null;
        }
    };
    private static Cache encoderCache = new Cache(3) {
        /* class sun.nio.cs.ThreadLocalCoders.AnonymousClass2 */

        /* access modifiers changed from: package-private */
        @Override // sun.nio.cs.ThreadLocalCoders.Cache
        public boolean hasName(Object obj, Object obj2) {
            if (obj2 instanceof String) {
                return ((CharsetEncoder) obj).charset().name().equals(obj2);
            }
            if (obj2 instanceof Charset) {
                return ((CharsetEncoder) obj).charset().equals(obj2);
            }
            return false;
        }

        /* access modifiers changed from: package-private */
        @Override // sun.nio.cs.ThreadLocalCoders.Cache
        public Object create(Object obj) {
            if (obj instanceof String) {
                return Charset.forName((String) obj).newEncoder();
            }
            if (obj instanceof Charset) {
                return ((Charset) obj).newEncoder();
            }
            return null;
        }
    };

    /* access modifiers changed from: private */
    public static abstract class Cache {
        private ThreadLocal cache = new ThreadLocal();
        private final int size;

        /* access modifiers changed from: package-private */
        public abstract Object create(Object obj);

        /* access modifiers changed from: package-private */
        public abstract boolean hasName(Object obj, Object obj2);

        Cache(int i) {
            this.size = i;
        }

        private void moveToFront(Object[] objArr, int i) {
            Object obj = objArr[i];
            while (i > 0) {
                objArr[i] = objArr[i - 1];
                i--;
            }
            objArr[0] = obj;
        }

        /* access modifiers changed from: package-private */
        public Object forName(Object obj) {
            Object[] objArr = (Object[]) this.cache.get();
            if (objArr == null) {
                objArr = new Object[this.size];
                this.cache.set(objArr);
            } else {
                for (int i = 0; i < objArr.length; i++) {
                    Object obj2 = objArr[i];
                    if (obj2 != null && hasName(obj2, obj)) {
                        if (i > 0) {
                            moveToFront(objArr, i);
                        }
                        return obj2;
                    }
                }
            }
            Object create = create(obj);
            objArr[objArr.length - 1] = create;
            moveToFront(objArr, objArr.length - 1);
            return create;
        }
    }

    public static CharsetDecoder decoderFor(Object obj) {
        CharsetDecoder charsetDecoder = (CharsetDecoder) decoderCache.forName(obj);
        charsetDecoder.reset();
        return charsetDecoder;
    }

    public static CharsetEncoder encoderFor(Object obj) {
        CharsetEncoder charsetEncoder = (CharsetEncoder) encoderCache.forName(obj);
        charsetEncoder.reset();
        return charsetEncoder;
    }
}
