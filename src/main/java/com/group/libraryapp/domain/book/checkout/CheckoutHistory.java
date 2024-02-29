package com.group.libraryapp.domain.book.checkout;

import static javax.persistence.GenerationType.IDENTITY;

import com.group.libraryapp.domain.user.User;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CheckoutHistory {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private String bookName;

    private boolean isReturned;

    public CheckoutHistory() {
    }

    public CheckoutHistory(User user, String bookName) {
        this.user = user;
        this.bookName = bookName;
        isReturned = false;
    }

    public void returnBook() {
        isReturned = true;
    }

    public String getBookName() {
        return this.bookName;
    }

    public boolean getIsReturned() {
        return this.isReturned;
    }
}
