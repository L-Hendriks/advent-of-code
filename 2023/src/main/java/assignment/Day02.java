package assignment;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static util.FileUtils.FileToList;

public class Day02 {
    private static final List<String> LINES = FileToList("day2-input.txt");
    private static final Integer RED_CUBE_LIMIT = 12;
    private static final Integer GREEN_CUBE_LIMIT = 13;
    private static final Integer BLUE_CUBE_LIMIT = 14;

    public static void main(String[] args){
        solution1();
        solution2();
    }

    private static void solution1() {


        int solution = LINES.stream()
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

    private static void solution2(){
        int solution = LINES.stream()
                .map(line -> Arrays.stream(line
                                .replace(" ", "")
                                .replace("\n", "")
                                .split("([:])"))
                        .collect(Collectors.toList()))
                .mapToInt(line -> {
                    line.removeFirst();
                    int red = maxForColor(line.getFirst(), "red");
                    int blue = maxForColor(line.getFirst(), "blue");
                    int green = maxForColor(line.getFirst(), "green");

                    return red * blue * green;
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

    private static int maxForColor(String lineChucks, String color){
        return Stream.of(lineChucks
                        .replace(";", ",")
                        .split(","))
                .filter(line -> line.contains(color))
                .map(line -> line.replace(color, ""))
                .mapToInt(Integer::parseInt)
                .max().orElse(0);
    }
}
