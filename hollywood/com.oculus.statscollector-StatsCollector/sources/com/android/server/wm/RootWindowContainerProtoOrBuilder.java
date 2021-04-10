package com.android.server.wm;

import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface RootWindowContainerProtoOrBuilder extends MessageLiteOrBuilder {
    DisplayContentProto getDisplays(int i);

    int getDisplaysCount();

    List<DisplayContentProto> getDisplaysList();

    WindowContainerProto getWindowContainer();

    IdentifierProto getWindows(int i);

    int getWindowsCount();

    List<IdentifierProto> getWindowsList();

    boolean hasWindowContainer();
}
