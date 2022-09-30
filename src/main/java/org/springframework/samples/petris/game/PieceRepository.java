package org.springframework.samples.petris.game;

import org.springframework.data.repository.CrudRepository;

public interface PieceRepository extends CrudRepository<Piece, Integer> {

}
