package android.service.print;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface PrinterDiscoverySessionProtoOrBuilder extends MessageLiteOrBuilder {
    String getDiscoveryRequests(int i);

    ByteString getDiscoveryRequestsBytes(int i);

    int getDiscoveryRequestsCount();

    List<String> getDiscoveryRequestsList();

    boolean getIsDestroyed();

    boolean getIsPrinterDiscoveryInProgress();

    PrinterInfoProto getPrinter(int i);

    int getPrinterCount();

    String getPrinterDiscoveryObservers(int i);

    ByteString getPrinterDiscoveryObserversBytes(int i);

    int getPrinterDiscoveryObserversCount();

    List<String> getPrinterDiscoveryObserversList();

    List<PrinterInfoProto> getPrinterList();

    PrinterIdProto getTrackedPrinterRequests(int i);

    int getTrackedPrinterRequestsCount();

    List<PrinterIdProto> getTrackedPrinterRequestsList();

    boolean hasIsDestroyed();

    boolean hasIsPrinterDiscoveryInProgress();
}
