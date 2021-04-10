package com.facebook.mobileconfig;

import com.facebook.infer.annotation.Nullsafe;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class FBMobileConfigEPInfo extends Table {
    public FBMobileConfigEPInfo __init(int i, ByteBuffer byteBuffer) {
        this.bb_pos = i;
        this.bb = byteBuffer;
        return this;
    }

    public int configIndex() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public int version() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }
}
