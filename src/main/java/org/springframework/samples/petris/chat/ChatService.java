package org.springframework.samples.petris.chat;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChatService {

	@Autowired
	private ChatRepository chatRepository;

	@Transactional
	public void save(Chat chat) {
		chatRepository.save(chat);
	}

}