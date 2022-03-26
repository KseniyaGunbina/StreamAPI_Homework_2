import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {


    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        // Найти количество несовершеннолетних (т.е. людей младше 18 лет)
        long count = persons.stream()
                .filter(x -> x.getAge() < 18)
                .count();
        System.out.println(count);

        // Получить список фамилий призывников (т.е. мужчин от 18 и до 27 лет)
        List<String> result = persons.stream()
                .filter(x -> x.getSex().equals(Sex.MAN))
                .filter(x -> x.getAge() >= 18 && x.getAge() <= 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        result.stream()
                .forEach(System.out::println);

        System.out.println("_________________________");

        // Получить отсортированный по фамилии список потенциально работоспособных людей
        // с высшим образованием в выборке (т.е. людей с высшим образованием от 18 до 60 лет
        // для женщин и до 65 лет для мужчин)
        List<String> workers = persons.stream()
                .filter(x -> x.getEducation().equals(Education.HIGHER))
                .filter(x -> {
                    if (x.getAge() >= 18 && x.getAge() <= 65 && x.getSex().equals(Sex.MAN))
                        return true;
                    else if (x.getAge() >= 18 && x.getAge() <= 60 && x.getSex().equals(Sex.WOMAN)) {
                        return true;
                    }
                    return false;
                })
                .map(Person::getFamily)
                .sorted()
                .collect(Collectors.toList());
        workers.stream()
                .forEach(System.out::println);






    }


}
