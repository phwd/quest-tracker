package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.PropertyName;

public class POJOPropertyBuilder extends BeanPropertyDefinition implements Comparable<POJOPropertyBuilder> {
    protected final AnnotationIntrospector _annotationIntrospector;
    protected Linked<AnnotatedParameter> _ctorParameters;
    protected Linked<AnnotatedField> _fields;
    protected final boolean _forSerialization;
    protected Linked<AnnotatedMethod> _getters;
    protected final String _internalName;
    protected final String _name;
    protected Linked<AnnotatedMethod> _setters;

    /* access modifiers changed from: private */
    public interface WithMember<T> {
        T withMember(AnnotatedMember annotatedMember);
    }

    public POJOPropertyBuilder(String str, AnnotationIntrospector annotationIntrospector, boolean z) {
        this._internalName = str;
        this._name = str;
        this._annotationIntrospector = annotationIntrospector;
        this._forSerialization = z;
    }

    public POJOPropertyBuilder(POJOPropertyBuilder pOJOPropertyBuilder, String str) {
        this._internalName = pOJOPropertyBuilder._internalName;
        this._name = str;
        this._annotationIntrospector = pOJOPropertyBuilder._annotationIntrospector;
        this._fields = pOJOPropertyBuilder._fields;
        this._ctorParameters = pOJOPropertyBuilder._ctorParameters;
        this._getters = pOJOPropertyBuilder._getters;
        this._setters = pOJOPropertyBuilder._setters;
        this._forSerialization = pOJOPropertyBuilder._forSerialization;
    }

    @Override // com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition
    public POJOPropertyBuilder withName(String str) {
        return new POJOPropertyBuilder(this, str);
    }

    public int compareTo(POJOPropertyBuilder pOJOPropertyBuilder) {
        if (this._ctorParameters != null) {
            if (pOJOPropertyBuilder._ctorParameters == null) {
                return -1;
            }
        } else if (pOJOPropertyBuilder._ctorParameters != null) {
            return 1;
        }
        return getName().compareTo(pOJOPropertyBuilder.getName());
    }

    @Override // com.fasterxml.jackson.databind.util.Named, com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition
    public String getName() {
        return this._name;
    }

    @Override // com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition
    public String getInternalName() {
        return this._internalName;
    }

    @Override // com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition
    public PropertyName getWrapperName() {
        AnnotationIntrospector annotationIntrospector;
        AnnotatedMember primaryMember = getPrimaryMember();
        if (primaryMember == null || (annotationIntrospector = this._annotationIntrospector) == null) {
            return null;
        }
        return annotationIntrospector.findWrapperName(primaryMember);
    }

    @Override // com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition
    public boolean isExplicitlyIncluded() {
        return _anyExplicitNames(this._fields) || _anyExplicitNames(this._getters) || _anyExplicitNames(this._setters) || _anyExplicitNames(this._ctorParameters);
    }

    @Override // com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition
    public boolean hasGetter() {
        return this._getters != null;
    }

    @Override // com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition
    public boolean hasSetter() {
        return this._setters != null;
    }

    @Override // com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition
    public boolean hasField() {
        return this._fields != null;
    }

    @Override // com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition
    public boolean hasConstructorParameter() {
        return this._ctorParameters != null;
    }

    @Override // com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition
    public boolean couldSerialize() {
        return (this._getters == null && this._fields == null) ? false : true;
    }

    @Override // com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition
    public AnnotatedMethod getGetter() {
        Linked<AnnotatedMethod> linked = this._getters;
        if (linked == null) {
            return null;
        }
        T t = linked.value;
        for (Linked<T> linked2 = this._getters.next; linked2 != null; linked2 = linked2.next) {
            T t2 = linked2.value;
            Class<?> declaringClass = t.getDeclaringClass();
            Class<?> declaringClass2 = t2.getDeclaringClass();
            if (declaringClass != declaringClass2) {
                if (declaringClass.isAssignableFrom(declaringClass2)) {
                    t = t2;
                } else if (declaringClass2.isAssignableFrom(declaringClass)) {
                }
            }
            throw new IllegalArgumentException("Conflicting getter definitions for property \"" + getName() + "\": " + t.getFullName() + " vs " + t2.getFullName());
        }
        return t;
    }

