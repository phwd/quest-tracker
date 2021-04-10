package com.google.common.base;

import java.io.Serializable;

public final class Suppliers {
    public static <T> Supplier<T> ofInstance(T instance) {
        return new SupplierOfInstance(instance);
    }

    private static class SupplierOfInstance<T> implements Supplier<T>, Serializable {
        final T instance;

        SupplierOfInstance(T instance2) {
            this.instance = instance2;
        }

        @Override // com.google.common.base.Supplier
        public T get() {
            return this.instance;
        }

        public boolean equals(Object obj) {
            if (obj instanceof SupplierOfInstance) {
                return Objects.equal(this.instance, ((SupplierOfInstance) obj).instance);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hashCode(this.instance);
        }

        public String toString() {
            return "Suppliers.ofInstance(" + ((Object) this.instance) + ")";
        }
    }
}
