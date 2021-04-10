package java.lang.reflect;

import java.lang.annotation.Annotation;
import java.util.Objects;
import libcore.reflect.AnnotatedElements;

public interface AnnotatedElement {
    <T extends Annotation> T getAnnotation(Class<T> cls);

    Annotation[] getAnnotations();

    Annotation[] getDeclaredAnnotations();

    default boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) {
        return getAnnotation(annotationClass) != null;
    }

    default <T extends Annotation> T[] getAnnotationsByType(Class<T> annotationClass) {
        return (T[]) AnnotatedElements.getDirectOrIndirectAnnotationsByType(this, annotationClass);
    }

    default <T extends Annotation> T getDeclaredAnnotation(Class<T> annotationClass) {
        Objects.requireNonNull(annotationClass);
        Annotation[] declaredAnnotations = getDeclaredAnnotations();
        for (Annotation annotation : declaredAnnotations) {
            if (annotationClass.equals(annotation.annotationType())) {
                return annotationClass.cast(annotation);
            }
        }
        return null;
    }

    default <T extends Annotation> T[] getDeclaredAnnotationsByType(Class<T> annotationClass) {
        return (T[]) AnnotatedElements.getDirectOrIndirectAnnotationsByType(this, annotationClass);
    }
}
