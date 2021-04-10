package com.android.server.am;

import com.android.server.am.AppErrorsProto;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface AppErrorsProtoOrBuilder extends MessageLiteOrBuilder {
    AppErrorsProto.BadProcess getBadProcesses(int i);

    int getBadProcessesCount();

    List<AppErrorsProto.BadProcess> getBadProcessesList();

    long getNowUptimeMs();

    AppErrorsProto.ProcessCrashTime getProcessCrashTimes(int i);

    int getProcessCrashTimesCount();

    List<AppErrorsProto.ProcessCrashTime> getProcessCrashTimesList();

    boolean hasNowUptimeMs();
}
