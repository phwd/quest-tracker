package com.fasterxml.jackson.databind.deser.impl;

import X.AbstractC1014qi;
import X.AbstractC1022qr;
import X.AbstractC1034r7;
import X.AnonymousClass08;
import X.AnonymousClass0q;
import X.C1025qv;
import X.NX;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.BeanDeserializerBase;

public class BeanAsArrayBuilderDeserializer extends BeanDeserializerBase {
    public static final long serialVersionUID = 1;
    public final AnonymousClass0q _buildMethod;
    public final BeanDeserializerBase _delegate;
    public final AbstractC1034r7[] _orderedProperties;

    public BeanAsArrayBuilderDeserializer(BeanDeserializerBase beanDeserializerBase, AbstractC1034r7[] r7VarArr, AnonymousClass0q r4) {
        super(beanDeserializerBase, beanDeserializerBase._ignoreAllUnknown);
        this._delegate = beanDeserializerBase;
        this._orderedProperties = r7VarArr;
        this._buildMethod = r4;
    }

    public static final Object A00(BeanAsArrayBuilderDeserializer beanAsArrayBuilderDeserializer, AbstractC1022qr qrVar, Object obj) {
        try {
            return beanAsArrayBuilderDeserializer._buildMethod.A00.invoke(obj, new Object[0]);
        } catch (Exception e) {
            beanAsArrayBuilderDeserializer.A0c(e, qrVar);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }

    public static final void A01(BeanAsArrayBuilderDeserializer beanAsArrayBuilderDeserializer, AbstractC1014qi qiVar) {
        StringBuilder sb = new StringBuilder("Can not deserialize a POJO (of type ");
        sb.append(beanAsArrayBuilderDeserializer._beanType._class.getName());
        sb.append(") from non-Array representation (token: ");
        sb.append(qiVar.A0U());
        sb.append("): type/property designed to be serialized as JSON Array");
        throw C1025qv.A00(null, sb.toString());
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0C(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        Object A01;
        Class cls;
        if (qiVar.A0U() != NX.START_ARRAY) {
            A01(this, qiVar);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        } else if (!this._vanillaProcessing) {
            if (this._nonStandardCreation) {
                JsonDeserializer jsonDeserializer = this._delegateDeserializer;
                if (jsonDeserializer != null) {
                    A01 = this._valueInstantiator.A02(qrVar, jsonDeserializer.A0C(qiVar, qrVar));
                } else if (this._propertyBasedCreator != null) {
                    A01 = A0P(qiVar, qrVar);
                } else if (this._beanType.A0G()) {
                    StringBuilder sb = new StringBuilder("Can not instantiate abstract type ");
                    sb.append(this._beanType);
                    sb.append(" (need to add/enable type information?)");
                    throw C1025qv.A00(qiVar, sb.toString());
                } else {
                    StringBuilder sb2 = new StringBuilder("No suitable constructor found for type ");
                    sb2.append(this._beanType);
                    sb2.append(": can not instantiate from JSON object (need to add/enable type information?)");
                    throw C1025qv.A00(qiVar, sb2.toString());
                }
            } else {
                A01 = this._valueInstantiator.A01(qrVar);
                if (this._injectables != null) {
                    A0a(qrVar);
                }
                if (this._needViewProcesing) {
                    cls = qrVar._view;
                } else {
                    cls = null;
                }
                AbstractC1034r7[] r7VarArr = this._orderedProperties;
                int i = 0;
                int length = r7VarArr.length;
                while (true) {
                    NX A0o = qiVar.A0o();
                    NX nx = NX.END_ARRAY;
                    if (A0o == nx) {
                        break;
                    } else if (i != length) {
                        AbstractC1034r7 r7Var = r7VarArr[i];
                        i++;
                        if (r7Var == null || (cls != null && !r7Var.A0A(cls))) {
                            qiVar.A0T();
                        } else {
                            try {
                                r7Var.A05(qiVar, qrVar, A01);
                            } catch (Exception e) {
                                A0d(e, A01, r7Var._propName, qrVar);
                                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                            }
                        }
                    } else if (!this._ignoreAllUnknown) {
                        throw C1025qv.A00(null, AnonymousClass08.A01("Unexpected JSON values; expected at most ", length, " properties (in JSON Array)"));
                    } else {
                        while (qiVar.A0o() != nx) {
                            qiVar.A0T();
                        }
                    }
                }
            }
            return A00(this, qrVar, A01);
        } else {
            Object A012 = this._valueInstantiator.A01(qrVar);
            AbstractC1034r7[] r7VarArr2 = this._orderedProperties;
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
                            A012 = r7Var2.A05(qiVar, qrVar, A012);
                        } catch (Exception e2) {
                            A0d(e2, A012, r7Var2._propName, qrVar);
                            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                        }
                    } else {
                        qiVar.A0T();
                    }
                    i2++;
                } else if (!this._ignoreAllUnknown) {
                    throw C1025qv.A00(null, AnonymousClass08.A01("Unexpected JSON values; expected at most ", length2, " properties (in JSON Array)"));
                } else {
                    while (qiVar.A0o() != nx2) {
                        qiVar.A0T();
                    }
                }
            }
            return A00(this, qrVar, A012);
        }
    }
}
