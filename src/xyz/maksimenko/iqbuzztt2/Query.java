package xyz.maksimenko.iqbuzztt2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Query {
	private String data;
	private String query;
	
	public Query(String query){
		this.query = query;
	}
	
	public void openFile(){
		try {
			BufferedReader bufRead = new BufferedReader(new InputStreamReader(new FileInputStream("D:\\test_file.txt"),"UTF-8"));
			String currentLine;
			StringBuilder builder = new StringBuilder(Integer.MAX_VALUE/10);
			while((currentLine = bufRead.readLine()) != null){
				builder.append(currentLine);
			}
			bufRead.close();
			this.data = builder.toString();
			/*Arrays.asList(data.split(" ")).parallelStream().forEach(word -> {
				
				int index = data.indexOf(word);
				positions.put(word, index);
				reversedPositions.put(index, word);
			}
			);*/
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public SearchResult search(){
		/*Integer targetPosition = positions.get(query);
		if(targetPosition != null){
			//if word is found
			SearchResult sr = new SearchResult();
			sr.setTargetPosition(targetPosition);
			if(targetPosition > 0){
				//if word is not the first
				String preWord;
				while((preWord = reversedPositions.get(targetPosition)) == null){
					targetPosition--;
				}
				sr.setPreWord(preWord);
			}
			if(targetPosition + query.length() < data.length() - 1){
				//if word is not the last
				String postWord;
				while((postWord = reversedPositions.get(targetPosition)) == null){
					targetPosition++;
				}
				sr.setPostWord(postWord);
			}
			return sr;
		}
		return null;*/
		int targetPosition = data.indexOf(query);
		SearchResult sr = new SearchResult();
		sr.setTargetPosition(targetPosition);
		if(targetPosition > 0){
			//if word is not the first
			int prePosition = targetPosition - 1;
			prePosition--;
			StringBuilder builder = new StringBuilder(10);
			while(data.charAt(prePosition) != ' '){
				prePosition--;
			}
			
			sr.setPreWord(data.substring(prePosition + 1, targetPosition - 1));
		}
		if(targetPosition + query.length() < data.length() - 1){
			//if word is not the last
			int postPosition = targetPosition + query.length();
			postPosition++;
			while(data.charAt(postPosition) != ' '){
				postPosition++;
			}
			sr.setPostWord(data.substring(targetPosition + query.length() + 1, postPosition));
			return sr;
		}
		return null;
	}
}
