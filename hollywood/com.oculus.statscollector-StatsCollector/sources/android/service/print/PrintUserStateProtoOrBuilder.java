package android.service.print;

import android.content.ComponentNameProto;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface PrintUserStateProtoOrBuilder extends MessageLiteOrBuilder {
    ActivePrintServiceProto getActiveServices(int i);

    int getActiveServicesCount();

    List<ActivePrintServiceProto> getActiveServicesList();

    CachedPrintJobProto getCachedPrintJobs(int i);

    int getCachedPrintJobsCount();

    List<CachedPrintJobProto> getCachedPrintJobsList();

    ComponentNameProto getDisabledServices(int i);

    int getDisabledServicesCount();

    List<ComponentNameProto> getDisabledServicesList();

    PrinterDiscoverySessionProto getDiscoverySessions(int i);

    int getDiscoverySessionsCount();

    List<PrinterDiscoverySessionProto> getDiscoverySessionsList();

    InstalledPrintServiceProto getInstalledServices(int i);

    int getInstalledServicesCount();

    List<InstalledPrintServiceProto> getInstalledServicesList();

    PrintSpoolerStateProto getPrintSpoolerState();

    int getUserId();

    boolean hasPrintSpoolerState();

    boolean hasUserId();
}
