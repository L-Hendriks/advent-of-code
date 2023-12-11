package assignment;

import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static util.FileUtils.FileToList;

public class Day04 {
    private static final Pattern PATTERN = Pattern.compile("\\d+|\\|");

    private static final List<String> LINES = FileToList("day4-input.txt");

    public static void main(String[] args) {
        solution1();
        solution2();
    }

    private static void solution1(){
        int solution = LINES.stream()
                .map(line -> PATTERN.matcher(line)
                        .results()
                        .map(MatchResult::group)
                        .collect(Collectors.toList()))
                .mapToInt(line -> {
                    line.removeFirst();
                    int index = line.indexOf("|");
                    List<String> numbers = line.subList(index + 1 , line.size());
                    numbers.retainAll(line.subList(0, index));
                    return fibonacci(numbers.size());
                }).sum();

        System.out.println(solution);
    }

    private static void solution2(){

    }

    private static int fibonacci(int size){
        return size == 0 ? 0 :(int) Math.pow(2, size -1);
    }
}
