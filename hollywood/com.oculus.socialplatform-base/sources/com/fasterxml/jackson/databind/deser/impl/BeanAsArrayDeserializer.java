package com.fasterxml.jackson.databind.deser.impl;

import X.AbstractC01170Rz;
import X.AbstractC02210iH;
import X.AbstractC02280iQ;
import X.AbstractC04870rR;
import X.AnonymousClass006;
import X.C02180iD;
import X.C03620oC;
import X.C04390pu;
import X.C04400pv;
import X.C04420px;
import X.EnumC03640oE;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.BeanDeserializerBase;
import java.io.IOException;
import java.util.HashSet;

public class BeanAsArrayDeserializer extends BeanDeserializerBase {
    public static final long serialVersionUID = 1;
    public final BeanDeserializerBase _delegate;
    public final AbstractC01170Rz[] _orderedProperties;

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase
    public final BeanDeserializerBase A0P() {
        return this;
    }

    public BeanAsArrayDeserializer(BeanDeserializerBase beanDeserializerBase, AbstractC01170Rz[] r3) {
        super(beanDeserializerBase, beanDeserializerBase._ignoreAllUnknown);
        this._delegate = beanDeserializerBase;
        this._orderedProperties = r3;
    }

    private final void A00(AbstractC02280iQ r3, AbstractC02210iH r4) throws IOException, C03620oC {
        StringBuilder sb = new StringBuilder("Can not deserialize a POJO (of type ");
        sb.append(this._beanType._class.getName());
        sb.append(") from non-Array representation (token: ");
        sb.append(r3.A0i());
        sb.append("): type/property designed to be serialized as JSON Array");
        throw C02180iD.A00(r4.A00, sb.toString());
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase, com.fasterxml.jackson.databind.JsonDeserializer
    public final JsonDeserializer<Object> A09(AbstractC04870rR r2) {
        return this._delegate.A09(r2);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0C(AbstractC02280iQ r6, AbstractC02210iH r7, Object obj) throws IOException, C03620oC {
        if (this._injectables != null) {
            A0d(r7);
        }
        AbstractC01170Rz[] r4 = this._orderedProperties;
        int i = 0;
        int length = r4.length;
        while (true) {
            EnumC03640oE A0j = r6.A0j();
            EnumC03640oE r1 = EnumC03640oE.END_ARRAY;
            if (A0j == r1) {
                break;
            } else if (i != length) {
                AbstractC01170Rz r0 = r4[i];
                if (r0 != null) {
                    try {
                        r0.A08(r6, r7, obj);
                    } catch (Exception e) {
                        A0g(e, obj, r0._propName, r7);
                        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                    }
                } else {
                    r6.A0h();
                }
                i++;
            } else if (!this._ignoreAllUnknown) {
                throw C02180iD.A00(r7.A00, AnonymousClass006.A04("Unexpected JSON values; expected at most ", length, " properties (in JSON Array)"));
            } else {
                while (r6.A0j() != r1) {
                    r6.A0h();
                }
            }
        }
        return obj;
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase
    public final BeanDeserializerBase A0Q(C04390pu r4) {
        return new BeanAsArrayDeserializer(this._delegate.A0Q(r4), this._orderedProperties);
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase
    public final BeanDeserializerBase A0R(HashSet hashSet) {
        return new BeanAsArrayDeserializer(this._delegate.A0R(hashSet), this._orderedProperties);
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase
    public final Object A0a(AbstractC02280iQ r11, AbstractC02210iH r12) throws IOException, C03620oC {
        AbstractC01170Rz r4;
        C04400pv r7 = this._propertyBasedCreator;
        C04420px A01 = r7.A01(r11, r12, this._objectIdReader);
        AbstractC01170Rz[] r5 = this._orderedProperties;
        int length = r5.length;
        Object obj = null;
        int i = 0;
        while (r11.A0j() != EnumC03640oE.END_ARRAY) {
            if (i < length) {
                r4 = r5[i];
            } else {
                r4 = null;
            }
            if (r4 == null) {
                r11.A0h();
            } else if (obj != null) {
                try {
                    r4.A08(r11, r12, obj);
                } catch (Exception e) {
                    A0g(e, obj, r4._propName, r12);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            } else {
                String str = r4._propName;
                AbstractC01170Rz r0 = r7.A00.get(str);
                if (r0 != null) {
                    if (A01.A02(r0.A01(), r0.A05(r11, r12))) {
                        try {
                            obj = r7.A02(r12, A01);
                            Class<?> cls = obj.getClass();
                            Class<?> cls2 = this._beanType._class;
                            if (cls != cls2) {
                                throw C02180iD.A00(r12.A00, AnonymousClass006.A0B("Can not support implicit polymorphic deserialization for POJOs-as-Arrays style: nominal type ", cls2.getName(), ", actual type ", cls.getName()));
                            }
                        } catch (Exception e2) {
                            A0g(e2, this._beanType._class, str, r12);
                            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                        }
                    } else {
                        continue;
                    }
                } else if (!A01.A03(str)) {
                    A01.A01(r4, r4.A05(r11, r12));
                }
            }
            i++;
        }
        if (obj != null) {
            return obj;
        }
        try {
            return r7.A02(r12, A01);
        } catch (Exception e3) {
            A0f(e3, r12);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0A(AbstractC02280iQ r9, AbstractC02210iH r10) throws IOException, C03620oC {
        Class<?> cls;
        if (r9.A0i() != EnumC03640oE.START_ARRAY) {
            A00(r9, r10);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        } else if (this._vanillaProcessing) {
            Object A05 = this._valueInstantiator.A05(r10);
            AbstractC01170Rz[] r4 = this._orderedProperties;
            int i = 0;
            int length = r4.length;
            while (true) {
                EnumC03640oE A0j = r9.A0j();
                EnumC03640oE r1 = EnumC03640oE.END_ARRAY;
                if (A0j == r1) {
                    break;
                } else if (i != length) {
                    AbstractC01170Rz r0 = r4[i];
                    if (r0 != null) {
                        try {
                            r0.A08(r9, r10, A05);
                        } catch (Exception e) {
                            A0g(e, A05, r0._propName, r10);
                            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                        }
                    } else {
                        r9.A0h();
                    }
                    i++;
                } else if (!this._ignoreAllUnknown) {
                    throw C02180iD.A00(r10.A00, AnonymousClass006.A04("Unexpected JSON values; expected at most ", length, " properties (in JSON Array)"));
                } else {
                    while (r9.A0j() != r1) {
                        r9.A0h();
                    }
                }
            }
            return A05;
        } else if (this._nonStandardCreation) {
            JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
            if (jsonDeserializer != null) {
                return this._valueInstantiator.A09(r10, jsonDeserializer.A0A(r9, r10));
            }
            if (this._propertyBasedCreator != null) {
                return A0a(r9, r10);
            }
            if (this._beanType.A0I()) {
                StringBuilder sb = new StringBuilder("Can not instantiate abstract type ");
                sb.append(this._beanType);
                sb.append(" (need to add/enable type information?)");
                throw C02180iD.A00(r9, sb.toString());
            }
            StringBuilder sb2 = new StringBuilder("No suitable constructor found for type ");
            sb2.append(this._beanType);
            sb2.append(": can not instantiate from JSON object (need to add/enable type information?)");
            throw C02180iD.A00(r9, sb2.toString());
        } else {
            Object A052 = this._valueInstantiator.A05(r10);
            if (this._injectables != null) {
                A0d(r10);
            }
            if (this._needViewProcesing) {
                cls = r10._view;
            } else {
                cls = null;
            }
            AbstractC01170Rz[] r5 = this._orderedProperties;
            int i2 = 0;
            int length2 = r5.length;
            while (true) {
                EnumC03640oE A0j2 = r9.A0j();
                EnumC03640oE r12 = EnumC03640oE.END_ARRAY;
                if (A0j2 == r12) {
                    return A052;
                }
                if (i2 != length2) {
                    AbstractC01170Rz r2 = r5[i2];
                    i2++;
                    if (r2 == null || (cls != null && !r2.A0B(cls))) {
                        r9.A0h();
                    } else {
                        try {
                            r2.A08(r9, r10, A052);
                        } catch (Exception e2) {
                            A0g(e2, A052, r2._propName, r10);
                            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                        }
                    }
                } else if (!this._ignoreAllUnknown) {
                    throw C02180iD.A00(r10.A00, AnonymousClass006.A04("Unexpected JSON values; expected at most ", length2, " properties (in JSON Array)"));
                } else {
                    while (r9.A0j() != r12) {
                        r9.A0h();
                    }
                    return A052;
                }
            }
        }
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase
    public final Object A0Z(AbstractC02280iQ r3, AbstractC02210iH r4) throws IOException, C03620oC {
        A00(r3, r4);
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }
}
