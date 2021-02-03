package com.rarl.sj.entity;


import javax.persistence.*;
import javax.persistence.Basic;
import javax.persistence.Column;
import java.util.Objects;

@Entity
@Table(name = "food", schema = "SJ")
public class FoodEntity {
    private String name;
    private int id;
    private Integer evaluation1;
    private Integer evaluation2;
    private String f_name;

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Evaluation_1")
    public Integer getEvaluation1() {
        return evaluation1;
    }

    public void setEvaluation1(Integer evaluation1) {
        this.evaluation1 = evaluation1;
    }

    @Basic
    @Column(name = "Evaluation_2")
    public Integer getEvaluation2() {
        return evaluation2;
    }

    public void setEvaluation2(Integer evaluation2) {
        this.evaluation2 = evaluation2;
    }

    @Basic
    @Column(name = "f_name")
    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FoodEntity that = (FoodEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(evaluation1, that.evaluation1) &&
                Objects.equals(evaluation2, that.evaluation2);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, id, evaluation1, evaluation2);
    }

    @Override
    public String toString() {
        return "FoodEntity{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", evaluation1=" + evaluation1 +
                ", evaluation2=" + evaluation2 +
                ", f_name=" + f_name +
                '}';
    }
}
