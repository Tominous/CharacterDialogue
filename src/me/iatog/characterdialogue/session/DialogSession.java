package me.iatog.characterdialogue.session;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.iatog.characterdialogue.CharacterDialogPlugin;
import me.iatog.characterdialogue.dialogs.DialogMethod;

public class DialogSession {

	private CharacterDialogPlugin main;
	private UUID uuid;
	private List<String> dialogs;
	private int index;
	private boolean stop;

	public DialogSession(CharacterDialogPlugin main, Player player, List<String> dialogs) {
		this.main = main;
		this.uuid = player.getUniqueId();
		this.dialogs = dialogs;
	}

	public void start(int index) {
		for (int i = index; i < dialogs.size(); i++) {
			if (stop) {
				this.stop = false;
				break;
			}
			String dialog = dialogs.get(i);
			this.index = i;
			if (!dialog.contains(":")) {
				continue;
			}
			String[] splitted = dialog.split(":");
			String methodName = splitted[0].toUpperCase().trim();
			String arg = dialog.substring(methodName.length() + 1).trim();

			if (!main.getCache().getMethods().containsKey(methodName)) {
				main.getLogger().warning("The method \"" + methodName + "\" doesn't exist");
				return;
			}
			
			DialogMethod method = main.getCache().getMethods().get(methodName);
			method.execute(Bukkit.getPlayer(uuid), arg, this);
		}
	}
	
	public void setCurrentIndex(int index) {
		this.index = index;
	}

	public int getCurrentIndex() {
		return index;
	}

	public List<String> getDialogs() {
		return dialogs;
	}

	public void cancel() {
		this.stop = true;
	}
	
	public void destroy() {
		if(main.getCache().getSessions().containsKey(uuid)) {
			main.getCache().getSessions().remove(uuid);
		}
	}
}
