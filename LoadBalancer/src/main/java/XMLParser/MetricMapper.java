package XMLParser;

import java.util.HashMap;
import java.util.Map;

import java.util.HashMap;
import java.util.Map;

public class MetricMapper {

    private static final Map<String, Map<String, int[]>> metricRance = new HashMap<>();
    static {
        // CPU usage ranges
        Map<String, int[]> cpuUsageRanges = new HashMap<>();
        cpuUsageRanges.put("Good", new int[]{0, 60});
        cpuUsageRanges.put("Average", new int[]{61, 90});
        cpuUsageRanges.put("Poor", new int[]{91, 100});
        metricRance.put("CPU usage", cpuUsageRanges);

        // Device up time ranges
        Map<String, int[]> deviceUptimeRanges = new HashMap<>();
        deviceUptimeRanges.put("Good", new int[]{0, 3});
        deviceUptimeRanges.put("Average", new int[]{4, 6});
        deviceUptimeRanges.put("Poor", new int[]{7, 100});
        metricRance.put("Device up time", deviceUptimeRanges);

        // WiFi receive rate ranges
        Map<String, int[]> wifiReceiveRateRanges = new HashMap<>();
        wifiReceiveRateRanges.put("Good", new int[]{50, 500});
        wifiReceiveRateRanges.put("Average", new int[]{10, 49});
        wifiReceiveRateRanges.put("Poor", new int[]{0, 9});
        metricRance.put("WiFi receive rate", wifiReceiveRateRanges);

        // CSAT ranges
        Map<String, int[]> csatRanges = new HashMap<>();
        csatRanges.put("Good", new int[]{4, 5});
        csatRanges.put("Average", new int[]{2, 3});
        csatRanges.put("Poor", new int[]{0, 1});
        metricRance.put("CSAT", csatRanges);

        // Response time ranges
        Map<String, int[]> responseTimeRanges = new HashMap<>();
        responseTimeRanges.put("Good", new int[]{0, 2});
        responseTimeRanges.put("Average", new int[]{3, 4});
        responseTimeRanges.put("Poor", new int[]{4, 100});
        metricRance.put("Response time", responseTimeRanges);

        // Number of crashes ranges
        Map<String, int[]> numberOfCrashesRanges = new HashMap<>();
        numberOfCrashesRanges.put("Good", new int[]{0, 0});
        numberOfCrashesRanges.put("Average", new int[]{1, 3});
        numberOfCrashesRanges.put("Poor", new int[]{4, 100});
        metricRance.put("Number of crashes", numberOfCrashesRanges);
    }

    // Define the new ranges for all metrics
    private static final Map<String, int[]> newRanges = new HashMap<>();
    static {
        newRanges.put("Good", new int[]{76, 100});
        newRanges.put("Average", new int[]{46, 75});
        newRanges.put("Poor", new int[]{0, 45});
    }

    // Define the directions for each metric
    private static final Map<String, String> metricDirections = new HashMap<>();
    static {
        metricDirections.put("CPU usage", "Minimize");
        metricDirections.put("Device up time", "Minimize");
        metricDirections.put("WiFi receive rate", "Maximize");
        metricDirections.put("CSAT", "Maximize");
        metricDirections.put("Response time", "Minimize");
        metricDirections.put("Number of crashes", "Minimize");
    }


    public static double mapValue(String metric, double value) {
        // Get the ranges for the metric
        Map<String, int[]> ranges = metricRance.get(metric);

        // Determine which range the value falls into (Good, Average, or Poor)
        String range = "Poor";
        for (Map.Entry<String, int[]> entry : ranges.entrySet()) {
            int[] bounds = entry.getValue();
            if (value >= bounds[0] && value <= bounds[1]) {
                range = entry.getKey();
                break;
            }
        }

        // Get the bounds for the determined range
        int[] bounds = ranges.get(range);

        // Get the new bounds for the metric
        int[] newBounds = newRanges.get(range);

        // Get the direction for the metric
        String direction = metricDirections.get(metric);

        // Calculate the mapped value based on the range and the provided formula
        double oldRange = bounds[1] - bounds[0];
        double newRange = newBounds[1] - newBounds[0];
        temp = newBounds;
        double newValue;
        if (direction.equals("Maximize")) {
            newValue = newBounds[0] + ((value - bounds[0]) / oldRange) * newRange;
        } else { // "Minimize"
            newValue = newBounds[1] - ((value - bounds[0]) / oldRange) * newRange;
        }
        return newValue;
    }

    private static int[] temp;

    public static void main(String[] args) {
        String metric = "Device up time";

        for (int i = 150; i <= 150; ++i) {
            double mappedValue = mapValue(metric, i);
            if (mappedValue < temp[0])
                continue;

            System.out.println("Value: " + i + " -> " + mappedValue);
        }
    }
}

