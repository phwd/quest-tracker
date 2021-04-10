package com.fasterxml.jackson.databind.deser.impl;

import X.AbstractC01680Ku;
import X.AbstractC02570aK;
import X.AbstractC07200ov;
import X.AnonymousClass006;
import X.AnonymousClass0EC;
import X.AnonymousClass0aG;
import X.AnonymousClass0aT;
import X.AnonymousClass0nL;
import X.AnonymousClass0nM;
import X.AnonymousClass0nO;
import X.C05910ld;
import X.EnumC05930lf;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.BeanDeserializerBase;
import java.io.IOException;
import java.util.HashSet;

public final class BeanAsArrayBuilderDeserializer extends BeanDeserializerBase {
    public static final long serialVersionUID = 1;
    public final AnonymousClass0EC _buildMethod;
    public final BeanDeserializerBase _delegate;
    public final AbstractC01680Ku[] _orderedProperties;

    public BeanAsArrayBuilderDeserializer(BeanDeserializerBase beanDeserializerBase, AbstractC01680Ku[] r3, AnonymousClass0EC r4) {
        super(beanDeserializerBase, beanDeserializerBase._ignoreAllUnknown);
        this._delegate = beanDeserializerBase;
        this._orderedProperties = r3;
        this._buildMethod = r4;
    }

