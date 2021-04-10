package com.oculus.panelapp.keyboardv2.japanese;

import android.os.SystemClock;
import com.oculus.panelapp.keyboardv2.KeyCode;
import com.oculus.vrshell.panels.AndroidPanelApp;
import com.oculus.vrshell.panels.FrameCommandChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import jp.co.omronsoft.iwnnime.ml.ComposingText;
import jp.co.omronsoft.iwnnime.ml.LetterConverter;
import jp.co.omronsoft.iwnnime.ml.StrSegment;
import jp.co.omronsoft.iwnnime.ml.WnnWord;
import jp.co.omronsoft.iwnnime.ml.iwnn.iWnnEngine;
import jp.co.omronsoft.iwnnime.ml.jajp.Romkan;

public class JapaneseInputMethodEditor {
    public static final String JAPANESE_INPUT_METHOD_EDITOR_EVENT_TAG = "oculus_keyboard_japaneseime_performance";
    public static final String METRIC_GET_PREDICTIONS = "ime_get_predictions_time";
    public static final String METRIC_IWNN_GET_CANDIDATES = "iwnn_get_candidates_time";
    public static final String METRIC_IWNN_INIT = "iwnn_init_time";
    public static final String METRIC_IWNN_PREDICT = "iwnn_predict_time";
    private static final String TAG = "JapaneseInputMethodEditor";
    public static final String TELEMETRY_EVENT_TYPE = "telemetry";
    private LetterConverter bufferConverter;
    private ComposingText compositionBuffer;
    private iWnnEngine japanesePredictionEngine;
    private AndroidPanelApp keyboardPanelApp;
    private int romajiActiveInputLayer = InputType.ROMAJI.getDefaultInputLayer();

    public enum InputType {
        HIRAGANA(1),
        ROMAJI(0);
        
        private final int mDefaultInputLayer;

        private InputType(int i) {
            this.mDefaultInputLayer = i;
        }

        public int getDefaultInputLayer() {
            return this.mDefaultInputLayer;
        }
    }

    private int getCurrentInputLayer(InputType inputType) {
        if (AnonymousClass1.$SwitchMap$com$oculus$panelapp$keyboardv2$japanese$JapaneseInputMethodEditor$InputType[inputType.ordinal()] != 1) {
            return 1;
        }
        return this.romajiActiveInputLayer;
    }

    public JapaneseInputMethodEditor(AndroidPanelApp androidPanelApp) {
        this.keyboardPanelApp = androidPanelApp;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        this.japanesePredictionEngine = iWnnEngine.getEngine();
        this.japanesePredictionEngine.setDictionary(0, 0, androidPanelApp.getContext());
        this.japanesePredictionEngine.setFlexibleCharset(1, 1);
        recordPerformance(TELEMETRY_EVENT_TYPE, JAPANESE_INPUT_METHOD_EDITOR_EVENT_TAG, METRIC_IWNN_INIT, SystemClock.elapsedRealtime() - elapsedRealtime);
        this.bufferConverter = new Romkan();
        this.compositionBuffer = new ComposingText();
    }

