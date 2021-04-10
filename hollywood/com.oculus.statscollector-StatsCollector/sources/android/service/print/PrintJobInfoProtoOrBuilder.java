package android.service.print;

import android.service.print.PrintJobInfoProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface PrintJobInfoProtoOrBuilder extends MessageLiteOrBuilder {
    PrintAttributesProto getAttributes();

    long getCreationTime();

    PrintDocumentInfoProto getDocumentInfo();

    boolean getHasAdvancedOptions();

    boolean getIsCanceling();

    String getLabel();

    ByteString getLabelBytes();

    PageRangeProto getPages(int i);

    int getPagesCount();

    List<PageRangeProto> getPagesList();

    String getPrintJobId();

    ByteString getPrintJobIdBytes();

    PrinterIdProto getPrinter();

    float getProgress();

    PrintJobInfoProto.State getState();

    String getStatus();

    ByteString getStatusBytes();

    String getTag();

    ByteString getTagBytes();

    boolean hasAttributes();

    boolean hasCreationTime();

    boolean hasDocumentInfo();

    boolean hasHasAdvancedOptions();

    boolean hasIsCanceling();

    boolean hasLabel();

    boolean hasPrintJobId();

    boolean hasPrinter();

    boolean hasProgress();

    boolean hasState();

    boolean hasStatus();

    boolean hasTag();
}
