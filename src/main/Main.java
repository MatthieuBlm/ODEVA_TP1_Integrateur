package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
	
	public static void main(String[] args) {
		BufferedReader br = null;
		FileReader fr = null;
		List<String> theme = new ArrayList<>();
		List<String> name = new ArrayList<>();
		String file = "";

		try {

			fr = new FileReader(args[0]);
			br = new BufferedReader(fr);

			String sCurrentLine;
			String [] tmp = null;
			
			while ((sCurrentLine = br.readLine()) != null) {
				file += sCurrentLine + "\n";
				tmp = sCurrentLine.split(";");
				
				if(!name.contains(tmp[1]))
					name.add(tmp[1]);
				
				if(!theme.contains(tmp[2]))
					theme.add(tmp[2]);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
		System.out.println(theme.size() + " themes.");
		System.out.println(name.size() + " noms.");
		
		int [][] matrice = new int[theme.size()][name.size()];
		String [] splitedFile = file.split("\n");
		
		for (int i = 0; i < matrice.length; i++) {				// Theme
			for (int j = 0; j < matrice[0].length; j++) {			// Name
				for (int x = 0; x < splitedFile.length; x++) {			// Parours tous le fichier
					String tmp = splitedFile[x];
					if(tmp.contains(theme.get(i)) && tmp.contains(name.get(j)))
						matrice[i][j]++;
				}
			}
		}
		
		printMatrice(matrice);
		
		// ### Save name ###
		String nameStr = "";
		for (String string : name) {
			nameStr += string + ";";
		}
		printInFile("name", nameStr);

		// ### Save theme ###
		String themeStr = "";
		for (String string : theme) {
			themeStr += string + ";";
		}
		printInFile("themes", themeStr);
		
		// ### Matrice ###
		String matriceStr = "";
		for (int i = 0; i < matrice.length; i++) {
			for (int j = 0; j < matrice[0].length; j++) {
				matriceStr += matrice[i][j] + ";";
			}
			matriceStr += "\n";
		}
		printInFile("matrice", matriceStr);
		
	}
	
	public static void printMatrice(int[][] matrice){
		for (int i = 0; i < matrice.length; i++) {
			for (int j = 0; j < matrice[0].length; j++) {
				System.out.print(matrice[i][j] + " ");
			}
			System.out.println("");
		}
	}
	
	public static void printInFile(String path, String content){
		BufferedWriter bw = null;
		FileWriter fw = null;

		try {

			fw = new FileWriter(path);
			bw = new BufferedWriter(fw);
			bw.write(content);

			System.out.println("Done");

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}

	}
	
}
