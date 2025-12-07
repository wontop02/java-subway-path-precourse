package subway.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SectionRepository {
    private static final String NOT_FOUND_STATION = "해당 섹션을 찾을 수 없습니다.";

    private static final List<Section> sections = new ArrayList<>();

    public static List<Section> sections() {
        return Collections.unmodifiableList(sections);
    }

    public static void addSection(Section section) {
        sections.add(section);
    }

    public static void deleteAll() {
        sections.clear();
    }

    public static Section findByStations(Station firstStation, Station secondStation) {
        Section section = sections.stream()
                .filter(l -> l.getUpStation().equals(firstStation) && l.getDownStation().equals(secondStation))
                .findFirst()
                .orElse(null);
        if (section == null) {
            section = sections.stream()
                    .filter(l -> l.getUpStation().equals(secondStation) && l.getDownStation().equals(firstStation))
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException(NOT_FOUND_STATION));
        }
        return section;
    }
}
