package com.google.common.base;

import com.facebook.assistant.oacr.OacrConstants;
import java.util.Arrays;

public final class MoreObjects$ToStringHelper {
    public final String className;
    public final ValueHolder holderHead;
    public ValueHolder holderTail;

    public final class ValueHolder {
        public String name;
        public ValueHolder next;
        public Object value;
    }

    private MoreObjects$ToStringHelper addHolder(String str, Object obj) {
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
        String str = OacrConstants.AUTO_SPEECH_DOMAIN;
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

    public MoreObjects$ToStringHelper(String str) {
        ValueHolder valueHolder = new ValueHolder();
        this.holderHead = valueHolder;
        this.holderTail = valueHolder;
        if (str != null) {
            this.className = str;
            return;
        }
        throw null;
    }

    public MoreObjects$ToStringHelper add(String str, int i) {
        addHolder(str, String.valueOf(i));
        return this;
    }

    public MoreObjects$ToStringHelper add(String str, long j) {
        addHolder(str, String.valueOf(j));
        return this;
    }

    public MoreObjects$ToStringHelper add(String str, Object obj) {
        addHolder(str, obj);
        return this;
    }

    public MoreObjects$ToStringHelper add(String str, boolean z) {
        addHolder(str, String.valueOf(z));
        return this;
    }
}
