package com.example.myfirstjob.persistence;

import android.content.Context;

import com.example.myfirstjob.data.User;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

public class UserFile extends FileOperations {

    //Methods
    public UserFile() {
        setDirectory("userFile");
    }

    /**
     * Guarda un Map<String, User> en un fichero
     *
     * @param context
     * @param users
     * @return
     */
    boolean write(Context context, Map<String, User> users) {
        try {
            FileOutputStream outputStream = context.openFileOutput(getDirectory(), Context.MODE_PRIVATE);
            ObjectOutputStream output = new ObjectOutputStream(outputStream);
            output.writeObject(users);
            output.close();
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    /**
     * Devuelve un Map<String, User> leido del fichero.
     *
     * @param context
     * @return
     */
    public Map<String, User> read(Context context) {
        try {
            FileInputStream inputStream = context.openFileInput(getDirectory());
            ObjectInputStream input = new ObjectInputStream(inputStream);
            Map<String, User> c = (Map<String, User>) input.readObject();
            input.close();
            return c;
        } catch (IOException | ClassNotFoundException ex) {
            return null;
        }
    }

}