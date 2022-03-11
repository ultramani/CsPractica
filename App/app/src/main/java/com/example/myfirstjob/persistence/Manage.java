package com.example.myfirstjob.persistence;

import android.content.Context;

import com.example.myfirstjob.data.Offer;
import com.example.myfirstjob.data.User;
import com.example.myfirstjob.ui.UserView;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Manage {

    //Properties
    private UserFile userFile;
    private OfferFile offerFile;

    //Methods

    /**
     * Constructor clase Manage.
     */
    public Manage() {
        userFile = new UserFile();
        offerFile = new OfferFile();
    }

    /**
     * Devuelve las ofertas en una lista
     */
    public List<Offer> loadOffers(Context context) {
        return (List<Offer>) offerFile.read(context);
    }

    /**
     * Añade un usuario al sistema
     */
    public boolean addUser(Context context, User user) {
        Map<String, User> users = userFile.read(context);
        if (users == null)
            users = new HashMap<>();
        users.put(user.getEmail(), user);
        return userFile.write(context, users);
    }

    /**
     * Añadir una oferta al sistema
     */
    public boolean addOffer(Context context, Offer offer, UserView u) {
        List<Offer> offers = (List<Offer>) offerFile.read(context);
        if (offers == null)
            offers = new LinkedList<>();
        offers.add(offer);
        HashMap<String, User> users = (HashMap<String, User>) userFile.read(context);
        User user = users.get(u.getEmail());
        List<Integer> l = user.getListOffersId();
        if (l == null)
            l = new LinkedList<>();
        l.add(offer.getOfferId());
        userFile.write(context, users);
        return offerFile.write(context, offers);
    }

    /**
     * deletes an offer from system
     */
    public boolean delete(Context context, Offer o) {
        //Falta añadir eliminación de usuario
        List<Offer> offers = (List<Offer>) offerFile.read(context);
        offers.remove(o);
        //offers.remove(position);
        return offerFile.write(context, offers);
    }

    /**
     * receives a user with email and password and returns original user
     */
    public User searchUser(User user, Context context) {
        Map<String, User> users = userFile.read(context);
        if (users == null)
            users = new HashMap<>();
        User u = users.get(user.getEmail());
        return u;
    }

    public boolean updateUser(Context context, User u, String oldEmail) {
        Map<String, User> users = userFile.read(context);
        User user = users.remove(oldEmail);
        user.setName(u.getName());
        user.setEmail(u.getEmail());
        user.setListOffersId(u.getListOffersId());
        user.setCompanyName(u.getCompanyName());
        users.put(user.getEmail(), user);
        return userFile.write(context, users);
    }


    public boolean updateOffers(Context context, Offer offer) {
        List<Offer> lOffers = loadOffers(context);
        lOffers.remove(offer);
        //Es necesario equals de offer comparando el OfferID
        lOffers.add(offer);
        return offerFile.write(context, lOffers);
    }

    public String getInstructions(Context context) {
        HelpFile help = new HelpFile();
        return help.read(context);
    }

    public User searchUserByID(String user, Context c) {
        Map<String, User> users = userFile.read(c);
        return users.get(user);
    }

    public Offer getOfferByID(int ID, Context c) {
        LinkedList<Offer> lOffers = (LinkedList<Offer>) loadOffers(c);
        Offer offer = null;
        for (Offer a : lOffers) {
            if (a.getOfferId() == ID) {
                return offer = a;
            }
        }
        return null;
    }

}