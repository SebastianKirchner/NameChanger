import java.io.File;
import java.util.Scanner;

public class NameChanger {

	private static Scanner sc;
	private static String path, new_name, file_extension, name_buff;
	private static int todo, counter, nr_files;
	private static File folder;
	private static File[] list_of_files;

	public static void main(String[] args) {
		
		counter = 0;
		nr_files = 0;
		sc = new Scanner(System.in);
		System.out.println("\n>>> Welcome to the CommandLine File_Name_Changer<<<");

		boolean valid = false;
		while (!valid && nr_files == 0) {
			System.out.println("\nEnter valid directory Path (e.g. \"C:\\Users\\JaneDoe\\ \") containing files:");

			if (sc.hasNext()) {
				path = sc.nextLine(); 			
			} 

			folder = new File(path);
			if (folder.exists()) {
				valid = true;
				list_of_files = folder.listFiles();
				nr_files = list_of_files.length;				
			}
		}

		System.out.println("\nWhat do you want to do with the " + nr_files + " file(s)?");
		System.out.println("\t- 0 ==> change all file names");
		System.out.println("\t- 1 ==> change all file names with certain identifier");
		System.out.println("\t- 2 ==> delete a part of all the filenames");
		System.out.println("\t- 3 ==> Quit this shit!");
		if (sc.hasNextInt()) {
			todo = sc.nextInt();
			sc.nextLine();
			if (todo == 0) {
				changeAll();
			} else if (todo == 1) {
				changeAllWithIdentifier();
			} else if (todo == 2) {
				deletePartOfName();
			} else if (todo == 3) {
				return;
			} else {
				System.out.println("\nInvalid, run again :)");
			}
		}

		System.out.println("\n>>> Operation Finished <<<");
	}

	private static void changeAll() {
		System.out.println("\nPlease enter the new Name:");
		if (sc.hasNext()) {
			new_name = sc.nextLine();
		}

		for (File f : list_of_files) {
			name_buff = f.getName();
			f.renameTo(new File(newName(name_buff)));
		}
	}

	private static void changeAllWithIdentifier() {
		System.out.println("\nGive the identifier of the files that should be changed:");
		String identifier = "";
		if (sc.hasNext()) {
			identifier = sc.nextLine();
		}
		
		sc.nextLine();
		System.out.println("\nAll Files containing " + identifier + " should be renamed to:");
		if (sc.hasNext()) {
			new_name = sc.nextLine();
		}
		
		for (File f : list_of_files) {
			name_buff = f.getName();
			if (name_buff.contains(identifier)) {
				f.renameTo(new File(newName(name_buff)));
			}
		}
	}

	private static void deletePartOfName() {
		System.out.println("\nGive the part of the name that should be deleted from all files:");
		String to_delete = "";
		if (sc.hasNext()) {
			to_delete = sc.nextLine();
		}
		
		System.out.println("\nAll instances of \"" + to_delete + "\" in filenames will be removed ...");
		int begin = 0, end = 0;
		for (File f : list_of_files) {
			name_buff = f.getName();
			if (name_buff.contains(to_delete)) {
				begin = name_buff.indexOf(to_delete);
				end = begin + to_delete.length();
				name_buff = path + "\\" + name_buff.substring(0, begin) + name_buff.substring(end);
				f.renameTo(new File(name_buff));
			}
		}
	}

	private static String zeroPad(int max, int curr) {
		String zeros = "";
		if (max < 100) {
			zeros += curr < 10 ? "0" : "";
		} 
		else if (max < 1000) {
			zeros += curr < 100 ? (curr < 10 ? "00" : "0") : "";
		}
		return zeros;
	}
	
	private static String newName(String s) {
		file_extension = name_buff.substring(name_buff.lastIndexOf("."));
		name_buff = path + "\\" + new_name + zeroPad(nr_files, counter) + "" +counter + file_extension;
		++counter;
		return name_buff;
	}

}
