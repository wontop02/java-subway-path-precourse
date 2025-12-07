# 🖥️ 우테코 프리코스 3주차 - 로또

- 기능 요구 사항에 기재되지 않은, 스스로 판단한 부분은 **볼드체** 사용

## 📖 프로젝트 개요

- 지하철 노선도에서 경로 조회하는 기능 구현
- 다음 사전 등록 정보로 반드시 초기 설정을 해야 함

```
1. 지하철역으로 교대역, 강남역, 역삼역, 남부터미널역, 양재역, 양재시민의숲역, 매봉역이 등록되어 있다.
2. 지하철 노선으로 2호선, 3호선, 신분당선이 등록되어 있다.
3. 노선에 역이 아래와 같이 등록되어 있다.(왼쪽 끝이 상행 종점)
- 2호선: 교대역 - ( 2km / 3분 ) - 강남역 - ( 2km / 3분 ) - 역삼역
- 3호선: 교대역 - ( 3km / 2분 ) - 남부터미널역 - ( 6km / 5분 ) - 양재역 - ( 1km / 1분 ) - 매봉역
- 신분당선: 강남역 - ( 2km / 8분 ) - 양재역 - ( 10km / 3분 ) - 양재시민의숲역
``` 

- 기대하는 출력 결과는 "[INFO]"를 붙여서 출력
- 사용자가 잘못된 값을 입력할 경우 `IllegalArgumentException`을 발생시키고,
  "[ERROR]"로 시작하는 에러 메시지 출력 후 그 부분부터 다시 입력 받음

## ⌨️ 입출력 요구 사항

### 입력

- 메인 화면: 원하는 기능 선택
    - "1"을 입력하면 경로 조회
    - "Q"를 입력하면 종료
    - 다른 문자열을 입력할 경우 예외 발생
- 경로 기준: 원하는 기능 선택
    - "1"을 입력하면 최단 거리
    - "2"를 입력하면 최소 시간
    - "B"를 입력하면 돌아가기
    - 다른 문자열을 입력할 경우 예외 발생
- 출발역
    - 존재하지 않은 역일 경우 예외 발생
- 도착역
    - 존재하지 않은 역일 경우 예외 발생
    - 출발역과 도착역이 같으면 예외 발생
    - 출발역과 도착역이 연결되어 있지 않으면 예외 발생

### 출력

- 메인 화면
- 경로 기준
- 조회 결과
    - "[INFO]"를 붙여서 출력
- 에러 메시지
    - "[ERROR]"를 붙여서 출력

### 실행 결과 예시

- 경로 조회

```
## 메인 화면
1. 경로 조회
Q. 종료

## 원하는 기능을 선택하세요.
1

## 경로 기준
1. 최단 거리
2. 최소 시간
B. 돌아가기

## 원하는 기능을 선택하세요.
1

## 출발역을 입력하세요.
교대역

## 도착역을 입력하세요.
양재역

## 조회 결과
[INFO] ---
[INFO] 총 거리: 4km
[INFO] 총 소요 시간: 11분
[INFO] ---
[INFO] 교대역
[INFO] 강남역
[INFO] 양재역

## 메인 화면
1. 경로 조회
Q. 종료

...
```

- 에러 출력

```
## 메인 화면
1. 경로 조회
Q. 종료

## 원하는 기능을 선택하세요.
1

## 경로 기준
1. 최단 거리
2. 최소 시간 
B. 돌아가기

## 원하는 기능을 선택하세요.
1

## 출발역을 입력하세요.
강남역

## 도착역을 입력하세요.
강남역

[ERROR] 출발역과 도착역이 동일합니다.

## 경로 기준
1. 최단 거리
2. 최소 시간 
B. 돌아가기

## 원하는 기능을 선택하세요.

...
```

## 📜 프로그래밍 요구사항

### Application

- Application 클래스를 활용해 구현해야 한다.
- Application의 패키지 구조는 변경하지 않는다.
- Application 클래스에 있는 Scanner를 사용하고 별도의 Scanner 객체를 만들지 않는다.

### Station, Line

- Station, Line 클래스를 활용하여 지하철역과 노선을 구현해야 한다.
- 제공하는 각 클래스의 기본 생성자를 추가할 수 없다.
- 필드(인스턴스 변수)인 name의 접근 제어자 private을 변경할 수 없다.
- 가능하면 setter 메소드(ex. setXXX)를 추가하지 않고 구현한다.

### StationRepository, LineRepository

