package com.facebook.inject;

import com.facebook.infer.annotation.Nullsafe;
import com.google.inject.Key;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Nullsafe(Nullsafe.Mode.STRICT)
public interface Binder {
    void bindScope(Class<? extends Annotation> cls, Scope scope);

    List<Binding> getBindings();

    List<ComponentBinding> getComponentBindings();

    Injector getInjector();

    Set<Key> getMultiBindingDeclarations();

    Map<Key, MultiBinding> getMultiBindings();

    List<Class<?>> getRequiredModules();

    Map<Class<? extends Annotation>, Scope> getScopes();
}
