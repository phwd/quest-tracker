package android.service.notification;

import android.service.notification.ZenPolicyProto;
import com.google.protobuf.MessageLiteOrBuilder;

public interface ZenPolicyProtoOrBuilder extends MessageLiteOrBuilder {
    ZenPolicyProto.State getAlarms();

    ZenPolicyProto.State getAmbient();

    ZenPolicyProto.State getBadge();

    ZenPolicyProto.State getCalls();

    ZenPolicyProto.State getEvents();

    ZenPolicyProto.State getFullScreenIntent();

    ZenPolicyProto.State getLights();

    ZenPolicyProto.State getMedia();

    ZenPolicyProto.State getMessages();

    ZenPolicyProto.State getNotificationList();

    ZenPolicyProto.State getPeek();

    ZenPolicyProto.Sender getPriorityCalls();

    ZenPolicyProto.Sender getPriorityMessages();

    ZenPolicyProto.State getReminders();

    ZenPolicyProto.State getRepeatCallers();

    ZenPolicyProto.State getStatusBar();

    ZenPolicyProto.State getSystem();

    boolean hasAlarms();

    boolean hasAmbient();

    boolean hasBadge();

    boolean hasCalls();

    boolean hasEvents();

    boolean hasFullScreenIntent();

    boolean hasLights();

    boolean hasMedia();

    boolean hasMessages();

    boolean hasNotificationList();

    boolean hasPeek();

    boolean hasPriorityCalls();

    boolean hasPriorityMessages();

    boolean hasReminders();

    boolean hasRepeatCallers();

    boolean hasStatusBar();

    boolean hasSystem();
}
