package com.android.server.am;

import android.content.ComponentNameProto;
import android.content.pm.ApplicationInfoProto;
import android.os.BundleProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface ActiveInstrumentationProtoOrBuilder extends MessageLiteOrBuilder {
    BundleProto getArguments();

    ComponentNameProto getClass_();

    boolean getFinished();

    String getProfileFile();

    ByteString getProfileFileBytes();

    ProcessRecordProto getRunningProcesses(int i);

    int getRunningProcessesCount();

    List<ProcessRecordProto> getRunningProcessesList();

    ApplicationInfoProto getTargetInfo();

    String getTargetProcesses(int i);

    ByteString getTargetProcessesBytes(int i);

    int getTargetProcessesCount();

    List<String> getTargetProcessesList();

    String getUiAutomationConnection();

    ByteString getUiAutomationConnectionBytes();

    String getWatcher();

    ByteString getWatcherBytes();

    boolean hasArguments();

    boolean hasClass_();

    boolean hasFinished();

    boolean hasProfileFile();

    boolean hasTargetInfo();

    boolean hasUiAutomationConnection();

    boolean hasWatcher();
}
