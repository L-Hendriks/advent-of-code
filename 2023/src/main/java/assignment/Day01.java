package assignment;


import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static util.FileUtils.FileToList;

public class Day01 {

    private static final Pattern PATTERN = Pattern.compile("\\d");
    private static final List<String> LINES = FileToList("day1-input.txt");
    private static final Map<String, String> TRANSLATION_MAP = Map.of(
            "one", "1",
            "two", "2",
            "three", "3",
            "four", "4",
            "five", "5",
            "six", "6",
            "seven", "7",
            "eight", "8",
            "nine", "9"
            );

    public static void main(String[] args){
        solution1();
        solution2();
    }

    private static void solution1(){
        int solution = LINES.stream()
                .map(line -> PATTERN.matcher(line).results().collect(Collectors.toList()))
                .map(line -> line.getFirst().group() + line.getLast().group())
                .mapToInt(Integer::parseInt)
                .sum();

        System.out.println(solution);
    }

    private static void solution2(){
        Pattern startFromBeginning = Pattern.compile("(?=\\d|one|two|three|four|five|six|seven|eight|nine)");
        Pattern startFromEnd = Pattern.compile("(?<=\\d|one|two|three|four|five|six|seven|eight|nine)");

        int solution = LINES.stream().mapToInt(line -> {
            int number1StartIndex = startFromBeginning.matcher(line).results().toList().getFirst().start();
            int number1EndIndex = startFromEnd.matcher(line).results().toList().getFirst().end();
            int number2StartIndex = startFromBeginning.matcher(line).results().toList().getLast().start();
            int number2EndIndex = startFromEnd.matcher(line).results().toList().getLast().end();
            String number1 = translate(line.substring(number1StartIndex, number1EndIndex));
            String number2 = translate(line.substring(number2StartIndex, number2EndIndex));
            return Integer.parseInt(number1 + number2);
        }).sum();

        System.out.println(solution);
    }

    private static String translate(String line){
        if(line.matches(PATTERN.pattern())){
            return line;
        }

        return TRANSLATION_MAP.get(line);
    }
}
