package com.google.protobuf;

import java.util.List;

public interface LazyStringList extends ProtocolStringList {
    List<?> getUnderlyingElements();
}
