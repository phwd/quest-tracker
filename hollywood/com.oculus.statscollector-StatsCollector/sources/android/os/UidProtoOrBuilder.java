package android.os;

import android.os.UidProto;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface UidProtoOrBuilder extends MessageLiteOrBuilder {
    UidProto.AggregatedWakelock getAggregatedWakelock();

    TimerProto getAudio();

    ControllerActivityProto getBluetoothController();

    UidProto.BluetoothMisc getBluetoothMisc();

    TimerProto getCamera();

    UidProto.Cpu getCpu();

    TimerProto getFlashlight();

    TimerProto getForegroundActivity();

    TimerProto getForegroundService();

    UidProto.JobCompletion getJobCompletion(int i);

    int getJobCompletionCount();

    List<UidProto.JobCompletion> getJobCompletionList();

    UidProto.Job getJobs(int i);

    int getJobsCount();

    List<UidProto.Job> getJobsList();

    ControllerActivityProto getModemController();

    UidProto.Network getNetwork();

    UidProto.Package getPackages(int i);

    int getPackagesCount();

    List<UidProto.Package> getPackagesList();

    UidProto.PowerUseItem getPowerUseItem();

    UidProto.Process getProcess(int i);

    int getProcessCount();

    List<UidProto.Process> getProcessList();

    UidProto.Sensor getSensors(int i);

    int getSensorsCount();

    List<UidProto.Sensor> getSensorsList();

    UidProto.StateTime getStates(int i);

    int getStatesCount();

    List<UidProto.StateTime> getStatesList();

    UidProto.Sync getSyncs(int i);

    int getSyncsCount();

    List<UidProto.Sync> getSyncsList();

    int getUid();

    UidProto.UserActivity getUserActivity(int i);

    int getUserActivityCount();

    List<UidProto.UserActivity> getUserActivityList();

    TimerProto getVibrator();

    TimerProto getVideo();

    UidProto.Wakelock getWakelocks(int i);

    int getWakelocksCount();

    List<UidProto.Wakelock> getWakelocksList();

    UidProto.WakeupAlarm getWakeupAlarm(int i);

    int getWakeupAlarmCount();

    List<UidProto.WakeupAlarm> getWakeupAlarmList();

    UidProto.Wifi getWifi();

    ControllerActivityProto getWifiController();

    TimerProto getWifiMulticastWakelock();

    boolean hasAggregatedWakelock();

    boolean hasAudio();

    boolean hasBluetoothController();

    boolean hasBluetoothMisc();

    boolean hasCamera();

    boolean hasCpu();

    boolean hasFlashlight();

    boolean hasForegroundActivity();

    boolean hasForegroundService();

    boolean hasModemController();

    boolean hasNetwork();

    boolean hasPowerUseItem();

    boolean hasUid();

    boolean hasVibrator();

    boolean hasVideo();

    boolean hasWifi();

    boolean hasWifiController();

    boolean hasWifiMulticastWakelock();
}
