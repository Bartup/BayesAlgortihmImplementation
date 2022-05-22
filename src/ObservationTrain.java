import java.util.Arrays;

public class ObservationTrain {
    public String verdict;
    public String[] values;

    public ObservationTrain(String verdict, String[] values) {
        this.verdict = verdict;
        this.values = values;
    }

    public String getVerdict() {
        return verdict;
    }

    public String[] getValues() {
        return values;
    }

    @Override
    public String toString() {
        return "ObservationTrain{" +
                "verdict='" + verdict + '\'' +
                ", values=" + Arrays.toString(values) +
                '}';
    }
}
