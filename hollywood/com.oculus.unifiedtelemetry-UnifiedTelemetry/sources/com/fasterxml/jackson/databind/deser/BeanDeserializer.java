package com.fasterxml.jackson.databind.deser;

import X.AbstractC0073Cr;
import X.AbstractC0226Wn;
import X.AbstractC0232Ww;
import X.AnonymousClass06;
import X.Br;
import X.C0201Vh;
import X.C0203Vk;
import X.C0204Vm;
import X.C0223Wj;
import X.C0245Xs;
import X.C0263Yn;
import X.C0295aT;
import X.C0307ah;
import X.EnumC0470q2;
import X.KI;
import X.X5;
import X.jm;
import X.q0;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.impl.BeanAsArrayDeserializer;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;

public class BeanDeserializer extends BeanDeserializerBase implements Serializable {
    public static final long serialVersionUID = 1;

    private final Object A00(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        Object A05 = this._valueInstantiator.A05(wn);
        while (ww.A0Z() != EnumC0470q2.END_OBJECT) {
            String A0c = ww.A0c();
            ww.A0a();
            AbstractC0073Cr A00 = this._beanProperties.A00(A0c);
            if (A00 != null) {
                try {
                    A00.A08(ww, wn, A05);
                } catch (Exception e) {
                    A0g(e, A05, A0c, wn);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            } else {
                A0c(ww, wn, A05, A0c);
            }
            ww.A0a();
        }
        return A05;
    }

    private final void A01(AbstractC0232Ww ww, AbstractC0226Wn wn, Object obj) throws IOException, q0 {
        Class<?> cls;
        Integer num;
        if (this._needViewProcesing) {
            cls = wn._view;
        } else {
            cls = null;
        }
        X5 x5 = new X5(this._externalTypeIdHandler);
        while (ww.A0Z() != EnumC0470q2.END_OBJECT) {
            String A0c = ww.A0c();
            ww.A0a();
            AbstractC0073Cr A00 = this._beanProperties.A00(A0c);
            if (A00 != null) {
                if (ww.A0Z().isScalarValue() && (num = x5.A00.get(A0c)) != null) {
                    int intValue = num.intValue();
                    if (A0c.equals(x5.A01[intValue].A02)) {
                        String A0d = ww.A0d();
                        if (obj != null) {
                            Br[] brArr = x5.A02;
                            if (brArr[intValue] != null) {
                                X5.A00(x5, ww, wn, obj, intValue, A0d);
                                brArr[intValue] = null;
                            }
                        }
                        x5.A03[intValue] = A0d;
                    }
                }
                if (cls == null || A00.A0B(cls)) {
                    try {
                        A00.A08(ww, wn, obj);
                        ww.A0a();
                    } catch (Exception e) {
                        A0g(e, obj, A0c, wn);
                        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                    }
                }
            } else {
                HashSet<String> hashSet = this._ignorableProps;
                if (hashSet == null || !hashSet.contains(A0c)) {
                    if (!x5.A02(ww, wn, A0c, obj)) {
                        C0263Yn yn = this._anySetter;
                        if (yn != null) {
                            yn.A01(ww, wn, obj, A0c);
                        } else {
                            A0N(ww, wn, obj, A0c);
                        }
                    }
                    ww.A0a();
                }
            }
            ww.A0Y();
            ww.A0a();
        }
        x5.A01(ww, wn, obj);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0A(AbstractC0232Ww ww, AbstractC0226Wn wn, Object obj) throws IOException, q0 {
        Class<?> cls;
        Class<?> cls2;
        if (this._injectables != null) {
            A0d(wn);
        }
        if (this._unwrappedPropertyHandler != null) {
            EnumC0470q2 A0Z = ww.A0Z();
            if (A0Z == EnumC0470q2.START_OBJECT) {
                A0Z = ww.A0a();
            }
            Br br = new Br(ww.A0H());
            br.A07();
            if (this._needViewProcesing) {
                cls2 = wn._view;
            } else {
                cls2 = null;
            }
            while (A0Z == EnumC0470q2.FIELD_NAME) {
                String A0c = ww.A0c();
                AbstractC0073Cr A00 = this._beanProperties.A00(A0c);
                ww.A0a();
                if (A00 == null) {
                    HashSet<String> hashSet = this._ignorableProps;
                    if (hashSet == null || !hashSet.contains(A0c)) {
                        br.A09(A0c);
                        br.A08(ww);
                        C0263Yn yn = this._anySetter;
                        if (yn != null) {
                            yn.A01(ww, wn, obj, A0c);
                        }
                        A0Z = ww.A0a();
                    }
                } else if (cls2 == null || A00.A0B(cls2)) {
                    try {
                        A00.A08(ww, wn, obj);
                        A0Z = ww.A0a();
                    } catch (Exception e) {
                        A0g(e, obj, A0c, wn);
                    }
                }
                ww.A0Y();
                A0Z = ww.A0a();
            }
            br.A05();
            this._unwrappedPropertyHandler.A00(wn, obj, br);
        } else if (this._externalTypeIdHandler != null) {
            A01(ww, wn, obj);
            return obj;
        } else {
            EnumC0470q2 A0Z2 = ww.A0Z();
            if (A0Z2 == EnumC0470q2.START_OBJECT) {
                A0Z2 = ww.A0a();
            }
            if (!this._needViewProcesing || (cls = wn._view) == null) {
                while (A0Z2 == EnumC0470q2.FIELD_NAME) {
                    String A0c2 = ww.A0c();
                    ww.A0a();
                    AbstractC0073Cr A002 = this._beanProperties.A00(A0c2);
                    if (A002 != null) {
                        try {
                            A002.A08(ww, wn, obj);
                        } catch (Exception e2) {
                            A0g(e2, obj, A0c2, wn);
                        }
                    } else {
                        HashSet<String> hashSet2 = this._ignorableProps;
                        if (hashSet2 == null || !hashSet2.contains(A0c2)) {
                            C0263Yn yn2 = this._anySetter;
                            if (yn2 != null) {
                                yn2.A01(ww, wn, obj, A0c2);
                            } else {
                                A0N(ww, wn, obj, A0c2);
                            }
                        } else {
                            ww.A0Y();
                        }
                    }
                    A0Z2 = ww.A0a();
                }
            } else {
                A02(ww, wn, obj, cls);
                return obj;
            }
        }
        return obj;
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase
    public final BeanDeserializerBase A0P() {
        return new BeanAsArrayDeserializer(this, this._beanProperties.A04());
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase
    public final BeanDeserializerBase A0Q(C0204Vm vm) {
        return new BeanDeserializer(this, vm);
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase
    public final BeanDeserializerBase A0R(HashSet hashSet) {
        return new BeanDeserializer(this, hashSet);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:103:?, code lost:
        r3 = r8.A02(r15, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x01c3, code lost:
        if (r1 != X.EnumC0470q2.FIELD_NAME) goto L_0x02ae;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x01c5, code lost:
        r14.A0a();
        r4.A08(r14);
        r1 = r14.A0a();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x01d0, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x01d1, code lost:
        A0g(r1, r13._beanType._class, r2, r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:144:0x02b6, code lost:
        if (r3.getClass() != r13._beanType._class) goto L_0x02bc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:145:0x02b8, code lost:
        r9.A01(r14, r15, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x02bb, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00b6, code lost:
        if (r4.getClass() == r13._beanType._class) goto L_0x00b8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x017e, code lost:
        r1 = r14.A0a();
     */
    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object A0Z(X.AbstractC0232Ww r14, X.AbstractC0226Wn r15) throws java.io.IOException, X.q0 {
        /*
        // Method dump skipped, instructions count: 831
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BeanDeserializer.A0Z(X.Ww, X.Wn):java.lang.Object");
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase
    public final Object A0a(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        C0203Vk vk = this._propertyBasedCreator;
        C0201Vh A01 = vk.A01(ww, wn, this._objectIdReader);
        EnumC0470q2 A0Z = ww.A0Z();
        Br br = null;
        while (A0Z == EnumC0470q2.FIELD_NAME) {
            String A0c = ww.A0c();
            ww.A0a();
            AbstractC0073Cr cr = vk.A00.get(A0c);
            if (cr != null) {
                if (A01.A02(cr.A01(), cr.A05(ww, wn))) {
                    ww.A0a();
                    try {
                        Object A02 = vk.A02(wn, A01);
                        if (A02.getClass() != this._beanType._class) {
                            return A0b(ww, wn, A02, br);
                        }
                        if (br != null) {
                            A0e(wn, A02, br);
                        }
                        A0A(ww, wn, A02);
                        return A02;
                    } catch (Exception e) {
                        A0g(e, this._beanType._class, A0c, wn);
                        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                    }
                }
            } else if (!A01.A03(A0c)) {
                AbstractC0073Cr A00 = this._beanProperties.A00(A0c);
                if (A00 != null) {
                    A01.A01(A00, A00.A05(ww, wn));
                } else {
                    HashSet<String> hashSet = this._ignorableProps;
                    if (hashSet == null || !hashSet.contains(A0c)) {
                        C0263Yn yn = this._anySetter;
                        if (yn != null) {
                            A01.A00(yn, A0c, yn.A00(ww, wn));
                        } else {
                            if (br == null) {
                                br = new Br(ww.A0H());
                            }
                            br.A09(A0c);
                            br.A08(ww);
                        }
                    } else {
                        ww.A0Y();
                    }
                }
            }
            A0Z = ww.A0a();
        }
        try {
            Object A022 = vk.A02(wn, A01);
            if (br == null) {
                return A022;
            }
            if (A022.getClass() != this._beanType._class) {
                return A0b(null, wn, A022, br);
            }
            A0e(wn, A022, br);
            return A022;
        } catch (Exception e2) {
            A0f(e2, wn);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }

    /* JADX WARN: Incorrect return type in method signature: (LX/Ww;LX/Wn;Ljava/lang/Object;Ljava/lang/Class<*>;)Ljava/lang/Object; */
    private final void A02(AbstractC0232Ww ww, AbstractC0226Wn wn, Object obj, Class cls) throws IOException, q0 {
        EnumC0470q2 A0Z = ww.A0Z();
        while (A0Z == EnumC0470q2.FIELD_NAME) {
            String A0c = ww.A0c();
            ww.A0a();
            AbstractC0073Cr A00 = this._beanProperties.A00(A0c);
            if (A00 == null) {
                HashSet<String> hashSet = this._ignorableProps;
                if (hashSet == null || !hashSet.contains(A0c)) {
                    C0263Yn yn = this._anySetter;
                    if (yn != null) {
                        yn.A01(ww, wn, obj, A0c);
                    } else {
                        A0N(ww, wn, obj, A0c);
                    }
                    A0Z = ww.A0a();
                }
            } else if (A00.A0B(cls)) {
                try {
                    A00.A08(ww, wn, obj);
                    A0Z = ww.A0a();
                } catch (Exception e) {
                    A0g(e, obj, A0c, wn);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            }
            ww.A0Y();
            A0Z = ww.A0a();
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A09(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        EnumC0470q2 A0Z = ww.A0Z();
        if (A0Z == EnumC0470q2.START_OBJECT) {
            if (this._vanillaProcessing) {
                ww.A0a();
                return A00(ww, wn);
            }
            ww.A0a();
        } else if (A0Z == null) {
            throw C0223Wj.A00(wn.A00, AnonymousClass06.A04("Unexpected end-of-input when trying to deserialize a ", this._beanType._class.getName()));
        } else {
            switch (C0307ah.A00[A0Z.ordinal()]) {
                case 1:
                    return A0W(ww, wn);
                case 2:
                    return A0V(ww, wn);
                case 3:
                    return A0U(ww, wn);
                case 4:
                    return ww.A0R();
                case 5:
                case 6:
                    return A0T(ww, wn);
                case 7:
                    return A0S(ww, wn);
                case 8:
                case 9:
                    if (this._vanillaProcessing) {
                        return A00(ww, wn);
                    }
                    break;
                default:
                    throw wn.A08(this._beanType._class);
            }
        }
        if (this._objectIdReader != null) {
            return A0X(ww, wn);
        }
        return A0Z(ww, wn);
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase, com.fasterxml.jackson.databind.JsonDeserializer
    public JsonDeserializer<Object> A0B(KI ki) {
        if (getClass() != BeanDeserializer.class) {
            return this;
        }
        return new BeanDeserializer(this, ki);
    }

    public BeanDeserializer(BeanDeserializerBase beanDeserializerBase) {
        super(beanDeserializerBase, beanDeserializerBase._ignoreAllUnknown);
    }

    public BeanDeserializer(BeanDeserializerBase beanDeserializerBase, C0204Vm vm) {
        super(beanDeserializerBase, vm);
    }

    public BeanDeserializer(BeanDeserializerBase beanDeserializerBase, KI ki) {
        super(beanDeserializerBase, ki);
    }

    public BeanDeserializer(BeanDeserializerBase beanDeserializerBase, HashSet<String> hashSet) {
        super(beanDeserializerBase, hashSet);
    }

    public BeanDeserializer(C0295aT aTVar, jm jmVar, C0245Xs xs, Map<String, AbstractC0073Cr> map, HashSet<String> hashSet, boolean z, boolean z2) {
        super(aTVar, jmVar, xs, map, hashSet, z, z2);
    }
}
