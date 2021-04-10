package java.nio.charset;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

public abstract class CharsetDecoder {
    private static String[] stateNames = {"RESET", "CODING", "CODING_END", "FLUSHED"};
    private final Charset charset;
    private CodingErrorAction malformedInputAction;
    private final float maxCharsPerByte;
    private String replacement;
    private int state;
    private CodingErrorAction unmappableCharacterAction;

    /* access modifiers changed from: protected */
    public abstract CoderResult decodeLoop(ByteBuffer byteBuffer, CharBuffer charBuffer);

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

    public final CharsetDecoder onMalformedInput(CodingErrorAction codingErrorAction) {
        if (codingErrorAction != null) {
            this.malformedInputAction = codingErrorAction;
            implOnMalformedInput(codingErrorAction);
            return this;
        }
        throw new IllegalArgumentException("Null action");
    }

    public final CharsetDecoder onUnmappableCharacter(CodingErrorAction codingErrorAction) {
        if (codingErrorAction != null) {
            this.unmappableCharacterAction = codingErrorAction;
            implOnUnmappableCharacter(codingErrorAction);
            return this;
        }
        throw new IllegalArgumentException("Null action");
    }

    public final float maxCharsPerByte() {
        return this.maxCharsPerByte;
    }

    public final CoderResult decode(ByteBuffer byteBuffer, CharBuffer charBuffer, boolean z) {
        CoderResult decodeLoop;
        CodingErrorAction codingErrorAction;
        int i = z ? 2 : 1;
        int i2 = this.state;
        if (i2 == 0 || i2 == 1 || (z && i2 == 2)) {
            this.state = i;
            while (true) {
                try {
                    decodeLoop = decodeLoop(byteBuffer, charBuffer);
                    if (decodeLoop.isOverflow()) {
                        return decodeLoop;
                    }
                    if (decodeLoop.isUnderflow()) {
                        if (!z || !byteBuffer.hasRemaining()) {
                            return decodeLoop;
                        }
                        decodeLoop = CoderResult.malformedForLength(byteBuffer.remaining());
                    }
                    if (decodeLoop.isMalformed()) {
                        codingErrorAction = this.malformedInputAction;
                    } else {
                        codingErrorAction = decodeLoop.isUnmappable() ? this.unmappableCharacterAction : null;
                    }
                    if (codingErrorAction == CodingErrorAction.REPORT) {
                        return decodeLoop;
                    }
                    if (codingErrorAction == CodingErrorAction.REPLACE) {
                        if (charBuffer.remaining() < this.replacement.length()) {
                            return CoderResult.OVERFLOW;
                        }
                        charBuffer.put(this.replacement);
                    }
                    if (codingErrorAction == CodingErrorAction.IGNORE || codingErrorAction == CodingErrorAction.REPLACE) {
                        byteBuffer.position(byteBuffer.position() + decodeLoop.length());
                    }
                } catch (BufferUnderflowException e) {
                    throw new CoderMalfunctionError(e);
                } catch (BufferOverflowException e2) {
                    throw new CoderMalfunctionError(e2);
                }
            }
            return decodeLoop;
        }
        throwIllegalStateException(this.state, i);
        throw null;
    }

    public final CoderResult flush(CharBuffer charBuffer) {
        int i = this.state;
        if (i == 2) {
            CoderResult implFlush = implFlush(charBuffer);
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
    public CoderResult implFlush(CharBuffer charBuffer) {
        return CoderResult.UNDERFLOW;
    }

    public final CharsetDecoder reset() {
        implReset();
        this.state = 0;
        return this;
    }

    private void throwIllegalStateException(int i, int i2) {
        throw new IllegalStateException("Current state = " + stateNames[i] + ", new state = " + stateNames[i2]);
    }
}
