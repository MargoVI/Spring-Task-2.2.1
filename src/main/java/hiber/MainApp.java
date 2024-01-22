package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("Vasya", "Ivanov", "iv@mail.ru");
      User user2 = new User("Katya", "Maximova", "max@mail.ru");
      User user3 = new User("Julia", "Li", "li@mail.ru");
      User user4 = new User("Max", "Kut", "kut@mail.ru");

      Car car1 = new Car("audi", 3);
      Car car2 = new Car("bmw", 5);
      Car car3 = new Car("mercedes", 53);
      Car car4 = new Car("mitsubishi", 1);

      user1.setCar(car1);
      user2.setCar(car2);
      user3.setCar(car3);
      user4.setCar(car4);

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = " +user.getCar().getModel() + " " +user.getCar().getSeries());
         System.out.println();
      }

      try {
         List<User> usersCars = userService.getUsers("bmw", 5);
         for (User userCar : usersCars) {
            System.out.println("Id = " + userCar.getId());
            System.out.println("First Name = " + userCar.getFirstName());
            System.out.println("Last Name = " + userCar.getLastName());
            System.out.println("Email = " + userCar.getEmail());
            System.out.println("Car = " + userCar.getCar().getModel() + " " + userCar.getCar().getSeries());
            System.out.println();
         }
      }catch (NoResultException e) {
         System.out.println("User not found");
      }

      context.close();
   }
}
