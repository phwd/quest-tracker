package com.android.server.wm;

import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface WindowManagerTraceFileProtoOrBuilder extends MessageLiteOrBuilder {
    WindowManagerTraceProto getEntry(int i);

    int getEntryCount();

    List<WindowManagerTraceProto> getEntryList();

    long getMagicNumber();

    boolean hasMagicNumber();
}
