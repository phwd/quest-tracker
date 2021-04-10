package com.facebook.inject;

import com.facebook.infer.annotation.Nullsafe;
import com.facebook.inject.binder.AnnotatedBindingBuilder;
import com.facebook.inject.binder.LinkedBindingBuilder;
import com.facebook.inject.binder.LinkedComponentBindingBuilder;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Nullsafe(Nullsafe.Mode.STRICT)
public interface Binder {
    <T> void assertBindingInstalled(Key<T> key);

    <T> void assertBindingInstalled(Class<T> cls);

    <T> void assertBindingInstalled(Class<T> cls, Class<? extends Annotation> cls2);

    <T> AnnotatedBindingBuilder<T> bind(TypeLiteral<T> typeLiteral);

    <T> AnnotatedBindingBuilder<T> bind(Class<T> cls);

    <T> LinkedBindingBuilder<T> bind(Key<T> key);

    <T> void bindAssistedProvider(Class<? extends AssistedProvider<T>> cls);

    <T> LinkedComponentBindingBuilder<T> bindComponent(Class<T> cls);

    <T> AnnotatedBindingBuilder<T> bindDefault(TypeLiteral<T> typeLiteral);

    <T> AnnotatedBindingBuilder<T> bindDefault(Class<T> cls);

    <T> LinkedBindingBuilder<T> bindDefault(Key<T> key);

    <T> ListBinding<T> bindList(Key<T> key);

    <T> ListBinding<T> bindList(Class<T> cls);

    <T> ListBinding<T> bindList(Class<T> cls, Class<? extends Annotation> cls2);

    <T> MultiBinding<T> bindMulti(Key<T> key);

    <T> MultiBinding<T> bindMulti(Class<T> cls);

    <T> MultiBinding<T> bindMulti(Class<T> cls, Class<? extends Annotation> cls2);

    void bindScope(Class<? extends Annotation> cls, Scope scope);

    void declareListBinding(Key<?> key);

    void declareListBinding(Class<?> cls);

    void declareListBinding(Class<?> cls, Class<? extends Annotation> cls2);

    void declareMultiBinding(Key<?> key);

    void declareMultiBinding(Class<?> cls);

    void declareMultiBinding(Class<?> cls, Class<? extends Annotation> cls2);

    Set<Key> getAssertedBindings();

    List<Binding> getBindings();

    List<ComponentBinding> getComponentBindings();

    Set<Class<?>> getDependencies();

    Injector getInjector();

    Map<Key, ListBinding> getListBindings();

    Set<Key> getListDeclarations();

    Set<Key> getMultiBindingDeclarations();

    Map<Key, MultiBinding> getMultiBindings();

    List<Class<?>> getRequiredModules();

    Map<Class<? extends Annotation>, Scope> getScopes();

    void require(Class<?> cls);
}
