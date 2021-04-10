package android.service.notification;

import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface NotificationServiceDumpProtoOrBuilder extends MessageLiteOrBuilder {
    ManagedServicesProto getConditionProviders();

    int getListenerHints();

    ListenersDisablingEffectsProto getListenersDisablingEffects(int i);

    int getListenersDisablingEffectsCount();

    List<ListenersDisablingEffectsProto> getListenersDisablingEffectsList();

    ManagedServicesProto getNotificationAssistants();

    ManagedServicesProto getNotificationListeners();

    RankingHelperProto getRankingConfig();

    NotificationRecordProto getRecords(int i);

    int getRecordsCount();

    List<NotificationRecordProto> getRecordsList();

    ZenModeProto getZen();

    boolean hasConditionProviders();

    boolean hasListenerHints();

    boolean hasNotificationAssistants();

    boolean hasNotificationListeners();

    boolean hasRankingConfig();

    boolean hasZen();
}
