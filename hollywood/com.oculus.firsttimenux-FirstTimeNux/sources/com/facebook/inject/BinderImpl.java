package com.facebook.inject;

import com.facebook.infer.annotation.Nullsafe;
import com.facebook.inject.binder.AnnotatedBindingBuilder;
import com.facebook.inject.binder.AnnotatedBindingBuilderImpl;
import com.facebook.inject.binder.LinkedBindingBuilder;
import com.facebook.inject.binder.LinkedComponentBindingBuilder;
import com.facebook.inject.binder.LinkedComponentBindingBuilderImpl;
import com.facebook.ultralight.UL;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

@Nullsafe(trustOnly = @Nullsafe.TrustList({}), value = Nullsafe.Mode.LOCAL)
public class BinderImpl implements Binder {
    @Nullable
    private Set<Key> mAssertedBindings;
    @Nullable
    private List<Binding> mBindings;
    @Nullable
    private List<ComponentBinding> mComponentBindings;
    private final Class mDeclaringModule;
    @Nullable
    private Set<Class<?>> mDependencies;
    private final FbInjector mInjector;
    @Nullable
    private Map<Key, ListBinding> mListBindings;
    @Nullable
    private Set<Key> mListDeclarations;
    @Nullable
    private Set<Key> mMultiBindingDeclarations;
    @Nullable
    private Map<Key, MultiBinding> mMultiBindings;
    @Nullable
    private List<Class<?>> mRequiredModules;
    @Nullable
    private Map<Class<? extends Annotation>, Scope> mScopes;

    public BinderImpl(FbInjector injector, Class declaringModule) {
        this.mInjector = injector;
        this.mDeclaringModule = declaringModule;
    }

    @Override // com.facebook.inject.Binder
    public <T> AnnotatedBindingBuilder<T> bind(Class<T> type) {
        return new AnnotatedBindingBuilderImpl(addBinding(Key.get((Class) type)));
    }

    @Override // com.facebook.inject.Binder
    public <T> AnnotatedBindingBuilder<T> bind(TypeLiteral<T> type) {
        return new AnnotatedBindingBuilderImpl(addBinding(Key.get(type)));
    }

    @Override // com.facebook.inject.Binder
    public <T> LinkedBindingBuilder<T> bind(Key<T> key) {
        return new AnnotatedBindingBuilderImpl(addBinding(key));
    }

    @Override // com.facebook.inject.Binder
    public <T> AnnotatedBindingBuilder<T> bindDefault(Class<T> type) {
        Binding<T> binding = addBinding(Key.get((Class) type));
        binding.setDefaultBinding(true);
        return new AnnotatedBindingBuilderImpl(binding);
    }

    @Override // com.facebook.inject.Binder
    public <T> AnnotatedBindingBuilder<T> bindDefault(TypeLiteral<T> type) {
        Binding<T> binding = addBinding(Key.get(type));
        binding.setDefaultBinding(true);
        return new AnnotatedBindingBuilderImpl(binding);
    }

    @Override // com.facebook.inject.Binder
    public <T> LinkedBindingBuilder<T> bindDefault(Key<T> key) {
        Binding<T> binding = addBinding(key);
        binding.setDefaultBinding(true);
        return new AnnotatedBindingBuilderImpl(binding);
    }

    @Override // com.facebook.inject.Binder
    public <T> LinkedComponentBindingBuilder<T> bindComponent(Class<T> type) {
        return new LinkedComponentBindingBuilderImpl(addComponentBinding(Key.get((Class) type)));
    }

    @Override // com.facebook.inject.Binder
    public <T> void bindAssistedProvider(Class<? extends AssistedProvider<T>> assistedProviderClass) {
        bind(assistedProviderClass).toProvider(new AssistedProviderProvider(assistedProviderClass));
    }

    @Override // com.facebook.inject.Binder
    public void declareMultiBinding(Class<?> key) {
        declareMultiBinding(Key.get((Class) key));
    }

