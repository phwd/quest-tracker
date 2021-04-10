package com.facebook.graphql.calls;

import X.AbstractC01860Mq;
import X.C01870Mr;
import X.C03170bk;
import X.C03180bl;
import androidx.annotation.Nullable;
import com.facebook.infer.annotation.ReturnsOwnership;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@JsonSerialize(using = GraphQlCallInputSerializer.class)
public abstract class GraphQlCallInput {
    public static final C01870Mr PARAMS_COLLECTION_POOL = new C01870Mr();
    public C01870Mr mParamsCollectionPool = PARAMS_COLLECTION_POOL;
    public C03170bk mPoolableParams = null;

    @Nullable
    private Object A00(Object obj) {
        ArrayList arrayList;
        String str;
        if (obj == null) {
            return null;
        }
        if (obj instanceof C03180bl) {
            ArrayList<Object> arrayList2 = ((C03180bl) obj).A00;
            if (arrayList2.size() > 0 && (arrayList2.get(0) instanceof C03170bk)) {
                arrayList = new ArrayList(arrayList2.size());
                for (int i = 0; i < arrayList2.size(); i++) {
                    if (arrayList2.get(i) != null) {
                        arrayList.add(A00(arrayList2.get(i)));
                    }
                }
            } else if (arrayList2.size() <= 0 || !(arrayList2.get(0) instanceof C03180bl)) {
                arrayList = new ArrayList(arrayList2.size());
                for (int i2 = 0; i2 < arrayList2.size(); i2++) {
                    Object obj2 = arrayList2.get(i2);
                    if (obj2 == null) {
                        str = null;
                        arrayList.add(str);
                    } else if (obj2 instanceof Number) {
                        arrayList.add(obj2);
                    } else {
                        str = obj2.toString();
                        arrayList.add(str);
                    }
                }
            } else {
                arrayList = new ArrayList(arrayList2.size());
                for (int i3 = 0; i3 < arrayList2.size(); i3++) {
                    if (arrayList2.get(i3) != null) {
                        arrayList.add(A00(arrayList2.get(i3)));
                    }
                }
            }
            return arrayList;
        } else if (!(obj instanceof C03170bk)) {
            return obj;
        } else {
            TreeMap treeMap = new TreeMap();
            A02(this, (C03170bk) obj, treeMap);
            return treeMap;
        }
    }

    public static final void A01(GraphQlCallInput graphQlCallInput, C03180bl r6, List list) {
        if (!(list == null || list.isEmpty())) {
            for (Object obj : list) {
                if (obj != null) {
                    if (obj instanceof List) {
                        Iterator it = list.iterator();
                        while (it.hasNext()) {
                            List list2 = (List) it.next();
                            C01870Mr r2 = r6.A01;
                            C03180bl A00 = r2.A01.A00();
                            if (A00 == null) {
                                A00 = new C03180bl(r2.A00);
                            }
                            A00.A03(r2);
                            r6.A04(A00);
                            A01(graphQlCallInput, A00, list2);
                        }
                        return;
                    } else if (obj instanceof String) {
                        for (Object obj2 : list) {
                            C03180bl.A00(r6, obj2);
                        }
                        return;
                    } else if (obj instanceof Number) {
                        for (Object obj3 : list) {
                            C03180bl.A00(r6, obj3);
                        }
                        return;
                    } else if (obj instanceof Enum) {
                        Iterator it2 = list.iterator();
                        while (it2.hasNext()) {
                            C03180bl.A00(r6, ((Enum) it2.next()).toString());
                        }
                        return;
                    } else if (obj instanceof GraphQlCallInput) {
                        Iterator it3 = list.iterator();
                        while (it3.hasNext()) {
                            r6.A04(((GraphQlCallInput) it3.next()).A03());
                        }
                        return;
                    } else if (obj instanceof Map) {
                        Iterator it4 = list.iterator();
                        while (it4.hasNext()) {
                            C03170bk A002 = r6.A01.A00();
                            r6.A04(A002);
                            graphQlCallInput.A04(A002, (Map) it4.next());
                        }
                        return;
                    } else {
                        throw new IllegalArgumentException("List value type is not supported: " + obj.getClass());
                    }
                }
            }
        }
    }

    /* JADX WARN: Incorrect args count in method signature: (LX/0bk;Ljava/util/Map<Ljava/lang/String;Ljava/lang/Object;>;)V */
    public static void A02(GraphQlCallInput graphQlCallInput, C03170bk r6, Map map) {
        if (r6 != null) {
            int i = 0;
            while (true) {
                int i2 = r6.A00;
                if (i < i2) {
                    if (i < 0 || i >= i2) {
                        break;
                    }
                    ArrayList<Object> arrayList = r6.A01;
                    Object obj = arrayList.get(i << 1);
                    if (i >= i2) {
                        break;
                    }
                    map.put(obj, graphQlCallInput.A00(arrayList.get((i << 1) + 1)));
                    i++;
                } else {
                    return;
                }
            }
            throw new ArrayIndexOutOfBoundsException(i);
        }
    }

    @ReturnsOwnership
    public final C03170bk A03() {
        C03170bk r0 = this.mPoolableParams;
        if (r0 != null) {
            return r0;
        }
        C03170bk A00 = this.mParamsCollectionPool.A00();
        this.mPoolableParams = A00;
        return A00;
    }

    public final void A04(C03170bk r5, Map<String, Object> map) {
        String obj;
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value != null) {
                if (value instanceof Boolean) {
                    if (((Boolean) value).booleanValue()) {
                        obj = "true";
                    } else {
                        obj = "false";
                    }
                } else if ((value instanceof Number) || (value instanceof String)) {
                    C03170bk.A00(r5, key, value);
                } else if (value instanceof Enum) {
                    obj = value.toString();
                } else if (value instanceof GraphQlCallInput) {
                    r5.A05(key, ((GraphQlCallInput) value).A03());
                } else if (value instanceof List) {
                    A01(this, r5.A04(key), (List) value);
                } else if (value instanceof Map) {
                    C03170bk A00 = ((AbstractC01860Mq) r5).A01.A00();
                    r5.A05(key, A00);
                    A04(A00, (Map) value);
                } else {
                    throw new IllegalArgumentException("Unexpected object value type " + value.getClass());
                }
                C03170bk.A00(r5, key, obj);
            }
        }
    }

    public final void A05(String str, String str2) {
        C03170bk.A00(A03(), str, str2);
    }
}
