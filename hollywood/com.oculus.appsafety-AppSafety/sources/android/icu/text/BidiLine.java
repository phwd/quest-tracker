package android.icu.text;

import android.icu.text.Bidi;
import java.util.Arrays;

final class BidiLine {
    BidiLine() {
    }

    static void setTrailingWSStart(Bidi bidi) {
        byte[] dirProps = bidi.dirProps;
        byte[] levels = bidi.levels;
        int start = bidi.length;
        byte paraLevel = bidi.paraLevel;
        if (dirProps[start - 1] == 7) {
            bidi.trailingWSStart = start;
            return;
        }
        while (start > 0 && (Bidi.DirPropFlag(dirProps[start - 1]) & Bidi.MASK_WS) != 0) {
            start--;
        }
        while (start > 0 && levels[start - 1] == paraLevel) {
            start--;
        }
        bidi.trailingWSStart = start;
    }

    static Bidi setLine(Bidi paraBidi, int start, int limit) {
        Bidi lineBidi = new Bidi();
        int length = limit - start;
        lineBidi.resultLength = length;
        lineBidi.originalLength = length;
        lineBidi.length = length;
        lineBidi.text = new char[length];
        System.arraycopy((Object) paraBidi.text, start, (Object) lineBidi.text, 0, length);
        lineBidi.paraLevel = paraBidi.GetParaLevelAt(start);
        lineBidi.paraCount = paraBidi.paraCount;
        lineBidi.runs = new BidiRun[0];
        lineBidi.reorderingMode = paraBidi.reorderingMode;
        lineBidi.reorderingOptions = paraBidi.reorderingOptions;
        if (paraBidi.controlCount > 0) {
            for (int j = start; j < limit; j++) {
                if (Bidi.IsBidiControlChar(paraBidi.text[j])) {
                    lineBidi.controlCount++;
                }
            }
            lineBidi.resultLength -= lineBidi.controlCount;
        }
        lineBidi.getDirPropsMemory(length);
        lineBidi.dirProps = lineBidi.dirPropsMemory;
        System.arraycopy(paraBidi.dirProps, start, lineBidi.dirProps, 0, length);
        lineBidi.getLevelsMemory(length);
        lineBidi.levels = lineBidi.levelsMemory;
        System.arraycopy(paraBidi.levels, start, lineBidi.levels, 0, length);
        lineBidi.runCount = -1;
        if (paraBidi.direction != 2) {
            lineBidi.direction = paraBidi.direction;
            if (paraBidi.trailingWSStart <= start) {
                lineBidi.trailingWSStart = 0;
            } else if (paraBidi.trailingWSStart < limit) {
                lineBidi.trailingWSStart = paraBidi.trailingWSStart - start;
            } else {
                lineBidi.trailingWSStart = length;
            }
        } else {
            byte[] levels = lineBidi.levels;
            setTrailingWSStart(lineBidi);
            int trailingWSStart = lineBidi.trailingWSStart;
            if (trailingWSStart == 0) {
                lineBidi.direction = (byte) (lineBidi.paraLevel & 1);
            } else {
                byte level = (byte) (levels[0] & 1);
                if (trailingWSStart >= length || (lineBidi.paraLevel & 1) == level) {
                    int i = 1;
                    while (true) {
                        if (i == trailingWSStart) {
                            lineBidi.direction = level;
                            break;
                        } else if ((levels[i] & 1) != level) {
                            lineBidi.direction = 2;
                            break;
                        } else {
                            i++;
                        }
                    }
                } else {
                    lineBidi.direction = 2;
                }
            }
            byte b = lineBidi.direction;
            if (b == 0) {
                lineBidi.paraLevel = (byte) ((lineBidi.paraLevel + 1) & -2);
                lineBidi.trailingWSStart = 0;
            } else if (b == 1) {
                lineBidi.paraLevel = (byte) (1 | lineBidi.paraLevel);
                lineBidi.trailingWSStart = 0;
            }
        }
        lineBidi.paraBidi = paraBidi;
        return lineBidi;
    }

