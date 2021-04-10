package com.facebook.flatbuffers;

import com.facebook.flatbuffers.Flattenable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.nio.ByteBuffer;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.SOURCE)
public @interface FlattenableField {
    Class<? extends Flattener<?>> keyFlattener() default NilFlattener.class;

    Class<? extends Flattenable.VirtualFlattenableResolver> keyTypeResolver() default NilResolver.class;

    int value();

    Class<? extends Flattener<?>> valueFlattener() default NilFlattener.class;

    Class<? extends Flattenable.VirtualFlattenableResolver> valueTypeResolver() default NilResolver.class;

    public static class NilResolver implements Flattenable.VirtualFlattenableResolver {
        private NilResolver() {
        }

        @Override // com.facebook.flatbuffers.Flattenable.VirtualFlattenableResolver
        public int getVirtualFlattenableType(Flattenable flattenable) {
            throw new UnsupportedOperationException("Should not invoked methods on NilResolver");
        }

        @Override // com.facebook.flatbuffers.Flattenable.VirtualFlattenableResolver
        public Flattenable resolveVirtualFlattenableType(int i) {
            throw new UnsupportedOperationException("Should not invoked methods on NilResolver");
        }
    }

    public static class NilFlattener implements Flattener<Object> {
        private NilFlattener() {
        }

        @Override // com.facebook.flatbuffers.Flattener
        public int flattenToBuffer(Object obj, FlatBufferBuilder flatBufferBuilder) {
            throw new UnsupportedOperationException("Should not invoked methods on NilFlattener");
        }

        @Override // com.facebook.flatbuffers.Flattener
        public Object initFromFlatBuffer(ByteBuffer byteBuffer, int i) throws InstantiationException, IllegalAccessException {
            throw new UnsupportedOperationException("Should not invoked methods on NilFlattener");
        }
    }
}