    @Override // com.facebook.inject.Binder
    public void declareMultiBinding(Class<?> key, Class<? extends Annotation> annotation) {
        declareMultiBinding(Key.get((Class) key, annotation));
    }

    @Override // com.facebook.inject.Binder
    public void declareMultiBinding(Key<?> key) {
        if (this.mMultiBindingDeclarations == null) {
            this.mMultiBindingDeclarations = Sets.newHashSet();
        }
        this.mMultiBindingDeclarations.add(key);
    }

    @Override // com.facebook.inject.Binder
    public void declareListBinding(Class<?> key) {
        declareListBinding(Key.get((Class) key));
    }

    @Override // com.facebook.inject.Binder
    public void declareListBinding(Class<?> key, Class<? extends Annotation> annotation) {
        declareListBinding(Key.get((Class) key, annotation));
    }

    @Override // com.facebook.inject.Binder
    public void declareListBinding(Key<?> key) {
        if (this.mListDeclarations == null) {
            this.mListDeclarations = Sets.newHashSet();
        }
        this.mListDeclarations.add(key);
    }

    @Override // com.facebook.inject.Binder
    public <T> MultiBinding<T> bindMulti(Class<T> type) {
        return addMultiBinding(Key.get((Class) type));
    }

    @Override // com.facebook.inject.Binder
    public <T> MultiBinding<T> bindMulti(Class<T> type, Class<? extends Annotation> annotation) {
        return addMultiBinding(Key.get((Class) type, annotation));
    }

    @Override // com.facebook.inject.Binder
    public <T> MultiBinding<T> bindMulti(Key<T> key) {
        return addMultiBinding(key);
    }

    @Override // com.facebook.inject.Binder
    public <T> ListBinding<T> bindList(Class<T> type) {
        return addListBinding(Key.get((Class) type));
    }

    @Override // com.facebook.inject.Binder
    public <T> ListBinding<T> bindList(Class<T> type, Class<? extends Annotation> annotation) {
        return addListBinding(Key.get((Class) type, annotation));
    }

    @Override // com.facebook.inject.Binder
    public <T> ListBinding<T> bindList(Key<T> key) {
        return addListBinding(key);
    }

    @Override // com.facebook.inject.Binder
    public void bindScope(Class<? extends Annotation> annotation, Scope scope) {
        if (this.mScopes == null) {
            this.mScopes = Maps.newHashMap();
        }
        this.mScopes.put(annotation, scope);
    }

    @Override // com.facebook.inject.Binder
    public List<Binding> getBindings() {
        ImmutableList.Builder builder = ImmutableList.builder();
        if (this.mBindings != null) {
            HashSet<Integer> keys = new HashSet<>(this.mBindings.size());
            for (Binding binding : this.mBindings) {
                int key = UL.id.dynamicId(binding.getKey());
                if (!keys.contains(Integer.valueOf(key))) {
                    keys.add(Integer.valueOf(key));
                    builder.add((Object) binding);
                }
            }
        }
        return builder.build();
    }

    @Override // com.facebook.inject.Binder
    public List<ComponentBinding> getComponentBindings() {
        return this.mComponentBindings != null ? this.mComponentBindings : ImmutableList.of();
    }

    @Override // com.facebook.inject.Binder
    public Set<Key> getMultiBindingDeclarations() {
        return this.mMultiBindingDeclarations != null ? this.mMultiBindingDeclarations : ImmutableSet.of();
    }

    @Override // com.facebook.inject.Binder
    public Set<Key> getListDeclarations() {
        return this.mListDeclarations != null ? this.mListDeclarations : ImmutableSet.of();
    }

    @Override // com.facebook.inject.Binder
    public Map<Key, MultiBinding> getMultiBindings() {
        return this.mMultiBindings != null ? this.mMultiBindings : ImmutableMap.of();
    }

    @Override // com.facebook.inject.Binder
    public Map<Key, ListBinding> getListBindings() {
        return this.mListBindings != null ? this.mListBindings : ImmutableMap.of();
    }

