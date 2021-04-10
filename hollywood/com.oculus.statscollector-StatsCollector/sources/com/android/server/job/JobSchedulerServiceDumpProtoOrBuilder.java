package com.android.server.job;

import com.android.server.job.JobSchedulerServiceDumpProto;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface JobSchedulerServiceDumpProtoOrBuilder extends MessageLiteOrBuilder {
    JobSchedulerServiceDumpProto.ActiveJob getActiveJobs(int i);

    int getActiveJobsCount();

    List<JobSchedulerServiceDumpProto.ActiveJob> getActiveJobsList();

    int getBackingUpUids(int i);

    int getBackingUpUidsCount();

    List<Integer> getBackingUpUidsList();

    JobConcurrencyManagerProto getConcurrencyManager();

    StateControllerProto getControllers(int i);

    int getControllersCount();

    List<StateControllerProto> getControllersList();

    int getCurrentHeartbeat();

    JobPackageHistoryProto getHistory();

    boolean getInParole();

    boolean getInThermal();

    boolean getIsReadyToRock();

    long getLastHeartbeatTimeMillis();

    int getMaxActiveJobs();

    int getNextHeartbeat(int i);

    int getNextHeartbeatCount();

    List<Integer> getNextHeartbeatList();

    long getNextHeartbeatTimeMillis();

    JobPackageTrackerDumpProto getPackageTracker();

    JobSchedulerServiceDumpProto.PendingJob getPendingJobs(int i);

    int getPendingJobsCount();

    List<JobSchedulerServiceDumpProto.PendingJob> getPendingJobsList();

    JobSchedulerServiceDumpProto.PriorityOverride getPriorityOverrides(int i);

    int getPriorityOverridesCount();

    List<JobSchedulerServiceDumpProto.PriorityOverride> getPriorityOverridesList();

    JobSchedulerServiceDumpProto.RegisteredJob getRegisteredJobs(int i);

    int getRegisteredJobsCount();

    List<JobSchedulerServiceDumpProto.RegisteredJob> getRegisteredJobsList();

    boolean getReportedActive();

    ConstantsProto getSettings();

    int getStartedUsers(int i);

    int getStartedUsersCount();

    List<Integer> getStartedUsersList();

    boolean hasConcurrencyManager();

    boolean hasCurrentHeartbeat();

    boolean hasHistory();

    boolean hasInParole();

    boolean hasInThermal();

    boolean hasIsReadyToRock();

    boolean hasLastHeartbeatTimeMillis();

    boolean hasMaxActiveJobs();

    boolean hasNextHeartbeatTimeMillis();

    boolean hasPackageTracker();

    boolean hasReportedActive();

    boolean hasSettings();
}
