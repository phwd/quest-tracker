package com.fasterxml.jackson.databind.introspect;

import java.io.Serializable;

public abstract class AnnotatedMember extends Annotated implements Serializable {
    private static final long serialVersionUID = 7364428299211355871L;
    protected final transient AnnotationMap _annotations;

    public abstract void setValue(Object obj, Object obj2) throws UnsupportedOperationException, IllegalArgumentException;

    protected AnnotatedMember(AnnotationMap annotationMap) {
        this._annotations = annotationMap;
    }
}
