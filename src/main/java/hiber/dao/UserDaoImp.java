package hiber.dao;

import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {

      try (Session currentSession = sessionFactory.openSession()) {
         currentSession.save(user);
      } catch (Exception e) {
         throw new RuntimeException("massage: " + e.getMessage());
      }

   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {

       TypedQuery<User> query;

       try (Session currentSession = sessionFactory.openSession()) {
           query = currentSession.createQuery("from User");
          return query.getResultList();
       } catch (Exception e){
          throw new RuntimeException("massage: " + e.getMessage());
       }

   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> getUserByModelAndSeriesOfCar(String model, int series) {

      TypedQuery<User> query;
      String hql = "from User users where users.car.model = :model and users.car.series = :series";

      try (Session currentSession = sessionFactory.openSession()) {
         query = currentSession.createQuery(hql);
         query.setParameter("model", model).setParameter("series", series);
         return query.getResultList();
      } catch (Exception e) {
         throw new RuntimeException("massage: " + e.getMessage());
      }

   }

}
