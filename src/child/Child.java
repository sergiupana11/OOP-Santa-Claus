package child;

import enums.Category;
import enums.Cities;
import enums.ElvesType;
import gift.Gift;
import input.ChildInputData;
import visitor.Visitable;
import visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

public class Child implements Visitable {
    private Integer id;
    private String lastName;
    private String firstName;
    private Cities city;
    private Integer age;
    private List<Category> giftsPreferences;
    private Double averageScore;
    private List<Double> niceScoreHistory;
    private Double assignedBudget;
    private List<Gift> receivedGifts;
    private ElvesType elf;
    private Double niceScoreBonus;
    private Double averageScoreOfCity;
    private String cityName;

    public Child(final Integer id, final String lastName, final String firstName,
                 final Integer age, final Cities city) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
        this.city = city;
    }

    public Child(final Integer id, final String lastName, final String firstName,
                 final Cities city, final Integer age,
                 final List<Category> giftsPreferences, final Double averageScore,
                 final List<Double> niceScoreHistory, final Double assignedBudget,
                 final List<Gift> receivedGifts) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.city = city;
        this.age = age;
        this.giftsPreferences = new ArrayList<>(giftsPreferences);
        this.averageScore = averageScore;
        this.niceScoreHistory = new ArrayList<>(niceScoreHistory);
        this.assignedBudget = assignedBudget;
        this.receivedGifts = new ArrayList<>(receivedGifts);
    }

    public Child(final ChildInputData child) {
        this.id = child.getId();
        this.lastName = child.getLastName();
        this.firstName = child.getFirstName();
        this.age = child.getAge();
        this.city = child.getCity();
        this.giftsPreferences = child.getGiftsPreferences();
        assignedBudget = 0.0;
        averageScore = 0.0;
        receivedGifts = new ArrayList<>();
        niceScoreHistory = new ArrayList<>();
        this.getNiceScoreHistory().add(child.getNiceScore());
        this.elf = child.getElf();
        this.niceScoreBonus = child.getNiceScoreBonus();
    }

    public Child(final Child child) {
        this.id = child.id;
        this.lastName = child.lastName;
        this.firstName = child.firstName;
        this.city = child.city;
        this.age = child.age;
        this.giftsPreferences = child.giftsPreferences;
        this.averageScore = child.averageScore;
        this.niceScoreHistory = child.niceScoreHistory;
        this.receivedGifts = child.receivedGifts;
        this.elf = child.elf;
        this.niceScoreBonus = child.niceScoreBonus;
        this.receivedGifts.clear();
        this.assignedBudget = 0.0;
        this.averageScoreOfCity = child.averageScoreOfCity;
        this.cityName = child.cityName;
    }

    /**
     * Calculates average score and assigns it to the child.
     * It is overwritten by all of its subclasses, except for YoungAdult,
     * because objects of that class do not require an average score.
     */
    public void calculateAverageScore() {
    }

    public final String getCityName() {
        return cityName;
    }

    public final void setCityName(final String cityName) {
        this.cityName = cityName;
    }

    public final Double getAverageScoreOfCity() {
        return averageScoreOfCity;
    }

    public final void setAverageScoreOfCity(final Double averageScoreOfCity) {
        this.averageScoreOfCity = averageScoreOfCity;
    }

    /**
     * Function that accepts the visit from the visitor and applies its effect.
     * @param v the visitor (which can be one of the four types of elves:
     *                      Yellow, Pink, Black or White)
     */
    public final void accept(final Visitor v) {
        v.visit(this);
    }

    public final ElvesType getElf() {
        return elf;
    }

    public final void setElf(final ElvesType elf) {
        this.elf = elf;
    }

    /**
     * Increments the age of the child
     */
    public final void incrementAge() {
        age++;
    }

    public final Integer getId() {
        return id;
    }

    public final void setId(final Integer id) {
        this.id = id;
    }

    public final String getLastName() {
        return lastName;
    }

    public final void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public final String getFirstName() {
        return firstName;
    }

    public final void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public final Integer getAge() {
        return age;
    }

    public final void setAge(final Integer age) {
        this.age = age;
    }

    public final Cities getCity() {
        return city;
    }

    public final void setCity(final Cities city) {
        this.city = city;
    }

    public final Double getAverageScore() {
        return averageScore;
    }

    public final void setAverageScore(final Double averageScore) {
        this.averageScore = averageScore;
    }

    public final List<Category> getGiftsPreferences() {
        return giftsPreferences;
    }

    public final void setGiftsPreferences(final List<Category> giftsPreferences) {
        this.giftsPreferences = giftsPreferences;
    }

    public final List<Double> getNiceScoreHistory() {
        return niceScoreHistory;
    }

    public final void setNiceScoreHistory(final List<Double> niceScoreHistory) {
        this.niceScoreHistory = niceScoreHistory;
    }

    public final Double getAssignedBudget() {
        return assignedBudget;
    }

    public final void setAssignedBudget(final Double assignedBudget) {
        this.assignedBudget = assignedBudget;
    }

    public final List<Gift> getReceivedGifts() {
        return receivedGifts;
    }

    public final void setReceivedGifts(final List<Gift> receivedGifts) {
        this.receivedGifts = receivedGifts;
    }

    public final Double getNiceScoreBonus() {
        return niceScoreBonus;
    }

    public final void setNiceScoreBonus(final Double niceScoreBonus) {
        this.niceScoreBonus = niceScoreBonus;
    }
}
