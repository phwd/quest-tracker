package android.service.procstats;

import com.google.protobuf.MessageLiteOrBuilder;

public interface ProcessStatsServiceDumpProtoOrBuilder extends MessageLiteOrBuilder {
    ProcessStatsSectionProto getProcstatsNow();

    ProcessStatsSectionProto getProcstatsOver24Hrs();

    ProcessStatsSectionProto getProcstatsOver3Hrs();

    boolean hasProcstatsNow();

    boolean hasProcstatsOver24Hrs();

    boolean hasProcstatsOver3Hrs();
}
