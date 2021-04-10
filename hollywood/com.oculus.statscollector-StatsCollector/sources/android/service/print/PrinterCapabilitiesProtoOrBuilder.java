package android.service.print;

import android.service.print.PrintAttributesProto;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface PrinterCapabilitiesProtoOrBuilder extends MessageLiteOrBuilder {
    PrintAttributesProto.ColorMode getColorModes(int i);

    int getColorModesCount();

    List<PrintAttributesProto.ColorMode> getColorModesList();

    PrintAttributesProto.DuplexMode getDuplexModes(int i);

    int getDuplexModesCount();

    List<PrintAttributesProto.DuplexMode> getDuplexModesList();

    MediaSizeProto getMediaSizes(int i);

    int getMediaSizesCount();

    List<MediaSizeProto> getMediaSizesList();

    MarginsProto getMinMargins();

    ResolutionProto getResolutions(int i);

    int getResolutionsCount();

    List<ResolutionProto> getResolutionsList();

    boolean hasMinMargins();
}
