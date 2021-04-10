package com.fasterxml.jackson.annotation;

public class ObjectIdGenerators {

    private static abstract class Base<T> extends ObjectIdGenerator<T> {
        protected final Class<?> _scope;

        protected Base(Class<?> cls) {
            this._scope = cls;
        }
    }

    public static abstract class PropertyGenerator extends Base<Object> {
        private static final long serialVersionUID = 1;

        protected PropertyGenerator(Class<?> cls) {
            super(cls);
        }
    }
}
