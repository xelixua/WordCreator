package xyz.maksimenko.iqbuzztt2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;

public class TextCreator {	
	private String characters = "abcdefghijklmoprstu";
	
	public static void main(String[] args){
		//creation of file with 1000000 random words
		//uncomment to use
		/*System.out.println("Program started");
		TextCreator tc = new TextCreator();
		String text = tc.createText();
		tc.writeToFile(text);*/
		
		
		//search by word in the file (specified in Query contructor
		System.out.println("Creating query");
		Query qu = new Query("plbbghe");
		System.out.println("Opening file");
		qu.openFile();
		System.out.println("Trying to search");
		SearchResult sr = qu.search();
		int posit = sr.getTargetPosition();
		System.out.println();
		if(posit > 0){
			System.out.println("Found target word at " + posit);
			if(sr.getPreWord() != null){
				System.out.println("Previous word: " + sr.getPreWord());
			}
			if(sr.getPostWord() != null){
				System.out.println("Next word: " + sr.getPostWord());
			}
		} else {
			System.out.println("Not found");
		}
		
	}
	
	public void writeToFile(String text){
		System.out.println("writing to file");
		PrintWriter prWriter = null;
		File f; 
		try {
			try {
				prWriter = new PrintWriter("D:\\test_file.txt", "UTF-8");
				prWriter.write(text);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			f = new File("test_file.txt");
			try {
				prWriter = new PrintWriter("D:\\test_file.txt", "UTF-8");
				prWriter.write(text);
			} catch (FileNotFoundException | UnsupportedEncodingException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try {
				f.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			if(prWriter != null)
				prWriter.close();
			
		}
		
	}
	
	public String createText() {
		StringBuilder builder = new StringBuilder(Integer.MAX_VALUE/10);
		Random random = new Random();
		for(int i = 0; i < 1000000; i++){
			int length = random.nextInt(8) + 2;
			System.out.print(i + ": ");
			builder.append(generateWord(length));
		}
		
		//deleting last space
		builder.deleteCharAt(builder.length() - 1);
		return builder.toString();
	}
	
	private String generateWord(int length){
		Random random = new Random();
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < length; i++)
	    {
	        builder.append(characters.charAt(random.nextInt(characters.length())));
	    }
		
		builder.append(' ');
		System.out.println(builder.toString());
		return builder.toString();
	}
}