    @Override // com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition
    public AnnotatedMethod getSetter() {
        Linked<AnnotatedMethod> linked = this._setters;
        if (linked == null) {
            return null;
        }
        T t = linked.value;
        for (Linked<T> linked2 = this._setters.next; linked2 != null; linked2 = linked2.next) {
            T t2 = linked2.value;
            Class<?> declaringClass = t.getDeclaringClass();
            Class<?> declaringClass2 = t2.getDeclaringClass();
            if (declaringClass != declaringClass2) {
                if (declaringClass.isAssignableFrom(declaringClass2)) {
                    t = t2;
                } else if (declaringClass2.isAssignableFrom(declaringClass)) {
                }
            }
            throw new IllegalArgumentException("Conflicting setter definitions for property \"" + getName() + "\": " + t.getFullName() + " vs " + t2.getFullName());
        }
        return t;
    }

    @Override // com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition
    public AnnotatedField getField() {
        Linked<AnnotatedField> linked = this._fields;
        if (linked == null) {
            return null;
        }
        T t = linked.value;
        for (Linked<T> linked2 = this._fields.next; linked2 != null; linked2 = linked2.next) {
            T t2 = linked2.value;
            Class<?> declaringClass = t.getDeclaringClass();
            Class<?> declaringClass2 = t2.getDeclaringClass();
            if (declaringClass != declaringClass2) {
                if (declaringClass.isAssignableFrom(declaringClass2)) {
                    t = t2;
                } else if (declaringClass2.isAssignableFrom(declaringClass)) {
                }
            }
            throw new IllegalArgumentException("Multiple fields representing property \"" + getName() + "\": " + t.getFullName() + " vs " + t2.getFullName());
        }
        return t;
    }

    @Override // com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition
    public AnnotatedParameter getConstructorParameter() {
        Linked linked = this._ctorParameters;
        if (linked == null) {
            return null;
        }
        while (!(linked.value.getOwner() instanceof AnnotatedConstructor)) {
            linked = linked.next;
            if (linked == null) {
                return this._ctorParameters.value;
            }
        }
        return linked.value;
    }

    @Override // com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition
    public AnnotatedMember getAccessor() {
        AnnotatedMethod getter = getGetter();
        return getter == null ? getField() : getter;
    }

    @Override // com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition
    public AnnotatedMember getMutator() {
        AnnotatedParameter constructorParameter = getConstructorParameter();
        if (constructorParameter != null) {
            return constructorParameter;
        }
        AnnotatedMethod setter = getSetter();
        return setter == null ? getField() : setter;
    }

    @Override // com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition
    public AnnotatedMember getPrimaryMember() {
        if (this._forSerialization) {
            return getAccessor();
        }
        return getMutator();
    }

    @Override // com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition
    public Class<?>[] findViews() {
        return (Class[]) fromMemberAnnotations(new WithMember<Class<?>[]>() {
            /* class com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder.AnonymousClass1 */

            @Override // com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder.WithMember
            public Class<?>[] withMember(AnnotatedMember annotatedMember) {
                return POJOPropertyBuilder.this._annotationIntrospector.findViews(annotatedMember);
            }
        });
    }

    @Override // com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition
    public AnnotationIntrospector.ReferenceProperty findReferenceType() {
        return (AnnotationIntrospector.ReferenceProperty) fromMemberAnnotations(new WithMember<AnnotationIntrospector.ReferenceProperty>() {
            /* class com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder.AnonymousClass2 */

            @Override // com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder.WithMember
            public AnnotationIntrospector.ReferenceProperty withMember(AnnotatedMember annotatedMember) {
                return POJOPropertyBuilder.this._annotationIntrospector.findReferenceType(annotatedMember);
            }
        });
    }

    @Override // com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition
    public boolean isTypeId() {
        Boolean bool = (Boolean) fromMemberAnnotations(new WithMember<Boolean>() {
            /* class com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder.AnonymousClass3 */

            @Override // com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder.WithMember
            public Boolean withMember(AnnotatedMember annotatedMember) {
                return POJOPropertyBuilder.this._annotationIntrospector.isTypeId(annotatedMember);
            }
        });
        return bool != null && bool.booleanValue();
    }

