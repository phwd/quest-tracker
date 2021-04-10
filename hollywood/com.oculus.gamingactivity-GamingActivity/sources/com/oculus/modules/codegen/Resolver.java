package com.oculus.modules.codegen;

public interface Resolver<T> {
    void reject(Throwable th);

    void resolve(T t);
}
