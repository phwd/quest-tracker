package com.android.server.am;

import com.android.server.IntentResolverProto;
import com.android.server.am.ActivityManagerServiceDumpBroadcastsProto;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface ActivityManagerServiceDumpBroadcastsProtoOrBuilder extends MessageLiteOrBuilder {
    BroadcastQueueProto getBroadcastQueue(int i);

    int getBroadcastQueueCount();

    List<BroadcastQueueProto> getBroadcastQueueList();

    ActivityManagerServiceDumpBroadcastsProto.MainHandler getHandler();

    ReceiverListProto getReceiverList(int i);

    int getReceiverListCount();

    List<ReceiverListProto> getReceiverListList();

    IntentResolverProto getReceiverResolver();

    StickyBroadcastProto getStickyBroadcasts(int i);

    int getStickyBroadcastsCount();

    List<StickyBroadcastProto> getStickyBroadcastsList();

    boolean hasHandler();

    boolean hasReceiverResolver();
}
