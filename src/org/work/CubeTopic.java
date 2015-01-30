package org.work;

import java.util.ArrayList;

import org.mindjet.*;

public class CubeTopic extends AttributedTopic {
	public String columnType = "";
	public String sampleText = "";

	/* @overridden */
	public CubeTopic getInstance() {
		return new CubeTopic();
	}

	/* @overridden */
	public boolean isDefinedLeaf(Topic t) {
		if (t.directChildrenTextContains("层次关系")
				|| t.directChildrenTextContains("枚举")
				|| t.directChildrenTextContains("基础指标")
				|| t.directChildrenTextContains("衍生指标") || t.isLeaf())
			return true;
		else
			return false;
	}

	/* @overridden */
	public void setAttributes(Topic t) {
		// set Attribute: columnType from child topic, path or isLeaf()
		if (t.directChildrenTextContains("基础指标")) {
			columnType = "基础指标";
		} else if (t.directChildrenTextContains("衍生指标")) {
			columnType = "衍生指标";
		} else if (t.directChildrenTextContains("层次关系")) {
			columnType = "层次关系";
		} else if (t.path.contains("/Measures指标") && isDefinedLeaf(t)) {
			columnType = "指标";
		} else if (t.path.contains("/维度") && isDefinedLeaf(t)) {
			columnType = "维度";
		} else {
			columnType = "";
		}

		// set Attribute: sampleText from grand children Topic
		sampleText = "";
		if (t.directChildrenTextContains("枚举")) {
			// pick children under it
			String childPath = t.path + "/枚举/";
			ArrayList<Topic> sampleChildren = t.selectNodes(childPath);
			for (int i = 0; i < sampleChildren.size(); i++) {
				Topic t1 = sampleChildren.get(i);
				if (t1.isLeaf()) {
					String sampleValue = t1.path.substring(childPath.length());
					sampleText = concat(sampleText, sampleValue, ";");
				}
			}
		}
	}

	/* @overridden */
	public String toString() {
		return path + "\t" + text + "\t" + columnType + "\t" + isLeaf() + "\t"
				+ sampleText;
	}

	private String concat(String s1, String s2, String delimiter) {
		return s1 + ((s1.equals("")) ? s2 : (delimiter + s2));
	}
}
