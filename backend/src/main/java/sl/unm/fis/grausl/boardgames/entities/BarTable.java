package sl.unm.fis.grausl.boardgames.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class BarTable {
	@Id
	private int id;
	private String boardGame;
	private String barName;
	@OneToMany(mappedBy="table")
	@JsonIgnore
	private List<Timeslot> listTimeslots;
	
	public BarTable() {
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBoardGame() {
		return boardGame;
	}
	public void setBoardGame(String boardGame) {
		this.boardGame = boardGame;
	}
	public String getBarName() {
		return barName;
	}
	public void setBarName(String barName) {
		this.barName = barName;
	}
	public List<Timeslot> getListTimeslots() {
		return listTimeslots;
	}
	public void setListTimeslots(List<Timeslot> listTimeslots) {
		this.listTimeslots = listTimeslots;
	}
}
