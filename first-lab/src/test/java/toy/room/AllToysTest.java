package toy.room;

import org.junit.jupiter.api.Test;
import toy.room.toys.Toy;
import toy.room.toys.balls.Ball;
import toy.room.toys.cars.BigCar;
import toy.room.toys.cars.MiddleCar;
import toy.room.toys.cars.SmallCar;
import toy.room.toys.cubes.Cube;
import toy.room.toys.dolls.Doll;
import toy.room.toys.exceptions.NegativePriceAmount;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AllToysTest {
    @Test
    public void shouldCreateDollWithPrice50(){
        //given
        //when
        Toy doll = new Doll(50f);
        //then
        assertEquals(doll.getPrice(), 50f);
        assertEquals(doll.getName(), "Doll");
    }

    @Test
    public void shouldCreateBigCarWithPrice300(){
        //given
        //when
        Toy bigCar = new BigCar(300f);
        //then
        assertEquals(bigCar.getPrice(), 300f);
        assertEquals(bigCar.getName(), "BigCar");
    }

    @Test
    public void shouldMiddleCarWithPrice200(){
        //given
        //when
        Toy middleCar = new MiddleCar(200f);
        //then
        assertEquals(middleCar.getPrice(), 200f);
        assertEquals(middleCar.getName(), "MiddleCar");
    }

    @Test
    public void shouldCreateSmallCarWithPrice100(){
        //given
        //when
        Toy smallCar = new SmallCar(100f);
        //then
        assertEquals(smallCar.getPrice(), 100f);
        assertEquals(smallCar.getName(), "SmallCar");
    }

    @Test
    public void shouldCreateCubeWithPrice150And50(){
        //given
        //when
        Toy cube = new Cube(150.50f);
        //then
        assertEquals(cube.getPrice(), 150.50f);
        assertEquals(cube.getName(), "Cube");
    }

    @Test
    public void shouldCreateBallWithPrice220And60(){
        //given
        //when
        Toy ball = new Ball(220.60f);
        //then
        assertEquals(ball.getPrice(), 220.60f);
        assertEquals(ball.getName(), "Ball");
    }

    @Test
    public void shouldRenameDoll(){
        //given
        //when
        Toy doll = new Doll(140.40f);
        doll.renameToy("Barbie");
        //then
        assertEquals(doll.getName(), "Barbie");
    }

    @Test
    public void shouldSetNewPrice() throws NegativePriceAmount {
        //given
        //when
        Toy ball = new Ball(20f);
        ball.setPrice(100f);
        //then
        assertEquals(ball.getPrice(), 100f);
    }

    @Test
    public void shouldThrowNegativePriceAmountException(){
        //given
        Toy toy = new Doll(150.5f);
        //when
        Throwable exception = assertThrows(NegativePriceAmount.class, () -> toy.setPrice(-13.65f));
        //then
        assertEquals("You can't change price to negative number", exception.getMessage());
    }
}
