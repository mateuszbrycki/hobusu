package dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import dtos.TransactionCategory;
import models.TransactionType;
import dtos.User;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Created by Mateusz Brycki on 14/01/2017.
 */
public class Transaction {

    public Long id;

    public Double amount;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    public LocalDateTime date;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    public LocalDateTime creationDate;

    public TransactionType type;

    public TransactionCategory category;

    public User owner;

    public String description;

    public Transaction() { }

    public Transaction(Long id, Double amount, LocalDateTime date, LocalDateTime creationDate, TransactionType type, TransactionCategory category, User owner, String description) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.creationDate = creationDate;
        this.type = type;
        this.category = category;
        this.owner = owner;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public TransactionCategory getCategory() {
        return category;
    }

    public void setCategory(TransactionCategory category) {
        this.category = category;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