    static byte getLevelAt(Bidi bidi, int charIndex) {
        if (bidi.direction != 2 || charIndex >= bidi.trailingWSStart) {
            return bidi.GetParaLevelAt(charIndex);
        }
        return bidi.levels[charIndex];
    }

    static byte[] getLevels(Bidi bidi) {
        int start = bidi.trailingWSStart;
        int length = bidi.length;
        if (start != length) {
            Arrays.fill(bidi.levels, start, length, bidi.paraLevel);
            bidi.trailingWSStart = length;
        }
        if (length >= bidi.levels.length) {
            return bidi.levels;
        }
        byte[] levels = new byte[length];
        System.arraycopy(bidi.levels, 0, levels, 0, length);
        return levels;
    }

    static BidiRun getLogicalRun(Bidi bidi, int logicalPosition) {
        BidiRun newRun = new BidiRun();
        getRuns(bidi);
        int runCount = bidi.runCount;
        int visualStart = 0;
        int logicalLimit = 0;
        BidiRun iRun = bidi.runs[0];
        for (int i = 0; i < runCount; i++) {
            iRun = bidi.runs[i];
            logicalLimit = (iRun.start + iRun.limit) - visualStart;
            if (logicalPosition >= iRun.start && logicalPosition < logicalLimit) {
                break;
            }
            visualStart = iRun.limit;
        }
        newRun.start = iRun.start;
        newRun.limit = logicalLimit;
        newRun.level = iRun.level;
        return newRun;
    }

    static BidiRun getVisualRun(Bidi bidi, int runIndex) {
        int limit;
        int start = bidi.runs[runIndex].start;
        byte level = bidi.runs[runIndex].level;
        if (runIndex > 0) {
            limit = (bidi.runs[runIndex].limit + start) - bidi.runs[runIndex - 1].limit;
        } else {
            limit = bidi.runs[0].limit + start;
        }
        return new BidiRun(start, limit, level);
    }

    static void getSingleRun(Bidi bidi, byte level) {
        bidi.runs = bidi.simpleRuns;
        bidi.runCount = 1;
        bidi.runs[0] = new BidiRun(0, bidi.length, level);
    }

    private static void reorderLine(Bidi bidi, byte minLevel, byte maxLevel) {
        if (maxLevel > (minLevel | 1)) {
            byte minLevel2 = (byte) (minLevel + 1);
            BidiRun[] runs = bidi.runs;
            byte[] levels = bidi.levels;
            int runCount = bidi.runCount;
            if (bidi.trailingWSStart < bidi.length) {
                runCount--;
            }
            while (true) {
                maxLevel = (byte) (maxLevel - 1);
                if (maxLevel < minLevel2) {
                    break;
                }
                int firstRun = 0;
                while (true) {
                    if (firstRun < runCount && levels[runs[firstRun].start] < maxLevel) {
                        firstRun++;
                    } else if (firstRun >= runCount) {
                        break;
                    } else {
                        int limitRun = firstRun;
                        do {
                            limitRun++;
                            if (limitRun >= runCount) {
                                break;
                            }
                        } while (levels[runs[limitRun].start] >= maxLevel);
                        for (int endRun = limitRun - 1; firstRun < endRun; endRun--) {
                            BidiRun tempRun = runs[firstRun];
                            runs[firstRun] = runs[endRun];
                            runs[endRun] = tempRun;
                            firstRun++;
                        }
                        if (limitRun == runCount) {
                            break;
                        }
                        firstRun = limitRun + 1;
                    }
                }
            }
            if ((minLevel2 & 1) == 0) {
                int firstRun2 = 0;
                if (bidi.trailingWSStart == bidi.length) {
                    runCount--;
                }
                while (firstRun2 < runCount) {
                    BidiRun tempRun2 = runs[firstRun2];
                    runs[firstRun2] = runs[runCount];
                    runs[runCount] = tempRun2;
                    firstRun2++;
                    runCount--;
                }
            }
        }
    }

