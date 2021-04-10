package com.google.protobuf;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractMessageLite.Builder;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLite;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

public abstract class AbstractMessageLite<MessageType extends AbstractMessageLite<MessageType, BuilderType>, BuilderType extends Builder<MessageType, BuilderType>> implements MessageLite {
    protected int memoizedHashCode = 0;

    @Override // com.google.protobuf.MessageLite
    public ByteString toByteString() {
        try {
            ByteString.CodedBuilder newCodedBuilder = ByteString.newCodedBuilder(getSerializedSize());
            writeTo(newCodedBuilder.getCodedOutput());
            return newCodedBuilder.build();
        } catch (IOException e) {
            throw new RuntimeException("Serializing to a ByteString threw an IOException (should never happen).", e);
        }
    }

    public byte[] toByteArray() {
        try {
            byte[] bArr = new byte[getSerializedSize()];
            CodedOutputStream newInstance = CodedOutputStream.newInstance(bArr);
            writeTo(newInstance);
            newInstance.checkNoSpaceLeft();
            return bArr;
        } catch (IOException e) {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", e);
        }
    }

    /* access modifiers changed from: package-private */
    public UninitializedMessageException newUninitializedMessageException() {
        return new UninitializedMessageException(this);
    }

    protected static <T> void addAll(Iterable<T> iterable, Collection<? super T> collection) {
        Builder.addAll(iterable, collection);
    }

    public static abstract class Builder<MessageType extends AbstractMessageLite<MessageType, BuilderType>, BuilderType extends Builder<MessageType, BuilderType>> implements MessageLite.Builder {
        /* access modifiers changed from: protected */
        public abstract BuilderType internalMergeFrom(MessageType messagetype);

        /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: com.google.protobuf.AbstractMessageLite$Builder<MessageType extends com.google.protobuf.AbstractMessageLite<MessageType, BuilderType>, BuilderType extends com.google.protobuf.AbstractMessageLite$Builder<MessageType, BuilderType>> */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.protobuf.MessageLite.Builder
        public BuilderType mergeFrom(MessageLite messageLite) {
            if (getDefaultInstanceForType().getClass().isInstance(messageLite)) {
                internalMergeFrom((AbstractMessageLite) messageLite);
                return this;
            }
            throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
        }

        protected static UninitializedMessageException newUninitializedMessageException(MessageLite messageLite) {
            return new UninitializedMessageException(messageLite);
        }

        protected static <T> void addAll(Iterable<T> iterable, Collection<? super T> collection) {
            if (iterable == null) {
                throw new NullPointerException();
            } else if (iterable instanceof LazyStringList) {
                checkForNullValues(((LazyStringList) iterable).getUnderlyingElements());
                collection.addAll((Collection) iterable);
            } else if (iterable instanceof Collection) {
                checkForNullValues(iterable);
                collection.addAll((Collection) iterable);
            } else {
                for (T t : iterable) {
                    if (t != null) {
                        collection.add(t);
                    } else {
                        throw new NullPointerException();
                    }
                }
            }
        }

        private static void checkForNullValues(Iterable<?> iterable) {
            Iterator<?> it = iterable.iterator();
            while (it.hasNext()) {
                if (it.next() == null) {
                    throw new NullPointerException();
                }
            }
        }
    }
}
