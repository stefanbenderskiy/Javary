package com.reflection.javary.parser;

public interface XMLConverter {
    public Object convertToObject(XMLElement e);
    public XMLElement convertToXMLElement(Object object);

}
