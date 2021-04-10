package android.os;

import android.os.IncidentMetadata;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface IncidentMetadataOrBuilder extends MessageLiteOrBuilder {
    IncidentMetadata.Destination getDest();

    long getReportId();

    int getRequestSize();

    IncidentMetadata.SectionStats getSections(int i);

    int getSectionsCount();

    List<IncidentMetadata.SectionStats> getSectionsList();

    int getSequenceNumber();

    boolean getUseDropbox();

    boolean hasDest();

    boolean hasReportId();

    boolean hasRequestSize();

    boolean hasSequenceNumber();

    boolean hasUseDropbox();
}
