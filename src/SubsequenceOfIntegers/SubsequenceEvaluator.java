package SubsequenceOfIntegers;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SubsequenceEvaluator {

    static Path filePath = Paths.get("src/SubsequenceOfIntegers/inputData.txt");
    private static final List<String> inputData = readFile(filePath);


    public static void main(String[] args) {
        List<Integer> dataConvertedToIntegers = splitIntoIntegers();
        int[] sequence = {1, 6, -1, 10,};
        System.out.println(isSubsequence(0, 0, dataConvertedToIntegers, sequence));

    }

    private static boolean isSubsequence(int entryPointData, int entryPointSequence, List<Integer> dataToGoThrough, int[] sequence){
        // pokud je "sequence" delší než dataToGoThrough, nemůže být sequence podposloupností
        if (sequence.length > dataToGoThrough.size()) {
            return false;
        }
        // pro každý int z "sequence" se projde "dataToGoThrough"; začíná se od nuly, pak se rekurzivně prochází od
        // nalezené shody (entryPointData)
        // od bodu v dataToGoThrough, kde se našla shoda
        // pokud se shoda nenajde, for-loop se vůbec nespustí a vrátí se false
        for (int i = entryPointData; i < dataToGoThrough.size(); i++) {
            // sequence se začne kontrolovat od prvního,
            // při iteraci rekurze se index posune o jedna nahoru
            if (sequence[entryPointSequence] == dataToGoThrough.get(i)){
                // tady se posouvá index
                entryPointSequence++;
                // když se zvýšením indexu dostaneme mimo "sequence", platí, že i jeho poslední prvek je
                // v podposloupnosi, takže se vrátí "true"
                if (entryPointSequence >= sequence.length) {
                    return true;
                }
                //když nejsme na konci, zavolá se rekurze;
                isSubsequence(i, entryPointSequence, dataToGoThrough, sequence);
            }
        }
        //když se dojde na konec dataToGoThrough a nenajde se v nich prvek z sequence, na kterém se pracuje, tak
        // vracíme false, protože to evidentně nemůže být podposloupnost
        return false;
    }


    private static List<String> readFile(Path inputPath) {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(inputPath, StandardCharsets.UTF_8);
        } catch (IOException exception) {
            exception.printStackTrace();
            System.out.println("The input file " + filePath + " is missing. Exiting.");
            System.exit(1);
        }
        return lines;
    }

    // všechny řádky se rozsekají na integery a strčí do Listu integerů
    private static List<Integer> splitIntoIntegers(){
        List<Integer> integerList = new ArrayList<>();
        for (String line :
                SubsequenceEvaluator.inputData) {
            String[] values = line.split(",");
            for (String s :
                    values) {
                integerList.add(Integer.parseInt(s));
            }
        }
        return integerList;
    }

}