    @Override // com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition
    public boolean isRequired() {
        Boolean bool = (Boolean) fromMemberAnnotations(new WithMember<Boolean>() {
            /* class com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder.AnonymousClass4 */

            @Override // com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder.WithMember
            public Boolean withMember(AnnotatedMember annotatedMember) {
                return POJOPropertyBuilder.this._annotationIntrospector.hasRequiredMarker(annotatedMember);
            }
        });
        return bool != null && bool.booleanValue();
    }

    @Override // com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition
    public ObjectIdInfo findObjectIdInfo() {
        return (ObjectIdInfo) fromMemberAnnotations(new WithMember<ObjectIdInfo>() {
            /* class com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder.AnonymousClass5 */

            @Override // com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder.WithMember
            public ObjectIdInfo withMember(AnnotatedMember annotatedMember) {
                ObjectIdInfo findObjectIdInfo = POJOPropertyBuilder.this._annotationIntrospector.findObjectIdInfo(annotatedMember);
                return findObjectIdInfo != null ? POJOPropertyBuilder.this._annotationIntrospector.findObjectReferenceInfo(annotatedMember, findObjectIdInfo) : findObjectIdInfo;
            }
        });
    }

    public void addField(AnnotatedField annotatedField, String str, boolean z, boolean z2) {
        this._fields = new Linked<>(annotatedField, this._fields, str, z, z2);
    }

    public void addCtor(AnnotatedParameter annotatedParameter, String str, boolean z, boolean z2) {
        this._ctorParameters = new Linked<>(annotatedParameter, this._ctorParameters, str, z, z2);
    }

    public void addGetter(AnnotatedMethod annotatedMethod, String str, boolean z, boolean z2) {
        this._getters = new Linked<>(annotatedMethod, this._getters, str, z, z2);
    }

    public void addSetter(AnnotatedMethod annotatedMethod, String str, boolean z, boolean z2) {
        this._setters = new Linked<>(annotatedMethod, this._setters, str, z, z2);
    }

    public void addAll(POJOPropertyBuilder pOJOPropertyBuilder) {
        this._fields = merge(this._fields, pOJOPropertyBuilder._fields);
        this._ctorParameters = merge(this._ctorParameters, pOJOPropertyBuilder._ctorParameters);
        this._getters = merge(this._getters, pOJOPropertyBuilder._getters);
        this._setters = merge(this._setters, pOJOPropertyBuilder._setters);
    }

    private static <T> Linked<T> merge(Linked<T> linked, Linked<T> linked2) {
        if (linked == null) {
            return linked2;
        }
        return linked2 == null ? linked : linked.append(linked2);
    }

    public void removeIgnored() {
        this._fields = _removeIgnored(this._fields);
        this._getters = _removeIgnored(this._getters);
        this._setters = _removeIgnored(this._setters);
        this._ctorParameters = _removeIgnored(this._ctorParameters);
    }

    @Deprecated
    public void removeNonVisible() {
        removeNonVisible(false);
    }

    public void removeNonVisible(boolean z) {
        this._getters = _removeNonVisible(this._getters);
        this._ctorParameters = _removeNonVisible(this._ctorParameters);
        if (z || this._getters == null) {
            this._fields = _removeNonVisible(this._fields);
            this._setters = _removeNonVisible(this._setters);
        }
    }

    public void trimByVisibility() {
        this._fields = _trimByVisibility(this._fields);
        this._getters = _trimByVisibility(this._getters);
        this._setters = _trimByVisibility(this._setters);
        this._ctorParameters = _trimByVisibility(this._ctorParameters);
    }

