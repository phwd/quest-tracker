package android.service.pm;

import android.content.pm.FeatureInfoProto;
import android.service.pm.PackageServiceDumpProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface PackageServiceDumpProtoOrBuilder extends MessageLiteOrBuilder {
    FeatureInfoProto getFeatures(int i);

    int getFeaturesCount();

    List<FeatureInfoProto> getFeaturesList();

    String getMessages(int i);

    ByteString getMessagesBytes(int i);

    int getMessagesCount();

    List<String> getMessagesList();

    PackageProto getPackages(int i);

    int getPackagesCount();

    List<PackageProto> getPackagesList();

    PackageServiceDumpProto.PackageShortProto getRequiredVerifierPackage();

    PackageServiceDumpProto.SharedLibraryProto getSharedLibraries(int i);

    int getSharedLibrariesCount();

    List<PackageServiceDumpProto.SharedLibraryProto> getSharedLibrariesList();

    PackageServiceDumpProto.SharedUserProto getSharedUsers(int i);

    int getSharedUsersCount();

    List<PackageServiceDumpProto.SharedUserProto> getSharedUsersList();

    PackageServiceDumpProto.PackageShortProto getVerifierPackage();

    boolean hasRequiredVerifierPackage();

    boolean hasVerifierPackage();
}
