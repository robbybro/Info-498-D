package com.tedneward.example;

import java.beans.*;
import java.util.*;

public class Person implements Comparable<Person>{
  private static int personCount = 0;
  private int age;
  private String name;
  private double salary;
  private String ssn;
  private boolean propertyChangeFired = false;

    public Person() {
    this("", 0, 0.0d);
  }
  
  public Person(String n, int a, double s) {
    this.personCount ++;
    name = n;
    age = a;
    salary = s;
    ssn = "";
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
      if(age < 0 ) {
          throw new IllegalArgumentException();
      }
      int old = age;
      this.age = age;
      pcs.firePropertyChange(new PropertyChangeEvent(this, "age", old, age));
      propertyChangeFired = true;

  }
  
  public String getName() {
    return name;
  }

  public void setName(String name) {
      if(name == null) {
          throw new IllegalArgumentException();
      }
      String old = name;
      this.name = name;
      pcs.firePropertyChange(new PropertyChangeEvent(this, "name", old, name));
      propertyChangeFired = true;

  }

  public double getSalary() {
    return salary;
  }

  public void setSalary(double salary) {
      double old = salary;
      this.salary = salary;
      pcs.firePropertyChange(new PropertyChangeEvent(this, "salary", old, salary));
      propertyChangeFired = true;

  }

  public String getSSN() {
    return this.ssn;
  }

    public void setSSN(String value) {
    String old = ssn;
    ssn = value;

    pcs.firePropertyChange(new PropertyChangeEvent(this, "ssn", old, ssn));
    propertyChangeFired = true;
  }

  public boolean getPropertyChangeFired() {
    return propertyChangeFired;
  }

  public double calculateBonus() {
    return salary * 1.10;
  }
  
  public String becomeJudge() {
    return "The Honorable " + name;
  }
  
  public int timeWarp() {
    return age + 10;
  }

  public int count() {
      return this.personCount;
  }

  public static ArrayList<Person> getNewardFamily() {
      Person ted = new Person("Ted", 41, 250000.00);
      Person charlotte = new Person("Charlotte", 43, 150000.00);
      Person michael = new Person("Michael", 22, 10000.00);
      Person matthew = new Person("Matthew", 15, 0.00);

      ArrayList<Person> family = new ArrayList<Person>();
      family.add(ted);
      family.add(charlotte);
      family.add(michael);
      family.add(matthew);

      return family;
  }
  
  public boolean equals(Object other) {
    if(other instanceof Person) {
        Person p = (Person) other;
        return (this.name.equals(p.name) && this.age == p.age);
    }
    return false;
  }

  // sort by salary desc
  public int compareTo(Person comparePerson) {
      return (int) comparePerson.getSalary() - (int) this.getSalary();
  }


   public static class AgeComparator implements Comparator<Person> {
      public int compare(Person p1, Person p2) {
          if(p2.getAge() > p1.getAge()) {
              return -1;
          } else if(p2.getAge() == p1.getAge()) {
              return 0;
          } else {
              return 1;
          }
      }
  };
  public String toString() {
      //[Person name:Fird Birfle age:20 salary:195750.22]
      return "[Person name:" + this.name + " age:" + this.age + " salary:" + this.salary + "]";
  }

  // PropertyChangeListener support; you shouldn't need to change any of
  // these two methods or the field
  //
  private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
  public void addPropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.addPropertyChangeListener(listener);
  }
  public void removePropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.removePropertyChangeListener(listener);
  }
}