    public void mergeAnnotations(boolean z) {
        if (z) {
            Linked<AnnotatedMethod> linked = this._getters;
            if (linked != null) {
                AnnotationMap _mergeAnnotations = _mergeAnnotations(0, linked, this._fields, this._ctorParameters, this._setters);
                Linked<AnnotatedMethod> linked2 = this._getters;
                this._getters = linked2.withValue(linked2.value.withAnnotations(_mergeAnnotations));
                return;
            }
            Linked<AnnotatedField> linked3 = this._fields;
            if (linked3 != null) {
                AnnotationMap _mergeAnnotations2 = _mergeAnnotations(0, linked3, this._ctorParameters, this._setters);
                Linked<AnnotatedField> linked4 = this._fields;
                this._fields = linked4.withValue(linked4.value.withAnnotations(_mergeAnnotations2));
                return;
            }
            return;
        }
        Linked<AnnotatedParameter> linked5 = this._ctorParameters;
        if (linked5 != null) {
            AnnotationMap _mergeAnnotations3 = _mergeAnnotations(0, linked5, this._setters, this._fields, this._getters);
            Linked<AnnotatedParameter> linked6 = this._ctorParameters;
            this._ctorParameters = linked6.withValue(linked6.value.withAnnotations(_mergeAnnotations3));
            return;
        }
        Linked<AnnotatedMethod> linked7 = this._setters;
        if (linked7 != null) {
            AnnotationMap _mergeAnnotations4 = _mergeAnnotations(0, linked7, this._fields, this._getters);
            Linked<AnnotatedMethod> linked8 = this._setters;
            this._setters = linked8.withValue(linked8.value.withAnnotations(_mergeAnnotations4));
            return;
        }
        Linked<AnnotatedField> linked9 = this._fields;
        if (linked9 != null) {
            AnnotationMap _mergeAnnotations5 = _mergeAnnotations(0, linked9, this._getters);
            Linked<AnnotatedField> linked10 = this._fields;
            this._fields = linked10.withValue(linked10.value.withAnnotations(_mergeAnnotations5));
        }
    }

    private AnnotationMap _mergeAnnotations(int i, Linked<? extends AnnotatedMember>... linkedArr) {
        AnnotationMap allAnnotations = linkedArr[i].value.getAllAnnotations();
        do {
            i++;
            if (i >= linkedArr.length) {
                return allAnnotations;
            }
        } while (linkedArr[i] == null);
        return AnnotationMap.merge(allAnnotations, _mergeAnnotations(i, linkedArr));
    }

    private <T> Linked<T> _removeIgnored(Linked<T> linked) {
        return linked == null ? linked : linked.withoutIgnored();
    }

    private <T> Linked<T> _removeNonVisible(Linked<T> linked) {
        return linked == null ? linked : linked.withoutNonVisible();
    }

    private <T> Linked<T> _trimByVisibility(Linked<T> linked) {
        return linked == null ? linked : linked.trimByVisibility();
    }

    private <T> boolean _anyExplicitNames(Linked<T> linked) {
        while (linked != null) {
            if (linked.explicitName != null && linked.explicitName.length() > 0) {
                return true;
            }
            linked = linked.next;
        }
        return false;
    }

    public boolean anyVisible() {
        return _anyVisible(this._fields) || _anyVisible(this._getters) || _anyVisible(this._setters) || _anyVisible(this._ctorParameters);
    }

    private <T> boolean _anyVisible(Linked<T> linked) {
        while (linked != null) {
            if (linked.isVisible) {
                return true;
            }
            linked = linked.next;
        }
        return false;
    }

    public boolean anyIgnorals() {
        return _anyIgnorals(this._fields) || _anyIgnorals(this._getters) || _anyIgnorals(this._setters) || _anyIgnorals(this._ctorParameters);
    }

    private <T> boolean _anyIgnorals(Linked<T> linked) {
        while (linked != null) {
            if (linked.isMarkedIgnored) {
                return true;
            }
            linked = linked.next;
        }
        return false;
    }

    public String findNewName() {
        Linked<? extends AnnotatedMember> findRenamed = findRenamed(this._ctorParameters, findRenamed(this._setters, findRenamed(this._getters, findRenamed(this._fields, null))));
        if (findRenamed == null) {
            return null;
        }
        return findRenamed.explicitName;
    }

    private Linked<? extends AnnotatedMember> findRenamed(Linked<? extends AnnotatedMember> linked, Linked<? extends AnnotatedMember> linked2) {
        while (linked != null) {
            String str = linked.explicitName;
            if (str != null && !str.equals(this._name)) {
                if (linked2 == null) {
                    linked2 = linked;
                } else if (!str.equals(linked2.explicitName)) {
                    throw new IllegalStateException("Conflicting property name definitions: '" + linked2.explicitName + "' (for " + ((Object) linked2.value) + ") vs '" + linked.explicitName + "' (for " + ((Object) linked.value) + ")");
                }
            }
            linked = linked.next;
        }
        return linked2;
    }

