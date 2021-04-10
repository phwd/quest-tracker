package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Arrays;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
public final class MoreObjects {

    public static final class ToStringHelper {
        public final String className;
        public final ValueHolder holderHead;
        public ValueHolder holderTail;

        public static final class ValueHolder {
            @NullableDecl
            public String name;
            @NullableDecl
            public ValueHolder next;
            @NullableDecl
            public Object value;
        }

        private ToStringHelper addHolder(String str, @NullableDecl Object obj) {
            ValueHolder valueHolder = new ValueHolder();
            this.holderTail.next = valueHolder;
            this.holderTail = valueHolder;
            valueHolder.value = obj;
            if (str != null) {
                valueHolder.name = str;
                return this;
            }
            throw null;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(32);
            sb.append(this.className);
            sb.append('{');
            String str = "";
            for (ValueHolder valueHolder = this.holderHead.next; valueHolder != null; valueHolder = valueHolder.next) {
                Object obj = valueHolder.value;
                sb.append(str);
                if (valueHolder.name != null) {
                    sb.append(valueHolder.name);
                    sb.append('=');
                }
                if (obj == null || !obj.getClass().isArray()) {
                    sb.append(obj);
                } else {
                    String deepToString = Arrays.deepToString(new Object[]{obj});
                    sb.append((CharSequence) deepToString, 1, deepToString.length() - 1);
                }
                str = ", ";
            }
            sb.append('}');
            return sb.toString();
        }

        public ToStringHelper(String str) {
            ValueHolder valueHolder = new ValueHolder();
            this.holderHead = valueHolder;
            this.holderTail = valueHolder;
            if (str != null) {
                this.className = str;
                return;
            }
            throw null;
        }

        @CanIgnoreReturnValue
        public ToStringHelper add(String str, boolean z) {
            addHolder(str, String.valueOf(z));
            return this;
        }

        @CanIgnoreReturnValue
        public ToStringHelper add(String str, int i) {
            addHolder(str, String.valueOf(i));
            return this;
        }

        @CanIgnoreReturnValue
        public ToStringHelper add(String str, long j) {
            addHolder(str, String.valueOf(j));
            return this;
        }

        @CanIgnoreReturnValue
        public ToStringHelper add(String str, @NullableDecl Object obj) {
            addHolder(str, obj);
            return this;
        }
    }

    public static <T> T firstNonNull(@NullableDecl T t, @NullableDecl T t2) {
        if (t != null) {
            return t;
        }
        if (t2 != null) {
            return t2;
        }
        throw new NullPointerException("Both parameters are null");
    }
}
