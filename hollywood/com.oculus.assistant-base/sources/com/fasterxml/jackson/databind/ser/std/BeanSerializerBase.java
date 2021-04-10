package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC0278Pa;
import X.AbstractC0282Pe;
import X.AbstractC1012qg;
import X.AbstractC1020qp;
import X.AbstractC1024qt;
import X.AbstractC1031r2;
import X.AbstractC1044rJ;
import X.AnonymousClass08;
import X.C0244Mv;
import X.C0289Pl;
import X.C0294Pq;
import X.C1015qj;
import X.C1025qv;
import X.C1060ra;
import X.EnumC0243Mu;
import X.O9;
import X.PU;
import X.PX;
import X.PY;
import X.Q3;
import X.QC;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ser.BeanSerializer;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.ser.impl.BeanAsArraySerializer;
import com.fasterxml.jackson.databind.ser.impl.IndexedListSerializer;
import com.fasterxml.jackson.databind.ser.impl.IteratorSerializer;
import com.fasterxml.jackson.databind.ser.impl.StringArraySerializer;
import com.fasterxml.jackson.databind.ser.impl.UnwrappingBeanSerializer;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

public abstract class BeanSerializerBase extends StdSerializer implements AbstractC0278Pa, AbstractC0282Pe {
    public static final C1060ra[] A07 = new C1060ra[0];
    public final EnumC0243Mu A00;
    public final AbstractC1044rJ A01;
    public final PX A02;
    public final C0289Pl A03;
    public final Object A04;
    public final C1060ra[] A05;
    public final C1060ra[] A06;

    private final BeanSerializerBase A0C(C0289Pl pl) {
        if (this instanceof UnwrappingBeanSerializer) {
            return new UnwrappingBeanSerializer((UnwrappingBeanSerializer) this, pl);
        }
        if (!(this instanceof BeanAsArraySerializer)) {
            return new BeanSerializer(this, pl);
        }
        return ((BeanAsArraySerializer) this).A00.A0C(pl);
    }

    public final void A0D() {
        Object obj = this.A04;
        StringBuilder sb = new StringBuilder("Can not resolve BeanPropertyFilter with id '");
        sb.append(obj);
        sb.append("'; no FilterProvider configured");
        throw new C1025qv(sb.toString());
    }

    public final void A0E(Object obj, AbstractC1012qg qgVar, AbstractC1031r2 r2Var) {
        AbstractC1044rJ rJVar;
        Object A0Q;
        String str = "[anySetter]";
        C1060ra[] raVarArr = this.A05;
        if (raVarArr == null || r2Var._serializationView == null) {
            raVarArr = this.A06;
        }
        try {
            for (C1060ra raVar : raVarArr) {
                if (raVar != null) {
                    raVar.A06(obj, qgVar, r2Var);
                }
            }
            PX px = this.A02;
            if (!(px == null || (A0Q = (rJVar = px.A02).A0Q(obj)) == null)) {
                if (A0Q instanceof Map) {
                    px.A00.A0D((Map) A0Q, qgVar, r2Var);
                    return;
                }
                throw new C1025qv(AnonymousClass08.A06("Value returned by 'any-getter' (", rJVar.A0K(), "()) not java.util.Map but ", A0Q.getClass().getName()));
            }
        } catch (Exception e) {
            if (0 != raVarArr.length) {
                str = raVarArr[0].A06.getValue();
            }
            StdSerializer.A04(r2Var, e, obj, str);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        } catch (StackOverflowError e2) {
            C1025qv qvVar = new C1025qv("Infinite recursion (StackOverflowError)", e2);
            if (0 != raVarArr.length) {
                str = raVarArr[0].A06.getValue();
            }
            qvVar.A03(new O9(obj, str));
            throw qvVar;
        }
    }

