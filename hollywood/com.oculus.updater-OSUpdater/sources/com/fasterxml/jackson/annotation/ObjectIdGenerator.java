package com.fasterxml.jackson.annotation;

import java.io.Serializable;

public abstract class ObjectIdGenerator<T> implements Serializable {
    public abstract ObjectIdGenerator<T> forScope(Class<?> cls);
}
