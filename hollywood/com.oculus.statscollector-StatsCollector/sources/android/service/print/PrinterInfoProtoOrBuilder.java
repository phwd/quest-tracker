package android.service.print;

import android.service.print.PrinterInfoProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface PrinterInfoProtoOrBuilder extends MessageLiteOrBuilder {
    PrinterCapabilitiesProto getCapabilities();

    String getDescription();

    ByteString getDescriptionBytes();

    PrinterIdProto getId();

    String getName();

    ByteString getNameBytes();

    PrinterInfoProto.Status getStatus();

    boolean hasCapabilities();

    boolean hasDescription();

    boolean hasId();

    boolean hasName();

    boolean hasStatus();
}
