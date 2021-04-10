package com.fasterxml.jackson.databind.deser.impl;

import X.AbstractC04020gg;
import X.AbstractC04100gp;
import X.AbstractC06410n2;
import X.AnonymousClass006;
import X.AnonymousClass07O;
import X.AnonymousClass0HD;
import X.AnonymousClass0jg;
import X.C03990gZ;
import X.C05530lQ;
import X.C05540lR;
import X.C05560lT;
import X.EnumC04820ji;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.BeanDeserializerBase;
import java.io.IOException;
import java.util.HashSet;

public final class BeanAsArrayBuilderDeserializer extends BeanDeserializerBase {
    public static final long serialVersionUID = 1;
    public final AnonymousClass07O _buildMethod;
    public final BeanDeserializerBase _delegate;
    public final AnonymousClass0HD[] _orderedProperties;

    public BeanAsArrayBuilderDeserializer(BeanDeserializerBase beanDeserializerBase, AnonymousClass0HD[] r3, AnonymousClass07O r4) {
        super(beanDeserializerBase, beanDeserializerBase._ignoreAllUnknown);
        this._delegate = beanDeserializerBase;
        this._orderedProperties = r3;
        this._buildMethod = r4;
    }

    private final Object A00(AbstractC04020gg r3, Object obj) throws IOException {
        try {
            return this._buildMethod.A00.invoke(obj, new Object[0]);
        } catch (Exception e) {
            A0f(e, r3);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }

    private final void A01(AbstractC04100gp r3) throws IOException, AnonymousClass0jg {
        StringBuilder sb = new StringBuilder("Can not deserialize a POJO (of type ");
        sb.append(this._beanType._class.getName());
        sb.append(") from non-Array representation (token: ");
        sb.append(r3.A0a());
        sb.append("): type/property designed to be serialized as JSON Array");
        throw C03990gZ.A00(null, sb.toString());
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0A(AbstractC04100gp r6, AbstractC04020gg r7, Object obj) throws IOException, AnonymousClass0jg {
        if (this._injectables != null) {
            A0d(r7);
        }
        AnonymousClass0HD[] r4 = this._orderedProperties;
        int i = 0;
        int length = r4.length;
        while (true) {
            EnumC04820ji A0b = r6.A0b();
            EnumC04820ji r1 = EnumC04820ji.END_ARRAY;
            if (A0b == r1) {
                break;
            } else if (i != length) {
                AnonymousClass0HD r0 = r4[i];
                if (r0 != null) {
                    try {
                        obj = r0.A06(r6, r7, obj);
                    } catch (Exception e) {
                        A0g(e, obj, r0._propName, r7);
                        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                    }
                } else {
                    r6.A0Z();
                }
                i++;
            } else if (!this._ignoreAllUnknown) {
                throw C03990gZ.A00(null, AnonymousClass006.A02("Unexpected JSON values; expected at most ", length, " properties (in JSON Array)"));
            } else {
                while (r6.A0b() != r1) {
                    r6.A0Z();
                }
            }
        }
        return A00(r7, obj);
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase, com.fasterxml.jackson.databind.JsonDeserializer
    public final JsonDeserializer<Object> A0B(AbstractC06410n2 r2) {
        return this._delegate.A0B(r2);
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase
    public final BeanDeserializerBase A0Q(C05530lQ r5) {
        return new BeanAsArrayBuilderDeserializer(this._delegate.A0Q(r5), this._orderedProperties, this._buildMethod);
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase
    public final BeanDeserializerBase A0R(HashSet hashSet) {
        return new BeanAsArrayBuilderDeserializer(this._delegate.A0R(hashSet), this._orderedProperties, this._buildMethod);
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase
    public final Object A0a(AbstractC04100gp r11, AbstractC04020gg r12) throws IOException, AnonymousClass0jg {
        AnonymousClass0HD r9;
        C05540lR r8 = this._propertyBasedCreator;
        C05560lT A01 = r8.A01(r11, r12, this._objectIdReader);
        AnonymousClass0HD[] r6 = this._orderedProperties;
        int length = r6.length;
        Object obj = null;
        int i = 0;
        while (r11.A0b() != EnumC04820ji.END_ARRAY) {
            if (i < length) {
                r9 = r6[i];
            } else {
                r9 = null;
            }
            if (r9 == null) {
                r11.A0Z();
            } else if (obj != null) {
                try {
                    obj = r9.A06(r11, r12, obj);
                } catch (Exception e) {
                    A0g(e, obj, r9._propName, r12);
                }
            } else {
                String str = r9._propName;
                AnonymousClass0HD r0 = r8.A00.get(str);
                if (r0 != null) {
                    if (A01.A02(r0.A01(), r0.A05(r11, r12))) {
                        try {
                            obj = r8.A02(r12, A01);
                            Class<?> cls = obj.getClass();
                            Class<?> cls2 = this._beanType._class;
                            if (cls != cls2) {
                                throw C03990gZ.A00(null, AnonymousClass006.A08("Can not support implicit polymorphic deserialization for POJOs-as-Arrays style: nominal type ", cls2.getName(), ", actual type ", cls.getName()));
                            }
                        } catch (Exception e2) {
                            A0g(e2, this._beanType._class, str, r12);
                            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                        }
                    } else {
                        continue;
                    }
                } else if (!A01.A03(str)) {
                    A01.A01(r9, r9.A05(r11, r12));
                }
            }
            i++;
        }
        if (obj != null) {
            return obj;
        }
        try {
            return r8.A02(r12, A01);
        } catch (Exception e3) {
            A0f(e3, r12);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A09(AbstractC04100gp r9, AbstractC04020gg r10) throws IOException, AnonymousClass0jg {
        String str;
        Object A05;
        Class<?> cls;
        StringBuilder sb;
        String str2;
        if (r9.A0a() != EnumC04820ji.START_ARRAY) {
            A01(r9);
        } else {
            if (!this._vanillaProcessing) {
                if (this._nonStandardCreation) {
                    JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
                    if (jsonDeserializer != null) {
                        A05 = this._valueInstantiator.A09(r10, jsonDeserializer.A09(r9, r10));
                    } else if (this._propertyBasedCreator != null) {
                        A05 = A0a(r9, r10);
                    } else {
                        if (this._beanType.A0E()) {
                            sb = new StringBuilder("Can not instantiate abstract type ");
                            sb.append(this._beanType);
                            str2 = " (need to add/enable type information?)";
                        } else {
                            sb = new StringBuilder("No suitable constructor found for type ");
                            sb.append(this._beanType);
                            str2 = ": can not instantiate from JSON object (need to add/enable type information?)";
                        }
                        sb.append(str2);
                        throw C03990gZ.A00(r9, sb.toString());
                    }
                } else {
                    A05 = this._valueInstantiator.A05(r10);
                    if (this._injectables != null) {
                        A0d(r10);
                    }
                    if (this._needViewProcesing) {
                        cls = r10._view;
                    } else {
                        cls = null;
                    }
                    AnonymousClass0HD[] r5 = this._orderedProperties;
                    int i = 0;
                    int length = r5.length;
                    while (true) {
                        EnumC04820ji A0b = r9.A0b();
                        EnumC04820ji r1 = EnumC04820ji.END_ARRAY;
                        if (A0b == r1) {
                            break;
                        } else if (i != length) {
                            AnonymousClass0HD r2 = r5[i];
                            i++;
                            if (r2 == null || (cls != null && !r2.A0B(cls))) {
                                r9.A0Z();
                            } else {
                                try {
                                    r2.A06(r9, r10, A05);
                                } catch (Exception e) {
                                    A0g(e, A05, r2._propName, r10);
                                }
                            }
                        } else if (!this._ignoreAllUnknown) {
                            str = AnonymousClass006.A02("Unexpected JSON values; expected at most ", length, " properties (in JSON Array)");
                        } else {
                            while (r9.A0b() != r1) {
                                r9.A0Z();
                            }
                        }
                    }
                }
                return A00(r10, A05);
            }
            Object A052 = this._valueInstantiator.A05(r10);
            AnonymousClass0HD[] r4 = this._orderedProperties;
            int i2 = 0;
            int length2 = r4.length;
            while (true) {
                EnumC04820ji A0b2 = r9.A0b();
                EnumC04820ji r12 = EnumC04820ji.END_ARRAY;
                if (A0b2 == r12) {
                    break;
                } else if (i2 != length2) {
                    AnonymousClass0HD r0 = r4[i2];
                    if (r0 != null) {
                        try {
                            A052 = r0.A06(r9, r10, A052);
                        } catch (Exception e2) {
                            A0g(e2, A052, r0._propName, r10);
                        }
                    } else {
                        r9.A0Z();
                    }
                    i2++;
                } else if (!this._ignoreAllUnknown) {
                    str = AnonymousClass006.A02("Unexpected JSON values; expected at most ", length2, " properties (in JSON Array)");
                } else {
                    while (r9.A0b() != r12) {
                        r9.A0Z();
                    }
                }
            }
            return A00(r10, A052);
            throw C03990gZ.A00(null, str);
        }
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase
    public final BeanDeserializerBase A0P() {
        return this;
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase
    public final Object A0Z(AbstractC04100gp r3, AbstractC04020gg r4) throws IOException, AnonymousClass0jg {
        A01(r3);
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }
}
