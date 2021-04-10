package android.service.usb;

import android.service.usb.UsbPortProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface UsbPortProtoOrBuilder extends MessageLiteOrBuilder {
    String getId();

    ByteString getIdBytes();

    UsbPortProto.Mode getSupportedModes(int i);

    int getSupportedModesCount();

    List<UsbPortProto.Mode> getSupportedModesList();

    boolean hasId();
}
