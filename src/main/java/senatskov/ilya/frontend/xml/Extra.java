package senatskov.ilya.frontend.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlType
public class Extra {
    @XmlAttribute(name="name")
    public String name;

    @XmlValue
    public String value;
}
