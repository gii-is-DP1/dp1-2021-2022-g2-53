package org.springframework.samples.petris.chat;

import java.util.Collection;



import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;



public interface ChatRepository extends CrudRepository<Chat, Integer>{
	
	@Query("SELECT c FROM Chat c WHERE c.game.id= :id")
	Collection<Chat> getChatsbyGameId(int id);
    
    
}