package android.content;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface IntentProtoOrBuilder extends MessageLiteOrBuilder {
    String getAction();

    ByteString getActionBytes();

    String getCategories(int i);

    ByteString getCategoriesBytes(int i);

    int getCategoriesCount();

    List<String> getCategoriesList();

    String getClipData();

    ByteString getClipDataBytes();

    ComponentNameProto getComponent();

    int getContentUserHint();

    String getData();

    ByteString getDataBytes();

    String getExtras();

    ByteString getExtrasBytes();

    String getFlag();

    ByteString getFlagBytes();

    String getIdentifier();

    ByteString getIdentifierBytes();

    String getPackage();

    ByteString getPackageBytes();

    String getSelector();

    ByteString getSelectorBytes();

    String getSourceBounds();

    ByteString getSourceBoundsBytes();

    String getType();

    ByteString getTypeBytes();

    boolean hasAction();

    boolean hasClipData();

    boolean hasComponent();

    boolean hasContentUserHint();

    boolean hasData();

    boolean hasExtras();

    boolean hasFlag();

    boolean hasIdentifier();

    boolean hasPackage();

    boolean hasSelector();

    boolean hasSourceBounds();

    boolean hasType();
}
