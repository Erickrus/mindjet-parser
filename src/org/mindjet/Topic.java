package org.mindjet;

import java.util.ArrayList;

public class Topic {
	public String text = "";
	public String path = "";
	public Topic parent = null;
	public ArrayList<Topic> children = new ArrayList<Topic>();

	public String getParentText() {
		if (parent != null) {
			return parent.text;
		} else {
			return "";
		}
	}

	public boolean isLeaf() {
		return children.size() == 0;
	}

	public ArrayList<Topic> getChildren() {
		return children;
	}

	public ArrayList<Topic> getAllChildren() {
		ArrayList<Topic> result = new ArrayList<Topic>();
		result.addAll(children);
		for (int i = 0; i < children.size(); i++) {
			Topic t1 = children.get(i);
			result.addAll(t1.getAllChildren());
		}
		return result;
	}

	public ArrayList<Topic> selectNodes(String path) {
		ArrayList<Topic> result = new ArrayList<Topic>(), allChildren = getAllChildren();
		for (int i = 0; i < allChildren.size(); i++) {
			Topic t = allChildren.get(i);
			if (t.path.startsWith(path)) {
				result.add(t);
			}
		}
		return result;
	}

	public Topic selectFirstNode(String path) {
		ArrayList<Topic> result = new ArrayList<Topic>(), allChildren = getAllChildren();
		for (int i = 0; i < allChildren.size(); i++) {
			Topic t = allChildren.get(i);
			if (t.path.equals(path)) {
				return t;
			}
		}
		return null;
	}

	public boolean directChildrenTextContains(String text) {
		ArrayList<Topic> result = selectNodes(this.path + "/" + text);
		return result.size() > 0;
	}

	public String toString() {
		return path + "\t" + text + "\t" + isLeaf();
	}
}
