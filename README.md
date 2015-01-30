# mindjet-parser
Java based Mindjet Parser Library and Customized Implementation Sample

1. Topic/Parser class is designed: 
   to extract information from mindjet with its text and path.
   The result will be a Topic Tree

2. AttributedTopic class is designed:
   to extract attributed information/grand children text from a parsed Topic Tree
   Thus an AttributedTopic Tree will be built based on a Topic Tree
   This class contains general template functions for this kind of works

3. CubeTopic class is an implmenetation of Attributed Topic
   with the class, such structured mindjet tree could be easily parsed and flattened to structured Text

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
