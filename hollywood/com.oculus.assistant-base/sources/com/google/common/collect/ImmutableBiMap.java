package com.google.common.collect;

import X.u7;
import com.google.common.collect.ImmutableMap;
import java.util.Map;

public abstract class ImmutableBiMap extends ImmutableMap implements Map {

    public class SerializedForm extends ImmutableMap.SerializedForm {
        public static final long serialVersionUID = 0;

        @Override // com.google.common.collect.ImmutableMap.SerializedForm
        public Object readResolve() {
            return A00(new u7());
        }

        public SerializedForm(ImmutableBiMap immutableBiMap) {
            super(immutableBiMap);
        }
    }

    @Override // com.google.common.collect.ImmutableMap
    public Object writeReplace() {
        return new SerializedForm(this);
    }
}
