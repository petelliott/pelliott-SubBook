package ca.pelliott.pelliott_subbook;

import java.util.ArrayList;

/**
 * Created by peter on 22/01/18.
 */

public final class SubscriptionList {
    private static ArrayList<Subscription> sublist;

    static {
        // TODO: load on creation
        sublist = new ArrayList<>();
    }

    // make sure SubscriptionList cant be instantiated
    private SubscriptionList() {}

    public static Subscription getSubscr(int index) {
        return sublist.get(index);
    }

    public static void addSubscr(Subscription sub) {
        sublist.add(sub);
    }

    // WARNING: this function will invalidate all indexes you have lying around.
    // make sure to account for this
    public static void remove(int index) {
        sublist.remove(index);
    }

    public static void remove(Subscription sub) {
        sublist.remove(sub);
    }

    public static ArrayList<Subscription> getArray() {
        return sublist;
    }

    public static void load() {
        // TODO
    }

    public static void save() {
        // TODO
    }

}