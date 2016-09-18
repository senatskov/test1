package senatskov.ilya.frontend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import senatskov.ilya.frontend.xml.Factory;
import senatskov.ilya.frontend.xml.Request;
import senatskov.ilya.frontend.xml.Response;
import senatskov.ilya.frontend.xml.ResponseCodes;

@Controller
@EnableWebMvc
public class FrontController {
    @Autowired
    private SimpleDAO dao;
    
    public void setSimpleDAO(SimpleDAO dao) {
        this.dao = dao;
    }
    
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public @ResponseBody Response index(HttpServletRequest request, HttpServletResponse response) {
        String xml = null;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()))) {
            xml = reader.lines().collect(Collectors.joining(""));  
        } catch (IOException e) {
            return Factory.newResponseInstance(ResponseCodes.TECHINCAL_ERROR);
        }
        
        try {
            Request xmlrequest = Factory.newRequestInstance(xml);
            
            if (xmlrequest.login.isEmpty() || xmlrequest.password.isEmpty())
                throw new IllegalArgumentException();
            
            switch (xmlrequest.requestType) {
                case CREATE_AGT:  
                    if (dao.insertNewUser(xmlrequest.login, xmlrequest.password))
                        return Factory.newResponseInstance(ResponseCodes.OK);
                    else
                        return Factory.newResponseInstance(ResponseCodes.USER_EXISTS);
                case GET_BALANCE: 
                    try {
                        BigDecimal balance = dao.getUserBalance(xmlrequest.login, xmlrequest.password);
                        if (balance != null)
                            return Factory.newResponseInstance(ResponseCodes.OK, "balance", balance.toPlainString());
                        else
                            return Factory.newResponseInstance(ResponseCodes.TECHINCAL_ERROR);
                    } catch (UserNotExistsException e) {
                        return Factory.newResponseInstance(ResponseCodes.USER_NOT_EXISTS);
                    } catch (PasswordNotCorrectException e2) {
                        return Factory.newResponseInstance(ResponseCodes.PASSWORD_NOT_CORRECT);
                    }
                default: throw new IllegalStateException();
            }
        } catch (Exception e) {
            return Factory.newResponseInstance(ResponseCodes.TECHINCAL_ERROR);
        }
    }
}
