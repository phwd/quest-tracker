package com.facebook.hyperthrift;

import X.AnonymousClass08;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class HyperThriftBase {
    public static final Object A04 = new Object();
    public int A00 = -1;
    public int A01 = -1;
    public String A02;
    public Object[] A03;

    public abstract class Builder {
        public int A00 = -1;
        public final Object[] A01;

        public static Object A00(Object obj) {
            if (obj instanceof List) {
                List<Object> list = (List) obj;
                ArrayList arrayList = new ArrayList(list.size());
                for (Object obj2 : list) {
                    arrayList.add(A00(obj2));
                }
                return arrayList;
            } else if (obj instanceof Set) {
                Set<Object> set = (Set) obj;
                HashSet hashSet = new HashSet(set.size());
                for (Object obj3 : set) {
                    hashSet.add(A00(obj3));
                }
                return hashSet;
            } else if (!(obj instanceof Map)) {
                return obj;
            } else {
                Map map = (Map) obj;
                HashMap hashMap = new HashMap(map.size());
                for (Map.Entry entry : map.entrySet()) {
                    hashMap.put(A00(entry.getKey()), A00(entry.getValue()));
                }
                return hashMap;
            }
        }

        public static void A01(Object[] objArr, int i) {
            objArr[i] = A00(objArr[i]);
        }

        public final void A02(int i, Object obj) {
            Object[] objArr = this.A01;
            if (obj == null) {
                obj = HyperThriftBase.A04;
            }
            objArr[i] = obj;
        }

        public final Object[] A03() {
            Object[] objArr = this.A01;
            int length = objArr.length;
            Object[] objArr2 = new Object[length];
            System.arraycopy(objArr, 0, objArr2, 0, length);
            return objArr2;
        }

        public Builder(int i) {
            this.A01 = new Object[i];
        }

        public Builder(HyperThriftBase hyperThriftBase) {
            this.A01 = hyperThriftBase.A03;
        }
    }

    public final Object A00(int i) {
        Object obj = this.A03[i];
        if (obj == A04 || obj == null) {
            return null;
        }
        return obj;
    }

    public final void A01(int i) {
        Object[] objArr = this.A03;
        int length = objArr.length;
        int i2 = -1;
        for (int i3 = 0; i3 < length; i3++) {
            Object obj = objArr[i3];
            if (!(obj == null || obj == A04)) {
                if (i2 < 0) {
                    i2 = i3;
                } else {
                    throw new IllegalStateException(AnonymousClass08.A02("Multiple eligible values for union field: ", i2, ", ", i3));
                }
            }
        }
        this.A01 = i2;
        this.A00 = i;
    }

    public final void A02(String str, Object[] objArr) {
        this.A02 = str;
        this.A03 = objArr;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof HyperThriftBase) {
            HyperThriftBase hyperThriftBase = (HyperThriftBase) obj;
            if (this.A02.equals(hyperThriftBase.A02)) {
                return Arrays.deepEquals(this.A03, hyperThriftBase.A03);
            }
        }
        return false;
    }

    public final int hashCode() {
        int i;
        int hashCode = this.A02.hashCode();
        Object[] objArr = this.A03;
        for (Object obj : objArr) {
            int i2 = hashCode * 31;
            if (obj != null) {
                i = obj.hashCode();
            } else {
                i = 0;
            }
            hashCode = i2 + i;
        }
        return hashCode;
    }

    public final String toString() {
        String obj;
        StringBuilder sb = new StringBuilder();
        sb.append(this.A02);
        sb.append('{');
        int length = this.A03.length;
        for (int i = 0; i < length; i++) {
            Object obj2 = this.A03[i];
            if (obj2 != null) {
                if (i > 0) {
                    sb.append(',');
                }
                sb.append(i);
                sb.append(':');
                if (obj2 == A04) {
                    obj = "null";
                } else {
                    obj = obj2.toString();
                }
                sb.append(obj);
            }
        }
        sb.append('}');
        return sb.toString();
    }
}
