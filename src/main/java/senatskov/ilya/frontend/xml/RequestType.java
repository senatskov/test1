package senatskov.ilya.frontend.xml;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum
public enum RequestType {
    @XmlEnumValue(value = "CREATE-AGT")
    CREATE_AGT,
    @XmlEnumValue(value = "GET-BALANCE")
    GET_BALANCE
}
