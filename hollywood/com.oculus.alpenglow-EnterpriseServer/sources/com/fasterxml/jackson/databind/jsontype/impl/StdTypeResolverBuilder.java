package com.fasterxml.jackson.databind.jsontype.impl;

import X.AnonymousClass0E4;
import X.AnonymousClass0E5;
import X.AnonymousClass0E6;
import X.AnonymousClass0E7;
import X.AnonymousClass0FM;
import X.AnonymousClass0Jy;
import X.AnonymousClass0K0;
import X.AnonymousClass0K1;
import X.AnonymousClass0K3;
import X.AnonymousClass0K4;
import X.AnonymousClass0K5;
import X.AnonymousClass0K6;
import X.AnonymousClass0a7;
import X.AnonymousClass0aI;
import X.AnonymousClass0o0;
import X.AnonymousClass0o3;
import X.AnonymousClass0o4;
import X.AnonymousClass0o5;
import X.AnonymousClass0o6;
import X.C01260Fu;
import X.EnumC05770lI;
import X.EnumC05780lJ;
import java.util.Collection;
import java.util.HashMap;

public class StdTypeResolverBuilder implements AnonymousClass0o5<StdTypeResolverBuilder> {
    public AnonymousClass0o4 _customIdResolver;
    public Class<?> _defaultImpl;
    public EnumC05780lJ _idType;
    public EnumC05770lI _includeAs;
    public boolean _typeIdVisible = false;
    public String _typeProperty;

    @Override // X.AnonymousClass0o5
    public final AnonymousClass0o3 A1b(C01260Fu r11, AnonymousClass0aI r12, Collection<AnonymousClass0o0> collection) {
        if (this._idType == EnumC05780lJ.NONE) {
            return null;
        }
        AnonymousClass0o4 A00 = A00(r11, r12, collection, false, true);
        EnumC05770lI r2 = this._includeAs;
        switch (r2.ordinal()) {
            case 0:
                return new AnonymousClass0E6(r12, A00, this._typeProperty, this._typeIdVisible, this._defaultImpl);
            case 1:
                return new AnonymousClass0K3(r12, A00, this._typeProperty, this._typeIdVisible);
            case 2:
                return new AnonymousClass0K6(r12, A00, this._typeProperty, this._typeIdVisible, this._defaultImpl);
            case 3:
                return new AnonymousClass0E7(r12, A00, this._typeProperty, this._typeIdVisible, this._defaultImpl);
            default:
                throw new IllegalStateException("Do not know how to construct standard type serializer for inclusion type: " + r2);
        }
    }

    @Override // X.AnonymousClass0o5
    public final AnonymousClass0o6 A1c(AnonymousClass0FM r12, AnonymousClass0aI r13, Collection<AnonymousClass0o0> collection) {
        if (this._idType == EnumC05780lJ.NONE) {
            return null;
        }
        AnonymousClass0o4 A00 = A00(r12, r13, collection, true, false);
        EnumC05770lI r2 = this._includeAs;
        switch (r2.ordinal()) {
            case 0:
                return new AnonymousClass0E5(A00, null, this._typeProperty);
            case 1:
                return new AnonymousClass0K1(A00, null);
            case 2:
                return new AnonymousClass0K5(A00, null);
            case 3:
                return new AnonymousClass0K4(A00, null, this._typeProperty);
            default:
                throw new IllegalStateException("Do not know how to construct standard type serializer for inclusion type: " + r2);
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    private final AnonymousClass0o4 A00(AnonymousClass0a7<?> r7, AnonymousClass0aI r8, Collection<AnonymousClass0o0> collection, boolean z, boolean z2) {
        String str;
        int lastIndexOf;
        AnonymousClass0aI r0;
        AnonymousClass0o4 r02 = this._customIdResolver;
        if (r02 != null) {
            return r02;
        }
        EnumC05780lJ r2 = this._idType;
        if (r2 != null) {
            switch (r2.ordinal()) {
                case 0:
                    return null;
                case 1:
                    return new AnonymousClass0K0(r8, r7._base._typeFactory);
                case 2:
                    return new AnonymousClass0E4(r8, r7._base._typeFactory);
                case 3:
                    if (z != z2) {
                        HashMap hashMap = null;
                        HashMap hashMap2 = z ? new HashMap() : null;
                        if (z2) {
                            hashMap = new HashMap();
                        }
                        if (collection != null) {
                            for (AnonymousClass0o0 r03 : collection) {
                                Class<?> cls = r03._class;
                                String str2 = r03._name;
                                if (str2 == null && (lastIndexOf = (str2 = cls.getName()).lastIndexOf(46)) >= 0) {
                                    str2 = str2.substring(lastIndexOf + 1);
                                }
                                if (z) {
                                    hashMap2.put(cls.getName(), str2);
                                }
                                if (z2 && ((r0 = (AnonymousClass0aI) hashMap.get(str2)) == null || !cls.isAssignableFrom(r0._class))) {
                                    hashMap.put(str2, r7.A03(cls));
                                }
                            }
                        }
                        return new AnonymousClass0Jy(r7, r8, hashMap2, hashMap);
                    }
                    throw new IllegalArgumentException();
                default:
                    str = "Do not know how to construct standard type id resolver for idType: " + r2;
                    break;
            }
        } else {
            str = "Can not build, 'init()' not yet called";
        }
        throw new IllegalStateException(str);
    }

    @Override // X.AnonymousClass0o5
    public final Class<?> A3N() {
        return this._defaultImpl;
    }

    /* Return type fixed from 'X.0o5' to match base method */
    @Override // X.AnonymousClass0o5
    public final StdTypeResolverBuilder A59(EnumC05770lI r3) {
        if (r3 != null) {
            this._includeAs = r3;
            return this;
        }
        throw new IllegalArgumentException("includeAs can not be null");
    }

    /* Return type fixed from 'X.0o5' to match base method */
    @Override // X.AnonymousClass0o5
    public final /* bridge */ /* synthetic */ StdTypeResolverBuilder A5B(EnumC05780lJ r3, AnonymousClass0o4 r4) {
        if (r3 != null) {
            this._idType = r3;
            this._customIdResolver = r4;
            this._typeProperty = r3.getDefaultPropertyName();
            return this;
        }
        throw new IllegalArgumentException("idType can not be null");
    }

    /* Return type fixed from 'X.0o5' to match base method */
    @Override // X.AnonymousClass0o5
    public final StdTypeResolverBuilder A8e(String str) {
        if (str == null || str.length() == 0) {
            str = this._idType.getDefaultPropertyName();
        }
        this._typeProperty = str;
        return this;
    }

    /* Return type fixed from 'X.0o5' to match base method */
    @Override // X.AnonymousClass0o5
    public final StdTypeResolverBuilder A25(Class cls) {
        this._defaultImpl = cls;
        return this;
    }

    /* Return type fixed from 'X.0o5' to match base method */
    @Override // X.AnonymousClass0o5
    public final StdTypeResolverBuilder A8d(boolean z) {
        this._typeIdVisible = z;
        return this;
    }
}
