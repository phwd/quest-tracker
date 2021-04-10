package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.util.LRUMap;
import java.io.Serializable;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class TypeFactory implements Serializable {
    protected static final SimpleType CORE_TYPE_BOOL = new SimpleType(Boolean.TYPE);
    protected static final SimpleType CORE_TYPE_INT = new SimpleType(Integer.TYPE);
    protected static final SimpleType CORE_TYPE_LONG = new SimpleType(Long.TYPE);
    protected static final SimpleType CORE_TYPE_STRING = new SimpleType(String.class);
    private static final JavaType[] NO_TYPES = new JavaType[0];
    protected static final TypeFactory instance = new TypeFactory();
    private static final long serialVersionUID = 1;
    protected transient HierarchicType _cachedArrayListType;
    protected transient HierarchicType _cachedHashMapType;
    protected final TypeModifier[] _modifiers = null;
    protected final TypeParser _parser = new TypeParser(this);
    protected final LRUMap<ClassKey, JavaType> _typeCache = new LRUMap<>(16, 100);

    private TypeFactory() {
    }

    public static TypeFactory defaultInstance() {
        return instance;
    }

    public JavaType constructFromCanonical(String str) throws IllegalArgumentException {
        return this._parser.parse(str);
    }

    public JavaType[] findTypeParameters(JavaType javaType, Class<?> cls) {
        Class<?> rawClass = javaType.getRawClass();
        if (rawClass != cls) {
            return findTypeParameters(rawClass, cls, new TypeBindings(this, javaType));
        }
        int containedTypeCount = javaType.containedTypeCount();
        if (containedTypeCount == 0) {
            return null;
        }
        JavaType[] javaTypeArr = new JavaType[containedTypeCount];
        for (int i = 0; i < containedTypeCount; i++) {
            javaTypeArr[i] = javaType.containedType(i);
        }
        return javaTypeArr;
    }

    public JavaType[] findTypeParameters(Class<?> cls, Class<?> cls2) {
        return findTypeParameters(cls, cls2, new TypeBindings(this, cls));
    }

    public JavaType[] findTypeParameters(Class<?> cls, Class<?> cls2, TypeBindings typeBindings) {
        HierarchicType _findSuperTypeChain = _findSuperTypeChain(cls, cls2);
        if (_findSuperTypeChain != null) {
            while (_findSuperTypeChain.getSuperType() != null) {
                _findSuperTypeChain = _findSuperTypeChain.getSuperType();
                Class<?> rawClass = _findSuperTypeChain.getRawClass();
                TypeBindings typeBindings2 = new TypeBindings(this, rawClass);
                if (_findSuperTypeChain.isGeneric()) {
                    Type[] actualTypeArguments = _findSuperTypeChain.asGeneric().getActualTypeArguments();
                    TypeVariable<Class<?>>[] typeParameters = rawClass.getTypeParameters();
                    int length = actualTypeArguments.length;
                    for (int i = 0; i < length; i++) {
                        typeBindings2.addBinding(typeParameters[i].getName(), _constructType(actualTypeArguments[i], typeBindings));
                    }
                }
                typeBindings = typeBindings2;
            }
            if (!_findSuperTypeChain.isGeneric()) {
                return null;
            }
            return typeBindings.typesAsArray();
        }
        throw new IllegalArgumentException("Class " + cls.getName() + " is not a subtype of " + cls2.getName());
    }

    public JavaType constructType(Type type) {
        return _constructType(type, null);
    }

    public JavaType constructType(Type type, TypeBindings typeBindings) {
        return _constructType(type, typeBindings);
    }

    /* access modifiers changed from: protected */
    public JavaType _constructType(Type type, TypeBindings typeBindings) {
        JavaType javaType;
        if (type instanceof Class) {
            javaType = _fromClass((Class) type, typeBindings);
        } else if (type instanceof ParameterizedType) {
            javaType = _fromParamType((ParameterizedType) type, typeBindings);
        } else if (type instanceof JavaType) {
            return (JavaType) type;
        } else {
            if (type instanceof GenericArrayType) {
                javaType = _fromArrayType((GenericArrayType) type, typeBindings);
            } else if (type instanceof TypeVariable) {
                javaType = _fromVariable((TypeVariable) type, typeBindings);
            } else if (type instanceof WildcardType) {
                javaType = _fromWildcard((WildcardType) type, typeBindings);
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("Unrecognized Type: ");
                sb.append(type == null ? "[null]" : type.toString());
                throw new IllegalArgumentException(sb.toString());
            }
        }
        if (this._modifiers != null && !javaType.isContainerType()) {
            for (TypeModifier typeModifier : this._modifiers) {
                javaType = typeModifier.modifyType(javaType, type, typeBindings, this);
            }
        }
        return javaType;
    }

    public JavaType constructSimpleType(Class<?> cls, JavaType[] javaTypeArr) {
        TypeVariable<Class<?>>[] typeParameters = cls.getTypeParameters();
        if (typeParameters.length == javaTypeArr.length) {
            String[] strArr = new String[typeParameters.length];
            int length = typeParameters.length;
            for (int i = 0; i < length; i++) {
                strArr[i] = typeParameters[i].getName();
            }
            return new SimpleType(cls, strArr, javaTypeArr, null, null, false);
        }
        throw new IllegalArgumentException("Parameter type mismatch for " + cls.getName() + ": expected " + typeParameters.length + " parameters, was given " + javaTypeArr.length);
    }

    /* access modifiers changed from: protected */
    public JavaType _fromClass(Class<?> cls, TypeBindings typeBindings) {
        JavaType javaType;
        JavaType javaType2;
        SimpleType simpleType;
        if (cls == String.class) {
            return CORE_TYPE_STRING;
        }
        if (cls == Boolean.TYPE) {
            return CORE_TYPE_BOOL;
        }
        if (cls == Integer.TYPE) {
            return CORE_TYPE_INT;
        }
        if (cls == Long.TYPE) {
            return CORE_TYPE_LONG;
        }
        ClassKey classKey = new ClassKey(cls);
        synchronized (this._typeCache) {
            javaType = this._typeCache.get(classKey);
        }
        if (javaType != null) {
            return javaType;
        }
        if (cls.isArray()) {
            javaType2 = ArrayType.construct(_constructType(cls.getComponentType(), null), null, null);
        } else {
            if (cls.isEnum()) {
                simpleType = new SimpleType(cls);
            } else if (Map.class.isAssignableFrom(cls)) {
                javaType2 = _mapType(cls);
            } else if (Collection.class.isAssignableFrom(cls)) {
                javaType2 = _collectionType(cls);
            } else {
                simpleType = new SimpleType(cls);
            }
            javaType2 = simpleType;
        }
        synchronized (this._typeCache) {
            this._typeCache.put(classKey, javaType2);
        }
        return javaType2;
    }

    /* access modifiers changed from: protected */
    public JavaType _fromParameterizedClass(Class<?> cls, List<JavaType> list) {
        if (cls.isArray()) {
            return ArrayType.construct(_constructType(cls.getComponentType(), null), null, null);
        }
        if (cls.isEnum()) {
            return new SimpleType(cls);
        }
        if (Map.class.isAssignableFrom(cls)) {
            if (list.size() <= 0) {
                return _mapType(cls);
            }
            return MapType.construct(cls, list.get(0), list.size() >= 2 ? list.get(1) : _unknownType());
        } else if (Collection.class.isAssignableFrom(cls)) {
            if (list.size() >= 1) {
                return CollectionType.construct(cls, list.get(0));
            }
            return _collectionType(cls);
        } else if (list.size() == 0) {
            return new SimpleType(cls);
        } else {
            return constructSimpleType(cls, (JavaType[]) list.toArray(new JavaType[list.size()]));
        }
    }

    /* access modifiers changed from: protected */
    public JavaType _fromParamType(ParameterizedType parameterizedType, TypeBindings typeBindings) {
        int i;
        JavaType[] javaTypeArr;
        Class<?> cls = (Class) parameterizedType.getRawType();
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        if (actualTypeArguments == null) {
            i = 0;
        } else {
            i = actualTypeArguments.length;
        }
        if (i == 0) {
            javaTypeArr = NO_TYPES;
        } else {
            JavaType[] javaTypeArr2 = new JavaType[i];
            for (int i2 = 0; i2 < i; i2++) {
                javaTypeArr2[i2] = _constructType(actualTypeArguments[i2], typeBindings);
            }
            javaTypeArr = javaTypeArr2;
        }
        if (Map.class.isAssignableFrom(cls)) {
            JavaType[] findTypeParameters = findTypeParameters(constructSimpleType(cls, javaTypeArr), Map.class);
            if (findTypeParameters.length == 2) {
                return MapType.construct(cls, findTypeParameters[0], findTypeParameters[1]);
            }
            throw new IllegalArgumentException("Could not find 2 type parameters for Map class " + cls.getName() + " (found " + findTypeParameters.length + ")");
        } else if (Collection.class.isAssignableFrom(cls)) {
            JavaType[] findTypeParameters2 = findTypeParameters(constructSimpleType(cls, javaTypeArr), Collection.class);
            if (findTypeParameters2.length == 1) {
                return CollectionType.construct(cls, findTypeParameters2[0]);
            }
            throw new IllegalArgumentException("Could not find 1 type parameter for Collection class " + cls.getName() + " (found " + findTypeParameters2.length + ")");
        } else if (i == 0) {
            return new SimpleType(cls);
        } else {
            return constructSimpleType(cls, javaTypeArr);
        }
    }

    /* access modifiers changed from: protected */
    public JavaType _fromArrayType(GenericArrayType genericArrayType, TypeBindings typeBindings) {
        return ArrayType.construct(_constructType(genericArrayType.getGenericComponentType(), typeBindings), null, null);
    }

    /* access modifiers changed from: protected */
    public JavaType _fromVariable(TypeVariable<?> typeVariable, TypeBindings typeBindings) {
        if (typeBindings == null) {
            return _unknownType();
        }
        String name = typeVariable.getName();
        JavaType findType = typeBindings.findType(name);
        if (findType != null) {
            return findType;
        }
        Type[] bounds = typeVariable.getBounds();
        typeBindings._addPlaceholder(name);
        return _constructType(bounds[0], typeBindings);
    }

    /* access modifiers changed from: protected */
    public JavaType _fromWildcard(WildcardType wildcardType, TypeBindings typeBindings) {
        return _constructType(wildcardType.getUpperBounds()[0], typeBindings);
    }

    private JavaType _mapType(Class<?> cls) {
        JavaType[] findTypeParameters = findTypeParameters(cls, Map.class);
        if (findTypeParameters == null) {
            return MapType.construct(cls, _unknownType(), _unknownType());
        }
        if (findTypeParameters.length == 2) {
            return MapType.construct(cls, findTypeParameters[0], findTypeParameters[1]);
        }
        throw new IllegalArgumentException("Strange Map type " + cls.getName() + ": can not determine type parameters");
    }

    private JavaType _collectionType(Class<?> cls) {
        JavaType[] findTypeParameters = findTypeParameters(cls, Collection.class);
        if (findTypeParameters == null) {
            return CollectionType.construct(cls, _unknownType());
        }
        if (findTypeParameters.length == 1) {
            return CollectionType.construct(cls, findTypeParameters[0]);
        }
        throw new IllegalArgumentException("Strange Collection type " + cls.getName() + ": can not determine type parameters");
    }

    /* access modifiers changed from: protected */
    public JavaType _unknownType() {
        return new SimpleType(Object.class);
    }

    /* access modifiers changed from: protected */
    public HierarchicType _findSuperTypeChain(Class<?> cls, Class<?> cls2) {
        if (cls2.isInterface()) {
            return _findSuperInterfaceChain(cls, cls2);
        }
        return _findSuperClassChain(cls, cls2);
    }

    /* access modifiers changed from: protected */
    public HierarchicType _findSuperClassChain(Type type, Class<?> cls) {
        HierarchicType _findSuperClassChain;
        HierarchicType hierarchicType = new HierarchicType(type);
        Class<?> rawClass = hierarchicType.getRawClass();
        if (rawClass == cls) {
            return hierarchicType;
        }
        Type genericSuperclass = rawClass.getGenericSuperclass();
        if (genericSuperclass == null || (_findSuperClassChain = _findSuperClassChain(genericSuperclass, cls)) == null) {
            return null;
        }
        _findSuperClassChain.setSubType(hierarchicType);
        hierarchicType.setSuperType(_findSuperClassChain);
        return hierarchicType;
    }

    /* access modifiers changed from: protected */
    public HierarchicType _findSuperInterfaceChain(Type type, Class<?> cls) {
        HierarchicType hierarchicType = new HierarchicType(type);
        Class<?> rawClass = hierarchicType.getRawClass();
        if (rawClass == cls) {
            return new HierarchicType(type);
        }
        if (rawClass == HashMap.class && cls == Map.class) {
            return _hashMapSuperInterfaceChain(hierarchicType);
        }
        if (rawClass == ArrayList.class && cls == List.class) {
            return _arrayListSuperInterfaceChain(hierarchicType);
        }
        return _doFindSuperInterfaceChain(hierarchicType, cls);
    }

    /* access modifiers changed from: protected */
    public HierarchicType _doFindSuperInterfaceChain(HierarchicType hierarchicType, Class<?> cls) {
        HierarchicType _findSuperInterfaceChain;
        Class<?> rawClass = hierarchicType.getRawClass();
        Type[] genericInterfaces = rawClass.getGenericInterfaces();
        if (genericInterfaces != null) {
            for (Type type : genericInterfaces) {
                HierarchicType _findSuperInterfaceChain2 = _findSuperInterfaceChain(type, cls);
                if (_findSuperInterfaceChain2 != null) {
                    _findSuperInterfaceChain2.setSubType(hierarchicType);
                    hierarchicType.setSuperType(_findSuperInterfaceChain2);
                    return hierarchicType;
                }
            }
        }
        Type genericSuperclass = rawClass.getGenericSuperclass();
        if (genericSuperclass == null || (_findSuperInterfaceChain = _findSuperInterfaceChain(genericSuperclass, cls)) == null) {
            return null;
        }
        _findSuperInterfaceChain.setSubType(hierarchicType);
        hierarchicType.setSuperType(_findSuperInterfaceChain);
        return hierarchicType;
    }

    /* access modifiers changed from: protected */
    public synchronized HierarchicType _hashMapSuperInterfaceChain(HierarchicType hierarchicType) {
        if (this._cachedHashMapType == null) {
            HierarchicType deepCloneWithoutSubtype = hierarchicType.deepCloneWithoutSubtype();
            _doFindSuperInterfaceChain(deepCloneWithoutSubtype, Map.class);
            this._cachedHashMapType = deepCloneWithoutSubtype.getSuperType();
        }
        HierarchicType deepCloneWithoutSubtype2 = this._cachedHashMapType.deepCloneWithoutSubtype();
        hierarchicType.setSuperType(deepCloneWithoutSubtype2);
        deepCloneWithoutSubtype2.setSubType(hierarchicType);
        return hierarchicType;
    }

    /* access modifiers changed from: protected */
    public synchronized HierarchicType _arrayListSuperInterfaceChain(HierarchicType hierarchicType) {
        if (this._cachedArrayListType == null) {
            HierarchicType deepCloneWithoutSubtype = hierarchicType.deepCloneWithoutSubtype();
            _doFindSuperInterfaceChain(deepCloneWithoutSubtype, List.class);
            this._cachedArrayListType = deepCloneWithoutSubtype.getSuperType();
        }
        HierarchicType deepCloneWithoutSubtype2 = this._cachedArrayListType.deepCloneWithoutSubtype();
        hierarchicType.setSuperType(deepCloneWithoutSubtype2);
        deepCloneWithoutSubtype2.setSubType(hierarchicType);
        return hierarchicType;
    }
}
