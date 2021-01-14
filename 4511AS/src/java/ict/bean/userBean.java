
package ict.bean;

import java.io.Serializable;


public class userBean implements Serializable{
    String name,pwd,role;

    public userBean(String name, String pwd, String role) {
        this.name = name;
        this.pwd = pwd;
        this.role = role;
    }

    public userBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
}
