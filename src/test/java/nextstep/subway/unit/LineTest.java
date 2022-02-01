package nextstep.subway.unit;

import nextstep.subway.domain.Line;
import nextstep.subway.domain.Section;
import nextstep.subway.domain.Station;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class LineTest {
    @DisplayName("구간 목록 마지막에 새로운 구간을 추가할 경우")
    @Test
    void addSection() {
        // given
        Station upStation = new Station("가양역");
        Station downStation = new Station("증미역");
        Line line = new Line("9호선", "금색");
        int distance = 10;

        Section section = new Section(line, upStation, downStation, distance);

        // when
        line.addSection(section);

        // then
        List<Section> sections = line.getSections();
        assertThat(sections.size()).isEqualTo(1);
        assertThat(sections.get(0).getUpStation()).isEqualTo(upStation);
        assertThat(sections.get(0).getDownStation()).isEqualTo(downStation);
        assertThat(sections.get(0).getDistance()).isEqualTo(distance);
    }

    @DisplayName("노선에 속해있는 역 목록 조회")
    @Test
    void getStations() {
        // given
        Station 가양역 = new Station("가양역");
        Station 증미역 = new Station("증미역");
        Station 등촌역 = new Station("등촌역");
        Line line = new Line("9호선", "금색");
        int distance1 = 10;
        int distance2 = 20;

        line.addSection(new Section(line, 가양역, 증미역, distance1));
        line.addSection(new Section(line, 증미역, 등촌역, distance2));

        // when
        List<Station> stations = line.getStations();

        // then
        List<Station> exceptedStations = Arrays.asList(가양역, 증미역, 등촌역);
        assertThat(stations).isEqualTo(exceptedStations);
    }

    @DisplayName("구간이 목록에서 마지막 역 삭제")
    @Test
    void removeSection() {
        // given
        Station 가양역 = new Station("가양역");
        Station 증미역 = new Station("증미역");
        Station 등촌역 = new Station("등촌역");
        Line line = new Line("9호선", "금색");
        int distance1 = 10;
        int distance2 = 20;

        line.addSection(new Section(line, 가양역, 증미역, distance1));
        line.addSection(new Section(line, 증미역, 등촌역, distance2));

        // when
        line.removeSection(등촌역);

        // then
        List<Section> sections = line.getSections();
        assertThat(sections.size()).isEqualTo(1);
        assertThat(sections.get(0).getDownStation()).isEqualTo(증미역);
    }
}