- Station과 Line의 상태를 저장할 수 있는 StationRepository, LineRepository를 제공한다.
- 필요 시 StationRepository, LineRepository 이 외 추가로 Repository를 만들 수 있다.
- 추가로 생성되는 객체에 대해서 XXXRepository 네이밍으로 저장 클래스를 추가한다.
- 객체들의 상태를 관리하기 위해서 XXXRepository 클래스를 활용해 저장 로직을 구현해야 한다.
- 작성된 메서드는 수정할 수 없고, 필요에 따라 메서드를 자유롭게 추가할 수 있다.

## 힌트

- jgrapht 라이브러리를 활용하면 간편하게 최단거리를 조회할 수 있음
- Dijkstra 알고리즘을 반드시 이해할 필요는 없고 미션에 적용할 정도로만 이해하면 됨
- JGraphtTest 클래스의 테스트를 활용하여 미션에 필요한 라이브러리의 기능을 학습할 수 있음
- 정점(vertex)과 간선(edge), 그리고 가중치 개념을 이용
    - 정점: 지하철역
    - 간선: 지하철역 연결정보
    - 가중치: 거리 or 소요 시간
- 최단 거리 기준 조회 시 가중치를 거리로 설정

```
@Test
public void getDijkstraShortestPath() {
    WeightedMultigraph<String, DefaultWeightedEdge> graph
            = new WeightedMultigraph(DefaultWeightedEdge.class);
    graph.addVertex("v1");
    graph.addVertex("v2");
    graph.addVertex("v3");
    graph.setEdgeWeight(graph.addEdge("v1", "v2"), 2);
    graph.setEdgeWeight(graph.addEdge("v2", "v3"), 2);
    graph.setEdgeWeight(graph.addEdge("v1", "v3"), 100);

    DijkstraShortestPath dijkstraShortestPath = new DijkstraShortestPath(graph);
    List<String> shortestPath = dijkstraShortestPath.getPath("v3", "v1").getVertexList();

    assertThat(shortestPath.size()).isEqualTo(3);
}
```

### 최단 경로 라이브러리

## ✅ 구현 기능 목록

### 지하철역, 노선 구현

- 지하철역
    - 교대역, 강남역, 역삼역, 남부터미널역, 양재역, 양재시민의숲역, 매봉역
- 노선
    - 2호선, 3호선, 신분당선
        - 2호선: 교대역 - ( 2km / 3분 ) - 강남역 - ( 2km / 3분 ) - 역삼역
        - 3호선: 교대역 - ( 3km / 2분 ) - 남부터미널역 - ( 6km / 5분 ) - 양재역 - ( 1km / 1분 ) - 매봉역
        - 신분당선: 강남역 - ( 2km / 8분 ) - 양재역 - ( 10km / 3분 ) - 양재시민의숲역

### 메인 화면 출력

```
## 메인 화면
1. 경로 조회
Q. 종료
```

### 메인 화면 - 원하는 기능 입력

- 사용자에게 입력값 요청
    - `camp.nextstep.edu.missionutils.Console`의 `readLine()` 활용
- 입력값 검증: 잘못된 값일 경우 `IllegalArgumentException` 발생 및 재입력 요청
    - "1", "Q"를 제외한 문자열을 입력한 경우

### 경로 기준 출력

```
## 경로 기준
1. 최단 거리
2. 최소 시간
B. 돌아가기
```

### 경로 기준 - 원하는 기능 입력

- 사용자에게 입력값 요청
    - `camp.nextstep.edu.missionutils.Console`의 `readLine()` 활용
- 입력값 검증: 잘못된 값일 경우 `IllegalArgumentException` 발생 및 재입력 요청
    - "1", "2", "B"를 제외한 문자열을 입력한 경우

### 출발역 입력

- 사용자에게 입력값 요청
    - `camp.nextstep.edu.missionutils.Console`의 `readLine()` 활용
- 입력값 검증: 잘못된 값일 경우 `IllegalArgumentException` 발생 및 재입력 요청
    - 존재하지 않은 역을 입력한 경우

### 도착역 입력

- 사용자에게 입력값 요청
    - `camp.nextstep.edu.missionutils.Console`의 `readLine()` 활용
- 입력값 검증: 잘못된 값일 경우 `IllegalArgumentException` 발생 및 재입력 요청
    - 존재하지 않은 역을 입력한 경우
    - 출발역과 동일한 역을 입력한 경우
    - 출발역과 연결되어 있지 않은 역을 입력한 경우

### 경로 조회

- 사용자가 선택한 경로 기준에 따라 경로 조회
    - jgrapht 라이브러리를 활용해 최단거리 조회

### 조회 결과 출력

- "[INFO]"를 포함해 조회 결과 출력

```
## 조회 결과
[INFO] ---
[INFO] 총 거리: 4km
[INFO] 총 소요 시간: 11분
[INFO] ---
[INFO] 교대역
[INFO] 강남역
[INFO] 양재역
```
