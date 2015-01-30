package org.mindjet;

import org.dom4j.Document;
import org.dom4j.Element;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;


import java.nio.charset.Charset;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class Parser {

	private final String UTF8_ENCODING = "UTF-8";
	private final String ROOT_NODE_NAME = "/ap:Map/ap:OneTopic/ap:Topic";
	
	private String body = "";
	private Document document;
	public Topic root;
	
	
	public String readZip(String filename) {
		StringBuffer fileContent = new StringBuffer();
		String result = "";
		try {
			ZipFile zf = new ZipFile(filename);
			InputStream in = new BufferedInputStream(new FileInputStream(
					filename));
			ZipInputStream zin = new ZipInputStream(in);
			ZipEntry ze;

			while ((ze = zin.getNextEntry()) != null) {
				if (ze.isDirectory()) {
				} else {
					if (ze.getName().equals("Document.xml")) {
						long size = ze.getSize();
						if (size > 0) {
							BufferedReader br = new BufferedReader(
									new InputStreamReader(
											zf.getInputStream(ze),
											Charset.forName(UTF8_ENCODING)));
							String line = null;
							while ((line = br.readLine()) != null) {
								fileContent.append(line);
								fileContent.append("\n");
							}
							br.close();
						}
					}
				}
			}
			zin.closeEntry();
			result = new String(fileContent.toString().getBytes(UTF8_ENCODING));
		} catch (Exception e) {
		}
		return result;
	}

	public Parser(String filename) {
		try {
			body = readZip(filename);
			document = org.dom4j.DocumentHelper.parseText(body);
			List l = document.selectNodes(ROOT_NODE_NAME);
			Element elm = (Element) l.get(0);
			root = parse(elm, "");

		} catch (Exception e) {
		}
	}

	public Topic parse(Element elm, String path) {
		Topic t = new Topic();
		t.text = elm.element("Text").attributeValue("PlainText");
		t.path = path + "/" + t.text/*.trim()*/;

		Element elms = elm.element("SubTopics");
		if (elms != null) {
			List l = elms.elements("Topic");
			for (int i = 0; i < l.size(); i++) {
				Topic t1 = parse((Element) l.get(i), t.path);
				t1.parent = t;
				t.children.add(t1);
			}
		}
		return t;
	}

	public String read(String fileName) throws Exception {
		StringBuffer fileContent = new StringBuffer();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(
					fileName), Charset.forName(UTF8_ENCODING)));
			String line = null;
			while ((line = br.readLine()) != null) {
				fileContent.append(line);
				fileContent.append("\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return new String(fileContent.toString().getBytes(UTF8_ENCODING));
	}
}
