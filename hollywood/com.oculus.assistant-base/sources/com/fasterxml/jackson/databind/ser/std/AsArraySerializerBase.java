package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC0278Pa;
import X.AbstractC0292Po;
import X.AbstractC1012qg;
import X.AbstractC1024qt;
import X.AbstractC1031r2;
import X.AbstractC1044rJ;
import X.C0290Pm;
import X.C1062rc;
import X.O5;
import X.PU;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.ser.impl.IndexedListSerializer;
import com.fasterxml.jackson.databind.ser.impl.IteratorSerializer;
import java.lang.reflect.Modifier;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public abstract class AsArraySerializerBase extends ContainerSerializer implements AbstractC0278Pa {
    public AbstractC0292Po A00;
    public final O5 A01;
    public final AbstractC1024qt A02;
    public final JsonSerializer A03;
    public final PU A04;
    public final boolean A05;

    private final JsonSerializer A0D(AbstractC0292Po po, AbstractC1024qt qtVar, AbstractC1031r2 r2Var) {
        JsonSerializer A08 = r2Var.A08(qtVar, this.A01);
        C0290Pm pm = new C0290Pm(A08, po.A01(qtVar._class, A08));
        AbstractC0292Po po2 = pm.A01;
        if (po != po2) {
            this.A00 = po2;
        }
        return pm.A00;
    }

    private final JsonSerializer A0E(AbstractC0292Po po, Class cls, AbstractC1031r2 r2Var) {
        JsonSerializer A0A = r2Var.A0A(cls, this.A01);
        C0290Pm pm = new C0290Pm(A0A, po.A01(cls, A0A));
        AbstractC0292Po po2 = pm.A01;
        if (po != po2) {
            this.A00 = po2;
        }
        return pm.A00;
    }

    public final void A0F(Object obj, AbstractC1012qg qgVar, AbstractC1031r2 r2Var) {
        if (this instanceof IterableSerializer) {
            Iterator it = ((Iterable) obj).iterator();
            if (it.hasNext()) {
                PU pu = this.A04;
                Class<?> cls = null;
                JsonSerializer jsonSerializer = null;
                do {
                    Object next = it.next();
                    if (next == null) {
                        r2Var.A0D(qgVar);
                    } else {
                        Class<?> cls2 = next.getClass();
                        if (cls2 != cls) {
                            jsonSerializer = r2Var.A0A(cls2, this.A01);
                            cls = cls2;
                        }
                        if (pu == null) {
                            jsonSerializer.A0B(next, qgVar, r2Var);
                        } else {
                            jsonSerializer.A09(next, qgVar, r2Var, pu);
                        }
                    }
                } while (it.hasNext());
            }
        } else if (this instanceof EnumSetSerializer) {
            JsonSerializer jsonSerializer2 = this.A03;
            Iterator it2 = ((AbstractCollection) obj).iterator();
            while (it2.hasNext()) {
                Enum r2 = (Enum) it2.next();
                if (jsonSerializer2 == null) {
                    jsonSerializer2 = r2Var.A0A(r2.getDeclaringClass(), this.A01);
                }
                jsonSerializer2.A0B(r2, qgVar, r2Var);
            }
        } else if (this instanceof CollectionSerializer) {
            Collection collection = (Collection) obj;
            JsonSerializer jsonSerializer3 = this.A03;
            if (jsonSerializer3 != null) {
                Iterator it3 = collection.iterator();
                if (it3.hasNext()) {
                    PU pu2 = this.A04;
                    int i = 0;
                    do {
                        Object next2 = it3.next();
                        if (next2 == null) {
                            try {
                                r2Var.A0D(qgVar);
                            } catch (Exception e) {
                                StdSerializer.A03(r2Var, e, collection, i);
                                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                            }
                        } else if (pu2 == null) {
                            jsonSerializer3.A0B(next2, qgVar, r2Var);
                        } else {
                            jsonSerializer3.A09(next2, qgVar, r2Var, pu2);
                        }
                        i++;
                    } while (it3.hasNext());
                    return;
                }
                return;
            }
            Iterator it4 = collection.iterator();
            if (it4.hasNext()) {
                AbstractC0292Po po = this.A00;
                PU pu3 = this.A04;
                int i2 = 0;
                do {
                    try {
                        Object next3 = it4.next();
                        if (next3 == null) {
                            r2Var.A0D(qgVar);
                        } else {
                            Class<?> cls3 = next3.getClass();
                            JsonSerializer A002 = po.A00(cls3);
                            if (A002 == null) {
                                AbstractC1024qt qtVar = this.A02;
                                if (qtVar.A0F()) {
                                    A002 = A0D(po, r2Var.A03(qtVar, cls3), r2Var);
                                } else {
                                    A002 = A0E(po, cls3, r2Var);
                                }
                                po = this.A00;
                            }
                            if (pu3 == null) {
                                A002.A0B(next3, qgVar, r2Var);
                            } else {
                                A002.A09(next3, qgVar, r2Var, pu3);
                            }
                        }
                        i2++;
                    } catch (Exception e2) {
                        StdSerializer.A03(r2Var, e2, collection, i2);
                        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                    }
                } while (it4.hasNext());
            }
        } else if (!(this instanceof IteratorSerializer)) {
            List list = (List) obj;
            JsonSerializer jsonSerializer4 = this.A03;
            if (jsonSerializer4 != null) {
                int size = list.size();
                if (size != 0) {
                    PU pu4 = this.A04;
                    for (int i3 = 0; i3 < size; i3++) {
                        Object obj2 = list.get(i3);
                        if (obj2 == null) {
                            try {
                                r2Var.A0D(qgVar);
                            } catch (Exception e3) {
                                StdSerializer.A03(r2Var, e3, list, i3);
                                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                            }
                        } else if (pu4 == null) {
                            jsonSerializer4.A0B(obj2, qgVar, r2Var);
                        } else {
                            jsonSerializer4.A09(obj2, qgVar, r2Var, pu4);
                        }
                    }
                    return;
                }
                return;
            }
            PU pu5 = this.A04;
            if (pu5 != null) {
                int size2 = list.size();
                if (size2 != 0) {
                    try {
                        AbstractC0292Po po2 = this.A00;
                        for (int i4 = 0; i4 < size2; i4++) {
                            Object obj3 = list.get(i4);
                            if (obj3 == null) {
                                r2Var.A0D(qgVar);
                            } else {
                                Class<?> cls4 = obj3.getClass();
                                JsonSerializer A003 = po2.A00(cls4);
                                if (A003 == null) {
                                    AbstractC1024qt qtVar2 = this.A02;
                                    if (qtVar2.A0F()) {
                                        A003 = A0D(po2, r2Var.A03(qtVar2, cls4), r2Var);
                                    } else {
                                        A003 = A0E(po2, cls4, r2Var);
                                    }
                                    po2 = this.A00;
                                }
                                A003.A09(obj3, qgVar, r2Var, pu5);
                            }
                        }
                    } catch (Exception e4) {
                        StdSerializer.A03(r2Var, e4, list, 0);
                        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                    }
                }
            } else {
                int size3 = list.size();
                if (size3 != 0) {
                    try {
                        AbstractC0292Po po3 = this.A00;
                        for (int i5 = 0; i5 < size3; i5++) {
                            Object obj4 = list.get(i5);
                            if (obj4 == null) {
                                r2Var.A0D(qgVar);
                            } else {
                                Class<?> cls5 = obj4.getClass();
                                JsonSerializer A004 = po3.A00(cls5);
                                if (A004 == null) {
                                    AbstractC1024qt qtVar3 = this.A02;
                                    if (qtVar3.A0F()) {
                                        A004 = A0D(po3, r2Var.A03(qtVar3, cls5), r2Var);
                                    } else {
                                        A004 = A0E(po3, cls5, r2Var);
                                    }
                                    po3 = this.A00;
                                }
                                A004.A0B(obj4, qgVar, r2Var);
                            }
                        }
                    } catch (Exception e5) {
                        StdSerializer.A03(r2Var, e5, list, 0);
                        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                    }
                }
            }
        } else {
            Iterator it5 = (Iterator) obj;
            if (it5.hasNext()) {
                PU pu6 = this.A04;
                Class<?> cls6 = null;
                JsonSerializer jsonSerializer5 = null;
                do {
                    Object next4 = it5.next();
                    if (next4 == null) {
                        r2Var.A0D(qgVar);
                    } else {
                        Class<?> cls7 = next4.getClass();
                        if (cls7 != cls6) {
                            jsonSerializer5 = r2Var.A0A(cls7, this.A01);
                            cls6 = cls7;
                        }
                        if (pu6 == null) {
                            jsonSerializer5.A0B(next4, qgVar, r2Var);
                        } else {
                            jsonSerializer5.A09(next4, qgVar, r2Var, pu6);
                        }
                    }
                } while (it5.hasNext());
            }
        }
    }

    @Override // X.AbstractC0278Pa
    public final JsonSerializer A1Y(AbstractC1031r2 r2Var, O5 o5) {
        JsonSerializer jsonSerializer;
        AbstractC1044rJ A2e;
        Object A0C;
        PU pu = this.A04;
        if (pu != null) {
            pu = pu.A00(o5);
        }
        if (o5 == null || (A2e = o5.A2e()) == null || (A0C = r2Var._config.A01().A0C(A2e)) == null || (jsonSerializer = r2Var.A09(A2e, A0C)) == null) {
            jsonSerializer = this.A03;
        }
        JsonSerializer A022 = StdSerializer.A02(r2Var, o5, jsonSerializer);
        if (A022 == null) {
            AbstractC1024qt qtVar = this.A02;
            if (qtVar != null && (this.A05 || ContainerSerializer.A00(r2Var, o5))) {
                A022 = r2Var.A08(qtVar, o5);
            }
        } else if (A022 instanceof AbstractC0278Pa) {
            A022 = ((AbstractC0278Pa) A022).A1Y(r2Var, o5);
        }
        if (A022 == this.A03 && o5 == this.A01 && pu == pu) {
            return this;
        }
        if (this instanceof IterableSerializer) {
            return new IterableSerializer((IterableSerializer) this, o5, pu, A022);
        }
        if (this instanceof EnumSetSerializer) {
            return new EnumSetSerializer((EnumSetSerializer) this, o5, pu, A022);
        }
        if (this instanceof CollectionSerializer) {
            return new CollectionSerializer((CollectionSerializer) this, o5, pu, A022);
        }
        if (!(this instanceof IteratorSerializer)) {
            return new IndexedListSerializer((IndexedListSerializer) this, o5, pu, A022);
        }
        return new IteratorSerializer((IteratorSerializer) this, o5, pu, A022);
    }

    public AsArraySerializerBase(AsArraySerializerBase asArraySerializerBase, O5 o5, PU pu, JsonSerializer jsonSerializer) {
        super(asArraySerializerBase);
        this.A02 = asArraySerializerBase.A02;
        this.A05 = asArraySerializerBase.A05;
        this.A04 = pu;
        this.A01 = o5;
        this.A03 = jsonSerializer;
        this.A00 = asArraySerializerBase.A00;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AsArraySerializerBase(Class cls, AbstractC1024qt qtVar, boolean z, PU pu, O5 o5, JsonSerializer jsonSerializer) {
        super(cls, false);
        boolean z2 = false;
        this.A02 = qtVar;
        if (z || (qtVar != null && Modifier.isFinal(qtVar._class.getModifiers()))) {
            z2 = true;
        }
        this.A05 = z2;
        this.A04 = pu;
        this.A01 = o5;
        this.A03 = jsonSerializer;
        this.A00 = C1062rc.A00;
    }
}
