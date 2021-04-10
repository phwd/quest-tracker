package android.service.print;

import android.content.ComponentNameProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface PrintSpoolerInternalStateProtoOrBuilder extends MessageLiteOrBuilder {
    ComponentNameProto getApprovedServices(int i);

    int getApprovedServicesCount();

    List<ComponentNameProto> getApprovedServicesList();

    String getPrintJobFiles(int i);

    ByteString getPrintJobFilesBytes(int i);

    int getPrintJobFilesCount();

    List<String> getPrintJobFilesList();

    PrintJobInfoProto getPrintJobs(int i);

    int getPrintJobsCount();

    List<PrintJobInfoProto> getPrintJobsList();
}
