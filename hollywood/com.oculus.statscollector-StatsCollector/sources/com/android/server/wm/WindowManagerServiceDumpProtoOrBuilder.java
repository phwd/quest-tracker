package com.android.server.wm;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface WindowManagerServiceDumpProtoOrBuilder extends MessageLiteOrBuilder {
    boolean getDisplayFrozen();

    String getFocusedApp();

    ByteString getFocusedAppBytes();

    IdentifierProto getFocusedWindow();

    IdentifierProto getInputMethodWindow();

    int getLastOrientation();

    WindowManagerPolicyProto getPolicy();

    RootWindowContainerProto getRootWindowContainer();

    int getRotation();

    boolean hasDisplayFrozen();

    boolean hasFocusedApp();

    boolean hasFocusedWindow();

    boolean hasInputMethodWindow();

    boolean hasLastOrientation();

    boolean hasPolicy();

    boolean hasRootWindowContainer();

    boolean hasRotation();
}
