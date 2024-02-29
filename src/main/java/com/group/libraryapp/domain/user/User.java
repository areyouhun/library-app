package com.group.libraryapp.domain.user;

import com.group.libraryapp.domain.book.checkout.CheckoutHistory;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20, name = "name")
    private String name;
    private Integer age;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<CheckoutHistory> checkoutHistories = new ArrayList<>();

    protected User() {
    }

    public User(String name, Integer age) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어왔습니다.", name));
        }

        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void checkoutBook(String bookName) {
        this.checkoutHistories.add(new CheckoutHistory(this, bookName));
    }

    public void returnBook(String bookName) {
        CheckoutHistory targetHistory = this.checkoutHistories.stream()
                .filter(history -> history.getBookName().equals(bookName)
                                    && history.getIsReturned() == false)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

        targetHistory.returnBook();
    }
}
