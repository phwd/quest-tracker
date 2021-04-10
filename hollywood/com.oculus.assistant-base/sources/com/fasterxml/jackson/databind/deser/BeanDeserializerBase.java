package com.fasterxml.jackson.databind.deser;

import X.AbstractC0264Od;
import X.AbstractC1014qi;
import X.AbstractC1022qr;
import X.AbstractC1024qt;
import X.AbstractC1034r7;
import X.AnonymousClass08;
import X.C0267Oj;
import X.C0271Os;
import X.C0273Ou;
import X.C0274Ov;
import X.C0276Ox;
import X.C0297Pt;
import X.C0680fB;
import X.C1025qv;
import X.C1042rH;
import X.EnumC0243Mu;
import X.JD;
import X.NX;
import X.Oi;
import X.Ok;
import X.Oo;
import X.Oz;
import X.Q0;
import X.QC;
import X.SV;
import X.TZ;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.impl.BeanAsArrayBuilderDeserializer;
import com.fasterxml.jackson.databind.deser.impl.BeanAsArrayDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.deser.std.ThrowableDeserializer;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public abstract class BeanDeserializerBase extends StdDeserializer implements AbstractC0264Od, Oi, Serializable {
    public static final long serialVersionUID = -2038793552422727904L;
    public transient HashMap A00;
    public final transient Q0 A01;
    public C0267Oj _anySetter;
    public final Map _backRefs;
    public final Oo _beanProperties;
    public final AbstractC1024qt _beanType;
    public JsonDeserializer _delegateDeserializer;
    public C0271Os _externalTypeIdHandler;
    public final HashSet _ignorableProps;
    public final boolean _ignoreAllUnknown;
    public final TZ[] _injectables;
    public final boolean _needViewProcesing;
    public boolean _nonStandardCreation;
    public final C0273Ou _objectIdReader;
    public C0274Ov _propertyBasedCreator;
    public final EnumC0243Mu _serializationShape;
    public Oz _unwrappedPropertyHandler;
    public final Ok _valueInstantiator;
    public boolean _vanillaProcessing;

    private final Object A0Y(AbstractC1014qi qiVar, AbstractC1022qr qrVar, Object obj, JD jd) {
        JsonDeserializer jsonDeserializer;
        synchronized (this) {
            HashMap hashMap = this.A00;
            if (hashMap == null) {
                jsonDeserializer = null;
            } else {
                jsonDeserializer = (JsonDeserializer) hashMap.get(new C0297Pt(obj.getClass()));
            }
        }
        if (jsonDeserializer == null) {
            Class<?> cls = obj.getClass();
            jsonDeserializer = qrVar.A07(qrVar._config.A03(cls));
            if (jsonDeserializer != null) {
                synchronized (this) {
                    HashMap hashMap2 = this.A00;
                    if (hashMap2 == null) {
                        hashMap2 = new HashMap();
                        this.A00 = hashMap2;
                    }
                    hashMap2.put(new C0297Pt(cls), jsonDeserializer);
                }
            }
        }
        if (jsonDeserializer != null) {
            if (jd != null) {
                jd.A09();
                C0680fB fBVar = new C0680fB(jd.A02, jd.A00);
                fBVar.A0o();
                obj = jsonDeserializer.A0A(fBVar, qrVar, obj);
            }
            if (qiVar != null) {
                return jsonDeserializer.A0A(qiVar, qrVar, obj);
            }
            return obj;
        }
        if (jd != null) {
            A0b(qrVar, obj, jd);
        }
        if (qiVar != null) {
            return A0A(qiVar, qrVar, obj);
        }
        return obj;
    }

    public static final Object A04(BeanDeserializerBase beanDeserializerBase, AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        Object A0C = beanDeserializerBase._objectIdReader.deserializer.A0C(qiVar, qrVar);
        Object obj = qrVar.A0F(A0C, beanDeserializerBase._objectIdReader.generator).A00;
        if (obj != null) {
            return obj;
        }
        StringBuilder sb = new StringBuilder("Could not resolve Object Id [");
        sb.append(A0C);
        sb.append("] (for ");
        sb.append(beanDeserializerBase._beanType);
        sb.append(") -- unresolved forward-reference?");
        throw new IllegalStateException(sb.toString());
    }

    private final BeanDeserializerBase A0N(C0273Ou ou) {
        if (this instanceof BeanAsArrayDeserializer) {
            BeanAsArrayDeserializer beanAsArrayDeserializer = (BeanAsArrayDeserializer) this;
            return new BeanAsArrayDeserializer(beanAsArrayDeserializer._delegate.A0N(ou), beanAsArrayDeserializer._orderedProperties);
        } else if (this instanceof BeanAsArrayBuilderDeserializer) {
            BeanAsArrayBuilderDeserializer beanAsArrayBuilderDeserializer = (BeanAsArrayBuilderDeserializer) this;
            return new BeanAsArrayBuilderDeserializer(beanAsArrayBuilderDeserializer._delegate.A0N(ou), beanAsArrayBuilderDeserializer._orderedProperties, beanAsArrayBuilderDeserializer._buildMethod);
        } else if (!(this instanceof BuilderBasedDeserializer)) {
            return new BeanDeserializer(this, ou);
        } else {
            return new BuilderBasedDeserializer((BuilderBasedDeserializer) this, ou);
        }
    }

    private final BeanDeserializerBase A0O(HashSet hashSet) {
        if (this instanceof BeanAsArrayDeserializer) {
            BeanAsArrayDeserializer beanAsArrayDeserializer = (BeanAsArrayDeserializer) this;
            return new BeanAsArrayDeserializer(beanAsArrayDeserializer._delegate.A0O(hashSet), beanAsArrayDeserializer._orderedProperties);
        } else if (this instanceof BeanAsArrayBuilderDeserializer) {
            BeanAsArrayBuilderDeserializer beanAsArrayBuilderDeserializer = (BeanAsArrayBuilderDeserializer) this;
            return new BeanAsArrayBuilderDeserializer(beanAsArrayBuilderDeserializer._delegate.A0O(hashSet), beanAsArrayBuilderDeserializer._orderedProperties, beanAsArrayBuilderDeserializer._buildMethod);
        } else if (!(this instanceof BuilderBasedDeserializer)) {
            return new BeanDeserializer(this, hashSet);
        } else {
            return new BuilderBasedDeserializer((BuilderBasedDeserializer) this, hashSet);
        }
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final JsonDeserializer A0B(QC qc) {
        BeanDeserializerBase beanDeserializerBase;
        if (this instanceof BeanAsArrayDeserializer) {
            beanDeserializerBase = ((BeanAsArrayDeserializer) this)._delegate;
        } else if (this instanceof BeanAsArrayBuilderDeserializer) {
            beanDeserializerBase = ((BeanAsArrayBuilderDeserializer) this)._delegate;
        } else if (this instanceof BuilderBasedDeserializer) {
            return new BuilderBasedDeserializer((BuilderBasedDeserializer) this, qc);
        } else {
            BeanDeserializer beanDeserializer = (BeanDeserializer) this;
            if (!(beanDeserializer instanceof ThrowableDeserializer)) {
                if (beanDeserializer.getClass() == BeanDeserializer.class) {
                    return new BeanDeserializer(beanDeserializer, qc);
                }
            } else if (beanDeserializer.getClass() == ThrowableDeserializer.class) {
                return new ThrowableDeserializer(beanDeserializer, qc);
            }
            return beanDeserializer;
        }
        return beanDeserializerBase.A0B(qc);
    }

    @Override // com.fasterxml.jackson.databind.deser.std.StdDeserializer
    public final void A0L(AbstractC1014qi qiVar, AbstractC1022qr qrVar, Object obj, String str) {
        HashSet hashSet;
        if (this._ignoreAllUnknown || ((hashSet = this._ignorableProps) != null && hashSet.contains(str))) {
            qiVar.A0T();
        } else {
            super.A0L(qiVar, qrVar, obj, str);
        }
    }

    public final Object A0P(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        Object obj;
        AbstractC1034r7 r7Var;
        AbstractC1034r7 r7Var2;
        Object obj2;
        if (this instanceof BeanAsArrayDeserializer) {
            BeanAsArrayDeserializer beanAsArrayDeserializer = (BeanAsArrayDeserializer) this;
            C0274Ov ov = beanAsArrayDeserializer._propertyBasedCreator;
            C0276Ox A012 = ov.A01(qiVar, qrVar, beanAsArrayDeserializer._objectIdReader);
            AbstractC1034r7[] r7VarArr = beanAsArrayDeserializer._orderedProperties;
            int length = r7VarArr.length;
            obj = null;
            int i = 0;
            while (qiVar.A0o() != NX.END_ARRAY) {
                if (i < length) {
                    r7Var = r7VarArr[i];
                } else {
                    r7Var = null;
                }
                if (r7Var == null) {
                    qiVar.A0T();
                } else if (obj != null) {
                    try {
                        r7Var.A07(qiVar, qrVar, obj);
                    } catch (Exception e) {
                        beanAsArrayDeserializer.A0d(e, obj, r7Var._propName, qrVar);
                        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                    }
                } else {
                    String str = r7Var._propName;
                    AbstractC1034r7 r7Var3 = (AbstractC1034r7) ov.A00.get(str);
                    if (r7Var3 != null) {
                        if (A012.A02(r7Var3.A01(), r7Var3.A04(qiVar, qrVar))) {
                            try {
                                obj = ov.A02(qrVar, A012);
                                Class<?> cls = obj.getClass();
                                Class<?> cls2 = beanAsArrayDeserializer._beanType._class;
                                if (cls != cls2) {
                                    throw C1025qv.A00(null, AnonymousClass08.A06("Can not support implicit polymorphic deserialization for POJOs-as-Arrays style: nominal type ", cls2.getName(), ", actual type ", cls.getName()));
                                }
                            } catch (Exception e2) {
                                beanAsArrayDeserializer.A0d(e2, beanAsArrayDeserializer._beanType._class, str, qrVar);
                                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                            }
                        } else {
                            continue;
                        }
                    } else if (!A012.A03(str)) {
                        A012.A01(r7Var, r7Var.A04(qiVar, qrVar));
                    }
                }
                i++;
            }
            if (obj == null) {
                try {
                    return ov.A02(qrVar, A012);
                } catch (Exception e3) {
                    beanAsArrayDeserializer.A0c(e3, qrVar);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            }
        } else if (!(this instanceof BeanAsArrayBuilderDeserializer)) {
            if (!(this instanceof BuilderBasedDeserializer)) {
                C0274Ov ov2 = this._propertyBasedCreator;
                C0276Ox A013 = ov2.A01(qiVar, qrVar, this._objectIdReader);
                NX A0U = qiVar.A0U();
                JD jd = null;
                while (true) {
                    if (A0U == NX.FIELD_NAME) {
                        String A0b = qiVar.A0b();
                        qiVar.A0o();
                        AbstractC1034r7 r7Var4 = (AbstractC1034r7) ov2.A00.get(A0b);
                        if (r7Var4 != null) {
                            if (A013.A02(r7Var4.A01(), r7Var4.A04(qiVar, qrVar))) {
                                qiVar.A0o();
                                try {
                                    obj2 = ov2.A02(qrVar, A013);
                                    if (obj2.getClass() != this._beanType._class) {
                                        return A0Y(qiVar, qrVar, obj2, jd);
                                    }
                                    if (jd != null) {
                                        A0b(qrVar, obj2, jd);
                                    }
                                    A0A(qiVar, qrVar, obj2);
                                } catch (Exception e4) {
                                    A0d(e4, this._beanType._class, A0b, qrVar);
                                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                                }
                            }
                        } else if (!A013.A03(A0b)) {
                            AbstractC1034r7 A002 = this._beanProperties.A00(A0b);
                            if (A002 != null) {
                                A013.A01(A002, A002.A04(qiVar, qrVar));
                            } else {
                                HashSet hashSet = this._ignorableProps;
                                if (hashSet == null || !hashSet.contains(A0b)) {
                                    C0267Oj oj = this._anySetter;
                                    if (oj != null) {
                                        A013.A00(oj, A0b, oj.A00(qiVar, qrVar));
                                    } else {
                                        if (jd == null) {
                                            jd = new JD(qiVar.A0W());
                                        }
                                        jd.A0M(A0b);
                                        jd.A0X(qiVar);
                                    }
                                } else {
                                    qiVar.A0T();
                                }
                            }
                        }
                        A0U = qiVar.A0o();
                    } else {
                        try {
                            obj2 = ov2.A02(qrVar, A013);
                            if (jd != null) {
                                if (obj2.getClass() != this._beanType._class) {
                                    return A0Y(null, qrVar, obj2, jd);
                                }
                                A0b(qrVar, obj2, jd);
                                return obj2;
                            }
                        } catch (Exception e5) {
                            A0c(e5, qrVar);
                            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                        }
                    }
                }
            } else {
                BuilderBasedDeserializer builderBasedDeserializer = (BuilderBasedDeserializer) this;
                C0274Ov ov3 = builderBasedDeserializer._propertyBasedCreator;
                C0276Ox A014 = ov3.A01(qiVar, qrVar, builderBasedDeserializer._objectIdReader);
                NX A0U2 = qiVar.A0U();
                JD jd2 = null;
                while (A0U2 == NX.FIELD_NAME) {
                    String A0b2 = qiVar.A0b();
                    qiVar.A0o();
                    AbstractC1034r7 r7Var5 = (AbstractC1034r7) ov3.A00.get(A0b2);
                    if (r7Var5 != null) {
                        if (A014.A02(r7Var5.A01(), r7Var5.A04(qiVar, qrVar))) {
                            qiVar.A0o();
                            try {
                                Object A02 = ov3.A02(qrVar, A014);
                                if (A02.getClass() != builderBasedDeserializer._beanType._class) {
                                    return builderBasedDeserializer.A0Y(qiVar, qrVar, A02, jd2);
                                }
                                if (jd2 != null) {
                                    builderBasedDeserializer.A0b(qrVar, A02, jd2);
                                }
                                return BuilderBasedDeserializer.A00(builderBasedDeserializer, qiVar, qrVar, A02);
                            } catch (Exception e6) {
                                builderBasedDeserializer.A0d(e6, builderBasedDeserializer._beanType._class, A0b2, qrVar);
                                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                            }
                        }
                    } else if (!A014.A03(A0b2)) {
                        AbstractC1034r7 A003 = builderBasedDeserializer._beanProperties.A00(A0b2);
                        if (A003 != null) {
                            A014.A01(A003, A003.A04(qiVar, qrVar));
                        } else {
                            HashSet hashSet2 = builderBasedDeserializer._ignorableProps;
                            if (hashSet2 == null || !hashSet2.contains(A0b2)) {
                                C0267Oj oj2 = builderBasedDeserializer._anySetter;
                                if (oj2 != null) {
                                    A014.A00(oj2, A0b2, oj2.A00(qiVar, qrVar));
                                } else {
                                    if (jd2 == null) {
                                        jd2 = new JD(qiVar.A0W());
                                    }
                                    jd2.A0M(A0b2);
                                    jd2.A0X(qiVar);
                                }
                            } else {
                                qiVar.A0T();
                            }
                        }
                    }
                    A0U2 = qiVar.A0o();
                }
                try {
                    obj2 = ov3.A02(qrVar, A014);
                    if (jd2 != null) {
                        if (obj2.getClass() != builderBasedDeserializer._beanType._class) {
                            return builderBasedDeserializer.A0Y(null, qrVar, obj2, jd2);
                        }
                        builderBasedDeserializer.A0b(qrVar, obj2, jd2);
                        return obj2;
                    }
                } catch (Exception e7) {
                    builderBasedDeserializer.A0c(e7, qrVar);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            }
            return obj2;
        } else {
            BeanAsArrayBuilderDeserializer beanAsArrayBuilderDeserializer = (BeanAsArrayBuilderDeserializer) this;
            C0274Ov ov4 = beanAsArrayBuilderDeserializer._propertyBasedCreator;
            C0276Ox A015 = ov4.A01(qiVar, qrVar, beanAsArrayBuilderDeserializer._objectIdReader);
            AbstractC1034r7[] r7VarArr2 = beanAsArrayBuilderDeserializer._orderedProperties;
            int length2 = r7VarArr2.length;
            obj = null;
            int i2 = 0;
            while (qiVar.A0o() != NX.END_ARRAY) {
                if (i2 < length2) {
                    r7Var2 = r7VarArr2[i2];
                } else {
                    r7Var2 = null;
                }
                if (r7Var2 == null) {
                    qiVar.A0T();
                } else if (obj != null) {
                    try {
                        obj = r7Var2.A05(qiVar, qrVar, obj);
                    } catch (Exception e8) {
                        beanAsArrayBuilderDeserializer.A0d(e8, obj, r7Var2._propName, qrVar);
                        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                    }
                } else {
                    String str2 = r7Var2._propName;
                    AbstractC1034r7 r7Var6 = (AbstractC1034r7) ov4.A00.get(str2);
                    if (r7Var6 != null) {
                        if (A015.A02(r7Var6.A01(), r7Var6.A04(qiVar, qrVar))) {
                            try {
                                obj = ov4.A02(qrVar, A015);
                                Class<?> cls3 = obj.getClass();
                                Class<?> cls4 = beanAsArrayBuilderDeserializer._beanType._class;
                                if (cls3 != cls4) {
                                    throw C1025qv.A00(null, AnonymousClass08.A06("Can not support implicit polymorphic deserialization for POJOs-as-Arrays style: nominal type ", cls4.getName(), ", actual type ", cls3.getName()));
                                }
                            } catch (Exception e9) {
                                beanAsArrayBuilderDeserializer.A0d(e9, beanAsArrayBuilderDeserializer._beanType._class, str2, qrVar);
                                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                            }
                        } else {
                            continue;
                        }
                    } else if (!A015.A03(str2)) {
                        A015.A01(r7Var2, r7Var2.A04(qiVar, qrVar));
                    }
                }
                i2++;
            }
            if (obj == null) {
                try {
                    return ov4.A02(qrVar, A015);
                } catch (Exception e10) {
                    beanAsArrayBuilderDeserializer.A0c(e10, qrVar);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            }
        }
        return obj;
    }

    public Object A0Q(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        Class cls;
        Class cls2;
        Object obj;
        if (this instanceof BeanAsArrayDeserializer) {
            BeanAsArrayDeserializer.A00((BeanAsArrayDeserializer) this, qiVar);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        } else if (!(this instanceof BeanAsArrayBuilderDeserializer)) {
            BuilderBasedDeserializer builderBasedDeserializer = (BuilderBasedDeserializer) this;
            if (!builderBasedDeserializer._nonStandardCreation) {
                Object A012 = builderBasedDeserializer._valueInstantiator.A01(qrVar);
                if (builderBasedDeserializer._injectables != null) {
                    builderBasedDeserializer.A0a(qrVar);
                }
                if (builderBasedDeserializer._needViewProcesing && (cls = qrVar._view) != null) {
                    return BuilderBasedDeserializer.A02(builderBasedDeserializer, qiVar, qrVar, A012, cls);
                }
                while (qiVar.A0U() != NX.END_OBJECT) {
                    String A0b = qiVar.A0b();
                    qiVar.A0o();
                    AbstractC1034r7 A002 = builderBasedDeserializer._beanProperties.A00(A0b);
                    if (A002 != null) {
                        try {
                            A012 = A002.A05(qiVar, qrVar, A012);
                        } catch (Exception e) {
                            builderBasedDeserializer.A0d(e, A012, A0b, qrVar);
                            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                        }
                    } else {
                        HashSet hashSet = builderBasedDeserializer._ignorableProps;
                        if (hashSet == null || !hashSet.contains(A0b)) {
                            C0267Oj oj = builderBasedDeserializer._anySetter;
                            if (oj != null) {
                                try {
                                    oj.A01(qiVar, qrVar, A012, A0b);
                                } catch (Exception e2) {
                                    builderBasedDeserializer.A0d(e2, A012, A0b, qrVar);
                                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                                }
                            } else {
                                builderBasedDeserializer.A0L(qiVar, qrVar, A012, A0b);
                            }
                        } else {
                            qiVar.A0T();
                        }
                    }
                    qiVar.A0o();
                }
                return A012;
            } else if (builderBasedDeserializer._unwrappedPropertyHandler != null) {
                JsonDeserializer jsonDeserializer = builderBasedDeserializer._delegateDeserializer;
                if (jsonDeserializer != null) {
                    return builderBasedDeserializer._valueInstantiator.A02(qrVar, jsonDeserializer.A0C(qiVar, qrVar));
                }
                C0274Ov ov = builderBasedDeserializer._propertyBasedCreator;
                if (ov != null) {
                    C0276Ox A013 = ov.A01(qiVar, qrVar, builderBasedDeserializer._objectIdReader);
                    JD jd = new JD(qiVar.A0W());
                    jd.A0C();
                    NX A0U = qiVar.A0U();
                    while (true) {
                        if (A0U == NX.FIELD_NAME) {
                            String A0b2 = qiVar.A0b();
                            qiVar.A0o();
                            AbstractC1034r7 r7Var = (AbstractC1034r7) ov.A00.get(A0b2);
                            if (r7Var != null) {
                                if (A013.A02(r7Var.A01(), r7Var.A04(qiVar, qrVar))) {
                                    NX A0o = qiVar.A0o();
                                    try {
                                        obj = ov.A02(qrVar, A013);
                                        while (A0o == NX.FIELD_NAME) {
                                            qiVar.A0o();
                                            jd.A0X(qiVar);
                                            A0o = qiVar.A0o();
                                        }
                                        jd.A09();
                                        if (obj.getClass() != builderBasedDeserializer._beanType._class) {
                                            throw C1025qv.A00(null, "Can not create polymorphic instances with unwrapped values");
                                        }
                                    } catch (Exception e3) {
                                        builderBasedDeserializer.A0d(e3, builderBasedDeserializer._beanType._class, A0b2, qrVar);
                                        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                                    }
                                }
                            } else if (!A013.A03(A0b2)) {
                                AbstractC1034r7 A003 = builderBasedDeserializer._beanProperties.A00(A0b2);
                                if (A003 != null) {
                                    A013.A01(A003, A003.A04(qiVar, qrVar));
                                } else {
                                    HashSet hashSet2 = builderBasedDeserializer._ignorableProps;
                                    if (hashSet2 == null || !hashSet2.contains(A0b2)) {
                                        jd.A0M(A0b2);
                                        jd.A0X(qiVar);
                                        C0267Oj oj2 = builderBasedDeserializer._anySetter;
                                        if (oj2 != null) {
                                            A013.A00(oj2, A0b2, oj2.A00(qiVar, qrVar));
                                        }
                                    } else {
                                        qiVar.A0T();
                                    }
                                }
                            }
                            A0U = qiVar.A0o();
                        } else {
                            try {
                                obj = ov.A02(qrVar, A013);
                                break;
                            } catch (Exception e4) {
                                builderBasedDeserializer.A0c(e4, qrVar);
                                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                            }
                        }
                    }
                    builderBasedDeserializer._unwrappedPropertyHandler.A00(qrVar, obj, jd);
                    return obj;
                }
                JD jd2 = new JD(qiVar.A0W());
                jd2.A0C();
                Object A014 = builderBasedDeserializer._valueInstantiator.A01(qrVar);
                if (builderBasedDeserializer._injectables != null) {
                    builderBasedDeserializer.A0a(qrVar);
                }
                if (builderBasedDeserializer._needViewProcesing) {
                    cls2 = qrVar._view;
                } else {
                    cls2 = null;
                }
                while (qiVar.A0U() != NX.END_OBJECT) {
                    String A0b3 = qiVar.A0b();
                    qiVar.A0o();
                    AbstractC1034r7 A004 = builderBasedDeserializer._beanProperties.A00(A0b3);
                    if (A004 == null) {
                        HashSet hashSet3 = builderBasedDeserializer._ignorableProps;
                        if (hashSet3 == null || !hashSet3.contains(A0b3)) {
                            jd2.A0M(A0b3);
                            jd2.A0X(qiVar);
                            C0267Oj oj3 = builderBasedDeserializer._anySetter;
                            if (oj3 != null) {
                                try {
                                    oj3.A01(qiVar, qrVar, A014, A0b3);
                                } catch (Exception e5) {
                                    builderBasedDeserializer.A0d(e5, A014, A0b3, qrVar);
                                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                                }
                            }
                            qiVar.A0o();
                        }
                    } else if (cls2 == null || A004.A0A(cls2)) {
                        try {
                            A014 = A004.A05(qiVar, qrVar, A014);
                            qiVar.A0o();
                        } catch (Exception e6) {
                            builderBasedDeserializer.A0d(e6, A014, A0b3, qrVar);
                            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                        }
                    }
                    qiVar.A0T();
                    qiVar.A0o();
                }
                jd2.A09();
                builderBasedDeserializer._unwrappedPropertyHandler.A00(qrVar, A014, jd2);
                return A014;
            } else if (builderBasedDeserializer._externalTypeIdHandler == null) {
                return builderBasedDeserializer.A0X(qiVar, qrVar);
            } else {
                if (builderBasedDeserializer._propertyBasedCreator == null) {
                    return BuilderBasedDeserializer.A01(builderBasedDeserializer, qiVar, qrVar, builderBasedDeserializer._valueInstantiator.A01(qrVar));
                }
                throw new IllegalStateException("Deserialization with Builder, External type id, @JsonCreator not yet implemented");
            }
        } else {
            BeanAsArrayBuilderDeserializer.A01((BeanAsArrayBuilderDeserializer) this, qiVar);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }

    public final Object A0R(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        JsonDeserializer jsonDeserializer = this._delegateDeserializer;
        if (jsonDeserializer != null) {
            try {
                Object A02 = this._valueInstantiator.A02(qrVar, jsonDeserializer.A0C(qiVar, qrVar));
                if (this._injectables != null) {
                    A0a(qrVar);
                }
                return A02;
            } catch (Exception e) {
                A0c(e, qrVar);
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
        } else {
            qrVar.A0J();
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }

    public final Object A0S(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        JsonDeserializer jsonDeserializer = this._delegateDeserializer;
        if (jsonDeserializer != null) {
            Ok ok = this._valueInstantiator;
            if (!(ok instanceof C1042rH) || ((C1042rH) ok)._fromBooleanCreator == null) {
                Object A02 = ok.A02(qrVar, jsonDeserializer.A0C(qiVar, qrVar));
                if (this._injectables == null) {
                    return A02;
                }
                A0a(qrVar);
                return A02;
            }
        }
        boolean z = false;
        if (qiVar.A0U() == NX.VALUE_TRUE) {
            z = true;
        }
        return this._valueInstantiator.A04(qrVar, z);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0034, code lost:
        if (r0 == false) goto L_0x004c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x004a, code lost:
        if (r0 == false) goto L_0x004c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x004c, code lost:
        r0 = r2.A02(r7, r3.A0C(r6, r7));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object A0U(X.AbstractC1014qi r6, X.AbstractC1022qr r7) {
        /*
        // Method dump skipped, instructions count: 274
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BeanDeserializerBase.A0U(X.qi, X.qr):java.lang.Object");
    }

    public final Object A0V(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        if (this._objectIdReader != null) {
            return A04(this, qiVar, qrVar);
        }
        JsonDeserializer jsonDeserializer = this._delegateDeserializer;
        if (jsonDeserializer != null) {
            Ok ok = this._valueInstantiator;
            if (!(ok instanceof C1042rH) || ((C1042rH) ok)._fromStringCreator == null) {
                Object A02 = ok.A02(qrVar, jsonDeserializer.A0C(qiVar, qrVar));
                if (this._injectables == null) {
                    return A02;
                }
                A0a(qrVar);
                return A02;
            }
        }
        return this._valueInstantiator.A03(qrVar, qiVar.A0p());
    }

    public final Object A0W(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        String str = this._objectIdReader.propertyName;
        if (str.equals(qiVar.A0b())) {
            return A0Q(qiVar, qrVar);
        }
        JD jd = new JD(qiVar.A0W());
        JD jd2 = null;
        while (qiVar.A0U() != NX.END_OBJECT) {
            String A0b = qiVar.A0b();
            if (jd2 != null) {
                jd2.A0M(A0b);
                qiVar.A0o();
                jd2.A0X(qiVar);
            } else if (str.equals(A0b)) {
                jd2 = new JD(qiVar.A0W());
                jd2.A0M(A0b);
                qiVar.A0o();
                jd2.A0X(qiVar);
                C0680fB fBVar = new C0680fB(jd.A02, jd.A00);
                while (fBVar.A0o() != null) {
                    JD.A00(jd2, fBVar);
                }
                jd = null;
            } else {
                jd.A0M(A0b);
                qiVar.A0o();
                jd.A0X(qiVar);
            }
            qiVar.A0o();
        }
        if (jd2 == null) {
            jd2 = jd;
        }
        jd2.A09();
        C0680fB fBVar2 = new C0680fB(jd2.A02, jd2.A00);
        fBVar2.A0o();
        return A0Q(fBVar2, qrVar);
    }

    public final Object A0X(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        JsonDeserializer jsonDeserializer = this._delegateDeserializer;
        if (jsonDeserializer != null) {
            return this._valueInstantiator.A02(qrVar, jsonDeserializer.A0C(qiVar, qrVar));
        }
        if (this._propertyBasedCreator != null) {
            return A0P(qiVar, qrVar);
        }
        if (this._beanType.A0G()) {
            StringBuilder sb = new StringBuilder("Can not instantiate abstract type ");
            sb.append(this._beanType);
            sb.append(" (need to add/enable type information?)");
            throw C1025qv.A00(qiVar, sb.toString());
        }
        StringBuilder sb2 = new StringBuilder("No suitable constructor found for type ");
        sb2.append(this._beanType);
        sb2.append(": can not instantiate from JSON object (need to add/enable type information?)");
        throw C1025qv.A00(qiVar, sb2.toString());
    }

    public final void A0Z(AbstractC1014qi qiVar, AbstractC1022qr qrVar, Object obj, String str) {
        HashSet hashSet = this._ignorableProps;
        if (hashSet == null || !hashSet.contains(str)) {
            C0267Oj oj = this._anySetter;
            if (oj != null) {
                try {
                    oj.A01(qiVar, qrVar, obj, str);
                } catch (Exception e) {
                    A0d(e, obj, str, qrVar);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            } else {
                A0L(qiVar, qrVar, obj, str);
            }
        } else {
            qiVar.A0T();
        }
    }

    public final void A0a(AbstractC1022qr qrVar) {
        TZ[] tzArr = this._injectables;
        if (0 < tzArr.length) {
            qrVar.A0N(tzArr[0].A00);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001c, code lost:
        if (r4.A0O(X.EnumC1023qs.WRAP_EXCEPTIONS) != false) goto L_0x001e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A0c(java.lang.Throwable r3, X.AbstractC1022qr r4) {
        /*
            r2 = this;
        L_0x0000:
            boolean r0 = r3 instanceof java.lang.reflect.InvocationTargetException
            if (r0 == 0) goto L_0x000f
            java.lang.Throwable r0 = r3.getCause()
            if (r0 == 0) goto L_0x000f
            java.lang.Throwable r3 = r3.getCause()
            goto L_0x0000
        L_0x000f:
            boolean r0 = r3 instanceof java.lang.Error
            if (r0 != 0) goto L_0x0034
            if (r4 == 0) goto L_0x001e
            X.qs r0 = X.EnumC1023qs.WRAP_EXCEPTIONS
            boolean r0 = r4.A0O(r0)
            r1 = 0
            if (r0 == 0) goto L_0x001f
        L_0x001e:
            r1 = 1
        L_0x001f:
            boolean r0 = r3 instanceof java.io.IOException
            if (r0 != 0) goto L_0x0033
            if (r1 != 0) goto L_0x002a
            boolean r0 = r3 instanceof java.lang.RuntimeException
            if (r0 == 0) goto L_0x002a
            throw r3
        L_0x002a:
            X.qt r0 = r2._beanType
            java.lang.Class r0 = r0._class
            X.qv r0 = r4.A0C(r0, r3)
            throw r0
        L_0x0033:
            throw r3
        L_0x0034:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BeanDeserializerBase.A0c(java.lang.Throwable, X.qr):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001c, code lost:
        if (r6.A0O(X.EnumC1023qs.WRAP_EXCEPTIONS) != false) goto L_0x001e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A0d(java.lang.Throwable r3, java.lang.Object r4, java.lang.String r5, X.AbstractC1022qr r6) {
        /*
            r2 = this;
        L_0x0000:
            boolean r0 = r3 instanceof java.lang.reflect.InvocationTargetException
            if (r0 == 0) goto L_0x000f
            java.lang.Throwable r0 = r3.getCause()
            if (r0 == 0) goto L_0x000f
            java.lang.Throwable r3 = r3.getCause()
            goto L_0x0000
        L_0x000f:
            boolean r0 = r3 instanceof java.lang.Error
            if (r0 != 0) goto L_0x003b
            if (r6 == 0) goto L_0x001e
            X.qs r0 = X.EnumC1023qs.WRAP_EXCEPTIONS
            boolean r0 = r6.A0O(r0)
            r1 = 0
            if (r0 == 0) goto L_0x001f
        L_0x001e:
            r1 = 1
        L_0x001f:
            boolean r0 = r3 instanceof java.io.IOException
            if (r0 == 0) goto L_0x0033
            if (r1 == 0) goto L_0x003a
            boolean r0 = r3 instanceof X.NV
            if (r0 == 0) goto L_0x003a
        L_0x0029:
            X.O9 r0 = new X.O9
            r0.<init>(r4, r5)
            X.qv r0 = X.C1025qv.A01(r3, r0)
            throw r0
        L_0x0033:
            if (r1 != 0) goto L_0x0029
            boolean r0 = r3 instanceof java.lang.RuntimeException
            if (r0 == 0) goto L_0x0029
            throw r3
        L_0x003a:
            throw r3
        L_0x003b:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BeanDeserializerBase.A0d(java.lang.Throwable, java.lang.Object, java.lang.String, X.qr):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0099, code lost:
        if (r6 == null) goto L_0x009c;
     */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0071 A[LOOP:0: B:28:0x006f->B:29:0x0071, LOOP_END] */
    @Override // X.AbstractC0264Od
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.fasterxml.jackson.databind.JsonDeserializer A1X(X.AbstractC1022qr r13, X.O5 r14) {
        /*
        // Method dump skipped, instructions count: 245
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BeanDeserializerBase.A1X(X.qr, X.O5):com.fasterxml.jackson.databind.JsonDeserializer");
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:207:0x0158 */
    /* JADX WARNING: Removed duplicated region for block: B:198:0x0297 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0092  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00b1  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00ea  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x00fb  */
    @Override // X.Oi
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A4s(X.AbstractC1022qr r23) {
        /*
        // Method dump skipped, instructions count: 877
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BeanDeserializerBase.A4s(X.qr):void");
    }

    private final void A0b(AbstractC1022qr qrVar, Object obj, JD jd) {
        jd.A09();
        C0680fB fBVar = new C0680fB(jd.A02, jd.A00);
        while (fBVar.A0o() != NX.END_OBJECT) {
            String A0b = fBVar.A0b();
            fBVar.A0o();
            A0L(fBVar, qrVar, obj, A0b);
        }
    }

    public final Object A0T(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        boolean z;
        switch (qiVar.A0X().intValue()) {
            case 3:
            case 4:
                JsonDeserializer jsonDeserializer = this._delegateDeserializer;
                if (jsonDeserializer != null) {
                    Ok ok = this._valueInstantiator;
                    if (!(ok instanceof C1042rH)) {
                        z = false;
                    } else {
                        z = false;
                        if (((C1042rH) ok)._fromDoubleCreator != null) {
                            z = true;
                        }
                    }
                    if (!z) {
                        Object A02 = ok.A02(qrVar, jsonDeserializer.A0C(qiVar, qrVar));
                        if (this._injectables == null) {
                            return A02;
                        }
                        A0a(qrVar);
                        return A02;
                    }
                }
                Ok ok2 = this._valueInstantiator;
                double A0F = qiVar.A0F();
                if (!(ok2 instanceof C1042rH)) {
                    throw new C1025qv(AnonymousClass08.A05("Can not instantiate value of type ", ok2.A05(), " from Floating-point number (double)"));
                }
                C1042rH rHVar = (C1042rH) ok2;
                try {
                    SV sv = rHVar._fromDoubleCreator;
                    if (sv != null) {
                        return sv.A0U(Double.valueOf(A0F));
                    }
                    throw new C1025qv(AnonymousClass08.A05("Can not instantiate value of type ", rHVar.A05(), " from Floating-point number; no one-double/Double-arg constructor/factory method"));
                } catch (Exception e) {
                    throw C1042rH.A00(rHVar, e);
                } catch (ExceptionInInitializerError e2) {
                    throw C1042rH.A00(rHVar, e2);
                }
            default:
                JsonDeserializer jsonDeserializer2 = this._delegateDeserializer;
                if (jsonDeserializer2 != null) {
                    return this._valueInstantiator.A02(qrVar, jsonDeserializer2.A0C(qiVar, qrVar));
                }
                throw qrVar.A0B(this._beanType._class, "no suitable creator method found to deserialize from JSON floating-point number");
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public BeanDeserializerBase(com.fasterxml.jackson.databind.deser.BeanDeserializerBase r3, X.C0273Ou r4) {
        /*
            r2 = this;
            X.qt r1 = r3._beanType
            r2.<init>(r1)
            X.Q0 r0 = r3.A01
            r2.A01 = r0
            r2._beanType = r1
            X.Ok r0 = r3._valueInstantiator
            r2._valueInstantiator = r0
            com.fasterxml.jackson.databind.JsonDeserializer r0 = r3._delegateDeserializer
            r2._delegateDeserializer = r0
            X.Ov r0 = r3._propertyBasedCreator
            r2._propertyBasedCreator = r0
            java.util.Map r0 = r3._backRefs
            r2._backRefs = r0
            java.util.HashSet r0 = r3._ignorableProps
            r2._ignorableProps = r0
            boolean r0 = r3._ignoreAllUnknown
            r2._ignoreAllUnknown = r0
            X.Oj r0 = r3._anySetter
            r2._anySetter = r0
            X.TZ[] r0 = r3._injectables
            r2._injectables = r0
            boolean r0 = r3._nonStandardCreation
            r2._nonStandardCreation = r0
            X.Oz r0 = r3._unwrappedPropertyHandler
            r2._unwrappedPropertyHandler = r0
            boolean r0 = r3._needViewProcesing
            r2._needViewProcesing = r0
            X.Mu r0 = r3._serializationShape
            r2._serializationShape = r0
            boolean r0 = r3._vanillaProcessing
            r2._vanillaProcessing = r0
            r2._objectIdReader = r4
            if (r4 != 0) goto L_0x0048
            X.Oo r0 = r3._beanProperties
        L_0x0045:
            r2._beanProperties = r0
            return
        L_0x0048:
            X.Tb r1 = new X.Tb
            r1.<init>(r4)
            X.Oo r0 = r3._beanProperties
            X.Oo r0 = r0.A01(r1)
            goto L_0x0045
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BeanDeserializerBase.<init>(com.fasterxml.jackson.databind.deser.BeanDeserializerBase, X.Ou):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0025, code lost:
        if (r8._ignoreAllUnknown != false) goto L_0x0027;
     */
    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public BeanDeserializerBase(com.fasterxml.jackson.databind.deser.BeanDeserializerBase r8, X.QC r9) {
        /*
        // Method dump skipped, instructions count: 210
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BeanDeserializerBase.<init>(com.fasterxml.jackson.databind.deser.BeanDeserializerBase, X.QC):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public BeanDeserializerBase(com.fasterxml.jackson.databind.deser.BeanDeserializerBase r3, java.util.HashSet r4) {
        /*
            r2 = this;
            X.qt r1 = r3._beanType
            r2.<init>(r1)
            X.Q0 r0 = r3.A01
            r2.A01 = r0
            r2._beanType = r1
            X.Ok r0 = r3._valueInstantiator
            r2._valueInstantiator = r0
            com.fasterxml.jackson.databind.JsonDeserializer r0 = r3._delegateDeserializer
            r2._delegateDeserializer = r0
            X.Ov r0 = r3._propertyBasedCreator
            r2._propertyBasedCreator = r0
            java.util.Map r0 = r3._backRefs
            r2._backRefs = r0
            r2._ignorableProps = r4
            boolean r0 = r3._ignoreAllUnknown
            r2._ignoreAllUnknown = r0
            X.Oj r0 = r3._anySetter
            r2._anySetter = r0
            X.TZ[] r0 = r3._injectables
            r2._injectables = r0
            boolean r0 = r3._nonStandardCreation
            r2._nonStandardCreation = r0
            X.Oz r0 = r3._unwrappedPropertyHandler
            r2._unwrappedPropertyHandler = r0
            boolean r0 = r3._needViewProcesing
            r2._needViewProcesing = r0
            X.Mu r0 = r3._serializationShape
            r2._serializationShape = r0
            boolean r0 = r3._vanillaProcessing
            r2._vanillaProcessing = r0
            X.Ou r0 = r3._objectIdReader
            r2._objectIdReader = r0
            X.Oo r0 = r3._beanProperties
            r2._beanProperties = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BeanDeserializerBase.<init>(com.fasterxml.jackson.databind.deser.BeanDeserializerBase, java.util.HashSet):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public BeanDeserializerBase(com.fasterxml.jackson.databind.deser.BeanDeserializerBase r3, boolean r4) {
        /*
            r2 = this;
            X.qt r1 = r3._beanType
            r2.<init>(r1)
            X.Q0 r0 = r3.A01
            r2.A01 = r0
            r2._beanType = r1
            X.Ok r0 = r3._valueInstantiator
            r2._valueInstantiator = r0
            com.fasterxml.jackson.databind.JsonDeserializer r0 = r3._delegateDeserializer
            r2._delegateDeserializer = r0
            X.Ov r0 = r3._propertyBasedCreator
            r2._propertyBasedCreator = r0
            X.Oo r0 = r3._beanProperties
            r2._beanProperties = r0
            java.util.Map r0 = r3._backRefs
            r2._backRefs = r0
            java.util.HashSet r0 = r3._ignorableProps
            r2._ignorableProps = r0
            r2._ignoreAllUnknown = r4
            X.Oj r0 = r3._anySetter
            r2._anySetter = r0
            X.TZ[] r0 = r3._injectables
            r2._injectables = r0
            X.Ou r0 = r3._objectIdReader
            r2._objectIdReader = r0
            boolean r0 = r3._nonStandardCreation
            r2._nonStandardCreation = r0
            X.Oz r0 = r3._unwrappedPropertyHandler
            r2._unwrappedPropertyHandler = r0
            boolean r0 = r3._needViewProcesing
            r2._needViewProcesing = r0
            X.Mu r0 = r3._serializationShape
            r2._serializationShape = r0
            boolean r0 = r3._vanillaProcessing
            r2._vanillaProcessing = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BeanDeserializerBase.<init>(com.fasterxml.jackson.databind.deser.BeanDeserializerBase, boolean):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x005c, code lost:
        if (r2.A07() == false) goto L_0x005e;
     */
    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public BeanDeserializerBase(X.C0262Oa r5, X.O4 r6, X.Oo r7, java.util.Map r8, java.util.HashSet r9, boolean r10, boolean r11) {
        /*
        // Method dump skipped, instructions count: 129
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BeanDeserializerBase.<init>(X.Oa, X.O4, X.Oo, java.util.Map, java.util.HashSet, boolean, boolean):void");
    }
}
