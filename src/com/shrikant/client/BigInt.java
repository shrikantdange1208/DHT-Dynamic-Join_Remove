package com.shrikant.client;



import java.math.BigInteger;
import java.security.MessageDigest;

public class BigInt {
	public static void main(String args[]){
	
		BigInteger two = BigInteger.valueOf(2);
		BigInteger raiseTo = two.pow(256);
		BigInteger raiseTo3 = two.pow(252);
		BigInteger range = two.pow(254);
		range = raiseTo.subtract(BigInteger.ONE);
		String key = sha256("127.0.1.1:9092");
		
		String key90 = sha256("127.0.1.1:9090");
		BigInteger val90 = new BigInteger(key90,16);
		
		System.out.println("9092 key:"+key);
		BigInteger keyVal = new BigInteger(key,16);
		BigInteger addition = keyVal.add(raiseTo3);
		if(addition.compareTo(range)>0){
			System.out.println("In if");
			addition = addition.mod(raiseTo);
			System.out.println("addition:"+addition);
			System.out.println("String:"+addition.toString(16));
			System.out.println("comprrrrrrrrr:"+addition.compareTo(val90));
		}
		else{
			System.out.println("In else");
			System.out.println("addition:"+addition);
			String val = addition.toString(16);
			System.out.println("Val String:"+val);
		}
		//System.out.println("91 comapre:"+val91.compareTo(addition));
//		String val = addition.toString(16);
//		System.out.println("Val:"+val);
//		System.out.println("Compare:"+addition.compareTo(raiseTo));
//		BigInteger temp = addition.mod(raiseTo);
//		System.out.println("Mod:"+temp);
//		String tempStr = temp.toString(16);
//		System.out.println("Mod String:"+tempStr);
		/*addition.add(keyVal);
		String key92 = temp.toString(16);
		System.out.println("addition:"+addition.toString(16));
		addition = addition.mod(raiseTo);
		System.out.println("addition after mod:"+addition.toString(16));
		String key91 = "bc64780fee5c358b288c340a003d3274fe33fd04dce91a23fd158ea911c37b2e";
		
		System.out.println("92+255 length:"+key92);
		System.out.println("91 length:"+key91.length());*/
		
		//System.out.println("p+ 2 raise to 0:"+addition);
		//System.out.println(addition.compareTo(raiseTo));
		/*System.out.println("2 rasie to 256:"+raiseTo3);
		System.out.println(raiseTo3.subtract(addition));
		System.out.println("p+ 2 raise to 255:"+addition);
		String resultKey = addition.toString(16);
		System.out.println("Resultant key:"+resultKey);
		String key2 = "bc64780fee5c358b288c340a003d3274fe33fd04dce91a23fd158ea911c37b2e";
		
		BigInteger one  = new BigInteger(key2,16);
		
		System.out.println("equals:"+key2.equals(resultKey));
		System.out.println("compareto Strings:"+key2.compareToIgnoreCase(resultKey));
		System.out.println("BigInteger compare:"+one.compareTo(addition));
		
		System.out.println(addition.subtract(one));
		System.out.println("Compare to Function:"+compareTo(key2,resultKey));*/
		//System.out.println("Node 9091:"+);
		/*String owner = "shrikant";
		String f1 = "test.txt";
		String f2 = "example.txt";
		String f3 = "temp.txt";
		String f4 = "assignment.txt";
		String f5 = "homework.txt";
		String f6 = "project.txt";
		String f7 = "tutorial.txt";
		String f8 = "xest.txt";
		String f9 = "abc.txt";
		String f10 = "tesFile.txt";
		System.out.println(f1+":"+sha256(owner+":"+f1));
		System.out.println(f2+":"+sha256(owner+":"+f2));
		System.out.println(f3+":"+sha256(owner+":"+f3));
		System.out.println(f4+":"+sha256(owner+":"+f4));
		System.out.println(f5+":"+sha256(owner+":"+f5));
		System.out.println(f6+":"+sha256(owner+":"+f6));
		System.out.println(f7+":"+sha256(owner+":"+f7));
		System.out.println(f8+":"+sha256(owner+":"+f8));
		System.out.println(f9+":"+sha256(owner+":"+f9));
		System.out.println(f10+":"+sha256(owner+":"+f10));*/
	}
	
	public static String sha256(String base) {
	    try{
	    	//System.out.println("Base:"+base);
	        MessageDigest digest = MessageDigest.getInstance("SHA-256");
	        byte[] hash = digest.digest(base.getBytes("UTF-8"));
	        StringBuffer hexString = new StringBuffer();

	        for (int i = 0; i < hash.length; i++) {
	            String hex = Integer.toHexString(0xff & hash[i]);
	            if(hex.length() == 1) hexString.append('0');
	            hexString.append(hex);
	        }

	        return hexString.toString();
	    } catch(Exception ex){
	       throw new RuntimeException(ex);
	    }
	}
	
	static int compareTo(String key1,String key2){
		int result = 0;
		BigInteger num1 = new BigInteger(key1,16);
		BigInteger num2 = new BigInteger(key2,16);
		result = num1.compareTo(num2);
		return result;
	}

}
