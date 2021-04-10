package android.service.print;

import android.service.print.PrintAttributesProto;
import com.google.protobuf.MessageLiteOrBuilder;

public interface PrintAttributesProtoOrBuilder extends MessageLiteOrBuilder {
    PrintAttributesProto.ColorMode getColorMode();

    PrintAttributesProto.DuplexMode getDuplexMode();

    boolean getIsPortrait();

    ResolutionProto getMediaSize();

    MarginsProto getMinMargins();

    ResolutionProto getResolution();

    boolean hasColorMode();

    boolean hasDuplexMode();

    boolean hasIsPortrait();

    boolean hasMediaSize();

    boolean hasMinMargins();

    boolean hasResolution();
}
