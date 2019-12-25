import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CSVParser {

    public static final String UTF8_BOM = "\uFEFF";

    public List<List<Double>> parse(String fileName) {
        Scanner inputStream;
        List<List<Double>> finalList = new ArrayList<>();
        try
        {
            File file = new File(fileName);

            // we don't know the amount of data ahead of time so we use lists
            List<Double> x = new ArrayList<>();
            List<Double> y = new ArrayList<>();

            inputStream = new Scanner(file);
            while (inputStream.hasNext())
            {
                String data = inputStream.next();
                String [] arr = data.split(",");

                x.add(Double.parseDouble(removeUTF8BOM(arr[0])));
                y.add(Double.parseDouble(removeUTF8BOM(arr[1])));
            }

            // Covert the lists to double arrays
            List<Double> xData = new ArrayList<>();
            List<Double> yData = new ArrayList<>();

            for (int i = 0; i < x.size(); i++)
            {
                xData.add(x.get(i));
            }

            for (int i = 0; i < y.size(); i++)
            {
                yData.add(y.get(i));
            }
            finalList = Arrays.asList(xData, yData);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return finalList;
    }

    /**
     * Java doesn't handle BOM characters very well.
     * This method removes any BOM characters preceding a string.
     * @param s The string to be parsed.
     * @return A string with no leading BOM characters.
     */
    private static String removeUTF8BOM(String s) {
        if (s.startsWith(UTF8_BOM)) {
            s = s.substring(1);
        }
        return s;
    }

}
