/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ppi;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author student
 */
public class XMLReader {
     ArrayList<ppi.Node> idList;
     public XMLReader(ArrayList<ppi.Node> list){
         idList=list;
     }
     public ArrayList<ppi.Node> readXML(){
            DomainResult dr=new DomainResult();
           
            
         try {
             for(int k=0;k<idList.size();k++){
                 if(idList.get(k).select ==true)
                 {
                     System.out.println(idList.get(k).getXmlId().toString()+".xml.xml");
               File file = new File(idList.get(k).getXmlId().toString()+".xml.xml");
               DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
               DocumentBuilder db = dbf.newDocumentBuilder();
               Document doc = db.parse(file);
               doc.getDocumentElement().normalize();
               System.out.println("Root element " + doc.getDocumentElement().getNodeName());
               NodeList interproList = doc.getElementsByTagName("interpro");
               System.out.println("Length of InterproList of " +idList.get(k).getXmlId().toString()+".xml.xml is:"+interproList.getLength());
               ArrayList<String> domainList=new ArrayList();
               ArrayList<String> type=new ArrayList();
               for(int s=0;s<interproList.getLength();s++){
                   Node interproNode=interproList.item(s);
                  if (interproNode.getNodeType() == Node.ELEMENT_NODE){
                      Element interproElmnt = (Element) interproNode;
                      if(!interproElmnt.getAttribute("type").equals("unintegrated")){
                          
                          dr.setIPRid(interproElmnt.getAttribute("id"));
                          dr.setType(interproElmnt.getAttribute("type").toString());
                          dr.setPfamID();
                          if(!dr.getPfamID().equals("")){
                              domainList.add(dr.getPfamID());
                              type.add(dr.getType());
                              dr=new DomainResult();
                          }
                      }
                  }
                
               }
               idList.get(k).setDomainList(domainList);
               idList.get(k).type=type;;
             }//end of if
             }// end of outer loop
             
        } catch (SAXException ex) {
            Logger.getLogger(XMLReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XMLReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XMLReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idList;
     }
}
