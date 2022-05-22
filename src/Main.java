import java.util.*;
import java.util.concurrent.TransferQueue;

public class Main {
    public static void main(String[] args) {

        ArrayList<ObservationTrain> trainingList = Service.parseTrain("src/bayes_training_set.csv");
        ArrayList<ObservationTest> testList = Service.parseTest("src/bayes_test_set.csv");

        doWePlay(trainingList,testList);
        myOwnExamples(trainingList);





}
static void myOwnExamples(ArrayList<ObservationTrain> trainingList){
    System.out.println();

    System.out.println("Do you want to enter own example? [yes/no]");

    Scanner scanner = new Scanner(System.in);
    boolean flag = scanner.next().equals("yes") ? true : false;

    double allRecordsCount = trainingList.size();
    double yesRecordCount = 0;
    double noRecordCount = 0;

    double[] argumentsCount = new double[trainingList.get(0).values.length];

    for (int i = 0; i < argumentsCount.length; i++){
        argumentsCount[i] = 0;
    }

    for(int i = 0; i < trainingList.get(0).values.length; i++){
        Set<String> noArg = new TreeSet<>();
        for(ObservationTrain observationTrain : trainingList){
            noArg.add(observationTrain.values[i]);
        }
        argumentsCount[i] = noArg.size();
    }

    for(ObservationTrain observationTrain : trainingList){
        if (observationTrain.verdict.equals("tak")){
            yesRecordCount += 1;
        }
        if (observationTrain.verdict.equals("nie")){
            noRecordCount += 1;
        }

    }



    while (flag){

        String[] values = new String[trainingList.get(0).values.length];

        System.out.println("Enter arguments");
        for (int i = 0; i < values.length; i++){
            values[i] = scanner.next();
        }

        double playYes = yesRecordCount/allRecordsCount;
        double playNo = noRecordCount/allRecordsCount;

        double[] specificAmountsYes = new double[values.length];

        for (int i = 0; i < specificAmountsYes.length; i++){
            specificAmountsYes[i] = 0;
        }

        for (ObservationTrain observationTrain : trainingList){
            if(observationTrain.verdict.equals("tak")){

                for (int i = 0; i < specificAmountsYes.length; i++){
                    if (observationTrain.values[i].equals(values[i])){
                        specificAmountsYes[i] += 1;
                    }
                }
            }
        }

        double[] specificAmountsNo = new double[values.length];

        for (int i = 0; i < specificAmountsNo.length; i++){
            specificAmountsNo[i] = 0;
        }

        for (ObservationTrain observationTrain : trainingList){
            if(observationTrain.verdict.equals("nie")){

                for (int i = 0; i < specificAmountsNo.length; i++){
                    if (observationTrain.values[i].equals(values[i])){
                        specificAmountsNo[i] += 1;
                    }
                }
            }
        }

        double[] itemsToMultiplyYes = new double[values.length];

        for(int i = 0; i < itemsToMultiplyYes.length; i++){
            itemsToMultiplyYes[i] = specificAmountsYes[i] / yesRecordCount;
            if(itemsToMultiplyYes[i] == 0){
                itemsToMultiplyYes[i] = (specificAmountsYes[i] + 1) / (yesRecordCount + argumentsCount[i]);
            }
        }

        for(double d : itemsToMultiplyYes){
            playYes = playYes * d;
        }

        double[] itemsToMultiplyNo = new double[values.length];

        for(int i = 0; i < itemsToMultiplyNo.length; i++){
            itemsToMultiplyNo[i] = specificAmountsNo[i] / noRecordCount;
            if(itemsToMultiplyNo[i] == 0){
                itemsToMultiplyNo[i] = (specificAmountsNo[i] + 1) / (noRecordCount + argumentsCount[i]);
            }
        }

        for(double d : itemsToMultiplyNo){
            playNo = playNo * d;
        }

        System.out.println("Probability yes : " + playYes);
        System.out.println("Probability no : " + playNo);

        if (playYes > playNo){
            System.out.println(Arrays.toString(values) + " VERDICT YES");
        }else{
            System.out.println(Arrays.toString(values) + " VERDICT NO");
        }

        System.out.println("Do continue? [yes/no]");
        flag = scanner.next().equals("yes") ? true : false;

    }

    System.out.println("Thank you");

}

static void doWePlay (ArrayList<ObservationTrain> trainingList,ArrayList<ObservationTest> testList){

        double allRecordsCount = trainingList.size();
        double yesRecordCount = 0;
        double noRecordCount = 0;
        double[] argumentsCount = new double[trainingList.get(0).values.length];

        for (int i = 0; i < argumentsCount.length; i++){
            argumentsCount[i] = 0;
        }

        for(int i = 0; i < trainingList.get(0).values.length; i++){
            Set<String> noArg = new TreeSet<>();
            for(ObservationTrain observationTrain : trainingList){
                noArg.add(observationTrain.values[i]);
            }
            argumentsCount[i] = noArg.size();
        }



        for(ObservationTrain observationTrain : trainingList){
            if (observationTrain.verdict.equals("tak")){
                yesRecordCount += 1;
            }
            if (observationTrain.verdict.equals("nie")){
                noRecordCount += 1;
            }

        }






    for(ObservationTest observationTest : testList){
        double playYes = yesRecordCount/allRecordsCount;
        double playNo = noRecordCount/allRecordsCount;

        double[] specificAmountsYes = new double[observationTest.values.length];

        for (int i = 0; i < specificAmountsYes.length; i++){
            specificAmountsYes[i] = 0;
        }

        for (ObservationTrain observationTrain : trainingList){
            if(observationTrain.verdict.equals("tak")){

                for (int i = 0; i < specificAmountsYes.length; i++){
                    if (observationTrain.values[i].equals(observationTest.values[i])){
                    specificAmountsYes[i] += 1;
                    }
                }
            }
        }

        double[] specificAmountsNo = new double[observationTest.values.length];

        for (int i = 0; i < specificAmountsNo.length; i++){
            specificAmountsNo[i] = 0;
        }

        for (ObservationTrain observationTrain : trainingList){
            if(observationTrain.verdict.equals("nie")){

                for (int i = 0; i < specificAmountsNo.length; i++){
                    if (observationTrain.values[i].equals(observationTest.values[i])){
                        specificAmountsNo[i] += 1;
                    }
                }
            }
        }

        double[] itemsToMultiplyYes = new double[observationTest.values.length];

        for(int i = 0; i < itemsToMultiplyYes.length; i++){
            itemsToMultiplyYes[i] = specificAmountsYes[i] / yesRecordCount;
            if(itemsToMultiplyYes[i] == 0){
                itemsToMultiplyYes[i] = (specificAmountsYes[i] + 1) / (yesRecordCount + argumentsCount[i]);
            }
        }

        for(double d : itemsToMultiplyYes){
            playYes = playYes * d;
        }

        double[] itemsToMultiplyNo = new double[observationTest.values.length];

        for(int i = 0; i < itemsToMultiplyNo.length; i++){
            itemsToMultiplyNo[i] = specificAmountsNo[i] / noRecordCount;
            if(itemsToMultiplyNo[i] == 0){
                itemsToMultiplyNo[i] = (specificAmountsNo[i] + 1) / (noRecordCount + argumentsCount[i]);
            }
        }

        for(double d : itemsToMultiplyNo){
            playNo = playNo * d;
        }

        if (playYes > playNo){
            System.out.println(observationTest.toString() + " VERDICT YES");
        }else{
            System.out.println(observationTest.toString() + " VERDICT NO");
        }
    }

}
}
