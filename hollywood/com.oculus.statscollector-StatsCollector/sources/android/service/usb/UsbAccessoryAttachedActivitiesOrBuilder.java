package android.service.usb;

import android.content.ComponentNameProto;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface UsbAccessoryAttachedActivitiesOrBuilder extends MessageLiteOrBuilder {
    ComponentNameProto getActivity();

    UsbAccessoryFilterProto getFilters(int i);

    int getFiltersCount();

    List<UsbAccessoryFilterProto> getFiltersList();

    boolean hasActivity();
}
