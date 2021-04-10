package com.google.inject;

import com.facebook.infer.annotation.Nullsafe;
import com.google.common.base.Preconditions;
import com.google.inject.internal.Annotations;
import com.google.inject.internal.MoreTypes;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.annotation.Nullable;
import javax.inject.Provider;

@Nullsafe(Nullsafe.Mode.STRICT)
public class Key<T> {
    private final AnnotationStrategy annotationStrategy;
    private final int hashCode;
    private final TypeLiteral<T> typeLiteral;

    /* access modifiers changed from: package-private */
    public interface AnnotationStrategy {
        @Nullable
        Annotation getAnnotation();

        @Nullable
        Class<? extends Annotation> getAnnotationType();

        boolean hasAttributes();

        AnnotationStrategy withoutAttributes();
    }

    private Key(Class<? extends Annotation> annotationType) {
        this.annotationStrategy = strategyFor(annotationType);
        this.typeLiteral = (TypeLiteral<T>) TypeLiteral.fromSuperclassTypeParameter(getClass());
        this.hashCode = computeHashCode();
    }

    private Key(Annotation annotation) {
        this.annotationStrategy = strategyFor(annotation);
        this.typeLiteral = (TypeLiteral<T>) TypeLiteral.fromSuperclassTypeParameter(getClass());
        this.hashCode = computeHashCode();
    }

    private Key() {
        this.annotationStrategy = NullAnnotationStrategy.INSTANCE;
        this.typeLiteral = (TypeLiteral<T>) TypeLiteral.fromSuperclassTypeParameter(getClass());
        this.hashCode = computeHashCode();
    }

    private Key(Type type, AnnotationStrategy annotationStrategy2) {
        this.annotationStrategy = annotationStrategy2;
        this.typeLiteral = MoreTypes.canonicalizeForKey(TypeLiteral.get(type));
        this.hashCode = computeHashCode();
    }

    private Key(TypeLiteral<T> typeLiteral2, AnnotationStrategy annotationStrategy2) {
        this.annotationStrategy = annotationStrategy2;
        this.typeLiteral = MoreTypes.canonicalizeForKey(typeLiteral2);
        this.hashCode = computeHashCode();
    }

    private int computeHashCode() {
        return (this.typeLiteral.hashCode() * 31) + this.annotationStrategy.hashCode();
    }

    public final TypeLiteral<T> getTypeLiteral() {
        return this.typeLiteral;
    }

    public final Class<? extends Annotation> getAnnotationType() {
        return this.annotationStrategy.getAnnotationType();
    }

    public final Annotation getAnnotation() {
        return this.annotationStrategy.getAnnotation();
    }

    /* access modifiers changed from: package-private */
    public boolean hasAnnotationType() {
        return this.annotationStrategy.getAnnotationType() != null;
    }

    /* access modifiers changed from: package-private */
    public String getAnnotationName() {
        Annotation annotation = this.annotationStrategy.getAnnotation();
        if (annotation != null) {
            return annotation.toString();
        }
        return ((Class) Preconditions.checkNotNull(this.annotationStrategy.getAnnotationType())).toString();
    }

    /* access modifiers changed from: package-private */
    public Class<? super T> getRawType() {
        return this.typeLiteral.getRawType();
    }

    /* JADX DEBUG: Type inference failed for r0v1. Raw type applied. Possible types: com.google.inject.TypeLiteral<javax.inject.Provider<T>>, com.google.inject.TypeLiteral<S> */
    /* access modifiers changed from: package-private */
    public Key<Provider<T>> providerKey() {
        return ofType((TypeLiteral<Provider<T>>) this.typeLiteral.providerType());
    }

