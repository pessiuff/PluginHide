package me.pessiuff.pluginhide.listeners.packet;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.mojang.brigadier.suggestion.Suggestion;
import com.mojang.brigadier.suggestion.Suggestions;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class OutGoingTabComplete extends PacketAdapter {
    public OutGoingTabComplete(Plugin plugin, PacketType... types) {
        super(plugin, types);
    }

    @Override
    public void onPacketSending(PacketEvent event) {
        if (event.getPacketType().equals(PacketType.Play.Server.TAB_COMPLETE)) {
            PacketContainer packet = event.getPacket();
            Suggestions suggestions = packet.getSpecificModifier(Suggestions.class).read(0);
            List<Suggestion> suggestionList = packet.getSpecificModifier(Suggestions.class).read(0).getList();
            List<Suggestion> toRemove  = new ArrayList<>();
            for (Suggestion suggestion : suggestionList) {
                String text = suggestion.getText();
                if (text.contains(":")) {
                    toRemove.add(suggestion);
                }
            }
            suggestionList.removeAll(toRemove);
            Suggestions modifiedSuggestions = new Suggestions(suggestions.getRange(), suggestionList);
            packet.getSpecificModifier(Suggestions.class).write(0, modifiedSuggestions);
        }
    }
}
