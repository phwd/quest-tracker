package com.google.inject.internal;

import com.google.common.base.Function;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.inject.BindingAnnotation;
import com.google.inject.ScopeAnnotation;
import com.google.inject.name.Names;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
import java.util.Collection;
import javax.inject.Named;
import javax.inject.Qualifier;
import javax.inject.Scope;

public class Annotations {
    private static final AnnotationChecker bindingAnnotationChecker = new AnnotationChecker(Arrays.asList(BindingAnnotation.class, Qualifier.class));
    private static final AnnotationChecker scopeChecker = new AnnotationChecker(Arrays.asList(ScopeAnnotation.class, Scope.class));

    public static boolean isMarker(Class<? extends Annotation> cls) {
        return cls.getDeclaredMethods().length == 0;
    }

    public static boolean isRetainedAtRuntime(Class<? extends Annotation> cls) {
        Retention retention = (Retention) cls.getAnnotation(Retention.class);
        return retention != null && retention.value() == RetentionPolicy.RUNTIME;
    }

    /* access modifiers changed from: package-private */
    public static class AnnotationChecker {
        private final Collection<Class<? extends Annotation>> annotationTypes;
        final LoadingCache<Class<? extends Annotation>, Boolean> cache = CacheBuilder.newBuilder().weakKeys().build(this.hasAnnotations);
        private CacheLoader<Class<? extends Annotation>, Boolean> hasAnnotations = CacheLoader.from(new Function<Class<? extends Annotation>, Boolean>() {
            /* class com.google.inject.internal.Annotations.AnnotationChecker.AnonymousClass1 */

            public Boolean apply(Class<? extends Annotation> cls) {
                for (Annotation annotation : cls.getAnnotations()) {
                    if (AnnotationChecker.this.annotationTypes.contains(annotation.annotationType())) {
                        return true;
                    }
                }
                return false;
            }
        });

        AnnotationChecker(Collection<Class<? extends Annotation>> collection) {
            this.annotationTypes = collection;
        }

        /* access modifiers changed from: package-private */
        public boolean hasAnnotations(Class<? extends Annotation> cls) {
            return this.cache.getUnchecked(cls).booleanValue();
        }
    }

    public static boolean isScopeAnnotation(Class<? extends Annotation> cls) {
        return scopeChecker.hasAnnotations(cls);
    }

    public static boolean isBindingAnnotation(Class<? extends Annotation> cls) {
        return bindingAnnotationChecker.hasAnnotations(cls);
    }

    public static Annotation canonicalizeIfNamed(Annotation annotation) {
        return annotation instanceof Named ? Names.named(((Named) annotation).value()) : annotation;
    }

    public static Class<? extends Annotation> canonicalizeIfNamed(Class<? extends Annotation> cls) {
        return cls == Named.class ? com.google.inject.name.Named.class : cls;
    }
}
