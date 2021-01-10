import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import entity.Car;
import entity.Person;
import entity.PersonDTO;
import mockdata.MockData;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
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
}
