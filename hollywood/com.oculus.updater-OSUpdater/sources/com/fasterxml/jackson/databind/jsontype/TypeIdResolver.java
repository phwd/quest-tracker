package com.fasterxml.jackson.databind.jsontype;

public interface TypeIdResolver {
    String idFromValueAndType(Object obj, Class<?> cls);
}
