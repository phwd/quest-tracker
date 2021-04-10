package android.service.usb;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface UsbSettingsAccessoryPermissionProtoOrBuilder extends MessageLiteOrBuilder {
    String getAccessoryDescription();

    ByteString getAccessoryDescriptionBytes();

    int getUids(int i);

    int getUidsCount();

    List<Integer> getUidsList();

    boolean hasAccessoryDescription();
}
