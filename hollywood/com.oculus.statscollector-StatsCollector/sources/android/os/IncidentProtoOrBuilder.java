package android.os;

import android.os.IncidentProto;
import android.providers.settings.SettingsServiceDumpProto;
import android.service.GraphicsStatsServiceDumpProto;
import android.service.NetworkStatsServiceDumpProto;
import android.service.appwidget.AppWidgetServiceDumpProto;
import android.service.battery.BatteryServiceDumpProto;
import android.service.batterystats.BatteryStatsServiceDumpHistoryProto;
import android.service.batterystats.BatteryStatsServiceDumpProto;
import android.service.diskstats.DiskStatsServiceDumpProto;
import android.service.notification.NotificationServiceDumpProto;
import android.service.pm.PackageServiceDumpProto;
import android.service.print.PrintServiceDumpProto;
import android.service.procstats.ProcessStatsServiceDumpProto;
import android.service.restricted_image.RestrictedImagesDumpProto;
import android.service.usb.UsbServiceDumpProto;
import android.util.EventLogTagMapProto;
import android.util.LogProto;
import com.android.server.AlarmManagerServiceDumpProto;
import com.android.server.am.ActivityManagerServiceDumpActivitiesProto;
import com.android.server.am.ActivityManagerServiceDumpBroadcastsProto;
import com.android.server.am.ActivityManagerServiceDumpProcessesProto;
import com.android.server.am.ActivityManagerServiceDumpServicesProto;
import com.android.server.am.MemInfoDumpProto;
import com.android.server.biometrics.fingerprint.FingerprintServiceDumpProto;
import com.android.server.job.JobSchedulerServiceDumpProto;
import com.android.server.power.PowerManagerServiceDumpProto;
import com.android.server.role.RoleManagerServiceDumpProto;
import com.android.server.wm.WindowManagerServiceDumpProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessageLite;
import java.util.List;

public interface IncidentProtoOrBuilder extends GeneratedMessageLite.ExtendableMessageOrBuilder<IncidentProto, IncidentProto.Builder> {
    ActivityManagerServiceDumpActivitiesProto getActivities();

    AlarmManagerServiceDumpProto getAlarm();

    ActivityManagerServiceDumpProcessesProto getAmprocesses();

    ActivityManagerServiceDumpServicesProto getAmservices();

    AppWidgetServiceDumpProto getAppwidget();

    BatteryServiceDumpProto getBattery();

    BatteryStatsServiceDumpHistoryProto getBatteryHistory();

    BatteryTypeProto getBatteryType();

    BatteryStatsServiceDumpProto getBatterystats();

    ActivityManagerServiceDumpBroadcastsProto getBroadcasts();

    CpuFreqProto getCpuFreq();

    CpuInfoProto getCpuInfo();

    LogProto getCrashLogs();

    DiskStatsServiceDumpProto getDiskstats();

    EventLogTagMapProto getEventLogTagMap();

    LogProto getEventsLogs();

    FingerprintServiceDumpProto getFingerprint();

    GraphicsStatsServiceDumpProto getGraphicsstats();

    BackTraceProto getHalTraces();

    IncidentHeaderProto getHeader(int i);

    int getHeaderCount();

    List<IncidentHeaderProto> getHeaderList();

    BackTraceProto getJavaTraces();

    JobSchedulerServiceDumpProto getJobscheduler();

    LogProto getKernelLogs();

    String getKernelVersion();

    ByteString getKernelVersionBytes();

    KernelWakeSourcesProto getKernelWakeSources();

    GZippedFileProto getLastKmsg();

    LogProto getMainLogs();

    MemInfoDumpProto getMeminfo();

    IncidentMetadata getMetadata();

    BackTraceProto getNativeTraces();

    NetworkStatsServiceDumpProto getNetstats();

    NotificationServiceDumpProto getNotification();

    PackageServiceDumpProto getPackage();

    PageTypeInfoProto getPageTypeInfo();

    PowerManagerServiceDumpProto getPower();

    PrintServiceDumpProto getPrint();

    PsProto getProcessesAndThreads();

    ProcrankProto getProcrank();

    ProcessStatsServiceDumpProto getProcstats();

    LogProto getRadioLogs();

    RestrictedImagesDumpProto getRestrictedImages();

    RoleManagerServiceDumpProto getRole();

    LogProto getSecurityLogs();

    SettingsServiceDumpProto getSettings();

    StatsDataDumpProto getStatsData();

    LogProto getStatsLogs();

    LogProto getSystemLogs();

    SystemPropertiesProto getSystemProperties();

    ByteString getSystemTrace();

    UsbServiceDumpProto getUsb();

    WindowManagerServiceDumpProto getWindow();

    boolean hasActivities();

    boolean hasAlarm();

    boolean hasAmprocesses();

    boolean hasAmservices();

    boolean hasAppwidget();

    boolean hasBattery();

    boolean hasBatteryHistory();

    boolean hasBatteryType();

    boolean hasBatterystats();

    boolean hasBroadcasts();

    boolean hasCpuFreq();

    boolean hasCpuInfo();

    boolean hasCrashLogs();

    boolean hasDiskstats();

    boolean hasEventLogTagMap();

    boolean hasEventsLogs();

    boolean hasFingerprint();

    boolean hasGraphicsstats();

    boolean hasHalTraces();

    boolean hasJavaTraces();

    boolean hasJobscheduler();

    boolean hasKernelLogs();

    boolean hasKernelVersion();

    boolean hasKernelWakeSources();

    boolean hasLastKmsg();

    boolean hasMainLogs();

    boolean hasMeminfo();

    boolean hasMetadata();

    boolean hasNativeTraces();

    boolean hasNetstats();

    boolean hasNotification();

    boolean hasPackage();

    boolean hasPageTypeInfo();

    boolean hasPower();

    boolean hasPrint();

    boolean hasProcessesAndThreads();

    boolean hasProcrank();

    boolean hasProcstats();

    boolean hasRadioLogs();

    boolean hasRestrictedImages();

    boolean hasRole();

    boolean hasSecurityLogs();

    boolean hasSettings();

    boolean hasStatsData();

    boolean hasStatsLogs();

    boolean hasSystemLogs();

    boolean hasSystemProperties();

    boolean hasSystemTrace();

    boolean hasUsb();

    boolean hasWindow();
}
