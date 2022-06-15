package Backend_Part;

import java.io.*;
import java.util.ArrayList;

public class DataBase {
	static String cashiersPath = "Resources/Cashiers.ser";
	static String menagersPath = "Resources/Menager.ser";
	static String categoriesPath = "Resources/Categories.ser";
	static String cdPath = "Resources/CDs.ser";
	static String cdboughtPath = "Resources/CD_bought.ser";
	static String suppliersPath = "Resources/Suppliers.ser";

	static ArrayList<Cashiers> cashiers;
	static ArrayList<Menagers> menagers;
	static ArrayList<String> categories;
	static ArrayList<CDs> cd;
	static ArrayList<CD_bought> cdbought;
	static ArrayList<Suppliers> suppliers;

	public static ArrayList<Cashiers> getCashiers() {
		return cashiers;
	}

	public static ArrayList<Menagers> getMenagers() {
		return menagers;
	}

	public static ArrayList<String> getCategories() {
		return categories;
	}

	public static ArrayList<CDs> getCDs() {
		return cd;
	}

	public static ArrayList<CD_bought> getCD_bought() {
		return cdbought;
	}

	public static ArrayList<Suppliers> getSuppliers() {
		return suppliers;
	}

	public static void putDataInLists() {
		cashiers = new ArrayList<>();
		menagers = new ArrayList<>();
		cd = new ArrayList<>();
		cdbought = new ArrayList<>();
		categories = new ArrayList<>();
		suppliers = new ArrayList<>();

		fillSuppliers();
		load();
		
	}

	public static void load() {
		try {
			// Reading the object from a Cashiers File
			FileInputStream cashiersListFile = new FileInputStream(cashiersPath);
			ObjectInputStream inCashiers = new ObjectInputStream(cashiersListFile);

			FileInputStream menagersListFile = new FileInputStream(menagersPath);
			ObjectInputStream inMenagers = new ObjectInputStream(menagersListFile);

			FileInputStream categoriesListFile = new FileInputStream(categoriesPath);
			ObjectInputStream inCategories = new ObjectInputStream(categoriesListFile);

			FileInputStream cdListFile = new FileInputStream(cdPath);
			ObjectInputStream inCd = new ObjectInputStream(cdListFile);

			FileInputStream cdboughtListFile = new FileInputStream(cdboughtPath);
			ObjectInputStream inCdbought = new ObjectInputStream(cdboughtListFile);

			FileInputStream suppliersListFile = new FileInputStream(suppliersPath);
			ObjectInputStream inSuppliers = new ObjectInputStream(suppliersListFile);

			cashiers = (ArrayList<Cashiers>) inCashiers.readObject();
			menagers = (ArrayList<Menagers>) inMenagers.readObject();
			categories = (ArrayList) inCategories.readObject();
			cd = (ArrayList<CDs>) inCd.readObject();
			cdbought = (ArrayList<CD_bought>) inCdbought.readObject();
			suppliers = (ArrayList<Suppliers>) inSuppliers.readObject();

			inCashiers.close();
			cashiersListFile.close();

			inMenagers.close();
			menagersListFile.close();

			inCategories.close();
			categoriesListFile.close();

			inCd.close();
			cdListFile.close();

			inCdbought.close();
			cdboughtListFile.close();

			inSuppliers.close();
			suppliersListFile.close();
		} catch (IOException ex) {
			System.out.println("IOException is caught.\nWasn't able to load database. You are missing files.");
		} catch (ClassNotFoundException ex) {
			System.out.println("ClassNotFoundException is caught");
		}
	}

	public static void save() {
		try {
			//Writing the object from a Cashiers file
			FileOutputStream fosCashiers = new FileOutputStream(cashiersPath);
			ObjectOutputStream oosCashiers = new ObjectOutputStream(fosCashiers);

			FileOutputStream fosMenagers = new FileOutputStream(menagersPath);
			ObjectOutputStream oosMenagers = new ObjectOutputStream(fosMenagers);

			FileOutputStream fosCd = new FileOutputStream(cdPath);
			ObjectOutputStream ooscd = new ObjectOutputStream(fosCd);

			FileOutputStream fosCdbought = new FileOutputStream(cdboughtPath);
			ObjectOutputStream ooscdbought = new ObjectOutputStream(fosCdbought);

			FileOutputStream fosCategories = new FileOutputStream(categoriesPath);
			ObjectOutputStream oosCategories = new ObjectOutputStream(fosCategories);

			FileOutputStream fosSuppliers = new FileOutputStream(suppliersPath);
			ObjectOutputStream oosSuppliers = new ObjectOutputStream(fosSuppliers);

			oosCashiers.writeObject(cashiers);
			oosMenagers.writeObject(menagers);
			ooscd.writeObject(cd);
			ooscdbought.writeObject(cdbought);
			oosCategories.writeObject(categories);
			oosSuppliers.writeObject(suppliers);

			oosCashiers.close();
			fosCashiers.close();

			oosMenagers.close();
			fosMenagers.close();

			ooscd.close();
			fosCd.close();

			ooscdbought.close();
			fosCdbought.close();

			oosCategories.close();
			fosCategories.close();

			oosSuppliers.close();
			fosSuppliers.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		System.out.println("Saved");
	}

	private static void fillSuppliers() {
		suppliers.add(new Suppliers("Supplier 1"));
		suppliers.add(new Suppliers("Supplier 2"));
		suppliers.add(new Suppliers("Supplier 3"));

		suppliers.get(0).getProductsOffered().add("Thriller");
		suppliers.get(0).getProductsOffered().add("The Bodyguard");
		suppliers.get(0).getProductsOffered().add("Revolver");

		suppliers.get(1).getProductsOffered().add("The Dark Side of the Moon");
		suppliers.get(1).getProductsOffered().add("Abbey Road");
		suppliers.get(1).getProductsOffered().add("Who's Next");

		suppliers.get(2).getProductsOffered().add("Black in Black");
		suppliers.get(2).getProductsOffered().add("Bat Out of Hell");

	}
}
