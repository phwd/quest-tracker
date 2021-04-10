package com.fasterxml.jackson.databind.jsontype.impl;

import X.AbstractC1024qt;
import X.AbstractC1032r3;
import X.AnonymousClass0a;
import X.AnonymousClass0c;
import X.AnonymousClass2H;
import X.AnonymousClass2I;
import X.C00000b;
import X.C00020f;
import X.C0313Qk;
import X.C0317Qp;
import X.NE;
import X.NF;
import X.PO;
import X.PR;
import X.PS;
import X.PT;
import X.PU;
import X.QP;
import X.QX;
import X.R7;
import X.R8;
import X.RU;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class StdTypeResolverBuilder implements PT {
    public PS _customIdResolver;
    public Class _defaultImpl;
    public NF _idType;
    public NE _includeAs;
    public boolean _typeIdVisible = false;
    public String _typeProperty;

    @Override // X.PT
    public final PR A1N(AnonymousClass2I r11, AbstractC1024qt qtVar, Collection collection) {
        if (this._idType == NF.NONE) {
            return null;
        }
        PS A00 = A00(r11, qtVar, collection, false, true);
        NE ne = this._includeAs;
        switch (ne.ordinal()) {
            case 0:
                return new AnonymousClass0c(qtVar, A00, this._typeProperty, this._typeIdVisible, this._defaultImpl);
            case 1:
                return new C0317Qp(qtVar, A00, this._typeProperty, this._typeIdVisible);
            case 2:
                return new RU(qtVar, A00, this._typeProperty, this._typeIdVisible, this._defaultImpl);
            case 3:
                return new C00020f(qtVar, A00, this._typeProperty, this._typeIdVisible, this._defaultImpl);
            default:
                StringBuilder sb = new StringBuilder("Do not know how to construct standard type serializer for inclusion type: ");
                sb.append(ne);
                throw new IllegalStateException(sb.toString());
        }
    }

    @Override // X.PT
    public final PU A1O(AnonymousClass2H r12, AbstractC1024qt qtVar, Collection collection) {
        if (this._idType == NF.NONE) {
            return null;
        }
        PS A00 = A00(r12, qtVar, collection, true, false);
        NE ne = this._includeAs;
        switch (ne.ordinal()) {
            case 0:
                return new C00000b(A00, null, this._typeProperty);
            case 1:
                return new C0313Qk(A00, null);
            case 2:
                return new R8(A00, null);
            case 3:
                return new R7(A00, null, this._typeProperty);
            default:
                StringBuilder sb = new StringBuilder("Do not know how to construct standard type serializer for inclusion type: ");
                sb.append(ne);
                throw new IllegalStateException(sb.toString());
        }
    }

    private final PS A00(AbstractC1032r3 r3Var, AbstractC1024qt qtVar, Collection collection, boolean z, boolean z2) {
        HashMap hashMap;
        int lastIndexOf;
        AbstractC1024qt qtVar2;
        PS ps = this._customIdResolver;
        if (ps != null) {
            return ps;
        }
        NF nf = this._idType;
        if (nf != null) {
            switch (nf.ordinal()) {
                case 0:
                    return null;
                case 1:
                    return new QX(qtVar, r3Var._base._typeFactory);
                case 2:
                    return new AnonymousClass0a(qtVar, r3Var._base._typeFactory);
                case 3:
                    if (z != z2) {
                        HashMap hashMap2 = null;
                        if (z) {
                            hashMap = new HashMap();
                        } else {
                            hashMap = null;
                        }
                        if (z2) {
                            hashMap2 = new HashMap();
                        }
                        if (collection != null) {
                            Iterator it = collection.iterator();
                            while (it.hasNext()) {
                                PO po = (PO) it.next();
                                Class cls = po._class;
                                String str = po._name;
                                if (str == null && (lastIndexOf = (str = cls.getName()).lastIndexOf(46)) >= 0) {
                                    str = str.substring(lastIndexOf + 1);
                                }
                                if (z) {
                                    hashMap.put(cls.getName(), str);
                                }
                                if (z2 && ((qtVar2 = (AbstractC1024qt) hashMap2.get(str)) == null || !cls.isAssignableFrom(qtVar2._class))) {
                                    hashMap2.put(str, r3Var.A03(cls));
                                }
                            }
                        }
                        return new QP(r3Var, qtVar, hashMap, hashMap2);
                    }
                    throw new IllegalArgumentException();
                default:
                    StringBuilder sb = new StringBuilder("Do not know how to construct standard type id resolver for idType: ");
                    sb.append(nf);
                    throw new IllegalStateException(sb.toString());
            }
        } else {
            throw new IllegalStateException("Can not build, 'init()' not yet called");
        }
    }

    @Override // X.PT
    public final PT A3H(NE ne) {
        if (ne != null) {
            this._includeAs = ne;
            return this;
        }
        throw new IllegalArgumentException("includeAs can not be null");
    }

    @Override // X.PT
    public final /* bridge */ /* synthetic */ PT A3J(NF nf, PS ps) {
        if (nf != null) {
            this._idType = nf;
            this._customIdResolver = ps;
            this._typeProperty = nf.getDefaultPropertyName();
            return this;
        }
        throw new IllegalArgumentException("idType can not be null");
    }

    @Override // X.PT
    public final PT A5J(String str) {
        if (str == null || str.length() == 0) {
            str = this._idType.getDefaultPropertyName();
        }
        this._typeProperty = str;
        return this;
    }

    @Override // X.PT
    public final Class A2M() {
        return this._defaultImpl;
    }

    @Override // X.PT
    public final PT A1e(Class cls) {
        this._defaultImpl = cls;
        return this;
    }

    @Override // X.PT
    public final PT A5I(boolean z) {
        this._typeIdVisible = z;
        return this;
    }
}
