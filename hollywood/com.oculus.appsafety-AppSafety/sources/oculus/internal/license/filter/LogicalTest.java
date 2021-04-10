package oculus.internal.license.filter;

public enum LogicalTest {
    all("all"),
    any("any"),
    none("none");
    
    private final String value;

    private LogicalTest(String value2) {
        this.value = value2;
    }

    public static LogicalTest fromString(String value2) throws IllegalArgumentException {
        LogicalTest[] values = values();
        for (LogicalTest logicTest : values) {
            if (logicTest.value.equalsIgnoreCase(value2)) {
                return logicTest;
            }
        }
        throw new IllegalArgumentException(String.format("%s: unrecognized logical test", value2));
    }
}
