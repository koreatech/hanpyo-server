package com.github.hanpyo.util;

import com.github.hanpyo.test.MockTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;

class TimeParserTest extends MockTest {

    @Test
    @DisplayName("포털에서 받아온 시간 데이터를 규격에 맞는 String으로 변환 가능하다")
    public void parseTimeStringTest() {
        // given
        String mockData = "월08A~09B,화08A~09B";
        String mockData2 = "월07A~07B,08A~09B";

        // when
        String result = TimeParser.parseTimeString(mockData);
        String result2 = TimeParser.parseTimeString(mockData2);
        String result3 = TimeParser.parseTimeString(null);

        // then
        then(result).isEqualTo("[{start:960, end:1080}, {start:2400, end:2520}]");
        then(result2).isEqualTo("[{start:900, end:960}, {start:960, end:1080}]");
        then(result3).isEqualTo("");
    }
}