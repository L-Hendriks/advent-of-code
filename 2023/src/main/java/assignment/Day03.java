package assignment;

import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

import static util.FileUtils.FileToList;

public class Day03 {
    private static final Pattern PATTERN = Pattern.compile("\\d+");
    private static final ArrayList<Number> numbers = new ArrayList<>();
    private static final ArrayList<Symbol> symbols = new ArrayList<>();

    public static void main(String[] args) {
        processData();
        solution1();
        solution2();
    }

    private static void solution1() {
        int solution = numbers.stream()
                .filter(number -> symbols.stream().anyMatch(number::hasAdjacent))
                .mapToInt(Number::number).sum();

        System.out.println(solution);
    }

    private static void solution2(){
        int solution = symbols.stream()
                .filter(symbol -> numbers.stream()
                        .filter(number -> number.hasAdjacent(symbol)).count() == 2)
                .mapToInt(symbol -> numbers.stream()
                        .filter(number -> number.hasAdjacent(symbol))
                        .mapToInt(Number::number)
                        .reduce((left, right) -> left * right)
                        .orElse(0))
                .sum();

        System.out.println(solution);
    }

    private static void processData() {
        List<String> lines = FileToList("day3-input.txt");
        AtomicInteger y = new AtomicInteger();
        lines.forEach(line -> {
            PATTERN.matcher(line).results().forEach(result -> {
                int number = Integer.parseInt(line.substring(result.start(), result.end()));
                numbers.add(new Number(number, result.start(), result.end()-1, y.get()));
            });
            AtomicInteger x = new AtomicInteger();
            line.chars().mapToObj(character -> (char) character)
                    .forEach(character -> {
                        if (!Character.isDigit(character) && character != '.') {
                            symbols.add(new Symbol(character, x.get(), y.get()));
                        }
                        x.getAndIncrement();
                    });
            y.getAndIncrement();
        });
    }

    record Number(int number, int start, int end, int y) {
        public boolean hasAdjacent(Symbol symbol) {
            return Math.abs(this.y() - symbol.y()) <= 1 && symbol.x() <= this.end + 1 && symbol.x() >= this.start - 1;
        }
    }

    record Symbol(char symbol, int x, int y) {
    }
}


