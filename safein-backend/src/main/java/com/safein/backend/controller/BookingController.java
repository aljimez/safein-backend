package com.safein.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.safein.backend.dto.Booking;
import com.safein.backend.dto.Handle;
import com.safein.backend.dto.Room;
import com.safein.backend.service.BookingServiceImpl;

public class BookingController {

	@Autowired
	BookingServiceImpl bookingServiceImpl;

	/*
	 * @Autowired HandleServiceImpl handlesServiceImpl;
	 */

	@GetMapping("/booking")
	public List<Handle> listBookings() {
		return bookingServiceImpl.listAllBookings();
	}

	@GetMapping("/booking/{id}")
	public Handle listBookingXID(@PathVariable(name = "id") Long id) {
		return bookingServiceImpl.listBookingsByBookingId(id);
	}

	@GetMapping("/booking/user/{users}")
	public List<Handle> listBookingByUser(@PathVariable(name = "users") Long user_id) {
		return bookingServiceImpl.listBookingsByUserId(user_id);
	}

	@GetMapping("/booking/customer/{customer}")
	public List<Handle> listBookingByCustomer(@PathVariable(name = "customer") Long id) {

		return bookingServiceImpl.listBookingsByCustomerId(id);
	}
	/*
	 * @GetMapping("/booking/rooms") public List<Booking>
	 * listBookingByRooms(@PathVariable(name = "rooms") String rooms) {
	 * 
	 * return handlesServiceImpl.listRoomsByBooking(rooms); }
	 */

	@GetMapping("/booking/rooms/{rooms}")
	public List<Room> listRoomByBookingId(@PathVariable(name = "rooms") Long id) {
		return bookingServiceImpl.listRoomsByBookingId(id);
	}

	@GetMapping("/booking/hotel/{hotel}")
	public List<Handle> listHotelByBookingId(@PathVariable(name = "hotel") Long id) {
		return bookingServiceImpl.listHandlesByHotelId(id);
	}

	@PostMapping("/booking")
	public Booking createBooking(@RequestBody Booking booking) {
		return bookingServiceImpl.saveBooking(booking);
	}

	@PutMapping("/booking/{id}")
	public Booking updateBooking(@PathVariable(name = "id") Long id, @RequestBody Booking booking) {

		Booking booking_selected = bookingServiceImpl.getBookingById(id);

		booking_selected.setAmount(booking.getAmount());
		booking_selected.setNumPeople(booking.getNumPeople());
		booking_selected.setPayed(booking.isPayed());
		booking_selected.setPendingPay(booking.isPendingPay());
		booking_selected.setUser(booking.getUser());
		booking_selected.setCustomers(booking.getCustomers());
		
		return bookingServiceImpl.updateBooking(booking_selected);
	}

	@DeleteMapping("/booking/{id}")
	public String eliminarBooking(@PathVariable(name = "id") Long id) {
		bookingServiceImpl.deleteBookingById(id);
		return "Booking deleted.";
	}
}