package org.example;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import org.example.collectionClasses.Product;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

import jakarta.xml.bind.JAXBException;


import static java.lang.Math.random;
import static java.lang.System.exit;

import org.example.collectionClasses.readers.ProductReadable;
import org.example.collectionClasses.readers.Readable;


/**
 * A console application class that implements interactive management
 * of a collection of Product objects.
 */

public class App {

    /**
     * Collection of Product objects.
     */
    private ProductCollection productCollection;

    /**
     * Date of collection creation.
     */
    private java.time.LocalDate creationDate;

    /**
     * An application constructor that creates collections from input data.
     * Input data is in a xml file.
     * To fill a collection, you need to specify the path to the input file in the 'Lab5' environment variable.
     * After creating a collection, the application automatically starts.
     */
    public App() {
        this.productCollection = new ProductCollection();
        this.creationDate = LocalDate.now();
        start();
    }

    /**
     * Launching the application
     */
    private void start() {
        while (true) {
            String[] command = Readable.scanner.nextLine().split(" ");
//            System.out.println(Arrays.toString(command));
            callCommandFunction(command);
        }
    }

    /**
     * Calls the function corresponding to the entered command
     *
     * @param command command and its parameters
     */
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
            case "execute_script" -> checkAndReadInputFile(command);
            case "replace_if_greater" -> replaceValue(true);
            case "replace_if_lowe" -> replaceValue(false);
            case "remove_greater_key" -> removeGreaterKey();
            case "min_by_manufacturer" -> minByManufacturer();
            case "max_by_coordinates" -> maxByCoordinates();
            case "print_field_ascending_price" -> printFieldAcsendingPrice();
            case "exit" -> exit(0);
        }
    }

    /**
     * Reads the Product id
     *
     * @return Product id
     */
    private int readId() {
        System.out.print("Enter id: ");
        int result = Readable.scanner.nextInt();
        Readable.scanner.nextLine();
        return result;
    }

    /**
     * Displays a description of the available commands.
     */
    private void help() {
        System.out.println("help - display help on available commands.");
        System.out.println("info - print information about the collection to standard output.");
        System.out.println("show - print all elements of a collection to standard output in string form.");
        System.out.println("insert {element} - add a new element with given key. The key is entered line by line.");
        System.out.println("update id {element} - update value collection's element, which have id equals given id. {element} is entered line by line.");
        System.out.println("remove_key {element} - remove collection's element by key {element}. {element} is entered line by line.");
        System.out.println("clear - clear collection");
        System.out.println("save - save collection to xml or txt file");
        System.out.println("execute_script file_name - execute commands in file, which name is file_name. File contains only command name.");
        System.out.println("exit - finish application.");
        System.out.println("replace_if_greater {element} - change value by {element}, if new value is bigger than old value. {element} is entered line by line.");
        System.out.println("replace_if_low {element} - change value by {element}, if new value is lower than old value. {element} is entered line by line.");
        System.out.println("remove_greater_key {element} - remove all elements, which are bigger than {element}. {element} is entered line by line.");
        System.out.println("min_by_manufacturer - print any collection's key, which value 'manufacturer' is minimal.");
        System.out.println("max_by_coordinates - print any collection's key, which value 'coordinates' is maximal.");
        System.out.println("print_field_ascending_price - print all values field 'price' in ascending order.");
    }

    /**
     * Displays information about the collection: its type, number of elements, and creation date.
     */
    private void info() {
        System.out.printf("Collection type: %s\n", productCollection.getProducts().getClass().getTypeName());
        System.out.printf("Creation date: %s\n", this.creationDate);
        System.out.printf("Collection size: %d\n", productCollection.getProducts().size());
    }

    /**
     * Displays the collection elements.
     */
    private void show() {
        Set<Map.Entry<Product, Integer>> set = this.productCollection.getProducts().entrySet();
        Iterator<Map.Entry<Product, Integer>> i = set.iterator();
        while (i.hasNext()) {
            Map.Entry<Product, Integer> elem = (Map.Entry<Product, Integer>) i.next();
            System.out.printf("%s : %s\n", elem.getKey(), elem.getValue());
        }
    }

    /**
     * Adds a new element to the collection.
     */
    private void insert() {
        Product add = ProductReadable.readProduct();
        productCollection.addProduct(add);
    }

    /**
     * Updates a collection element by id.
     */
    private void update() {
        System.out.print("Enter id, which you want to change: ");
        int idChange = readId();
        this.productCollection.setId(idChange);
    }

    /**
     * Removes a collection element by id.
     */
    private void removeKey() {
        System.out.print("Enter id Product, which you want to delete: ");
        int id = readId();
        this.productCollection.deleteId(id);
    }

    /**
     * Removes all elements from the collection.
     */
    private void clearCollection() {
        this.productCollection.clearCollection();
    }

    /**
     * Saves the collection to a xml or txt file.
     */
    private void saveCollection() {
        System.out.print("Enter file extension (xml or txt): ");
        String extension = Readable.scanner.nextLine();
        switch (extension) {
            case "xml" -> xmlSaveCollection();
            case "txt" -> txtSaveCollection();
        }
    }

    /**
     * Saves the collection to a xml file.
     */
    private void xmlSaveCollection() {
        try {
            JAXBContext context = JAXBContext.newInstance(ProductCollection.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            String nameFileOutput = getFileOutputName();
            File fileOutput = new File(nameFileOutput);
            this.productCollection.createOut(this.productCollection.getProducts().keySet());
            marshaller.marshal(this.productCollection, fileOutput);
        } catch (JAXBException e) {
            System.out.println("Failed to write to xml file. Try again.");
            xmlSaveCollection();
        }
    }

    /**
     * Saves the collection to a txt file.
     */
    private void txtSaveCollection() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(getFileOutputName()))) {
            for (Product elem : this.productCollection.getProducts().keySet()) {
                writer.write(elem.toString() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Failed to write to txt file. Try again.");
            txtSaveCollection();
        }
    }

    /**
     * Reads commands from a script.
     *
     * @param name name of the file to read.
     */
    private void executeScript(String name) {
        try (Scanner reader = new Scanner(new BufferedInputStream(new FileInputStream(new File(name))))) {
            while (reader.hasNextLine()) {
                String[] curCommand = reader.nextLine().split(" ");
                callCommandFunction(curCommand);
            }
        } catch (IOException e) {
            System.out.print("File not found. Please, enter file path again: ");
            String tmp = Readable.scanner.next();
            executeScript(tmp);
        }
    }

    /**
     * Checks the correctness of the entered command and runs the corresponding function.
     * @param command input command.
     */
    private void checkAndReadInputFile(String[] command) {
        String resultName;
        if (command.length < 2) {
            System.out.print("Enter path input file: ");
            resultName = Readable.scanner.nextLine();
        }else {
            resultName = command[1];
        }
        executeScript(resultName);
    }

    /**
     * Replaces the collection value, depending on the greater parameter.
     *
     * @param greater true if the new value is greater than the old one, false otherwise.
     */
    private void replaceValue(boolean greater) {
        System.out.print("Enter changeable id: ");
        int changeId = readId();
        Product product = this.productCollection.findId(changeId);
        while (product == null) {
            System.out.print("Don't exist this id. Enter changeable id again: ");
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

    /**
     * Removes all elements of the collection that are greater than the element specified by id.
     */
    private void removeGreaterKey() {
        System.out.println("Enter the id starting from which you want to delete elements.");
        int id = readId();
        boolean isMet = false;
        for (Map.Entry<Product, Integer> elem : this.productCollection.getProducts().entrySet()) {
            if (isMet) {
                this.productCollection.getProducts().remove(elem.getKey(), elem.getValue());
            }
            if (elem.getKey().getId() == id) {
                isMet = true;
            }
        }
    }

    /**
     * Displays the collection element with the minimum Manufacturer value.
     */
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

    /**
     * Displays the collection element with the maximum Coordinates value.
     */
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

    /**
     * Displays price values in ascending order.
     */
    private void printFieldAcsendingPrice() {
        Iterator<Map.Entry<Product, Integer>> i = this.productCollection.getProducts().entrySet().iterator();
        while (i.hasNext()) {
            Map.Entry<Product, Integer> elem = (Map.Entry<Product, Integer>) i.next();
            System.out.println(elem.getKey().getPrice());
        }
    }

    /**
     * Reads the name of the file to save the collection to.
     *
     * @return the name of the output file.
     */
    private String getFileOutputName() {
        System.out.print("Enter name file output: ");
        String name = Readable.scanner.next();
        if (!name.substring(name.length() - 4).equals("xml")) {
            name += ".xml";
        }
        return name;
    }
}