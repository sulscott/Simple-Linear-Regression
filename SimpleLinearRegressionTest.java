package datascience;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.mockito.Mockito.*;

class SimpleLinearRegressionTest {

    private List<Double> xData = Arrays.asList(95.0, 85.0, 80.0, 70.0, 60.0);
    private List<Double> yData = Arrays.asList(85.0, 95.0, 70.0, 65.0, 70.0);
    private List<List<Double>> data = new ArrayList<>(Arrays.asList(xData, yData));

    @Mock
    private CSVParser parser;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    public void getXBar_givenTestData_returnsCorrectXBar() {
        // GIVEN
        when(parser.parse(any(String.class))).thenReturn(data);
        SimpleLinearRegression slr = new SimpleLinearRegression("test", parser);
        Double expectedXBar = 78.0;

        // WHEN
        Double actualXBar = slr.getXBar();

        // THEN
        assertEquals(expectedXBar, actualXBar, "Incorrect xBar is being calculated");
    }

    @Test
    public void getYBar_givenTestData_returnsCorrectYBar() {
        // GIVEN
        when(parser.parse(any(String.class))).thenReturn(data);
        SimpleLinearRegression slr = new SimpleLinearRegression("test", parser);
        Double expectedYBar = 77.0;

        // WHEN
        Double actualYBar = slr.getYBar();

        // THEN
        assertEquals(expectedYBar, actualYBar, "Incorrect yBar is being calculated");
        verify(parser).parse(any(String.class));
    }

    @Test
    public void getSlope_givenTestData_returnsCorrectSlope() {
        // GIVEN
        when(parser.parse(any(String.class))).thenReturn(data);
        SimpleLinearRegression slr = new SimpleLinearRegression("test", parser);
        Double expectedSlope = 0.6438356164383562;

        // WHEN
        Double actualSlope = slr.getSlope();

        // THEN
        assertEquals(expectedSlope, actualSlope, "Incorrect slope is being calculated.");
        verify(parser).parse(any(String.class));
    }

    @Test
    public void getSlope_givenTestDataWithListsOfDifferentSizes_throwsException() {
        // GIVEN
        List<Double> xData = Arrays.asList(95.0, 85.0, 80.0, 70.0, 60.0);
        List<Double> yData = Arrays.asList(85.0, 95.0, 70.0, 65.0);
        List<List<Double>> data = new ArrayList<>(Arrays.asList(xData, yData));
        when(parser.parse(any(String.class))).thenReturn(data);
        SimpleLinearRegression slr = new SimpleLinearRegression("test", parser);

        // WHEN + THEN
        assertThrows(IllegalArgumentException.class, () -> {
            slr.getSlope();
        }, "Should throw an IllegalArgumentException when input arrays are of different length");
        verify(parser).parse(any(String.class));
    }

    @Test
    public void getYIntercept_givenTestData_returnsCorrectYIntercept() {
        // GIVEN
        when(parser.parse(any(String.class))).thenReturn(data);
        SimpleLinearRegression slr = new SimpleLinearRegression("test", parser);
        Double expectedIntercept = 26.78082191780822;

        // WHEN
        Double actualIntercept = slr.getYintercept();

        // THEN
        assertEquals(expectedIntercept, actualIntercept, "Incorrect y-intercept is being calculated");
    }

    @Test
    public void predictValue_givenTestDataAndPredictorVariable() {
        // GIVEN
        when(parser.parse(any(String.class))).thenReturn(data);
        SimpleLinearRegression slr = new SimpleLinearRegression("test", parser);
        Double expectedOutput = 78.2876712328767;
        Double predictorVariable = 80.0;

        // WHEN
        Double actualOutput = slr.predictValue(predictorVariable);

        // THEN
        assertEquals(expectedOutput, actualOutput, "Incorrect output based on input data");
    }
}