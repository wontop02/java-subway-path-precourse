package subway.view;

import java.util.Scanner;

public class InputView {
    private static final String REQUEST_FUNCTION =
            "\n## 원하는 기능을 선택하세요.";

    private static final String REQUEST_START =
            "\n## 출발역을 입력하세요.";

    private static final String REQUEST_END =
            "\n## 도착역을 입력하세요.";

    private InputView() {
    }

    public static String requestFunction(Scanner scanner) {
        System.out.println(REQUEST_FUNCTION);
        return scanner.nextLine();
    }

    public static String requestStart(Scanner scanner) {
        System.out.println(REQUEST_START);
        return scanner.nextLine();
    }

    public static String requestEnd(Scanner scanner) {
        System.out.println(REQUEST_END);
        return scanner.nextLine();
    }
}
