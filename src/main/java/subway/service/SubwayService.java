package subway.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;
import subway.domain.Line;
import subway.domain.LineRepository;
import subway.domain.Section;
import subway.domain.SectionRepository;
import subway.domain.Station;
import subway.domain.StationRepository;

public class SubwayService {
    private static final Map<String, List<String>> STATIONS =
            Map.of(
                    "2호선", List.of("교대역", "강남역", "역삼역"),
                    "3호선", List.of("교대역", "남부터미널역", "양재역", "매봉역"),
                    "신분당선", List.of("강남역", "양재역", "양재시민의숲역"));

    public void init() {
        for (Map.Entry<String, List<String>> entry : STATIONS.entrySet()) {
            List<Station> stations = entry.getValue().stream()
                    .map(Station::new)
                    .collect(Collectors.toList());
            stations.forEach(StationRepository::addStation);
        }
        lineInit();
    }

    public void lineInit() {
        Station gyodae = StationRepository.findByName("교대역");
        Station gangnam = StationRepository.findByName("강남역");
        Station yeoksam = StationRepository.findByName("역삼역");
        Station nambuTerminal = StationRepository.findByName("남부터미널역");
        Station yangjae = StationRepository.findByName("양재역");
        Station maebong = StationRepository.findByName("매봉역");
        Station yangjaeForest = StationRepository.findByName("양재시민의숲역");

        Line line2 = new Line("2호선",
                List.of(
                        new Section(gyodae, gangnam, 2, 3),
                        new Section(gangnam, yeoksam, 2, 3)));
        LineRepository.addLine(line2);
        line2.getSections()
                .forEach(SectionRepository::addSection);

        Line line3 = new Line("3호선",
                List.of(
                        new Section(gyodae, nambuTerminal, 3, 2),
                        new Section(nambuTerminal, yangjae, 6, 5),
                        new Section(yangjae, maebong, 1, 1)));
        LineRepository.addLine(line3);
        line3.getSections()
                .forEach(SectionRepository::addSection);

        Line lineSinbundang = new Line("신분당선",
                List.of(
                        new Section(gangnam, yangjae, 2, 8),
                        new Section(yangjae, yangjaeForest, 10, 3)));
        LineRepository.addLine(lineSinbundang);
        lineSinbundang.getSections()
                .forEach(SectionRepository::addSection);
    }

    public List<Station> findPath(Station startStation, Station endStation, String criteria) {
        DijkstraShortestPath dijkstraShortestPath;
        if (criteria.equals("1")) {
            dijkstraShortestPath = new DijkstraShortestPath(makeDistanceGraph());
            return dijkstraShortestPath.getPath(startStation, endStation).getVertexList();
        }
        dijkstraShortestPath = new DijkstraShortestPath(makeTimeGraph());
        return dijkstraShortestPath.getPath(startStation, endStation).getVertexList();
    }

    private WeightedMultigraph<Station, DefaultWeightedEdge> makeDistanceGraph() {
        WeightedMultigraph<Station, DefaultWeightedEdge> graph
                = new WeightedMultigraph(DefaultWeightedEdge.class);
        for (Section section : SectionRepository.sections()) {
            Station upStation = section.getUpStation();
            Station downStation = section.getDownStation();
            graph.addVertex(upStation);
            graph.addVertex(downStation);
            graph.setEdgeWeight(graph.addEdge(upStation, downStation), section.getDistance());
        }
        return graph;
    }

    private WeightedMultigraph<Station, DefaultWeightedEdge> makeTimeGraph() {
        WeightedMultigraph<Station, DefaultWeightedEdge> graph
                = new WeightedMultigraph(DefaultWeightedEdge.class);
        for (Section section : SectionRepository.sections()) {
            Station upStation = section.getUpStation();
            Station downStation = section.getDownStation();
            graph.addVertex(upStation);
            graph.addVertex(downStation);
            graph.setEdgeWeight(graph.addEdge(upStation, downStation), section.getTime());
        }
        return graph;
    }

    public List<Integer> calculateDistanceAndTime(List<Station> stations) {
        List<Integer> distanceAndTime = new ArrayList<>();
        int distance = 0;
        int time = 0;
        for (int i = 0; i < stations.size() - 1; i++) {
            Section section = SectionRepository.findByStations(stations.get(i), stations.get(i + 1));
            distance += section.getDistance();
            time += section.getTime();
        }
        distanceAndTime.add(distance); // index 0은 거리
        distanceAndTime.add(time); // 1은 시간
        return distanceAndTime;
    }

    public Station findStation(String station) {
        return StationRepository.findByName(station);
    }
}
