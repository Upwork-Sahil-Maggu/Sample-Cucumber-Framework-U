package com.sahil.mtours.Utilities;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class GetXmlData

	{

		public static final String xml_File_Path = "/Test Data/Test Data.xml";

		public static String getTagValue(String xpath, String tag)
			{

				String value = null;
				try
					{

						DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
						DocumentBuilder builder = factory.newDocumentBuilder();

						File currentDir = new File("");
						System.out.println(currentDir.getAbsolutePath());
						File xml_File = new File(currentDir.getAbsolutePath() + xml_File_Path);
						FileInputStream xml_Input_Stream = new FileInputStream(xml_File);
						Document document = builder.parse(xml_Input_Stream);

						XPathFactory xpf = XPathFactory.newInstance();
						XPath xp = xpf.newXPath();

						ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
						Source xmlSource = new DOMSource(document);
						Result outputTarget = new StreamResult(outputStream);
						TransformerFactory.newInstance().newTransformer().transform(xmlSource, outputTarget);
						InputStream is = new ByteArrayInputStream(outputStream.toByteArray());
						InputSource source = new InputSource(is);

						NodeList leafNodeObjects = (NodeList) xp.evaluate(xpath + "/" + tag, source, XPathConstants.NODESET);

						for (int x = 0; x < leafNodeObjects.getLength(); x++)

							{

								System.out.print("nodeElement is :: ");
								System.out.print(leafNodeObjects.item(x).getNodeName());

								System.out.print(" => ");
								System.out.println(leafNodeObjects.item(x).getTextContent());

								value = leafNodeObjects.item(x).getTextContent();

							}

					}

				catch (Exception e)
					{
						e.printStackTrace();
						System.exit(1);
					}

				return value;
			}

	}
