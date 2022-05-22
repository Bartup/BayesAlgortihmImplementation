import java.util.Arrays;

public class ObservationTest {
    public String[] values;

    public ObservationTest(String[] values) {
        this.values = values;
    }

    public String[] getValues() {
        return values;
    }

    @Override
    public String toString() {
        return
                Arrays.toString(values);

    }
}
