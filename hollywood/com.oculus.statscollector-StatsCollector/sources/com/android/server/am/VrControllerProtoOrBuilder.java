package com.android.server.am;

import com.android.server.am.VrControllerProto;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface VrControllerProtoOrBuilder extends MessageLiteOrBuilder {
    int getRenderThreadId();

    VrControllerProto.VrMode getVrMode(int i);

    int getVrModeCount();

    List<VrControllerProto.VrMode> getVrModeList();

    boolean hasRenderThreadId();
}
