package adminclient.view;

import adminclient.controller.Controller;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static Controller c;
    public static void main(String[] args) {
        c = new Controller();
        Scanner scanner = new Scanner(System.in);

        logIn(scanner);

        possibilities(scanner);

        System.out.println("Farvel");

        scanner.close();


    }
    private static void logIn (Scanner scanner){
        System.out.println("*-------------------------------------*\n" +
                           "|Velkommen til Hjemmesalg-AdminProgram|\n" +
                           "*-------------------------------------*\n " +
                "\n Du skal indtaste brugernavn og kodeord for at tilgå funktionerne:");

        while(true) {
            System.out.println("Brugernavn: ");
            String username = scanner.nextLine();
            System.out.println("Kodeord:");
            String password = scanner.nextLine();
            try {
                if (c.attemptLogin(username, password))
                    break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Forkert brugernavn eller kodeord. Prøv igen.\n\n");
        }
    }

    private static void possibilities(Scanner scanner) {
        while(true) {
            scanner = new Scanner(System.in);
            System.out.println("\n\n" +
                               "*-------------*\n" +
                               "|  Hovedmenu  |\n" +
                               "*-------------*\n");

            System.out.println("Du har nu følgende muligheder du kan foretage dig: ");

            System.out.println("Tryk 1 for at se statistik");

            System.out.println("Tryk 2 for at håndtere kategorier");

            System.out.println("Tryk 3 for at logge ud og afslutte programmet.");


            int userInput = scanner.nextInt();
            switch(userInput){
                case 1:
                    viewStatistics(scanner);
                    break;
                case 2:
                     handleCategories(scanner);
                    break;
                case 3:
                    return;

                default:
                    System.out.println("Muligheden findes ikke. Prøv igen!\n\n\n");
                    break;

            }


        }

    }

    private static void handleCategories(Scanner scanner) {
        System.out.println("*----------------*\n" +
                           "|  Kategorimenu  |\n" +
                           "*----------------*\n");
        showCategoryOpportunities();
        while(true) {


            scanner = new Scanner(System.in);
            int userInput = scanner.nextInt();
            String output;
            switch (userInput){
                case 1:
                    output = c.fetchAllCategories();
                    System.out.println(output);

                    break;
                case 2:
                    output = handleUpdateCategory(scanner);

                    System.out.println(output);
                    break;
                case 3:
                    output = c.deleteCategory();
                    System.out.println(output);

                    break;
                case 4:
                    showCategoryOpportunities();

                    break;
                case 5:
                    return;
                default:
                    System.out.println("Muligheden " + userInput + " findes ikke. Prøv igen.\n");
                    showCategoryOpportunities();
            }
        }
    }

    private static String handleUpdateCategory(Scanner scanner) {
        while(true) {
            System.out.println("Skriv id på kategori du ønsker at opdater: ");
            scanner = new Scanner(System.in);
            int id = scanner.nextInt();
            System.out.println("Skriv hvad du ønsker at ændre kategorinavnet til: ");
            scanner = new Scanner(System.in);
            String categoryName = scanner.nextLine();
            String returnValue = c.updateCategory(id, categoryName);
            return returnValue;

        }
    }

    private static void showCategoryOpportunities() {
        System.out.println("Du har nu følgende muligheder:");
        System.out.println("Tryk 1 for at se en liste med alle kategorier: ");
        System.out.println("Tryk 2 for at redigere en eksisterende kategori: ");
        System.out.println("Tryk 3 for at slette en kategori: ");
        System.out.println("Tryk 4 for at se muligheder igen.");
        System.out.println("Tryk 5 for at gå tilbage til hovedmenuen: ");

    }

    private static void viewStatistics(Scanner scanner) {
        System.out.println("Når du er færdig med at se statistikker, så tryk på enter: ");
            try {
                String s = c.getStatistics();
                System.out.println(s);
            } catch (IOException e) {
                System.out.println("Noget gik galt. Statistikker kan ikke hentes lige nu Server er utilgængelig i øjeblikket! \n" +
                        "tryk på en tast for at gå tilbage til hovedmenuen.");
            }
        scanner = new Scanner(System.in);
        if(scanner.nextLine().equals(""));


    }

}