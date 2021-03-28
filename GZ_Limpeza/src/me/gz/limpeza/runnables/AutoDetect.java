package me.gz.limpeza.runnables;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import me.gz.limpeza.main;

public class AutoDetect implements Runnable {
	int max = main.CFG.getInt("MaxEntidades");
	int atual = 0;
	
	@Override
	public void run() {
		for(World wd : Bukkit.getWorlds()) {
			for(Entity en : wd.getEntities()) {
				if(en.getType() == EntityType.DROPPED_ITEM) {
					
					if(atual >= max) {
						if(Limpar.i <= 10)
							return;
						Bukkit.broadcastMessage(main.CFG.getString("Alertas.Detectar").replace("&", "§"));
						Limpar.i = 10;
						atual = 0;
					}
					atual++;
				}
			}
		}
	}
}
