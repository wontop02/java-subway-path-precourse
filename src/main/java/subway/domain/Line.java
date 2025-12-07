package subway.domain;

import java.util.Collections;
import java.util.List;

public class Line {
    private String name;
    private List<Section> sections;

    public Line(String name, List<Section> sections) {
        this.name = name;
        this.sections = sections;
    }

    public String getName() {
        return name;
    }

    public List<Section> getSections() {
        return Collections.unmodifiableList(sections);
    }

    // 추가 기능 구현
}
