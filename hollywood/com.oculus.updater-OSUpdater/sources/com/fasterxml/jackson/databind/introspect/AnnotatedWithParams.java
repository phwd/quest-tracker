package com.fasterxml.jackson.databind.introspect;

public abstract class AnnotatedWithParams extends AnnotatedMember {
    private static final long serialVersionUID = 1;
    protected final AnnotationMap[] _paramAnnotations;

    protected AnnotatedWithParams(AnnotationMap annotationMap, AnnotationMap[] annotationMapArr) {
        super(annotationMap);
        this._paramAnnotations = annotationMapArr;
    }
}
