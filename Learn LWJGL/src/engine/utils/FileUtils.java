package engine.utils;

import java.io.*;

public class FileUtils {
	public static String loadAsString(String path) {
		StringBuilder result=new StringBuilder();
		try(BufferedReader reader= new BufferedReader(new InputStreamReader(FileUtils.class.getResourceAsStream(path)))){
			String line="";
			while((line=reader.readLine())!=null) {
				result.append(line).append("\n");
			}
			System.out.println(result);
		}catch(IOException e) {
			System.err.println("Couldn't find the file at:"+path);
			
		}
		return result.toString();
	}

}
