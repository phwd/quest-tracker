package android.service.print;

import com.google.protobuf.MessageLiteOrBuilder;

public interface CachedPrintJobProtoOrBuilder extends MessageLiteOrBuilder {
    int getAppId();

    PrintJobInfoProto getPrintJob();

    boolean hasAppId();

    boolean hasPrintJob();
}
