import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        String separator = File.separator;
        File input = new File("src" + separator + "main" + separator + "resources" + separator + "input.txt");
        Scanner scanner = new Scanner(input);

        int count1 = Integer.parseInt(scanner.nextLine());
        String[] lines1 = new String[count1];
        for (int i = 0; i < lines1.length; i++) {
            lines1[i] = scanner.nextLine();
        }
        List<String> list1 = Arrays.stream(lines1).toList();

        int count2 = Integer.parseInt(scanner.nextLine());
        String[] lines2 = new String[count2];
        for (int i = 0; i < lines2.length; i++) {
            lines2[i] = scanner.nextLine();
        }
        List<String> list2 = Arrays.stream(lines2).toList();
        scanner.close();

        Map<String,String> finish=new HashMap<>();

        if (count1 == 1 && count2 == 1) {
            finish.put(list1.get(0),list2.get(0));
        }

        for (String line1 : list1) {
            for (String line2 : list2) {
                for (String wordsLine1 : line1.split(" ")) {
                    for (String wordsLine2 : line2.split(" ")) {
                        if (findSimilarity(wordsLine1, wordsLine2) > 0.9) {
                            finish.put(line1,line2);
                        }
                    }
                }
            }
        }

        for (String listString : list1) {
            if (!finish.containsKey(listString)&&!finish.containsValue(listString)){
                finish.put(listString,"?");
            }
        }
        for (String listString : list2) {
            if (!finish.containsKey(listString)&&!finish.containsValue(listString)){
                finish.put(listString,"?");
            }
        }

        File output = new File("src" + separator + "main" + separator + "resources" + separator + "output.txt");
        PrintWriter printWriter = new PrintWriter(output);
        for (Map.Entry set : finish.entrySet()) {
            printWriter.println(set.getKey()+" : "+set.getValue());
        }
        printWriter.close();
    }


    public static double findSimilarity(String x, String y) {
        if (x == null && y == null) {
            return 1.0;
        }
        if (x == null || y == null) {
            return 0.0;
        }
        return StringUtils.getJaroWinklerDistance(x, y);
    }

}

