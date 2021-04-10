package com.google.common.hash;

abstract class AbstractCompositeHashFunction extends AbstractStreamingHashFunction {
    final HashFunction[] functions;
}
