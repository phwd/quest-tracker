package android.service.print;

import android.content.ComponentNameProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface InstalledPrintServiceProtoOrBuilder extends MessageLiteOrBuilder {
    String getAddPrintersActivity();

    ByteString getAddPrintersActivityBytes();

    String getAdvancedOptionsActivity();

    ByteString getAdvancedOptionsActivityBytes();

    ComponentNameProto getComponentName();

    String getSettingsActivity();

    ByteString getSettingsActivityBytes();

    boolean hasAddPrintersActivity();

    boolean hasAdvancedOptionsActivity();

    boolean hasComponentName();

    boolean hasSettingsActivity();
}
