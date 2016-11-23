package levelp;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
/**
 * Created by hanaevamaria on 19/09/16.
 */
public class Contact implements Comparable<Contact>, Serializable{
    @Expose
    private String name;
    private int age;
    @Expose
    private String email;
    @Expose
    private String phone;


    public Contact() {

    }

    public Contact(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    @Override
    public String toString() {
        return name + " " + phone + " " + email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Contact) {
            Contact another = (Contact) obj;
            return name.equals(another.getName());
        }
        return false;
    }

    @Override
    public int compareTo(Contact o) {
        if (name.toLowerCase().charAt(0) < o.getName().toLowerCase().charAt(0)) {
            return -1;
        }
        if (name.toLowerCase().charAt(0) > o.getName().toLowerCase().charAt(0)) {
            return 1;
        }
        return 0;
        //return name.compareTo(o.getName());
    }

//    @Override
//    public int compareTo(Contact o) {
//        return email.compareTo(o.getEmail());
//    }
}
