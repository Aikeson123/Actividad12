import java.io.*;
import java.util.*;

class AddressBook {
    private Map<String, String> contacts;
    private static final String FILENAME = "contacts.txt";

    public AddressBook() {
        this.contacts = new HashMap<>();
    }

    public void load() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                contacts.put(parts[0].trim(), parts[1].trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME))) {
            for (Map.Entry<String, String> entry : contacts.entrySet()) {
                bw.write(entry.getKey() + " : " + entry.getValue());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void list() {
        System.out.println("Contactos:");
        for (Map.Entry<String, String> entry : contacts.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    public void create(String number, String name) {
        contacts.put(number, name);
    }

    public void delete(String number) {
        contacts.remove(number);
    }
}

public class Main {
    public static void main(String[] args) {
        AddressBook addressBook = new AddressBook();
        addressBook.load();

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("\n1. Listar contactos");
            System.out.println("2. Crear contacto");
            System.out.println("3. Borrar contacto");
            System.out.println("4. Guardar y salir");
            System.out.print("Ingrese la opción deseada: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addressBook.list();
                    break;
                case 2:
                    System.out.print("Ingrese el número de teléfono: ");
                    String newNumber = scanner.nextLine();
                    System.out.print("Ingrese el nombre: ");
                    String newName = scanner.nextLine();
                    addressBook.create(newNumber, newName);
                    System.out.println("Contacto creado.");
                    break;
                case 3:
                    System.out.print("Ingrese el número de teléfono a eliminar: ");
                    String deleteNumber = scanner.nextLine();
                    addressBook.delete(deleteNumber);
                    System.out.println("Contacto eliminado.");
                    break;
                case 4:
                    addressBook.save();
                    exit = true;
                    break;
                default:
                    System.out.println("Opción inválida, por favor ingrese un número del 1 al 4.");
            }
        }
        scanner.close();
    }
}
