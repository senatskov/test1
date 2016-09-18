package senatskov.ilya.frontend.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="request")
public class Request {
    @XmlElement(name="request-type", nillable=false)
    public RequestType requestType;
    
    @XmlElement(name="extra", nillable=false)
    public List<Extra> extras;
    
    public String login;
    public String password;
}
