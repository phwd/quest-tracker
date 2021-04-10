package com.android.service;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface NetworkWatchlistReportProtoOrBuilder extends MessageLiteOrBuilder {
    NetworkWatchlistAppResultProto getAppResult(int i);

    int getAppResultCount();

    List<NetworkWatchlistAppResultProto> getAppResultList();

    int getReportVersion();

    String getWatchlistConfigHash();

    ByteString getWatchlistConfigHashBytes();

    boolean hasReportVersion();

    boolean hasWatchlistConfigHash();
}
