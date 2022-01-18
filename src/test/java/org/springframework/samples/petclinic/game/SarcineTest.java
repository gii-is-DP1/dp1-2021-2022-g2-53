package org.springframework.samples.petclinic.game;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))

public class SarcineTest {

	@Autowired
	private BoardService boardService;
	@Autowired

	
	
	@Test
	public void getPositionYInPixelsSarcineTest() {
	
		Board board = boardService.findById(2);
		
		Integer position = 1;
		Sarcine sarcine = new Sarcine();
		sarcine.setBoard(board);
		sarcine.setPosition(position);
		sarcine.setColor("black");
		
		Integer position2 = 3;
		Sarcine sarcine2 = new Sarcine();
		sarcine2.setBoard(board);
		sarcine2.setPosition(position2);
		sarcine2.setColor("red");
		
		Assertions.assertThat(sarcine.getPositionYInPixels(position)).isEqualTo(50);
		Assertions.assertThat(sarcine2.getPositionYInPixels(position2)).isEqualTo(140);

	}
	

	@Test
	public void getPositionXInPixelsBlackSarcineTest() {
	
		Board board = boardService.findById(2);
	
		Integer position = 4;
		Sarcine sarcine = new Sarcine();
		sarcine.setBoard(board);
		sarcine.setPosition(position);
		sarcine.setColor("black");
		
		Assertions.assertThat(sarcine.getPositionXInPixels(position)).isEqualTo(220);
	}
	
	@Test
	public void getPositionXInPixelsRedSarcineTest() {
	
		Board board = boardService.findById(2);
		
		Integer position = 4;
		Sarcine sarcine = new Sarcine();
		sarcine.setBoard(board);
		sarcine.setPosition(position);
		sarcine.setColor("red");
		
		
		Assertions.assertThat(sarcine.getPositionXInPixels(position)).isEqualTo(260);
	}


}
