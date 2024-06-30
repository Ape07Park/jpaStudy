import entity.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class CustomerJpaExam {
    public static void main(String[] args) {
        // EntityManager를 가져오기 위한 팩토리 설정
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("customer-exam"); // persistence.xml의 <persistence-unit name="customer-exam">을 따라감
        // EntityManager(쿼리를 만들어 보내고 데이터 가져옴) 생성: jpa를 이용해 db에 저장 시 필요
        EntityManager em = emf.createEntityManager();

        /*
        EntityManager를 통해 트랜젝션을 가져오고 그 트랜젝션 안에서 CRUD 작업 실시
        * jpa로 CRUD 시 그 작업들이 트랜젝션 단위에서 이루어짐. 따라서 CRUD 작업 시 트랜젝션 가져오기 - 트랜젝션 시작 - 작업 - 커밋 or 에러 시 롤백 - 자원 반환의 단계를 거침
         */

        // 트랜젝션 가져오기
        EntityTransaction tx = em.getTransaction();
        // 트랜젝션 시작
        tx.begin();

         /*
        CRUD 작업 시작
         */
        try {

        /*
        CRUD 작업
         */
              /*
                C 작업
               */
//            em.persist(Customer.sample()); // 매개변수로 실제 넣을 객체 넣기

            /*
                R 작업
            */
            // *find() 메서드는 EntityManager가 DB에서 가져온 데이터를 기본생성자를 이용해 인스턴스를 생성함
            Customer foundCustomer = em.find(Customer.class, "ID0001"); // 매개변수로 가져올 객체, PK 지정
            System.out.println("가져온 customer의 내용물" + foundCustomer.toString());

            /*
                U 작업: 반드시 먼저 R 작업 필요
             */

            // set으로 변경
//            foundCustomer.setName("Park");
            System.out.println("수정한 customer의 내용물" + foundCustomer.toString());

            /*
                D 작업
             */
            em.remove(foundCustomer);
            System.out.println("제거 customer의 내용물" + foundCustomer.toString());

            // 트랜젝션 커밋 즉 CRUD 작업 적용. 트랜젝션 커밋 시 EntityManager의 관리 하에 있는 것은 업데이트된 것이 자동 반영됨
            tx.commit();
        } catch (Exception e) {

            tx.rollback();
        } finally {
            // 자원 반환
            em.close();

        }
        emf.close();
    }
}
