package me.iatog.characterdialogue.api;

import java.util.Optional;

public interface CharacterDialogueAPI {
	
	/**
	 * search if a dialogue is present
	 * @param id the id of the npc
	 * @return the path of the dialogue
	 */
	Optional<String> searchDialogueByNPCId(int id);
	
	/**
	 * reload all character holograms.
	 */
	void reloadHolograms();
	
	/**
	 * load npc hologram
	 * @param npcId the id of the npc
	 */
	void loadHologram(int npcId);
}
