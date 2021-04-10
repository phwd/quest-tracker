package android.os;

import android.os.PersistableBundleProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface PersistableBundleProtoOrBuilder extends MessageLiteOrBuilder {
    PersistableBundleProto.DataCase getDataCase();

    String getMapData();

    ByteString getMapDataBytes();

    int getParcelledDataSize();

    boolean hasMapData();

    boolean hasParcelledDataSize();
}
