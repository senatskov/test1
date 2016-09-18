package senatskov.ilya.frontend.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="response")
public class Response {
    @XmlElement(name="response-code", nillable=false)
    public int responseCode;
    
    @XmlElement(name="extra")
    public Extra extra;
}
