import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import entity.Person;
import mockdata.MockData;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class JavaStreamAPI {
    public static void main(String[] args) {

        try {
            imperativeApproach();

            declarativeApproach();

        } catch (IOException e) {
            System.out.println("Something went wrong.");
        }
    }

    private static void imperativeApproach() throws IOException {
        ImmutableList<Person> personList = MockData.getPeople();

        List<Person> personUnderAge18List = Lists.newArrayList();
        final int peopleLimit = 12;
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

    private static void declarativeApproach() throws IOException {
        ImmutableList<Person> personList = MockData.getPeople();

        List<Person> personUnderAge18List = personList.stream()
                .filter(person -> person.getAge() < 18)
                .limit(12)
                .collect(Collectors.toList());

        personUnderAge18List.forEach(System.out::println);
    }
}
