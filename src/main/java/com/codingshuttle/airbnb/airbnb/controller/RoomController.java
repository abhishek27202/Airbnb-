package com.codingshuttle.airbnb.airbnb.controller;

import com.codingshuttle.airbnb.airbnb.dto.RoomDto;
import com.codingshuttle.airbnb.airbnb.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/hotels/{hotelId}/room")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping()
    public ResponseEntity<RoomDto> createRoom (@PathVariable Long hotelId,
            @RequestBody RoomDto roomDto){
        RoomDto room=roomService. saveRoom(hotelId,roomDto);
        return new ResponseEntity<>(room, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<RoomDto>> getAllRoomInHotel(@PathVariable Long hotelId){
       List<RoomDto> room= roomService.findAllRoomByHotel(hotelId);
       return new ResponseEntity<>(room,HttpStatus.OK);

    }

    @GetMapping("/{roomId}")
    public ResponseEntity<RoomDto> getRoom(@PathVariable Long roomId){
        RoomDto room=roomService.getRoomById(roomId);
        return new ResponseEntity<>(room,HttpStatus.OK);
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long roomId) {
        roomService.deleteById(roomId);
        return ResponseEntity.noContent().build();
    }
}
