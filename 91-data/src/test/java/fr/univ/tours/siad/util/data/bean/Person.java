package fr.univ.tours.siad.util.data.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author: François Robert
 */
@Entity
public class Person {
    @Id @GeneratedValue
    private Long id;

    private String login;


    public Person() {
    }

    public Person(String login, String name) {
        this();
        this.login = login;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        if (!id.equals(person.id)) return false;
        if (!login.equals(person.login)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + login.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Person{");
        sb.append("id=").append(id);
        sb.append(", login='").append(login).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
