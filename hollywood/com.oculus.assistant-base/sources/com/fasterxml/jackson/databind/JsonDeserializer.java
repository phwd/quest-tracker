package com.fasterxml.jackson.databind;

import X.AbstractC1014qi;
import X.AbstractC1022qr;
import X.AbstractC1034r7;
import X.AnonymousClass08;
import X.AnonymousClass0W;
import X.C0267Oj;
import X.C0273Ou;
import X.C0317Qp;
import X.C1025qv;
import X.EnumC1023qs;
import X.JD;
import X.NX;
import X.OX;
import X.P4;
import X.P5;
import X.P7;
import X.PR;
import X.QC;
import X.RU;
import com.fasterxml.jackson.databind.deser.AbstractDeserializer;
import com.fasterxml.jackson.databind.deser.BeanDeserializer;
import com.fasterxml.jackson.databind.deser.BeanDeserializerBase;
import com.fasterxml.jackson.databind.deser.BuilderBasedDeserializer;
import com.fasterxml.jackson.databind.deser.impl.BeanAsArrayBuilderDeserializer;
import com.fasterxml.jackson.databind.deser.impl.BeanAsArrayDeserializer;
import com.fasterxml.jackson.databind.deser.impl.TypeWrappedDeserializer;
import com.fasterxml.jackson.databind.deser.std.BaseNodeDeserializer;
import com.fasterxml.jackson.databind.deser.std.CollectionDeserializer;
import com.fasterxml.jackson.databind.deser.std.EnumMapDeserializer;
import com.fasterxml.jackson.databind.deser.std.EnumSetDeserializer;
import com.fasterxml.jackson.databind.deser.std.MapDeserializer;
import com.fasterxml.jackson.databind.deser.std.NullifyingDeserializer;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers$BooleanDeserializer;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers$DoubleDeserializer;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers$IntegerDeserializer;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers$NumberDeserializer;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers$PrimitiveOrWrapperDeserializer;
import com.fasterxml.jackson.databind.deser.std.ObjectArrayDeserializer;
import com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers;
import com.fasterxml.jackson.databind.deser.std.StdDelegatingDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.fasterxml.jackson.databind.deser.std.StringArrayDeserializer;
import com.fasterxml.jackson.databind.deser.std.StringCollectionDeserializer;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import com.fasterxml.jackson.databind.deser.std.UntypedObjectDeserializer;
import com.fasterxml.jackson.datatype.guava.deser.GuavaCollectionDeserializer;
import com.fasterxml.jackson.datatype.guava.deser.GuavaMapDeserializer;
import com.oculus.aidl.OVRServiceInterface;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

public abstract class JsonDeserializer {

    public abstract class None extends JsonDeserializer {
    }

    public JsonDeserializer A0B(QC qc) {
        return this;
    }

    public abstract Object A0C(AbstractC1014qi qiVar, AbstractC1022qr qrVar);

    public final Object A08() {
        if (this instanceof NumberDeserializers$PrimitiveOrWrapperDeserializer) {
            return ((NumberDeserializers$PrimitiveOrWrapperDeserializer) this)._nullValue;
        }
        if (!(this instanceof BaseNodeDeserializer)) {
            return null;
        }
        return AnonymousClass0W.A00;
    }

