package levelp;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by hanaevamaria on 21/09/16.
 */
public class ContactList implements Serializable {
    private Contact[] array = null;

    public boolean exists() {
        if (array != null) {
            return true;
        } else {
            return false;
        }
    }

    public void add(Contact value) {
        if (array == null) {
            array = new Contact[1];
            array[0] = value;
        } else {
            Contact[] temp = new Contact[array.length + 1];
            for (int i = 0; i < array.length; i++) {
                temp[i] = array[i];
            }
            array = temp;
            array[array.length - 1] = value;
        }
    }

    public void add(int i, Contact value) {
        Contact[] temp = new Contact[array.length + 1];
        for (int j = 0; j < i; j++) {
            temp[j] = array[j];
        }
        temp[i] = value;
        for (int j = i + 1; j < array.length + 1; j++) {
            temp[j] = array[j - 1];
        }
        array = temp;
    }

    public void remove(int i) {
        Contact[] temp = new Contact[array.length - 1];
        for (int j = 0; j < i; j++) {
            temp[j] = array[j];
        }
        for (int j = i; j < array.length - 1; j++) {
            temp[j] = array[j + 1];
        }
        array = temp;
        if (array.length == 0) {
            array = null;
        }
    }

    public Contact get(int i) {
        return array[i];
    }

    public int size() {
        if (exists()) {
            return array.length;
        } else return 0;
    }

    public void clear() {
        array = null;
    }

    public void sort() {

        Arrays.sort(array);
    }

}



