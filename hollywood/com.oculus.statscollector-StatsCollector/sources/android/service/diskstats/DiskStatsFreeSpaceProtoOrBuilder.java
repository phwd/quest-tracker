package android.service.diskstats;

import android.service.diskstats.DiskStatsFreeSpaceProto;
import com.google.protobuf.MessageLiteOrBuilder;

public interface DiskStatsFreeSpaceProtoOrBuilder extends MessageLiteOrBuilder {
    long getAvailableSpaceKb();

    DiskStatsFreeSpaceProto.Folder getFolder();

    long getTotalSpaceKb();

    boolean hasAvailableSpaceKb();

    boolean hasFolder();

    boolean hasTotalSpaceKb();
}
