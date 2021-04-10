package com.facebook.graphql.modelutil;

import com.facebook.flatbuffers.MutableFlatBuffer;
import com.facebook.flatbuffers.MutableFlattenable;

public interface FragmentModel extends MutableFlattenable, TypeTagModel {
    FragmentModel createAndInit(MutableFlatBuffer mutableFlatBuffer, int i);
}
