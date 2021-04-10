package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.JavaType;

public final class SimpleType extends TypeBase {
    private static final long serialVersionUID = -800374828948534376L;
    protected final String[] _typeNames;
    protected final JavaType[] _typeParameters;

    @Override // com.fasterxml.jackson.databind.JavaType
    public boolean isContainerType() {
        return false;
    }

    protected SimpleType(Class<?> cls) {
        this(cls, null, null, null, null, false);
    }

    protected SimpleType(Class<?> cls, String[] strArr, JavaType[] javaTypeArr, Object obj, Object obj2, boolean z) {
        super(cls, 0, obj, obj2, z);
        if (strArr == null || strArr.length == 0) {
            this._typeNames = null;
            this._typeParameters = null;
            return;
        }
        this._typeNames = strArr;
        this._typeParameters = javaTypeArr;
    }

    /* access modifiers changed from: protected */
    @Override // com.fasterxml.jackson.databind.JavaType
    public JavaType _narrow(Class<?> cls) {
        return new SimpleType(cls, this._typeNames, this._typeParameters, this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    public JavaType narrowContentsBy(Class<?> cls) {
        throw new IllegalArgumentException("Internal error: SimpleType.narrowContentsBy() should never be called");
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    public SimpleType withTypeHandler(Object obj) {
        return new SimpleType(this._class, this._typeNames, this._typeParameters, this._valueHandler, obj, this._asStatic);
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    public SimpleType withValueHandler(Object obj) {
        if (obj == this._valueHandler) {
            return this;
        }
        return new SimpleType(this._class, this._typeNames, this._typeParameters, obj, this._typeHandler, this._asStatic);
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    public SimpleType withContentValueHandler(Object obj) {
        throw new IllegalArgumentException("Simple types have no content types; can not call withContenValueHandler()");
    }

    /* access modifiers changed from: protected */
    @Override // com.fasterxml.jackson.databind.type.TypeBase
    public String buildCanonicalName() {
        StringBuilder sb = new StringBuilder();
        sb.append(this._class.getName());
        JavaType[] javaTypeArr = this._typeParameters;
        if (javaTypeArr != null && javaTypeArr.length > 0) {
            sb.append('<');
            JavaType[] javaTypeArr2 = this._typeParameters;
            boolean z = true;
            for (JavaType javaType : javaTypeArr2) {
                if (z) {
                    z = false;
                } else {
                    sb.append(',');
                }
                sb.append(javaType.toCanonical());
            }
            sb.append('>');
        }
        return sb.toString();
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    public int containedTypeCount() {
        JavaType[] javaTypeArr = this._typeParameters;
        if (javaTypeArr == null) {
            return 0;
        }
        return javaTypeArr.length;
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    public JavaType containedType(int i) {
        JavaType[] javaTypeArr;
        if (i < 0 || (javaTypeArr = this._typeParameters) == null || i >= javaTypeArr.length) {
            return null;
        }
        return javaTypeArr[i];
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    public String containedTypeName(int i) {
        String[] strArr;
        if (i < 0 || (strArr = this._typeNames) == null || i >= strArr.length) {
            return null;
        }
        return strArr[i];
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    public String toString() {
        StringBuilder sb = new StringBuilder(40);
        sb.append("[simple type, class ");
        sb.append(buildCanonicalName());
        sb.append(']');
        return sb.toString();
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        SimpleType simpleType = (SimpleType) obj;
        if (simpleType._class != this._class) {
            return false;
        }
        JavaType[] javaTypeArr = this._typeParameters;
        JavaType[] javaTypeArr2 = simpleType._typeParameters;
        if (javaTypeArr == null) {
            if (javaTypeArr2 == null || javaTypeArr2.length == 0) {
                return true;
            }
            return false;
        } else if (javaTypeArr2 == null || javaTypeArr.length != javaTypeArr2.length) {
            return false;
        } else {
            int length = javaTypeArr.length;
            for (int i = 0; i < length; i++) {
                if (!javaTypeArr[i].equals(javaTypeArr2[i])) {
                    return false;
                }
            }
            return true;
        }
    }
}