    static int getRunFromLogicalIndex(Bidi bidi, int logicalIndex) {
        BidiRun[] runs = bidi.runs;
        int runCount = bidi.runCount;
        int visualStart = 0;
        for (int i = 0; i < runCount; i++) {
            int length = runs[i].limit - visualStart;
            int logicalStart = runs[i].start;
            if (logicalIndex >= logicalStart && logicalIndex < logicalStart + length) {
                return i;
            }
            visualStart += length;
        }
        throw new IllegalStateException("Internal ICU error in getRunFromLogicalIndex");
    }

    static void getRuns(Bidi bidi) {
        if (bidi.runCount < 0) {
            if (bidi.direction != 2) {
                getSingleRun(bidi, bidi.paraLevel);
            } else {
                int length = bidi.length;
                byte[] levels = bidi.levels;
                byte level = -1;
                int limit = bidi.trailingWSStart;
                int runCount = 0;
                for (int i = 0; i < limit; i++) {
                    if (levels[i] != level) {
                        runCount++;
                        level = levels[i];
                    }
                }
                if (runCount == 1 && limit == length) {
                    getSingleRun(bidi, levels[0]);
                } else {
                    byte minLevel = 126;
                    byte maxLevel = 0;
                    if (limit < length) {
                        runCount++;
                    }
                    bidi.getRunsMemory(runCount);
                    BidiRun[] runs = bidi.runsMemory;
                    int runIndex = 0;
                    int i2 = 0;
                    while (true) {
                        byte level2 = levels[i2];
                        if (level2 < minLevel) {
                            minLevel = level2;
                        }
                        if (level2 > maxLevel) {
                            maxLevel = level2;
                        }
                        do {
                            i2++;
                            if (i2 >= limit) {
                                break;
                            }
                        } while (levels[i2] == level2);
                        runs[runIndex] = new BidiRun(i2, i2 - i2, level2);
                        runIndex++;
                        if (i2 >= limit) {
                            break;
                        }
                    }
                    if (limit < length) {
                        runs[runIndex] = new BidiRun(limit, length - limit, bidi.paraLevel);
                        if (bidi.paraLevel < minLevel) {
                            minLevel = bidi.paraLevel;
                        }
                    }
                    bidi.runs = runs;
                    bidi.runCount = runCount;
                    reorderLine(bidi, minLevel, maxLevel);
                    int limit2 = 0;
                    for (int i3 = 0; i3 < runCount; i3++) {
                        runs[i3].level = levels[runs[i3].start];
                        BidiRun bidiRun = runs[i3];
                        int i4 = bidiRun.limit + limit2;
                        bidiRun.limit = i4;
                        limit2 = i4;
                    }
                    if (runIndex < runCount) {
                        runs[(bidi.paraLevel & 1) != 0 ? 0 : runIndex].level = bidi.paraLevel;
                    }
                }
            }
            if (bidi.insertPoints.size > 0) {
                for (int ip = 0; ip < bidi.insertPoints.size; ip++) {
                    Bidi.Point point = bidi.insertPoints.points[ip];
                    bidi.runs[getRunFromLogicalIndex(bidi, point.pos)].insertRemove |= point.flag;
                }
            }
            if (bidi.controlCount > 0) {
                for (int ic = 0; ic < bidi.length; ic++) {
                    if (Bidi.IsBidiControlChar(bidi.text[ic])) {
                        bidi.runs[getRunFromLogicalIndex(bidi, ic)].insertRemove--;
                    }
                }
            }
        }
    }

    static int[] prepareReorder(byte[] levels, byte[] pMinLevel, byte[] pMaxLevel) {
        if (levels == null || levels.length <= 0) {
            return null;
        }
        byte minLevel = 126;
        byte maxLevel = 0;
        int start = levels.length;
        while (start > 0) {
            start--;
            byte level = levels[start];
            if (level < 0 || level > 126) {
                return null;
            }
            if (level < minLevel) {
                minLevel = level;
            }
            if (level > maxLevel) {
                maxLevel = level;
            }
        }
        pMinLevel[0] = minLevel;
        pMaxLevel[0] = maxLevel;
        int[] indexMap = new int[levels.length];
        int start2 = levels.length;
        while (start2 > 0) {
            start2--;
            indexMap[start2] = start2;
        }
        return indexMap;
    }

