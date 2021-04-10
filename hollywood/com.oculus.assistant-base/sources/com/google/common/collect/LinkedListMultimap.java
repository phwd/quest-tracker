package com.google.common.collect;

import X.AbstractC1156tw;
import X.AbstractC1169uQ;
import X.C1168uP;
import X.UB;
import X.UC;
import X.UF;
import X.UH;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class LinkedListMultimap extends AbstractC1156tw implements AbstractC1169uQ, Serializable {
    public static final long serialVersionUID = 0;
    public transient int A00;
    public transient int A01;
    public transient C1168uP A02;
    public transient C1168uP A03;
    public transient Map A04 = new CompactHashMap(12);

    @Override // X.UK
    public final void clear() {
        this.A02 = null;
        this.A03 = null;
        this.A04.clear();
        this.A01 = 0;
        this.A00++;
    }

    public static C1168uP A00(LinkedListMultimap linkedListMultimap, Object obj, Object obj2, C1168uP uPVar) {
        C1168uP uPVar2 = new C1168uP(obj, obj2);
        if (linkedListMultimap.A02 == null) {
            linkedListMultimap.A03 = uPVar2;
            linkedListMultimap.A02 = uPVar2;
        } else {
            if (uPVar == null) {
                C1168uP uPVar3 = linkedListMultimap.A03;
                uPVar3.A02 = uPVar2;
                uPVar2.A03 = uPVar3;
                linkedListMultimap.A03 = uPVar2;
                UF uf = (UF) linkedListMultimap.A04.get(obj);
                if (uf != null) {
                    uf.A00++;
                    C1168uP uPVar4 = uf.A02;
                    uPVar4.A00 = uPVar2;
                    uPVar2.A01 = uPVar4;
                    uf.A02 = uPVar2;
                }
            } else {
                ((UF) linkedListMultimap.A04.get(obj)).A00++;
                uPVar2.A03 = uPVar.A03;
                uPVar2.A01 = uPVar.A01;
                uPVar2.A02 = uPVar;
                uPVar2.A00 = uPVar;
                C1168uP uPVar5 = uPVar.A01;
                if (uPVar5 == null) {
                    ((UF) linkedListMultimap.A04.get(obj)).A01 = uPVar2;
                } else {
                    uPVar5.A00 = uPVar2;
                }
                C1168uP uPVar6 = uPVar.A03;
                if (uPVar6 == null) {
                    linkedListMultimap.A02 = uPVar2;
                } else {
                    uPVar6.A02 = uPVar2;
                }
                uPVar.A03 = uPVar2;
                uPVar.A01 = uPVar2;
            }
            linkedListMultimap.A01++;
            return uPVar2;
        }
        linkedListMultimap.A04.put(obj, new UF(uPVar2));
        linkedListMultimap.A00++;
        linkedListMultimap.A01++;
        return uPVar2;
    }

    public static void A01(LinkedListMultimap linkedListMultimap, C1168uP uPVar) {
        C1168uP uPVar2 = uPVar.A03;
        if (uPVar2 != null) {
            uPVar2.A02 = uPVar.A02;
        } else {
            linkedListMultimap.A02 = uPVar.A02;
        }
        C1168uP uPVar3 = uPVar.A02;
        if (uPVar3 != null) {
            uPVar3.A03 = uPVar2;
        } else {
            linkedListMultimap.A03 = uPVar2;
        }
        if (uPVar.A01 == null && uPVar.A00 == null) {
            ((UF) linkedListMultimap.A04.remove(uPVar.A05)).A00 = 0;
            linkedListMultimap.A00++;
        } else {
            UF uf = (UF) linkedListMultimap.A04.get(uPVar.A05);
            uf.A00--;
            C1168uP uPVar4 = uPVar.A01;
            if (uPVar4 == null) {
                uf.A01 = uPVar.A00;
            } else {
                uPVar4.A00 = uPVar.A00;
            }
            C1168uP uPVar5 = uPVar.A00;
            if (uPVar5 == null) {
                uf.A02 = uPVar4;
            } else {
                uPVar5.A01 = uPVar4;
            }
        }
        linkedListMultimap.A01--;
    }

    @Override // X.UK
    public final /* bridge */ /* synthetic */ Collection A2E(Object obj) {
        return new UC(this, obj);
    }

    @Override // X.UK
    public final /* bridge */ /* synthetic */ Collection A4n(Object obj) {
        UH uh = new UH(this, obj);
        ArrayList arrayList = new ArrayList();
        UB.A01(arrayList, uh);
        List unmodifiableList = Collections.unmodifiableList(arrayList);
        UB.A00(new UH(this, obj));
        return unmodifiableList;
    }

    @Override // X.UK
    public final boolean containsKey(Object obj) {
        return this.A04.containsKey(obj);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        this.A04 = new CompactLinkedHashMap();
        int readInt = objectInputStream.readInt();
        for (int i = 0; i < readInt; i++) {
            A4Y(objectInputStream.readObject(), objectInputStream.readObject());
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeInt(size());
        for (Map.Entry entry : (List) super.A02()) {
            objectOutputStream.writeObject(entry.getKey());
            objectOutputStream.writeObject(entry.getValue());
        }
    }

    @Override // X.AbstractC1156tw
    public final /* bridge */ /* synthetic */ Collection A02() {
        return super.A02();
    }

    @Override // X.UK
    public final int size() {
        return this.A01;
    }
}
