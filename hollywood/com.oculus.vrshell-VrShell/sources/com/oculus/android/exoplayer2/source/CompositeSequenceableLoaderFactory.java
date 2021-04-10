package com.oculus.android.exoplayer2.source;

public interface CompositeSequenceableLoaderFactory {
    SequenceableLoader createCompositeSequenceableLoader(SequenceableLoader... sequenceableLoaderArr);
}
