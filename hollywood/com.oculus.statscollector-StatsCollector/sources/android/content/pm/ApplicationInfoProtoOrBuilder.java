package android.content.pm;

import android.content.pm.ApplicationInfoProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface ApplicationInfoProtoOrBuilder extends MessageLiteOrBuilder {
    String getClassLoaderName();

    ByteString getClassLoaderNameBytes();

    String getDataDir();

    ByteString getDataDirBytes();

    ApplicationInfoProto.Detail getDetail();

    int getFlags();

    PackageItemInfoProto getPackage();

    String getPermission();

    ByteString getPermissionBytes();

    int getPrivateFlags();

    String getProcessName();

    ByteString getProcessNameBytes();

    String getPublicSourceDir();

    ByteString getPublicSourceDirBytes();

    String getResourceDirs(int i);

    ByteString getResourceDirsBytes(int i);

    int getResourceDirsCount();

    List<String> getResourceDirsList();

    String getSourceDir();

    ByteString getSourceDirBytes();

    String getSplitClassLoaderNames(int i);

    ByteString getSplitClassLoaderNamesBytes(int i);

    int getSplitClassLoaderNamesCount();

    List<String> getSplitClassLoaderNamesList();

    String getSplitPublicSourceDirs(int i);

    ByteString getSplitPublicSourceDirsBytes(int i);

    int getSplitPublicSourceDirsCount();

    List<String> getSplitPublicSourceDirsList();

    String getSplitSourceDirs(int i);

    ByteString getSplitSourceDirsBytes(int i);

    int getSplitSourceDirsCount();

    List<String> getSplitSourceDirsList();

    int getTheme();

    int getUid();

    ApplicationInfoProto.Version getVersion();

    boolean hasClassLoaderName();

    boolean hasDataDir();

    boolean hasDetail();

    boolean hasFlags();

    boolean hasPackage();

    boolean hasPermission();

    boolean hasPrivateFlags();

    boolean hasProcessName();

    boolean hasPublicSourceDir();

    boolean hasSourceDir();

    boolean hasTheme();

    boolean hasUid();

    boolean hasVersion();
}
