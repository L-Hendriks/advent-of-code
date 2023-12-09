package assignment;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static util.FileUtils.FileToList;

public class DayTwo {

    private static final Integer RED_CUBE_LIMIT = 12;
    private static final Integer GREEN_CUBE_LIMIT = 13;
    private static final Integer BLUE_CUBE_LIMIT = 14;

    public static void main(String[] args){
        solution1();
    }

    private static void solution1() {
        List<String> lines = FileToList("day2-input.txt");
        System.out.println(lines);


        int solution = lines.stream()
                .map(line -> Arrays.stream(line
                        .replace(" ", "")
                        .replace("\n", "")
                        .split("([:;])"))
                        .collect(Collectors.toList()))
                .mapToInt(line -> {
                    int game = Integer.parseInt(line.removeFirst().replace("Game", ""));
                    boolean valid = line.stream().allMatch(lineChunck -> {
                        boolean redValid  = countForColor(lineChunck, "red") <= RED_CUBE_LIMIT;
                        boolean greenValid  = countForColor(lineChunck, "green") <= GREEN_CUBE_LIMIT;
                        boolean blueValid  = countForColor(lineChunck, "blue") <= BLUE_CUBE_LIMIT;
                        return redValid && greenValid && blueValid;
                    });
                    return valid ? game : 0;
                })
                .sum();

        System.out.println(solution);
    }

    private static int countForColor(String lineChucks, String color){
        return Stream.of(lineChucks
                .split(","))
                .filter(line -> line.contains(color))
                .map(line -> line.replace(color, ""))
                .mapToInt(Integer::parseInt)
                .sum();
    }
}
