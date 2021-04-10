package X;

import com.facebook.assistant.oacr.OacrConstants;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.jsontype.impl.FailingDeserializer;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

/* renamed from: X.r7  reason: case insensitive filesystem */
public abstract class AbstractC1034r7 implements O5, Serializable {
    public static final JsonDeserializer A01 = new FailingDeserializer();
    public static final long serialVersionUID = -1026580169193933453L;
    public final transient Q0 A00;
    public final boolean _isRequired;
    public String _managedReferenceName;
    public final C0272Ot _nullProvider;
    public final String _propName;
    public int _propertyIndex;
    public final AbstractC1024qt _type;
    public JsonDeserializer _valueDeserializer;
    public final PR _valueTypeDeserializer;
    public QL _viewMatcher;
    public final OE _wrapperName;

    public static final void A00(Exception exc) {
        if (!(exc instanceof IOException)) {
            boolean z = exc instanceof RuntimeException;
            Exception exc2 = exc;
            if (z) {
                throw exc;
            }
            while (exc2.getCause() != null) {
                exc2 = exc2.getCause();
            }
            throw new C1025qv(exc2.getMessage(), null, exc2);
        }
        throw exc;
    }

    private final void A08(Exception exc, Object obj) {
        String name;
        if (exc instanceof IllegalArgumentException) {
            if (obj == null) {
                name = "[NULL]";
            } else {
                name = obj.getClass().getName();
            }
            StringBuilder sb = new StringBuilder("Problem deserializing property '");
            sb.append(this._propName);
            sb.append("' (expected type: ");
            sb.append(A34());
            sb.append("; actual type: ");
            sb.append(name);
            sb.append(")");
            String message = exc.getMessage();
            if (message != null) {
                sb.append(", problem: ");
            } else {
                message = " (no error message provided)";
            }
            sb.append(message);
            throw new C1025qv(sb.toString(), null, exc);
        }
        A00(exc);
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    public final int A01() {
        if (!(this instanceof C0372Ui)) {
            return -1;
        }
        return ((C0372Ui) this)._creatorIndex;
    }

    public final AbstractC1034r7 A02(JsonDeserializer jsonDeserializer) {
        if (this instanceof Ta) {
            return new Ta((Ta) this, jsonDeserializer);
        }
        if (this instanceof C0351Tb) {
            return new C0351Tb((C0351Tb) this, jsonDeserializer);
        }
        if (this instanceof C0352Tc) {
            return new C0352Tc((C0352Tc) this, jsonDeserializer);
        }
        if (this instanceof C0353Td) {
            return new C0353Td((C0353Td) this, jsonDeserializer);
        }
        if (this instanceof UP) {
            return new UP((UP) this, jsonDeserializer);
        }
        if (!(this instanceof C0367Uc)) {
            return new C0372Ui((C0372Ui) this, jsonDeserializer);
        }
        return new C0367Uc((C0367Uc) this, jsonDeserializer);
    }

    public final AbstractC1034r7 A03(String str) {
        if (this instanceof Ta) {
            return new Ta((Ta) this, str);
        }
        if (this instanceof C0351Tb) {
            return new C0351Tb((C0351Tb) this, str);
        }
        if (this instanceof C0352Tc) {
            return new C0352Tc((C0352Tc) this, str);
        }
        if (this instanceof C0353Td) {
            return new C0353Td((C0353Td) this, str);
        }
        if (this instanceof UP) {
            return new UP((UP) this, str);
        }
        if (!(this instanceof C0367Uc)) {
            return new C0372Ui((C0372Ui) this, str);
        }
        return new C0367Uc((C0367Uc) this, str);
    }

    public final Object A05(AbstractC1014qi qiVar, AbstractC1022qr qrVar, Object obj) {
        if (!(this instanceof Ta)) {
            if (this instanceof C0351Tb) {
                C0351Tb tb = (C0351Tb) this;
                Object A0C = tb._valueDeserializer.A0C(qiVar, qrVar);
                qrVar.A0F(A0C, tb._objectIdReader.generator).A00(obj);
                AbstractC1034r7 r7Var = tb._objectIdReader.idProperty;
                if (r7Var != null) {
                    return r7Var.A06(obj, A0C);
                }
            } else if ((this instanceof C0352Tc) || (this instanceof C0353Td) || (this instanceof UP)) {
                return A06(obj, A04(qiVar, qrVar));
            } else {
                if (!(this instanceof C0367Uc)) {
                    A04(qiVar, qrVar);
                } else {
                    A06(obj, A04(qiVar, qrVar));
                    return obj;
                }
            }
            return obj;
        }
        A07(qiVar, qrVar, obj);
        return obj;
    }

    public final Object A06(Object obj, Object obj2) {
        if (this instanceof Ta) {
            A09(obj, obj2);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        } else if (!(this instanceof C0351Tb)) {
            if (this instanceof C0352Tc) {
                C0352Tc tc = (C0352Tc) this;
                try {
                    Object invoke = tc.A00.invoke(obj, obj2);
                    if (invoke != null) {
                        return invoke;
                    }
                } catch (Exception e) {
                    tc.A08(e, obj2);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            } else if (this instanceof C0353Td) {
                C0353Td td = (C0353Td) this;
                Object A06 = td._managedProperty.A06(obj, obj2);
                if (obj2 != null) {
                    if (!td._isContainer) {
                        td._backProperty.A09(obj2, obj);
                    } else if (obj2 instanceof Object[]) {
                        Object[] objArr = (Object[]) obj2;
                        for (Object obj3 : objArr) {
                            if (obj3 != null) {
                                td._backProperty.A09(obj3, obj);
                            }
                        }
                    } else if (obj2 instanceof Collection) {
                        for (Object obj4 : (Collection) obj2) {
                            if (obj4 != null) {
                                td._backProperty.A09(obj4, obj);
                            }
                        }
                    } else if (obj2 instanceof Map) {
                        for (Object obj5 : ((Map) obj2).values()) {
                            if (obj5 != null) {
                                td._backProperty.A09(obj5, obj);
                            }
                        }
                    } else {
                        throw new IllegalStateException(AnonymousClass08.A07("Unsupported container type (", obj2.getClass().getName(), ") when resolving reference '", td._referenceName, "'"));
                    }
                }
                return A06;
            } else if (this instanceof UP) {
                return ((UP) this)._delegate.A06(obj, obj2);
            } else {
                if (this instanceof C0367Uc) {
                    C0367Uc uc = (C0367Uc) this;
                    try {
                        uc.A00.set(obj, obj2);
                        return obj;
                    } catch (Exception e2) {
                        uc.A08(e2, obj2);
                        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                    }
                }
            }
            return obj;
        } else {
            AbstractC1034r7 r7Var = ((C0351Tb) this)._objectIdReader.idProperty;
            if (r7Var != null) {
                return r7Var.A06(obj, obj2);
            }
            throw new UnsupportedOperationException("Should not call set() on ObjectIdProperty that has no SettableBeanProperty");
        }
    }

    public final void A07(AbstractC1014qi qiVar, AbstractC1022qr qrVar, Object obj) {
        if (this instanceof Ta) {
            Ta ta = (Ta) this;
            if (qiVar.A0U() != NX.VALUE_NULL) {
                try {
                    Object invoke = ta._getter.invoke(obj, new Object[0]);
                    if (invoke != null) {
                        ta._valueDeserializer.A0A(qiVar, qrVar, invoke);
                        return;
                    }
                    throw new C1025qv(AnonymousClass08.A05("Problem deserializing 'setterless' property '", ta._propName, "': get method returned null"));
                } catch (Exception e) {
                    A00(e);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            }
        } else if (!(this instanceof C0351Tb)) {
            if (!(this instanceof C0352Tc)) {
                if (this instanceof C0353Td) {
                    C0353Td td = (C0353Td) this;
                    td.A09(obj, td._managedProperty.A04(qiVar, qrVar));
                    return;
                } else if (this instanceof UP) {
                    UP up = (UP) this;
                    Object obj2 = null;
                    if (qiVar.A0U() == NX.VALUE_NULL) {
                        C0272Ot ot = up._nullProvider;
                        if (ot != null) {
                            obj2 = ot.A00(qrVar);
                        }
                    } else {
                        PR pr = up._valueTypeDeserializer;
                        if (pr != null) {
                            obj2 = up._valueDeserializer.A09(qiVar, qrVar, pr);
                        } else {
                            try {
                                obj2 = up._creator.newInstance(obj);
                                up._valueDeserializer.A0A(qiVar, qrVar, obj2);
                            } catch (Exception e2) {
                                Q5.A05(e2, AnonymousClass08.A06("Failed to instantiate class ", up._creator.getDeclaringClass().getName(), ", problem: ", e2.getMessage()));
                                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                            }
                        }
                    }
                    up.A09(obj, obj2);
                    return;
                } else if (!(this instanceof C0367Uc)) {
                    A09(obj, A04(qiVar, qrVar));
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            }
            A09(obj, A04(qiVar, qrVar));
        } else {
            A05(qiVar, qrVar, obj);
        }
    }

    public final void A09(Object obj, Object obj2) {
        if (!(this instanceof Ta)) {
            if (!(this instanceof C0351Tb)) {
                if (this instanceof C0352Tc) {
                    C0352Tc tc = (C0352Tc) this;
                    try {
                        tc.A00.invoke(obj, obj2);
                        return;
                    } catch (Exception e) {
                        tc.A08(e, obj2);
                        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                    }
                } else if (!(this instanceof C0353Td)) {
                    if (this instanceof UP) {
                        ((UP) this)._delegate.A09(obj, obj2);
                        return;
                    } else if (!(this instanceof C0367Uc)) {
                        throw new IllegalStateException(AnonymousClass08.A04("Method should never be called on a ", getClass().getName()));
                    } else {
                        C0367Uc uc = (C0367Uc) this;
                        try {
                            uc.A00.set(obj, obj2);
                            return;
                        } catch (Exception e2) {
                            uc.A08(e2, obj2);
                            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                        }
                    }
                }
            }
            A06(obj, obj2);
            return;
        }
        throw new UnsupportedOperationException("Should never call 'set' on setterless property");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002e, code lost:
        if (r0.isAssignableFrom(r5) != false) goto L_0x0030;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean A0A(java.lang.Class r5) {
        /*
            r4 = this;
            X.QL r3 = r4._viewMatcher
            if (r3 == 0) goto L_0x0030
            boolean r0 = r3 instanceof X.C1081rx
            if (r0 != 0) goto L_0x0023
            boolean r0 = r3 instanceof X.C1080rw
            if (r0 == 0) goto L_0x0032
            X.rw r3 = (X.C1080rw) r3
            java.lang.Class[] r0 = r3._views
            int r2 = r0.length
            r1 = 0
        L_0x0012:
            if (r1 >= r2) goto L_0x0032
            java.lang.Class[] r0 = r3._views
            r0 = r0[r1]
            if (r5 == r0) goto L_0x0030
            boolean r0 = r0.isAssignableFrom(r5)
            if (r0 != 0) goto L_0x0030
            int r1 = r1 + 1
            goto L_0x0012
        L_0x0023:
            X.rx r3 = (X.C1081rx) r3
            java.lang.Class r0 = r3._view
            if (r5 == r0) goto L_0x0030
            boolean r0 = r0.isAssignableFrom(r5)
            r1 = 0
            if (r0 == 0) goto L_0x0033
        L_0x0030:
            r1 = 1
        L_0x0031:
            return r1
        L_0x0032:
            r1 = 0
        L_0x0033:
            if (r1 == 0) goto L_0x0031
            goto L_0x0030
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AbstractC1034r7.A0A(java.lang.Class):boolean");
    }

    @Override // X.O5
    public final AbstractC1044rJ A2e() {
        if (this instanceof Ta) {
            return ((Ta) this)._annotated;
        }
        if (this instanceof C0351Tb) {
            return null;
        }
        if (this instanceof C0352Tc) {
            return ((C0352Tc) this)._annotated;
        }
        if (this instanceof C0353Td) {
            return ((C0353Td) this)._managedProperty.A2e();
        }
        if (this instanceof UP) {
            return ((UP) this)._delegate.A2e();
        }
        if (!(this instanceof C0367Uc)) {
            return ((C0372Ui) this)._annotated;
        }
        return ((C0367Uc) this)._annotated;
    }

    public String toString() {
        return AnonymousClass08.A05("[property '", this._propName, "']");
    }

    public final Object A04(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        if (qiVar.A0U() == NX.VALUE_NULL) {
            C0272Ot ot = this._nullProvider;
            if (ot == null) {
                return null;
            }
            return ot.A00(qrVar);
        }
        PR pr = this._valueTypeDeserializer;
        if (pr != null) {
            return this._valueDeserializer.A09(qiVar, qrVar, pr);
        }
        return this._valueDeserializer.A0C(qiVar, qrVar);
    }

    @Override // X.O5
    public final AbstractC1024qt A34() {
        return this._type;
    }

    public AbstractC1034r7(AbstractC1034r7 r7Var) {
        this._propertyIndex = -1;
        this._propName = r7Var._propName;
        this._type = r7Var._type;
        this._wrapperName = r7Var._wrapperName;
        this._isRequired = r7Var._isRequired;
        this.A00 = r7Var.A00;
        this._valueDeserializer = r7Var._valueDeserializer;
        this._valueTypeDeserializer = r7Var._valueTypeDeserializer;
        this._nullProvider = r7Var._nullProvider;
        this._managedReferenceName = r7Var._managedReferenceName;
        this._propertyIndex = r7Var._propertyIndex;
        this._viewMatcher = r7Var._viewMatcher;
    }

    public AbstractC1034r7(AbstractC1034r7 r7Var, JsonDeserializer jsonDeserializer) {
        this._propertyIndex = -1;
        this._propName = r7Var._propName;
        AbstractC1024qt qtVar = r7Var._type;
        this._type = qtVar;
        this._wrapperName = r7Var._wrapperName;
        this._isRequired = r7Var._isRequired;
        this.A00 = r7Var.A00;
        this._valueTypeDeserializer = r7Var._valueTypeDeserializer;
        this._managedReferenceName = r7Var._managedReferenceName;
        this._propertyIndex = r7Var._propertyIndex;
        C0272Ot ot = null;
        if (jsonDeserializer == null) {
            this._nullProvider = null;
            jsonDeserializer = A01;
        } else {
            Object A08 = jsonDeserializer.A08();
            this._nullProvider = A08 != null ? new C0272Ot(qtVar, A08) : ot;
        }
        this._valueDeserializer = jsonDeserializer;
        this._viewMatcher = r7Var._viewMatcher;
    }

    public AbstractC1034r7(AbstractC1034r7 r7Var, String str) {
        this._propertyIndex = -1;
        this._propName = str;
        this._type = r7Var._type;
        this._wrapperName = r7Var._wrapperName;
        this._isRequired = r7Var._isRequired;
        this.A00 = r7Var.A00;
        this._valueDeserializer = r7Var._valueDeserializer;
        this._valueTypeDeserializer = r7Var._valueTypeDeserializer;
        this._nullProvider = r7Var._nullProvider;
        this._managedReferenceName = r7Var._managedReferenceName;
        this._propertyIndex = r7Var._propertyIndex;
        this._viewMatcher = r7Var._viewMatcher;
    }

    public AbstractC1034r7(PE pe, AbstractC1024qt qtVar, PR pr, Q0 q0) {
        this(pe.A0D(), qtVar, pe.A06(), pr, q0, pe.A0J());
    }

    public AbstractC1034r7(String str, AbstractC1024qt qtVar, OE oe, PR pr, Q0 q0, boolean z) {
        String str2;
        this._propertyIndex = -1;
        if (str == null || str.length() == 0) {
            str2 = OacrConstants.AUTO_SPEECH_DOMAIN;
        } else {
            C0261Nz nz = C0261Nz.A00;
            synchronized (nz) {
                str2 = (String) nz.get(str);
                if (str2 == null) {
                    str2 = str.intern();
                    nz.put(str2, str2);
                }
            }
        }
        this._propName = str2;
        this._type = qtVar;
        this._wrapperName = oe;
        this._isRequired = z;
        this.A00 = q0;
        this._viewMatcher = null;
        this._nullProvider = null;
        this._valueTypeDeserializer = pr != null ? pr.A03(this) : pr;
        this._valueDeserializer = A01;
    }
}
