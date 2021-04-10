package android.util;

import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface LogProtoOrBuilder extends MessageLiteOrBuilder {
    BinaryLogEntry getBinaryLogs(int i);

    int getBinaryLogsCount();

    List<BinaryLogEntry> getBinaryLogsList();

    TextLogEntry getTextLogs(int i);

    int getTextLogsCount();

    List<TextLogEntry> getTextLogsList();
}
