package ClassAssignments;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;

class FileHandle {

	protected String path;

	/* Constructor */
	public FileHandle(String flpath) {
		this.path = flpath;
	}

	// Insert a File
	public void insert(String Filename) {
		String nwFile = this.path + "\\" + Filename;
		File Fileins = new File(nwFile);

		try {
			if (Fileins.createNewFile()) {
				System.out.println(Filename + " -New File is created!");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Delete a File
	public void delete(String Filename) {
		String dlFile = this.path + "\\" + Filename;
		File Filedel = new File(dlFile);
		try {

			boolean result = Filedel.delete();

			if (result)
				System.out.println(Filename + " -File is deleted!");
			else
				System.out.println(Filename + " -File does not exists");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
   //Search a File
	public void search(String Filename) {
		String sFile = this.path + "\\" + Filename;
		File Filesearch = new File(sFile);
		try {

			boolean result = Filesearch.exists();

			if (result)
				System.out.println(Filename + " -File exists!");
			else
				System.out.println(Filename + " -File not found");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
}

public class Assignment1 {

	public static void main(String[] args) {
		System.out.println("Assignment 1 - Core Java - Developer-Jiten ");
		System.out.println("--------------------------------------------");
		File dir = new File("C:\\Jiten\\Mxl");
		if (dir.isDirectory()) {

			List<File> files = Arrays.asList(dir.listFiles());

			// Lists only files since we have applied file filter
			// Creating a filter to return only files.
			FileFilter fileFilter = new FileFilter() {

				@Override
				public boolean accept(File file) {
					// TODO Auto-generated method stub
					return !file.isDirectory();
				}
			};

			files = Arrays.asList(dir.listFiles(fileFilter));

			System.out.println("\n List of File names(filtering out Folder names) in Ascending order");
			System.out.println("--------------------------------");

			Collections.sort(files);

			// List all Files in ascending order
			for (File filenm : files) {
				System.out.println(filenm.getName());
			}

			System.out.println("--------------------------------");

			FileHandle f = new FileHandle("C:\\Jiten\\Mxl");

			// Business Level Operations
			Scanner scan = new Scanner(System.in);
			char ch;
			do {
				System.out.println("\nQueue Operations");
				System.out.println("1. Create");
				System.out.println("2. Delete");
				System.out.println("3. Search");
				System.out.println("4. Return to main context");
				System.out.println("5. Exit Application");
				
				int choice = scan.nextInt();
				switch (choice) {
				case 1: {

					System.out.println("Enter the file name to be Created");
					Scanner inpfile = new Scanner(System.in);
					String flname = inpfile.next();					
					f.insert(flname);
					break;
					
				}
				case 2: {

					System.out.println("Enter the file name to be Deleted");
					String fldel = new Scanner(System.in).next();
					f.delete(fldel);
					break;
				}
				case 3: {

					System.out.println("Enter the file name to be Searched");
					String flsearch = new Scanner(System.in).next();
					f.search(flsearch);
					break;
				}
				case 4: {

					System.out.println("Return to main context");
					break;
				}
				case 5: {

					System.out.println("Application Closed");
					System.exit(0);
				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + choice);
				}
				/* display list of files after operation */

				System.out.println("\nDo you want to continue (Type y or n) \n");
				ch = scan.next().charAt(0);

			} while (ch == 'Y' || ch == 'y');

		}

	}

}
