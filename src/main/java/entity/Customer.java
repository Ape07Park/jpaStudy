package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@ToString
@NoArgsConstructor
@Setter
@Getter
@Entity // jpa를 이용해 데이터가 들어갈 객체임을 표시
@Table(name="customer_tb") // 어떤 테이블에 데이터 CRUD 할지
public class Customer {
    @Id // PK
    private String id;
    private String name;
    private long registerDate;

    public Customer(String id, String name) {
        this.id = id;
        this.name = name;
        this.registerDate = System.currentTimeMillis();
    }

    public static Customer sample() {
        return new Customer("ID0001", "kim");
    }
}
