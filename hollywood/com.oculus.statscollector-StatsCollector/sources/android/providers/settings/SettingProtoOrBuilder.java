package android.providers.settings;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface SettingProtoOrBuilder extends MessageLiteOrBuilder {
    boolean getDefaultFromSystem();

    String getDefaultValue();

    ByteString getDefaultValueBytes();

    String getId();

    ByteString getIdBytes();

    String getName();

    ByteString getNameBytes();

    String getPkg();

    ByteString getPkgBytes();

    String getValue();

    ByteString getValueBytes();

    boolean hasDefaultFromSystem();

    boolean hasDefaultValue();

    boolean hasId();

    boolean hasName();

    boolean hasPkg();

    boolean hasValue();
}
