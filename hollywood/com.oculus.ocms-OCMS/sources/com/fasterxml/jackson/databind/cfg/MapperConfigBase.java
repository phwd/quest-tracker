package com.fasterxml.jackson.databind.cfg;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.cfg.ConfigFeature;
import com.fasterxml.jackson.databind.cfg.MapperConfigBase;
import com.fasterxml.jackson.databind.introspect.ClassIntrospector;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.databind.jsontype.SubtypeResolver;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.type.ClassKey;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public abstract class MapperConfigBase<CFG extends ConfigFeature, T extends MapperConfigBase<CFG, T>> extends MapperConfig<T> implements Serializable {
    private static final int DEFAULT_MAPPER_FEATURES = collectFeatureDefaults(MapperFeature.class);
    private static final long serialVersionUID = -8378230381628000111L;
    protected final Map<ClassKey, Class<?>> _mixInAnnotations;
    protected final String _rootName;
    protected final SubtypeResolver _subtypeResolver;
    protected final Class<?> _view;

    public abstract T with(Base64Variant base64Variant);

    public abstract T with(AnnotationIntrospector annotationIntrospector);

    public abstract T with(PropertyNamingStrategy propertyNamingStrategy);

    public abstract T with(HandlerInstantiator handlerInstantiator);

    public abstract T with(ClassIntrospector classIntrospector);

    public abstract T with(VisibilityChecker<?> visibilityChecker);

    public abstract T with(SubtypeResolver subtypeResolver);

    public abstract T with(TypeResolverBuilder<?> typeResolverBuilder);

    public abstract T with(TypeFactory typeFactory);

    public abstract T with(DateFormat dateFormat);

    public abstract T with(Locale locale);

    public abstract T with(TimeZone timeZone);

    public abstract T withAppendedAnnotationIntrospector(AnnotationIntrospector annotationIntrospector);

    public abstract T withInsertedAnnotationIntrospector(AnnotationIntrospector annotationIntrospector);

    public abstract T withRootName(String str);

    public abstract T withView(Class<?> cls);

    public abstract T withVisibility(PropertyAccessor propertyAccessor, JsonAutoDetect.Visibility visibility);

    protected MapperConfigBase(BaseSettings baseSettings, SubtypeResolver subtypeResolver, Map<ClassKey, Class<?>> map) {
        super(baseSettings, DEFAULT_MAPPER_FEATURES);
        this._mixInAnnotations = map;
        this._subtypeResolver = subtypeResolver;
        this._rootName = null;
        this._view = null;
    }

    protected MapperConfigBase(MapperConfigBase<CFG, T> mapperConfigBase) {
        super(mapperConfigBase);
        this._mixInAnnotations = mapperConfigBase._mixInAnnotations;
        this._subtypeResolver = mapperConfigBase._subtypeResolver;
        this._rootName = mapperConfigBase._rootName;
        this._view = mapperConfigBase._view;
    }

    protected MapperConfigBase(MapperConfigBase<CFG, T> mapperConfigBase, BaseSettings baseSettings) {
        super(baseSettings, mapperConfigBase._mapperFeatures);
        this._mixInAnnotations = mapperConfigBase._mixInAnnotations;
        this._subtypeResolver = mapperConfigBase._subtypeResolver;
        this._rootName = mapperConfigBase._rootName;
        this._view = mapperConfigBase._view;
    }

    protected MapperConfigBase(MapperConfigBase<CFG, T> mapperConfigBase, int i) {
        super(mapperConfigBase._base, i);
        this._mixInAnnotations = mapperConfigBase._mixInAnnotations;
        this._subtypeResolver = mapperConfigBase._subtypeResolver;
        this._rootName = mapperConfigBase._rootName;
        this._view = mapperConfigBase._view;
    }

    protected MapperConfigBase(MapperConfigBase<CFG, T> mapperConfigBase, SubtypeResolver subtypeResolver) {
        super(mapperConfigBase);
        this._mixInAnnotations = mapperConfigBase._mixInAnnotations;
        this._subtypeResolver = subtypeResolver;
        this._rootName = mapperConfigBase._rootName;
        this._view = mapperConfigBase._view;
    }

    protected MapperConfigBase(MapperConfigBase<CFG, T> mapperConfigBase, String str) {
        super(mapperConfigBase);
        this._mixInAnnotations = mapperConfigBase._mixInAnnotations;
        this._subtypeResolver = mapperConfigBase._subtypeResolver;
        this._rootName = str;
        this._view = mapperConfigBase._view;
    }

    protected MapperConfigBase(MapperConfigBase<CFG, T> mapperConfigBase, Class<?> cls) {
        super(mapperConfigBase);
        this._mixInAnnotations = mapperConfigBase._mixInAnnotations;
        this._subtypeResolver = mapperConfigBase._subtypeResolver;
        this._rootName = mapperConfigBase._rootName;
        this._view = cls;
    }

    protected MapperConfigBase(MapperConfigBase<CFG, T> mapperConfigBase, Map<ClassKey, Class<?>> map) {
        super(mapperConfigBase);
        this._mixInAnnotations = map;
        this._subtypeResolver = mapperConfigBase._subtypeResolver;
        this._rootName = mapperConfigBase._rootName;
        this._view = mapperConfigBase._view;
    }

    @Override // com.fasterxml.jackson.databind.cfg.MapperConfig
    public final SubtypeResolver getSubtypeResolver() {
        return this._subtypeResolver;
    }

    public final String getRootName() {
        return this._rootName;
    }

    @Override // com.fasterxml.jackson.databind.cfg.MapperConfig
    public final Class<?> getActiveView() {
        return this._view;
    }

    @Override // com.fasterxml.jackson.databind.introspect.ClassIntrospector.MixInResolver
    public final Class<?> findMixInClassFor(Class<?> cls) {
        Map<ClassKey, Class<?>> map = this._mixInAnnotations;
        if (map == null) {
            return null;
        }
        return map.get(new ClassKey(cls));
    }

    public final int mixInCount() {
        Map<ClassKey, Class<?>> map = this._mixInAnnotations;
        if (map == null) {
            return 0;
        }
        return map.size();
    }
}
