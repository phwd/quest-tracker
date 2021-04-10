package com.android.server;

import com.android.server.IntentResolverProto;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface IntentResolverProtoOrBuilder extends MessageLiteOrBuilder {
    IntentResolverProto.ArrayMapEntry getBaseMimeTypes(int i);

    int getBaseMimeTypesCount();

    List<IntentResolverProto.ArrayMapEntry> getBaseMimeTypesList();

    IntentResolverProto.ArrayMapEntry getFullMimeTypes(int i);

    int getFullMimeTypesCount();

    List<IntentResolverProto.ArrayMapEntry> getFullMimeTypesList();

    IntentResolverProto.ArrayMapEntry getMimeTypedActions(int i);

    int getMimeTypedActionsCount();

    List<IntentResolverProto.ArrayMapEntry> getMimeTypedActionsList();

    IntentResolverProto.ArrayMapEntry getNonDataActions(int i);

    int getNonDataActionsCount();

    List<IntentResolverProto.ArrayMapEntry> getNonDataActionsList();

    IntentResolverProto.ArrayMapEntry getSchemes(int i);

    int getSchemesCount();

    List<IntentResolverProto.ArrayMapEntry> getSchemesList();

    IntentResolverProto.ArrayMapEntry getWildMimeTypes(int i);

    int getWildMimeTypesCount();

    List<IntentResolverProto.ArrayMapEntry> getWildMimeTypesList();
}
