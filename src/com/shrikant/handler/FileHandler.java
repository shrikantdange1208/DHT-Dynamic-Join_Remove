package com.shrikant.handler;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import com.shrikant.service.FileStore;
import com.shrikant.service.NodeID;
import com.shrikant.service.RFile;
import com.shrikant.service.RFileMetadata;
import com.shrikant.service.SystemException;
public class FileHandler implements FileStore.Iface{
	
	public static Map<String,RFile> fileStorage;
	private List<NodeID> fingerTable;
	private static TTransport transport;
	private static TProtocol protocol;
	int portNumber;
	String deleteFile;
	NodeID thisNode=null;
	NodeID predecessor=null;
	
	public FileHandler(int port){
		
		fingerTable = new ArrayList<NodeID>();
		fileStorage = new HashMap<String,RFile>();
		
		portNumber=port;
		String tempPort = Integer.toString(port);
		
		String currentNodeKey;
		String currentIp=null;
		try {
			currentIp = InetAddress.getLocalHost().getHostAddress();
			
		} catch (UnknownHostException e) {
			
			e.printStackTrace();
		}
		currentNodeKey = sha256(currentIp+":"+tempPort);
		
		thisNode = new NodeID(currentNodeKey,currentIp,port);
		
		for(int i=0;i<256;i++){
			fingerTable.add(thisNode);
		}
		predecessor = thisNode;
		
		
		File oldFiles = new File("./output/");
		File files[] = oldFiles.listFiles();
		for(File file:files){
			if(!(file.isDirectory())){
				file.delete();
			}
		}
		
		
	}
	
	
	
	public boolean belongsTo(String fileName,String user){
		boolean belongs = false;
		String thisKey = thisNode.getId();
		String predKey = predecessor.getId();
		String fileKey = sha256(user+":"+fileName);
		
		if(thisKey.compareToIgnoreCase(predKey)==0){
			belongs = true;
		}
		else if(thisKey.compareToIgnoreCase(predKey)>0){
			if((fileKey.compareToIgnoreCase(predKey)>0)&&(fileKey.compareToIgnoreCase(thisKey)<=0)){
				belongs = true;
			}
		}
		else if(thisKey.compareToIgnoreCase(predKey)<0){
			if((fileKey.compareToIgnoreCase(predKey)>0)||(fileKey.compareToIgnoreCase(thisKey)<=0)){
				belongs = true;
			}
		}
		else{
			belongs = false;
		}
		return belongs;
	}
	
