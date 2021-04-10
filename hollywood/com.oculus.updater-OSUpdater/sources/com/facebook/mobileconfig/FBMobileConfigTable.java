package com.facebook.mobileconfig;

import com.facebook.infer.annotation.Nullsafe;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class FBMobileConfigTable extends Table {
    public static FBMobileConfigTable getRootAsFBMobileConfigTable(ByteBuffer byteBuffer) {
        return getRootAsFBMobileConfigTable(byteBuffer, new FBMobileConfigTable());
    }

    public static FBMobileConfigTable getRootAsFBMobileConfigTable(ByteBuffer byteBuffer, FBMobileConfigTable fBMobileConfigTable) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return fBMobileConfigTable.__init(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public FBMobileConfigTable __init(int i, ByteBuffer byteBuffer) {
        this.bb_pos = i;
        this.bb = byteBuffer;
        return this;
    }

    @Nullable
    public ByteBuffer schemaHashAsByteBuffer() {
        return __vector_as_bytebuffer(4, 1);
    }

    public byte bools(int i) {
        int __offset = __offset(6);
        if (__offset != 0) {
            return this.bb.get(__vector(__offset) + (i * 1));
        }
        return 0;
    }

    public int boolsMeta(int i) {
        int __offset = __offset(8);
        if (__offset != 0) {
            return this.bb.getInt(__vector(__offset) + (i * 4));
        }
        return 0;
    }

    public long longs(int i) {
        int __offset = __offset(10);
        if (__offset != 0) {
            return this.bb.getLong(__vector(__offset) + (i * 8));
        }
        return 0;
    }

    public int longsMeta(int i) {
        int __offset = __offset(12);
        if (__offset != 0) {
            return this.bb.getInt(__vector(__offset) + (i * 4));
        }
        return 0;
    }

    public double doubles(int i) {
        int __offset = __offset(18);
        if (__offset != 0) {
            return this.bb.getDouble(__vector(__offset) + (i * 8));
        }
        return 0.0d;
    }

    public int doublesMeta(int i) {
        int __offset = __offset(20);
        if (__offset != 0) {
            return this.bb.getInt(__vector(__offset) + (i * 4));
        }
        return 0;
    }

    @Nullable
    public String strings(int i) {
        int __offset = __offset(22);
        if (__offset != 0) {
            return __string(__vector(__offset) + (i * 4));
        }
        return null;
    }

    public int stringsMeta(int i) {
        int __offset = __offset(24);
        if (__offset != 0) {
            return this.bb.getInt(__vector(__offset) + (i * 4));
        }
        return 0;
    }

    @Nullable
    public String loggingIds(int i) {
        int __offset = __offset(26);
        if (__offset != 0) {
            return __string(__vector(__offset) + (i * 4));
        }
        return null;
    }

    public int loggingIdsLength() {
        int __offset = __offset(26);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public int magic() {
        int __offset = __offset(28);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    @Nullable
    public FBMobileConfigEPInfo emergencyPushInfo(int i) {
        return emergencyPushInfo(new FBMobileConfigEPInfo(), i);
    }

    @Nullable
    public FBMobileConfigEPInfo emergencyPushInfo(FBMobileConfigEPInfo fBMobileConfigEPInfo, int i) {
        int __offset = __offset(32);
        if (__offset != 0) {
            return fBMobileConfigEPInfo.__init(__indirect(__vector(__offset) + (i * 4)), this.bb);
        }
        return null;
    }

    public int emergencyPushInfoLength() {
        int __offset = __offset(32);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public int magic2() {
        int __offset = __offset(34);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }
}
