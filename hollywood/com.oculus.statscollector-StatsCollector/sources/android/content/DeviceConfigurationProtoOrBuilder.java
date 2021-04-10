package android.content;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface DeviceConfigurationProtoOrBuilder extends MessageLiteOrBuilder {
    String getCpuArchitectures(int i);

    ByteString getCpuArchitecturesBytes(int i);

    int getCpuArchitecturesCount();

    List<String> getCpuArchitecturesList();

    String getFeatures(int i);

    ByteString getFeaturesBytes(int i);

    int getFeaturesCount();

    List<String> getFeaturesList();

    boolean getHasSecureScreenLock();

    boolean getLowRam();

    int getMaxCores();

    String getOpenglExtensions(int i);

    ByteString getOpenglExtensionsBytes(int i);

    int getOpenglExtensionsCount();

    List<String> getOpenglExtensionsList();

    int getOpenglVersion();

    String getSharedLibraries(int i);

    ByteString getSharedLibrariesBytes(int i);

    int getSharedLibrariesCount();

    List<String> getSharedLibrariesList();

    int getStableDensityDpi();

    int getStableScreenHeightPx();

    int getStableScreenWidthPx();

    long getTotalRam();

    boolean hasHasSecureScreenLock();

    boolean hasLowRam();

    boolean hasMaxCores();

    boolean hasOpenglVersion();

    boolean hasStableDensityDpi();

    boolean hasStableScreenHeightPx();

    boolean hasStableScreenWidthPx();

    boolean hasTotalRam();
}