    private <T> Binding<T> addBinding(Key<T> key) {
        if (this.mBindings == null) {
            this.mBindings = Lists.newArrayList();
        }
        Binding<T> binding = new Binding<>();
        binding.setModule(this.mDeclaringModule);
        binding.setKey(key);
        this.mBindings.add(binding);
        return binding;
    }

    private <T> ComponentBinding<T> addComponentBinding(Key<T> key) {
        if (this.mComponentBindings == null) {
            this.mComponentBindings = Lists.newArrayList();
        }
        ComponentBinding<T> binding = new ComponentBinding<>();
        binding.setModule(this.mDeclaringModule);
        binding.setKey(key);
        this.mComponentBindings.add(binding);
        return binding;
    }

    private <T> MultiBinding<T> addMultiBinding(Key<T> key) {
        if (this.mMultiBindings == null) {
            this.mMultiBindings = Maps.newHashMap();
        }
        MultiBinding multiBinding = this.mMultiBindings.get(key);
        if (multiBinding != null) {
            return multiBinding;
        }
        MultiBinding multiBinding2 = new MultiBinding(key);
        this.mMultiBindings.put(key, multiBinding2);
        return multiBinding2;
    }

    private <T> ListBinding<T> addListBinding(Key<T> key) {
        if (this.mListBindings == null) {
            this.mListBindings = Maps.newHashMap();
        }
        ListBinding listBinding = this.mListBindings.get(key);
        if (listBinding != null) {
            return listBinding;
        }
        ListBinding listBinding2 = new ListBinding(key);
        this.mListBindings.put(key, listBinding2);
        return listBinding2;
    }

    @Override // com.facebook.inject.Binder
    public <T> void assertBindingInstalled(Class<T> type) {
        if (this.mAssertedBindings == null) {
            this.mAssertedBindings = Sets.newHashSet();
        }
        this.mAssertedBindings.add(Key.get((Class) type));
    }

    @Override // com.facebook.inject.Binder
    public <T> void assertBindingInstalled(Class<T> type, Class<? extends Annotation> annotatedWith) {
        if (this.mAssertedBindings == null) {
            this.mAssertedBindings = Sets.newHashSet();
        }
        this.mAssertedBindings.add(Key.get((Class) type, annotatedWith));
    }

    @Override // com.facebook.inject.Binder
    public <T> void assertBindingInstalled(Key<T> key) {
        if (this.mAssertedBindings == null) {
            this.mAssertedBindings = Sets.newHashSet();
        }
        this.mAssertedBindings.add(key);
    }

    @Override // com.facebook.inject.Binder
    public Set<Key> getAssertedBindings() {
        return this.mAssertedBindings != null ? this.mAssertedBindings : ImmutableSet.of();
    }

    private void addDependency(Class<?> moduleClass) {
        if (this.mDependencies == null) {
            this.mDependencies = Sets.newHashSet();
        }
        this.mDependencies.add(moduleClass);
    }

    @Override // com.facebook.inject.Binder
    public Set<Class<?>> getDependencies() {
        if (this.mDependencies == null) {
            return ImmutableSet.of();
        }
        return this.mDependencies;
    }

    @Override // com.facebook.inject.Binder
    public void require(Class<?> moduleClass) {
        if (this.mRequiredModules == null) {
            this.mRequiredModules = new ArrayList();
        }
        this.mRequiredModules.add(moduleClass);
        addDependency(moduleClass);
    }

    @Override // com.facebook.inject.Binder
    public List<Class<?>> getRequiredModules() {
        return this.mRequiredModules != null ? this.mRequiredModules : ImmutableList.of();
    }

    @Override // com.facebook.inject.Binder
    public Map<Class<? extends Annotation>, Scope> getScopes() {
        return this.mScopes == null ? ImmutableMap.of() : this.mScopes;
    }

    @Override // com.facebook.inject.Binder
    public Injector getInjector() {
        return this.mInjector;
    }
}
