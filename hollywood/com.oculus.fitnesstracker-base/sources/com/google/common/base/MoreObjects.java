package com.google.common.base;

import com.oculus.common.build.BuildConfig;
import java.util.Arrays;

public final class MoreObjects {

    public static final class ToStringHelper {
        private final String className;
        private final ValueHolder holderHead;
        public ValueHolder holderTail;
        private boolean omitNullValues;

        public /* synthetic */ ToStringHelper(String str, byte b) {
            this(str);
        }

        private ToStringHelper(String str) {
            this.holderHead = new ValueHolder((byte) 0);
            this.holderTail = this.holderHead;
            this.omitNullValues = false;
            this.className = (String) Preconditions.checkNotNull(str);
        }

        public final String toString() {
            boolean z = this.omitNullValues;
            StringBuilder sb = new StringBuilder(32);
            sb.append(this.className);
            sb.append('{');
            String str = BuildConfig.PROVIDER_SUFFIX;
            for (ValueHolder valueHolder = this.holderHead.next; valueHolder != null; valueHolder = valueHolder.next) {
                Object obj = valueHolder.value;
                if (!z || obj != null) {
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
            }
            sb.append('}');
            return sb.toString();
        }

        public static final class ValueHolder {
            String name;
            public ValueHolder next;
            public Object value;

            private ValueHolder() {
            }

            public /* synthetic */ ValueHolder(byte b) {
                this();
            }
        }
    }
}
