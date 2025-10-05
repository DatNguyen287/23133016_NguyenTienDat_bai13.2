package data;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import model.User;

public class UserDB {

    // Thêm user
    public static void insert(User user) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.persist(user);
            trans.commit();
        } catch (Exception e) {
            System.out.println("Insert error: " + e.getMessage());
            if (trans.isActive()) {
                trans.rollback();
            }
        } finally {
            em.close();
        }
    }

    // Cập nhật user
    public static void update(User user) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.merge(user);
            trans.commit();
        } catch (Exception e) {
            System.out.println("Update error: " + e.getMessage());
            if (trans.isActive()) {
                trans.rollback();
            }
        } finally {
            em.close();
        }
    }

    // Xóa user
    public static void delete(User user) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.remove(em.merge(user)); // merge để đảm bảo user nằm trong context
            trans.commit();
        } catch (Exception e) {
            System.out.println("Delete error: " + e.getMessage());
            if (trans.isActive()) {
                trans.rollback();
            }
        } finally {
            em.close();
        }
    }

    // Tìm user theo email
    public static User selectUserByEmail(String email) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT u FROM User u WHERE u.email = :email";
        TypedQuery<User> q = em.createQuery(qString, User.class);
        q.setParameter("email", email);

        try {
            return q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    // Kiểm tra email đã tồn tại chưa
    public static boolean emailExists(String email) {
        return selectUserByEmail(email) != null;
    }

    // Lấy danh sách tất cả user
    public static List<User> selectUsers() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT u FROM User u ORDER BY u.userId";
        TypedQuery<User> q = em.createQuery(qString, User.class);

        List<User> users = null;
        try {
            users = q.getResultList();
        } catch (Exception e) {
            System.out.println("Select users error: " + e.getMessage());
        } finally {
            em.close();
        }
        return users;
    }
}
