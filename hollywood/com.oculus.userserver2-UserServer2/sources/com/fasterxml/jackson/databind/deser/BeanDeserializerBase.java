package com.fasterxml.jackson.databind.deser;

import X.A3;
import X.AQ;
import X.AbstractC00010j;
import X.AbstractC0122Rd;
import X.AnonymousClass0E;
import X.AnonymousClass0J;
import X.AnonymousClass0K;
import X.AnonymousClass0Y;
import X.AnonymousClass1T;
import X.AnonymousClass1V;
import X.AnonymousClass1Y;
import X.AnonymousClass7m;
import X.AnonymousClass9p;
import X.AnonymousClass9r;
import X.B3;
import X.C00000b;
import X.C00020k;
import X.C00071a;
import X.CR;
import X.RW;
import X.RZ;
import X.Rn;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.impl.BeanAsArrayBuilderDeserializer;
import com.fasterxml.jackson.databind.deser.impl.BeanAsArrayDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.deser.std.ThrowableDeserializer;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Map;

public abstract class BeanDeserializerBase extends StdDeserializer<Object> implements AnonymousClass1V, AbstractC00010j, Serializable {
    public static final long serialVersionUID = -2038793552422727904L;
    public final C00000b _anySetter;
    public final Map<String, AQ> _backRefs;
    public final AnonymousClass0K _beanProperties;
    public final RZ _beanType;
    public final JsonDeserializer<Object> _delegateDeserializer;
    public final AnonymousClass0J _externalTypeIdHandler;
    public final HashSet<String> _ignorableProps;
    public final boolean _ignoreAllUnknown;
    public final AnonymousClass7m[] _injectables;
    public final boolean _needViewProcesing;
    public final boolean _nonStandardCreation;
    public final C00020k _objectIdReader;
    public final AnonymousClass1T _propertyBasedCreator;
    public final CR _serializationShape;
    public final C00071a _unwrappedPropertyHandler;
    public final AnonymousClass0Y _valueInstantiator;
    public final boolean _vanillaProcessing;

    private final void A0D(Rn rn, AbstractC0122Rd rd) throws IOException, AnonymousClass9r {
        StringBuilder sb;
        String str;
        JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
        if (jsonDeserializer != null) {
            jsonDeserializer.A03(rn, rd);
            throw null;
        } else if (this._propertyBasedCreator != null) {
            A09(rn, rd);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        } else {
            if (this._beanType.A00()) {
                sb = new StringBuilder("Can not instantiate abstract type ");
                sb.append(this._beanType);
                str = " (need to add/enable type information?)";
            } else {
                sb = new StringBuilder("No suitable constructor found for type ");
                sb.append(this._beanType);
                str = ": can not instantiate from JSON object (need to add/enable type information?)";
            }
            sb.append(str);
            throw RW.A00(rn, sb.toString());
        }
    }

    private final void A0F(Throwable th, Object obj, String str, AbstractC0122Rd rd) throws IOException {
        while ((th instanceof InvocationTargetException) && th.getCause() != null) {
            th = th.getCause();
        }
        if (!(th instanceof Error)) {
            if (rd != null) {
                throw null;
            } else if (!(th instanceof IOException) || (th instanceof AnonymousClass9r)) {
                throw RW.A01(th, obj, str);
            }
        }
        throw th;
    }

    @Override // com.fasterxml.jackson.databind.deser.std.StdDeserializer
    public final void A08(Rn rn, AbstractC0122Rd rd, Object obj, String str) throws IOException, AnonymousClass9r {
        HashSet<String> hashSet;
        if (this._ignoreAllUnknown || ((hashSet = this._ignorableProps) != null && hashSet.contains(str))) {
            rn.A03();
        } else {
            super.A08(rn, rd, obj, str);
        }
    }

