package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.util.Annotations;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

public final class ManagedReferenceProperty extends SettableBeanProperty {
    private static final long serialVersionUID = 1;
    protected final SettableBeanProperty _backProperty;
    protected final boolean _isContainer;
    protected final SettableBeanProperty _managedProperty;
    protected final String _referenceName;

    public ManagedReferenceProperty(SettableBeanProperty settableBeanProperty, String str, SettableBeanProperty settableBeanProperty2, Annotations annotations, boolean z) {
        super(settableBeanProperty.getName(), settableBeanProperty.getType(), settableBeanProperty.getWrapperName(), settableBeanProperty.getValueTypeDeserializer(), annotations, settableBeanProperty.isRequired());
        this._referenceName = str;
        this._managedProperty = settableBeanProperty;
        this._backProperty = settableBeanProperty2;
        this._isContainer = z;
    }

    protected ManagedReferenceProperty(ManagedReferenceProperty managedReferenceProperty, JsonDeserializer<?> jsonDeserializer) {
        super(managedReferenceProperty, jsonDeserializer);
        this._referenceName = managedReferenceProperty._referenceName;
        this._isContainer = managedReferenceProperty._isContainer;
        this._managedProperty = managedReferenceProperty._managedProperty;
        this._backProperty = managedReferenceProperty._backProperty;
    }

    protected ManagedReferenceProperty(ManagedReferenceProperty managedReferenceProperty, String str) {
        super(managedReferenceProperty, str);
        this._referenceName = managedReferenceProperty._referenceName;
        this._isContainer = managedReferenceProperty._isContainer;
        this._managedProperty = managedReferenceProperty._managedProperty;
        this._backProperty = managedReferenceProperty._backProperty;
    }

    @Override // com.fasterxml.jackson.databind.deser.SettableBeanProperty
    public ManagedReferenceProperty withName(String str) {
        return new ManagedReferenceProperty(this, str);
    }

    @Override // com.fasterxml.jackson.databind.deser.SettableBeanProperty
    public ManagedReferenceProperty withValueDeserializer(JsonDeserializer<?> jsonDeserializer) {
        return new ManagedReferenceProperty(this, jsonDeserializer);
    }

    @Override // com.fasterxml.jackson.databind.BeanProperty, com.fasterxml.jackson.databind.deser.SettableBeanProperty
    public AnnotatedMember getMember() {
        return this._managedProperty.getMember();
    }

    @Override // com.fasterxml.jackson.databind.deser.SettableBeanProperty
    public void deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException, JsonProcessingException {
        set(obj, this._managedProperty.deserialize(jsonParser, deserializationContext));
    }

    @Override // com.fasterxml.jackson.databind.deser.SettableBeanProperty
    public Object deserializeSetAndReturn(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException, JsonProcessingException {
        return setAndReturn(obj, deserialize(jsonParser, deserializationContext));
    }

    @Override // com.fasterxml.jackson.databind.deser.SettableBeanProperty
    public final void set(Object obj, Object obj2) throws IOException {
        setAndReturn(obj, obj2);
    }

    @Override // com.fasterxml.jackson.databind.deser.SettableBeanProperty
    public Object setAndReturn(Object obj, Object obj2) throws IOException {
        Object andReturn = this._managedProperty.setAndReturn(obj, obj2);
        if (obj2 != null) {
            if (!this._isContainer) {
                this._backProperty.set(obj2, obj);
            } else if (obj2 instanceof Object[]) {
                Object[] objArr = (Object[]) obj2;
                for (Object obj3 : objArr) {
                    if (obj3 != null) {
                        this._backProperty.set(obj3, obj);
                    }
                }
            } else if (obj2 instanceof Collection) {
                for (Object obj4 : (Collection) obj2) {
                    if (obj4 != null) {
                        this._backProperty.set(obj4, obj);
                    }
                }
            } else if (obj2 instanceof Map) {
                for (Object obj5 : ((Map) obj2).values()) {
                    if (obj5 != null) {
                        this._backProperty.set(obj5, obj);
                    }
                }
            } else {
                throw new IllegalStateException("Unsupported container type (" + obj2.getClass().getName() + ") when resolving reference '" + this._referenceName + "'");
            }
        }
        return andReturn;
    }
}
