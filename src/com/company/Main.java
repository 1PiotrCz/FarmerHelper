package com.company;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static int newInt;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        HashMap<String, Barn> barnAnimalHashMap = new HashMap<>();

        do {
            menu();
            int numb = scanner.nextInt();

            switch (numb) {
                case 1:
                    case1(scanner, barnAnimalHashMap);
                    break;

                case 2:
                    case2(scanner, barnAnimalHashMap);
                    break;

                case 3:
                    case3(scanner, barnAnimalHashMap);
                    break;

                case 4:
                    case4(scanner, barnAnimalHashMap);
                    break;

                case 5:
                    case5(barnAnimalHashMap);
                    break;

                case 6:
                    System.exit(0);
                    break;
            }
        } while (true);
    }

    private static void menu() {

        System.out.println("-----------------------------");
        System.out.println("WYBIERZ DZIAŁANIE");
        System.out.println("1. Dodaj zwierzę");
        System.out.println("2. Dodaj stodołę");
        System.out.println("3. Usuń zwierzę");
        System.out.println("4. Usuń stodołę");
        System.out.println("5. Wyświetl wszystkie zwierzęta");
        System.out.println("6. Exit");
        System.out.println("-----------------------------");

    }

    private static String enterValue(String write) {

        Scanner scanner = new Scanner(System.in);
        System.out.println(write);

        return scanner.next();

    }

    private static Integer enterInt(String write) {

        Scanner scanner = new Scanner(System.in);
        System.out.println(write);

        try {
            newInt = scanner.nextInt();
        } catch (InputMismatchException e) {
            enterInt("W tym polu trzeba podać cyfrę");
        }
        return newInt;
    }

    private static void case1(Scanner scanner, HashMap<String, Barn> barnAnimalHashMap) {

        String species = enterValue("Wpisz gatunek: ");

        String name = enterValue("Wpisz imię: ");

        Integer age = enterInt("Wpisz wiek: ");

        Animal animal = new Animal(species, name, age);

        if (!barnAnimalHashMap.containsKey(animal.getSpecies())) {
            System.out.println("---------------------------------------");
            System.out.println("Stodoła dla tego gatunku nie istnieje! Konieczne jest uworzenie nowej stodoły: ");
            System.out.println("Podaj nazwę nowej stodoły: ");
            String barnName = scanner.next();
            barnAnimalHashMap.put(animal.getSpecies(), new Barn(barnName, animal.getSpecies()));
        }

        barnAnimalHashMap.get(animal.getSpecies()).addAnimal(animal);
        System.out.println("Dodano: ");
        System.out.println("Imie: " + animal.getName() + " | Gatunek: " + animal.getSpecies() + " | Wiek: " + animal.getAge() + " lat");
    }

    private static void case2(Scanner scanner, HashMap<String, Barn> barnAnimalHashMap) {

        System.out.println("Podaj nazwę nowej stodoły:");
        String barnName = scanner.next();
        System.out.println("Podaj dla jakiego gatunku będzie to dom: )");
        String barnSpecies = scanner.next();

        Barn barn = new Barn(barnName, barnSpecies);


        if (barnAnimalHashMap.containsKey(barn.getBarnSpecies())) {
            System.out.println("---------------------------------------");
            System.out.println("Taka stodoła już istnieje!");
            System.out.println("---------------------------------------");
        } else {
            barnAnimalHashMap.put(barn.getBarnSpecies(), barn);
        }
    }

    private static void case3(Scanner scanner, HashMap<String, Barn> barnAnimalHashMap) {

        System.out.println("---------------------------------------");
        System.out.println("Podaj dane zwierzęcia do usunięcia: ");
        System.out.println("Gatunek: ");
        String speciesToDelete = scanner.next();

        System.out.println("Imię: ");
        String nameToDelete = scanner.next();

        System.out.println("Wiek: ");
        int ageToDelete = scanner.nextInt();

        Animal animalToDelete = new Animal(speciesToDelete, nameToDelete, ageToDelete);

        if (barnAnimalHashMap.containsKey(animalToDelete.getSpecies())) {

            if (barnAnimalHashMap.get(animalToDelete.getSpecies()).deleteAnimal(animalToDelete)) {
                System.out.println("---------------------------------------");
                System.out.println("Zwierzę zostało usunięte! ");
                System.out.println("---------------------------------------");
            } else {
                System.out.println("---------------------------------------");
                System.out.println("Nie ma takiego zwierzaka!");
                System.out.println("---------------------------------------");
            }
        } else {
            System.out.println("---------------------------------------");
            System.out.println("Taka stodoła nie istnieje!");
            System.out.println("---------------------------------------");
        }
    }

    private static void case4(Scanner scanner, HashMap<String, Barn> barnAnimalHashMap) {
        System.out.println("Wpisz gatunek mieszkający w stodole, którą chcesz usunąć");
        System.out.println("<!>  USUNIĘCIE STODOŁY SPOWODUJE USUNIĘCIE WSZYSTKIOH JEJ MIESZKAŃCÓW  <!>");

        String barnToDelete = scanner.next();
        barnAnimalHashMap.remove(barnToDelete);

        System.out.println("Stodoła dla " + barnToDelete + " została usunięta!");
    }


    private static void case5(HashMap<String, Barn> barnAnimalHashMap) {

        for (Barn barn1 : barnAnimalHashMap.values()) {
            System.out.println("....................................");
            System.out.println("Stodoła: " + barn1.getBarnName());

            for (int i = 0; i < barn1.getAnimalList().size(); i++) {
                Animal animaToShow = barn1.getAnimalList().get(i);
                System.out.println("id: " + i + " | Gatunek: " + animaToShow.getSpecies() + " | Imię: " + animaToShow.getName() + " | Wiek :  " + animaToShow.getAge() + " lat(a)");
            }
        }
    }

}
