package handlers;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class Item {

    public ItemStack create(Material mat) { return new ItemStack(mat); };

    public ItemStack create(Material mat, String name){
        ItemStack item = new ItemStack(mat);
        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.setDisplayName(name);
        item.setItemMeta(itemMeta);

        return item;
    }

    public ItemStack create(Material mat, String name, List<String> lore){
        ItemStack item = new ItemStack(mat);
        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.setDisplayName(name);
        itemMeta.setLore(lore);

        item.setItemMeta(itemMeta);
        return item;
    }

    public ItemStack create(Material mat, int customModelData) {
        ItemStack item = new ItemStack(mat);
        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.setCustomModelData(customModelData);

        item.setItemMeta(itemMeta);

        return item;
    }

    public ItemStack create(Material mat, int customModelData, String name, List<String> lore) {
        ItemStack item = new ItemStack(mat);
        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.setDisplayName(name);
        itemMeta.setLore(lore);
        itemMeta.setCustomModelData(customModelData);

        item.setItemMeta(itemMeta);

        return item;
    }

    public ItemStack updateLore(ItemStack item, List<String> newLore) {
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setLore(newLore);
        item.setItemMeta(itemMeta);
        return item;
    }

}
