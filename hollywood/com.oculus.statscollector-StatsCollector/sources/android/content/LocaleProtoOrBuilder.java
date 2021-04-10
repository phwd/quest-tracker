package android.content;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface LocaleProtoOrBuilder extends MessageLiteOrBuilder {
    String getCountry();

    ByteString getCountryBytes();

    String getLanguage();

    ByteString getLanguageBytes();

    String getScript();

    ByteString getScriptBytes();

    String getVariant();

    ByteString getVariantBytes();

    boolean hasCountry();

    boolean hasLanguage();

    boolean hasScript();

    boolean hasVariant();
}
