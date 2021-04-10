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

    public BinderImpl(FbInjector fbInjector, Class cls) {
        this.mInjector = fbInjector;
        this.mDeclaringModule = cls;
    }

    @Override // com.facebook.inject.Binder
    public <T> AnnotatedBindingBuilder<T> bind(Class<T> cls) {
        return new AnnotatedBindingBuilderImpl(addBinding(Key.get((Class) cls)));
    }

    @Override // com.facebook.inject.Binder
    public <T> AnnotatedBindingBuilder<T> bind(TypeLiteral<T> typeLiteral) {
        return new AnnotatedBindingBuilderImpl(addBinding(Key.get(typeLiteral)));
    }

    @Override // com.facebook.inject.Binder
    public <T> LinkedBindingBuilder<T> bind(Key<T> key) {
        return new AnnotatedBindingBuilderImpl(addBinding(key));
    }

    @Override // com.facebook.inject.Binder
    public <T> AnnotatedBindingBuilder<T> bindDefault(Class<T> cls) {
        Binding<T> addBinding = addBinding(Key.get((Class) cls));
        addBinding.setDefaultBinding(true);
        return new AnnotatedBindingBuilderImpl(addBinding);
    }

    @Override // com.facebook.inject.Binder
    public <T> AnnotatedBindingBuilder<T> bindDefault(TypeLiteral<T> typeLiteral) {
        Binding<T> addBinding = addBinding(Key.get(typeLiteral));
        addBinding.setDefaultBinding(true);
        return new AnnotatedBindingBuilderImpl(addBinding);
    }

    @Override // com.facebook.inject.Binder
    public <T> LinkedBindingBuilder<T> bindDefault(Key<T> key) {
        Binding<T> addBinding = addBinding(key);
        addBinding.setDefaultBinding(true);
        return new AnnotatedBindingBuilderImpl(addBinding);
    }

    @Override // com.facebook.inject.Binder
    public <T> LinkedComponentBindingBuilder<T> bindComponent(Class<T> cls) {
        return new LinkedComponentBindingBuilderImpl(addComponentBinding(Key.get((Class) cls)));
    }

    @Override // com.facebook.inject.Binder
    public <T> void bindAssistedProvider(Class<? extends AssistedProvider<T>> cls) {
        bind(cls).toProvider(new AssistedProviderProvider(cls));
    }

    @Override // com.facebook.inject.Binder
    public void declareMultiBinding(Class<?> cls) {
        declareMultiBinding(Key.get((Class) cls));
    }

    @Override // com.facebook.inject.Binder
    public void declareMultiBinding(Class<?> cls, Class<? extends Annotation> cls2) {
        declareMultiBinding(Key.get((Class) cls, cls2));
    }

    @Override // com.facebook.inject.Binder
    public void declareMultiBinding(Key<?> key) {
        if (this.mMultiBindingDeclarations == null) {
            this.mMultiBindingDeclarations = Sets.newHashSet();
        }
        this.mMultiBindingDeclarations.add(key);
    }

    @Override // com.facebook.inject.Binder
    public void declareListBinding(Class<?> cls) {
        declareListBinding(Key.get((Class) cls));
    }

    @Override // com.facebook.inject.Binder
    public void declareListBinding(Class<?> cls, Class<? extends Annotation> cls2) {
        declareListBinding(Key.get((Class) cls, cls2));
    }

    @Override // com.facebook.inject.Binder
    public void declareListBinding(Key<?> key) {
        if (this.mListDeclarations == null) {
            this.mListDeclarations = Sets.newHashSet();
        }
        this.mListDeclarations.add(key);
    }

    @Override // com.facebook.inject.Binder
    public <T> MultiBinding<T> bindMulti(Class<T> cls) {
        return addMultiBinding(Key.get((Class) cls));
    }

    @Override // com.facebook.inject.Binder
    public <T> MultiBinding<T> bindMulti(Class<T> cls, Class<? extends Annotation> cls2) {
        return addMultiBinding(Key.get((Class) cls, cls2));
    }

    @Override // com.facebook.inject.Binder
    public <T> MultiBinding<T> bindMulti(Key<T> key) {
        return addMultiBinding(key);
    }

    @Override // com.facebook.inject.Binder
    public <T> ListBinding<T> bindList(Class<T> cls) {
        return addListBinding(Key.get((Class) cls));
    }

    @Override // com.facebook.inject.Binder
    public <T> ListBinding<T> bindList(Class<T> cls, Class<? extends Annotation> cls2) {
        return addListBinding(Key.get((Class) cls, cls2));
    }

    @Override // com.facebook.inject.Binder
    public <T> ListBinding<T> bindList(Key<T> key) {
        return addListBinding(key);
    }

    @Override // com.facebook.inject.Binder
    public void bindScope(Class<? extends Annotation> cls, Scope scope) {
        if (this.mScopes == null) {
            this.mScopes = Maps.newHashMap();
        }
        this.mScopes.put(cls, scope);
    }

    @Override // com.facebook.inject.Binder
    public List<Binding> getBindings() {
        ImmutableList.Builder builder = ImmutableList.builder();
        List<Binding> list = this.mBindings;
        if (list != null) {
            HashSet hashSet = new HashSet(list.size());
            for (Binding binding : this.mBindings) {
                int dynamicId = UL.id.dynamicId(binding.getKey());
                if (!hashSet.contains(Integer.valueOf(dynamicId))) {
                    hashSet.add(Integer.valueOf(dynamicId));
                    builder.add((Object) binding);
                }
            }
        }
        return builder.build();
    }

    @Override // com.facebook.inject.Binder
    public List<ComponentBinding> getComponentBindings() {
        List<ComponentBinding> list = this.mComponentBindings;
        return list != null ? list : ImmutableList.of();
    }

    @Override // com.facebook.inject.Binder
    public Set<Key> getMultiBindingDeclarations() {
        Set<Key> set = this.mMultiBindingDeclarations;
        return set != null ? set : ImmutableSet.of();
    }

    @Override // com.facebook.inject.Binder
    public Set<Key> getListDeclarations() {
        Set<Key> set = this.mListDeclarations;
        return set != null ? set : ImmutableSet.of();
    }

    @Override // com.facebook.inject.Binder
    public Map<Key, MultiBinding> getMultiBindings() {
        Map<Key, MultiBinding> map = this.mMultiBindings;
        return map != null ? map : ImmutableMap.of();
    }

    @Override // com.facebook.inject.Binder
    public Map<Key, ListBinding> getListBindings() {
        Map<Key, ListBinding> map = this.mListBindings;
        return map != null ? map : ImmutableMap.of();
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
        ComponentBinding<T> componentBinding = new ComponentBinding<>();
        componentBinding.setModule(this.mDeclaringModule);
        componentBinding.setKey(key);
        this.mComponentBindings.add(componentBinding);
        return componentBinding;
    }

    private <T> MultiBinding<T> addMultiBinding(Key<T> key) {
        if (this.mMultiBindings == null) {
            this.mMultiBindings = Maps.newHashMap();
        }
        MultiBinding<T> multiBinding = this.mMultiBindings.get(key);
        if (multiBinding != null) {
            return multiBinding;
        }
        MultiBinding<T> multiBinding2 = new MultiBinding<>(key);
        this.mMultiBindings.put(key, multiBinding2);
        return multiBinding2;
    }

    private <T> ListBinding<T> addListBinding(Key<T> key) {
        if (this.mListBindings == null) {
            this.mListBindings = Maps.newHashMap();
        }
        ListBinding<T> listBinding = this.mListBindings.get(key);
        if (listBinding != null) {
            return listBinding;
        }
        ListBinding<T> listBinding2 = new ListBinding<>(key);
        this.mListBindings.put(key, listBinding2);
        return listBinding2;
    }

    @Override // com.facebook.inject.Binder
    public <T> void assertBindingInstalled(Class<T> cls) {
        if (this.mAssertedBindings == null) {
            this.mAssertedBindings = Sets.newHashSet();
        }
        this.mAssertedBindings.add(Key.get((Class) cls));
    }

    @Override // com.facebook.inject.Binder
    public <T> void assertBindingInstalled(Class<T> cls, Class<? extends Annotation> cls2) {
        if (this.mAssertedBindings == null) {
            this.mAssertedBindings = Sets.newHashSet();
        }
        this.mAssertedBindings.add(Key.get((Class) cls, cls2));
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
        Set<Key> set = this.mAssertedBindings;
        return set != null ? set : ImmutableSet.of();
    }

    private void addDependency(Class<?> cls) {
        if (this.mDependencies == null) {
            this.mDependencies = Sets.newHashSet();
        }
        this.mDependencies.add(cls);
    }

    @Override // com.facebook.inject.Binder
    public Set<Class<?>> getDependencies() {
        Set<Class<?>> set = this.mDependencies;
        return set == null ? ImmutableSet.of() : set;
    }

    @Override // com.facebook.inject.Binder
    public void require(Class<?> cls) {
        if (this.mRequiredModules == null) {
            this.mRequiredModules = new ArrayList();
        }
        this.mRequiredModules.add(cls);
        addDependency(cls);
    }

    @Override // com.facebook.inject.Binder
    public List<Class<?>> getRequiredModules() {
        List<Class<?>> list = this.mRequiredModules;
        return list != null ? list : ImmutableList.of();
    }

    @Override // com.facebook.inject.Binder
    public Map<Class<? extends Annotation>, Scope> getScopes() {
        Map<Class<? extends Annotation>, Scope> map = this.mScopes;
        return map == null ? ImmutableMap.of() : map;
    }

    @Override // com.facebook.inject.Binder
    public Injector getInjector() {
        return this.mInjector;
    }
}
