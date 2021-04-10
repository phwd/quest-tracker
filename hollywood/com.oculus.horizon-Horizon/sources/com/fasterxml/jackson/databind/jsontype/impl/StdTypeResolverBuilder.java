package com.fasterxml.jackson.databind.jsontype.impl;

import X.AbstractC04000gb;
import X.AbstractC05940mA;
import X.AbstractC05950mB;
import X.AnonymousClass07I;
import X.AnonymousClass07L;
import X.AnonymousClass07M;
import X.AnonymousClass08X;
import X.AnonymousClass0GK;
import X.AnonymousClass0GO;
import X.AnonymousClass0GP;
import X.AnonymousClass0GQ;
import X.AnonymousClass0m9;
import X.C05910m6;
import X.EnumC04710jK;
import X.EnumC04720jL;
import java.util.Collection;
import java.util.HashMap;

public class StdTypeResolverBuilder implements AbstractC05950mB<StdTypeResolverBuilder> {
    public AbstractC05940mA _customIdResolver;
    public Class<?> _defaultImpl;
    public EnumC04720jL _idType;
    public EnumC04710jK _includeAs;
    public boolean _typeIdVisible = false;
    public String _typeProperty;

    @Override // X.AbstractC05950mB
    public final AnonymousClass0m9 A1W(AnonymousClass08X r11, AbstractC04000gb r12, Collection<C05910m6> collection) {
        int lastIndexOf;
        EnumC04720jL r2 = this._idType;
        if (r2 == EnumC04720jL.NONE) {
            return null;
        }
        AbstractC05940mA r6 = this._customIdResolver;
        if (r6 == null) {
            if (r2 != null) {
                switch (r2.ordinal()) {
                    case 0:
                        r6 = null;
                        break;
                    case 1:
                        r6 = new AnonymousClass0GO(r12, r11._base._typeFactory);
                        break;
                    case 2:
                        r6 = new AnonymousClass07I(r12, r11._base._typeFactory);
                        break;
                    case 3:
                        HashMap hashMap = new HashMap();
                        if (collection != null) {
                            for (C05910m6 r0 : collection) {
                                Class<?> cls = r0._class;
                                String str = r0._name;
                                if (str == null && (lastIndexOf = (str = cls.getName()).lastIndexOf(46)) >= 0) {
                                    str = str.substring(lastIndexOf + 1);
                                }
                                AbstractC04000gb r02 = (AbstractC04000gb) hashMap.get(str);
                                if (r02 == null || !cls.isAssignableFrom(r02._class)) {
                                    hashMap.put(str, r11.A03(cls));
                                }
                            }
                        }
                        r6 = new AnonymousClass0GK(r11, r12, hashMap);
                        break;
                    default:
                        StringBuilder sb = new StringBuilder("Do not know how to construct standard type id resolver for idType: ");
                        sb.append(r2);
                        throw new IllegalStateException(sb.toString());
                }
            } else {
                throw new IllegalStateException("Can not build, 'init()' not yet called");
            }
        }
        EnumC04710jK r22 = this._includeAs;
        switch (r22.ordinal()) {
            case 0:
                return new AnonymousClass07L(r12, r6, this._typeProperty, this._typeIdVisible, this._defaultImpl);
            case 1:
                return new AnonymousClass0GP(r12, r6, this._typeProperty, this._typeIdVisible);
            case 2:
                return new AnonymousClass0GQ(r12, r6, this._typeProperty, this._typeIdVisible, this._defaultImpl);
            case 3:
                return new AnonymousClass07M(r12, r6, this._typeProperty, this._typeIdVisible, this._defaultImpl);
            default:
                StringBuilder sb2 = new StringBuilder("Do not know how to construct standard type serializer for inclusion type: ");
                sb2.append(r22);
                throw new IllegalStateException(sb2.toString());
        }
    }

    /* Return type fixed from 'X.0mB' to match base method */
    @Override // X.AbstractC05950mB
    public final StdTypeResolverBuilder A4m(EnumC04710jK r3) {
        if (r3 != null) {
            this._includeAs = r3;
            return this;
        }
        throw new IllegalArgumentException("includeAs can not be null");
    }

    /* Return type fixed from 'X.0mB' to match base method */
    @Override // X.AbstractC05950mB
    public final /* bridge */ /* synthetic */ StdTypeResolverBuilder A4o(EnumC04720jL r3, AbstractC05940mA r4) {
        if (r3 != null) {
            this._idType = r3;
            this._customIdResolver = r4;
            this._typeProperty = r3.getDefaultPropertyName();
            return this;
        }
        throw new IllegalArgumentException("idType can not be null");
    }

    /* Return type fixed from 'X.0mB' to match base method */
    @Override // X.AbstractC05950mB
    public final StdTypeResolverBuilder A9g(String str) {
        if (str == null || str.length() == 0) {
            str = this._idType.getDefaultPropertyName();
        }
        this._typeProperty = str;
        return this;
    }

    @Override // X.AbstractC05950mB
    public final Class<?> A3I() {
        return this._defaultImpl;
    }

    /* Return type fixed from 'X.0mB' to match base method */
    @Override // X.AbstractC05950mB
    public final StdTypeResolverBuilder A2C(Class cls) {
        this._defaultImpl = cls;
        return this;
    }

    /* Return type fixed from 'X.0mB' to match base method */
    @Override // X.AbstractC05950mB
    public final StdTypeResolverBuilder A9f(boolean z) {
        this._typeIdVisible = z;
        return this;
    }
}
