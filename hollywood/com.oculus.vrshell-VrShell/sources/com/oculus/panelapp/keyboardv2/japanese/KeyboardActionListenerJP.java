package com.oculus.panelapp.keyboardv2.japanese;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;
import com.oculus.panelapp.keyboardv2.DynamicKeyArea;
import com.oculus.panelapp.keyboardv2.KeyAction;
import com.oculus.panelapp.keyboardv2.KeyCode;
import com.oculus.panelapp.keyboardv2.Keyboard;
import com.oculus.panelapp.keyboardv2.KeyboardPanelApp;
import com.oculus.panelapp.keyboardv2.R;
import com.oculus.panelapp.keyboardv2.japanese.JapaneseInputMethodEditor;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class KeyboardActionListenerJP extends KeyboardPanelApp.KeyboardActionListenerDefault {
    private static final String ACTION_KEY_COMPOSE_LABEL = "確定";
    public static final String TAG = "KeyboardActionListenerJP";
    private final Optional<Keyboard.Key> mActionKey;
    private final Optional<Drawable> mActionKeyGoIcon;
    private final KeyboardPanelApp mApp;
    private final HiraganaTransformDag mHiraganaTransform = new HiraganaTransformDag();
    private final JapaneseInputMethodEditor.InputType mInputType;
    private final JapaneseInputMethodEditor mJpIme;
    private final Keyboard mKeyboard;
    private final int mScrollStepSizeInPixels;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public KeyboardActionListenerJP(KeyboardPanelApp keyboardPanelApp, Keyboard keyboard, int i) {
        super();
        Objects.requireNonNull(keyboardPanelApp);
        this.mApp = keyboardPanelApp;
        this.mKeyboard = keyboard;
        this.mJpIme = new JapaneseInputMethodEditor(this.mApp);
        this.mInputType = getInputTypeFromResId(i);
        this.mScrollStepSizeInPixels = keyboardPanelApp.getContext().getResources().getDimensionPixelSize(R.dimen.scroll_step);
        this.mActionKey = getActionKey();
        if (this.mActionKey.isPresent()) {
            this.mActionKeyGoIcon = Optional.ofNullable(this.mActionKey.get().icon);
        } else {
            this.mActionKeyGoIcon = Optional.empty();
        }
    }

    private Optional<Keyboard.Key> getActionKey() {
        Optional<Keyboard.Key> empty = Optional.empty();
        for (Keyboard.Key key : this.mKeyboard.getKeys()) {
            if (!(key.codes == null || key.codes.isEmpty() || KeyCode.get(key.codes.get(0).intValue()) != KeyCode.ACTION_KEY)) {
                return Optional.of(key);
            }
        }
        return empty;
    }

    @Override // com.oculus.panelapp.keyboardv2.KeyboardView.OnKeyboardActionListener, com.oculus.panelapp.keyboardv2.KeyboardPanelApp.KeyboardActionListenerDefault
    public void onPress(String str, boolean z, Keyboard.Key key) {
        if (key != null && !key.disabled) {
            KeyCode keyCode = key.codes != null ? KeyCode.get(key.codes.get(0).intValue()) : KeyCode.NONE;
            if (keyCode == KeyCode.JP_CONVERSION) {
                this.mApp.playPressSound();
                if (!" ".equals(key.text) || getCurrentCompositionString().length() != 0) {
                    KeyboardPanelApp keyboardPanelApp = this.mApp;
                    keyboardPanelApp.nativeOnAction(keyboardPanelApp.getNativePointer(), str, key.text != null ? key.text.toString() : "", KeyCode.JP_CONVERSION.name(), KeyAction.COMMAND.name(), true);
                    this.mJpIme.onCommand(keyCode, key.text.toString(), this.mInputType);
                    switchToDefaultState();
                    logConversion(key);
                } else {
                    this.mApp.commitTextInput(str, " ", KeyCode.NONE, KeyAction.TEXT, false);
                }
                clearConversionKeys();
            } else if (keyCode == KeyCode.JP_SCROLL_LEFT) {
                this.mApp.playPressSound();
                retreatConversionKeys();
            } else if (keyCode == KeyCode.JP_SCROLL_RIGHT) {
                this.mApp.playPressSound();
                advanceConversionKeys();
            } else if (keyCode == KeyCode.JP_DIACRITIC) {
                this.mApp.playPressSound();
                cycleCharacterBeforeCursor(str);
            } else if (keyCode == KeyCode.BACKSPACE) {
                PredictionResult onCommand = this.mJpIme.onCommand(keyCode, null, this.mInputType);
                displayPredictions(onCommand);
                onCommand.getPredictionCompositionString();
                super.onPress(str, z, key);
            } else if (keyCode != KeyCode.NONE) {
                super.onPress(str, z, key);
            } else {
                this.mApp.playPressSound();
                if (!z || key.shiftText == null) {
                    inputText(str, key.text);
                } else {
                    inputText(str, key.shiftText);
                }
            }
        }
    }

    private void commitComposition(String str, String str2, Keyboard.Key key) {
        if (!str.isEmpty()) {
            KeyboardPanelApp keyboardPanelApp = this.mApp;
            keyboardPanelApp.nativeOnAction(keyboardPanelApp.getNativePointer(), str2, str, KeyCode.JP_CONVERSION.name(), KeyAction.COMMAND.name(), true);
        }
        clearConversionKeys();
        switchToDefaultState();
        logConversion(key);
    }

    @Override // com.oculus.panelapp.keyboardv2.KeyboardView.OnKeyboardActionListener, com.oculus.panelapp.keyboardv2.KeyboardPanelApp.KeyboardActionListenerDefault
    public void onRelease(String str, boolean z, Keyboard.Key key) {
        if (key != null && !key.disabled) {
            KeyCode keyCode = key.codes != null ? KeyCode.get(key.codes.get(0).intValue()) : KeyCode.NONE;
            if (keyCode == KeyCode.LAYOUT || keyCode == KeyCode.DISMISS) {
                commitComposition(this.mJpIme.onCommand(keyCode, null, this.mInputType).getPredictionCompositionString(), str, key);
                super.onRelease(str, z, key);
            } else if (keyCode == KeyCode.ACTION_KEY) {
                String predictionCompositionString = this.mJpIme.onCommand(keyCode, null, this.mInputType).getPredictionCompositionString();
                if (predictionCompositionString == null || predictionCompositionString.isEmpty()) {
                    super.onRelease(str, z, key);
                    return;
                }
                Log.d(TAG, String.format("composition string: \"%s\"", predictionCompositionString));
                commitComposition(predictionCompositionString, str, key);
            } else {
                super.onRelease(str, z, key);
            }
        }
    }

    public void resetIMEState() {
        clearConversionKeys();
        switchToDefaultState();
        this.mJpIme.flushBuffers();
    }

    private JapaneseInputMethodEditor.InputType getInputTypeFromResId(int i) {
        if (i == R.xml.ja_jp_text_default_32 || i == R.xml.ja_jp_text_default_48) {
            return JapaneseInputMethodEditor.InputType.HIRAGANA;
        }
        if (i == R.xml.ja_jp_romaji_text_default_32 || i == R.xml.ja_jp_romaji_text_default_48 || i == R.xml.ja_jp_text_romaji_abc_32 || i == R.xml.ja_jp_text_romaji_abc_48) {
            return JapaneseInputMethodEditor.InputType.ROMAJI;
        }
        throw new IllegalArgumentException("JA_JP keyboard listener instantiated on non-JA_JP keyboard layout");
    }

    private PredictionResult updatePredictions(CharSequence charSequence) {
        return this.mJpIme.getPredictions(charSequence.toString(), this.mInputType);
    }

    private String getCurrentCompositionString() {
        return updatePredictions("").getPredictionCompositionString();
    }

    private void displayPredictions(PredictionResult predictionResult) {
        List<Prediction> predictions;
        clearConversionKeys();
        if (!(predictionResult == null || (predictions = predictionResult.getPredictions()) == null)) {
            for (Prediction prediction : predictions) {
                insertConversionKey(prediction.getCandidate());
            }
        }
    }

    private DynamicKeyArea getConversionKeyArea() {
        return this.mKeyboard.getDynamicKeyArea(R.id.conversion_key_area);
    }

    private void clearConversionKeys() {
        getConversionKeyArea().clear();
    }

    private void insertConversionKey(String str, String str2) {
        getConversionKeyArea().addKey(str, str2, KeyCode.JP_CONVERSION);
    }

    private void insertConversionKey(String str) {
        insertConversionKey(str, str);
    }

    private void scrollConversionKeys(int i) {
        getConversionKeyArea().scrollHorizontal(i);
    }

    private void advanceConversionKeys() {
        scrollConversionKeys(-this.mScrollStepSizeInPixels);
    }

    private void retreatConversionKeys() {
        scrollConversionKeys(this.mScrollStepSizeInPixels);
    }

    private void insertTextKey(String str, String str2) {
        getConversionKeyArea().addKey(str, str2, KeyCode.NONE);
    }

    private void inputText(String str, CharSequence charSequence) {
        PredictionResult updatePredictions = updatePredictions(charSequence);
        String predictionCompositionString = updatePredictions.getPredictionCompositionString();
        displayPredictions(updatePredictions);
        this.mApp.commitTextInput(str, predictionCompositionString, KeyCode.NONE, KeyAction.TEXT, false);
        switchToComposingState();
    }

    private void cycleCharacterBeforeCursor(String str) {
        Optional<String> segmentBeforeCursor = this.mJpIme.getSegmentBeforeCursor(JapaneseInputMethodEditor.InputType.HIRAGANA.getDefaultInputLayer());
        if (segmentBeforeCursor.isPresent()) {
            String next = this.mHiraganaTransform.next(segmentBeforeCursor.get());
            this.mJpIme.onCommand(KeyCode.BACKSPACE, null, this.mInputType);
            inputText(str, next);
        }
    }

    private void switchToDefaultState() {
        if (this.mActionKey.isPresent() && this.mActionKeyGoIcon.isPresent()) {
            Keyboard.Key key = this.mActionKey.get();
            key.label = null;
            key.icon = this.mActionKeyGoIcon.get();
            this.mApp.invalidateKey(this.mActionKey.get());
        }
    }

    private void switchToComposingState() {
        if (this.mActionKey.isPresent()) {
            Keyboard.Key key = this.mActionKey.get();
            key.label = ACTION_KEY_COMPOSE_LABEL;
            key.icon = null;
            this.mApp.invalidateKey(this.mActionKey.get());
        }
    }

    private void logConversion(Keyboard.Key key) {
        StringBuilder sb = new StringBuilder();
        sb.append("telemetry oculus_shell_input_japanese_keyboard_conversions 1 2 ");
        KeyCode keyCode = KeyCode.get((key.codes == null || key.codes.size() <= 0) ? 0 : key.codes.get(0).intValue());
        keyCode.toString();
        if (keyCode == KeyCode.JP_CONVERSION && " ".equals(key.text)) {
            keyCode = KeyCode.NONE;
        }
        sb.append(" ");
        sb.append(String.format("\"key_command\" \"%s\"", keyCode));
        if (keyCode == KeyCode.JP_CONVERSION) {
            sb.append(" ");
            sb.append(String.format("\"conversion_ordinal\" \"%d\"", Integer.valueOf(key.layoutIndex + 1)));
        }
        try {
            String resourceEntryName = this.mApp.getContext().getResources().getResourceEntryName(key.getKeyboard().getLayoutResId());
            sb.append(" ");
            sb.append(String.format("\"keyboard_layout\" \"%s\"", resourceEntryName));
        } catch (Resources.NotFoundException e) {
            Log.w(TAG, String.format("Keyboard layout resource ID %d not found", Integer.valueOf(key.getKeyboard().getLayoutResId())), e);
        }
        this.mApp.getCommandChannel().sendCommand(sb.toString());
    }
}
