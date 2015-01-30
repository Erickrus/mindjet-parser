package org.work;

import java.util.ArrayList;

import org.mindjet.Parser;
import org.mindjet.Topic;

public class TestCubeTopic {
	public static void main(String[] arg) {
		Parser p = new Parser("C:\\CUBE.mmap");
		
		org.work.CubeTopic c = new org.work.CubeTopic();
		c.parse(p.root);
		
		ArrayList<Topic> allChildren = c.getAllChildren();
		for (int i=0;i<allChildren.size();i++) {
			System.out.println(allChildren.get(i));
		}
	}
}
