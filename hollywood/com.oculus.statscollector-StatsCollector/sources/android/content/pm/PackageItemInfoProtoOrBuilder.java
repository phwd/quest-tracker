package android.content.pm;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface PackageItemInfoProtoOrBuilder extends MessageLiteOrBuilder {
    int getBanner();

    int getIcon();

    int getLabelRes();

    String getName();

    ByteString getNameBytes();

    String getNonLocalizedLabel();

    ByteString getNonLocalizedLabelBytes();

    String getPackageName();

    ByteString getPackageNameBytes();

    boolean hasBanner();

    boolean hasIcon();

    boolean hasLabelRes();

    boolean hasName();

    boolean hasNonLocalizedLabel();

    boolean hasPackageName();
}
