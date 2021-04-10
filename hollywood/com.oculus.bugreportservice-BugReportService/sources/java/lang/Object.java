package java.lang;

public class Object {
    private transient Class shadow$_klass_;
    private transient int shadow$_monitor_;

    private static native int identityHashCodeNative(Object obj);

    private native Object internalClone();

    public boolean equals(Object obj) {
        return this == obj;
    }

    public final native void notify();

    public final native void notifyAll();

    public final native void wait(long j, int i);

    public final Class getClass() {
        return this.shadow$_klass_;
    }

    public int hashCode() {
        return identityHashCode(this);
    }

    static int identityHashCode(Object obj) {
        int i = obj.shadow$_monitor_;
        if ((-1073741824 & i) == Integer.MIN_VALUE) {
            return 268435455 & i;
        }
        return identityHashCodeNative(obj);
    }

    /* access modifiers changed from: protected */
    public Object clone() {
        if (this instanceof Cloneable) {
            return internalClone();
        }
        throw new CloneNotSupportedException("Class " + getClass().getName() + " doesn't implement Cloneable");
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getName());
        sb.append("@");
        Integer.toHexString(hashCode());
        throw null;
    }

    public final void wait(long j) {
        wait(j, 0);
    }

    public final void wait() {
        wait(0);
    }
}
