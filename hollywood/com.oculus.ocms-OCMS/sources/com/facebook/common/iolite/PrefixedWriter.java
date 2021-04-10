package com.facebook.common.iolite;

import android.annotation.SuppressLint;
import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

@SuppressLint({"BadMethodUse-java.lang.String.length", "BadMethodUse-java.lang.String.charAt"})
public class PrefixedWriter extends FilterWriter {
    private static final String SYSTEM_LINE_SEPARATOR = System.getProperty("line.separator");
    private final String mLineSeparator;
    private final String mPrefix;
    private final CharacterStateMachine mStateMachine;

    /* access modifiers changed from: private */
    public enum CharacterState {
        AFTER_CARRIAGE_RETURN {
            /* access modifiers changed from: protected */
            @Override // com.facebook.common.iolite.PrefixedWriter.CharacterState
            public void handle(CharacterStateMachine characterStateMachine, int i) throws IOException {
                characterStateMachine.writePrefix();
                AFTER_FIRST_CHARACTER.handle(characterStateMachine, i);
            }
        },
        AFTER_FIRST_CHARACTER {
            /* access modifiers changed from: protected */
            @Override // com.facebook.common.iolite.PrefixedWriter.CharacterState
            public void handle(CharacterStateMachine characterStateMachine, int i) throws IOException {
                characterStateMachine.write(i);
                if (i == characterStateMachine.getLineSeparator().charAt(0)) {
                    characterStateMachine.changeState(SEPARATOR_CHARACTER_n);
                } else {
                    characterStateMachine.changeState(AFTER_FIRST_CHARACTER);
                }
            }
        },
        SEPARATOR_CHARACTER_n {
            /* access modifiers changed from: protected */
            @Override // com.facebook.common.iolite.PrefixedWriter.CharacterState
            public void onEnterState(CharacterStateMachine characterStateMachine) {
                characterStateMachine.separatorMatchIndex = 1;
                if (characterStateMachine.separatorMatchIndex >= characterStateMachine.getLineSeparator().length()) {
                    characterStateMachine.changeState(AFTER_CARRIAGE_RETURN);
                }
            }

            /* access modifiers changed from: protected */
            @Override // com.facebook.common.iolite.PrefixedWriter.CharacterState
            public void handle(CharacterStateMachine characterStateMachine, int i) throws IOException {
                characterStateMachine.write(i);
                String lineSeparator = characterStateMachine.getLineSeparator();
                if (i != lineSeparator.charAt(CharacterStateMachine.access$608(characterStateMachine))) {
                    if (i == lineSeparator.charAt(0)) {
                        onEnterState(characterStateMachine);
                    } else {
                        characterStateMachine.changeState(AFTER_FIRST_CHARACTER);
                    }
                } else if (characterStateMachine.separatorMatchIndex == lineSeparator.length()) {
                    characterStateMachine.changeState(AFTER_CARRIAGE_RETURN);
                }
            }
        };

        /* access modifiers changed from: protected */
        public abstract void handle(CharacterStateMachine characterStateMachine, int i) throws IOException;

        /* access modifiers changed from: protected */
        public void onEnterState(CharacterStateMachine characterStateMachine) {
        }

        /* access modifiers changed from: protected */
        public void onExitState(CharacterStateMachine characterStateMachine) {
        }
    }

    public PrefixedWriter(Writer writer, String str) {
        this(writer, str, SYSTEM_LINE_SEPARATOR);
    }

    PrefixedWriter(Writer writer, String str, String str2) {
        super(writer);
        this.mPrefix = str;
        this.mLineSeparator = str2;
        this.mStateMachine = new CharacterStateMachine();
    }

    @Override // java.io.FilterWriter, java.io.Writer
    public void write(char[] cArr, int i, int i2) throws IOException {
        synchronized (this.lock) {
            while (true) {
                int i3 = i2 - 1;
                if (i2 > 0) {
                    int i4 = i + 1;
                    doWrite(cArr[i]);
                    i = i4;
                    i2 = i3;
                }
            }
        }
    }

    @Override // java.io.FilterWriter, java.io.Writer
    public void write(int i) throws IOException {
        synchronized (this.lock) {
            doWrite(i);
        }
    }

    @Override // java.io.FilterWriter, java.io.Writer
    public void write(String str, int i, int i2) throws IOException {
        synchronized (this.lock) {
            while (true) {
                int i3 = i2 - 1;
                if (i2 > 0) {
                    int i4 = i + 1;
                    doWrite(str.charAt(i));
                    i = i4;
                    i2 = i3;
                }
            }
        }
    }

    private void doWrite(int i) throws IOException {
        this.mStateMachine.handle(i);
    }

    /* access modifiers changed from: private */
    public class CharacterStateMachine {
        private CharacterState currentState;
        private int separatorMatchIndex;

        private CharacterStateMachine() {
            this.currentState = CharacterState.AFTER_CARRIAGE_RETURN;
            this.separatorMatchIndex = -1;
        }

        static /* synthetic */ int access$608(CharacterStateMachine characterStateMachine) {
            int i = characterStateMachine.separatorMatchIndex;
            characterStateMachine.separatorMatchIndex = i + 1;
            return i;
        }

        public void handle(int i) throws IOException {
            this.currentState.handle(this, i);
        }

        public void changeState(CharacterState characterState) {
            CharacterState characterState2 = this.currentState;
            if (characterState2 != characterState) {
                characterState2.onExitState(this);
                this.currentState = characterState;
                characterState.onEnterState(this);
            }
        }

        public String getLineSeparator() {
            return PrefixedWriter.this.mLineSeparator;
        }

        public void writePrefix() throws IOException {
            PrefixedWriter.this.out.write(PrefixedWriter.this.mPrefix);
        }

        public void write(int i) throws IOException {
            PrefixedWriter.this.out.write(i);
        }
    }
}
