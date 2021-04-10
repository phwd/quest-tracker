package android.service.notification;

import android.service.notification.RankingHelperProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface RankingHelperProtoOrBuilder extends MessageLiteOrBuilder {
    String getNotificationSignalExtractors(int i);

    ByteString getNotificationSignalExtractorsBytes(int i);

    int getNotificationSignalExtractorsCount();

    List<String> getNotificationSignalExtractorsList();

    RankingHelperProto.RecordProto getRecords(int i);

    int getRecordsCount();

    List<RankingHelperProto.RecordProto> getRecordsList();

    RankingHelperProto.RecordProto getRecordsRestoredWithoutUid(int i);

    int getRecordsRestoredWithoutUidCount();

    List<RankingHelperProto.RecordProto> getRecordsRestoredWithoutUidList();
}
