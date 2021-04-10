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

    public static boolean isMarker(Class<? extends Annotation> annotationType) {
        return annotationType.getDeclaredMethods().length == 0;
    }

    public static boolean isRetainedAtRuntime(Class<? extends Annotation> annotationType) {
        Retention retention = (Retention) annotationType.getAnnotation(Retention.class);
        return retention != null && retention.value() == RetentionPolicy.RUNTIME;
    }

    /* access modifiers changed from: package-private */
    public static class AnnotationChecker {
        private final Collection<Class<? extends Annotation>> annotationTypes;
        final LoadingCache<Class<? extends Annotation>, Boolean> cache = CacheBuilder.newBuilder().weakKeys().build(this.hasAnnotations);
        private CacheLoader<Class<? extends Annotation>, Boolean> hasAnnotations = CacheLoader.from(new Function<Class<? extends Annotation>, Boolean>() {
            /* class com.google.inject.internal.Annotations.AnnotationChecker.AnonymousClass1 */

            public Boolean apply(Class<? extends Annotation> annotationType) {
                for (Annotation annotation : annotationType.getAnnotations()) {
                    if (AnnotationChecker.this.annotationTypes.contains(annotation.annotationType())) {
                        return true;
                    }
                }
                return false;
            }
        });

        AnnotationChecker(Collection<Class<? extends Annotation>> annotationTypes2) {
            this.annotationTypes = annotationTypes2;
        }

        /* access modifiers changed from: package-private */
        public boolean hasAnnotations(Class<? extends Annotation> annotated) {
            return this.cache.getUnchecked(annotated).booleanValue();
        }
    }

    public static boolean isScopeAnnotation(Class<? extends Annotation> annotationType) {
        return scopeChecker.hasAnnotations(annotationType);
    }

    public static boolean isBindingAnnotation(Class<? extends Annotation> annotationType) {
        return bindingAnnotationChecker.hasAnnotations(annotationType);
    }

    public static Annotation canonicalizeIfNamed(Annotation annotation) {
        if (annotation instanceof Named) {
            return Names.named(((Named) annotation).value());
        }
        return annotation;
    }

    public static Class<? extends Annotation> canonicalizeIfNamed(Class<? extends Annotation> annotationType) {
        if (annotationType == Named.class) {
            return com.google.inject.name.Named.class;
        }
        return annotationType;
    }
}
