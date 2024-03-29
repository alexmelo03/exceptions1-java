package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.exceptions.DomainException;

public class Reservation {
	private Integer roomNuber;
	private Date checkIn;
	private Date checkOut;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public Reservation(Integer roomNuber, Date checkIn, Date checkOut)  {
		if(!checkOut.after(checkIn)) {
			throw new DomainException("Check-out date must be after check-in date");
		}	
		this.roomNuber = roomNuber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}
	public Integer getRoomNuber() {
		return roomNuber;
	}
	public void setRoomNuber(Integer roomNuber) {
		this.roomNuber = roomNuber;
	}
	public Date getCheckIn() {
		return checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}
	
	public long durantion() {
		long diff = checkOut.getTime() - checkIn.getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		
	}
	
	public void updateDate(Date checkIn, Date checkOut)  {
		Date now = new Date();
		
		if(checkIn.before(now)|| checkOut.before(now)) {
			throw new DomainException("Reservation dates for update must be future dates");
		}
		if(!checkOut.after(checkIn)) {
			throw new DomainException("Check-out date must be after check-in date");
		}		
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		
	}
	
	@Override
	public String toString() {
		return "Romm: "
				+ roomNuber
				+ ", Check-in: "
				+ sdf.format(checkIn)
				+", Check-out: "
				+ sdf.format(checkOut)
		        + ", "
		        + durantion()
		        + " Nights" ;
		
	}
	
}
