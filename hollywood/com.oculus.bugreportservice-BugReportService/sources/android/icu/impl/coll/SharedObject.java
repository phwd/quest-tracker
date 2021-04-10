package android.icu.impl.coll;

import android.icu.util.ICUCloneNotSupportedException;
import java.util.concurrent.atomic.AtomicInteger;

public class SharedObject implements Cloneable {
    private AtomicInteger refCount = new AtomicInteger();

    public static final class Reference implements Cloneable {
        private SharedObject ref;

        public Reference(SharedObject sharedObject) {
            this.ref = sharedObject;
            if (sharedObject != null) {
                sharedObject.addRef();
            }
        }

        public Reference clone() {
            try {
                Reference reference = (Reference) super.clone();
                SharedObject sharedObject = this.ref;
                if (sharedObject != null) {
                    sharedObject.addRef();
                }
                return reference;
            } catch (CloneNotSupportedException e) {
                throw new ICUCloneNotSupportedException(e);
            }
        }

        public SharedObject readOnly() {
            return this.ref;
        }

        public SharedObject copyOnWrite() {
            SharedObject sharedObject = this.ref;
            if (sharedObject.getRefCount() <= 1) {
                return sharedObject;
            }
            SharedObject clone = sharedObject.clone();
            sharedObject.removeRef();
            this.ref = clone;
            clone.addRef();
            return clone;
        }
    }

    public SharedObject clone() {
        try {
            SharedObject sharedObject = (SharedObject) super.clone();
            sharedObject.refCount = new AtomicInteger();
            return sharedObject;
        } catch (CloneNotSupportedException e) {
            throw new ICUCloneNotSupportedException(e);
        }
    }

    public final void addRef() {
        this.refCount.incrementAndGet();
    }

    public final void removeRef() {
        this.refCount.decrementAndGet();
    }

    public final int getRefCount() {
        return this.refCount.get();
    }
}
