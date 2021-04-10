package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.util.Annotations;

public interface BeanProperty {
    AnnotatedMember getMember();

    public static class Std implements BeanProperty {
        protected final Annotations _contextAnnotations;
        protected final boolean _isRequired;
        protected final AnnotatedMember _member;
        protected final String _name;
        protected final JavaType _type;
        protected final PropertyName _wrapperName;

        public Std(String str, JavaType javaType, PropertyName propertyName, Annotations annotations, AnnotatedMember annotatedMember, boolean z) {
            this._name = str;
            this._type = javaType;
            this._wrapperName = propertyName;
            this._isRequired = z;
            this._member = annotatedMember;
            this._contextAnnotations = annotations;
        }

        @Override // com.fasterxml.jackson.databind.BeanProperty
        public AnnotatedMember getMember() {
            return this._member;
        }
    }
}
