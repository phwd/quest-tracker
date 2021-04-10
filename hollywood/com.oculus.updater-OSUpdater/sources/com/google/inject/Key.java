package com.google.inject;

import com.facebook.infer.annotation.Nullsafe;
import com.google.common.base.Preconditions;
import com.google.inject.internal.Annotations;
import com.google.inject.internal.MoreTypes;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.annotation.Nullable;

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

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Key)) {
            return false;
        }
        Key key = (Key) obj;
        if (!this.annotationStrategy.equals(key.annotationStrategy) || !this.typeLiteral.equals(key.typeLiteral)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return this.hashCode;
    }

    public final String toString() {
        return "Key[type=" + this.typeLiteral + ", annotation=" + this.annotationStrategy + "]";
    }

    public static <T> Key<T> get(Class<T> cls) {
        return new Key<>(cls, NullAnnotationStrategy.INSTANCE);
    }

    public static <S> Key<S> get(Class<S> cls, Class<? extends Annotation> cls2) {
        return new Key<>(cls, strategyFor(cls2));
    }

    public static Key<?> get(Type type) {
        return new Key<>(type, NullAnnotationStrategy.INSTANCE);
    }

    public static Key<?> get(Type type, Class<? extends Annotation> cls) {
        return new Key<>(type, strategyFor(cls));
    }

    public static Key<?> get(Type type, Annotation annotation) {
        return new Key<>(type, strategyFor(annotation));
    }

    public static <S> Key<S> get(TypeLiteral<S> typeLiteral2) {
        return new Key<>(typeLiteral2, NullAnnotationStrategy.INSTANCE);
    }

    public static <S> Key<S> get(TypeLiteral<S> typeLiteral2, Class<? extends Annotation> cls) {
        return new Key<>(typeLiteral2, strategyFor(cls));
    }

    static AnnotationStrategy strategyFor(Annotation annotation) {
        Preconditions.checkNotNull(annotation, "annotation");
        Class<? extends Annotation> annotationType = annotation.annotationType();
        if (Annotations.isMarker(annotationType)) {
            return new AnnotationTypeStrategy(annotationType, annotation);
        }
        return new AnnotationInstanceStrategy(Annotations.canonicalizeIfNamed(annotation));
    }

    static AnnotationStrategy strategyFor(Class<? extends Annotation> cls) {
        Preconditions.checkNotNull(cls, "annotation type");
        return new AnnotationTypeStrategy(Annotations.canonicalizeIfNamed(cls), null);
    }

    /* access modifiers changed from: package-private */
    public enum NullAnnotationStrategy implements AnnotationStrategy {
        INSTANCE;

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

        public boolean hasAttributes() {
            return false;
        }

        public String toString() {
            return "[none]";
        }

        public AnnotationStrategy withoutAttributes() {
            throw new UnsupportedOperationException("Key already has no attributes.");
        }
    }

    /* access modifiers changed from: package-private */
    public static class AnnotationInstanceStrategy implements AnnotationStrategy {
        final Annotation annotation;

        AnnotationInstanceStrategy(Annotation annotation2) {
            this.annotation = (Annotation) Preconditions.checkNotNull(annotation2, "annotation");
        }

        @Override // com.google.inject.Key.AnnotationStrategy
        public Annotation getAnnotation() {
            return this.annotation;
        }

        @Override // com.google.inject.Key.AnnotationStrategy
        public Class<? extends Annotation> getAnnotationType() {
            return this.annotation.annotationType();
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof AnnotationInstanceStrategy)) {
                return false;
            }
            return this.annotation.equals(((AnnotationInstanceStrategy) obj).annotation);
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

        AnnotationTypeStrategy(Class<? extends Annotation> cls, @Nullable Annotation annotation2) {
            this.annotationType = (Class) Preconditions.checkNotNull(cls, "annotation type");
            this.annotation = annotation2;
        }

        @Override // com.google.inject.Key.AnnotationStrategy
        public Annotation getAnnotation() {
            return this.annotation;
        }

        @Override // com.google.inject.Key.AnnotationStrategy
        public Class<? extends Annotation> getAnnotationType() {
            return this.annotationType;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof AnnotationTypeStrategy)) {
                return false;
            }
            return this.annotationType.equals(((AnnotationTypeStrategy) obj).annotationType);
        }

        public int hashCode() {
            return this.annotationType.hashCode();
        }

        public String toString() {
            return "@" + this.annotationType.getName();
        }
    }
}
