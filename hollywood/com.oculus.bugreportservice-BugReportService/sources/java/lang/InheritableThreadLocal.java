package java.lang;

import java.lang.ThreadLocal;

public class InheritableThreadLocal extends ThreadLocal {
    /* access modifiers changed from: protected */
    @Override // java.lang.ThreadLocal
    public Object childValue(Object obj) {
        return obj;
    }

    /* access modifiers changed from: package-private */
    @Override // java.lang.ThreadLocal
    public ThreadLocal.ThreadLocalMap getMap(Thread thread) {
        return thread.inheritableThreadLocals;
    }

    /* access modifiers changed from: package-private */
    @Override // java.lang.ThreadLocal
    public void createMap(Thread thread, Object obj) {
        thread.inheritableThreadLocals = new ThreadLocal.ThreadLocalMap(this, obj);
    }
}
