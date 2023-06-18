package me.jaja.jajalibrary;

import me.jaja.jajalibrary.handlers.Hooks;
import me.jaja.jajalibrary.handlers.Item;
import me.jaja.jajalibrary.handlers.Locales;
import me.jaja.jajalibrary.utils.Strings;

public class JajaLibrary {
    public static Locales localesInstance = new Locales();
    public static Strings stringsInstance = new Strings();
    public static Item itemInstance = new Item();
    public static Hooks hooksInstance = new Hooks();
}
