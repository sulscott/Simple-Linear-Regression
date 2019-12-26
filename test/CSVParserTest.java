package datascience;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.mockito.Mockito.*;

public class CSVParserTest {
    private List<Double> xData = Arrays.asList(95.0, 85.0, 80.0, 70.0, 60.0);
    private List<Double> yData = Arrays.asList(85.0, 95.0, 70.0, 65.0, 70.0);
    private List<List<Double>> data;
    private CSVParser parser = new CSVParser();

    @Test
    public void parse_givenInputFile_returnsCorrectData() {
        // GIVEN
        data = new ArrayList<>(Arrays.asList(xData, yData));

        // WHEN
        List<List<Double>> actualData = parser.parse("/Users/sulscott/Documents/sample_data/sample_test_score_data.csv");

        // THEN
        assertEquals(data, actualData, "Data output is unexpected");
    }

}
