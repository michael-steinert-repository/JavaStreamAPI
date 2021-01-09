import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import entity.Person;
import mockdata.MockData;

import java.io.IOException;
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
}
