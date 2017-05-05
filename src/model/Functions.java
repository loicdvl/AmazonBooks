package model;

public class Functions {
	
	public static String echapStringSql(String str){
    	if(str ==null) {
    		return null;
    	}
    	return str.replace("'", "''");
    }

}
