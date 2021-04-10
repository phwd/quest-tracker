package X;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Type;
import java.util.HashMap;

/* renamed from: X.0GX  reason: invalid class name */
public final class AnonymousClass0GX extends AnonymousClass0g9 implements Serializable {
    public static final long serialVersionUID = 7364428299211355871L;
    public final transient Field A00;
    public C05700li _serialization;

    @Override // X.AbstractC05680lg
    public final Class<?> A0J() {
        return this.A00.getType();
    }

    @Override // X.AbstractC05680lg
    public final String A0K() {
        return this.A00.getName();
    }

    @Override // X.AbstractC05680lg
    public final <A extends Annotation> A A0L(Class<A> cls) {
        HashMap<Class<? extends Annotation>, Annotation> hashMap;
        C03800g8 r0 = super.A00;
        if (r0 == null || (hashMap = r0.A00) == null) {
            return null;
        }
        return (A) hashMap.get(cls);
    }

    @Override // X.AbstractC05680lg
    public final Type A0N() {
        return this.A00.getGenericType();
    }

    @Override // X.AnonymousClass0g9
    public final Class<?> A0O() {
        return this.A00.getDeclaringClass();
    }

    public Object readResolve() {
        C05700li r0 = this._serialization;
        Class<?> cls = r0.clazz;
        try {
            Field declaredField = cls.getDeclaredField(r0.name);
            if (!declaredField.isAccessible()) {
                C06330mu.A05(declaredField);
            }
            return new AnonymousClass0GX(declaredField, null);
        } catch (Exception unused) {
            throw new IllegalArgumentException(AnonymousClass006.A08("Could not find method '", this._serialization.name, "' from Class '", cls.getName()));
        }
    }

    public final String toString() {
        return AnonymousClass006.A07("[field ", AnonymousClass006.A07(A0O().getName(), "#", A0K()), "]");
    }

    public Object writeReplace() {
        return new AnonymousClass0GX(new C05700li(this.A00));
    }

    @Override // X.AbstractC05680lg
    public final /* bridge */ /* synthetic */ AnnotatedElement A0M() {
        return this.A00;
    }

    @Override // X.AnonymousClass0g9
    public final Member A0P() {
        return this.A00;
    }

    public AnonymousClass0GX(C05700li r2) {
        super(null);
        this.A00 = null;
        this._serialization = r2;
    }

    public AnonymousClass0GX(Field field, C03800g8 r2) {
        super(r2);
        this.A00 = field;
    }
}
