package org.example;

import org.example.collectionClasses.Product;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

import org.example.collectionClasses.interfaces.ProductReadable;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import static java.lang.Math.random;
import static java.lang.System.exit;

public class App {

    private ProductCollection productCollection;
    private java.time.LocalDate creationDate;

    public App() {
        this.productCollection = new ProductCollection();
        this.creationDate = LocalDate.now();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter command: ");
            String[] command = scanner.nextLine().split(" ");
            callCommandFunction(command);
        }
    }

    private void callCommandFunction(String[] command) {
        switch (command[0]) {
            case "help" -> help();
            case "info" -> info();
            case "show" -> show();
            case "insert" -> insert();
            case "update" -> update();
            case "remove_key" -> removeKey();
            case "clear" -> clearCollection();
            case "save" -> saveCollection();
            case "execute_script" -> executeScript(command[1]);
            case "replace_if_greater" -> replaceValue(true);
            case "replace_if_lowe" -> replaceValue(false);
            case "remove_greater_key" -> removeGreaterKey();
            case "min_by_manufacturer" -> minByManufacturer();
            case "max_by_coordinates" -> maxByCoordinates();
            case "print_field_ascending_price" -> printFieldAcsendingPrice();
            case "exit" -> exit(0);
        }
    }

    private int readId() {
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        scanner.close();
        return id;
    }

    private void help() {
        System.out.println("help - display help on available commands.");
        System.out.println("info - print information about the collection to standard output.");
        System.out.println("show - print all elements of a collection to standard output in string form.");
        System.out.println("insert {element} - add a new element with given key. The key is entered line by line.");
        System.out.println("update id {element} - update value collection's element, which have id equals given id. {element} is entered line by line.");
        System.out.println("remove_key {element} - remove collection's element by key {element}. {element} is entered line by line.");
        System.out.println("clear - clear collection");
        System.out.println("save - save collection to file");
        System.out.println("execute_script file_name - execute commands in file, which name is file_name. File contains commands in interactive format.");
        System.out.println("exit - finish application.");
        System.out.println("replace_if_greater {element} - change value by {element}, if new value is bigger than old value. {element} is entered line by line.");
        System.out.println("replace_if_low {element} - change value by {element}, if new value is lower than old value. {element} is entered line by line.");
        System.out.println("remove_greater_key {element} - remove all elements, which are bigger than {element}. {element} is entered line by line.");
        System.out.println("min_by_manufacturer - print any collection's key, which value 'manufacturer' is minimal.");
        System.out.println("max_by_coordinates - print any collection's key, which value 'coordinates' is maximal.");
        System.out.println("print_field_ascending_price - print all values field 'price' in ascending order.");
    }

    private void info() {
        System.out.printf("Collection type: %s\n", productCollection.getProducts().getClass().getTypeName());
        System.out.printf("Creation date: %s\n", this.creationDate);
        System.out.printf("Collection size: %d\n", productCollection.getProducts().size());
    }

    private void show() {
        Set<Map.Entry<Product, Integer>> set = this.productCollection.getProducts().entrySet();
        Iterator<Map.Entry<Product, Integer>> i = set.iterator();
        while (i.hasNext()) {
            Map.Entry<Product, Integer> elem = (Map.Entry<Product, Integer>) i.next();
            System.out.printf("%s : %s\n", elem.getKey(), elem.getValue());
        }
    }

    private void insert() {
        Product add = ProductReadable.readProduct();
        productCollection.addProduct(add);
    }

    private void update() {
        System.out.println("Enter id, which you want to change: ");
        int idChange = readId();
        Product newProduct = ProductReadable.readProduct();
        this.productCollection.setId(idChange, newProduct);
    }

    private void removeKey() {
        System.out.println("Enter id Product, which you want to delete: ");
        int id = readId();
        this.productCollection.deleteId(id);
    }

    private void clearCollection() {
        this.productCollection.clearCollection();
    }

    private void saveCollection() {
        try {
            JAXBContext context = JAXBContext.newInstance(ProductCollection.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            String nameFileOutput = getFileOutputName();
            File fileOutput = new File(nameFileOutput);
            this.productCollection.createOut(this.productCollection.getProducts().keySet());
            marshaller.marshal(this.productCollection, fileOutput);
        } catch (JAXBException e) {
            System.out.println(e.getMessage());
            System.out.println("Sorry, failed to output collection.");
        }
    }

    private void executeScript(String name) {
        try (Scanner reader = new Scanner(new BufferedInputStream(new FileInputStream(new File(name))))) {
            while (reader.hasNextLine()) {
                String[] command = reader.nextLine().split(" ");
                callCommandFunction(command);
            }
        } catch (IOException e) {
            System.out.println("File not found. Please, enter file name again.");
            Scanner scanner = new Scanner(System.in);
            String tmp = scanner.next();
            executeScript(tmp);
        }
    }

    private void replaceValue(boolean greater) {
        System.out.println("Enter changeable id: ");
        int changeId = readId();
        Product product = this.productCollection.findId(changeId);
        while (product == null) {
            System.out.println("Don't exist this id. Enter changeable id again: ");
            changeId = readId();
            product = this.productCollection.findId(changeId);
        }
        int newVal = (int) (random() * 1000);
        if (!greater && newVal < this.productCollection.getProducts().get(product)) {
            this.productCollection.getProducts().replace(product, newVal);
        } else if (greater && newVal > this.productCollection.getProducts().get(product)) {
            this.productCollection.getProducts().replace(product, newVal);
        }
    }

    private void removeGreaterKey() {
        System.out.println("Enter the id starting from which you want to delete elements: ");
        int id = readId();
        Iterator<Map.Entry<Product, Integer>> i = this.productCollection.getProducts().entrySet().iterator();
        boolean isMet = false;
        while (i.hasNext()) {
            Map.Entry<Product, Integer> elem = (Map.Entry<Product, Integer>) i.next();
            if (isMet) {
                i.remove();
            }
            if (elem.getKey().getId() == id) {
                isMet = true;
            }
        }
    }

    private void minByManufacturer() {
        Iterator<Map.Entry<Product, Integer>> i = this.productCollection.getProducts().entrySet().iterator();
        boolean isMet = false;
        Product mn = ((Map.Entry<Product, Integer>) i.next()).getKey();
        while (i.hasNext()) {
            Map.Entry<Product, Integer> elem = (Map.Entry<Product, Integer>) i.next();
            if (elem.getKey().getOrganization().isLow(mn.getOrganization())) {
                mn = elem.getKey();
            }
        }
        System.out.println(mn.toString());
    }

    private void maxByCoordinates() {
        Iterator<Map.Entry<Product, Integer>> i = this.productCollection.getProducts().entrySet().iterator();
        boolean isMet = false;
        Product mn = ((Map.Entry<Product, Integer>) i.next()).getKey();
        while (i.hasNext()) {
            Map.Entry<Product, Integer> elem = (Map.Entry<Product, Integer>) i.next();
            if (elem.getKey().getCoordinates().isBig(mn.getCoordinates())) {
                mn = elem.getKey();
            }
        }
        System.out.println(mn.toString());
    }

    private void printFieldAcsendingPrice() {
        Iterator<Map.Entry<Product, Integer>> i = this.productCollection.getProducts().entrySet().iterator();
        while (i.hasNext()) {
            Map.Entry<Product, Integer> elem = (Map.Entry<Product, Integer>) i.next();
            System.out.println(elem.getKey().getPrice());
        }
    }

    private String getFileOutputName(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter name file output: ");
        return in.next();
    }
}