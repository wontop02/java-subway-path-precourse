package subway.controller;

import java.util.List;
import java.util.Scanner;
import subway.domain.Station;
import subway.service.SubwayService;
import subway.util.InputValidator;
import subway.util.StationParser;
import subway.view.InputView;
import subway.view.OutputView;

public class SubwayController {
    private static final String END = "Q";
    private static final String BACK = "B";

    private final SubwayService service = new SubwayService();

    public void run(Scanner scanner) {
        service.init();
        while (true) {
            if (chooseFunction(scanner)) {
                return;
            }
        }
    }

    private boolean chooseFunction(Scanner scanner) {
        while (true) {
            try {
                OutputView.printMain();
                String function = InputView.requestFunction(scanner);
                InputValidator.validateMainInput(function);
                if (function.equals(END)) {
                    return true;
                }
                chooseCriteria(scanner);
            } catch (Exception e) {
                OutputView.printError(e.getMessage());
            }
        }
    }

    private void chooseCriteria(Scanner scanner) {
        while (true) {
            try {
                OutputView.printCriteria();
                String criteria = InputView.requestFunction(scanner);
                InputValidator.validateCriteriaInput(criteria);
                if (criteria.equals(BACK)) {
                    break;
                }
                findPath(scanner, criteria);
                break;
            } catch (Exception e) {
                OutputView.printError(e.getMessage());
            }
        }
    }

    private void findPath(Scanner scanner, String criteria) {
        try {
            String start = InputView.requestStart(scanner);
            Station startStation = service.findStation(start);
            String end = InputView.requestEnd(scanner);
            InputValidator.validateEndStationInput(start, end);
            Station endStation = service.findStation(end);
            List<Station> path = service.findPath(startStation, endStation, criteria);
            List<Integer> distanceAndTime = service.calculateDistanceAndTime(path);
            List<String> stations = StationParser.toStringList(path);
            OutputView.printResult(stations, distanceAndTime.get(0), distanceAndTime.get(1));
        } catch (Exception e) {
            OutputView.printError(e.getMessage());
        }
    }
}
