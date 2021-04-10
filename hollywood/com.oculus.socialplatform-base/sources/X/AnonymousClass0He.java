package X;

import com.facebook.common.stringformat.StringFormatUtil;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import javax.annotation.Nullable;

/* renamed from: X.0He  reason: invalid class name */
public final class AnonymousClass0He<E> extends AbstractSet<E> implements Set<E>, Cloneable, Serializable {
    public static final Object A03 = new AnonymousClass0Hc();
    public static final Object[] A04 = new Object[0];
    public static final long serialVersionUID = 0;
    public transient int A00 = 0;
    public transient int A01 = 0;
    public transient Object[] A02;
    public int mSize = 0;

    public AnonymousClass0He() {
        Object[] objArr;
        float f = ((float) 0) / 0.75f;
        int i = (int) f;
        if (i >= 0) {
            if (i == 0) {
                objArr = A04;
            } else {
                objArr = new Object[i];
            }
            this.A02 = objArr;
            return;
        }
        throw new RuntimeException(StringFormatUtil.formatStrLocaleSafe("adjustedCapacity = %d, capacity = %d, LOAD_FACTOR = %s, (capacity / LOAD_FACTOR) = %s", Integer.valueOf(i), 0, Float.toString(0.75f), Float.toString(f)));
    }

    public static int A02(Object obj, Object[] objArr) {
        int length = objArr.length;
        int A012 = A01(obj, length);
        int i = A012;
        do {
            Object obj2 = objArr[i];
            if (obj2 == null || obj2 == obj || obj2.equals(obj)) {
                return i;
            }
            i++;
            if (i == length) {
                i = 0;
                continue;
            }
        } while (i != A012);
        return i;
    }

    public final void clear() {
        this.mSize = 0;
        Arrays.fill(this.A02, (Object) null);
        this.A00++;
    }