    public final void A0F(Object obj, AbstractC1012qg qgVar, AbstractC1031r2 r2Var, boolean z) {
        C0289Pl pl = this.A03;
        C0294Pq A0C = r2Var.A0C(obj, pl.A00);
        Object obj2 = A0C.A00;
        if (obj2 == null || (!A0C.A01 && !pl.A04)) {
            obj2 = A0C.A02.A00(obj);
            A0C.A00 = obj2;
            if (!pl.A04) {
                if (z) {
                    qgVar.A0C();
                }
                C1015qj qjVar = pl.A01;
                A0C.A01 = true;
                if (qjVar != null) {
                    qgVar.A0J(qjVar);
                    pl.A03.A0B(A0C.A00, qgVar, r2Var);
                }
                if (this.A04 != null) {
                    A0D();
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
                A0E(obj, qgVar, r2Var);
                if (z) {
                    qgVar.A09();
                    return;
                }
                return;
            }
        }
        pl.A03.A0B(obj2, qgVar, r2Var);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0105, code lost:
        if (r9 != null) goto L_0x0043;
     */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0063  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x007f  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00a0  */
    @Override // X.AbstractC0278Pa
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.fasterxml.jackson.databind.JsonSerializer A1Y(X.AbstractC1031r2 r16, X.O5 r17) {
        /*
        // Method dump skipped, instructions count: 308
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.A1Y(X.r2, X.O5):com.fasterxml.jackson.databind.JsonSerializer");
    }

    @Override // X.AbstractC0282Pe
    public final void A4t(AbstractC1031r2 r2Var) {
        int length;
        JsonSerializer jsonSerializer;
        C1060ra raVar;
        PU pu;
        Type genericType;
        Object A0F;
        JsonSerializer jsonSerializer2;
        C1060ra raVar2;
        C1060ra[] raVarArr = this.A05;
        if (raVarArr == null) {
            length = 0;
        } else {
            length = raVarArr.length;
        }
        C1060ra[] raVarArr2 = this.A06;
        int length2 = raVarArr2.length;
        for (int i = 0; i < length2; i++) {
            C1060ra raVar3 = raVarArr2[i];
            if (!raVar3.A0B && raVar3.A01 == null && (jsonSerializer2 = r2Var._nullValueSerializer) != null) {
                raVar3.A03(jsonSerializer2);
                if (i < length && (raVar2 = raVarArr[i]) != null) {
                    raVar2.A03(jsonSerializer2);
                }
            }
            if (raVar3.A02 == null) {
                AbstractC1020qp A012 = r2Var._config.A01();
                if (A012 == null || (A0F = A012.A0F(raVar3.A2e())) == null) {
                    AbstractC1024qt qtVar = raVar3.A07;
                    if (qtVar == null) {
                        Method method = raVar3.A0A;
                        if (method != null) {
                            genericType = method.getGenericReturnType();
                        } else {
                            genericType = raVar3.A09.getGenericType();
                        }
                        qtVar = r2Var.A05().A09(genericType, null);
                        if (!Modifier.isFinal(qtVar._class.getModifiers())) {
                            if (qtVar.A0H() || qtVar.A03() > 0) {
                                raVar3.A00 = qtVar;
                            }
                        }
                    }
                    JsonSerializer A08 = r2Var.A08(qtVar, raVar3);
                    if (qtVar.A0H() && (pu = (PU) qtVar.A04()._typeHandler) != null && (A08 instanceof ContainerSerializer)) {
                        A08 = (ContainerSerializer) A08;
                        if (A08 instanceof MapSerializer) {
                            jsonSerializer = new MapSerializer((MapSerializer) A08, pu);
                        } else if (A08 instanceof EnumMapSerializer) {
                            EnumMapSerializer enumMapSerializer = (EnumMapSerializer) A08;
                            jsonSerializer = new EnumMapSerializer(enumMapSerializer.A01, enumMapSerializer.A05, enumMapSerializer.A04, pu, enumMapSerializer.A02);
                        } else if (!(A08 instanceof StdArraySerializers$IntArraySerializer)) {
                            if (A08 instanceof StdArraySerializers$ShortArraySerializer) {
                                StdArraySerializers$ShortArraySerializer stdArraySerializers$ShortArraySerializer = (StdArraySerializers$ShortArraySerializer) A08;
                                jsonSerializer = new StdArraySerializers$ShortArraySerializer(stdArraySerializers$ShortArraySerializer, ((ArraySerializerBase) stdArraySerializers$ShortArraySerializer).A00, pu);
                            } else if (A08 instanceof StdArraySerializers$LongArraySerializer) {
                                StdArraySerializers$LongArraySerializer stdArraySerializers$LongArraySerializer = (StdArraySerializers$LongArraySerializer) A08;
                                jsonSerializer = new StdArraySerializers$LongArraySerializer(stdArraySerializers$LongArraySerializer, ((ArraySerializerBase) stdArraySerializers$LongArraySerializer).A00, pu);
                            } else if (A08 instanceof StdArraySerializers$FloatArraySerializer) {
                                StdArraySerializers$FloatArraySerializer stdArraySerializers$FloatArraySerializer = (StdArraySerializers$FloatArraySerializer) A08;
                                jsonSerializer = new StdArraySerializers$FloatArraySerializer(stdArraySerializers$FloatArraySerializer, ((ArraySerializerBase) stdArraySerializers$FloatArraySerializer).A00, pu);
                            } else if (!(A08 instanceof StdArraySerializers$DoubleArraySerializer) && !(A08 instanceof StdArraySerializers$BooleanArraySerializer)) {
                                if (A08 instanceof ObjectArraySerializer) {
                                    ObjectArraySerializer objectArraySerializer = (ObjectArraySerializer) A08;
                                    jsonSerializer = new ObjectArraySerializer(objectArraySerializer.A02, objectArraySerializer.A04, pu, objectArraySerializer.A00);
                                } else if (!(A08 instanceof StringArraySerializer)) {
                                    if (A08 instanceof IterableSerializer) {
                                        AsArraySerializerBase asArraySerializerBase = (AsArraySerializerBase) A08;
                                        jsonSerializer = new IterableSerializer(asArraySerializerBase.A02, asArraySerializerBase.A05, pu, asArraySerializerBase.A01);
                                    } else if (!(A08 instanceof EnumSetSerializer)) {
                                        if (A08 instanceof CollectionSerializer) {
                                            AsArraySerializerBase asArraySerializerBase2 = (AsArraySerializerBase) A08;
                                            jsonSerializer = new CollectionSerializer(asArraySerializerBase2.A02, asArraySerializerBase2.A05, pu, asArraySerializerBase2.A01, asArraySerializerBase2.A03);
                                        } else if (!(A08 instanceof IteratorSerializer)) {
                                            AsArraySerializerBase asArraySerializerBase3 = (AsArraySerializerBase) A08;
                                            jsonSerializer = new IndexedListSerializer(asArraySerializerBase3.A02, asArraySerializerBase3.A05, pu, asArraySerializerBase3.A01, asArraySerializerBase3.A03);
                                        } else {
                                            AsArraySerializerBase asArraySerializerBase4 = (AsArraySerializerBase) A08;
                                            jsonSerializer = new IteratorSerializer(asArraySerializerBase4.A02, asArraySerializerBase4.A05, pu, asArraySerializerBase4.A01);
                                        }
                                    }
                                }
                            }
                        }
                        raVar3.A04(jsonSerializer);
                        if (i < length && (raVar = raVarArr[i]) != null) {
                            raVar.A04(jsonSerializer);
                        }
                    }
                    jsonSerializer = A08;
                    raVar3.A04(jsonSerializer);
                    raVar.A04(jsonSerializer);
                } else {
                    r2Var.A06(A0F);
                    throw new NullPointerException("getOutputType");
                }
            }
        }
        PX px = this.A02;
        if (px != null) {
            px.A00 = (MapSerializer) px.A00.A1Y(r2Var, px.A01);
        }
    }

    public BeanSerializerBase(AbstractC1024qt qtVar, PY py, C1060ra[] raVarArr, C1060ra[] raVarArr2) {
        super(qtVar);
        this.A06 = raVarArr;
        this.A05 = raVarArr2;
        EnumC0243Mu mu = null;
        if (py == null) {
            this.A01 = null;
            this.A02 = null;
            this.A04 = null;
            this.A03 = null;
        } else {
            this.A01 = py.A01;
            this.A02 = py.A02;
            this.A04 = py.A04;
            this.A03 = py.A03;
            C0244Mv A032 = py.A07.A03(null);
            if (A032 != null) {
                mu = A032.A00;
            }
        }
        this.A00 = mu;
    }

    public BeanSerializerBase(BeanSerializerBase beanSerializerBase, C0289Pl pl) {
        super(((StdSerializer) beanSerializerBase).A00);
        this.A06 = beanSerializerBase.A06;
        this.A05 = beanSerializerBase.A05;
        this.A01 = beanSerializerBase.A01;
        this.A02 = beanSerializerBase.A02;
        this.A03 = pl;
        this.A04 = beanSerializerBase.A04;
        this.A00 = beanSerializerBase.A00;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BeanSerializerBase(BeanSerializerBase beanSerializerBase, QC qc) {
        super(((StdSerializer) beanSerializerBase).A00);
        int length;
        int length2;
        C1060ra[] raVarArr = beanSerializerBase.A06;
        if (!(raVarArr == null || (length2 = raVarArr.length) == 0 || qc == null || qc == QC.A00)) {
            C1060ra[] raVarArr2 = new C1060ra[length2];
            for (int i = 0; i < length2; i++) {
                C1060ra raVar = raVarArr[i];
                if (raVar != null) {
                    raVarArr2[i] = raVar.A01(qc);
                }
            }
            raVarArr = raVarArr2;
        }
        C1060ra[] raVarArr3 = beanSerializerBase.A05;
        if (!(raVarArr3 == null || (length = raVarArr3.length) == 0 || qc == null || qc == QC.A00)) {
            C1060ra[] raVarArr4 = new C1060ra[length];
            for (int i2 = 0; i2 < length; i2++) {
                C1060ra raVar2 = raVarArr3[i2];
                if (raVar2 != null) {
                    raVarArr4[i2] = raVar2.A01(qc);
                }
            }
            raVarArr3 = raVarArr4;
        }
        this.A06 = raVarArr;
        this.A05 = raVarArr3;
        this.A01 = beanSerializerBase.A01;
        this.A02 = beanSerializerBase.A02;
        this.A03 = beanSerializerBase.A03;
        this.A04 = beanSerializerBase.A04;
        this.A00 = beanSerializerBase.A00;
    }

    public BeanSerializerBase(BeanSerializerBase beanSerializerBase, String[] strArr) {
        super(((StdSerializer) beanSerializerBase).A00);
        HashSet A002 = Q3.A00(strArr);
        C1060ra[] raVarArr = beanSerializerBase.A06;
        C1060ra[] raVarArr2 = beanSerializerBase.A05;
        int length = raVarArr.length;
        ArrayList arrayList = new ArrayList(length);
        C1060ra[] raVarArr3 = null;
        ArrayList arrayList2 = raVarArr2 == null ? null : new ArrayList(length);
        for (int i = 0; i < length; i++) {
            C1060ra raVar = raVarArr[i];
            if (!A002.contains(raVar.A06.getValue())) {
                arrayList.add(raVar);
                if (raVarArr2 != null) {
                    arrayList2.add(raVarArr2[i]);
                }
            }
        }
        this.A06 = (C1060ra[]) arrayList.toArray(new C1060ra[arrayList.size()]);
        this.A05 = arrayList2 != null ? (C1060ra[]) arrayList2.toArray(new C1060ra[arrayList2.size()]) : raVarArr3;
        this.A01 = beanSerializerBase.A01;
        this.A02 = beanSerializerBase.A02;
        this.A03 = beanSerializerBase.A03;
        this.A04 = beanSerializerBase.A04;
        this.A00 = beanSerializerBase.A00;
    }
}
