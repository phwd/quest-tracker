package android.service.usb;

import android.content.ComponentNameProto;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface UsbDeviceAttachedActivitiesOrBuilder extends MessageLiteOrBuilder {
    ComponentNameProto getActivity();

    UsbDeviceFilterProto getFilters(int i);

    int getFiltersCount();

    List<UsbDeviceFilterProto> getFiltersList();

    boolean hasActivity();
}
