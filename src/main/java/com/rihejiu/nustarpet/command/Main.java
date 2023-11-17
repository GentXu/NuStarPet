package com.rihejiu.nustarpet.command;

import com.rihejiu.nustarpet.Menu;
import com.rihejiu.nustarpet.attribute.AddSourceAttribute;
import com.rihejiu.nustarpet.listens.EventListener;
import com.rihejiu.nustarpet.menu.*;
import com.rihejiu.nustarpet.taltent.PetTalent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import javax.annotation.ParametersAreNonnullByDefault;
import java.text.NumberFormat;

public class Main implements CommandExecutor {
    @Override
    @ParametersAreNonnullByDefault
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args){
        if (!sender.isOp() && !(sender instanceof ConsoleCommandSender)){
            return false;
        }
        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "用法：");
            sender.sendMessage(ChatColor.RED + "/nspet identify playername petid 鉴定异兽");
            sender.sendMessage(ChatColor.RED + "/nspet petup playername 喂养异兽");
            sender.sendMessage(ChatColor.RED + "/nspet petattr playername 查看异兽属性");
            sender.sendMessage(ChatColor.RED + "/nspet petimmolate playername 献祭异兽");
            sender.sendMessage(ChatColor.RED + "/nspet petevolve playername 进化异兽");
            sender.sendMessage(ChatColor.RED + "/nspet petinherit playername 传承异兽");
            sender.sendMessage(ChatColor.RED + "/nspet pettalent playername 培养异兽");
            sender.sendMessage(ChatColor.RED + "/nspet petfruit playername 喂养果实");
            return true;
        }
        switch (args[0]){
            case "identify":
                PetList pl = new PetList();
                if (args[2].equals("bianfu")){
                    pl.bianfu(Bukkit.getPlayer(args[1]));
                    break;
                }
                if (args[2].equals("zhizhu")){
                    pl.zhizhu(Bukkit.getPlayer(args[1]));
                    break;
                }
                if (args[2].equals("saman")){
                    pl.saman(Bukkit.getPlayer(args[1]));
                    break;
                }
                if (args[2].equals("tonghon")){
                    pl.tonghong(Bukkit.getPlayer(args[1]));
                    break;
                }
                if (args[2].equals("konglang")){
                    pl.konglang(Bukkit.getPlayer(args[1]));
                    break;
                }
                if (args[2].equals("zhanhun")){
                    pl.zhanhun(Bukkit.getPlayer(args[1]));
                    break;
                }
                break;
            case "petup":
                new Menu(Bukkit.getPlayer(args[1])).open();
                break;
            case "petattr":
                PetTalent pt = new PetTalent();
                NumberFormat formatter = NumberFormat.getNumberInstance();
                formatter.setMaximumFractionDigits(2);
                Player player = Bukkit.getPlayer(args[1]);
                AddSourceAttribute asa = EventListener.getPlayerAsa(player);
                player.sendMessage(ChatColor.WHITE + "异兽提供属性:");
                player.sendMessage(ChatColor.WHITE + "攻击力: " + formatter.format(asa.getPower() * 0.3));
                player.sendMessage(ChatColor.WHITE + "命中几率: " + formatter.format(asa.getPower() * 0.03));
                player.sendMessage(ChatColor.WHITE + "闪避几率: " + formatter.format(asa.getSpeed() * 0.03));
                player.sendMessage(ChatColor.WHITE + "生命值: " + formatter.format(asa.getSpirit() * 6L));
                player.sendMessage(ChatColor.WHITE + "魔力值: " + formatter.format(asa.getWisdom() * 1.2));
                player.sendMessage(ChatColor.WHITE + "魔击力: " + formatter.format(asa.getWisdom() * 0.1));
                pt.talentMsg(asa.getPetnm(),player);
                break;
            case "petimmolate":
                new ImmolateMenu(Bukkit.getPlayer(args[1])).open();
                break;
            case "petrevive":
                new ReviveMenu(Bukkit.getPlayer(args[1])).open();
                break;
            case "petevolve":
                new EvolveMenu(Bukkit.getPlayer(args[1])).open();
                break;
            case "petinherit":
                new InheritMenu(Bukkit.getPlayer(args[1])).open();
                break;
            case "follow":
                Utils.follow(Bukkit.getPlayer(args[1]));
                break;
            case "unfollow":
                Utils.unFollow(Bukkit.getPlayer(args[1]));
                break;
            case "pettalent":
                new TalentMenu(Bukkit.getPlayer(args[1])).open();
                break;
            case "petfruit":
                new FruitMenu(Bukkit.getPlayer(args[1])).open();
                break;
        }
        return true;
    }
}