    static int[] reorderLogical(byte[] levels) {
        byte[] aMinLevel = new byte[1];
        byte[] aMaxLevel = new byte[1];
        int[] indexMap = prepareReorder(levels, aMinLevel, aMaxLevel);
        if (indexMap == null) {
            return null;
        }
        byte minLevel = aMinLevel[0];
        byte maxLevel = aMaxLevel[0];
        if (minLevel == maxLevel && (minLevel & 1) == 0) {
            return indexMap;
        }
        byte minLevel2 = (byte) (minLevel | 1);
        do {
            int start = 0;
            while (true) {
                if (start < levels.length && levels[start] < maxLevel) {
                    start++;
                } else if (start >= levels.length) {
                    break;
                } else {
                    int limit = start;
                    do {
                        limit++;
                        if (limit >= levels.length) {
                            break;
                        }
                    } while (levels[limit] >= maxLevel);
                    int sumOfSosEos = (start + limit) - 1;
                    do {
                        indexMap[start] = sumOfSosEos - indexMap[start];
                        start++;
                    } while (start < limit);
                    if (limit == levels.length) {
                        break;
                    }
                    start = limit + 1;
                }
            }
            maxLevel = (byte) (maxLevel - 1);
        } while (maxLevel >= minLevel2);
        return indexMap;
    }

    static int[] reorderVisual(byte[] levels) {
        byte[] aMinLevel = new byte[1];
        byte[] aMaxLevel = new byte[1];
        int[] indexMap = prepareReorder(levels, aMinLevel, aMaxLevel);
        if (indexMap == null) {
            return null;
        }
        byte minLevel = aMinLevel[0];
        byte maxLevel = aMaxLevel[0];
        if (minLevel == maxLevel && (minLevel & 1) == 0) {
            return indexMap;
        }
        byte minLevel2 = (byte) (minLevel | 1);
        do {
            int start = 0;
            while (true) {
                if (start < levels.length && levels[start] < maxLevel) {
                    start++;
                } else if (start >= levels.length) {
                    break;
                } else {
                    int limit = start;
                    do {
                        limit++;
                        if (limit >= levels.length) {
                            break;
                        }
                    } while (levels[limit] >= maxLevel);
                    for (int end = limit - 1; start < end; end--) {
                        int temp = indexMap[start];
                        indexMap[start] = indexMap[end];
                        indexMap[end] = temp;
                        start++;
                    }
                    if (limit == levels.length) {
                        break;
                    }
                    start = limit + 1;
                }
            }
            maxLevel = (byte) (maxLevel - 1);
        } while (maxLevel >= minLevel2);
        return indexMap;
    }

