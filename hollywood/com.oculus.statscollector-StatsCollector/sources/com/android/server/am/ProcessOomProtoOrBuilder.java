package com.android.server.am;

import android.app.ProcessStateEnum;
import android.content.ComponentNameProto;
import com.android.server.am.ProcessOomProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface ProcessOomProtoOrBuilder extends MessageLiteOrBuilder {
    boolean getActivities();

    ProcessOomProto.AdjSourceCase getAdjSourceCase();

    String getAdjSourceObject();

    ByteString getAdjSourceObjectBytes();

    ProcessRecordProto getAdjSourceProc();

    ProcessOomProto.AdjTargetCase getAdjTargetCase();

    ComponentNameProto getAdjTargetComponentName();

    String getAdjTargetObject();

    ByteString getAdjTargetObjectBytes();

    String getAdjType();

    ByteString getAdjTypeBytes();

    ProcessOomProto.Detail getDetail();

    ProcessOomProto.ForegroundCase getForegroundCase();

    int getNum();

    String getOomAdj();

    ByteString getOomAdjBytes();

    boolean getPersistent();

    ProcessRecordProto getProc();

    ProcessOomProto.SchedGroup getSchedGroup();

    boolean getServices();

    ProcessStateEnum getState();

    int getTrimMemoryLevel();

    boolean hasActivities();

    boolean hasAdjSourceObject();

    boolean hasAdjSourceProc();

    boolean hasAdjTargetComponentName();

    boolean hasAdjTargetObject();

    boolean hasAdjType();

    boolean hasDetail();

    boolean hasNum();

    boolean hasOomAdj();

    boolean hasPersistent();

    boolean hasProc();

    boolean hasSchedGroup();

    boolean hasServices();

    boolean hasState();

    boolean hasTrimMemoryLevel();
}