    public final Object A09(AbstractC1014qi qiVar, AbstractC1022qr qrVar, PR pr) {
        NX A0U;
        Object A0p;
        NX A0U2;
        if (!(this instanceof GuavaMapDeserializer)) {
            if (this instanceof TypeWrappedDeserializer) {
                throw new IllegalStateException("Type-wrapped deserializer's deserializeWithType should never get called");
            } else if (!(this instanceof GuavaCollectionDeserializer)) {
                if (this instanceof UntypedObjectDeserializer) {
                    switch (P7.A00[qiVar.A0U().ordinal()]) {
                        case 1:
                        case 2:
                        case 3:
                            break;
                        case 4:
                            return qiVar.A0Z();
                        case 5:
                            return qiVar.A0p();
                        case 6:
                            if (qrVar.A0O(EnumC1023qs.USE_BIG_INTEGER_FOR_INTS)) {
                                return qiVar.A0d();
                            }
                            return qiVar.A0Y();
                        case OVRServiceInterface.Stub.TRANSACTION_getEntitlementCheckBundle /*{ENCODED_INT: 7}*/:
                            if (qrVar.A0O(EnumC1023qs.USE_BIG_DECIMAL_FOR_FLOATS)) {
                                return qiVar.A0c();
                            }
                            return Double.valueOf(qiVar.A0F());
                        case 8:
                            return Boolean.TRUE;
                        case OVRServiceInterface.Stub.TRANSACTION_getLatestAvailableAppInformation /*{ENCODED_INT: 9}*/:
                            return Boolean.FALSE;
                        case 10:
                            return null;
                        default:
                            qrVar.A0J();
                            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                    }
                } else if (!(this instanceof StringArrayDeserializer)) {
                    if (!(this instanceof StdDelegatingDeserializer)) {
                        if (!(this instanceof PrimitiveArrayDeserializers)) {
                            if (this instanceof StdScalarDeserializer) {
                                StdScalarDeserializer stdScalarDeserializer = (StdScalarDeserializer) this;
                                if (stdScalarDeserializer instanceof StringDeserializer) {
                                    return StringDeserializer.A00((StringDeserializer) stdScalarDeserializer, qiVar, qrVar);
                                }
                                if (stdScalarDeserializer instanceof NumberDeserializers$NumberDeserializer) {
                                    NumberDeserializers$NumberDeserializer numberDeserializers$NumberDeserializer = (NumberDeserializers$NumberDeserializer) stdScalarDeserializer;
                                    int i = P5.A00[qiVar.A0U().ordinal()];
                                    if (i == 1 || i == 2 || i == 3) {
                                        return NumberDeserializers$NumberDeserializer.A00(numberDeserializers$NumberDeserializer, qiVar, qrVar);
                                    }
                                } else if (stdScalarDeserializer instanceof NumberDeserializers$IntegerDeserializer) {
                                    return stdScalarDeserializer.A0J(qiVar, qrVar);
                                } else {
                                    if (stdScalarDeserializer instanceof NumberDeserializers$DoubleDeserializer) {
                                        return stdScalarDeserializer.A0I(qiVar, qrVar);
                                    }
                                    if (stdScalarDeserializer instanceof NumberDeserializers$BooleanDeserializer) {
                                        return stdScalarDeserializer.A0H(qiVar, qrVar);
                                    }
                                }
                                if (!(pr instanceof C0317Qp)) {
                                    return RU.A02((RU) pr, qiVar, qrVar);
                                }
                                return C0317Qp.A00((C0317Qp) pr, qiVar, qrVar);
                            } else if (this instanceof NullifyingDeserializer) {
                                int i2 = P4.A00[qiVar.A0U().ordinal()];
                                if (!(i2 == 1 || i2 == 2 || i2 == 3)) {
                                    return null;
                                }
                            } else if (!(this instanceof EnumSetDeserializer)) {
                                if (!(this instanceof EnumMapDeserializer)) {
                                    if (!(this instanceof StringCollectionDeserializer) && !(this instanceof ObjectArrayDeserializer)) {
                                        if (!(this instanceof MapDeserializer)) {
                                            if (!(this instanceof CollectionDeserializer)) {
                                                if (this instanceof BeanDeserializerBase) {
                                                    BeanDeserializerBase beanDeserializerBase = (BeanDeserializerBase) this;
                                                    if (!(beanDeserializerBase._objectIdReader == null || (A0U = qiVar.A0U()) == null || !A0U.isScalarValue())) {
                                                        return BeanDeserializerBase.A04(beanDeserializerBase, qiVar, qrVar);
                                                    }
                                                } else if (this instanceof AbstractDeserializer) {
                                                    AbstractDeserializer abstractDeserializer = (AbstractDeserializer) this;
                                                    C0273Ou ou = abstractDeserializer._objectIdReader;
                                                    if (ou == null || (A0U2 = qiVar.A0U()) == null || !A0U2.isScalarValue()) {
                                                        int i3 = OX.A00[qiVar.A0U().ordinal()];
                                                        if (i3 != 1) {
                                                            if (i3 != 2) {
                                                                if (i3 != 3) {
                                                                    if (i3 != 4) {
                                                                        if (i3 == 5 && abstractDeserializer._acceptBoolean) {
                                                                            A0p = Boolean.FALSE;
                                                                        }
                                                                    } else if (abstractDeserializer._acceptBoolean) {
                                                                        A0p = Boolean.TRUE;
                                                                    }
                                                                } else if (abstractDeserializer._acceptDouble) {
                                                                    A0p = Double.valueOf(qiVar.A0F());
                                                                }
                                                            } else if (abstractDeserializer._acceptInt) {
                                                                A0p = Integer.valueOf(qiVar.A0J());
                                                            }
                                                        } else if (abstractDeserializer._acceptString) {
                                                            A0p = qiVar.A0p();
                                                        }
                                                        if (A0p != null) {
                                                            return A0p;
                                                        }
                                                    } else {
                                                        Object A0C = ou.deserializer.A0C(qiVar, qrVar);
                                                        Object obj = qrVar.A0F(A0C, abstractDeserializer._objectIdReader.generator).A00;
                                                        if (obj != null) {
                                                            return obj;
                                                        }
                                                        StringBuilder sb = new StringBuilder("Could not resolve Object Id [");
                                                        sb.append(A0C);
                                                        sb.append("] -- unresolved forward-reference?");
                                                        throw new IllegalStateException(sb.toString());
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                return pr.A05(qiVar, qrVar);
                            }
                        }
                    } else if (((StdDelegatingDeserializer) this)._delegateDeserializer.A09(qiVar, qrVar, pr) == null) {
                        return null;
                    } else {
                        throw new NullPointerException("convert");
                    }
                }
                return pr.A06(qiVar, qrVar);
            }
        }
        return pr.A04(qiVar, qrVar);
    }

    public final Object A0A(AbstractC1014qi qiVar, AbstractC1022qr qrVar, Object obj) {
        Class cls;
        Class cls2;
        if (this instanceof TypeWrappedDeserializer) {
            return ((TypeWrappedDeserializer) this).A01.A0A(qiVar, qrVar, obj);
        }
        if (this instanceof StringCollectionDeserializer) {
            Collection collection = (Collection) obj;
            StringCollectionDeserializer.A01((StringCollectionDeserializer) this, qiVar, qrVar, collection);
            return collection;
        } else if (this instanceof MapDeserializer) {
            MapDeserializer mapDeserializer = (MapDeserializer) this;
            Map map = (Map) obj;
            NX A0U = qiVar.A0U();
            if (A0U != NX.START_OBJECT && A0U != NX.FIELD_NAME) {
                qrVar.A0J();
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            } else if (mapDeserializer._standardStringKey) {
                MapDeserializer.A02(mapDeserializer, qiVar, qrVar, map);
                return map;
            } else {
                MapDeserializer.A01(mapDeserializer, qiVar, qrVar, map);
                return map;
            }
        } else if (this instanceof CollectionDeserializer) {
            return ((CollectionDeserializer) this).A0O(qiVar, qrVar, (Collection) obj);
        } else {
            if (this instanceof BeanAsArrayDeserializer) {
                BeanAsArrayDeserializer beanAsArrayDeserializer = (BeanAsArrayDeserializer) this;
                if (beanAsArrayDeserializer._injectables != null) {
                    beanAsArrayDeserializer.A0a(qrVar);
                }
                AbstractC1034r7[] r7VarArr = beanAsArrayDeserializer._orderedProperties;
                int i = 0;
                int length = r7VarArr.length;
                while (true) {
                    NX A0o = qiVar.A0o();
                    NX nx = NX.END_ARRAY;
                    if (A0o == nx) {
                        break;
                    } else if (i != length) {
                        AbstractC1034r7 r7Var = r7VarArr[i];
                        if (r7Var != null) {
                            try {
                                r7Var.A07(qiVar, qrVar, obj);
                            } catch (Exception e) {
                                beanAsArrayDeserializer.A0d(e, obj, r7Var._propName, qrVar);
                                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                            }
                        } else {
                            qiVar.A0T();
                        }
                        i++;
                    } else if (!beanAsArrayDeserializer._ignoreAllUnknown) {
                        throw C1025qv.A00(null, AnonymousClass08.A01("Unexpected JSON values; expected at most ", length, " properties (in JSON Array)"));
                    } else {
                        while (qiVar.A0o() != nx) {
                            qiVar.A0T();
                        }
                    }
                }
            } else if (this instanceof BeanAsArrayBuilderDeserializer) {
                BeanAsArrayBuilderDeserializer beanAsArrayBuilderDeserializer = (BeanAsArrayBuilderDeserializer) this;
                if (beanAsArrayBuilderDeserializer._injectables != null) {
                    beanAsArrayBuilderDeserializer.A0a(qrVar);
                }
                AbstractC1034r7[] r7VarArr2 = beanAsArrayBuilderDeserializer._orderedProperties;
                int i2 = 0;
                int length2 = r7VarArr2.length;
                while (true) {
                    NX A0o2 = qiVar.A0o();
                    NX nx2 = NX.END_ARRAY;
                    if (A0o2 == nx2) {
                        break;
                    } else if (i2 != length2) {
                        AbstractC1034r7 r7Var2 = r7VarArr2[i2];
                        if (r7Var2 != null) {
                            try {
                                obj = r7Var2.A05(qiVar, qrVar, obj);
                            } catch (Exception e2) {
                                beanAsArrayBuilderDeserializer.A0d(e2, obj, r7Var2._propName, qrVar);
                                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                            }
                        } else {
                            qiVar.A0T();
                        }
                        i2++;
                    } else if (!beanAsArrayBuilderDeserializer._ignoreAllUnknown) {
                        throw C1025qv.A00(null, AnonymousClass08.A01("Unexpected JSON values; expected at most ", length2, " properties (in JSON Array)"));
                    } else {
                        while (qiVar.A0o() != nx2) {
                            qiVar.A0T();
                        }
                    }
                }
                return BeanAsArrayBuilderDeserializer.A00(beanAsArrayBuilderDeserializer, qrVar, obj);
            } else if (this instanceof BuilderBasedDeserializer) {
                BuilderBasedDeserializer builderBasedDeserializer = (BuilderBasedDeserializer) this;
                return BuilderBasedDeserializer.A03(builderBasedDeserializer, qrVar, BuilderBasedDeserializer.A00(builderBasedDeserializer, qiVar, qrVar, obj));
            } else if (!(this instanceof BeanDeserializer)) {
                throw new UnsupportedOperationException(AnonymousClass08.A07("Can not update object of type ", obj.getClass().getName(), " (by deserializer of type ", getClass().getName(), ")"));
            } else {
                BeanDeserializer beanDeserializer = (BeanDeserializer) this;
                if (beanDeserializer._injectables != null) {
                    beanDeserializer.A0a(qrVar);
                }
                if (beanDeserializer._unwrappedPropertyHandler != null) {
                    NX A0U2 = qiVar.A0U();
                    if (A0U2 == NX.START_OBJECT) {
                        A0U2 = qiVar.A0o();
                    }
                    JD jd = new JD(qiVar.A0W());
                    jd.A0C();
                    if (beanDeserializer._needViewProcesing) {
                        cls2 = qrVar._view;
                    } else {
                        cls2 = null;
                    }
                    while (A0U2 == NX.FIELD_NAME) {
                        String A0b = qiVar.A0b();
                        AbstractC1034r7 A00 = beanDeserializer._beanProperties.A00(A0b);
                        qiVar.A0o();
                        if (A00 == null) {
                            HashSet hashSet = beanDeserializer._ignorableProps;
                            if (hashSet == null || !hashSet.contains(A0b)) {
                                jd.A0M(A0b);
                                jd.A0X(qiVar);
                                C0267Oj oj = beanDeserializer._anySetter;
                                if (oj != null) {
                                    oj.A01(qiVar, qrVar, obj, A0b);
                                }
                                A0U2 = qiVar.A0o();
                            }
                        } else if (cls2 == null || A00.A0A(cls2)) {
                            try {
                                A00.A07(qiVar, qrVar, obj);
                                A0U2 = qiVar.A0o();
                            } catch (Exception e3) {
                                beanDeserializer.A0d(e3, obj, A0b, qrVar);
                                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                            }
                        }
                        qiVar.A0T();
                        A0U2 = qiVar.A0o();
                    }
                    jd.A09();
                    beanDeserializer._unwrappedPropertyHandler.A00(qrVar, obj, jd);
                } else if (beanDeserializer._externalTypeIdHandler != null) {
                    BeanDeserializer.A01(beanDeserializer, qiVar, qrVar, obj);
                    return obj;
                } else {
                    NX A0U3 = qiVar.A0U();
                    if (A0U3 == NX.START_OBJECT) {
                        A0U3 = qiVar.A0o();
                    }
                    if (!beanDeserializer._needViewProcesing || (cls = qrVar._view) == null) {
                        while (A0U3 == NX.FIELD_NAME) {
                            String A0b2 = qiVar.A0b();
                            qiVar.A0o();
                            AbstractC1034r7 A002 = beanDeserializer._beanProperties.A00(A0b2);
                            if (A002 != null) {
                                try {
                                    A002.A07(qiVar, qrVar, obj);
                                } catch (Exception e4) {
                                    beanDeserializer.A0d(e4, obj, A0b2, qrVar);
                                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                                }
                            } else {
                                HashSet hashSet2 = beanDeserializer._ignorableProps;
                                if (hashSet2 == null || !hashSet2.contains(A0b2)) {
                                    C0267Oj oj2 = beanDeserializer._anySetter;
                                    if (oj2 != null) {
                                        oj2.A01(qiVar, qrVar, obj, A0b2);
                                    } else {
                                        beanDeserializer.A0L(qiVar, qrVar, obj, A0b2);
                                    }
                                } else {
                                    qiVar.A0T();
                                }
                            }
                            A0U3 = qiVar.A0o();
                        }
                    } else {
                        BeanDeserializer.A02(beanDeserializer, qiVar, qrVar, obj, cls);
                        return obj;
                    }
                }
            }
            return obj;
        }
    }
}
