package android.service;

import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface GraphicsStatsServiceDumpProtoOrBuilder extends MessageLiteOrBuilder {
    GraphicsStatsProto getStats(int i);

    int getStatsCount();

    List<GraphicsStatsProto> getStatsList();
}
