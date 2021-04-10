package android.util;

import android.util.BinaryLogEntry;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface BinaryLogEntryOrBuilder extends MessageLiteOrBuilder {
    BinaryLogEntry.Elem getElems(int i);

    int getElemsCount();

    List<BinaryLogEntry.Elem> getElemsList();

    long getNanosec();

    int getPid();

    long getSec();

    int getTagIndex();

    int getTid();

    int getUid();

    boolean hasNanosec();

    boolean hasPid();

    boolean hasSec();

    boolean hasTagIndex();

    boolean hasTid();

    boolean hasUid();
}
