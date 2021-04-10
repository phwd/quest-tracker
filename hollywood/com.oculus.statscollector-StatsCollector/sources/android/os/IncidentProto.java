package android.os;

import android.os.BackTraceProto;
import android.os.BatteryTypeProto;
import android.os.CpuFreqProto;
import android.os.CpuInfoProto;
import android.os.GZippedFileProto;
import android.os.IncidentHeaderProto;
import android.os.IncidentMetadata;
import android.os.KernelWakeSourcesProto;
import android.os.PageTypeInfoProto;
import android.os.ProcrankProto;
import android.os.PsProto;
import android.os.StatsDataDumpProto;
import android.os.SystemPropertiesProto;
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
import android.telephony.DataConnectionPowerStateEnum;
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
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public final class IncidentProto extends GeneratedMessageLite.ExtendableMessage<IncidentProto, Builder> implements IncidentProtoOrBuilder {
    public static final int ACTIVITIES_FIELD_NUMBER = 3012;
    public static final int ALARM_FIELD_NUMBER = 3016;
    public static final int AMPROCESSES_FIELD_NUMBER = 3015;
    public static final int AMSERVICES_FIELD_NUMBER = 3014;
    public static final int APPWIDGET_FIELD_NUMBER = 3003;
    public static final int BATTERYSTATS_FIELD_NUMBER = 3005;
    public static final int BATTERY_FIELD_NUMBER = 3006;
    public static final int BATTERY_HISTORY_FIELD_NUMBER = 3022;
    public static final int BATTERY_TYPE_FIELD_NUMBER = 2006;
    public static final int BROADCASTS_FIELD_NUMBER = 3013;
    public static final int CPU_FREQ_FIELD_NUMBER = 2004;
    public static final int CPU_INFO_FIELD_NUMBER = 2003;
    public static final int CRASH_LOGS_FIELD_NUMBER = 1105;
    private static final IncidentProto DEFAULT_INSTANCE = new IncidentProto();
    public static final int DISKSTATS_FIELD_NUMBER = 3007;
    public static final int EVENTS_LOGS_FIELD_NUMBER = 1103;
    public static final int EVENT_LOG_TAG_MAP_FIELD_NUMBER = 1100;
    public static final int FINGERPRINT_FIELD_NUMBER = 3000;
    public static final int GRAPHICSSTATS_FIELD_NUMBER = 3019;
    public static final int HAL_TRACES_FIELD_NUMBER = 1201;
    public static final int HEADER_FIELD_NUMBER = 1;
    public static final int JAVA_TRACES_FIELD_NUMBER = 1202;
    public static final int JOBSCHEDULER_FIELD_NUMBER = 3020;
    public static final int KERNEL_LOGS_FIELD_NUMBER = 1108;
    public static final int KERNEL_VERSION_FIELD_NUMBER = 1002;
    public static final int KERNEL_WAKE_SOURCES_FIELD_NUMBER = 2002;
    public static final int LAST_KMSG_FIELD_NUMBER = 2007;
    public static final int MAIN_LOGS_FIELD_NUMBER = 1101;
    public static final int MEMINFO_FIELD_NUMBER = 3018;
    public static final int METADATA_FIELD_NUMBER = 2;
    public static final int NATIVE_TRACES_FIELD_NUMBER = 1200;
    public static final int NETSTATS_FIELD_NUMBER = 3001;
    public static final int NOTIFICATION_FIELD_NUMBER = 3004;
    public static final int PACKAGE_FIELD_NUMBER = 3008;
    public static final int PAGE_TYPE_INFO_FIELD_NUMBER = 2001;
    private static volatile Parser<IncidentProto> PARSER = null;
    public static final int POWER_FIELD_NUMBER = 3009;
    public static final int PRINT_FIELD_NUMBER = 3010;
    public static final int PROCESSES_AND_THREADS_FIELD_NUMBER = 2005;
    public static final int PROCRANK_FIELD_NUMBER = 2000;
    public static final int PROCSTATS_FIELD_NUMBER = 3011;
    public static final int RADIO_LOGS_FIELD_NUMBER = 1102;
    public static final int RESTRICTED_IMAGES_FIELD_NUMBER = 3025;
    public static final int ROLE_FIELD_NUMBER = 3024;
    public static final int SECURITY_LOGS_FIELD_NUMBER = 1107;
    public static final int SETTINGS_FIELD_NUMBER = 3002;
    public static final int STATS_DATA_FIELD_NUMBER = 3023;
    public static final int STATS_LOGS_FIELD_NUMBER = 1106;
    public static final int SYSTEM_LOGS_FIELD_NUMBER = 1104;
    public static final int SYSTEM_PROPERTIES_FIELD_NUMBER = 1000;
    public static final int SYSTEM_TRACE_FIELD_NUMBER = 3026;
    public static final int USB_FIELD_NUMBER = 3021;
    public static final int WINDOW_FIELD_NUMBER = 3017;
    private ActivityManagerServiceDumpActivitiesProto activities_;
    private AlarmManagerServiceDumpProto alarm_;
    private ActivityManagerServiceDumpProcessesProto amprocesses_;
    private ActivityManagerServiceDumpServicesProto amservices_;
    private AppWidgetServiceDumpProto appwidget_;
    private BatteryStatsServiceDumpHistoryProto batteryHistory_;
    private BatteryTypeProto batteryType_;
    private BatteryServiceDumpProto battery_;
    private BatteryStatsServiceDumpProto batterystats_;
    private int bitField0_;
    private int bitField1_;
    private ActivityManagerServiceDumpBroadcastsProto broadcasts_;
    private CpuFreqProto cpuFreq_;
    private CpuInfoProto cpuInfo_;
    private LogProto crashLogs_;
    private DiskStatsServiceDumpProto diskstats_;
    private EventLogTagMapProto eventLogTagMap_;
    private LogProto eventsLogs_;
    private FingerprintServiceDumpProto fingerprint_;
    private GraphicsStatsServiceDumpProto graphicsstats_;
    private BackTraceProto halTraces_;
    private Internal.ProtobufList<IncidentHeaderProto> header_ = emptyProtobufList();
    private BackTraceProto javaTraces_;
    private JobSchedulerServiceDumpProto jobscheduler_;
    private LogProto kernelLogs_;
    private String kernelVersion_ = "";
    private KernelWakeSourcesProto kernelWakeSources_;
    private GZippedFileProto lastKmsg_;
    private LogProto mainLogs_;
    private MemInfoDumpProto meminfo_;
    private byte memoizedIsInitialized = -1;
    private IncidentMetadata metadata_;
    private BackTraceProto nativeTraces_;
    private NetworkStatsServiceDumpProto netstats_;
    private NotificationServiceDumpProto notification_;
    private PackageServiceDumpProto package_;
    private PageTypeInfoProto pageTypeInfo_;
    private PowerManagerServiceDumpProto power_;
    private PrintServiceDumpProto print_;
    private PsProto processesAndThreads_;
    private ProcrankProto procrank_;
    private ProcessStatsServiceDumpProto procstats_;
    private LogProto radioLogs_;
    private RestrictedImagesDumpProto restrictedImages_;
    private RoleManagerServiceDumpProto role_;
    private LogProto securityLogs_;
    private SettingsServiceDumpProto settings_;
    private StatsDataDumpProto statsData_;
    private LogProto statsLogs_;
    private LogProto systemLogs_;
    private SystemPropertiesProto systemProperties_;
    private ByteString systemTrace_ = ByteString.EMPTY;
    private UsbServiceDumpProto usb_;
    private WindowManagerServiceDumpProto window_;

    private IncidentProto() {
    }

    @Override // android.os.IncidentProtoOrBuilder
    public List<IncidentHeaderProto> getHeaderList() {
        return this.header_;
    }

    public List<? extends IncidentHeaderProtoOrBuilder> getHeaderOrBuilderList() {
        return this.header_;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public int getHeaderCount() {
        return this.header_.size();
    }

    @Override // android.os.IncidentProtoOrBuilder
    public IncidentHeaderProto getHeader(int index) {
        return this.header_.get(index);
    }

    public IncidentHeaderProtoOrBuilder getHeaderOrBuilder(int index) {
        return this.header_.get(index);
    }

    private void ensureHeaderIsMutable() {
        if (!this.header_.isModifiable()) {
            this.header_ = GeneratedMessageLite.mutableCopy(this.header_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHeader(int index, IncidentHeaderProto value) {
        if (value != null) {
            ensureHeaderIsMutable();
            this.header_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHeader(int index, IncidentHeaderProto.Builder builderForValue) {
        ensureHeaderIsMutable();
        this.header_.set(index, (IncidentHeaderProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addHeader(IncidentHeaderProto value) {
        if (value != null) {
            ensureHeaderIsMutable();
            this.header_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addHeader(int index, IncidentHeaderProto value) {
        if (value != null) {
            ensureHeaderIsMutable();
            this.header_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addHeader(IncidentHeaderProto.Builder builderForValue) {
        ensureHeaderIsMutable();
        this.header_.add((IncidentHeaderProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addHeader(int index, IncidentHeaderProto.Builder builderForValue) {
        ensureHeaderIsMutable();
        this.header_.add(index, (IncidentHeaderProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllHeader(Iterable<? extends IncidentHeaderProto> values) {
        ensureHeaderIsMutable();
        AbstractMessageLite.addAll(values, this.header_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearHeader() {
        this.header_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeHeader(int index) {
        ensureHeaderIsMutable();
        this.header_.remove(index);
    }

    @Override // android.os.IncidentProtoOrBuilder
    public boolean hasMetadata() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public IncidentMetadata getMetadata() {
        IncidentMetadata incidentMetadata = this.metadata_;
        return incidentMetadata == null ? IncidentMetadata.getDefaultInstance() : incidentMetadata;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMetadata(IncidentMetadata value) {
        if (value != null) {
            this.metadata_ = value;
            this.bitField0_ |= 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMetadata(IncidentMetadata.Builder builderForValue) {
        this.metadata_ = (IncidentMetadata) builderForValue.build();
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeMetadata(IncidentMetadata value) {
        IncidentMetadata incidentMetadata = this.metadata_;
        if (incidentMetadata == null || incidentMetadata == IncidentMetadata.getDefaultInstance()) {
            this.metadata_ = value;
        } else {
            this.metadata_ = (IncidentMetadata) ((IncidentMetadata.Builder) IncidentMetadata.newBuilder(this.metadata_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMetadata() {
        this.metadata_ = null;
        this.bitField0_ &= -2;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public boolean hasSystemProperties() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public SystemPropertiesProto getSystemProperties() {
        SystemPropertiesProto systemPropertiesProto = this.systemProperties_;
        return systemPropertiesProto == null ? SystemPropertiesProto.getDefaultInstance() : systemPropertiesProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSystemProperties(SystemPropertiesProto value) {
        if (value != null) {
            this.systemProperties_ = value;
            this.bitField0_ |= 2;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSystemProperties(SystemPropertiesProto.Builder builderForValue) {
        this.systemProperties_ = (SystemPropertiesProto) builderForValue.build();
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeSystemProperties(SystemPropertiesProto value) {
        SystemPropertiesProto systemPropertiesProto = this.systemProperties_;
        if (systemPropertiesProto == null || systemPropertiesProto == SystemPropertiesProto.getDefaultInstance()) {
            this.systemProperties_ = value;
        } else {
            this.systemProperties_ = (SystemPropertiesProto) ((SystemPropertiesProto.Builder) SystemPropertiesProto.newBuilder(this.systemProperties_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSystemProperties() {
        this.systemProperties_ = null;
        this.bitField0_ &= -3;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public boolean hasKernelVersion() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public String getKernelVersion() {
        return this.kernelVersion_;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public ByteString getKernelVersionBytes() {
        return ByteString.copyFromUtf8(this.kernelVersion_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setKernelVersion(String value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.kernelVersion_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearKernelVersion() {
        this.bitField0_ &= -5;
        this.kernelVersion_ = getDefaultInstance().getKernelVersion();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setKernelVersionBytes(ByteString value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.kernelVersion_ = value.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    @Override // android.os.IncidentProtoOrBuilder
    public boolean hasEventLogTagMap() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public EventLogTagMapProto getEventLogTagMap() {
        EventLogTagMapProto eventLogTagMapProto = this.eventLogTagMap_;
        return eventLogTagMapProto == null ? EventLogTagMapProto.getDefaultInstance() : eventLogTagMapProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setEventLogTagMap(EventLogTagMapProto value) {
        if (value != null) {
            this.eventLogTagMap_ = value;
            this.bitField0_ |= 8;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setEventLogTagMap(EventLogTagMapProto.Builder builderForValue) {
        this.eventLogTagMap_ = (EventLogTagMapProto) builderForValue.build();
        this.bitField0_ |= 8;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeEventLogTagMap(EventLogTagMapProto value) {
        EventLogTagMapProto eventLogTagMapProto = this.eventLogTagMap_;
        if (eventLogTagMapProto == null || eventLogTagMapProto == EventLogTagMapProto.getDefaultInstance()) {
            this.eventLogTagMap_ = value;
        } else {
            this.eventLogTagMap_ = (EventLogTagMapProto) ((EventLogTagMapProto.Builder) EventLogTagMapProto.newBuilder(this.eventLogTagMap_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 8;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearEventLogTagMap() {
        this.eventLogTagMap_ = null;
        this.bitField0_ &= -9;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public boolean hasMainLogs() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public LogProto getMainLogs() {
        LogProto logProto = this.mainLogs_;
        return logProto == null ? LogProto.getDefaultInstance() : logProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMainLogs(LogProto value) {
        if (value != null) {
            this.mainLogs_ = value;
            this.bitField0_ |= 16;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMainLogs(LogProto.Builder builderForValue) {
        this.mainLogs_ = (LogProto) builderForValue.build();
        this.bitField0_ |= 16;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeMainLogs(LogProto value) {
        LogProto logProto = this.mainLogs_;
        if (logProto == null || logProto == LogProto.getDefaultInstance()) {
            this.mainLogs_ = value;
        } else {
            this.mainLogs_ = (LogProto) ((LogProto.Builder) LogProto.newBuilder(this.mainLogs_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 16;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMainLogs() {
        this.mainLogs_ = null;
        this.bitField0_ &= -17;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public boolean hasRadioLogs() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public LogProto getRadioLogs() {
        LogProto logProto = this.radioLogs_;
        return logProto == null ? LogProto.getDefaultInstance() : logProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRadioLogs(LogProto value) {
        if (value != null) {
            this.radioLogs_ = value;
            this.bitField0_ |= 32;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRadioLogs(LogProto.Builder builderForValue) {
        this.radioLogs_ = (LogProto) builderForValue.build();
        this.bitField0_ |= 32;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeRadioLogs(LogProto value) {
        LogProto logProto = this.radioLogs_;
        if (logProto == null || logProto == LogProto.getDefaultInstance()) {
            this.radioLogs_ = value;
        } else {
            this.radioLogs_ = (LogProto) ((LogProto.Builder) LogProto.newBuilder(this.radioLogs_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 32;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearRadioLogs() {
        this.radioLogs_ = null;
        this.bitField0_ &= -33;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public boolean hasEventsLogs() {
        return (this.bitField0_ & 64) == 64;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public LogProto getEventsLogs() {
        LogProto logProto = this.eventsLogs_;
        return logProto == null ? LogProto.getDefaultInstance() : logProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setEventsLogs(LogProto value) {
        if (value != null) {
            this.eventsLogs_ = value;
            this.bitField0_ |= 64;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setEventsLogs(LogProto.Builder builderForValue) {
        this.eventsLogs_ = (LogProto) builderForValue.build();
        this.bitField0_ |= 64;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeEventsLogs(LogProto value) {
        LogProto logProto = this.eventsLogs_;
        if (logProto == null || logProto == LogProto.getDefaultInstance()) {
            this.eventsLogs_ = value;
        } else {
            this.eventsLogs_ = (LogProto) ((LogProto.Builder) LogProto.newBuilder(this.eventsLogs_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 64;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearEventsLogs() {
        this.eventsLogs_ = null;
        this.bitField0_ &= -65;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public boolean hasSystemLogs() {
        return (this.bitField0_ & 128) == 128;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public LogProto getSystemLogs() {
        LogProto logProto = this.systemLogs_;
        return logProto == null ? LogProto.getDefaultInstance() : logProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSystemLogs(LogProto value) {
        if (value != null) {
            this.systemLogs_ = value;
            this.bitField0_ |= 128;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSystemLogs(LogProto.Builder builderForValue) {
        this.systemLogs_ = (LogProto) builderForValue.build();
        this.bitField0_ |= 128;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeSystemLogs(LogProto value) {
        LogProto logProto = this.systemLogs_;
        if (logProto == null || logProto == LogProto.getDefaultInstance()) {
            this.systemLogs_ = value;
        } else {
            this.systemLogs_ = (LogProto) ((LogProto.Builder) LogProto.newBuilder(this.systemLogs_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 128;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSystemLogs() {
        this.systemLogs_ = null;
        this.bitField0_ &= -129;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public boolean hasCrashLogs() {
        return (this.bitField0_ & 256) == 256;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public LogProto getCrashLogs() {
        LogProto logProto = this.crashLogs_;
        return logProto == null ? LogProto.getDefaultInstance() : logProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCrashLogs(LogProto value) {
        if (value != null) {
            this.crashLogs_ = value;
            this.bitField0_ |= 256;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCrashLogs(LogProto.Builder builderForValue) {
        this.crashLogs_ = (LogProto) builderForValue.build();
        this.bitField0_ |= 256;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeCrashLogs(LogProto value) {
        LogProto logProto = this.crashLogs_;
        if (logProto == null || logProto == LogProto.getDefaultInstance()) {
            this.crashLogs_ = value;
        } else {
            this.crashLogs_ = (LogProto) ((LogProto.Builder) LogProto.newBuilder(this.crashLogs_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 256;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCrashLogs() {
        this.crashLogs_ = null;
        this.bitField0_ &= -257;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public boolean hasStatsLogs() {
        return (this.bitField0_ & 512) == 512;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public LogProto getStatsLogs() {
        LogProto logProto = this.statsLogs_;
        return logProto == null ? LogProto.getDefaultInstance() : logProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStatsLogs(LogProto value) {
        if (value != null) {
            this.statsLogs_ = value;
            this.bitField0_ |= 512;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStatsLogs(LogProto.Builder builderForValue) {
        this.statsLogs_ = (LogProto) builderForValue.build();
        this.bitField0_ |= 512;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeStatsLogs(LogProto value) {
        LogProto logProto = this.statsLogs_;
        if (logProto == null || logProto == LogProto.getDefaultInstance()) {
            this.statsLogs_ = value;
        } else {
            this.statsLogs_ = (LogProto) ((LogProto.Builder) LogProto.newBuilder(this.statsLogs_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 512;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearStatsLogs() {
        this.statsLogs_ = null;
        this.bitField0_ &= -513;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public boolean hasSecurityLogs() {
        return (this.bitField0_ & 1024) == 1024;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public LogProto getSecurityLogs() {
        LogProto logProto = this.securityLogs_;
        return logProto == null ? LogProto.getDefaultInstance() : logProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSecurityLogs(LogProto value) {
        if (value != null) {
            this.securityLogs_ = value;
            this.bitField0_ |= 1024;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSecurityLogs(LogProto.Builder builderForValue) {
        this.securityLogs_ = (LogProto) builderForValue.build();
        this.bitField0_ |= 1024;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeSecurityLogs(LogProto value) {
        LogProto logProto = this.securityLogs_;
        if (logProto == null || logProto == LogProto.getDefaultInstance()) {
            this.securityLogs_ = value;
        } else {
            this.securityLogs_ = (LogProto) ((LogProto.Builder) LogProto.newBuilder(this.securityLogs_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1024;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSecurityLogs() {
        this.securityLogs_ = null;
        this.bitField0_ &= -1025;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public boolean hasKernelLogs() {
        return (this.bitField0_ & 2048) == 2048;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public LogProto getKernelLogs() {
        LogProto logProto = this.kernelLogs_;
        return logProto == null ? LogProto.getDefaultInstance() : logProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setKernelLogs(LogProto value) {
        if (value != null) {
            this.kernelLogs_ = value;
            this.bitField0_ |= 2048;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setKernelLogs(LogProto.Builder builderForValue) {
        this.kernelLogs_ = (LogProto) builderForValue.build();
        this.bitField0_ |= 2048;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeKernelLogs(LogProto value) {
        LogProto logProto = this.kernelLogs_;
        if (logProto == null || logProto == LogProto.getDefaultInstance()) {
            this.kernelLogs_ = value;
        } else {
            this.kernelLogs_ = (LogProto) ((LogProto.Builder) LogProto.newBuilder(this.kernelLogs_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 2048;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearKernelLogs() {
        this.kernelLogs_ = null;
        this.bitField0_ &= -2049;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public boolean hasNativeTraces() {
        return (this.bitField0_ & 4096) == 4096;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public BackTraceProto getNativeTraces() {
        BackTraceProto backTraceProto = this.nativeTraces_;
        return backTraceProto == null ? BackTraceProto.getDefaultInstance() : backTraceProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNativeTraces(BackTraceProto value) {
        if (value != null) {
            this.nativeTraces_ = value;
            this.bitField0_ |= 4096;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNativeTraces(BackTraceProto.Builder builderForValue) {
        this.nativeTraces_ = (BackTraceProto) builderForValue.build();
        this.bitField0_ |= 4096;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeNativeTraces(BackTraceProto value) {
        BackTraceProto backTraceProto = this.nativeTraces_;
        if (backTraceProto == null || backTraceProto == BackTraceProto.getDefaultInstance()) {
            this.nativeTraces_ = value;
        } else {
            this.nativeTraces_ = (BackTraceProto) ((BackTraceProto.Builder) BackTraceProto.newBuilder(this.nativeTraces_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 4096;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearNativeTraces() {
        this.nativeTraces_ = null;
        this.bitField0_ &= -4097;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public boolean hasHalTraces() {
        return (this.bitField0_ & 8192) == 8192;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public BackTraceProto getHalTraces() {
        BackTraceProto backTraceProto = this.halTraces_;
        return backTraceProto == null ? BackTraceProto.getDefaultInstance() : backTraceProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHalTraces(BackTraceProto value) {
        if (value != null) {
            this.halTraces_ = value;
            this.bitField0_ |= 8192;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHalTraces(BackTraceProto.Builder builderForValue) {
        this.halTraces_ = (BackTraceProto) builderForValue.build();
        this.bitField0_ |= 8192;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeHalTraces(BackTraceProto value) {
        BackTraceProto backTraceProto = this.halTraces_;
        if (backTraceProto == null || backTraceProto == BackTraceProto.getDefaultInstance()) {
            this.halTraces_ = value;
        } else {
            this.halTraces_ = (BackTraceProto) ((BackTraceProto.Builder) BackTraceProto.newBuilder(this.halTraces_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 8192;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearHalTraces() {
        this.halTraces_ = null;
        this.bitField0_ &= -8193;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public boolean hasJavaTraces() {
        return (this.bitField0_ & 16384) == 16384;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public BackTraceProto getJavaTraces() {
        BackTraceProto backTraceProto = this.javaTraces_;
        return backTraceProto == null ? BackTraceProto.getDefaultInstance() : backTraceProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setJavaTraces(BackTraceProto value) {
        if (value != null) {
            this.javaTraces_ = value;
            this.bitField0_ |= 16384;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setJavaTraces(BackTraceProto.Builder builderForValue) {
        this.javaTraces_ = (BackTraceProto) builderForValue.build();
        this.bitField0_ |= 16384;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeJavaTraces(BackTraceProto value) {
        BackTraceProto backTraceProto = this.javaTraces_;
        if (backTraceProto == null || backTraceProto == BackTraceProto.getDefaultInstance()) {
            this.javaTraces_ = value;
        } else {
            this.javaTraces_ = (BackTraceProto) ((BackTraceProto.Builder) BackTraceProto.newBuilder(this.javaTraces_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 16384;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearJavaTraces() {
        this.javaTraces_ = null;
        this.bitField0_ &= -16385;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public boolean hasProcrank() {
        return (this.bitField0_ & 32768) == 32768;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public ProcrankProto getProcrank() {
        ProcrankProto procrankProto = this.procrank_;
        return procrankProto == null ? ProcrankProto.getDefaultInstance() : procrankProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProcrank(ProcrankProto value) {
        if (value != null) {
            this.procrank_ = value;
            this.bitField0_ |= 32768;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProcrank(ProcrankProto.Builder builderForValue) {
        this.procrank_ = (ProcrankProto) builderForValue.build();
        this.bitField0_ |= 32768;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeProcrank(ProcrankProto value) {
        ProcrankProto procrankProto = this.procrank_;
        if (procrankProto == null || procrankProto == ProcrankProto.getDefaultInstance()) {
            this.procrank_ = value;
        } else {
            this.procrank_ = (ProcrankProto) ((ProcrankProto.Builder) ProcrankProto.newBuilder(this.procrank_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 32768;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearProcrank() {
        this.procrank_ = null;
        this.bitField0_ &= -32769;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public boolean hasPageTypeInfo() {
        return (this.bitField0_ & 65536) == 65536;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public PageTypeInfoProto getPageTypeInfo() {
        PageTypeInfoProto pageTypeInfoProto = this.pageTypeInfo_;
        return pageTypeInfoProto == null ? PageTypeInfoProto.getDefaultInstance() : pageTypeInfoProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPageTypeInfo(PageTypeInfoProto value) {
        if (value != null) {
            this.pageTypeInfo_ = value;
            this.bitField0_ |= 65536;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPageTypeInfo(PageTypeInfoProto.Builder builderForValue) {
        this.pageTypeInfo_ = (PageTypeInfoProto) builderForValue.build();
        this.bitField0_ |= 65536;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergePageTypeInfo(PageTypeInfoProto value) {
        PageTypeInfoProto pageTypeInfoProto = this.pageTypeInfo_;
        if (pageTypeInfoProto == null || pageTypeInfoProto == PageTypeInfoProto.getDefaultInstance()) {
            this.pageTypeInfo_ = value;
        } else {
            this.pageTypeInfo_ = (PageTypeInfoProto) ((PageTypeInfoProto.Builder) PageTypeInfoProto.newBuilder(this.pageTypeInfo_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 65536;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPageTypeInfo() {
        this.pageTypeInfo_ = null;
        this.bitField0_ &= -65537;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public boolean hasKernelWakeSources() {
        return (this.bitField0_ & 131072) == 131072;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public KernelWakeSourcesProto getKernelWakeSources() {
        KernelWakeSourcesProto kernelWakeSourcesProto = this.kernelWakeSources_;
        return kernelWakeSourcesProto == null ? KernelWakeSourcesProto.getDefaultInstance() : kernelWakeSourcesProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setKernelWakeSources(KernelWakeSourcesProto value) {
        if (value != null) {
            this.kernelWakeSources_ = value;
            this.bitField0_ |= 131072;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setKernelWakeSources(KernelWakeSourcesProto.Builder builderForValue) {
        this.kernelWakeSources_ = (KernelWakeSourcesProto) builderForValue.build();
        this.bitField0_ |= 131072;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeKernelWakeSources(KernelWakeSourcesProto value) {
        KernelWakeSourcesProto kernelWakeSourcesProto = this.kernelWakeSources_;
        if (kernelWakeSourcesProto == null || kernelWakeSourcesProto == KernelWakeSourcesProto.getDefaultInstance()) {
            this.kernelWakeSources_ = value;
        } else {
            this.kernelWakeSources_ = (KernelWakeSourcesProto) ((KernelWakeSourcesProto.Builder) KernelWakeSourcesProto.newBuilder(this.kernelWakeSources_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 131072;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearKernelWakeSources() {
        this.kernelWakeSources_ = null;
        this.bitField0_ &= -131073;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public boolean hasCpuInfo() {
        return (this.bitField0_ & 262144) == 262144;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public CpuInfoProto getCpuInfo() {
        CpuInfoProto cpuInfoProto = this.cpuInfo_;
        return cpuInfoProto == null ? CpuInfoProto.getDefaultInstance() : cpuInfoProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCpuInfo(CpuInfoProto value) {
        if (value != null) {
            this.cpuInfo_ = value;
            this.bitField0_ |= 262144;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCpuInfo(CpuInfoProto.Builder builderForValue) {
        this.cpuInfo_ = (CpuInfoProto) builderForValue.build();
        this.bitField0_ |= 262144;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeCpuInfo(CpuInfoProto value) {
        CpuInfoProto cpuInfoProto = this.cpuInfo_;
        if (cpuInfoProto == null || cpuInfoProto == CpuInfoProto.getDefaultInstance()) {
            this.cpuInfo_ = value;
        } else {
            this.cpuInfo_ = (CpuInfoProto) ((CpuInfoProto.Builder) CpuInfoProto.newBuilder(this.cpuInfo_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 262144;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCpuInfo() {
        this.cpuInfo_ = null;
        this.bitField0_ &= -262145;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public boolean hasCpuFreq() {
        return (this.bitField0_ & 524288) == 524288;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public CpuFreqProto getCpuFreq() {
        CpuFreqProto cpuFreqProto = this.cpuFreq_;
        return cpuFreqProto == null ? CpuFreqProto.getDefaultInstance() : cpuFreqProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCpuFreq(CpuFreqProto value) {
        if (value != null) {
            this.cpuFreq_ = value;
            this.bitField0_ |= 524288;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCpuFreq(CpuFreqProto.Builder builderForValue) {
        this.cpuFreq_ = (CpuFreqProto) builderForValue.build();
        this.bitField0_ |= 524288;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeCpuFreq(CpuFreqProto value) {
        CpuFreqProto cpuFreqProto = this.cpuFreq_;
        if (cpuFreqProto == null || cpuFreqProto == CpuFreqProto.getDefaultInstance()) {
            this.cpuFreq_ = value;
        } else {
            this.cpuFreq_ = (CpuFreqProto) ((CpuFreqProto.Builder) CpuFreqProto.newBuilder(this.cpuFreq_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 524288;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCpuFreq() {
        this.cpuFreq_ = null;
        this.bitField0_ &= -524289;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public boolean hasProcessesAndThreads() {
        return (this.bitField0_ & 1048576) == 1048576;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public PsProto getProcessesAndThreads() {
        PsProto psProto = this.processesAndThreads_;
        return psProto == null ? PsProto.getDefaultInstance() : psProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProcessesAndThreads(PsProto value) {
        if (value != null) {
            this.processesAndThreads_ = value;
            this.bitField0_ |= 1048576;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProcessesAndThreads(PsProto.Builder builderForValue) {
        this.processesAndThreads_ = (PsProto) builderForValue.build();
        this.bitField0_ |= 1048576;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeProcessesAndThreads(PsProto value) {
        PsProto psProto = this.processesAndThreads_;
        if (psProto == null || psProto == PsProto.getDefaultInstance()) {
            this.processesAndThreads_ = value;
        } else {
            this.processesAndThreads_ = (PsProto) ((PsProto.Builder) PsProto.newBuilder(this.processesAndThreads_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1048576;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearProcessesAndThreads() {
        this.processesAndThreads_ = null;
        this.bitField0_ &= -1048577;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public boolean hasBatteryType() {
        return (this.bitField0_ & 2097152) == 2097152;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public BatteryTypeProto getBatteryType() {
        BatteryTypeProto batteryTypeProto = this.batteryType_;
        return batteryTypeProto == null ? BatteryTypeProto.getDefaultInstance() : batteryTypeProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBatteryType(BatteryTypeProto value) {
        if (value != null) {
            this.batteryType_ = value;
            this.bitField0_ |= 2097152;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBatteryType(BatteryTypeProto.Builder builderForValue) {
        this.batteryType_ = (BatteryTypeProto) builderForValue.build();
        this.bitField0_ |= 2097152;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeBatteryType(BatteryTypeProto value) {
        BatteryTypeProto batteryTypeProto = this.batteryType_;
        if (batteryTypeProto == null || batteryTypeProto == BatteryTypeProto.getDefaultInstance()) {
            this.batteryType_ = value;
        } else {
            this.batteryType_ = (BatteryTypeProto) ((BatteryTypeProto.Builder) BatteryTypeProto.newBuilder(this.batteryType_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 2097152;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearBatteryType() {
        this.batteryType_ = null;
        this.bitField0_ &= -2097153;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public boolean hasLastKmsg() {
        return (this.bitField0_ & 4194304) == 4194304;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public GZippedFileProto getLastKmsg() {
        GZippedFileProto gZippedFileProto = this.lastKmsg_;
        return gZippedFileProto == null ? GZippedFileProto.getDefaultInstance() : gZippedFileProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLastKmsg(GZippedFileProto value) {
        if (value != null) {
            this.lastKmsg_ = value;
            this.bitField0_ |= 4194304;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLastKmsg(GZippedFileProto.Builder builderForValue) {
        this.lastKmsg_ = (GZippedFileProto) builderForValue.build();
        this.bitField0_ |= 4194304;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeLastKmsg(GZippedFileProto value) {
        GZippedFileProto gZippedFileProto = this.lastKmsg_;
        if (gZippedFileProto == null || gZippedFileProto == GZippedFileProto.getDefaultInstance()) {
            this.lastKmsg_ = value;
        } else {
            this.lastKmsg_ = (GZippedFileProto) ((GZippedFileProto.Builder) GZippedFileProto.newBuilder(this.lastKmsg_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 4194304;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLastKmsg() {
        this.lastKmsg_ = null;
        this.bitField0_ &= -4194305;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public boolean hasFingerprint() {
        return (this.bitField0_ & 8388608) == 8388608;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public FingerprintServiceDumpProto getFingerprint() {
        FingerprintServiceDumpProto fingerprintServiceDumpProto = this.fingerprint_;
        return fingerprintServiceDumpProto == null ? FingerprintServiceDumpProto.getDefaultInstance() : fingerprintServiceDumpProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFingerprint(FingerprintServiceDumpProto value) {
        if (value != null) {
            this.fingerprint_ = value;
            this.bitField0_ |= 8388608;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFingerprint(FingerprintServiceDumpProto.Builder builderForValue) {
        this.fingerprint_ = (FingerprintServiceDumpProto) builderForValue.build();
        this.bitField0_ |= 8388608;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeFingerprint(FingerprintServiceDumpProto value) {
        FingerprintServiceDumpProto fingerprintServiceDumpProto = this.fingerprint_;
        if (fingerprintServiceDumpProto == null || fingerprintServiceDumpProto == FingerprintServiceDumpProto.getDefaultInstance()) {
            this.fingerprint_ = value;
        } else {
            this.fingerprint_ = (FingerprintServiceDumpProto) ((FingerprintServiceDumpProto.Builder) FingerprintServiceDumpProto.newBuilder(this.fingerprint_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 8388608;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFingerprint() {
        this.fingerprint_ = null;
        this.bitField0_ &= -8388609;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public boolean hasNetstats() {
        return (this.bitField0_ & 16777216) == 16777216;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public NetworkStatsServiceDumpProto getNetstats() {
        NetworkStatsServiceDumpProto networkStatsServiceDumpProto = this.netstats_;
        return networkStatsServiceDumpProto == null ? NetworkStatsServiceDumpProto.getDefaultInstance() : networkStatsServiceDumpProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNetstats(NetworkStatsServiceDumpProto value) {
        if (value != null) {
            this.netstats_ = value;
            this.bitField0_ |= 16777216;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNetstats(NetworkStatsServiceDumpProto.Builder builderForValue) {
        this.netstats_ = (NetworkStatsServiceDumpProto) builderForValue.build();
        this.bitField0_ |= 16777216;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeNetstats(NetworkStatsServiceDumpProto value) {
        NetworkStatsServiceDumpProto networkStatsServiceDumpProto = this.netstats_;
        if (networkStatsServiceDumpProto == null || networkStatsServiceDumpProto == NetworkStatsServiceDumpProto.getDefaultInstance()) {
            this.netstats_ = value;
        } else {
            this.netstats_ = (NetworkStatsServiceDumpProto) ((NetworkStatsServiceDumpProto.Builder) NetworkStatsServiceDumpProto.newBuilder(this.netstats_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 16777216;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearNetstats() {
        this.netstats_ = null;
        this.bitField0_ &= -16777217;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public boolean hasSettings() {
        return (this.bitField0_ & 33554432) == 33554432;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public SettingsServiceDumpProto getSettings() {
        SettingsServiceDumpProto settingsServiceDumpProto = this.settings_;
        return settingsServiceDumpProto == null ? SettingsServiceDumpProto.getDefaultInstance() : settingsServiceDumpProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSettings(SettingsServiceDumpProto value) {
        if (value != null) {
            this.settings_ = value;
            this.bitField0_ |= 33554432;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSettings(SettingsServiceDumpProto.Builder builderForValue) {
        this.settings_ = (SettingsServiceDumpProto) builderForValue.build();
        this.bitField0_ |= 33554432;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeSettings(SettingsServiceDumpProto value) {
        SettingsServiceDumpProto settingsServiceDumpProto = this.settings_;
        if (settingsServiceDumpProto == null || settingsServiceDumpProto == SettingsServiceDumpProto.getDefaultInstance()) {
            this.settings_ = value;
        } else {
            this.settings_ = (SettingsServiceDumpProto) ((SettingsServiceDumpProto.Builder) SettingsServiceDumpProto.newBuilder(this.settings_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 33554432;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSettings() {
        this.settings_ = null;
        this.bitField0_ &= -33554433;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public boolean hasAppwidget() {
        return (this.bitField0_ & 67108864) == 67108864;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public AppWidgetServiceDumpProto getAppwidget() {
        AppWidgetServiceDumpProto appWidgetServiceDumpProto = this.appwidget_;
        return appWidgetServiceDumpProto == null ? AppWidgetServiceDumpProto.getDefaultInstance() : appWidgetServiceDumpProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAppwidget(AppWidgetServiceDumpProto value) {
        if (value != null) {
            this.appwidget_ = value;
            this.bitField0_ |= 67108864;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAppwidget(AppWidgetServiceDumpProto.Builder builderForValue) {
        this.appwidget_ = (AppWidgetServiceDumpProto) builderForValue.build();
        this.bitField0_ |= 67108864;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeAppwidget(AppWidgetServiceDumpProto value) {
        AppWidgetServiceDumpProto appWidgetServiceDumpProto = this.appwidget_;
        if (appWidgetServiceDumpProto == null || appWidgetServiceDumpProto == AppWidgetServiceDumpProto.getDefaultInstance()) {
            this.appwidget_ = value;
        } else {
            this.appwidget_ = (AppWidgetServiceDumpProto) ((AppWidgetServiceDumpProto.Builder) AppWidgetServiceDumpProto.newBuilder(this.appwidget_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 67108864;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAppwidget() {
        this.appwidget_ = null;
        this.bitField0_ &= -67108865;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public boolean hasNotification() {
        return (this.bitField0_ & 134217728) == 134217728;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public NotificationServiceDumpProto getNotification() {
        NotificationServiceDumpProto notificationServiceDumpProto = this.notification_;
        return notificationServiceDumpProto == null ? NotificationServiceDumpProto.getDefaultInstance() : notificationServiceDumpProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNotification(NotificationServiceDumpProto value) {
        if (value != null) {
            this.notification_ = value;
            this.bitField0_ |= 134217728;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNotification(NotificationServiceDumpProto.Builder builderForValue) {
        this.notification_ = (NotificationServiceDumpProto) builderForValue.build();
        this.bitField0_ |= 134217728;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeNotification(NotificationServiceDumpProto value) {
        NotificationServiceDumpProto notificationServiceDumpProto = this.notification_;
        if (notificationServiceDumpProto == null || notificationServiceDumpProto == NotificationServiceDumpProto.getDefaultInstance()) {
            this.notification_ = value;
        } else {
            this.notification_ = (NotificationServiceDumpProto) ((NotificationServiceDumpProto.Builder) NotificationServiceDumpProto.newBuilder(this.notification_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 134217728;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearNotification() {
        this.notification_ = null;
        this.bitField0_ &= -134217729;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public boolean hasBatterystats() {
        return (this.bitField0_ & 268435456) == 268435456;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public BatteryStatsServiceDumpProto getBatterystats() {
        BatteryStatsServiceDumpProto batteryStatsServiceDumpProto = this.batterystats_;
        return batteryStatsServiceDumpProto == null ? BatteryStatsServiceDumpProto.getDefaultInstance() : batteryStatsServiceDumpProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBatterystats(BatteryStatsServiceDumpProto value) {
        if (value != null) {
            this.batterystats_ = value;
            this.bitField0_ |= 268435456;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBatterystats(BatteryStatsServiceDumpProto.Builder builderForValue) {
        this.batterystats_ = (BatteryStatsServiceDumpProto) builderForValue.build();
        this.bitField0_ |= 268435456;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeBatterystats(BatteryStatsServiceDumpProto value) {
        BatteryStatsServiceDumpProto batteryStatsServiceDumpProto = this.batterystats_;
        if (batteryStatsServiceDumpProto == null || batteryStatsServiceDumpProto == BatteryStatsServiceDumpProto.getDefaultInstance()) {
            this.batterystats_ = value;
        } else {
            this.batterystats_ = (BatteryStatsServiceDumpProto) ((BatteryStatsServiceDumpProto.Builder) BatteryStatsServiceDumpProto.newBuilder(this.batterystats_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 268435456;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearBatterystats() {
        this.batterystats_ = null;
        this.bitField0_ &= -268435457;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public boolean hasBattery() {
        return (this.bitField0_ & 536870912) == 536870912;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public BatteryServiceDumpProto getBattery() {
        BatteryServiceDumpProto batteryServiceDumpProto = this.battery_;
        return batteryServiceDumpProto == null ? BatteryServiceDumpProto.getDefaultInstance() : batteryServiceDumpProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBattery(BatteryServiceDumpProto value) {
        if (value != null) {
            this.battery_ = value;
            this.bitField0_ |= 536870912;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBattery(BatteryServiceDumpProto.Builder builderForValue) {
        this.battery_ = (BatteryServiceDumpProto) builderForValue.build();
        this.bitField0_ |= 536870912;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeBattery(BatteryServiceDumpProto value) {
        BatteryServiceDumpProto batteryServiceDumpProto = this.battery_;
        if (batteryServiceDumpProto == null || batteryServiceDumpProto == BatteryServiceDumpProto.getDefaultInstance()) {
            this.battery_ = value;
        } else {
            this.battery_ = (BatteryServiceDumpProto) ((BatteryServiceDumpProto.Builder) BatteryServiceDumpProto.newBuilder(this.battery_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 536870912;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearBattery() {
        this.battery_ = null;
        this.bitField0_ &= -536870913;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public boolean hasDiskstats() {
        return (this.bitField0_ & 1073741824) == 1073741824;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public DiskStatsServiceDumpProto getDiskstats() {
        DiskStatsServiceDumpProto diskStatsServiceDumpProto = this.diskstats_;
        return diskStatsServiceDumpProto == null ? DiskStatsServiceDumpProto.getDefaultInstance() : diskStatsServiceDumpProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDiskstats(DiskStatsServiceDumpProto value) {
        if (value != null) {
            this.diskstats_ = value;
            this.bitField0_ |= 1073741824;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDiskstats(DiskStatsServiceDumpProto.Builder builderForValue) {
        this.diskstats_ = (DiskStatsServiceDumpProto) builderForValue.build();
        this.bitField0_ |= 1073741824;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeDiskstats(DiskStatsServiceDumpProto value) {
        DiskStatsServiceDumpProto diskStatsServiceDumpProto = this.diskstats_;
        if (diskStatsServiceDumpProto == null || diskStatsServiceDumpProto == DiskStatsServiceDumpProto.getDefaultInstance()) {
            this.diskstats_ = value;
        } else {
            this.diskstats_ = (DiskStatsServiceDumpProto) ((DiskStatsServiceDumpProto.Builder) DiskStatsServiceDumpProto.newBuilder(this.diskstats_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1073741824;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDiskstats() {
        this.diskstats_ = null;
        this.bitField0_ &= -1073741825;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public boolean hasPackage() {
        return (this.bitField0_ & Integer.MIN_VALUE) == Integer.MIN_VALUE;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public PackageServiceDumpProto getPackage() {
        PackageServiceDumpProto packageServiceDumpProto = this.package_;
        return packageServiceDumpProto == null ? PackageServiceDumpProto.getDefaultInstance() : packageServiceDumpProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPackage(PackageServiceDumpProto value) {
        if (value != null) {
            this.package_ = value;
            this.bitField0_ |= Integer.MIN_VALUE;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPackage(PackageServiceDumpProto.Builder builderForValue) {
        this.package_ = (PackageServiceDumpProto) builderForValue.build();
        this.bitField0_ |= Integer.MIN_VALUE;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergePackage(PackageServiceDumpProto value) {
        PackageServiceDumpProto packageServiceDumpProto = this.package_;
        if (packageServiceDumpProto == null || packageServiceDumpProto == PackageServiceDumpProto.getDefaultInstance()) {
            this.package_ = value;
        } else {
            this.package_ = (PackageServiceDumpProto) ((PackageServiceDumpProto.Builder) PackageServiceDumpProto.newBuilder(this.package_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= Integer.MIN_VALUE;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPackage() {
        this.package_ = null;
        this.bitField0_ &= DataConnectionPowerStateEnum.DATA_CONNECTION_POWER_STATE_UNKNOWN_VALUE;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public boolean hasPower() {
        return (this.bitField1_ & 1) == 1;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public PowerManagerServiceDumpProto getPower() {
        PowerManagerServiceDumpProto powerManagerServiceDumpProto = this.power_;
        return powerManagerServiceDumpProto == null ? PowerManagerServiceDumpProto.getDefaultInstance() : powerManagerServiceDumpProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPower(PowerManagerServiceDumpProto value) {
        if (value != null) {
            this.power_ = value;
            this.bitField1_ |= 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPower(PowerManagerServiceDumpProto.Builder builderForValue) {
        this.power_ = (PowerManagerServiceDumpProto) builderForValue.build();
        this.bitField1_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergePower(PowerManagerServiceDumpProto value) {
        PowerManagerServiceDumpProto powerManagerServiceDumpProto = this.power_;
        if (powerManagerServiceDumpProto == null || powerManagerServiceDumpProto == PowerManagerServiceDumpProto.getDefaultInstance()) {
            this.power_ = value;
        } else {
            this.power_ = (PowerManagerServiceDumpProto) ((PowerManagerServiceDumpProto.Builder) PowerManagerServiceDumpProto.newBuilder(this.power_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField1_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPower() {
        this.power_ = null;
        this.bitField1_ &= -2;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public boolean hasPrint() {
        return (this.bitField1_ & 2) == 2;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public PrintServiceDumpProto getPrint() {
        PrintServiceDumpProto printServiceDumpProto = this.print_;
        return printServiceDumpProto == null ? PrintServiceDumpProto.getDefaultInstance() : printServiceDumpProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPrint(PrintServiceDumpProto value) {
        if (value != null) {
            this.print_ = value;
            this.bitField1_ |= 2;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPrint(PrintServiceDumpProto.Builder builderForValue) {
        this.print_ = (PrintServiceDumpProto) builderForValue.build();
        this.bitField1_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergePrint(PrintServiceDumpProto value) {
        PrintServiceDumpProto printServiceDumpProto = this.print_;
        if (printServiceDumpProto == null || printServiceDumpProto == PrintServiceDumpProto.getDefaultInstance()) {
            this.print_ = value;
        } else {
            this.print_ = (PrintServiceDumpProto) ((PrintServiceDumpProto.Builder) PrintServiceDumpProto.newBuilder(this.print_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField1_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPrint() {
        this.print_ = null;
        this.bitField1_ &= -3;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public boolean hasProcstats() {
        return (this.bitField1_ & 4) == 4;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public ProcessStatsServiceDumpProto getProcstats() {
        ProcessStatsServiceDumpProto processStatsServiceDumpProto = this.procstats_;
        return processStatsServiceDumpProto == null ? ProcessStatsServiceDumpProto.getDefaultInstance() : processStatsServiceDumpProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProcstats(ProcessStatsServiceDumpProto value) {
        if (value != null) {
            this.procstats_ = value;
            this.bitField1_ |= 4;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProcstats(ProcessStatsServiceDumpProto.Builder builderForValue) {
        this.procstats_ = (ProcessStatsServiceDumpProto) builderForValue.build();
        this.bitField1_ |= 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeProcstats(ProcessStatsServiceDumpProto value) {
        ProcessStatsServiceDumpProto processStatsServiceDumpProto = this.procstats_;
        if (processStatsServiceDumpProto == null || processStatsServiceDumpProto == ProcessStatsServiceDumpProto.getDefaultInstance()) {
            this.procstats_ = value;
        } else {
            this.procstats_ = (ProcessStatsServiceDumpProto) ((ProcessStatsServiceDumpProto.Builder) ProcessStatsServiceDumpProto.newBuilder(this.procstats_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField1_ |= 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearProcstats() {
        this.procstats_ = null;
        this.bitField1_ &= -5;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public boolean hasActivities() {
        return (this.bitField1_ & 8) == 8;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public ActivityManagerServiceDumpActivitiesProto getActivities() {
        ActivityManagerServiceDumpActivitiesProto activityManagerServiceDumpActivitiesProto = this.activities_;
        return activityManagerServiceDumpActivitiesProto == null ? ActivityManagerServiceDumpActivitiesProto.getDefaultInstance() : activityManagerServiceDumpActivitiesProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setActivities(ActivityManagerServiceDumpActivitiesProto value) {
        if (value != null) {
            this.activities_ = value;
            this.bitField1_ |= 8;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setActivities(ActivityManagerServiceDumpActivitiesProto.Builder builderForValue) {
        this.activities_ = (ActivityManagerServiceDumpActivitiesProto) builderForValue.build();
        this.bitField1_ |= 8;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeActivities(ActivityManagerServiceDumpActivitiesProto value) {
        ActivityManagerServiceDumpActivitiesProto activityManagerServiceDumpActivitiesProto = this.activities_;
        if (activityManagerServiceDumpActivitiesProto == null || activityManagerServiceDumpActivitiesProto == ActivityManagerServiceDumpActivitiesProto.getDefaultInstance()) {
            this.activities_ = value;
        } else {
            this.activities_ = (ActivityManagerServiceDumpActivitiesProto) ((ActivityManagerServiceDumpActivitiesProto.Builder) ActivityManagerServiceDumpActivitiesProto.newBuilder(this.activities_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField1_ |= 8;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearActivities() {
        this.activities_ = null;
        this.bitField1_ &= -9;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public boolean hasBroadcasts() {
        return (this.bitField1_ & 16) == 16;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public ActivityManagerServiceDumpBroadcastsProto getBroadcasts() {
        ActivityManagerServiceDumpBroadcastsProto activityManagerServiceDumpBroadcastsProto = this.broadcasts_;
        return activityManagerServiceDumpBroadcastsProto == null ? ActivityManagerServiceDumpBroadcastsProto.getDefaultInstance() : activityManagerServiceDumpBroadcastsProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBroadcasts(ActivityManagerServiceDumpBroadcastsProto value) {
        if (value != null) {
            this.broadcasts_ = value;
            this.bitField1_ |= 16;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBroadcasts(ActivityManagerServiceDumpBroadcastsProto.Builder builderForValue) {
        this.broadcasts_ = (ActivityManagerServiceDumpBroadcastsProto) builderForValue.build();
        this.bitField1_ |= 16;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeBroadcasts(ActivityManagerServiceDumpBroadcastsProto value) {
        ActivityManagerServiceDumpBroadcastsProto activityManagerServiceDumpBroadcastsProto = this.broadcasts_;
        if (activityManagerServiceDumpBroadcastsProto == null || activityManagerServiceDumpBroadcastsProto == ActivityManagerServiceDumpBroadcastsProto.getDefaultInstance()) {
            this.broadcasts_ = value;
        } else {
            this.broadcasts_ = (ActivityManagerServiceDumpBroadcastsProto) ((ActivityManagerServiceDumpBroadcastsProto.Builder) ActivityManagerServiceDumpBroadcastsProto.newBuilder(this.broadcasts_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField1_ |= 16;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearBroadcasts() {
        this.broadcasts_ = null;
        this.bitField1_ &= -17;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public boolean hasAmservices() {
        return (this.bitField1_ & 32) == 32;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public ActivityManagerServiceDumpServicesProto getAmservices() {
        ActivityManagerServiceDumpServicesProto activityManagerServiceDumpServicesProto = this.amservices_;
        return activityManagerServiceDumpServicesProto == null ? ActivityManagerServiceDumpServicesProto.getDefaultInstance() : activityManagerServiceDumpServicesProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAmservices(ActivityManagerServiceDumpServicesProto value) {
        if (value != null) {
            this.amservices_ = value;
            this.bitField1_ |= 32;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAmservices(ActivityManagerServiceDumpServicesProto.Builder builderForValue) {
        this.amservices_ = (ActivityManagerServiceDumpServicesProto) builderForValue.build();
        this.bitField1_ |= 32;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeAmservices(ActivityManagerServiceDumpServicesProto value) {
        ActivityManagerServiceDumpServicesProto activityManagerServiceDumpServicesProto = this.amservices_;
        if (activityManagerServiceDumpServicesProto == null || activityManagerServiceDumpServicesProto == ActivityManagerServiceDumpServicesProto.getDefaultInstance()) {
            this.amservices_ = value;
        } else {
            this.amservices_ = (ActivityManagerServiceDumpServicesProto) ((ActivityManagerServiceDumpServicesProto.Builder) ActivityManagerServiceDumpServicesProto.newBuilder(this.amservices_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField1_ |= 32;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAmservices() {
        this.amservices_ = null;
        this.bitField1_ &= -33;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public boolean hasAmprocesses() {
        return (this.bitField1_ & 64) == 64;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public ActivityManagerServiceDumpProcessesProto getAmprocesses() {
        ActivityManagerServiceDumpProcessesProto activityManagerServiceDumpProcessesProto = this.amprocesses_;
        return activityManagerServiceDumpProcessesProto == null ? ActivityManagerServiceDumpProcessesProto.getDefaultInstance() : activityManagerServiceDumpProcessesProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAmprocesses(ActivityManagerServiceDumpProcessesProto value) {
        if (value != null) {
            this.amprocesses_ = value;
            this.bitField1_ |= 64;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAmprocesses(ActivityManagerServiceDumpProcessesProto.Builder builderForValue) {
        this.amprocesses_ = (ActivityManagerServiceDumpProcessesProto) builderForValue.build();
        this.bitField1_ |= 64;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeAmprocesses(ActivityManagerServiceDumpProcessesProto value) {
        ActivityManagerServiceDumpProcessesProto activityManagerServiceDumpProcessesProto = this.amprocesses_;
        if (activityManagerServiceDumpProcessesProto == null || activityManagerServiceDumpProcessesProto == ActivityManagerServiceDumpProcessesProto.getDefaultInstance()) {
            this.amprocesses_ = value;
        } else {
            this.amprocesses_ = (ActivityManagerServiceDumpProcessesProto) ((ActivityManagerServiceDumpProcessesProto.Builder) ActivityManagerServiceDumpProcessesProto.newBuilder(this.amprocesses_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField1_ |= 64;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAmprocesses() {
        this.amprocesses_ = null;
        this.bitField1_ &= -65;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public boolean hasAlarm() {
        return (this.bitField1_ & 128) == 128;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public AlarmManagerServiceDumpProto getAlarm() {
        AlarmManagerServiceDumpProto alarmManagerServiceDumpProto = this.alarm_;
        return alarmManagerServiceDumpProto == null ? AlarmManagerServiceDumpProto.getDefaultInstance() : alarmManagerServiceDumpProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAlarm(AlarmManagerServiceDumpProto value) {
        if (value != null) {
            this.alarm_ = value;
            this.bitField1_ |= 128;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAlarm(AlarmManagerServiceDumpProto.Builder builderForValue) {
        this.alarm_ = (AlarmManagerServiceDumpProto) builderForValue.build();
        this.bitField1_ |= 128;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeAlarm(AlarmManagerServiceDumpProto value) {
        AlarmManagerServiceDumpProto alarmManagerServiceDumpProto = this.alarm_;
        if (alarmManagerServiceDumpProto == null || alarmManagerServiceDumpProto == AlarmManagerServiceDumpProto.getDefaultInstance()) {
            this.alarm_ = value;
        } else {
            this.alarm_ = (AlarmManagerServiceDumpProto) ((AlarmManagerServiceDumpProto.Builder) AlarmManagerServiceDumpProto.newBuilder(this.alarm_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField1_ |= 128;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAlarm() {
        this.alarm_ = null;
        this.bitField1_ &= -129;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public boolean hasWindow() {
        return (this.bitField1_ & 256) == 256;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public WindowManagerServiceDumpProto getWindow() {
        WindowManagerServiceDumpProto windowManagerServiceDumpProto = this.window_;
        return windowManagerServiceDumpProto == null ? WindowManagerServiceDumpProto.getDefaultInstance() : windowManagerServiceDumpProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWindow(WindowManagerServiceDumpProto value) {
        if (value != null) {
            this.window_ = value;
            this.bitField1_ |= 256;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWindow(WindowManagerServiceDumpProto.Builder builderForValue) {
        this.window_ = (WindowManagerServiceDumpProto) builderForValue.build();
        this.bitField1_ |= 256;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeWindow(WindowManagerServiceDumpProto value) {
        WindowManagerServiceDumpProto windowManagerServiceDumpProto = this.window_;
        if (windowManagerServiceDumpProto == null || windowManagerServiceDumpProto == WindowManagerServiceDumpProto.getDefaultInstance()) {
            this.window_ = value;
        } else {
            this.window_ = (WindowManagerServiceDumpProto) ((WindowManagerServiceDumpProto.Builder) WindowManagerServiceDumpProto.newBuilder(this.window_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField1_ |= 256;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearWindow() {
        this.window_ = null;
        this.bitField1_ &= -257;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public boolean hasMeminfo() {
        return (this.bitField1_ & 512) == 512;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public MemInfoDumpProto getMeminfo() {
        MemInfoDumpProto memInfoDumpProto = this.meminfo_;
        return memInfoDumpProto == null ? MemInfoDumpProto.getDefaultInstance() : memInfoDumpProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMeminfo(MemInfoDumpProto value) {
        if (value != null) {
            this.meminfo_ = value;
            this.bitField1_ |= 512;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMeminfo(MemInfoDumpProto.Builder builderForValue) {
        this.meminfo_ = (MemInfoDumpProto) builderForValue.build();
        this.bitField1_ |= 512;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeMeminfo(MemInfoDumpProto value) {
        MemInfoDumpProto memInfoDumpProto = this.meminfo_;
        if (memInfoDumpProto == null || memInfoDumpProto == MemInfoDumpProto.getDefaultInstance()) {
            this.meminfo_ = value;
        } else {
            this.meminfo_ = (MemInfoDumpProto) ((MemInfoDumpProto.Builder) MemInfoDumpProto.newBuilder(this.meminfo_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField1_ |= 512;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMeminfo() {
        this.meminfo_ = null;
        this.bitField1_ &= -513;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public boolean hasGraphicsstats() {
        return (this.bitField1_ & 1024) == 1024;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public GraphicsStatsServiceDumpProto getGraphicsstats() {
        GraphicsStatsServiceDumpProto graphicsStatsServiceDumpProto = this.graphicsstats_;
        return graphicsStatsServiceDumpProto == null ? GraphicsStatsServiceDumpProto.getDefaultInstance() : graphicsStatsServiceDumpProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setGraphicsstats(GraphicsStatsServiceDumpProto value) {
        if (value != null) {
            this.graphicsstats_ = value;
            this.bitField1_ |= 1024;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setGraphicsstats(GraphicsStatsServiceDumpProto.Builder builderForValue) {
        this.graphicsstats_ = (GraphicsStatsServiceDumpProto) builderForValue.build();
        this.bitField1_ |= 1024;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeGraphicsstats(GraphicsStatsServiceDumpProto value) {
        GraphicsStatsServiceDumpProto graphicsStatsServiceDumpProto = this.graphicsstats_;
        if (graphicsStatsServiceDumpProto == null || graphicsStatsServiceDumpProto == GraphicsStatsServiceDumpProto.getDefaultInstance()) {
            this.graphicsstats_ = value;
        } else {
            this.graphicsstats_ = (GraphicsStatsServiceDumpProto) ((GraphicsStatsServiceDumpProto.Builder) GraphicsStatsServiceDumpProto.newBuilder(this.graphicsstats_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField1_ |= 1024;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearGraphicsstats() {
        this.graphicsstats_ = null;
        this.bitField1_ &= -1025;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public boolean hasJobscheduler() {
        return (this.bitField1_ & 2048) == 2048;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public JobSchedulerServiceDumpProto getJobscheduler() {
        JobSchedulerServiceDumpProto jobSchedulerServiceDumpProto = this.jobscheduler_;
        return jobSchedulerServiceDumpProto == null ? JobSchedulerServiceDumpProto.getDefaultInstance() : jobSchedulerServiceDumpProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setJobscheduler(JobSchedulerServiceDumpProto value) {
        if (value != null) {
            this.jobscheduler_ = value;
            this.bitField1_ |= 2048;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setJobscheduler(JobSchedulerServiceDumpProto.Builder builderForValue) {
        this.jobscheduler_ = (JobSchedulerServiceDumpProto) builderForValue.build();
        this.bitField1_ |= 2048;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeJobscheduler(JobSchedulerServiceDumpProto value) {
        JobSchedulerServiceDumpProto jobSchedulerServiceDumpProto = this.jobscheduler_;
        if (jobSchedulerServiceDumpProto == null || jobSchedulerServiceDumpProto == JobSchedulerServiceDumpProto.getDefaultInstance()) {
            this.jobscheduler_ = value;
        } else {
            this.jobscheduler_ = (JobSchedulerServiceDumpProto) ((JobSchedulerServiceDumpProto.Builder) JobSchedulerServiceDumpProto.newBuilder(this.jobscheduler_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField1_ |= 2048;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearJobscheduler() {
        this.jobscheduler_ = null;
        this.bitField1_ &= -2049;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public boolean hasUsb() {
        return (this.bitField1_ & 4096) == 4096;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public UsbServiceDumpProto getUsb() {
        UsbServiceDumpProto usbServiceDumpProto = this.usb_;
        return usbServiceDumpProto == null ? UsbServiceDumpProto.getDefaultInstance() : usbServiceDumpProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUsb(UsbServiceDumpProto value) {
        if (value != null) {
            this.usb_ = value;
            this.bitField1_ |= 4096;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUsb(UsbServiceDumpProto.Builder builderForValue) {
        this.usb_ = (UsbServiceDumpProto) builderForValue.build();
        this.bitField1_ |= 4096;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeUsb(UsbServiceDumpProto value) {
        UsbServiceDumpProto usbServiceDumpProto = this.usb_;
        if (usbServiceDumpProto == null || usbServiceDumpProto == UsbServiceDumpProto.getDefaultInstance()) {
            this.usb_ = value;
        } else {
            this.usb_ = (UsbServiceDumpProto) ((UsbServiceDumpProto.Builder) UsbServiceDumpProto.newBuilder(this.usb_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField1_ |= 4096;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearUsb() {
        this.usb_ = null;
        this.bitField1_ &= -4097;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public boolean hasBatteryHistory() {
        return (this.bitField1_ & 8192) == 8192;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public BatteryStatsServiceDumpHistoryProto getBatteryHistory() {
        BatteryStatsServiceDumpHistoryProto batteryStatsServiceDumpHistoryProto = this.batteryHistory_;
        return batteryStatsServiceDumpHistoryProto == null ? BatteryStatsServiceDumpHistoryProto.getDefaultInstance() : batteryStatsServiceDumpHistoryProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBatteryHistory(BatteryStatsServiceDumpHistoryProto value) {
        if (value != null) {
            this.batteryHistory_ = value;
            this.bitField1_ |= 8192;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBatteryHistory(BatteryStatsServiceDumpHistoryProto.Builder builderForValue) {
        this.batteryHistory_ = (BatteryStatsServiceDumpHistoryProto) builderForValue.build();
        this.bitField1_ |= 8192;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeBatteryHistory(BatteryStatsServiceDumpHistoryProto value) {
        BatteryStatsServiceDumpHistoryProto batteryStatsServiceDumpHistoryProto = this.batteryHistory_;
        if (batteryStatsServiceDumpHistoryProto == null || batteryStatsServiceDumpHistoryProto == BatteryStatsServiceDumpHistoryProto.getDefaultInstance()) {
            this.batteryHistory_ = value;
        } else {
            this.batteryHistory_ = (BatteryStatsServiceDumpHistoryProto) ((BatteryStatsServiceDumpHistoryProto.Builder) BatteryStatsServiceDumpHistoryProto.newBuilder(this.batteryHistory_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField1_ |= 8192;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearBatteryHistory() {
        this.batteryHistory_ = null;
        this.bitField1_ &= -8193;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public boolean hasStatsData() {
        return (this.bitField1_ & 16384) == 16384;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public StatsDataDumpProto getStatsData() {
        StatsDataDumpProto statsDataDumpProto = this.statsData_;
        return statsDataDumpProto == null ? StatsDataDumpProto.getDefaultInstance() : statsDataDumpProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStatsData(StatsDataDumpProto value) {
        if (value != null) {
            this.statsData_ = value;
            this.bitField1_ |= 16384;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStatsData(StatsDataDumpProto.Builder builderForValue) {
        this.statsData_ = (StatsDataDumpProto) builderForValue.build();
        this.bitField1_ |= 16384;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeStatsData(StatsDataDumpProto value) {
        StatsDataDumpProto statsDataDumpProto = this.statsData_;
        if (statsDataDumpProto == null || statsDataDumpProto == StatsDataDumpProto.getDefaultInstance()) {
            this.statsData_ = value;
        } else {
            this.statsData_ = (StatsDataDumpProto) ((StatsDataDumpProto.Builder) StatsDataDumpProto.newBuilder(this.statsData_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField1_ |= 16384;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearStatsData() {
        this.statsData_ = null;
        this.bitField1_ &= -16385;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public boolean hasRole() {
        return (this.bitField1_ & 32768) == 32768;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public RoleManagerServiceDumpProto getRole() {
        RoleManagerServiceDumpProto roleManagerServiceDumpProto = this.role_;
        return roleManagerServiceDumpProto == null ? RoleManagerServiceDumpProto.getDefaultInstance() : roleManagerServiceDumpProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRole(RoleManagerServiceDumpProto value) {
        if (value != null) {
            this.role_ = value;
            this.bitField1_ |= 32768;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRole(RoleManagerServiceDumpProto.Builder builderForValue) {
        this.role_ = (RoleManagerServiceDumpProto) builderForValue.build();
        this.bitField1_ |= 32768;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeRole(RoleManagerServiceDumpProto value) {
        RoleManagerServiceDumpProto roleManagerServiceDumpProto = this.role_;
        if (roleManagerServiceDumpProto == null || roleManagerServiceDumpProto == RoleManagerServiceDumpProto.getDefaultInstance()) {
            this.role_ = value;
        } else {
            this.role_ = (RoleManagerServiceDumpProto) ((RoleManagerServiceDumpProto.Builder) RoleManagerServiceDumpProto.newBuilder(this.role_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField1_ |= 32768;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearRole() {
        this.role_ = null;
        this.bitField1_ &= -32769;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public boolean hasRestrictedImages() {
        return (this.bitField1_ & 65536) == 65536;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public RestrictedImagesDumpProto getRestrictedImages() {
        RestrictedImagesDumpProto restrictedImagesDumpProto = this.restrictedImages_;
        return restrictedImagesDumpProto == null ? RestrictedImagesDumpProto.getDefaultInstance() : restrictedImagesDumpProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRestrictedImages(RestrictedImagesDumpProto value) {
        if (value != null) {
            this.restrictedImages_ = value;
            this.bitField1_ |= 65536;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRestrictedImages(RestrictedImagesDumpProto.Builder builderForValue) {
        this.restrictedImages_ = (RestrictedImagesDumpProto) builderForValue.build();
        this.bitField1_ |= 65536;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeRestrictedImages(RestrictedImagesDumpProto value) {
        RestrictedImagesDumpProto restrictedImagesDumpProto = this.restrictedImages_;
        if (restrictedImagesDumpProto == null || restrictedImagesDumpProto == RestrictedImagesDumpProto.getDefaultInstance()) {
            this.restrictedImages_ = value;
        } else {
            this.restrictedImages_ = (RestrictedImagesDumpProto) ((RestrictedImagesDumpProto.Builder) RestrictedImagesDumpProto.newBuilder(this.restrictedImages_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField1_ |= 65536;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearRestrictedImages() {
        this.restrictedImages_ = null;
        this.bitField1_ &= -65537;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public boolean hasSystemTrace() {
        return (this.bitField1_ & 131072) == 131072;
    }

    @Override // android.os.IncidentProtoOrBuilder
    public ByteString getSystemTrace() {
        return this.systemTrace_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSystemTrace(ByteString value) {
        if (value != null) {
            this.bitField1_ |= 131072;
            this.systemTrace_ = value;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSystemTrace() {
        this.bitField1_ &= -131073;
        this.systemTrace_ = getDefaultInstance().getSystemTrace();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        GeneratedMessageLite.ExtendableMessage<IncidentProto, Builder>.ExtensionWriter extensionWriter = newExtensionWriter();
        for (int i = 0; i < this.header_.size(); i++) {
            output.writeMessage(1, this.header_.get(i));
        }
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(2, getMetadata());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeMessage(1000, getSystemProperties());
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeString(1002, getKernelVersion());
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeMessage(EVENT_LOG_TAG_MAP_FIELD_NUMBER, getEventLogTagMap());
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeMessage(MAIN_LOGS_FIELD_NUMBER, getMainLogs());
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeMessage(RADIO_LOGS_FIELD_NUMBER, getRadioLogs());
        }
        if ((this.bitField0_ & 64) == 64) {
            output.writeMessage(EVENTS_LOGS_FIELD_NUMBER, getEventsLogs());
        }
        if ((this.bitField0_ & 128) == 128) {
            output.writeMessage(SYSTEM_LOGS_FIELD_NUMBER, getSystemLogs());
        }
        if ((this.bitField0_ & 256) == 256) {
            output.writeMessage(CRASH_LOGS_FIELD_NUMBER, getCrashLogs());
        }
        if ((this.bitField0_ & 512) == 512) {
            output.writeMessage(STATS_LOGS_FIELD_NUMBER, getStatsLogs());
        }
        if ((this.bitField0_ & 1024) == 1024) {
            output.writeMessage(SECURITY_LOGS_FIELD_NUMBER, getSecurityLogs());
        }
        if ((this.bitField0_ & 2048) == 2048) {
            output.writeMessage(KERNEL_LOGS_FIELD_NUMBER, getKernelLogs());
        }
        if ((this.bitField0_ & 4096) == 4096) {
            output.writeMessage(NATIVE_TRACES_FIELD_NUMBER, getNativeTraces());
        }
        if ((this.bitField0_ & 8192) == 8192) {
            output.writeMessage(HAL_TRACES_FIELD_NUMBER, getHalTraces());
        }
        if ((this.bitField0_ & 16384) == 16384) {
            output.writeMessage(JAVA_TRACES_FIELD_NUMBER, getJavaTraces());
        }
        if ((this.bitField0_ & 32768) == 32768) {
            output.writeMessage(PROCRANK_FIELD_NUMBER, getProcrank());
        }
        if ((this.bitField0_ & 65536) == 65536) {
            output.writeMessage(PAGE_TYPE_INFO_FIELD_NUMBER, getPageTypeInfo());
        }
        if ((this.bitField0_ & 131072) == 131072) {
            output.writeMessage(KERNEL_WAKE_SOURCES_FIELD_NUMBER, getKernelWakeSources());
        }
        if ((this.bitField0_ & 262144) == 262144) {
            output.writeMessage(CPU_INFO_FIELD_NUMBER, getCpuInfo());
        }
        if ((this.bitField0_ & 524288) == 524288) {
            output.writeMessage(CPU_FREQ_FIELD_NUMBER, getCpuFreq());
        }
        if ((this.bitField0_ & 1048576) == 1048576) {
            output.writeMessage(PROCESSES_AND_THREADS_FIELD_NUMBER, getProcessesAndThreads());
        }
        if ((this.bitField0_ & 2097152) == 2097152) {
            output.writeMessage(BATTERY_TYPE_FIELD_NUMBER, getBatteryType());
        }
        if ((this.bitField0_ & 4194304) == 4194304) {
            output.writeMessage(LAST_KMSG_FIELD_NUMBER, getLastKmsg());
        }
        if ((this.bitField0_ & 8388608) == 8388608) {
            output.writeMessage(FINGERPRINT_FIELD_NUMBER, getFingerprint());
        }
        if ((this.bitField0_ & 16777216) == 16777216) {
            output.writeMessage(NETSTATS_FIELD_NUMBER, getNetstats());
        }
        if ((this.bitField0_ & 33554432) == 33554432) {
            output.writeMessage(SETTINGS_FIELD_NUMBER, getSettings());
        }
        if ((this.bitField0_ & 67108864) == 67108864) {
            output.writeMessage(APPWIDGET_FIELD_NUMBER, getAppwidget());
        }
        if ((this.bitField0_ & 134217728) == 134217728) {
            output.writeMessage(NOTIFICATION_FIELD_NUMBER, getNotification());
        }
        if ((this.bitField0_ & 268435456) == 268435456) {
            output.writeMessage(BATTERYSTATS_FIELD_NUMBER, getBatterystats());
        }
        if ((this.bitField0_ & 536870912) == 536870912) {
            output.writeMessage(BATTERY_FIELD_NUMBER, getBattery());
        }
        if ((this.bitField0_ & 1073741824) == 1073741824) {
            output.writeMessage(DISKSTATS_FIELD_NUMBER, getDiskstats());
        }
        if ((this.bitField0_ & Integer.MIN_VALUE) == Integer.MIN_VALUE) {
            output.writeMessage(PACKAGE_FIELD_NUMBER, getPackage());
        }
        if ((this.bitField1_ & 1) == 1) {
            output.writeMessage(POWER_FIELD_NUMBER, getPower());
        }
        if ((this.bitField1_ & 2) == 2) {
            output.writeMessage(PRINT_FIELD_NUMBER, getPrint());
        }
        if ((this.bitField1_ & 4) == 4) {
            output.writeMessage(PROCSTATS_FIELD_NUMBER, getProcstats());
        }
        if ((this.bitField1_ & 8) == 8) {
            output.writeMessage(ACTIVITIES_FIELD_NUMBER, getActivities());
        }
        if ((this.bitField1_ & 16) == 16) {
            output.writeMessage(BROADCASTS_FIELD_NUMBER, getBroadcasts());
        }
        if ((this.bitField1_ & 32) == 32) {
            output.writeMessage(AMSERVICES_FIELD_NUMBER, getAmservices());
        }
        if ((this.bitField1_ & 64) == 64) {
            output.writeMessage(AMPROCESSES_FIELD_NUMBER, getAmprocesses());
        }
        if ((this.bitField1_ & 128) == 128) {
            output.writeMessage(ALARM_FIELD_NUMBER, getAlarm());
        }
        if ((this.bitField1_ & 256) == 256) {
            output.writeMessage(WINDOW_FIELD_NUMBER, getWindow());
        }
        if ((this.bitField1_ & 512) == 512) {
            output.writeMessage(MEMINFO_FIELD_NUMBER, getMeminfo());
        }
        if ((this.bitField1_ & 1024) == 1024) {
            output.writeMessage(GRAPHICSSTATS_FIELD_NUMBER, getGraphicsstats());
        }
        if ((this.bitField1_ & 2048) == 2048) {
            output.writeMessage(JOBSCHEDULER_FIELD_NUMBER, getJobscheduler());
        }
        if ((this.bitField1_ & 4096) == 4096) {
            output.writeMessage(USB_FIELD_NUMBER, getUsb());
        }
        if ((this.bitField1_ & 8192) == 8192) {
            output.writeMessage(BATTERY_HISTORY_FIELD_NUMBER, getBatteryHistory());
        }
        if ((this.bitField1_ & 16384) == 16384) {
            output.writeMessage(STATS_DATA_FIELD_NUMBER, getStatsData());
        }
        if ((this.bitField1_ & 32768) == 32768) {
            output.writeMessage(ROLE_FIELD_NUMBER, getRole());
        }
        if ((this.bitField1_ & 65536) == 65536) {
            output.writeMessage(RESTRICTED_IMAGES_FIELD_NUMBER, getRestrictedImages());
        }
        if ((this.bitField1_ & 131072) == 131072) {
            output.writeBytes(SYSTEM_TRACE_FIELD_NUMBER, this.systemTrace_);
        }
        extensionWriter.writeUntil(100001, output);
        this.unknownFields.writeTo(output);
    }

    @Override // com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int size = this.memoizedSerializedSize;
        if (size != -1) {
            return size;
        }
        int size2 = 0;
        for (int i = 0; i < this.header_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(1, this.header_.get(i));
        }
        if ((this.bitField0_ & 1) == 1) {
            size2 += CodedOutputStream.computeMessageSize(2, getMetadata());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeMessageSize(1000, getSystemProperties());
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeStringSize(1002, getKernelVersion());
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeMessageSize(EVENT_LOG_TAG_MAP_FIELD_NUMBER, getEventLogTagMap());
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeMessageSize(MAIN_LOGS_FIELD_NUMBER, getMainLogs());
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeMessageSize(RADIO_LOGS_FIELD_NUMBER, getRadioLogs());
        }
        if ((this.bitField0_ & 64) == 64) {
            size2 += CodedOutputStream.computeMessageSize(EVENTS_LOGS_FIELD_NUMBER, getEventsLogs());
        }
        if ((this.bitField0_ & 128) == 128) {
            size2 += CodedOutputStream.computeMessageSize(SYSTEM_LOGS_FIELD_NUMBER, getSystemLogs());
        }
        if ((this.bitField0_ & 256) == 256) {
            size2 += CodedOutputStream.computeMessageSize(CRASH_LOGS_FIELD_NUMBER, getCrashLogs());
        }
        if ((this.bitField0_ & 512) == 512) {
            size2 += CodedOutputStream.computeMessageSize(STATS_LOGS_FIELD_NUMBER, getStatsLogs());
        }
        if ((this.bitField0_ & 1024) == 1024) {
            size2 += CodedOutputStream.computeMessageSize(SECURITY_LOGS_FIELD_NUMBER, getSecurityLogs());
        }
        if ((this.bitField0_ & 2048) == 2048) {
            size2 += CodedOutputStream.computeMessageSize(KERNEL_LOGS_FIELD_NUMBER, getKernelLogs());
        }
        if ((this.bitField0_ & 4096) == 4096) {
            size2 += CodedOutputStream.computeMessageSize(NATIVE_TRACES_FIELD_NUMBER, getNativeTraces());
        }
        if ((this.bitField0_ & 8192) == 8192) {
            size2 += CodedOutputStream.computeMessageSize(HAL_TRACES_FIELD_NUMBER, getHalTraces());
        }
        if ((this.bitField0_ & 16384) == 16384) {
            size2 += CodedOutputStream.computeMessageSize(JAVA_TRACES_FIELD_NUMBER, getJavaTraces());
        }
        if ((this.bitField0_ & 32768) == 32768) {
            size2 += CodedOutputStream.computeMessageSize(PROCRANK_FIELD_NUMBER, getProcrank());
        }
        if ((this.bitField0_ & 65536) == 65536) {
            size2 += CodedOutputStream.computeMessageSize(PAGE_TYPE_INFO_FIELD_NUMBER, getPageTypeInfo());
        }
        if ((this.bitField0_ & 131072) == 131072) {
            size2 += CodedOutputStream.computeMessageSize(KERNEL_WAKE_SOURCES_FIELD_NUMBER, getKernelWakeSources());
        }
        if ((this.bitField0_ & 262144) == 262144) {
            size2 += CodedOutputStream.computeMessageSize(CPU_INFO_FIELD_NUMBER, getCpuInfo());
        }
        if ((this.bitField0_ & 524288) == 524288) {
            size2 += CodedOutputStream.computeMessageSize(CPU_FREQ_FIELD_NUMBER, getCpuFreq());
        }
        if ((this.bitField0_ & 1048576) == 1048576) {
            size2 += CodedOutputStream.computeMessageSize(PROCESSES_AND_THREADS_FIELD_NUMBER, getProcessesAndThreads());
        }
        if ((this.bitField0_ & 2097152) == 2097152) {
            size2 += CodedOutputStream.computeMessageSize(BATTERY_TYPE_FIELD_NUMBER, getBatteryType());
        }
        if ((this.bitField0_ & 4194304) == 4194304) {
            size2 += CodedOutputStream.computeMessageSize(LAST_KMSG_FIELD_NUMBER, getLastKmsg());
        }
        if ((this.bitField0_ & 8388608) == 8388608) {
            size2 += CodedOutputStream.computeMessageSize(FINGERPRINT_FIELD_NUMBER, getFingerprint());
        }
        if ((this.bitField0_ & 16777216) == 16777216) {
            size2 += CodedOutputStream.computeMessageSize(NETSTATS_FIELD_NUMBER, getNetstats());
        }
        if ((this.bitField0_ & 33554432) == 33554432) {
            size2 += CodedOutputStream.computeMessageSize(SETTINGS_FIELD_NUMBER, getSettings());
        }
        if ((this.bitField0_ & 67108864) == 67108864) {
            size2 += CodedOutputStream.computeMessageSize(APPWIDGET_FIELD_NUMBER, getAppwidget());
        }
        if ((this.bitField0_ & 134217728) == 134217728) {
            size2 += CodedOutputStream.computeMessageSize(NOTIFICATION_FIELD_NUMBER, getNotification());
        }
        if ((this.bitField0_ & 268435456) == 268435456) {
            size2 += CodedOutputStream.computeMessageSize(BATTERYSTATS_FIELD_NUMBER, getBatterystats());
        }
        if ((this.bitField0_ & 536870912) == 536870912) {
            size2 += CodedOutputStream.computeMessageSize(BATTERY_FIELD_NUMBER, getBattery());
        }
        if ((this.bitField0_ & 1073741824) == 1073741824) {
            size2 += CodedOutputStream.computeMessageSize(DISKSTATS_FIELD_NUMBER, getDiskstats());
        }
        if ((this.bitField0_ & Integer.MIN_VALUE) == Integer.MIN_VALUE) {
            size2 += CodedOutputStream.computeMessageSize(PACKAGE_FIELD_NUMBER, getPackage());
        }
        if ((this.bitField1_ & 1) == 1) {
            size2 += CodedOutputStream.computeMessageSize(POWER_FIELD_NUMBER, getPower());
        }
        if ((this.bitField1_ & 2) == 2) {
            size2 += CodedOutputStream.computeMessageSize(PRINT_FIELD_NUMBER, getPrint());
        }
        if ((this.bitField1_ & 4) == 4) {
            size2 += CodedOutputStream.computeMessageSize(PROCSTATS_FIELD_NUMBER, getProcstats());
        }
        if ((this.bitField1_ & 8) == 8) {
            size2 += CodedOutputStream.computeMessageSize(ACTIVITIES_FIELD_NUMBER, getActivities());
        }
        if ((this.bitField1_ & 16) == 16) {
            size2 += CodedOutputStream.computeMessageSize(BROADCASTS_FIELD_NUMBER, getBroadcasts());
        }
        if ((this.bitField1_ & 32) == 32) {
            size2 += CodedOutputStream.computeMessageSize(AMSERVICES_FIELD_NUMBER, getAmservices());
        }
        if ((this.bitField1_ & 64) == 64) {
            size2 += CodedOutputStream.computeMessageSize(AMPROCESSES_FIELD_NUMBER, getAmprocesses());
        }
        if ((this.bitField1_ & 128) == 128) {
            size2 += CodedOutputStream.computeMessageSize(ALARM_FIELD_NUMBER, getAlarm());
        }
        if ((this.bitField1_ & 256) == 256) {
            size2 += CodedOutputStream.computeMessageSize(WINDOW_FIELD_NUMBER, getWindow());
        }
        if ((this.bitField1_ & 512) == 512) {
            size2 += CodedOutputStream.computeMessageSize(MEMINFO_FIELD_NUMBER, getMeminfo());
        }
        if ((this.bitField1_ & 1024) == 1024) {
            size2 += CodedOutputStream.computeMessageSize(GRAPHICSSTATS_FIELD_NUMBER, getGraphicsstats());
        }
        if ((this.bitField1_ & 2048) == 2048) {
            size2 += CodedOutputStream.computeMessageSize(JOBSCHEDULER_FIELD_NUMBER, getJobscheduler());
        }
        if ((this.bitField1_ & 4096) == 4096) {
            size2 += CodedOutputStream.computeMessageSize(USB_FIELD_NUMBER, getUsb());
        }
        if ((this.bitField1_ & 8192) == 8192) {
            size2 += CodedOutputStream.computeMessageSize(BATTERY_HISTORY_FIELD_NUMBER, getBatteryHistory());
        }
        if ((this.bitField1_ & 16384) == 16384) {
            size2 += CodedOutputStream.computeMessageSize(STATS_DATA_FIELD_NUMBER, getStatsData());
        }
        if ((this.bitField1_ & 32768) == 32768) {
            size2 += CodedOutputStream.computeMessageSize(ROLE_FIELD_NUMBER, getRole());
        }
        if ((this.bitField1_ & 65536) == 65536) {
            size2 += CodedOutputStream.computeMessageSize(RESTRICTED_IMAGES_FIELD_NUMBER, getRestrictedImages());
        }
        if ((this.bitField1_ & 131072) == 131072) {
            size2 += CodedOutputStream.computeBytesSize(SYSTEM_TRACE_FIELD_NUMBER, this.systemTrace_);
        }
        int size3 = size2 + extensionsSerializedSize() + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static IncidentProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (IncidentProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static IncidentProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (IncidentProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static IncidentProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (IncidentProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static IncidentProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (IncidentProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static IncidentProto parseFrom(InputStream input) throws IOException {
        return (IncidentProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static IncidentProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (IncidentProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static IncidentProto parseDelimitedFrom(InputStream input) throws IOException {
        return (IncidentProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static IncidentProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (IncidentProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static IncidentProto parseFrom(CodedInputStream input) throws IOException {
        return (IncidentProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static IncidentProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (IncidentProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(IncidentProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.ExtendableBuilder<IncidentProto, Builder> implements IncidentProtoOrBuilder {
        private Builder() {
            super(IncidentProto.DEFAULT_INSTANCE);
        }

        @Override // android.os.IncidentProtoOrBuilder
        public List<IncidentHeaderProto> getHeaderList() {
            return Collections.unmodifiableList(((IncidentProto) this.instance).getHeaderList());
        }

        @Override // android.os.IncidentProtoOrBuilder
        public int getHeaderCount() {
            return ((IncidentProto) this.instance).getHeaderCount();
        }

        @Override // android.os.IncidentProtoOrBuilder
        public IncidentHeaderProto getHeader(int index) {
            return ((IncidentProto) this.instance).getHeader(index);
        }

        public Builder setHeader(int index, IncidentHeaderProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).setHeader((IncidentProto) index, (int) value);
            return this;
        }

        public Builder setHeader(int index, IncidentHeaderProto.Builder builderForValue) {
            copyOnWrite();
            ((IncidentProto) this.instance).setHeader((IncidentProto) index, (int) builderForValue);
            return this;
        }

        public Builder addHeader(IncidentHeaderProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).addHeader((IncidentProto) value);
            return this;
        }

        public Builder addHeader(int index, IncidentHeaderProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).addHeader((IncidentProto) index, (int) value);
            return this;
        }

        public Builder addHeader(IncidentHeaderProto.Builder builderForValue) {
            copyOnWrite();
            ((IncidentProto) this.instance).addHeader((IncidentProto) builderForValue);
            return this;
        }

        public Builder addHeader(int index, IncidentHeaderProto.Builder builderForValue) {
            copyOnWrite();
            ((IncidentProto) this.instance).addHeader((IncidentProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllHeader(Iterable<? extends IncidentHeaderProto> values) {
            copyOnWrite();
            ((IncidentProto) this.instance).addAllHeader(values);
            return this;
        }

        public Builder clearHeader() {
            copyOnWrite();
            ((IncidentProto) this.instance).clearHeader();
            return this;
        }

        public Builder removeHeader(int index) {
            copyOnWrite();
            ((IncidentProto) this.instance).removeHeader(index);
            return this;
        }

        @Override // android.os.IncidentProtoOrBuilder
        public boolean hasMetadata() {
            return ((IncidentProto) this.instance).hasMetadata();
        }

        @Override // android.os.IncidentProtoOrBuilder
        public IncidentMetadata getMetadata() {
            return ((IncidentProto) this.instance).getMetadata();
        }

        public Builder setMetadata(IncidentMetadata value) {
            copyOnWrite();
            ((IncidentProto) this.instance).setMetadata((IncidentProto) value);
            return this;
        }

        public Builder setMetadata(IncidentMetadata.Builder builderForValue) {
            copyOnWrite();
            ((IncidentProto) this.instance).setMetadata((IncidentProto) builderForValue);
            return this;
        }

        public Builder mergeMetadata(IncidentMetadata value) {
            copyOnWrite();
            ((IncidentProto) this.instance).mergeMetadata(value);
            return this;
        }

        public Builder clearMetadata() {
            copyOnWrite();
            ((IncidentProto) this.instance).clearMetadata();
            return this;
        }

        @Override // android.os.IncidentProtoOrBuilder
        public boolean hasSystemProperties() {
            return ((IncidentProto) this.instance).hasSystemProperties();
        }

        @Override // android.os.IncidentProtoOrBuilder
        public SystemPropertiesProto getSystemProperties() {
            return ((IncidentProto) this.instance).getSystemProperties();
        }

        public Builder setSystemProperties(SystemPropertiesProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).setSystemProperties((IncidentProto) value);
            return this;
        }

        public Builder setSystemProperties(SystemPropertiesProto.Builder builderForValue) {
            copyOnWrite();
            ((IncidentProto) this.instance).setSystemProperties((IncidentProto) builderForValue);
            return this;
        }

        public Builder mergeSystemProperties(SystemPropertiesProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).mergeSystemProperties(value);
            return this;
        }

        public Builder clearSystemProperties() {
            copyOnWrite();
            ((IncidentProto) this.instance).clearSystemProperties();
            return this;
        }

        @Override // android.os.IncidentProtoOrBuilder
        public boolean hasKernelVersion() {
            return ((IncidentProto) this.instance).hasKernelVersion();
        }

        @Override // android.os.IncidentProtoOrBuilder
        public String getKernelVersion() {
            return ((IncidentProto) this.instance).getKernelVersion();
        }

        @Override // android.os.IncidentProtoOrBuilder
        public ByteString getKernelVersionBytes() {
            return ((IncidentProto) this.instance).getKernelVersionBytes();
        }

        public Builder setKernelVersion(String value) {
            copyOnWrite();
            ((IncidentProto) this.instance).setKernelVersion(value);
            return this;
        }

        public Builder clearKernelVersion() {
            copyOnWrite();
            ((IncidentProto) this.instance).clearKernelVersion();
            return this;
        }

        public Builder setKernelVersionBytes(ByteString value) {
            copyOnWrite();
            ((IncidentProto) this.instance).setKernelVersionBytes(value);
            return this;
        }

        @Override // android.os.IncidentProtoOrBuilder
        public boolean hasEventLogTagMap() {
            return ((IncidentProto) this.instance).hasEventLogTagMap();
        }

        @Override // android.os.IncidentProtoOrBuilder
        public EventLogTagMapProto getEventLogTagMap() {
            return ((IncidentProto) this.instance).getEventLogTagMap();
        }

        public Builder setEventLogTagMap(EventLogTagMapProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).setEventLogTagMap((IncidentProto) value);
            return this;
        }

        public Builder setEventLogTagMap(EventLogTagMapProto.Builder builderForValue) {
            copyOnWrite();
            ((IncidentProto) this.instance).setEventLogTagMap((IncidentProto) builderForValue);
            return this;
        }

        public Builder mergeEventLogTagMap(EventLogTagMapProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).mergeEventLogTagMap(value);
            return this;
        }

        public Builder clearEventLogTagMap() {
            copyOnWrite();
            ((IncidentProto) this.instance).clearEventLogTagMap();
            return this;
        }

        @Override // android.os.IncidentProtoOrBuilder
        public boolean hasMainLogs() {
            return ((IncidentProto) this.instance).hasMainLogs();
        }

        @Override // android.os.IncidentProtoOrBuilder
        public LogProto getMainLogs() {
            return ((IncidentProto) this.instance).getMainLogs();
        }

        public Builder setMainLogs(LogProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).setMainLogs((IncidentProto) value);
            return this;
        }

        public Builder setMainLogs(LogProto.Builder builderForValue) {
            copyOnWrite();
            ((IncidentProto) this.instance).setMainLogs((IncidentProto) builderForValue);
            return this;
        }

        public Builder mergeMainLogs(LogProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).mergeMainLogs(value);
            return this;
        }

        public Builder clearMainLogs() {
            copyOnWrite();
            ((IncidentProto) this.instance).clearMainLogs();
            return this;
        }

        @Override // android.os.IncidentProtoOrBuilder
        public boolean hasRadioLogs() {
            return ((IncidentProto) this.instance).hasRadioLogs();
        }

        @Override // android.os.IncidentProtoOrBuilder
        public LogProto getRadioLogs() {
            return ((IncidentProto) this.instance).getRadioLogs();
        }

        public Builder setRadioLogs(LogProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).setRadioLogs((IncidentProto) value);
            return this;
        }

        public Builder setRadioLogs(LogProto.Builder builderForValue) {
            copyOnWrite();
            ((IncidentProto) this.instance).setRadioLogs((IncidentProto) builderForValue);
            return this;
        }

        public Builder mergeRadioLogs(LogProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).mergeRadioLogs(value);
            return this;
        }

        public Builder clearRadioLogs() {
            copyOnWrite();
            ((IncidentProto) this.instance).clearRadioLogs();
            return this;
        }

        @Override // android.os.IncidentProtoOrBuilder
        public boolean hasEventsLogs() {
            return ((IncidentProto) this.instance).hasEventsLogs();
        }

        @Override // android.os.IncidentProtoOrBuilder
        public LogProto getEventsLogs() {
            return ((IncidentProto) this.instance).getEventsLogs();
        }

        public Builder setEventsLogs(LogProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).setEventsLogs((IncidentProto) value);
            return this;
        }

        public Builder setEventsLogs(LogProto.Builder builderForValue) {
            copyOnWrite();
            ((IncidentProto) this.instance).setEventsLogs((IncidentProto) builderForValue);
            return this;
        }

        public Builder mergeEventsLogs(LogProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).mergeEventsLogs(value);
            return this;
        }

        public Builder clearEventsLogs() {
            copyOnWrite();
            ((IncidentProto) this.instance).clearEventsLogs();
            return this;
        }

        @Override // android.os.IncidentProtoOrBuilder
        public boolean hasSystemLogs() {
            return ((IncidentProto) this.instance).hasSystemLogs();
        }

        @Override // android.os.IncidentProtoOrBuilder
        public LogProto getSystemLogs() {
            return ((IncidentProto) this.instance).getSystemLogs();
        }

        public Builder setSystemLogs(LogProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).setSystemLogs((IncidentProto) value);
            return this;
        }

        public Builder setSystemLogs(LogProto.Builder builderForValue) {
            copyOnWrite();
            ((IncidentProto) this.instance).setSystemLogs((IncidentProto) builderForValue);
            return this;
        }

        public Builder mergeSystemLogs(LogProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).mergeSystemLogs(value);
            return this;
        }

        public Builder clearSystemLogs() {
            copyOnWrite();
            ((IncidentProto) this.instance).clearSystemLogs();
            return this;
        }

        @Override // android.os.IncidentProtoOrBuilder
        public boolean hasCrashLogs() {
            return ((IncidentProto) this.instance).hasCrashLogs();
        }

        @Override // android.os.IncidentProtoOrBuilder
        public LogProto getCrashLogs() {
            return ((IncidentProto) this.instance).getCrashLogs();
        }

        public Builder setCrashLogs(LogProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).setCrashLogs((IncidentProto) value);
            return this;
        }

        public Builder setCrashLogs(LogProto.Builder builderForValue) {
            copyOnWrite();
            ((IncidentProto) this.instance).setCrashLogs((IncidentProto) builderForValue);
            return this;
        }

        public Builder mergeCrashLogs(LogProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).mergeCrashLogs(value);
            return this;
        }

        public Builder clearCrashLogs() {
            copyOnWrite();
            ((IncidentProto) this.instance).clearCrashLogs();
            return this;
        }

        @Override // android.os.IncidentProtoOrBuilder
        public boolean hasStatsLogs() {
            return ((IncidentProto) this.instance).hasStatsLogs();
        }

        @Override // android.os.IncidentProtoOrBuilder
        public LogProto getStatsLogs() {
            return ((IncidentProto) this.instance).getStatsLogs();
        }

        public Builder setStatsLogs(LogProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).setStatsLogs((IncidentProto) value);
            return this;
        }

        public Builder setStatsLogs(LogProto.Builder builderForValue) {
            copyOnWrite();
            ((IncidentProto) this.instance).setStatsLogs((IncidentProto) builderForValue);
            return this;
        }

        public Builder mergeStatsLogs(LogProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).mergeStatsLogs(value);
            return this;
        }

        public Builder clearStatsLogs() {
            copyOnWrite();
            ((IncidentProto) this.instance).clearStatsLogs();
            return this;
        }

        @Override // android.os.IncidentProtoOrBuilder
        public boolean hasSecurityLogs() {
            return ((IncidentProto) this.instance).hasSecurityLogs();
        }

        @Override // android.os.IncidentProtoOrBuilder
        public LogProto getSecurityLogs() {
            return ((IncidentProto) this.instance).getSecurityLogs();
        }

        public Builder setSecurityLogs(LogProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).setSecurityLogs((IncidentProto) value);
            return this;
        }

        public Builder setSecurityLogs(LogProto.Builder builderForValue) {
            copyOnWrite();
            ((IncidentProto) this.instance).setSecurityLogs((IncidentProto) builderForValue);
            return this;
        }

        public Builder mergeSecurityLogs(LogProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).mergeSecurityLogs(value);
            return this;
        }

        public Builder clearSecurityLogs() {
            copyOnWrite();
            ((IncidentProto) this.instance).clearSecurityLogs();
            return this;
        }

        @Override // android.os.IncidentProtoOrBuilder
        public boolean hasKernelLogs() {
            return ((IncidentProto) this.instance).hasKernelLogs();
        }

        @Override // android.os.IncidentProtoOrBuilder
        public LogProto getKernelLogs() {
            return ((IncidentProto) this.instance).getKernelLogs();
        }

        public Builder setKernelLogs(LogProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).setKernelLogs((IncidentProto) value);
            return this;
        }

        public Builder setKernelLogs(LogProto.Builder builderForValue) {
            copyOnWrite();
            ((IncidentProto) this.instance).setKernelLogs((IncidentProto) builderForValue);
            return this;
        }

        public Builder mergeKernelLogs(LogProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).mergeKernelLogs(value);
            return this;
        }

        public Builder clearKernelLogs() {
            copyOnWrite();
            ((IncidentProto) this.instance).clearKernelLogs();
            return this;
        }

        @Override // android.os.IncidentProtoOrBuilder
        public boolean hasNativeTraces() {
            return ((IncidentProto) this.instance).hasNativeTraces();
        }

        @Override // android.os.IncidentProtoOrBuilder
        public BackTraceProto getNativeTraces() {
            return ((IncidentProto) this.instance).getNativeTraces();
        }

        public Builder setNativeTraces(BackTraceProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).setNativeTraces((IncidentProto) value);
            return this;
        }

        public Builder setNativeTraces(BackTraceProto.Builder builderForValue) {
            copyOnWrite();
            ((IncidentProto) this.instance).setNativeTraces((IncidentProto) builderForValue);
            return this;
        }

        public Builder mergeNativeTraces(BackTraceProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).mergeNativeTraces(value);
            return this;
        }

        public Builder clearNativeTraces() {
            copyOnWrite();
            ((IncidentProto) this.instance).clearNativeTraces();
            return this;
        }

        @Override // android.os.IncidentProtoOrBuilder
        public boolean hasHalTraces() {
            return ((IncidentProto) this.instance).hasHalTraces();
        }

        @Override // android.os.IncidentProtoOrBuilder
        public BackTraceProto getHalTraces() {
            return ((IncidentProto) this.instance).getHalTraces();
        }

        public Builder setHalTraces(BackTraceProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).setHalTraces((IncidentProto) value);
            return this;
        }

        public Builder setHalTraces(BackTraceProto.Builder builderForValue) {
            copyOnWrite();
            ((IncidentProto) this.instance).setHalTraces((IncidentProto) builderForValue);
            return this;
        }

        public Builder mergeHalTraces(BackTraceProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).mergeHalTraces(value);
            return this;
        }

        public Builder clearHalTraces() {
            copyOnWrite();
            ((IncidentProto) this.instance).clearHalTraces();
            return this;
        }

        @Override // android.os.IncidentProtoOrBuilder
        public boolean hasJavaTraces() {
            return ((IncidentProto) this.instance).hasJavaTraces();
        }

        @Override // android.os.IncidentProtoOrBuilder
        public BackTraceProto getJavaTraces() {
            return ((IncidentProto) this.instance).getJavaTraces();
        }

        public Builder setJavaTraces(BackTraceProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).setJavaTraces((IncidentProto) value);
            return this;
        }

        public Builder setJavaTraces(BackTraceProto.Builder builderForValue) {
            copyOnWrite();
            ((IncidentProto) this.instance).setJavaTraces((IncidentProto) builderForValue);
            return this;
        }

        public Builder mergeJavaTraces(BackTraceProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).mergeJavaTraces(value);
            return this;
        }

        public Builder clearJavaTraces() {
            copyOnWrite();
            ((IncidentProto) this.instance).clearJavaTraces();
            return this;
        }

        @Override // android.os.IncidentProtoOrBuilder
        public boolean hasProcrank() {
            return ((IncidentProto) this.instance).hasProcrank();
        }

        @Override // android.os.IncidentProtoOrBuilder
        public ProcrankProto getProcrank() {
            return ((IncidentProto) this.instance).getProcrank();
        }

        public Builder setProcrank(ProcrankProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).setProcrank((IncidentProto) value);
            return this;
        }

        public Builder setProcrank(ProcrankProto.Builder builderForValue) {
            copyOnWrite();
            ((IncidentProto) this.instance).setProcrank((IncidentProto) builderForValue);
            return this;
        }

        public Builder mergeProcrank(ProcrankProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).mergeProcrank(value);
            return this;
        }

        public Builder clearProcrank() {
            copyOnWrite();
            ((IncidentProto) this.instance).clearProcrank();
            return this;
        }

        @Override // android.os.IncidentProtoOrBuilder
        public boolean hasPageTypeInfo() {
            return ((IncidentProto) this.instance).hasPageTypeInfo();
        }

        @Override // android.os.IncidentProtoOrBuilder
        public PageTypeInfoProto getPageTypeInfo() {
            return ((IncidentProto) this.instance).getPageTypeInfo();
        }

        public Builder setPageTypeInfo(PageTypeInfoProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).setPageTypeInfo((IncidentProto) value);
            return this;
        }

        public Builder setPageTypeInfo(PageTypeInfoProto.Builder builderForValue) {
            copyOnWrite();
            ((IncidentProto) this.instance).setPageTypeInfo((IncidentProto) builderForValue);
            return this;
        }

        public Builder mergePageTypeInfo(PageTypeInfoProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).mergePageTypeInfo(value);
            return this;
        }

        public Builder clearPageTypeInfo() {
            copyOnWrite();
            ((IncidentProto) this.instance).clearPageTypeInfo();
            return this;
        }

        @Override // android.os.IncidentProtoOrBuilder
        public boolean hasKernelWakeSources() {
            return ((IncidentProto) this.instance).hasKernelWakeSources();
        }

        @Override // android.os.IncidentProtoOrBuilder
        public KernelWakeSourcesProto getKernelWakeSources() {
            return ((IncidentProto) this.instance).getKernelWakeSources();
        }

        public Builder setKernelWakeSources(KernelWakeSourcesProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).setKernelWakeSources((IncidentProto) value);
            return this;
        }

        public Builder setKernelWakeSources(KernelWakeSourcesProto.Builder builderForValue) {
            copyOnWrite();
            ((IncidentProto) this.instance).setKernelWakeSources((IncidentProto) builderForValue);
            return this;
        }

        public Builder mergeKernelWakeSources(KernelWakeSourcesProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).mergeKernelWakeSources(value);
            return this;
        }

        public Builder clearKernelWakeSources() {
            copyOnWrite();
            ((IncidentProto) this.instance).clearKernelWakeSources();
            return this;
        }

        @Override // android.os.IncidentProtoOrBuilder
        public boolean hasCpuInfo() {
            return ((IncidentProto) this.instance).hasCpuInfo();
        }

        @Override // android.os.IncidentProtoOrBuilder
        public CpuInfoProto getCpuInfo() {
            return ((IncidentProto) this.instance).getCpuInfo();
        }

        public Builder setCpuInfo(CpuInfoProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).setCpuInfo((IncidentProto) value);
            return this;
        }

        public Builder setCpuInfo(CpuInfoProto.Builder builderForValue) {
            copyOnWrite();
            ((IncidentProto) this.instance).setCpuInfo((IncidentProto) builderForValue);
            return this;
        }

        public Builder mergeCpuInfo(CpuInfoProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).mergeCpuInfo(value);
            return this;
        }

        public Builder clearCpuInfo() {
            copyOnWrite();
            ((IncidentProto) this.instance).clearCpuInfo();
            return this;
        }

        @Override // android.os.IncidentProtoOrBuilder
        public boolean hasCpuFreq() {
            return ((IncidentProto) this.instance).hasCpuFreq();
        }

        @Override // android.os.IncidentProtoOrBuilder
        public CpuFreqProto getCpuFreq() {
            return ((IncidentProto) this.instance).getCpuFreq();
        }

        public Builder setCpuFreq(CpuFreqProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).setCpuFreq((IncidentProto) value);
            return this;
        }

        public Builder setCpuFreq(CpuFreqProto.Builder builderForValue) {
            copyOnWrite();
            ((IncidentProto) this.instance).setCpuFreq((IncidentProto) builderForValue);
            return this;
        }

        public Builder mergeCpuFreq(CpuFreqProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).mergeCpuFreq(value);
            return this;
        }

        public Builder clearCpuFreq() {
            copyOnWrite();
            ((IncidentProto) this.instance).clearCpuFreq();
            return this;
        }

        @Override // android.os.IncidentProtoOrBuilder
        public boolean hasProcessesAndThreads() {
            return ((IncidentProto) this.instance).hasProcessesAndThreads();
        }

        @Override // android.os.IncidentProtoOrBuilder
        public PsProto getProcessesAndThreads() {
            return ((IncidentProto) this.instance).getProcessesAndThreads();
        }

        public Builder setProcessesAndThreads(PsProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).setProcessesAndThreads((IncidentProto) value);
            return this;
        }

        public Builder setProcessesAndThreads(PsProto.Builder builderForValue) {
            copyOnWrite();
            ((IncidentProto) this.instance).setProcessesAndThreads((IncidentProto) builderForValue);
            return this;
        }

        public Builder mergeProcessesAndThreads(PsProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).mergeProcessesAndThreads(value);
            return this;
        }

        public Builder clearProcessesAndThreads() {
            copyOnWrite();
            ((IncidentProto) this.instance).clearProcessesAndThreads();
            return this;
        }

        @Override // android.os.IncidentProtoOrBuilder
        public boolean hasBatteryType() {
            return ((IncidentProto) this.instance).hasBatteryType();
        }

        @Override // android.os.IncidentProtoOrBuilder
        public BatteryTypeProto getBatteryType() {
            return ((IncidentProto) this.instance).getBatteryType();
        }

        public Builder setBatteryType(BatteryTypeProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).setBatteryType((IncidentProto) value);
            return this;
        }

        public Builder setBatteryType(BatteryTypeProto.Builder builderForValue) {
            copyOnWrite();
            ((IncidentProto) this.instance).setBatteryType((IncidentProto) builderForValue);
            return this;
        }

        public Builder mergeBatteryType(BatteryTypeProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).mergeBatteryType(value);
            return this;
        }

        public Builder clearBatteryType() {
            copyOnWrite();
            ((IncidentProto) this.instance).clearBatteryType();
            return this;
        }

        @Override // android.os.IncidentProtoOrBuilder
        public boolean hasLastKmsg() {
            return ((IncidentProto) this.instance).hasLastKmsg();
        }

        @Override // android.os.IncidentProtoOrBuilder
        public GZippedFileProto getLastKmsg() {
            return ((IncidentProto) this.instance).getLastKmsg();
        }

        public Builder setLastKmsg(GZippedFileProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).setLastKmsg((IncidentProto) value);
            return this;
        }

        public Builder setLastKmsg(GZippedFileProto.Builder builderForValue) {
            copyOnWrite();
            ((IncidentProto) this.instance).setLastKmsg((IncidentProto) builderForValue);
            return this;
        }

        public Builder mergeLastKmsg(GZippedFileProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).mergeLastKmsg(value);
            return this;
        }

        public Builder clearLastKmsg() {
            copyOnWrite();
            ((IncidentProto) this.instance).clearLastKmsg();
            return this;
        }

        @Override // android.os.IncidentProtoOrBuilder
        public boolean hasFingerprint() {
            return ((IncidentProto) this.instance).hasFingerprint();
        }

        @Override // android.os.IncidentProtoOrBuilder
        public FingerprintServiceDumpProto getFingerprint() {
            return ((IncidentProto) this.instance).getFingerprint();
        }

        public Builder setFingerprint(FingerprintServiceDumpProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).setFingerprint((IncidentProto) value);
            return this;
        }

        public Builder setFingerprint(FingerprintServiceDumpProto.Builder builderForValue) {
            copyOnWrite();
            ((IncidentProto) this.instance).setFingerprint((IncidentProto) builderForValue);
            return this;
        }

        public Builder mergeFingerprint(FingerprintServiceDumpProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).mergeFingerprint(value);
            return this;
        }

        public Builder clearFingerprint() {
            copyOnWrite();
            ((IncidentProto) this.instance).clearFingerprint();
            return this;
        }

        @Override // android.os.IncidentProtoOrBuilder
        public boolean hasNetstats() {
            return ((IncidentProto) this.instance).hasNetstats();
        }

        @Override // android.os.IncidentProtoOrBuilder
        public NetworkStatsServiceDumpProto getNetstats() {
            return ((IncidentProto) this.instance).getNetstats();
        }

        public Builder setNetstats(NetworkStatsServiceDumpProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).setNetstats((IncidentProto) value);
            return this;
        }

        public Builder setNetstats(NetworkStatsServiceDumpProto.Builder builderForValue) {
            copyOnWrite();
            ((IncidentProto) this.instance).setNetstats((IncidentProto) builderForValue);
            return this;
        }

        public Builder mergeNetstats(NetworkStatsServiceDumpProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).mergeNetstats(value);
            return this;
        }

        public Builder clearNetstats() {
            copyOnWrite();
            ((IncidentProto) this.instance).clearNetstats();
            return this;
        }

        @Override // android.os.IncidentProtoOrBuilder
        public boolean hasSettings() {
            return ((IncidentProto) this.instance).hasSettings();
        }

        @Override // android.os.IncidentProtoOrBuilder
        public SettingsServiceDumpProto getSettings() {
            return ((IncidentProto) this.instance).getSettings();
        }

        public Builder setSettings(SettingsServiceDumpProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).setSettings((IncidentProto) value);
            return this;
        }

        public Builder setSettings(SettingsServiceDumpProto.Builder builderForValue) {
            copyOnWrite();
            ((IncidentProto) this.instance).setSettings((IncidentProto) builderForValue);
            return this;
        }

        public Builder mergeSettings(SettingsServiceDumpProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).mergeSettings(value);
            return this;
        }

        public Builder clearSettings() {
            copyOnWrite();
            ((IncidentProto) this.instance).clearSettings();
            return this;
        }

        @Override // android.os.IncidentProtoOrBuilder
        public boolean hasAppwidget() {
            return ((IncidentProto) this.instance).hasAppwidget();
        }

        @Override // android.os.IncidentProtoOrBuilder
        public AppWidgetServiceDumpProto getAppwidget() {
            return ((IncidentProto) this.instance).getAppwidget();
        }

        public Builder setAppwidget(AppWidgetServiceDumpProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).setAppwidget((IncidentProto) value);
            return this;
        }

        public Builder setAppwidget(AppWidgetServiceDumpProto.Builder builderForValue) {
            copyOnWrite();
            ((IncidentProto) this.instance).setAppwidget((IncidentProto) builderForValue);
            return this;
        }

        public Builder mergeAppwidget(AppWidgetServiceDumpProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).mergeAppwidget(value);
            return this;
        }

        public Builder clearAppwidget() {
            copyOnWrite();
            ((IncidentProto) this.instance).clearAppwidget();
            return this;
        }

        @Override // android.os.IncidentProtoOrBuilder
        public boolean hasNotification() {
            return ((IncidentProto) this.instance).hasNotification();
        }

        @Override // android.os.IncidentProtoOrBuilder
        public NotificationServiceDumpProto getNotification() {
            return ((IncidentProto) this.instance).getNotification();
        }

        public Builder setNotification(NotificationServiceDumpProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).setNotification((IncidentProto) value);
            return this;
        }

        public Builder setNotification(NotificationServiceDumpProto.Builder builderForValue) {
            copyOnWrite();
            ((IncidentProto) this.instance).setNotification((IncidentProto) builderForValue);
            return this;
        }

        public Builder mergeNotification(NotificationServiceDumpProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).mergeNotification(value);
            return this;
        }

        public Builder clearNotification() {
            copyOnWrite();
            ((IncidentProto) this.instance).clearNotification();
            return this;
        }

        @Override // android.os.IncidentProtoOrBuilder
        public boolean hasBatterystats() {
            return ((IncidentProto) this.instance).hasBatterystats();
        }

        @Override // android.os.IncidentProtoOrBuilder
        public BatteryStatsServiceDumpProto getBatterystats() {
            return ((IncidentProto) this.instance).getBatterystats();
        }

        public Builder setBatterystats(BatteryStatsServiceDumpProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).setBatterystats((IncidentProto) value);
            return this;
        }

        public Builder setBatterystats(BatteryStatsServiceDumpProto.Builder builderForValue) {
            copyOnWrite();
            ((IncidentProto) this.instance).setBatterystats((IncidentProto) builderForValue);
            return this;
        }

        public Builder mergeBatterystats(BatteryStatsServiceDumpProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).mergeBatterystats(value);
            return this;
        }

        public Builder clearBatterystats() {
            copyOnWrite();
            ((IncidentProto) this.instance).clearBatterystats();
            return this;
        }

        @Override // android.os.IncidentProtoOrBuilder
        public boolean hasBattery() {
            return ((IncidentProto) this.instance).hasBattery();
        }

        @Override // android.os.IncidentProtoOrBuilder
        public BatteryServiceDumpProto getBattery() {
            return ((IncidentProto) this.instance).getBattery();
        }

        public Builder setBattery(BatteryServiceDumpProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).setBattery((IncidentProto) value);
            return this;
        }

        public Builder setBattery(BatteryServiceDumpProto.Builder builderForValue) {
            copyOnWrite();
            ((IncidentProto) this.instance).setBattery((IncidentProto) builderForValue);
            return this;
        }

        public Builder mergeBattery(BatteryServiceDumpProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).mergeBattery(value);
            return this;
        }

        public Builder clearBattery() {
            copyOnWrite();
            ((IncidentProto) this.instance).clearBattery();
            return this;
        }

        @Override // android.os.IncidentProtoOrBuilder
        public boolean hasDiskstats() {
            return ((IncidentProto) this.instance).hasDiskstats();
        }

        @Override // android.os.IncidentProtoOrBuilder
        public DiskStatsServiceDumpProto getDiskstats() {
            return ((IncidentProto) this.instance).getDiskstats();
        }

        public Builder setDiskstats(DiskStatsServiceDumpProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).setDiskstats((IncidentProto) value);
            return this;
        }

        public Builder setDiskstats(DiskStatsServiceDumpProto.Builder builderForValue) {
            copyOnWrite();
            ((IncidentProto) this.instance).setDiskstats((IncidentProto) builderForValue);
            return this;
        }

        public Builder mergeDiskstats(DiskStatsServiceDumpProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).mergeDiskstats(value);
            return this;
        }

        public Builder clearDiskstats() {
            copyOnWrite();
            ((IncidentProto) this.instance).clearDiskstats();
            return this;
        }

        @Override // android.os.IncidentProtoOrBuilder
        public boolean hasPackage() {
            return ((IncidentProto) this.instance).hasPackage();
        }

        @Override // android.os.IncidentProtoOrBuilder
        public PackageServiceDumpProto getPackage() {
            return ((IncidentProto) this.instance).getPackage();
        }

        public Builder setPackage(PackageServiceDumpProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).setPackage((IncidentProto) value);
            return this;
        }

        public Builder setPackage(PackageServiceDumpProto.Builder builderForValue) {
            copyOnWrite();
            ((IncidentProto) this.instance).setPackage((IncidentProto) builderForValue);
            return this;
        }

        public Builder mergePackage(PackageServiceDumpProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).mergePackage(value);
            return this;
        }

        public Builder clearPackage() {
            copyOnWrite();
            ((IncidentProto) this.instance).clearPackage();
            return this;
        }

        @Override // android.os.IncidentProtoOrBuilder
        public boolean hasPower() {
            return ((IncidentProto) this.instance).hasPower();
        }

        @Override // android.os.IncidentProtoOrBuilder
        public PowerManagerServiceDumpProto getPower() {
            return ((IncidentProto) this.instance).getPower();
        }

        public Builder setPower(PowerManagerServiceDumpProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).setPower((IncidentProto) value);
            return this;
        }

        public Builder setPower(PowerManagerServiceDumpProto.Builder builderForValue) {
            copyOnWrite();
            ((IncidentProto) this.instance).setPower((IncidentProto) builderForValue);
            return this;
        }

        public Builder mergePower(PowerManagerServiceDumpProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).mergePower(value);
            return this;
        }

        public Builder clearPower() {
            copyOnWrite();
            ((IncidentProto) this.instance).clearPower();
            return this;
        }

        @Override // android.os.IncidentProtoOrBuilder
        public boolean hasPrint() {
            return ((IncidentProto) this.instance).hasPrint();
        }

        @Override // android.os.IncidentProtoOrBuilder
        public PrintServiceDumpProto getPrint() {
            return ((IncidentProto) this.instance).getPrint();
        }

        public Builder setPrint(PrintServiceDumpProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).setPrint((IncidentProto) value);
            return this;
        }

        public Builder setPrint(PrintServiceDumpProto.Builder builderForValue) {
            copyOnWrite();
            ((IncidentProto) this.instance).setPrint((IncidentProto) builderForValue);
            return this;
        }

        public Builder mergePrint(PrintServiceDumpProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).mergePrint(value);
            return this;
        }

        public Builder clearPrint() {
            copyOnWrite();
            ((IncidentProto) this.instance).clearPrint();
            return this;
        }

        @Override // android.os.IncidentProtoOrBuilder
        public boolean hasProcstats() {
            return ((IncidentProto) this.instance).hasProcstats();
        }

        @Override // android.os.IncidentProtoOrBuilder
        public ProcessStatsServiceDumpProto getProcstats() {
            return ((IncidentProto) this.instance).getProcstats();
        }

        public Builder setProcstats(ProcessStatsServiceDumpProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).setProcstats((IncidentProto) value);
            return this;
        }

        public Builder setProcstats(ProcessStatsServiceDumpProto.Builder builderForValue) {
            copyOnWrite();
            ((IncidentProto) this.instance).setProcstats((IncidentProto) builderForValue);
            return this;
        }

        public Builder mergeProcstats(ProcessStatsServiceDumpProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).mergeProcstats(value);
            return this;
        }

        public Builder clearProcstats() {
            copyOnWrite();
            ((IncidentProto) this.instance).clearProcstats();
            return this;
        }

        @Override // android.os.IncidentProtoOrBuilder
        public boolean hasActivities() {
            return ((IncidentProto) this.instance).hasActivities();
        }

        @Override // android.os.IncidentProtoOrBuilder
        public ActivityManagerServiceDumpActivitiesProto getActivities() {
            return ((IncidentProto) this.instance).getActivities();
        }

        public Builder setActivities(ActivityManagerServiceDumpActivitiesProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).setActivities((IncidentProto) value);
            return this;
        }

        public Builder setActivities(ActivityManagerServiceDumpActivitiesProto.Builder builderForValue) {
            copyOnWrite();
            ((IncidentProto) this.instance).setActivities((IncidentProto) builderForValue);
            return this;
        }

        public Builder mergeActivities(ActivityManagerServiceDumpActivitiesProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).mergeActivities(value);
            return this;
        }

        public Builder clearActivities() {
            copyOnWrite();
            ((IncidentProto) this.instance).clearActivities();
            return this;
        }

        @Override // android.os.IncidentProtoOrBuilder
        public boolean hasBroadcasts() {
            return ((IncidentProto) this.instance).hasBroadcasts();
        }

        @Override // android.os.IncidentProtoOrBuilder
        public ActivityManagerServiceDumpBroadcastsProto getBroadcasts() {
            return ((IncidentProto) this.instance).getBroadcasts();
        }

        public Builder setBroadcasts(ActivityManagerServiceDumpBroadcastsProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).setBroadcasts((IncidentProto) value);
            return this;
        }

        public Builder setBroadcasts(ActivityManagerServiceDumpBroadcastsProto.Builder builderForValue) {
            copyOnWrite();
            ((IncidentProto) this.instance).setBroadcasts((IncidentProto) builderForValue);
            return this;
        }

        public Builder mergeBroadcasts(ActivityManagerServiceDumpBroadcastsProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).mergeBroadcasts(value);
            return this;
        }

        public Builder clearBroadcasts() {
            copyOnWrite();
            ((IncidentProto) this.instance).clearBroadcasts();
            return this;
        }

        @Override // android.os.IncidentProtoOrBuilder
        public boolean hasAmservices() {
            return ((IncidentProto) this.instance).hasAmservices();
        }

        @Override // android.os.IncidentProtoOrBuilder
        public ActivityManagerServiceDumpServicesProto getAmservices() {
            return ((IncidentProto) this.instance).getAmservices();
        }

        public Builder setAmservices(ActivityManagerServiceDumpServicesProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).setAmservices((IncidentProto) value);
            return this;
        }

        public Builder setAmservices(ActivityManagerServiceDumpServicesProto.Builder builderForValue) {
            copyOnWrite();
            ((IncidentProto) this.instance).setAmservices((IncidentProto) builderForValue);
            return this;
        }

        public Builder mergeAmservices(ActivityManagerServiceDumpServicesProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).mergeAmservices(value);
            return this;
        }

        public Builder clearAmservices() {
            copyOnWrite();
            ((IncidentProto) this.instance).clearAmservices();
            return this;
        }

        @Override // android.os.IncidentProtoOrBuilder
        public boolean hasAmprocesses() {
            return ((IncidentProto) this.instance).hasAmprocesses();
        }

        @Override // android.os.IncidentProtoOrBuilder
        public ActivityManagerServiceDumpProcessesProto getAmprocesses() {
            return ((IncidentProto) this.instance).getAmprocesses();
        }

        public Builder setAmprocesses(ActivityManagerServiceDumpProcessesProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).setAmprocesses((IncidentProto) value);
            return this;
        }

        public Builder setAmprocesses(ActivityManagerServiceDumpProcessesProto.Builder builderForValue) {
            copyOnWrite();
            ((IncidentProto) this.instance).setAmprocesses((IncidentProto) builderForValue);
            return this;
        }

        public Builder mergeAmprocesses(ActivityManagerServiceDumpProcessesProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).mergeAmprocesses(value);
            return this;
        }

        public Builder clearAmprocesses() {
            copyOnWrite();
            ((IncidentProto) this.instance).clearAmprocesses();
            return this;
        }

        @Override // android.os.IncidentProtoOrBuilder
        public boolean hasAlarm() {
            return ((IncidentProto) this.instance).hasAlarm();
        }

        @Override // android.os.IncidentProtoOrBuilder
        public AlarmManagerServiceDumpProto getAlarm() {
            return ((IncidentProto) this.instance).getAlarm();
        }

        public Builder setAlarm(AlarmManagerServiceDumpProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).setAlarm((IncidentProto) value);
            return this;
        }

        public Builder setAlarm(AlarmManagerServiceDumpProto.Builder builderForValue) {
            copyOnWrite();
            ((IncidentProto) this.instance).setAlarm((IncidentProto) builderForValue);
            return this;
        }

        public Builder mergeAlarm(AlarmManagerServiceDumpProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).mergeAlarm(value);
            return this;
        }

        public Builder clearAlarm() {
            copyOnWrite();
            ((IncidentProto) this.instance).clearAlarm();
            return this;
        }

        @Override // android.os.IncidentProtoOrBuilder
        public boolean hasWindow() {
            return ((IncidentProto) this.instance).hasWindow();
        }

        @Override // android.os.IncidentProtoOrBuilder
        public WindowManagerServiceDumpProto getWindow() {
            return ((IncidentProto) this.instance).getWindow();
        }

        public Builder setWindow(WindowManagerServiceDumpProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).setWindow((IncidentProto) value);
            return this;
        }

        public Builder setWindow(WindowManagerServiceDumpProto.Builder builderForValue) {
            copyOnWrite();
            ((IncidentProto) this.instance).setWindow((IncidentProto) builderForValue);
            return this;
        }

        public Builder mergeWindow(WindowManagerServiceDumpProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).mergeWindow(value);
            return this;
        }

        public Builder clearWindow() {
            copyOnWrite();
            ((IncidentProto) this.instance).clearWindow();
            return this;
        }

        @Override // android.os.IncidentProtoOrBuilder
        public boolean hasMeminfo() {
            return ((IncidentProto) this.instance).hasMeminfo();
        }

        @Override // android.os.IncidentProtoOrBuilder
        public MemInfoDumpProto getMeminfo() {
            return ((IncidentProto) this.instance).getMeminfo();
        }

        public Builder setMeminfo(MemInfoDumpProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).setMeminfo((IncidentProto) value);
            return this;
        }

        public Builder setMeminfo(MemInfoDumpProto.Builder builderForValue) {
            copyOnWrite();
            ((IncidentProto) this.instance).setMeminfo((IncidentProto) builderForValue);
            return this;
        }

        public Builder mergeMeminfo(MemInfoDumpProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).mergeMeminfo(value);
            return this;
        }

        public Builder clearMeminfo() {
            copyOnWrite();
            ((IncidentProto) this.instance).clearMeminfo();
            return this;
        }

        @Override // android.os.IncidentProtoOrBuilder
        public boolean hasGraphicsstats() {
            return ((IncidentProto) this.instance).hasGraphicsstats();
        }

        @Override // android.os.IncidentProtoOrBuilder
        public GraphicsStatsServiceDumpProto getGraphicsstats() {
            return ((IncidentProto) this.instance).getGraphicsstats();
        }

        public Builder setGraphicsstats(GraphicsStatsServiceDumpProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).setGraphicsstats((IncidentProto) value);
            return this;
        }

        public Builder setGraphicsstats(GraphicsStatsServiceDumpProto.Builder builderForValue) {
            copyOnWrite();
            ((IncidentProto) this.instance).setGraphicsstats((IncidentProto) builderForValue);
            return this;
        }

        public Builder mergeGraphicsstats(GraphicsStatsServiceDumpProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).mergeGraphicsstats(value);
            return this;
        }

        public Builder clearGraphicsstats() {
            copyOnWrite();
            ((IncidentProto) this.instance).clearGraphicsstats();
            return this;
        }

        @Override // android.os.IncidentProtoOrBuilder
        public boolean hasJobscheduler() {
            return ((IncidentProto) this.instance).hasJobscheduler();
        }

        @Override // android.os.IncidentProtoOrBuilder
        public JobSchedulerServiceDumpProto getJobscheduler() {
            return ((IncidentProto) this.instance).getJobscheduler();
        }

        public Builder setJobscheduler(JobSchedulerServiceDumpProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).setJobscheduler((IncidentProto) value);
            return this;
        }

        public Builder setJobscheduler(JobSchedulerServiceDumpProto.Builder builderForValue) {
            copyOnWrite();
            ((IncidentProto) this.instance).setJobscheduler((IncidentProto) builderForValue);
            return this;
        }

        public Builder mergeJobscheduler(JobSchedulerServiceDumpProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).mergeJobscheduler(value);
            return this;
        }

        public Builder clearJobscheduler() {
            copyOnWrite();
            ((IncidentProto) this.instance).clearJobscheduler();
            return this;
        }

        @Override // android.os.IncidentProtoOrBuilder
        public boolean hasUsb() {
            return ((IncidentProto) this.instance).hasUsb();
        }

        @Override // android.os.IncidentProtoOrBuilder
        public UsbServiceDumpProto getUsb() {
            return ((IncidentProto) this.instance).getUsb();
        }

        public Builder setUsb(UsbServiceDumpProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).setUsb((IncidentProto) value);
            return this;
        }

        public Builder setUsb(UsbServiceDumpProto.Builder builderForValue) {
            copyOnWrite();
            ((IncidentProto) this.instance).setUsb((IncidentProto) builderForValue);
            return this;
        }

        public Builder mergeUsb(UsbServiceDumpProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).mergeUsb(value);
            return this;
        }

        public Builder clearUsb() {
            copyOnWrite();
            ((IncidentProto) this.instance).clearUsb();
            return this;
        }

        @Override // android.os.IncidentProtoOrBuilder
        public boolean hasBatteryHistory() {
            return ((IncidentProto) this.instance).hasBatteryHistory();
        }

        @Override // android.os.IncidentProtoOrBuilder
        public BatteryStatsServiceDumpHistoryProto getBatteryHistory() {
            return ((IncidentProto) this.instance).getBatteryHistory();
        }

        public Builder setBatteryHistory(BatteryStatsServiceDumpHistoryProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).setBatteryHistory((IncidentProto) value);
            return this;
        }

        public Builder setBatteryHistory(BatteryStatsServiceDumpHistoryProto.Builder builderForValue) {
            copyOnWrite();
            ((IncidentProto) this.instance).setBatteryHistory((IncidentProto) builderForValue);
            return this;
        }

        public Builder mergeBatteryHistory(BatteryStatsServiceDumpHistoryProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).mergeBatteryHistory(value);
            return this;
        }

        public Builder clearBatteryHistory() {
            copyOnWrite();
            ((IncidentProto) this.instance).clearBatteryHistory();
            return this;
        }

        @Override // android.os.IncidentProtoOrBuilder
        public boolean hasStatsData() {
            return ((IncidentProto) this.instance).hasStatsData();
        }

        @Override // android.os.IncidentProtoOrBuilder
        public StatsDataDumpProto getStatsData() {
            return ((IncidentProto) this.instance).getStatsData();
        }

        public Builder setStatsData(StatsDataDumpProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).setStatsData((IncidentProto) value);
            return this;
        }

        public Builder setStatsData(StatsDataDumpProto.Builder builderForValue) {
            copyOnWrite();
            ((IncidentProto) this.instance).setStatsData((IncidentProto) builderForValue);
            return this;
        }

        public Builder mergeStatsData(StatsDataDumpProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).mergeStatsData(value);
            return this;
        }

        public Builder clearStatsData() {
            copyOnWrite();
            ((IncidentProto) this.instance).clearStatsData();
            return this;
        }

        @Override // android.os.IncidentProtoOrBuilder
        public boolean hasRole() {
            return ((IncidentProto) this.instance).hasRole();
        }

        @Override // android.os.IncidentProtoOrBuilder
        public RoleManagerServiceDumpProto getRole() {
            return ((IncidentProto) this.instance).getRole();
        }

        public Builder setRole(RoleManagerServiceDumpProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).setRole((IncidentProto) value);
            return this;
        }

        public Builder setRole(RoleManagerServiceDumpProto.Builder builderForValue) {
            copyOnWrite();
            ((IncidentProto) this.instance).setRole((IncidentProto) builderForValue);
            return this;
        }

        public Builder mergeRole(RoleManagerServiceDumpProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).mergeRole(value);
            return this;
        }

        public Builder clearRole() {
            copyOnWrite();
            ((IncidentProto) this.instance).clearRole();
            return this;
        }

        @Override // android.os.IncidentProtoOrBuilder
        public boolean hasRestrictedImages() {
            return ((IncidentProto) this.instance).hasRestrictedImages();
        }

        @Override // android.os.IncidentProtoOrBuilder
        public RestrictedImagesDumpProto getRestrictedImages() {
            return ((IncidentProto) this.instance).getRestrictedImages();
        }

        public Builder setRestrictedImages(RestrictedImagesDumpProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).setRestrictedImages((IncidentProto) value);
            return this;
        }

        public Builder setRestrictedImages(RestrictedImagesDumpProto.Builder builderForValue) {
            copyOnWrite();
            ((IncidentProto) this.instance).setRestrictedImages((IncidentProto) builderForValue);
            return this;
        }

        public Builder mergeRestrictedImages(RestrictedImagesDumpProto value) {
            copyOnWrite();
            ((IncidentProto) this.instance).mergeRestrictedImages(value);
            return this;
        }

        public Builder clearRestrictedImages() {
            copyOnWrite();
            ((IncidentProto) this.instance).clearRestrictedImages();
            return this;
        }

        @Override // android.os.IncidentProtoOrBuilder
        public boolean hasSystemTrace() {
            return ((IncidentProto) this.instance).hasSystemTrace();
        }

        @Override // android.os.IncidentProtoOrBuilder
        public ByteString getSystemTrace() {
            return ((IncidentProto) this.instance).getSystemTrace();
        }

        public Builder setSystemTrace(ByteString value) {
            copyOnWrite();
            ((IncidentProto) this.instance).setSystemTrace(value);
            return this;
        }

        public Builder clearSystemTrace() {
            copyOnWrite();
            ((IncidentProto) this.instance).clearSystemTrace();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new IncidentProto();
            case IS_INITIALIZED:
                byte isInitialized = this.memoizedIsInitialized;
                if (isInitialized == 1) {
                    return DEFAULT_INSTANCE;
                }
                if (isInitialized == 0) {
                    return null;
                }
                boolean shouldMemoize = ((Boolean) arg0).booleanValue();
                if (extensionsAreInitialized()) {
                    if (shouldMemoize) {
                        this.memoizedIsInitialized = 1;
                    }
                    return DEFAULT_INSTANCE;
                } else if (!shouldMemoize) {
                    return null;
                } else {
                    this.memoizedIsInitialized = 0;
                    return null;
                }
            case MAKE_IMMUTABLE:
                this.header_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                IncidentProto other = (IncidentProto) arg1;
                this.header_ = visitor.visitList(this.header_, other.header_);
                this.metadata_ = (IncidentMetadata) visitor.visitMessage(this.metadata_, other.metadata_);
                this.systemProperties_ = (SystemPropertiesProto) visitor.visitMessage(this.systemProperties_, other.systemProperties_);
                this.kernelVersion_ = visitor.visitString(hasKernelVersion(), this.kernelVersion_, other.hasKernelVersion(), other.kernelVersion_);
                this.eventLogTagMap_ = (EventLogTagMapProto) visitor.visitMessage(this.eventLogTagMap_, other.eventLogTagMap_);
                this.mainLogs_ = (LogProto) visitor.visitMessage(this.mainLogs_, other.mainLogs_);
                this.radioLogs_ = (LogProto) visitor.visitMessage(this.radioLogs_, other.radioLogs_);
                this.eventsLogs_ = (LogProto) visitor.visitMessage(this.eventsLogs_, other.eventsLogs_);
                this.systemLogs_ = (LogProto) visitor.visitMessage(this.systemLogs_, other.systemLogs_);
                this.crashLogs_ = (LogProto) visitor.visitMessage(this.crashLogs_, other.crashLogs_);
                this.statsLogs_ = (LogProto) visitor.visitMessage(this.statsLogs_, other.statsLogs_);
                this.securityLogs_ = (LogProto) visitor.visitMessage(this.securityLogs_, other.securityLogs_);
                this.kernelLogs_ = (LogProto) visitor.visitMessage(this.kernelLogs_, other.kernelLogs_);
                this.nativeTraces_ = (BackTraceProto) visitor.visitMessage(this.nativeTraces_, other.nativeTraces_);
                this.halTraces_ = (BackTraceProto) visitor.visitMessage(this.halTraces_, other.halTraces_);
                this.javaTraces_ = (BackTraceProto) visitor.visitMessage(this.javaTraces_, other.javaTraces_);
                this.procrank_ = (ProcrankProto) visitor.visitMessage(this.procrank_, other.procrank_);
                this.pageTypeInfo_ = (PageTypeInfoProto) visitor.visitMessage(this.pageTypeInfo_, other.pageTypeInfo_);
                this.kernelWakeSources_ = (KernelWakeSourcesProto) visitor.visitMessage(this.kernelWakeSources_, other.kernelWakeSources_);
                this.cpuInfo_ = (CpuInfoProto) visitor.visitMessage(this.cpuInfo_, other.cpuInfo_);
                this.cpuFreq_ = (CpuFreqProto) visitor.visitMessage(this.cpuFreq_, other.cpuFreq_);
                this.processesAndThreads_ = (PsProto) visitor.visitMessage(this.processesAndThreads_, other.processesAndThreads_);
                this.batteryType_ = (BatteryTypeProto) visitor.visitMessage(this.batteryType_, other.batteryType_);
                this.lastKmsg_ = (GZippedFileProto) visitor.visitMessage(this.lastKmsg_, other.lastKmsg_);
                this.fingerprint_ = (FingerprintServiceDumpProto) visitor.visitMessage(this.fingerprint_, other.fingerprint_);
                this.netstats_ = (NetworkStatsServiceDumpProto) visitor.visitMessage(this.netstats_, other.netstats_);
                this.settings_ = (SettingsServiceDumpProto) visitor.visitMessage(this.settings_, other.settings_);
                this.appwidget_ = (AppWidgetServiceDumpProto) visitor.visitMessage(this.appwidget_, other.appwidget_);
                this.notification_ = (NotificationServiceDumpProto) visitor.visitMessage(this.notification_, other.notification_);
                this.batterystats_ = (BatteryStatsServiceDumpProto) visitor.visitMessage(this.batterystats_, other.batterystats_);
                this.battery_ = (BatteryServiceDumpProto) visitor.visitMessage(this.battery_, other.battery_);
                this.diskstats_ = (DiskStatsServiceDumpProto) visitor.visitMessage(this.diskstats_, other.diskstats_);
                this.package_ = (PackageServiceDumpProto) visitor.visitMessage(this.package_, other.package_);
                this.power_ = (PowerManagerServiceDumpProto) visitor.visitMessage(this.power_, other.power_);
                this.print_ = (PrintServiceDumpProto) visitor.visitMessage(this.print_, other.print_);
                this.procstats_ = (ProcessStatsServiceDumpProto) visitor.visitMessage(this.procstats_, other.procstats_);
                this.activities_ = (ActivityManagerServiceDumpActivitiesProto) visitor.visitMessage(this.activities_, other.activities_);
                this.broadcasts_ = (ActivityManagerServiceDumpBroadcastsProto) visitor.visitMessage(this.broadcasts_, other.broadcasts_);
                this.amservices_ = (ActivityManagerServiceDumpServicesProto) visitor.visitMessage(this.amservices_, other.amservices_);
                this.amprocesses_ = (ActivityManagerServiceDumpProcessesProto) visitor.visitMessage(this.amprocesses_, other.amprocesses_);
                this.alarm_ = (AlarmManagerServiceDumpProto) visitor.visitMessage(this.alarm_, other.alarm_);
                this.window_ = (WindowManagerServiceDumpProto) visitor.visitMessage(this.window_, other.window_);
                this.meminfo_ = (MemInfoDumpProto) visitor.visitMessage(this.meminfo_, other.meminfo_);
                this.graphicsstats_ = (GraphicsStatsServiceDumpProto) visitor.visitMessage(this.graphicsstats_, other.graphicsstats_);
                this.jobscheduler_ = (JobSchedulerServiceDumpProto) visitor.visitMessage(this.jobscheduler_, other.jobscheduler_);
                this.usb_ = (UsbServiceDumpProto) visitor.visitMessage(this.usb_, other.usb_);
                this.batteryHistory_ = (BatteryStatsServiceDumpHistoryProto) visitor.visitMessage(this.batteryHistory_, other.batteryHistory_);
                this.statsData_ = (StatsDataDumpProto) visitor.visitMessage(this.statsData_, other.statsData_);
                this.role_ = (RoleManagerServiceDumpProto) visitor.visitMessage(this.role_, other.role_);
                this.restrictedImages_ = (RestrictedImagesDumpProto) visitor.visitMessage(this.restrictedImages_, other.restrictedImages_);
                this.systemTrace_ = visitor.visitByteString(hasSystemTrace(), this.systemTrace_, other.hasSystemTrace(), other.systemTrace_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= other.bitField0_;
                    this.bitField1_ |= other.bitField1_;
                }
                return this;
            case MERGE_FROM_STREAM:
                CodedInputStream input = (CodedInputStream) arg0;
                ExtensionRegistryLite extensionRegistry = (ExtensionRegistryLite) arg1;
                boolean done = false;
                while (!done) {
                    try {
                        int tag = input.readTag();
                        switch (tag) {
                            case 0:
                                done = true;
                                break;
                            case 10:
                                if (!this.header_.isModifiable()) {
                                    this.header_ = GeneratedMessageLite.mutableCopy(this.header_);
                                }
                                this.header_.add((IncidentHeaderProto) input.readMessage(IncidentHeaderProto.parser(), extensionRegistry));
                                break;
                            case 18:
                                IncidentMetadata.Builder subBuilder = null;
                                if ((this.bitField0_ & 1) == 1) {
                                    subBuilder = (IncidentMetadata.Builder) this.metadata_.toBuilder();
                                }
                                this.metadata_ = (IncidentMetadata) input.readMessage(IncidentMetadata.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.metadata_);
                                    this.metadata_ = (IncidentMetadata) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 1;
                                break;
                            case 8002:
                                SystemPropertiesProto.Builder subBuilder2 = null;
                                if ((this.bitField0_ & 2) == 2) {
                                    subBuilder2 = (SystemPropertiesProto.Builder) this.systemProperties_.toBuilder();
                                }
                                this.systemProperties_ = (SystemPropertiesProto) input.readMessage(SystemPropertiesProto.parser(), extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom((GeneratedMessageLite) this.systemProperties_);
                                    this.systemProperties_ = (SystemPropertiesProto) subBuilder2.buildPartial();
                                }
                                this.bitField0_ = 2 | this.bitField0_;
                                break;
                            case 8018:
                                String s = input.readString();
                                this.bitField0_ |= 4;
                                this.kernelVersion_ = s;
                                break;
                            case 8802:
                                EventLogTagMapProto.Builder subBuilder3 = null;
                                if ((this.bitField0_ & 8) == 8) {
                                    subBuilder3 = (EventLogTagMapProto.Builder) this.eventLogTagMap_.toBuilder();
                                }
                                this.eventLogTagMap_ = (EventLogTagMapProto) input.readMessage(EventLogTagMapProto.parser(), extensionRegistry);
                                if (subBuilder3 != null) {
                                    subBuilder3.mergeFrom((GeneratedMessageLite) this.eventLogTagMap_);
                                    this.eventLogTagMap_ = (EventLogTagMapProto) subBuilder3.buildPartial();
                                }
                                this.bitField0_ |= 8;
                                break;
                            case 8810:
                                LogProto.Builder subBuilder4 = null;
                                if ((this.bitField0_ & 16) == 16) {
                                    subBuilder4 = (LogProto.Builder) this.mainLogs_.toBuilder();
                                }
                                this.mainLogs_ = (LogProto) input.readMessage(LogProto.parser(), extensionRegistry);
                                if (subBuilder4 != null) {
                                    subBuilder4.mergeFrom((GeneratedMessageLite) this.mainLogs_);
                                    this.mainLogs_ = (LogProto) subBuilder4.buildPartial();
                                }
                                this.bitField0_ |= 16;
                                break;
                            case 8818:
                                LogProto.Builder subBuilder5 = null;
                                if ((this.bitField0_ & 32) == 32) {
                                    subBuilder5 = (LogProto.Builder) this.radioLogs_.toBuilder();
                                }
                                this.radioLogs_ = (LogProto) input.readMessage(LogProto.parser(), extensionRegistry);
                                if (subBuilder5 != null) {
                                    subBuilder5.mergeFrom((GeneratedMessageLite) this.radioLogs_);
                                    this.radioLogs_ = (LogProto) subBuilder5.buildPartial();
                                }
                                this.bitField0_ |= 32;
                                break;
                            case 8826:
                                LogProto.Builder subBuilder6 = null;
                                if ((this.bitField0_ & 64) == 64) {
                                    subBuilder6 = (LogProto.Builder) this.eventsLogs_.toBuilder();
                                }
                                this.eventsLogs_ = (LogProto) input.readMessage(LogProto.parser(), extensionRegistry);
                                if (subBuilder6 != null) {
                                    subBuilder6.mergeFrom((GeneratedMessageLite) this.eventsLogs_);
                                    this.eventsLogs_ = (LogProto) subBuilder6.buildPartial();
                                }
                                this.bitField0_ |= 64;
                                break;
                            case 8834:
                                LogProto.Builder subBuilder7 = null;
                                if ((this.bitField0_ & 128) == 128) {
                                    subBuilder7 = (LogProto.Builder) this.systemLogs_.toBuilder();
                                }
                                this.systemLogs_ = (LogProto) input.readMessage(LogProto.parser(), extensionRegistry);
                                if (subBuilder7 != null) {
                                    subBuilder7.mergeFrom((GeneratedMessageLite) this.systemLogs_);
                                    this.systemLogs_ = (LogProto) subBuilder7.buildPartial();
                                }
                                this.bitField0_ |= 128;
                                break;
                            case 8842:
                                LogProto.Builder subBuilder8 = null;
                                if ((this.bitField0_ & 256) == 256) {
                                    subBuilder8 = (LogProto.Builder) this.crashLogs_.toBuilder();
                                }
                                this.crashLogs_ = (LogProto) input.readMessage(LogProto.parser(), extensionRegistry);
                                if (subBuilder8 != null) {
                                    subBuilder8.mergeFrom((GeneratedMessageLite) this.crashLogs_);
                                    this.crashLogs_ = (LogProto) subBuilder8.buildPartial();
                                }
                                this.bitField0_ |= 256;
                                break;
                            case 8850:
                                LogProto.Builder subBuilder9 = null;
                                if ((this.bitField0_ & 512) == 512) {
                                    subBuilder9 = (LogProto.Builder) this.statsLogs_.toBuilder();
                                }
                                this.statsLogs_ = (LogProto) input.readMessage(LogProto.parser(), extensionRegistry);
                                if (subBuilder9 != null) {
                                    subBuilder9.mergeFrom((GeneratedMessageLite) this.statsLogs_);
                                    this.statsLogs_ = (LogProto) subBuilder9.buildPartial();
                                }
                                this.bitField0_ |= 512;
                                break;
                            case 8858:
                                LogProto.Builder subBuilder10 = null;
                                if ((this.bitField0_ & 1024) == 1024) {
                                    subBuilder10 = (LogProto.Builder) this.securityLogs_.toBuilder();
                                }
                                this.securityLogs_ = (LogProto) input.readMessage(LogProto.parser(), extensionRegistry);
                                if (subBuilder10 != null) {
                                    subBuilder10.mergeFrom((GeneratedMessageLite) this.securityLogs_);
                                    this.securityLogs_ = (LogProto) subBuilder10.buildPartial();
                                }
                                this.bitField0_ |= 1024;
                                break;
                            case 8866:
                                LogProto.Builder subBuilder11 = null;
                                if ((this.bitField0_ & 2048) == 2048) {
                                    subBuilder11 = (LogProto.Builder) this.kernelLogs_.toBuilder();
                                }
                                this.kernelLogs_ = (LogProto) input.readMessage(LogProto.parser(), extensionRegistry);
                                if (subBuilder11 != null) {
                                    subBuilder11.mergeFrom((GeneratedMessageLite) this.kernelLogs_);
                                    this.kernelLogs_ = (LogProto) subBuilder11.buildPartial();
                                }
                                this.bitField0_ |= 2048;
                                break;
                            case 9602:
                                BackTraceProto.Builder subBuilder12 = null;
                                if ((this.bitField0_ & 4096) == 4096) {
                                    subBuilder12 = (BackTraceProto.Builder) this.nativeTraces_.toBuilder();
                                }
                                this.nativeTraces_ = (BackTraceProto) input.readMessage(BackTraceProto.parser(), extensionRegistry);
                                if (subBuilder12 != null) {
                                    subBuilder12.mergeFrom((GeneratedMessageLite) this.nativeTraces_);
                                    this.nativeTraces_ = (BackTraceProto) subBuilder12.buildPartial();
                                }
                                this.bitField0_ |= 4096;
                                break;
                            case 9610:
                                BackTraceProto.Builder subBuilder13 = null;
                                if ((this.bitField0_ & 8192) == 8192) {
                                    subBuilder13 = (BackTraceProto.Builder) this.halTraces_.toBuilder();
                                }
                                this.halTraces_ = (BackTraceProto) input.readMessage(BackTraceProto.parser(), extensionRegistry);
                                if (subBuilder13 != null) {
                                    subBuilder13.mergeFrom((GeneratedMessageLite) this.halTraces_);
                                    this.halTraces_ = (BackTraceProto) subBuilder13.buildPartial();
                                }
                                this.bitField0_ |= 8192;
                                break;
                            case 9618:
                                BackTraceProto.Builder subBuilder14 = null;
                                if ((this.bitField0_ & 16384) == 16384) {
                                    subBuilder14 = (BackTraceProto.Builder) this.javaTraces_.toBuilder();
                                }
                                this.javaTraces_ = (BackTraceProto) input.readMessage(BackTraceProto.parser(), extensionRegistry);
                                if (subBuilder14 != null) {
                                    subBuilder14.mergeFrom((GeneratedMessageLite) this.javaTraces_);
                                    this.javaTraces_ = (BackTraceProto) subBuilder14.buildPartial();
                                }
                                this.bitField0_ |= 16384;
                                break;
                            case 16002:
                                ProcrankProto.Builder subBuilder15 = null;
                                if ((this.bitField0_ & 32768) == 32768) {
                                    subBuilder15 = (ProcrankProto.Builder) this.procrank_.toBuilder();
                                }
                                this.procrank_ = (ProcrankProto) input.readMessage(ProcrankProto.parser(), extensionRegistry);
                                if (subBuilder15 != null) {
                                    subBuilder15.mergeFrom((GeneratedMessageLite) this.procrank_);
                                    this.procrank_ = (ProcrankProto) subBuilder15.buildPartial();
                                }
                                this.bitField0_ = 32768 | this.bitField0_;
                                break;
                            case 16010:
                                PageTypeInfoProto.Builder subBuilder16 = null;
                                if ((this.bitField0_ & 65536) == 65536) {
                                    subBuilder16 = (PageTypeInfoProto.Builder) this.pageTypeInfo_.toBuilder();
                                }
                                this.pageTypeInfo_ = (PageTypeInfoProto) input.readMessage(PageTypeInfoProto.parser(), extensionRegistry);
                                if (subBuilder16 != null) {
                                    subBuilder16.mergeFrom((GeneratedMessageLite) this.pageTypeInfo_);
                                    this.pageTypeInfo_ = (PageTypeInfoProto) subBuilder16.buildPartial();
                                }
                                this.bitField0_ |= 65536;
                                break;
                            case 16018:
                                KernelWakeSourcesProto.Builder subBuilder17 = null;
                                if ((this.bitField0_ & 131072) == 131072) {
                                    subBuilder17 = (KernelWakeSourcesProto.Builder) this.kernelWakeSources_.toBuilder();
                                }
                                this.kernelWakeSources_ = (KernelWakeSourcesProto) input.readMessage(KernelWakeSourcesProto.parser(), extensionRegistry);
                                if (subBuilder17 != null) {
                                    subBuilder17.mergeFrom((GeneratedMessageLite) this.kernelWakeSources_);
                                    this.kernelWakeSources_ = (KernelWakeSourcesProto) subBuilder17.buildPartial();
                                }
                                this.bitField0_ |= 131072;
                                break;
                            case 16026:
                                CpuInfoProto.Builder subBuilder18 = null;
                                if ((this.bitField0_ & 262144) == 262144) {
                                    subBuilder18 = (CpuInfoProto.Builder) this.cpuInfo_.toBuilder();
                                }
                                this.cpuInfo_ = (CpuInfoProto) input.readMessage(CpuInfoProto.parser(), extensionRegistry);
                                if (subBuilder18 != null) {
                                    subBuilder18.mergeFrom((GeneratedMessageLite) this.cpuInfo_);
                                    this.cpuInfo_ = (CpuInfoProto) subBuilder18.buildPartial();
                                }
                                this.bitField0_ |= 262144;
                                break;
                            case 16034:
                                CpuFreqProto.Builder subBuilder19 = null;
                                if ((this.bitField0_ & 524288) == 524288) {
                                    subBuilder19 = (CpuFreqProto.Builder) this.cpuFreq_.toBuilder();
                                }
                                this.cpuFreq_ = (CpuFreqProto) input.readMessage(CpuFreqProto.parser(), extensionRegistry);
                                if (subBuilder19 != null) {
                                    subBuilder19.mergeFrom((GeneratedMessageLite) this.cpuFreq_);
                                    this.cpuFreq_ = (CpuFreqProto) subBuilder19.buildPartial();
                                }
                                this.bitField0_ |= 524288;
                                break;
                            case 16042:
                                PsProto.Builder subBuilder20 = null;
                                if ((this.bitField0_ & 1048576) == 1048576) {
                                    subBuilder20 = (PsProto.Builder) this.processesAndThreads_.toBuilder();
                                }
                                this.processesAndThreads_ = (PsProto) input.readMessage(PsProto.parser(), extensionRegistry);
                                if (subBuilder20 != null) {
                                    subBuilder20.mergeFrom((GeneratedMessageLite) this.processesAndThreads_);
                                    this.processesAndThreads_ = (PsProto) subBuilder20.buildPartial();
                                }
                                this.bitField0_ |= 1048576;
                                break;
                            case 16050:
                                BatteryTypeProto.Builder subBuilder21 = null;
                                if ((this.bitField0_ & 2097152) == 2097152) {
                                    subBuilder21 = (BatteryTypeProto.Builder) this.batteryType_.toBuilder();
                                }
                                this.batteryType_ = (BatteryTypeProto) input.readMessage(BatteryTypeProto.parser(), extensionRegistry);
                                if (subBuilder21 != null) {
                                    subBuilder21.mergeFrom((GeneratedMessageLite) this.batteryType_);
                                    this.batteryType_ = (BatteryTypeProto) subBuilder21.buildPartial();
                                }
                                this.bitField0_ |= 2097152;
                                break;
                            case 16058:
                                GZippedFileProto.Builder subBuilder22 = null;
                                if ((this.bitField0_ & 4194304) == 4194304) {
                                    subBuilder22 = (GZippedFileProto.Builder) this.lastKmsg_.toBuilder();
                                }
                                this.lastKmsg_ = (GZippedFileProto) input.readMessage(GZippedFileProto.parser(), extensionRegistry);
                                if (subBuilder22 != null) {
                                    subBuilder22.mergeFrom((GeneratedMessageLite) this.lastKmsg_);
                                    this.lastKmsg_ = (GZippedFileProto) subBuilder22.buildPartial();
                                }
                                this.bitField0_ |= 4194304;
                                break;
                            case 24002:
                                FingerprintServiceDumpProto.Builder subBuilder23 = null;
                                if ((this.bitField0_ & 8388608) == 8388608) {
                                    subBuilder23 = (FingerprintServiceDumpProto.Builder) this.fingerprint_.toBuilder();
                                }
                                this.fingerprint_ = (FingerprintServiceDumpProto) input.readMessage(FingerprintServiceDumpProto.parser(), extensionRegistry);
                                if (subBuilder23 != null) {
                                    subBuilder23.mergeFrom((GeneratedMessageLite) this.fingerprint_);
                                    this.fingerprint_ = (FingerprintServiceDumpProto) subBuilder23.buildPartial();
                                }
                                this.bitField0_ |= 8388608;
                                break;
                            case 24010:
                                NetworkStatsServiceDumpProto.Builder subBuilder24 = null;
                                if ((this.bitField0_ & 16777216) == 16777216) {
                                    subBuilder24 = (NetworkStatsServiceDumpProto.Builder) this.netstats_.toBuilder();
                                }
                                this.netstats_ = (NetworkStatsServiceDumpProto) input.readMessage(NetworkStatsServiceDumpProto.parser(), extensionRegistry);
                                if (subBuilder24 != null) {
                                    subBuilder24.mergeFrom((GeneratedMessageLite) this.netstats_);
                                    this.netstats_ = (NetworkStatsServiceDumpProto) subBuilder24.buildPartial();
                                }
                                this.bitField0_ |= 16777216;
                                break;
                            case 24018:
                                SettingsServiceDumpProto.Builder subBuilder25 = null;
                                if ((this.bitField0_ & 33554432) == 33554432) {
                                    subBuilder25 = (SettingsServiceDumpProto.Builder) this.settings_.toBuilder();
                                }
                                this.settings_ = (SettingsServiceDumpProto) input.readMessage(SettingsServiceDumpProto.parser(), extensionRegistry);
                                if (subBuilder25 != null) {
                                    subBuilder25.mergeFrom((GeneratedMessageLite) this.settings_);
                                    this.settings_ = (SettingsServiceDumpProto) subBuilder25.buildPartial();
                                }
                                this.bitField0_ |= 33554432;
                                break;
                            case 24026:
                                AppWidgetServiceDumpProto.Builder subBuilder26 = null;
                                if ((this.bitField0_ & 67108864) == 67108864) {
                                    subBuilder26 = (AppWidgetServiceDumpProto.Builder) this.appwidget_.toBuilder();
                                }
                                this.appwidget_ = (AppWidgetServiceDumpProto) input.readMessage(AppWidgetServiceDumpProto.parser(), extensionRegistry);
                                if (subBuilder26 != null) {
                                    subBuilder26.mergeFrom((GeneratedMessageLite) this.appwidget_);
                                    this.appwidget_ = (AppWidgetServiceDumpProto) subBuilder26.buildPartial();
                                }
                                this.bitField0_ |= 67108864;
                                break;
                            case 24034:
                                NotificationServiceDumpProto.Builder subBuilder27 = null;
                                if ((this.bitField0_ & 134217728) == 134217728) {
                                    subBuilder27 = (NotificationServiceDumpProto.Builder) this.notification_.toBuilder();
                                }
                                this.notification_ = (NotificationServiceDumpProto) input.readMessage(NotificationServiceDumpProto.parser(), extensionRegistry);
                                if (subBuilder27 != null) {
                                    subBuilder27.mergeFrom((GeneratedMessageLite) this.notification_);
                                    this.notification_ = (NotificationServiceDumpProto) subBuilder27.buildPartial();
                                }
                                this.bitField0_ |= 134217728;
                                break;
                            case 24042:
                                BatteryStatsServiceDumpProto.Builder subBuilder28 = null;
                                if ((this.bitField0_ & 268435456) == 268435456) {
                                    subBuilder28 = (BatteryStatsServiceDumpProto.Builder) this.batterystats_.toBuilder();
                                }
                                this.batterystats_ = (BatteryStatsServiceDumpProto) input.readMessage(BatteryStatsServiceDumpProto.parser(), extensionRegistry);
                                if (subBuilder28 != null) {
                                    subBuilder28.mergeFrom((GeneratedMessageLite) this.batterystats_);
                                    this.batterystats_ = (BatteryStatsServiceDumpProto) subBuilder28.buildPartial();
                                }
                                this.bitField0_ |= 268435456;
                                break;
                            case 24050:
                                BatteryServiceDumpProto.Builder subBuilder29 = null;
                                if ((this.bitField0_ & 536870912) == 536870912) {
                                    subBuilder29 = (BatteryServiceDumpProto.Builder) this.battery_.toBuilder();
                                }
                                this.battery_ = (BatteryServiceDumpProto) input.readMessage(BatteryServiceDumpProto.parser(), extensionRegistry);
                                if (subBuilder29 != null) {
                                    subBuilder29.mergeFrom((GeneratedMessageLite) this.battery_);
                                    this.battery_ = (BatteryServiceDumpProto) subBuilder29.buildPartial();
                                }
                                this.bitField0_ |= 536870912;
                                break;
                            case 24058:
                                DiskStatsServiceDumpProto.Builder subBuilder30 = null;
                                if ((this.bitField0_ & 1073741824) == 1073741824) {
                                    subBuilder30 = (DiskStatsServiceDumpProto.Builder) this.diskstats_.toBuilder();
                                }
                                this.diskstats_ = (DiskStatsServiceDumpProto) input.readMessage(DiskStatsServiceDumpProto.parser(), extensionRegistry);
                                if (subBuilder30 != null) {
                                    subBuilder30.mergeFrom((GeneratedMessageLite) this.diskstats_);
                                    this.diskstats_ = (DiskStatsServiceDumpProto) subBuilder30.buildPartial();
                                }
                                this.bitField0_ |= 1073741824;
                                break;
                            case 24066:
                                PackageServiceDumpProto.Builder subBuilder31 = null;
                                if ((this.bitField0_ & Integer.MIN_VALUE) == Integer.MIN_VALUE) {
                                    subBuilder31 = (PackageServiceDumpProto.Builder) this.package_.toBuilder();
                                }
                                this.package_ = (PackageServiceDumpProto) input.readMessage(PackageServiceDumpProto.parser(), extensionRegistry);
                                if (subBuilder31 != null) {
                                    subBuilder31.mergeFrom((GeneratedMessageLite) this.package_);
                                    this.package_ = (PackageServiceDumpProto) subBuilder31.buildPartial();
                                }
                                this.bitField0_ |= Integer.MIN_VALUE;
                                break;
                            case 24074:
                                PowerManagerServiceDumpProto.Builder subBuilder32 = null;
                                if ((this.bitField1_ & 1) == 1) {
                                    subBuilder32 = (PowerManagerServiceDumpProto.Builder) this.power_.toBuilder();
                                }
                                this.power_ = (PowerManagerServiceDumpProto) input.readMessage(PowerManagerServiceDumpProto.parser(), extensionRegistry);
                                if (subBuilder32 != null) {
                                    subBuilder32.mergeFrom((GeneratedMessageLite) this.power_);
                                    this.power_ = (PowerManagerServiceDumpProto) subBuilder32.buildPartial();
                                }
                                this.bitField1_ |= 1;
                                break;
                            case 24082:
                                PrintServiceDumpProto.Builder subBuilder33 = null;
                                if ((this.bitField1_ & 2) == 2) {
                                    subBuilder33 = (PrintServiceDumpProto.Builder) this.print_.toBuilder();
                                }
                                this.print_ = (PrintServiceDumpProto) input.readMessage(PrintServiceDumpProto.parser(), extensionRegistry);
                                if (subBuilder33 != null) {
                                    subBuilder33.mergeFrom((GeneratedMessageLite) this.print_);
                                    this.print_ = (PrintServiceDumpProto) subBuilder33.buildPartial();
                                }
                                this.bitField1_ = 2 | this.bitField1_;
                                break;
                            case 24090:
                                ProcessStatsServiceDumpProto.Builder subBuilder34 = null;
                                if ((this.bitField1_ & 4) == 4) {
                                    subBuilder34 = (ProcessStatsServiceDumpProto.Builder) this.procstats_.toBuilder();
                                }
                                this.procstats_ = (ProcessStatsServiceDumpProto) input.readMessage(ProcessStatsServiceDumpProto.parser(), extensionRegistry);
                                if (subBuilder34 != null) {
                                    subBuilder34.mergeFrom((GeneratedMessageLite) this.procstats_);
                                    this.procstats_ = (ProcessStatsServiceDumpProto) subBuilder34.buildPartial();
                                }
                                this.bitField1_ |= 4;
                                break;
                            case 24098:
                                ActivityManagerServiceDumpActivitiesProto.Builder subBuilder35 = null;
                                if ((this.bitField1_ & 8) == 8) {
                                    subBuilder35 = (ActivityManagerServiceDumpActivitiesProto.Builder) this.activities_.toBuilder();
                                }
                                this.activities_ = (ActivityManagerServiceDumpActivitiesProto) input.readMessage(ActivityManagerServiceDumpActivitiesProto.parser(), extensionRegistry);
                                if (subBuilder35 != null) {
                                    subBuilder35.mergeFrom((GeneratedMessageLite) this.activities_);
                                    this.activities_ = (ActivityManagerServiceDumpActivitiesProto) subBuilder35.buildPartial();
                                }
                                this.bitField1_ |= 8;
                                break;
                            case 24106:
                                ActivityManagerServiceDumpBroadcastsProto.Builder subBuilder36 = null;
                                if ((this.bitField1_ & 16) == 16) {
                                    subBuilder36 = (ActivityManagerServiceDumpBroadcastsProto.Builder) this.broadcasts_.toBuilder();
                                }
                                this.broadcasts_ = (ActivityManagerServiceDumpBroadcastsProto) input.readMessage(ActivityManagerServiceDumpBroadcastsProto.parser(), extensionRegistry);
                                if (subBuilder36 != null) {
                                    subBuilder36.mergeFrom((GeneratedMessageLite) this.broadcasts_);
                                    this.broadcasts_ = (ActivityManagerServiceDumpBroadcastsProto) subBuilder36.buildPartial();
                                }
                                this.bitField1_ |= 16;
                                break;
                            case 24114:
                                ActivityManagerServiceDumpServicesProto.Builder subBuilder37 = null;
                                if ((this.bitField1_ & 32) == 32) {
                                    subBuilder37 = (ActivityManagerServiceDumpServicesProto.Builder) this.amservices_.toBuilder();
                                }
                                this.amservices_ = (ActivityManagerServiceDumpServicesProto) input.readMessage(ActivityManagerServiceDumpServicesProto.parser(), extensionRegistry);
                                if (subBuilder37 != null) {
                                    subBuilder37.mergeFrom((GeneratedMessageLite) this.amservices_);
                                    this.amservices_ = (ActivityManagerServiceDumpServicesProto) subBuilder37.buildPartial();
                                }
                                this.bitField1_ |= 32;
                                break;
                            case 24122:
                                ActivityManagerServiceDumpProcessesProto.Builder subBuilder38 = null;
                                if ((this.bitField1_ & 64) == 64) {
                                    subBuilder38 = (ActivityManagerServiceDumpProcessesProto.Builder) this.amprocesses_.toBuilder();
                                }
                                this.amprocesses_ = (ActivityManagerServiceDumpProcessesProto) input.readMessage(ActivityManagerServiceDumpProcessesProto.parser(), extensionRegistry);
                                if (subBuilder38 != null) {
                                    subBuilder38.mergeFrom((GeneratedMessageLite) this.amprocesses_);
                                    this.amprocesses_ = (ActivityManagerServiceDumpProcessesProto) subBuilder38.buildPartial();
                                }
                                this.bitField1_ |= 64;
                                break;
                            case 24130:
                                AlarmManagerServiceDumpProto.Builder subBuilder39 = null;
                                if ((this.bitField1_ & 128) == 128) {
                                    subBuilder39 = (AlarmManagerServiceDumpProto.Builder) this.alarm_.toBuilder();
                                }
                                this.alarm_ = (AlarmManagerServiceDumpProto) input.readMessage(AlarmManagerServiceDumpProto.parser(), extensionRegistry);
                                if (subBuilder39 != null) {
                                    subBuilder39.mergeFrom((GeneratedMessageLite) this.alarm_);
                                    this.alarm_ = (AlarmManagerServiceDumpProto) subBuilder39.buildPartial();
                                }
                                this.bitField1_ |= 128;
                                break;
                            case 24138:
                                WindowManagerServiceDumpProto.Builder subBuilder40 = null;
                                if ((this.bitField1_ & 256) == 256) {
                                    subBuilder40 = (WindowManagerServiceDumpProto.Builder) this.window_.toBuilder();
                                }
                                this.window_ = (WindowManagerServiceDumpProto) input.readMessage(WindowManagerServiceDumpProto.parser(), extensionRegistry);
                                if (subBuilder40 != null) {
                                    subBuilder40.mergeFrom((GeneratedMessageLite) this.window_);
                                    this.window_ = (WindowManagerServiceDumpProto) subBuilder40.buildPartial();
                                }
                                this.bitField1_ |= 256;
                                break;
                            case 24146:
                                MemInfoDumpProto.Builder subBuilder41 = null;
                                if ((this.bitField1_ & 512) == 512) {
                                    subBuilder41 = (MemInfoDumpProto.Builder) this.meminfo_.toBuilder();
                                }
                                this.meminfo_ = (MemInfoDumpProto) input.readMessage(MemInfoDumpProto.parser(), extensionRegistry);
                                if (subBuilder41 != null) {
                                    subBuilder41.mergeFrom((GeneratedMessageLite) this.meminfo_);
                                    this.meminfo_ = (MemInfoDumpProto) subBuilder41.buildPartial();
                                }
                                this.bitField1_ |= 512;
                                break;
                            case 24154:
                                GraphicsStatsServiceDumpProto.Builder subBuilder42 = null;
                                if ((this.bitField1_ & 1024) == 1024) {
                                    subBuilder42 = (GraphicsStatsServiceDumpProto.Builder) this.graphicsstats_.toBuilder();
                                }
                                this.graphicsstats_ = (GraphicsStatsServiceDumpProto) input.readMessage(GraphicsStatsServiceDumpProto.parser(), extensionRegistry);
                                if (subBuilder42 != null) {
                                    subBuilder42.mergeFrom((GeneratedMessageLite) this.graphicsstats_);
                                    this.graphicsstats_ = (GraphicsStatsServiceDumpProto) subBuilder42.buildPartial();
                                }
                                this.bitField1_ |= 1024;
                                break;
                            case 24162:
                                JobSchedulerServiceDumpProto.Builder subBuilder43 = null;
                                if ((this.bitField1_ & 2048) == 2048) {
                                    subBuilder43 = (JobSchedulerServiceDumpProto.Builder) this.jobscheduler_.toBuilder();
                                }
                                this.jobscheduler_ = (JobSchedulerServiceDumpProto) input.readMessage(JobSchedulerServiceDumpProto.parser(), extensionRegistry);
                                if (subBuilder43 != null) {
                                    subBuilder43.mergeFrom((GeneratedMessageLite) this.jobscheduler_);
                                    this.jobscheduler_ = (JobSchedulerServiceDumpProto) subBuilder43.buildPartial();
                                }
                                this.bitField1_ |= 2048;
                                break;
                            case 24170:
                                UsbServiceDumpProto.Builder subBuilder44 = null;
                                if ((this.bitField1_ & 4096) == 4096) {
                                    subBuilder44 = (UsbServiceDumpProto.Builder) this.usb_.toBuilder();
                                }
                                this.usb_ = (UsbServiceDumpProto) input.readMessage(UsbServiceDumpProto.parser(), extensionRegistry);
                                if (subBuilder44 != null) {
                                    subBuilder44.mergeFrom((GeneratedMessageLite) this.usb_);
                                    this.usb_ = (UsbServiceDumpProto) subBuilder44.buildPartial();
                                }
                                this.bitField1_ |= 4096;
                                break;
                            case 24178:
                                BatteryStatsServiceDumpHistoryProto.Builder subBuilder45 = null;
                                if ((this.bitField1_ & 8192) == 8192) {
                                    subBuilder45 = (BatteryStatsServiceDumpHistoryProto.Builder) this.batteryHistory_.toBuilder();
                                }
                                this.batteryHistory_ = (BatteryStatsServiceDumpHistoryProto) input.readMessage(BatteryStatsServiceDumpHistoryProto.parser(), extensionRegistry);
                                if (subBuilder45 != null) {
                                    subBuilder45.mergeFrom((GeneratedMessageLite) this.batteryHistory_);
                                    this.batteryHistory_ = (BatteryStatsServiceDumpHistoryProto) subBuilder45.buildPartial();
                                }
                                this.bitField1_ |= 8192;
                                break;
                            case 24186:
                                StatsDataDumpProto.Builder subBuilder46 = null;
                                if ((this.bitField1_ & 16384) == 16384) {
                                    subBuilder46 = (StatsDataDumpProto.Builder) this.statsData_.toBuilder();
                                }
                                this.statsData_ = (StatsDataDumpProto) input.readMessage(StatsDataDumpProto.parser(), extensionRegistry);
                                if (subBuilder46 != null) {
                                    subBuilder46.mergeFrom((GeneratedMessageLite) this.statsData_);
                                    this.statsData_ = (StatsDataDumpProto) subBuilder46.buildPartial();
                                }
                                this.bitField1_ |= 16384;
                                break;
                            case 24194:
                                RoleManagerServiceDumpProto.Builder subBuilder47 = null;
                                if ((this.bitField1_ & 32768) == 32768) {
                                    subBuilder47 = (RoleManagerServiceDumpProto.Builder) this.role_.toBuilder();
                                }
                                this.role_ = (RoleManagerServiceDumpProto) input.readMessage(RoleManagerServiceDumpProto.parser(), extensionRegistry);
                                if (subBuilder47 != null) {
                                    subBuilder47.mergeFrom((GeneratedMessageLite) this.role_);
                                    this.role_ = (RoleManagerServiceDumpProto) subBuilder47.buildPartial();
                                }
                                this.bitField1_ = 32768 | this.bitField1_;
                                break;
                            case 24202:
                                RestrictedImagesDumpProto.Builder subBuilder48 = null;
                                if ((this.bitField1_ & 65536) == 65536) {
                                    subBuilder48 = (RestrictedImagesDumpProto.Builder) this.restrictedImages_.toBuilder();
                                }
                                this.restrictedImages_ = (RestrictedImagesDumpProto) input.readMessage(RestrictedImagesDumpProto.parser(), extensionRegistry);
                                if (subBuilder48 != null) {
                                    subBuilder48.mergeFrom((GeneratedMessageLite) this.restrictedImages_);
                                    this.restrictedImages_ = (RestrictedImagesDumpProto) subBuilder48.buildPartial();
                                }
                                this.bitField1_ |= 65536;
                                break;
                            case 24210:
                                this.bitField1_ |= 131072;
                                this.systemTrace_ = input.readBytes();
                                break;
                            default:
                                if (parseUnknownField((IncidentProto) getDefaultInstanceForType(), input, extensionRegistry, tag)) {
                                    break;
                                } else {
                                    done = true;
                                    break;
                                }
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw new RuntimeException(e.setUnfinishedMessage(this));
                    } catch (IOException e2) {
                        throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                    }
                }
                break;
            case GET_DEFAULT_INSTANCE:
                break;
            case GET_PARSER:
                if (PARSER == null) {
                    synchronized (IncidentProto.class) {
                        if (PARSER == null) {
                            PARSER = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                        }
                    }
                }
                return PARSER;
            default:
                throw new UnsupportedOperationException();
        }
        return DEFAULT_INSTANCE;
    }

    static {
        DEFAULT_INSTANCE.makeImmutable();
    }

    public static IncidentProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<IncidentProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