    public String toString() {
        return "[Property '" + this._name + "'; ctors: " + this._ctorParameters + ", field(s): " + this._fields + ", getter(s): " + this._getters + ", setter(s): " + this._setters + "]";
    }

    /* access modifiers changed from: protected */
    public <T> T fromMemberAnnotations(WithMember<T> withMember) {
        Linked<AnnotatedField> linked;
        Linked<AnnotatedMethod> linked2;
        T t = null;
        if (this._annotationIntrospector == null) {
            return null;
        }
        if (this._forSerialization) {
            Linked<AnnotatedMethod> linked3 = this._getters;
            if (linked3 != null) {
                t = withMember.withMember(linked3.value);
            }
        } else {
            Linked<AnnotatedParameter> linked4 = this._ctorParameters;
            if (linked4 != null) {
                t = withMember.withMember(linked4.value);
            }
            if (t == null && (linked2 = this._setters) != null) {
                t = withMember.withMember(linked2.value);
            }
        }
        return (t != null || (linked = this._fields) == null) ? t : withMember.withMember(linked.value);
    }

    /* access modifiers changed from: private */
    public static final class Linked<T> {
        public final String explicitName;
        public final boolean isMarkedIgnored;
        public final boolean isVisible;
        public final Linked<T> next;
        public final T value;

        public Linked(T t, Linked<T> linked, String str, boolean z, boolean z2) {
            this.value = t;
            this.next = linked;
            String str2 = null;
            if (str == null) {
                this.explicitName = null;
            } else {
                this.explicitName = str.length() != 0 ? str : str2;
            }
            this.isVisible = z;
            this.isMarkedIgnored = z2;
        }

        public Linked<T> withValue(T t) {
            if (t == this.value) {
                return this;
            }
            return new Linked<>(t, this.next, this.explicitName, this.isVisible, this.isMarkedIgnored);
        }

        public Linked<T> withNext(Linked<T> linked) {
            if (linked == this.next) {
                return this;
            }
            return new Linked<>(this.value, linked, this.explicitName, this.isVisible, this.isMarkedIgnored);
        }

        public Linked<T> withoutIgnored() {
            Linked<T> withoutIgnored;
            if (this.isMarkedIgnored) {
                Linked<T> linked = this.next;
                if (linked == null) {
                    return null;
                }
                return linked.withoutIgnored();
            }
            Linked<T> linked2 = this.next;
            return (linked2 == null || (withoutIgnored = linked2.withoutIgnored()) == this.next) ? this : withNext(withoutIgnored);
        }

        public Linked<T> withoutNonVisible() {
            Linked<T> linked = this.next;
            Linked<T> withoutNonVisible = linked == null ? null : linked.withoutNonVisible();
            return this.isVisible ? withNext(withoutNonVisible) : withoutNonVisible;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private Linked<T> append(Linked<T> linked) {
            Linked<T> linked2 = this.next;
            if (linked2 == null) {
                return withNext(linked);
            }
            return withNext(linked2.append(linked));
        }

        public Linked<T> trimByVisibility() {
            Linked<T> linked = this.next;
            if (linked == null) {
                return this;
            }
            Linked<T> trimByVisibility = linked.trimByVisibility();
            if (this.explicitName != null) {
                if (trimByVisibility.explicitName == null) {
                    return withNext(null);
                }
                return withNext(trimByVisibility);
            } else if (trimByVisibility.explicitName != null) {
                return trimByVisibility;
            } else {
                boolean z = this.isVisible;
                if (z == trimByVisibility.isVisible) {
                    return withNext(trimByVisibility);
                }
                return z ? withNext(null) : trimByVisibility;
            }
        }

        public String toString() {
            String str = this.value.toString() + "[visible=" + this.isVisible + "]";
            if (this.next == null) {
                return str;
            }
            return str + ", " + this.next.toString();
        }
    }
}