    public final Object A09(Rn rn, AbstractC0122Rd rd) throws IOException, AnonymousClass9r {
        AQ aq;
        AQ aq2;
        if (this instanceof BeanAsArrayDeserializer) {
            BeanAsArrayDeserializer beanAsArrayDeserializer = (BeanAsArrayDeserializer) this;
            AnonymousClass1T r7 = beanAsArrayDeserializer._propertyBasedCreator;
            AnonymousClass1Y r6 = new AnonymousClass1Y(rn, rd, r7.A00, beanAsArrayDeserializer._objectIdReader);
            AQ[] aqArr = beanAsArrayDeserializer._orderedProperties;
            int length = aqArr.length;
            int i = 0;
            while (rn.A04() != AnonymousClass9p.END_ARRAY) {
                if (i >= length || (aq = aqArr[i]) == null) {
                    rn.A03();
                } else {
                    String str = aq._propName;
                    AQ aq3 = r7.A01.get(str);
                    if (aq3 != null) {
                        if (r6.A02(aq3.A00(rn, rd))) {
                            try {
                                r7.A00(r6);
                                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                            } catch (Exception e) {
                                beanAsArrayDeserializer.A0F(e, beanAsArrayDeserializer._beanType._class, str, rd);
                                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                            }
                        }
                    } else if (!r6.A03(str)) {
                        r6.A01(aq, aq.A00(rn, rd));
                    }
                }
                i++;
            }
            try {
                r7.A00(r6);
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            } catch (Exception e2) {
                beanAsArrayDeserializer.A0E(e2, rd);
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
        } else if (this instanceof BeanAsArrayBuilderDeserializer) {
            BeanAsArrayBuilderDeserializer beanAsArrayBuilderDeserializer = (BeanAsArrayBuilderDeserializer) this;
            AnonymousClass1T r72 = beanAsArrayBuilderDeserializer._propertyBasedCreator;
            AnonymousClass1Y r62 = new AnonymousClass1Y(rn, rd, r72.A00, beanAsArrayBuilderDeserializer._objectIdReader);
            AQ[] aqArr2 = beanAsArrayBuilderDeserializer._orderedProperties;
            int length2 = aqArr2.length;
            int i2 = 0;
            while (rn.A04() != AnonymousClass9p.END_ARRAY) {
                if (i2 >= length2 || (aq2 = aqArr2[i2]) == null) {
                    rn.A03();
                } else {
                    String str2 = aq2._propName;
                    AQ aq4 = r72.A01.get(str2);
                    if (aq4 != null) {
                        if (r62.A02(aq4.A00(rn, rd))) {
                            try {
                                r72.A00(r62);
                                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                            } catch (Exception e3) {
                                beanAsArrayBuilderDeserializer.A0F(e3, beanAsArrayBuilderDeserializer._beanType._class, str2, rd);
                                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                            }
                        }
                    } else if (!r62.A03(str2)) {
                        r62.A01(aq2, aq2.A00(rn, rd));
                    }
                }
                i2++;
            }
            try {
                r72.A00(r62);
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            } catch (Exception e4) {
                beanAsArrayBuilderDeserializer.A0E(e4, rd);
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
        } else if (!(this instanceof BuilderBasedDeserializer)) {
            AnonymousClass1T r63 = this._propertyBasedCreator;
            AnonymousClass1Y r5 = new AnonymousClass1Y(rn, rd, r63.A00, this._objectIdReader);
            AnonymousClass9p r1 = ((B3) rn).A00;
            A3 a3 = null;
            while (r1 == AnonymousClass9p.FIELD_NAME) {
                String A08 = rn.A08();
                rn.A04();
                AQ aq5 = r63.A01.get(A08);
                if (aq5 != null) {
                    if (r5.A02(aq5.A00(rn, rd))) {
                        rn.A04();
                        r63.A00(r5);
                        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                    }
                } else if (!r5.A03(A08)) {
                    AQ A00 = this._beanProperties.A00(A08);
                    if (A00 != null) {
                        r5.A01(A00, A00.A00(rn, rd));
                    } else {
                        HashSet<String> hashSet = this._ignorableProps;
                        if (hashSet == null || !hashSet.contains(A08)) {
                            C00000b r12 = this._anySetter;
                            if (r12 != null) {
                                r5.A00(r12, A08, r12.A00(rn, rd));
                            } else {
                                if (a3 == null) {
                                    a3 = new A3(null);
                                }
                                a3.A08(A08);
                                a3.A07(rn);
                            }
                        } else {
                            rn.A03();
                        }
                    }
                }
                r1 = rn.A04();
            }
            r63.A00(r5);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        } else {
            AnonymousClass1T r64 = this._propertyBasedCreator;
            AnonymousClass1Y r52 = new AnonymousClass1Y(rn, rd, r64.A00, this._objectIdReader);
            AnonymousClass9p r13 = ((B3) rn).A00;
            A3 a32 = null;
            while (r13 == AnonymousClass9p.FIELD_NAME) {
                String A082 = rn.A08();
                rn.A04();
                AQ aq6 = r64.A01.get(A082);
                if (aq6 != null) {
                    if (r52.A02(aq6.A00(rn, rd))) {
                        rn.A04();
                        try {
                            r64.A00(r52);
                            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                        } catch (Exception e5) {
                            A0F(e5, this._beanType._class, A082, rd);
                            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                        }
                    }
                } else if (!r52.A03(A082)) {
                    AQ A002 = this._beanProperties.A00(A082);
                    if (A002 != null) {
                        r52.A01(A002, A002.A00(rn, rd));
                    } else {
                        HashSet<String> hashSet2 = this._ignorableProps;
                        if (hashSet2 == null || !hashSet2.contains(A082)) {
                            C00000b r14 = this._anySetter;
                            if (r14 != null) {
                                r52.A00(r14, A082, r14.A00(rn, rd));
                            } else {
                                if (a32 == null) {
                                    a32 = new A3(null);
                                }
                                a32.A08(A082);
                                a32.A07(rn);
                            }
                        } else {
                            rn.A03();
                        }
                    }
                }
                r13 = rn.A04();
            }
            try {
                r64.A00(r52);
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            } catch (Exception e6) {
                A0E(e6, rd);
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
        }
    }

    public final Object A0A(Rn rn, AbstractC0122Rd rd) throws IOException, AnonymousClass9r {
        JsonDeserializer<Object> jsonDeserializer;
        if (this instanceof BeanAsArrayDeserializer) {
            throw null;
        } else if (!(this instanceof BeanAsArrayBuilderDeserializer)) {
            if (!(this instanceof BuilderBasedDeserializer)) {
                BeanDeserializer beanDeserializer = (BeanDeserializer) this;
                if (!(beanDeserializer instanceof ThrowableDeserializer)) {
                    if (!beanDeserializer._nonStandardCreation) {
                        throw null;
                    } else if (beanDeserializer._unwrappedPropertyHandler != null) {
                        jsonDeserializer = beanDeserializer._delegateDeserializer;
                        if (jsonDeserializer == null) {
                            AnonymousClass1T r6 = beanDeserializer._propertyBasedCreator;
                            if (r6 != null) {
                                AnonymousClass1Y r5 = new AnonymousClass1Y(rn, rd, r6.A00, beanDeserializer._objectIdReader);
                                A3 a3 = new A3(null);
                                a3.A06();
                                AnonymousClass9p r1 = ((B3) rn).A00;
                                while (r1 == AnonymousClass9p.FIELD_NAME) {
                                    String A08 = rn.A08();
                                    rn.A04();
                                    AQ aq = r6.A01.get(A08);
                                    if (aq != null) {
                                        if (r5.A02(aq.A00(rn, rd))) {
                                            rn.A04();
                                            r6.A00(r5);
                                            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                                        }
                                    } else if (!r5.A03(A08)) {
                                        AQ A00 = beanDeserializer._beanProperties.A00(A08);
                                        if (A00 != null) {
                                            r5.A01(A00, A00.A00(rn, rd));
                                        } else {
                                            HashSet<String> hashSet = beanDeserializer._ignorableProps;
                                            if (hashSet == null || !hashSet.contains(A08)) {
                                                a3.A08(A08);
                                                a3.A07(rn);
                                                C00000b r12 = beanDeserializer._anySetter;
                                                if (r12 != null) {
                                                    r5.A00(r12, A08, r12.A00(rn, rd));
                                                }
                                            } else {
                                                rn.A03();
                                            }
                                        }
                                    }
                                    r1 = rn.A04();
                                }
                                r6.A00(r5);
                                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                            }
                            new A3(null).A06();
                            throw null;
                        }
                    } else {
                        AnonymousClass0J r0 = beanDeserializer._externalTypeIdHandler;
                        if (r0 != null) {
                            AnonymousClass1T r8 = beanDeserializer._propertyBasedCreator;
                            if (r8 != null) {
                                AnonymousClass0J r10 = new AnonymousClass0J(r0);
                                AnonymousClass1Y r7 = new AnonymousClass1Y(rn, rd, r8.A00, beanDeserializer._objectIdReader);
                                new A3(null).A06();
                                AnonymousClass9p r13 = ((B3) rn).A00;
                                while (r13 == AnonymousClass9p.FIELD_NAME) {
                                    String A082 = rn.A08();
                                    rn.A04();
                                    AQ aq2 = r8.A01.get(A082);
                                    if (aq2 != null) {
                                        if (!r10.A00(rn, rd, A082, r7) && r7.A02(aq2.A00(rn, rd))) {
                                            rn.A04();
                                            try {
                                                r8.A00(r7);
                                                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                                            } catch (Exception e) {
                                                beanDeserializer.A0F(e, beanDeserializer._beanType._class, A082, rd);
                                            }
                                        }
                                    } else if (!r7.A03(A082)) {
                                        AQ A002 = beanDeserializer._beanProperties.A00(A082);
                                        if (A002 != null) {
                                            r7.A01(A002, A002.A00(rn, rd));
                                        } else if (!r10.A00(rn, rd, A082, null)) {
                                            HashSet<String> hashSet2 = beanDeserializer._ignorableProps;
                                            if (hashSet2 == null || !hashSet2.contains(A082)) {
                                                C00000b r14 = beanDeserializer._anySetter;
                                                if (r14 != null) {
                                                    r7.A00(r14, A082, r14.A00(rn, rd));
                                                }
                                            } else {
                                                rn.A03();
                                            }
                                        }
                                    }
                                    r13 = rn.A04();
                                }
                                try {
                                    AnonymousClass0E[] r62 = r10.A01;
                                    int length = r62.length;
                                    Object[] objArr = new Object[length];
                                    for (int i = 0; i < length; i++) {
                                        String str = r10.A03[i];
                                        if (str != null) {
                                            A3[] a3Arr = r10.A02;
                                            if (a3Arr[i] != null) {
                                                A3 a32 = new A3(null);
                                                a32.A05();
                                                A3.A02(a32, AnonymousClass9p.VALUE_STRING, str);
                                                Rn A03 = a3Arr[i].A03(rn);
                                                A03.A04();
                                                a32.A07(A03);
                                                a32.A04();
                                                Rn A032 = a32.A03(rn);
                                                A032.A04();
                                                objArr[i] = r62[i].A00.A00(A032, rd);
                                            } else {
                                                throw null;
                                            }
                                        } else if (r10.A02[i] != null) {
                                            throw null;
                                        }
                                    }
                                    for (int i2 = 0; i2 < length; i2++) {
                                        if (r8.A01.get(r62[i2].A00._propName) != null) {
                                            r7.A02(objArr[i2]);
                                        }
                                    }
                                    r8.A00(r7);
                                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                                } catch (Exception e2) {
                                    beanDeserializer.A0E(e2, rd);
                                }
                            } else {
                                throw null;
                            }
                        } else {
                            beanDeserializer.A0D(rn, rd);
                            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                        }
                    }
                } else if (beanDeserializer._propertyBasedCreator != null) {
                    beanDeserializer.A09(rn, rd);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                } else {
                    jsonDeserializer = beanDeserializer._delegateDeserializer;
                    if (jsonDeserializer == null) {
                        if (beanDeserializer._beanType.A00()) {
                            StringBuilder sb = new StringBuilder("Can not instantiate abstract type ");
                            sb.append(beanDeserializer._beanType);
                            sb.append(" (need to add/enable type information?)");
                            throw RW.A00(rn, sb.toString());
                        }
                        throw null;
                    }
                }
            } else if (!this._nonStandardCreation) {
                throw null;
            } else if (this._unwrappedPropertyHandler != null) {
                jsonDeserializer = this._delegateDeserializer;
                if (jsonDeserializer == null) {
                    AnonymousClass1T r52 = this._propertyBasedCreator;
                    if (r52 != null) {
                        AnonymousClass1Y r4 = new AnonymousClass1Y(rn, rd, r52.A00, this._objectIdReader);
                        A3 a33 = new A3(null);
                        a33.A06();
                        AnonymousClass9p r15 = ((B3) rn).A00;
                        while (r15 == AnonymousClass9p.FIELD_NAME) {
                            String A083 = rn.A08();
                            rn.A04();
                            AQ aq3 = r52.A01.get(A083);
                            if (aq3 != null) {
                                if (r4.A02(aq3.A00(rn, rd))) {
                                    rn.A04();
                                    try {
                                        r52.A00(r4);
                                        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                                    } catch (Exception e3) {
                                        A0F(e3, this._beanType._class, A083, rd);
                                    }
                                }
                            } else if (!r4.A03(A083)) {
                                AQ A003 = this._beanProperties.A00(A083);
                                if (A003 != null) {
                                    r4.A01(A003, A003.A00(rn, rd));
                                } else {
                                    HashSet<String> hashSet3 = this._ignorableProps;
                                    if (hashSet3 == null || !hashSet3.contains(A083)) {
                                        a33.A08(A083);
                                        a33.A07(rn);
                                        C00000b r16 = this._anySetter;
                                        if (r16 != null) {
                                            r4.A00(r16, A083, r16.A00(rn, rd));
                                        }
                                    } else {
                                        rn.A03();
                                    }
                                }
                            }
                            r15 = rn.A04();
                        }
                        try {
                            r52.A00(r4);
                            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                        } catch (Exception e4) {
                            A0E(e4, rd);
                        }
                    }
                    new A3(null).A06();
                    throw null;
                }
            } else if (this._externalTypeIdHandler == null) {
                A0D(rn, rd);
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            } else if (this._propertyBasedCreator != null) {
                throw new IllegalStateException("Deserialization with Builder, External type id, @JsonCreator not yet implemented");
            } else {
                throw null;
            }
            jsonDeserializer.A03(rn, rd);
            throw null;
        } else {
            throw null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0018, code lost:
        if (r0 != null) goto L_0x0006;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A0C(X.Rn r3, X.AbstractC0122Rd r4) throws java.io.IOException, X.AnonymousClass9r {
        /*
            r2 = this;
            X.0k r0 = r2._objectIdReader
            if (r0 == 0) goto L_0x000b
            com.fasterxml.jackson.databind.JsonDeserializer<java.lang.Object> r0 = r0.deserializer
        L_0x0006:
            r0.A03(r3, r4)
        L_0x0009:
            r0 = 0
            throw r0
        L_0x000b:
            java.lang.Integer r0 = r3.A05()
            int r1 = r0.intValue()
            com.fasterxml.jackson.databind.JsonDeserializer<java.lang.Object> r0 = r2._delegateDeserializer
            switch(r1) {
                case 0: goto L_0x0025;
                case 1: goto L_0x001b;
                default: goto L_0x0018;
            }
        L_0x0018:
            if (r0 == 0) goto L_0x0009
            goto L_0x0006
        L_0x001b:
            if (r0 != 0) goto L_0x0006
            java.lang.Number r0 = r3.A06()
            r0.longValue()
            goto L_0x0009
        L_0x0025:
            if (r0 != 0) goto L_0x0006
            r3.A00()
            goto L_0x0009
            switch-data {0->0x0025, 1->0x001b, }
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BeanDeserializerBase.A0C(X.Rn, X.Rd):void");
    }

    public final void A0E(Throwable th, AbstractC0122Rd rd) throws IOException {
        while ((th instanceof InvocationTargetException) && th.getCause() != null) {
            th = th.getCause();
        }
        if ((th instanceof Error) || (rd == null && (th instanceof IOException))) {
            throw th;
        }
        throw null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x000d, code lost:
        if (r0 != null) goto L_0x000f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A0B(X.Rn r2, X.AbstractC0122Rd r3) throws java.io.IOException, X.AnonymousClass9r {
        /*
            r1 = this;
            java.lang.Integer r0 = r2.A05()
            int r0 = r0.intValue()
            switch(r0) {
                case 3: goto L_0x0014;
                case 4: goto L_0x0014;
                default: goto L_0x000b;
            }
        L_0x000b:
            com.fasterxml.jackson.databind.JsonDeserializer<java.lang.Object> r0 = r1._delegateDeserializer
            if (r0 == 0) goto L_0x0012
        L_0x000f:
            r0.A03(r2, r3)
        L_0x0012:
            r0 = 0
            throw r0
        L_0x0014:
            com.fasterxml.jackson.databind.JsonDeserializer<java.lang.Object> r0 = r1._delegateDeserializer
            if (r0 != 0) goto L_0x000f
            java.lang.Number r0 = r2.A06()
            r0.doubleValue()
            goto L_0x0012
            switch-data {3->0x0014, 4->0x0014, }
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BeanDeserializerBase.A0B(X.Rn, X.Rd):void");
    }
}
