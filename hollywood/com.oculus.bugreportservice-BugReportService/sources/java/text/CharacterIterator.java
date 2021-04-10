package java.text;

public interface CharacterIterator extends Cloneable {
    Object clone();

    char current();

    char first();

    int getBeginIndex();

    int getEndIndex();

    int getIndex();

    char next();

    char previous();

    char setIndex(int i);
}
