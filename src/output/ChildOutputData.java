package output;

import enums.Category;
import enums.Cities;
import gift.Gift;

import java.util.ArrayList;
import java.util.List;

public class ChildOutputData {
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

    public ChildOutputData(final Integer id, final String lastName, final String firstName,
                           final Cities city, final Integer age,
                           final List<Category> giftsPreferences,
                           final Double averageScore, final List<Double> niceScoreHistory,
                           final Double assignedBudget, final List<Gift> receivedGifts) {
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

    public final Cities getCity() {
        return city;
    }

    public final void setCity(final Cities city) {
        this.city = city;
    }

    public final Integer getAge() {
        return age;
    }

    public final void setAge(final Integer age) {
        this.age = age;
    }

    public final List<Category> getGiftsPreferences() {
        return giftsPreferences;
    }

    public final void setGiftsPreferences(final List<Category> giftsPreferences) {
        this.giftsPreferences = giftsPreferences;
    }

    public final Double getAverageScore() {
        return averageScore;
    }

    public final void setAverageScore(final Double averageScore) {
        this.averageScore = averageScore;
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
}
