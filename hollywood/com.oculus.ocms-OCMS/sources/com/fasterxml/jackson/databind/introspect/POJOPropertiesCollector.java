package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class POJOPropertiesCollector {
    protected final AnnotationIntrospector _annotationIntrospector;
    protected LinkedList<AnnotatedMember> _anyGetters;
    protected LinkedList<AnnotatedMethod> _anySetters;
    protected final AnnotatedClass _classDef;
    protected final MapperConfig<?> _config;
    protected LinkedList<POJOPropertyBuilder> _creatorProperties;
    protected final boolean _forSerialization;
    protected HashSet<String> _ignoredPropertyNames;
    protected LinkedHashMap<Object, AnnotatedMember> _injectables;
    protected LinkedList<AnnotatedMethod> _jsonValueGetters;
    protected final String _mutatorPrefix;
    protected final LinkedHashMap<String, POJOPropertyBuilder> _properties = new LinkedHashMap<>();
    protected final JavaType _type;
    protected final VisibilityChecker<?> _visibilityChecker;

    protected POJOPropertiesCollector(MapperConfig<?> mapperConfig, boolean z, JavaType javaType, AnnotatedClass annotatedClass, String str) {
        AnnotationIntrospector annotationIntrospector = null;
        this._creatorProperties = null;
        this._anyGetters = null;
        this._anySetters = null;
        this._jsonValueGetters = null;
        this._config = mapperConfig;
        this._forSerialization = z;
        this._type = javaType;
        this._classDef = annotatedClass;
        this._mutatorPrefix = str == null ? "set" : str;
        this._annotationIntrospector = mapperConfig.isAnnotationProcessingEnabled() ? this._config.getAnnotationIntrospector() : annotationIntrospector;
        AnnotationIntrospector annotationIntrospector2 = this._annotationIntrospector;
        if (annotationIntrospector2 == null) {
            this._visibilityChecker = this._config.getDefaultVisibilityChecker();
        } else {
            this._visibilityChecker = annotationIntrospector2.findAutoDetectVisibility(annotatedClass, this._config.getDefaultVisibilityChecker());
        }
    }

    public MapperConfig<?> getConfig() {
        return this._config;
    }

    public JavaType getType() {
        return this._type;
    }

    public AnnotatedClass getClassDef() {
        return this._classDef;
    }

    public AnnotationIntrospector getAnnotationIntrospector() {
        return this._annotationIntrospector;
    }

    public List<BeanPropertyDefinition> getProperties() {
        return new ArrayList(this._properties.values());
    }

    public Map<Object, AnnotatedMember> getInjectables() {
        return this._injectables;
    }

    public AnnotatedMethod getJsonValueMethod() {
        LinkedList<AnnotatedMethod> linkedList = this._jsonValueGetters;
        if (linkedList == null) {
            return null;
        }
        if (linkedList.size() > 1) {
            reportProblem("Multiple value properties defined (" + this._jsonValueGetters.get(0) + " vs " + this._jsonValueGetters.get(1) + ")");
        }
        return this._jsonValueGetters.get(0);
    }

    public AnnotatedMember getAnyGetter() {
        LinkedList<AnnotatedMember> linkedList = this._anyGetters;
        if (linkedList == null) {
            return null;
        }
        if (linkedList.size() > 1) {
            reportProblem("Multiple 'any-getters' defined (" + this._anyGetters.get(0) + " vs " + this._anyGetters.get(1) + ")");
        }
        return this._anyGetters.getFirst();
    }

    public AnnotatedMethod getAnySetterMethod() {
        LinkedList<AnnotatedMethod> linkedList = this._anySetters;
        if (linkedList == null) {
            return null;
        }
        if (linkedList.size() > 1) {
            reportProblem("Multiple 'any-setters' defined (" + this._anySetters.get(0) + " vs " + this._anySetters.get(1) + ")");
        }
        return this._anySetters.getFirst();
    }

    public Set<String> getIgnoredPropertyNames() {
        return this._ignoredPropertyNames;
    }

    public ObjectIdInfo getObjectIdInfo() {
        AnnotationIntrospector annotationIntrospector = this._annotationIntrospector;
        if (annotationIntrospector == null) {
            return null;
        }
        ObjectIdInfo findObjectIdInfo = annotationIntrospector.findObjectIdInfo(this._classDef);
        return findObjectIdInfo != null ? this._annotationIntrospector.findObjectReferenceInfo(this._classDef, findObjectIdInfo) : findObjectIdInfo;
    }

    public Class<?> findPOJOBuilderClass() {
        return this._annotationIntrospector.findPOJOBuilder(this._classDef);
    }

    /* access modifiers changed from: protected */
    public Map<String, POJOPropertyBuilder> getPropertyMap() {
        return this._properties;
    }

    public POJOPropertiesCollector collect() {
        this._properties.clear();
        _addFields();
        _addMethods();
        _addCreators();
        _addInjectables();
        _removeUnwantedProperties();
        _renameProperties();
        PropertyNamingStrategy _findNamingStrategy = _findNamingStrategy();
        if (_findNamingStrategy != null) {
            _renameUsing(_findNamingStrategy);
        }
        for (POJOPropertyBuilder pOJOPropertyBuilder : this._properties.values()) {
            pOJOPropertyBuilder.trimByVisibility();
        }
        for (POJOPropertyBuilder pOJOPropertyBuilder2 : this._properties.values()) {
            pOJOPropertyBuilder2.mergeAnnotations(this._forSerialization);
        }
        if (this._config.isEnabled(MapperFeature.USE_WRAPPER_NAME_AS_PROPERTY_NAME)) {
            _renameWithWrappers();
        }
        _sortProperties();
        return this;
    }

    /* access modifiers changed from: protected */
    public void _sortProperties() {
        boolean z;
        Map map;
        AnnotationIntrospector annotationIntrospector = this._annotationIntrospector;
        String[] strArr = null;
        Boolean findSerializationSortAlphabetically = annotationIntrospector == null ? null : annotationIntrospector.findSerializationSortAlphabetically(this._classDef);
        if (findSerializationSortAlphabetically == null) {
            z = this._config.shouldSortPropertiesAlphabetically();
        } else {
            z = findSerializationSortAlphabetically.booleanValue();
        }
        if (annotationIntrospector != null) {
            strArr = annotationIntrospector.findSerializationPropertyOrder(this._classDef);
        }
        if (!(!z && this._creatorProperties == null && strArr == null)) {
            int size = this._properties.size();
            if (z) {
                map = new TreeMap();
            } else {
                map = new LinkedHashMap(size + size);
            }
            for (POJOPropertyBuilder pOJOPropertyBuilder : this._properties.values()) {
                map.put(pOJOPropertyBuilder.getName(), pOJOPropertyBuilder);
            }
            LinkedHashMap linkedHashMap = new LinkedHashMap(size + size);
            if (strArr != null) {
                for (String str : strArr) {
                    POJOPropertyBuilder pOJOPropertyBuilder2 = (POJOPropertyBuilder) map.get(str);
                    if (pOJOPropertyBuilder2 == null) {
                        Iterator<POJOPropertyBuilder> it = this._properties.values().iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            POJOPropertyBuilder next = it.next();
                            if (str.equals(next.getInternalName())) {
                                str = next.getName();
                                pOJOPropertyBuilder2 = next;
                                break;
                            }
                        }
                    }
                    if (pOJOPropertyBuilder2 != null) {
                        linkedHashMap.put(str, pOJOPropertyBuilder2);
                    }
                }
            }
            LinkedList<POJOPropertyBuilder> linkedList = this._creatorProperties;
            if (linkedList != null) {
                Iterator<POJOPropertyBuilder> it2 = linkedList.iterator();
                while (it2.hasNext()) {
                    POJOPropertyBuilder next2 = it2.next();
                    linkedHashMap.put(next2.getName(), next2);
                }
            }
            linkedHashMap.putAll(map);
            this._properties.clear();
            this._properties.putAll(linkedHashMap);
        }
    }

    /* access modifiers changed from: protected */
    public void _addFields() {
        AnnotationIntrospector annotationIntrospector = this._annotationIntrospector;
        boolean z = !this._forSerialization && !this._config.isEnabled(MapperFeature.ALLOW_FINAL_FIELDS_AS_MUTATORS);
        for (AnnotatedField annotatedField : this._classDef.fields()) {
            String name = annotatedField.getName();
            String str = null;
            if (annotationIntrospector != null) {
                if (this._forSerialization) {
                    PropertyName findNameForSerialization = annotationIntrospector.findNameForSerialization(annotatedField);
                    if (findNameForSerialization != null) {
                        str = findNameForSerialization.getSimpleName();
                    }
                } else {
                    PropertyName findNameForDeserialization = annotationIntrospector.findNameForDeserialization(annotatedField);
                    if (findNameForDeserialization != null) {
                        str = findNameForDeserialization.getSimpleName();
                    }
                }
            }
            if ("".equals(str)) {
                str = name;
            }
            boolean z2 = str != null;
            if (!z2) {
                z2 = this._visibilityChecker.isFieldVisible(annotatedField);
            }
            boolean z3 = annotationIntrospector != null && annotationIntrospector.hasIgnoreMarker(annotatedField);
            if (!z || str != null || z3 || !Modifier.isFinal(annotatedField.getModifiers())) {
                _property(name).addField(annotatedField, str, z2, z3);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _addCreators() {
        AnnotationIntrospector annotationIntrospector = this._annotationIntrospector;
        if (annotationIntrospector != null) {
            for (AnnotatedConstructor annotatedConstructor : this._classDef.getConstructors()) {
                if (this._creatorProperties == null) {
                    this._creatorProperties = new LinkedList<>();
                }
                int parameterCount = annotatedConstructor.getParameterCount();
                for (int i = 0; i < parameterCount; i++) {
                    AnnotatedParameter parameter = annotatedConstructor.getParameter(i);
                    PropertyName findNameForDeserialization = annotationIntrospector.findNameForDeserialization(parameter);
                    String simpleName = findNameForDeserialization == null ? null : findNameForDeserialization.getSimpleName();
                    if (simpleName != null) {
                        POJOPropertyBuilder _property = _property(simpleName);
                        _property.addCtor(parameter, simpleName, true, false);
                        this._creatorProperties.add(_property);
                    }
                }
            }
            for (AnnotatedMethod annotatedMethod : this._classDef.getStaticMethods()) {
                if (this._creatorProperties == null) {
                    this._creatorProperties = new LinkedList<>();
                }
                int parameterCount2 = annotatedMethod.getParameterCount();
                for (int i2 = 0; i2 < parameterCount2; i2++) {
                    AnnotatedParameter parameter2 = annotatedMethod.getParameter(i2);
                    PropertyName findNameForDeserialization2 = annotationIntrospector.findNameForDeserialization(parameter2);
                    String simpleName2 = findNameForDeserialization2 == null ? null : findNameForDeserialization2.getSimpleName();
                    if (simpleName2 != null) {
                        POJOPropertyBuilder _property2 = _property(simpleName2);
                        _property2.addCtor(parameter2, simpleName2, true, false);
                        this._creatorProperties.add(_property2);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _addMethods() {
        AnnotationIntrospector annotationIntrospector = this._annotationIntrospector;
        for (AnnotatedMethod annotatedMethod : this._classDef.memberMethods()) {
            int parameterCount = annotatedMethod.getParameterCount();
            if (parameterCount == 0) {
                _addGetterMethod(annotatedMethod, annotationIntrospector);
            } else if (parameterCount == 1) {
                _addSetterMethod(annotatedMethod, annotationIntrospector);
            } else if (parameterCount == 2 && annotationIntrospector != null && annotationIntrospector.hasAnySetterAnnotation(annotatedMethod)) {
                if (this._anySetters == null) {
                    this._anySetters = new LinkedList<>();
                }
                this._anySetters.add(annotatedMethod);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _addGetterMethod(AnnotatedMethod annotatedMethod, AnnotationIntrospector annotationIntrospector) {
        PropertyName propertyName;
        boolean z;
        String str;
        boolean z2;
        if (annotationIntrospector != null) {
            if (annotationIntrospector.hasAnyGetterAnnotation(annotatedMethod)) {
                if (this._anyGetters == null) {
                    this._anyGetters = new LinkedList<>();
                }
                this._anyGetters.add(annotatedMethod);
                return;
            } else if (annotationIntrospector.hasAsValueAnnotation(annotatedMethod)) {
                if (this._jsonValueGetters == null) {
                    this._jsonValueGetters = new LinkedList<>();
                }
                this._jsonValueGetters.add(annotatedMethod);
                return;
            }
        }
        String str2 = null;
        if (annotationIntrospector == null) {
            propertyName = null;
        } else {
            propertyName = annotationIntrospector.findNameForSerialization(annotatedMethod);
        }
        if (propertyName != null) {
            str2 = propertyName.getSimpleName();
        }
        if (str2 == null) {
            str = BeanUtil.okNameForRegularGetter(annotatedMethod, annotatedMethod.getName());
            if (str == null) {
                str = BeanUtil.okNameForIsGetter(annotatedMethod, annotatedMethod.getName());
                if (str != null) {
                    z = this._visibilityChecker.isIsGetterVisible(annotatedMethod);
                } else {
                    return;
                }
            } else {
                z = this._visibilityChecker.isGetterVisible(annotatedMethod);
            }
        } else {
            str = BeanUtil.okNameForGetter(annotatedMethod);
            if (str == null) {
                str = annotatedMethod.getName();
            }
            if (str2.length() == 0) {
                str2 = str;
            }
            z = true;
        }
        if (annotationIntrospector == null) {
            z2 = false;
        } else {
            z2 = annotationIntrospector.hasIgnoreMarker(annotatedMethod);
        }
        _property(str).addGetter(annotatedMethod, str2, z, z2);
    }

    /* access modifiers changed from: protected */
    public void _addSetterMethod(AnnotatedMethod annotatedMethod, AnnotationIntrospector annotationIntrospector) {
        boolean z;
        String str;
        boolean z2;
        String str2 = null;
        PropertyName findNameForDeserialization = annotationIntrospector == null ? null : annotationIntrospector.findNameForDeserialization(annotatedMethod);
        if (findNameForDeserialization != null) {
            str2 = findNameForDeserialization.getSimpleName();
        }
        if (str2 == null) {
            str = BeanUtil.okNameForMutator(annotatedMethod, this._mutatorPrefix);
            if (str != null) {
                z = this._visibilityChecker.isSetterVisible(annotatedMethod);
            } else {
                return;
            }
        } else {
            str = BeanUtil.okNameForMutator(annotatedMethod, this._mutatorPrefix);
            if (str == null) {
                str = annotatedMethod.getName();
            }
            if (str2.length() == 0) {
                str2 = str;
            }
            z = true;
        }
        if (annotationIntrospector == null) {
            z2 = false;
        } else {
            z2 = annotationIntrospector.hasIgnoreMarker(annotatedMethod);
        }
        _property(str).addSetter(annotatedMethod, str2, z, z2);
    }

    /* access modifiers changed from: protected */
    public void _addInjectables() {
        AnnotationIntrospector annotationIntrospector = this._annotationIntrospector;
        if (annotationIntrospector != null) {
            for (AnnotatedMember annotatedMember : this._classDef.fields()) {
                _doAddInjectable(annotationIntrospector.findInjectableValueId(annotatedMember), annotatedMember);
            }
            for (AnnotatedMethod annotatedMethod : this._classDef.memberMethods()) {
                if (annotatedMethod.getParameterCount() == 1) {
                    _doAddInjectable(annotationIntrospector.findInjectableValueId(annotatedMethod), annotatedMethod);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _doAddInjectable(Object obj, AnnotatedMember annotatedMember) {
        if (obj != null) {
            if (this._injectables == null) {
                this._injectables = new LinkedHashMap<>();
            }
            if (this._injectables.put(obj, annotatedMember) != null) {
                String name = obj.getClass().getName();
                throw new IllegalArgumentException("Duplicate injectable value with id '" + String.valueOf(obj) + "' (of type " + name + ")");
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _removeUnwantedProperties() {
        Iterator<Map.Entry<String, POJOPropertyBuilder>> it = this._properties.entrySet().iterator();
        boolean z = !this._config.isEnabled(MapperFeature.INFER_PROPERTY_MUTATORS);
        while (it.hasNext()) {
            POJOPropertyBuilder value = it.next().getValue();
            if (!value.anyVisible()) {
                it.remove();
            } else {
                if (value.anyIgnorals()) {
                    if (!value.isExplicitlyIncluded()) {
                        it.remove();
                        _addIgnored(value.getName());
                    } else {
                        value.removeIgnored();
                        if (!this._forSerialization && !value.couldDeserialize()) {
                            _addIgnored(value.getName());
                        }
                    }
                }
                value.removeNonVisible(z);
            }
        }
    }

    private void _addIgnored(String str) {
        if (!this._forSerialization) {
            if (this._ignoredPropertyNames == null) {
                this._ignoredPropertyNames = new HashSet<>();
            }
            this._ignoredPropertyNames.add(str);
        }
    }

    /* access modifiers changed from: protected */
    public void _renameProperties() {
        Iterator<Map.Entry<String, POJOPropertyBuilder>> it = this._properties.entrySet().iterator();
        LinkedList linkedList = null;
        while (it.hasNext()) {
            POJOPropertyBuilder value = it.next().getValue();
            String findNewName = value.findNewName();
            if (findNewName != null) {
                if (linkedList == null) {
                    linkedList = new LinkedList();
                }
                linkedList.add(value.withName(findNewName));
                it.remove();
            }
        }
        if (linkedList != null) {
            Iterator it2 = linkedList.iterator();
            while (it2.hasNext()) {
                POJOPropertyBuilder pOJOPropertyBuilder = (POJOPropertyBuilder) it2.next();
                String name = pOJOPropertyBuilder.getName();
                POJOPropertyBuilder pOJOPropertyBuilder2 = this._properties.get(name);
                if (pOJOPropertyBuilder2 == null) {
                    this._properties.put(name, pOJOPropertyBuilder);
                } else {
                    pOJOPropertyBuilder2.addAll(pOJOPropertyBuilder);
                }
                if (this._creatorProperties != null) {
                    int i = 0;
                    while (true) {
                        if (i >= this._creatorProperties.size()) {
                            break;
                        } else if (this._creatorProperties.get(i).getInternalName() == pOJOPropertyBuilder.getInternalName()) {
                            this._creatorProperties.set(i, pOJOPropertyBuilder);
                            break;
                        } else {
                            i++;
                        }
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _renameUsing(PropertyNamingStrategy propertyNamingStrategy) {
        POJOPropertyBuilder[] pOJOPropertyBuilderArr = (POJOPropertyBuilder[]) this._properties.values().toArray(new POJOPropertyBuilder[this._properties.size()]);
        this._properties.clear();
        for (POJOPropertyBuilder pOJOPropertyBuilder : pOJOPropertyBuilderArr) {
            String name = pOJOPropertyBuilder.getName();
            if (this._forSerialization) {
                if (pOJOPropertyBuilder.hasGetter()) {
                    name = propertyNamingStrategy.nameForGetterMethod(this._config, pOJOPropertyBuilder.getGetter(), name);
                } else if (pOJOPropertyBuilder.hasField()) {
                    name = propertyNamingStrategy.nameForField(this._config, pOJOPropertyBuilder.getField(), name);
                }
            } else if (pOJOPropertyBuilder.hasSetter()) {
                name = propertyNamingStrategy.nameForSetterMethod(this._config, pOJOPropertyBuilder.getSetter(), name);
            } else if (pOJOPropertyBuilder.hasConstructorParameter()) {
                name = propertyNamingStrategy.nameForConstructorParameter(this._config, pOJOPropertyBuilder.getConstructorParameter(), name);
            } else if (pOJOPropertyBuilder.hasField()) {
                name = propertyNamingStrategy.nameForField(this._config, pOJOPropertyBuilder.getField(), name);
            } else if (pOJOPropertyBuilder.hasGetter()) {
                name = propertyNamingStrategy.nameForGetterMethod(this._config, pOJOPropertyBuilder.getGetter(), name);
            }
            if (!name.equals(pOJOPropertyBuilder.getName())) {
                pOJOPropertyBuilder = pOJOPropertyBuilder.withName(name);
            }
            POJOPropertyBuilder pOJOPropertyBuilder2 = this._properties.get(name);
            if (pOJOPropertyBuilder2 == null) {
                this._properties.put(name, pOJOPropertyBuilder);
            } else {
                pOJOPropertyBuilder2.addAll(pOJOPropertyBuilder);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _renameWithWrappers() {
        PropertyName findWrapperName;
        Iterator<Map.Entry<String, POJOPropertyBuilder>> it = this._properties.entrySet().iterator();
        LinkedList linkedList = null;
        while (it.hasNext()) {
            POJOPropertyBuilder value = it.next().getValue();
            AnnotatedMember primaryMember = value.getPrimaryMember();
            if (!(primaryMember == null || (findWrapperName = this._annotationIntrospector.findWrapperName(primaryMember)) == null || !findWrapperName.hasSimpleName())) {
                String simpleName = findWrapperName.getSimpleName();
                if (!simpleName.equals(value.getName())) {
                    if (linkedList == null) {
                        linkedList = new LinkedList();
                    }
                    linkedList.add(value.withName(simpleName));
                    it.remove();
                }
            }
        }
        if (linkedList != null) {
            Iterator it2 = linkedList.iterator();
            while (it2.hasNext()) {
                POJOPropertyBuilder pOJOPropertyBuilder = (POJOPropertyBuilder) it2.next();
                String name = pOJOPropertyBuilder.getName();
                POJOPropertyBuilder pOJOPropertyBuilder2 = this._properties.get(name);
                if (pOJOPropertyBuilder2 == null) {
                    this._properties.put(name, pOJOPropertyBuilder);
                } else {
                    pOJOPropertyBuilder2.addAll(pOJOPropertyBuilder);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void reportProblem(String str) {
        throw new IllegalArgumentException("Problem with definition of " + this._classDef + ": " + str);
    }

    /* access modifiers changed from: protected */
    public POJOPropertyBuilder _property(String str) {
        POJOPropertyBuilder pOJOPropertyBuilder = this._properties.get(str);
        if (pOJOPropertyBuilder != null) {
            return pOJOPropertyBuilder;
        }
        POJOPropertyBuilder pOJOPropertyBuilder2 = new POJOPropertyBuilder(str, this._annotationIntrospector, this._forSerialization);
        this._properties.put(str, pOJOPropertyBuilder2);
        return pOJOPropertyBuilder2;
    }

    private PropertyNamingStrategy _findNamingStrategy() {
        Object obj;
        PropertyNamingStrategy namingStrategyInstance;
        AnnotationIntrospector annotationIntrospector = this._annotationIntrospector;
        if (annotationIntrospector == null) {
            obj = null;
        } else {
            obj = annotationIntrospector.findNamingStrategy(this._classDef);
        }
        if (obj == null) {
            return this._config.getPropertyNamingStrategy();
        }
        if (obj instanceof PropertyNamingStrategy) {
            return (PropertyNamingStrategy) obj;
        }
        if (obj instanceof Class) {
            Class<?> cls = (Class) obj;
            if (PropertyNamingStrategy.class.isAssignableFrom(cls)) {
                HandlerInstantiator handlerInstantiator = this._config.getHandlerInstantiator();
                if (handlerInstantiator == null || (namingStrategyInstance = handlerInstantiator.namingStrategyInstance(this._config, this._classDef, cls)) == null) {
                    return (PropertyNamingStrategy) ClassUtil.createInstance(cls, this._config.canOverrideAccessModifiers());
                }
                return namingStrategyInstance;
            }
            throw new IllegalStateException("AnnotationIntrospector returned Class " + cls.getName() + "; expected Class<PropertyNamingStrategy>");
        }
        throw new IllegalStateException("AnnotationIntrospector returned PropertyNamingStrategy definition of type " + obj.getClass().getName() + "; expected type PropertyNamingStrategy or Class<PropertyNamingStrategy> instead");
    }
}
