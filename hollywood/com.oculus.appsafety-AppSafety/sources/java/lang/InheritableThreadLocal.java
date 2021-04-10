package java.lang;

import java.lang.ThreadLocal;

public class InheritableThreadLocal<T> extends ThreadLocal<T> {
    /* access modifiers changed from: protected */
    @Override // java.lang.ThreadLocal
    public T childValue(T parentValue) {
        return parentValue;
    }

    /* access modifiers changed from: package-private */
    @Override // java.lang.ThreadLocal
    public ThreadLocal.ThreadLocalMap getMap(Thread t) {
        return t.inheritableThreadLocals;
    }

    /* access modifiers changed from: package-private */
    @Override // java.lang.ThreadLocal
    public void createMap(Thread t, T firstValue) {
        t.inheritableThreadLocals = new ThreadLocal.ThreadLocalMap(this, firstValue);
    }
}
