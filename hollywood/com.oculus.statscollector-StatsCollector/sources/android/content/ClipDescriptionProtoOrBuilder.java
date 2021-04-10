package android.content;

import android.os.PersistableBundleProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface ClipDescriptionProtoOrBuilder extends MessageLiteOrBuilder {
    PersistableBundleProto getExtras();

    String getLabel();

    ByteString getLabelBytes();

    String getMimeTypes(int i);

    ByteString getMimeTypesBytes(int i);

    int getMimeTypesCount();

    List<String> getMimeTypesList();

    long getTimestampMs();

    boolean hasExtras();

    boolean hasLabel();

    boolean hasTimestampMs();
}
