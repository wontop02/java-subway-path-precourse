package subway.view;

import java.util.List;

public class OutputView {
    private static final String PRINT_MAIN =
            "## 메인 화면\n"
                    + "1. 경로 조회\n"
                    + "Q. 종료";

    private static final String PRINT_CRITERIA =
            "## 경로 기준\n"
                    + "1. 최단 거리\n"
                    + "2. 최소 시간 \n"
                    + "B. 돌아가기";

    private static final String PRINT_RESULT = "## 조회 결과";
    private static final String PRINT_LINE = "---";
    private static final String PRINT_TOTAL_DISTANCE = "총 거리: %dkm";
    private static final String PRINT_TOTAL_TIME = "총 소요 시간: %d분";

    private static final String ERROR = "[ERROR] ";
    private static final String INFO = "[INFO] ";

    private OutputView() {
    }

    public static void printError(String message) {
        System.out.println(ERROR + message);
    }

    public static void printMain() {
        System.out.println();
        System.out.println(PRINT_MAIN);
    }

    public static void printCriteria() {
        System.out.println();
        System.out.println(PRINT_CRITERIA);
    }

    public static void printResult(List<String> stations, int distance, int time) {
        System.out.println();
        System.out.println(PRINT_RESULT);
        System.out.println(INFO + PRINT_LINE);
        System.out.println(INFO + String.format(PRINT_TOTAL_DISTANCE, distance));
        System.out.println(INFO + String.format(PRINT_TOTAL_TIME, time));
        System.out.println(INFO + PRINT_LINE);
        for (String station : stations) {
            System.out.println(INFO + station);
        }
    }
}