    public final boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Key)) {
            return false;
        }
        Key<?> other = (Key) o;
        return this.annotationStrategy.equals(other.annotationStrategy) && this.typeLiteral.equals(other.typeLiteral);
    }

    public final int hashCode() {
        return this.hashCode;
    }

    public final String toString() {
        return "Key[type=" + this.typeLiteral + ", annotation=" + this.annotationStrategy + "]";
    }

    static <T> Key<T> get(Class<T> type, AnnotationStrategy annotationStrategy2) {
        return new Key<>(type, annotationStrategy2);
    }

    public static <T> Key<T> get(Class<T> type) {
        return new Key<>(type, NullAnnotationStrategy.INSTANCE);
    }

    public static <S> Key<S> get(Class<S> type, Class<? extends Annotation> annotationType) {
        return new Key<>(type, strategyFor(annotationType));
    }

    public static <S> Key<S> get(Class<S> type, Annotation annotation) {
        return new Key<>(type, strategyFor(annotation));
    }

    public static Key<?> get(Type type) {
        return new Key<>(type, NullAnnotationStrategy.INSTANCE);
    }

    public static Key<?> get(Type type, Class<? extends Annotation> annotationType) {
        return new Key<>(type, strategyFor(annotationType));
    }

    public static Key<?> get(Type type, Annotation annotation) {
        return new Key<>(type, strategyFor(annotation));
    }

    public static <S> Key<S> get(TypeLiteral<S> typeLiteral2) {
        return new Key<>(typeLiteral2, NullAnnotationStrategy.INSTANCE);
    }

    public static <S> Key<S> get(TypeLiteral<S> typeLiteral2, Class<? extends Annotation> annotationType) {
        return new Key<>(typeLiteral2, strategyFor(annotationType));
    }

    public static <S> Key<S> get(TypeLiteral<S> typeLiteral2, Annotation annotation) {
        return new Key<>(typeLiteral2, strategyFor(annotation));
    }

    public <S> Key<S> ofType(Class<S> type) {
        return new Key<>(type, this.annotationStrategy);
    }

    public Key<?> ofType(Type type) {
        return new Key<>(type, this.annotationStrategy);
    }

    public <S> Key<S> ofType(TypeLiteral<S> type) {
        return new Key<>(type, this.annotationStrategy);
    }

    public boolean hasAttributes() {
        return this.annotationStrategy.hasAttributes();
    }

    public Key<T> withoutAttributes() {
        return new Key<>(this.typeLiteral, this.annotationStrategy.withoutAttributes());
    }

    static AnnotationStrategy strategyFor(Annotation annotation) {
        Preconditions.checkNotNull(annotation, "annotation");
        Class<? extends Annotation> annotationType = annotation.annotationType();
        if (Annotations.isMarker(annotationType)) {
            return new AnnotationTypeStrategy(annotationType, annotation);
        }
        return new AnnotationInstanceStrategy(Annotations.canonicalizeIfNamed(annotation));
    }

    static AnnotationStrategy strategyFor(Class<? extends Annotation> annotationType) {
        Preconditions.checkNotNull(annotationType, "annotation type");
        return new AnnotationTypeStrategy(Annotations.canonicalizeIfNamed(annotationType), null);
    }

    private static void ensureRetainedAtRuntime(Class<? extends Annotation> annotationType) {
        Preconditions.checkArgument(Annotations.isRetainedAtRuntime(annotationType), "%s is not retained at runtime. Please annotate it with @Retention(RUNTIME).", annotationType.getName());
    }

    private static void ensureIsBindingAnnotation(Class<? extends Annotation> annotationType) {
        Preconditions.checkArgument(Annotations.isBindingAnnotation(annotationType), "%s is not a binding annotation. Please annotate it with @BindingAnnotation.", annotationType.getName());
    }

    /* access modifiers changed from: package-private */
    public enum NullAnnotationStrategy implements AnnotationStrategy {
        INSTANCE;

        @Override // com.google.inject.Key.AnnotationStrategy
        public boolean hasAttributes() {
            return false;
        }

        @Override // com.google.inject.Key.AnnotationStrategy
        public AnnotationStrategy withoutAttributes() {
            throw new UnsupportedOperationException("Key already has no attributes.");
        }

        @Override // com.google.inject.Key.AnnotationStrategy
        @Nullable
        public Annotation getAnnotation() {
            return null;
        }

        @Override // com.google.inject.Key.AnnotationStrategy
        @Nullable
        public Class<? extends Annotation> getAnnotationType() {
            return null;
        }

        public String toString() {
            return "[none]";
        }
    }

    /* access modifiers changed from: package-private */
    public static class AnnotationInstanceStrategy implements AnnotationStrategy {
        final Annotation annotation;

        AnnotationInstanceStrategy(Annotation annotation2) {
            this.annotation = (Annotation) Preconditions.checkNotNull(annotation2, "annotation");
        }

        @Override // com.google.inject.Key.AnnotationStrategy
        public boolean hasAttributes() {
            return true;
        }

        @Override // com.google.inject.Key.AnnotationStrategy
        public AnnotationStrategy withoutAttributes() {
            return new AnnotationTypeStrategy(getAnnotationType(), this.annotation);
        }

        @Override // com.google.inject.Key.AnnotationStrategy
        public Annotation getAnnotation() {
            return this.annotation;
        }

        @Override // com.google.inject.Key.AnnotationStrategy
        public Class<? extends Annotation> getAnnotationType() {
            return this.annotation.annotationType();
        }

        public boolean equals(Object o) {
            if (!(o instanceof AnnotationInstanceStrategy)) {
                return false;
            }
            return this.annotation.equals(((AnnotationInstanceStrategy) o).annotation);
        }

        public int hashCode() {
            return this.annotation.hashCode();
        }

        public String toString() {
            return this.annotation.toString();
        }
    }

    /* access modifiers changed from: package-private */
    public static class AnnotationTypeStrategy implements AnnotationStrategy {
        final Annotation annotation;
        final Class<? extends Annotation> annotationType;

        AnnotationTypeStrategy(Class<? extends Annotation> annotationType2, @Nullable Annotation annotation2) {
            this.annotationType = (Class) Preconditions.checkNotNull(annotationType2, "annotation type");
            this.annotation = annotation2;
        }

        @Override // com.google.inject.Key.AnnotationStrategy
        public boolean hasAttributes() {
            return false;
        }

        @Override // com.google.inject.Key.AnnotationStrategy
        public AnnotationStrategy withoutAttributes() {
            throw new UnsupportedOperationException("Key already has no attributes.");
        }

        @Override // com.google.inject.Key.AnnotationStrategy
        public Annotation getAnnotation() {
            return this.annotation;
        }

        @Override // com.google.inject.Key.AnnotationStrategy
        public Class<? extends Annotation> getAnnotationType() {
            return this.annotationType;
        }

        public boolean equals(Object o) {
            if (!(o instanceof AnnotationTypeStrategy)) {
                return false;
            }
            return this.annotationType.equals(((AnnotationTypeStrategy) o).annotationType);
        }

        public int hashCode() {
            return this.annotationType.hashCode();
        }

        public String toString() {
            return "@" + this.annotationType.getName();
        }
    }
}