    public final boolean equals(Object obj) {
        if (obj != this) {
            if (obj instanceof Set) {
                Set set = (Set) obj;
                if (size() == set.size()) {
                    Object[] objArr = this.A02;
                    for (Object obj2 : objArr) {
                        if (obj2 == null || set.contains(obj2)) {
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

    public final int hashCode() {
        int A002 = A00(this, -1);
        int i = 0;
        while (A002 >= 0) {
            i += this.A02[A002].hashCode();
            A002 = A00(this, A002);
        }
        return i;
    }

    public static final int A00(AnonymousClass0He r2, int i) {
        Object[] objArr;
        do {
            i++;
            objArr = r2.A02;
            if (i >= objArr.length) {
                return Integer.MIN_VALUE;
            }
        } while (objArr[i] == null);
        return i;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean add(@Nullable E e) {
        if (this.mSize >= this.A01) {
            this.A00++;
            Object[] objArr = this.A02;
            int length = objArr.length;
            int i = length << 1;
            if (i == 0) {
                i = 2;
            }
            Object[] objArr2 = new Object[i];
            for (Object obj : objArr) {
                if (obj != null) {
                    objArr2[A02(obj, objArr2)] = obj;
                }
            }
            this.A02 = objArr2;
            this.A01 = (int) (((float) i) * 0.75f);
        }
        if (e == null) {
            e = (E) A03;
        }
        int length2 = this.A02.length;
        int A012 = A01(e, length2);
        while (true) {
            Object[] objArr3 = this.A02;
            Object obj2 = objArr3[A012];
            if (obj2 == null) {
                this.mSize++;
                this.A00++;
                objArr3[A012] = e;
                return true;
            } else if (obj2 == e || obj2.equals(e)) {
                return false;
            } else {
                A012++;
                if (A012 == length2) {
                    A012 = 0;
                }
            }
        }
        return false;
    }

    public final boolean contains(@Nullable Object obj) {
        Object[] objArr = this.A02;
        if (objArr.length == 0) {
            return false;
        }
        if (obj == null) {
            obj = A03;
        }
        if (objArr[A02(obj, objArr)] != null) {
            return true;
        }
        return false;
    }

    public final boolean isEmpty() {
        if (this.mSize == 0) {
            return true;
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
    public final Iterator<E> iterator() {
        return new C00570Hd(this, this);
    }

    public final boolean remove(@Nullable Object obj) {
        Object obj2;
        Object[] objArr = this.A02;
        if (objArr.length != 0) {
            if (obj == null) {
                obj = A03;
            }
            int A022 = A02(obj, objArr);
            Object[] objArr2 = this.A02;
            if (objArr2[A022] != null) {
                int length = objArr2.length;
                while (true) {
                    while (true) {
                        A022++;
                        if (A022 >= length) {
                            A022 = 0;
                        }
                        Object[] objArr3 = this.A02;
                        obj2 = objArr3[A022];
                        if (obj2 != null) {
                            int A012 = A01(obj2, length);
                            boolean z = false;
                            if (A012 > A022) {
                                z = true;
                            }
                            if (A022 >= A022) {
                                if (!z) {
                                    break;
                                }
                            } else if (z) {
                                continue;
                            }
                            if (A012 > A022) {
                                break;
                            }
                        } else {
                            this.A00++;
                            this.mSize--;
                            objArr3[A022] = null;
                            return true;
                        }
                    }
                    this.A02[A022] = obj2;
                }
            }
        }
        return false;
    }

    public static int A01(Object obj, int i) {
        int hashCode = obj.hashCode();
        int i2 = hashCode + ((hashCode << 15) ^ -12931);
        int i3 = i2 ^ (i2 >>> 10);
        int i4 = i3 + (i3 << 3);
        int i5 = i4 ^ (i4 >>> 6);
        int i6 = i5 + (i5 << 2) + (i5 << 14);
        return ((i6 ^ (i6 >>> 16)) & Integer.MAX_VALUE) % i;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: X.0He<E> */
    /* JADX WARN: Multi-variable type inference failed */
    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        int readInt = objectInputStream.readInt();
        objectInputStream.readFloat();
        this.A02 = new Object[readInt];
        this.mSize = 0;
        int readInt2 = objectInputStream.readInt();
        for (int i = 0; i < readInt2; i++) {
            add(objectInputStream.readObject());
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeInt(this.A02.length);
        objectOutputStream.writeFloat(0.75f);
        objectOutputStream.writeInt(size());
        int A002 = A00(this, -1);
        while (A002 >= 0) {
            Object obj = this.A02[A002];
            if (obj == A03) {
                obj = null;
            }
            objectOutputStream.writeObject(obj);
            A002 = A00(this, A002);
        }
    }

    @Override // java.lang.Object
    public final Object clone() {
        try {
            AnonymousClass0He r4 = (AnonymousClass0He) super.clone();
            Object[] objArr = new Object[this.A02.length];
            r4.A02 = objArr;
            Object[] objArr2 = this.A02;
            System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
            return r4;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final int size() {
        return this.mSize;
    }

    public final String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder("{");
        boolean z = true;
        int A002 = A00(this, -1);
        while (A002 >= 0) {
            Object obj = this.A02[A002];
            if (!z) {
                sb.append(", ");
            }
            if (obj == A03) {
                obj = "null";
            }
            sb.append(obj);
            z = false;
            A002 = A00(this, A002);
        }
        sb.append('}');
        return sb.toString();
    }

    public final Object[] toArray() {
        int size = size();
        if (size == 0) {
            return A04;
        }
        Object[] objArr = new Object[size];
        int i = 0;
        int A002 = A00(this, -1);
        while (A002 >= 0) {
            int i2 = i + 1;
            Object obj = this.A02[A002];
            if (obj == A03) {
                obj = null;
            }
            objArr[i] = obj;
            A002 = A00(this, A002);
            i = i2;
        }
        return objArr;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r7v7, resolved type: T[] */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final <T> T[] toArray(T[] tArr) {
        int size = size();
        if (tArr.length < size) {
            tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), size));
        }
        int i = 0;
        int A002 = A00(this, -1);
        while (A002 >= 0) {
            int i2 = i + 1;
            Object obj = this.A02[A002];
            if (obj == A03) {
                obj = null;
            }
            tArr[i] = obj;
            A002 = A00(this, A002);
            i = i2;
        }
        if (tArr.length > size) {
            tArr[size] = null;
        }
        return tArr;
    }
}
