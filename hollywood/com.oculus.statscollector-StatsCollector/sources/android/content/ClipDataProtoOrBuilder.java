package android.content;

import android.content.ClipDataProto;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface ClipDataProtoOrBuilder extends MessageLiteOrBuilder {
    ClipDescriptionProto getDescription();

    ClipDataProto.Icon getIcon();

    ClipDataProto.Item getItems(int i);

    int getItemsCount();

    List<ClipDataProto.Item> getItemsList();

    boolean hasDescription();

    boolean hasIcon();
}
