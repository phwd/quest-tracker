package com.fasterxml.jackson.databind.jsontype.impl;

import X.AbstractC0224Wl;
import X.AnonymousClass7K;
import X.AnonymousClass7L;
import X.AnonymousClass7N;
import X.AnonymousClass8M;
import X.C2;
import X.C4;
import X.C5;
import X.C6;
import X.EnumC0463pg;
import X.EnumC0464ph;
import X.V2;
import X.V3;
import X.V4;
import X.V7;
import java.util.Collection;
import java.util.HashMap;

public class StdTypeResolverBuilder implements V2<StdTypeResolverBuilder> {
    public V3 _customIdResolver;
    public Class<?> _defaultImpl;
    public EnumC0464ph _idType;
    public EnumC0463pg _includeAs;
    public boolean _typeIdVisible = false;
    public String _typeProperty;

    @Override // X.V2
    public final V4 A1W(AnonymousClass8M r11, AbstractC0224Wl wl, Collection<V7> collection) {
        int lastIndexOf;
        EnumC0464ph phVar = this._idType;
        if (phVar == EnumC0464ph.NONE) {
            return null;
        }
        V3 v3 = this._customIdResolver;
        if (v3 == null) {
            if (phVar != null) {
                switch (phVar.ordinal()) {
                    case 0:
                        v3 = null;
                        break;
                    case 1:
                        v3 = new C4(wl, r11._base._typeFactory);
                        break;
                    case 2:
                        v3 = new AnonymousClass7K(wl, r11._base._typeFactory);
                        break;
                    case 3:
                        HashMap hashMap = new HashMap();
                        if (collection != null) {
                            for (V7 v7 : collection) {
                                Class<?> cls = v7._class;
                                String str = v7._name;
                                if (str == null && (lastIndexOf = (str = cls.getName()).lastIndexOf(46)) >= 0) {
                                    str = str.substring(lastIndexOf + 1);
                                }
                                AbstractC0224Wl wl2 = (AbstractC0224Wl) hashMap.get(str);
                                if (wl2 == null || !cls.isAssignableFrom(wl2._class)) {
                                    hashMap.put(str, r11.A03(cls));
                                }
                            }
                        }
                        v3 = new C2(r11, wl, hashMap);
                        break;
                    default:
                        StringBuilder sb = new StringBuilder("Do not know how to construct standard type id resolver for idType: ");
                        sb.append(phVar);
                        throw new IllegalStateException(sb.toString());
                }
            } else {
                throw new IllegalStateException("Can not build, 'init()' not yet called");
            }
        }
        EnumC0463pg pgVar = this._includeAs;
        switch (pgVar.ordinal()) {
            case 0:
                return new AnonymousClass7L(wl, v3, this._typeProperty, this._typeIdVisible, this._defaultImpl);
            case 1:
                return new C5(wl, v3, this._typeProperty, this._typeIdVisible);
            case 2:
                return new C6(wl, v3, this._typeProperty, this._typeIdVisible, this._defaultImpl);
            case 3:
                return new AnonymousClass7N(wl, v3, this._typeProperty, this._typeIdVisible, this._defaultImpl);
            default:
                StringBuilder sb2 = new StringBuilder("Do not know how to construct standard type serializer for inclusion type: ");
                sb2.append(pgVar);
                throw new IllegalStateException(sb2.toString());
        }
    }

    /* Return type fixed from 'X.V2' to match base method */
    @Override // X.V2
    public final StdTypeResolverBuilder A33(EnumC0463pg pgVar) {
        if (pgVar != null) {
            this._includeAs = pgVar;
            return this;
        }
        throw new IllegalArgumentException("includeAs can not be null");
    }

    /* Return type fixed from 'X.V2' to match base method */
    @Override // X.V2
    public final /* bridge */ /* synthetic */ StdTypeResolverBuilder A35(EnumC0464ph phVar, V3 v3) {
        if (phVar != null) {
            this._idType = phVar;
            this._customIdResolver = v3;
            this._typeProperty = phVar.getDefaultPropertyName();
            return this;
        }
        throw new IllegalArgumentException("idType can not be null");
    }

    /* Return type fixed from 'X.V2' to match base method */
    @Override // X.V2
    public final StdTypeResolverBuilder A5Y(String str) {
        if (str == null || str.length() == 0) {
            str = this._idType.getDefaultPropertyName();
        }
        this._typeProperty = str;
        return this;
    }

    @Override // X.V2
    public final Class<?> A2Q() {
        return this._defaultImpl;
    }

    /* Return type fixed from 'X.V2' to match base method */
    @Override // X.V2
    public final StdTypeResolverBuilder A1n(Class cls) {
        this._defaultImpl = cls;
        return this;
    }

    /* Return type fixed from 'X.V2' to match base method */
    @Override // X.V2
    public final StdTypeResolverBuilder A5X(boolean z) {
        this._typeIdVisible = z;
        return this;
    }
}