    static int getVisualIndex(Bidi bidi, int logicalIndex) {
        int length;
        int insertRemove;
        int limit;
        int start;
        int visualIndex = -1;
        byte b = bidi.direction;
        if (b == 0) {
            visualIndex = logicalIndex;
        } else if (b != 1) {
            getRuns(bidi);
            BidiRun[] runs = bidi.runs;
            int visualStart = 0;
            int i = 0;
            while (true) {
                if (i >= bidi.runCount) {
                    break;
                }
                int length2 = runs[i].limit - visualStart;
                int offset = logicalIndex - runs[i].start;
                if (offset < 0 || offset >= length2) {
                    visualStart += length2;
                    i++;
                } else if (runs[i].isEvenRun()) {
                    visualIndex = visualStart + offset;
                } else {
                    visualIndex = ((visualStart + length2) - offset) - 1;
                }
            }
            if (i >= bidi.runCount) {
                return -1;
            }
        } else {
            visualIndex = (bidi.length - logicalIndex) - 1;
        }
        if (bidi.insertPoints.size > 0) {
            BidiRun[] runs2 = bidi.runs;
            int visualStart2 = 0;
            int markFound = 0;
            int i2 = 0;
            while (true) {
                int length3 = runs2[i2].limit - visualStart2;
                int insertRemove2 = runs2[i2].insertRemove;
                if ((insertRemove2 & 5) > 0) {
                    markFound++;
                }
                if (visualIndex < runs2[i2].limit) {
                    return visualIndex + markFound;
                }
                if ((insertRemove2 & 10) > 0) {
                    markFound++;
                }
                i2++;
                visualStart2 += length3;
            }
        } else if (bidi.controlCount <= 0) {
            return visualIndex;
        } else {
            BidiRun[] runs3 = bidi.runs;
            int visualStart3 = 0;
            int controlFound = 0;
            if (Bidi.IsBidiControlChar(bidi.text[logicalIndex])) {
                return -1;
            }
            int i3 = 0;
            while (true) {
                length = runs3[i3].limit - visualStart3;
                insertRemove = runs3[i3].insertRemove;
                if (visualIndex < runs3[i3].limit) {
                    break;
                }
                controlFound -= insertRemove;
                i3++;
                visualStart3 += length;
            }
            if (insertRemove == 0) {
                return visualIndex - controlFound;
            }
            if (runs3[i3].isEvenRun()) {
                start = runs3[i3].start;
                limit = logicalIndex;
            } else {
                start = logicalIndex + 1;
                limit = runs3[i3].start + length;
            }
            for (int j = start; j < limit; j++) {
                if (Bidi.IsBidiControlChar(bidi.text[j])) {
                    controlFound++;
                }
            }
            return visualIndex - controlFound;
        }
    }

    static int getLogicalIndex(Bidi bidi, int visualIndex) {
        int i;
        int i2;
        int length;
        int insertRemove;
        Bidi bidi2 = bidi;
        int visualIndex2 = visualIndex;
        BidiRun[] runs = bidi2.runs;
        int runCount = bidi2.runCount;
        if (bidi2.insertPoints.size > 0) {
            int markFound = 0;
            int visualStart = 0;
            int i3 = 0;
            while (true) {
                int length2 = runs[i3].limit - visualStart;
                int insertRemove2 = runs[i3].insertRemove;
                if ((insertRemove2 & 5) > 0) {
                    if (visualIndex2 <= visualStart + markFound) {
                        return -1;
                    }
                    markFound++;
                }
                if (visualIndex2 < runs[i3].limit + markFound) {
                    visualIndex2 -= markFound;
                    break;
                }
                if ((insertRemove2 & 10) > 0) {
                    if (visualIndex2 == visualStart + length2 + markFound) {
                        return -1;
                    }
                    markFound++;
                }
                i3++;
                visualStart += length2;
            }
        } else if (bidi2.controlCount > 0) {
            int controlFound = 0;
            int visualStart2 = 0;
            int i4 = 0;
            while (true) {
                length = runs[i4].limit - visualStart2;
                insertRemove = runs[i4].insertRemove;
                if (visualIndex2 < (runs[i4].limit - controlFound) + insertRemove) {
                    break;
                }
                controlFound -= insertRemove;
                i4++;
                visualStart2 += length;
            }
            if (insertRemove == 0) {
                visualIndex2 += controlFound;
            } else {
                int logicalStart = runs[i4].start;
                boolean evenRun = runs[i4].isEvenRun();
                int logicalEnd = (logicalStart + length) - 1;
                int j = 0;
                while (j < length) {
                    if (Bidi.IsBidiControlChar(bidi2.text[evenRun ? logicalStart + j : logicalEnd - j])) {
                        controlFound++;
                    }
                    if (visualIndex2 + controlFound == visualStart2 + j) {
                        break;
                    }
                    j++;
                    bidi2 = bidi;
                }
                visualIndex2 += controlFound;
            }
        }
        if (runCount <= 10) {
            i = 0;
            while (visualIndex2 >= runs[i].limit) {
                i++;
            }
        } else {
            int begin = 0;
            int limit = runCount;
            while (true) {
                i2 = (begin + limit) >>> 1;
                if (visualIndex2 >= runs[i2].limit) {
                    begin = i2 + 1;
                } else if (i2 == 0 || visualIndex2 >= runs[i2 - 1].limit) {
                    i = i2;
                } else {
                    limit = i2;
                }
            }
            i = i2;
        }
        int start = runs[i].start;
        if (!runs[i].isEvenRun()) {
            return ((runs[i].limit + start) - visualIndex2) - 1;
        }
        if (i > 0) {
            visualIndex2 -= runs[i - 1].limit;
        }
        return start + visualIndex2;
    }

