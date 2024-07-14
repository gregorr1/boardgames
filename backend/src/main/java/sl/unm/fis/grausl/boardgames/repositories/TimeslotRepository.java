package sl.unm.fis.grausl.boardgames.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sl.unm.fis.grausl.boardgames.entities.Timeslot;

public interface TimeslotRepository extends JpaRepository<Timeslot, Integer> {
	List<Timeslot> findByTableId(int tableId);
}
