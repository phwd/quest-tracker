package android.service.pm;

import android.service.pm.PackageProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface PackageProtoOrBuilder extends MessageLiteOrBuilder {
    long getInstallTimeMs();

    String getInstallerName();

    ByteString getInstallerNameBytes();

    String getName();

    ByteString getNameBytes();

    PackageProto.SplitProto getSplits(int i);

    int getSplitsCount();

    List<PackageProto.SplitProto> getSplitsList();

    int getUid();

    long getUpdateTimeMs();

    PackageProto.UserInfoProto getUsers(int i);

    int getUsersCount();

    List<PackageProto.UserInfoProto> getUsersList();

    int getVersionCode();

    String getVersionString();

    ByteString getVersionStringBytes();

    boolean hasInstallTimeMs();

    boolean hasInstallerName();

    boolean hasName();

    boolean hasUid();

    boolean hasUpdateTimeMs();

    boolean hasVersionCode();

    boolean hasVersionString();
}
