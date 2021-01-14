package ict.tag;

import ict.bean.userBean;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    

public class WelcomeTag extends SimpleTagSupport {

    userBean users = new userBean();

    public userBean getUsers() {
        return users;
    }

    public void setUsers(userBean users) {
        this.users = users;
    }

  
 

    @Override
    public void doTag() throws IOException {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
   LocalDateTime now = LocalDateTime.now();  
            PageContext pageContext = (PageContext) getJspContext();
            JspWriter out = pageContext.getOut();
            out.print("welcome!"+users.getName()+"<br/> THE time is"+now);
            
        }
    }

