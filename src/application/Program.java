package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Product;

public class Program {

	public static void main(String[] args) throws Exception {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		// Instanciando a lista;
		
		List<Product> list = new ArrayList<>();
		
		// Entrada do caminho do arquivo pelo usuario;
		
		System.out.print("Enter file path: ");
		String sourceFileStr = sc.nextLine();
		
		// chamada do método para informar o caminho do arquivo
		
		File sourceFile = new File(sourceFileStr);
		String sourceFolderStr = sourceFile.getParent();
		
		//Criando um novo diretório
		
		boolean success = new File(sourceFolderStr + "\\out").mkdir();
		
		//Criando o arquivo dentro do diretorio
		
		String targetFileStr = sourceFolderStr + "\\out\\summary.csv";
		
		//Lendo o arquivo de entrada de dados e tratando erros
		
		try(BufferedReader br = new BufferedReader(new FileReader(sourceFileStr))){
			
			String itemCsv = br.readLine();
			while (itemCsv != null) {
				String[] fields = itemCsv.split(",");
				String name = fields[0];
				double price = Double.parseDouble(fields[1]);
				int quantity = Integer.parseInt(fields[2]);
				
				list.add(new Product(name, price, quantity));
				
				itemCsv = br.readLine();
			}
			
			//escrevendo o arquivo de entrada de dados
			
			try(BufferedWriter bw = new BufferedWriter(new FileWriter(targetFileStr))){
				for (Product item : list) {
					bw.write(item.getName() + "," + String.format("%.2f", item.total()));
					bw.newLine();
				}
				
				System.out.println(targetFileStr + " Created");
				
			}
			catch (IOException e) {
				System.out.println("Error writing file: " + e.getMessage());				
			}
			
		}
		catch (IOException e) {
			System.out.println("Error readring file: " + e.getMessage());
		}
		
		sc.close();

	}

}
