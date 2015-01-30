# mindjet-parser
A Java-based Mindjet Parser Library and Customized Implementation with Sample.
Currently, this library only support to extract text from mmap.

1. Topic and Parser class are designed: 
   to extract information from mindjet with its text and path.
   The result will be a TopicTree.
   A Series of mindjet TopicTree accessing API functions are defined here, such as:
   * members:
      * text, path, parent, children
   * functions:
      * getParentText(), isLeaf(), getChildren(), getAllChildren(), selectNodes(), selectFirstNode(), directChildrenTextContains(), toString()

2. AttributedTopic class is designed:
   to extract attributed information/grand children text from a parsed TopicTree.
   Thus an AttributedTopic Tree will be built based on a TopicTree.
   This class contains general template functions for this kind of works.
   The "Template Pattern" is applied in parse() function.
   The rest of functions should be overriden in subclass.

3. CubeTopic class is an implmenetation of Attributed Topic.
   With CubeTopic, structured mindjet tree could be parsed and flattened to structured Text.
   Below is a demo of "CUBE topic"

* CUBE
  * Measures
    * Sales
      * Sales based Measure
        * Card revenue
          * Base Measure
        * Price per Ticket
          * Derived Measure
        * Product per Ticket
          * Derived Measure
  * Dimensions
    * Period
      * Quarter
        * Enumeration
          * Q1
          * Q2
          * Q3
          * Q4
      * Month
      * Year
      * Date
      * Year-Month-Day
        * Hierarchy
        * Enumeration
          * 2013
            * January
              * 1
              * 2
    * Enterprise Name
      * Enumeration
        * Google
        * Apple
