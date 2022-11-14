package org.springframework.samples.petris.game;

import static org.junit.Assert.assertEquals;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.beans.PropertyEditor;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;
import javax.validation.Validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class GameServiceTests {

	@Autowired
	private GameService gameService;
	
	@Autowired
	private PieceService pieceService;
	
	@Autowired 
	private PieceRepository pieceRepository;
	
	private static final BindingResult result = new BindingResult() {
		
		@Override
		public void setNestedPath(String nestedPath) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void rejectValue(String field, String errorCode, Object[] errorArgs, String defaultMessage) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void rejectValue(String field, String errorCode, String defaultMessage) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void rejectValue(String field, String errorCode) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void reject(String errorCode, Object[] errorArgs, String defaultMessage) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void reject(String errorCode, String defaultMessage) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void reject(String errorCode) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void pushNestedPath(String subPath) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void popNestedPath() throws IllegalStateException {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public boolean hasGlobalErrors() {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean hasFieldErrors(String field) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean hasFieldErrors() {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean hasErrors() {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public String getObjectName() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public String getNestedPath() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public List<ObjectError> getGlobalErrors() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public int getGlobalErrorCount() {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public ObjectError getGlobalError() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Object getFieldValue(String field) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Class<?> getFieldType(String field) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public List<FieldError> getFieldErrors(String field) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public List<FieldError> getFieldErrors() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public int getFieldErrorCount(String field) {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public int getFieldErrorCount() {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public FieldError getFieldError(String field) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public FieldError getFieldError() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public int getErrorCount() {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public List<ObjectError> getAllErrors() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public void addAllErrors(Errors errors) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public String[] resolveMessageCodes(String errorCode, String field) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public String[] resolveMessageCodes(String errorCode) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Object getTarget() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Object getRawFieldValue(String field) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public PropertyEditorRegistry getPropertyEditorRegistry() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Map<String, Object> getModel() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public PropertyEditor findEditor(String field, Class<?> valueType) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public void addError(ObjectError error) {
			// TODO Auto-generated method stub
			
		}
	};
	

	
	

	@Test
	@Transactional
	public void GameCountTest() {
		int count = gameService.gameCount();
		assertEquals(count, 3);
	}

	@Test
	public void SaveGameTest() {
		Game game = new Game();
		gameService.save(game);
		int count = gameService.gameCount();
		assertEquals(count, 4);

	}

	@Test
	@Transactional
	public void DeleteGameTest() {
		Game game = new Game();
		gameService.save(game);
		gameService.delete(game);
		int count = gameService.gameCount();
		assertEquals(count, 3);

	}

	@Test
	@Transactional
	public void FindByIdTest() {
		Game n = gameService.findId(2);
		assertEquals(n.getId(), 2);
		assertEquals(n.getBoard().getId(), 2);

	}

	@Test
	@Transactional
	public void FindGameByToken() {
		Game game = gameService.findId(2);
		Assertions.assertTrue(game.getToken().equals("ebc-qer"));
	}
	
	// Probamos movimiento de ficha negra con exito
//	@WithMockUser(value = "user")
////	@Test
//	@Transactional
//	public void testPhasesMovimientoConExitoTurnoBlack() throws MoveInvalidException {
//		Game game = gameService.findId(3);
//		
//			
//		Movement movement = new Movement();
//		movement.setInitialPosition(3);
//		movement.setNumber(1);
//		movement.setDestinyPosition(1);
//		Integer turnoAntesMovimiento = game.getTurno();
//		Integer positionInitial = game.getBoard().getPieces().get(0).getPosition();
//		this.gameService.phases(game, movement, result);
//		Integer positionFinal = game.getBoard().getPieces().get(0).getPosition();
//		Integer turnoDespuesMovimiento = game.getTurno();
//		Assertions.assertTrue(turnoDespuesMovimiento == turnoAntesMovimiento+1);
//		Assertions.assertTrue(positionInitial == 3);
//		Assertions.assertTrue(positionFinal == 1);
//	}
	
	// Probamos movimiento de ficha negra
		@WithMockUser(value = "user")
		@Test
		@Transactional
		public void testPhasesMovimientoTurnoBlack() throws MoveInvalidException {
			Game game = gameService.findId(2);
			Movement movement = new Movement();
			movement.setInitialPosition(3);
			movement.setNumber(1);
			movement.setDestinyPosition(1);
			Integer turnoAntesMovimiento = game.getTurno();
			Integer positionInitial = game.getBoard().getPieces().get(0).getPosition();
			this.gameService.phases(game, movement, result);
			Integer positionFinal = game.getBoard().getPieces().get(0).getPosition();
			Integer turnoDespuesMovimiento = game.getTurno();
			Assertions.assertTrue(turnoDespuesMovimiento == turnoAntesMovimiento+1);
			Assertions.assertTrue(positionInitial == 3);
			Assertions.assertTrue(positionFinal == 1);
		}
	
		// Probamos movimiento tipo binary
		@WithMockUser(value = "user")
		@Test
		@Transactional
		public void testPhasesMovimientoTurnoBinary() throws MoveInvalidException {
			Game game = gameService.findId(1);
			Movement movement = new Movement();
			Integer turnoAntesMovimiento = game.getTurno();
			this.gameService.phases(game, movement, result);
			Integer turnoDespuesMovimiento = game.getTurno();
			Assertions.assertTrue(turnoDespuesMovimiento == turnoAntesMovimiento+1);
		}
		
		// Probamos movimiento tipo pollution
				@WithMockUser(value = "user")
				@Test
				@Transactional
				public void testPhasesMovimientoTurnoPollution() throws MoveInvalidException {
					Game game = gameService.findId(3);
					Movement movement = new Movement();
					Integer puntosBlackInicial = game.getPointsBlack();
					this.gameService.phases(game, movement, result);
					Integer puntosBlackFinal = game.getPointsBlack();
					Assertions.assertTrue(puntosBlackFinal == puntosBlackInicial+1);
				}
}