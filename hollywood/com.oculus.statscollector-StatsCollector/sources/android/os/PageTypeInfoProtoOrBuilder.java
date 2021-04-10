package android.os;

import android.os.PageTypeInfoProto;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface PageTypeInfoProtoOrBuilder extends MessageLiteOrBuilder {
    PageTypeInfoProto.Block getBlocks(int i);

    int getBlocksCount();

    List<PageTypeInfoProto.Block> getBlocksList();

    PageTypeInfoProto.MigrateType getMigrateTypes(int i);

    int getMigrateTypesCount();

    List<PageTypeInfoProto.MigrateType> getMigrateTypesList();

    int getPageBlockOrder();

    int getPagesPerBlock();

    boolean hasPageBlockOrder();

    boolean hasPagesPerBlock();
}
