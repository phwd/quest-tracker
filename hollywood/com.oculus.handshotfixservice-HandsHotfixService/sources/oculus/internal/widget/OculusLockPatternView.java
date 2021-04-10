package oculus.internal.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.DisplayListCanvas;
import com.android.internal.widget.LockPatternUtils;
import com.android.internal.widget.LockPatternView;
import com.oculus.os.platform.internal.R;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class OculusLockPatternView extends LockPatternView {
    private static final int GAP_COL = 2;
    private static final int NUM_COLS = 5;
    private static final int NUM_DOTS = 8;
    private static final int NUM_ROWS = 3;
    private static final float OUTER_COL_OFFSET = 0.4f;
    private static final String TAG = "OculusLockPatternView";
    private static final Map<Pair<Integer, Integer>, Pair<Integer, Integer>> gridToOvalMap = new HashMap();
    private static final Map<Pair<Integer, Integer>, Pair<Integer, Integer>> ovalToGridMap = new HashMap();
    private final Rect mPatternBounds;
    private int mUnselectedColor;

    static {
        ovalToGridMap.put(new Pair<>(1, 4), new Pair<>(0, 0));
        ovalToGridMap.put(new Pair<>(0, 3), new Pair<>(0, 1));
        ovalToGridMap.put(new Pair<>(0, 2), new Pair<>(0, 2));
        ovalToGridMap.put(new Pair<>(0, 1), new Pair<>(1, 0));
        ovalToGridMap.put(new Pair<>(1, 0), new Pair<>(1, 1));
        ovalToGridMap.put(new Pair<>(2, 1), new Pair<>(1, 2));
        ovalToGridMap.put(new Pair<>(2, 2), new Pair<>(2, 0));
        ovalToGridMap.put(new Pair<>(2, 3), new Pair<>(2, 1));
        for (Pair<Integer, Integer> loc : ovalToGridMap.keySet()) {
            gridToOvalMap.put(ovalToGridMap.get(loc), loc);
        }
    }

    public static final class Cell extends LockPatternView.Cell {
        private static final LockPatternView.Cell[][] sCells = createCells();

        private static LockPatternView.Cell[][] createCells() {
            LockPatternView.Cell[][] res = (Cell[][]) Array.newInstance(Cell.class, 3, 5);
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 5; j++) {
                    res[i][j] = new Cell(i, j);
                }
            }
            return res;
        }

        private Cell(int row, int column) {
            checkRange(row, column);
            this.row = row;
            this.column = column;
        }

        public static LockPatternView.Cell of(int row, int column) {
            checkRange(row, column);
            return sCells[row][column];
        }

        private static void checkRange(int row, int col) {
            if (row < 0 || row > 3) {
                throw new IllegalArgumentException("row must be in range 0-3");
            } else if (col < 0 || col > 5) {
                throw new IllegalArgumentException("column must be in range 0-5");
            }
        }
    }

    public OculusLockPatternView(Context context) {
        this(context, null);
    }

    public OculusLockPatternView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mPatternBounds = new Rect();
        setFadePattern(false);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.OculusLockPatternView);
        this.mUnselectedColor = context.getColor(17170862);
        this.mUnselectedColor = a.getColor(0, this.mUnselectedColor);
        this.mCellStates = (LockPatternView.CellState[][]) Array.newInstance(LockPatternView.CellState.class, 3, 5);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                this.mCellStates[i][j] = new LockPatternView.CellState();
                this.mCellStates[i][j].radius = (float) (this.mDotSize / 2);
                this.mCellStates[i][j].row = i;
                this.mCellStates[i][j].col = j;
            }
        }
        this.mPatternDrawLookup = (boolean[][]) Array.newInstance(boolean.class, 3, 5);
        this.mPattern = new ArrayList(8);
    }

    /* access modifiers changed from: protected */
    public void clearPatternDrawLookup() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                this.mPatternDrawLookup[i][j] = false;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        OculusLockPatternView.super.onSizeChanged(w, h, oldw, oldh);
        this.mSquareWidth = ((float) ((w - this.mPaddingLeft) - this.mPaddingRight)) / 5.0f;
        this.mSquareHeight = ((float) ((h - this.mPaddingTop) - this.mPaddingBottom)) / 3.0f;
        this.mPatternBounds.set((int) getCenterXForColumn(0), (int) getCenterYForRow(0), (int) getCenterXForColumn(4), (int) getCenterYForRow(2));
    }

    /* access modifiers changed from: protected */
    public LockPatternView.Cell detectAndAddHit(float x, float y) {
        LockPatternView.Cell cell = checkForNewHit(x, y);
        if (cell == null) {
            return null;
        }
        LockPatternView.Cell fillInGapCell = null;
        ArrayList<LockPatternView.Cell> pattern = this.mPattern;
        if (!pattern.isEmpty()) {
            LockPatternView.Cell lastCell = pattern.get(pattern.size() - 1);
            int dRow = cell.row - lastCell.row;
            if (Math.abs(cell.column - lastCell.column) == 2 && Math.abs(dRow) == 0) {
                fillInGapCell = Cell.of(lastCell.row, 2);
            }
        }
        if (fillInGapCell != null && !this.mPatternDrawLookup[fillInGapCell.row][fillInGapCell.column]) {
            addCellToPattern(fillInGapCell);
        }
        addCellToPattern(cell);
        if (isTactileFeedbackEnabled()) {
            performHapticFeedback(1, 3);
        }
        return cell;
    }

    /* access modifiers changed from: protected */
    public LockPatternView.Cell checkForNewHit(float x, float y) {
        int columnHit;
        int rowHit = getRowHit(y);
        if (rowHit >= 0 && (columnHit = getColumnHit(x)) >= 0 && !this.mPatternDrawLookup[rowHit][columnHit] && ovalToGridMap.keySet().contains(new Pair(Integer.valueOf(rowHit), Integer.valueOf(columnHit)))) {
            return Cell.of(rowHit, columnHit);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public int getColumnHit(float x) {
        float halfHitSize = (this.mSquareWidth * this.mHitFactor) / 2.0f;
        for (int i = 0; i < 5; i++) {
            float centerX = getCenterXForColumn(i);
            if (x >= centerX - halfHitSize && x <= centerX + halfHitSize) {
                return i;
            }
        }
        return -1;
    }

    /* access modifiers changed from: protected */
    public void cancelLineAnimations() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                LockPatternView.CellState state = this.mCellStates[i][j];
                if (state.lineAnimator != null) {
                    state.lineAnimator.cancel();
                    state.lineEndX = Float.MIN_VALUE;
                    state.lineEndY = Float.MIN_VALUE;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        Canvas canvas2;
        Canvas canvas3 = canvas;
        ArrayList<LockPatternView.Cell> pattern = this.mPattern;
        int count = pattern.size();
        boolean[][] drawLookup = this.mPatternDrawLookup;
        Path currentPath = this.mCurrentPath;
        currentPath.rewind();
        int i = 0;
        while (i < 3) {
            float centerY = getCenterYForRow(i);
            int j = 0;
            while (j < 5) {
                if (ovalToGridMap.keySet().contains(new Pair(Integer.valueOf(i), Integer.valueOf(j)))) {
                    LockPatternView.CellState cellState = this.mCellStates[i][j];
                    float centerX = getCenterXForColumn(j);
                    float translationY = cellState.translationY;
                    if (!isHardwareAccelerated() || !cellState.hwAnimating) {
                        drawCircle(canvas, (float) ((int) centerX), ((float) ((int) centerY)) + translationY, cellState.radius, drawLookup[i][j], cellState.alpha);
                    } else {
                        ((DisplayListCanvas) canvas3).drawCircle(cellState.hwCenterX, cellState.hwCenterY, cellState.hwRadius, cellState.hwPaint);
                    }
                }
                j++;
                canvas3 = canvas;
            }
            i++;
            canvas3 = canvas;
        }
        if (!isInStealthMode()) {
            this.mPathPaint.setColor(getCurrentColor(true));
            boolean anyCircles = false;
            float lastX = 0.0f;
            float lastY = 0.0f;
            int i2 = 0;
            while (true) {
                if (i2 >= count) {
                    canvas2 = canvas;
                    break;
                }
                LockPatternView.Cell cell = pattern.get(i2);
                if (!drawLookup[cell.row][cell.column]) {
                    canvas2 = canvas;
                    break;
                }
                anyCircles = true;
                float centerX2 = getCenterXForColumn(cell.column);
                float centerY2 = getCenterYForRow(cell.row);
                if (i2 != 0) {
                    LockPatternView.CellState state = this.mCellStates[cell.row][cell.column];
                    currentPath.rewind();
                    currentPath.moveTo(lastX, lastY);
                    if (state.lineEndX == Float.MIN_VALUE || state.lineEndY == Float.MIN_VALUE) {
                        currentPath.lineTo(centerX2, centerY2);
                    } else {
                        currentPath.lineTo(state.lineEndX, state.lineEndY);
                    }
                    canvas.drawPath(currentPath, this.mPathPaint);
                }
                lastX = centerX2;
                lastY = centerY2;
                i2++;
            }
            if ((this.mPatternInProgress || this.mPatternDisplayMode == LockPatternView.DisplayMode.Animate) && anyCircles) {
                currentPath.rewind();
                currentPath.moveTo(lastX, lastY);
                currentPath.lineTo(this.mInProgressX, this.mInProgressY);
                this.mPathPaint.setAlpha((int) (calculateLastSegmentAlpha(this.mInProgressX, this.mInProgressY, lastX, lastY) * 255.0f));
                canvas2.drawPath(currentPath, this.mPathPaint);
            }
        }
    }

    /* access modifiers changed from: protected */
    public float calculateLastSegmentAlpha(float x, float y, float lastX, float lastY) {
        if (this.mPatternBounds.contains((int) x, (int) y)) {
            return 1.0f;
        }
        float xOut = Math.max(Math.max(x - ((float) this.mPatternBounds.right), ((float) this.mPatternBounds.left) - x), 0.0f);
        float yOut = Math.max(Math.max(y - ((float) this.mPatternBounds.bottom), ((float) this.mPatternBounds.top) - y), 0.0f);
        return 1.0f - Math.min(((float) Math.sqrt((double) ((xOut * xOut) + (yOut * yOut)))) / (this.mSquareWidth * 0.75f), 1.0f);
    }

    /* access modifiers changed from: protected */
    public int getCurrentColor(boolean partOfPattern) {
        if (!partOfPattern || this.mInStealthMode) {
            return this.mUnselectedColor;
        }
        if (this.mPatternInProgress) {
            return this.mRegularColor;
        }
        if (this.mPatternDisplayMode == LockPatternView.DisplayMode.Wrong) {
            return this.mErrorColor;
        }
        if (this.mPatternDisplayMode == LockPatternView.DisplayMode.Correct || this.mPatternDisplayMode == LockPatternView.DisplayMode.Animate) {
            return this.mSuccessColor;
        }
        throw new IllegalStateException("unknown display mode " + this.mPatternDisplayMode);
    }

    /* access modifiers changed from: protected */
    public float getCenterXForColumn(int column) {
        float centerX = ((float) this.mPaddingLeft) + (((float) column) * this.mSquareWidth) + (this.mSquareWidth / 2.0f);
        if (column == 0) {
            centerX += this.mSquareWidth * OUTER_COL_OFFSET;
        }
        if (column == 4) {
            return centerX - (this.mSquareWidth * OUTER_COL_OFFSET);
        }
        return centerX;
    }

    public void setPattern(LockPatternView.DisplayMode displayMode, List<LockPatternView.Cell> pattern) {
        this.mPattern.clear();
        this.mPattern.addAll(mapGridToOval(pattern));
        clearPatternDrawLookup();
        Iterator it = this.mPattern.iterator();
        while (it.hasNext()) {
            LockPatternView.Cell cell = (LockPatternView.Cell) it.next();
            this.mPatternDrawLookup[cell.getRow()][cell.getColumn()] = true;
        }
        setDisplayMode(displayMode);
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        return new LockPatternView.SavedState(OculusLockPatternView.super.onSaveInstanceState(), LockPatternUtils.patternToString(mapOvalToGrid(this.mPattern)), this.mPatternDisplayMode.ordinal(), this.mInputEnabled, this.mInStealthMode, this.mEnableHapticFeedback);
    }

    /* access modifiers changed from: protected */
    public void notifyPatternDetected() {
        sendAccessEvent(17040258);
        if (this.mOnPatternListener != null) {
            this.mOnPatternListener.onPatternDetected(mapOvalToGrid(this.mPattern));
        }
    }

    private static List<LockPatternView.Cell> mapOvalToGrid(List<LockPatternView.Cell> patternOval) {
        List<LockPatternView.Cell> patternGrid = new ArrayList<>();
        for (LockPatternView.Cell cell : patternOval) {
            Pair<Integer, Integer> mapped = ovalToGridMap.get(new Pair(Integer.valueOf(cell.row), Integer.valueOf(cell.column)));
            patternGrid.add(LockPatternView.Cell.of(((Integer) mapped.first).intValue(), ((Integer) mapped.second).intValue()));
        }
        return patternGrid;
    }

    private static List<LockPatternView.Cell> mapGridToOval(List<LockPatternView.Cell> patternGrid) {
        List<LockPatternView.Cell> patternOval = new ArrayList<>();
        for (LockPatternView.Cell cell : patternGrid) {
            Pair<Integer, Integer> mapped = gridToOvalMap.get(new Pair(Integer.valueOf(cell.row), Integer.valueOf(cell.column)));
            patternOval.add(Cell.of(((Integer) mapped.first).intValue(), ((Integer) mapped.second).intValue()));
        }
        return patternOval;
    }
}
