package com.android.server.connectivity;

import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface DnsEventOrBuilder extends MessageLiteOrBuilder {
    int getDnsReturnCode(int i);

    int getDnsReturnCodeCount();

    List<Integer> getDnsReturnCodeList();

    long getDnsTime(int i);

    int getDnsTimeCount();

    List<Long> getDnsTimeList();
}
