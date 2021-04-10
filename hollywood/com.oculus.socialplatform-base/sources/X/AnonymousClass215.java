package X;

import io.reactivex.annotations.Nullable;

/* renamed from: X.215  reason: invalid class name */
public final class AnonymousClass215<T> {
    public static final AnonymousClass215<Object> A01 = new AnonymousClass215<>(null);
    public final Object A00;

    @Nullable
    public final Throwable A00() {
        Object obj = this.A00;
        if (EnumC139220y.isError(obj)) {
            return EnumC139220y.getError(obj);
        }
        return null;
    }

    public final boolean A01() {
        Object obj = this.A00;
        if (obj == null || EnumC139220y.isError(obj)) {
            return false;
        }
        return true;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof AnonymousClass215)) {
            return false;
        }
        Object obj2 = this.A00;
        Object obj3 = ((AnonymousClass215) obj).A00;
        if (obj2 == obj3) {
            return true;
        }
        if (obj2 == null || !obj2.equals(obj3)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        Object obj = this.A00;
        if (obj != null) {
            return obj.hashCode();
        }
        return 0;
    }

    public final String toString() {
        Object obj = this.A00;
        if (obj == null) {
            return "OnCompleteNotification";
        }
        if (EnumC139220y.isError(obj)) {
            StringBuilder sb = new StringBuilder("OnErrorNotification[");
            sb.append(EnumC139220y.getError(obj));
            sb.append("]");
            return sb.toString();
        }
        StringBuilder sb2 = new StringBuilder("OnNextNotification[");
        sb2.append(obj);
        sb2.append("]");
        return sb2.toString();
    }

    public AnonymousClass215(Object obj) {
        this.A00 = obj;
    }
}
