package android.service.notification;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface PackageRemoteViewInfoProtoOrBuilder extends MessageLiteOrBuilder {
    String getPackageName();

    ByteString getPackageNameBytes();

    boolean hasPackageName();
}
