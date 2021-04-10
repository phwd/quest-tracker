package java.nio.charset;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

public abstract class CharsetEncoder {
    private static String[] stateNames = {"RESET", "CODING", "CODING_END", "FLUSHED"};
    private final float averageBytesPerChar;
    private final Charset charset;
    private CodingErrorAction malformedInputAction;
    private final float maxBytesPerChar;
    private byte[] replacement;
    private int state;
    private CodingErrorAction unmappableCharacterAction;

    /* access modifiers changed from: protected */
    public abstract CoderResult encodeLoop(CharBuffer charBuffer, ByteBuffer byteBuffer);

    /* access modifiers changed from: protected */
    public void implOnMalformedInput(CodingErrorAction codingErrorAction) {
    }

    /* access modifiers changed from: protected */
    public void implOnUnmappableCharacter(CodingErrorAction codingErrorAction) {
    }

    /* access modifiers changed from: protected */
    public void implReset() {
    }

    public final Charset charset() {
        return this.charset;
    }

    public final CharsetEncoder onMalformedInput(CodingErrorAction codingErrorAction) {
        if (codingErrorAction != null) {
            this.malformedInputAction = codingErrorAction;
            implOnMalformedInput(codingErrorAction);
            return this;
        }
        throw new IllegalArgumentException("Null action");
    }

    public final CharsetEncoder onUnmappableCharacter(CodingErrorAction codingErrorAction) {
        if (codingErrorAction != null) {
            this.unmappableCharacterAction = codingErrorAction;
            implOnUnmappableCharacter(codingErrorAction);
            return this;
        }
        throw new IllegalArgumentException("Null action");
    }

    public final float averageBytesPerChar() {
        return this.averageBytesPerChar;
    }

    public final float maxBytesPerChar() {
        return this.maxBytesPerChar;
    }

    public final CoderResult encode(CharBuffer charBuffer, ByteBuffer byteBuffer, boolean z) {
        CoderResult encodeLoop;
        CodingErrorAction codingErrorAction;
        int i = z ? 2 : 1;
        int i2 = this.state;
        if (i2 == 0 || i2 == 1 || (z && i2 == 2)) {
            this.state = i;
            while (true) {
                try {
                    encodeLoop = encodeLoop(charBuffer, byteBuffer);
                    if (encodeLoop.isOverflow()) {
                        return encodeLoop;
                    }
                    if (encodeLoop.isUnderflow()) {
                        if (!z || !charBuffer.hasRemaining()) {
                            return encodeLoop;
                        }
                        encodeLoop = CoderResult.malformedForLength(charBuffer.remaining());
                    }
                    if (encodeLoop.isMalformed()) {
                        codingErrorAction = this.malformedInputAction;
                    } else {
                        codingErrorAction = encodeLoop.isUnmappable() ? this.unmappableCharacterAction : null;
                    }
                    if (codingErrorAction == CodingErrorAction.REPORT) {
                        return encodeLoop;
                    }
                    if (codingErrorAction == CodingErrorAction.REPLACE) {
                        int remaining = byteBuffer.remaining();
                        byte[] bArr = this.replacement;
                        if (remaining < bArr.length) {
                            return CoderResult.OVERFLOW;
                        }
                        byteBuffer.put(bArr);
                    }
                    if (codingErrorAction == CodingErrorAction.IGNORE || codingErrorAction == CodingErrorAction.REPLACE) {
                        charBuffer.position(charBuffer.position() + encodeLoop.length());
                    }
                } catch (BufferUnderflowException e) {
                    throw new CoderMalfunctionError(e);
                } catch (BufferOverflowException e2) {
                    throw new CoderMalfunctionError(e2);
                }
            }
            return encodeLoop;
        }
        throwIllegalStateException(this.state, i);
        throw null;
    }

    public final CoderResult flush(ByteBuffer byteBuffer) {
        int i = this.state;
        if (i == 2) {
            CoderResult implFlush = implFlush(byteBuffer);
            if (implFlush.isUnderflow()) {
                this.state = 3;
            }
            return implFlush;
        } else if (i == 3) {
            return CoderResult.UNDERFLOW;
        } else {
            throwIllegalStateException(i, 3);
            throw null;
        }
    }

    /* access modifiers changed from: protected */
    public CoderResult implFlush(ByteBuffer byteBuffer) {
        return CoderResult.UNDERFLOW;
    }

    public final CharsetEncoder reset() {
        implReset();
        this.state = 0;
        return this;
    }

    public final ByteBuffer encode(CharBuffer charBuffer) {
        int remaining = (int) (((float) charBuffer.remaining()) * averageBytesPerChar());
        ByteBuffer allocate = ByteBuffer.allocate(remaining);
        if (remaining == 0 && charBuffer.remaining() == 0) {
            return allocate;
        }
        reset();
        while (true) {
            CoderResult encode = charBuffer.hasRemaining() ? encode(charBuffer, allocate, true) : CoderResult.UNDERFLOW;
            if (encode.isUnderflow()) {
                encode = flush(allocate);
            }
            if (encode.isUnderflow()) {
                allocate.flip();
                return allocate;
            } else if (encode.isOverflow()) {
                remaining = (remaining * 2) + 1;
                ByteBuffer allocate2 = ByteBuffer.allocate(remaining);
                allocate.flip();
                allocate2.put(allocate);
                allocate = allocate2;
            } else {
                encode.throwException();
            }
        }
    }

    private void throwIllegalStateException(int i, int i2) {
        throw new IllegalStateException("Current state = " + stateNames[i] + ", new state = " + stateNames[i2]);
    }
}
