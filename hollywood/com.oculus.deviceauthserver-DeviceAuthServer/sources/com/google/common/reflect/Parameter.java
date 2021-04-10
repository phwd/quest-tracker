package com.google.common.reflect;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import javax.annotation.Nullable;

@Beta
public final class Parameter implements AnnotatedElement {
    private final ImmutableList<Annotation> annotations;
    private final Invokable<?, ?> declaration;
    private final int position;
    private final TypeToken<?> type;

    Parameter(Invokable<?, ?> declaration2, int position2, TypeToken<?> type2, Annotation[] annotations2) {
        this.declaration = declaration2;
        this.position = position2;
        this.type = type2;
        this.annotations = ImmutableList.copyOf(annotations2);
    }

    public TypeToken<?> getType() {
        return this.type;
    }

    public Invokable<?, ?> getDeclaringInvokable() {
        return this.declaration;
    }

    @Override // java.lang.reflect.AnnotatedElement
    public boolean isAnnotationPresent(Class<? extends Annotation> annotationType) {
        return getAnnotation(annotationType) != null;
    }

    @Override // java.lang.reflect.AnnotatedElement
    @Nullable
    public <A extends Annotation> A getAnnotation(Class<A> annotationType) {
        Preconditions.checkNotNull(annotationType);
        UnmodifiableIterator<Annotation> it = this.annotations.iterator();
        while (it.hasNext()) {
            Annotation annotation = it.next();
            if (annotationType.isInstance(annotation)) {
                return annotationType.cast(annotation);
            }
        }
        return null;
    }

    public Annotation[] getAnnotations() {
        return getDeclaredAnnotations();
    }

    @Override // java.lang.reflect.AnnotatedElement
    public <A extends Annotation> A[] getAnnotationsByType(Class<A> annotationType) {
        return (A[]) getDeclaredAnnotationsByType(annotationType);
    }

    public Annotation[] getDeclaredAnnotations() {
        ImmutableList<Annotation> immutableList = this.annotations;
        return (Annotation[]) immutableList.toArray(new Annotation[immutableList.size()]);
    }

    @Override // java.lang.reflect.AnnotatedElement
    @Nullable
    public <A extends Annotation> A getDeclaredAnnotation(Class<A> annotationType) {
        Preconditions.checkNotNull(annotationType);
        return (A) ((Annotation) FluentIterable.from(this.annotations).filter(annotationType).first().orNull());
    }

    @Override // java.lang.reflect.AnnotatedElement
    public <A extends Annotation> A[] getDeclaredAnnotationsByType(Class<A> annotationType) {
        return (A[]) ((Annotation[]) FluentIterable.from(this.annotations).filter(annotationType).toArray(annotationType));
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof Parameter)) {
            return false;
        }
        Parameter that = (Parameter) obj;
        if (this.position != that.position || !this.declaration.equals(that.declaration)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.position;
    }

    public String toString() {
        return this.type + " arg" + this.position;
    }
}
