package com.facebook.flatbuffers;

import com.facebook.infer.annotation.Nullsafe;
import java.nio.ByteBuffer;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class Struct {
    @Nullable
    protected ByteBuffer bb;
    protected int bb_pos;
}
