package com.example.myfirstjob.persistence;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;

public class FileOperations {

    //Properties
    private String directory;

    //Methods

    /**
     * Devuelve el directorio del fichero.
     *
     * @return
     */
    public String getDirectory() {
        return directory;
    }

    /**
     * Establece el directorio de un fichero.
     *
     * @param directory
     */
    public void setDirectory(String directory) {
        this.directory = directory;
    }

    /**
     * Escribe una colección en el fichero.
     *
     * @param context
     * @param c
     * @return
     */
    public boolean write(Context context, Collection c) {
        try {
            FileOutputStream outputStream = context.openFileOutput(getDirectory(), Context.MODE_PRIVATE);
            ObjectOutputStream output = new ObjectOutputStream(outputStream);
            output.writeObject(c);
            output.close();
            return true;
        } catch (IOException ex) {
            System.out.println("Error en writeObject");
            return false;
        }
    }

    /**
     * Lee una colección del fichero.
     *
     * @param context
     * @return
     */
    public Object read(Context context) {
        try {
            FileInputStream inputStream = context.openFileInput(getDirectory());
            ObjectInputStream input = new ObjectInputStream(inputStream);
            Collection c = (Collection) input.readObject();
            input.close();
            return c;
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Error en readObject");
            return null;
        }
    }

}