    public PredictionResult getPredictions(String str, InputType inputType) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        PredictionResult predictionResult = new PredictionResult(null, null);
        if (str == null) {
            throw new IllegalArgumentException("compositionString cannot be null.");
        } else if (shouldFlushCompositionBuffer(str)) {
            predictionResult.setPredictionCompositionString(this.compositionBuffer.toString(1));
            flushBuffers();
            return predictionResult;
        } else {
            if (inputType == InputType.ROMAJI) {
                this.compositionBuffer.insertStrSegment(0, 1, new StrSegment(str));
                if (this.bufferConverter.convert(this.compositionBuffer)) {
                    this.romajiActiveInputLayer = 1;
                } else {
                    this.romajiActiveInputLayer = 0;
                }
            } else {
                this.compositionBuffer.insertStrSegment(1, 2, new StrSegment(str));
            }
            predictionResult.setPredictionCompositionString(this.compositionBuffer.toString(1));
            predictionResult.setPredictions(getPredictionsInternal());
            recordPerformance(TELEMETRY_EVENT_TYPE, JAPANESE_INPUT_METHOD_EDITOR_EVENT_TAG, METRIC_GET_PREDICTIONS, SystemClock.elapsedRealtime() - elapsedRealtime);
            return predictionResult;
        }
    }

    private List<Prediction> getPredictionsInternal() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        int predict = this.japanesePredictionEngine.predict(this.compositionBuffer, 0, -1);
        recordPerformance(TELEMETRY_EVENT_TYPE, JAPANESE_INPUT_METHOD_EDITOR_EVENT_TAG, METRIC_IWNN_PREDICT, SystemClock.elapsedRealtime() - elapsedRealtime);
        ArrayList arrayList = new ArrayList(predict);
        if (predict > 0) {
            long elapsedRealtime2 = SystemClock.elapsedRealtime();
            while (true) {
                WnnWord nextCandidate = this.japanesePredictionEngine.getNextCandidate();
                if (nextCandidate == null) {
                    break;
                }
                arrayList.add(new Prediction(nextCandidate.candidate, nextCandidate.stroke, nextCandidate.frequency));
            }
            recordPerformance(TELEMETRY_EVENT_TYPE, JAPANESE_INPUT_METHOD_EDITOR_EVENT_TAG, METRIC_IWNN_GET_CANDIDATES, SystemClock.elapsedRealtime() - elapsedRealtime2);
        }
        return arrayList;
    }

    public PredictionResult onCommand(KeyCode keyCode, String str, InputType inputType) {
        PredictionResult predictionResult = new PredictionResult(null, null);
        String composingText = this.compositionBuffer.toString(1);
        int i = AnonymousClass1.$SwitchMap$com$oculus$panelapp$keyboardv2$KeyCode[keyCode.ordinal()];
        if (i == 1) {
            if (str == null) {
                predictionResult.setPredictionCompositionString(composingText);
            } else {
                predictionResult.setPredictionCompositionString(str);
            }
            flushBuffers();
            return predictionResult;
        } else if (i == 2) {
            int currentInputLayer = getCurrentInputLayer(inputType);
            removeSegmentBeforeCursor(currentInputLayer);
            predictionResult.setPredictionCompositionString(this.compositionBuffer.toString(currentInputLayer));
            predictionResult.setPredictions(getPredictionsInternal());
            return predictionResult;
        } else if (i != 3 && i != 4 && i != 5) {
            return predictionResult;
        } else {
            predictionResult.setPredictionCompositionString(composingText);
            flushBuffers();
            return predictionResult;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.panelapp.keyboardv2.japanese.JapaneseInputMethodEditor$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$panelapp$keyboardv2$KeyCode = new int[KeyCode.values().length];
        static final /* synthetic */ int[] $SwitchMap$com$oculus$panelapp$keyboardv2$japanese$JapaneseInputMethodEditor$InputType = new int[InputType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(17:0|(2:1|2)|3|5|6|7|8|9|10|11|12|13|15|16|17|18|20) */
        /* JADX WARNING: Can't wrap try/catch for region: R(18:0|1|2|3|5|6|7|8|9|10|11|12|13|15|16|17|18|20) */
        /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0035 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0053 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002a */
        static {
            /*
                com.oculus.panelapp.keyboardv2.KeyCode[] r0 = com.oculus.panelapp.keyboardv2.KeyCode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.oculus.panelapp.keyboardv2.japanese.JapaneseInputMethodEditor.AnonymousClass1.$SwitchMap$com$oculus$panelapp$keyboardv2$KeyCode = r0
                r0 = 1
                int[] r1 = com.oculus.panelapp.keyboardv2.japanese.JapaneseInputMethodEditor.AnonymousClass1.$SwitchMap$com$oculus$panelapp$keyboardv2$KeyCode     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.oculus.panelapp.keyboardv2.KeyCode r2 = com.oculus.panelapp.keyboardv2.KeyCode.JP_CONVERSION     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                r1 = 2
                int[] r2 = com.oculus.panelapp.keyboardv2.japanese.JapaneseInputMethodEditor.AnonymousClass1.$SwitchMap$com$oculus$panelapp$keyboardv2$KeyCode     // Catch:{ NoSuchFieldError -> 0x001f }
                com.oculus.panelapp.keyboardv2.KeyCode r3 = com.oculus.panelapp.keyboardv2.KeyCode.BACKSPACE     // Catch:{ NoSuchFieldError -> 0x001f }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r2 = com.oculus.panelapp.keyboardv2.japanese.JapaneseInputMethodEditor.AnonymousClass1.$SwitchMap$com$oculus$panelapp$keyboardv2$KeyCode     // Catch:{ NoSuchFieldError -> 0x002a }
                com.oculus.panelapp.keyboardv2.KeyCode r3 = com.oculus.panelapp.keyboardv2.KeyCode.ACTION_KEY     // Catch:{ NoSuchFieldError -> 0x002a }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r4 = 3
                r2[r3] = r4     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r2 = com.oculus.panelapp.keyboardv2.japanese.JapaneseInputMethodEditor.AnonymousClass1.$SwitchMap$com$oculus$panelapp$keyboardv2$KeyCode     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.oculus.panelapp.keyboardv2.KeyCode r3 = com.oculus.panelapp.keyboardv2.KeyCode.LAYOUT     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r4 = 4
                r2[r3] = r4     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r2 = com.oculus.panelapp.keyboardv2.japanese.JapaneseInputMethodEditor.AnonymousClass1.$SwitchMap$com$oculus$panelapp$keyboardv2$KeyCode     // Catch:{ NoSuchFieldError -> 0x0040 }
                com.oculus.panelapp.keyboardv2.KeyCode r3 = com.oculus.panelapp.keyboardv2.KeyCode.DISMISS     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r4 = 5
                r2[r3] = r4     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                com.oculus.panelapp.keyboardv2.japanese.JapaneseInputMethodEditor$InputType[] r2 = com.oculus.panelapp.keyboardv2.japanese.JapaneseInputMethodEditor.InputType.values()
                int r2 = r2.length
                int[] r2 = new int[r2]
                com.oculus.panelapp.keyboardv2.japanese.JapaneseInputMethodEditor.AnonymousClass1.$SwitchMap$com$oculus$panelapp$keyboardv2$japanese$JapaneseInputMethodEditor$InputType = r2
                int[] r2 = com.oculus.panelapp.keyboardv2.japanese.JapaneseInputMethodEditor.AnonymousClass1.$SwitchMap$com$oculus$panelapp$keyboardv2$japanese$JapaneseInputMethodEditor$InputType     // Catch:{ NoSuchFieldError -> 0x0053 }
                com.oculus.panelapp.keyboardv2.japanese.JapaneseInputMethodEditor$InputType r3 = com.oculus.panelapp.keyboardv2.japanese.JapaneseInputMethodEditor.InputType.ROMAJI     // Catch:{ NoSuchFieldError -> 0x0053 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0053 }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x0053 }
            L_0x0053:
                int[] r0 = com.oculus.panelapp.keyboardv2.japanese.JapaneseInputMethodEditor.AnonymousClass1.$SwitchMap$com$oculus$panelapp$keyboardv2$japanese$JapaneseInputMethodEditor$InputType     // Catch:{ NoSuchFieldError -> 0x005d }
                com.oculus.panelapp.keyboardv2.japanese.JapaneseInputMethodEditor$InputType r2 = com.oculus.panelapp.keyboardv2.japanese.JapaneseInputMethodEditor.InputType.HIRAGANA     // Catch:{ NoSuchFieldError -> 0x005d }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x005d }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x005d }
            L_0x005d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.keyboardv2.japanese.JapaneseInputMethodEditor.AnonymousClass1.<clinit>():void");
        }
    }

    public void close() {
        this.japanesePredictionEngine.close();
    }

    /* access modifiers changed from: protected */
    public void flushBuffers() {
        this.compositionBuffer.clear();
    }

    private void removeSegmentBeforeCursor(int i) {
        this.compositionBuffer.getCursor(i);
        if (this.compositionBuffer.getCursor(i) > 0) {
            this.compositionBuffer.replaceStrSegment(i, new StrSegment[0], 1);
        }
    }

    public Optional<String> getSegmentBeforeCursor(int i) {
        Optional<StrSegment> segmentBeforeCursor = getSegmentBeforeCursor(this.compositionBuffer, i);
        if (segmentBeforeCursor.isPresent()) {
            return Optional.ofNullable(segmentBeforeCursor.get().string);
        }
        return Optional.empty();
    }

    private static Optional<StrSegment> getSegmentBeforeCursor(ComposingText composingText, int i) {
        int cursor = composingText.getCursor(i);
        if (composingText.getCursor(i) > 0) {
            return Optional.of(composingText.getStrSegment(i, cursor - 1));
        }
        return Optional.empty();
    }

    private boolean shouldFlushCompositionBuffer(String str) {
        return str != null && (str.isEmpty() || str.equals(" "));
    }

    private void recordPerformance(String str, String str2, String str3, long j) {
        FrameCommandChannel commandChannel = this.keyboardPanelApp.getCommandChannel();
        commandChannel.sendCommand(String.format("%s %s 1 2 %s " + j, str, str2, str3));
    }
}
