package com.fasterxml.jackson.databind.deser.impl;

import X.AbstractC0073Cr;
import X.AbstractC0226Wn;
import X.AbstractC0232Ww;
import X.AnonymousClass06;
import X.C0201Vh;
import X.C0203Vk;
import X.C0204Vm;
import X.C0223Wj;
import X.EnumC0470q2;
import X.KI;
import X.q0;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.BeanDeserializerBase;
import java.io.IOException;
import java.util.HashSet;

public final class BeanAsArrayDeserializer extends BeanDeserializerBase {
    public static final long serialVersionUID = 1;
    public final BeanDeserializerBase _delegate;
    public final AbstractC0073Cr[] _orderedProperties;

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase
    public final BeanDeserializerBase A0P() {
        return this;
    }

    public BeanAsArrayDeserializer(BeanDeserializerBase beanDeserializerBase, AbstractC0073Cr[] crArr) {
        super(beanDeserializerBase, beanDeserializerBase._ignoreAllUnknown);
        this._delegate = beanDeserializerBase;
        this._orderedProperties = crArr;
    }

    private final void A00(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        StringBuilder sb = new StringBuilder("Can not deserialize a POJO (of type ");
        sb.append(this._beanType._class.getName());
        sb.append(") from non-Array representation (token: ");
        sb.append(ww.A0Z());
        sb.append("): type/property designed to be serialized as JSON Array");
        throw C0223Wj.A00(wn.A00, sb.toString());
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0A(AbstractC0232Ww ww, AbstractC0226Wn wn, Object obj) throws IOException, q0 {
        if (this._injectables != null) {
            A0d(wn);
        }
        AbstractC0073Cr[] crArr = this._orderedProperties;
        int i = 0;
        int length = crArr.length;
        while (true) {
            EnumC0470q2 A0a = ww.A0a();
            EnumC0470q2 q2Var = EnumC0470q2.END_ARRAY;
            if (A0a == q2Var) {
                break;
            } else if (i != length) {
                AbstractC0073Cr cr = crArr[i];
                if (cr != null) {
                    try {
                        cr.A08(ww, wn, obj);
                    } catch (Exception e) {
                        A0g(e, obj, cr._propName, wn);
                        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                    }
                } else {
                    ww.A0Y();
                }
                i++;
            } else if (!this._ignoreAllUnknown) {
                throw C0223Wj.A00(wn.A00, AnonymousClass06.A02("Unexpected JSON values; expected at most ", length, " properties (in JSON Array)"));
            } else {
                while (ww.A0a() != q2Var) {
                    ww.A0Y();
                }
            }
        }
        return obj;
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase, com.fasterxml.jackson.databind.JsonDeserializer
    public final JsonDeserializer<Object> A0B(KI ki) {
        return this._delegate.A0B(ki);
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase
    public final BeanDeserializerBase A0Q(C0204Vm vm) {
        return new BeanAsArrayDeserializer(this._delegate.A0Q(vm), this._orderedProperties);
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase
    public final BeanDeserializerBase A0R(HashSet hashSet) {
        return new BeanAsArrayDeserializer(this._delegate.A0R(hashSet), this._orderedProperties);
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase
    public final Object A0a(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        AbstractC0073Cr cr;
        C0203Vk vk = this._propertyBasedCreator;
        C0201Vh A01 = vk.A01(ww, wn, this._objectIdReader);
        AbstractC0073Cr[] crArr = this._orderedProperties;
        int length = crArr.length;
        Object obj = null;
        int i = 0;
        while (ww.A0a() != EnumC0470q2.END_ARRAY) {
            if (i < length) {
                cr = crArr[i];
            } else {
                cr = null;
            }
            if (cr == null) {
                ww.A0Y();
            } else if (obj != null) {
                try {
                    cr.A08(ww, wn, obj);
                } catch (Exception e) {
                    A0g(e, obj, cr._propName, wn);
                }
            } else {
                String str = cr._propName;
                AbstractC0073Cr cr2 = vk.A00.get(str);
                if (cr2 != null) {
                    if (A01.A02(cr2.A01(), cr2.A05(ww, wn))) {
                        try {
                            obj = vk.A02(wn, A01);
                            Class<?> cls = obj.getClass();
                            Class<?> cls2 = this._beanType._class;
                            if (cls != cls2) {
                                throw C0223Wj.A00(wn.A00, AnonymousClass06.A06("Can not support implicit polymorphic deserialization for POJOs-as-Arrays style: nominal type ", cls2.getName(), ", actual type ", cls.getName()));
                            }
                        } catch (Exception e2) {
                            A0g(e2, this._beanType._class, str, wn);
                            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                        }
                    } else {
                        continue;
                    }
                } else if (!A01.A03(str)) {
                    A01.A01(cr, cr.A05(ww, wn));
                }
            }
            i++;
        }
        if (obj != null) {
            return obj;
        }
        try {
            return vk.A02(wn, A01);
        } catch (Exception e3) {
            A0f(e3, wn);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A09(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        String str;
        Class<?> cls;
        StringBuilder sb;
        String str2;
        if (ww.A0Z() != EnumC0470q2.START_ARRAY) {
            A00(ww, wn);
        } else {
            if (this._vanillaProcessing) {
                Object A05 = this._valueInstantiator.A05(wn);
                AbstractC0073Cr[] crArr = this._orderedProperties;
                int i = 0;
                int length = crArr.length;
                while (true) {
                    EnumC0470q2 A0a = ww.A0a();
                    EnumC0470q2 q2Var = EnumC0470q2.END_ARRAY;
                    if (A0a == q2Var) {
                        break;
                    } else if (i != length) {
                        AbstractC0073Cr cr = crArr[i];
                        if (cr != null) {
                            try {
                                cr.A08(ww, wn, A05);
                            } catch (Exception e) {
                                A0g(e, A05, cr._propName, wn);
                            }
                        } else {
                            ww.A0Y();
                        }
                        i++;
                    } else if (!this._ignoreAllUnknown) {
                        str = AnonymousClass06.A02("Unexpected JSON values; expected at most ", length, " properties (in JSON Array)");
                    } else {
                        while (ww.A0a() != q2Var) {
                            ww.A0Y();
                        }
                    }
                }
                return A05;
            } else if (this._nonStandardCreation) {
                JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
                if (jsonDeserializer != null) {
                    return this._valueInstantiator.A09(wn, jsonDeserializer.A09(ww, wn));
                }
                if (this._propertyBasedCreator != null) {
                    return A0a(ww, wn);
                }
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
                throw C0223Wj.A00(ww, sb.toString());
            } else {
                Object A052 = this._valueInstantiator.A05(wn);
                if (this._injectables != null) {
                    A0d(wn);
                }
                if (this._needViewProcesing) {
                    cls = wn._view;
                } else {
                    cls = null;
                }
                AbstractC0073Cr[] crArr2 = this._orderedProperties;
                int i2 = 0;
                int length2 = crArr2.length;
                while (true) {
                    EnumC0470q2 A0a2 = ww.A0a();
                    EnumC0470q2 q2Var2 = EnumC0470q2.END_ARRAY;
                    if (A0a2 == q2Var2) {
                        return A052;
                    }
                    if (i2 != length2) {
                        AbstractC0073Cr cr2 = crArr2[i2];
                        i2++;
                        if (cr2 == null || (cls != null && !cr2.A0B(cls))) {
                            ww.A0Y();
                        } else {
                            try {
                                cr2.A08(ww, wn, A052);
                            } catch (Exception e2) {
                                A0g(e2, A052, cr2._propName, wn);
                            }
                        }
                    } else if (!this._ignoreAllUnknown) {
                        str = AnonymousClass06.A02("Unexpected JSON values; expected at most ", length2, " properties (in JSON Array)");
                    } else {
                        while (ww.A0a() != q2Var2) {
                            ww.A0Y();
                        }
                        return A052;
                    }
                }
            }
            throw C0223Wj.A00(wn.A00, str);
        }
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase
    public final Object A0Z(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        A00(ww, wn);
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }
}
