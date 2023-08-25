package me.pessiuff.pluginhide.listeners.packet;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import org.bukkit.plugin.Plugin;

public class InComingTabComplete extends PacketAdapter {
    public InComingTabComplete(Plugin plugin, PacketType... types) {
        super(plugin, types);
    }

    @Override
    public void onPacketReceiving(PacketEvent event) {
        PacketContainer packet = event.getPacket();
        if (event.getPacketType().equals(PacketType.Play.Client.TAB_COMPLETE)) {
            String[] args = packet.getStrings().read(0).split(" ");
            if (args[0].equals("bukkit:ver")) {
                plugin.getLogger().warning(event.getPlayer().getName() + " tried BukkitVer using a client(?). Dropping the packet...");
                event.setCancelled(true);
            }
        }
    }
}
