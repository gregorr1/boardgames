package sl.unm.fis.grausl.boardgames.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import sl.unm.fis.grausl.boardgames.entities.Player;
import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
	List<Player> findByTimeslotNotNull();
	List<Player> findByTimeslotId(int timeslotId);
}