	@Override
	public void writeFile(RFile rFile) throws SystemException, TException {
		
		boolean belongs = false;
		RFileMetadata userMetadata = rFile.getMeta();
		//String absoluteFileName = userMetadata.getFilename();
		//String[] fileParts = absoluteFileName.split("\\.");
		String fileName = userMetadata.getFilename();
		String user = userMetadata.getOwner();
		belongs = belongsTo(fileName,user);
		
		SystemException exception=null;
		if(belongs){
			File newFile=null;
			RFile serverRFile = null;
			RFileMetadata serverMetadata = null;
			String contents = rFile.getContent();
			//Check if the file already exists
			if(fileStorage.containsKey(fileName)){
				serverRFile = fileStorage.get(fileName);
				serverMetadata = serverRFile.getMeta();
				if(userMetadata.getOwner().equals(serverMetadata.getOwner())){
					//Overwrite file
					File serverFile = new File("./output/"+fileName);
					try {
						PrintWriter writer = new PrintWriter(serverFile);
						writer.write(contents);
						writer.close();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					//update data structure(Time Stamps , content lenght , content hash)
					  
					  serverRFile.setContent(contents);
					  serverMetadata.setVersion(serverMetadata.getVersion()+1);
					  serverMetadata.setUpdated(System.currentTimeMillis());
					  serverMetadata.setDeleted(0);
					  serverMetadata.setContentLength(contents.length());
					  String shaKey = sha256(contents);
					  serverMetadata.setContentHash(shaKey);
					  serverRFile.setMeta(serverMetadata);
					 
					  fileStorage.put(fileName, serverRFile);
					//update data structure(Time Stamps , content lenght , content hash)
				}
				else{
					exception = new SystemException();
					exception.setMessage("You cant overwrite a file of different owner. Enter valid arguments");
					throw exception;
				}
			}
			else{
				//create a new file
				
				try {
					newFile = new File("./output/"+fileName);
					FileOutputStream is = new FileOutputStream(newFile);
				    OutputStreamWriter osw = new OutputStreamWriter(is);
					BufferedWriter writer = new BufferedWriter(osw);
					writer.write(rFile.getContent());
					writer.close();
					is.close();
					osw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				rFile.setContent(contents);
				userMetadata.setCreated(System.currentTimeMillis());
				userMetadata.setUpdated(System.currentTimeMillis());
				userMetadata.setVersion(0);
				userMetadata.setDeleted(0);
				userMetadata.setContentLength(contents.length());
				String shaHash = sha256(contents);
				userMetadata.setContentHash(shaHash);
				rFile.setMeta(userMetadata);
				
				fileStorage.put(fileName, rFile);
			}
		}
		else{
			exception = new SystemException();
			exception.setMessage("This file doesnt belong to this node. Try again");
			throw exception;
		}
		
		
	}
	
	@Override
	public RFile readFile(String filename, String owner)
			throws SystemException, TException {
		
		boolean belongs = false;
		belongs = belongsTo(filename,owner);
		SystemException exception=null;
		if(belongs){
			RFile rFile=null;
			
			String key = filename;
			if(!(fileStorage.containsKey(key))){
				exception= new SystemException();
				exception.setMessage("File not found. Please enter valid file name");
				throw exception;
			}
			else{
				rFile = fileStorage.get(key);
				RFileMetadata metadata= rFile.getMeta();
				if(metadata.getOwner().equals(owner)){
					if(metadata.getDeleted()==0){
						return rFile;
					}
					else{
						exception= new SystemException();
						exception.setMessage("This file is deleted");
						throw exception;
					}
					
				}
				else{
					exception= new SystemException();
					exception.setMessage("This file is not owned by the specified owner");
					throw exception;
				}
			}
		}
		else{
			exception = new SystemException();
			exception.setMessage("File doesn't belong to this node. Try again");
			throw exception;
		}
		
		
	}

	@Override
	public void deleteFile(String filename, String owner)
			throws SystemException, TException {
		
		boolean belongs = false;
		belongs = belongsTo(filename,owner);
		SystemException exception=null;
		if(belongs){
			
			RFile rFile;
			String key = filename;
			rFile = fileStorage.get(key);
			if(rFile==null){
				exception= new SystemException();
				exception.setMessage("File not found. Please enter valid file name");
				throw exception;
			}
			else{
				RFileMetadata metadata= rFile.getMeta();
				if(metadata.getOwner().equals(owner)){
					if(metadata.getDeleted()==0){
						File file = new File("./output/"+filename);
						metadata.setDeleted(System.currentTimeMillis());
						rFile.setContent(null);
						
						rFile.setMeta(metadata);
						fileStorage.put(key, rFile);
						file.delete();
					}
					else{
						exception= new SystemException();
						exception.setMessage("This file is already deleted");
						throw exception;
					}
					
				}
				else{
					exception= new SystemException();
					exception.setMessage("This file is not owned by the specified owner");
					throw exception;
				}
			}

		}
		else{
			exception = new SystemException();
			exception.setMessage("File doesn't belongs to this node. Try again");
			throw exception;
		}
		
		
	}

	//@Override
	/*public void setFingertable(List<NodeID> node_list) throws TException {
		fingerTable = node_list;
		
	}*/

	@Override
	public NodeID findSucc(String key) throws SystemException, TException {
		
		//check if the current node is the successor of the specified key
		//If yes, return this node else call findPred(key)
		
		
		
		String currentNodeKey;
		NodeID successorNode=null,predecessorNode=null;
		String currentIp=null;
		try {
			 currentIp = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		String port = Integer.toString(portNumber);
		currentNodeKey = sha256(currentIp+":"+port);
		
		//check if the current node is the successor of the specified key
		//If yes, return this node else call findPred(key)
		int result=keyCompareTo(currentNodeKey,key);
		if(result==0){
			successorNode = new NodeID();
			successorNode.setId(currentNodeKey);
			successorNode.setIp(currentIp);
			successorNode.setPort(portNumber);
			
		}else{
			try{
				predecessorNode = findPred(key);
				if(predecessorNode==null){
					SystemException nullException = new SystemException();
					nullException.setMessage("Node cannot be found");
					throw nullException;
				}
			}catch(SystemException exception){
				throw exception;
			}
			
			if(predecessorNode!=null){
				String host = predecessorNode.getIp();
				int pPort = predecessorNode.getPort();
				if(pPort==portNumber){
					successorNode=this.getNodeSucc();
				}
				else{
					try {
						transport  = new TSocket(host,pPort);
						transport.open();
						protocol = new TBinaryProtocol(transport);
						FileStore.Client client = new FileStore.Client(protocol);
						try {
							successorNode=client.getNodeSucc();
						} catch (SystemException e) {
							throw e;
						} catch (TException e) {
							e.printStackTrace();
							System.exit(0);
						}
						transport.close();
					} catch (TTransportException e) {
						e.printStackTrace();
						System.exit(0);
					}
				}	
			}
		}		 
		return successorNode;
	}

	@Override
	public NodeID findPred(String key) throws SystemException, TException {
		//key.keyCompareTo(currentKey);
		//negative value if specified key is greater;
		//zero if specified key is equal;
		//positive value if specified key is smaller;
		
		NodeID predNode=null,tempNode,currentNode,targetNode;
		String currentNodeKey;
		String currentIp=null;
		try {
			currentIp = InetAddress.getLocalHost().getHostAddress();
			
		} catch (UnknownHostException e) {
			
			e.printStackTrace();
		}
		String port = Integer.toString(portNumber);
		currentNodeKey = sha256(currentIp+":"+port);
		
		
		String secondKey;
		int result,value,tempResult,lessValue=0,greaterValue=0;
		
		//Create current node object
		currentNode = new NodeID();
		currentNode.setId(currentNodeKey);
		currentNode.setIp(currentIp);
		currentNode.setPort(portNumber);
		//Create current node object
		
		//Check if key lies between currentNode and First entry in fingerTable
		//If satisfied, currentNode is the predecessor
		
		if(fingerTable.size()>0){
			
			tempNode = fingerTable.get(0);
			secondKey = tempNode.getId();
			
			//Check if second key is smaller than the first key to adjust order of comparison
			result=keyCompareTo(currentNodeKey,secondKey);
			
			if(result>0){
				
				value=keyCompareTo(key,secondKey);
				lessValue=keyCompareTo(key,secondKey);
				greaterValue=keyCompareTo(key,currentNodeKey);
				if(lessValue<=0||greaterValue>=0){
					predNode=currentNode; 
				}
				else{
					
					targetNode = comparefingerTable(key);
					try{
						predNode = nextRpcCall(targetNode,key);
					}catch(SystemException exception){
						throw exception;
					}
				}
			}else if(result==0){
				predNode=currentNode;
				return predNode;
			}else if(result<0){
				
				value=keyCompareTo(key,currentNodeKey);
				
				if(value>0){
					tempResult = keyCompareTo(key,secondKey);
					if(tempResult<=0){
						predNode = currentNode;
					}
					else{
						targetNode = comparefingerTable(key);
						try{
							predNode = nextRpcCall(targetNode,key);
						}catch(SystemException exception){
							throw exception;
						}
						//RPC on targetNode
					}
					
				}
				else{
					targetNode = comparefingerTable(key);
					try{
						predNode = nextRpcCall(targetNode,key);
					}catch(SystemException exception){
						throw exception;
					}
					//RPC on targetNode
				}
			}
		}
		else{
			return null;
		}
		
		return predNode;
	}
	
private NodeID comparefingerTable(String key) {
		
		//key.keyCompareTo(currentKey);
		//negative value if specified key is greater;
		//zero if specified key is equal;
		//positive value if specified key is smaller;
		
		NodeID firstNode,secondNode,targetNode=null;
		String firstKey,secondKey;
		int result,value,tempResult,lessValue,greaterValue;
		int firstCounter=0;
		int secondCounter=1;
		int flag=1;
		while(flag==1){
			firstNode = fingerTable.get(firstCounter);
			firstKey = firstNode.getId();
			secondNode = fingerTable.get(secondCounter);
			secondKey = secondNode.getId();
			
			//Check if second key is smaller than the first key to adjust order of comparison
			result=keyCompareTo(firstKey,secondKey);
			if(result>0){
				
				value=keyCompareTo(key,secondKey);
				lessValue=keyCompareTo(key,secondKey);
				greaterValue=keyCompareTo(key,firstKey);
				if(lessValue<=0||greaterValue>=0){
					targetNode = firstNode;
					break;
				}
				else{
					firstCounter++;
					secondCounter++;
					if(secondCounter>=fingerTable.size()){
						flag=0;
						targetNode = fingerTable.get(fingerTable.size()-1);
						//return targetNode;
						break;
					}
				}
			}
			else if(result<0){
				value=keyCompareTo(key,firstKey);
				if(value>0){
					tempResult = keyCompareTo(key,secondKey);
					if(tempResult<=0){
						targetNode = firstNode;
						//return firstNode;
						break;
					}
					else{
						firstCounter++;
						secondCounter++;
						if(secondCounter>=fingerTable.size()){
							flag=0;
							targetNode = fingerTable.get(fingerTable.size()-1);
							
							break;
						}
					}
				}
				else{
					firstCounter++;
					secondCounter++;
					if(secondCounter>=fingerTable.size()){
						flag=0;
						targetNode = fingerTable.get(fingerTable.size()-1);
						//return targetNode;
						break;
					}
				}
			}
			else if(result==0){
				firstCounter++;
				secondCounter++;
				if(secondCounter>=fingerTable.size()){
					flag=0;
					targetNode = fingerTable.get(fingerTable.size()-1);
					//return targetNode;
					break;
				}
			}
			
		}
		return targetNode;
	}

	private NodeID nextRpcCall(NodeID targetNode, String key) throws SystemException,TException{
		
		NodeID predNode=null;
		
		if(portNumber == targetNode.getPort()){
			try {
				SystemException  exception = new SystemException();
				exception.setMessage("Calling same port again, Last entry is same as current node.Infinit loop");			
				throw exception;
			} catch (SystemException e) {
				
				e.printStackTrace();
			}
		}
		String host = targetNode.getIp();
		int port = targetNode.getPort();
		
		try {
			transport  = new TSocket(host,port);
			transport.open();
			protocol = new TBinaryProtocol(transport);
			FileStore.Client client = new FileStore.Client(protocol);
			try {
				
				predNode=client.findPred(key);
				
				transport.close();
			} catch (SystemException e) {
				e.printStackTrace();
			} catch (TException e) {
				e.printStackTrace();
			}
			
		} catch (TTransportException e) {
			e.printStackTrace();
			System.exit(0);
		}
		return predNode;
	}
		
	@Override
	public NodeID getNodeSucc() throws SystemException, TException {
		
		NodeID succNode = null;
		if(fingerTable.size()>0){
			succNode = fingerTable.get(0);
		}
	
		return succNode;
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
	
	
	//Methods for Project 3
	@Override
	public void setNodePred(NodeID nodeId) throws SystemException, org.apache.thrift.TException{
		predecessor = nodeId;
	}
	
	@Override
    public void updateFinger(int idx, NodeID nodeId) throws SystemException, org.apache.thrift.TException{
		fingerTable.set(idx, nodeId);
    }

	@Override
    public List<NodeID> getFingertable() throws SystemException, org.apache.thrift.TException{
    	return fingerTable;
    }

	@Override	
    public List<RFile> pullUnownedFiles() throws SystemException, org.apache.thrift.TException{
    	
		Set<String> keys  = fileStorage.keySet();
		
		Iterator<String> keyItr=null;
		
		List<RFile> returnList = new ArrayList<RFile>();
		List<String> removeItems = new ArrayList<String>();
		String fileName=null;
		String owner=null;
		String key=null;
		RFile rFile = null;
		RFileMetadata metadata = null;
		boolean unowned = false;
		
		keyItr = keys.iterator();
		while(keyItr.hasNext()){
		  fileName = (String)keyItr.next();
			rFile = fileStorage.get(fileName);
			
			metadata = rFile.getMeta();
			owner = metadata.getOwner();
			
			key = sha256(owner+":"+fileName);
			
			unowned = checkIfUnowned(key);
			
			if(unowned){
				returnList.add(rFile);
				removeItems.add(fileName);
			}
		}
		for(int i=0;i<removeItems.size();i++){
			fileStorage.remove(removeItems.get(i));
		}
		keys = fileStorage.keySet();
		return returnList;
    }

	private boolean checkIfUnowned(String checkKey) {
		
		int interval;
		String thisKey = thisNode.getId();
		String predKey = predecessor.getId();
		
		
		int greaterThan=0,lessThan=0;
		interval = keyCompareTo(thisKey,predKey);
		if(interval>0){
			greaterThan = keyCompareTo(checkKey,predKey);
			lessThan = keyCompareTo(checkKey,thisKey);
			if((greaterThan>0)&&(lessThan<=0)){
				return true;
			}
			
		}else if(interval<0){
			
			greaterThan = keyCompareTo(checkKey,thisKey);
			lessThan = keyCompareTo(checkKey,predKey);
			if((greaterThan>0)&&(lessThan<=0)){
				return true;
			}
		}
		
		return false;
	}
	@Override
    public void pushUnownedFiles(List<RFile> files) throws SystemException, org.apache.thrift.TException{
		
		String fileName=null;
		RFile rFile = null;
		RFileMetadata metadata = null;
		Iterator<RFile> keyItr = files.iterator();
		while(keyItr.hasNext()){
			rFile= keyItr.next();
			if(rFile!=null){
				metadata = rFile.getMeta();
				fileName = metadata.getFilename();
				fileStorage.put(fileName, rFile);
			}
		}
		
    }

	@Override
    public void join(NodeID nodeId) throws SystemException, org.apache.thrift.TException{
		int i;
		String ip;
		int port;
		TTransport transport=null;
		TProtocol protocol=null;
		FileStore.Client client = null;
		
		String thisKey = thisNode.getId(); //node_new key
		
		BigInteger keyVal = null;
		//BigInteger newKey = new BigInteger(thisKey,16);
		BigInteger tempKey = null;
		BigInteger addition =null;
		BigInteger two = BigInteger.valueOf(2);
		BigInteger highestVal = two.pow(256);
		BigInteger range = highestVal.subtract(BigInteger.ONE);
		String nextKey=null;
		NodeID successorNode;
		NodeID predNode = null;
		
		
		
		
		ip = nodeId.getIp();
		port = nodeId.getPort();
		keyVal = new BigInteger(thisKey,16);
		try {
			
			transport  = new TSocket(ip,port);
			transport.open();
			protocol = new TBinaryProtocol(transport);
			client = new FileStore.Client(protocol);
			for(i=0;i<256;i++){
				nextKey=null;
				tempKey = two.pow(i);
				addition = keyVal.add(tempKey);
				if(addition.compareTo(range)>0){
					addition = addition.mod(highestVal);
				}
				addition = addition.mod(highestVal);
				nextKey = addition.toString(16);
				
				try {
					successorNode=client.findSucc(nextKey);
					fingerTable.set(i,successorNode);
				} catch (SystemException e) {
					throw e;
				} catch (TException e) {
					e.printStackTrace();
					System.exit(0);
				}
						
			}
			
			//Code to set predecesssor. Ask arbitrary node to get the predecessor for this node 
			//Set predecessor using setNodePred() function
			predNode = client.findPred(thisNode.getId());
			transport.close();
			setNodePred(predNode);
			//Code to set predecesssor. Ask arbitrary node to get the predecessor for this node 
			//Set predecessor using setNodePred() function
					
			//Code to update successors predecessor to avoid infinite loop in case of only 2 nodes
			successorNode = fingerTable.get(0);
			transport  = new TSocket(successorNode.getIp(),successorNode.getPort());
			transport.open();
			protocol = new TBinaryProtocol(transport);
			FileStore.Client client1 = new FileStore.Client(protocol);
			client1.setNodePred(thisNode);
			transport.close();
			//Code to update successors predecessor to avoid infinite loop in case of only 2 nodes
				
			} catch (TTransportException e) {
				e.printStackTrace();
				System.exit(0);
			}
			
			//Code to update finger table
			updateFingerTable("join");
			//Code to update finger table
			
			//Pull UnownedFiles from successor
			List<RFile> pulledFiles = null;
			successorNode = fingerTable.get(0);
			transport  = new TSocket(successorNode.getIp(),successorNode.getPort());
			transport.open();
			protocol = new TBinaryProtocol(transport);
			FileStore.Client client1 = new FileStore.Client(protocol);
			pulledFiles = client1.pullUnownedFiles();
			RFile rFile = null;
			String fileName=null;
			RFileMetadata metadata = null;
			for(i=0;i<pulledFiles.size();i++){
				rFile = pulledFiles.get(i);
				if(rFile!=null){
					metadata = rFile.getMeta();
					fileName = metadata.getFilename();
					fileStorage.put(fileName, rFile);
				
				}
			}
			transport.close();
			//Pull UnownedFiles from successor	
		
    }

	@Override
    public void remove() throws SystemException, org.apache.thrift.TException{
    	
    	
		
		String ip = null;
    	int port;
		TTransport transport = null;
		TProtocol protocol = null;
		FileStore.Client client = null;
		NodeID successorNode = null;
		
		//Push Files to successor
		Set<String> keySet = fileStorage.keySet();
		Iterator<String> keyItr = keySet.iterator();
		List<RFile> pushFiles=new ArrayList<RFile>();
		String fileName = null;
		RFile rFile = null;
		while(keyItr.hasNext()){
			fileName = keyItr.next();
			rFile = fileStorage.get(fileName);
			if(rFile!=null){
				pushFiles.add(rFile); 
			}
		}
		successorNode = fingerTable.get(0);
    	ip = successorNode.getIp();
    	port = successorNode.getPort();
    	transport  = new TSocket(ip,port);
		transport.open();
		protocol = new TBinaryProtocol(transport);
		client = new FileStore.Client(protocol);
    	client.pushUnownedFiles(pushFiles);
    	transport.close();
		//Push Files to successor
		
		updateFingerTable("remove");
    	
    	// Change predecessor of successor of this node to predecessor of this node
    	successorNode = fingerTable.get(0);
    	ip = successorNode.getIp();
    	port = successorNode.getPort();
    	transport  = new TSocket(ip,port);
		transport.open();
		protocol = new TBinaryProtocol(transport);
		client = new FileStore.Client(protocol);
    	client.setNodePred(predecessor);
    	transport.close();
    	// Change predecessor of successor of this node to predecessor of this node
    	
    	//No need to change successor of predecessor... Since it will be updated in update finger table code
    }
	
	public void updateFingerTable(String operation){
		//New Code
		int i;
		String ip;
		int port;
		NodeID useNode=null;
		if(operation.equals("join")){
			useNode = thisNode;
		}
		else if(operation.equals("remove")){
			useNode = fingerTable.get(0);
		}
		String thisKey = thisNode.getId(); //node_new key i.e. key of current node
		
		BigInteger newKey = new BigInteger(thisKey,16);
		BigInteger two = BigInteger.valueOf(2);
		BigInteger lastVal = two.pow(256); //For modular addition
		BigInteger tempKey = null;
		BigInteger substraction = null;
		
		NodeID checkSuccessor=null; //Used if the (node_new-2^i is the actual node)
		NodeID predNode = null;   //Calculated predecessor node
		FileStore.Client client = null;
		TProtocol protocol = null;
		TTransport transport=null;
			for(i=0;i<256;i++){
				tempKey = two.pow(i);
				substraction = newKey.subtract(tempKey);
				if(substraction.compareTo(BigInteger.ZERO)<0){
					substraction = substraction.add(lastVal);
				}
				String searchKey = substraction.toString(16);
				
				try {
					predNode = findPred(searchKey);
				} catch (SystemException e1) {
					e1.printStackTrace();
				} catch (TException e1) {
					e1.printStackTrace();
				}
				
				
				ip = predNode.getIp();
				port = predNode.getPort(); 
				try{
					if(predNode.getPort()==thisNode.getPort()){
						checkSuccessor = getNodeSucc();
					}
					else{
						transport  = new TSocket(ip,port);
						transport.open();
						protocol = new TBinaryProtocol(transport);
						client = new FileStore.Client(protocol);
						checkSuccessor = client.getNodeSucc();
						transport.close();
					}
					
					
					if(keyCompareTo(checkSuccessor.getId(),searchKey)!=0){
						updateFingerComparison(predNode,i,useNode,operation);
					}
					else if(keyCompareTo(checkSuccessor.getId(),searchKey)==0){
						updateFingerComparison(checkSuccessor,i,useNode,operation);
					}
						
				} catch (TTransportException e) {
						e.printStackTrace();
						System.exit(0);
				} catch (SystemException e) {
						e.printStackTrace();
				} catch (TException e) {	
						e.printStackTrace();
				}
												
		}
				//New Code

	}
	
	public void updateFingerComparison(NodeID checkNode, int index, NodeID useNode,String operation){
		String thisKey = thisNode.getId(); //node_new key i.e. key of current node
		String predKey = predecessor.getId(); // predecessor key of current node
		BigInteger two = BigInteger.valueOf(2);
		BigInteger tempKey = two.pow(index);
		BigInteger keyVal = null;
		String checkKey=null;
		int greaterThan=0,lessThan=0;
		NodeID predecessorNode = null;
		int interval=0;
		int flag = 0;
		FileStore.Client client1 = null;
		TProtocol protocol1 = null;
		TTransport transport1=null;
		BigInteger highestVal = two.pow(256);
		BigInteger range = highestVal.subtract(BigInteger.ONE);
		if(checkNode.getPort()==thisNode.getPort()){	
			keyVal = new BigInteger(checkNode.getId(),16);
			keyVal = keyVal.add(tempKey);
			if(keyVal.compareTo(range)>0){
				keyVal = keyVal.mod(highestVal);		
			}
			checkKey = keyVal.toString(16); // p+2^i	
			flag=0;
			interval = keyCompareTo(thisKey,predKey);
			if(interval>0){
				greaterThan = keyCompareTo(checkKey,predKey);
				lessThan = keyCompareTo(thisKey,checkKey);
				if((greaterThan>0)&&(lessThan>=0)){
					flag=1;
				}
			}else if(interval<0){
				greaterThan = keyCompareTo(checkKey,predKey);
				lessThan = keyCompareTo(checkKey,thisKey);
				if((greaterThan>=0)||lessThan<=0){
					flag=1;
				}
			}
				
			if(flag==1){
				try {
					updateFinger(index,useNode);
					//predecessorNode=findPred(checkNode.getId());
					//updateFingerComparison(predecessorNode,index,useNode,operation);

				} catch (SystemException e) {
					
					e.printStackTrace();
				} catch (TException e) {
						e.printStackTrace();
				}
			}	
		}
		else{
			transport1 = new TSocket(checkNode.getIp(),checkNode.getPort());
			try {
				
				
				transport1.open();
				protocol1 = new TBinaryProtocol(transport1);
				client1 = new FileStore.Client(protocol1);
				
				keyVal = new BigInteger(checkNode.getId(),16);
				keyVal = keyVal.add(tempKey);
				if(keyVal.compareTo(range)>0){
					keyVal = keyVal.mod(highestVal);		
				}
				checkKey = keyVal.toString(16);
				flag=0;
				interval = keyCompareTo(thisKey,predKey);
				if(interval>0){
					greaterThan = keyCompareTo(checkKey,predKey);
					lessThan = keyCompareTo(thisKey,checkKey);
					if((greaterThan>0)&&(lessThan>=0)){
						flag=1;
					}
						
				}else if(interval<0){
					greaterThan = keyCompareTo(checkKey,predKey);
					lessThan = keyCompareTo(checkKey,thisKey);
					if((greaterThan>=0)||lessThan<=0){
						flag=1;
					}
				}
				if(flag==1){
						
						client1.updateFinger(index, useNode);
						transport1.close();
							
						predecessorNode=findPred(checkNode.getId());
						updateFingerComparison(predecessorNode,index,useNode,operation);
						
				}
					
					
				transport1.close();
			} catch (SystemException e) {
				e.printStackTrace();
			} catch (TException e) {
				e.printStackTrace();
			}
		}
	}
	
	int keyCompareTo(String key1,String key2){
		int result = 0;
		BigInteger num1 = new BigInteger(key1,16);
		BigInteger num2 = new BigInteger(key2,16);
		result = num1.compareTo(num2);
		return result;
	}
}