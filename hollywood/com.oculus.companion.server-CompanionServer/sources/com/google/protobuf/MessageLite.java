package com.google.protobuf;

import java.io.IOException;

public interface MessageLite extends MessageLiteOrBuilder {

    public interface Builder extends MessageLiteOrBuilder, Cloneable {
        MessageLite build();

        Builder mergeFrom(MessageLite messageLite);
    }

    Parser<? extends MessageLite> getParserForType();

    int getSerializedSize();

    Builder toBuilder();

    ByteString toByteString();

    void writeTo(CodedOutputStream codedOutputStream) throws IOException;
}
