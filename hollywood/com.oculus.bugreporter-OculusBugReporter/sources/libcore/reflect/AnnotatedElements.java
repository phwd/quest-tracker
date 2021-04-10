package libcore.reflect;

import java.lang.annotation.Annotation;
import java.lang.annotation.IncompleteAnnotationException;
import java.lang.annotation.Repeatable;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public final class AnnotatedElements {
    public static <T extends Annotation> T[] getDirectOrIndirectAnnotationsByType(AnnotatedElement element, Class<T> annotationClass) {
        if (annotationClass != null) {
            Annotation[] annotations = element.getDeclaredAnnotations();
            ArrayList arrayList = new ArrayList();
            Class<? extends Annotation> repeatableAnnotationClass = getRepeatableAnnotationContainerClassFor(annotationClass);
            for (int i = 0; i < annotations.length; i++) {
                if (annotationClass.isInstance(annotations[i])) {
                    arrayList.add(annotations[i]);
                } else if (repeatableAnnotationClass != null && repeatableAnnotationClass.isInstance(annotations[i])) {
                    insertAnnotationValues(annotations[i], annotationClass, arrayList);
                }
            }
            return (T[]) ((Annotation[]) arrayList.toArray((Annotation[]) Array.newInstance((Class<?>) annotationClass, 0)));
        }
        throw new NullPointerException("annotationClass");
    }

    /* JADX DEBUG: Multi-variable search result rejected for r9v0, resolved type: java.util.ArrayList<T extends java.lang.annotation.Annotation> */
    /* JADX WARN: Multi-variable type inference failed */
    private static <T extends Annotation> void insertAnnotationValues(Annotation annotation, Class<T> annotationClass, ArrayList<T> unfoldedAnnotations) {
        Annotation[] annotationArr;
        ((Annotation[]) Array.newInstance((Class<?>) annotationClass, 0)).getClass();
        try {
            Method valuesMethod = annotation.getClass().getDeclaredMethod("value", new Class[0]);
            if (!valuesMethod.getReturnType().isArray()) {
                throw new AssertionError((Object) ("annotation container = " + ((Object) annotation) + "annotation element class = " + ((Object) annotationClass) + "; value() doesn't return array"));
            } else if (annotationClass.equals(valuesMethod.getReturnType().getComponentType())) {
                try {
                    for (Annotation annotation2 : (Annotation[]) valuesMethod.invoke(annotation, new Object[0])) {
                        unfoldedAnnotations.add(annotation2);
                    }
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new AssertionError(e);
                }
            } else {
                throw new AssertionError((Object) ("annotation container = " + ((Object) annotation) + "annotation element class = " + ((Object) annotationClass) + "; value() returns incorrect type"));
            }
        } catch (NoSuchMethodException e2) {
            throw new AssertionError((Object) ("annotation container = " + ((Object) annotation) + "annotation element class = " + ((Object) annotationClass) + "; missing value() method"));
        } catch (SecurityException e3) {
            throw new IncompleteAnnotationException(annotation.getClass(), "value");
        }
    }

    private static <T extends Annotation> Class<? extends Annotation> getRepeatableAnnotationContainerClassFor(Class<T> annotationClass) {
        Repeatable repeatableAnnotation = (Repeatable) annotationClass.getDeclaredAnnotation(Repeatable.class);
        if (repeatableAnnotation == null) {
            return null;
        }
        return repeatableAnnotation.value();
    }

    private AnnotatedElements() {
    }
}
