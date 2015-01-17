package com.shrikant.join_remove;

import java.security.MessageDigest;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import com.shrikant.service.FileStore;


public class Remove {

	public static void main(String[] args) {
		
		String port=args[0];
		int portNumber = Integer.parseInt(port);
		TTransport transport;
		
		 try {
			transport  = new TSocket("127.0.1.1",portNumber);
			transport.open();
			TProtocol protocol = new TBinaryProtocol(transport);
			FileStore.Client client = new FileStore.Client(protocol);
			client.remove();
			transport.close();
		} catch (TTransportException e) {
			e.printStackTrace();
			System.exit(0);
		} catch (TException e) {
			e.printStackTrace();
			System.exit(0);
		}

	}
	public static String sha256(String base) {
	    try{
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

}
