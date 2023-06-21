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
        System.out.println("Podaj markę samochodu:");
        String brand = scanner.nextLine();
        System.out.println("Podaj model samochodu:");
        String model = scanner.nextLine();
        System.out.println("Podaj rok produkcji samochodu:");
        int year = scanner.nextInt();
        System.out.println("Podaj przebieg samochodu:");
        int mileage = scanner.nextInt();
        System.out.println("Podaj cenę samochodu:");
        double price = scanner.nextDouble();

        Car car = new Car(brand, model, year, mileage, price);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATABASE_FILE, true))) {
            writer.write(car.toString());
            writer.newLine();

            System.out.println("Samochód został dodany do bazy danych.");
        } catch (IOException e) {
            System.err.println("Błąd zapisu do bazy danych: " + e.getMessage());
        }
    }

    private static void readAllCars() {
        try (BufferedReader reader = new BufferedReader(new FileReader(DATABASE_FILE))) {
            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Błąd odczytu bazy danych: " + e.getMessage());
        }
    }

    private static void searchCars() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj markę samochodu do wyszukania:");
        String searchTerm = scanner.nextLine();

        try (BufferedReader reader = new BufferedReader(new FileReader(DATABASE_FILE))) {
            String line;
            boolean found = false;

            while ((line = reader.readLine()) != null) {
                if (line.contains(searchTerm)) {
                    System.out.println(line);
                    found = true;
                }
            }

            if (!found) {
                System.out.println("Nie znaleziono samochodu o podanej marce.");
            }
        } catch (IOException e) {
            System.err.println("Błąd odczytu bazy danych: " + e.getMessage());
        }
    }
}