    static int[] getLogicalMap(Bidi bidi) {
        int visualStart;
        int visualStart2;
        BidiRun[] runs = bidi.runs;
        int[] indexMap = new int[bidi.length];
        if (bidi.length > bidi.resultLength) {
            Arrays.fill(indexMap, -1);
        }
        int visualStart3 = 0;
        for (int j = 0; j < bidi.runCount; j++) {
            int logicalStart = runs[j].start;
            int visualLimit = runs[j].limit;
            if (runs[j].isEvenRun()) {
                do {
                    visualStart2 = visualStart3 + 1;
                    indexMap[logicalStart] = visualStart3;
                    logicalStart++;
                    visualStart3 = visualStart2;
                } while (visualStart2 < visualLimit);
            } else {
                int logicalStart2 = logicalStart + (visualLimit - visualStart3);
                do {
                    logicalStart2--;
                    visualStart = visualStart3 + 1;
                    indexMap[logicalStart2] = visualStart3;
                    visualStart3 = visualStart;
                } while (visualStart < visualLimit);
            }
        }
        if (bidi.insertPoints.size > 0) {
            int markFound = 0;
            int runCount = bidi.runCount;
            BidiRun[] runs2 = bidi.runs;
            int visualStart4 = 0;
            int i = 0;
            while (i < runCount) {
                int length = runs2[i].limit - visualStart4;
                int insertRemove = runs2[i].insertRemove;
                if ((insertRemove & 5) > 0) {
                    markFound++;
                }
                if (markFound > 0) {
                    int logicalStart3 = runs2[i].start;
                    int logicalLimit = logicalStart3 + length;
                    for (int j2 = logicalStart3; j2 < logicalLimit; j2++) {
                        indexMap[j2] = indexMap[j2] + markFound;
                    }
                }
                if ((insertRemove & 10) > 0) {
                    markFound++;
                }
                i++;
                visualStart4 += length;
            }
        } else if (bidi.controlCount > 0) {
            int controlFound = 0;
            int runCount2 = bidi.runCount;
            BidiRun[] runs3 = bidi.runs;
            int visualStart5 = 0;
            int i2 = 0;
            while (i2 < runCount2) {
                int length2 = runs3[i2].limit - visualStart5;
                int insertRemove2 = runs3[i2].insertRemove;
                if (controlFound - insertRemove2 != 0) {
                    int logicalStart4 = runs3[i2].start;
                    boolean evenRun = runs3[i2].isEvenRun();
                    int logicalLimit2 = logicalStart4 + length2;
                    if (insertRemove2 == 0) {
                        for (int j3 = logicalStart4; j3 < logicalLimit2; j3++) {
                            indexMap[j3] = indexMap[j3] - controlFound;
                        }
                    } else {
                        for (int j4 = 0; j4 < length2; j4++) {
                            int k = evenRun ? logicalStart4 + j4 : (logicalLimit2 - j4) - 1;
                            if (Bidi.IsBidiControlChar(bidi.text[k])) {
                                controlFound++;
                                indexMap[k] = -1;
                            } else {
                                indexMap[k] = indexMap[k] - controlFound;
                            }
                        }
                    }
                }
                i2++;
                visualStart5 += length2;
            }
        }
        return indexMap;
    }

