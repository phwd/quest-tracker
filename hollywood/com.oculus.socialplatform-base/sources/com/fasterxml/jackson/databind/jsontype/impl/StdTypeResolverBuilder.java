package com.fasterxml.jackson.databind.jsontype.impl;

import X.AbstractC02110i2;
import X.AbstractC02190iF;
import X.AbstractC04520qa;
import X.AbstractC04530qb;
import X.AbstractC04540qc;
import X.AbstractC04550qd;
import X.AnonymousClass0Cl;
import X.AnonymousClass0Cm;
import X.AnonymousClass0Cn;
import X.AnonymousClass0Cp;
import X.AnonymousClass0HM;
import X.AnonymousClass0HU;
import X.AnonymousClass0Ol;
import X.AnonymousClass0On;
import X.AnonymousClass0Oo;
import X.AnonymousClass0Op;
import X.AnonymousClass0Oq;
import X.AnonymousClass0Or;
import X.AnonymousClass0Os;
import X.AnonymousClass0qX;
import X.EnumC03570nq;
import X.EnumC03580nr;
import java.util.Collection;
import java.util.HashMap;

public class StdTypeResolverBuilder implements AbstractC04540qc<StdTypeResolverBuilder> {
    public AbstractC04530qb _customIdResolver;
    public Class<?> _defaultImpl;
    public EnumC03580nr _idType;
    public EnumC03570nq _includeAs;
    public boolean _typeIdVisible = false;
    public String _typeProperty;

    @Override // X.AbstractC04540qc
    public final AbstractC04520qa A1s(AnonymousClass0HU r11, AbstractC02190iF r12, Collection<AnonymousClass0qX> collection) {
        if (this._idType == EnumC03580nr.NONE) {
            return null;
        }
        AbstractC04530qb A00 = A00(r11, r12, collection, false, true);
        EnumC03570nq r2 = this._includeAs;
        switch (r2.ordinal()) {
            case 0:
                return new AnonymousClass0Cn(r12, A00, this._typeProperty, this._typeIdVisible, this._defaultImpl);
            case 1:
                return new AnonymousClass0Op(r12, A00, this._typeProperty, this._typeIdVisible);
            case 2:
                return new AnonymousClass0Os(r12, A00, this._typeProperty, this._typeIdVisible, this._defaultImpl);
            case 3:
                return new AnonymousClass0Cp(r12, A00, this._typeProperty, this._typeIdVisible, this._defaultImpl);
            default:
                StringBuilder sb = new StringBuilder("Do not know how to construct standard type serializer for inclusion type: ");
                sb.append(r2);
                throw new IllegalStateException(sb.toString());
        }
    }

    @Override // X.AbstractC04540qc
    public final AbstractC04550qd A1t(AnonymousClass0HM r12, AbstractC02190iF r13, Collection<AnonymousClass0qX> collection) {
        if (this._idType == EnumC03580nr.NONE) {
            return null;
        }
        AbstractC04530qb A00 = A00(r12, r13, collection, true, false);
        EnumC03570nq r2 = this._includeAs;
        switch (r2.ordinal()) {
            case 0:
                return new AnonymousClass0Cm(A00, null, this._typeProperty);
            case 1:
                return new AnonymousClass0Oo(A00, null);
            case 2:
                return new AnonymousClass0Or(A00, null);
            case 3:
                return new AnonymousClass0Oq(A00, null, this._typeProperty);
            default:
                StringBuilder sb = new StringBuilder("Do not know how to construct standard type serializer for inclusion type: ");
                sb.append(r2);
                throw new IllegalStateException(sb.toString());
        }
    }

    private final AbstractC04530qb A00(AbstractC02110i2<?> r7, AbstractC02190iF r8, Collection<AnonymousClass0qX> collection, boolean z, boolean z2) {
        int lastIndexOf;
        AbstractC02190iF r0;
        AbstractC04530qb r02 = this._customIdResolver;
        if (r02 != null) {
            return r02;
        }
        EnumC03580nr r2 = this._idType;
        if (r2 != null) {
            switch (r2.ordinal()) {
                case 0:
                    return null;
                case 1:
                    return new AnonymousClass0On(r8, r7._base._typeFactory);
                case 2:
                    return new AnonymousClass0Cl(r8, r7._base._typeFactory);
                case 3:
                    if (z != z2) {
                        HashMap hashMap = null;
                        HashMap hashMap2 = z ? new HashMap() : null;
                        if (z2) {
                            hashMap = new HashMap();
                        }
                        if (collection != null) {
                            for (AnonymousClass0qX r03 : collection) {
                                Class<?> cls = r03._class;
                                String str = r03._name;
                                if (str == null && (lastIndexOf = (str = cls.getName()).lastIndexOf(46)) >= 0) {
                                    str = str.substring(lastIndexOf + 1);
                                }
                                if (z) {
                                    hashMap2.put(cls.getName(), str);
                                }
                                if (z2 && ((r0 = (AbstractC02190iF) hashMap.get(str)) == null || !cls.isAssignableFrom(r0._class))) {
                                    hashMap.put(str, r7.A03(cls));
                                }
                            }
                        }
                        return new AnonymousClass0Ol(r7, r8, hashMap2, hashMap);
                    }
                    throw new IllegalArgumentException();
                default:
                    StringBuilder sb = new StringBuilder("Do not know how to construct standard type id resolver for idType: ");
                    sb.append(r2);
                    throw new IllegalStateException(sb.toString());
            }
        } else {
            throw new IllegalStateException("Can not build, 'init()' not yet called");
        }
    }

    /* Return type fixed from 'X.0qc' to match base method */
    @Override // X.AbstractC04540qc
    public final StdTypeResolverBuilder A5Z(EnumC03570nq r3) {
        if (r3 != null) {
            this._includeAs = r3;
            return this;
        }
        throw new IllegalArgumentException("includeAs can not be null");
    }

    /* Return type fixed from 'X.0qc' to match base method */
    @Override // X.AbstractC04540qc
    public final /* bridge */ /* synthetic */ StdTypeResolverBuilder A5b(EnumC03580nr r3, AbstractC04530qb r4) {
        if (r3 != null) {
            this._idType = r3;
            this._customIdResolver = r4;
            this._typeProperty = r3.getDefaultPropertyName();
            return this;
        }
        throw new IllegalArgumentException("idType can not be null");
    }

    /* Return type fixed from 'X.0qc' to match base method */
    @Override // X.AbstractC04540qc
    public final StdTypeResolverBuilder AAp(String str) {
        if (str == null || str.length() == 0) {
            str = this._idType.getDefaultPropertyName();
        }
        this._typeProperty = str;
        return this;
    }

    @Override // X.AbstractC04540qc
    public final Class<?> A3l() {
        return this._defaultImpl;
    }

    /* Return type fixed from 'X.0qc' to match base method */
    @Override // X.AbstractC04540qc
    public final StdTypeResolverBuilder A2Y(Class cls) {
        this._defaultImpl = cls;
        return this;
    }

    /* Return type fixed from 'X.0qc' to match base method */
    @Override // X.AbstractC04540qc
    public final StdTypeResolverBuilder AAo(boolean z) {
        this._typeIdVisible = z;
        return this;
    }
}
