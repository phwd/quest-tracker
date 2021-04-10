package android.os;

import android.os.BundleProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface BundleProtoOrBuilder extends MessageLiteOrBuilder {
    BundleProto.DataCase getDataCase();

    String getMapData();

    ByteString getMapDataBytes();

    int getParcelledDataSize();

    boolean hasMapData();

    boolean hasParcelledDataSize();
}
