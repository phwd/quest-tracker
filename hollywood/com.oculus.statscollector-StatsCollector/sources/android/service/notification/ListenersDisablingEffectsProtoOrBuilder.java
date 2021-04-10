package android.service.notification;

import android.content.ComponentNameProto;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface ListenersDisablingEffectsProtoOrBuilder extends MessageLiteOrBuilder {
    int getHint();

    ComponentNameProto getListenerComponents(int i);

    int getListenerComponentsCount();

    List<ComponentNameProto> getListenerComponentsList();

    boolean hasHint();
}
