package android.service.notification;

import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface NotificationRemoteViewsProtoOrBuilder extends MessageLiteOrBuilder {
    PackageRemoteViewInfoProto getPackageRemoteViewInfo(int i);

    int getPackageRemoteViewInfoCount();

    List<PackageRemoteViewInfoProto> getPackageRemoteViewInfoList();
}
