import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import entity.Car;
import entity.Person;
import entity.PersonDTO;
import mockdata.MockData;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class JavaStreamAPI {
    public static void main(String[] args) {

        try {
            System.out.println("Imperative Approach to Iterating Lists");
            imperativeApproachToIteratingLists();

            System.out.println("Declarative Approach to Iterating Lists");
            declarativeApproachToIteratingLists();

            System.out.println("Iterating Lists with Range and Index");
            intStreamRangeIteratingLists();

            System.out.println("Iterating even Numbers");
            intStreamIterate();

            System.out.println("Minimum Number of 1, 2, 3, 4");
            minNumberComparator();

            System.out.println("Minimum Number of 1, 2, 3, 4");
            maxNumberComparator();

            System.out.println("Minimum Number of 1, 1, 2, 2, 3, 4");
            distinctNumbers();

            System.out.println("Mapping Person to PersonDTO");
            mappingPersonToPersonDtoLists();

            System.out.println("Mapping Car Price to Double and build Average");
            mappingToDoubleAndBuildAverage();

            System.out.println("Find first Integer in Array");
            findFirstInArray();

            System.out.println("Find any Integer in Array");
            findAnyInArray();

            System.out.println("Count Male in List");
            countMaleInList();

            System.out.println("Get min Price form yellow Cars");
            minPriceAndYellowCarFromList();

            System.out.println("Get max Price form yellow Cars");
            maxPriceAndYellowCarFromList();

            System.out.println("Get Statistics for Price form Cars");
            statisticsPriceForCarFromList();

            System.out.println("Group Cars by Make");
            groupCarsByMakeFromList();

            System.out.println("Grouping and Counting Names in a List");
            countingNamesInList();

            System.out.println("Reduce all Integers in a List");
            reduceIntegersInArray();

            System.out.println("Flat all Lists in a List");
            flatMapToFlatListsInList();

            System.out.println("Joining all Strings in a List");
            joiningStringInList();

            System.out.println("Implemented the Collectors.toList()");
            implementCollectorsToList();

        } catch (IOException e) {
            System.out.println("Something went wrong.");
        }
    }

    private static void imperativeApproachToIteratingLists() throws IOException {
        ImmutableList<Person> personList = MockData.getPeople();

        List<Person> personUnderAge18List = Lists.newArrayList();
        final int peopleLimit = 4;
        int counter = 0;

        for (Person person : personList) {
            if (person.getAge() < 18) {
                personUnderAge18List.add(person);
                counter++;
                if (counter == peopleLimit) {
                    break;
                }
            }
        }

        for (Person person : personUnderAge18List) {
            System.out.println(person);
        }
    }

    private static void declarativeApproachToIteratingLists() throws IOException {
        ImmutableList<Person> personList = MockData.getPeople();

        List<Person> personUnderAge18List = personList.stream()
                .filter(person -> person.getAge() < 18)
                .limit(4)
                .collect(Collectors.toList());

        personUnderAge18List.forEach(System.out::println);
    }

    private static void intStreamRangeIteratingLists() throws IOException {
        ImmutableList<Person> personList = MockData.getPeople();

        IntStream.range(0, 4).forEach(index -> {
            System.out.println(personList.get(index));
        });
    }

    private static void intStreamIterate() {
        IntStream.iterate(0, operand -> operand + 1)
                .filter(number -> number % 2 == 0)
                .limit(10)
                .forEach(System.out::println);
    }

    private static void minNumberComparator() {
        final List<Integer> integerList = ImmutableList.of(1, 2, 3, 4);

        Integer minNumber = integerList.stream()
                .min((number1, number2) -> number1 > number2 ? 1 : -1)
                .get();

        System.out.println(minNumber);
    }

    private static void maxNumberComparator() {
        final List<Integer> integerList = ImmutableList.of(1, 2, 3, 4);

        Integer maxNumber = integerList.stream()
                .max(Comparator.naturalOrder())
                .get();

        System.out.println(maxNumber);
    }

    private static void distinctNumbers() {
        final List<Integer> integerList = ImmutableList.of(1, 1, 2, 2, 3, 4);

        List<Integer> distinctIntegers = integerList.stream()
                .distinct()
                .collect(Collectors.toList());

        System.out.println(distinctIntegers);
    }

    private static void mappingPersonToPersonDtoLists() throws IOException {
        ImmutableList<Person> personList = MockData.getPeople();

        List<PersonDTO> personDTOList = personList.stream()
                .map(person -> new PersonDTO(person.getId(), person.getFirstName(), person.getAge()))
                .limit(4)
                .collect(Collectors.toList());

        personDTOList.forEach(System.out::println);
    }

    private static void mappingToDoubleAndBuildAverage() throws IOException {
        ImmutableList<Car> carList = MockData.getCars();

        double average = carList.stream()
                .mapToDouble(car -> car.getPrice())
                .average()
                .orElse(0);

        System.out.println(Math.round(average));
    }
    
    private static void findAnyInArray() {
        Integer[] integerArray = {1, 2, 3, 4};

        Integer anyInteger = Arrays.stream(integerArray)
                .filter(integer -> integer < 3)
                .findAny()
                .get();

        System.out.println(anyInteger);
    }

    private static void findFirstInArray() {
        Integer[] integerArray = {1, 2, 3, 4};

        Integer firstInteger = Arrays.stream(integerArray)
                .filter(integer -> integer < 3)
                .findFirst()
                .get();

        System.out.println(firstInteger);
    }

    private static void countMaleInList() throws IOException {
        ImmutableList<Person> personList = MockData.getPeople();

        long countMale = personList.stream()
                .filter(person -> person.getGender().equalsIgnoreCase("male"))
                .count();

        System.out.println(countMale);
    }

    private static void minPriceAndYellowCarFromList() throws IOException {
        ImmutableList<Car> carList = MockData.getCars();

        double minPriceFromYellowCars = carList.stream()
                .filter(car -> car.getColor().equalsIgnoreCase("yellow"))
                .mapToDouble(car -> car.getPrice())
                .min()
                .orElse(0);

        System.out.println(Math.round(minPriceFromYellowCars));
    }

    private static void maxPriceAndYellowCarFromList() throws IOException {
        ImmutableList<Car> carList = MockData.getCars();

        double maxPriceFromYellowCars = carList.stream()
                .filter(car -> car.getColor().equalsIgnoreCase("yellow"))
                .mapToDouble(car -> car.getPrice())
                .max()
                .getAsDouble();

        System.out.println(Math.round(maxPriceFromYellowCars));
    }

    private static void statisticsPriceForCarFromList() throws IOException {
        ImmutableList<Car> carList = MockData.getCars();

        DoubleSummaryStatistics priceSummaryStatistics = carList.stream()
                .mapToDouble(car -> car.getPrice())
                .summaryStatistics();

        System.out.println(priceSummaryStatistics);
        System.out.println(priceSummaryStatistics.getMax());
        System.out.println(priceSummaryStatistics.getSum());
        System.out.println(priceSummaryStatistics.getMin());
        System.out.println(priceSummaryStatistics.getCount());
        System.out.println(priceSummaryStatistics.getAverage());
    }

    private static void groupCarsByMakeFromList() throws IOException {
        ImmutableList<Car> carList = MockData.getCars();

        Map<String, List<Car>> groupCarsByMake = carList.stream()
                .limit(4)
                .collect(Collectors.groupingBy(car -> car.getMake()));

        groupCarsByMake.forEach((make, cars) -> {
            System.out.println(make);
            cars.forEach(System.out::println);
        });
    }

    private static void countingNamesInList() throws IOException {
        ArrayList<String> nameList = Lists.newArrayList("Michael", "Michael", "Marie");

        Map<String, Long> countingNames = nameList.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        countingNames.forEach((name, count) -> {
            System.out.println(name + " > " + count);
        });
    }

    private static void reduceIntegersInArray() {
        Integer[] integerArray = {1, 2, 3, 4};

        int reducedInteger = Arrays.stream(integerArray)
                .reduce(0, (a, b) -> a + b);

        System.out.println(reducedInteger);
    }

    private static void flatMapToFlatListsInList() {
        final List<ArrayList<String>> arrayListOfNames = Lists.newArrayList(
                Lists.newArrayList("Mariam", "Alex", "Michael"),
                Lists.newArrayList("John", "Alesha", "Andre"),
                Lists.newArrayList("Susy", "Ali")
        );

        List<String> flatListOfNames = Lists.newArrayList();

        // without FlatMap()
        for(List<String> listOfNames : arrayListOfNames) {
            for(String name : listOfNames) {
                flatListOfNames.add(name);
            }
        }

        System.out.println(flatListOfNames);

        List<String> flatMapListOfNames = arrayListOfNames.stream()
                .flatMap(list -> list.stream())
                .collect(Collectors.toList());

        System.out.println(flatMapListOfNames);
    }

    private static void joiningStringInList() {
        final List<String> arrayListOfNames = Lists.newArrayList("Mariam", "Alex", "Michael");

        String joinedStrings = arrayListOfNames.stream()
                .map(string -> string.toUpperCase())
                .collect(Collectors.joining(","));

        System.out.println(joinedStrings);
    }

    private static void implementCollectorsToList() throws IOException {
        ImmutableList<Person> personList = MockData.getPeople();

        List<String> emailList = personList.stream()
                .map(person -> person.getEmail())
                .limit(4)
                .collect(
                        () -> new ArrayList<String>(),
                        (list, element) -> list.add(element),
                        (list1, list2) -> list1.addAll(list2));
//                .collect(Collectors.toList());

        emailList.forEach(System.out::println);
    }
}
