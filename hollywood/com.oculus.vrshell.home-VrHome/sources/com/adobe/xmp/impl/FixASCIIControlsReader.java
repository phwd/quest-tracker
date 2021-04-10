package com.adobe.xmp.impl;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.Reader;

public class FixASCIIControlsReader extends PushbackReader {
    private static final int BUFFER_SIZE = 8;
    private static final int STATE_AMP = 1;
    private static final int STATE_DIG1 = 4;
    private static final int STATE_ERROR = 5;
    private static final int STATE_HASH = 2;
    private static final int STATE_HEX = 3;
    private static final int STATE_START = 0;
    private int control = 0;
    private int digits = 0;
    private int state = 0;

    public FixASCIIControlsReader(Reader in) {
        super(in, 8);
    }

    @Override // java.io.PushbackReader, java.io.FilterReader, java.io.Reader
    public int read(char[] cbuf, int off, int len) throws IOException {
        int pos;
        int readAhead = 0;
        int read = 0;
        char[] readAheadBuffer = new char[8];
        boolean available = true;
        int pos2 = off;
        while (available && read < len) {
            if (super.read(readAheadBuffer, readAhead, 1) == 1) {
                available = true;
            } else {
                available = false;
            }
            if (available) {
                char c = processChar(readAheadBuffer[readAhead]);
                if (this.state == 0) {
                    if (Utils.isControlChar(c)) {
                        c = ' ';
                    }
                    pos = pos2 + 1;
                    cbuf[pos2] = c;
                    readAhead = 0;
                    read++;
                } else if (this.state == 5) {
                    unread(readAheadBuffer, 0, readAhead + 1);
                    readAhead = 0;
                    pos = pos2;
                } else {
                    readAhead++;
                    pos = pos2;
                }
                pos2 = pos;
            } else if (readAhead > 0) {
                unread(readAheadBuffer, 0, readAhead);
                this.state = 5;
                readAhead = 0;
                available = true;
            }
        }
        if (read > 0 || available) {
            return read;
        }
        return -1;
    }

    private char processChar(char ch) {
        switch (this.state) {
            case 0:
                if (ch != '&') {
                    return ch;
                }
                this.state = 1;
                return ch;
            case 1:
                if (ch == '#') {
                    this.state = 2;
                    return ch;
                }
                this.state = 5;
                return ch;
            case 2:
                if (ch == 'x') {
                    this.control = 0;
                    this.digits = 0;
                    this.state = 3;
                    return ch;
                } else if ('0' > ch || ch > '9') {
                    this.state = 5;
                    return ch;
                } else {
                    this.control = Character.digit(ch, 10);
                    this.digits = 1;
                    this.state = 4;
                    return ch;
                }
            case 3:
                if (('0' <= ch && ch <= '9') || (('a' <= ch && ch <= 'f') || ('A' <= ch && ch <= 'F'))) {
                    this.control = (this.control * 16) + Character.digit(ch, 16);
                    this.digits++;
                    if (this.digits <= 4) {
                        this.state = 3;
                        return ch;
                    }
                    this.state = 5;
                    return ch;
                } else if (ch != ';' || !Utils.isControlChar((char) this.control)) {
                    this.state = 5;
                    return ch;
                } else {
                    this.state = 0;
                    return (char) this.control;
                }
            case 4:
                if ('0' <= ch && ch <= '9') {
                    this.control = (this.control * 10) + Character.digit(ch, 10);
                    this.digits++;
                    if (this.digits <= 5) {
                        this.state = 4;
                        return ch;
                    }
                    this.state = 5;
                    return ch;
                } else if (ch != ';' || !Utils.isControlChar((char) this.control)) {
                    this.state = 5;
                    return ch;
                } else {
                    this.state = 0;
                    return (char) this.control;
                }
            case 5:
                this.state = 0;
                return ch;
            default:
                return ch;
        }
    }
}
