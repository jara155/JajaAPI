//package handlers;
//
//import org.bukkit.Bukkit;
//import org.bukkit.Material;
//import org.bukkit.entity.Player;
//import org.bukkit.event.inventory.InventoryClickEvent;
//import org.bukkit.event.inventory.InventoryDragEvent;
//import org.bukkit.inventory.Inventory;
//import org.bukkit.inventory.ItemStack;
//import org.bukkit.inventory.meta.SkullMeta;
//import utils.Instances;
//
//import java.util.*;
//
//public class Menu {
//    private static final Map<UUID, Menu> openMenus = new HashMap<>();
//    private static final Map<String, Set<UUID>> viewers = new HashMap<>();
//
//    private final Map<Integer, MenuClick> menuClickActions = new HashMap<>();
//
//    private MenuClick generalClickAction;
//    private MenuClick generalInvClickAction;
//    private MenuDrag generalDragAction;
//
//    private MenuOpen openAction;
//    private MenuClose closeAction;
//
//    public final UUID uuid;
//    private final Inventory inventory;
//    private final String viewerID;
//
//    public Menu(int size, String name){
//        uuid = UUID.randomUUID();
//        inventory = Bukkit.createInventory(null, size, name);
//        viewerID = null;
//    }
//
//    public Menu(int size, String name, String viewerID){
//        uuid = UUID.randomUUID();
//        inventory = Bukkit.createInventory(null, size, name);
//        this.viewerID = null;
//    }
//
//    public MenuClick getGeneralClickAction() {
//        return generalClickAction;
//    }
//
//    public static Menu getMenu(Player p) { return openMenus.getOrDefault(p.getUniqueId(), null); }
//
//    public void open(Player p){
//        p.openInventory(inventory);
//        openMenus.put(p.getUniqueId(), this);
//        if(viewerID != null) addViewer(p);
//        if(openAction != null) openAction.open(p);
//    }
//
//    public void remove(){
//        openMenus.entrySet().removeIf(entry -> {
//            if(entry.getValue().getUuid().equals(uuid)){
//                Player p = Bukkit.getPlayer(entry.getKey());
//                if(p != null){
//                    if(viewerID != null) removeViewer(p);
//                    if(closeAction != null) closeAction.close(p);
//                }
//                return true;
//            }
//            return false;
//        });
//    }
//
//    public UUID getUuid() { return uuid; };
//
//    public void close(Player p){
//        p.closeInventory();
//        openMenus.entrySet().removeIf(entry -> {
//            if(entry.getKey().equals(p.getUniqueId())){
//                if(viewerID != null) removeViewer(p);
//                if(closeAction != null) closeAction.close(p);
//                return true;
//            }
//            return false;
//        });
//    }
//
//    private void addViewer(Player p){
//        if(viewerID == null) return;
//        Set<UUID> list = viewers.getOrDefault(viewerID, new HashSet<>());
//        list.add(p.getUniqueId());
//        viewers.put(viewerID, list);
//    }
//
//    private void removeViewer(Player p){
//        if(viewerID == null) return;
//        Set<UUID> list = viewers.getOrDefault(viewerID, null);
//        if(list == null) return;
//        list.remove(p.getUniqueId());
//        if(list.isEmpty()) viewers.remove(viewerID);
//        else viewers.put(viewerID, list);
//    }
//
//    public Set<Player> getViewers(){
//        if(viewerID == null) return new HashSet<>();
//        Set<Player> viewerList = new HashSet<>();
//        for (UUID uuid : viewers.getOrDefault(viewerID, new HashSet<>())){
//            Player p = Bukkit.getPlayer(uuid);
//            if(p == null) continue;
//            viewerList.add(p);
//        }
//        return viewerList;
//    }
//
//    public MenuClick getAction(int index){ return menuClickActions.getOrDefault(index, null); }
//
//    protected void setGeneralClickAction(MenuClick generalClickAction) {
//        this.generalClickAction = generalClickAction;
//    }
//
//    public MenuClick getGeneralInvClickAction() {
//        return generalInvClickAction;
//    }
//
//    public void setGeneralInvClickAction(MenuClick generalInvClickAction) {
//        this.generalInvClickAction = generalInvClickAction;
//    }
//
//    public MenuDrag getGeneralDragAction() {
//        return generalDragAction;
//    }
//
//    public void setGeneralDragAction(MenuDrag generalDragAction) {
//        this.generalDragAction = generalDragAction;
//    }
//
//    protected void setOpenAction(MenuOpen openAction) {
//        this.openAction = openAction;
//    }
//
//    protected void setCloseAction(MenuClose closeAction) {
//        this.closeAction = closeAction;
//    }
//
//    public interface MenuOpen{
//        void open(Player p);
//    }
//    public interface MenuClick{
//        void click(Player p, InventoryClickEvent e);
//    }
//    public interface MenuDrag{
//        void drag(Player p, InventoryDragEvent e);
//    }
//
//    public interface MenuClose{
//        void close(Player p);
//    }
//
//    public void setItem(int index, ItemStack item){
//        inventory.setItem(index, item);
//    }
//    public void setItem(int index, ItemStack item, MenuClick action){
//        inventory.setItem(index, item);
//        if(action == null) menuClickActions.remove(index);
//        else menuClickActions.put(index, action);
//    }
//
//    public Inventory getInventory() {
//        return inventory;
//    }
//
////    public void corner() {
////        setItem(0, Instances.itemInstance.create(Material.valueOf(JajaDungeons.getInstance().getConfig().getString("MenuCornerGlassItem"))));
////        setItem(8, Instances.itemInstance.create(Material.valueOf(JajaDungeons.getInstance().getConfig().getString("MenuCornerGlassItem"))));
////        setItem(45, Instances.itemInstance.create(Material.valueOf(JajaDungeons.getInstance().getConfig().getString("MenuCornerGlassItem"))));
////        setItem(53, Instances.itemInstance.create(Material.valueOf(JajaDungeons.getInstance().getConfig().getString("MenuCornerGlassItem"))));
////    }
//
////    public void square() {
////        int rows = (inventory.getSize() + 1) / 9;
////        for (int i = 0; i < rows * 9; i++) {
////            if ((i <= 8) || (i >= (rows * 9) - 9) // bottom and top
////                    || i == 9 || i == 18 // borders
////                    || i == 27 || i == 36
////                    || i == 17 || i == 26
////                    || i == 35 || i == 44)
//////                if(inventory.getItem(i) != null) return;
////                setItem(i, Instances.itemInstance.create(Material.valueOf(JajaDungeons.getInstance().getConfig().getString("MenuGlassItem"))));
////        }
////
////    }
//
//    public static ItemStack getPlayerHead(Player p) {
//        ItemStack playerHead = Instances.itemInstance.create(Material.PLAYER_HEAD);
//        SkullMeta sm = (SkullMeta) playerHead.getItemMeta();
//        sm.setDisplayName(Instances.stringsInstance.format("PlayerHeadName", "%NAME%", p.getName(), p));
//        sm.setOwner(p.getPlayer().getName());
//        playerHead.setItemMeta(sm);
//        playerHead = Instances.itemInstance.updateLore(playerHead, Instances.stringsInstance.formatList("PlayerHeadLore", p));
//        return playerHead;
//    }
//
//    public static ItemStack getPlayerHeadWOLore(Player p) {
//        ItemStack playerHead = Instances.itemInstance.create(Material.PLAYER_HEAD);
//        SkullMeta sm = (SkullMeta) playerHead.getItemMeta();
////        sm.setDisplayName(
////                Instances.stringsInstance.colorText(
////                        instance.getLocalesInstance().getLocale().getString("PlayerHeadColor") + p.getName()
////                )
////        );
//        sm.setDisplayName(Instances.stringsInstance.format("PartyMemberName", "%NAME%", p.getName(), p));
//        sm.setOwner(p.getPlayer().getName());
//        playerHead.setItemMeta(sm);
//        return playerHead;
//    }
//
//    public void fill(){
//        for (int i = 0; i < inventory.getSize(); i++) {
//            if(inventory.getItem(i) != null) continue;
//            setItem(i, Instances.itemInstance.create(Material.BLACK_STAINED_GLASS_PANE, ""));
//        }
//    }
//
//
//}
