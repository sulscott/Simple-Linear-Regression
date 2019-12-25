import java.util.List;

/**
 * This class demonstrates basic simple linear regression.
 * Regression analysis is commonly used for modeling the relationship between a single dependent variable Y
 * and one or more predictors. When we have one predictor, we call this "simple" linear regression.
 *
 * The expected value of Y is a straight-line function of X. The betas are selected by choosing the line that
 * minimizes the squared distance between each Y value and the line of best fit. The betas are chosen such that
 * they minimize this expression:
 *
 * ∑i (yi – (β0 + β1X))^2
 *
 * There are four assumptions associated with a linear regression model:
 *
 *     Linearity: The relationship between X and the mean of Y is linear.
 *     Homoscedasticity: The variance of the residual is the same for any value of X.
 *     Independence: Observations are independent of each other.
 *     Normality: For any fixed value of X, Y is normally distributed.
 *
 * Other resources:
 * 1. http://sphweb.bumc.bu.edu/otlt/MPH-Modules/BS/R/R5_Correlation-Regression/R5_Correlation-Regression4.html
 * 2. https://towardsdatascience.com/linear-regression-detailed-view-ea73175f6e86
 */
public class SimpleLinearRegression {

    private CSVParser parser = new CSVParser();
    private List<List<Double>> data;
    private List<Double> xData;
    private List<Double> yData;


    public SimpleLinearRegression(String fileName) {
        data = parser.parse(fileName);
        this.xData = data.get(0);
        this.yData = data.get(1);
    }

    /**
     * Returns the expected response {@code y} given the value of the predictor
     * variable {@code x}.
     *
     * @param  x the value of the predictor variable
     * @return the expected response {@code y} given the value of the predictor
     *         variable {@code x}
     */
    public Double predictValue(Double x) {
        Double y = (getSlope(xData, yData) * x) + getYintercept(xData, yData);
        return y;
    }

    /**
     * Returns the mean of the x values determined by the input file.
     * @param xData
     * @return Double of the mean.
     */
    public Double getXBar(List<Double> xData) {
        Double result = Double.valueOf("0");
        for (int i = 0; i < xData.size(); i++) {
            result += xData.get(i);
        }
        return result / xData.size();
    }

    /**
     * Returns the mean of the y values determined by the input file.
     * @param yData
     * @return Double of the mean.
     */
    public Double getYBar(List<Double> yData) {
        Double result = Double.valueOf("0");
        for (int i = 0; i < yData.size(); i++) {
            result += yData.get(i);
        }
        return result / yData.size();
    }

    /**
     * Gets the slope of the data given x and y coordinates
     * that that minimizes the sum of squared residuals of the linear regression model.
     * @param xData
     * @param yData
     * @return Slope of the line of best fit.
     */
    public Double getSlope(List<Double> xData, List<Double> yData) {
        if (xData.size() != yData.size()) {
            System.out.println("Requires input Lists to be the same size.");
            throw new IllegalArgumentException();
        }

        Double xBar = getXBar(xData);
        Double yBar = getYBar(yData);
        Double numeratorSum = Double.valueOf("0");
        Double denominatorSum = Double.valueOf("0");
        for (int i = 0; i < xData.size(); i++) {
            numeratorSum += (xData.get(i) - xBar) * (yData.get(i) - yBar);
            denominatorSum += (xData.get(i) - xBar) * (xData.get(i) - xBar);
        }
        return numeratorSum / denominatorSum;
    }

    /**
     * Returns the y-intercept of the line of best fit.
     * @param xData
     * @param yData
     * @return the y-intercept of the line of best fit.
     */
    public Double getYintercept(List<Double> xData, List<Double> yData) {
        Double xBar = getXBar(xData);
        Double yBar = getYBar(yData);
        Double slope = getSlope(xData, yData);
        Double yIntercept = yBar - (slope * xBar);

        return yIntercept;
    }

//    public static void main(String[] args) {
//        SimpleLinearRegression test = new SimpleLinearRegression("sample_test_score_data.csv");
//        System.out.println(test.data);
//        System.out.println(test.getSlope(test.xData, test.yData));
//        System.out.println(test.getYintercept(test.xData, test.yData));
//        System.out.println(test.predictValue(80.0));
//    }
}
