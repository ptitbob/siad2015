package fr.univ.tours.siad.organisation.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * SIAD - JavaEE et WS
 *
 * @author francois
 */
@Entity
@SequenceGenerator(name = "category_sequence", sequenceName = "category_sequence", allocationSize = 2)
@NamedQueries({
        @NamedQuery(name = Category.FIND_ALL, query = "select c from Category c")
})
public class Category {

    public static final String CATEGORY_ID = "CATEGORY_ID";
    public static final String FIND_ALL = "Category.FIND_ALL";

    @Id
    @GeneratedValue(generator = "category_sequence")
    @Column(name = CATEGORY_ID)
    private Long id;

    /**
     * Nom de la catégory
     */
    @Column(length = 100)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Activity> activityList;

    public Category() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Activity> getActivityList() {
        return activityList;
    }

    public void setActivityList(List<Activity> activityList) {
        this.activityList = activityList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category category = (Category) o;
        return Objects.equals(getName(), category.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
