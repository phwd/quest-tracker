package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Arrays;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
public final class MoreObjects {
    public static <T> T firstNonNull(@NullableDecl T first, @NullableDecl T second) {
        if (first != null) {
            return first;
        }
        if (second != null) {
            return second;
        }
        throw new NullPointerException("Both parameters are null");
    }

    public static ToStringHelper toStringHelper(Object self) {
        return new ToStringHelper(self.getClass().getSimpleName());
    }

    public static ToStringHelper toStringHelper(Class<?> clazz) {
        return new ToStringHelper(clazz.getSimpleName());
    }

    public static ToStringHelper toStringHelper(String className) {
        return new ToStringHelper(className);
    }

    public static final class ToStringHelper {
        private final String className;
        private final ValueHolder holderHead;
        private ValueHolder holderTail;
        private boolean omitNullValues;

        private ToStringHelper(String className2) {
            this.holderHead = new ValueHolder();
            this.holderTail = this.holderHead;
            this.omitNullValues = false;
            this.className = (String) Preconditions.checkNotNull(className2);
        }

        @CanIgnoreReturnValue
        public ToStringHelper omitNullValues() {
            this.omitNullValues = true;
            return this;
        }

        @CanIgnoreReturnValue
        public ToStringHelper add(String name, @NullableDecl Object value) {
            return addHolder(name, value);
        }

        @CanIgnoreReturnValue
        public ToStringHelper add(String name, boolean value) {
            return addHolder(name, String.valueOf(value));
        }

        @CanIgnoreReturnValue
        public ToStringHelper add(String name, char value) {
            return addHolder(name, String.valueOf(value));
        }

        @CanIgnoreReturnValue
        public ToStringHelper add(String name, double value) {
            return addHolder(name, String.valueOf(value));
        }

        @CanIgnoreReturnValue
        public ToStringHelper add(String name, float value) {
            return addHolder(name, String.valueOf(value));
        }

        @CanIgnoreReturnValue
        public ToStringHelper add(String name, int value) {
            return addHolder(name, String.valueOf(value));
        }

        @CanIgnoreReturnValue
        public ToStringHelper add(String name, long value) {
            return addHolder(name, String.valueOf(value));
        }

        @CanIgnoreReturnValue
        public ToStringHelper addValue(@NullableDecl Object value) {
            return addHolder(value);
        }

        @CanIgnoreReturnValue
        public ToStringHelper addValue(boolean value) {
            return addHolder(String.valueOf(value));
        }

        @CanIgnoreReturnValue
        public ToStringHelper addValue(char value) {
            return addHolder(String.valueOf(value));
        }

        @CanIgnoreReturnValue
        public ToStringHelper addValue(double value) {
            return addHolder(String.valueOf(value));
        }

        @CanIgnoreReturnValue
        public ToStringHelper addValue(float value) {
            return addHolder(String.valueOf(value));
        }

        @CanIgnoreReturnValue
        public ToStringHelper addValue(int value) {
            return addHolder(String.valueOf(value));
        }

        @CanIgnoreReturnValue
        public ToStringHelper addValue(long value) {
            return addHolder(String.valueOf(value));
        }

        public String toString() {
            boolean omitNullValuesSnapshot = this.omitNullValues;
            String nextSeparator = "";
            StringBuilder builder = new StringBuilder(32).append(this.className).append('{');
            for (ValueHolder valueHolder = this.holderHead.next; valueHolder != null; valueHolder = valueHolder.next) {
                Object value = valueHolder.value;
                if (!omitNullValuesSnapshot || value != null) {
                    builder.append(nextSeparator);
                    nextSeparator = ", ";
                    if (valueHolder.name != null) {
                        builder.append(valueHolder.name).append('=');
                    }
                    if (value == null || !value.getClass().isArray()) {
                        builder.append(value);
                    } else {
                        String arrayString = Arrays.deepToString(new Object[]{value});
                        builder.append((CharSequence) arrayString, 1, arrayString.length() - 1);
                    }
                }
            }
            return builder.append('}').toString();
        }

        private ValueHolder addHolder() {
            ValueHolder valueHolder = new ValueHolder();
            this.holderTail.next = valueHolder;
            this.holderTail = valueHolder;
            return valueHolder;
        }

        private ToStringHelper addHolder(@NullableDecl Object value) {
            addHolder().value = value;
            return this;
        }

        private ToStringHelper addHolder(String name, @NullableDecl Object value) {
            ValueHolder valueHolder = addHolder();
            valueHolder.value = value;
            valueHolder.name = (String) Preconditions.checkNotNull(name);
            return this;
        }

        /* access modifiers changed from: private */
        public static final class ValueHolder {
            @NullableDecl
            String name;
            @NullableDecl
            ValueHolder next;
            @NullableDecl
            Object value;

            private ValueHolder() {
            }
        }
    }

    private MoreObjects() {
    }
}
