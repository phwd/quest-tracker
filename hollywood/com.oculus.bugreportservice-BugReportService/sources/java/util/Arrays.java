package java.util;

import java.io.Serializable;
import java.lang.reflect.Array;

public class Arrays {
    private static void rangeCheck(int i, int i2, int i3) {
        if (i2 > i3) {
            throw new IllegalArgumentException("fromIndex(" + i2 + ") > toIndex(" + i3 + ")");
        } else if (i2 < 0) {
            throw new ArrayIndexOutOfBoundsException(i2);
        } else if (i3 > i) {
            throw new ArrayIndexOutOfBoundsException(i3);
        }
    }

    public static void sort(long[] jArr) {
        DualPivotQuicksort.sort(jArr, 0, jArr.length - 1, null, 0, 0);
    }

    public static void sort(Object[] objArr) {
        ComparableTimSort.sort(objArr, 0, objArr.length, null, 0, 0);
    }

    public static void sort(Object[] objArr, int i, int i2) {
        rangeCheck(objArr.length, i, i2);
        ComparableTimSort.sort(objArr, i, i2, null, 0, 0);
    }

    public static void sort(Object[] objArr, Comparator comparator) {
        if (comparator == null) {
            sort(objArr);
        } else {
            TimSort.sort(objArr, 0, objArr.length, comparator, null, 0, 0);
        }
    }

    public static void sort(Object[] objArr, int i, int i2, Comparator comparator) {
        if (comparator == null) {
            sort(objArr, i, i2);
            return;
        }
        rangeCheck(objArr.length, i, i2);
        TimSort.sort(objArr, i, i2, comparator, null, 0, 0);
    }

    public static int binarySearch(long[] jArr, long j) {
        return binarySearch0(jArr, 0, jArr.length, j);
    }

    private static int binarySearch0(long[] jArr, int i, int i2, long j) {
        int i3 = i2 - 1;
        while (i <= i3) {
            int i4 = (i + i3) >>> 1;
            int i5 = (jArr[i4] > j ? 1 : (jArr[i4] == j ? 0 : -1));
            if (i5 < 0) {
                i = i4 + 1;
            } else if (i5 <= 0) {
                return i4;
            } else {
                i3 = i4 - 1;
            }
        }
        return -(i + 1);
    }

    public static int binarySearch(int[] iArr, int i, int i2, int i3) {
        rangeCheck(iArr.length, i, i2);
        return binarySearch0(iArr, i, i2, i3);
    }

    private static int binarySearch0(int[] iArr, int i, int i2, int i3) {
        int i4 = i2 - 1;
        while (i <= i4) {
            int i5 = (i + i4) >>> 1;
            int i6 = iArr[i5];
            if (i6 < i3) {
                i = i5 + 1;
            } else if (i6 <= i3) {
                return i5;
            } else {
                i4 = i5 - 1;
            }
        }
        return -(i + 1);
    }

    public static int binarySearch(Object[] objArr, Object obj) {
        return binarySearch0(objArr, 0, objArr.length, obj);
    }

    private static int binarySearch0(Object[] objArr, int i, int i2, Object obj) {
        int i3 = i2 - 1;
        while (i <= i3) {
            int i4 = (i + i3) >>> 1;
            int compareTo = ((Comparable) objArr[i4]).compareTo(obj);
            if (compareTo < 0) {
                i = i4 + 1;
            } else if (compareTo <= 0) {
                return i4;
            } else {
                i3 = i4 - 1;
            }
        }
        return -(i + 1);
    }

    public static int binarySearch(Object[] objArr, Object obj, Comparator comparator) {
        return binarySearch0(objArr, 0, objArr.length, obj, comparator);
    }

    private static int binarySearch0(Object[] objArr, int i, int i2, Object obj, Comparator comparator) {
        if (comparator == null) {
            return binarySearch0(objArr, i, i2, obj);
        }
        int i3 = i2 - 1;
        while (i <= i3) {
            int i4 = (i + i3) >>> 1;
            int compare = comparator.compare(objArr[i4], obj);
            if (compare < 0) {
                i = i4 + 1;
            } else if (compare <= 0) {
                return i4;
            } else {
                i3 = i4 - 1;
            }
        }
        return -(i + 1);
    }

