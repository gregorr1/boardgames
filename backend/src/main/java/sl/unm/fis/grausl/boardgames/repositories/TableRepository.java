package sl.unm.fis.grausl.boardgames.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sl.unm.fis.grausl.boardgames.entities.BarTable;

public interface TableRepository extends JpaRepository<BarTable, Integer> {
	List<BarTable> findByBoardGameContainingIgnoreCase(String boardGame);
}