    private final Object A00(AbstractC02570aK r3, Object obj) throws IOException {
        try {
            return this._buildMethod.A00.invoke(obj, new Object[0]);
        } catch (Exception e) {
            A0f(e, r3);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }

    private final void A01(AnonymousClass0aT r3, AbstractC02570aK r4) throws IOException, C05910ld {
        throw AnonymousClass0aG.A00(r4.A00, "Can not deserialize a POJO (of type " + this._beanType._class.getName() + ") from non-Array representation (token: " + r3.A0G() + "): type/property designed to be serialized as JSON Array");
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0A(AnonymousClass0aT r6, AbstractC02570aK r7, Object obj) throws IOException, C05910ld {
        if (this._injectables != null) {
            A0d(r7);
        }
        AbstractC01680Ku[] r4 = this._orderedProperties;
        int i = 0;
        int length = r4.length;
        while (true) {
            EnumC05930lf A0a = r6.A0a();
            EnumC05930lf r1 = EnumC05930lf.END_ARRAY;
            if (A0a == r1) {
                break;
            } else if (i != length) {
                AbstractC01680Ku r0 = r4[i];
                if (r0 != null) {
                    try {
                        obj = r0.A06(r6, r7, obj);
                    } catch (Exception e) {
                        A0g(e, obj, r0._propName, r7);
                        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                    }
                } else {
                    r6.A0F();
                }
                i++;
            } else if (!this._ignoreAllUnknown) {
                throw AnonymousClass0aG.A00(r7.A00, AnonymousClass006.A02("Unexpected JSON values; expected at most ", length, " properties (in JSON Array)"));
            } else {
                while (r6.A0a() != r1) {
                    r6.A0F();
                }
            }
        }
        return A00(r7, obj);
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase, com.fasterxml.jackson.databind.JsonDeserializer
    public final JsonDeserializer<Object> A0B(AbstractC07200ov r2) {
        return this._delegate.A0B(r2);
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase
    public final BeanDeserializerBase A0P() {
        return this;
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase
    public final BeanDeserializerBase A0Q(AnonymousClass0nL r5) {
        return new BeanAsArrayBuilderDeserializer(this._delegate.A0Q(r5), this._orderedProperties, this._buildMethod);
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase
    public final BeanDeserializerBase A0R(HashSet hashSet) {
        return new BeanAsArrayBuilderDeserializer(this._delegate.A0R(hashSet), this._orderedProperties, this._buildMethod);
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase
    public final Object A0a(AnonymousClass0aT r11, AbstractC02570aK r12) throws IOException, C05910ld {
        AbstractC01680Ku r4;
        AnonymousClass0nM r7 = this._propertyBasedCreator;
        AnonymousClass0nO A01 = r7.A01(r11, r12, this._objectIdReader);
        AbstractC01680Ku[] r5 = this._orderedProperties;
        int length = r5.length;
        Object obj = null;
        int i = 0;
        while (r11.A0a() != EnumC05930lf.END_ARRAY) {
            if (i < length) {
                r4 = r5[i];
            } else {
                r4 = null;
            }
            if (r4 == null) {
                r11.A0F();
            } else if (obj != null) {
                try {
                    obj = r4.A06(r11, r12, obj);
                } catch (Exception e) {
                    A0g(e, obj, r4._propName, r12);
                }
            } else {
                String str = r4._propName;
                AbstractC01680Ku r0 = r7.A00.get(str);
                if (r0 != null) {
                    if (A01.A02(r0.A01(), r0.A05(r11, r12))) {
                        try {
                            obj = r7.A02(r12, A01);
                            Class<?> cls = obj.getClass();
                            Class<?> cls2 = this._beanType._class;
                            if (cls != cls2) {
                                throw AnonymousClass0aG.A00(r12.A00, AnonymousClass006.A08("Can not support implicit polymorphic deserialization for POJOs-as-Arrays style: nominal type ", cls2.getName(), ", actual type ", cls.getName()));
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
    public final Object A09(AnonymousClass0aT r9, AbstractC02570aK r10) throws IOException, C05910ld {
        String str;
        Object A05;
        Class<?> cls;
        StringBuilder sb;
        String str2;
        if (r9.A0G() != EnumC05930lf.START_ARRAY) {
            A01(r9, r10);
        } else {
            if (!this._vanillaProcessing) {
                if (this._nonStandardCreation) {
                    JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
                    if (jsonDeserializer != null) {
                        A05 = this._valueInstantiator.A09(r10, jsonDeserializer.A09(r9, r10));
                    } else if (this._propertyBasedCreator != null) {
                        A05 = A0a(r9, r10);
                    } else {
                        if (this._beanType.A0I()) {
                            sb = new StringBuilder("Can not instantiate abstract type ");
                            sb.append(this._beanType);
                            str2 = " (need to add/enable type information?)";
                        } else {
                            sb = new StringBuilder("No suitable constructor found for type ");
                            sb.append(this._beanType);
                            str2 = ": can not instantiate from JSON object (need to add/enable type information?)";
                        }
                        sb.append(str2);
                        throw AnonymousClass0aG.A00(r9, sb.toString());
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
                    AbstractC01680Ku[] r5 = this._orderedProperties;
                    int i = 0;
                    int length = r5.length;
                    while (true) {
                        EnumC05930lf A0a = r9.A0a();
                        EnumC05930lf r1 = EnumC05930lf.END_ARRAY;
                        if (A0a == r1) {
                            break;
                        } else if (i != length) {
                            AbstractC01680Ku r2 = r5[i];
                            i++;
                            if (r2 == null || (cls != null && !r2.A0B(cls))) {
                                r9.A0F();
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
                            while (r9.A0a() != r1) {
                                r9.A0F();
                            }
                        }
                    }
                }
                return A00(r10, A05);
            }
            Object A052 = this._valueInstantiator.A05(r10);
            AbstractC01680Ku[] r4 = this._orderedProperties;
            int i2 = 0;
            int length2 = r4.length;
            while (true) {
                EnumC05930lf A0a2 = r9.A0a();
                EnumC05930lf r12 = EnumC05930lf.END_ARRAY;
                if (A0a2 == r12) {
                    break;
                } else if (i2 != length2) {
                    AbstractC01680Ku r0 = r4[i2];
                    if (r0 != null) {
                        try {
                            A052 = r0.A06(r9, r10, A052);
                        } catch (Exception e2) {
                            A0g(e2, A052, r0._propName, r10);
                        }
                    } else {
                        r9.A0F();
                    }
                    i2++;
                } else if (!this._ignoreAllUnknown) {
                    str = AnonymousClass006.A02("Unexpected JSON values; expected at most ", length2, " properties (in JSON Array)");
                } else {
                    while (r9.A0a() != r12) {
                        r9.A0F();
                    }
                }
            }
            return A00(r10, A052);
            throw AnonymousClass0aG.A00(r10.A00, str);
        }
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase
    public final Object A0Z(AnonymousClass0aT r3, AbstractC02570aK r4) throws IOException, C05910ld {
        A01(r3, r4);
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }
}
