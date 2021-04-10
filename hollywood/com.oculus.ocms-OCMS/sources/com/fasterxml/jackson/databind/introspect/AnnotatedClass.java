package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.introspect.ClassIntrospector;
import com.fasterxml.jackson.databind.util.Annotations;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public final class AnnotatedClass extends Annotated {
    private static final AnnotationMap[] NO_ANNOTATION_MAPS = new AnnotationMap[0];
    protected final AnnotationIntrospector _annotationIntrospector;
    protected final Class<?> _class;
    protected AnnotationMap _classAnnotations;
    protected List<AnnotatedConstructor> _constructors;
    protected List<AnnotatedMethod> _creatorMethods;
    protected boolean _creatorsResolved = false;
    protected AnnotatedConstructor _defaultConstructor;
    protected List<AnnotatedField> _fields;
    protected AnnotatedMethodMap _memberMethods;
    protected final ClassIntrospector.MixInResolver _mixInResolver;
    protected final Class<?> _primaryMixIn;
    protected final List<Class<?>> _superTypes;

    private AnnotatedClass(Class<?> cls, List<Class<?>> list, AnnotationIntrospector annotationIntrospector, ClassIntrospector.MixInResolver mixInResolver, AnnotationMap annotationMap) {
        Class<?> cls2;
        this._class = cls;
        this._superTypes = list;
        this._annotationIntrospector = annotationIntrospector;
        this._mixInResolver = mixInResolver;
        ClassIntrospector.MixInResolver mixInResolver2 = this._mixInResolver;
        if (mixInResolver2 == null) {
            cls2 = null;
        } else {
            cls2 = mixInResolver2.findMixInClassFor(this._class);
        }
        this._primaryMixIn = cls2;
        this._classAnnotations = annotationMap;
    }

    @Override // com.fasterxml.jackson.databind.introspect.Annotated
    public AnnotatedClass withAnnotations(AnnotationMap annotationMap) {
        return new AnnotatedClass(this._class, this._superTypes, this._annotationIntrospector, this._mixInResolver, annotationMap);
    }

    public static AnnotatedClass construct(Class<?> cls, AnnotationIntrospector annotationIntrospector, ClassIntrospector.MixInResolver mixInResolver) {
        return new AnnotatedClass(cls, ClassUtil.findSuperTypes(cls, null), annotationIntrospector, mixInResolver, null);
    }

    public static AnnotatedClass constructWithoutSuperTypes(Class<?> cls, AnnotationIntrospector annotationIntrospector, ClassIntrospector.MixInResolver mixInResolver) {
        return new AnnotatedClass(cls, Collections.emptyList(), annotationIntrospector, mixInResolver, null);
    }

    @Override // com.fasterxml.jackson.databind.introspect.Annotated
    public Class<?> getAnnotated() {
        return this._class;
    }

    @Override // com.fasterxml.jackson.databind.introspect.Annotated
    public int getModifiers() {
        return this._class.getModifiers();
    }

    @Override // com.fasterxml.jackson.databind.introspect.Annotated
    public String getName() {
        return this._class.getName();
    }

    @Override // com.fasterxml.jackson.databind.introspect.Annotated
    public <A extends Annotation> A getAnnotation(Class<A> cls) {
        if (this._classAnnotations == null) {
            resolveClassAnnotations();
        }
        return (A) this._classAnnotations.get(cls);
    }

    @Override // com.fasterxml.jackson.databind.introspect.Annotated
    public Type getGenericType() {
        return this._class;
    }

    @Override // com.fasterxml.jackson.databind.introspect.Annotated
    public Class<?> getRawType() {
        return this._class;
    }

    /* access modifiers changed from: protected */
    @Override // com.fasterxml.jackson.databind.introspect.Annotated
    public AnnotationMap getAllAnnotations() {
        if (this._classAnnotations == null) {
            resolveClassAnnotations();
        }
        return this._classAnnotations;
    }

    public Annotations getAnnotations() {
        if (this._classAnnotations == null) {
            resolveClassAnnotations();
        }
        return this._classAnnotations;
    }

    public boolean hasAnnotations() {
        if (this._classAnnotations == null) {
            resolveClassAnnotations();
        }
        return this._classAnnotations.size() > 0;
    }

    public AnnotatedConstructor getDefaultConstructor() {
        if (!this._creatorsResolved) {
            resolveCreators();
        }
        return this._defaultConstructor;
    }

    public List<AnnotatedConstructor> getConstructors() {
        if (!this._creatorsResolved) {
            resolveCreators();
        }
        return this._constructors;
    }

    public List<AnnotatedMethod> getStaticMethods() {
        if (!this._creatorsResolved) {
            resolveCreators();
        }
        return this._creatorMethods;
    }

    public Iterable<AnnotatedMethod> memberMethods() {
        if (this._memberMethods == null) {
            resolveMemberMethods();
        }
        return this._memberMethods;
    }

    public int getMemberMethodCount() {
        if (this._memberMethods == null) {
            resolveMemberMethods();
        }
        return this._memberMethods.size();
    }

    public AnnotatedMethod findMethod(String str, Class<?>[] clsArr) {
        if (this._memberMethods == null) {
            resolveMemberMethods();
        }
        return this._memberMethods.find(str, clsArr);
    }

    public int getFieldCount() {
        if (this._fields == null) {
            resolveFields();
        }
        return this._fields.size();
    }

    public Iterable<AnnotatedField> fields() {
        if (this._fields == null) {
            resolveFields();
        }
        return this._fields;
    }

    private void resolveClassAnnotations() {
        this._classAnnotations = new AnnotationMap();
        if (this._annotationIntrospector != null) {
            Class<?> cls = this._primaryMixIn;
            if (cls != null) {
                _addClassMixIns(this._classAnnotations, this._class, cls);
            }
            _addAnnotationsIfNotPresent(this._classAnnotations, this._class.getDeclaredAnnotations());
            for (Class<?> cls2 : this._superTypes) {
                _addClassMixIns(this._classAnnotations, cls2);
                _addAnnotationsIfNotPresent(this._classAnnotations, cls2.getDeclaredAnnotations());
            }
            _addClassMixIns(this._classAnnotations, Object.class);
        }
    }

    private void resolveCreators() {
        Constructor<?>[] declaredConstructors = this._class.getDeclaredConstructors();
        ArrayList arrayList = null;
        ArrayList arrayList2 = null;
        for (Constructor<?> constructor : declaredConstructors) {
            if (constructor.getParameterTypes().length == 0) {
                this._defaultConstructor = _constructConstructor(constructor, true);
            } else {
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList(Math.max(10, declaredConstructors.length));
                }
                arrayList2.add(_constructConstructor(constructor, false));
            }
        }
        if (arrayList2 == null) {
            this._constructors = Collections.emptyList();
        } else {
            this._constructors = arrayList2;
        }
        if (this._primaryMixIn != null && (this._defaultConstructor != null || !this._constructors.isEmpty())) {
            _addConstructorMixIns(this._primaryMixIn);
        }
        AnnotationIntrospector annotationIntrospector = this._annotationIntrospector;
        if (annotationIntrospector != null) {
            AnnotatedConstructor annotatedConstructor = this._defaultConstructor;
            if (annotatedConstructor != null && annotationIntrospector.hasIgnoreMarker(annotatedConstructor)) {
                this._defaultConstructor = null;
            }
            List<AnnotatedConstructor> list = this._constructors;
            if (list != null) {
                int size = list.size();
                while (true) {
                    size--;
                    if (size < 0) {
                        break;
                    } else if (this._annotationIntrospector.hasIgnoreMarker(this._constructors.get(size))) {
                        this._constructors.remove(size);
                    }
                }
            }
        }
        Method[] declaredMethods = this._class.getDeclaredMethods();
        for (Method method : declaredMethods) {
            if (Modifier.isStatic(method.getModifiers())) {
                if (arrayList == null) {
                    arrayList = new ArrayList(8);
                }
                arrayList.add(_constructCreatorMethod(method));
            }
        }
        if (arrayList == null) {
            this._creatorMethods = Collections.emptyList();
        } else {
            this._creatorMethods = arrayList;
            Class<?> cls = this._primaryMixIn;
            if (cls != null) {
                _addFactoryMixIns(cls);
            }
            if (this._annotationIntrospector != null) {
                int size2 = this._creatorMethods.size();
                while (true) {
                    size2--;
                    if (size2 < 0) {
                        break;
                    } else if (this._annotationIntrospector.hasIgnoreMarker(this._creatorMethods.get(size2))) {
                        this._creatorMethods.remove(size2);
                    }
                }
            }
        }
        this._creatorsResolved = true;
    }

    private void resolveMemberMethods() {
        Class<?> findMixInClassFor;
        this._memberMethods = new AnnotatedMethodMap();
        AnnotatedMethodMap annotatedMethodMap = new AnnotatedMethodMap();
        _addMemberMethods(this._class, this._memberMethods, this._primaryMixIn, annotatedMethodMap);
        for (Class<?> cls : this._superTypes) {
            ClassIntrospector.MixInResolver mixInResolver = this._mixInResolver;
            _addMemberMethods(cls, this._memberMethods, mixInResolver == null ? null : mixInResolver.findMixInClassFor(cls), annotatedMethodMap);
        }
        ClassIntrospector.MixInResolver mixInResolver2 = this._mixInResolver;
        if (!(mixInResolver2 == null || (findMixInClassFor = mixInResolver2.findMixInClassFor(Object.class)) == null)) {
            _addMethodMixIns(this._class, this._memberMethods, findMixInClassFor, annotatedMethodMap);
        }
        if (!(this._annotationIntrospector == null || annotatedMethodMap.isEmpty())) {
            Iterator<AnnotatedMethod> it = annotatedMethodMap.iterator();
            while (it.hasNext()) {
                AnnotatedMethod next = it.next();
                try {
                    Method declaredMethod = Object.class.getDeclaredMethod(next.getName(), next.getRawParameterTypes());
                    if (declaredMethod != null) {
                        AnnotatedMethod _constructMethod = _constructMethod(declaredMethod);
                        _addMixOvers(next.getAnnotated(), _constructMethod, false);
                        this._memberMethods.add(_constructMethod);
                    }
                } catch (Exception unused) {
                }
            }
        }
    }

    private void resolveFields() {
        Map<String, AnnotatedField> _findFields = _findFields(this._class, null);
        if (_findFields == null || _findFields.size() == 0) {
            this._fields = Collections.emptyList();
            return;
        }
        this._fields = new ArrayList(_findFields.size());
        this._fields.addAll(_findFields.values());
    }

    /* access modifiers changed from: protected */
    public void _addClassMixIns(AnnotationMap annotationMap, Class<?> cls) {
        ClassIntrospector.MixInResolver mixInResolver = this._mixInResolver;
        if (mixInResolver != null) {
            _addClassMixIns(annotationMap, cls, mixInResolver.findMixInClassFor(cls));
        }
    }

    /* access modifiers changed from: protected */
    public void _addClassMixIns(AnnotationMap annotationMap, Class<?> cls, Class<?> cls2) {
        if (cls2 != null) {
            _addAnnotationsIfNotPresent(annotationMap, cls2.getDeclaredAnnotations());
            for (Class<?> cls3 : ClassUtil.findSuperTypes(cls2, cls)) {
                _addAnnotationsIfNotPresent(annotationMap, cls3.getDeclaredAnnotations());
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _addConstructorMixIns(Class<?> cls) {
        List<AnnotatedConstructor> list = this._constructors;
        int size = list == null ? 0 : list.size();
        Constructor<?>[] declaredConstructors = cls.getDeclaredConstructors();
        MemberKey[] memberKeyArr = null;
        for (Constructor<?> constructor : declaredConstructors) {
            if (constructor.getParameterTypes().length != 0) {
                if (memberKeyArr == null) {
                    memberKeyArr = new MemberKey[size];
                    for (int i = 0; i < size; i++) {
                        memberKeyArr[i] = new MemberKey(this._constructors.get(i).getAnnotated());
                    }
                }
                MemberKey memberKey = new MemberKey(constructor);
                int i2 = 0;
                while (true) {
                    if (i2 >= size) {
                        break;
                    }
                    if (memberKey.equals(memberKeyArr[i2])) {
                        _addMixOvers(constructor, this._constructors.get(i2), true);
                        break;
                    }
                    i2++;
                }
            } else {
                AnnotatedConstructor annotatedConstructor = this._defaultConstructor;
                if (annotatedConstructor != null) {
                    _addMixOvers(constructor, annotatedConstructor, false);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _addFactoryMixIns(Class<?> cls) {
        int size = this._creatorMethods.size();
        Method[] declaredMethods = cls.getDeclaredMethods();
        MemberKey[] memberKeyArr = null;
        for (Method method : declaredMethods) {
            if (Modifier.isStatic(method.getModifiers()) && method.getParameterTypes().length != 0) {
                if (memberKeyArr == null) {
                    memberKeyArr = new MemberKey[size];
                    for (int i = 0; i < size; i++) {
                        memberKeyArr[i] = new MemberKey(this._creatorMethods.get(i).getAnnotated());
                    }
                }
                MemberKey memberKey = new MemberKey(method);
                int i2 = 0;
                while (true) {
                    if (i2 >= size) {
                        break;
                    }
                    if (memberKey.equals(memberKeyArr[i2])) {
                        _addMixOvers(method, this._creatorMethods.get(i2), true);
                        break;
                    }
                    i2++;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _addMemberMethods(Class<?> cls, AnnotatedMethodMap annotatedMethodMap, Class<?> cls2, AnnotatedMethodMap annotatedMethodMap2) {
        if (cls2 != null) {
            _addMethodMixIns(cls, annotatedMethodMap, cls2, annotatedMethodMap2);
        }
        if (cls != null) {
            Method[] declaredMethods = cls.getDeclaredMethods();
            for (Method method : declaredMethods) {
                if (_isIncludableMemberMethod(method)) {
                    AnnotatedMethod find = annotatedMethodMap.find(method);
                    if (find == null) {
                        AnnotatedMethod _constructMethod = _constructMethod(method);
                        annotatedMethodMap.add(_constructMethod);
                        AnnotatedMethod remove = annotatedMethodMap2.remove(method);
                        if (remove != null) {
                            _addMixOvers(remove.getAnnotated(), _constructMethod, false);
                        }
                    } else {
                        _addMixUnders(method, find);
                        if (find.getDeclaringClass().isInterface() && !method.getDeclaringClass().isInterface()) {
                            annotatedMethodMap.add(find.withMethod(method));
                        }
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _addMethodMixIns(Class<?> cls, AnnotatedMethodMap annotatedMethodMap, Class<?> cls2, AnnotatedMethodMap annotatedMethodMap2) {
        ArrayList<Class> arrayList = new ArrayList();
        arrayList.add(cls2);
        ClassUtil.findSuperTypes(cls2, cls, arrayList);
        for (Class cls3 : arrayList) {
            Method[] declaredMethods = cls3.getDeclaredMethods();
            for (Method method : declaredMethods) {
                if (_isIncludableMemberMethod(method)) {
                    AnnotatedMethod find = annotatedMethodMap.find(method);
                    if (find != null) {
                        _addMixUnders(method, find);
                    } else {
                        annotatedMethodMap2.add(_constructMethod(method));
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public Map<String, AnnotatedField> _findFields(Class<?> cls, Map<String, AnnotatedField> map) {
        Class<?> findMixInClassFor;
        Class<? super Object> superclass = cls.getSuperclass();
        if (superclass != null) {
            map = _findFields(superclass, map);
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (_isIncludableField(field)) {
                    if (map == null) {
                        map = new LinkedHashMap<>();
                    }
                    map.put(field.getName(), _constructField(field));
                }
            }
            ClassIntrospector.MixInResolver mixInResolver = this._mixInResolver;
            if (!(mixInResolver == null || (findMixInClassFor = mixInResolver.findMixInClassFor(cls)) == null)) {
                _addFieldMixIns(superclass, findMixInClassFor, map);
            }
        }
        return map;
    }

    /* access modifiers changed from: protected */
    public void _addFieldMixIns(Class<?> cls, Class<?> cls2, Map<String, AnnotatedField> map) {
        AnnotatedField annotatedField;
        ArrayList<Class> arrayList = new ArrayList();
        arrayList.add(cls2);
        ClassUtil.findSuperTypes(cls2, cls, arrayList);
        for (Class cls3 : arrayList) {
            Field[] declaredFields = cls3.getDeclaredFields();
            for (Field field : declaredFields) {
                if (_isIncludableField(field) && (annotatedField = map.get(field.getName())) != null) {
                    _addOrOverrideAnnotations(annotatedField, field.getDeclaredAnnotations());
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public AnnotatedMethod _constructMethod(Method method) {
        if (this._annotationIntrospector == null) {
            return new AnnotatedMethod(method, _emptyAnnotationMap(), null);
        }
        return new AnnotatedMethod(method, _collectRelevantAnnotations(method.getDeclaredAnnotations()), null);
    }

    /* access modifiers changed from: protected */
    public AnnotatedConstructor _constructConstructor(Constructor<?> constructor, boolean z) {
        AnnotationMap[] annotationMapArr;
        Annotation[][] annotationArr;
        if (this._annotationIntrospector == null) {
            return new AnnotatedConstructor(constructor, _emptyAnnotationMap(), _emptyAnnotationMaps(constructor.getParameterTypes().length));
        }
        if (z) {
            return new AnnotatedConstructor(constructor, _collectRelevantAnnotations(constructor.getDeclaredAnnotations()), null);
        }
        Annotation[][] parameterAnnotations = constructor.getParameterAnnotations();
        int length = constructor.getParameterTypes().length;
        if (length != parameterAnnotations.length) {
            Class<?> declaringClass = constructor.getDeclaringClass();
            if (declaringClass.isEnum() && length == parameterAnnotations.length + 2) {
                annotationArr = new Annotation[(parameterAnnotations.length + 2)][];
                System.arraycopy(parameterAnnotations, 0, annotationArr, 2, parameterAnnotations.length);
                annotationMapArr = _collectRelevantAnnotations(annotationArr);
            } else if (!declaringClass.isMemberClass() || length != parameterAnnotations.length + 1) {
                annotationArr = parameterAnnotations;
                annotationMapArr = null;
            } else {
                annotationArr = new Annotation[(parameterAnnotations.length + 1)][];
                System.arraycopy(parameterAnnotations, 0, annotationArr, 1, parameterAnnotations.length);
                annotationMapArr = _collectRelevantAnnotations(annotationArr);
            }
            if (annotationMapArr == null) {
                throw new IllegalStateException("Internal error: constructor for " + constructor.getDeclaringClass().getName() + " has mismatch: " + length + " parameters; " + annotationArr.length + " sets of annotations");
            }
        } else {
            annotationMapArr = _collectRelevantAnnotations(parameterAnnotations);
        }
        return new AnnotatedConstructor(constructor, _collectRelevantAnnotations(constructor.getDeclaredAnnotations()), annotationMapArr);
    }

    /* access modifiers changed from: protected */
    public AnnotatedMethod _constructCreatorMethod(Method method) {
        if (this._annotationIntrospector == null) {
            return new AnnotatedMethod(method, _emptyAnnotationMap(), _emptyAnnotationMaps(method.getParameterTypes().length));
        }
        return new AnnotatedMethod(method, _collectRelevantAnnotations(method.getDeclaredAnnotations()), _collectRelevantAnnotations(method.getParameterAnnotations()));
    }

    /* access modifiers changed from: protected */
    public AnnotatedField _constructField(Field field) {
        if (this._annotationIntrospector == null) {
            return new AnnotatedField(field, _emptyAnnotationMap());
        }
        return new AnnotatedField(field, _collectRelevantAnnotations(field.getDeclaredAnnotations()));
    }

    private AnnotationMap _emptyAnnotationMap() {
        return new AnnotationMap();
    }

    private AnnotationMap[] _emptyAnnotationMaps(int i) {
        if (i == 0) {
            return NO_ANNOTATION_MAPS;
        }
        AnnotationMap[] annotationMapArr = new AnnotationMap[i];
        for (int i2 = 0; i2 < i; i2++) {
            annotationMapArr[i2] = _emptyAnnotationMap();
        }
        return annotationMapArr;
    }

    /* access modifiers changed from: protected */
    public boolean _isIncludableMemberMethod(Method method) {
        if (!Modifier.isStatic(method.getModifiers()) && !method.isSynthetic() && !method.isBridge() && method.getParameterTypes().length <= 2) {
            return true;
        }
        return false;
    }

    private boolean _isIncludableField(Field field) {
        if (field.isSynthetic()) {
            return false;
        }
        int modifiers = field.getModifiers();
        if (Modifier.isStatic(modifiers) || Modifier.isTransient(modifiers)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public AnnotationMap[] _collectRelevantAnnotations(Annotation[][] annotationArr) {
        int length = annotationArr.length;
        AnnotationMap[] annotationMapArr = new AnnotationMap[length];
        for (int i = 0; i < length; i++) {
            annotationMapArr[i] = _collectRelevantAnnotations(annotationArr[i]);
        }
        return annotationMapArr;
    }

    /* access modifiers changed from: protected */
    public AnnotationMap _collectRelevantAnnotations(Annotation[] annotationArr) {
        AnnotationMap annotationMap = new AnnotationMap();
        _addAnnotationsIfNotPresent(annotationMap, annotationArr);
        return annotationMap;
    }

    private void _addAnnotationsIfNotPresent(AnnotationMap annotationMap, Annotation[] annotationArr) {
        if (annotationArr != null) {
            LinkedList<Annotation[]> linkedList = null;
            for (Annotation annotation : annotationArr) {
                if (_isAnnotationBundle(annotation)) {
                    if (linkedList == null) {
                        linkedList = new LinkedList();
                    }
                    linkedList.add(annotation.annotationType().getDeclaredAnnotations());
                } else {
                    annotationMap.addIfNotPresent(annotation);
                }
            }
            if (linkedList != null) {
                for (Annotation[] annotationArr2 : linkedList) {
                    _addAnnotationsIfNotPresent(annotationMap, annotationArr2);
                }
            }
        }
    }

    private void _addAnnotationsIfNotPresent(AnnotatedMember annotatedMember, Annotation[] annotationArr) {
        if (annotationArr != null) {
            LinkedList<Annotation[]> linkedList = null;
            for (Annotation annotation : annotationArr) {
                if (_isAnnotationBundle(annotation)) {
                    if (linkedList == null) {
                        linkedList = new LinkedList();
                    }
                    linkedList.add(annotation.annotationType().getDeclaredAnnotations());
                } else {
                    annotatedMember.addIfNotPresent(annotation);
                }
            }
            if (linkedList != null) {
                for (Annotation[] annotationArr2 : linkedList) {
                    _addAnnotationsIfNotPresent(annotatedMember, annotationArr2);
                }
            }
        }
    }

    private void _addOrOverrideAnnotations(AnnotatedMember annotatedMember, Annotation[] annotationArr) {
        if (annotationArr != null) {
            LinkedList<Annotation[]> linkedList = null;
            for (Annotation annotation : annotationArr) {
                if (_isAnnotationBundle(annotation)) {
                    if (linkedList == null) {
                        linkedList = new LinkedList();
                    }
                    linkedList.add(annotation.annotationType().getDeclaredAnnotations());
                } else {
                    annotatedMember.addOrOverride(annotation);
                }
            }
            if (linkedList != null) {
                for (Annotation[] annotationArr2 : linkedList) {
                    _addOrOverrideAnnotations(annotatedMember, annotationArr2);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _addMixOvers(Constructor<?> constructor, AnnotatedConstructor annotatedConstructor, boolean z) {
        _addOrOverrideAnnotations(annotatedConstructor, constructor.getDeclaredAnnotations());
        if (z) {
            Annotation[][] parameterAnnotations = constructor.getParameterAnnotations();
            int length = parameterAnnotations.length;
            for (int i = 0; i < length; i++) {
                for (Annotation annotation : parameterAnnotations[i]) {
                    annotatedConstructor.addOrOverrideParam(i, annotation);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _addMixOvers(Method method, AnnotatedMethod annotatedMethod, boolean z) {
        _addOrOverrideAnnotations(annotatedMethod, method.getDeclaredAnnotations());
        if (z) {
            Annotation[][] parameterAnnotations = method.getParameterAnnotations();
            int length = parameterAnnotations.length;
            for (int i = 0; i < length; i++) {
                for (Annotation annotation : parameterAnnotations[i]) {
                    annotatedMethod.addOrOverrideParam(i, annotation);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _addMixUnders(Method method, AnnotatedMethod annotatedMethod) {
        _addAnnotationsIfNotPresent(annotatedMethod, method.getDeclaredAnnotations());
    }

    private final boolean _isAnnotationBundle(Annotation annotation) {
        AnnotationIntrospector annotationIntrospector = this._annotationIntrospector;
        return annotationIntrospector != null && annotationIntrospector.isAnnotationBundle(annotation);
    }

    public String toString() {
        return "[AnnotedClass " + this._class.getName() + "]";
    }
}
