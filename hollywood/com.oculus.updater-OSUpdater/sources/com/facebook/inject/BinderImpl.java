package com.facebook.inject;

import com.facebook.infer.annotation.Nullsafe;
import com.facebook.ultralight.UL;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.inject.Key;
import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

@Nullsafe(trustOnly = @Nullsafe.TrustList({}), value = Nullsafe.Mode.LOCAL)
public class BinderImpl implements Binder {
    @Nullable
    private List<Binding> mBindings;
    @Nullable
    private List<ComponentBinding> mComponentBindings;
    private final Class mDeclaringModule;
    private final FbInjector mInjector;
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
    public Map<Key, MultiBinding> getMultiBindings() {
        Map<Key, MultiBinding> map = this.mMultiBindings;
        return map != null ? map : ImmutableMap.of();
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
