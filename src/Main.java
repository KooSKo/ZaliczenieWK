import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Car {
    private String brand;
    private String model;
    private int year;
    private int mileage;
    private double price;

    public Car(String brand, String model, int year, int mileage, double price) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.mileage = mileage;
        this.price = price;
    }

    @Override
    public String toString() {
        return brand + " " + model + " " + year + " " + mileage + " " + price;
    }
}

class CarDatabase {
    private static final String DATABASE_FILE = "baza_danych.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("------ Menu ------");
            System.out.println("1. Dodaj samochód");
            System.out.println("2. Odczytaj wszystkie samochody");
            System.out.println("3. Wyszukaj samochody");
            System.out.println("4. Wyjdź");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addCar();
                    break;
                case 2:
                    readAllCars();
                    break;
                case 3:
                    searchCars();
                    break;
                case 4:
                    System.out.println("Do widzenia!");
                    exit = true;
                    break;
                default:
                    System.out.println("Nieprawidłowy wybór. Wybierz ponownie.");
                    break;
            }
        }

        scanner.close();
    }

    private static void addCar() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj markę samochodu:");  // Przyjmuje markę samochodu od użytkownika.
        String brand = scanner.nextLine();
        System.out.println("Podaj model samochodu:");  // Przyjmuje model samochodu od użytkownika.
        String model = scanner.nextLine();
        System.out.println("Podaj rok produkcji samochodu:");  // Przyjmuje rok produkcji samochodu od użytkownika.
        int year = scanner.nextInt();
        System.out.println("Podaj przebieg samochodu:");  // Przyjmuje przebieg samochodu od użytkownika.
        int mileage = scanner.nextInt();
        System.out.println("Podaj cenę samochodu:");  // Przyjmuje cenę samochodu od użytkownika.
        double price = scanner.nextDouble();

        Car car = new Car(brand, model, year, mileage, price);  // Tworzy nowy obiekt klasy Car na podstawie wprowadzonych danych.

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATABASE_FILE, true))) {
            writer.write(car.toString());  // Zapisuje dane samochodu do pliku bazy danych.
            writer.newLine();

            System.out.println("Samochód został dodany do bazy danych.");  // Wyświetla komunikat potwierdzający dodanie samochodu.
        } catch (IOException e) {
            System.err.println("Błąd zapisu do bazy danych: " + e.getMessage());  // Wyświetla błąd zapisu do bazy danych w przypadku wystąpienia wyjątku IOException.
        }
    }

    private static void readAllCars() {
        try (BufferedReader reader = new BufferedReader(new FileReader(DATABASE_FILE))) {
            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);  // Wyświetla każdą linię (dane samochodu) z pliku bazy danych.
            }
        } catch (IOException e) {
            System.err.println("Błąd odczytu bazy danych: " + e.getMessage());  // Wyświetla błąd odczytu bazy danych w przypadku wystąpienia wyjątku IOException.
        }
    }

    private static void searchCars() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj markę samochodu do wyszukania:");  // Przyjmuje markę samochodu do wyszukania od użytkownika.
        String searchTerm = scanner.nextLine();

        try (BufferedReader reader = new BufferedReader(new FileReader(DATABASE_FILE))) {
            String line;
            boolean found = false;

            while ((line = reader.readLine()) != null) {
                if (line.contains(searchTerm)) {  // Sprawdza, czy linia zawiera podany przez użytkownika termin wyszukiwania.
                    System.out.println(line);  // Wyświetla linię (dane samochodu), jeśli zawiera podaną markę samochodu.
                    found = true;
                }
            }

            if (!found) {
                System.out.println("Nie znaleziono samochodu o podanej marce.");  // Wyświetla komunikat informujący, że nie znaleziono samochodu o podanej marce.
            }
        } catch (IOException e) {
            System.err.println("Błąd odczytu bazy danych: " + e.getMessage());  // Wyświetla błąd odczytu bazy danych w przypadku wystąpienia wyjątku IOException.
        }
    }
}
