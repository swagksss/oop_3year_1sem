package toy.room;


import org.junit.jupiter.api.Test;
import toy.room.toys.Toy;
import toy.room.toys.ToysComparator;
import toy.room.toys.balls.Ball;
import toy.room.toys.cars.BigCar;
import toy.room.toys.cars.MiddleCar;
import toy.room.toys.cubes.Cube;
import toy.room.toys.dolls.Doll;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {
    private Room room;
    private List<Toy> toys = new ArrayList<>(Arrays.asList(new Doll(150.50f),
            new Ball(100.30f), new Ball(45.65f), new MiddleCar(170.f),
            new Cube(40.f)));

    public void createRoom(){
        this.room = new Room("Sunshine", 800, toys, Children.PRESCHOOLERS);
    }

    @Test
    public void shouldCreateRoomWithoutNameAndToys(){
        //given
        //when
        this.room = new Room();
        //then
        assertEquals("", room.getName());
        assertTrue(room.getToys().isEmpty());
    }

    @Test
    public void shouldCreateRoomWithToys(){
        //given
        //when
        createRoom();
        //then
        assertEquals(toys, room.getToys());
    }

    @Test
    public void shouldRenameRoom(){
        //given
        createRoom();
        //when
        room.renameRoom("Moonlight");
        //then
        assertEquals("Moonlight", room.getName());
    }

    @Test
    public void shouldChangeTypeOFRoom(){
        //given
        createRoom();
        //when
        room.changeTypeOfRoom(Children.INFANTS);
        //then
        assertEquals(room.getTypeOfRoom(), Children.INFANTS);
    }

    @Test
    public void shouldCAddBigCarToRoom(){
        //given
        createRoom();
        Toy bigCar = new BigCar(250.f);
        //when
        room.addToy(bigCar);
        //then
        assertTrue(room.getToys().contains(bigCar));
        assertEquals(room.getToys().size(), 6);
    }

    @Test
    public void shouldRemoveBallFromRoom(){
        //given
        createRoom();
        //when
        room.removeToy("Ball");
        //then
        assertFalse(room.getToys().contains(new Ball(100.30f)));
        assertFalse(room.getToys().contains(new Ball(45.65f)));

    }

    @Test
    public void shouldSortToysFromLowToHigh(){
        //given
        createRoom();
        //when
        room.sortToys(new ToysComparator());
        //then
        assertEquals(room.getToys(), Arrays.asList(new Cube(40.f), new Ball(45.65f),
                new Ball(100.30f), new Doll(150.50f), new MiddleCar(170.f)
                ));
    }

    @Test
    public void shouldFindToysInInterval(){
        //given
        createRoom();
        //when
        List<Toy> result = room.findToysInPriceInterval(90.0, 160.0);
        //then
        assertEquals(result, Arrays.asList(new Doll(150.50f), new Ball(100.30f)));
    }

    @Test
    public void shouldNotAddNewToyToRoom(){
        //given
        createRoom();
        Toy expensiveDoll = new Doll(800f);
        //when
        room.addToy(expensiveDoll);
        //then
        assertFalse(room.getToys().contains(expensiveDoll));
    }
}