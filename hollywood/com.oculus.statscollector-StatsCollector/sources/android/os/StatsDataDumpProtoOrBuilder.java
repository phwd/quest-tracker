package android.os;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface StatsDataDumpProtoOrBuilder extends MessageLiteOrBuilder {
    ByteString getConfigMetricsReportList(int i);

    int getConfigMetricsReportListCount();

    List<ByteString> getConfigMetricsReportListList();
}