    public static boolean equals(long[] jArr, long[] jArr2) {
        int length;
        if (jArr == jArr2) {
            return true;
        }
        if (jArr == null || jArr2 == null || jArr2.length != (length = jArr.length)) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (jArr[i] != jArr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static boolean equals(int[] iArr, int[] iArr2) {
        int length;
        if (iArr == iArr2) {
            return true;
        }
        if (iArr == null || iArr2 == null || iArr2.length != (length = iArr.length)) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (iArr[i] != iArr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static boolean equals(short[] sArr, short[] sArr2) {
        int length;
        if (sArr == sArr2) {
            return true;
        }
        if (sArr == null || sArr2 == null || sArr2.length != (length = sArr.length)) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (sArr[i] != sArr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static boolean equals(char[] cArr, char[] cArr2) {
        int length;
        if (cArr == cArr2) {
            return true;
        }
        if (cArr == null || cArr2 == null || cArr2.length != (length = cArr.length)) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (cArr[i] != cArr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static boolean equals(byte[] bArr, byte[] bArr2) {
        int length;
        if (bArr == bArr2) {
            return true;
        }
        if (bArr == null || bArr2 == null || bArr2.length != (length = bArr.length)) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (bArr[i] != bArr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static boolean equals(boolean[] zArr, boolean[] zArr2) {
        int length;
        if (zArr == zArr2) {
            return true;
        }
        if (zArr == null || zArr2 == null || zArr2.length != (length = zArr.length)) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (zArr[i] != zArr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static boolean equals(double[] dArr, double[] dArr2) {
        int length;
        if (dArr == dArr2) {
            return true;
        }
        if (dArr == null || dArr2 == null || dArr2.length != (length = dArr.length)) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (Double.doubleToLongBits(dArr[i]) != Double.doubleToLongBits(dArr2[i])) {
                return false;
            }
        }
        return true;
    }

    public static boolean equals(float[] fArr, float[] fArr2) {
        int length;
        if (fArr == fArr2) {
            return true;
        }
        if (fArr == null || fArr2 == null || fArr2.length != (length = fArr.length)) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (Float.floatToIntBits(fArr[i]) != Float.floatToIntBits(fArr2[i])) {
                return false;
            }
        }
        return true;
    }

    public static boolean equals(Object[] objArr, Object[] objArr2) {
        int length;
        if (objArr == objArr2) {
            return true;
        }
        if (objArr == null || objArr2 == null || objArr2.length != (length = objArr.length)) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            Object obj = objArr[i];
            Object obj2 = objArr2[i];
            if (obj == null) {
                if (obj2 == null) {
                }
            } else if (obj.equals(obj2)) {
            }
            return false;
        }
        return true;
    }

    public static void fill(long[] jArr, long j) {
        int length = jArr.length;
        for (int i = 0; i < length; i++) {
            jArr[i] = j;
        }
    }

    public static void fill(int[] iArr, int i) {
        int length = iArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = i;
        }
    }

    public static void fill(int[] iArr, int i, int i2, int i3) {
        rangeCheck(iArr.length, i, i2);
        while (i < i2) {
            iArr[i] = i3;
            i++;
        }
    }

    public static void fill(char[] cArr, char c) {
        int length = cArr.length;
        for (int i = 0; i < length; i++) {
            cArr[i] = c;
        }
    }

    public static void fill(char[] cArr, int i, int i2, char c) {
        rangeCheck(cArr.length, i, i2);
        while (i < i2) {
            cArr[i] = c;
            i++;
        }
    }

    public static void fill(byte[] bArr, int i, int i2, byte b) {
        rangeCheck(bArr.length, i, i2);
        while (i < i2) {
            bArr[i] = b;
            i++;
        }
    }

    public static void fill(Object[] objArr, Object obj) {
        int length = objArr.length;
        for (int i = 0; i < length; i++) {
            objArr[i] = obj;
        }
    }

    public static Object[] copyOf(Object[] objArr, int i) {
        return copyOf(objArr, i, objArr.getClass());
    }

    public static Object[] copyOf(Object[] objArr, int i, Class cls) {
        Object[] objArr2;
        if (cls == Object[].class) {
            objArr2 = new Object[i];
        } else {
            objArr2 = (Object[]) Array.newInstance(cls.getComponentType(), i);
        }
        System.arraycopy(objArr, 0, objArr2, 0, Math.min(objArr.length, i));
        return objArr2;
    }

    public static byte[] copyOf(byte[] bArr, int i) {
        byte[] bArr2 = new byte[i];
        System.arraycopy(bArr, 0, bArr2, 0, Math.min(bArr.length, i));
        return bArr2;
    }

    public static int[] copyOf(int[] iArr, int i) {
        int[] iArr2 = new int[i];
        System.arraycopy(iArr, 0, iArr2, 0, Math.min(iArr.length, i));
        return iArr2;
    }

    public static long[] copyOf(long[] jArr, int i) {
        long[] jArr2 = new long[i];
        System.arraycopy(jArr, 0, jArr2, 0, Math.min(jArr.length, i));
        return jArr2;
    }

    public static char[] copyOf(char[] cArr, int i) {
        char[] cArr2 = new char[i];
        System.arraycopy((Object) cArr, 0, (Object) cArr2, 0, Math.min(cArr.length, i));
        return cArr2;
    }

    public static Object[] copyOfRange(Object[] objArr, int i, int i2) {
        return copyOfRange(objArr, i, i2, objArr.getClass());
    }

    public static Object[] copyOfRange(Object[] objArr, int i, int i2, Class cls) {
        Object[] objArr2;
        int i3 = i2 - i;
        if (i3 >= 0) {
            if (cls == Object[].class) {
                objArr2 = new Object[i3];
            } else {
                objArr2 = (Object[]) Array.newInstance(cls.getComponentType(), i3);
            }
            System.arraycopy(objArr, i, objArr2, 0, Math.min(objArr.length - i, i3));
            return objArr2;
        }
        throw new IllegalArgumentException(i + " > " + i2);
    }

    public static char[] copyOfRange(char[] cArr, int i, int i2) {
        int i3 = i2 - i;
        if (i3 >= 0) {
            char[] cArr2 = new char[i3];
            System.arraycopy((Object) cArr, i, (Object) cArr2, 0, Math.min(cArr.length - i, i3));
            return cArr2;
        }
        throw new IllegalArgumentException(i + " > " + i2);
    }

    public static List asList(Object... objArr) {
        return new ArrayList(objArr);
    }

    private static class ArrayList extends AbstractList implements RandomAccess, Serializable {
        private static final long serialVersionUID = -2764017481108945198L;
        private final Object[] a;

        ArrayList(Object[] objArr) {
            this.a = (Object[]) Objects.requireNonNull(objArr);
        }

        @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
        public int size() {
            return this.a.length;
        }

        @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
        public Object[] toArray() {
            return (Object[]) this.a.clone();
        }

        @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
        public Object[] toArray(Object[] objArr) {
            int size = size();
            if (objArr.length < size) {
                return Arrays.copyOf(this.a, size, objArr.getClass());
            }
            System.arraycopy(this.a, 0, objArr, 0, size);
            if (objArr.length > size) {
                objArr[size] = null;
            }
            return objArr;
        }

        @Override // java.util.List, java.util.AbstractList
        public Object get(int i) {
            return this.a[i];
        }

        @Override // java.util.List, java.util.AbstractList
        public Object set(int i, Object obj) {
            Object[] objArr = this.a;
            Object obj2 = objArr[i];
            objArr[i] = obj;
            return obj2;
        }

        @Override // java.util.List, java.util.AbstractList
        public int indexOf(Object obj) {
            Object[] objArr = this.a;
            int i = 0;
            if (obj == null) {
                while (i < objArr.length) {
                    if (objArr[i] == null) {
                        return i;
                    }
                    i++;
                }
                return -1;
            }
            while (i < objArr.length) {
                if (obj.equals(objArr[i])) {
                    return i;
                }
                i++;
            }
            return -1;
        }

        @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
        public boolean contains(Object obj) {
            return indexOf(obj) != -1;
        }

        @Override // java.util.List
        public void sort(Comparator comparator) {
            Arrays.sort(this.a, comparator);
        }
    }

    public static int hashCode(long[] jArr) {
        if (jArr == null) {
            return 0;
        }
        int i = 1;
        for (long j : jArr) {
            i = (i * 31) + ((int) (j ^ (j >>> 32)));
        }
        return i;
    }

    public static int hashCode(int[] iArr) {
        if (iArr == null) {
            return 0;
        }
        int i = 1;
        for (int i2 : iArr) {
            i = (i * 31) + i2;
        }
        return i;
    }

    public static int hashCode(char[] cArr) {
        if (cArr == null) {
            return 0;
        }
        int i = 1;
        for (char c : cArr) {
            i = (i * 31) + c;
        }
        return i;
    }

    public static int hashCode(byte[] bArr) {
        if (bArr == null) {
            return 0;
        }
        int i = 1;
        for (byte b : bArr) {
            i = (i * 31) + b;
        }
        return i;
    }

    public static int hashCode(Object[] objArr) {
        if (objArr == null) {
            return 0;
        }
        int i = 1;
        for (Object obj : objArr) {
            i = (i * 31) + (obj == null ? 0 : obj.hashCode());
        }
        return i;
    }

    public static boolean deepEquals(Object[] objArr, Object[] objArr2) {
        int length;
        if (objArr == objArr2) {
            return true;
        }
        if (objArr == null || objArr2 == null || objArr2.length != (length = objArr.length)) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            Object obj = objArr[i];
            Object obj2 = objArr2[i];
            if (obj != obj2 && (obj == null || !deepEquals0(obj, obj2))) {
                return false;
            }
        }
        return true;
    }

    static boolean deepEquals0(Object obj, Object obj2) {
        if ((obj instanceof Object[]) && (obj2 instanceof Object[])) {
            return deepEquals((Object[]) obj, (Object[]) obj2);
        }
        if ((obj instanceof byte[]) && (obj2 instanceof byte[])) {
            return equals((byte[]) obj, (byte[]) obj2);
        }
        if ((obj instanceof short[]) && (obj2 instanceof short[])) {
            return equals((short[]) obj, (short[]) obj2);
        }
        if ((obj instanceof int[]) && (obj2 instanceof int[])) {
            return equals((int[]) obj, (int[]) obj2);
        }
        if ((obj instanceof long[]) && (obj2 instanceof long[])) {
            return equals((long[]) obj, (long[]) obj2);
        }
        if ((obj instanceof char[]) && (obj2 instanceof char[])) {
            return equals((char[]) obj, (char[]) obj2);
        }
        if ((obj instanceof float[]) && (obj2 instanceof float[])) {
            return equals((float[]) obj, (float[]) obj2);
        }
        if ((obj instanceof double[]) && (obj2 instanceof double[])) {
            return equals((double[]) obj, (double[]) obj2);
        }
        if (!(obj instanceof boolean[]) || !(obj2 instanceof boolean[])) {
            return obj.equals(obj2);
        }
        return equals((boolean[]) obj, (boolean[]) obj2);
    }

    public static String toString(long[] jArr) {
        if (jArr == null) {
            return "null";
        }
        int length = jArr.length - 1;
        if (length == -1) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        int i = 0;
        while (true) {
            sb.append(jArr[i]);
            if (i == length) {
                sb.append(']');
                return sb.toString();
            }
            sb.append(", ");
            i++;
        }
    }

    public static String toString(int[] iArr) {
        if (iArr == null) {
            return "null";
        }
        int length = iArr.length - 1;
        if (length == -1) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        int i = 0;
        while (true) {
            sb.append(iArr[i]);
            if (i == length) {
                sb.append(']');
                return sb.toString();
            }
            sb.append(", ");
            i++;
        }
    }

    public static String toString(short[] sArr) {
        if (sArr == null) {
            return "null";
        }
        int length = sArr.length - 1;
        if (length == -1) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        int i = 0;
        while (true) {
            sb.append((int) sArr[i]);
            if (i == length) {
                sb.append(']');
                return sb.toString();
            }
            sb.append(", ");
            i++;
        }
    }

    public static String toString(char[] cArr) {
        if (cArr == null) {
            return "null";
        }
        int length = cArr.length - 1;
        if (length == -1) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        int i = 0;
        while (true) {
            sb.append(cArr[i]);
            if (i == length) {
                sb.append(']');
                return sb.toString();
            }
            sb.append(", ");
            i++;
        }
    }

    public static String toString(byte[] bArr) {
        if (bArr == null) {
            return "null";
        }
        int length = bArr.length - 1;
        if (length == -1) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        int i = 0;
        while (true) {
            sb.append((int) bArr[i]);
            if (i == length) {
                sb.append(']');
                return sb.toString();
            }
            sb.append(", ");
            i++;
        }
    }

    public static String toString(boolean[] zArr) {
        if (zArr == null) {
            return "null";
        }
        int length = zArr.length - 1;
        if (length == -1) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        int i = 0;
        while (true) {
            sb.append(zArr[i]);
            if (i == length) {
                sb.append(']');
                return sb.toString();
            }
            sb.append(", ");
            i++;
        }
    }

    public static String toString(float[] fArr) {
        if (fArr == null) {
            return "null";
        }
        int length = fArr.length - 1;
        if (length == -1) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        int i = 0;
        while (true) {
            sb.append(fArr[i]);
            if (i == length) {
                sb.append(']');
                return sb.toString();
            }
            sb.append(", ");
            i++;
        }
    }

    public static String toString(double[] dArr) {
        if (dArr == null) {
            return "null";
        }
        int length = dArr.length - 1;
        if (length == -1) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        int i = 0;
        while (true) {
            sb.append(dArr[i]);
            if (i == length) {
                sb.append(']');
                return sb.toString();
            }
            sb.append(", ");
            i++;
        }
    }

    public static String toString(Object[] objArr) {
        if (objArr == null) {
            return "null";
        }
        int length = objArr.length - 1;
        if (length == -1) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        int i = 0;
        while (true) {
            sb.append(String.valueOf(objArr[i]));
            if (i == length) {
                sb.append(']');
                return sb.toString();
            }
            sb.append(", ");
            i++;
        }
    }
}
