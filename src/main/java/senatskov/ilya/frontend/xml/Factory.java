package senatskov.ilya.frontend.xml;

import java.io.StringReader;
import java.lang.instrument.IllegalClassFormatException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

public class Factory {
    public static Request newRequestInstance(String xml) throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(Request.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        Request request = (Request) jaxbUnmarshaller.unmarshal(new StringReader(xml));
        for (Extra e: request.extras) {
            switch (e.name) {
                case "login": request.login = e.value; break;
                case "password": request.password = e.value; break;
                default: throw new IllegalClassFormatException();
            }
        }
        if (request.login == null || request.password == null)
            throw new IllegalClassFormatException();
        return request;
    }
    
    public static Response newResponseInstance(ResponseCodes responseCode) {
        Response xmlresponse = new Response();
        xmlresponse.responseCode = responseCode.getCode();
        return xmlresponse;
    }
    
    public static Response newResponseInstance(ResponseCodes responseCode, String extraName, String extraValue) {
        Response response = newResponseInstance(responseCode);
        Extra extra = new Extra();
        extra.name = extraName;
        extra.value = extraValue;
        response.extra = extra;
        return response;
    }
}
