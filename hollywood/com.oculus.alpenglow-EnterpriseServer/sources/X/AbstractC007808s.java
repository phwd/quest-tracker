package X;

import com.facebook.graphservice.tree.TreeJNI;
import com.google.common.collect.ImmutableList;
import java.util.Locale;
import javax.annotation.Nullable;

/* renamed from: X.08s  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC007808s extends C01380Gl implements AbstractC01940Ou {
    public static <T extends Enum<T>> ImmutableList<T> A00(ImmutableList<String> immutableList, T t) {
        Enum r0;
        ImmutableList.Builder builder = new ImmutableList.Builder();
        AnonymousClass0u6<String> it = immutableList.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (next == null) {
                r0 = t;
            } else {
                try {
                    r0 = Enum.valueOf(t.getClass(), next.toUpperCase(Locale.US));
                } catch (IllegalArgumentException unused) {
                    r0 = t;
                }
            }
            builder.add((Object) r0);
        }
        return builder.build();
    }

    @Nullable
    public static final Object A01(AbstractC007808s r2, int i) {
        int fieldCacheIndex;
        if (r2.mFieldCache == null || (fieldCacheIndex = r2.getFieldCacheIndex(i)) < 0) {
            return null;
        }
        return r2.mFieldCache[fieldCacheIndex];
    }

    public static final void A02(AbstractC007808s r2, @Nullable int i, Object obj) {
        int fieldCacheIndex;
        if (r2.mFieldCache != null && (fieldCacheIndex = r2.getFieldCacheIndex(i)) >= 0) {
            Object[] objArr = r2.mFieldCache;
            if (obj == null) {
                obj = C01380Gl.NULL;
            }
            objArr[fieldCacheIndex] = obj;
        }
    }

    @Nullable
    public final <T extends TreeJNI> T A03(int i, Class<T> cls, int i2) {
        Object A01 = A01(this, i);
        if (A01 == null) {
            A01 = getTree(i, cls, i2);
            A02(this, i, A01);
        }
        if (A01 != C01380Gl.NULL) {
            return (T) ((TreeJNI) A01);
        }
        return null;
    }

    public final <T extends TreeJNI> ImmutableList<T> A04(int i, Class<T> cls, int i2) {
        Object A01 = A01(this, i);
        if (A01 == null) {
            A01 = getTreeList(i, cls, i2);
            A02(this, i, A01);
        }
        return (ImmutableList) A01;
    }

    public final <T extends Enum<T>> ImmutableList<T> A05(int i, T t) {
        Object A01 = A01(this, i);
        if (A01 == null) {
            A01 = A00(getStringList(i), t);
            A02(this, i, A01);
        }
        if (A01 instanceof ImmutableList) {
            ImmutableList immutableList = (ImmutableList) A01;
            if (!immutableList.isEmpty() && (immutableList.get(0) instanceof String)) {
                A01 = A00(immutableList, t);
                A02(this, i, A01);
            }
        }
        return (ImmutableList) A01;
    }

    public final <T extends Enum<T>> T A06(int i, T t) {
        Object A01 = A01(this, i);
        if (A01 == null) {
            String string = getString(i);
            if (string == null) {
                A01 = t;
            } else {
                try {
                    A01 = Enum.valueOf(t.getClass(), string.toUpperCase(Locale.US));
                } catch (IllegalArgumentException unused) {
                    A01 = t;
                }
            }
            A02(this, i, A01);
        }
        if (A01 instanceof String) {
            String str = (String) A01;
            if (str == null) {
                A01 = t;
            } else {
                try {
                    A01 = Enum.valueOf(t.getClass(), str.toUpperCase(Locale.US));
                } catch (IllegalArgumentException unused2) {
                    A01 = t;
                }
            }
            A02(this, i, A01);
        }
        return (T) ((Enum) A01);
    }

    @Nullable
    public final String A07(int i) {
        Object A01 = A01(this, i);
        if (A01 == null) {
            A01 = getString(i);
            A02(this, i, A01);
        }
        if (A01 != C01380Gl.NULL) {
            return (String) A01;
        }
        return null;
    }

    public AbstractC007808s(int i, @Nullable int[] iArr) {
        super(i, iArr);
    }
}
