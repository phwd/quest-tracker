package java.text;

import java.text.PatternEntry;
import java.util.ArrayList;

final class MergeCollation {
    private final byte BITARRAYMASK = 1;
    private final int BYTEMASK = 7;
    private final int BYTEPOWER = 3;
    private transient StringBuffer excess = new StringBuffer();
    private transient PatternEntry lastEntry = null;
    ArrayList<PatternEntry> patterns = new ArrayList<>();
    private transient PatternEntry saveEntry = null;
    private transient byte[] statusArray = new byte[8192];

    public MergeCollation(String pattern) throws ParseException {
        int i = 0;
        while (true) {
            byte[] bArr = this.statusArray;
            if (i < bArr.length) {
                bArr[i] = 0;
                i++;
            } else {
                setPattern(pattern);
                return;
            }
        }
    }

    public String getPattern() {
        return getPattern(true);
    }

    public String getPattern(boolean withWhiteSpace) {
        StringBuffer result = new StringBuffer();
        ArrayList<PatternEntry> extList = null;
        int i = 0;
        while (i < this.patterns.size()) {
            PatternEntry entry = this.patterns.get(i);
            if (entry.extension.length() != 0) {
                if (extList == null) {
                    extList = new ArrayList<>();
                }
                extList.add(entry);
            } else {
                if (extList != null) {
                    PatternEntry last = findLastWithNoExtension(i - 1);
                    for (int j = extList.size() - 1; j >= 0; j--) {
                        extList.get(j).addToBuffer(result, false, withWhiteSpace, last);
                    }
                    extList = null;
                }
                entry.addToBuffer(result, false, withWhiteSpace, null);
            }
            i++;
        }
        if (extList != null) {
            PatternEntry last2 = findLastWithNoExtension(i - 1);
            for (int j2 = extList.size() - 1; j2 >= 0; j2--) {
                extList.get(j2).addToBuffer(result, false, withWhiteSpace, last2);
            }
        }
        return result.toString();
    }

    private final PatternEntry findLastWithNoExtension(int i) {
        PatternEntry entry;
        do {
            i--;
            if (i < 0) {
                return null;
            }
            entry = this.patterns.get(i);
        } while (entry.extension.length() != 0);
        return entry;
    }

    public String emitPattern() {
        return emitPattern(true);
    }

    public String emitPattern(boolean withWhiteSpace) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < this.patterns.size(); i++) {
            PatternEntry entry = this.patterns.get(i);
            if (entry != null) {
                entry.addToBuffer(result, true, withWhiteSpace, null);
            }
        }
        return result.toString();
    }

    public void setPattern(String pattern) throws ParseException {
        this.patterns.clear();
        addPattern(pattern);
    }

    public void addPattern(String pattern) throws ParseException {
        if (pattern != null) {
            PatternEntry.Parser parser = new PatternEntry.Parser(pattern);
            for (PatternEntry entry = parser.next(); entry != null; entry = parser.next()) {
                fixEntry(entry);
            }
        }
    }

    public int getCount() {
        return this.patterns.size();
    }

    public PatternEntry getItemAt(int index) {
        return this.patterns.get(index);
    }

    private final void fixEntry(PatternEntry newEntry) throws ParseException {
        if (this.lastEntry == null || !newEntry.chars.equals(this.lastEntry.chars) || !newEntry.extension.equals(this.lastEntry.extension)) {
            boolean changeLastEntry = true;
            if (newEntry.strength != -2) {
                int oldIndex = -1;
                if (newEntry.chars.length() == 1) {
                    char c = newEntry.chars.charAt(0);
                    int statusIndex = c >> 3;
                    byte bitClump = this.statusArray[statusIndex];
                    byte setBit = (byte) (1 << (c & 7));
                    if (bitClump == 0 || (bitClump & setBit) == 0) {
                        this.statusArray[statusIndex] = (byte) (bitClump | setBit);
                    } else {
                        oldIndex = this.patterns.lastIndexOf(newEntry);
                    }
                } else {
                    oldIndex = this.patterns.lastIndexOf(newEntry);
                }
                if (oldIndex != -1) {
                    this.patterns.remove(oldIndex);
                }
                this.excess.setLength(0);
                int lastIndex = findLastEntry(this.lastEntry, this.excess);
                if (this.excess.length() != 0) {
                    newEntry.extension = ((Object) this.excess) + newEntry.extension;
                    if (lastIndex != this.patterns.size()) {
                        this.lastEntry = this.saveEntry;
                        changeLastEntry = false;
                    }
                }
                if (lastIndex == this.patterns.size()) {
                    this.patterns.add(newEntry);
                    this.saveEntry = newEntry;
                } else {
                    this.patterns.add(lastIndex, newEntry);
                }
            }
            if (changeLastEntry) {
                this.lastEntry = newEntry;
            }
        } else if (newEntry.strength != 3 && newEntry.strength != -2) {
            throw new ParseException("The entries " + ((Object) this.lastEntry) + " and " + ((Object) newEntry) + " are adjacent in the rules, but have conflicting strengths: A character can't be unequal to itself.", -1);
        }
    }

    private final int findLastEntry(PatternEntry entry, StringBuffer excessChars) throws ParseException {
        if (entry == null) {
            return 0;
        }
        if (entry.strength != -2) {
            int oldIndex = -1;
            if (entry.chars.length() == 1) {
                if (((1 << (entry.chars.charAt(0) & 7)) & this.statusArray[entry.chars.charAt(0) >> 3]) != 0) {
                    oldIndex = this.patterns.lastIndexOf(entry);
                }
            } else {
                oldIndex = this.patterns.lastIndexOf(entry);
            }
            if (oldIndex != -1) {
                return oldIndex + 1;
            }
            throw new ParseException("couldn't find last entry: " + ((Object) entry), oldIndex);
        }
        int i = this.patterns.size() - 1;
        while (true) {
            if (i < 0) {
                break;
            }
            PatternEntry e = this.patterns.get(i);
            if (e.chars.regionMatches(0, entry.chars, 0, e.chars.length())) {
                excessChars.append(entry.chars.substring(e.chars.length(), entry.chars.length()));
                break;
            }
            i--;
        }
        if (i != -1) {
            return i + 1;
        }
        throw new ParseException("couldn't find: " + ((Object) entry), i);
    }
}
