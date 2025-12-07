package subway.domain;

public class Section {
    private final Station upStation;
    private final Station downStation;
    private final int distance;
    private final int time;

    public Section(Station upStation, Station downStation, int distance, int time) {
        this.upStation = upStation;
        this.downStation = downStation;
        this.distance = distance;
        this.time = time;
    }

    public Station getUpStation() {
        return upStation;
    }

    public Station getDownStation() {
        return downStation;
    }

    public int getDistance() {
        return distance;
    }

    public int getTime() {
        return time;
    }
}
