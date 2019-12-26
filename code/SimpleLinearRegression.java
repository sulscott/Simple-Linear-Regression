package datascience;

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

    private List<List<Double>> data;
    private List<Double> xData;
    private List<Double> yData;


    public SimpleLinearRegression(String fileName, CSVParser parser) {
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
     * @param data
     * @return Double of the mean.
     */
    public Double getXBar(List<List<Double>> data) {
        Double result = data.get(0).stream().mapToDouble(val -> val).average().orElse(0.0);
        return result;
    }

    /**
     * Returns the mean of the y values determined by the input file.
     * @param data
     * @return Double of the mean.
     */
    public Double getYBar(List<List<Double>> data) {
        Double result = data.get(1).stream().mapToDouble(val -> val).average().orElse(0.0);
        return result;
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

        Double xBar = getXBar(data);
        Double yBar = getYBar(data);
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
        Double xBar = getXBar(data);
        Double yBar = getYBar(data);
        Double slope = getSlope(xData, yData);
        Double yIntercept = yBar - (slope * xBar);

        return yIntercept;
    }

//     public static void main(String[] args) {
//         CSVParser parser = new CSVParser();
//         SimpleLinearRegression test = new SimpleLinearRegression("/Users/sulscott/Documents/sample_data/sample_test_score_data.csv", parser);
//         System.out.println(test.data);
//         System.out.println(test.getSlope(test.xData, test.yData));
//         System.out.println(test.getYintercept(test.xData, test.yData));
//         System.out.println(test.predictValue(80.0));
    }
}
