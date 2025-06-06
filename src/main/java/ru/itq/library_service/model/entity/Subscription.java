package ru.itq.library_service.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "subscriptions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subscription_id", nullable = false)
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "user_full_name", nullable = false)
    private String userFullName;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "user_active", nullable = false)
    private boolean userActive;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "subscription_id")
    private List<Book> books;

    public Subscription(String userName, String userFullName, boolean userActive) {
        this.userName = userName;
        this.userFullName = userFullName;
        this.userActive = userActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subscription that = (Subscription) o;
        return Objects.equals(id, that.id) && Objects.equals(userFullName, that.userFullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userFullName);
    }
}
