package com.android.server.am;

import android.content.IntentProto;
import android.util.Duration;
import com.android.server.am.ServiceRecordProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface ServiceRecordProtoOrBuilder extends MessageLiteOrBuilder {
    ProcessRecordProto getApp();

    ServiceRecordProto.AppInfo getAppinfo();

    IntentBindRecordProto getBindings(int i);

    int getBindingsCount();

    List<IntentBindRecordProto> getBindingsList();

    ConnectionRecordProto getConnections(int i);

    int getConnectionsCount();

    List<ConnectionRecordProto> getConnectionsList();

    ServiceRecordProto.Crash getCrash();

    Duration getCreateRealTime();

    boolean getCreatedFromFg();

    boolean getDelayed();

    ServiceRecordProto.StartItem getDeliveredStarts(int i);

    int getDeliveredStartsCount();

    List<ServiceRecordProto.StartItem> getDeliveredStartsList();

    Duration getDestoryTime();

    ServiceRecordProto.ExecuteNesting getExecute();

    ServiceRecordProto.Foreground getForeground();

    IntentProto getIntent();

    boolean getIsRunning();

    ProcessRecordProto getIsolatedProc();

    Duration getLastActivityTime();

    String getPackageName();

    ByteString getPackageNameBytes();

    ServiceRecordProto.StartItem getPendingStarts(int i);

    int getPendingStartsCount();

    List<ServiceRecordProto.StartItem> getPendingStartsList();

    String getPermission();

    ByteString getPermissionBytes();

    int getPid();

    String getProcessName();

    ByteString getProcessNameBytes();

    Duration getRestartTime();

    String getShortName();

    ByteString getShortNameBytes();

    ServiceRecordProto.Start getStart();

    Duration getStartingBgTimeout();

    boolean getWhitelistManager();

    boolean hasApp();

    boolean hasAppinfo();

    boolean hasCrash();

    boolean hasCreateRealTime();

    boolean hasCreatedFromFg();

    boolean hasDelayed();

    boolean hasDestoryTime();

    boolean hasExecute();

    boolean hasForeground();

    boolean hasIntent();

    boolean hasIsRunning();

    boolean hasIsolatedProc();

    boolean hasLastActivityTime();

    boolean hasPackageName();

    boolean hasPermission();

    boolean hasPid();

    boolean hasProcessName();

    boolean hasRestartTime();

    boolean hasShortName();

    boolean hasStart();

    boolean hasStartingBgTimeout();

    boolean hasWhitelistManager();
}
