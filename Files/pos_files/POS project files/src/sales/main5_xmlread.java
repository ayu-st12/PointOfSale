package sales;

import org.w3c.dom.*;

import javax.xml.parsers.*;

import java.io.*;
import java.util.*;


public class main5_xmlread {
	
	public static Map<String, String> data = new HashMap<String, String>();
	
	public Map return_func(String eid){
		try{	
			//getting file path
			String filepath = main5_xmlread.class.getProtectionDomain().getCodeSource().getLocation().getPath();      
			int newl = filepath.length()-4;
			String newadd = filepath.substring(0,newl);
			while((newadd.indexOf("%20"))!=-1){
				newadd = newadd.replace("%20"," ");
			};
			newadd += "src/application/";
						
			//setting up XML File
			File inputFile = new File(newadd+"1-admin.xml");
			DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dbfactory.newDocumentBuilder();
			Document doc = builder.parse(inputFile);
			doc.getDocumentElement().normalize();
			System.out.println("Root Element: "+doc.getDocumentElement().getNodeName());
			System.out.println("----------------------------");
			NodeList rootNode = doc.getElementsByTagName("privs");
			
			Node emp = rootNode.item(0);
			Element evals = (Element) emp;
			String empid = evals.getAttribute("eid");
			String tpe = evals.getAttribute("type");
			data.put(empid,tpe);
			NodeList permission = doc.getElementsByTagName("function");
			for(int i=0;i<permission.getLength();i++){
				Node function = permission.item(i);
				Element func = (Element) function;
				Node name = func.getElementsByTagName("name").item(0);
				Element namevalue = (Element) name;
				//System.out.println("Function Name: "+namevalue.getTextContent());
				String name_t = "name_t"+i;
				name_t = namevalue.getTextContent();
				Node value = func.getElementsByTagName("value").item(0);
				Element vvalue = (Element) value;
				String value_t = "value_t"+i;
				value_t = vvalue.getTextContent();
				data.put(name_t,value_t);
				//System.out.println("Value: "+vvalue.getTextContent());
				//System.out.println(func.getTextContent());
			}
			//for (Map.Entry<String, String> entry : data.entrySet()) {
				//System.out.println("Function Name: "+entry.getKey()+" Value: "+entry.getValue());
				//}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}	
		return data;
	}
}
