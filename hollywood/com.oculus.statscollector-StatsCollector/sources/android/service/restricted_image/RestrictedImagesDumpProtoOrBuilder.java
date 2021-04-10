package android.service.restricted_image;

import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface RestrictedImagesDumpProtoOrBuilder extends MessageLiteOrBuilder {
    RestrictedImageSetProto getSets(int i);

    int getSetsCount();

    List<RestrictedImageSetProto> getSetsList();
}
