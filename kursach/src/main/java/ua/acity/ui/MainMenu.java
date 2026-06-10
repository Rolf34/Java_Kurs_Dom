package ua.acity.ui;

import java.util.Scanner;

public class MainMenu {
    private Scanner scanner;

    public MainMenu() {
        this.scanner = new Scanner(System.in);
    }

    public void displayMainMenu() {
        System.out.println("=== City Building Management System ===");
        System.out.println("1. Manage Buildings");
        System.out.println("2. Manage Users");
        System.out.println("3. View Statistics");
        System.out.println("4. Exit");
        System.out.print("Choose an option: ");
    }

    public int getMenuChoice() {
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            scanner.nextLine();
            return -1;
        }
    }

    public void close() {
        scanner.close();
    }
}
