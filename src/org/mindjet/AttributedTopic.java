package org.mindjet;

//parse topic in following structure
//
//Topic text=...
//   Topic text=...
//      Topic text=attribute1
//      Topic text=attribute2
//
//Finally the attributes will be extracted and assembled as
//
//AttributedTopic text=...
//   AttributedTopic text=... attribute1=... attribute2=...
//
public class AttributedTopic extends Topic {
	
	//Template Approach to parse and reorganize the topic
	public Topic parse(Topic t) {
		this.setAttributes(t);
		this.path = t.path;
		this.text = t.text;
		if (!isDefinedLeaf(t)) {
			for (int i=0;i<t.children.size();i++) {
				AttributedTopic t1 = getInstance();
				children.add(t1.parse(t.children.get(i)));
				t1.parent = this;
			}
		}
		return this;
	}
	
	//should be overridden
	public AttributedTopic getInstance() {
		return new AttributedTopic();
	}

	//should be overridden
	public boolean isDefinedLeaf(Topic t) {
		return t.isLeaf();
	}

	//should be overridden
	public void setAttributes(Topic t) {}
	
	//should be overridden
	public String toString() {return super.toString();}
}
