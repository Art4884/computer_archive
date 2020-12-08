package com.company;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		System.out.println("This program is a data base of computers. There stored name, firm, main tech characteristic, price and warranty of computers sorted by price.");
		startProgram();
	}

	public static void startProgram() throws IOException {
		boolean isExit = true;
		ArrayList<Computer> array = new ArrayList<>();
		loadBeforeStart(array);
		while(isExit){
			String selection = actionSelection();
			switch (selection) {
				case "L":
					readFromFile(array);
					System.out.println();
					break;
				case "C":
					editComputer(array);
					System.out.println();
					break;
				case "S":
					saveComputerList(array);
					System.out.println();
					break;
				case "V":
					displayAllComputers(array);
					System.out.println();
					break;
				case "E":
					afterExitSave(array);
					isExit = false;
					break;
			}
		}
	}



	public static String actionSelection() {
		Scanner s = new Scanner(System.in);
		boolean isCorrect;
		HashSet<String> check = new HashSet<>();
		check.add("L");
		check.add("C");
		check.add("S");
		check.add("V");
		check.add("E");
		String choice;
		System.out.println("Please, choose what you want to do:\n" +
				"Press <L> if you want to load list of computers.\n" +
				"Press <C> if you want to change data.\n" +
				"Press <S> if you want to save computer list.\n" +
				"Press <V> if you want to display all computers.\n" +
				"Press <E> if you want to exit from program.");
		do{
			isCorrect = false;
			choice = s.nextLine();
			choice = choice.toUpperCase();
			if(choice.length() != 1){
				System.out.println("Error! Please, enter one letter!");
				isCorrect = false;
			}else{
				if(check.contains(choice)){
					isCorrect = true;
				}else{
					System.out.println("Error! Please, enter only available letters!");
					isCorrect = false;
				}
			}
		}while(!isCorrect);
		return choice;
	}

	public static void loadBeforeStart(ArrayList<Computer> array) throws IOException {
		FileReader fin = new FileReader("D:\\OAIP\\Java\\Системные файлы\\SystemFile41.txt");
		Scanner scan = new Scanner(fin);
		while(scan.hasNextLine()){
			String line = scan.nextLine();
			String[] strArr = line.split(" ");
			double tempCost = Double.parseDouble(strArr[3]);
			int tempWarranty = Integer.parseInt(strArr[4]);
			Computer comp = new Computer(strArr[0], strArr[1], strArr[2], tempCost, tempWarranty);
			array.add(comp);
			fin.close();
		}
	}

	public static void afterExitSave(ArrayList<Computer> array) throws IOException {
		FileWriter fout = new FileWriter("D:\\OAIP\\Java\\Системные файлы\\SystemFile41.txt");
		for(int i = 0; i < array.size();i++){
			String name = array.get(i).getName();
			String firm = array.get(i).getFirm();
			String tech = array.get(i).getTech();
			String price = Double.toString(array.get(i).getPrice());
			String warranty = Integer.toString(array.get(i).getWarranty());
			fout.write(name + " " + firm + " " + tech + " " + price + " " + warranty + "\n");
		}
		fout.close();
	}

	public static void saveComputerList(ArrayList<Computer> array){
		Scanner s = new Scanner(System.in);
		boolean isCorrect;
		do{
			try{
				isCorrect = true;
				System.out.println("Please, enter a path to file where will be stored your computer list:");
				String OutPath = s.nextLine();
				FileWriter fout = new FileWriter(OutPath);
				for(int i = 0; i < array.size();i++){
					String name = array.get(i).getName();
					String firm = array.get(i).getFirm();
					String tech = array.get(i).getTech();
					String price = Double.toString(array.get(i).getPrice());
					String warranty = Integer.toString(array.get(i).getWarranty());
					fout.write("Computer #" + (i+1) + " " + name + " " + firm + " " + tech + " " + price + " " + warranty + "\n");
				}
				fout.close();
			}catch (FileNotFoundException e){
				System.out.println("Error! Sorry, but file was not found! Check your input and try again.");
				isCorrect = false;
			}catch (IOException e){
				System.out.println("Error! Unable to open this file. Please check the file and try again.");
				isCorrect = false;
			}
		}while(!isCorrect);
	}

	public static void readFromFile(ArrayList<Computer> array) {
		boolean isCorrect;
		do{
			Scanner s = new Scanner(System.in);
			try{
				isCorrect = true;
				System.out.println("Please, enter a path to file where stored computer list.");
				String inPath = s.nextLine();
				FileReader fin = new FileReader(inPath);
				Scanner scan = new Scanner(fin);
				while(scan.hasNextLine()){
					String line = scan.nextLine();
					String[] strArr = line.split(" ");
					if(strArr.length != 5){
						System.out.println("Error! Please, check your file. There is more or less data then need.");
						isCorrect = false;
					}else{
						double tempCost = Double.parseDouble(strArr[3]);
						int tempWarranty = Integer.parseInt(strArr[4]);
						if((tempWarranty > 24) || (tempWarranty < 0)){
							System.out.println("Error! Your warranty is bigger or less then it cab be! Remember, warranty must be less then 24 months, but bigger then 0.");
							isCorrect = false;
						}else if(tempCost < 0){
							System.out.println("Error! Price can not be less then 0.");
							isCorrect = false;
						}else{
							Computer comp = new Computer(strArr[0], strArr[1], strArr[2], tempCost, tempWarranty);
							array.add(comp);
						}
						fin.close();
					}
				}
			}catch (FileNotFoundException e){
				System.out.println("Error! Sorry, but file was not found! Check your input and try again.");
				isCorrect = false;
			}catch (IOException e){
				System.out.println("Error! Unable to open this file. Please check the file and try again.");
				isCorrect = false;
			}catch (NumberFormatException e){
				System.out.println("Error! There is incorrect data in your file. Please, check it and try again.");
				isCorrect = false;
			}
		}while(!isCorrect);
	}

	public static void editComputer(ArrayList<Computer> array){
		boolean isExit = true;
		while(isExit){
			String selection =  changeSelection();
			switch (selection) {
				case "A":
					addNewComputer(array);
					System.out.println();
					break;
				case "C":
					changeComputerData(array);
					System.out.println();
					break;
				case "D":
					deleteComputer(array);
					System.out.println();
					break;
				case "E":
					isExit = false;
					break;
			}
					}
	}




	public static String changeSelection(){
		Scanner s = new Scanner(System.in);
		boolean isCorrect;
		HashSet<String> check = new HashSet<>();
		check.add("A");
		check.add("C");
		check.add("D");
		check.add("E");
		String choice;
		System.out.println("Please, choose what you want to do:\n" +
				"Press <A> if you want to add new computer in list of computers.\n" +
				"Press <C> if you want to change data in computers.\n" +
				"Press <D> if you want to delete computer from computer list.\n" +
				"Press <E> if you want to exit to previous menu.");
		do{
			isCorrect = false;
			choice = s.nextLine();
			choice = choice.toUpperCase();
			if(choice.length() != 1){
				System.out.println("Error! Please, enter one letter!");
				isCorrect = false;
			}else
				if(check.contains(choice)){
				isCorrect = true;
			}else{
				System.out.println("Error! Please, enter only available letters!");
				isCorrect = false;
			}

		}while(!isCorrect);
		return choice;
	}

	public static void deleteComputer(ArrayList<Computer> array){
		Scanner s = new Scanner(System.in);
		boolean isCorrect;
		displayAllComputers(array);
		do{
			isCorrect = true;
			System.out.print("Please, enter a number of computer which will be deleted: ");
			int delNum = Integer.parseInt(s.nextLine());
			if(delNum > array.size()){
				System.out.println("Error! You input number bigger then then quantity of computers.");
				isCorrect = false;
			}else
				if(delNum < array.size()){
					System.out.println("Error! Number you input is less then quantity of computers.");
					isCorrect = false;
				}else{
					array.remove(delNum - 1);
					isCorrect = true;
				}

		}while(!isCorrect);
	}

	public static void addNewComputer(ArrayList<Computer> array){
		boolean isCorrect;
		do{
			try{
				isCorrect = true;
				Scanner s = new Scanner(System.in);
				String[] forInput = {" name: "," firm: ", " main tech characteristic: ", " price: ", " warranty: "};
				String[] forInfo = new String[5];
				for(int i = 0; i < forInput.length; i++){
					System.out.print("Please, enter" + forInput[i]);
					forInfo[i] = s.nextLine();
				}
				double tempCost = Double.parseDouble(forInfo[3]);
				int tempWarranty = Integer.parseInt(forInfo[4]);
				if((tempWarranty > 24) || (tempWarranty < 0)){
					System.out.println("Error! Your warranty is bigger or less then it cab be! Remember, warranty must be less then 24 months, but bigger then 0.");
					isCorrect = false;
				}else
					if(tempCost < 0){
					System.out.println("Error! Price can not be less then 0.");
					isCorrect = false;
				}else{
				Computer comp = new Computer(forInfo[0], forInfo[1], forInfo[2], tempCost, tempWarranty);
				array.add(comp);
				}
			}catch (NumberFormatException e){
				System.out.println("Error! Please, remember that price and warranty must be only numbers.");
				isCorrect = false;
			}
		}while (!isCorrect);
	}

	public static void changeComputerData(ArrayList<Computer> array){
		Scanner s = new Scanner(System.in);
		boolean isCorrect;
		displayAllComputers(array);
		do{
			try{
				isCorrect = true;
				System.out.print("Please, enter a number of computer you want to change: ");
				int compNum = Integer.parseInt(s.nextLine());
				if(compNum > array.size()){
					System.out.println("Error! You input number which is bigger then quantity of computers.");
					isCorrect = false;
				}else
					if(compNum < 0l){
						System.out.println("Error! You input number which is less then one.");
						isCorrect = false;
					}else{
						String[] forInput = {" name: "," firm: ", " main tech characteristic: ", " price: ", " warranty: "};
						String[] forInfo = new String[5];
						for(int i = 0; i < forInput.length; i++){
							System.out.print("Please, enter new" + forInput[i]);
							forInfo[i] = s.nextLine();
						}
						double tempCost = Double.parseDouble(forInfo[3]);
						int tempWarranty = Integer.parseInt(forInfo[4]);
						if((tempWarranty > 24) || (tempWarranty < 0)){
							System.out.println("Error! Your warranty is bigger or less then it cab be! Remember, warranty must be less then 24 months, but bigger then 0.");
							isCorrect = false;
						}else if(tempCost < 0){
							System.out.println("Error! Price can not be less then 0.");
							isCorrect = false;
						}else{
							array.get(compNum - 1).setName(forInfo[0]);
							array.get(compNum - 1).setFirm(forInfo[1]);
							array.get(compNum - 1).setTech(forInfo[2]);
							array.get(compNum - 1).setPrice(tempCost);
							array.get(compNum - 1).setWarranty(tempWarranty);
						}
					}

			}catch (NumberFormatException e){
				System.out.println("Error! Please, remember that price and warranty must be numbers only.");
				isCorrect = false;
			}
		}while (!isCorrect);
	}

	public static void displayAllComputers(ArrayList<Computer> array){
		bubSort(array);
		for(int i = 0; i < array.size(); i++){
			String name = array.get(i).getName();
			String firm = array.get(i).getFirm();
			String tech = array.get(i).getTech();
			String price = Double.toString(array.get(i).getPrice());
			String warranty = Integer.toString(array.get(i).getWarranty());
			System.out.println("Computer #" + (i+1) + " " + name + " " + firm + " " + tech + " " + price + " " + warranty);
		}
	}

	public static void bubSort(ArrayList<Computer> array){
		int n = array.size();
		for (int i = 0; i < n-1; i++){
			for (int j = 0; j < n-i-1; j++){
				if (array.get(j).getPrice() < array.get(j + 1).getPrice()) {
					String tempStr = array.get(j).getName();
					array.get(j).setName(array.get(j + 1).getName());
					array.get(j + 1).setName(tempStr);
					tempStr = array.get(j).getFirm();
					array.get(j).setFirm(array.get(j + 1).getFirm());
					array.get(j + 1).setFirm(tempStr);
					tempStr = array.get(j).getTech();
					array.get(j).setTech(array.get(j + 1).getTech());
					array.get(j + 1).setTech(tempStr);
					double dTemp = array.get(j).getPrice();
					array.get(j).setPrice(array.get(j + 1).getPrice());
					array.get(j + 1).setPrice(dTemp);
					int temp = array.get(j).getWarranty();
					array.get(j).setWarranty(array.get(j + 1).getWarranty());
					array.get(j + 1).setWarranty(temp);
				}
			}
		}
	}
}
