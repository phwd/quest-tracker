package com.fasterxml.jackson.databind.deser;

import X.AbstractC1014qi;
import X.AbstractC1022qr;
import X.AbstractC1024qt;
import X.AbstractC1034r7;
import X.AbstractC1055rV;
import X.AnonymousClass08;
import X.C0262Oa;
import X.C0267Oj;
import X.C0270Or;
import X.C0271Os;
import X.C0273Ou;
import X.C0274Ov;
import X.C0276Ox;
import X.C1025qv;
import X.C1042rH;
import X.JD;
import X.NX;
import X.O4;
import X.OY;
import X.Ok;
import X.Oo;
import X.PR;
import X.QC;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.std.ThrowableDeserializer;
import com.oculus.aidl.OVRServiceInterface;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;

public class BeanDeserializer extends BeanDeserializerBase implements Serializable {
    public static final long serialVersionUID = 1;

    private final Object A00(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        Object A01 = this._valueInstantiator.A01(qrVar);
        while (qiVar.A0U() != NX.END_OBJECT) {
            String A0b = qiVar.A0b();
            qiVar.A0o();
            AbstractC1034r7 A00 = this._beanProperties.A00(A0b);
            if (A00 != null) {
                try {
                    A00.A07(qiVar, qrVar, A01);
                } catch (Exception e) {
                    A0d(e, A01, A0b, qrVar);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            } else {
                A0Z(qiVar, qrVar, A01, A0b);
            }
            qiVar.A0o();
        }
        return A01;
    }

    public static final void A01(BeanDeserializer beanDeserializer, AbstractC1014qi qiVar, AbstractC1022qr qrVar, Object obj) {
        Class cls;
        Number number;
        if (beanDeserializer._needViewProcesing) {
            cls = qrVar._view;
        } else {
            cls = null;
        }
        C0271Os os = new C0271Os(beanDeserializer._externalTypeIdHandler);
        while (qiVar.A0U() != NX.END_OBJECT) {
            String A0b = qiVar.A0b();
            qiVar.A0o();
            AbstractC1034r7 A00 = beanDeserializer._beanProperties.A00(A0b);
            if (A00 != null) {
                if (qiVar.A0U().isScalarValue() && (number = (Number) os.A00.get(A0b)) != null) {
                    int intValue = number.intValue();
                    if (A0b.equals(os.A01[intValue].A02)) {
                        String A0p = qiVar.A0p();
                        if (obj != null) {
                            JD[] jdArr = os.A02;
                            if (jdArr[intValue] != null) {
                                C0271Os.A00(os, qiVar, qrVar, obj, intValue, A0p);
                                jdArr[intValue] = null;
                            }
                        }
                        os.A03[intValue] = A0p;
                    }
                }
                if (cls == null || A00.A0A(cls)) {
                    try {
                        A00.A07(qiVar, qrVar, obj);
                        qiVar.A0o();
                    } catch (Exception e) {
                        beanDeserializer.A0d(e, obj, A0b, qrVar);
                        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                    }
                }
            } else {
                HashSet hashSet = beanDeserializer._ignorableProps;
                if (hashSet == null || !hashSet.contains(A0b)) {
                    if (!os.A02(qiVar, qrVar, A0b, obj)) {
                        C0267Oj oj = beanDeserializer._anySetter;
                        if (oj != null) {
                            try {
                                oj.A01(qiVar, qrVar, obj, A0b);
                            } catch (Exception e2) {
                                beanDeserializer.A0d(e2, obj, A0b, qrVar);
                                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                            }
                        } else {
                            beanDeserializer.A0L(qiVar, qrVar, obj, A0b);
                        }
                    }
                    qiVar.A0o();
                }
            }
            qiVar.A0T();
            qiVar.A0o();
        }
        os.A01(qiVar, qrVar, obj);
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase
    public final Object A0Q(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        boolean z;
        Object A01;
        Class cls;
        JD[] jdArr;
        Class cls2;
        Class cls3;
        Class cls4;
        Object obj;
        if (!(this instanceof ThrowableDeserializer)) {
            if (!this._nonStandardCreation) {
                Object A012 = this._valueInstantiator.A01(qrVar);
                if (this._injectables != null) {
                    A0a(qrVar);
                }
                if (!this._needViewProcesing || (cls = qrVar._view) == null) {
                    while (qiVar.A0U() != NX.END_OBJECT) {
                        String A0b = qiVar.A0b();
                        qiVar.A0o();
                        AbstractC1034r7 A00 = this._beanProperties.A00(A0b);
                        if (A00 != null) {
                            try {
                                A00.A07(qiVar, qrVar, A012);
                            } catch (Exception e) {
                                A0d(e, A012, A0b, qrVar);
                                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                            }
                        } else {
                            HashSet hashSet = this._ignorableProps;
                            if (hashSet == null || !hashSet.contains(A0b)) {
                                C0267Oj oj = this._anySetter;
                                if (oj != null) {
                                    try {
                                        oj.A01(qiVar, qrVar, A012, A0b);
                                    } catch (Exception e2) {
                                        A0d(e2, A012, A0b, qrVar);
                                        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                                    }
                                } else {
                                    A0L(qiVar, qrVar, A012, A0b);
                                }
                            } else {
                                qiVar.A0T();
                            }
                        }
                        qiVar.A0o();
                    }
                    return A012;
                }
                A02(this, qiVar, qrVar, A012, cls);
                return A012;
            } else if (this._unwrappedPropertyHandler != null) {
                JsonDeserializer jsonDeserializer = this._delegateDeserializer;
                if (jsonDeserializer != null) {
                    return this._valueInstantiator.A02(qrVar, jsonDeserializer.A0C(qiVar, qrVar));
                }
                C0274Ov ov = this._propertyBasedCreator;
                if (ov != null) {
                    C0276Ox A013 = ov.A01(qiVar, qrVar, this._objectIdReader);
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
                                        if (obj.getClass() != this._beanType._class) {
                                            throw C1025qv.A00(null, "Can not create polymorphic instances with unwrapped values");
                                        }
                                    } catch (Exception e3) {
                                        A0d(e3, this._beanType._class, A0b2, qrVar);
                                        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                                    }
                                }
                            } else if (!A013.A03(A0b2)) {
                                AbstractC1034r7 A002 = this._beanProperties.A00(A0b2);
                                if (A002 != null) {
                                    A013.A01(A002, A002.A04(qiVar, qrVar));
                                } else {
                                    HashSet hashSet2 = this._ignorableProps;
                                    if (hashSet2 == null || !hashSet2.contains(A0b2)) {
                                        jd.A0M(A0b2);
                                        jd.A0X(qiVar);
                                        C0267Oj oj2 = this._anySetter;
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
                                A0c(e4, qrVar);
                                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                            }
                        }
                    }
                    this._unwrappedPropertyHandler.A00(qrVar, obj, jd);
                    return obj;
                }
                JD jd2 = new JD(qiVar.A0W());
                jd2.A0C();
                Object A014 = this._valueInstantiator.A01(qrVar);
                if (this._injectables != null) {
                    A0a(qrVar);
                }
                if (this._needViewProcesing) {
                    cls4 = qrVar._view;
                } else {
                    cls4 = null;
                }
                while (qiVar.A0U() != NX.END_OBJECT) {
                    String A0b3 = qiVar.A0b();
                    qiVar.A0o();
                    AbstractC1034r7 A003 = this._beanProperties.A00(A0b3);
                    if (A003 == null) {
                        HashSet hashSet3 = this._ignorableProps;
                        if (hashSet3 == null || !hashSet3.contains(A0b3)) {
                            jd2.A0M(A0b3);
                            jd2.A0X(qiVar);
                            C0267Oj oj3 = this._anySetter;
                            if (oj3 != null) {
                                try {
                                    oj3.A01(qiVar, qrVar, A014, A0b3);
                                } catch (Exception e5) {
                                    A0d(e5, A014, A0b3, qrVar);
                                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                                }
                            }
                            qiVar.A0o();
                        }
                    } else if (cls4 == null || A003.A0A(cls4)) {
                        try {
                            A003.A07(qiVar, qrVar, A014);
                            qiVar.A0o();
                        } catch (Exception e6) {
                            A0d(e6, A014, A0b3, qrVar);
                            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                        }
                    }
                    qiVar.A0T();
                    qiVar.A0o();
                }
                jd2.A09();
                this._unwrappedPropertyHandler.A00(qrVar, A014, jd2);
                return A014;
            } else {
                C0271Os os = this._externalTypeIdHandler;
                if (os == null) {
                    return A0X(qiVar, qrVar);
                }
                C0274Ov ov2 = this._propertyBasedCreator;
                if (ov2 != null) {
                    C0271Os os2 = new C0271Os(os);
                    C0276Ox A015 = ov2.A01(qiVar, qrVar, this._objectIdReader);
                    JD jd3 = new JD(qiVar.A0W());
                    jd3.A0C();
                    NX A0U2 = qiVar.A0U();
                    while (A0U2 == NX.FIELD_NAME) {
                        String A0b4 = qiVar.A0b();
                        qiVar.A0o();
                        AbstractC1034r7 r7Var2 = (AbstractC1034r7) ov2.A00.get(A0b4);
                        if (r7Var2 != null) {
                            if (os2.A02(qiVar, qrVar, A0b4, A015)) {
                                continue;
                            } else if (A015.A02(r7Var2.A01(), r7Var2.A04(qiVar, qrVar))) {
                                NX A0o2 = qiVar.A0o();
                                try {
                                    Object A02 = ov2.A02(qrVar, A015);
                                    while (A0o2 == NX.FIELD_NAME) {
                                        qiVar.A0o();
                                        jd3.A0X(qiVar);
                                        A0o2 = qiVar.A0o();
                                    }
                                    if (A02.getClass() == this._beanType._class) {
                                        os2.A01(qiVar, qrVar, A02);
                                        return A02;
                                    }
                                    throw C1025qv.A00(null, "Can not create polymorphic instances with unwrapped values");
                                } catch (Exception e7) {
                                    A0d(e7, this._beanType._class, A0b4, qrVar);
                                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                                }
                            }
                        } else if (!A015.A03(A0b4)) {
                            AbstractC1034r7 A004 = this._beanProperties.A00(A0b4);
                            if (A004 != null) {
                                A015.A01(A004, A004.A04(qiVar, qrVar));
                            } else if (!os2.A02(qiVar, qrVar, A0b4, null)) {
                                HashSet hashSet4 = this._ignorableProps;
                                if (hashSet4 == null || !hashSet4.contains(A0b4)) {
                                    C0267Oj oj4 = this._anySetter;
                                    if (oj4 != null) {
                                        A015.A00(oj4, A0b4, oj4.A00(qiVar, qrVar));
                                    }
                                } else {
                                    qiVar.A0T();
                                }
                            }
                        }
                        A0U2 = qiVar.A0o();
                    }
                    try {
                        C0270Or[] orArr = os2.A01;
                        int length = orArr.length;
                        Object[] objArr = new Object[length];
                        for (int i = 0; i < length; i++) {
                            String str = os2.A03[i];
                            if (str == null) {
                                jdArr = os2.A02;
                                if (jdArr[i] != null) {
                                    C0270Or or = orArr[i];
                                    AbstractC1024qt qtVar = ((AbstractC1055rV) or.A01)._defaultImpl;
                                    if (qtVar == null) {
                                        cls2 = null;
                                    } else {
                                        cls2 = qtVar._class;
                                    }
                                    boolean z2 = false;
                                    if (cls2 != null) {
                                        z2 = true;
                                    }
                                    if (z2) {
                                        PR pr = orArr[i].A01;
                                        AbstractC1024qt qtVar2 = ((AbstractC1055rV) pr)._defaultImpl;
                                        if (qtVar2 == null) {
                                            cls3 = null;
                                        } else {
                                            cls3 = qtVar2._class;
                                        }
                                        str = cls3 == null ? null : ((AbstractC1055rV) pr)._idResolver.A3G(null, cls3);
                                    } else {
                                        throw C1025qv.A00(null, AnonymousClass08.A05("Missing external type id property '", or.A02, "'"));
                                    }
                                } else {
                                    continue;
                                }
                            } else {
                                jdArr = os2.A02;
                                if (jdArr[i] == null) {
                                    C0270Or or2 = orArr[i];
                                    throw C1025qv.A00(null, AnonymousClass08.A06("Missing property '", or2.A00._propName, "' for external type id '", or2.A02));
                                }
                            }
                            JD jd4 = new JD(qiVar.A0W());
                            jd4.A0B();
                            jd4.A0P(str);
                            AbstractC1014qi A0W = jdArr[i].A0W(qiVar);
                            A0W.A0o();
                            jd4.A0X(A0W);
                            jd4.A08();
                            AbstractC1014qi A0W2 = jd4.A0W(qiVar);
                            A0W2.A0o();
                            objArr[i] = orArr[i].A00.A04(A0W2, qrVar);
                        }
                        for (int i2 = 0; i2 < length; i2++) {
                            AbstractC1034r7 r7Var3 = orArr[i2].A00;
                            if (((AbstractC1034r7) ov2.A00.get(r7Var3._propName)) != null) {
                                A015.A02(r7Var3.A01(), objArr[i2]);
                            }
                        }
                        Object A022 = ov2.A02(qrVar, A015);
                        for (int i3 = 0; i3 < length; i3++) {
                            AbstractC1034r7 r7Var4 = orArr[i3].A00;
                            if (((AbstractC1034r7) ov2.A00.get(r7Var4._propName)) == null) {
                                r7Var4.A09(A022, objArr[i3]);
                            }
                        }
                        return A022;
                    } catch (Exception e8) {
                        A0c(e8, qrVar);
                        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                    }
                } else {
                    Object A016 = this._valueInstantiator.A01(qrVar);
                    A01(this, qiVar, qrVar, A016);
                    return A016;
                }
            }
        } else if (this._propertyBasedCreator != null) {
            return A0P(qiVar, qrVar);
        } else {
            JsonDeserializer jsonDeserializer2 = this._delegateDeserializer;
            if (jsonDeserializer2 != null) {
                return this._valueInstantiator.A02(qrVar, jsonDeserializer2.A0C(qiVar, qrVar));
            }
            if (!this._beanType.A0G()) {
                Ok ok = this._valueInstantiator;
                if (!(ok instanceof C1042rH)) {
                    z = false;
                } else {
                    z = false;
                    if (((C1042rH) ok)._fromStringCreator != null) {
                        z = true;
                    }
                }
                boolean A07 = ok.A07();
                if (z || A07) {
                    Object obj2 = null;
                    Object[] objArr2 = null;
                    int i4 = 0;
                    while (qiVar.A0U() != NX.END_OBJECT) {
                        String A0b5 = qiVar.A0b();
                        AbstractC1034r7 A005 = this._beanProperties.A00(A0b5);
                        qiVar.A0o();
                        if (A005 != null) {
                            if (obj2 != null) {
                                A005.A07(qiVar, qrVar, obj2);
                            } else {
                                if (objArr2 == null) {
                                    int i5 = this._beanProperties._size;
                                    objArr2 = new Object[(i5 + i5)];
                                }
                                int i6 = i4 + 1;
                                objArr2[i4] = A005;
                                i4 = i6 + 1;
                                objArr2[i6] = A005.A04(qiVar, qrVar);
                            }
                        } else if (!"message".equals(A0b5) || !z) {
                            HashSet hashSet5 = this._ignorableProps;
                            if (hashSet5 == null || !hashSet5.contains(A0b5)) {
                                C0267Oj oj5 = this._anySetter;
                                if (oj5 != null) {
                                    oj5.A01(qiVar, qrVar, obj2, A0b5);
                                } else {
                                    A0L(qiVar, qrVar, obj2, A0b5);
                                }
                            } else {
                                qiVar.A0T();
                            }
                        } else {
                            obj2 = this._valueInstantiator.A03(qrVar, qiVar.A0p());
                            if (objArr2 != null) {
                                for (int i7 = 0; i7 < i4; i7 += 2) {
                                    ((AbstractC1034r7) objArr2[i7]).A09(obj2, objArr2[i7 + 1]);
                                }
                                objArr2 = null;
                            }
                        }
                        qiVar.A0o();
                    }
                    if (obj2 != null) {
                        return obj2;
                    }
                    Ok ok2 = this._valueInstantiator;
                    if (z) {
                        A01 = ok2.A03(qrVar, null);
                    } else {
                        A01 = ok2.A01(qrVar);
                    }
                    if (objArr2 == null) {
                        return A01;
                    }
                    for (int i8 = 0; i8 < i4; i8 += 2) {
                        ((AbstractC1034r7) objArr2[i8]).A09(A01, objArr2[i8 + 1]);
                    }
                    return A01;
                }
                StringBuilder sb = new StringBuilder("Can not deserialize Throwable of type ");
                sb.append(this._beanType);
                sb.append(" without having a default contructor, a single-String-arg constructor; or explicit @JsonCreator");
                throw new C1025qv(sb.toString());
            }
            StringBuilder sb2 = new StringBuilder("Can not instantiate abstract type ");
            sb2.append(this._beanType);
            sb2.append(" (need to add/enable type information?)");
            throw C1025qv.A00(qiVar, sb2.toString());
        }
    }

    public static final void A02(BeanDeserializer beanDeserializer, AbstractC1014qi qiVar, AbstractC1022qr qrVar, Object obj, Class cls) {
        NX A0U = qiVar.A0U();
        while (A0U == NX.FIELD_NAME) {
            String A0b = qiVar.A0b();
            qiVar.A0o();
            AbstractC1034r7 A00 = beanDeserializer._beanProperties.A00(A0b);
            if (A00 == null) {
                HashSet hashSet = beanDeserializer._ignorableProps;
                if (hashSet == null || !hashSet.contains(A0b)) {
                    C0267Oj oj = beanDeserializer._anySetter;
                    if (oj != null) {
                        oj.A01(qiVar, qrVar, obj, A0b);
                    } else {
                        beanDeserializer.A0L(qiVar, qrVar, obj, A0b);
                    }
                    A0U = qiVar.A0o();
                }
            } else if (A00.A0A(cls)) {
                try {
                    A00.A07(qiVar, qrVar, obj);
                    A0U = qiVar.A0o();
                } catch (Exception e) {
                    beanDeserializer.A0d(e, obj, A0b, qrVar);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            }
            qiVar.A0T();
            A0U = qiVar.A0o();
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0C(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        NX A0U = qiVar.A0U();
        if (A0U == NX.START_OBJECT) {
            if (this._vanillaProcessing) {
                qiVar.A0o();
                return A00(qiVar, qrVar);
            }
            qiVar.A0o();
        } else if (A0U == null) {
            throw C1025qv.A00(null, AnonymousClass08.A04("Unexpected end-of-input when trying to deserialize a ", this._beanType._class.getName()));
        } else {
            switch (OY.A00[A0U.ordinal()]) {
                case 1:
                    return A0V(qiVar, qrVar);
                case 2:
                    return A0U(qiVar, qrVar);
                case 3:
                    return A0T(qiVar, qrVar);
                case 4:
                    return qiVar.A0Z();
                case 5:
                case 6:
                    return A0S(qiVar, qrVar);
                case OVRServiceInterface.Stub.TRANSACTION_getEntitlementCheckBundle /*{ENCODED_INT: 7}*/:
                    return A0R(qiVar, qrVar);
                case 8:
                case OVRServiceInterface.Stub.TRANSACTION_getLatestAvailableAppInformation /*{ENCODED_INT: 9}*/:
                    if (this._vanillaProcessing) {
                        return A00(qiVar, qrVar);
                    }
                    break;
                default:
                    qrVar.A0J();
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
        }
        if (this._objectIdReader != null) {
            return A0W(qiVar, qrVar);
        }
        return A0Q(qiVar, qrVar);
    }

    public BeanDeserializer(BeanDeserializerBase beanDeserializerBase) {
        super(beanDeserializerBase, beanDeserializerBase._ignoreAllUnknown);
    }

    public BeanDeserializer(BeanDeserializerBase beanDeserializerBase, C0273Ou ou) {
        super(beanDeserializerBase, ou);
    }

    public BeanDeserializer(BeanDeserializerBase beanDeserializerBase, QC qc) {
        super(beanDeserializerBase, qc);
    }

    public BeanDeserializer(BeanDeserializerBase beanDeserializerBase, HashSet hashSet) {
        super(beanDeserializerBase, hashSet);
    }

    public BeanDeserializer(C0262Oa oa, O4 o4, Oo oo, Map map, HashSet hashSet, boolean z, boolean z2) {
        super(oa, o4, oo, map, hashSet, z, z2);
    }
}
