package sl.unm.fis.grausl.boardgames.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sl.unm.fis.grausl.boardgames.entities.BarTable;
import sl.unm.fis.grausl.boardgames.entities.Timeslot;
import sl.unm.fis.grausl.boardgames.entities.Player;
import sl.unm.fis.grausl.boardgames.repositories.PlayerRepository;
import sl.unm.fis.grausl.boardgames.repositories.TableRepository;
import sl.unm.fis.grausl.boardgames.repositories.TimeslotRepository;

@Service
public class GameService {
	@Autowired
	PlayerRepository playerRepository;
	@Autowired
	TableRepository tableRepository;
	@Autowired
	TimeslotRepository timeslotRepository;
	
	public List<Player> getAllPlayers() {
		return playerRepository.findAll();
	}
	
	public List<BarTable> getAllTables() {
		return tableRepository.findAll();
	}
	
	public List<Timeslot> getAllTimeslots() {
		return timeslotRepository.findAll();
	}
	
	public void addOrUpdatePlayer(Player p) {
		playerRepository.save(p);
	}
	
	public void addOrUpdateTable(BarTable t) {
		tableRepository.save(t);
	}
	
	public void addOrUpdateTimeslot(Timeslot t) {
		timeslotRepository.save(t);
	}
	
	public void deletePlayer(int idPlayer) {
		playerRepository.deleteById(idPlayer);
	}
	
	public void deleteTable(int idTable) {
		tableRepository.deleteById(idTable);
	}
	
	public void deleteTimeslot(int idTimeslot) {
		timeslotRepository.deleteById(idTimeslot);
	}
	
	public List<Timeslot> getTimeslotsByTableId(int idTable) {
		return timeslotRepository.findByTableId(idTable);
	}
	
	public List<Player> getPlayersByTimeslotId(int idTimeslot) {
		return playerRepository.findByTimeslotId(idTimeslot);
	}
	
	public List<BarTable> getTablesByBoardGame(String boardGame) {
		return tableRepository.findByBoardGameContainingIgnoreCase(boardGame);
	}
}
