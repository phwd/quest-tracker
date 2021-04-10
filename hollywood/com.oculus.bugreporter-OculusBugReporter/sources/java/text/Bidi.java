package java.text;

public final class Bidi {
    public static final int DIRECTION_DEFAULT_LEFT_TO_RIGHT = -2;
    public static final int DIRECTION_DEFAULT_RIGHT_TO_LEFT = -1;
    public static final int DIRECTION_LEFT_TO_RIGHT = 0;
    public static final int DIRECTION_RIGHT_TO_LEFT = 1;
    private final android.icu.text.Bidi bidiBase;

    private static int translateConstToIcu(int javaInt) {
        if (javaInt == -2) {
            return 126;
        }
        if (javaInt != -1) {
            return (javaInt == 0 || javaInt != 1) ? 0 : 1;
        }
        return 127;
    }

    public Bidi(String paragraph, int flags) {
        if (paragraph != null) {
            this.bidiBase = new android.icu.text.Bidi(paragraph.toCharArray(), 0, null, 0, paragraph.length(), translateConstToIcu(flags));
            return;
        }
        throw new IllegalArgumentException("paragraph is null");
    }

    public Bidi(AttributedCharacterIterator paragraph) {
        if (paragraph != null) {
            this.bidiBase = new android.icu.text.Bidi(paragraph);
            return;
        }
        throw new IllegalArgumentException("paragraph is null");
    }

    public Bidi(char[] text, int textStart, byte[] embeddings, int embStart, int paragraphLength, int flags) {
        if (text == null) {
            throw new IllegalArgumentException("text is null");
        } else if (paragraphLength < 0) {
            throw new IllegalArgumentException("bad length: " + paragraphLength);
        } else if (textStart < 0 || paragraphLength > text.length - textStart) {
            throw new IllegalArgumentException("bad range: " + textStart + " length: " + paragraphLength + " for text of length: " + text.length);
        } else if (embeddings == null || (embStart >= 0 && paragraphLength <= embeddings.length - embStart)) {
            this.bidiBase = new android.icu.text.Bidi(text, textStart, embeddings, embStart, paragraphLength, translateConstToIcu(flags));
        } else {
            throw new IllegalArgumentException("bad range: " + embStart + " length: " + paragraphLength + " for embeddings of length: " + text.length);
        }
    }

    private Bidi(android.icu.text.Bidi bidiBase2) {
        this.bidiBase = bidiBase2;
    }

    public Bidi createLineBidi(int lineStart, int lineLimit) {
        if (lineStart < 0 || lineLimit < 0 || lineStart > lineLimit || lineLimit > getLength()) {
            throw new IllegalArgumentException("Invalid ranges (start=" + lineStart + ", limit=" + lineLimit + ", length=" + getLength() + ")");
        } else if (lineStart == lineLimit) {
            return new Bidi(new android.icu.text.Bidi(new char[0], 0, new byte[0], 0, 0, translateConstToIcu(0)));
        } else {
            return new Bidi(this.bidiBase.createLineBidi(lineStart, lineLimit));
        }
    }

    public boolean isMixed() {
        return this.bidiBase.isMixed();
    }

    public boolean isLeftToRight() {
        return this.bidiBase.isLeftToRight();
    }

    public boolean isRightToLeft() {
        return this.bidiBase.isRightToLeft();
    }

    public int getLength() {
        return this.bidiBase.getLength();
    }

    public boolean baseIsLeftToRight() {
        return this.bidiBase.baseIsLeftToRight();
    }

    public int getBaseLevel() {
        return this.bidiBase.getParaLevel();
    }

    public int getLevelAt(int offset) {
        try {
            return this.bidiBase.getLevelAt(offset);
        } catch (IllegalArgumentException e) {
            return getBaseLevel();
        }
    }

    public int getRunCount() {
        int runCount = this.bidiBase.countRuns();
        if (runCount == 0) {
            return 1;
        }
        return runCount;
    }

    public int getRunLevel(int run) {
        if (run == getRunCount()) {
            return getBaseLevel();
        }
        return this.bidiBase.countRuns() == 0 ? this.bidiBase.getBaseLevel() : this.bidiBase.getRunLevel(run);
    }

    public int getRunStart(int run) {
        if (run == getRunCount()) {
            return getBaseLevel();
        }
        if (this.bidiBase.countRuns() == 0) {
            return 0;
        }
        return this.bidiBase.getRunStart(run);
    }

    public int getRunLimit(int run) {
        if (run == getRunCount()) {
            return getBaseLevel();
        }
        return this.bidiBase.countRuns() == 0 ? this.bidiBase.getLength() : this.bidiBase.getRunLimit(run);
    }

    public static boolean requiresBidi(char[] text, int start, int limit) {
        if (start >= 0 && start <= limit && limit <= text.length) {
            return android.icu.text.Bidi.requiresBidi(text, start, limit);
        }
        throw new IllegalArgumentException("Value start " + start + " is out of range 0 to " + limit);
    }

    public static void reorderVisually(byte[] levels, int levelStart, Object[] objects, int objectStart, int count) {
        if (levelStart < 0 || levels.length <= levelStart) {
            StringBuilder sb = new StringBuilder();
            sb.append("Value levelStart ");
            sb.append(levelStart);
            sb.append(" is out of range 0 to ");
            sb.append(levels.length - 1);
            throw new IllegalArgumentException(sb.toString());
        } else if (objectStart < 0 || objects.length <= objectStart) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Value objectStart ");
            sb2.append(levelStart);
            sb2.append(" is out of range 0 to ");
            sb2.append(objects.length - 1);
            throw new IllegalArgumentException(sb2.toString());
        } else if (count < 0 || objects.length < objectStart + count) {
            throw new IllegalArgumentException("Value count " + levelStart + " is out of range 0 to " + (objects.length - objectStart));
        } else {
            android.icu.text.Bidi.reorderVisually(levels, levelStart, objects, objectStart, count);
        }
    }

    public String toString() {
        return getClass().getName() + "[direction: " + ((int) this.bidiBase.getDirection()) + " baseLevel: " + this.bidiBase.getBaseLevel() + " length: " + this.bidiBase.getLength() + " runs: " + this.bidiBase.getRunCount() + "]";
    }
}
