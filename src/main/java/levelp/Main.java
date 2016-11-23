package levelp;

import java.io.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 * Created by hanaevamaria on 19/09/16.
 */
public class Main {
    public static long count = 0;
    public static ContactList contacts;

    public static void main(String[] args) throws Exception {
        File dir = new File("ContactsJson");
        count = dir.list().length;
        if (count > 0) {
            loading();
        }

        contacts = new ContactList();
        int n = 0;
        int i = 0;
        int a = 0;
        try

        {
            while (readJson(a) != null) {
                contacts.add(readJson(a));
                i = contacts.size();
                a++;
            }
        } catch (NullPointerException e)
        {
        }

//        for (int s = 0; s < 1000; s++) {
//            String str = "" + s;
//            Contact contact = new Contact(str, "0", "0");
//            contacts.add(contact);
//            i=contacts.size();
//        }

        while (n != 6)

        {
            count = contacts.size();
            System.out.println("Menu:\n" +
                    "1 - Add contact\n" +
                    "2 - Show all contacts\n" +
                    "3 - Delete contact\n" +
                    "4 - Edit Contact\n" +
                    "5 - Sort contacts alphabetically\n" +
                    "6 - Quit");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            n = Integer.parseInt(reader.readLine());

            if (n == 1) {
                System.out.println("Введите имя");
                String name = reader.readLine();
                boolean nameExist = false;
                if (contacts.exists()) {
                    for (int j = 0; j < contacts.size(); j++) {
                        if (contacts.get(j).getName().equals(name)) {
                            System.out.println("Такой контакт уже существует");
                            nameExist = true;
                        }
                    }
                }
                if (!nameExist) {
                    System.out.println("Введите номер телефона");
                    String phone = reader.readLine();
                    System.out.println("Введите e-mail");
                    String email = reader.readLine();
                    Contact contact = new Contact(name, phone, email);
                    contacts.add(contact);
                    i++;
                }
            }

            if (n == 2) {
                System.out.println("Список контактов: ");
                if (!contacts.exists()) {
                    System.out.println("Список контактов пуст");
                } else {
                    for (int j = 0; j < contacts.size(); j++) {
                        System.out.println(j + 1 + " " + contacts.get(j).toString());
                    }
                }
                System.out.println();
            }

            if (n == 3) {
                System.out.println("Введите имя для поиска");
                String contactForDelete = reader.readLine();
                boolean contactIsFound = false;
                for (int k = 0; k < i; k++) {
                    if (contacts.get(k).getName().equals(contactForDelete)) {
                        contactIsFound = true;
                        contacts.remove(k);
                        i--;
                    }
                }
                if (!contactIsFound) {
                    System.out.println("Такой контакт не найден");
                }
            }

            if (n == 4) {
                if (contacts.exists()) {
                    System.out.println("Введите имя редактируемого контакта");
                    String name = reader.readLine();
                    boolean contactIsFound = false;
                    for (int j = 0; j < contacts.size(); j++) {
                        if (contacts.get(j).getName().equals(name)) {
                            contactIsFound = true;
                            System.out.println("Если хотите отредактировать имя контакта - введите новое имя. Если нет - введите skip");
                            String input = reader.readLine();
                            if (!input.equals("skip")) {
                                contacts.get(j).setName(input);
                            }
                            System.out.println("Если хотите отредактировать телефон контакта - введите новый номер. Если нет - введите skip");
                            input = reader.readLine();
                            if (!input.equals("skip")) {
                                contacts.get(j).setPhone(input);
                            }
                            System.out.println("Если хотите отредактировать email контакта - введите новый email. Если нет - введите skip");
                            input = reader.readLine();
                            if (!input.equals("skip")) {
                                contacts.get(j).setEmail(input);
                            }

                        }
                    }
                    if (!contactIsFound) {
                        System.out.println("Такой контакт не найден");
                    }
                } else {
                    System.out.println("Список контактов пуст");
                }
            }
            if (n == 5) {
                contacts.sort();
            }
            if (n == 6) {
                SavingThread savingThread = new SavingThread();
                savingThread.start();

//                System.out.println("Выберите способ сохранения:\n" +
//                        "1 - Serialization\n" +
//                        "2 - Json");
//                int w = Integer.parseInt(reader.readLine());
//                if (w == 1) {
                //File dir = new File("ContactsJson");
//                dir.mkdir();
//                File[] files = dir.listFiles();
//                for (File f : files) {
//                    f.delete();
//                }
//                for (int q = 0; q < contacts.size(); q++) {
//                    writeJson(contacts.get(q), q);
//                }
//                }
//                if (w == 2) {
//                    for (int q = 0; q < contacts.size(); q++) {
//                        writeJson(contacts.get(q), q);
//                    }
//                    write(contacts);
////                        String json = gson.toJson(contacts);
////                    System.out.println(json);


            }
        }

    }

    private static Contact readJson(int id) {

        Contact u = null;
        try {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .excludeFieldsWithoutExposeAnnotation()
                    .create();
            File temp = new File("ContactsJson/user" + id + ".data");
            if (temp.exists()) {

                FileInputStream fis = new FileInputStream("ContactsJson/user" + id + ".data");
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader bufferedReader = new BufferedReader(isr);
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }
                String json = sb.toString();

                u = gson.fromJson(json, Contact.class);
                bufferedReader.close();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return u;
    }

    public static void writeJson(Contact object, int id) {
        try {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .excludeFieldsWithoutExposeAnnotation()
                    .create();

            String json = gson.toJson(object);

            File dir = new File("ContactsJson");
            FileOutputStream fos = new FileOutputStream("ContactsJson/user" + id + ".data");
            fos.write(json.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


//    private static Contact read(int id) {
//        ObjectInputStream ois = null;
//        Contact u = null;
//        try {
//            File temp = new File("Contacts/user" + id + ".data");
//            if (temp.exists()) {
//                FileInputStream fis = new FileInputStream("Contacts/user" + id + ".data");
//                ois = new ObjectInputStream(fis);
//                u = (Contact) ois.readObject();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } finally {
//            if (ois != null) {
//                try {
//                    ois.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return u;
//    }
//
//    private static void write(Contact object, int id) {
//        ObjectOutputStream oos = null;
//        try {
//            File dir = new File("Contacts");
//            FileOutputStream fos = new FileOutputStream("Contacts/user" + id + ".data");
//            oos = new ObjectOutputStream(fos);
//            oos.writeObject(object);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (oos != null) {
//                try {
//                    oos.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }

    private static void loading() {
        ProgressThread loadingThread = new ProgressThread();
        loadingThread.start();
        String line = "=";
        int procentInt = 0;
        while (procentInt < 100) {
            procentInt = (int) ((loadingThread.procent * 100) / Main.count);
            System.out.print("\r" + line + " " + procentInt+"%");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (procentInt > 0 && procentInt % 10 == 0) {
                line += "=";
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println();

    }

}
