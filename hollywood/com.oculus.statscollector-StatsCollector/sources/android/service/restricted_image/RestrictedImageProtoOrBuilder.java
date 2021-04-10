package android.service.restricted_image;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface RestrictedImageProtoOrBuilder extends MessageLiteOrBuilder {
    ByteString getImageData();

    ByteString getMetadata();

    String getMimeType();

    ByteString getMimeTypeBytes();

    boolean hasImageData();

    boolean hasMetadata();

    boolean hasMimeType();
}
