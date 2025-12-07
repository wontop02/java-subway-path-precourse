package subway.util;

import java.util.List;
import subway.domain.Station;

public class StationParser {
    private StationParser() {
    }

    public static List<String> toStringList(List<Station> stations) {
        return stations.stream()
                .map(Station::getName)
                .toList();
    }
}
