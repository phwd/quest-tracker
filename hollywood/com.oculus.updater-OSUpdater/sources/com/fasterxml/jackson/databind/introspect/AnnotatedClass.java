package com.fasterxml.jackson.databind.introspect;

public final class AnnotatedClass extends Annotated {
    private static final AnnotationMap[] NO_ANNOTATION_MAPS = new AnnotationMap[0];
    protected final Class<?> _class;

    @Override // com.fasterxml.jackson.databind.introspect.Annotated
    public String getName() {
        return this._class.getName();
    }

    public String toString() {
        return "[AnnotedClass " + this._class.getName() + "]";
    }
}
