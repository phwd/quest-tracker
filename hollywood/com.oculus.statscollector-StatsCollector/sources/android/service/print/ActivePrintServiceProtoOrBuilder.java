package android.service.print;

import android.content.ComponentNameProto;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface ActivePrintServiceProtoOrBuilder extends MessageLiteOrBuilder {
    ComponentNameProto getComponentName();

    boolean getHasActivePrintJobs();

    boolean getHasDiscoverySession();

    boolean getIsBound();

    boolean getIsDestroyed();

    boolean getIsDiscoveringPrinters();

    PrinterIdProto getTrackedPrinters(int i);

    int getTrackedPrintersCount();

    List<PrinterIdProto> getTrackedPrintersList();

    boolean hasComponentName();

    boolean hasHasActivePrintJobs();

    boolean hasHasDiscoverySession();

    boolean hasIsBound();

    boolean hasIsDestroyed();

    boolean hasIsDiscoveringPrinters();
}
