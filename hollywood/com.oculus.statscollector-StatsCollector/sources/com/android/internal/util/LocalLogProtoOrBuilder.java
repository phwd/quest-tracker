package com.android.internal.util;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface LocalLogProtoOrBuilder extends MessageLiteOrBuilder {
    String getLines(int i);

    ByteString getLinesBytes(int i);

    int getLinesCount();

    List<String> getLinesList();
}
