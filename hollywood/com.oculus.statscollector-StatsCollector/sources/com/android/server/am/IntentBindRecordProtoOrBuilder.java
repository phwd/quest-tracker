package com.android.server.am;

import android.content.IntentProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface IntentBindRecordProtoOrBuilder extends MessageLiteOrBuilder {
    AppBindRecordProto getApps(int i);

    int getAppsCount();

    List<AppBindRecordProto> getAppsList();

    boolean getAutoCreate();

    String getBinder();

    ByteString getBinderBytes();

    boolean getDoRebind();

    boolean getHasBound();

    IntentProto getIntent();

    boolean getReceived();

    boolean getRequested();

    boolean hasAutoCreate();

    boolean hasBinder();

    boolean hasDoRebind();

    boolean hasHasBound();

    boolean hasIntent();

    boolean hasReceived();

    boolean hasRequested();
}
