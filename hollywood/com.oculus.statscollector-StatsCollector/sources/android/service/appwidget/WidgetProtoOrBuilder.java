package android.service.appwidget;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface WidgetProtoOrBuilder extends MessageLiteOrBuilder {
    String getHostPackage();

    ByteString getHostPackageBytes();

    boolean getIsCrossProfile();

    boolean getIsHostStopped();

    int getMaxHeight();

    int getMaxWidth();

    int getMinHeight();

    int getMinWidth();

    String getProviderClass();

    ByteString getProviderClassBytes();

    String getProviderPackage();

    ByteString getProviderPackageBytes();

    boolean hasHostPackage();

    boolean hasIsCrossProfile();

    boolean hasIsHostStopped();

    boolean hasMaxHeight();

    boolean hasMaxWidth();

    boolean hasMinHeight();

    boolean hasMinWidth();

    boolean hasProviderClass();

    boolean hasProviderPackage();
}
