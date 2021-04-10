package com.android.server.wm;

import android.app.StatusBarManagerProto;
import com.google.protobuf.MessageLiteOrBuilder;

public interface BarControllerProtoOrBuilder extends MessageLiteOrBuilder {
    StatusBarManagerProto.WindowState getState();

    StatusBarManagerProto.TransientWindowState getTransientState();

    boolean hasState();

    boolean hasTransientState();
}
