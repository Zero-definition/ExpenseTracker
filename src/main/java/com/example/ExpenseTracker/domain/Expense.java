package com.example.ExpenseTracker.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

@Entity
@Table(name = "expense")
public class Expense {

    @Id
    @Column(name = "id",nullable = false,updatable = false)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cost",nullable = false,updatable = false)
    @NotNull(message = "The cost of the expense can not be null")
    @Digits(integer = 10,fraction = 4,message = "The cost of the expense should contain {0} integers "+
                                                    "and {1} fractional digits")
    private BigDecimal cost;

    @Column(name = "title",nullable = false,updatable = false)
    @NotNull(message = "The title of the asset can not be null")
    @NotBlank(message = "The title of the asset can not be blank")
    @NotEmpty(message = "The title of the expense can not be empty")
    private String title;

    @Column(name = "dateid",nullable = false,updatable = false)
    private int dateid;


    @Column(name = "purchaseDate",nullable = false,updatable = false)
    @PastOrPresent(message = "The date of the expense can only be in the past or the present")
    private Date dateOfExpense;

    private Expense(){
        super();
    }

    public Expense( final String title,
                    final BigDecimal cost,
                    final Date dateOfExpense) {

        this.title = title;
        this.cost = cost;
        this.dateOfExpense = dateOfExpense;
        //setDate(dateOfExpense);
        setDateid(dateOfExpense);
    }

    /**
     * Get the cost of the expense
     *
     * @return the cost of the expense
     */
    public BigDecimal getCost() {
        return cost;
    }


    /**
     * Gets the date  of the expense
     *
     * @return the date of the expense
     */
    public Date getDateOfExpense() {
        return dateOfExpense;
    }

    /**
     * Gets the id of the expense
     *
     * @return the id of the expense
     */
    public Long getId() {
        return id;
    }

    /**
     * Get the title of the expense
     *
     * @return the title of the expense
     */
    public String getTitle() {
        return title;
    }

    public void setCost(final BigDecimal cost) {
        this.cost = cost;
    }

    public void setDateOfExpense(final Date dateOfExpense) {
        this.dateOfExpense = dateOfExpense;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public int getDateid() {
        return dateid;
    }

    public void setDateid(final Date dateOfExpense) {
        Instant instant = dateOfExpense.toInstant();
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
        LocalDate localDate = zonedDateTime.toLocalDate();

        this.dateid=localDate.getYear()*100 + localDate.getMonthValue();
    }

    /**
    @ManyToOne
    @JoinColumn(name = "year_month_id", nullable = false, updatable = false)
    private YearMonth date;

    public void setDate(final Date dateOfExpense) {

        Instant instant = dateOfExpense.toInstant();
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
        LocalDate localDate = zonedDateTime.toLocalDate();

        this.date=new YearMonth(localDate.getYear(),localDate.getMonthValue());
    }

    public YearMonth getDate() {
        return date;
    }
    */
}
