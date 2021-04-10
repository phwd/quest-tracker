package android.app;

import android.app.PolicyProto;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface PolicyProtoOrBuilder extends MessageLiteOrBuilder {
    PolicyProto.Sender getPriorityCallSender();

    PolicyProto.Category getPriorityCategories(int i);

    int getPriorityCategoriesCount();

    List<PolicyProto.Category> getPriorityCategoriesList();

    PolicyProto.Sender getPriorityMessageSender();

    PolicyProto.SuppressedVisualEffect getSuppressedVisualEffects(int i);

    int getSuppressedVisualEffectsCount();

    List<PolicyProto.SuppressedVisualEffect> getSuppressedVisualEffectsList();

    boolean hasPriorityCallSender();

    boolean hasPriorityMessageSender();
}
