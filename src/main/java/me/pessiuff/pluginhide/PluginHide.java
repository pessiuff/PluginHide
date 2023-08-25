package me.pessiuff.pluginhide;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import me.pessiuff.pluginhide.listeners.packet.InComingTabComplete;
import me.pessiuff.pluginhide.listeners.packet.OutGoingTabComplete;
import org.bukkit.plugin.java.JavaPlugin;

public final class PluginHide extends JavaPlugin {
    private static PluginHide pluginInstance = null;
    private static ProtocolManager protocolManager = null;

    @Override
    public void onEnable() {
        // Plugin startup logic
        pluginInstance = this;
        protocolManager = ProtocolLibrary.getProtocolManager();
        protocolManager.addPacketListener(new InComingTabComplete(this, new PacketType[] { PacketType.Play.Client.TAB_COMPLETE }));
        protocolManager.addPacketListener(new OutGoingTabComplete(this, new PacketType[] { PacketType.Play.Server.TAB_COMPLETE }));
    }

    public static PluginHide getPluginInstance() {
        return pluginInstance;
    }

    public static ProtocolManager getProtocolManager() {
        return protocolManager;
    }
}
