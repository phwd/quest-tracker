package android.service.procstats;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface ProcessStatsAvailablePagesProtoOrBuilder extends MessageLiteOrBuilder {
    String getLabel();

    ByteString getLabelBytes();

    int getNode();

    int getPagesPerOrder(int i);

    int getPagesPerOrderCount();

    List<Integer> getPagesPerOrderList();

    String getZone();

    ByteString getZoneBytes();

    boolean hasLabel();

    boolean hasNode();

    boolean hasZone();
}
