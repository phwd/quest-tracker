package android.content;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface ComponentNameProtoOrBuilder extends MessageLiteOrBuilder {
    String getClassName();

    ByteString getClassNameBytes();

    String getPackageName();

    ByteString getPackageNameBytes();

    boolean hasClassName();

    boolean hasPackageName();
}
