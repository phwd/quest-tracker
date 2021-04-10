package android.service.notification;

import android.content.ComponentNameProto;
import android.service.notification.ManagedServicesProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface ManagedServicesProtoOrBuilder extends MessageLiteOrBuilder {
    ManagedServicesProto.ServiceProto getApproved(int i);

    int getApprovedCount();

    List<ManagedServicesProto.ServiceProto> getApprovedList();

    String getCaption();

    ByteString getCaptionBytes();

    ComponentNameProto getEnabled(int i);

    int getEnabledCount();

    List<ComponentNameProto> getEnabledList();

    ManagedServiceInfoProto getLiveServices(int i);

    int getLiveServicesCount();

    List<ManagedServiceInfoProto> getLiveServicesList();

    ComponentNameProto getSnoozed(int i);

    int getSnoozedCount();

    List<ComponentNameProto> getSnoozedList();

    boolean hasCaption();
}
