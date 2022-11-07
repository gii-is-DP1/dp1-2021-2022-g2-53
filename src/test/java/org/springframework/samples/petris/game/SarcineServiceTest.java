package org.springframework.samples.petris.game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))

public class SarcineServiceTest {

    @Autowired
    private GameService gameService;

    @Autowired
    private BoardService boardService;

    @Autowired
    private SarcineService sarcineService;

    @Test
    public void sarcinesCount() {
            int count=sarcineService.sarcineCount();
            assertEquals(count, 2);
        }

    @Test
    public void SaveSarcine() {
        Game game= gameService.findId(1);
        Board board = boardService.findById(2);
        board.setGame(game);
        Sarcine sarcine=new Sarcine();
        sarcine.setBoard(board);
        sarcine.setColor("black");
        sarcine.setPosition(1);
        sarcineService.save(sarcine);
        int count= sarcineService.sarcineCount();
        assertEquals(count, 3);
}


}
