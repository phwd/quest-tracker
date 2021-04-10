package com.android.server.am;

import com.android.server.am.StickyBroadcastProto;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface StickyBroadcastProtoOrBuilder extends MessageLiteOrBuilder {
    StickyBroadcastProto.StickyAction getActions(int i);

    int getActionsCount();

    List<StickyBroadcastProto.StickyAction> getActionsList();

    int getUser();

    boolean hasUser();
}
