package repository;

import com.avizhen.entity.*;
import com.avizhen.enums.UserRoleEnum;
import com.avizhen.factory.CarFactory;
import com.avizhen.factory.OrderFactory;
import com.avizhen.factory.UserFactory;
import com.avizhen.repository.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * Created by Александр on 25.10.2016.
 */
@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestRepositoryConfig.class)
public class TestRepository {
    private static final Logger LOG = LogManager.getLogger();

    private User tempUser = UserFactory.createUser("testname", "testsurname", UserRoleEnum.GUEST);
    private Order tempOrder = OrderFactory.createOrder("ready");
    private Car tempCar = CarFactory.createCar("Astra", "Opel");

    @Resource
    private EntityManagerFactory emf;
    protected EntityManager em;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarRepository carRepository;

    @Resource
    private OrderRepository orderRepository;

    @Autowired
    private AdvertRepository advertRepository;
    @Autowired
    private ItemRepository itemRepository;

    @Before
    public void setUp() throws Exception {
        em = emf.createEntityManager();
    }


    @Test
    //@Transactional
    public void testSaveUserAndDelete() {
        LOG.info("Start saving user ...");
        tempUser.getRole().setRole("dsas");
        userRepository.save(tempUser);
        int userId = tempUser.getId();
        LOG.info("user id: " + userId);
        LOG.info("User was saved successful");
        LOG.info("Start deleting user ...");
        userRepository.delete(userId);
        User checkUser = userRepository.findOne(userId);
        Assert.assertNull(checkUser);
        LOG.info("User was deleted successful");

    }

    @Test
    public void testSaveOrderAddingToUserAndDelete() {
        LOG.info("Start saving order ...");
        orderRepository.save(tempOrder);
        LOG.info("Order was saved successful");
        LOG.info("Start adding order ...");
        userRepository.save(tempUser);
        int userId = tempUser.getId();
        tempOrder = orderRepository.findOne(tempOrder.getId());
        tempOrder.setUser(tempUser);
        orderRepository.save(tempOrder);
        int orderId = tempOrder.getId();
        LOG.info("Order was adding successful");
        LOG.info("Deleting Order and user");
        userRepository.delete(userId);
        Order checkOrder = orderRepository.findOne(orderId);
        Assert.assertNull(checkOrder);
        Order checkUser = orderRepository.findOne(userId);
        Assert.assertNull(checkUser);
        LOG.info("Order and user was deleted successful");
    }

    @Test
    public void testAddCarAndDelete() {
        LOG.info("Start saving car ...");
        carRepository.save(tempCar);
        int carId = tempCar.getId();
        LOG.info("Car was saved successful");
        LOG.info("Deleting the car");
        carRepository.delete(carId);
        Car checkCar = carRepository.findOne(carId);
        Assert.assertNull(checkCar);
        LOG.info("Car was deleted successful");

    }

    @Test
    @Transactional
    public void testAddAdvertAndDelete() {
        LOG.info("Start saving advert ...");
        Advert tempAdvert = new Advert();
        carRepository.save(tempCar);
        tempAdvert.setCar(tempCar);
        tempAdvert.setDescription("Hello from test");
        advertRepository.save(tempAdvert);
        int carId = tempCar.getId();
        int advertId = tempAdvert.getId();
        LOG.info("Advert was saved successful ...");
        LOG.info("Start deleting advert and temp car");
        advertRepository.delete(advertId);
        carRepository.delete(carId);
        Car checkCar = carRepository.findOne(carId);
        Assert.assertNull(checkCar);
        LOG.info("Car was deleted successful");
        Advert checkAdvert = advertRepository.findOne(advertId);
        Assert.assertNull(checkAdvert);
        LOG.info("Advert was deleted successful");
    }

    @Test
    public void testAddItemAndDelete() {
        LOG.info("Start saving order and car ...");
        orderRepository.save(tempOrder);
        int orderId = tempOrder.getId();
        LOG.info("Order was saved successful");
        LOG.info("Start saving car ...");
        carRepository.save(tempCar);
        int carId = tempCar.getId();
        LOG.info("Car was saved successful");
        LOG.info("Start saving the item ...");
        Item tempItem = new Item();
        tempItem.setCar(tempCar);
        tempItem.setUserOrder(tempOrder);
        tempItem.setCount(1);
        itemRepository.save(tempItem);
        int itemId = tempItem.getId();
        LOG.info("Item was saved successful ...");
        LOG.info("Start deleting the item ...");
        itemRepository.delete(tempItem);
        Item checkItem = itemRepository.findOne(itemId);
        Assert.assertNull(checkItem);
        LOG.info("Item was deleted successful");
        LOG.info("Start deleting car and order ...");
        carRepository.delete(tempCar);
        Item checkCar = itemRepository.findOne(carId);
        Assert.assertNull(checkCar);
        LOG.info("Car was deleted successful");
        orderRepository.delete(tempOrder);
        Order checkOrder = orderRepository.findOne(orderId);
        Assert.assertNull(checkOrder);
        LOG.info("Order was deleted successful");
    }

    @Test
    @Ignore
    public void addOrderToUser() {
        tempCar = CarFactory.createCar("Omega", "Opel");
        carRepository.save(tempCar);

        tempCar = CarFactory.createCar("6", "Mazda");
        carRepository.save(tempCar);

        tempCar = CarFactory.createCar("Megane", "Renault");
        carRepository.save(tempCar);

        tempCar = CarFactory.createCar("A7", "Audi");
        carRepository.save(tempCar);
    }

    /*@Resource
    private RateService rateService;
    @Test
    public void testRest() {
        Rate rate = rateService.getRate("RUB", LocalDate.now());
        LOG.info(rate);
    }*/

    @Ignore
    @Test
    public void testClearTables() {

        orderRepository.deleteAll();
        userRepository.deleteAll();
        advertRepository.deleteAll();
        carRepository.deleteAll();
    }





}