    static int[] getVisualMap(Bidi bidi) {
        int allocLength;
        BidiRun[] runs = bidi.runs;
        if (bidi.length > bidi.resultLength) {
            allocLength = bidi.length;
        } else {
            allocLength = bidi.resultLength;
        }
        int[] indexMap = new int[allocLength];
        int visualStart = 0;
        int idx = 0;
        for (int j = 0; j < bidi.runCount; j++) {
            int logicalStart = runs[j].start;
            int visualLimit = runs[j].limit;
            if (runs[j].isEvenRun()) {
                do {
                    indexMap[idx] = logicalStart;
                    visualStart++;
                    idx++;
                    logicalStart++;
                } while (visualStart < visualLimit);
            } else {
                int logicalStart2 = logicalStart + (visualLimit - visualStart);
                do {
                    logicalStart2--;
                    indexMap[idx] = logicalStart2;
                    visualStart++;
                    idx++;
                } while (visualStart < visualLimit);
            }
        }
        if (bidi.insertPoints.size > 0) {
            int markFound = 0;
            int runCount = bidi.runCount;
            BidiRun[] runs2 = bidi.runs;
            for (int i = 0; i < runCount; i++) {
                int insertRemove = runs2[i].insertRemove;
                if ((insertRemove & 5) > 0) {
                    markFound++;
                }
                if ((insertRemove & 10) > 0) {
                    markFound++;
                }
            }
            int k = bidi.resultLength;
            int i2 = runCount - 1;
            while (i2 >= 0 && markFound > 0) {
                int insertRemove2 = runs2[i2].insertRemove;
                if ((insertRemove2 & 10) > 0) {
                    k--;
                    indexMap[k] = -1;
                    markFound--;
                }
                int visualStart2 = i2 > 0 ? runs2[i2 - 1].limit : 0;
                for (int j2 = runs2[i2].limit - 1; j2 >= visualStart2 && markFound > 0; j2--) {
                    k--;
                    indexMap[k] = indexMap[j2];
                }
                if ((insertRemove2 & 5) > 0) {
                    k--;
                    indexMap[k] = -1;
                    markFound--;
                }
                i2--;
            }
        } else if (bidi.controlCount > 0) {
            int runCount2 = bidi.runCount;
            BidiRun[] runs3 = bidi.runs;
            int visualStart3 = 0;
            int k2 = 0;
            int i3 = 0;
            while (i3 < runCount2) {
                int length = runs3[i3].limit - visualStart3;
                int insertRemove3 = runs3[i3].insertRemove;
                if (insertRemove3 == 0 && k2 == visualStart3) {
                    k2 += length;
                } else if (insertRemove3 == 0) {
                    int visualLimit2 = runs3[i3].limit;
                    int j3 = visualStart3;
                    while (j3 < visualLimit2) {
                        indexMap[k2] = indexMap[j3];
                        j3++;
                        k2++;
                    }
                } else {
                    int logicalStart3 = runs3[i3].start;
                    boolean evenRun = runs3[i3].isEvenRun();
                    int logicalEnd = (logicalStart3 + length) - 1;
                    for (int j4 = 0; j4 < length; j4++) {
                        int m = evenRun ? logicalStart3 + j4 : logicalEnd - j4;
                        if (!Bidi.IsBidiControlChar(bidi.text[m])) {
                            indexMap[k2] = m;
                            k2++;
                        }
                    }
                }
                i3++;
                visualStart3 += length;
            }
        }
        if (allocLength == bidi.resultLength) {
            return indexMap;
        }
        int[] newMap = new int[bidi.resultLength];
        System.arraycopy((Object) indexMap, 0, (Object) newMap, 0, bidi.resultLength);
        return newMap;
    }

    static int[] invertMap(int[] srcMap) {
        int srcLength = srcMap.length;
        int destLength = -1;
        int count = 0;
        for (int srcEntry : srcMap) {
            if (srcEntry > destLength) {
                destLength = srcEntry;
            }
            if (srcEntry >= 0) {
                count++;
            }
        }
        int destLength2 = destLength + 1;
        int[] destMap = new int[destLength2];
        if (count < destLength2) {
            Arrays.fill(destMap, -1);
        }
        for (int i = 0; i < srcLength; i++) {
            int srcEntry2 = srcMap[i];
            if (srcEntry2 >= 0) {
                destMap[srcEntry2] = i;
            }
        }
        return destMap;
    }
}
