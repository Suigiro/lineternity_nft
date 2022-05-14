package com.dev.handler.itemhandlers;

import com.dev.data.xml.DressMeData;
import com.dev.handler.IItemHandler;
import com.dev.model.DressMe;

import net.sf.l2j.gameserver.model.actor.Player;
import net.sf.l2j.gameserver.model.item.instance.ItemInstance;
import net.sf.l2j.gameserver.network.serverpackets.MagicSkillUse;

/**
 * @author Williams and Stinkymadness
 */
public class Skins implements IItemHandler
{
    @Override
    public void useItem(Player player, ItemInstance item)
    {
        
        final DressMe dress = DressMeData.getInstance().getItemId(item.getItemId());
        if (dress == null)
            return;
        
        if (player.isDressMeEnabled())
        {
            player.setDress(null);
            player.broadcastPacket(new MagicSkillUse(player, player, 1036, 1, 4000, 0));
            player.broadcastUserInfo();
            player.setDressMeEnabled(false);
            player.sendMessage("A skin foi ativada!");
            
        }
        else
        {
            player.setDress(dress);
            player.broadcastPacket(new MagicSkillUse(player, player, 1036, 1, 4000, 0));
            player.broadcastUserInfo();
            player.setDressMeEnabled(true);
            player.sendMessage("A skin foi desativada!");
            
        }
    }
}
