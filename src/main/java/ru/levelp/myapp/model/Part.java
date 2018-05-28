package ru.levelp.myapp.model;

import javax.persistence.*;

import static ru.levelp.myapp.model.Part.SEARCH_BY_PART_ID;

/**
 * Created by ulbas on 06.05.2018.
 */

@Entity // класс стал сущностью для h2
@NamedQueries({
        @NamedQuery(name = SEARCH_BY_PART_ID, query = "from Part where partId = :partId")
})

@Table // сама по себе аннотация ничего не меняет, но для нее можно прописывать парамметры
        // например, индексы, констрейнты (типа uniqueConstraint - сделать уникальность на 2 поля, а не на одно
public class Part {

    public static final String SEARCH_BY_PART_ID = "getPart";

    @Id // первичный ключ
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "part_id", unique = true, nullable = false) // не первичный ключ, но будет присутствовать в базе, причем поле будет
    private String partId;    // называться "part_id", будет уникальным и не null

    @Column
    private String title;

    @ManyToOne (cascade = CascadeType.PERSIST) // можно постаивть optional = true, тогда supplier необязательным будет
    private Supplier supplier;

    @Column
    private boolean legacy;

    public Part (String partId, String title) {
        if (partId == null) throw new IllegalArgumentException("partId shouldn't be null");
        this.partId = partId;
        this.title = title;
    }

    public Part () {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPartId() {
        return partId;
    }

    public void setPartId(String partId) {
        this.partId = partId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public boolean isLegacy() {
        return legacy;
    }

    public void setLegacy(boolean legacy) {
        this.legacy = legacy;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Part part = (Part) o;

        if (id != part.id) return false;
        if (legacy != part.legacy) return false;
        if (!partId.equals(part.partId)) return false;
        if (title != null ? !title.equals(part.title) : part.title != null) return false;
        return supplier.equals(part.supplier);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + partId.hashCode();
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + supplier.hashCode();
        result = 31 * result + (legacy ? 1 : 0);
        return result;
    }
}
