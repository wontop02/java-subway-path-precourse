package subway.util;

import java.util.Set;

public class InputValidator {
    private static final Set<String> MAIN_FUNCTION = Set.of("1", "Q");
    private static final Set<String> CRITERIA_FUNCTION = Set.of("1", "2", "B");
    private static final String INVALID_FUNCTION = "기능을 정확히 입력해 주세요.";
    private static final String CAN_NOT_EQUAL = "출발역과 도착역이 동일합니다.";

    private InputValidator() {
    }

    public static void validateMainInput(String input) {
        if (!MAIN_FUNCTION.contains(input)) {
            throw new IllegalArgumentException(INVALID_FUNCTION);
        }
    }

    public static void validateCriteriaInput(String input) {
        if (!CRITERIA_FUNCTION.contains(input)) {
            throw new IllegalArgumentException(INVALID_FUNCTION);
        }
    }

    public static void validateEndStationInput(String startStation, String endStation) {
        if (startStation.equals(endStation)) {
            throw new IllegalArgumentException(CAN_NOT_EQUAL);
        }
    }
}
