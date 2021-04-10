package android.stats.devicepolicy;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface StringListOrBuilder extends MessageLiteOrBuilder {
    String getStringValue(int i);

    ByteString getStringValueBytes(int i);

    int getStringValueCount();

    List<String> getStringValueList();
}
