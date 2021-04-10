package java.time.zone;

import android.support.v4.media.MediaPlayer2;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.Externalizable;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.StreamCorruptedException;
import java.time.ZoneOffset;

/* access modifiers changed from: package-private */
public final class Ser implements Externalizable {
    static final byte ZOT = 2;
    static final byte ZOTRULE = 3;
    static final byte ZRULES = 1;
    private static final long serialVersionUID = -8885321777449118786L;
    private Object object;
    private byte type;

    public Ser() {
    }

    Ser(byte type2, Object object2) {
        this.type = type2;
        this.object = object2;
    }

    @Override // java.io.Externalizable
    public void writeExternal(ObjectOutput out) throws IOException {
        writeInternal(this.type, this.object, out);
    }

    static void write(Object object2, DataOutput out) throws IOException {
        writeInternal((byte) 1, object2, out);
    }

    private static void writeInternal(byte type2, Object object2, DataOutput out) throws IOException {
        out.writeByte(type2);
        if (type2 == 1) {
            ((ZoneRules) object2).writeExternal(out);
        } else if (type2 == 2) {
            ((ZoneOffsetTransition) object2).writeExternal(out);
        } else if (type2 == 3) {
            ((ZoneOffsetTransitionRule) object2).writeExternal(out);
        } else {
            throw new InvalidClassException("Unknown serialized type");
        }
    }

    @Override // java.io.Externalizable
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.type = in.readByte();
        this.object = readInternal(this.type, in);
    }

    static Object read(DataInput in) throws IOException, ClassNotFoundException {
        return readInternal(in.readByte(), in);
    }

    private static Object readInternal(byte type2, DataInput in) throws IOException, ClassNotFoundException {
        if (type2 == 1) {
            return ZoneRules.readExternal(in);
        }
        if (type2 == 2) {
            return ZoneOffsetTransition.readExternal(in);
        }
        if (type2 == 3) {
            return ZoneOffsetTransitionRule.readExternal(in);
        }
        throw new StreamCorruptedException("Unknown serialized type");
    }

    private Object readResolve() {
        return this.object;
    }

    static void writeOffset(ZoneOffset offset, DataOutput out) throws IOException {
        int offsetSecs = offset.getTotalSeconds();
        int offsetByte = offsetSecs % MediaPlayer2.MEDIA_INFO_TIMED_TEXT_ERROR == 0 ? offsetSecs / MediaPlayer2.MEDIA_INFO_TIMED_TEXT_ERROR : 127;
        out.writeByte(offsetByte);
        if (offsetByte == 127) {
            out.writeInt(offsetSecs);
        }
    }

    static ZoneOffset readOffset(DataInput in) throws IOException {
        int offsetByte = in.readByte();
        return ZoneOffset.ofTotalSeconds(offsetByte == 127 ? in.readInt() : offsetByte * MediaPlayer2.MEDIA_INFO_TIMED_TEXT_ERROR);
    }

    static void writeEpochSec(long epochSec, DataOutput out) throws IOException {
        if (epochSec < -4575744000L || epochSec >= 10413792000L || epochSec % 900 != 0) {
            out.writeByte(255);
            out.writeLong(epochSec);
            return;
        }
        int store = (int) ((4575744000L + epochSec) / 900);
        out.writeByte((store >>> 16) & 255);
        out.writeByte(255 & (store >>> 8));
        out.writeByte(store & 255);
    }

    static long readEpochSec(DataInput in) throws IOException {
        int hiByte = in.readByte() & 255;
        if (hiByte == 255) {
            return in.readLong();
        }
        return (900 * ((long) (((hiByte << 16) + ((in.readByte() & 255) << 8)) + (255 & in.readByte())))) - 4575744000L;
    }
}
