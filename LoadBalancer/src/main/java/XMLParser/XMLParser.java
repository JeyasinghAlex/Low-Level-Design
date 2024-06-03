package XMLParser;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XMLParser {


    public static void main(String[] args) {

    }

    private static final String SYS_ID = "sys_id";
    private static void processXMLContent(File contentFile) throws Exception {

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(contentFile);
        doc.getDocumentElement().normalize();

        insertTableData(doc.getElementsByTagName("sn_wn_app_config"));
        insertTableData( doc.getElementsByTagName("sn_wn_content"));
        insertTableData(doc.getElementsByTagName("sn_wn_media"));

        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();
        XPathExpression expr = xpath.compile("//sys_attachment[not(ancestor::sys_attachment_doc)]");
        NodeList attachmentList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

        insertTableData(attachmentList);
        insertTableData(doc.getElementsByTagName("sys_attachment_doc"));
        insertTableData(doc.getElementsByTagName("sn_wn_app_config_content_m2m"));
        insertTableData(doc.getElementsByTagName("sn_wn_content_media_m2m"));
    }

    private static void insertTableData(NodeList nodeList) {
        for (int i = 0; i < nodeList.getLength(); ++i) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                String tableName = element.getTagName();

                if (tableName.equals("sys_attachment") && !isTableNameValid(element))
                    continue;

                Map<String, String> data = extractDataFromElement(element);
                String action = element.getAttribute("action");
                if ("INSERT_OR_UPDATE".equals(action))
                    performInsertOrUpdate(tableName, data);
                else if ("DELETE".equals(action))
                    deleteRecord(tableName, data.get(SYS_ID));
            }
        }
    }

    private static Map<String, String> extractDataFromElement(Element element) {
        Map<String, String> data = new HashMap<>();
        NodeList childNodes = element.getChildNodes();
        for (int j = 0; j < childNodes.getLength(); ++j) {
            Node childNode = childNodes.item(j);
            if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                Element childElement = (Element) childNode;
                data.put(childElement.getTagName(), childElement.getTextContent());
            }
        }
        return data;
    }

    private static void performInsertOrUpdate(String tableName, Map<String, String> data) {
       /* GlideRecord gr = new GlideRecord(tableName);
        gr.addQuery(SYS_ID, data.get(SYS_ID));
        gr.query();

        if (gr.next()) {
            for (Map.Entry<String, String> entry : data.entrySet())
                gr.setValue(entry.getKey(), entry.getValue());
            gr.update();
            return ;
        }*/

       /* gr = new GlideRecord(tableName);
        gr.setNewGuid(data.get(SYS_ID));
        for (Map.Entry<String, String> entry : data.entrySet())
            gr.setValue(entry.getKey(), entry.getValue());
        gr.insert();*/
    }

    private static void deleteRecord(String tableName, String sysId) {
        /*GlideRecord gr = new GlideRecord(tableName);
        gr.addQuery(SYS_ID, sysId);
        gr.query();
        while (gr.next())
            gr.delete();*/
    }

    private static Map<String, String> extractAttributes(Element element) {
        Map<String, String> data = new HashMap<>();
        if (element.hasAttributes()) {
            NamedNodeMap attributes = element.getAttributes();
            for (int i = 0; i < attributes.getLength(); i++) {
                Node attribute = attributes.item(i);
                data.put(attribute.getNodeName(), attribute.getNodeValue());
            }
        }
        return data;
    }

    private static boolean isTableNameValid(Element element) {
        NodeList tableNames = element.getElementsByTagName("table_name");
        if (tableNames.getLength() > 0) {
            Element tableNameElement = (Element) tableNames.item(0);
            String tableNameValue = tableNameElement.getTextContent();
            if (!tableNameValue.contains("sn_wn_media")) {
                return false;
            }
        }
        return true;
    }
}
