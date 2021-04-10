package android.util;

import android.util.TextLogEntry;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface TextLogEntryOrBuilder extends MessageLiteOrBuilder {
    String getLog();

    ByteString getLogBytes();

    long getNanosec();

    int getPid();

    TextLogEntry.LogPriority getPriority();

    long getSec();

    String getTag();

    ByteString getTagBytes();

    int getTid();

    int getUid();

    boolean hasLog();

    boolean hasNanosec();

    boolean hasPid();

    boolean hasPriority();

    boolean hasSec();

    boolean hasTag();

    boolean hasTid();

    boolean hasUid();
}
